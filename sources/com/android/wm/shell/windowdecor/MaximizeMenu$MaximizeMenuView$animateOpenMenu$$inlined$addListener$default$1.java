package com.android.wm.shell.windowdecor;

import android.animation.Animator;
import com.android.wm.shell.windowdecor.MaximizeMenu;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaximizeMenu$MaximizeMenuView$animateOpenMenu$$inlined$addListener$default$1 implements Animator.AnimatorListener {
    public final /* synthetic */ Function0 $onEnd$inlined;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MaximizeMenu.MaximizeMenuView this$0;

    public /* synthetic */ MaximizeMenu$MaximizeMenuView$animateOpenMenu$$inlined$addListener$default$1(MaximizeMenu.MaximizeMenuView maximizeMenuView, Function0 function0, int i) {
        this.$r8$classId = i;
        this.this$0 = maximizeMenuView;
        this.$onEnd$inlined = function0;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.maximizeButton.setLayerType(1, null);
                this.this$0.maximizeText.setLayerType(1, null);
                this.$onEnd$inlined.invoke();
                break;
            default:
                this.this$0.maximizeButton.setLayerType(1, null);
                this.this$0.maximizeText.setLayerType(1, null);
                Function0 function0 = this.$onEnd$inlined;
                if (function0 != null) {
                    function0.invoke();
                    break;
                }
                break;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i = this.$r8$classId;
    }

    private final void onAnimationCancel$com$android$wm$shell$windowdecor$MaximizeMenu$MaximizeMenuView$animateCloseMenu$$inlined$addListener$default$1(Animator animator) {
    }

    private final void onAnimationCancel$com$android$wm$shell$windowdecor$MaximizeMenu$MaximizeMenuView$animateOpenMenu$$inlined$addListener$default$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$wm$shell$windowdecor$MaximizeMenu$MaximizeMenuView$animateCloseMenu$$inlined$addListener$default$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$wm$shell$windowdecor$MaximizeMenu$MaximizeMenuView$animateOpenMenu$$inlined$addListener$default$1(Animator animator) {
    }

    private final void onAnimationStart$com$android$wm$shell$windowdecor$MaximizeMenu$MaximizeMenuView$animateCloseMenu$$inlined$addListener$default$1(Animator animator) {
    }

    private final void onAnimationStart$com$android$wm$shell$windowdecor$MaximizeMenu$MaximizeMenuView$animateOpenMenu$$inlined$addListener$default$1(Animator animator) {
    }
}
