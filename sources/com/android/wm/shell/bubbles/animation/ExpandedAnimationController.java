package com.android.wm.shell.bubbles.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BadgedImageView;
import com.android.wm.shell.bubbles.BubblePositioner;
import com.android.wm.shell.bubbles.BubbleStackView;
import com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda2;
import com.android.wm.shell.bubbles.BubbleViewProvider;
import com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.google.android.collect.Sets;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExpandedAnimationController extends PhysicsAnimationLayout.PhysicsAnimationController {
    public BubbleStackView$$ExternalSyntheticLambda2 mAfterCollapse;
    public Runnable mAfterExpand;
    public float mBubbleSizePx;
    public final BubbleStackView mBubbleStackView;
    public PointF mCollapsePoint;
    public AnonymousClass1 mMagnetizedBubbleDraggingOut;
    public final BubbleStackView$$ExternalSyntheticLambda2 mOnBubbleAnimatedOutAction;
    public final BubblePositioner mPositioner;
    public float mStackOffsetPx;
    public final PhysicsAnimator.SpringConfig mAnimateOutSpringConfig = new PhysicsAnimator.SpringConfig(400.0f, 1.0f);
    public boolean mAnimatingExpand = false;
    public boolean mPreparingToCollapse = false;
    public boolean mAnimatingCollapse = false;
    public boolean mFadeBubblesDuringCollapse = false;
    public boolean mSpringingBubbleToTouch = false;
    public boolean mSpringToTouchOnNextMotionEvent = false;
    public boolean mBubbleDraggedOutEnough = false;

    public ExpandedAnimationController(BubblePositioner bubblePositioner, BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2, BubbleStackView bubbleStackView) {
        this.mPositioner = bubblePositioner;
        updateResources();
        this.mOnBubbleAnimatedOutAction = bubbleStackView$$ExternalSyntheticLambda2;
        this.mCollapsePoint = bubblePositioner.getDefaultStartPosition(false);
        this.mBubbleStackView = bubbleStackView;
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final Set getAnimatedProperties() {
        return Sets.newHashSet(new DynamicAnimation.ViewProperty[]{DynamicAnimation.TRANSLATION_X, DynamicAnimation.TRANSLATION_Y, DynamicAnimation.TRANSLATION_Z, DynamicAnimation.SCALE_X, DynamicAnimation.SCALE_Y, DynamicAnimation.ALPHA});
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final int getNextAnimationInChain(DynamicAnimation.ViewProperty viewProperty, int i) {
        return -1;
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final float getOffsetForChainedPropertyAnimation(DynamicAnimation.ViewProperty viewProperty, int i) {
        return 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0026  */
    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.dynamicanimation.animation.SpringForce getSpringForce(android.view.View r1) {
        /*
            r0 = this;
            boolean r0 = r1 instanceof com.android.wm.shell.bubbles.BadgedImageView
            if (r0 == 0) goto L1a
            com.android.wm.shell.bubbles.BadgedImageView r1 = (com.android.wm.shell.bubbles.BadgedImageView) r1
            com.android.wm.shell.bubbles.BubbleViewProvider r0 = r1.mBubble
            if (r0 == 0) goto Lf
            java.lang.String r0 = r0.getKey()
            goto L10
        Lf:
            r0 = 0
        L10:
            java.lang.String r1 = "Overflow"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L1a
            r0 = 1
            goto L1b
        L1a:
            r0 = 0
        L1b:
            androidx.dynamicanimation.animation.SpringForce r1 = new androidx.dynamicanimation.animation.SpringForce
            r1.<init>()
            if (r0 == 0) goto L26
            r0 = 1063675494(0x3f666666, float:0.9)
            goto L29
        L26:
            r0 = 1059481190(0x3f266666, float:0.65)
        L29:
            r1.setDampingRatio(r0)
            r0 = 1128792064(0x43480000, float:200.0)
            r1.setStiffness(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.animation.ExpandedAnimationController.getSpringForce(android.view.View):androidx.dynamicanimation.animation.SpringForce");
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final void onActiveControllerForLayout(PhysicsAnimationLayout physicsAnimationLayout) {
        updateResources();
        this.mLayout.setVisibility(0);
        animationsForChildrenFromIndex(false, new ExpandedAnimationController$$ExternalSyntheticLambda1()).startAll(new Runnable[0]);
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final void onChildAdded(View view, int i) {
        if (this.mAnimatingExpand) {
            startOrUpdatePathAnimation(true);
            return;
        }
        if (this.mAnimatingCollapse) {
            startOrUpdatePathAnimation(false);
            return;
        }
        PointF pointF = this.mCollapsePoint;
        BubblePositioner bubblePositioner = this.mPositioner;
        boolean isStackOnLeft = bubblePositioner.isStackOnLeft(pointF);
        PointF expandedBubbleXY = bubblePositioner.getExpandedBubbleXY(i, this.mBubbleStackView.getState());
        if (bubblePositioner.showBubblesVertically()) {
            view.setTranslationY(expandedBubbleXY.y);
        } else {
            view.setTranslationX(expandedBubbleXY.x);
        }
        if (this.mPreparingToCollapse) {
            return;
        }
        if (bubblePositioner.showBubblesVertically()) {
            float f = isStackOnLeft ? expandedBubbleXY.x - (this.mBubbleSizePx * 4.0f) : expandedBubbleXY.x + (this.mBubbleSizePx * 4.0f);
            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(view);
            Map map = animationForChild.mInitialPropertyValues;
            DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
            map.put(anonymousClass1, Float.valueOf(f));
            animationForChild.mPathAnimator = null;
            animationForChild.property(anonymousClass1, expandedBubbleXY.x, new Runnable[0]);
            animationForChild.start(new Runnable[0]);
        } else {
            float f2 = expandedBubbleXY.y - (this.mBubbleSizePx * 4.0f);
            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild2 = animationForChild(view);
            animationForChild2.mInitialPropertyValues.put(DynamicAnimation.TRANSLATION_Y, Float.valueOf(f2));
            animationForChild2.translationY(expandedBubbleXY.y, new Runnable[0]);
            animationForChild2.start(new Runnable[0]);
        }
        updateBubblePositions();
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final void onChildRemoved(View view, PhysicsAnimationLayout$$ExternalSyntheticLambda0 physicsAnimationLayout$$ExternalSyntheticLambda0) {
        BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = this.mOnBubbleAnimatedOutAction;
        AnonymousClass1 anonymousClass1 = this.mMagnetizedBubbleDraggingOut;
        if (view.equals(anonymousClass1 == null ? null : (View) anonymousClass1.underlyingObject)) {
            this.mMagnetizedBubbleDraggingOut = null;
            physicsAnimationLayout$$ExternalSyntheticLambda0.run();
            bubbleStackView$$ExternalSyntheticLambda2.run();
        } else {
            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
            PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(view);
            companion.spring(DynamicAnimation.ALPHA, 0.0f);
            DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.SCALE_X;
            PhysicsAnimator.SpringConfig springConfig = this.mAnimateOutSpringConfig;
            companion.spring(anonymousClass12, 0.0f, 0.0f, springConfig);
            companion.spring(DynamicAnimation.SCALE_Y, 0.0f, 0.0f, springConfig);
            companion.withEndActions(physicsAnimationLayout$$ExternalSyntheticLambda0, bubbleStackView$$ExternalSyntheticLambda2);
            companion.start();
        }
        updateBubblePositions();
    }

    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController
    public final void onChildReordered() {
        if (this.mPreparingToCollapse) {
            return;
        }
        if (this.mAnimatingCollapse) {
            startOrUpdatePathAnimation(false);
        } else {
            updateBubblePositions();
        }
    }

    public final void snapBubbleBack(View view, float f, float f2) {
        PhysicsAnimationLayout physicsAnimationLayout = this.mLayout;
        if (physicsAnimationLayout == null) {
            return;
        }
        int indexOfChild = physicsAnimationLayout.indexOfChild(view);
        BubbleStackView.StackViewState state = this.mBubbleStackView.getState();
        BubblePositioner bubblePositioner = this.mPositioner;
        PointF expandedBubbleXY = bubblePositioner.getExpandedBubbleXY(indexOfChild, state);
        float f3 = bubblePositioner.mMaxBubbles - indexOfChild;
        PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(this.mLayout.getChildAt(indexOfChild));
        float f4 = expandedBubbleXY.x;
        float f5 = expandedBubbleXY.y;
        animationForChild.mPositionEndActions = new Runnable[0];
        animationForChild.mPathAnimator = null;
        DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
        animationForChild.property(anonymousClass1, f4, new Runnable[0]);
        animationForChild.translationY(f5, new Runnable[0]);
        animationForChild.mPathAnimator = null;
        animationForChild.property(DynamicAnimation.TRANSLATION_Z, f3, new Runnable[0]);
        animationForChild.mPositionStartVelocities.put(anonymousClass1, Float.valueOf(f));
        animationForChild.mPositionStartVelocities.put(DynamicAnimation.TRANSLATION_Y, Float.valueOf(f2));
        animationForChild.start(new Runnable[0]);
        this.mMagnetizedBubbleDraggingOut = null;
        updateBubblePositions();
    }

    public final void startOrUpdatePathAnimation(final boolean z) {
        ExpandedAnimationController$$ExternalSyntheticLambda0 expandedAnimationController$$ExternalSyntheticLambda0 = z ? new ExpandedAnimationController$$ExternalSyntheticLambda0(this, 1) : new ExpandedAnimationController$$ExternalSyntheticLambda0(this, 2);
        final boolean showBubblesVertically = this.mPositioner.showBubblesVertically();
        final boolean z2 = this.mLayout.getContext().getResources().getConfiguration().getLayoutDirection() == 1;
        animationsForChildrenFromIndex(this.mFadeBubblesDuringCollapse, new PhysicsAnimationLayout.PhysicsAnimationController.ChildAnimationConfigurator() { // from class: com.android.wm.shell.bubbles.animation.ExpandedAnimationController$$ExternalSyntheticLambda4
            @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController.ChildAnimationConfigurator
            public final void configureAnimationForChildAtIndex(int i, PhysicsAnimationLayout.PhysicsPropertyAnimator physicsPropertyAnimator) {
                float min;
                ExpandedAnimationController expandedAnimationController = ExpandedAnimationController.this;
                View childAt = expandedAnimationController.mLayout.getChildAt(i);
                Path path = new Path();
                path.moveTo(childAt.getTranslationX(), childAt.getTranslationY());
                PointF expandedBubbleXY = expandedAnimationController.mPositioner.getExpandedBubbleXY(i, expandedAnimationController.mBubbleStackView.getState());
                boolean z3 = z;
                if (z3) {
                    path.lineTo(childAt.getTranslationX(), expandedBubbleXY.y);
                    path.lineTo(expandedBubbleXY.x, expandedBubbleXY.y);
                } else {
                    float f = expandedAnimationController.mCollapsePoint.x;
                    path.lineTo(f, expandedBubbleXY.y);
                    if (childAt instanceof BadgedImageView) {
                        BubbleViewProvider bubbleViewProvider = ((BadgedImageView) childAt).mBubble;
                        if ("Overflow".equals(bubbleViewProvider != null ? bubbleViewProvider.getKey() : null)) {
                            min = 0.0f;
                            path.lineTo(f, expandedAnimationController.mCollapsePoint.y + min);
                        }
                    }
                    min = Math.min(i, 1) * expandedAnimationController.mStackOffsetPx;
                    path.lineTo(f, expandedAnimationController.mCollapsePoint.y + min);
                }
                boolean z4 = showBubblesVertically || !z2 ? !((!z3 || expandedAnimationController.mLayout.isFirstChildXLeftOfCenter(childAt.getTranslationX())) && (z3 || !expandedAnimationController.mLayout.isFirstChildXLeftOfCenter(expandedAnimationController.mCollapsePoint.x))) : !(!(z3 && expandedAnimationController.mLayout.isFirstChildXLeftOfCenter(childAt.getTranslationX())) && (z3 || expandedAnimationController.mLayout.isFirstChildXLeftOfCenter(expandedAnimationController.mCollapsePoint.x)));
                int childCount = z4 ? i * 10 : (expandedAnimationController.mLayout.getChildCount() - i) * 10;
                if ((!z4 || i != 0) && !z4) {
                    expandedAnimationController.mLayout.getChildCount();
                }
                Interpolator interpolator = z3 ? Interpolators.EMPHASIZED_ACCELERATE : Interpolators.EMPHASIZED_DECELERATE;
                Runnable[] runnableArr = {null, new ExpandedAnimationController$$ExternalSyntheticLambda0(expandedAnimationController, 0)};
                ObjectAnimator objectAnimator = physicsPropertyAnimator.mPathAnimator;
                if (objectAnimator != null) {
                    objectAnimator.cancel();
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(physicsPropertyAnimator, physicsPropertyAnimator.mCurrentPointOnPathXProperty, physicsPropertyAnimator.mCurrentPointOnPathYProperty, path);
                physicsPropertyAnimator.mPathAnimator = ofFloat;
                ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsPropertyAnimator.3
                    public final /* synthetic */ Runnable[] val$pathAnimEndActions;

                    public AnonymousClass3(Runnable[] runnableArr2) {
                        r1 = runnableArr2;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        for (Runnable runnable : r1) {
                            if (runnable != null) {
                                runnable.run();
                            }
                        }
                    }
                });
                physicsPropertyAnimator.mPathAnimator.setDuration(175);
                physicsPropertyAnimator.mPathAnimator.setInterpolator(interpolator);
                Map map = physicsPropertyAnimator.mAnimatedProperties;
                DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
                map.remove(anonymousClass1);
                Map map2 = physicsPropertyAnimator.mAnimatedProperties;
                DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_Y;
                map2.remove(anonymousClass12);
                Map map3 = physicsPropertyAnimator.mAnimatedProperties;
                DynamicAnimation.AnonymousClass1 anonymousClass13 = DynamicAnimation.TRANSLATION_Z;
                map3.remove(anonymousClass13);
                physicsPropertyAnimator.mInitialPropertyValues.remove(anonymousClass1);
                physicsPropertyAnimator.mInitialPropertyValues.remove(anonymousClass12);
                physicsPropertyAnimator.mInitialPropertyValues.remove(anonymousClass13);
                PhysicsAnimationLayout physicsAnimationLayout = PhysicsAnimationLayout.this;
                physicsAnimationLayout.mEndActionForProperty.remove(anonymousClass1);
                physicsAnimationLayout.mEndActionForProperty.remove(anonymousClass12);
                physicsAnimationLayout.mEndActionForProperty.remove(anonymousClass13);
                physicsPropertyAnimator.mStartDelay = childCount;
                physicsPropertyAnimator.mStiffness = 400.0f;
            }
        }).startAll(new Runnable[]{expandedAnimationController$$ExternalSyntheticLambda0});
    }

    public final void updateBubblePositions() {
        if (this.mAnimatingExpand || this.mAnimatingCollapse) {
            return;
        }
        for (int i = 0; i < this.mLayout.getChildCount(); i++) {
            View childAt = this.mLayout.getChildAt(i);
            AnonymousClass1 anonymousClass1 = this.mMagnetizedBubbleDraggingOut;
            if (childAt.equals(anonymousClass1 == null ? null : (View) anonymousClass1.underlyingObject)) {
                return;
            }
            PointF expandedBubbleXY = this.mPositioner.getExpandedBubbleXY(i, this.mBubbleStackView.getState());
            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = animationForChild(childAt);
            animationForChild.mPathAnimator = null;
            animationForChild.property(DynamicAnimation.TRANSLATION_X, expandedBubbleXY.x, new Runnable[0]);
            animationForChild.translationY(expandedBubbleXY.y, new Runnable[0]);
            animationForChild.start(new Runnable[0]);
        }
    }

    public final void updateResources() {
        if (this.mLayout == null) {
            return;
        }
        this.mStackOffsetPx = r0.getContext().getResources().getDimensionPixelSize(R.dimen.bubble_stack_offset);
        this.mBubbleSizePx = this.mPositioner.mBubbleSize;
    }
}
