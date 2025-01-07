package com.android.systemui.qs.tileimpl;

import android.animation.ValueAnimator;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import com.android.systemui.haptics.qs.QSLongPressEffect;
import com.android.systemui.statusbar.VibratorHelper;
import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileViewImpl$initLongPressEffectCallback$1 {
    public final /* synthetic */ QSTileViewImpl this$0;

    public QSTileViewImpl$initLongPressEffectCallback$1(QSTileViewImpl qSTileViewImpl) {
        this.this$0 = qSTileViewImpl;
    }

    public final void onReverseAnimator(boolean z) {
        QSLongPressEffect qSLongPressEffect;
        QSTileViewImpl qSTileViewImpl = this.this$0;
        ValueAnimator valueAnimator = qSTileViewImpl.longPressEffectAnimator;
        if (valueAnimator != null) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            if (z && (qSLongPressEffect = qSTileViewImpl.longPressEffect) != null) {
                int[] iArr = qSLongPressEffect.durations;
                int i = iArr != null ? iArr[0] : 0;
                float f = animatedFraction * qSLongPressEffect.effectDuration;
                VibrationEffect vibrationEffect = null;
                if (f != 0.0f) {
                    if (i == 0) {
                        Log.d("LongPressHapticBuilder", "Cannot play reverse haptics because LOW_TICK is not supported");
                    } else {
                        int i2 = (int) (f / i);
                        if (i2 != 0) {
                            VibrationEffect.Composition startComposition = VibrationEffect.startComposition();
                            float f2 = 0.08f / i2;
                            for (int i3 = 0; i3 < i2; i3++) {
                                startComposition.addPrimitive(8, Math.max(0.08f - (i3 * f2), 0.0f), 0);
                            }
                            vibrationEffect = startComposition.compose();
                        }
                    }
                }
                VibratorHelper vibratorHelper = qSLongPressEffect.vibratorHelper;
                if (vibratorHelper != null && vibratorHelper.hasVibrator()) {
                    Executor executor = vibratorHelper.mExecutor;
                    final Vibrator vibrator = vibratorHelper.mVibrator;
                    Objects.requireNonNull(vibrator);
                    executor.execute(new Runnable() { // from class: com.android.systemui.statusbar.VibratorHelper$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            vibrator.cancel();
                        }
                    });
                }
                if (vibratorHelper != null && vibrationEffect != null) {
                    vibratorHelper.vibrate(vibrationEffect);
                }
            }
            valueAnimator.reverse();
        }
    }
}
