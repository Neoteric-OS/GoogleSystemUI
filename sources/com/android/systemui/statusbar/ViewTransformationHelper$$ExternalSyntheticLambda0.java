package com.android.systemui.statusbar;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ViewTransformationHelper$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ViewTransformationHelper f$0;
    public final /* synthetic */ TransformableView f$1;

    public /* synthetic */ ViewTransformationHelper$$ExternalSyntheticLambda0(ViewTransformationHelper viewTransformationHelper, TransformableView transformableView, int i) {
        this.$r8$classId = i;
        this.f$0 = viewTransformationHelper;
        this.f$1 = transformableView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                ViewTransformationHelper viewTransformationHelper = this.f$0;
                TransformableView transformableView = this.f$1;
                viewTransformationHelper.getClass();
                viewTransformationHelper.transformTo(valueAnimator.getAnimatedFraction(), transformableView);
                break;
            default:
                ViewTransformationHelper viewTransformationHelper2 = this.f$0;
                TransformableView transformableView2 = this.f$1;
                viewTransformationHelper2.getClass();
                viewTransformationHelper2.transformFrom(valueAnimator.getAnimatedFraction(), transformableView2);
                break;
        }
    }
}
