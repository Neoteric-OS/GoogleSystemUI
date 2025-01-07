package com.android.systemui.shade;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.keyguard.LockIconView;
import com.android.keyguard.LockIconViewController;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.bouncer.ui.binder.BouncerViewBinder;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.dock.DockManager;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyevent.domain.interactor.SysUIKeyEventHandler;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.statusbar.DragDownHelper;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.NotificationInsetsImpl;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.DozeScrimController;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationShadeWindowViewController implements Dumpable {
    public final AlternateBouncerInteractor mAlternateBouncerInteractor;
    public final AmbientState mAmbientState;
    public View mBrightnessMirror;
    public final SystemClock mClock;
    public final NotificationShadeDepthController mDepthController;
    public final DockManager mDockManager;
    public MotionEvent mDownEvent;
    public final DozeScrimController mDozeScrimController;
    public final DozeServiceHost mDozeServiceHost;
    public DragDownHelper mDragDownHelper;
    public boolean mExpandAnimationRunning;
    public boolean mExpandingBelowNotch;
    public final FalsingCollector mFalsingCollector;
    public final FeatureFlagsClassic mFeatureFlagsClassic;
    public final GlanceableHubContainerController mGlanceableHubContainerController;
    public final KeyguardUnlockAnimationController mKeyguardUnlockAnimationController;
    public long mLaunchAnimationTimeout;
    public final LockIconViewController mLockIconViewController;
    public final LockscreenShadeTransitionController mLockscreenShadeTransitionController;
    public final NotificationShadeWindowViewController$$ExternalSyntheticLambda5 mLockscreenToDreamingTransition;
    public final NotificationInsetsImpl mNotificationInsetsController;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final NotificationStackScrollLayoutController mNotificationStackScrollLayoutController;
    public final PanelExpansionInteractor mPanelExpansionInteractor;
    public final PrimaryBouncerInteractor mPrimaryBouncerInteractor;
    public final PulsingGestureListener mPulsingGestureListener;
    public GestureDetector mPulsingWakeupGestureHandler;
    public final QuickSettingsController mQuickSettingsController;
    public final CentralSurfaces mService;
    public final ShadeExpansionStateManager mShadeExpansionStateManager;
    public final ShadeLogger mShadeLogger;
    public final ShadeViewController mShadeViewController;
    public NotificationStackScrollLayout mStackScrollLayout;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public PhoneStatusBarViewController mStatusBarViewController;
    public final StatusBarWindowStateController mStatusBarWindowStateController;
    public final SysUIKeyEventHandler mSysUIKeyEventHandler;
    public boolean mTouchActive;
    public boolean mTouchCancelled;
    public final NotificationShadeWindowView mView;
    public boolean mExternalTouchIntercepted = false;
    public boolean mIsTrackingBarGesture = false;
    public boolean mIsOcclusionTransitionRunning = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shade.NotificationShadeWindowViewController$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public boolean mLastInterceptWasDragDownHelper = false;

        public AnonymousClass1() {
        }
    }

    /* renamed from: -$$Nest$mdidNotificationPanelInterceptEvent, reason: not valid java name */
    public static boolean m861$$Nest$mdidNotificationPanelInterceptEvent(NotificationShadeWindowViewController notificationShadeWindowViewController, MotionEvent motionEvent) {
        if (!notificationShadeWindowViewController.mShadeViewController.handleExternalInterceptTouch(motionEvent)) {
            return false;
        }
        notificationShadeWindowViewController.mShadeLogger.d("NSWVC: NPVC intercepted");
        return true;
    }

    /* renamed from: -$$Nest$mlogDownDispatch, reason: not valid java name */
    public static void m862$$Nest$mlogDownDispatch(NotificationShadeWindowViewController notificationShadeWindowViewController, MotionEvent motionEvent, String str, final Boolean bool) {
        notificationShadeWindowViewController.getClass();
        if (motionEvent.getAction() == 0) {
            ShadeLogger shadeLogger = notificationShadeWindowViewController.mShadeLogger;
            LogLevel logLevel = LogLevel.VERBOSE;
            Function1 function1 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logShadeWindowDispatch$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String str2;
                    LogMessage logMessage = (LogMessage) obj;
                    Boolean bool2 = bool;
                    if (Intrinsics.areEqual(bool2, Boolean.TRUE)) {
                        str2 = "SHADE TOUCH REROUTED";
                    } else if (Intrinsics.areEqual(bool2, Boolean.FALSE)) {
                        str2 = "SHADE TOUCH BLOCKED";
                    } else {
                        if (bool2 != null) {
                            throw new NoWhenBranchMatchedException();
                        }
                        str2 = "SHADE TOUCH DISPATCHED";
                    }
                    return str2 + ": eventTime=" + logMessage.getLong1() + ",downTime=" + logMessage.getLong2() + ", reason=" + logMessage.getStr1();
                }
            };
            LogBuffer logBuffer = shadeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("systemui.shade", logLevel, function1, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = str;
            logMessageImpl.long1 = motionEvent.getEventTime();
            logMessageImpl.long2 = motionEvent.getDownTime();
            logBuffer.commit(obtain);
        }
    }

    public NotificationShadeWindowViewController(LockscreenShadeTransitionController lockscreenShadeTransitionController, FalsingCollector falsingCollector, SysuiStatusBarStateController sysuiStatusBarStateController, DockManager dockManager, NotificationShadeDepthController notificationShadeDepthController, NotificationShadeWindowView notificationShadeWindowView, ShadeViewController shadeViewController, PanelExpansionInteractor panelExpansionInteractor, ShadeExpansionStateManager shadeExpansionStateManager, NotificationStackScrollLayoutController notificationStackScrollLayoutController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, StatusBarWindowStateController statusBarWindowStateController, LockIconViewController lockIconViewController, CentralSurfaces centralSurfaces, DozeServiceHost dozeServiceHost, DozeScrimController dozeScrimController, NotificationShadeWindowController notificationShadeWindowController, Optional optional, KeyguardUnlockAnimationController keyguardUnlockAnimationController, NotificationInsetsImpl notificationInsetsImpl, AmbientState ambientState, ShadeLogger shadeLogger, DumpManager dumpManager, PulsingGestureListener pulsingGestureListener, LockscreenHostedDreamGestureListener lockscreenHostedDreamGestureListener, KeyguardTransitionInteractor keyguardTransitionInteractor, GlanceableHubContainerController glanceableHubContainerController, NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor, FeatureFlagsClassic featureFlagsClassic, SystemClock systemClock, SysUIKeyEventHandler sysUIKeyEventHandler, QuickSettingsController quickSettingsController, PrimaryBouncerInteractor primaryBouncerInteractor, AlternateBouncerInteractor alternateBouncerInteractor, BouncerViewBinder bouncerViewBinder) {
        NotificationShadeWindowViewController$$ExternalSyntheticLambda5 notificationShadeWindowViewController$$ExternalSyntheticLambda5 = new NotificationShadeWindowViewController$$ExternalSyntheticLambda5(this, 1);
        this.mLockscreenShadeTransitionController = lockscreenShadeTransitionController;
        this.mFalsingCollector = falsingCollector;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mView = notificationShadeWindowView;
        this.mDockManager = dockManager;
        this.mShadeViewController = shadeViewController;
        this.mPanelExpansionInteractor = panelExpansionInteractor;
        this.mShadeExpansionStateManager = shadeExpansionStateManager;
        this.mDepthController = notificationShadeDepthController;
        this.mNotificationStackScrollLayoutController = notificationStackScrollLayoutController;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mStatusBarWindowStateController = statusBarWindowStateController;
        this.mLockIconViewController = lockIconViewController;
        this.mShadeLogger = shadeLogger;
        this.mService = centralSurfaces;
        this.mDozeServiceHost = dozeServiceHost;
        this.mDozeScrimController = dozeScrimController;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mKeyguardUnlockAnimationController = keyguardUnlockAnimationController;
        this.mAmbientState = ambientState;
        this.mPulsingGestureListener = pulsingGestureListener;
        this.mNotificationInsetsController = notificationInsetsImpl;
        this.mGlanceableHubContainerController = glanceableHubContainerController;
        this.mFeatureFlagsClassic = featureFlagsClassic;
        this.mSysUIKeyEventHandler = sysUIKeyEventHandler;
        this.mPrimaryBouncerInteractor = primaryBouncerInteractor;
        this.mAlternateBouncerInteractor = alternateBouncerInteractor;
        this.mQuickSettingsController = quickSettingsController;
        this.mBrightnessMirror = notificationShadeWindowView.findViewById(R.id.brightness_mirror_container);
        new ArrayList();
        bouncerViewBinder.bind((ViewGroup) notificationShadeWindowView.findViewById(R.id.keyguard_bouncer_container));
        KeyguardState keyguardState = KeyguardState.LOCKSCREEN;
        KeyguardState keyguardState2 = KeyguardState.DREAMING;
        Edge.Companion companion = Edge.Companion;
        JavaAdapterKt.collectFlow(notificationShadeWindowView, keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState, keyguardState2)), notificationShadeWindowViewController$$ExternalSyntheticLambda5);
        JavaAdapterKt.collectFlow(notificationShadeWindowView, notificationLaunchAnimationInteractor.repository.isLaunchAnimationRunning, new NotificationShadeWindowViewController$$ExternalSyntheticLambda5(this, 2));
        Optional map = optional.map(new NotificationPanelViewController$$ExternalSyntheticLambda21(0));
        Optional map2 = optional.map(new NotificationShadeWindowViewController$$ExternalSyntheticLambda2());
        map.ifPresent(new NotificationShadeWindowViewController$$ExternalSyntheticLambda3());
        map2.ifPresent(new NotificationShadeWindowViewController$$ExternalSyntheticLambda5(this, 3));
        this.mClock = systemClock;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlagsClassic.getClass();
        lockIconViewController.setLockIconView((LockIconView) notificationShadeWindowView.findViewById(R.id.lock_icon_view));
        dumpManager.registerDumpable(this);
    }

    public final void cancelCurrentTouch() {
        this.mShadeLogger.d("NSWVC: cancelling current touch");
        if (this.mTouchActive) {
            ((SystemClockImpl) this.mClock).getClass();
            long uptimeMillis = android.os.SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(this.mDownEvent);
            obtain.setDownTime(uptimeMillis);
            obtain.setAction(3);
            obtain.setLocation(0.0f, 0.0f);
            Log.w("NotifShadeWindowVC", "Canceling current touch event (should be very rare)");
            this.mView.dispatchTouchEvent(obtain);
            obtain.recycle();
            this.mTouchCancelled = true;
        }
        AmbientState ambientState = this.mAmbientState;
        if (ambientState.mIsSwipingUp) {
            ambientState.mIsFlingRequiredAfterLockScreenSwipeUp = true;
        }
        ambientState.mIsSwipingUp = false;
        this.mDragDownHelper.stopDragging();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.print("  mExpandingBelowNotch=");
        printWriter.println(this.mExpandingBelowNotch);
        printWriter.print("  mExpandAnimationRunning=");
        printWriter.println(this.mExpandAnimationRunning);
        printWriter.print("  mExternalTouchIntercepted=");
        printWriter.println(this.mExternalTouchIntercepted);
        printWriter.print("  mIsOcclusionTransitionRunning=");
        printWriter.println(this.mIsOcclusionTransitionRunning);
        printWriter.print("  mIsTrackingBarGesture=");
        printWriter.println(this.mIsTrackingBarGesture);
        printWriter.print("  mLaunchAnimationTimeout=");
        printWriter.println(this.mLaunchAnimationTimeout);
        printWriter.print("  mTouchActive=");
        printWriter.println(this.mTouchActive);
        printWriter.print("  mTouchCancelled=");
        printWriter.println(this.mTouchCancelled);
    }

    public void setDragDownHelper(DragDownHelper dragDownHelper) {
        this.mDragDownHelper = dragDownHelper;
    }

    public void setExpandAnimationRunning(boolean z) {
        if (this.mExpandAnimationRunning != z) {
            if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
                MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("Setting mExpandAnimationRunning=", "NotifShadeWindowVC", z);
            }
            if (z) {
                ((SystemClockImpl) this.mClock).getClass();
                this.mLaunchAnimationTimeout = android.os.SystemClock.uptimeMillis() + 5000;
            }
            this.mExpandAnimationRunning = z;
            NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController;
            NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
            notificationShadeWindowState.launchingActivityFromNotification = z;
            notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
        }
    }
}
