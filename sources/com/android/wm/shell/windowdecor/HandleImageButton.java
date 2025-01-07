package com.android.wm.shell.windowdecor;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HandleImageButton extends ImageButton {
    public final ValueAnimator handleAnimator;

    public HandleImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.handleAnimator = new ValueAnimator();
    }

    public final void animateHandle(float f, long j) {
        if (this.handleAnimator.isRunning()) {
            this.handleAnimator.cancel();
        }
        this.handleAnimator.setDuration(j);
        this.handleAnimator.setFloatValues(getScaleX(), f);
        this.handleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.windowdecor.HandleImageButton$animateHandle$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                HandleImageButton.this.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.handleAnimator.start();
    }

    @Override // android.view.View
    public final void onHoverChanged(boolean z) {
        super.onHoverChanged(z);
        if (z) {
            animateHandle(1.2f, 300L);
        } else {
            if (isPressed()) {
                return;
            }
            animateHandle(1.0f, 300L);
        }
    }

    @Override // android.view.View
    public final void setPressed(boolean z) {
        if (isPressed() != z) {
            super.setPressed(z);
            if (z) {
                animateHandle(0.85f, 200L);
            } else {
                animateHandle(1.0f, 200L);
            }
        }
    }
}
