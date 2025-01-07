package com.android.systemui.statusbar.notification.collection.coordinator;

import android.util.IndentingPrintWriter;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OriginalUnseenKeyguardCoordinator implements Coordinator, Dumpable {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long SEEN_TIMEOUT;
    public final DumpManager dumpManager;
    public final HeadsUpManager headsUpManager;
    public final KeyguardRepositoryImpl keyguardRepository;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public final KeyguardCoordinatorLogger logger;
    public final CoroutineScope scope;
    public final SeenNotificationsInteractor seenNotificationsInteractor;
    public final StatusBarStateController statusBarStateController;
    public boolean unseenFilterEnabled;
    public final Set unseenNotifications = new LinkedHashSet();
    public final SharedFlowImpl unseenEntryAdded = SharedFlowKt.MutableSharedFlow$default(0, 1, null, 5);
    public final SharedFlowImpl unseenEntryRemoved = SharedFlowKt.MutableSharedFlow$default(0, 1, null, 5);
    public final OriginalUnseenKeyguardCoordinator$collectionListener$1 collectionListener = new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$collectionListener$1
        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryAdded(NotificationEntry notificationEntry) {
            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = OriginalUnseenKeyguardCoordinator.this;
            if (((KeyguardStateControllerImpl) originalUnseenKeyguardCoordinator.keyguardRepository.keyguardStateController).mShowing || !originalUnseenKeyguardCoordinator.statusBarStateController.isExpanded()) {
                KeyguardCoordinatorLogger keyguardCoordinatorLogger = originalUnseenKeyguardCoordinator.logger;
                LogLevel logLevel = LogLevel.DEBUG;
                KeyguardCoordinatorLogger$logUnseenAdded$2 keyguardCoordinatorLogger$logUnseenAdded$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logUnseenAdded$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Unseen notif added: ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
                LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logUnseenAdded$2, null);
                ((LogMessageImpl) obtain).str1 = notificationEntry.mKey;
                logBuffer.commit(obtain);
                originalUnseenKeyguardCoordinator.unseenNotifications.add(notificationEntry);
                originalUnseenKeyguardCoordinator.unseenEntryAdded.tryEmit(notificationEntry);
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = OriginalUnseenKeyguardCoordinator.this;
            if (originalUnseenKeyguardCoordinator.unseenNotifications.remove(notificationEntry)) {
                KeyguardCoordinatorLogger keyguardCoordinatorLogger = originalUnseenKeyguardCoordinator.logger;
                String str = notificationEntry.mKey;
                LogLevel logLevel = LogLevel.DEBUG;
                KeyguardCoordinatorLogger$logUnseenRemoved$2 keyguardCoordinatorLogger$logUnseenRemoved$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logUnseenRemoved$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Unseen notif removed: ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
                LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logUnseenRemoved$2, null);
                ((LogMessageImpl) obtain).str1 = str;
                logBuffer.commit(obtain);
                originalUnseenKeyguardCoordinator.unseenEntryRemoved.tryEmit(notificationEntry);
            }
        }

        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryUpdated(NotificationEntry notificationEntry) {
            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = OriginalUnseenKeyguardCoordinator.this;
            if (((KeyguardStateControllerImpl) originalUnseenKeyguardCoordinator.keyguardRepository.keyguardStateController).mShowing || !originalUnseenKeyguardCoordinator.statusBarStateController.isExpanded()) {
                KeyguardCoordinatorLogger keyguardCoordinatorLogger = originalUnseenKeyguardCoordinator.logger;
                LogLevel logLevel = LogLevel.DEBUG;
                KeyguardCoordinatorLogger$logUnseenUpdated$2 keyguardCoordinatorLogger$logUnseenUpdated$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logUnseenUpdated$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Unseen notif updated: ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
                LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logUnseenUpdated$2, null);
                ((LogMessageImpl) obtain).str1 = notificationEntry.mKey;
                logBuffer.commit(obtain);
                originalUnseenKeyguardCoordinator.unseenNotifications.add(notificationEntry);
                originalUnseenKeyguardCoordinator.unseenEntryAdded.tryEmit(notificationEntry);
            }
        }
    };
    public final OriginalUnseenKeyguardCoordinator$unseenNotifFilter$1 unseenNotifFilter = new NotifFilter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$unseenNotifFilter$1
        public boolean hasFilteredAnyNotifs;

        {
            super("OriginalUnseenKeyguardCoordinator");
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.Pluggable
        public final void onCleanup() {
            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = OriginalUnseenKeyguardCoordinator.this;
            KeyguardCoordinatorLogger keyguardCoordinatorLogger = originalUnseenKeyguardCoordinator.logger;
            boolean z = this.hasFilteredAnyNotifs;
            LogLevel logLevel = LogLevel.DEBUG;
            KeyguardCoordinatorLogger$logProviderHasFilteredOutSeenNotifs$2 keyguardCoordinatorLogger$logProviderHasFilteredOutSeenNotifs$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger$logProviderHasFilteredOutSeenNotifs$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("UI showing unseen filter treatment: ", ((LogMessage) obj).getBool1());
                }
            };
            LogBuffer logBuffer = keyguardCoordinatorLogger.buffer;
            LogMessage obtain = logBuffer.obtain("KeyguardCoordinator", logLevel, keyguardCoordinatorLogger$logProviderHasFilteredOutSeenNotifs$2, null);
            ((LogMessageImpl) obtain).bool1 = z;
            logBuffer.commit(obtain);
            AuthContainerView$$ExternalSyntheticOutline0.m(this.hasFilteredAnyNotifs, originalUnseenKeyguardCoordinator.seenNotificationsInteractor.notificationListRepository.hasFilteredOutSeenNotifications, null);
            this.hasFilteredAnyNotifs = false;
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            boolean z;
            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = OriginalUnseenKeyguardCoordinator.this;
            boolean z2 = true;
            if (originalUnseenKeyguardCoordinator.unseenFilterEnabled && ((KeyguardStateControllerImpl) originalUnseenKeyguardCoordinator.keyguardRepository.keyguardStateController).mShowing && !originalUnseenKeyguardCoordinator.unseenNotifications.contains(notificationEntry)) {
                GroupEntry groupEntry = notificationEntry.mAttachState.parent;
                if (!Intrinsics.areEqual(groupEntry != null ? groupEntry.mSummary : null, notificationEntry)) {
                    ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                    if (!(expandableNotificationRow == null ? false : expandableNotificationRow.mEntry.mSbn.getNotification().isMediaNotification()) && !notificationEntry.mSbn.isOngoing()) {
                        z = true;
                        if (!this.hasFilteredAnyNotifs && !z) {
                            z2 = false;
                        }
                        this.hasFilteredAnyNotifs = z2;
                        return z;
                    }
                }
            }
            z = false;
            if (!this.hasFilteredAnyNotifs) {
                z2 = false;
            }
            this.hasFilteredAnyNotifs = z2;
            return z;
        }
    };

    static {
        int i = Duration.$r8$clinit;
        SEEN_TIMEOUT = DurationKt.toDuration(5, DurationUnit.SECONDS);
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$collectionListener$1] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator$unseenNotifFilter$1] */
    public OriginalUnseenKeyguardCoordinator(DumpManager dumpManager, HeadsUpManager headsUpManager, KeyguardRepositoryImpl keyguardRepositoryImpl, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardCoordinatorLogger keyguardCoordinatorLogger, CoroutineScope coroutineScope, SeenNotificationsInteractor seenNotificationsInteractor, StatusBarStateController statusBarStateController, SceneInteractor sceneInteractor) {
        this.dumpManager = dumpManager;
        this.headsUpManager = headsUpManager;
        this.keyguardRepository = keyguardRepositoryImpl;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.logger = keyguardCoordinatorLogger;
        this.scope = coroutineScope;
        this.seenNotificationsInteractor = seenNotificationsInteractor;
        this.statusBarStateController = statusBarStateController;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addFinalizeFilter(this.unseenNotifFilter);
        notifPipeline.addCollectionListener(this.collectionListener);
        BuildersKt.launch$default(this.scope, null, null, new OriginalUnseenKeyguardCoordinator$attach$1(this, null), 3);
        this.dumpManager.registerDumpable(this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.println("notificationListInteractor.hasFilteredOutSeenNotifications.value=" + this.seenNotificationsInteractor.hasFilteredOutSeenNotifications.getValue());
        asIndenting.println("unseen notifications:");
        asIndenting.increaseIndent();
        Iterator it = this.unseenNotifications.iterator();
        while (it.hasNext()) {
            asIndenting.println(((NotificationEntry) it.next()).mKey);
        }
        asIndenting.decreaseIndent();
    }

    public static /* synthetic */ void getUnseenNotifFilter$annotations() {
    }
}
