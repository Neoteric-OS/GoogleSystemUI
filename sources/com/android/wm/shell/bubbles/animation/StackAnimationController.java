package com.android.wm.shell.bubbles.animation;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BubblePositioner;
import com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda2;
import com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda20;
import com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda25;
import com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout;
import com.android.wm.shell.common.FloatingContentCoordinator;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.google.android.collect.Sets;
import java.util.HashMap;
import java.util.Set;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StackAnimationController extends PhysicsAnimationLayout.PhysicsAnimationController {
    public final BubbleStackView$$ExternalSyntheticLambda20 mBubbleCountSupplier;
    public int mBubblePaddingTop;
    public int mBubbleSize;
    public int mElevation;
    public final FloatingContentCoordinator mFloatingContentCoordinator;
    public AnonymousClass2 mMagnetizedStack;
    public int mMaxBubbles;
    public final BubbleStackView$$ExternalSyntheticLambda2 mOnBubbleAnimatedOutAction;
    public final BubbleStackView$$ExternalSyntheticLambda2 mOnStackAnimationFinished;
    public final BubblePositioner mPositioner;
    public float mStackOffset;
    public float mSwapAnimationOffset;
    public final PhysicsAnimator.SpringConfig mAnimateOutSpringConfig = new PhysicsAnimator.SpringConfig(700.0f, 1.0f);
    public final PointF mStackPosition = new PointF(-1.0f, -1.0f);
    public Rect mAnimatingToBounds = new Rect();
    public boolean mStackMovedToStartPosition = false;
    public float mPreImeY = -1.4E-45f;
    public final HashMap mStackPositionAnimations = new HashMap();
    public boolean mIsMovingFromFlinging = false;
    public boolean mFirstBubbleSpringingToTouch = false;
    public boolean mSpringToTouchOnNextMotionEvent = false;
    public final AnonymousClass1 mStackFloatingContent = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.animation.StackAnimationController$1, reason: invalid class name */
    public final class AnonymousClass1 implements FloatingContentCoordinator.FloatingContent {
        public final Rect mFloatingBoundsOnScreen = new Rect();

        public AnonymousClass1() {
        }

        @Override // com.android.wm.shell.common.FloatingContentCoordinator.FloatingContent
        public final Rect getAllowedFloatingBoundsRegion() {
            Rect floatingBoundsOnScreen = getFloatingBoundsOnScreen();
            Rect rect = new Rect();
            StackAnimationController stackAnimationController = StackAnimationController.this;
            stackAnimationController.mPositioner.getAllowableStackPositionRegion(stackAnimationController.mBubbleCountSupplier.f$0.getBubbleCount()).roundOut(rect);
            rect.right = floatingBoundsOnScreen.width() + rect.right;
            rect.bottom = floatingBoundsOnScreen.height() + rect.bottom;
            return rect;
        }

        @Override // com.android.wm.shell.common.FloatingContentCoordinator.FloatingContent
        public final Rect getFloatingBoundsOnScreen() {
            StackAnimationController stackAnimationController = StackAnimationController.this;
            if (!stackAnimationController.mAnimatingToBounds.isEmpty()) {
                return stackAnimationController.mAnimatingToBounds;
            }
            if (stackAnimationController.mLayout.getChildCount() > 0) {
                Rect rect = this.mFloatingBoundsOnScreen;
                PointF pointF = stackAnimationController.mStackPosition;
                float f = pointF.x;
                float f2 = pointF.y;
                int i = stackAnimationController.mBubbleSize;
                rect.set((int) f, (int) f2, ((int) f) + i, ((int) f2) + i + stackAnimationController.mBubblePaddingTop);
            } else {
                this.mFloatingBoundsOnScreen.setEmpty();
            }
            return this.mFloatingBoundsOnScreen;
        }

        @Override // com.android.wm.shell.common.FloatingContentCoordinator.FloatingContent
        public final void moveToBounds(Rect rect) {
            StackAnimationController.this.springStack(rect.left, rect.top, 700.0f);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StackPositionProperty extends FloatPropertyCompat {
        public final DynamicAnimation.ViewProperty mProperty;

        public StackPositionProperty(DynamicAnimation.ViewProperty viewProperty) {
            viewProperty.toString();
            this.mProperty = viewProperty;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public final float getValue(Object obj) {
            StackAnimationController stackAnimationController = StackAnimationController.this;
            if (stackAnimationController.mLayout.getChildCount() <= 0) {
                return 0.0f;
            }
            return this.mProperty.getValue(stackAnimationController.mLayout.getChildAt(0));
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public final void setValue(Object obj, float f) {
            StackAnimationController.this.moveFirstBubbleWithStackFollowing(this.mProperty, f);
        }
    }

    public StackAnimationController(FloatingContentCoordinator floatingContentCoordinator, BubbleStackView$$ExternalSyntheticLambda20 bubbleStackView$$ExternalSyntheticLambda20, BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2, BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda22, BubblePositioner bubblePositioner) {
        this.mFloatingContentCoordinator = floatingContentCoordinator;
        this.mBubbleCountSupplier = bubbleStackView$$ExternalSyntheticLambda20;
        this.mOnBubbleAnimatedOutAction = bubbleStackView$$ExternalSyntheticLambda2;
        this.mOnStackAnimationFinished = bubbleStackView$$ExternalSyntheticLambda22;
        this.mPositioner = bubblePositioner;
    }

    public final void animateInBubble(View view, int i) {
        if (isActiveController()) {
            float offsetForChainedPropertyAnimation = getOffsetForChainedPropertyAnimation(DynamicAnimation.TRANSLATION_Y, 0);
            PointF pointF = this.mStackPosition;
            float f = (offsetForChainedPropertyAnimation * i) + pointF.y;
            float f2 = pointF.x;
            BubblePositioner bubblePositioner = this.mPositioner;
            if (bubblePositioner.showBubblesVertically()) {
                view.setTranslationY(f);
                view.setTranslationX(isStackOnLeftSide() ? f2 - 100.0f : f2 + 100.0f);
            } else {
                view.setTranslationX(this.mStackPosition.x);
                view.setTranslationY(100.0f + f);
            }
            view.setScaleX(0.5f);
            view.setScaleY(0.5f);
            view.setAlpha(0.0f);
            ViewPropertyAnimator withEndAction = view.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(300L).withEndAction(new StackAnimationController$$ExternalSyntheticLambda0(1, view));
            view.setTag(R.id.reorder_animator_tag, withEndAction);
            if (bubblePositioner.showBubblesVertically()) {
                withEndAction.translationX(f2);
            } else {
                withEndAction.translationY(f);
            }
        }
    }

    public final void cancelStackPositionAnimation(DynamicAnimation.ViewProperty viewProperty) {
        if (this.mStackPositionAnimations.containsKey(viewProperty)) {
            ((DynamicAnimation) this.mStackPositionAnimations.get(viewProperty)).cancel();
        }
    }

    public final float flingStackThenSpringToEdge(float f, float f2, float f3) {
        boolean z = f - ((float) (this.mBubbleSize / 2)) >= ((float) (this.mLayout.getWidth() / 2)) ? f2 < -750.0f : f2 < 750.0f;
        RectF allowableStackPositionRegion = this.mPositioner.getAllowableStackPositionRegion(this.mBubbleCountSupplier.f$0.getBubbleCount());
        float f4 = z ? allowableStackPositionRegion.left : allowableStackPositionRegion.right;
        PhysicsAnimationLayout physicsAnimationLayout = this.mLayout;
        if (physicsAnimationLayout != null && physicsAnimationLayout.getChildCount() != 0) {
            ContentResolver contentResolver = this.mLayout.getContext().getContentResolver();
            float f5 = Settings.Secure.getFloat(contentResolver, "bubble_stiffness", 700.0f);
            float f6 = Settings.Secure.getFloat(contentResolver, "bubble_damping", 0.85f);
            float f7 = Settings.Secure.getFloat(contentResolver, "bubble_friction", 1.9f);
            float f8 = 4.2f * f7;
            float f9 = (f4 - f) * f8;
            notifyFloatingCoordinatorStackAnimatingTo(f4, Math.min(allowableStackPositionRegion.bottom, Math.max(allowableStackPositionRegion.top, (f3 / f8) + this.mStackPosition.y)));
            float min = z ? Math.min(f9, f2) : Math.max(f9, f2);
            DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
            SpringForce springForce = new SpringForce();
            springForce.setStiffness(f5);
            springForce.setDampingRatio(f6);
            flingThenSpringFirstBubbleWithStackFollowing(anonymousClass1, min, f7, springForce, Float.valueOf(f4));
            DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_Y;
            SpringForce springForce2 = new SpringForce();
            springForce2.setStiffness(f5);
            springForce2.setDampingRatio(f6);
            flingThenSpringFirstBubbleWithStackFollowing(anonymousClass12, f3, f7, springForce2, null);
            this.mFirstBubbleSpringingToTouch = false;
            this.mIsMovingFromFlinging = true;
        }
        return f4;
    }

    public final void flingThenSpringFirstBubbleWithStackFollowing(final DynamicAnimation.ViewProperty viewProperty, float f, float f2, final SpringForce springForce, final Float f3) {
        if (isActiveController()) {
            Log.d("Bubbs.StackCtrl", "Flinging " + PhysicsAnimationLayout.getReadablePropertyName(viewProperty) + ".");
            StackPositionProperty stackPositionProperty = new StackPositionProperty(viewProperty);
            float value = this.mLayout.getChildCount() > 0 ? stackPositionProperty.mProperty.getValue(this.mLayout.getChildAt(0)) : 0.0f;
            RectF allowableStackPositionRegion = this.mPositioner.getAllowableStackPositionRegion(this.mBubbleCountSupplier.f$0.getBubbleCount());
            DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
            final float f4 = viewProperty.equals(anonymousClass1) ? allowableStackPositionRegion.left : allowableStackPositionRegion.top;
            final float f5 = viewProperty.equals(anonymousClass1) ? allowableStackPositionRegion.right : allowableStackPositionRegion.bottom;
            FlingAnimation flingAnimation = new FlingAnimation(this, stackPositionProperty);
            flingAnimation.setFriction(f2);
            flingAnimation.mVelocity = f;
            flingAnimation.mMinValue = Math.min(value, f4);
            flingAnimation.mMaxValue = Math.max(value, f5);
            flingAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.wm.shell.bubbles.animation.StackAnimationController$$ExternalSyntheticLambda5
                @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
                public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f6, float f7) {
                    float max;
                    StackAnimationController stackAnimationController = StackAnimationController.this;
                    if (z) {
                        stackAnimationController.getClass();
                        return;
                    }
                    stackAnimationController.mPositioner.setRestingPosition(stackAnimationController.mStackPosition);
                    Float f8 = f3;
                    if (f8 != null) {
                        max = f8.floatValue();
                    } else {
                        max = Math.max(f4, Math.min(f5, f6));
                    }
                    float f9 = max;
                    stackAnimationController.springFirstBubbleWithStackFollowing(viewProperty, springForce, f7, f9, new Runnable[0]);
                }
            });
            cancelStackPositionAnimation(viewProperty);
            this.mStackPositionAnimations.put(viewProperty, flingAnimation);
            flingAnimation.start();
        }
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final Set getAnimatedProperties() {
        return Sets.newHashSet(new DynamicAnimation.ViewProperty[]{DynamicAnimation.TRANSLATION_X, DynamicAnimation.TRANSLATION_Y, DynamicAnimation.ALPHA, DynamicAnimation.SCALE_X, DynamicAnimation.SCALE_Y});
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final int getNextAnimationInChain(DynamicAnimation.ViewProperty viewProperty, int i) {
        if (viewProperty.equals(DynamicAnimation.TRANSLATION_X) || viewProperty.equals(DynamicAnimation.TRANSLATION_Y)) {
            return i + 1;
        }
        return -1;
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final float getOffsetForChainedPropertyAnimation(DynamicAnimation.ViewProperty viewProperty, int i) {
        if (!viewProperty.equals(DynamicAnimation.TRANSLATION_Y) || isStackStuckToTarget() || i > 1) {
            return 0.0f;
        }
        return this.mStackOffset;
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final SpringForce getSpringForce(View view) {
        float f = Settings.Secure.getFloat(this.mLayout.getContext().getContentResolver(), "bubble_damping", 0.9f);
        SpringForce springForce = new SpringForce();
        springForce.setDampingRatio(f);
        springForce.setStiffness(800.0f);
        return springForce;
    }

    public final boolean isStackOnLeftSide() {
        return this.mPositioner.isStackOnLeft(this.mStackPosition);
    }

    public final boolean isStackStuckToTarget() {
        AnonymousClass2 anonymousClass2 = this.mMagnetizedStack;
        return anonymousClass2 != null && anonymousClass2.getObjectStuckToTarget();
    }

    public final void moveFirstBubbleWithStackFollowing(DynamicAnimation.ViewProperty viewProperty, float f) {
        if (viewProperty.equals(DynamicAnimation.TRANSLATION_X)) {
            this.mStackPosition.x = f;
        } else if (viewProperty.equals(DynamicAnimation.TRANSLATION_Y)) {
            this.mStackPosition.y = f;
        }
        if (this.mLayout.getChildCount() > 0) {
            viewProperty.setValue(this.mLayout.getChildAt(0), f);
            if (this.mLayout.getChildCount() > 1) {
                float offsetForChainedPropertyAnimation = getOffsetForChainedPropertyAnimation(viewProperty, 0) + f;
                PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(this.mLayout.getChildAt(1));
                animationForChild.property(viewProperty, offsetForChainedPropertyAnimation, new Runnable[0]);
                animationForChild.start(new Runnable[0]);
            }
        }
    }

    public final void moveToFinalIndex(View view, int i, BubbleStackView$$ExternalSyntheticLambda25 bubbleStackView$$ExternalSyntheticLambda25) {
        view.setTag(R.id.reorder_animator_tag, view.animate().translationY((Math.min(i, 1) * this.mStackOffset) + this.mStackPosition.y).setDuration(300L).withEndAction(new StackAnimationController$$ExternalSyntheticLambda2(1, view, bubbleStackView$$ExternalSyntheticLambda25)));
    }

    public final void notifyFloatingCoordinatorStackAnimatingTo(float f, float f2) {
        AnonymousClass1 anonymousClass1 = this.mStackFloatingContent;
        Rect floatingBoundsOnScreen = anonymousClass1.getFloatingBoundsOnScreen();
        floatingBoundsOnScreen.offsetTo((int) f, (int) f2);
        this.mAnimatingToBounds = floatingBoundsOnScreen;
        this.mFloatingContentCoordinator.onContentMoved(anonymousClass1);
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final void onActiveControllerForLayout(PhysicsAnimationLayout physicsAnimationLayout) {
        Resources resources = physicsAnimationLayout.getResources();
        BubblePositioner bubblePositioner = this.mPositioner;
        this.mStackOffset = bubblePositioner.mStackOffset;
        this.mSwapAnimationOffset = resources.getDimensionPixelSize(R.dimen.bubble_swap_animation_offset);
        this.mMaxBubbles = resources.getInteger(R.integer.bubbles_max_rendered);
        this.mElevation = resources.getDimensionPixelSize(R.dimen.bubble_elevation);
        this.mBubbleSize = bubblePositioner.mBubbleSize;
        this.mBubblePaddingTop = bubblePositioner.mBubblePaddingTop;
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final void onChildAdded(View view, int i) {
        if (isStackStuckToTarget()) {
            return;
        }
        if (this.mBubbleCountSupplier.f$0.getBubbleCount() == 1) {
            this.mLayout.setVisibility(4);
            this.mLayout.post(new StackAnimationController$$ExternalSyntheticLambda0(0, this));
        } else {
            if (this.mStackMovedToStartPosition && this.mLayout.indexOfChild(view) == 0) {
                animateInBubble(view, i);
                return;
            }
            view.setAlpha(1.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final void onChildRemoved(View view, PhysicsAnimationLayout$$ExternalSyntheticLambda0 physicsAnimationLayout$$ExternalSyntheticLambda0) {
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(view);
        companion.spring(DynamicAnimation.ALPHA, 0.0f);
        DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.SCALE_X;
        PhysicsAnimator.SpringConfig springConfig = this.mAnimateOutSpringConfig;
        companion.spring(anonymousClass1, 0.0f, 0.0f, springConfig);
        companion.spring(DynamicAnimation.SCALE_Y, 0.0f, 0.0f, springConfig);
        companion.withEndActions(physicsAnimationLayout$$ExternalSyntheticLambda0, this.mOnBubbleAnimatedOutAction);
        companion.start();
        if (this.mBubbleCountSupplier.f$0.getBubbleCount() <= 0) {
            BubblePositioner bubblePositioner = this.mPositioner;
            bubblePositioner.setRestingPosition(bubblePositioner.getRestingPosition());
            this.mFloatingContentCoordinator.allContentBounds.remove(this.mStackFloatingContent);
        } else {
            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(this.mLayout.getChildAt(0));
            animationForChild.mPathAnimator = null;
            animationForChild.property(DynamicAnimation.TRANSLATION_X, this.mStackPosition.x, new Runnable[0]);
            animationForChild.start(new Runnable[0]);
        }
    }

    public final void setStackPosition(PointF pointF) {
        Log.d("Bubbs.StackCtrl", String.format("Setting position to (%f, %f).", Float.valueOf(pointF.x), Float.valueOf(pointF.y)));
        this.mStackPosition.set(pointF.x, pointF.y);
        this.mPositioner.setRestingPosition(this.mStackPosition);
        if (isActiveController()) {
            PhysicsAnimationLayout physicsAnimationLayout = this.mLayout;
            DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
            DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_Y;
            physicsAnimationLayout.cancelAllAnimationsOfProperties(anonymousClass1, anonymousClass12);
            cancelStackPositionAnimation(anonymousClass1);
            cancelStackPositionAnimation(anonymousClass12);
            this.mLayout.mEndActionForProperty.remove(anonymousClass1);
            this.mLayout.mEndActionForProperty.remove(anonymousClass12);
            float offsetForChainedPropertyAnimation = getOffsetForChainedPropertyAnimation(anonymousClass1, 0);
            float offsetForChainedPropertyAnimation2 = getOffsetForChainedPropertyAnimation(anonymousClass12, 0);
            for (int i = 0; i < this.mLayout.getChildCount(); i++) {
                float min = Math.min(i, 1);
                this.mLayout.getChildAt(i).setTranslationX((min * offsetForChainedPropertyAnimation) + pointF.x);
                this.mLayout.getChildAt(i).setTranslationY((min * offsetForChainedPropertyAnimation2) + pointF.y);
            }
        }
    }

    public final void springFirstBubbleWithStackFollowing(DynamicAnimation.ViewProperty viewProperty, SpringForce springForce, float f, float f2, final Runnable... runnableArr) {
        if (this.mLayout.getChildCount() == 0 || !isActiveController()) {
            return;
        }
        Log.d("Bubbs.StackCtrl", String.format("Springing %s to final position %f.", PhysicsAnimationLayout.getReadablePropertyName(viewProperty), Float.valueOf(f2)));
        final boolean z = this.mSpringToTouchOnNextMotionEvent;
        SpringAnimation springAnimation = new SpringAnimation(this, new StackPositionProperty(viewProperty));
        springAnimation.mSpring = springForce;
        springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.wm.shell.bubbles.animation.StackAnimationController$$ExternalSyntheticLambda1
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z2, float f3, float f4) {
                Runnable[] runnableArr2 = runnableArr;
                boolean z3 = z;
                StackAnimationController stackAnimationController = StackAnimationController.this;
                if (!z3) {
                    stackAnimationController.mPositioner.setRestingPosition(stackAnimationController.mStackPosition);
                }
                BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = stackAnimationController.mOnStackAnimationFinished;
                if (bubbleStackView$$ExternalSyntheticLambda2 != null) {
                    bubbleStackView$$ExternalSyntheticLambda2.run();
                }
                for (Runnable runnable : runnableArr2) {
                    runnable.run();
                }
            }
        });
        springAnimation.mVelocity = f;
        cancelStackPositionAnimation(viewProperty);
        this.mStackPositionAnimations.put(viewProperty, springAnimation);
        springAnimation.animateToFinalPosition(f2);
    }

    public final void springStack(float f, float f2, float f3) {
        notifyFloatingCoordinatorStackAnimatingTo(f, f2);
        DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(f3);
        springForce.setDampingRatio(0.85f);
        springFirstBubbleWithStackFollowing(anonymousClass1, springForce, 0.0f, f, new Runnable[0]);
        DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_Y;
        SpringForce springForce2 = new SpringForce();
        springForce2.setStiffness(f3);
        springForce2.setDampingRatio(0.85f);
        springFirstBubbleWithStackFollowing(anonymousClass12, springForce2, 0.0f, f2, new Runnable[0]);
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final void onChildReordered() {
    }
}
