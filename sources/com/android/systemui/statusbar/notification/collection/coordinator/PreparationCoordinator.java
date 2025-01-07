package com.android.systemui.statusbar.notification.collection.coordinator;

import android.app.Notification;
import android.app.RemoteInput;
import android.graphics.drawable.Icon;
import android.os.Trace;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListAttachState;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifInflaterImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManagerImpl;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflater;
import com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustment;
import com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentProvider;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.collection.render.NotifViewBarn;
import com.android.systemui.statusbar.notification.row.NotifInflationErrorManager;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionControllerImpl;
import com.android.systemui.util.ListenerSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.MergingSequence;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class PreparationCoordinator implements Coordinator {
    public final NotifUiAdjustmentProvider mAdjustmentProvider;
    public final BindEventManagerImpl mBindEventManager;
    public final int mChildBindCutoff;
    public final PreparationCoordinatorLogger mLogger;
    public final long mMaxGroupInflationDelay;
    public final NotifInflationErrorManager mNotifErrorManager;
    public final NotifInflater mNotifInflater;
    public final IStatusBarService mStatusBarService;
    public final NotifViewBarn mViewBarn;
    public final ArrayMap mInflationStates = new ArrayMap();
    public final ArrayMap mInflationAdjustments = new ArrayMap();
    public final ArraySet mInflatingNotifs = new ArraySet();
    public final AnonymousClass1 mNotifCollectionListener = new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator.1
        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryCleanUp(NotificationEntry notificationEntry) {
            PreparationCoordinator preparationCoordinator = PreparationCoordinator.this;
            preparationCoordinator.mInflationStates.remove(notificationEntry);
            preparationCoordinator.mViewBarn.rowMap.remove(notificationEntry.mKey);
            preparationCoordinator.mInflationAdjustments.remove(notificationEntry);
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryInit(NotificationEntry notificationEntry) {
            PreparationCoordinator.this.mInflationStates.put(notificationEntry, 0);
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
            PreparationCoordinator.this.abortInflation(notificationEntry, "entryRemoved reason=" + i);
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryUpdated(NotificationEntry notificationEntry) {
            PreparationCoordinator preparationCoordinator = PreparationCoordinator.this;
            preparationCoordinator.abortInflation(notificationEntry, "entryUpdated");
            int inflationState = preparationCoordinator.getInflationState(notificationEntry);
            if (inflationState == 1) {
                preparationCoordinator.mInflationStates.put(notificationEntry, 2);
            } else if (inflationState == -1) {
                preparationCoordinator.mInflationStates.put(notificationEntry, 0);
            }
        }
    };
    public final AnonymousClass2 mNotifInflationErrorFilter = new NotifFilter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator.2
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            return PreparationCoordinator.this.getInflationState(notificationEntry) == -1;
        }
    };
    public final AnonymousClass3 mNotifInflatingFilter = new NotifFilter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator.3
        public final Map mIsDelayedGroupCache = new ArrayMap();

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.Pluggable
        public final void onCleanup() {
            ((ArrayMap) this.mIsDelayedGroupCache).clear();
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            boolean z;
            int inflationState;
            GroupEntry groupEntry = notificationEntry.mAttachState.parent;
            Objects.requireNonNull(groupEntry);
            Boolean bool = (Boolean) ((ArrayMap) this.mIsDelayedGroupCache).get(groupEntry);
            PreparationCoordinator preparationCoordinator = PreparationCoordinator.this;
            if (bool == null) {
                if (groupEntry != GroupEntry.ROOT_ENTRY && groupEntry.mPreviousAttachState.parent == null) {
                    long j2 = j - groupEntry.mCreationTime;
                    long j3 = preparationCoordinator.mMaxGroupInflationDelay;
                    PreparationCoordinatorLogger preparationCoordinatorLogger = preparationCoordinator.mLogger;
                    if (j2 > j3) {
                        preparationCoordinatorLogger.getClass();
                        LogLevel logLevel = LogLevel.WARNING;
                        PreparationCoordinatorLogger$logGroupInflationTookTooLong$2 preparationCoordinatorLogger$logGroupInflationTookTooLong$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinatorLogger$logGroupInflationTookTooLong$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Group inflation took too long for ", ((LogMessage) obj).getStr1(), ", releasing children early");
                            }
                        };
                        LogBuffer logBuffer = preparationCoordinatorLogger.buffer;
                        LogMessage obtain = logBuffer.obtain("PreparationCoordinator", logLevel, preparationCoordinatorLogger$logGroupInflationTookTooLong$2, null);
                        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(groupEntry);
                        logBuffer.commit(obtain);
                    } else {
                        NotificationEntry notificationEntry2 = groupEntry.mSummary;
                        if (notificationEntry2 == null || (inflationState = preparationCoordinator.getInflationState(notificationEntry2)) == 1 || inflationState == 2) {
                            for (NotificationEntry notificationEntry3 : groupEntry.mUnmodifiableChildren) {
                                if (preparationCoordinator.mInflatingNotifs.contains(notificationEntry3) && notificationEntry3.mPreviousAttachState.parent == null) {
                                    preparationCoordinatorLogger.logDelayingGroupRelease(groupEntry, notificationEntry3);
                                }
                            }
                            preparationCoordinatorLogger.getClass();
                            LogLevel logLevel2 = LogLevel.DEBUG;
                            PreparationCoordinatorLogger$logDoneWaitingForGroupInflation$2 preparationCoordinatorLogger$logDoneWaitingForGroupInflation$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinatorLogger$logDoneWaitingForGroupInflation$2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Finished inflating all members of group ", ((LogMessage) obj).getStr1(), ", releasing group");
                                }
                            };
                            LogBuffer logBuffer2 = preparationCoordinatorLogger.buffer;
                            LogMessage obtain2 = logBuffer2.obtain("PreparationCoordinator", logLevel2, preparationCoordinatorLogger$logDoneWaitingForGroupInflation$2, null);
                            ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(groupEntry);
                            logBuffer2.commit(obtain2);
                        } else {
                            preparationCoordinatorLogger.logDelayingGroupRelease(groupEntry, groupEntry.mSummary);
                        }
                        z = true;
                        bool = Boolean.valueOf(z);
                        ((ArrayMap) this.mIsDelayedGroupCache).put(groupEntry, bool);
                    }
                }
                z = false;
                bool = Boolean.valueOf(z);
                ((ArrayMap) this.mIsDelayedGroupCache).put(groupEntry, bool);
            }
            int inflationState2 = preparationCoordinator.getInflationState(notificationEntry);
            return !(inflationState2 == 1 || inflationState2 == 2) || bool.booleanValue();
        }
    };
    public final AnonymousClass4 mInflationErrorListener = new AnonymousClass4();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator$4, reason: invalid class name */
    public final class AnonymousClass4 {
        public AnonymousClass4() {
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator$1] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator$2] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator$3] */
    public PreparationCoordinator(PreparationCoordinatorLogger preparationCoordinatorLogger, NotifInflater notifInflater, NotifInflationErrorManager notifInflationErrorManager, NotifViewBarn notifViewBarn, NotifUiAdjustmentProvider notifUiAdjustmentProvider, IStatusBarService iStatusBarService, BindEventManagerImpl bindEventManagerImpl, int i, long j) {
        this.mLogger = preparationCoordinatorLogger;
        this.mNotifInflater = notifInflater;
        this.mNotifErrorManager = notifInflationErrorManager;
        this.mViewBarn = notifViewBarn;
        this.mAdjustmentProvider = notifUiAdjustmentProvider;
        this.mStatusBarService = iStatusBarService;
        this.mChildBindCutoff = i;
        this.mMaxGroupInflationDelay = j;
        this.mBindEventManager = bindEventManagerImpl;
    }

    public final void abortInflation(NotificationEntry notificationEntry, String str) {
        boolean abortInflation = ((NotifInflaterImpl) this.mNotifInflater).abortInflation(notificationEntry);
        boolean remove = this.mInflatingNotifs.remove(notificationEntry);
        if (abortInflation || remove) {
            PreparationCoordinatorLogger preparationCoordinatorLogger = this.mLogger;
            preparationCoordinatorLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            PreparationCoordinatorLogger$logInflationAborted$2 preparationCoordinatorLogger$logInflationAborted$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinatorLogger$logInflationAborted$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Infation aborted for notif ", logMessage.getStr1(), " reason=", logMessage.getStr2());
                }
            };
            LogBuffer logBuffer = preparationCoordinatorLogger.buffer;
            LogMessage obtain = logBuffer.obtain("PreparationCoordinator", logLevel, preparationCoordinatorLogger$logInflationAborted$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logMessageImpl.str2 = str;
            logBuffer.commit(obtain);
        }
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        this.mNotifErrorManager.mListeners.add(this.mInflationErrorListener);
        Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                invalidateList("adjustmentProviderChanged");
            }
        };
        NotifUiAdjustmentProvider notifUiAdjustmentProvider = this.mAdjustmentProvider;
        ListenerSet listenerSet = notifUiAdjustmentProvider.dirtyListeners;
        if (listenerSet.listeners.isEmpty()) {
            ((NotificationLockscreenUserManagerImpl) notifUiAdjustmentProvider.lockscreenUserManager).mNotifStateChangedListeners.addIfAbsent(notifUiAdjustmentProvider.notifStateChangedListener);
            ((SensitiveNotificationProtectionControllerImpl) notifUiAdjustmentProvider.sensitiveNotifProtectionController).mListeners.addIfAbsent(notifUiAdjustmentProvider.onSensitiveStateChangedListener);
            notifUiAdjustmentProvider.updateSnoozeEnabled();
            notifUiAdjustmentProvider.secureSettings.registerContentObserverForUserSync("show_notification_snooze", notifUiAdjustmentProvider.settingsObserver, -1);
        }
        listenerSet.addIfAbsent(runnable);
        notifPipeline.addCollectionListener(this.mNotifCollectionListener);
        notifPipeline.addOnBeforeFinalizeFilterListener(new OnBeforeFinalizeFilterListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator$$ExternalSyntheticLambda1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener
            public final void onBeforeFinalizeFilter(List list) {
                PreparationCoordinator preparationCoordinator = PreparationCoordinator.this;
                preparationCoordinator.getClass();
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ListEntry listEntry = (ListEntry) list.get(i);
                    if (listEntry instanceof GroupEntry) {
                        GroupEntry groupEntry = (GroupEntry) listEntry;
                        NotificationEntry notificationEntry = groupEntry.mSummary;
                        if (notificationEntry != null) {
                            notificationEntry.mHasEverBeenGroupSummary = true;
                        }
                        List list2 = groupEntry.mUnmodifiableChildren;
                        preparationCoordinator.inflateRequiredNotifViews(notificationEntry);
                        for (int i2 = 0; i2 < list2.size(); i2++) {
                            NotificationEntry notificationEntry2 = (NotificationEntry) list2.get(i2);
                            notificationEntry2.mHasEverBeenGroupChild = true;
                            if (i2 < preparationCoordinator.mChildBindCutoff) {
                                preparationCoordinator.inflateRequiredNotifViews(notificationEntry2);
                            } else {
                                if (preparationCoordinator.mInflatingNotifs.contains(notificationEntry2)) {
                                    preparationCoordinator.abortInflation(notificationEntry2, "Past last visible group child");
                                }
                                int inflationState = preparationCoordinator.getInflationState(notificationEntry2);
                                if (inflationState == 1 || inflationState == 2) {
                                    PreparationCoordinatorLogger preparationCoordinatorLogger = preparationCoordinator.mLogger;
                                    preparationCoordinatorLogger.getClass();
                                    LogLevel logLevel = LogLevel.DEBUG;
                                    PreparationCoordinatorLogger$logFreeNotifViews$2 preparationCoordinatorLogger$logFreeNotifViews$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinatorLogger$logFreeNotifViews$2
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            LogMessage logMessage = (LogMessage) obj;
                                            return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Freeing content views for notif ", logMessage.getStr1(), " reason=", logMessage.getStr2());
                                        }
                                    };
                                    LogBuffer logBuffer = preparationCoordinatorLogger.buffer;
                                    LogMessage obtain = logBuffer.obtain("PreparationCoordinator", logLevel, preparationCoordinatorLogger$logFreeNotifViews$2, null);
                                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                                    logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry2);
                                    logMessageImpl.str2 = "Past last visible group child";
                                    logBuffer.commit(obtain);
                                    preparationCoordinator.mViewBarn.rowMap.remove(notificationEntry2.mKey);
                                    ((NotifInflaterImpl) preparationCoordinator.mNotifInflater).releaseViews(notificationEntry2);
                                    preparationCoordinator.mInflationStates.put(notificationEntry2, 0);
                                }
                            }
                        }
                    } else {
                        preparationCoordinator.inflateRequiredNotifViews((NotificationEntry) listEntry);
                    }
                }
            }
        });
        notifPipeline.addFinalizeFilter(this.mNotifInflationErrorFilter);
        notifPipeline.addFinalizeFilter(this.mNotifInflatingFilter);
    }

    public final int getInflationState(NotificationEntry notificationEntry) {
        Integer num = (Integer) this.mInflationStates.get(notificationEntry);
        Objects.requireNonNull(num, "Asking state of a notification preparation coordinator doesn't know about");
        return num.intValue();
    }

    public final void inflateEntry(NotificationEntry notificationEntry, NotifUiAdjustment notifUiAdjustment, String str) {
        Trace.beginSection("PrepCoord.inflateEntry");
        abortInflation(notificationEntry, str);
        this.mInflationAdjustments.put(notificationEntry, notifUiAdjustment);
        this.mInflatingNotifs.add(notificationEntry);
        ((NotifInflaterImpl) this.mNotifInflater).inflateViews(notificationEntry, new NotifInflater.Params(notifUiAdjustment.isMinimized, str, notifUiAdjustment.isSnoozeEnabled, notifUiAdjustment.isChildInGroup, notifUiAdjustment.isGroupSummary, notifUiAdjustment.needsRedaction), new PreparationCoordinator$$ExternalSyntheticLambda2(this));
        Trace.endSection();
    }

    public final void inflateRequiredNotifViews(NotificationEntry notificationEntry) {
        NotifUiAdjustmentProvider notifUiAdjustmentProvider = this.mAdjustmentProvider;
        notifUiAdjustmentProvider.getClass();
        String str = notificationEntry.mKey;
        List<Notification.Action> smartActions = notificationEntry.mRanking.getSmartActions();
        List<CharSequence> smartReplies = notificationEntry.mRanking.getSmartReplies();
        boolean isConversation = notificationEntry.mRanking.isConversation();
        boolean z = notifUiAdjustmentProvider.isSnoozeSettingsEnabled && !notificationEntry.isCanceled();
        ListAttachState listAttachState = notificationEntry.mAttachState;
        NotifSection notifSection = listAttachState.section;
        if (notifSection == null) {
            throw new IllegalStateException("Entry must have a section to determine if minimized");
        }
        GroupEntry groupEntry = listAttachState.parent;
        if (groupEntry == null) {
            throw new IllegalStateException("Entry must have a parent to determine if minimized");
        }
        Set set = notifUiAdjustmentProvider.sectionStyleProvider.lowPrioritySections;
        if (set == null) {
            set = null;
        }
        boolean z2 = set.contains(notifSection.sectioner) && (groupEntry.equals(GroupEntry.ROOT_ENTRY) || Intrinsics.areEqual(groupEntry.mSummary, notificationEntry));
        boolean z3 = ((NotificationLockscreenUserManagerImpl) notifUiAdjustmentProvider.lockscreenUserManager).needsRedaction(notificationEntry) || ((SensitiveNotificationProtectionControllerImpl) notifUiAdjustmentProvider.sensitiveNotifProtectionController).shouldProtectNotification(notificationEntry);
        boolean z4 = notificationEntry.mHasEverBeenGroupChild;
        boolean z5 = notificationEntry.mHasEverBeenGroupSummary;
        NotifUiAdjustment notifUiAdjustment = new NotifUiAdjustment(smartActions, smartReplies, isConversation, z, z2, z3, z4, z5);
        if (this.mInflatingNotifs.contains(notificationEntry)) {
            if (needToReinflate(notificationEntry, notifUiAdjustment, "Inflating notification has no adjustments")) {
                inflateEntry(notificationEntry, notifUiAdjustment, "adjustment changed while inflating");
                return;
            }
            return;
        }
        int intValue = ((Integer) this.mInflationStates.get(notificationEntry)).intValue();
        if (intValue == -1) {
            if (needToReinflate(notificationEntry, notifUiAdjustment, null)) {
                inflateEntry(notificationEntry, notifUiAdjustment, "adjustment changed after error");
                return;
            }
            return;
        }
        if (intValue == 0) {
            inflateEntry(notificationEntry, notifUiAdjustment, "entryAdded");
            return;
        }
        NotifInflater notifInflater = this.mNotifInflater;
        if (intValue != 1) {
            if (intValue != 2) {
                return;
            }
            this.mInflationAdjustments.put(notificationEntry, notifUiAdjustment);
            this.mInflatingNotifs.add(notificationEntry);
            ((NotifInflaterImpl) notifInflater).rebindViews(notificationEntry, new NotifInflater.Params(z2, "entryUpdated", z, z4, z5, z3), new PreparationCoordinator$$ExternalSyntheticLambda2(this));
            return;
        }
        if (needToReinflate(notificationEntry, notifUiAdjustment, "Fully inflated notification has no adjustments")) {
            this.mInflationAdjustments.put(notificationEntry, notifUiAdjustment);
            this.mInflatingNotifs.add(notificationEntry);
            ((NotifInflaterImpl) notifInflater).rebindViews(notificationEntry, new NotifInflater.Params(z2, "adjustment changed after inflated", z, z4, z5, z3), new PreparationCoordinator$$ExternalSyntheticLambda2(this));
        }
    }

    public final boolean needToReinflate(NotificationEntry notificationEntry, NotifUiAdjustment notifUiAdjustment, String str) {
        NotifUiAdjustment notifUiAdjustment2 = (NotifUiAdjustment) this.mInflationAdjustments.get(notificationEntry);
        if (notifUiAdjustment2 == null) {
            if (str == null) {
                return true;
            }
            throw new IllegalStateException(str);
        }
        if (notifUiAdjustment2 != notifUiAdjustment) {
            if (notifUiAdjustment2.isConversation != notifUiAdjustment.isConversation || notifUiAdjustment2.isSnoozeEnabled != notifUiAdjustment.isSnoozeEnabled || notifUiAdjustment2.isMinimized != notifUiAdjustment.isMinimized || notifUiAdjustment2.needsRedaction != notifUiAdjustment.needsRedaction) {
                return true;
            }
            List list = notifUiAdjustment2.smartActions;
            List list2 = notifUiAdjustment.smartActions;
            if (list != list2) {
                if (list.size() != list2.size()) {
                    return true;
                }
                MergingSequence zip = SequencesKt.zip(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(list), new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(list2));
                Iterator it = zip.sequence1.iterator();
                Iterator it2 = zip.sequence2.iterator();
                while (it.hasNext() && it2.hasNext()) {
                    Pair pair = (Pair) zip.transform.invoke(it.next(), it2.next());
                    if (!TextUtils.equals(((Notification.Action) pair.getFirst()).title, ((Notification.Action) pair.getSecond()).title)) {
                        return true;
                    }
                    Icon icon = ((Notification.Action) pair.getFirst()).getIcon();
                    Icon icon2 = ((Notification.Action) pair.getSecond()).getIcon();
                    if ((icon != icon2 && (icon == null || icon2 == null || !icon.sameAs(icon2))) || !Intrinsics.areEqual(((Notification.Action) pair.getFirst()).actionIntent, ((Notification.Action) pair.getSecond()).actionIntent)) {
                        return true;
                    }
                    RemoteInput[] remoteInputs = ((Notification.Action) pair.getFirst()).getRemoteInputs();
                    RemoteInput[] remoteInputs2 = ((Notification.Action) pair.getSecond()).getRemoteInputs();
                    if (remoteInputs != remoteInputs2) {
                        if (remoteInputs == null || remoteInputs2 == null || remoteInputs.length != remoteInputs2.length) {
                            return true;
                        }
                        MergingSequence zip2 = SequencesKt.zip(ArraysKt.asSequence(remoteInputs), ArraysKt.asSequence(remoteInputs2));
                        Iterator it3 = zip2.sequence1.iterator();
                        Iterator it4 = zip2.sequence2.iterator();
                        while (it3.hasNext() && it4.hasNext()) {
                            Pair pair2 = (Pair) zip2.transform.invoke(it3.next(), it4.next());
                            if (!TextUtils.equals(((RemoteInput) pair2.getFirst()).getLabel(), ((RemoteInput) pair2.getSecond()).getLabel())) {
                                return true;
                            }
                            CharSequence[] choices = ((RemoteInput) pair2.getFirst()).getChoices();
                            CharSequence[] choices2 = ((RemoteInput) pair2.getSecond()).getChoices();
                            if (choices != choices2) {
                                if (choices == null || choices2 == null || choices.length != choices2.length) {
                                    return true;
                                }
                                MergingSequence zip3 = SequencesKt.zip(ArraysKt.asSequence(choices), ArraysKt.asSequence(choices2));
                                Iterator it5 = zip3.sequence1.iterator();
                                Iterator it6 = zip3.sequence2.iterator();
                                while (it5.hasNext() && it6.hasNext()) {
                                    Pair pair3 = (Pair) zip3.transform.invoke(it5.next(), it6.next());
                                    if (!TextUtils.equals((CharSequence) pair3.getFirst(), (CharSequence) pair3.getSecond())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!Intrinsics.areEqual(notifUiAdjustment.smartReplies, notifUiAdjustment2.smartReplies)) {
                return true;
            }
            if (!notifUiAdjustment2.isChildInGroup && notifUiAdjustment.isChildInGroup) {
                return true;
            }
            if (!notifUiAdjustment2.isGroupSummary && notifUiAdjustment.isGroupSummary) {
                return true;
            }
        }
        return false;
    }
}
