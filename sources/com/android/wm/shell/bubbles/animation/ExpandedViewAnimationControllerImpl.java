package com.android.wm.shell.bubbles.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.view.ViewConfiguration;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.animation.FlingAnimationUtils;
import com.android.wm.shell.bubbles.BubbleExpandedView;
import com.android.wm.shell.bubbles.BubblePositioner;
import com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda2;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.taskview.TaskView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExpandedViewAnimationControllerImpl {
    public static final AnonymousClass1 COLLAPSE_HEIGHT_PROPERTY = new AnonymousClass1();
    public SpringAnimation mBackToExpandedAnimation;
    public ObjectAnimator mBottomClipAnim;
    public AnimatorSet mCollapseAnimation;
    public float mCollapsedAmount;
    public int mDraggedAmount;
    public BubbleExpandedView mExpandedView;
    public final FlingAnimationUtils mFlingAnimationUtils;
    public final int mMinFlingVelocity;
    public boolean mNotifiedAboutThreshold;
    public final BubblePositioner mPositioner;
    public float mSwipeDownVelocity;
    public float mSwipeUpVelocity;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.animation.ExpandedViewAnimationControllerImpl$1, reason: invalid class name */
    public final class AnonymousClass1 extends FloatPropertyCompat {
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public final float getValue(Object obj) {
            return ((ExpandedViewAnimationControllerImpl) obj).mCollapsedAmount;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public final void setValue(Object obj, float f) {
            ((ExpandedViewAnimationControllerImpl) obj).setCollapsedAmount(f);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.animation.ExpandedViewAnimationControllerImpl$3, reason: invalid class name */
    public final class AnonymousClass3 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass3(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    ((ExpandedViewAnimationControllerImpl) this.this$0).mBottomClipAnim = null;
                    break;
                default:
                    ((BubbleStackView$$ExternalSyntheticLambda2) this.this$0).run();
                    break;
            }
        }
    }

    public ExpandedViewAnimationControllerImpl(Context context, BubblePositioner bubblePositioner) {
        this.mFlingAnimationUtils = new FlingAnimationUtils(context.getResources().getDisplayMetrics(), 0.25f);
        this.mMinFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        this.mPositioner = bubblePositioner;
    }

    public final void animateBackToExpanded() {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 1719283693982530888L, 0, null);
        }
        final BubbleExpandedView bubbleExpandedView = this.mExpandedView;
        if (bubbleExpandedView == null) {
            return;
        }
        bubbleExpandedView.mIsAnimating = true;
        SpringAnimation springAnimation = new SpringAnimation(this, COLLAPSE_HEIGHT_PROPERTY);
        this.mBackToExpandedAnimation = springAnimation;
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(200.0f);
        springForce.setDampingRatio(0.75f);
        springAnimation.mSpring = springForce;
        this.mBackToExpandedAnimation.addEndListener(new OneTimeEndListener() { // from class: com.android.wm.shell.bubbles.animation.ExpandedViewAnimationControllerImpl.2
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                dynamicAnimation.removeEndListener(this);
                ExpandedViewAnimationControllerImpl expandedViewAnimationControllerImpl = ExpandedViewAnimationControllerImpl.this;
                expandedViewAnimationControllerImpl.mNotifiedAboutThreshold = false;
                expandedViewAnimationControllerImpl.mBackToExpandedAnimation = null;
                bubbleExpandedView.setAnimating(false);
            }
        });
        SpringAnimation springAnimation2 = this.mBackToExpandedAnimation;
        springAnimation2.mValue = this.mCollapsedAmount;
        springAnimation2.mStartValueIsSet = true;
        springAnimation2.animateToFinalPosition(0.0f);
    }

    public final void animateForImeVisibilityChange(boolean z) {
        int i;
        if (this.mExpandedView != null) {
            ObjectAnimator objectAnimator = this.mBottomClipAnim;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            int i2 = 0;
            if (z) {
                BubbleExpandedView bubbleExpandedView = this.mExpandedView;
                bubbleExpandedView.getClass();
                Rect rect = new Rect();
                if (bubbleExpandedView.mIsOverflow) {
                    bubbleExpandedView.mOverflowView.getBoundsOnScreen(rect);
                }
                TaskView taskView = bubbleExpandedView.mTaskView;
                if (taskView != null) {
                    taskView.getBoundsOnScreen(rect);
                }
                int i3 = rect.bottom;
                BubblePositioner bubblePositioner = this.mPositioner;
                boolean z2 = bubblePositioner.mImeVisible;
                if (z2) {
                    i = (bubblePositioner.mScreenRect.bottom - (z2 ? bubblePositioner.mImeHeight : 0)) - bubblePositioner.mInsets.bottom;
                } else {
                    i = 0;
                }
                i2 = Math.max(i3 - i, 0);
            }
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this.mExpandedView, BubbleExpandedView.BOTTOM_CLIP_PROPERTY, i2);
            this.mBottomClipAnim = ofInt;
            ofInt.addListener(new AnonymousClass3(0, this));
            this.mBottomClipAnim.start();
        }
    }

    public final void reset() {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 6527960190166384277L, 0, null);
        }
        BubbleExpandedView bubbleExpandedView = this.mExpandedView;
        if (bubbleExpandedView == null) {
            return;
        }
        bubbleExpandedView.setAnimating(false);
        AnimatorSet animatorSet = this.mCollapseAnimation;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        SpringAnimation springAnimation = this.mBackToExpandedAnimation;
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        this.mExpandedView.setContentAlpha(1.0f);
        BubbleExpandedView bubbleExpandedView2 = this.mExpandedView;
        bubbleExpandedView2.mPointerView.setAlpha(1.0f);
        bubbleExpandedView2.setAlpha(1.0f);
        this.mExpandedView.mManageButton.setAlpha(1.0f);
        setCollapsedAmount(0.0f);
        BubbleExpandedView bubbleExpandedView3 = this.mExpandedView;
        bubbleExpandedView3.mBottomClip = 0;
        bubbleExpandedView3.onContainerClipUpdate();
        this.mExpandedView.movePointerBy(0.0f);
        this.mCollapsedAmount = 0.0f;
        this.mDraggedAmount = 0;
        this.mSwipeUpVelocity = 0.0f;
        this.mSwipeDownVelocity = 0.0f;
        this.mNotifiedAboutThreshold = false;
    }

    public final void setCollapsedAmount(float f) {
        ShapeDrawable shapeDrawable;
        float f2 = this.mCollapsedAmount;
        if (f2 != f) {
            this.mCollapsedAmount = f;
            BubbleExpandedView bubbleExpandedView = this.mExpandedView;
            if (bubbleExpandedView != null) {
                if (f2 == 0.0f) {
                    bubbleExpandedView.setSurfaceZOrderedOnTop(true);
                    this.mExpandedView.mIsAnimating = true;
                }
                BubbleExpandedView bubbleExpandedView2 = this.mExpandedView;
                bubbleExpandedView2.mTopClip = (int) this.mCollapsedAmount;
                bubbleExpandedView2.onContainerClipUpdate();
                BubbleExpandedView bubbleExpandedView3 = this.mExpandedView;
                float f3 = -this.mCollapsedAmount;
                bubbleExpandedView3.mExpandedViewContainer.setTranslationY(f3);
                if (f3 <= 0.0f && ((shapeDrawable = bubbleExpandedView3.mCurrentPointer) == bubbleExpandedView3.mLeftPointer || shapeDrawable == bubbleExpandedView3.mRightPointer)) {
                    float bottom = ((bubbleExpandedView3.mExpandedViewContainer.getBottom() - bubbleExpandedView3.mBottomClip) - bubbleExpandedView3.mCornerRadius) + f3;
                    float f4 = bubbleExpandedView3.mPointerPos.y + bubbleExpandedView3.mPointerHeight;
                    float f5 = f4 > bottom ? f4 - bottom : 0.0f;
                    if (bubbleExpandedView3.mCurrentPointer == bubbleExpandedView3.mLeftPointer) {
                        bubbleExpandedView3.movePointerBy(f5);
                    } else {
                        bubbleExpandedView3.movePointerBy(-f5);
                    }
                    bubbleExpandedView3.mPointerView.setVisibility(f5 > ((float) bubbleExpandedView3.mPointerWidth) ? 4 : 0);
                }
                this.mExpandedView.mManageButton.setTranslationY(-this.mCollapsedAmount);
                if (this.mCollapsedAmount == 0.0f) {
                    this.mExpandedView.setSurfaceZOrderedOnTop(false);
                    this.mExpandedView.setAnimating(false);
                }
            }
        }
    }
}
