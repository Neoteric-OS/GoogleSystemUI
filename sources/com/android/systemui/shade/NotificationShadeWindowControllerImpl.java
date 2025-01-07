package com.android.systemui.shade;

import android.R;
import android.app.IActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Trace;
import android.view.Display;
import android.view.WindowManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.common.buffer.RingBuffer;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.dump.DumpsysTableLogger;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.shade.ui.viewmodel.NotificationShadeWindowModel;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager$$ExternalSyntheticLambda1;
import com.android.systemui.statusbar.phone.StatusBarWindowCallback;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WindowRootViewComponentFactory;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationShadeWindowControllerImpl implements NotificationShadeWindowController, Dumpable, ConfigurationController.ConfigurationListener {
    public final IActivityManager mActivityManager;
    public final AuthController mAuthController;
    public final Executor mBackgroundExecutor;
    public final SysuiColorExtractor mColorExtractor;
    public final Lazy mCommunalInteractor;
    public final Context mContext;
    public int mDeferWindowLayoutParams;
    public final DozeParameters mDozeParameters;
    public StatusBarTouchableRegionManager$$ExternalSyntheticLambda1 mForcePluginOpenListener;
    public boolean mHasTopUi;
    public boolean mHasTopUiChanged;
    public final KeyguardBypassController mKeyguardBypassController;
    public final float mKeyguardMaxRefreshRate;
    public final float mKeyguardPreferredRefreshRate;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardViewMediator mKeyguardViewMediator;
    public boolean mLastKeyguardRotationAllowed;
    public CentralSurfacesImpl$$ExternalSyntheticLambda0 mListener;
    public final long mLockScreenDisplayTimeout;
    public final ShadeWindowLogger mLogger;
    public WindowManager.LayoutParams mLp;
    public final WindowManager.LayoutParams mLpChanged;
    public final NotificationShadeWindowModel mNotificationShadeWindowModel;
    public float mScreenBrightnessDoze;
    public NotificationShadeDepthController.AnonymousClass1 mScrimsVisibilityListener;
    public final Lazy mShadeInteractorLazy;
    public final AnonymousClass1 mStateListener;
    public final Lazy mUserInteractor;
    public final UserTracker.Callback mUserTrackerCallback;
    public final ViewCaptureAwareWindowManager mWindowManager;
    public NotificationShadeWindowView mWindowRootView;
    public final DaggerSysUIGoogleGlobalRootComponent$WindowRootViewComponentFactory mWindowRootViewComponentFactory;
    public final NotificationShadeWindowState mCurrentState = new NotificationShadeWindowState();
    public final ArrayList mCallbacks = new ArrayList();
    public final NotificationShadeWindowState.Buffer mStateBuffer = new NotificationShadeWindowState.Buffer();

    public NotificationShadeWindowControllerImpl(Context context, DaggerSysUIGoogleGlobalRootComponent$WindowRootViewComponentFactory daggerSysUIGoogleGlobalRootComponent$WindowRootViewComponentFactory, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, IActivityManager iActivityManager, DozeParameters dozeParameters, StatusBarStateController statusBarStateController, ConfigurationController configurationController, KeyguardViewMediator keyguardViewMediator, KeyguardBypassController keyguardBypassController, Executor executor, Executor executor2, SysuiColorExtractor sysuiColorExtractor, DumpManager dumpManager, KeyguardStateController keyguardStateController, AuthController authController, Lazy lazy, ShadeWindowLogger shadeWindowLogger, Lazy lazy2, UserTracker userTracker, NotificationShadeWindowModel notificationShadeWindowModel, Lazy lazy3) {
        StatusBarStateController.StateListener stateListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.shade.NotificationShadeWindowControllerImpl.1
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDozingChanged(boolean z) {
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = NotificationShadeWindowControllerImpl.this;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                notificationShadeWindowState.dozing = z;
                notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDreamingChanged(boolean z) {
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = NotificationShadeWindowControllerImpl.this;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                notificationShadeWindowState.dreaming = z;
                notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = NotificationShadeWindowControllerImpl.this;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                notificationShadeWindowState.statusBarState = i;
                notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
            }
        };
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.shade.NotificationShadeWindowControllerImpl.2
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onBeforeUserSwitching(int i) {
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = NotificationShadeWindowControllerImpl.this;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                if (notificationShadeWindowState.isSwitchingUsers) {
                    return;
                }
                notificationShadeWindowState.isSwitchingUsers = true;
                notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
            }

            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = NotificationShadeWindowControllerImpl.this;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                if (notificationShadeWindowState.isSwitchingUsers) {
                    notificationShadeWindowState.isSwitchingUsers = false;
                    notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                }
            }
        };
        this.mUserTrackerCallback = callback;
        this.mContext = context;
        this.mWindowRootViewComponentFactory = daggerSysUIGoogleGlobalRootComponent$WindowRootViewComponentFactory;
        this.mWindowManager = viewCaptureAwareWindowManager;
        this.mActivityManager = iActivityManager;
        this.mDozeParameters = dozeParameters;
        this.mKeyguardStateController = keyguardStateController;
        this.mLogger = shadeWindowLogger;
        this.mScreenBrightnessDoze = dozeParameters.mResources.getInteger(R.integer.config_screen_magnification_multi_tap_adjustment) / 255.0f;
        this.mLpChanged = new WindowManager.LayoutParams();
        this.mKeyguardViewMediator = keyguardViewMediator;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mBackgroundExecutor = executor2;
        this.mColorExtractor = sysuiColorExtractor;
        this.mNotificationShadeWindowModel = notificationShadeWindowModel;
        dumpManager.registerCriticalDumpable("{slow}NotificationShadeWindowControllerImpl", this);
        this.mAuthController = authController;
        this.mUserInteractor = lazy2;
        this.mCommunalInteractor = lazy3;
        this.mLastKeyguardRotationAllowed = ((KeyguardStateControllerImpl) keyguardStateController).isKeyguardScreenRotationAllowed();
        this.mLockScreenDisplayTimeout = context.getResources().getInteger(com.android.wm.shell.R.integer.config_lockScreenDisplayTimeout);
        this.mShadeInteractorLazy = lazy;
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) ((SysuiStatusBarStateController) statusBarStateController);
        synchronized (statusBarStateControllerImpl.mListeners) {
            statusBarStateControllerImpl.addListenerInternalLocked(stateListener, 1);
        }
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
        ((UserTrackerImpl) userTracker).addCallback(callback, executor);
        float f = -1.0f;
        if (context.getResources().getInteger(com.android.wm.shell.R.integer.config_keyguardRefreshRate) > -1.0f) {
            Display.Mode[] systemSupportedModes = context.getDisplay().getSystemSupportedModes();
            int length = systemSupportedModes.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Display.Mode mode = systemSupportedModes[i];
                if (Math.abs(mode.getRefreshRate() - r1) <= 0.1d) {
                    f = mode.getRefreshRate();
                    break;
                }
                i++;
            }
        }
        this.mKeyguardPreferredRefreshRate = f;
        this.mKeyguardMaxRefreshRate = context.getResources().getInteger(com.android.wm.shell.R.integer.config_keyguardMaxRefreshRate);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0337  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void apply(com.android.systemui.shade.NotificationShadeWindowState r29) {
        /*
            Method dump skipped, instructions count: 937
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.NotificationShadeWindowControllerImpl.apply(com.android.systemui.shade.NotificationShadeWindowState):void");
    }

    public final void applyWindowLayoutParams() {
        WindowManager.LayoutParams layoutParams;
        if (this.mDeferWindowLayoutParams != 0 || (layoutParams = this.mLp) == null || layoutParams.copyFrom(this.mLpChanged) == 0) {
            return;
        }
        Trace.beginSection("updateViewLayout");
        this.mWindowManager.updateViewLayout(this.mWindowRootView, this.mLp);
        Trace.endSection();
    }

    public final void batchApplyWindowLayoutParams(Runnable runnable) {
        this.mDeferWindowLayoutParams++;
        runnable.run();
        this.mDeferWindowLayoutParams--;
        applyWindowLayoutParams();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "NotificationShadeWindowController:", "  mKeyguardMaxRefreshRate="), this.mKeyguardMaxRefreshRate, printWriter, "  mKeyguardPreferredRefreshRate="), this.mKeyguardPreferredRefreshRate, printWriter, "  preferredMinDisplayRefreshRate="), this.mLpChanged.preferredMinDisplayRefreshRate, printWriter, "  preferredMaxDisplayRefreshRate="), this.mLpChanged.preferredMaxDisplayRefreshRate, printWriter, "  mDeferWindowLayoutParams=");
        m.append(this.mDeferWindowLayoutParams);
        printWriter.println(m.toString());
        printWriter.println(this.mCurrentState);
        NotificationShadeWindowView notificationShadeWindowView = this.mWindowRootView;
        if (notificationShadeWindowView != null && notificationShadeWindowView.getViewRootImpl() != null) {
            Trace.beginSection("mWindowRootView.dump()");
            this.mWindowRootView.getViewRootImpl().dump("  ", printWriter);
            Trace.endSection();
        }
        Trace.beginSection("Table<State>");
        List list = NotificationShadeWindowState.TABLE_HEADERS;
        NotificationShadeWindowState.Buffer buffer = this.mStateBuffer;
        buffer.getClass();
        RingBuffer ringBuffer = buffer.buffer;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(ringBuffer, 10));
        int i = 0;
        while (true) {
            if (!(i < ringBuffer.getSize())) {
                new DumpsysTableLogger("NotificationShadeWindowController", list, arrayList).printTableData(printWriter);
                Trace.endSection();
                return;
            } else {
                if (i >= ringBuffer.getSize()) {
                    throw new NoSuchElementException();
                }
                Object obj = ringBuffer.get(i);
                i++;
                arrayList.add((List) ((NotificationShadeWindowState) obj).asStringList$delegate.getValue());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isExpanded(NotificationShadeWindowState notificationShadeWindowState) {
        boolean z = (!notificationShadeWindowState.forceWindowCollapsed && (notificationShadeWindowState.isKeyguardShowingAndNotOccluded() || notificationShadeWindowState.panelVisible || notificationShadeWindowState.keyguardFadingAway || notificationShadeWindowState.bouncerShowing || notificationShadeWindowState.headsUpNotificationShowing || notificationShadeWindowState.scrimsVisibility != 0)) || notificationShadeWindowState.backgroundBlurRadius > 0 || notificationShadeWindowState.launchingActivityFromNotification;
        boolean z2 = notificationShadeWindowState.forceWindowCollapsed;
        boolean isKeyguardShowingAndNotOccluded = notificationShadeWindowState.isKeyguardShowingAndNotOccluded();
        boolean z3 = notificationShadeWindowState.panelVisible;
        boolean z4 = notificationShadeWindowState.keyguardFadingAway;
        boolean z5 = notificationShadeWindowState.bouncerShowing;
        boolean z6 = notificationShadeWindowState.headsUpNotificationShowing;
        Object[] objArr = notificationShadeWindowState.scrimsVisibility != 0;
        boolean z7 = notificationShadeWindowState.backgroundBlurRadius > 0;
        boolean z8 = notificationShadeWindowState.launchingActivityFromNotification;
        ShadeWindowLogger shadeWindowLogger = this.mLogger;
        shadeWindowLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        ShadeWindowLogger$logIsExpanded$2 shadeWindowLogger$logIsExpanded$2 = new Function1() { // from class: com.android.systemui.shade.ShadeWindowLogger$logIsExpanded$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                boolean bool4 = logMessage.getBool4();
                int int1 = logMessage.getInt1();
                int int2 = logMessage.getInt2();
                long long1 = logMessage.getLong1();
                long long2 = logMessage.getLong2();
                double double1 = logMessage.getDouble1();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Setting isExpanded to ", str1, ": forceWindowCollapsed ", bool1, ", isKeyguardShowingAndNotOccluded ");
                BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, bool2, ", panelVisible ", bool3, ", keyguardFadingAway ");
                m.append(bool4);
                m.append(", bouncerShowing ");
                m.append(int1);
                m.append(",headsUpNotificationShowing ");
                m.append(int2);
                m.append(", scrimsVisibilityNotTransparent ");
                m.append(long1);
                m.append(",backgroundBlurRadius ");
                m.append(long2);
                m.append(", launchingActivityFromNotification ");
                m.append(double1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = shadeWindowLogger.buffer;
        LogMessage obtain = logBuffer.obtain("systemui.shadewindow", logLevel, shadeWindowLogger$logIsExpanded$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = String.valueOf(z);
        logMessageImpl.bool1 = z2;
        logMessageImpl.bool2 = isKeyguardShowingAndNotOccluded;
        logMessageImpl.bool3 = z3;
        logMessageImpl.bool4 = z4;
        logMessageImpl.int1 = z5 ? 1 : 0;
        logMessageImpl.int2 = z6 ? 1 : 0;
        logMessageImpl.long1 = objArr != false ? 1L : 0L;
        logMessageImpl.long2 = z7 ? 1L : 0L;
        logMessageImpl.double1 = z8 ? 1.0d : 0.0d;
        logBuffer.commit(obtain);
        return z;
    }

    public final void notifyStateChangedCallbacks() {
        for (StatusBarWindowCallback statusBarWindowCallback : (List) this.mCallbacks.stream().map(new NotificationShadeWindowControllerImpl$$ExternalSyntheticLambda1()).filter(new NotificationShadeWindowControllerImpl$$ExternalSyntheticLambda2()).collect(Collectors.toList())) {
            NotificationShadeWindowState notificationShadeWindowState = this.mCurrentState;
            statusBarWindowCallback.onStateChanged(notificationShadeWindowState.keyguardShowing, notificationShadeWindowState.keyguardOccluded, notificationShadeWindowState.keyguardGoingAway, notificationShadeWindowState.bouncerShowing, notificationShadeWindowState.dozing, notificationShadeWindowState.shadeOrQsExpanded, notificationShadeWindowState.dreaming, notificationShadeWindowState.communalVisible);
        }
    }

    public void onCommunalVisibleChanged(Boolean bool) {
        boolean booleanValue = bool.booleanValue();
        NotificationShadeWindowState notificationShadeWindowState = this.mCurrentState;
        notificationShadeWindowState.communalVisible = booleanValue;
        apply(notificationShadeWindowState);
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        boolean isKeyguardScreenRotationAllowed = ((KeyguardStateControllerImpl) this.mKeyguardStateController).isKeyguardScreenRotationAllowed();
        if (this.mLastKeyguardRotationAllowed != isKeyguardScreenRotationAllowed) {
            apply(this.mCurrentState);
            this.mLastKeyguardRotationAllowed = isKeyguardScreenRotationAllowed;
        }
    }

    @Override // com.android.systemui.statusbar.RemoteInputController.Callback
    public final void onRemoteInputActive(boolean z) {
        NotificationShadeWindowState notificationShadeWindowState = this.mCurrentState;
        notificationShadeWindowState.remoteInputActive = z;
        apply(notificationShadeWindowState);
    }

    public void onShadeOrQsExpanded(Boolean bool) {
        NotificationShadeWindowState notificationShadeWindowState = this.mCurrentState;
        if (notificationShadeWindowState.shadeOrQsExpanded != bool.booleanValue()) {
            notificationShadeWindowState.shadeOrQsExpanded = bool.booleanValue();
            apply(notificationShadeWindowState);
        }
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onThemeChanged() {
        if (this.mWindowRootView == null) {
            return;
        }
        boolean supportsDarkText = this.mColorExtractor.mNeutralColorsLock.supportsDarkText();
        int systemUiVisibility = this.mWindowRootView.getSystemUiVisibility();
        this.mWindowRootView.setSystemUiVisibility(supportsDarkText ? systemUiVisibility | 8208 : systemUiVisibility & (-8209));
    }

    public final void registerCallback(StatusBarWindowCallback statusBarWindowCallback) {
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            if (((WeakReference) this.mCallbacks.get(i)).get() == statusBarWindowCallback) {
                return;
            }
        }
        this.mCallbacks.add(new WeakReference(statusBarWindowCallback));
    }

    public final void setForcePluginOpen(Object obj, boolean z) {
        NotificationShadeWindowState notificationShadeWindowState = this.mCurrentState;
        if (z) {
            notificationShadeWindowState.forceOpenTokens.add(obj);
        } else {
            notificationShadeWindowState.forceOpenTokens.remove(obj);
        }
        boolean z2 = notificationShadeWindowState.forcePluginOpen;
        notificationShadeWindowState.forcePluginOpen = !notificationShadeWindowState.forceOpenTokens.isEmpty();
        if (z2 != notificationShadeWindowState.forcePluginOpen) {
            apply(notificationShadeWindowState);
            StatusBarTouchableRegionManager$$ExternalSyntheticLambda1 statusBarTouchableRegionManager$$ExternalSyntheticLambda1 = this.mForcePluginOpenListener;
            if (statusBarTouchableRegionManager$$ExternalSyntheticLambda1 != null) {
                boolean z3 = notificationShadeWindowState.forcePluginOpen;
                statusBarTouchableRegionManager$$ExternalSyntheticLambda1.f$0.updateTouchableRegion();
            }
        }
    }

    public final void setNotificationShadeFocusable(boolean z) {
        ShadeWindowLogger shadeWindowLogger = this.mLogger;
        shadeWindowLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        ShadeWindowLogger$logShadeFocusable$2 shadeWindowLogger$logShadeFocusable$2 = new Function1() { // from class: com.android.systemui.shade.ShadeWindowLogger$logShadeFocusable$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Updating shade, should be focusable : ", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = shadeWindowLogger.buffer;
        LogMessage obtain = logBuffer.obtain("systemui.shadewindow", logLevel, shadeWindowLogger$logShadeFocusable$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
        NotificationShadeWindowState notificationShadeWindowState = this.mCurrentState;
        notificationShadeWindowState.notificationShadeFocusable = z;
        apply(notificationShadeWindowState);
    }

    public final void setPanelVisible(boolean z) {
        NotificationShadeWindowState notificationShadeWindowState = this.mCurrentState;
        if (notificationShadeWindowState.panelVisible == z && notificationShadeWindowState.notificationShadeFocusable == z) {
            return;
        }
        ShadeWindowLogger shadeWindowLogger = this.mLogger;
        shadeWindowLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        ShadeWindowLogger$logShadeVisibleAndFocusable$2 shadeWindowLogger$logShadeVisibleAndFocusable$2 = new Function1() { // from class: com.android.systemui.shade.ShadeWindowLogger$logShadeVisibleAndFocusable$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Updating shade, should be visible and focusable: ", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = shadeWindowLogger.buffer;
        LogMessage obtain = logBuffer.obtain("systemui.shadewindow", logLevel, shadeWindowLogger$logShadeVisibleAndFocusable$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
        notificationShadeWindowState.panelVisible = z;
        notificationShadeWindowState.notificationShadeFocusable = z;
        apply(notificationShadeWindowState);
    }

    public final void setRequestTopUi(String str, boolean z) {
        NotificationShadeWindowState notificationShadeWindowState = this.mCurrentState;
        if (z) {
            notificationShadeWindowState.componentsForcingTopUi.add(str);
        } else {
            notificationShadeWindowState.componentsForcingTopUi.remove(str);
        }
        apply(notificationShadeWindowState);
    }
}
