package com.android.systemui.statusbar.phone;

import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.util.AlarmTimeout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ScrimController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ ScrimController$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ScrimController scrimController = (ScrimController) obj;
                ScrimController.Callback callback = scrimController.mCallback;
                if (callback != null) {
                    callback.onDisplayBlanked();
                    scrimController.mScreenBlankingCallbackCalled = true;
                }
                scrimController.mBlankingTransitionRunnable = new ScrimController$$ExternalSyntheticLambda0(1, scrimController);
                int i2 = scrimController.mScreenOn ? 32 : 500;
                if (ScrimController.DEBUG) {
                    ExifInterface$$ExternalSyntheticOutline0.m("Fading out scrims with delay: ", "ScrimController", i2);
                }
                scrimController.mHandler.postDelayed(scrimController.mBlankingTransitionRunnable, i2);
                break;
            case 1:
                ScrimController scrimController2 = (ScrimController) obj;
                scrimController2.mBlankingTransitionRunnable = null;
                scrimController2.mPendingFrameCallback = null;
                scrimController2.mBlankScreen = false;
                scrimController2.updateScrims();
                break;
            case 2:
                ScrimController scrimController3 = (ScrimController) obj;
                AlarmTimeout alarmTimeout = scrimController3.mTimeTicker;
                DozeParameters dozeParameters = scrimController3.mDozeParameters;
                alarmTimeout.schedule(dozeParameters.mControlScreenOffAnimation ? 2500L : dozeParameters.mAlwaysOnPolicy.wallpaperVisibilityDuration, 1);
                break;
            case 3:
                ScrimController scrimController4 = (ScrimController) obj;
                AlarmTimeout alarmTimeout2 = scrimController4.mTimeTicker;
                DozeParameters dozeParameters2 = scrimController4.mDozeParameters;
                alarmTimeout2.schedule(dozeParameters2.mControlScreenOffAnimation ? 2500L : dozeParameters2.mAlwaysOnPolicy.wallpaperVisibilityDuration, 1);
                break;
            default:
                ((AlarmTimeout) obj).cancel();
                break;
        }
    }
}
