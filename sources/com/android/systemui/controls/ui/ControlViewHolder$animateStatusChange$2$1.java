package com.android.systemui.controls.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlViewHolder$animateStatusChange$2$1 extends AnimatorListenerAdapter {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ ControlViewHolder$animateStatusChange$2$1(ControlViewHolder controlViewHolder, int i) {
        this.$r8$classId = i;
        this.this$0 = controlViewHolder;
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                ((ControlViewHolder) this.this$0).status.setAlpha(1.0f);
                ((ControlViewHolder) this.this$0).statusAnimator = null;
                break;
            case 1:
                ((ControlViewHolder) this.this$0).stateAnimator = null;
                break;
            default:
                ((Lambda) this.this$0).invoke();
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ControlViewHolder$animateStatusChange$2$1(Function0 function0) {
        this.$r8$classId = 2;
        this.this$0 = (Lambda) function0;
    }
}
