package com.android.systemui.media.controls.ui.drawable;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LightSourceDrawable$active$1$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LightSourceDrawable this$0;

    public /* synthetic */ LightSourceDrawable$active$1$1(LightSourceDrawable lightSourceDrawable, int i) {
        this.$r8$classId = i;
        this.this$0 = lightSourceDrawable;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        RippleData rippleData;
        RippleData rippleData2;
        RippleData rippleData3;
        switch (this.$r8$classId) {
            case 0:
                rippleData = this.this$0.rippleData;
                rippleData.alpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                this.this$0.invalidateSelf();
                break;
            case 1:
                rippleData2 = this.this$0.rippleData;
                rippleData2.alpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                this.this$0.invalidateSelf();
                break;
            default:
                rippleData3 = this.this$0.rippleData;
                rippleData3.progress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                this.this$0.invalidateSelf();
                break;
        }
    }
}
