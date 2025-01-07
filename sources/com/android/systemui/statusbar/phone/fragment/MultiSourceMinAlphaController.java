package com.android.systemui.statusbar.phone.fragment;

import android.view.View;
import androidx.core.animation.Animator;
import androidx.core.animation.PathInterpolator;
import androidx.core.animation.ValueAnimator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MultiSourceMinAlphaController {
    public final Map alphas = new LinkedHashMap();
    public final Map animators = new LinkedHashMap();
    public final View view;

    public MultiSourceMinAlphaController(View view) {
        this.view = view;
    }

    public final void animateToAlpha(float f, long j, PathInterpolator pathInterpolator, long j2) {
        ValueAnimator valueAnimator = (ValueAnimator) this.animators.get(2);
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(getMinAlpha(), f);
        ofFloat.setDuration(j);
        ofFloat.setStartDelay(j2);
        ofFloat.mInterpolator = pathInterpolator;
        ofFloat.addUpdateListener(new Animator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.phone.fragment.MultiSourceMinAlphaController$animateToAlpha$1
            @Override // androidx.core.animation.Animator.AnimatorUpdateListener
            public final void onAnimationUpdate(Animator animator) {
                MultiSourceMinAlphaController.this.updateAlpha(2, ((Float) ofFloat.getAnimatedValue()).floatValue());
            }
        });
        ofFloat.start(false);
        this.animators.put(2, ofFloat);
    }

    public final float getMinAlpha() {
        Float valueOf;
        Iterator it = this.alphas.entrySet().iterator();
        if (it.hasNext()) {
            float floatValue = ((Number) ((Map.Entry) it.next()).getValue()).floatValue();
            while (it.hasNext()) {
                floatValue = Math.min(floatValue, ((Number) ((Map.Entry) it.next()).getValue()).floatValue());
            }
            valueOf = Float.valueOf(floatValue);
        } else {
            valueOf = null;
        }
        if (valueOf != null) {
            return valueOf.floatValue();
        }
        return 1.0f;
    }

    public final void setAlpha(int i, float f) {
        ValueAnimator valueAnimator = (ValueAnimator) this.animators.get(Integer.valueOf(i));
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        updateAlpha(i, f);
    }

    public final void updateAlpha(int i, float f) {
        this.alphas.put(Integer.valueOf(i), Float.valueOf(f));
        float minAlpha = getMinAlpha();
        this.view.setVisibility(minAlpha == 0.0f ? 4 : 0);
        this.view.setAlpha(minAlpha);
    }
}
