package com.android.wm.shell.bubbles;

import android.graphics.PointF;
import android.widget.FrameLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.bubbles.animation.AnimatableScaleMatrix;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.taskview.TaskView;
import java.util.Collections;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleStackView f$0;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda2(BubbleStackView bubbleStackView, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleStackView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TaskView taskView;
        TaskView taskView2;
        int i = 0;
        int i2 = this.$r8$classId;
        BubbleStackView bubbleStackView = this.f$0;
        switch (i2) {
            case 0:
                bubbleStackView.mExpandedViewContainer.setAnimationMatrix(null);
                bubbleStackView.mIsExpansionAnimating = false;
                bubbleStackView.updateExpandedView();
                bubbleStackView.requestUpdate();
                BubbleExpandedView expandedView = bubbleStackView.getExpandedView();
                if (expandedView != null) {
                    expandedView.setSurfaceZOrderedOnTop(false);
                    break;
                }
                break;
            case 1:
                bubbleStackView.showManageMenu(true);
                break;
            case 2:
                bubbleStackView.animateFlyoutCollapsed(0.0f, true);
                break;
            case 3:
                if (bubbleStackView.mTemporarilyInvisible && bubbleStackView.mFlyout.getVisibility() != 0) {
                    if (!bubbleStackView.mStackAnimationController.isStackOnLeftSide()) {
                        BubblePositioner bubblePositioner = bubbleStackView.mPositioner;
                        bubbleStackView.mBubbleContainer.animate().translationX(bubbleStackView.mBubbleSize - (bubblePositioner.mPositionRect.right - bubblePositioner.mScreenRect.right)).start();
                        break;
                    } else {
                        BubblePositioner bubblePositioner2 = bubbleStackView.mPositioner;
                        bubbleStackView.mBubbleContainer.animate().translationX(-(bubbleStackView.mBubbleSize + (bubblePositioner2.mPositionRect.left - bubblePositioner2.mScreenRect.left))).start();
                        break;
                    }
                } else {
                    bubbleStackView.mBubbleContainer.animate().translationX(0.0f).start();
                    break;
                }
                break;
            case 4:
                if (bubbleStackView.mFlyout.getVisibility() != 0 && !bubbleStackView.mIsDraggingStack && !bubbleStackView.mIsExpansionAnimating && !bubbleStackView.mIsExpanded && !bubbleStackView.isStackEduVisible()) {
                    float f = bubbleStackView.mBubbleSize;
                    float f2 = f - (0.55f * f);
                    if (!bubbleStackView.mStackAnimationController.isStackOnLeftSide()) {
                        BubblePositioner bubblePositioner3 = bubbleStackView.mPositioner;
                        bubbleStackView.mBubbleContainer.animate().translationX(f2 - (bubblePositioner3.mPositionRect.right - bubblePositioner3.mScreenRect.right)).start();
                        break;
                    } else {
                        BubblePositioner bubblePositioner4 = bubbleStackView.mPositioner;
                        bubbleStackView.mBubbleContainer.animate().translationX(-(f2 + (bubblePositioner4.mPositionRect.left - bubblePositioner4.mScreenRect.left))).start();
                        break;
                    }
                }
                break;
            case 5:
                if (bubbleStackView.getBubbleCount() == 0) {
                    bubbleStackView.mExpandedViewTemporarilyHidden = false;
                    BubbleController bubbleController = bubbleStackView.mManager.$controller;
                    BubbleStackView bubbleStackView2 = bubbleController.mStackView;
                    if (bubbleStackView2 == null) {
                        BubbleBarLayerView bubbleBarLayerView = bubbleController.mLayerView;
                        if (bubbleBarLayerView != null) {
                            bubbleBarLayerView.setVisibility(4);
                            bubbleController.removeFromWindowManagerMaybe();
                            break;
                        }
                    } else {
                        bubbleStackView2.setVisibility(4);
                        bubbleController.removeFromWindowManagerMaybe();
                        break;
                    }
                }
                break;
            case 6:
                bubbleStackView.animateShadows();
                break;
            case 7:
                bubbleStackView.showManageMenu(true);
                break;
            case 8:
                bubbleStackView.mManageMenu.getChildAt(0).requestAccessibilityFocus();
                BubbleExpandedView expandedView2 = bubbleStackView.getExpandedView();
                if (expandedView2 != null && (taskView = expandedView2.mTaskView) != null) {
                    taskView.onLocationChanged();
                    break;
                }
                break;
            case 9:
                bubbleStackView.mManageMenu.setVisibility(4);
                BubbleExpandedView expandedView3 = bubbleStackView.getExpandedView();
                if (expandedView3 != null && (taskView2 = expandedView3.mTaskView) != null) {
                    taskView2.onLocationChanged();
                    break;
                }
                break;
            case 10:
                bubbleStackView.showManageMenu(true);
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView, 12);
                bubbleStackView.mAnimateInFlyout = bubbleStackView$$ExternalSyntheticLambda2;
                bubbleStackView.mFlyout.postDelayed(bubbleStackView$$ExternalSyntheticLambda2, 200L);
                break;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                bubbleStackView.mFlyout.setVisibility(0);
                bubbleStackView.updateTemporarilyInvisibleAnimation(false);
                bubbleStackView.mFlyoutDragDeltaX = bubbleStackView.mStackAnimationController.isStackOnLeftSide() ? -bubbleStackView.mFlyout.getWidth() : bubbleStackView.mFlyout.getWidth();
                bubbleStackView.animateFlyoutCollapsed(0.0f, false);
                bubbleStackView.mFlyout.postDelayed(bubbleStackView.mHideFlyout, 5000L);
                break;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                bubbleStackView.getClass();
                bubbleStackView.post(new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView, 17));
                break;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                bubbleStackView.mBubbleContainer.setActiveController(bubbleStackView.mStackAnimationController);
                bubbleStackView.updateOverflowVisibility();
                bubbleStackView.animateShadows();
                break;
            case 15:
                BubbleViewProvider bubbleViewProvider = bubbleStackView.mExpandedBubble;
                bubbleStackView.mIsExpansionAnimating = true;
                bubbleStackView.hideFlyoutImmediate();
                bubbleStackView.updateExpandedBubble();
                bubbleStackView.updateExpandedView();
                ManageEducationView manageEducationView = bubbleStackView.mManageEduView;
                if (manageEducationView != null) {
                    manageEducationView.hide();
                }
                bubbleStackView.updateBadges(true);
                bubbleStackView.mIsExpansionAnimating = false;
                bubbleStackView.updateExpandedView();
                bubbleStackView.requestUpdate();
                if (bubbleViewProvider != null) {
                    bubbleViewProvider.setTaskViewVisibility();
                }
                bubbleStackView.mExpandedViewAnimationController.reset();
                break;
            case 16:
                BubbleStackView.$r8$lambda$IqtEhCPUiJmY5XbDTuX9Ey7r4cM(bubbleStackView);
                break;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                if (!bubbleStackView.mIsExpanded) {
                    bubbleStackView.mIsBubbleSwitchAnimating = false;
                    break;
                } else {
                    FrameLayout frameLayout = bubbleStackView.mAnimatingOutSurfaceContainer;
                    Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                    PhysicsAnimator.Companion.getInstance(frameLayout).cancel();
                    bubbleStackView.mAnimatingOutSurfaceAlphaAnimator.reverse();
                    bubbleStackView.mExpandedViewAlphaAnimator.start();
                    BubbleViewProvider bubbleViewProvider2 = bubbleStackView.mExpandedBubble;
                    if (bubbleViewProvider2 != null && ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -2359996827066879853L, 0, String.valueOf(bubbleViewProvider2.getKey()));
                    }
                    if (bubbleStackView.mPositioner.showBubblesVertically()) {
                        float translationX = bubbleStackView.mStackAnimationController.isStackOnLeftSide() ? bubbleStackView.mAnimatingOutSurfaceContainer.getTranslationX() + (bubbleStackView.mBubbleSize * 2) : bubbleStackView.mAnimatingOutSurfaceContainer.getTranslationX();
                        PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(bubbleStackView.mAnimatingOutSurfaceContainer);
                        companion.spring(DynamicAnimation.TRANSLATION_X, translationX, 0.0f, bubbleStackView.mTranslateSpringConfig);
                        companion.start();
                    } else {
                        PhysicsAnimator companion2 = PhysicsAnimator.Companion.getInstance(bubbleStackView.mAnimatingOutSurfaceContainer);
                        companion2.spring(DynamicAnimation.TRANSLATION_Y, bubbleStackView.mAnimatingOutSurfaceContainer.getTranslationY() - bubbleStackView.mBubbleSize, 0.0f, bubbleStackView.mTranslateSpringConfig);
                        companion2.start();
                    }
                    BubbleViewProvider bubbleViewProvider3 = bubbleStackView.mExpandedBubble;
                    PointF expandedBubbleXY = bubbleStackView.mPositioner.getExpandedBubbleXY(bubbleViewProvider3 != null && bubbleViewProvider3.getKey().equals("Overflow") ? bubbleStackView.mBubbleContainer.getChildCount() - 1 : Collections.unmodifiableList(bubbleStackView.mBubbleData.mBubbles).indexOf(bubbleStackView.mExpandedBubble), bubbleStackView.getState());
                    bubbleStackView.mExpandedViewContainer.setAlpha(1.0f);
                    bubbleStackView.mExpandedViewContainer.setVisibility(0);
                    if (bubbleStackView.mPositioner.showBubblesVertically()) {
                        float f3 = expandedBubbleXY.y;
                        float f4 = bubbleStackView.mBubbleSize;
                        bubbleStackView.mExpandedViewContainerMatrix.setScale(0.9f, 0.9f, bubbleStackView.mStackOnLeftOrWillBe ? expandedBubbleXY.x + f4 + bubbleStackView.mExpandedViewPadding : expandedBubbleXY.x - bubbleStackView.mExpandedViewPadding, (f4 / 2.0f) + f3);
                    } else {
                        AnimatableScaleMatrix animatableScaleMatrix = bubbleStackView.mExpandedViewContainerMatrix;
                        float f5 = expandedBubbleXY.x;
                        float f6 = bubbleStackView.mBubbleSize;
                        animatableScaleMatrix.setScale(0.9f, 0.9f, (f6 / 2.0f) + f5, expandedBubbleXY.y + f6 + bubbleStackView.mExpandedViewPadding);
                    }
                    bubbleStackView.mExpandedViewContainer.setAnimationMatrix(bubbleStackView.mExpandedViewContainerMatrix);
                    ((HandlerExecutor) bubbleStackView.mMainExecutor).executeDelayed(new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView, 18), 25L);
                    break;
                }
                break;
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                if (!bubbleStackView.mIsExpanded) {
                    bubbleStackView.mIsBubbleSwitchAnimating = false;
                    break;
                } else {
                    AnimatableScaleMatrix animatableScaleMatrix2 = bubbleStackView.mExpandedViewContainerMatrix;
                    Function2 function22 = PhysicsAnimator.onAnimatorCreated;
                    PhysicsAnimator.Companion.getInstance(animatableScaleMatrix2).cancel();
                    PhysicsAnimator companion3 = PhysicsAnimator.Companion.getInstance(bubbleStackView.mExpandedViewContainerMatrix);
                    companion3.spring(AnimatableScaleMatrix.SCALE_X, 499.99997f, 0.0f, bubbleStackView.mScaleInSpringConfig);
                    companion3.spring(AnimatableScaleMatrix.SCALE_Y, 499.99997f, 0.0f, bubbleStackView.mScaleInSpringConfig);
                    companion3.updateListeners.add(new BubbleStackView$$ExternalSyntheticLambda8(bubbleStackView, i));
                    companion3.withEndActions(new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView, 19));
                    companion3.start();
                    break;
                }
            default:
                bubbleStackView.mExpandedViewTemporarilyHidden = false;
                bubbleStackView.mIsBubbleSwitchAnimating = false;
                bubbleStackView.mExpandedViewContainer.setAnimationMatrix(null);
                BubbleExpandedView expandedView4 = bubbleStackView.getExpandedView();
                if (expandedView4 != null) {
                    expandedView4.setSurfaceZOrderedOnTop(false);
                    expandedView4.setAnimating(false);
                    break;
                }
                break;
        }
    }
}
