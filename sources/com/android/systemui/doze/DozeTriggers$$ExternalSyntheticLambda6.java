package com.android.systemui.doze;

import android.util.Log;
import com.android.systemui.biometrics.UdfpsController;
import com.android.systemui.biometrics.UdfpsController$$ExternalSyntheticLambda2;
import com.android.systemui.biometrics.UdfpsControllerOverlay;
import com.android.systemui.biometrics.ui.view.UdfpsTouchOverlay;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DozeTriggers$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ DozeTriggers f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ float[] f$3;

    public /* synthetic */ DozeTriggers$$ExternalSyntheticLambda6(DozeTriggers dozeTriggers, float f, float f2, float[] fArr) {
        this.f$0 = dozeTriggers;
        this.f$1 = f;
        this.f$2 = f2;
        this.f$3 = fArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UdfpsTouchOverlay udfpsTouchOverlay;
        DozeTriggers dozeTriggers = this.f$0;
        float f = this.f$1;
        float f2 = this.f$2;
        float[] fArr = this.f$3;
        int i = (int) f;
        int i2 = (int) f2;
        float f3 = fArr[3];
        float f4 = fArr[4];
        UdfpsController udfpsController = dozeTriggers.mAuthController.mUdfpsController;
        if (udfpsController == null || udfpsController.mIsAodInterruptActive) {
            return;
        }
        if (udfpsController.mKeyguardUpdateMonitor.isFingerprintDetectionRunning()) {
            UdfpsControllerOverlay udfpsControllerOverlay = udfpsController.mOverlay;
            UdfpsController$$ExternalSyntheticLambda2 udfpsController$$ExternalSyntheticLambda2 = new UdfpsController$$ExternalSyntheticLambda2(udfpsController, udfpsControllerOverlay != null ? udfpsControllerOverlay.requestId : -1L, i, i2, f4, f3);
            udfpsController.mAodInterruptRunnable = udfpsController$$ExternalSyntheticLambda2;
            if (udfpsController.mScreenOn) {
                udfpsController$$ExternalSyntheticLambda2.run();
                udfpsController.mAodInterruptRunnable = null;
                return;
            }
            return;
        }
        if (udfpsController.mFalsingManager.isFalseLongTap(1)) {
            return;
        }
        udfpsController.mKeyguardViewManager.showPrimaryBouncer(true);
        UdfpsControllerOverlay udfpsControllerOverlay2 = udfpsController.mOverlay;
        if (udfpsControllerOverlay2 == null || (udfpsTouchOverlay = udfpsControllerOverlay2.overlayTouchView) == null) {
            Log.e("UdfpsController", "No haptics played. Could not obtain overlay view to performvibration. Either the controller overlay is null or has no view");
        } else {
            udfpsController.mVibrator.getClass();
            udfpsTouchOverlay.performHapticFeedback(0);
        }
    }
}
