package com.android.systemui.shade;

import android.graphics.Point;
import android.hardware.display.AmbientDisplayConfiguration;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dock.DockManager;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.domain.interactor.DozeInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.tuner.TunerService;
import com.google.android.systemui.dreamliner.DockObserver;
import java.io.PrintWriter;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PulsingGestureListener extends GestureDetector.SimpleOnGestureListener implements Dumpable {
    public final AmbientDisplayConfiguration ambientDisplayConfiguration;
    public final DockManager dockManager;
    public boolean doubleTapEnabled;
    public final DozeInteractor dozeInteractor;
    public final FalsingManager falsingManager;
    public final PowerInteractor powerInteractor;
    public final ShadeLogger shadeLogger;
    public boolean singleTapEnabled;
    public final StatusBarStateController statusBarStateController;

    public PulsingGestureListener(FalsingManager falsingManager, DockManager dockManager, PowerInteractor powerInteractor, AmbientDisplayConfiguration ambientDisplayConfiguration, StatusBarStateController statusBarStateController, ShadeLogger shadeLogger, DozeInteractor dozeInteractor, final UserTracker userTracker, TunerService tunerService, DumpManager dumpManager) {
        this.falsingManager = falsingManager;
        this.dockManager = dockManager;
        this.powerInteractor = powerInteractor;
        this.ambientDisplayConfiguration = ambientDisplayConfiguration;
        this.statusBarStateController = statusBarStateController;
        this.shadeLogger = shadeLogger;
        this.dozeInteractor = dozeInteractor;
        tunerService.addTunable(new TunerService.Tunable() { // from class: com.android.systemui.shade.PulsingGestureListener$tunable$1
            @Override // com.android.systemui.tuner.TunerService.Tunable
            public final void onTuningChanged(String str, String str2) {
                boolean areEqual = Intrinsics.areEqual(str, "doze_pulse_on_double_tap");
                UserTracker userTracker2 = userTracker;
                PulsingGestureListener pulsingGestureListener = PulsingGestureListener.this;
                if (areEqual) {
                    pulsingGestureListener.doubleTapEnabled = pulsingGestureListener.ambientDisplayConfiguration.doubleTapGestureEnabled(((UserTrackerImpl) userTracker2).getUserId());
                } else if (Intrinsics.areEqual(str, "doze_tap_gesture")) {
                    pulsingGestureListener.singleTapEnabled = pulsingGestureListener.ambientDisplayConfiguration.tapGestureEnabled(((UserTrackerImpl) userTracker2).getUserId());
                }
            }
        }, "doze_pulse_on_double_tap", "doze_tap_gesture");
        dumpManager.registerDumpable(this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("singleTapEnabled=", this.singleTapEnabled, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("doubleTapEnabled=", this.doubleTapEnabled, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("isDocked=", ((DockObserver) this.dockManager).isDocked(), printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("isProxCovered=", this.falsingManager.isProximityNear(), printWriter);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 1 || !this.statusBarStateController.isDozing() || ((!this.doubleTapEnabled && !this.singleTapEnabled) || this.falsingManager.isProximityNear() || this.falsingManager.isFalseDoubleTap())) {
            return false;
        }
        this.powerInteractor.wakeUpIfDozing(15, "PULSING_DOUBLE_TAP");
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean isDocked = ((DockObserver) this.dockManager).isDocked();
        ShadeLogger shadeLogger = this.shadeLogger;
        boolean isDozing = this.statusBarStateController.isDozing();
        boolean z = this.singleTapEnabled;
        shadeLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        ShadeLogger$logSingleTapUp$2 shadeLogger$logSingleTapUp$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logSingleTapUp$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("PulsingGestureListener#onSingleTapUp all of this must true for single tap to be detected: isDozing: ", ", singleTapEnabled: ", ", isNotDocked: ", bool1, bool2);
                m.append(bool3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = shadeLogger.buffer;
        LogMessage obtain = logBuffer.obtain("systemui.shade", logLevel, shadeLogger$logSingleTapUp$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = isDozing;
        logMessageImpl.bool2 = z;
        logMessageImpl.bool3 = !isDocked;
        logBuffer.commit(obtain);
        if (!this.statusBarStateController.isDozing() || !this.singleTapEnabled || isDocked) {
            this.shadeLogger.d("onSingleTapUp event ignored");
            return false;
        }
        boolean isProximityNear = this.falsingManager.isProximityNear();
        boolean isFalseTap = this.falsingManager.isFalseTap(1);
        ShadeLogger shadeLogger2 = this.shadeLogger;
        shadeLogger2.getClass();
        ShadeLogger$logSingleTapUpFalsingState$2 shadeLogger$logSingleTapUpFalsingState$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logSingleTapUpFalsingState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "PulsingGestureListener#onSingleTapUp all of this must true for single tap to be detected: proximityIsNotNear: " + logMessage.getBool1() + ", isNotFalseTap: " + logMessage.getBool2();
            }
        };
        LogBuffer logBuffer2 = shadeLogger2.buffer;
        LogMessage obtain2 = logBuffer2.obtain("systemui.shade", logLevel, shadeLogger$logSingleTapUpFalsingState$2, null);
        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
        logMessageImpl2.bool1 = !isProximityNear;
        logMessageImpl2.bool2 = !isFalseTap;
        logBuffer2.commit(obtain2);
        if (isProximityNear || isFalseTap) {
            return true;
        }
        this.shadeLogger.d("Single tap handled, requesting centralSurfaces.wakeUpIfDozing");
        DozeInteractor dozeInteractor = this.dozeInteractor;
        Point point = new Point((int) x, (int) y);
        StateFlowImpl stateFlowImpl = dozeInteractor.keyguardRepository._lastDozeTapToWakePosition;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, point);
        this.powerInteractor.wakeUpIfDozing(15, "PULSING_SINGLE_TAP");
        return true;
    }
}
