package com.android.systemui.statusbar.phone;

import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.view.View;
import com.android.keyguard.ActiveUnlockConfig;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DejankUtils;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.doze.DozeHost$Callback;
import com.android.systemui.doze.DozeLog;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.doze.DozeScreenState$$ExternalSyntheticLambda1;
import com.android.systemui.doze.DozeUi;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.DozeInteractor;
import com.android.systemui.shade.NotificationShadeWindowViewController;
import com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.util.Assert;
import com.android.systemui.util.CopyOnLoopListenerSet;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.dreamliner.DockObserver;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.ArrayIterator;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DozeServiceHost {
    public boolean mAlwaysOnSuppressed;
    public View mAmbientIndicationContainer;
    public boolean mAnimateWakeup;
    public final Lazy mAssistManagerLazy;
    public final AuthController mAuthController;
    public final BatteryController mBatteryController;
    public final Lazy mBiometricUnlockControllerLazy;
    public final CopyOnLoopListenerSet mCallbacks = new CopyOnLoopListenerSet();
    public CentralSurfacesImpl mCentralSurfaces;
    public final DozeServiceHost$$ExternalSyntheticLambda0 mDefaultHasPendingScreenOffCallbackChangeListener;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public final DozeInteractor mDozeInteractor;
    public final DozeLog mDozeLog;
    public final DozeScrimController mDozeScrimController;
    public boolean mDozingRequested;
    public HasPendingScreenOffCallbackChangeListener mHasPendingScreenOffCallbackChangeListener;
    public final HeadsUpManager mHeadsUpManager;
    public boolean mIgnoreTouchWhilePulsing;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public NotificationShadeWindowViewController mNotificationShadeWindowViewController;
    public final NotificationWakeUpCoordinator mNotificationWakeUpCoordinator;
    public final AnonymousClass2 mOnHeadsUpChangedListener;
    public DozeScreenState$$ExternalSyntheticLambda1 mPendingScreenOffCallback;
    public final PowerManager mPowerManager;
    public final PulseExpansionHandler mPulseExpansionHandler;
    public boolean mPulsePending;
    public boolean mPulsing;
    public final ScrimController mScrimController;
    public final ShadeLockscreenInteractor mShadeLockscreenInteractor;
    public StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final SysuiStatusBarStateController mStatusBarStateController;
    boolean mWakeLockScreenPerformsAuth;
    public final WakefulnessLifecycle mWakefulnessLifecycle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.DozeServiceHost$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public final /* synthetic */ DozeUi.AnonymousClass2 val$callback;
        public final /* synthetic */ boolean val$passiveAuthInterrupt;

        public AnonymousClass1(DozeUi.AnonymousClass2 anonymousClass2, boolean z) {
            this.val$callback = anonymousClass2;
            this.val$passiveAuthInterrupt = z;
        }

        public final void onPulseFinished() {
            DozeServiceHost dozeServiceHost = DozeServiceHost.this;
            dozeServiceHost.mPulsing = false;
            DozeUi.this.mMachine.requestState(DozeMachine.State.DOZE_PULSE_DONE);
            dozeServiceHost.mCentralSurfaces.updateNotificationPanelTouchState();
            dozeServiceHost.mScrimController.setWakeLockScreenSensorActive(false);
            setPulsing(false);
        }

        public final void setPulsing(boolean z) {
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = DozeServiceHost.this.mStatusBarKeyguardViewManager;
            if (statusBarKeyguardViewManager.mPulsing != z) {
                statusBarKeyguardViewManager.mPulsing = z;
                statusBarKeyguardViewManager.updateStates();
            }
            DozeServiceHost.this.mShadeLockscreenInteractor.setPulsing(z);
            StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) DozeServiceHost.this.mStatusBarStateController;
            if (statusBarStateControllerImpl.mPulsing != z) {
                statusBarStateControllerImpl.mPulsing = z;
                synchronized (statusBarStateControllerImpl.mListeners) {
                    try {
                        Iterator it = new ArrayList(statusBarStateControllerImpl.mListeners).iterator();
                        while (it.hasNext()) {
                            ((SysuiStatusBarStateController.RankedListener) it.next()).mListener.onPulsingChanged(z);
                        }
                    } finally {
                    }
                }
            }
            DozeServiceHost dozeServiceHost = DozeServiceHost.this;
            dozeServiceHost.mIgnoreTouchWhilePulsing = false;
            KeyguardUpdateMonitor keyguardUpdateMonitor = dozeServiceHost.mKeyguardUpdateMonitor;
            if (keyguardUpdateMonitor != null && this.val$passiveAuthInterrupt) {
                keyguardUpdateMonitor.mLogger.logAuthInterruptDetected(z);
                if (keyguardUpdateMonitor.mAuthInterruptActive != z) {
                    keyguardUpdateMonitor.mAuthInterruptActive = z;
                    keyguardUpdateMonitor.requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.WAKE, "onReach");
                }
            }
            DozeServiceHost.this.mCentralSurfaces.updateScrimController();
            DozeServiceHost dozeServiceHost2 = DozeServiceHost.this;
            dozeServiceHost2.mPulseExpansionHandler.mPulsing = z;
            NotificationWakeUpCoordinator notificationWakeUpCoordinator = dozeServiceHost2.mNotificationWakeUpCoordinator;
            notificationWakeUpCoordinator.pulsing = z;
            if (z) {
                notificationWakeUpCoordinator.updateNotificationVisibility(notificationWakeUpCoordinator.shouldAnimateVisibility(), false);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface HasPendingScreenOffCallbackChangeListener {
        void onHasPendingScreenOffCallbackChanged(boolean z);
    }

    public DozeServiceHost(DozeLog dozeLog, PowerManager powerManager, WakefulnessLifecycle wakefulnessLifecycle, SysuiStatusBarStateController sysuiStatusBarStateController, DeviceProvisionedController deviceProvisionedController, HeadsUpManager headsUpManager, BatteryController batteryController, ScrimController scrimController, Lazy lazy, Lazy lazy2, DozeScrimController dozeScrimController, KeyguardUpdateMonitor keyguardUpdateMonitor, PulseExpansionHandler pulseExpansionHandler, NotificationShadeWindowController notificationShadeWindowController, NotificationWakeUpCoordinator notificationWakeUpCoordinator, AuthController authController, ShadeLockscreenInteractor shadeLockscreenInteractor, DozeInteractor dozeInteractor) {
        DozeServiceHost$$ExternalSyntheticLambda0 dozeServiceHost$$ExternalSyntheticLambda0 = new DozeServiceHost$$ExternalSyntheticLambda0();
        this.mDefaultHasPendingScreenOffCallbackChangeListener = dozeServiceHost$$ExternalSyntheticLambda0;
        this.mHasPendingScreenOffCallbackChangeListener = dozeServiceHost$$ExternalSyntheticLambda0;
        this.mWakeLockScreenPerformsAuth = SystemProperties.getBoolean("persist.sysui.wake_performs_auth", true);
        OnHeadsUpChangedListener onHeadsUpChangedListener = new OnHeadsUpChangedListener() { // from class: com.android.systemui.statusbar.phone.DozeServiceHost.2
            @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
            public final void onHeadsUpStateChanged(NotificationEntry notificationEntry, boolean z) {
                DozeServiceHost dozeServiceHost = DozeServiceHost.this;
                boolean z2 = ((StatusBarStateControllerImpl) dozeServiceHost.mStatusBarStateController).mIsDozing;
                DozeScrimController dozeScrimController2 = dozeServiceHost.mDozeScrimController;
                if (z2 && z) {
                    notificationEntry.getClass();
                    DozeServiceHost$$ExternalSyntheticLambda1 dozeServiceHost$$ExternalSyntheticLambda1 = new DozeServiceHost$$ExternalSyntheticLambda1(dozeServiceHost, notificationEntry);
                    Assert.isMainThread();
                    Iterator it = dozeServiceHost.mCallbacks.iterator();
                    while (true) {
                        ArrayIterator arrayIterator = (ArrayIterator) it;
                        if (!arrayIterator.hasNext()) {
                            break;
                        } else {
                            ((DozeHost$Callback) arrayIterator.next()).onNotificationAlerted(dozeServiceHost$$ExternalSyntheticLambda1);
                        }
                    }
                    if (dozeServiceHost.mPulsing) {
                        Handler handler = dozeScrimController2.mHandler;
                        handler.removeCallbacks(dozeScrimController2.mPulseOut);
                        handler.removeCallbacks(dozeScrimController2.mPulseOutExtended);
                    }
                }
                if (z || ((BaseHeadsUpManager) dozeServiceHost.mHeadsUpManager).hasNotifications$1()) {
                    return;
                }
                dozeServiceHost.mPulsePending = false;
                dozeScrimController2.mPulseOut.run();
            }
        };
        this.mDozeLog = dozeLog;
        this.mPowerManager = powerManager;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mHeadsUpManager = headsUpManager;
        this.mBatteryController = batteryController;
        this.mScrimController = scrimController;
        this.mBiometricUnlockControllerLazy = lazy;
        this.mAssistManagerLazy = lazy2;
        this.mDozeScrimController = dozeScrimController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mPulseExpansionHandler = pulseExpansionHandler;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mNotificationWakeUpCoordinator = notificationWakeUpCoordinator;
        this.mAuthController = authController;
        this.mShadeLockscreenInteractor = shadeLockscreenInteractor;
        ((BaseHeadsUpManager) headsUpManager).addListener(onHeadsUpChangedListener);
        this.mDozeInteractor = dozeInteractor;
    }

    public final void extendPulse(int i) {
        if (i == 8) {
            this.mScrimController.setWakeLockScreenSensorActive(true);
        }
        DozeScrimController dozeScrimController = this.mDozeScrimController;
        if (dozeScrimController.mPulseCallback != null) {
            HeadsUpManager headsUpManager = this.mHeadsUpManager;
            if (((BaseHeadsUpManager) headsUpManager).hasNotifications$1()) {
                HeadsUpManagerPhone.HeadsUpEntryPhone headsUpEntryPhone = (HeadsUpManagerPhone.HeadsUpEntryPhone) ((HeadsUpManagerPhone) headsUpManager).getTopHeadsUpEntry();
                if (headsUpEntryPhone == null || headsUpEntryPhone.extended) {
                    return;
                }
                headsUpEntryPhone.extended = true;
                headsUpEntryPhone.updateEntry("extendPulse()", false);
                return;
            }
        }
        dozeScrimController.mHandler.removeCallbacks(dozeScrimController.mPulseOut);
    }

    public final void pulseWhileDozing(DozeUi.AnonymousClass2 anonymousClass2, int i) {
        if (i == 5) {
            this.mPowerManager.wakeUp(SystemClock.uptimeMillis(), 4, "com.android.systemui:LONG_PRESS");
            ((AssistManagerGoogle) this.mAssistManagerLazy.get()).startAssist(new Bundle());
            return;
        }
        if (i == 8) {
            this.mScrimController.setWakeLockScreenSensorActive(true);
        }
        boolean z = i == 8 && this.mWakeLockScreenPerformsAuth;
        this.mPulsing = true;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(anonymousClass2, z);
        DozeScrimController dozeScrimController = this.mDozeScrimController;
        if (dozeScrimController.mDozing && dozeScrimController.mPulseCallback == null) {
            dozeScrimController.mPulseCallback = anonymousClass1;
            dozeScrimController.mPulseReason = i;
        } else {
            anonymousClass1.onPulseFinished();
            boolean z2 = dozeScrimController.mDozing;
            DozeLog dozeLog = dozeScrimController.mDozeLog;
            if (z2) {
                dozeLog.tracePulseDropped("pulse - already has pulse callback mPulseCallback=" + dozeScrimController.mPulseCallback);
            } else {
                dozeLog.tracePulseDropped("pulse - device isn't dozing");
            }
        }
        this.mCentralSurfaces.updateScrimController();
    }

    public final void setAodDimmingScrim(float f) {
        this.mDozeLog.traceSetAodDimmingScrim(f);
        ScrimController scrimController = this.mScrimController;
        float f2 = scrimController.mInFrontAlpha;
        ScrimState scrimState = ScrimState.PULSING;
        ScrimState scrimState2 = ScrimState.AOD;
        if (f2 != f && ((scrimController.mState == scrimState2 && (scrimController.mDozeParameters.getAlwaysOn() || ((DockObserver) scrimController.mDockManager).isDocked())) || scrimController.mState == scrimState)) {
            scrimController.mInFrontAlpha = f;
            scrimController.updateScrims();
        }
        scrimState2.mAodFrontScrimAlpha = f;
        scrimState.mAodFrontScrimAlpha = f;
    }

    public final String toString() {
        return "PSB.DozeServiceHost[mCallbacks=" + this.mCallbacks.listeners.size() + "]";
    }

    public final void updateDozing() {
        Assert.isMainThread();
        boolean z = ((BiometricUnlockController) this.mBiometricUnlockControllerLazy.get()).mMode != 1 ? this.mDozingRequested && ((StatusBarStateControllerImpl) this.mStatusBarStateController).mState == 1 : false;
        Iterator it = this.mCallbacks.iterator();
        while (true) {
            ArrayIterator arrayIterator = (ArrayIterator) it;
            if (!arrayIterator.hasNext()) {
                break;
            } else {
                ((DozeHost$Callback) arrayIterator.next()).getClass();
            }
        }
        KeyguardRepositoryImpl keyguardRepositoryImpl = this.mDozeInteractor.keyguardRepository;
        Boolean valueOf = Boolean.valueOf(z);
        StateFlowImpl stateFlowImpl = keyguardRepositoryImpl._isDozing;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) this.mStatusBarStateController;
        if (statusBarStateControllerImpl.mIsDozing == z) {
            return;
        }
        statusBarStateControllerImpl.mIsDozing = z;
        synchronized (statusBarStateControllerImpl.mListeners) {
            try {
                DejankUtils.startDetectingBlockingIpcs("StatusBarStateControllerImpl#setIsDozing");
                Iterator it2 = new ArrayList(statusBarStateControllerImpl.mListeners).iterator();
                while (it2.hasNext()) {
                    ((SysuiStatusBarStateController.RankedListener) it2.next()).mListener.onDozingChanged(z);
                }
                DejankUtils.stopDetectingBlockingIpcs("StatusBarStateControllerImpl#setIsDozing");
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
