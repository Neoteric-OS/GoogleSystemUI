package com.android.wm.shell.bubbles.bar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;
import android.widget.FrameLayout;
import com.android.wm.shell.bubbles.BubblePositioner;
import com.android.wm.shell.bubbles.BubbleViewProvider;
import com.android.wm.shell.bubbles.animation.AnimatableScaleMatrix;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.taskview.TaskView;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleBarAnimationHelper {
    public BubbleViewProvider mExpandedBubble;
    public final ValueAnimator mExpandedViewAlphaAnimator;
    public boolean mIsExpanded;
    public final BubblePositioner mPositioner;
    public Animator mRunningDragAnimator;
    public final int[] mTmpLocation;
    public final PhysicsAnimator.SpringConfig mScaleInSpringConfig = new PhysicsAnimator.SpringConfig(300.0f, 0.9f);
    public final PhysicsAnimator.SpringConfig mScaleOutSpringConfig = new PhysicsAnimator.SpringConfig(900.0f, 1.0f);
    public final AnimatableScaleMatrix mExpandedViewContainerMatrix = new AnimatableScaleMatrix();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.bar.BubbleBarAnimationHelper$2, reason: invalid class name */
    public final class AnonymousClass2 extends DragAnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object val$bbev;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass2(BubbleBarAnimationHelper bubbleBarAnimationHelper, BubbleBarExpandedView bubbleBarExpandedView, Object obj, int i) {
            super(bubbleBarExpandedView);
            this.$r8$classId = i;
            this.val$bbev = obj;
        }

        @Override // com.android.wm.shell.bubbles.bar.BubbleBarAnimationHelper.DragAnimatorListenerAdapter, android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    super.onAnimationEnd(animator);
                    ((BubbleBarExpandedView) this.val$bbev).resetPivot();
                    BubbleBarExpandedView bubbleBarExpandedView = (BubbleBarExpandedView) this.val$bbev;
                    if (bubbleBarExpandedView.mIsDragging) {
                        bubbleBarExpandedView.mIsDragging = false;
                        bubbleBarExpandedView.updateSamplingState();
                        break;
                    }
                    break;
                default:
                    super.onAnimationEnd(animator);
                    BubbleBarExpandedViewDragController$sam$java_lang_Runnable$0 bubbleBarExpandedViewDragController$sam$java_lang_Runnable$0 = (BubbleBarExpandedViewDragController$sam$java_lang_Runnable$0) this.val$bbev;
                    if (bubbleBarExpandedViewDragController$sam$java_lang_Runnable$0 != null) {
                        bubbleBarExpandedViewDragController$sam$java_lang_Runnable$0.run();
                        break;
                    }
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class DragAnimatorListenerAdapter extends AnimatorListenerAdapter {
        public final BubbleBarExpandedView mBubbleBarExpandedView;

        public DragAnimatorListenerAdapter(BubbleBarExpandedView bubbleBarExpandedView) {
            this.mBubbleBarExpandedView = bubbleBarExpandedView;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.mBubbleBarExpandedView.setAnimating(false);
            BubbleBarAnimationHelper.this.mRunningDragAnimator = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            this.mBubbleBarExpandedView.setAnimating(true);
        }
    }

    public BubbleBarAnimationHelper(BubblePositioner bubblePositioner) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mExpandedViewAlphaAnimator = ofFloat;
        this.mTmpLocation = new int[2];
        this.mIsExpanded = false;
        this.mPositioner = bubblePositioner;
        ofFloat.setDuration(150L);
        ofFloat.setInterpolator(Interpolators.PANEL_CLOSE_ACCELERATED);
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarAnimationHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                TaskView taskView;
                BubbleBarExpandedView expandedView = BubbleBarAnimationHelper.this.getExpandedView();
                if (expandedView != null) {
                    if (BubbleBarAnimationHelper.this.mIsExpanded && (taskView = expandedView.mTaskView) != null) {
                        taskView.setZOrderedOnTop(false, true);
                    }
                    expandedView.setContentVisibility(BubbleBarAnimationHelper.this.mIsExpanded);
                    expandedView.setAnimating(false);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                BubbleBarExpandedView expandedView = BubbleBarAnimationHelper.this.getExpandedView();
                if (expandedView != null) {
                    TaskView taskView = expandedView.mTaskView;
                    if (taskView != null) {
                        taskView.setZOrderedOnTop(true, true);
                    }
                    expandedView.setAnimating(true);
                }
            }
        });
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarAnimationHelper$$ExternalSyntheticLambda2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BubbleBarExpandedView expandedView = BubbleBarAnimationHelper.this.getExpandedView();
                if (expandedView != null) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    TaskView taskView = expandedView.mTaskView;
                    if (taskView != null) {
                        taskView.setAlpha(floatValue);
                    }
                    expandedView.setAlpha(floatValue);
                    expandedView.setAlpha(floatValue);
                }
            }
        });
    }

    public static void setDragPivot(BubbleBarExpandedView bubbleBarExpandedView) {
        bubbleBarExpandedView.setPivotX(bubbleBarExpandedView.getWidth() / 2.0f);
        bubbleBarExpandedView.setPivotY(0.0f);
    }

    public final void cancelAnimations() {
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        PhysicsAnimator.Companion.getInstance(this.mExpandedViewContainerMatrix).cancel();
        this.mExpandedViewAlphaAnimator.cancel();
        BubbleBarExpandedView expandedView = getExpandedView();
        if (expandedView != null) {
            expandedView.animate().cancel();
        }
        Animator animator = this.mRunningDragAnimator;
        if (animator != null) {
            animator.cancel();
            this.mRunningDragAnimator = null;
        }
    }

    public final BubbleBarExpandedView getExpandedView() {
        BubbleViewProvider bubbleViewProvider = this.mExpandedBubble;
        if (bubbleViewProvider != null) {
            return bubbleViewProvider.getBubbleBarExpandedView();
        }
        return null;
    }

    public final Point getExpandedViewRestPosition(Size size) {
        BubblePositioner bubblePositioner = this.mPositioner;
        int i = bubblePositioner.mExpandedViewPadding;
        Point point = new Point();
        if (bubblePositioner.isBubbleBarOnLeft()) {
            point.x = bubblePositioner.mInsets.left + i;
        } else {
            point.x = (bubblePositioner.mPositionRect.width() - size.getWidth()) - i;
        }
        point.y = (bubblePositioner.mBubbleBarTopOnScreen - bubblePositioner.mExpandedViewPadding) - size.getHeight();
        return point;
    }

    public final void updateExpandedView() {
        final BubbleBarExpandedView expandedView = getExpandedView();
        if (expandedView == null) {
            Log.w("BubbleBarAnimationHelper", "Trying to update the expanded view without a bubble");
            return;
        }
        boolean equals = this.mExpandedBubble.getKey().equals("Overflow");
        BubblePositioner bubblePositioner = this.mPositioner;
        Size size = new Size(equals ? bubblePositioner.mOverflowWidth : bubblePositioner.mExpandedViewLargeScreenWidth, bubblePositioner.getExpandedViewHeightForBubbleBar(equals));
        Point expandedViewRestPosition = getExpandedViewRestPosition(size);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) expandedView.getLayoutParams();
        layoutParams.width = size.getWidth();
        layoutParams.height = size.getHeight();
        expandedView.setLayoutParams(layoutParams);
        expandedView.setX(expandedViewRestPosition.x);
        expandedView.setY(expandedViewRestPosition.y);
        TaskView taskView = expandedView.mTaskView;
        if (taskView != null) {
            taskView.onLocationChanged();
        }
        if (expandedView.mOverflowView != null) {
            expandedView.post(new Runnable() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarExpandedView$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    BubbleBarExpandedView.this.mOverflowView.show();
                }
            });
        }
    }
}
