package com.android.systemui.statusbar.policy;

import androidx.core.animation.Animator;
import androidx.core.animation.ValueAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RemoteInputView$$ExternalSyntheticLambda1 implements Animator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ RemoteInputView f$0;
    public final /* synthetic */ ValueAnimator f$1;

    public /* synthetic */ RemoteInputView$$ExternalSyntheticLambda1(RemoteInputView remoteInputView, ValueAnimator valueAnimator, int i) {
        this.$r8$classId = i;
        this.f$0 = remoteInputView;
        this.f$1 = valueAnimator;
    }

    @Override // androidx.core.animation.Animator.AnimatorUpdateListener
    public final void onAnimationUpdate(Animator animator) {
        ValueAnimator valueAnimator = this.f$1;
        RemoteInputView remoteInputView = this.f$0;
        switch (this.$r8$classId) {
            case 0:
                Object obj = RemoteInputView.VIEW_TAG;
                remoteInputView.getClass();
                remoteInputView.setFocusAnimationScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                Object obj2 = RemoteInputView.VIEW_TAG;
                remoteInputView.setFocusAnimationScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
        }
    }
}
