package com.google.android.systemui.assist.uihints;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.CompositionSamplingListener;
import androidx.profileinstaller.ProfileInstallReceiver$$ExternalSyntheticLambda0;
import com.google.android.systemui.assist.uihints.LightnessProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LightnessProvider {
    public NgaUiController$$ExternalSyntheticLambda2 mListener;
    public boolean mIsMonitoringColor = false;
    public boolean mMuted = false;
    public final Handler mUiHandler = new Handler(Looper.getMainLooper());
    public final AnonymousClass1 mColorMonitor = new AnonymousClass1(new ProfileInstallReceiver$$ExternalSyntheticLambda0());

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.assist.uihints.LightnessProvider$1, reason: invalid class name */
    public final class AnonymousClass1 extends CompositionSamplingListener {
        public AnonymousClass1(ProfileInstallReceiver$$ExternalSyntheticLambda0 profileInstallReceiver$$ExternalSyntheticLambda0) {
            super(profileInstallReceiver$$ExternalSyntheticLambda0);
        }

        public final void onSampleCollected(final float f) {
            LightnessProvider.this.mUiHandler.post(new Runnable() { // from class: com.google.android.systemui.assist.uihints.LightnessProvider$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LightnessProvider.AnonymousClass1 anonymousClass1 = LightnessProvider.AnonymousClass1.this;
                    float f2 = f;
                    LightnessProvider lightnessProvider = LightnessProvider.this;
                    NgaUiController$$ExternalSyntheticLambda2 ngaUiController$$ExternalSyntheticLambda2 = lightnessProvider.mListener;
                    if (ngaUiController$$ExternalSyntheticLambda2 == null || lightnessProvider.mMuted) {
                        return;
                    }
                    NgaUiController ngaUiController = ngaUiController$$ExternalSyntheticLambda2.f$0;
                    if (ngaUiController.mColorMonitoringStart > 0) {
                        long elapsedRealtime = SystemClock.elapsedRealtime() - ngaUiController.mColorMonitoringStart;
                        if (NgaUiController.VERBOSE) {
                            Log.d("NgaUiController", "Got lightness update (" + f2 + ") after " + elapsedRealtime + " ms");
                        }
                        ngaUiController.mColorMonitoringStart = 0L;
                    }
                    ScrimController scrimController = ngaUiController.mScrimController;
                    scrimController.mHaveAccurateLightness = true;
                    scrimController.mMedianLightness = f2;
                    scrimController.refresh();
                    boolean z = f2 <= 0.4f;
                    if (ngaUiController.mHasDarkBackground != z) {
                        ngaUiController.mHasDarkBackground = z;
                        PromptView promptView = ngaUiController.mPromptView;
                        if (z != promptView.mHasDarkBackground) {
                            promptView.setTextColor(z ? promptView.mTextColorDark : promptView.mTextColorLight);
                            promptView.mHasDarkBackground = z;
                        }
                    }
                    ngaUiController.refresh$1();
                }
            });
        }
    }
}
