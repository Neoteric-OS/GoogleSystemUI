package com.android.keyguard;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.app.animation.Interpolators;
import com.android.settingslib.Utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PinShapeNonHintingView extends LinearLayout implements PinShapeInput {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int mColor;
    public final Rect mFirstChildVisibleRect;
    public boolean mIsAnimatingReset;
    public final PinShapeAdapter mPinShapeAdapter;
    public int mPosition;
    public final ValueAnimator mValueAnimator;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PinShapeViewTransition extends Transition {
        public static void captureValues(TransitionValues transitionValues) {
            Rect rect = new Rect();
            rect.left = transitionValues.view.getLeft();
            rect.top = transitionValues.view.getTop();
            rect.right = transitionValues.view.getRight();
            rect.bottom = transitionValues.view.getBottom();
            transitionValues.values.put("PinShapeViewTransition:bounds", rect);
        }

        @Override // android.transition.Transition
        public final void captureEndValues(TransitionValues transitionValues) {
            if (transitionValues != null) {
                captureValues(transitionValues);
            }
        }

        @Override // android.transition.Transition
        public final void captureStartValues(TransitionValues transitionValues) {
            if (transitionValues != null) {
                captureValues(transitionValues);
            }
        }

        @Override // android.transition.Transition
        public final Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
            if (viewGroup == null || transitionValues == null || transitionValues2 == null) {
                return null;
            }
            final Rect rect = (Rect) transitionValues.values.get("PinShapeViewTransition:bounds");
            final Rect rect2 = (Rect) transitionValues2.values.get("PinShapeViewTransition:bounds");
            final View view = transitionValues.view;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setDuration(160L);
            ofFloat.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.keyguard.PinShapeNonHintingView$PinShapeViewTransition$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Rect rect3 = rect;
                    Rect rect4 = rect2;
                    View view2 = view;
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    int i = (int) ((r2 - rect4.left) * floatValue);
                    view2.setLeftTopRightBottom(rect3.left - i, rect3.top, rect3.right - i, rect3.bottom);
                }
            });
            ofFloat.start();
            return ofFloat;
        }

        @Override // android.transition.Transition
        public final String[] getTransitionProperties() {
            return new String[]{"PinShapeViewTransition:bounds"};
        }
    }

    public PinShapeNonHintingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mColor = Utils.getColorAttr(R.^attr-private.materialColorOnSurfaceVariant, getContext()).getDefaultColor();
        this.mPosition = 0;
        this.mIsAnimatingReset = false;
        this.mValueAnimator = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.mFirstChildVisibleRect = new Rect();
        this.mPinShapeAdapter = new PinShapeAdapter(context);
    }

    @Override // com.android.keyguard.PinShapeInput
    public final void append() {
        if (this.mIsAnimatingReset) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.password_shape_size);
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize));
        imageView.setImageResource(this.mPinShapeAdapter.getShape(this.mPosition));
        if (imageView.getDrawable() != null) {
            imageView.getDrawable().setTint(this.mColor);
        }
        if (imageView.getDrawable() instanceof AnimatedVectorDrawable) {
            ((AnimatedVectorDrawable) imageView.getDrawable()).start();
        }
        TransitionManager.beginDelayedTransition(this, new PinShapeViewTransition());
        addView(imageView);
        this.mPosition++;
    }

    @Override // com.android.keyguard.PinShapeInput
    public final void delete() {
        if (this.mPosition == 0) {
            Log.e(getClass().getName(), "Trying to delete a non-existent char");
            return;
        }
        if (this.mValueAnimator.isRunning()) {
            this.mValueAnimator.end();
        }
        int i = this.mPosition - 1;
        this.mPosition = i;
        final ImageView imageView = (ImageView) getChildAt(i);
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.keyguard.PinShapeNonHintingView$$ExternalSyntheticLambda2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ImageView imageView2 = imageView;
                int i2 = PinShapeNonHintingView.$r8$clinit;
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                imageView2.setScaleX(floatValue);
                imageView2.setScaleY(floatValue);
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.keyguard.PinShapeNonHintingView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                TransitionManager.beginDelayedTransition(PinShapeNonHintingView.this, new PinShapeViewTransition());
                PinShapeNonHintingView.this.removeView(imageView);
            }
        });
        this.mValueAnimator.setDuration(160L);
        this.mValueAnimator.start();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (getChildCount() > 2) {
            boolean z2 = false;
            View childAt = getChildAt(0);
            boolean localVisibleRect = childAt.getLocalVisibleRect(this.mFirstChildVisibleRect);
            Rect rect = this.mFirstChildVisibleRect;
            if (rect.right - rect.left < childAt.getWidth() && childAt.getScaleX() == 1.0f) {
                z2 = true;
            }
            if (!localVisibleRect || z2) {
                setGravity(8388629);
                return;
            }
        }
        setGravity(17);
    }

    @Override // com.android.keyguard.PinShapeInput
    public final void reset() {
        int i = this.mPosition;
        if (i == 0) {
            return;
        }
        float min = Math.min(200 / i, 40);
        this.mIsAnimatingReset = true;
        for (int i2 = 0; i2 < i; i2++) {
            final int i3 = 0;
            Runnable runnable = new Runnable(this) { // from class: com.android.keyguard.PinShapeNonHintingView$$ExternalSyntheticLambda0
                public final /* synthetic */ PinShapeNonHintingView f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i4 = i3;
                    PinShapeNonHintingView pinShapeNonHintingView = this.f$0;
                    switch (i4) {
                        case 0:
                            pinShapeNonHintingView.delete();
                            break;
                        default:
                            pinShapeNonHintingView.mIsAnimatingReset = false;
                            break;
                    }
                }
            };
            long j = (int) (i2 * min);
            postDelayed(runnable, j);
            if (i2 == i - 1) {
                final int i4 = 1;
                postDelayed(new Runnable(this) { // from class: com.android.keyguard.PinShapeNonHintingView$$ExternalSyntheticLambda0
                    public final /* synthetic */ PinShapeNonHintingView f$0;

                    {
                        this.f$0 = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i42 = i4;
                        PinShapeNonHintingView pinShapeNonHintingView = this.f$0;
                        switch (i42) {
                            case 0:
                                pinShapeNonHintingView.delete();
                                break;
                            default:
                                pinShapeNonHintingView.mIsAnimatingReset = false;
                                break;
                        }
                    }
                }, j);
            }
        }
    }

    @Override // com.android.keyguard.PinShapeInput
    public final void setDrawColor(int i) {
        this.mColor = i;
    }

    @Override // com.android.keyguard.PinShapeInput
    public final View getView() {
        return this;
    }
}
