package com.android.systemui.statusbar;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockscreenShadeTransitionController$setPulseHeight$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LockscreenShadeTransitionController this$0;

    public /* synthetic */ LockscreenShadeTransitionController$setPulseHeight$1(LockscreenShadeTransitionController lockscreenShadeTransitionController, int i) {
        this.$r8$classId = i;
        this.this$0 = lockscreenShadeTransitionController;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.setPulseHeight(((Float) valueAnimator.getAnimatedValue()).floatValue(), false);
                break;
            default:
                this.this$0.setDragDownAmount$frameworks__base__packages__SystemUI__android_common__SystemUI_core(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
        }
    }
}
