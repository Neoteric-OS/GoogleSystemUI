package com.android.systemui.statusbar.phone.fragment;

import android.app.Fragment;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Trace;
import android.telephony.SubscriptionManager;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.IndentingPrintWriter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.animation.Animator;
import com.android.app.animation.Interpolators;
import com.android.app.animation.InterpolatorsAndroidX;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dumpable;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.battery.BatteryMeterViewController;
import com.android.systemui.demomode.DemoMode;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.OperatorNameView;
import com.android.systemui.statusbar.OperatorNameViewController;
import com.android.systemui.statusbar.connectivity.ui.MobileContextProvider;
import com.android.systemui.statusbar.disableflags.DisableFlagsLogger;
import com.android.systemui.statusbar.events.SystemStatusAnimationCallback;
import com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerStatusBarViewBinder;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController;
import com.android.systemui.statusbar.phone.LegacyLightsOutNotifController;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewController;
import com.android.systemui.statusbar.phone.StatusBarBoundsProvider;
import com.android.systemui.statusbar.phone.StatusBarBoundsProvider$layoutListener$1;
import com.android.systemui.statusbar.phone.StatusBarDemoMode;
import com.android.systemui.statusbar.phone.StatusBarHideIconsForBouncerManager;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusBarLocationPublisher;
import com.android.systemui.statusbar.phone.fragment.dagger.StatusBarFragmentComponent$Startable$State;
import com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController;
import com.android.systemui.statusbar.phone.ui.DarkIconManager;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.CollapsedStatusBarViewBinderImpl;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.StatusBarVisibilityChangeListener;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.CollapsedStatusBarViewModel;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.statusbar.window.StatusBarWindowStateListener;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.CarrierConfigTracker;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CollapsedStatusBarFragment extends Fragment implements CommandQueue.Callbacks, StatusBarStateController.StateListener, SystemStatusAnimationCallback, Dumpable {
    public final SystemStatusAnimationSchedulerImpl mAnimationScheduler;
    public final CarrierConfigTracker mCarrierConfigTracker;
    public View mClockView;
    public final CollapsedStatusBarFragmentLogger mCollapsedStatusBarFragmentLogger;
    public final CollapsedStatusBarViewBinderImpl mCollapsedStatusBarViewBinder;
    public final CollapsedStatusBarViewModel mCollapsedStatusBarViewModel;
    public final CommandQueue mCommandQueue;
    public DarkIconManager mDarkIconManager;
    public final DarkIconManager.Factory mDarkIconManagerFactory;
    public final DemoModeController mDemoModeController;
    public final DumpManager mDumpManager;
    public MultiSourceMinAlphaController mEndSideAlphaController;
    public LinearLayout mEndSideContent;
    public boolean mHasPrimaryOngoingActivity;
    public boolean mHasSecondaryOngoingActivity;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final StatusBarLocationPublisher mLocationPublisher;
    public final Executor mMainExecutor;
    public RepeatWhenAttachedKt$repeatWhenAttached$1 mNicBindingDisposable;
    public final NotificationIconContainerStatusBarViewBinder mNicViewBinder;
    public NotificationIconContainer mNotificationIconAreaInner;
    public final OngoingCallController mOngoingCallController;
    public OperatorNameViewController mOperatorNameViewController;
    public final OperatorNameViewController.Factory mOperatorNameViewControllerFactory;
    public final PanelExpansionInteractor mPanelExpansionInteractor;
    public View mPrimaryOngoingActivityChip;
    public View mSecondaryOngoingActivityChip;
    public final SecureSettings mSecureSettings;
    public final ShadeExpansionStateManager mShadeExpansionStateManager;
    public PhoneStatusBarView mStatusBar;
    public DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl mStatusBarFragmentComponent;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory mStatusBarFragmentComponentFactory;
    public final StatusBarHideIconsForBouncerManager mStatusBarHideIconsForBouncerManager;
    public final StatusBarIconController mStatusBarIconController;
    public final StatusBarStateController mStatusBarStateController;
    public final StatusBarWindowStateController mStatusBarWindowStateController;
    public StatusBarSystemEventDefaultAnimator mSystemEventAnimator;
    public StatusBarVisibilityModel mLastSystemVisibility = new StatusBarVisibilityModel(true, true, true, true, true);
    public StatusBarVisibilityModel mLastModifiedVisibility = new StatusBarVisibilityModel(true, true, true, true, true);
    public final List mBlockedIcons = new ArrayList();
    public final Map mStartableStates = new ArrayMap();
    public final AnonymousClass1 mOngoingCallListener = new AnonymousClass1();
    public final AnonymousClass2 mCarrierConfigCallback = new AnonymousClass2();
    public final AnonymousClass3 mDefaultDataListener = new AnonymousClass3();
    public boolean mWaitingForWindowStateChangeAfterCameraLaunch = false;
    public boolean mTransitionFromLockscreenToDreamStarted = false;
    public final CollapsedStatusBarFragment$$ExternalSyntheticLambda4 mStatusBarWindowStateListener = new StatusBarWindowStateListener() { // from class: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$$ExternalSyntheticLambda4
        @Override // com.android.systemui.statusbar.window.StatusBarWindowStateListener
        public final void onStatusBarWindowStateChanged(int i) {
            CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
            collapsedStatusBarFragment.mWaitingForWindowStateChangeAfterCameraLaunch = false;
            collapsedStatusBarFragment.mTransitionFromLockscreenToDreamStarted = false;
        }
    };
    public boolean mAnimationsEnabled = true;
    public final AnonymousClass4 mDemoModeCallback = new DemoMode() { // from class: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment.4
        @Override // com.android.systemui.demomode.DemoMode
        public final List demoCommands() {
            return List.of("notifications");
        }

        @Override // com.android.systemui.demomode.DemoModeCommandReceiver
        public final void dispatchDemoCommand(Bundle bundle, String str) {
            CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
            if (collapsedStatusBarFragment.mNotificationIconAreaInner == null) {
                return;
            }
            if ("false".equals(bundle.getString("visible"))) {
                collapsedStatusBarFragment.mNotificationIconAreaInner.setVisibility(4);
            } else {
                collapsedStatusBarFragment.mNotificationIconAreaInner.setVisibility(0);
            }
        }

        @Override // com.android.systemui.demomode.DemoModeCommandReceiver
        public final void onDemoModeFinished() {
            NotificationIconContainer notificationIconContainer = CollapsedStatusBarFragment.this.mNotificationIconAreaInner;
            if (notificationIconContainer == null) {
                return;
            }
            notificationIconContainer.setVisibility(0);
        }
    };
    public final AnonymousClass5 mStatusBarVisibilityChangeListener = new AnonymousClass5();
    public final AnonymousClass6 mVolumeSettingObserver = new ContentObserver() { // from class: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment.6
        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            CollapsedStatusBarFragment.this.updateBlockedIcons();
        }
    };
    public final CollapsedStatusBarFragment$$ExternalSyntheticLambda5 mStatusBarLayoutListener = new View.OnLayoutChangeListener() { // from class: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$$ExternalSyntheticLambda5
        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
            collapsedStatusBarFragment.getClass();
            if (i == i5 && i3 == i7) {
                return;
            }
            collapsedStatusBarFragment.updateStatusBarLocation(i, i3);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$2, reason: invalid class name */
    public final class AnonymousClass2 {
        public AnonymousClass2() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$3, reason: invalid class name */
    public final class AnonymousClass3 {
        public AnonymousClass3() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$5, reason: invalid class name */
    public final class AnonymousClass5 implements StatusBarVisibilityChangeListener {
        public AnonymousClass5() {
        }
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$4] */
    /* JADX WARN: Type inference failed for: r1v12, types: [com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$6] */
    /* JADX WARN: Type inference failed for: r1v13, types: [com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$$ExternalSyntheticLambda5] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$$ExternalSyntheticLambda4] */
    public CollapsedStatusBarFragment(DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, OngoingCallController ongoingCallController, SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl, StatusBarLocationPublisher statusBarLocationPublisher, ShadeExpansionStateManager shadeExpansionStateManager, StatusBarIconController statusBarIconController, DarkIconManager.Factory factory, CollapsedStatusBarViewModel collapsedStatusBarViewModel, CollapsedStatusBarViewBinderImpl collapsedStatusBarViewBinderImpl, StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager, KeyguardStateController keyguardStateController, PanelExpansionInteractor panelExpansionInteractor, StatusBarStateController statusBarStateController, NotificationIconContainerStatusBarViewBinder notificationIconContainerStatusBarViewBinder, CommandQueue commandQueue, CarrierConfigTracker carrierConfigTracker, CollapsedStatusBarFragmentLogger collapsedStatusBarFragmentLogger, OperatorNameViewController.Factory factory2, SecureSettings secureSettings, Executor executor, DumpManager dumpManager, StatusBarWindowStateController statusBarWindowStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, DemoModeController demoModeController) {
        this.mStatusBarFragmentComponentFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
        this.mOngoingCallController = ongoingCallController;
        this.mAnimationScheduler = systemStatusAnimationSchedulerImpl;
        this.mLocationPublisher = statusBarLocationPublisher;
        this.mShadeExpansionStateManager = shadeExpansionStateManager;
        this.mStatusBarIconController = statusBarIconController;
        this.mCollapsedStatusBarViewModel = collapsedStatusBarViewModel;
        this.mCollapsedStatusBarViewBinder = collapsedStatusBarViewBinderImpl;
        this.mStatusBarHideIconsForBouncerManager = statusBarHideIconsForBouncerManager;
        this.mDarkIconManagerFactory = factory;
        this.mKeyguardStateController = keyguardStateController;
        this.mPanelExpansionInteractor = panelExpansionInteractor;
        this.mStatusBarStateController = statusBarStateController;
        this.mNicViewBinder = notificationIconContainerStatusBarViewBinder;
        this.mCommandQueue = commandQueue;
        this.mCarrierConfigTracker = carrierConfigTracker;
        this.mCollapsedStatusBarFragmentLogger = collapsedStatusBarFragmentLogger;
        this.mOperatorNameViewControllerFactory = factory2;
        this.mSecureSettings = secureSettings;
        this.mMainExecutor = executor;
        this.mDumpManager = dumpManager;
        this.mStatusBarWindowStateController = statusBarWindowStateController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mDemoModeController = demoModeController;
    }

    public final void animateHiddenState(final int i, final View view, boolean z) {
        view.animate().cancel();
        if (z && this.mAnimationsEnabled) {
            view.animate().alpha(0.0f).setDuration(160L).setStartDelay(0L).setInterpolator(Interpolators.ALPHA_OUT).withEndAction(new Runnable() { // from class: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    view.setVisibility(i);
                }
            });
        } else {
            view.setAlpha(0.0f);
            view.setVisibility(i);
        }
    }

    public final void animateShow(View view, boolean z) {
        view.animate().cancel();
        view.setVisibility(0);
        if (!z || !this.mAnimationsEnabled) {
            view.setAlpha(1.0f);
            return;
        }
        view.animate().alpha(1.0f).setDuration(320L).setInterpolator(Interpolators.ALPHA_IN).setStartDelay(50L).withEndAction(null);
        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mKeyguardFadingAway) {
            view.animate().setDuration(((KeyguardStateControllerImpl) this.mKeyguardStateController).mKeyguardFadingAwayDuration).setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN).setStartDelay(((KeyguardStateControllerImpl) this.mKeyguardStateController).mKeyguardFadingAwayDelay).start();
        }
    }

    public final int clockHiddenMode() {
        return (this.mShadeExpansionStateManager.state == 0 || ((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing || this.mStatusBarStateController.isDozing()) ? 8 : 4;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void disable(int i, int i2, int i3, boolean z) {
        if (i != getContext().getDisplayId()) {
            return;
        }
        final CollapsedStatusBarFragmentLogger collapsedStatusBarFragmentLogger = this.mCollapsedStatusBarFragmentLogger;
        collapsedStatusBarFragmentLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragmentLogger$logDisableFlagChange$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CollapsedStatusBarFragmentLogger.this.disableFlagsLogger.getDisableFlagsString(new DisableFlagsLogger.DisableState(logMessage.getInt1(), logMessage.getInt2()), null);
            }
        };
        LogBuffer logBuffer = collapsedStatusBarFragmentLogger.buffer;
        LogMessage obtain = logBuffer.obtain("CollapsedSbFragment", logLevel, function1, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i2;
        logMessageImpl.int2 = i3;
        logBuffer.commit(obtain);
        int i4 = 67108864 & i2;
        this.mLastSystemVisibility = new StatusBarVisibilityModel((8388608 & i2) == 0, (131072 & i2) == 0, i4 == 0, i4 == 0, (i2 & 1048576) == 0 && (i3 & 2) == 0);
        updateStatusBarVisibilities(z);
    }

    public void disableAnimationsForTesting() {
        this.mAnimationsEnabled = false;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.println("mHasPrimaryOngoingActivity=" + this.mHasPrimaryOngoingActivity);
        indentingPrintWriter.println("mHasSecondaryOngoingActivity=" + this.mHasSecondaryOngoingActivity);
        indentingPrintWriter.println("mAnimationsEnabled=" + this.mAnimationsEnabled);
        DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl = this.mStatusBarFragmentComponent;
        if (daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl == null) {
            indentingPrintWriter.println("StatusBarFragmentComponent is null");
            return;
        }
        Set<StatusBarBoundsProvider> singleton = Collections.singleton((StatusBarBoundsProvider) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.statusBarBoundsProvider.get());
        indentingPrintWriter.println("Startables: " + singleton.size());
        indentingPrintWriter.increaseIndent();
        for (StatusBarBoundsProvider statusBarBoundsProvider : singleton) {
            indentingPrintWriter.println(statusBarBoundsProvider + ", state: " + ((StatusBarFragmentComponent$Startable$State) this.mStartableStates.getOrDefault(statusBarBoundsProvider, StatusBarFragmentComponent$Startable$State.NONE)));
        }
        indentingPrintWriter.decreaseIndent();
    }

    public void enableAnimationsForTesting() {
        this.mAnimationsEnabled = true;
    }

    public List getBlockedIcons() {
        return this.mBlockedIcons;
    }

    public final void initOperatorName() {
        OperatorNameViewController operatorNameViewController;
        if (this.mCarrierConfigTracker.getShowOperatorNameInStatusBarConfig(SubscriptionManager.getDefaultDataSubscriptionId())) {
            View findViewById = this.mStatusBar.findViewById(R.id.operator_name);
            OperatorNameViewController.Factory factory = this.mOperatorNameViewControllerFactory;
            OperatorNameViewController operatorNameViewController2 = new OperatorNameViewController((OperatorNameView) findViewById, factory.mDarkIconDispatcher, factory.mTunerService, factory.mTelephonyManager, factory.mKeyguardUpdateMonitor, factory.mCarrierConfigTracker, factory.mAirplaneModeInteractor, factory.mSubscriptionManagerProxy, factory.mJavaAdapter);
            this.mOperatorNameViewController = operatorNameViewController2;
            operatorNameViewController2.init$9();
            if (!((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing || (operatorNameViewController = this.mOperatorNameViewController) == null) {
                return;
            }
            animateHiddenState(4, operatorNameViewController.mView, false);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void onCameraLaunchGestureDetected(int i) {
        this.mWaitingForWindowStateChangeAfterCameraLaunch = true;
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StatusBarWindowStateController statusBarWindowStateController = this.mStatusBarWindowStateController;
        statusBarWindowStateController.listeners.add(this.mStatusBarWindowStateListener);
        this.mDemoModeController.addCallback((DemoMode) this.mDemoModeCallback);
    }

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.status_bar, viewGroup, false);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        StatusBarWindowStateController statusBarWindowStateController = this.mStatusBarWindowStateController;
        statusBarWindowStateController.listeners.remove(this.mStatusBarWindowStateListener);
        this.mDemoModeController.removeCallback((DemoMode) this.mDemoModeCallback);
    }

    @Override // android.app.Fragment
    public final void onDestroyView() {
        super.onDestroyView();
        StatusBarIconController statusBarIconController = this.mStatusBarIconController;
        DarkIconManager darkIconManager = this.mDarkIconManager;
        StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) statusBarIconController;
        statusBarIconControllerImpl.getClass();
        darkIconManager.destroy();
        statusBarIconControllerImpl.mIconGroups.remove(darkIconManager);
        CarrierConfigTracker carrierConfigTracker = this.mCarrierConfigTracker;
        ((ArraySet) carrierConfigTracker.mListeners).remove(this.mCarrierConfigCallback);
        CarrierConfigTracker carrierConfigTracker2 = this.mCarrierConfigTracker;
        ((ArraySet) carrierConfigTracker2.mDataListeners).remove(this.mDefaultDataListener);
        for (StatusBarBoundsProvider statusBarBoundsProvider : Collections.singleton((StatusBarBoundsProvider) this.mStatusBarFragmentComponent.statusBarBoundsProvider.get())) {
            ((ArrayMap) this.mStartableStates).put(statusBarBoundsProvider, StatusBarFragmentComponent$Startable$State.STOPPING);
            View view = statusBarBoundsProvider.startSideContent;
            StatusBarBoundsProvider$layoutListener$1 statusBarBoundsProvider$layoutListener$1 = statusBarBoundsProvider.layoutListener;
            view.removeOnLayoutChangeListener(statusBarBoundsProvider$layoutListener$1);
            statusBarBoundsProvider.endSideContent.removeOnLayoutChangeListener(statusBarBoundsProvider$layoutListener$1);
            ((ArrayMap) this.mStartableStates).put(statusBarBoundsProvider, StatusBarFragmentComponent$Startable$State.STOPPED);
        }
        this.mDumpManager.unregisterDumpable("CollapsedStatusBarFragment");
        RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = this.mNicBindingDisposable;
        if (repeatWhenAttachedKt$repeatWhenAttached$1 != null) {
            repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
            this.mNicBindingDisposable = null;
        }
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onDozingChanged(boolean z) {
        updateStatusBarVisibilities(false);
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        this.mCommandQueue.removeCallback((CommandQueue.Callbacks) this);
        this.mStatusBarStateController.removeCallback(this);
        OngoingCallController ongoingCallController = this.mOngoingCallController;
        AnonymousClass1 anonymousClass1 = this.mOngoingCallListener;
        synchronized (ongoingCallController.mListeners) {
            ongoingCallController.mListeners.remove(anonymousClass1);
        }
        this.mAnimationScheduler.removeCallback(this);
        this.mSecureSettings.unregisterContentObserverSync(this.mVolumeSettingObserver);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
        this.mStatusBarStateController.addCallback(this);
        OngoingCallController ongoingCallController = this.mOngoingCallController;
        AnonymousClass1 anonymousClass1 = this.mOngoingCallListener;
        synchronized (ongoingCallController.mListeners) {
            if (!ongoingCallController.mListeners.contains(anonymousClass1)) {
                ongoingCallController.mListeners.add(anonymousClass1);
            }
        }
        this.mOngoingCallController.setChipView(this.mPrimaryOngoingActivityChip);
        this.mAnimationScheduler.addCallback(this);
        this.mSecureSettings.registerContentObserverForUserSync("status_bar_show_vibrate_icon", false, (ContentObserver) this.mVolumeSettingObserver, -1);
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        this.mStatusBar.saveHierarchyState(sparseArray);
        bundle.putSparseParcelableArray("panel_state", sparseArray);
    }

    @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
    public final Animator onSystemEventAnimationBegin() {
        return this.mSystemEventAnimator.onSystemEventAnimationBegin();
    }

    @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
    public final Animator onSystemEventAnimationFinish(boolean z) {
        return this.mSystemEventAnimator.onSystemEventAnimationFinish(z);
    }

    @Override // android.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        final int i = 0;
        final int i2 = 1;
        super.onViewCreated(view, bundle);
        DumpManager dumpManager = this.mDumpManager;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "CollapsedStatusBarFragment", this);
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = this.mStatusBarFragmentComponentFactory;
        PhoneStatusBarView phoneStatusBarView = (PhoneStatusBarView) getView();
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.getClass();
        phoneStatusBarView.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl = new DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, phoneStatusBarView);
        this.mStatusBarFragmentComponent = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl;
        new BatteryMeterViewController((BatteryMeterView) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideBatteryMeterViewProvider.get(), (StatusBarLocation) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.getStatusBarLocationProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get()).init$9();
        ((HeadsUpAppearanceController) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.headsUpAppearanceControllerProvider.get()).init$9();
        ((PhoneStatusBarViewController) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.providePhoneStatusBarViewControllerProvider.get()).init$9();
        ((LegacyLightsOutNotifController) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.legacyLightsOutNotifControllerProvider.get()).init$9();
        ((StatusBarDemoMode) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.statusBarDemoModeProvider.get()).init$9();
        ((ArrayMap) this.mStartableStates).clear();
        for (StatusBarBoundsProvider statusBarBoundsProvider : Collections.singleton((StatusBarBoundsProvider) this.mStatusBarFragmentComponent.statusBarBoundsProvider.get())) {
            ((ArrayMap) this.mStartableStates).put(statusBarBoundsProvider, StatusBarFragmentComponent$Startable$State.STARTING);
            View view2 = statusBarBoundsProvider.startSideContent;
            StatusBarBoundsProvider$layoutListener$1 statusBarBoundsProvider$layoutListener$1 = statusBarBoundsProvider.layoutListener;
            view2.addOnLayoutChangeListener(statusBarBoundsProvider$layoutListener$1);
            statusBarBoundsProvider.endSideContent.addOnLayoutChangeListener(statusBarBoundsProvider$layoutListener$1);
            ((ArrayMap) this.mStartableStates).put(statusBarBoundsProvider, StatusBarFragmentComponent$Startable$State.STARTED);
        }
        PhoneStatusBarView phoneStatusBarView2 = (PhoneStatusBarView) view;
        this.mStatusBar = phoneStatusBarView2;
        View findViewById = phoneStatusBarView2.findViewById(R.id.status_bar_contents);
        findViewById.addOnLayoutChangeListener(this.mStatusBarLayoutListener);
        updateStatusBarLocation(findViewById.getLeft(), findViewById.getRight());
        if (bundle != null && bundle.containsKey("panel_state")) {
            this.mStatusBar.restoreHierarchyState(bundle.getSparseParcelableArray("panel_state"));
        }
        DarkIconManager.Factory factory = this.mDarkIconManagerFactory;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.statusIcons);
        StatusBarLocation statusBarLocation = StatusBarLocation.HOME;
        MobileContextProvider mobileContextProvider = factory.mMobileContextProvider;
        DarkIconManager darkIconManager = new DarkIconManager(linearLayout, factory.mWifiUiAdapter, factory.mMobileUiAdapter, mobileContextProvider, factory.mDarkIconDispatcher);
        this.mDarkIconManager = darkIconManager;
        darkIconManager.mShouldLog = true;
        updateBlockedIcons();
        ((StatusBarIconControllerImpl) this.mStatusBarIconController).addIconGroup(this.mDarkIconManager);
        LinearLayout linearLayout2 = (LinearLayout) this.mStatusBar.findViewById(R.id.status_bar_end_side_content);
        this.mEndSideContent = linearLayout2;
        this.mEndSideAlphaController = new MultiSourceMinAlphaController(linearLayout2);
        this.mClockView = this.mStatusBar.findViewById(R.id.clock);
        this.mPrimaryOngoingActivityChip = this.mStatusBar.findViewById(R.id.ongoing_activity_chip_primary);
        this.mSecondaryOngoingActivityChip = this.mStatusBar.findViewById(R.id.ongoing_activity_chip_secondary);
        showEndSideContent(false);
        animateShow(this.mClockView, false);
        initOperatorName();
        Trace.beginSection("CollapsedStatusBarFragment#initNotifIconArea");
        ViewGroup viewGroup = (ViewGroup) this.mStatusBar.requireViewById(R.id.notification_icon_area);
        LayoutInflater.from(getContext()).inflate(R.layout.notification_icon_area, viewGroup, true);
        NotificationIconContainer notificationIconContainer = (NotificationIconContainer) viewGroup.requireViewById(R.id.notificationIcons);
        this.mNotificationIconAreaInner = notificationIconContainer;
        this.mNicBindingDisposable = this.mNicViewBinder.bindWhileAttached(notificationIconContainer);
        updateNotificationIconAreaAndOngoingActivityChip(false);
        Trace.endSection();
        this.mSystemEventAnimator = new StatusBarSystemEventDefaultAnimator(getResources(), new Function1(this) { // from class: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$$ExternalSyntheticLambda0
            public final /* synthetic */ CollapsedStatusBarFragment f$0;

            {
                this.f$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Float f = (Float) obj;
                switch (i) {
                    case 0:
                        this.f$0.mEndSideAlphaController.setAlpha(1, f.floatValue());
                        break;
                    default:
                        this.f$0.mEndSideContent.setTranslationX(f.floatValue());
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new Function1(this) { // from class: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$$ExternalSyntheticLambda0
            public final /* synthetic */ CollapsedStatusBarFragment f$0;

            {
                this.f$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Float f = (Float) obj;
                switch (i2) {
                    case 0:
                        this.f$0.mEndSideAlphaController.setAlpha(1, f.floatValue());
                        break;
                    default:
                        this.f$0.mEndSideContent.setTranslationX(f.floatValue());
                        break;
                }
                return Unit.INSTANCE;
            }
        }, false);
        CarrierConfigTracker carrierConfigTracker = this.mCarrierConfigTracker;
        ((ArraySet) carrierConfigTracker.mListeners).add(this.mCarrierConfigCallback);
        CarrierConfigTracker carrierConfigTracker2 = this.mCarrierConfigTracker;
        ((ArraySet) carrierConfigTracker2.mDataListeners).add(this.mDefaultDataListener);
        this.mCollapsedStatusBarViewBinder.bind(this.mStatusBar, this.mCollapsedStatusBarViewModel, this.mStatusBarVisibilityChangeListener);
    }

    public final void showEndSideContent(boolean z) {
        if (!z || !this.mAnimationsEnabled) {
            this.mEndSideAlphaController.setAlpha(2, 1.0f);
            return;
        }
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        if (keyguardStateControllerImpl.mKeyguardFadingAway) {
            this.mEndSideAlphaController.animateToAlpha(1.0f, keyguardStateControllerImpl.mKeyguardFadingAwayDuration, InterpolatorsAndroidX.LINEAR_OUT_SLOW_IN, keyguardStateControllerImpl.mKeyguardFadingAwayDelay);
        } else {
            this.mEndSideAlphaController.animateToAlpha(1.0f, 320L, InterpolatorsAndroidX.ALPHA_IN, 50L);
        }
    }

    public void updateBlockedIcons() {
        this.mBlockedIcons.clear();
        List asList = Arrays.asList(getResources().getStringArray(R.array.config_collapsed_statusbar_icon_blocklist));
        String string = getString(android.R.string.time_of_day);
        boolean z = this.mSecureSettings.getIntForUser("status_bar_show_vibrate_icon", 0, -2) == 0;
        for (int i = 0; i < asList.size(); i++) {
            if (!((String) asList.get(i)).equals(string)) {
                this.mBlockedIcons.add((String) asList.get(i));
            } else if (z) {
                this.mBlockedIcons.add((String) asList.get(i));
            }
        }
        this.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
                collapsedStatusBarFragment.mDarkIconManager.setBlockList(collapsedStatusBarFragment.mBlockedIcons);
            }
        });
    }

    public final void updateNotificationIconAreaAndOngoingActivityChip(boolean z) {
        StatusBarVisibilityModel statusBarVisibilityModel = this.mLastModifiedVisibility;
        boolean z2 = statusBarVisibilityModel.showNotificationIcons;
        boolean z3 = statusBarVisibilityModel.showPrimaryOngoingActivityChip;
        if (!z2 || z3) {
            animateHiddenState(4, this.mNotificationIconAreaInner, z && !z3);
        } else {
            animateShow(this.mNotificationIconAreaInner, z);
        }
        if (z3 && z2) {
            animateShow(this.mPrimaryOngoingActivityChip, z);
        } else {
            animateHiddenState(8, this.mPrimaryOngoingActivityChip, z);
        }
        animateHiddenState(8, this.mSecondaryOngoingActivityChip, z);
    }

    public final void updateStatusBarLocation(int i, int i2) {
        List<WeakReference> list;
        this.mStatusBar.getLeft();
        this.mStatusBar.getRight();
        StatusBarLocationPublisher statusBarLocationPublisher = this.mLocationPublisher;
        statusBarLocationPublisher.getClass();
        synchronized (statusBarLocationPublisher) {
            list = CollectionsKt.toList(statusBarLocationPublisher.listeners);
        }
        for (WeakReference weakReference : list) {
            if (weakReference.get() == null) {
                statusBarLocationPublisher.listeners.remove(weakReference);
            }
            if (weakReference.get() != null) {
                throw new ClassCastException();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0080, code lost:
    
        if (r5.wereIconsJustHidden == false) goto L40;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateStatusBarVisibilities(boolean r21) {
        /*
            Method dump skipped, instructions count: 341
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment.updateStatusBarVisibilities(boolean):void");
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onStateChanged(int i) {
    }
}
