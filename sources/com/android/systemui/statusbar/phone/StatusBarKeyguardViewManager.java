package com.android.systemui.statusbar.phone;

import android.content.res.ColorStateList;
import android.hardware.biometrics.BiometricSourceType;
import android.os.Message;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.WindowInsets;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import com.android.internal.util.LatencyTracker;
import com.android.keyguard.ActiveUnlockConfig;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityContainer;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.TrustGrantFlags;
import com.android.systemui.biometrics.UdfpsKeyguardViewControllerLegacy$occludingAppBiometricUI$1;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.bouncer.shared.model.BouncerShowMessageModel;
import com.android.systemui.bouncer.ui.BouncerViewImpl;
import com.android.systemui.dock.DockManager;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.keyguard.DismissCallbackRegistry;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda1;
import com.android.systemui.keyguard.domain.interactor.KeyguardDismissTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.TaskbarDelegate;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.CameraLauncher;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.shade.ShadeExpansionListener;
import com.android.systemui.shade.ShadeSurface;
import com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl$$ExternalSyntheticLambda0;
import com.android.systemui.unfold.FoldAodAnimationController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.google.android.systemui.dreamliner.DockObserver;
import dagger.Lazy;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import kotlin.Unit;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarKeyguardViewManager implements RemoteInputController.Callback, StatusBarStateController.StateListener, ConfigurationController.ConfigurationListener, ShadeExpansionListener, NavigationModeController.ModeChangedListener, FoldAodAnimationController.FoldAodAnimationStatus {
    public final ActivityStarter mActivityStarter;
    public ActivityStarter.OnDismissAction mAfterKeyguardGoneAction;
    public final AlternateBouncerInteractor mAlternateBouncerInteractor;
    public BiometricUnlockController mBiometricUnlockController;
    public boolean mBouncerShowingOverDream;
    public CentralSurfacesImpl mCentralSurfaces;
    public boolean mCentralSurfacesRegistered;
    public final ConfigurationController mConfigurationController;
    public boolean mDismissActionWillAnimateOnKeyguard;
    public final DismissCallbackRegistry mDismissCallbackRegistry;
    public final DockManager mDockManager;
    public boolean mDozing;
    public final DreamOverlayStateController mDreamOverlayStateController;
    public final DelayableExecutor mExecutor;
    public final FoldAodAnimationController mFoldAodAnimationController;
    public boolean mGesturalNav;
    public boolean mIsDocked;
    public final JavaAdapter mJavaAdapter;
    public Runnable mKeyguardGoneCancelAction;
    public final KeyguardSecurityModel mKeyguardSecurityModel;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateManager;
    public int mLastBiometricMode;
    public boolean mLastBouncerDismissible;
    public boolean mLastDozing;
    public boolean mLastGesturalNav;
    public boolean mLastIsDocked;
    public boolean mLastOccluded;
    public boolean mLastPrimaryBouncerIsOrWillBeShowing;
    public boolean mLastPrimaryBouncerShowing;
    public boolean mLastPulsing;
    public boolean mLastRemoteInputActive;
    public boolean mLastScreenOffAnimationPlaying;
    public boolean mLastShowing;
    public final LatencyTracker mLatencyTracker;
    public final NavigationModeController mNavigationModeController;
    public View mNotificationContainer;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final UdfpsKeyguardViewControllerLegacy$occludingAppBiometricUI$1 mOccludingAppBiometricUI;
    public DismissWithActionRequest mPendingWakeupAction;
    public final PrimaryBouncerCallbackInteractor mPrimaryBouncerCallbackInteractor;
    public final PrimaryBouncerInteractor mPrimaryBouncerInteractor;
    public final BouncerViewImpl mPrimaryBouncerView;
    public boolean mPulsing;
    public boolean mRemoteInputActive;
    public boolean mScreenOffAnimationPlaying;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final Lazy mShadeController;
    public ShadeLockscreenInteractor mShadeLockscreenInteractor;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public TaskbarDelegate mTaskbarDelegate;
    public final KeyguardViewMediator.AnonymousClass4 mViewMediatorCallback;
    public StandaloneCoroutine mListenForCanShowAlternateBouncer = null;
    public float mFraction = -1.0f;
    public boolean mTracking = false;
    public int mAttemptsToShowBouncer = 0;
    public boolean mIsSleeping = false;
    public final AnonymousClass1 mExpansionCallback = new PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback() { // from class: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager.1
        public boolean mPrimaryBouncerAnimating;

        @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
        public final void onExpansionChanged(float f) {
            if (this.mPrimaryBouncerAnimating) {
                StatusBarKeyguardViewManager.this.mCentralSurfaces.setPrimaryBouncerHiddenFraction(f);
            }
        }

        @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
        public final void onFullyHidden() {
            this.mPrimaryBouncerAnimating = false;
            StatusBarKeyguardViewManager.this.updateStates();
        }

        @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
        public final void onFullyShown() {
            this.mPrimaryBouncerAnimating = false;
            StatusBarKeyguardViewManager.this.updateStates();
        }

        @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
        public final void onStartingToHide() {
            this.mPrimaryBouncerAnimating = true;
            StatusBarKeyguardViewManager.this.updateStates();
        }

        @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
        public final void onStartingToShow() {
            this.mPrimaryBouncerAnimating = true;
            StatusBarKeyguardViewManager.this.updateStates();
        }

        @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
        public final void onVisibilityChanged(boolean z) {
            ViewRootImpl viewRootImpl;
            ViewRootImpl viewRootImpl2;
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = StatusBarKeyguardViewManager.this;
            statusBarKeyguardViewManager.mBouncerShowingOverDream = z && statusBarKeyguardViewManager.mDreamOverlayStateController.isOverlayActive();
            if (!z) {
                statusBarKeyguardViewManager.mCentralSurfaces.setPrimaryBouncerHiddenFraction(1.0f);
            }
            AnonymousClass2 anonymousClass2 = statusBarKeyguardViewManager.mOnBackInvokedCallback;
            if (z) {
                if (statusBarKeyguardViewManager.mIsBackCallbackRegistered || (viewRootImpl2 = statusBarKeyguardViewManager.getViewRootImpl()) == null) {
                    return;
                }
                viewRootImpl2.getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, anonymousClass2);
                statusBarKeyguardViewManager.mIsBackCallbackRegistered = true;
                return;
            }
            if (!statusBarKeyguardViewManager.mIsBackCallbackRegistered || (viewRootImpl = statusBarKeyguardViewManager.getViewRootImpl()) == null) {
                return;
            }
            viewRootImpl.getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(anonymousClass2);
            statusBarKeyguardViewManager.mIsBackCallbackRegistered = false;
        }
    };
    public final AnonymousClass2 mOnBackInvokedCallback = new OnBackAnimationCallback() { // from class: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager.2
        @Override // android.window.OnBackAnimationCallback
        public final void onBackCancelled() {
            if (!StatusBarKeyguardViewManager.m883$$Nest$mshouldPlayBackAnimation(StatusBarKeyguardViewManager.this) || StatusBarKeyguardViewManager.this.mPrimaryBouncerView.getDelegate() == null) {
                return;
            }
            ((KeyguardSecurityContainer) StatusBarKeyguardViewManager.this.mPrimaryBouncerView.getDelegate().$securityContainerController.mView).mBackCallback.onBackCancelled();
        }

        @Override // android.window.OnBackInvokedCallback
        public final void onBackInvoked() {
            StatusBarKeyguardViewManager.this.onBackPressed();
            if (!StatusBarKeyguardViewManager.m883$$Nest$mshouldPlayBackAnimation(StatusBarKeyguardViewManager.this) || StatusBarKeyguardViewManager.this.mPrimaryBouncerView.getDelegate() == null) {
                return;
            }
            KeyguardSecurityContainer.AnonymousClass2 anonymousClass2 = ((KeyguardSecurityContainer) StatusBarKeyguardViewManager.this.mPrimaryBouncerView.getDelegate().$securityContainerController.mView).mBackCallback;
        }

        @Override // android.window.OnBackAnimationCallback
        public final void onBackProgressed(BackEvent backEvent) {
            if (!StatusBarKeyguardViewManager.m883$$Nest$mshouldPlayBackAnimation(StatusBarKeyguardViewManager.this) || StatusBarKeyguardViewManager.this.mPrimaryBouncerView.getDelegate() == null) {
                return;
            }
            ((KeyguardSecurityContainer) StatusBarKeyguardViewManager.this.mPrimaryBouncerView.getDelegate().$securityContainerController.mView).mBackCallback.onBackProgressed(backEvent);
        }

        @Override // android.window.OnBackAnimationCallback
        public final void onBackStarted(BackEvent backEvent) {
            if (!StatusBarKeyguardViewManager.m883$$Nest$mshouldPlayBackAnimation(StatusBarKeyguardViewManager.this) || StatusBarKeyguardViewManager.this.mPrimaryBouncerView.getDelegate() == null) {
                return;
            }
            ((KeyguardSecurityContainer) StatusBarKeyguardViewManager.this.mPrimaryBouncerView.getDelegate().$securityContainerController.mView).mBackCallback.onBackStarted(backEvent);
        }
    };
    public boolean mIsBackCallbackRegistered = false;
    public final AnonymousClass3 mDockEventListener = new DockManager.DockEventListener() { // from class: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager.3
        @Override // com.android.systemui.dock.DockManager.DockEventListener
        public final void onEvent(int i) {
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = StatusBarKeyguardViewManager.this;
            boolean isDocked = ((DockObserver) statusBarKeyguardViewManager.mDockManager).isDocked();
            if (isDocked == statusBarKeyguardViewManager.mIsDocked) {
                return;
            }
            statusBarKeyguardViewManager.mIsDocked = isDocked;
            statusBarKeyguardViewManager.updateStates();
        }
    };
    public boolean mGlobalActionsVisible = false;
    public boolean mLastGlobalActionsVisible = false;
    public boolean mFirstUpdate = true;
    public final Set mCallbacks = new HashSet();
    public final ArrayList mAfterKeyguardGoneRunnables = new ArrayList();
    public final KeyguardUpdateMonitorCallback mUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager.4
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onEmergencyCallAction() {
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = StatusBarKeyguardViewManager.this;
            if (((KeyguardStateControllerImpl) statusBarKeyguardViewManager.mKeyguardStateController).mOccluded) {
                statusBarKeyguardViewManager.reset(true, false);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTrustGrantedForCurrentUser(boolean z, boolean z2, TrustGrantFlags trustGrantFlags, String str) {
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = StatusBarKeyguardViewManager.this;
            AlternateBouncerInteractor alternateBouncerInteractor = statusBarKeyguardViewManager.mAlternateBouncerInteractor;
            statusBarKeyguardViewManager.updateAlternateBouncerShowing((!alternateBouncerInteractor.isVisibleState() || ((Boolean) alternateBouncerInteractor.canShowAlternateBouncer.getValue()).booleanValue()) ? false : alternateBouncerInteractor.hide());
        }
    };
    public final AnonymousClass8 mMakeNavigationBarVisibleRunnable = new AnonymousClass8();
    public final boolean mIsBackAnimationEnabled = true;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$6, reason: invalid class name */
    public final class AnonymousClass6 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ StatusBarKeyguardViewManager this$0;
        public final /* synthetic */ boolean val$wasFlingingToDismissKeyguard;

        public /* synthetic */ AnonymousClass6(StatusBarKeyguardViewManager statusBarKeyguardViewManager, boolean z, int i) {
            this.$r8$classId = i;
            this.this$0 = statusBarKeyguardViewManager;
            this.val$wasFlingingToDismissKeyguard = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.mCentralSurfaces.hideKeyguard();
                    NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) this.this$0.mNotificationShadeWindowController;
                    NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                    notificationShadeWindowState.keyguardFadingAway = false;
                    notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                    if (this.val$wasFlingingToDismissKeyguard) {
                        this.this$0.mCentralSurfaces.finishKeyguardFadingAway();
                    }
                    this.this$0.mViewMediatorCallback.keyguardGone();
                    this.this$0.executeAfterKeyguardGoneAction();
                    break;
                default:
                    NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl2 = (NotificationShadeWindowControllerImpl) this.this$0.mNotificationShadeWindowController;
                    NotificationShadeWindowState notificationShadeWindowState2 = notificationShadeWindowControllerImpl2.mCurrentState;
                    notificationShadeWindowState2.keyguardFadingAway = false;
                    notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState2);
                    if (this.val$wasFlingingToDismissKeyguard) {
                        this.this$0.mCentralSurfaces.finishKeyguardFadingAway();
                    }
                    StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.this$0;
                    if (!statusBarKeyguardViewManager.primaryBouncerIsOrWillBeShowing()) {
                        statusBarKeyguardViewManager.mAfterKeyguardGoneAction = null;
                        statusBarKeyguardViewManager.mDismissActionWillAnimateOnKeyguard = false;
                        Runnable runnable = statusBarKeyguardViewManager.mKeyguardGoneCancelAction;
                        if (runnable != null) {
                            runnable.run();
                            statusBarKeyguardViewManager.mKeyguardGoneCancelAction = null;
                            break;
                        }
                    }
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$8, reason: invalid class name */
    public final class AnonymousClass8 implements Runnable {
        public AnonymousClass8() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            NavigationBarView navigationBarView = StatusBarKeyguardViewManager.this.mCentralSurfaces.getNavigationBarView();
            if (navigationBarView != null) {
                navigationBarView.setVisibility(0);
            }
            ((NotificationShadeWindowControllerImpl) StatusBarKeyguardViewManager.this.mNotificationShadeWindowController).mWindowRootView.getWindowInsetsController().show(WindowInsets.Type.navigationBars());
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DismissWithActionRequest {
        public final boolean afterKeyguardGone;
        public final Runnable cancelAction;
        public final ActivityStarter.OnDismissAction dismissAction;
        public final String message;

        public DismissWithActionRequest(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z, String str) {
            this.dismissAction = onDismissAction;
            this.cancelAction = runnable;
            this.afterKeyguardGone = z;
            this.message = str;
        }
    }

    /* renamed from: -$$Nest$mshouldPlayBackAnimation, reason: not valid java name */
    public static boolean m883$$Nest$mshouldPlayBackAnimation(StatusBarKeyguardViewManager statusBarKeyguardViewManager) {
        return !statusBarKeyguardViewManager.needsFullscreenBouncer() && statusBarKeyguardViewManager.mIsBackAnimationEnabled;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$1] */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$2] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$3] */
    public StatusBarKeyguardViewManager(KeyguardViewMediator.AnonymousClass4 anonymousClass4, SysuiStatusBarStateController sysuiStatusBarStateController, ConfigurationController configurationController, KeyguardUpdateMonitor keyguardUpdateMonitor, DreamOverlayStateController dreamOverlayStateController, NavigationModeController navigationModeController, DockManager dockManager, NotificationShadeWindowController notificationShadeWindowController, KeyguardStateController keyguardStateController, KeyguardMessageAreaController.Factory factory, Optional optional, Lazy lazy, LatencyTracker latencyTracker, KeyguardSecurityModel keyguardSecurityModel, PrimaryBouncerCallbackInteractor primaryBouncerCallbackInteractor, PrimaryBouncerInteractor primaryBouncerInteractor, BouncerViewImpl bouncerViewImpl, AlternateBouncerInteractor alternateBouncerInteractor, UdfpsOverlayInteractor udfpsOverlayInteractor, ActivityStarter activityStarter, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardDismissTransitionInteractor keyguardDismissTransitionInteractor, Lazy lazy2, SelectedUserInteractor selectedUserInteractor, JavaAdapter javaAdapter, Lazy lazy3, StatusBarKeyguardViewManagerInteractor statusBarKeyguardViewManagerInteractor, DelayableExecutor delayableExecutor, Lazy lazy4, DismissCallbackRegistry dismissCallbackRegistry) {
        this.mExecutor = delayableExecutor;
        this.mViewMediatorCallback = anonymousClass4;
        this.mConfigurationController = configurationController;
        this.mNavigationModeController = navigationModeController;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mDreamOverlayStateController = dreamOverlayStateController;
        this.mKeyguardStateController = keyguardStateController;
        this.mKeyguardUpdateManager = keyguardUpdateMonitor;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mDockManager = dockManager;
        this.mShadeController = lazy;
        this.mLatencyTracker = latencyTracker;
        this.mKeyguardSecurityModel = keyguardSecurityModel;
        this.mPrimaryBouncerCallbackInteractor = primaryBouncerCallbackInteractor;
        this.mPrimaryBouncerInteractor = primaryBouncerInteractor;
        this.mPrimaryBouncerView = bouncerViewImpl;
        this.mFoldAodAnimationController = (FoldAodAnimationController) optional.map(new DozeParameters$$ExternalSyntheticLambda0()).orElse(null);
        this.mAlternateBouncerInteractor = alternateBouncerInteractor;
        this.mActivityStarter = activityStarter;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mJavaAdapter = javaAdapter;
        this.mDismissCallbackRegistry = dismissCallbackRegistry;
    }

    public void consumeFromAlternateBouncerTransitionSteps(TransitionStep transitionStep) {
        hideAlternateBouncer(false);
    }

    public void consumeKeyguardAuthenticatedBiometricsHandled(Unit unit) {
        if (this.mAlternateBouncerInteractor.isVisibleState()) {
            hideAlternateBouncer(false);
        }
    }

    public final void dismissWithAction(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z, String str) {
        int i;
        Runnable runnable2;
        AlternateBouncerInteractor alternateBouncerInteractor = this.mAlternateBouncerInteractor;
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        if (keyguardStateControllerImpl.mShowing) {
            try {
                Trace.beginSection("StatusBarKeyguardViewManager#dismissWithAction");
                DismissWithActionRequest dismissWithActionRequest = this.mPendingWakeupAction;
                this.mPendingWakeupAction = null;
                if (dismissWithActionRequest != null && (runnable2 = dismissWithActionRequest.cancelAction) != null) {
                    runnable2.run();
                }
                if (this.mDozing && (i = this.mBiometricUnlockController.mMode) != 1 && i != 2) {
                    this.mPendingWakeupAction = new DismissWithActionRequest(onDismissAction, runnable, z, str);
                    return;
                }
                this.mAfterKeyguardGoneAction = onDismissAction;
                this.mKeyguardGoneCancelAction = runnable;
                this.mDismissActionWillAnimateOnKeyguard = onDismissAction != null && onDismissAction.willRunAnimationOnKeyguard();
                boolean booleanValue = ((Boolean) alternateBouncerInteractor.canShowAlternateBouncer.getValue()).booleanValue();
                PrimaryBouncerInteractor primaryBouncerInteractor = this.mPrimaryBouncerInteractor;
                if (booleanValue) {
                    if (!z) {
                        primaryBouncerInteractor.setDismissAction(this.mAfterKeyguardGoneAction, this.mKeyguardGoneCancelAction);
                        this.mAfterKeyguardGoneAction = null;
                        this.mKeyguardGoneCancelAction = null;
                    }
                    Log.d("StatusBarKeyguardViewManager", "dismissWithAction:alternateBouncer.forceShow()");
                    alternateBouncerInteractor.bouncerRepository.setAlternateVisible(true);
                    updateAlternateBouncerShowing(alternateBouncerInteractor.isVisibleState());
                    setKeyguardMessage(str, null);
                    return;
                }
                KeyguardViewMediator.this.mCustomMessage = str;
                if (z) {
                    primaryBouncerInteractor.show(true);
                } else {
                    primaryBouncerInteractor.setDismissAction(this.mAfterKeyguardGoneAction, this.mKeyguardGoneCancelAction);
                    primaryBouncerInteractor.show(true);
                    this.mAfterKeyguardGoneAction = null;
                    this.mKeyguardGoneCancelAction = null;
                }
            } finally {
                Trace.endSection();
            }
        } else {
            Log.w("StatusBarKeyguardViewManager", "Ignoring request to dismiss, dumping state: ");
            StringWriter stringWriter = new StringWriter();
            keyguardStateControllerImpl.dump(new PrintWriter(stringWriter), null);
            Log.w("StatusBarKeyguardViewManager", stringWriter.toString());
        }
        updateStates();
    }

    public final void executeAfterKeyguardGoneAction() {
        ActivityStarter.OnDismissAction onDismissAction = this.mAfterKeyguardGoneAction;
        if (onDismissAction != null) {
            onDismissAction.onDismiss();
            this.mAfterKeyguardGoneAction = null;
        }
        this.mKeyguardGoneCancelAction = null;
        this.mDismissActionWillAnimateOnKeyguard = false;
        for (int i = 0; i < this.mAfterKeyguardGoneRunnables.size(); i++) {
            ((Runnable) this.mAfterKeyguardGoneRunnables.get(i)).run();
        }
        this.mAfterKeyguardGoneRunnables.clear();
    }

    public final ViewRootImpl getViewRootImpl() {
        NotificationShadeWindowView notificationShadeWindowView = ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).mWindowRootView;
        if (notificationShadeWindowView != null) {
            return notificationShadeWindowView.getViewRootImpl();
        }
        return null;
    }

    public final void hide(long j, long j2) {
        Trace.beginSection("StatusBarKeyguardViewManager#hide");
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        keyguardStateControllerImpl.notifyKeyguardState(false, keyguardStateControllerImpl.mOccluded);
        launchPendingWakeupAction();
        long j3 = this.mKeyguardUpdateManager.mNeedsSlowUnlockTransition ? 2000L : j2;
        long max = Math.max(0L, (j - 48) - SystemClock.uptimeMillis());
        boolean z = keyguardStateControllerImpl.mFlingingToDismissKeyguard;
        NotificationShadeWindowController notificationShadeWindowController = this.mNotificationShadeWindowController;
        if (z) {
            CentralSurfacesImpl centralSurfacesImpl = this.mCentralSurfaces;
            AnonymousClass6 anonymousClass6 = new AnonymousClass6(this, z, 0);
            AnonymousClass6 anonymousClass62 = new AnonymousClass6(this, z, 1);
            centralSurfacesImpl.mMessageRouter.cancelMessages(1003);
            centralSurfacesImpl.mLaunchTransitionEndRunnable = anonymousClass6;
            centralSurfacesImpl.mLaunchTransitionCancelRunnable = anonymousClass62;
            KeyguardStateControllerImpl keyguardStateControllerImpl2 = (KeyguardStateControllerImpl) centralSurfacesImpl.mKeyguardStateController;
            keyguardStateControllerImpl2.mLaunchTransitionFadingAway = true;
            keyguardStateControllerImpl2.invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(6));
            NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) notificationShadeWindowController;
            NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
            notificationShadeWindowState.keyguardShowing = false;
            notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
            NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl2 = (NotificationShadeWindowControllerImpl) notificationShadeWindowController;
            NotificationShadeWindowState notificationShadeWindowState2 = notificationShadeWindowControllerImpl2.mCurrentState;
            notificationShadeWindowState2.keyguardFadingAway = true;
            notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState2);
            hideBouncer(true);
            updateStates();
            centralSurfacesImpl.updateScrimController();
            ShadeSurface shadeSurface = centralSurfacesImpl.mShadeSurface;
            shadeSurface.resetAlpha();
            shadeSurface.fadeOut(new CentralSurfacesImpl$$ExternalSyntheticLambda1(centralSurfacesImpl, 2));
            centralSurfacesImpl.mCommandQueue.appTransitionStarting(centralSurfacesImpl.mDisplayId, SystemClock.uptimeMillis(), 120L, true);
        } else {
            executeAfterKeyguardGoneAction();
            CentralSurfacesImpl centralSurfacesImpl2 = this.mCentralSurfaces;
            centralSurfacesImpl2.mCommandQueue.appTransitionStarting(centralSurfacesImpl2.mDisplayId, (j + j3) - 120, 120L, true);
            centralSurfacesImpl2.mCommandQueue.recomputeDisableFlags(centralSurfacesImpl2.mDisplayId, j3 > 0);
            centralSurfacesImpl2.mCommandQueue.appTransitionStarting(centralSurfacesImpl2.mDisplayId, j - 120, 120L, true);
            KeyguardStateControllerImpl keyguardStateControllerImpl3 = (KeyguardStateControllerImpl) centralSurfacesImpl2.mKeyguardStateController;
            keyguardStateControllerImpl3.mKeyguardFadingAwayDelay = max;
            keyguardStateControllerImpl3.mKeyguardFadingAwayDuration = j3;
            if (!keyguardStateControllerImpl3.mKeyguardFadingAway) {
                Trace.traceCounter(4096L, "keyguardFadingAway", 1);
                keyguardStateControllerImpl3.mKeyguardFadingAway = true;
                keyguardStateControllerImpl3.invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(0));
            }
            BiometricUnlockController biometricUnlockController = this.mBiometricUnlockController;
            biometricUnlockController.getClass();
            biometricUnlockController.mHandler.postDelayed(new BiometricUnlockController.AnonymousClass1(biometricUnlockController, 1), 96L);
            hideBouncer(true);
            if (((StatusBarStateControllerImpl) this.mStatusBarStateController).mLeaveOpenOnKeyguardHide) {
                this.mCentralSurfaces.hideKeyguard();
                this.mCentralSurfaces.finishKeyguardFadingAway();
                BiometricUnlockController biometricUnlockController2 = this.mBiometricUnlockController;
                if (biometricUnlockController2.isWakeAndUnlock()) {
                    biometricUnlockController2.mFadedAwayAfterWakeAndUnlock = true;
                }
                biometricUnlockController2.resetMode();
            } else {
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl3 = (NotificationShadeWindowControllerImpl) notificationShadeWindowController;
                NotificationShadeWindowState notificationShadeWindowState3 = notificationShadeWindowControllerImpl3.mCurrentState;
                notificationShadeWindowState3.keyguardFadingAway = true;
                notificationShadeWindowControllerImpl3.apply(notificationShadeWindowState3);
                if (this.mBiometricUnlockController.isWakeAndUnlock() && this.mLatencyTracker.isEnabled()) {
                    this.mLatencyTracker.onActionEnd(this.mBiometricUnlockController.mBiometricType == BiometricSourceType.FACE ? 7 : 2);
                }
                this.mCentralSurfaces.hideKeyguard();
                this.mCentralSurfaces.updateScrimController();
            }
            updateStates();
            NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl4 = (NotificationShadeWindowControllerImpl) notificationShadeWindowController;
            NotificationShadeWindowState notificationShadeWindowState4 = notificationShadeWindowControllerImpl4.mCurrentState;
            notificationShadeWindowState4.keyguardShowing = false;
            notificationShadeWindowControllerImpl4.apply(notificationShadeWindowState4);
            this.mViewMediatorCallback.keyguardGone();
        }
        SysUiStatsLog.write(62, 1);
        Trace.endSection();
    }

    public final void hideAlternateBouncer(boolean z) {
        updateAlternateBouncerShowing(this.mAlternateBouncerInteractor.hide() && z);
    }

    public void hideBouncer(boolean z) {
        Runnable runnable;
        this.mPrimaryBouncerInteractor.hide();
        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing && !primaryBouncerIsOrWillBeShowing()) {
            this.mAfterKeyguardGoneAction = null;
            this.mDismissActionWillAnimateOnKeyguard = false;
            Runnable runnable2 = this.mKeyguardGoneCancelAction;
            if (runnable2 != null) {
                runnable2.run();
                this.mKeyguardGoneCancelAction = null;
            }
        }
        DismissWithActionRequest dismissWithActionRequest = this.mPendingWakeupAction;
        this.mPendingWakeupAction = null;
        if (dismissWithActionRequest == null || (runnable = dismissWithActionRequest.cancelAction) == null) {
            return;
        }
        runnable.run();
    }

    public final boolean isBouncerShowing() {
        return this.mPrimaryBouncerInteractor.isFullyShowing() || this.mAlternateBouncerInteractor.isVisibleState();
    }

    public final boolean isFullscreenBouncer() {
        KeyguardSecurityModel.SecurityMode securityMode;
        BouncerViewImpl bouncerViewImpl = this.mPrimaryBouncerView;
        return bouncerViewImpl.getDelegate() != null && ((securityMode = bouncerViewImpl.getDelegate().$securityContainerController.mCurrentSecurityMode) == KeyguardSecurityModel.SecurityMode.SimPin || securityMode == KeyguardSecurityModel.SecurityMode.SimPuk);
    }

    public final boolean isNavBarVisible() {
        BiometricUnlockController biometricUnlockController = this.mBiometricUnlockController;
        boolean z = biometricUnlockController != null && biometricUnlockController.mMode == 2;
        KeyguardStateController keyguardStateController = this.mKeyguardStateController;
        keyguardStateController.getClass();
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        boolean z2 = keyguardStateControllerImpl.mShowing && !keyguardStateControllerImpl.mOccluded;
        boolean z3 = this.mDozing;
        return !(z2 || (z3 && !z) || this.mScreenOffAnimationPlaying) || this.mPrimaryBouncerInteractor.isFullyShowing() || this.mRemoteInputActive || (((z2 && !z3 && !this.mScreenOffAnimationPlaying) || (this.mPulsing && !this.mIsDocked)) && this.mGesturalNav) || this.mGlobalActionsVisible;
    }

    public final void launchPendingWakeupAction() {
        DismissWithActionRequest dismissWithActionRequest = this.mPendingWakeupAction;
        this.mPendingWakeupAction = null;
        if (dismissWithActionRequest != null) {
            boolean z = ((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing;
            ActivityStarter.OnDismissAction onDismissAction = dismissWithActionRequest.dismissAction;
            if (z) {
                dismissWithAction(onDismissAction, dismissWithActionRequest.cancelAction, dismissWithActionRequest.afterKeyguardGone, dismissWithActionRequest.message);
            } else if (onDismissAction != null) {
                onDismissAction.onDismiss();
            }
        }
    }

    public final boolean needsFullscreenBouncer() {
        KeyguardSecurityModel.SecurityMode securityMode = this.mKeyguardSecurityModel.getSecurityMode(this.mSelectedUserInteractor.getSelectedUserId());
        return securityMode == KeyguardSecurityModel.SecurityMode.SimPin || securityMode == KeyguardSecurityModel.SecurityMode.SimPuk;
    }

    public final void notifyKeyguardAuthenticated(boolean z) {
        this.mPrimaryBouncerInteractor.repository._keyguardAuthenticatedBiometrics.setValue(Boolean.valueOf(z));
        if (this.mAlternateBouncerInteractor.isVisibleState()) {
            executeAfterKeyguardGoneAction();
        }
    }

    public final void onBackPressed() {
        PrimaryBouncerInteractor primaryBouncerInteractor = this.mPrimaryBouncerInteractor;
        if (primaryBouncerInteractor.isFullyShowing()) {
            boolean z = isBouncerShowing() && this.mDreamOverlayStateController.isOverlayActive();
            CentralSurfacesImpl centralSurfacesImpl = this.mCentralSurfaces;
            centralSurfacesImpl.releaseGestureWakeLock();
            ((CameraLauncher) centralSurfacesImpl.mCameraLauncherLazy.get()).mKeyguardBypassController.launchingAffordance = false;
            if (z || (((Boolean) ((StateFlowImpl) primaryBouncerInteractor.repository.primaryBouncerScrimmed.$$delegate_0).getValue()).booleanValue() && !needsFullscreenBouncer())) {
                hideBouncer(false);
                updateStates();
                return;
            }
            boolean z2 = this.mCentralSurfaces.mScrimController.mState == ScrimState.BOUNCER_SCRIMMED;
            reset(z2, false);
            if (z2) {
                ((StatusBarStateControllerImpl) this.mStatusBarStateController).mLeaveOpenOnKeyguardHide = false;
            } else {
                this.mShadeLockscreenInteractor.expandToNotifications();
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onDensityOrFontScaleChanged() {
        hideBouncer(true);
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onDozingChanged(boolean z) {
        if (this.mDozing != z) {
            this.mDozing = z;
            if (z || needsFullscreenBouncer() || ((KeyguardStateControllerImpl) this.mKeyguardStateController).mOccluded) {
                reset(z, false);
            }
            updateStates();
            if (z) {
                return;
            }
            launchPendingWakeupAction();
        }
    }

    @Override // com.android.systemui.unfold.FoldAodAnimationController.FoldAodAnimationStatus
    public final void onFoldToAodAnimationChanged() {
        FoldAodAnimationController foldAodAnimationController = this.mFoldAodAnimationController;
        if (foldAodAnimationController != null) {
            this.mScreenOffAnimationPlaying = foldAodAnimationController.shouldPlayAnimation;
        }
    }

    public final void onKeyguardFadedAway$1() {
        this.mNotificationContainer.postDelayed(new StatusBarKeyguardViewManager$$ExternalSyntheticLambda0(this), 100L);
        this.mShadeLockscreenInteractor.resetViewGroupFade();
        this.mCentralSurfaces.finishKeyguardFadingAway();
        BiometricUnlockController biometricUnlockController = this.mBiometricUnlockController;
        if (biometricUnlockController.isWakeAndUnlock()) {
            biometricUnlockController.mFadedAwayAfterWakeAndUnlock = true;
        }
        biometricUnlockController.resetMode();
    }

    @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
    public final void onNavigationModeChanged(int i) {
        boolean isGesturalMode = QuickStepContract.isGesturalMode(i);
        if (isGesturalMode != this.mGesturalNav) {
            this.mGesturalNav = isGesturalMode;
            updateStates();
        }
    }

    @Override // com.android.systemui.shade.ShadeExpansionListener
    public final void onPanelExpansionChanged(ShadeExpansionChangeEvent shadeExpansionChangeEvent) {
        float f = this.mFraction;
        float f2 = shadeExpansionChangeEvent.fraction;
        boolean z = shadeExpansionChangeEvent.tracking;
        if (f == f2 && this.mTracking == z) {
            return;
        }
        this.mFraction = f2;
        this.mTracking = z;
        boolean z2 = this.mDreamOverlayStateController.isOverlayActive() && (this.mShadeLockscreenInteractor.isExpanded() || ((ShadeController) this.mShadeController.get()).isExpandingOrCollapsing());
        boolean z3 = f2 != 1.0f && z;
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        boolean z4 = keyguardStateControllerImpl.mShowing;
        PrimaryBouncerInteractor primaryBouncerInteractor = this.mPrimaryBouncerInteractor;
        if (z4 && !primaryBouncerIsOrWillBeShowing() && !keyguardStateControllerImpl.mKeyguardGoingAway && z3 && !z2 && !keyguardStateControllerImpl.mOccluded && !keyguardStateControllerImpl.mCanDismissLockScreen && !primaryBouncerInteractor.isAnimatingAway() && ((StatusBarStateControllerImpl) this.mStatusBarStateController).mState != 2) {
            primaryBouncerInteractor.show(false);
        }
        if (primaryBouncerIsOrWillBeShowing()) {
            if (keyguardStateControllerImpl.mShowing) {
                primaryBouncerInteractor.setPanelExpansion(f2);
            } else {
                primaryBouncerInteractor.setPanelExpansion(1.0f);
            }
        }
    }

    @Override // com.android.systemui.statusbar.RemoteInputController.Callback
    public final void onRemoteInputActive(boolean z) {
        this.mRemoteInputActive = z;
        updateStates();
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onThemeChanged() {
        KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = this.mPrimaryBouncerInteractor.repository;
        Boolean bool = Boolean.TRUE;
        StateFlowImpl stateFlowImpl = keyguardBouncerRepositoryImpl._resourceUpdateRequests;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
    }

    public final boolean primaryBouncerIsOrWillBeShowing() {
        return isBouncerShowing() || this.mPrimaryBouncerInteractor.isInTransit();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0022, code lost:
    
        if (r1.mCancelAction == null) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean primaryBouncerNeedsScrimming() {
        /*
            r3 = this;
            com.android.systemui.statusbar.policy.KeyguardStateController r0 = r3.mKeyguardStateController
            com.android.systemui.statusbar.policy.KeyguardStateControllerImpl r0 = (com.android.systemui.statusbar.policy.KeyguardStateControllerImpl) r0
            boolean r0 = r0.mOccluded
            if (r0 == 0) goto L10
            com.android.systemui.dreams.DreamOverlayStateController r0 = r3.mDreamOverlayStateController
            boolean r0 = r0.isOverlayActive()
            if (r0 == 0) goto L45
        L10:
            com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor r0 = r3.mPrimaryBouncerInteractor
            com.android.systemui.bouncer.ui.BouncerViewImpl r1 = r0.primaryBouncerView
            com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$delegate$1 r1 = r1.getDelegate()
            if (r1 == 0) goto L25
            com.android.keyguard.KeyguardSecurityContainerController r1 = r1.$securityContainerController
            com.android.systemui.plugins.ActivityStarter$OnDismissAction r2 = r1.mDismissAction
            if (r2 != 0) goto L45
            java.lang.Runnable r1 = r1.mCancelAction
            if (r1 == 0) goto L25
            goto L45
        L25:
            boolean r1 = r0.isFullyShowing()
            if (r1 == 0) goto L3f
            com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl r0 = r0.repository
            kotlinx.coroutines.flow.ReadonlyStateFlow r0 = r0.primaryBouncerScrimmed
            kotlinx.coroutines.flow.MutableStateFlow r0 = r0.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r0 = (kotlinx.coroutines.flow.StateFlowImpl) r0
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L45
        L3f:
            boolean r3 = r3.isFullscreenBouncer()
            if (r3 == 0) goto L47
        L45:
            r3 = 1
            goto L48
        L47:
            r3 = 0
        L48:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager.primaryBouncerNeedsScrimming():boolean");
    }

    public final void reset(boolean z, boolean z2) {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        if (keyguardStateControllerImpl.mShowing) {
            PrimaryBouncerInteractor primaryBouncerInteractor = this.mPrimaryBouncerInteractor;
            if (primaryBouncerInteractor.isAnimatingAway()) {
                return;
            }
            boolean z3 = keyguardStateControllerImpl.mOccluded;
            this.mShadeLockscreenInteractor.resetViews(!z3);
            if (!z3 || this.mDozing) {
                showBouncerOrKeyguard(z, z2);
            } else {
                this.mCentralSurfaces.hideKeyguard();
                if (z || needsFullscreenBouncer()) {
                    hideBouncer(false);
                }
            }
            if (z && isBouncerShowing()) {
                hideAlternateBouncer(true);
                this.mDismissCallbackRegistry.notifyDismissCancelled();
                primaryBouncerInteractor.setDismissAction(null, null);
            }
            this.mKeyguardUpdateManager.mHandler.obtainMessage(312).sendToTarget();
            updateStates();
        }
    }

    public void setAttemptsToShowBouncer(int i) {
        this.mAttemptsToShowBouncer = i;
    }

    public final void setKeyguardMessage(String str, ColorStateList colorStateList) {
        if (this.mAlternateBouncerInteractor.isVisibleState()) {
            return;
        }
        PrimaryBouncerInteractor primaryBouncerInteractor = this.mPrimaryBouncerInteractor;
        primaryBouncerInteractor.getClass();
        primaryBouncerInteractor.repository._showMessage.setValue(new BouncerShowMessageModel(str, colorStateList));
    }

    public final void setOccluded(boolean z, boolean z2) {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        boolean z3 = keyguardStateControllerImpl.mOccluded;
        boolean z4 = !z3 && z;
        boolean z5 = z3 && !z;
        keyguardStateControllerImpl.notifyKeyguardState(keyguardStateControllerImpl.mShowing, z);
        updateStates();
        boolean z6 = keyguardStateControllerImpl.mShowing;
        boolean z7 = keyguardStateControllerImpl.mOccluded;
        if (z6 && z4) {
            SysUiStatsLog.write(62, 3);
            CentralSurfacesImpl centralSurfacesImpl = this.mCentralSurfaces;
            if (centralSurfacesImpl.mIsLaunchingActivityOverLockscreen) {
                StatusBarKeyguardViewManager$$ExternalSyntheticLambda0 statusBarKeyguardViewManager$$ExternalSyntheticLambda0 = new StatusBarKeyguardViewManager$$ExternalSyntheticLambda0(this, z7);
                if (centralSurfacesImpl.mDismissingShadeForActivityLaunch) {
                    ((BaseShadeControllerImpl) ((ShadeController) this.mShadeController.get())).postCollapseActions.add(statusBarKeyguardViewManager$$ExternalSyntheticLambda0);
                    return;
                } else {
                    statusBarKeyguardViewManager$$ExternalSyntheticLambda0.run();
                    return;
                }
            }
        } else if (z6 && z5) {
            SysUiStatsLog.write(62, 2);
        }
        if (this.mDozing || keyguardStateControllerImpl.mKeyguardGoingAway) {
            return;
        }
        reset(z4, false);
    }

    public final void show$3() {
        Trace.beginSection("StatusBarKeyguardViewManager#show");
        NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController;
        NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
        notificationShadeWindowState.keyguardShowing = true;
        notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        keyguardStateControllerImpl.notifyKeyguardState(true, keyguardStateControllerImpl.mOccluded);
        reset(true, false);
        SysUiStatsLog.write(62, 2);
        Trace.endSection();
    }

    public final void showBouncerOrKeyguard(final boolean z, final boolean z2) {
        if (!needsFullscreenBouncer() || this.mDozing || this.mIsSleeping) {
            this.mCentralSurfaces.showKeyguard();
            if (z) {
                hideBouncer(false);
            }
        } else {
            PrimaryBouncerInteractor primaryBouncerInteractor = this.mPrimaryBouncerInteractor;
            if (primaryBouncerInteractor.isFullyShowing()) {
                if (!z2) {
                    Log.i("StatusBarKeyguardViewManager", "Sim bouncer is already showing, issuing a refresh");
                    primaryBouncerInteractor.show(true);
                }
            } else if (primaryBouncerInteractor.show(true)) {
                this.mAttemptsToShowBouncer = 0;
                this.mCentralSurfaces.hideKeyguard();
            } else {
                int i = this.mAttemptsToShowBouncer;
                if (i > 6) {
                    this.mAttemptsToShowBouncer = 0;
                    Log.e("StatusBarKeyguardViewManager", "Too many failed attempts to show bouncer, showing keyguard instead");
                    this.mCentralSurfaces.showKeyguard();
                } else {
                    this.mAttemptsToShowBouncer = i + 1;
                    this.mExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            StatusBarKeyguardViewManager.this.showBouncerOrKeyguard(z, z2);
                        }
                    }, 500L);
                }
            }
        }
        updateStates();
    }

    public final void showPrimaryBouncer(boolean z) {
        hideAlternateBouncer(false);
        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing && !isBouncerShowing()) {
            this.mPrimaryBouncerInteractor.show(z);
        }
        updateStates();
    }

    public final void startPreHideAnimation(KeyguardViewMediator$$ExternalSyntheticLambda1 keyguardViewMediator$$ExternalSyntheticLambda1) {
        PrimaryBouncerInteractor primaryBouncerInteractor = this.mPrimaryBouncerInteractor;
        if (primaryBouncerInteractor.isFullyShowing()) {
            if (primaryBouncerInteractor.willRunDismissFromKeyguard()) {
                keyguardViewMediator$$ExternalSyntheticLambda1.run();
            } else {
                primaryBouncerInteractor.repository._primaryBouncerDisappearAnimation.setValue(keyguardViewMediator$$ExternalSyntheticLambda1);
            }
            this.mShadeLockscreenInteractor.startBouncerPreHideAnimation();
            if (this.mDismissActionWillAnimateOnKeyguard) {
                updateStates();
            }
        } else if (keyguardViewMediator$$ExternalSyntheticLambda1 != null) {
            keyguardViewMediator$$ExternalSyntheticLambda1.run();
        }
        this.mShadeLockscreenInteractor.blockExpansionForCurrentTouch();
    }

    public final void updateAlternateBouncerShowing(boolean z) {
        if (this.mCentralSurfacesRegistered) {
            boolean isVisibleState = this.mAlternateBouncerInteractor.isVisibleState();
            KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateManager;
            keyguardUpdateMonitor.mAlternateBouncerShowing = isVisibleState;
            if (isVisibleState) {
                keyguardUpdateMonitor.requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.UNLOCK_INTENT, "alternateBouncer");
            }
            keyguardUpdateMonitor.updateFingerprintListeningState(2);
            if (z) {
                this.mCentralSurfaces.updateScrimController();
            }
        }
    }

    public final void updateStates() {
        TaskbarDelegate taskbarDelegate;
        if (this.mCentralSurfacesRegistered) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
            boolean z = keyguardStateControllerImpl.mShowing;
            boolean z2 = keyguardStateControllerImpl.mOccluded;
            PrimaryBouncerInteractor primaryBouncerInteractor = this.mPrimaryBouncerInteractor;
            boolean isFullyShowing = primaryBouncerInteractor.isFullyShowing();
            boolean primaryBouncerIsOrWillBeShowing = primaryBouncerIsOrWillBeShowing();
            boolean isFullscreenBouncer = isFullscreenBouncer();
            boolean z3 = !isFullscreenBouncer;
            boolean z4 = this.mRemoteInputActive;
            if (((isFullscreenBouncer && z && !z4) ? false : true) != (this.mLastBouncerDismissible || !this.mLastShowing || this.mLastRemoteInputActive) || this.mFirstUpdate) {
                KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = primaryBouncerInteractor.repository;
                if (isFullscreenBouncer && z && !z4) {
                    StateFlowImpl stateFlowImpl = keyguardBouncerRepositoryImpl._isBackButtonEnabled;
                    Boolean bool = Boolean.FALSE;
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, bool);
                } else {
                    StateFlowImpl stateFlowImpl2 = keyguardBouncerRepositoryImpl._isBackButtonEnabled;
                    Boolean bool2 = Boolean.TRUE;
                    stateFlowImpl2.getClass();
                    stateFlowImpl2.updateState(null, bool2);
                }
            }
            boolean isNavBarVisible = isNavBarVisible();
            boolean z5 = this.mLastShowing && !this.mLastOccluded;
            boolean z6 = this.mLastDozing;
            boolean z7 = !(z5 || (z6 && this.mLastBiometricMode != 2) || this.mLastScreenOffAnimationPlaying) || this.mLastPrimaryBouncerShowing || this.mLastRemoteInputActive || (((z5 && !z6 && !this.mLastScreenOffAnimationPlaying) || (this.mLastPulsing && !this.mLastIsDocked)) && this.mLastGesturalNav) || this.mLastGlobalActionsVisible;
            NotificationShadeWindowController notificationShadeWindowController = this.mNotificationShadeWindowController;
            if ((isNavBarVisible != z7 || this.mFirstUpdate) && (this.mCentralSurfaces.getNavigationBarView() != null || ((taskbarDelegate = this.mTaskbarDelegate) != null && taskbarDelegate.mInitialized))) {
                if (isNavBarVisible) {
                    long j = keyguardStateControllerImpl.mKeyguardFadingAway ? keyguardStateControllerImpl.mKeyguardFadingAwayDelay : isBouncerShowing() ? 320L : 0L;
                    if (j == 0) {
                        this.mMakeNavigationBarVisibleRunnable.run();
                    } else {
                        this.mNotificationContainer.postOnAnimationDelayed(this.mMakeNavigationBarVisibleRunnable, j);
                    }
                } else {
                    this.mNotificationContainer.removeCallbacks(this.mMakeNavigationBarVisibleRunnable);
                    ((NotificationShadeWindowControllerImpl) notificationShadeWindowController).mWindowRootView.getWindowInsetsController().hide(WindowInsets.Type.navigationBars());
                }
            }
            boolean z8 = isFullyShowing != this.mLastPrimaryBouncerShowing;
            this.mLastPrimaryBouncerShowing = isFullyShowing;
            if (z8 || this.mFirstUpdate) {
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) notificationShadeWindowController;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                notificationShadeWindowState.bouncerShowing = isFullyShowing;
                notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                CentralSurfacesImpl centralSurfacesImpl = this.mCentralSurfaces;
                centralSurfacesImpl.mBouncerShowing = isFullyShowing;
                centralSurfacesImpl.mKeyguardBypassController.bouncerShowing = isFullyShowing;
                centralSurfacesImpl.mPulseExpansionHandler.bouncerShowing = isFullyShowing;
                int i = isFullyShowing ? 4 : 0;
                PhoneStatusBarViewController phoneStatusBarViewController = centralSurfacesImpl.mPhoneStatusBarViewController;
                if (phoneStatusBarViewController != null) {
                    ((PhoneStatusBarView) phoneStatusBarViewController.mView).setImportantForAccessibility(i);
                }
                ShadeSurface shadeSurface = centralSurfacesImpl.mShadeSurface;
                shadeSurface.setImportantForAccessibility(i);
                shadeSurface.setBouncerShowing(isFullyShowing);
                StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager = centralSurfacesImpl.mStatusBarHideIconsForBouncerManager;
                statusBarHideIconsForBouncerManager.bouncerShowing = isFullyShowing;
                statusBarHideIconsForBouncerManager.updateHideIconsForBouncer(true);
                centralSurfacesImpl.mCommandQueue.recomputeDisableFlags(centralSurfacesImpl.mDisplayId, true);
                if (centralSurfacesImpl.mBouncerShowing) {
                    centralSurfacesImpl.mPowerInteractor.wakeUpIfDozing(4, "BOUNCER_VISIBLE");
                }
                centralSurfacesImpl.updateScrimController();
                if (!centralSurfacesImpl.mBouncerShowing) {
                    centralSurfacesImpl.updatePanelExpansionForKeyguard();
                }
            }
            if (primaryBouncerIsOrWillBeShowing != this.mLastPrimaryBouncerIsOrWillBeShowing || this.mFirstUpdate || z8) {
                KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateManager;
                keyguardUpdateMonitor.mLogger.logSendPrimaryBouncerChanged(primaryBouncerIsOrWillBeShowing, isFullyShowing);
                Message obtainMessage = keyguardUpdateMonitor.mHandler.obtainMessage(322);
                obtainMessage.arg1 = primaryBouncerIsOrWillBeShowing ? 1 : 0;
                obtainMessage.arg2 = isFullyShowing ? 1 : 0;
                obtainMessage.sendToTarget();
            }
            this.mFirstUpdate = false;
            this.mLastShowing = z;
            this.mLastGlobalActionsVisible = this.mGlobalActionsVisible;
            this.mLastOccluded = z2;
            this.mLastPrimaryBouncerIsOrWillBeShowing = primaryBouncerIsOrWillBeShowing;
            this.mLastBouncerDismissible = z3;
            this.mLastRemoteInputActive = z4;
            this.mLastDozing = this.mDozing;
            this.mLastPulsing = this.mPulsing;
            this.mLastScreenOffAnimationPlaying = this.mScreenOffAnimationPlaying;
            this.mLastBiometricMode = this.mBiometricUnlockController.mMode;
            this.mLastGesturalNav = this.mGesturalNav;
            this.mLastIsDocked = this.mIsDocked;
            this.mCentralSurfaces.logStateToEventlog();
        }
    }
}
