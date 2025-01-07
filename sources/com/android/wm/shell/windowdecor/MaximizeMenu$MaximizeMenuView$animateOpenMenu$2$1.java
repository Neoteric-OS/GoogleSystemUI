package com.android.wm.shell.windowdecor;

import android.animation.ValueAnimator;
import com.android.wm.shell.windowdecor.MaximizeMenu;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaximizeMenu$MaximizeMenuView$animateOpenMenu$2$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ValueAnimator $this_apply;
    public final /* synthetic */ MaximizeMenu.MaximizeMenuView this$0;

    public /* synthetic */ MaximizeMenu$MaximizeMenuView$animateOpenMenu$2$1(ValueAnimator valueAnimator, MaximizeMenu.MaximizeMenuView maximizeMenuView, int i) {
        this.$r8$classId = i;
        this.$this_apply = valueAnimator;
        this.this$0 = maximizeMenuView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                float floatValue = ((Float) this.$this_apply.getAnimatedValue()).floatValue();
                MaximizeMenu.MaximizeMenuView maximizeMenuView = this.this$0;
                int i = maximizeMenuView.menuPadding;
                maximizeMenuView.container.setPadding(i, i - ((int) ((1 - floatValue) * maximizeMenuView.menuHeight)), i, i);
                break;
            case 1:
                float floatValue2 = ((Float) this.$this_apply.getAnimatedValue()).floatValue();
                MaximizeMenu.MaximizeMenuView maximizeMenuView2 = this.this$0;
                int i2 = maximizeMenuView2.menuPadding;
                maximizeMenuView2.container.setPadding(i2, i2 - ((int) ((1 - floatValue2) * maximizeMenuView2.menuHeight)), i2, i2);
                break;
            case 2:
                float floatValue3 = ((Float) this.$this_apply.getAnimatedValue()).floatValue();
                this.this$0.maximizeButton.setScaleY(floatValue3);
                this.this$0.snapButtonsLayout.setScaleY(floatValue3);
                this.this$0.maximizeText.setScaleY(floatValue3);
                this.this$0.snapWindowText.setScaleY(floatValue3);
                break;
            case 3:
                float floatValue4 = ((Float) this.$this_apply.getAnimatedValue()).floatValue();
                this.this$0.maximizeButton.setAlpha(floatValue4);
                this.this$0.snapButtonsLayout.setAlpha(floatValue4);
                this.this$0.maximizeText.setAlpha(floatValue4);
                this.this$0.snapWindowText.setAlpha(floatValue4);
                break;
            case 4:
                float floatValue5 = ((Float) this.$this_apply.getAnimatedValue()).floatValue();
                this.this$0.maximizeButton.setScaleY(floatValue5);
                this.this$0.snapButtonsLayout.setScaleY(floatValue5);
                this.this$0.maximizeText.setScaleY(floatValue5);
                this.this$0.snapWindowText.setScaleY(floatValue5);
                break;
            default:
                float floatValue6 = ((Float) this.$this_apply.getAnimatedValue()).floatValue();
                this.this$0.maximizeButton.setAlpha(floatValue6);
                this.this$0.snapButtonsLayout.setAlpha(floatValue6);
                this.this$0.maximizeText.setAlpha(floatValue6);
                this.this$0.snapWindowText.setAlpha(floatValue6);
                break;
        }
    }
}
