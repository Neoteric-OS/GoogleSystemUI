package com.android.systemui.biometrics;

import android.animation.ValueAnimator;
import com.android.internal.graphics.ColorUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthRippleView$fadeDwellRipple$1$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AuthRippleView this$0;

    public /* synthetic */ AuthRippleView$fadeDwellRipple$1$1(AuthRippleView authRippleView, int i) {
        this.$r8$classId = i;
        this.this$0 = authRippleView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                DwellRippleShader dwellRippleShader = this.this$0.dwellShader;
                dwellRippleShader.setColor(ColorUtils.setAlphaComponent(dwellRippleShader.color, ((Integer) valueAnimator.getAnimatedValue()).intValue()));
                this.this$0.invalidate();
                break;
            case 1:
                DwellRippleShader dwellRippleShader2 = this.this$0.dwellShader;
                dwellRippleShader2.setColor(ColorUtils.setAlphaComponent(dwellRippleShader2.color, ((Integer) valueAnimator.getAnimatedValue()).intValue()));
                this.this$0.invalidate();
                break;
            case 2:
                long currentPlayTime = valueAnimator.getCurrentPlayTime();
                this.this$0.dwellShader.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                this.this$0.dwellShader.setTime(currentPlayTime);
                this.this$0.invalidate();
                break;
            case 3:
                long currentPlayTime2 = valueAnimator.getCurrentPlayTime();
                this.this$0.dwellShader.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                this.this$0.dwellShader.setTime(currentPlayTime2);
                this.this$0.invalidate();
                break;
            case 4:
                long currentPlayTime3 = valueAnimator.getCurrentPlayTime();
                this.this$0.dwellShader.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                this.this$0.dwellShader.setTime(currentPlayTime3);
                this.this$0.invalidate();
                break;
            default:
                long currentPlayTime4 = valueAnimator.getCurrentPlayTime();
                this.this$0.rippleShader.setRawProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                this.this$0.rippleShader.setFloatUniform("in_time", currentPlayTime4);
                this.this$0.invalidate();
                break;
        }
    }
}
