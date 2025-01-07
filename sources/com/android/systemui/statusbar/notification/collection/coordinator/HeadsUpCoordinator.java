package com.android.systemui.statusbar.notification.collection.coordinator;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.util.ArrayMap;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda12;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender;
import com.android.systemui.statusbar.notification.collection.provider.LaunchFullScreenIntentProvider;
import com.android.systemui.statusbar.notification.collection.render.NodeController;
import com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinder;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProviderImpl;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.util.Assert;
import com.android.systemui.util.NamedListenerSet;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HeadsUpCoordinator implements Coordinator {
    public NotifCollection$$ExternalSyntheticLambda12 mEndLifetimeExtension;
    public final DelayableExecutor mExecutor;
    public final HeadsUpManager mHeadsUpManager;
    public final HeadsUpViewBinder mHeadsUpViewBinder;
    public final LaunchFullScreenIntentProvider mLaunchFullScreenIntentProvider;
    public final HeadsUpCoordinatorLogger mLogger;
    public NotifPipeline mNotifPipeline;
    public final NotificationRemoteInputManager mRemoteInputManager;
    public final SystemClock mSystemClock;
    public final VisualInterruptionDecisionProvider mVisualInterruptionDecisionProvider;
    public final ArrayMap mEntriesBindingUntil = new ArrayMap();
    public final ArrayMap mEntriesUpdateTimes = new ArrayMap();
    public final ArrayMap mFSIUpdateCandidates = new ArrayMap();
    public long mNow = -1;
    public final LinkedHashMap mPostedEntries = new LinkedHashMap();
    public final ArrayMap mNotifsExtendingLifetime = new ArrayMap();
    public final HeadsUpCoordinator$mNotifCollectionListener$1 mNotifCollectionListener = new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mNotifCollectionListener$1
        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryAdded(NotificationEntry notificationEntry) {
            HeadsUpCoordinator headsUpCoordinator = HeadsUpCoordinator.this;
            VisualInterruptionDecisionProvider.FullScreenIntentDecision makeUnloggedFullScreenIntentDecision = ((VisualInterruptionDecisionProviderImpl) headsUpCoordinator.mVisualInterruptionDecisionProvider).makeUnloggedFullScreenIntentDecision(notificationEntry);
            VisualInterruptionDecisionProvider visualInterruptionDecisionProvider = headsUpCoordinator.mVisualInterruptionDecisionProvider;
            ((VisualInterruptionDecisionProviderImpl) visualInterruptionDecisionProvider).logFullScreenIntentDecision(makeUnloggedFullScreenIntentDecision);
            VisualInterruptionDecisionProviderImpl.FullScreenIntentDecisionImpl fullScreenIntentDecisionImpl = (VisualInterruptionDecisionProviderImpl.FullScreenIntentDecisionImpl) makeUnloggedFullScreenIntentDecision;
            boolean shouldInterrupt = fullScreenIntentDecisionImpl.getShouldInterrupt();
            SystemClock systemClock = headsUpCoordinator.mSystemClock;
            if (shouldInterrupt) {
                headsUpCoordinator.mLaunchFullScreenIntentProvider.launchFullScreenIntent(notificationEntry);
            } else if (fullScreenIntentDecisionImpl.getWouldInterruptWithoutDnd()) {
                ((SystemClockImpl) systemClock).getClass();
                headsUpCoordinator.addForFSIReconsideration(notificationEntry, System.currentTimeMillis());
            }
            boolean z = ((VisualInterruptionDecisionProviderImpl.DecisionImpl) ((VisualInterruptionDecisionProviderImpl) visualInterruptionDecisionProvider).makeAndLogHeadsUpDecision(notificationEntry)).shouldInterrupt;
            headsUpCoordinator.mPostedEntries.put(notificationEntry.mKey, new HeadsUpCoordinator.PostedEntry(notificationEntry, true, false, z, true, false, false));
            ((SystemClockImpl) systemClock).getClass();
            headsUpCoordinator.setUpdateTime(notificationEntry, System.currentTimeMillis());
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryCleanUp(NotificationEntry notificationEntry) {
            HeadsUpCoordinator.this.mHeadsUpViewBinder.abortBindCallback(notificationEntry);
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
            HeadsUpCoordinator headsUpCoordinator = HeadsUpCoordinator.this;
            LinkedHashMap linkedHashMap = headsUpCoordinator.mPostedEntries;
            String str = notificationEntry.mKey;
            linkedHashMap.remove(str);
            headsUpCoordinator.mEntriesUpdateTimes.remove(str);
            headsUpCoordinator.cancelHeadsUpBind(notificationEntry);
            HeadsUpManager headsUpManager = headsUpCoordinator.mHeadsUpManager;
            if (((BaseHeadsUpManager) headsUpManager).isHeadsUpEntry(str)) {
                RemoteInputController remoteInputController = headsUpCoordinator.mRemoteInputManager.mRemoteInputController;
                ((BaseHeadsUpManager) headsUpManager).removeNotification$1(str, AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "onEntryRemoved, reason: "), (remoteInputController == null || !remoteInputController.mSpinning.containsKey(str) || NotificationRemoteInputManager.FORCE_REMOTE_INPUT_HISTORY) ? false : true);
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryUpdated(final NotificationEntry notificationEntry) {
            HeadsUpCoordinator headsUpCoordinator = HeadsUpCoordinator.this;
            final boolean z = ((VisualInterruptionDecisionProviderImpl.DecisionImpl) ((VisualInterruptionDecisionProviderImpl) headsUpCoordinator.mVisualInterruptionDecisionProvider).makeAndLogHeadsUpDecision(notificationEntry)).shouldInterrupt;
            final boolean z2 = !notificationEntry.interruption || (notificationEntry.mSbn.getNotification().flags & 8) == 0;
            HeadsUpManager headsUpManager = headsUpCoordinator.mHeadsUpManager;
            String str = notificationEntry.mKey;
            final boolean isHeadsUpEntry = ((BaseHeadsUpManager) headsUpManager).isHeadsUpEntry(str);
            final boolean isEntryBinding = headsUpCoordinator.isEntryBinding(notificationEntry);
            HeadsUpCoordinator.PostedEntry postedEntry = (HeadsUpCoordinator.PostedEntry) headsUpCoordinator.mPostedEntries.compute(str, new BiFunction() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mNotifCollectionListener$1$onEntryUpdated$posted$1
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    HeadsUpCoordinator.PostedEntry postedEntry2 = (HeadsUpCoordinator.PostedEntry) obj2;
                    if (postedEntry2 == null) {
                        return new HeadsUpCoordinator.PostedEntry(NotificationEntry.this, false, true, z, z2, isHeadsUpEntry, isEntryBinding);
                    }
                    boolean z3 = z;
                    boolean z4 = z2;
                    boolean z5 = isHeadsUpEntry;
                    boolean z6 = isEntryBinding;
                    boolean z7 = true;
                    postedEntry2.wasUpdated = true;
                    postedEntry2.shouldHeadsUpEver = z3;
                    if (!postedEntry2.shouldHeadsUpAgain && !z4) {
                        z7 = false;
                    }
                    postedEntry2.shouldHeadsUpAgain = z7;
                    postedEntry2.isHeadsUpEntry = z5;
                    postedEntry2.isBinding = z6;
                    return postedEntry2;
                }
            });
            if (postedEntry != null && !postedEntry.shouldHeadsUpEver) {
                if (postedEntry.isHeadsUpEntry) {
                    ((BaseHeadsUpManager) headsUpManager).removeNotification$1(postedEntry.key, "onEntryUpdated", false);
                } else if (postedEntry.isBinding) {
                    headsUpCoordinator.cancelHeadsUpBind(postedEntry.entry);
                }
            }
            ((SystemClockImpl) headsUpCoordinator.mSystemClock).getClass();
            headsUpCoordinator.setUpdateTime(notificationEntry, System.currentTimeMillis());
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onRankingApplied() {
            HeadsUpCoordinator headsUpCoordinator = HeadsUpCoordinator.this;
            NotifPipeline notifPipeline = headsUpCoordinator.mNotifPipeline;
            if (notifPipeline == null) {
                notifPipeline = null;
            }
            for (NotificationEntry notificationEntry : notifPipeline.getAllNotifs()) {
                if (headsUpCoordinator.mEntriesUpdateTimes.containsKey(notificationEntry.mKey)) {
                    ArrayMap arrayMap = headsUpCoordinator.mEntriesUpdateTimes;
                    String str = notificationEntry.mKey;
                    Long l = (Long) arrayMap.get(str);
                    if (l != null) {
                        long longValue = l.longValue();
                        SystemClock systemClock = headsUpCoordinator.mSystemClock;
                        ((SystemClockImpl) systemClock).getClass();
                        if (System.currentTimeMillis() - longValue <= 2000 && !notificationEntry.interruption) {
                            Long l2 = (Long) headsUpCoordinator.mFSIUpdateCandidates.get(str);
                            HeadsUpCoordinatorLogger headsUpCoordinatorLogger = headsUpCoordinator.mLogger;
                            VisualInterruptionDecisionProvider visualInterruptionDecisionProvider = headsUpCoordinator.mVisualInterruptionDecisionProvider;
                            if (l2 != null) {
                                long longValue2 = l2.longValue();
                                ((SystemClockImpl) systemClock).getClass();
                                if (System.currentTimeMillis() - longValue2 <= 2000) {
                                    VisualInterruptionDecisionProvider.FullScreenIntentDecision makeUnloggedFullScreenIntentDecision = ((VisualInterruptionDecisionProviderImpl) visualInterruptionDecisionProvider).makeUnloggedFullScreenIntentDecision(notificationEntry);
                                    VisualInterruptionDecisionProviderImpl.FullScreenIntentDecisionImpl fullScreenIntentDecisionImpl = (VisualInterruptionDecisionProviderImpl.FullScreenIntentDecisionImpl) makeUnloggedFullScreenIntentDecision;
                                    if (fullScreenIntentDecisionImpl.getShouldInterrupt()) {
                                        String logReason = fullScreenIntentDecisionImpl.getLogReason();
                                        LogLevel logLevel = LogLevel.DEBUG;
                                        HeadsUpCoordinatorLogger$logEntryUpdatedToFullScreen$2 headsUpCoordinatorLogger$logEntryUpdatedToFullScreen$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinatorLogger$logEntryUpdatedToFullScreen$2
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                LogMessage logMessage = (LogMessage) obj;
                                                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("updating entry to launch full screen intent: ", logMessage.getStr1(), " because ", logMessage.getStr2());
                                            }
                                        };
                                        LogBuffer logBuffer = headsUpCoordinatorLogger.buffer;
                                        LogMessage obtain = logBuffer.obtain("HeadsUpCoordinator", logLevel, headsUpCoordinatorLogger$logEntryUpdatedToFullScreen$2, null);
                                        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                                        logMessageImpl.str1 = str;
                                        logMessageImpl.str2 = logReason;
                                        logBuffer.commit(obtain);
                                        ((VisualInterruptionDecisionProviderImpl) visualInterruptionDecisionProvider).logFullScreenIntentDecision(makeUnloggedFullScreenIntentDecision);
                                        headsUpCoordinator.mLaunchFullScreenIntentProvider.launchFullScreenIntent(notificationEntry);
                                        headsUpCoordinator.mFSIUpdateCandidates.remove(str);
                                    } else if (!fullScreenIntentDecisionImpl.getWouldInterruptWithoutDnd()) {
                                        String logReason2 = fullScreenIntentDecisionImpl.getLogReason();
                                        LogLevel logLevel2 = LogLevel.DEBUG;
                                        HeadsUpCoordinatorLogger$logEntryDisqualifiedFromFullScreen$2 headsUpCoordinatorLogger$logEntryDisqualifiedFromFullScreen$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinatorLogger$logEntryDisqualifiedFromFullScreen$2
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                LogMessage logMessage = (LogMessage) obj;
                                                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("updated entry no longer qualifies for full screen intent: ", logMessage.getStr1(), " because ", logMessage.getStr2());
                                            }
                                        };
                                        LogBuffer logBuffer2 = headsUpCoordinatorLogger.buffer;
                                        LogMessage obtain2 = logBuffer2.obtain("HeadsUpCoordinator", logLevel2, headsUpCoordinatorLogger$logEntryDisqualifiedFromFullScreen$2, null);
                                        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
                                        logMessageImpl2.str1 = str;
                                        logMessageImpl2.str2 = logReason2;
                                        logBuffer2.commit(obtain2);
                                        ((VisualInterruptionDecisionProviderImpl) visualInterruptionDecisionProvider).logFullScreenIntentDecision(makeUnloggedFullScreenIntentDecision);
                                        headsUpCoordinator.mFSIUpdateCandidates.remove(str);
                                    }
                                }
                            }
                            VisualInterruptionDecisionProvider.Decision makeUnloggedHeadsUpDecision = ((VisualInterruptionDecisionProviderImpl) visualInterruptionDecisionProvider).makeUnloggedHeadsUpDecision(notificationEntry);
                            boolean z = ((VisualInterruptionDecisionProviderImpl.DecisionImpl) makeUnloggedHeadsUpDecision).shouldInterrupt;
                            HeadsUpCoordinator.PostedEntry postedEntry = (HeadsUpCoordinator.PostedEntry) headsUpCoordinator.mPostedEntries.get(str);
                            if ((postedEntry != null ? postedEntry.shouldHeadsUpEver : false) != z) {
                                LogLevel logLevel3 = LogLevel.DEBUG;
                                HeadsUpCoordinatorLogger$logEntryUpdatedByRanking$2 headsUpCoordinatorLogger$logEntryUpdatedByRanking$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinatorLogger$logEntryUpdatedByRanking$2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        LogMessage logMessage = (LogMessage) obj;
                                        String str1 = logMessage.getStr1();
                                        boolean bool1 = logMessage.getBool1();
                                        String str2 = logMessage.getStr2();
                                        StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("updating entry via ranking applied: ", str1, " updated shouldHeadsUp=", bool1, " because ");
                                        m.append(str2);
                                        return m.toString();
                                    }
                                };
                                LogBuffer logBuffer3 = headsUpCoordinatorLogger.buffer;
                                LogMessage obtain3 = logBuffer3.obtain("HeadsUpCoordinator", logLevel3, headsUpCoordinatorLogger$logEntryUpdatedByRanking$2, null);
                                LogMessageImpl logMessageImpl3 = (LogMessageImpl) obtain3;
                                logMessageImpl3.str1 = str;
                                logMessageImpl3.bool1 = z;
                                logMessageImpl3.str2 = ((VisualInterruptionDecisionProviderImpl.DecisionImpl) makeUnloggedHeadsUpDecision).logReason;
                                logBuffer3.commit(obtain3);
                                onEntryUpdated(notificationEntry);
                            }
                        }
                    }
                }
            }
        }
    };
    public final HeadsUpCoordinator$mActionPressListener$1 mActionPressListener = new Consumer() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mActionPressListener$1
        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            final NotificationEntry notificationEntry = (NotificationEntry) obj;
            HeadsUpManager headsUpManager = HeadsUpCoordinator.this.mHeadsUpManager;
            Intrinsics.checkNotNull(notificationEntry);
            BaseHeadsUpManager baseHeadsUpManager = (BaseHeadsUpManager) headsUpManager;
            baseHeadsUpManager.getClass();
            BaseHeadsUpManager.HeadsUpEntry headsUpEntry = baseHeadsUpManager.getHeadsUpEntry(notificationEntry.mKey);
            if (headsUpEntry != null) {
                headsUpEntry.mUserActionMayIndirectlyRemove = true;
            }
            final HeadsUpCoordinator headsUpCoordinator = HeadsUpCoordinator.this;
            ((ExecutorImpl) headsUpCoordinator.mExecutor).execute(new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mActionPressListener$1.1
                @Override // java.lang.Runnable
                public final void run() {
                    HeadsUpCoordinator.access$endNotifLifetimeExtensionIfExtended(HeadsUpCoordinator.this, notificationEntry);
                }
            });
        }
    };
    public final HeadsUpCoordinator$mLifetimeExtender$1 mLifetimeExtender = new NotifLifetimeExtender() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mLifetimeExtender$1
        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
        public final void cancelLifetimeExtension$1(NotificationEntry notificationEntry) {
            Runnable runnable = (Runnable) HeadsUpCoordinator.this.mNotifsExtendingLifetime.remove(notificationEntry);
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
        public final String getName() {
            return "HeadsUpCoordinator";
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
        public final boolean maybeExtendLifetime(final NotificationEntry notificationEntry, final int i) {
            final HeadsUpCoordinator headsUpCoordinator = HeadsUpCoordinator.this;
            HeadsUpManager headsUpManager = headsUpCoordinator.mHeadsUpManager;
            String str = notificationEntry.mKey;
            if (((HeadsUpManagerPhone) headsUpManager).canRemoveImmediately(str)) {
                return false;
            }
            HeadsUpManager headsUpManager2 = headsUpCoordinator.mHeadsUpManager;
            BaseHeadsUpManager.HeadsUpEntry headsUpEntry = ((BaseHeadsUpManager) headsUpManager2).getHeadsUpEntry(str);
            boolean isSticky = headsUpEntry != null ? headsUpEntry.isSticky() : false;
            DelayableExecutor delayableExecutor = headsUpCoordinator.mExecutor;
            if (!isSticky) {
                final int i2 = 1;
                ((ExecutorImpl) delayableExecutor).execute(new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mLifetimeExtender$1$maybeExtendLifetime$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i2) {
                            case 0:
                                ((BaseHeadsUpManager) headsUpCoordinator.mHeadsUpManager).removeNotification$1(notificationEntry.mKey, GenericDocument$$ExternalSyntheticOutline0.m("cancel lifetime extension - extended for reason: ", ", isSticky: true", i), true);
                                break;
                            default:
                                ((BaseHeadsUpManager) headsUpCoordinator.mHeadsUpManager).removeNotification$1(notificationEntry.mKey, GenericDocument$$ExternalSyntheticOutline0.m("lifetime extension - extended for reason: ", ", isSticky: false", i), false);
                                break;
                        }
                    }
                });
                headsUpCoordinator.mNotifsExtendingLifetime.put(notificationEntry, null);
                return true;
            }
            BaseHeadsUpManager baseHeadsUpManager = (BaseHeadsUpManager) headsUpManager2;
            BaseHeadsUpManager.HeadsUpEntry headsUpEntry2 = (BaseHeadsUpManager.HeadsUpEntry) baseHeadsUpManager.mHeadsUpEntryMap.get(str);
            long j = 0;
            if (headsUpEntry2 != null) {
                long j2 = headsUpEntry2.mEarliestRemovalTime;
                ((SystemClockImpl) baseHeadsUpManager.mSystemClock).getClass();
                j = Math.max(0L, j2 - android.os.SystemClock.elapsedRealtime());
            }
            final int i3 = 0;
            headsUpCoordinator.mNotifsExtendingLifetime.put(notificationEntry, delayableExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mLifetimeExtender$1$maybeExtendLifetime$1
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i3) {
                        case 0:
                            ((BaseHeadsUpManager) headsUpCoordinator.mHeadsUpManager).removeNotification$1(notificationEntry.mKey, GenericDocument$$ExternalSyntheticOutline0.m("cancel lifetime extension - extended for reason: ", ", isSticky: true", i), true);
                            break;
                        default:
                            ((BaseHeadsUpManager) headsUpCoordinator.mHeadsUpManager).removeNotification$1(notificationEntry.mKey, GenericDocument$$ExternalSyntheticOutline0.m("lifetime extension - extended for reason: ", ", isSticky: false", i), false);
                            break;
                    }
                }
            }, j));
            return true;
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
        public final void setCallback(NotifCollection$$ExternalSyntheticLambda12 notifCollection$$ExternalSyntheticLambda12) {
            HeadsUpCoordinator.this.mEndLifetimeExtension = notifCollection$$ExternalSyntheticLambda12;
        }
    };
    public final HeadsUpCoordinator$mNotifPromoter$1 mNotifPromoter = new NotifPromoter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mNotifPromoter$1
        {
            super("HeadsUpCoordinator");
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter
        public final boolean shouldPromoteToTopLevel(NotificationEntry notificationEntry) {
            return HeadsUpCoordinator.access$isGoingToShowHunNoRetract(HeadsUpCoordinator.this, notificationEntry);
        }
    };
    public final HeadsUpCoordinator$sectioner$1 sectioner = new NotifSectioner() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$sectioner$1
        {
            super("HeadsUp", 2);
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final NotifComparator getComparator() {
            final HeadsUpCoordinator headsUpCoordinator = HeadsUpCoordinator.this;
            return new NotifComparator() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$sectioner$1$getComparator$1
                {
                    super("HeadsUp");
                }

                @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator
                public final int compare(ListEntry listEntry, ListEntry listEntry2) {
                    HeadsUpManager headsUpManager = HeadsUpCoordinator.this.mHeadsUpManager;
                    NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
                    NotificationEntry representativeEntry2 = listEntry2.getRepresentativeEntry();
                    BaseHeadsUpManager baseHeadsUpManager = (BaseHeadsUpManager) headsUpManager;
                    baseHeadsUpManager.getClass();
                    if (representativeEntry == null || representativeEntry2 == null) {
                        return Boolean.compare(representativeEntry == null, representativeEntry2 == null);
                    }
                    BaseHeadsUpManager.HeadsUpEntry headsUpEntry = baseHeadsUpManager.getHeadsUpEntry(representativeEntry.mKey);
                    BaseHeadsUpManager.HeadsUpEntry headsUpEntry2 = baseHeadsUpManager.getHeadsUpEntry(representativeEntry2.mKey);
                    if (headsUpEntry == null || headsUpEntry2 == null) {
                        return Boolean.compare(headsUpEntry == null, headsUpEntry2 == null);
                    }
                    return headsUpEntry.compareTo(headsUpEntry2);
                }
            };
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final NodeController getHeaderNodeController() {
            return null;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final boolean isInSection(ListEntry listEntry) {
            return HeadsUpCoordinator.access$isGoingToShowHunNoRetract(HeadsUpCoordinator.this, listEntry);
        }
    };
    public final HeadsUpCoordinator$mOnHeadsUpChangedListener$1 mOnHeadsUpChangedListener = new OnHeadsUpChangedListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mOnHeadsUpChangedListener$1
        @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
        public final void onHeadsUpStateChanged(NotificationEntry notificationEntry, boolean z) {
            if (z) {
                return;
            }
            HeadsUpCoordinator headsUpCoordinator = HeadsUpCoordinator.this;
            headsUpCoordinator.mNotifPromoter.invalidateList("headsUpEnded: " + NotificationUtilsKt.getLogKey(notificationEntry));
            headsUpCoordinator.mHeadsUpViewBinder.unbindHeadsUpView(notificationEntry);
            HeadsUpCoordinator.access$endNotifLifetimeExtensionIfExtended(headsUpCoordinator, notificationEntry);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PostedEntry {
        public final NotificationEntry entry;
        public boolean isBinding;
        public boolean isHeadsUpEntry;
        public final String key;
        public boolean shouldHeadsUpAgain;
        public boolean shouldHeadsUpEver;
        public final boolean wasAdded;
        public boolean wasUpdated;

        public PostedEntry(NotificationEntry notificationEntry, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
            this.entry = notificationEntry;
            this.wasAdded = z;
            this.wasUpdated = z2;
            this.shouldHeadsUpEver = z3;
            this.shouldHeadsUpAgain = z4;
            this.isHeadsUpEntry = z5;
            this.isBinding = z6;
            this.key = notificationEntry.mKey;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PostedEntry)) {
                return false;
            }
            PostedEntry postedEntry = (PostedEntry) obj;
            return this.entry.equals(postedEntry.entry) && this.wasAdded == postedEntry.wasAdded && this.wasUpdated == postedEntry.wasUpdated && this.shouldHeadsUpEver == postedEntry.shouldHeadsUpEver && this.shouldHeadsUpAgain == postedEntry.shouldHeadsUpAgain && this.isHeadsUpEntry == postedEntry.isHeadsUpEntry && this.isBinding == postedEntry.isBinding;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isBinding) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(this.entry.hashCode() * 31, 31, this.wasAdded), 31, this.wasUpdated), 31, this.shouldHeadsUpEver), 31, this.shouldHeadsUpAgain), 31, this.isHeadsUpEntry);
        }

        public final String toString() {
            boolean z = this.wasUpdated;
            boolean z2 = this.shouldHeadsUpEver;
            boolean z3 = this.shouldHeadsUpAgain;
            boolean z4 = this.isHeadsUpEntry;
            boolean z5 = this.isBinding;
            StringBuilder sb = new StringBuilder("PostedEntry(entry=");
            sb.append(this.entry);
            sb.append(", wasAdded=");
            BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(sb, this.wasAdded, ", wasUpdated=", z, ", shouldHeadsUpEver=");
            BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(sb, z2, ", shouldHeadsUpAgain=", z3, ", isHeadsUpEntry=");
            sb.append(z4);
            sb.append(", isBinding=");
            sb.append(z5);
            sb.append(")");
            return sb.toString();
        }
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mNotifPromoter$1] */
    /* JADX WARN: Type inference failed for: r1v11, types: [com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$sectioner$1] */
    /* JADX WARN: Type inference failed for: r1v12, types: [com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mOnHeadsUpChangedListener$1] */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mNotifCollectionListener$1] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mActionPressListener$1] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$mLifetimeExtender$1] */
    public HeadsUpCoordinator(HeadsUpCoordinatorLogger headsUpCoordinatorLogger, SystemClock systemClock, HeadsUpManager headsUpManager, HeadsUpViewBinder headsUpViewBinder, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, NotificationRemoteInputManager notificationRemoteInputManager, LaunchFullScreenIntentProvider launchFullScreenIntentProvider, DelayableExecutor delayableExecutor) {
        this.mLogger = headsUpCoordinatorLogger;
        this.mSystemClock = systemClock;
        this.mHeadsUpManager = headsUpManager;
        this.mHeadsUpViewBinder = headsUpViewBinder;
        this.mVisualInterruptionDecisionProvider = visualInterruptionDecisionProvider;
        this.mRemoteInputManager = notificationRemoteInputManager;
        this.mLaunchFullScreenIntentProvider = launchFullScreenIntentProvider;
        this.mExecutor = delayableExecutor;
    }

    public static final void access$endNotifLifetimeExtensionIfExtended(HeadsUpCoordinator headsUpCoordinator, NotificationEntry notificationEntry) {
        if (headsUpCoordinator.mNotifsExtendingLifetime.containsKey(notificationEntry)) {
            Runnable runnable = (Runnable) headsUpCoordinator.mNotifsExtendingLifetime.remove(notificationEntry);
            if (runnable != null) {
                runnable.run();
            }
            NotifCollection$$ExternalSyntheticLambda12 notifCollection$$ExternalSyntheticLambda12 = headsUpCoordinator.mEndLifetimeExtension;
            if (notifCollection$$ExternalSyntheticLambda12 != null) {
                notifCollection$$ExternalSyntheticLambda12.onEndLifetimeExtension(notificationEntry, headsUpCoordinator.mLifetimeExtender);
            }
        }
    }

    public static final void access$handlePostedEntry(HeadsUpCoordinator headsUpCoordinator, PostedEntry postedEntry, HunMutatorImpl hunMutatorImpl, String str) {
        HeadsUpCoordinatorLogger headsUpCoordinatorLogger = headsUpCoordinator.mLogger;
        if (postedEntry.wasAdded) {
            if (postedEntry.shouldHeadsUpEver) {
                headsUpCoordinator.bindForAsyncHeadsUp(postedEntry);
                return;
            }
            return;
        }
        boolean z = postedEntry.isHeadsUpEntry;
        if (!z && !postedEntry.isBinding) {
            if (postedEntry.shouldHeadsUpEver && postedEntry.shouldHeadsUpAgain) {
                headsUpCoordinator.bindForAsyncHeadsUp(postedEntry);
                return;
            }
            return;
        }
        boolean z2 = postedEntry.shouldHeadsUpEver;
        String str2 = postedEntry.key;
        if (z2) {
            if (z) {
                ((BaseHeadsUpManager) hunMutatorImpl.headsUpManager).updateNotification(str2, postedEntry.shouldHeadsUpAgain);
                return;
            }
            return;
        }
        if (!z) {
            headsUpCoordinator.cancelHeadsUpBind(postedEntry.entry);
            return;
        }
        hunMutatorImpl.getClass();
        hunMutatorImpl.deferred.add(new Pair(str2, Boolean.FALSE));
    }

    public static final boolean access$isGoingToShowHunNoRetract(HeadsUpCoordinator headsUpCoordinator, ListEntry listEntry) {
        PostedEntry postedEntry = (PostedEntry) headsUpCoordinator.mPostedEntries.get(listEntry.getKey());
        if (postedEntry == null) {
            if (((BaseHeadsUpManager) headsUpCoordinator.mHeadsUpManager).isHeadsUpEntry(listEntry.getKey()) || headsUpCoordinator.isEntryBinding(listEntry)) {
                return true;
            }
        } else {
            if (postedEntry.isHeadsUpEntry || postedEntry.isBinding) {
                return true;
            }
            if (postedEntry.shouldHeadsUpEver && (postedEntry.wasAdded || postedEntry.shouldHeadsUpAgain)) {
                return true;
            }
        }
        return false;
    }

    public final void addForFSIReconsideration(NotificationEntry notificationEntry, long j) {
        this.mFSIUpdateCandidates.put(notificationEntry.mKey, Long.valueOf(j));
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        this.mNotifPipeline = notifPipeline;
        ((BaseHeadsUpManager) this.mHeadsUpManager).addListener(this.mOnHeadsUpChangedListener);
        notifPipeline.addCollectionListener(this.mNotifCollectionListener);
        HeadsUpCoordinator$attach$1 headsUpCoordinator$attach$1 = new HeadsUpCoordinator$attach$1(this);
        ShadeListBuilder shadeListBuilder = notifPipeline.mShadeListBuilder;
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        NamedListenerSet namedListenerSet = shadeListBuilder.mOnBeforeTransformGroupsListeners;
        namedListenerSet.listeners.addIfAbsent(new NamedListenerSet.NamedListener(namedListenerSet, headsUpCoordinator$attach$1));
        notifPipeline.addOnBeforeFinalizeFilterListener(new OnBeforeFinalizeFilterListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$attach$2
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener
            public final void onBeforeFinalizeFilter(final List list) {
                final HeadsUpCoordinator headsUpCoordinator = HeadsUpCoordinator.this;
                headsUpCoordinator.getClass();
                HeadsUpCoordinatorKt.access$modifyHuns(headsUpCoordinator.mHeadsUpManager, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$onBeforeFinalizeFilter$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:123:0x015d, code lost:
                    
                        if (r6.isEntryBinding(r11) == false) goto L59;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:59:0x0149, code lost:
                    
                        if (r8.isBinding != false) goto L63;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:68:0x01d3, code lost:
                    
                        if (r12.mSbn.getNotification().getGroupAlertBehavior() == 1) goto L75;
                     */
                    /* JADX WARN: Removed duplicated region for block: B:112:0x01dc  */
                    /* JADX WARN: Removed duplicated region for block: B:114:0x01b4  */
                    /* JADX WARN: Removed duplicated region for block: B:62:0x01b2  */
                    /* JADX WARN: Removed duplicated region for block: B:65:0x01bd  */
                    /* JADX WARN: Removed duplicated region for block: B:70:0x01d9  */
                    /* JADX WARN: Removed duplicated region for block: B:76:0x0239  */
                    /* JADX WARN: Removed duplicated region for block: B:79:0x0244  */
                    /* JADX WARN: Removed duplicated region for block: B:80:0x023b  */
                    /* JADX WARN: Removed duplicated region for block: B:83:0x0249  */
                    /* JADX WARN: Removed duplicated region for block: B:88:0x0262  */
                    @Override // kotlin.jvm.functions.Function1
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invoke(java.lang.Object r28) {
                        /*
                            Method dump skipped, instructions count: 972
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator$onBeforeFinalizeFilter$1.invoke(java.lang.Object):java.lang.Object");
                    }
                });
            }
        });
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        List list = shadeListBuilder.mNotifPromoters;
        HeadsUpCoordinator$mNotifPromoter$1 headsUpCoordinator$mNotifPromoter$1 = this.mNotifPromoter;
        list.add(headsUpCoordinator$mNotifPromoter$1);
        headsUpCoordinator$mNotifPromoter$1.mListener = new ShadeListBuilder$$ExternalSyntheticLambda0(shadeListBuilder, 7);
        notifPipeline.addNotificationLifetimeExtender(this.mLifetimeExtender);
        this.mRemoteInputManager.mActionPressListeners.addIfAbsent(this.mActionPressListener);
    }

    public final void bindForAsyncHeadsUp(PostedEntry postedEntry) {
        this.mEntriesBindingUntil.put(postedEntry.key, Long.valueOf(this.mNow + 1000));
        HeadsUpCoordinator$bindForAsyncHeadsUp$1 headsUpCoordinator$bindForAsyncHeadsUp$1 = new HeadsUpCoordinator$bindForAsyncHeadsUp$1(this);
        this.mHeadsUpViewBinder.bindHeadsUpView(postedEntry.entry, headsUpCoordinator$bindForAsyncHeadsUp$1);
    }

    public final void cancelHeadsUpBind(NotificationEntry notificationEntry) {
        this.mEntriesBindingUntil.remove(notificationEntry.mKey);
        this.mHeadsUpViewBinder.abortBindCallback(notificationEntry);
    }

    public final boolean isEntryBinding(ListEntry listEntry) {
        Long l = (Long) this.mEntriesBindingUntil.get(listEntry.getKey());
        return l != null && l.longValue() >= this.mNow;
    }

    public final void setUpdateTime(NotificationEntry notificationEntry, long j) {
        this.mEntriesUpdateTimes.put(notificationEntry.mKey, Long.valueOf(j));
    }
}
