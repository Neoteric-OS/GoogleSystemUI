package com.android.systemui.statusbar.phone.ongoingcall;

import android.app.IActivityManager;
import android.app.PendingIntent;
import android.app.UidObserver;
import android.content.Context;
import android.view.View;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.chips.ui.view.ChipBackgroundContainer;
import com.android.systemui.statusbar.chips.ui.view.ChipChronometer;
import com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryImpl;
import com.android.systemui.statusbar.gesture.SwipeStatusBarAwayGestureHandler;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment;
import com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController;
import com.android.systemui.statusbar.phone.ongoingcall.data.repository.OngoingCallRepository;
import com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OngoingCallController implements CallbackController, Dumpable, CoreStartable {
    public final ActiveNotificationsInteractor activeNotificationsInteractor;
    public final ActivityStarter activityStarter;
    public CallNotificationInfo callNotificationInfo;
    public View chipView;
    public final Context context;
    public final DumpManager dumpManager;
    public final IActivityManager iActivityManager;
    public boolean isFullscreen;
    public final LogBuffer logger;
    public final Executor mainExecutor;
    public final OngoingCallRepository ongoingCallRepository;
    public final CoroutineScope scope;
    public final StatusBarModeRepositoryImpl statusBarModeRepository;
    public final StatusBarWindowControllerImpl statusBarWindowController;
    public final SwipeStatusBarAwayGestureHandler swipeStatusBarAwayGestureHandler;
    public final List mListeners = new ArrayList();
    public final CallAppUidObserver uidObserver = new CallAppUidObserver();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CallAppUidObserver extends UidObserver {
        public Integer callAppUid;
        public boolean isCallAppVisible;
        public boolean isRegistered;

        public CallAppUidObserver() {
        }

        public final void onUidStateChanged(int i, int i2, long j, int i3) {
            Integer num = this.callAppUid;
            if (num == null || i != num.intValue()) {
                return;
            }
            boolean z = this.isCallAppVisible;
            OngoingCallController.this.getClass();
            boolean z2 = i2 <= 2;
            this.isCallAppVisible = z2;
            if (z != z2) {
                LogBuffer logBuffer = OngoingCallController.this.logger;
                LogMessage obtain = logBuffer.obtain("OngoingCall", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$CallAppUidObserver$onUidStateChanged$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("#onUidStateChanged. isCallAppVisible=", ((LogMessage) obj).getBool1());
                    }
                }, null);
                ((LogMessageImpl) obtain).bool1 = this.isCallAppVisible;
                logBuffer.commit(obtain);
                final OngoingCallController ongoingCallController = OngoingCallController.this;
                ongoingCallController.mainExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$CallAppUidObserver$onUidStateChanged$3
                    @Override // java.lang.Runnable
                    public final void run() {
                        OngoingCallController.this.sendStateChangeEvent();
                    }
                });
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CallNotificationInfo {
        public final long callStartTime;
        public final PendingIntent intent;
        public final boolean isOngoing;
        public final String key;
        public final StatusBarIconView notificationIconView;
        public final boolean statusBarSwipedAway;
        public final int uid;

        public CallNotificationInfo(String str, long j, StatusBarIconView statusBarIconView, PendingIntent pendingIntent, int i, boolean z, boolean z2) {
            this.key = str;
            this.callStartTime = j;
            this.notificationIconView = statusBarIconView;
            this.intent = pendingIntent;
            this.uid = i;
            this.isOngoing = z;
            this.statusBarSwipedAway = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CallNotificationInfo)) {
                return false;
            }
            CallNotificationInfo callNotificationInfo = (CallNotificationInfo) obj;
            return Intrinsics.areEqual(this.key, callNotificationInfo.key) && this.callStartTime == callNotificationInfo.callStartTime && Intrinsics.areEqual(this.notificationIconView, callNotificationInfo.notificationIconView) && Intrinsics.areEqual(this.intent, callNotificationInfo.intent) && this.uid == callNotificationInfo.uid && this.isOngoing == callNotificationInfo.isOngoing && this.statusBarSwipedAway == callNotificationInfo.statusBarSwipedAway;
        }

        public final int hashCode() {
            int m = Scale$$ExternalSyntheticOutline0.m(this.key.hashCode() * 31, 31, this.callStartTime);
            StatusBarIconView statusBarIconView = this.notificationIconView;
            int hashCode = (m + (statusBarIconView == null ? 0 : statusBarIconView.hashCode())) * 31;
            PendingIntent pendingIntent = this.intent;
            return Boolean.hashCode(this.statusBarSwipedAway) + TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.uid, (hashCode + (pendingIntent != null ? pendingIntent.hashCode() : 0)) * 31, 31), 31, this.isOngoing);
        }

        public final String toString() {
            return "CallNotificationInfo(key=" + this.key + ", callStartTime=" + this.callStartTime + ", notificationIconView=" + this.notificationIconView + ", intent=" + this.intent + ", uid=" + this.uid + ", isOngoing=" + this.isOngoing + ", statusBarSwipedAway=" + this.statusBarSwipedAway + ")";
        }
    }

    public OngoingCallController(CoroutineScope coroutineScope, Context context, OngoingCallRepository ongoingCallRepository, CommonNotifCollection commonNotifCollection, ActiveNotificationsInteractor activeNotificationsInteractor, SystemClock systemClock, ActivityStarter activityStarter, Executor executor, IActivityManager iActivityManager, DumpManager dumpManager, StatusBarWindowControllerImpl statusBarWindowControllerImpl, SwipeStatusBarAwayGestureHandler swipeStatusBarAwayGestureHandler, StatusBarModeRepositoryImpl statusBarModeRepositoryImpl, LogBuffer logBuffer) {
        this.scope = coroutineScope;
        this.context = context;
        this.ongoingCallRepository = ongoingCallRepository;
        this.activeNotificationsInteractor = activeNotificationsInteractor;
        this.activityStarter = activityStarter;
        this.mainExecutor = executor;
        this.iActivityManager = iActivityManager;
        this.dumpManager = dumpManager;
        this.statusBarWindowController = statusBarWindowControllerImpl;
        this.swipeStatusBarAwayGestureHandler = swipeStatusBarAwayGestureHandler;
        this.statusBarModeRepository = statusBarModeRepositoryImpl;
        this.logger = logBuffer;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        CollapsedStatusBarFragment.AnonymousClass1 anonymousClass1 = (CollapsedStatusBarFragment.AnonymousClass1) obj;
        synchronized (this.mListeners) {
            if (!this.mListeners.contains(anonymousClass1)) {
                this.mListeners.add(anonymousClass1);
            }
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Active call notification: " + this.callNotificationInfo);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("Call app visible: ", this.uidObserver.isCallAppVisible, printWriter);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        CollapsedStatusBarFragment.AnonymousClass1 anonymousClass1 = (CollapsedStatusBarFragment.AnonymousClass1) obj;
        synchronized (this.mListeners) {
            this.mListeners.remove(anonymousClass1);
        }
    }

    public final void removeChip() {
        this.callNotificationInfo = null;
        StatusBarWindowControllerImpl statusBarWindowControllerImpl = this.statusBarWindowController;
        StatusBarWindowControllerImpl.State state = statusBarWindowControllerImpl.mCurrentState;
        state.mOngoingProcessRequiresStatusBarVisible = false;
        statusBarWindowControllerImpl.apply(state);
        this.swipeStatusBarAwayGestureHandler.removeOnGestureDetectedCallback("OngoingCall");
        sendStateChangeEvent();
        CallAppUidObserver callAppUidObserver = this.uidObserver;
        callAppUidObserver.callAppUid = null;
        callAppUidObserver.isRegistered = false;
        OngoingCallController ongoingCallController = OngoingCallController.this;
        ongoingCallController.iActivityManager.unregisterUidObserver(ongoingCallController.uidObserver);
    }

    public final void sendStateChangeEvent() {
        CallNotificationInfo callNotificationInfo = this.callNotificationInfo;
        boolean z = (callNotificationInfo == null || !callNotificationInfo.isOngoing || this.uidObserver.isCallAppVisible) ? false : true;
        OngoingCallModel ongoingCallModel = OngoingCallModel.NoCall.INSTANCE;
        if (z && callNotificationInfo != null) {
            LogLevel logLevel = LogLevel.DEBUG;
            OngoingCallController$getOngoingCallModel$2 ongoingCallController$getOngoingCallModel$2 = new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$getOngoingCallModel$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "Creating OngoingCallModel.InCall. notifIconFlag=" + logMessage.getBool1() + " hasIcon=" + logMessage.getBool2();
                }
            };
            LogBuffer logBuffer = this.logger;
            LogMessage obtain = logBuffer.obtain("OngoingCall", logLevel, ongoingCallController$getOngoingCallModel$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.bool1 = true;
            StatusBarIconView statusBarIconView = callNotificationInfo.notificationIconView;
            logMessageImpl.bool2 = statusBarIconView != null;
            logBuffer.commit(obtain);
            ongoingCallModel = new OngoingCallModel.InCall(callNotificationInfo.callStartTime, statusBarIconView, callNotificationInfo.intent);
        }
        this.ongoingCallRepository.setOngoingCallState(ongoingCallModel);
        Iterator it = this.mListeners.iterator();
        while (it.hasNext()) {
            CollapsedStatusBarFragment.this.updateStatusBarVisibilities(true);
        }
    }

    public final void setChipView(View view) {
        tearDownChipView();
        this.chipView = view;
        ChipBackgroundContainer chipBackgroundContainer = (ChipBackgroundContainer) view.findViewById(R.id.ongoing_activity_chip_background);
        if (chipBackgroundContainer != null) {
            chipBackgroundContainer.maxHeightFetcher = new Function0() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$setChipView$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Integer.valueOf(OngoingCallController.this.statusBarWindowController.mBarHeight);
                }
            };
        }
        CallNotificationInfo callNotificationInfo = this.callNotificationInfo;
        if (callNotificationInfo == null || !callNotificationInfo.isOngoing || this.uidObserver.isCallAppVisible) {
            return;
        }
        updateChip();
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.dumpManager.registerDumpable(this);
        OngoingCallController$start$1 ongoingCallController$start$1 = new OngoingCallController$start$1(this, null);
        CoroutineScope coroutineScope = this.scope;
        BuildersKt.launch$default(coroutineScope, null, null, ongoingCallController$start$1, 3);
        BuildersKt.launch$default(coroutineScope, null, null, new OngoingCallController$start$2(this, null), 3);
    }

    public final Unit tearDownChipView() {
        ChipChronometer chipChronometer;
        View view = this.chipView;
        if (view == null || (chipChronometer = (ChipChronometer) view.findViewById(R.id.ongoing_activity_chip_time)) == null) {
            return null;
        }
        chipChronometer.stop();
        return Unit.INSTANCE;
    }

    public final void updateChip() {
        CallNotificationInfo callNotificationInfo = this.callNotificationInfo;
        if (callNotificationInfo == null) {
            return;
        }
        View view = this.chipView;
        ChipChronometer chipChronometer = view != null ? (ChipChronometer) view.findViewById(R.id.ongoing_activity_chip_time) : null;
        if (view == null || chipChronometer == null) {
            this.callNotificationInfo = null;
            LogLevel logLevel = LogLevel.WARNING;
            OngoingCallController$updateChip$2 ongoingCallController$updateChip$2 = new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$updateChip$2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return "Ongoing call chip view could not be found; Not displaying chip in status bar";
                }
            };
            LogBuffer logBuffer = this.logger;
            logBuffer.commit(logBuffer.obtain("OngoingCall", logLevel, ongoingCallController$updateChip$2, null));
            return;
        }
        CallAppUidObserver callAppUidObserver = this.uidObserver;
        Integer num = callAppUidObserver.callAppUid;
        int i = callNotificationInfo.uid;
        if (num == null || num.intValue() != i) {
            callAppUidObserver.callAppUid = Integer.valueOf(i);
            try {
                OngoingCallController ongoingCallController = OngoingCallController.this;
                callAppUidObserver.isCallAppVisible = ongoingCallController.iActivityManager.getUidProcessState(i, ongoingCallController.context.getOpPackageName()) <= 2;
                LogBuffer logBuffer2 = OngoingCallController.this.logger;
                LogMessage obtain = logBuffer2.obtain("OngoingCall", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$CallAppUidObserver$registerWithUid$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("On uid observer registration, isCallAppVisible=", ((LogMessage) obj).getBool1());
                    }
                }, null);
                ((LogMessageImpl) obtain).bool1 = callAppUidObserver.isCallAppVisible;
                logBuffer2.commit(obtain);
                if (!callAppUidObserver.isRegistered) {
                    OngoingCallController ongoingCallController2 = OngoingCallController.this;
                    ongoingCallController2.iActivityManager.registerUidObserver(ongoingCallController2.uidObserver, 1, -1, ongoingCallController2.context.getOpPackageName());
                    callAppUidObserver.isRegistered = true;
                }
            } catch (SecurityException e) {
                LogBuffer logBuffer3 = OngoingCallController.this.logger;
                logBuffer3.commit(logBuffer3.obtain("OngoingCall", LogLevel.ERROR, new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$CallAppUidObserver$registerWithUid$4
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return "Security exception when trying to set up uid observer";
                    }
                }, e));
            }
        }
        if (!callNotificationInfo.statusBarSwipedAway) {
            StatusBarWindowControllerImpl statusBarWindowControllerImpl = this.statusBarWindowController;
            StatusBarWindowControllerImpl.State state = statusBarWindowControllerImpl.mCurrentState;
            state.mOngoingProcessRequiresStatusBarVisible = true;
            statusBarWindowControllerImpl.apply(state);
        }
        updateGestureListening();
        sendStateChangeEvent();
    }

    public final void updateGestureListening() {
        CallNotificationInfo callNotificationInfo = this.callNotificationInfo;
        SwipeStatusBarAwayGestureHandler swipeStatusBarAwayGestureHandler = this.swipeStatusBarAwayGestureHandler;
        if (callNotificationInfo == null || callNotificationInfo.statusBarSwipedAway || !this.isFullscreen) {
            swipeStatusBarAwayGestureHandler.removeOnGestureDetectedCallback("OngoingCall");
        } else {
            swipeStatusBarAwayGestureHandler.addOnGestureDetectedCallback("OngoingCall", new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$updateGestureListening$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    OngoingCallController ongoingCallController = OngoingCallController.this;
                    ongoingCallController.getClass();
                    LogLevel logLevel = LogLevel.DEBUG;
                    OngoingCallController$onSwipeAwayGestureDetected$2 ongoingCallController$onSwipeAwayGestureDetected$2 = new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$onSwipeAwayGestureDetected$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                            return "Swipe away gesture detected";
                        }
                    };
                    LogBuffer logBuffer = ongoingCallController.logger;
                    OngoingCallController.CallNotificationInfo callNotificationInfo2 = null;
                    logBuffer.commit(logBuffer.obtain("OngoingCall", logLevel, ongoingCallController$onSwipeAwayGestureDetected$2, null));
                    OngoingCallController.CallNotificationInfo callNotificationInfo3 = ongoingCallController.callNotificationInfo;
                    if (callNotificationInfo3 != null) {
                        PendingIntent pendingIntent = callNotificationInfo3.intent;
                        callNotificationInfo2 = new OngoingCallController.CallNotificationInfo(callNotificationInfo3.key, callNotificationInfo3.callStartTime, callNotificationInfo3.notificationIconView, pendingIntent, callNotificationInfo3.uid, callNotificationInfo3.isOngoing, true);
                    }
                    ongoingCallController.callNotificationInfo = callNotificationInfo2;
                    StatusBarWindowControllerImpl statusBarWindowControllerImpl = ongoingCallController.statusBarWindowController;
                    StatusBarWindowControllerImpl.State state = statusBarWindowControllerImpl.mCurrentState;
                    state.mOngoingProcessRequiresStatusBarVisible = false;
                    statusBarWindowControllerImpl.apply(state);
                    ongoingCallController.swipeStatusBarAwayGestureHandler.removeOnGestureDetectedCallback("OngoingCall");
                    return Unit.INSTANCE;
                }
            });
        }
    }
}
