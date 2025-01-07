package com.android.systemui.doze;

import android.hardware.display.AmbientDisplayConfiguration;
import android.text.TextUtils;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.util.Assert;
import com.android.systemui.util.CopyOnLoopListenerSet;
import dagger.Lazy;
import java.io.PrintWriter;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeSuppressor implements DozeMachine.Part {
    public final Lazy mBiometricUnlockControllerLazy;
    public final AmbientDisplayConfiguration mConfig;
    public final DozeServiceHost mDozeHost;
    public final DozeLog mDozeLog;
    public DozeMachine mMachine;
    public final UserTracker mUserTracker;
    public boolean mIsCarModeEnabled = false;
    public final AnonymousClass1 mHostCallback = new DozeHost$Callback() { // from class: com.android.systemui.doze.DozeSuppressor.1
        @Override // com.android.systemui.doze.DozeHost$Callback
        public final void onAlwaysOnSuppressedChanged(boolean z) {
            DozeSuppressor dozeSuppressor = DozeSuppressor.this;
            DozeMachine.State state = (!dozeSuppressor.mConfig.alwaysOnEnabled(((UserTrackerImpl) dozeSuppressor.mUserTracker).getUserId()) || z) ? DozeMachine.State.DOZE : DozeMachine.State.DOZE_AOD;
            DozeLogger dozeLogger = dozeSuppressor.mDozeLog.mLogger;
            dozeLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            DozeLogger$logAlwaysOnSuppressedChange$2 dozeLogger$logAlwaysOnSuppressedChange$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logAlwaysOnSuppressedChange$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "Always on (AOD) suppressed changed, suppressed=" + logMessage.getBool1() + " nextState=" + logMessage.getStr1();
                }
            };
            LogBuffer logBuffer = dozeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logAlwaysOnSuppressedChange$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.bool1 = z;
            logMessageImpl.str1 = state.name();
            logBuffer.commit(obtain);
            dozeSuppressor.mMachine.requestState(state);
        }

        @Override // com.android.systemui.doze.DozeHost$Callback
        public final void onPowerSaveChanged() {
            DozeSuppressor dozeSuppressor = DozeSuppressor.this;
            boolean z = ((BatteryControllerImpl) dozeSuppressor.mDozeHost.mBatteryController).mAodPowerSave;
            DozeMachine.State state = DozeMachine.State.DOZE;
            if (!z) {
                state = (dozeSuppressor.mMachine.getState() == state && dozeSuppressor.mConfig.alwaysOnEnabled(((UserTrackerImpl) dozeSuppressor.mUserTracker).getUserId())) ? DozeMachine.State.DOZE_AOD : null;
            }
            if (state != null) {
                boolean z2 = ((BatteryControllerImpl) dozeSuppressor.mDozeHost.mBatteryController).mAodPowerSave;
                DozeLogger dozeLogger = dozeSuppressor.mDozeLog.mLogger;
                dozeLogger.getClass();
                LogLevel logLevel = LogLevel.INFO;
                DozeLogger$logPowerSaveChanged$2 dozeLogger$logPowerSaveChanged$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logPowerSaveChanged$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return "Power save active=" + logMessage.getBool1() + " nextState=" + logMessage.getStr1();
                    }
                };
                LogBuffer logBuffer = dozeLogger.buffer;
                LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logPowerSaveChanged$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.bool1 = z2;
                logMessageImpl.str1 = state.name();
                logBuffer.commit(obtain);
                dozeSuppressor.mMachine.requestState(state);
            }
        }
    };

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.doze.DozeSuppressor$1] */
    public DozeSuppressor(DozeServiceHost dozeServiceHost, AmbientDisplayConfiguration ambientDisplayConfiguration, DozeLog dozeLog, Lazy lazy, UserTracker userTracker) {
        this.mDozeHost = dozeServiceHost;
        this.mConfig = ambientDisplayConfiguration;
        this.mDozeLog = dozeLog;
        this.mBiometricUnlockControllerLazy = lazy;
        this.mUserTracker = userTracker;
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void destroy() {
        AnonymousClass1 anonymousClass1 = this.mHostCallback;
        DozeServiceHost dozeServiceHost = this.mDozeHost;
        dozeServiceHost.getClass();
        Assert.isMainThread();
        dozeServiceHost.mCallbacks.remove(anonymousClass1);
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void dump(PrintWriter printWriter) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder(" isCarModeEnabled="), this.mIsCarModeEnabled, printWriter, " hasPendingAuth=");
        m.append(((BiometricUnlockController) this.mBiometricUnlockControllerLazy.get()).hasPendingAuthentication());
        printWriter.println(m.toString());
        StringBuilder sb = new StringBuilder(" isProvisioned=");
        DozeServiceHost dozeServiceHost = this.mDozeHost;
        DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) dozeServiceHost.mDeviceProvisionedController;
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(sb, deviceProvisionedControllerImpl.deviceProvisioned.get() && deviceProvisionedControllerImpl.isCurrentUserSetup(), printWriter, " isAlwaysOnSuppressed="), dozeServiceHost.mAlwaysOnSuppressed, printWriter, " aodPowerSaveActive="), ((BatteryControllerImpl) dozeServiceHost.mBatteryController).mAodPowerSave, printWriter);
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void onUiModeTypeChanged(int i) {
        boolean z = i == 3;
        if (this.mIsCarModeEnabled == z) {
            return;
        }
        this.mIsCarModeEnabled = z;
        DozeMachine.State state = this.mMachine.mState;
        if (state == DozeMachine.State.UNINITIALIZED || state == DozeMachine.State.FINISH) {
            return;
        }
        DozeLog dozeLog = this.mDozeLog;
        if (z) {
            DozeLogger dozeLogger = dozeLog.mLogger;
            dozeLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            DozeLogger$logCarModeStarted$2 dozeLogger$logCarModeStarted$2 = DozeLogger$logCarModeStarted$2.INSTANCE;
            LogBuffer logBuffer = dozeLogger.buffer;
            logBuffer.commit(logBuffer.obtain("DozeLog", logLevel, dozeLogger$logCarModeStarted$2, null));
            this.mMachine.requestState(DozeMachine.State.DOZE_SUSPEND_TRIGGERS);
            return;
        }
        DozeLogger dozeLogger2 = dozeLog.mLogger;
        dozeLogger2.getClass();
        LogLevel logLevel2 = LogLevel.INFO;
        DozeLogger$logCarModeEnded$2 dozeLogger$logCarModeEnded$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logCarModeEnded$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Doze car mode ended";
            }
        };
        LogBuffer logBuffer2 = dozeLogger2.buffer;
        logBuffer2.commit(logBuffer2.obtain("DozeLog", logLevel2, dozeLogger$logCarModeEnded$2, null));
        this.mMachine.requestState(this.mConfig.alwaysOnEnabled(((UserTrackerImpl) this.mUserTracker).getUserId()) ? DozeMachine.State.DOZE_AOD : DozeMachine.State.DOZE);
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void setDozeMachine(DozeMachine dozeMachine) {
        this.mMachine = dozeMachine;
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void transitionTo(DozeMachine.State state, DozeMachine.State state2) {
        int ordinal = state2.ordinal();
        if (ordinal != 1) {
            if (ordinal != 9) {
                return;
            }
            destroy();
            return;
        }
        AnonymousClass1 anonymousClass1 = this.mHostCallback;
        DozeServiceHost dozeServiceHost = this.mDozeHost;
        dozeServiceHost.getClass();
        Assert.isMainThread();
        CopyOnLoopListenerSet copyOnLoopListenerSet = dozeServiceHost.mCallbacks;
        if (!copyOnLoopListenerSet.listeners.contains(anonymousClass1)) {
            copyOnLoopListenerSet.listeners.add(anonymousClass1);
        }
        DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) dozeServiceHost.mDeviceProvisionedController;
        String str = !(deviceProvisionedControllerImpl.deviceProvisioned.get() && deviceProvisionedControllerImpl.isCurrentUserSetup()) ? "device_unprovisioned" : ((BiometricUnlockController) this.mBiometricUnlockControllerLazy.get()).hasPendingAuthentication() ? "has_pending_auth" : null;
        boolean isEmpty = TextUtils.isEmpty(str);
        DozeLog dozeLog = this.mDozeLog;
        if (!isEmpty) {
            DozeLogger dozeLogger = dozeLog.mLogger;
            dozeLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            DozeLogger$logImmediatelyEndDoze$2 dozeLogger$logImmediatelyEndDoze$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logImmediatelyEndDoze$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Doze immediately ended due to ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = dozeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logImmediatelyEndDoze$2, null);
            ((LogMessageImpl) obtain).str1 = str;
            logBuffer.commit(obtain);
            this.mMachine.requestState(DozeMachine.State.FINISH);
        }
        if (this.mIsCarModeEnabled) {
            DozeLogger dozeLogger2 = dozeLog.mLogger;
            dozeLogger2.getClass();
            LogLevel logLevel2 = LogLevel.INFO;
            DozeLogger$logCarModeStarted$2 dozeLogger$logCarModeStarted$2 = DozeLogger$logCarModeStarted$2.INSTANCE;
            LogBuffer logBuffer2 = dozeLogger2.buffer;
            logBuffer2.commit(logBuffer2.obtain("DozeLog", logLevel2, dozeLogger$logCarModeStarted$2, null));
            this.mMachine.requestState(DozeMachine.State.DOZE_SUSPEND_TRIGGERS);
        }
    }
}
