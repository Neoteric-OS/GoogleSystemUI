package com.android.systemui.biometrics;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.android.internal.graphics.ColorUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthRippleView$fadeDwellRipple$1$2 extends AnimatorListenerAdapter {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AuthRippleView this$0;

    public /* synthetic */ AuthRippleView$fadeDwellRipple$1$2(AuthRippleView authRippleView, int i) {
        this.$r8$classId = i;
        this.this$0 = authRippleView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                AuthRippleView authRippleView = this.this$0;
                authRippleView.drawDwell = false;
                DwellRippleShader dwellRippleShader = authRippleView.dwellShader;
                dwellRippleShader.setColor(ColorUtils.setAlphaComponent(dwellRippleShader.color, 255));
                break;
            case 1:
                AuthRippleView authRippleView2 = this.this$0;
                authRippleView2.drawDwell = false;
                DwellRippleShader dwellRippleShader2 = authRippleView2.dwellShader;
                dwellRippleShader2.setColor(ColorUtils.setAlphaComponent(dwellRippleShader2.color, 255));
                break;
            default:
                this.this$0.drawDwell = false;
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                Animator animator2 = this.this$0.retractDwellAnimator;
                if (animator2 != null) {
                    animator2.cancel();
                }
                Animator animator3 = this.this$0.dwellPulseOutAnimator;
                if (animator3 != null) {
                    animator3.cancel();
                }
                this.this$0.drawDwell = true;
                break;
            case 1:
                Animator animator4 = this.this$0.dwellPulseOutAnimator;
                if (animator4 != null) {
                    animator4.cancel();
                }
                this.this$0.drawDwell = true;
                break;
            default:
                Animator animator5 = this.this$0.retractDwellAnimator;
                if (animator5 != null) {
                    animator5.cancel();
                }
                Animator animator6 = this.this$0.fadeDwellAnimator;
                if (animator6 != null) {
                    animator6.cancel();
                }
                this.this$0.setVisibility(0);
                this.this$0.drawDwell = true;
                break;
        }
    }
}
