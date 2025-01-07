package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.Interpolators;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class TapAgainView extends TextView {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.TapAgainView$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ TapAgainView this$0;

        public /* synthetic */ AnonymousClass1(TapAgainView tapAgainView, int i) {
            this.$r8$classId = i;
            this.this$0 = tapAgainView;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.setTranslationY(0.0f);
                    break;
                default:
                    this.this$0.setVisibility(8);
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 1:
                    this.this$0.setVisibility(8);
                    break;
                default:
                    super.onAnimationEnd(animator);
                    break;
            }
        }
    }

    public TapAgainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void animateIn() {
        int dimensionPixelSize = ((TextView) this).mContext.getResources().getDimensionPixelSize(R.dimen.keyguard_indication_y_translation);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, (Property<TapAgainView, Float>) View.ALPHA, 1.0f);
        ofFloat.setStartDelay(150L);
        ofFloat.setDuration(317L);
        ofFloat.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, (Property<TapAgainView, Float>) View.TRANSLATION_Y, dimensionPixelSize, 0.0f);
        ofFloat2.setDuration(600L);
        ofFloat2.addListener(new AnonymousClass1(this, 0));
        animatorSet.playTogether(ofFloat2, ofFloat);
        animatorSet.start();
        setVisibility(0);
    }

    public final void animateOut() {
        int dimensionPixelSize = ((TextView) this).mContext.getResources().getDimensionPixelSize(R.dimen.keyguard_indication_y_translation);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, (Property<TapAgainView, Float>) View.ALPHA, 0.0f);
        ofFloat.setDuration(167L);
        ofFloat.setInterpolator(Interpolators.FAST_OUT_LINEAR_IN);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, (Property<TapAgainView, Float>) View.TRANSLATION_Y, 0.0f, -dimensionPixelSize);
        ofFloat2.setDuration(167L);
        animatorSet.addListener(new AnonymousClass1(this, 1));
        animatorSet.playTogether(ofFloat2, ofFloat);
        animatorSet.start();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        updateColor();
    }

    public final void updateColor() {
        setTextColor(Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorOnSurface, 0, ((TextView) this).mContext));
        setBackground(getResources().getDrawable(R.drawable.rounded_bg_full, ((TextView) this).mContext.getTheme()));
    }
}
