package com.android.systemui.screenshot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.MathUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DraggableConstraintLayout$SwipeDismissHandler implements View.OnTouchListener {
    public int mDirectionX;
    public ValueAnimator mDismissAnimation;
    public final DisplayMetrics mDisplayMetrics;
    public final GestureDetector mGestureDetector;
    public float mPreviousX;
    public float mStartX;
    public final ClipboardOverlayView mView;
    public final /* synthetic */ ClipboardOverlayView this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissHandler$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public boolean mCancelled;

        public AnonymousClass1() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
            this.mCancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (this.mCancelled) {
                return;
            }
            DraggableConstraintLayout$SwipeDismissHandler.this.this$0.mCallbacks.onDismissComplete();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SwipeDismissGestureListener extends GestureDetector.SimpleOnGestureListener {
        public SwipeDismissGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (DraggableConstraintLayout$SwipeDismissHandler.this.mView.getTranslationX() * f <= 0.0f) {
                return false;
            }
            ValueAnimator valueAnimator = DraggableConstraintLayout$SwipeDismissHandler.this.mDismissAnimation;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                return false;
            }
            ValueAnimator createSwipeDismissAnimation = DraggableConstraintLayout$SwipeDismissHandler.this.createSwipeDismissAnimation(f / 1000.0f);
            DraggableConstraintLayout$SwipeDismissHandler.this.this$0.mCallbacks.onSwipeDismissInitiated(createSwipeDismissAnimation);
            DraggableConstraintLayout$SwipeDismissHandler draggableConstraintLayout$SwipeDismissHandler = DraggableConstraintLayout$SwipeDismissHandler.this;
            draggableConstraintLayout$SwipeDismissHandler.mDismissAnimation = createSwipeDismissAnimation;
            createSwipeDismissAnimation.addListener(draggableConstraintLayout$SwipeDismissHandler.new AnonymousClass1());
            draggableConstraintLayout$SwipeDismissHandler.mDismissAnimation.start();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            DraggableConstraintLayout$SwipeDismissHandler.this.mView.setTranslationX(motionEvent2.getRawX() - DraggableConstraintLayout$SwipeDismissHandler.this.mStartX);
            DraggableConstraintLayout$SwipeDismissHandler draggableConstraintLayout$SwipeDismissHandler = DraggableConstraintLayout$SwipeDismissHandler.this;
            float rawX = motionEvent2.getRawX();
            DraggableConstraintLayout$SwipeDismissHandler draggableConstraintLayout$SwipeDismissHandler2 = DraggableConstraintLayout$SwipeDismissHandler.this;
            draggableConstraintLayout$SwipeDismissHandler.mDirectionX = rawX < draggableConstraintLayout$SwipeDismissHandler2.mPreviousX ? -1 : 1;
            draggableConstraintLayout$SwipeDismissHandler2.mPreviousX = motionEvent2.getRawX();
            return true;
        }
    }

    public DraggableConstraintLayout$SwipeDismissHandler(ClipboardOverlayView clipboardOverlayView, Context context, ClipboardOverlayView clipboardOverlayView2) {
        this.this$0 = clipboardOverlayView;
        this.mView = clipboardOverlayView2;
        this.mGestureDetector = new GestureDetector(context, new SwipeDismissGestureListener());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mDisplayMetrics = displayMetrics;
        context.getDisplay().getRealMetrics(displayMetrics);
    }

    public final ValueAnimator createSwipeDismissAnimation(float f) {
        int i;
        float min = Math.min(3.0f, Math.max(1.0f, f));
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        final float translationX = this.mView.getTranslationX();
        int layoutDirection = this.mView.getContext().getResources().getConfiguration().getLayoutDirection();
        if (translationX > 0.0f || (translationX == 0.0f && layoutDirection == 1)) {
            i = this.mDisplayMetrics.widthPixels;
        } else {
            View findViewById = this.this$0.findViewById(R.id.actions_container_background);
            i = (findViewById == null ? 0 : findViewById.getRight()) * (-1);
        }
        float f2 = i - translationX;
        float min2 = Math.min(Math.abs(f2), FloatingWindowUtil.dpToPx(this.mDisplayMetrics, 400.0f));
        final float copySign = Math.copySign(min2, f2);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissHandler$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                DraggableConstraintLayout$SwipeDismissHandler draggableConstraintLayout$SwipeDismissHandler = DraggableConstraintLayout$SwipeDismissHandler.this;
                float f3 = translationX;
                float f4 = copySign;
                draggableConstraintLayout$SwipeDismissHandler.getClass();
                draggableConstraintLayout$SwipeDismissHandler.mView.setTranslationX(MathUtils.lerp(f3, f4 + f3, valueAnimator.getAnimatedFraction()));
                draggableConstraintLayout$SwipeDismissHandler.mView.setAlpha(1.0f - valueAnimator.getAnimatedFraction());
            }
        });
        ofFloat.setDuration((long) Math.abs(min2 / min));
        return ofFloat;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean onTouchEvent = this.mGestureDetector.onTouchEvent(motionEvent);
        this.this$0.mCallbacks.onInteraction();
        if (motionEvent.getActionMasked() == 0) {
            float rawX = motionEvent.getRawX();
            this.mStartX = rawX;
            this.mPreviousX = rawX;
            return true;
        }
        if (motionEvent.getActionMasked() != 1) {
            return onTouchEvent;
        }
        ValueAnimator valueAnimator = this.mDismissAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            return true;
        }
        float translationX = this.mView.getTranslationX();
        if (this.mDirectionX * translationX <= 0.0f || Math.abs(translationX) < FloatingWindowUtil.dpToPx(this.mDisplayMetrics, 20.0f)) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            final float translationX2 = this.mView.getTranslationX();
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissHandler$$ExternalSyntheticLambda1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    DraggableConstraintLayout$SwipeDismissHandler draggableConstraintLayout$SwipeDismissHandler = DraggableConstraintLayout$SwipeDismissHandler.this;
                    float f = translationX2;
                    draggableConstraintLayout$SwipeDismissHandler.getClass();
                    draggableConstraintLayout$SwipeDismissHandler.mView.setTranslationX(MathUtils.lerp(f, 0.0f, valueAnimator2.getAnimatedFraction()));
                }
            });
            ofFloat.start();
        } else {
            ValueAnimator createSwipeDismissAnimation = createSwipeDismissAnimation(FloatingWindowUtil.dpToPx(this.mDisplayMetrics, 1.0f));
            this.this$0.mCallbacks.onSwipeDismissInitiated(createSwipeDismissAnimation);
            this.mDismissAnimation = createSwipeDismissAnimation;
            createSwipeDismissAnimation.addListener(new AnonymousClass1());
            this.mDismissAnimation.start();
        }
        return true;
    }
}
