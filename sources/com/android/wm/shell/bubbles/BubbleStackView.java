package com.android.wm.shell.bubbles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Choreographer;
import android.view.InputMonitor;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.ScreenCapture;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.FrameworkStatsLog;
import com.android.systemui.wmshell.BubblesManager;
import com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda9;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BadgedImageView;
import com.android.wm.shell.bubbles.Bubble;
import com.android.wm.shell.bubbles.BubbleLogger;
import com.android.wm.shell.bubbles.BubblePositioner;
import com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda25;
import com.android.wm.shell.bubbles.animation.AnimatableScaleMatrix;
import com.android.wm.shell.bubbles.animation.ExpandedAnimationController;
import com.android.wm.shell.bubbles.animation.ExpandedViewAnimationControllerImpl;
import com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout;
import com.android.wm.shell.bubbles.animation.StackAnimationController;
import com.android.wm.shell.bubbles.animation.StackAnimationController$$ExternalSyntheticLambda2;
import com.android.wm.shell.bubbles.animation.StackAnimationController.StackPositionProperty;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;
import com.android.wm.shell.common.FloatingContentCoordinator;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.bubbles.DismissView;
import com.android.wm.shell.shared.bubbles.RelativeTouchListener;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject$MagneticTarget$updateLocationOnScreen$1;
import com.android.wm.shell.taskview.TaskView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleStackView extends FrameLayout implements ViewTreeObserver.OnComputeInternalInsetsListener {
    static final int FLYOUT_HIDE_AFTER = 5000;
    public BubbleStackView$$ExternalSyntheticLambda27 mAfterFlyoutHidden;
    public final BubbleStackView$$ExternalSyntheticLambda16 mAfterFlyoutTransitionSpring;
    public BubbleStackView$$ExternalSyntheticLambda2 mAnimateInFlyout;
    public final BubbleStackView$$ExternalSyntheticLambda2 mAnimateStashedState;
    public final BubbleStackView$$ExternalSyntheticLambda2 mAnimateTemporarilyInvisibleImmediate;
    public ScreenCapture.ScreenshotHardwareBuffer mAnimatingOutBubbleBuffer;
    public final ValueAnimator mAnimatingOutSurfaceAlphaAnimator;
    public final FrameLayout mAnimatingOutSurfaceContainer;
    public boolean mAnimatingOutSurfaceReady;
    public final SurfaceView mAnimatingOutSurfaceView;
    public final AnonymousClass6 mBubbleClickListener;
    public final PhysicsAnimationLayout mBubbleContainer;
    public final BubbleData mBubbleData;
    public final int mBubbleElevation;
    public final BubbleOverflow mBubbleOverflow;
    public int mBubbleSize;
    public BubbleViewProvider mBubbleToExpandAfterFlyoutCollapse;
    public final AnonymousClass7 mBubbleTouchListener;
    public final int mBubbleTouchPadding;
    public BubblesNavBarGestureTracker mBubblesNavBarGestureTracker;
    public final AnonymousClass7 mContainerSwipeListener;
    public final int mCornerRadius;
    public BubbleStackView$$ExternalSyntheticLambda54 mDelayedAnimation;
    public final ValueAnimator mDismissBubbleAnimator;
    public DismissView mDismissView;
    public BubbleController$$ExternalSyntheticLambda2 mExpandListener;
    public final ExpandedAnimationController mExpandedAnimationController;
    public BubbleViewProvider mExpandedBubble;
    public final ValueAnimator mExpandedViewAlphaAnimator;
    public final ExpandedViewAnimationControllerImpl mExpandedViewAnimationController;
    public final FrameLayout mExpandedViewContainer;
    public final AnimatableScaleMatrix mExpandedViewContainerMatrix;
    public final int mExpandedViewPadding;
    public boolean mExpandedViewTemporarilyHidden;
    public BubbleFlyoutView mFlyout;
    public final AnonymousClass6 mFlyoutClickListener;
    public final AnonymousClass3 mFlyoutCollapseProperty;
    public float mFlyoutDragDeltaX;
    public final AnonymousClass7 mFlyoutTouchListener;
    public final SpringAnimation mFlyoutTransitionSpring;
    public final BubbleStackView$$ExternalSyntheticLambda2 mHideFlyout;
    public final AnonymousClass4 mIndividualBubbleMagnetListener;
    public boolean mIsBubbleSwitchAnimating;
    public boolean mIsDraggingStack;
    public boolean mIsExpanded;
    public boolean mIsExpansionAnimating;
    public boolean mIsGestureInProgress;
    public MagnetizedObject.MagneticTarget mMagneticTarget;
    public MagnetizedObject mMagnetizedObject;
    public final ShellExecutor mMainExecutor;
    public ViewGroup mManageDontBubbleView;
    public ManageEducationView mManageEduView;
    public ViewGroup mManageMenu;
    public final View mManageMenuScrim;
    public ImageView mManageSettingsIcon;
    public TextView mManageSettingsText;
    public ViewGroup mManageSettingsView;
    public final PhysicsAnimator.SpringConfig mManageSpringConfig;
    public final BubbleStackViewManager$Companion$fromBubbleController$1 mManager;
    public final BubbleStackView$$ExternalSyntheticLambda22 mOrientationChangedListener;
    public int mPointerIndexDown;
    public final BubblePositioner mPositioner;
    public RelativeStackPosition mRelativeStackPositionBeforeRotation;
    public boolean mRemovingLastBubbleWhileExpanded;
    public final PhysicsAnimator.SpringConfig mScaleInSpringConfig;
    public final PhysicsAnimator.SpringConfig mScaleOutSpringConfig;
    public final View mScrim;
    public ViewPropertyAnimator mScrimAnimation;
    public boolean mSensitiveNotificationProtectionActive;
    public boolean mShouldReorderBubblesAfterGestureCompletes;
    public boolean mShowedUserEducationInTouchListenerActive;
    public boolean mShowingManage;
    public final boolean mShowingOverflow;
    public final StackAnimationController mStackAnimationController;
    public StackEducationView mStackEduView;
    public BubbleStackView$$ExternalSyntheticLambda36 mStackEducationViewManager;
    public final AnonymousClass4 mStackMagnetListener;
    public boolean mStackOnLeftOrWillBe;
    public final StackViewState mStackViewState;
    public final AnonymousClass1 mSurfaceSynchronizer;
    public final AnonymousClass4 mSwipeUpListener;
    public final BubbleStackView$$ExternalSyntheticLambda15 mSystemGestureExcludeUpdater;
    public final List mSystemGestureExclusionRects;
    public final BubbleController mSysuiProxyProvider;
    public final Rect mTempRect;
    public boolean mTemporarilyInvisible;
    public final PhysicsAnimator.SpringConfig mTranslateSpringConfig;
    public BubbleController$$ExternalSyntheticLambda14 mUnbubbleConversationCallback;
    public View mViewBeingDismissed;
    public boolean mViewUpdatedRequested;
    public final AnonymousClass2 mViewUpdater;
    public static final PhysicsAnimator.SpringConfig FLYOUT_IME_ANIMATION_SPRING_CONFIG = new PhysicsAnimator.SpringConfig(200.0f, 0.9f);
    public static final AnonymousClass1 DEFAULT_SURFACE_SYNCHRONIZER = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleStackView$1, reason: invalid class name */
    public final class AnonymousClass1 {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.wm.shell.bubbles.BubbleStackView$1$1, reason: invalid class name and collision with other inner class name */
        public final class ChoreographerFrameCallbackC02691 implements Choreographer.FrameCallback {
            public int mFrameWait = 2;
            public final /* synthetic */ Runnable val$callback;

            public ChoreographerFrameCallbackC02691(Runnable runnable) {
                this.val$callback = runnable;
            }

            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                int i = this.mFrameWait - 1;
                this.mFrameWait = i;
                if (i > 0) {
                    Choreographer.getInstance().postFrameCallback(this);
                } else {
                    this.val$callback.run();
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleStackView$13, reason: invalid class name */
    public final class AnonymousClass13 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ BubbleStackView this$0;

        public /* synthetic */ AnonymousClass13(BubbleStackView bubbleStackView, int i) {
            this.$r8$classId = i;
            this.this$0 = bubbleStackView;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            switch (this.$r8$classId) {
                case 2:
                    super.onAnimationCancel(animator);
                    BubbleStackView.m900$$Nest$mresetDismissAnimator(this.this$0);
                    break;
                default:
                    super.onAnimationCancel(animator);
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    BubbleExpandedView expandedView = this.this$0.getExpandedView();
                    if (expandedView != null && !this.this$0.mExpandedViewTemporarilyHidden) {
                        expandedView.setSurfaceZOrderedOnTop(false);
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 8146331188896153203L, 0, String.valueOf(expandedView.getBubbleKey()));
                        }
                        expandedView.setAnimating(false);
                        break;
                    }
                    break;
                case 1:
                    this.this$0.releaseAnimatingOutBubbleBuffer();
                    break;
                default:
                    super.onAnimationEnd(animator);
                    BubbleStackView.m900$$Nest$mresetDismissAnimator(this.this$0);
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    BubbleExpandedView expandedView = this.this$0.getExpandedView();
                    if (expandedView != null) {
                        expandedView.setSurfaceZOrderedOnTop(true);
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 1990180620272481653L, 0, String.valueOf(expandedView.getBubbleKey()));
                        }
                        expandedView.mIsAnimating = true;
                        this.this$0.mExpandedViewContainer.setVisibility(0);
                        break;
                    }
                    break;
                default:
                    super.onAnimationStart(animator);
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleStackView$4, reason: invalid class name */
    public final class AnonymousClass4 implements MagnetizedObject.MagnetListener {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ BubbleStackView this$0;

        public /* synthetic */ AnonymousClass4(BubbleStackView bubbleStackView, int i) {
            this.$r8$classId = i;
            this.this$0 = bubbleStackView;
        }

        public void onMove(float f) {
            int round;
            BubbleExpandedView bubbleExpandedView;
            BubbleStackView bubbleStackView = this.this$0;
            if (bubbleStackView.isManageEduVisible() || bubbleStackView.isStackEduVisible()) {
                return;
            }
            if (bubbleStackView.mShowingManage) {
                bubbleStackView.showManageMenu(false);
            }
            float f2 = -Math.min(f, 0.0f);
            ExpandedViewAnimationControllerImpl expandedViewAnimationControllerImpl = bubbleStackView.mExpandedViewAnimationController;
            float f3 = (int) f2;
            BubbleExpandedView bubbleExpandedView2 = expandedViewAnimationControllerImpl.mExpandedView;
            if (bubbleExpandedView2 != null) {
                int contentHeight = bubbleExpandedView2.getContentHeight();
                if (Float.compare(f3, 0.0f) == 0) {
                    round = 0;
                } else {
                    float f4 = contentHeight;
                    float f5 = f3 / f4;
                    float abs = f5 / Math.abs(f5);
                    float abs2 = Math.abs(f5) - 1.0f;
                    float f6 = ((abs2 * abs2 * abs2) + 1.0f) * abs;
                    if (Math.abs(f6) >= 1.0f) {
                        f6 /= Math.abs(f6);
                    }
                    round = Math.round(f6 * 0.07f * f4);
                }
                expandedViewAnimationControllerImpl.mDraggedAmount = round;
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -8974127699338313944L, 6, Double.valueOf(f3), Long.valueOf(round));
                }
                expandedViewAnimationControllerImpl.setCollapsedAmount(expandedViewAnimationControllerImpl.mDraggedAmount);
                if (!expandedViewAnimationControllerImpl.mNotifiedAboutThreshold && (bubbleExpandedView = expandedViewAnimationControllerImpl.mExpandedView) != null && expandedViewAnimationControllerImpl.mDraggedAmount > bubbleExpandedView.getContentHeight() * 0.02f) {
                    expandedViewAnimationControllerImpl.mNotifiedAboutThreshold = true;
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -287698628829432196L, 0, null);
                    }
                    BubbleExpandedView bubbleExpandedView3 = expandedViewAnimationControllerImpl.mExpandedView;
                    if (bubbleExpandedView3 != null) {
                        bubbleExpandedView3.performHapticFeedback(11);
                    }
                }
            }
            if (bubbleStackView.mScrimAnimation == null) {
                bubbleStackView.mScrim.setAlpha(bubbleStackView.getExpandedView() != null ? Math.max(0.32f - ((f2 / r8.getContentHeight()) * 0.11999999f), 0.2f) : 0.32f);
            }
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
        public void onReleasedInTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject) {
            switch (this.$r8$classId) {
                case 0:
                    Object obj = magnetizedObject.underlyingObject;
                    boolean z = obj instanceof View;
                    BubbleStackView bubbleStackView = this.this$0;
                    if (z) {
                        View view = (View) obj;
                        ExpandedAnimationController expandedAnimationController = bubbleStackView.mExpandedAnimationController;
                        float height = bubbleStackView.mDismissView.getHeight();
                        BubbleStackView$$ExternalSyntheticLambda25 bubbleStackView$$ExternalSyntheticLambda25 = new BubbleStackView$$ExternalSyntheticLambda25(2, this, view);
                        if (view == null) {
                            expandedAnimationController.getClass();
                        } else {
                            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = expandedAnimationController.animationForChild(view);
                            animationForChild.mStiffness = 10000.0f;
                            animationForChild.property(DynamicAnimation.SCALE_X, 0.0f, new Runnable[0]);
                            animationForChild.property(DynamicAnimation.SCALE_Y, 0.0f, new Runnable[0]);
                            animationForChild.translationY(view.getTranslationY() + height, new Runnable[0]);
                            animationForChild.property(DynamicAnimation.ALPHA, 0.0f, bubbleStackView$$ExternalSyntheticLambda25);
                            animationForChild.start(new Runnable[0]);
                            expandedAnimationController.updateBubblePositions();
                        }
                    }
                    bubbleStackView.mDismissView.hide();
                    break;
                default:
                    BubbleStackView bubbleStackView2 = this.this$0;
                    final StackAnimationController stackAnimationController = bubbleStackView2.mStackAnimationController;
                    final float height2 = bubbleStackView2.mDismissView.getHeight();
                    BubbleStackView$$ExternalSyntheticLambda7 bubbleStackView$$ExternalSyntheticLambda7 = new BubbleStackView$$ExternalSyntheticLambda7(1, this);
                    stackAnimationController.getClass();
                    stackAnimationController.animationsForChildrenFromIndex(false, new PhysicsAnimationLayout.PhysicsAnimationController.ChildAnimationConfigurator() { // from class: com.android.wm.shell.bubbles.animation.StackAnimationController$$ExternalSyntheticLambda4
                        @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController.ChildAnimationConfigurator
                        public final void configureAnimationForChildAtIndex(int i, PhysicsAnimationLayout.PhysicsPropertyAnimator physicsPropertyAnimator) {
                            StackAnimationController stackAnimationController2 = StackAnimationController.this;
                            stackAnimationController2.getClass();
                            physicsPropertyAnimator.property(DynamicAnimation.SCALE_X, 0.0f, new Runnable[0]);
                            physicsPropertyAnimator.property(DynamicAnimation.SCALE_Y, 0.0f, new Runnable[0]);
                            physicsPropertyAnimator.property(DynamicAnimation.ALPHA, 0.0f, new Runnable[0]);
                            physicsPropertyAnimator.translationY(stackAnimationController2.mLayout.getChildAt(i).getTranslationY() + height2, new Runnable[0]);
                            physicsPropertyAnimator.mStiffness = 10000.0f;
                        }
                    }).startAll(new Runnable[]{bubbleStackView$$ExternalSyntheticLambda7});
                    bubbleStackView2.mDismissView.hide();
                    break;
            }
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
        public void onStuckToTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject) {
            switch (this.$r8$classId) {
                case 0:
                    Object obj = magnetizedObject.underlyingObject;
                    if (obj instanceof View) {
                        BubbleStackView.m899$$Nest$manimateDismissBubble(this.this$0, (View) obj, true);
                        break;
                    }
                    break;
                default:
                    BubbleStackView bubbleStackView = this.this$0;
                    BubbleStackView.m899$$Nest$manimateDismissBubble(bubbleStackView, bubbleStackView.mBubbleContainer, true);
                    break;
            }
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
        public void onUnstuckFromTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject, float f, float f2, boolean z) {
            switch (this.$r8$classId) {
                case 0:
                    Object obj = magnetizedObject.underlyingObject;
                    if (obj instanceof View) {
                        View view = (View) obj;
                        BubbleStackView bubbleStackView = this.this$0;
                        BubbleStackView.m899$$Nest$manimateDismissBubble(bubbleStackView, view, false);
                        if (!z) {
                            bubbleStackView.mExpandedAnimationController.mSpringToTouchOnNextMotionEvent = true;
                            break;
                        } else {
                            bubbleStackView.mExpandedAnimationController.snapBubbleBack(view, f, f2);
                            bubbleStackView.mDismissView.hide();
                            break;
                        }
                    }
                    break;
                default:
                    BubbleStackView bubbleStackView2 = this.this$0;
                    BubbleStackView.m899$$Nest$manimateDismissBubble(bubbleStackView2, bubbleStackView2.mBubbleContainer, false);
                    if (!z) {
                        bubbleStackView2.mStackAnimationController.mSpringToTouchOnNextMotionEvent = true;
                        break;
                    } else {
                        StackAnimationController stackAnimationController = bubbleStackView2.mStackAnimationController;
                        stackAnimationController.flingStackThenSpringToEdge(stackAnimationController.mStackPosition.x, f, f2);
                        bubbleStackView2.mDismissView.hide();
                        break;
                    }
            }
        }

        public void onUp(float f) {
            BubbleStackView bubbleStackView = this.this$0;
            ExpandedViewAnimationControllerImpl expandedViewAnimationControllerImpl = bubbleStackView.mExpandedViewAnimationController;
            if (f < 0.0f) {
                expandedViewAnimationControllerImpl.getClass();
                expandedViewAnimationControllerImpl.mSwipeUpVelocity = Math.abs(f);
                expandedViewAnimationControllerImpl.mSwipeDownVelocity = 0.0f;
            } else {
                expandedViewAnimationControllerImpl.mSwipeUpVelocity = 0.0f;
                expandedViewAnimationControllerImpl.mSwipeDownVelocity = f;
            }
            ExpandedViewAnimationControllerImpl expandedViewAnimationControllerImpl2 = bubbleStackView.mExpandedViewAnimationController;
            float f2 = expandedViewAnimationControllerImpl2.mSwipeDownVelocity;
            int i = expandedViewAnimationControllerImpl2.mMinFlingVelocity;
            float f3 = i;
            if (f2 <= f3) {
                float f4 = expandedViewAnimationControllerImpl2.mSwipeUpVelocity;
                if (f4 <= f3) {
                    if (expandedViewAnimationControllerImpl2.mExpandedView == null || expandedViewAnimationControllerImpl2.mDraggedAmount <= r0.getContentHeight() * 0.02f) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -4284314353285188051L, 0, null);
                        }
                    } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -8066306625404848681L, 1, Long.valueOf(expandedViewAnimationControllerImpl2.mDraggedAmount));
                    }
                } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 5757023429076269063L, 6, Double.valueOf(f4), Long.valueOf(i));
                }
                bubbleStackView.mBubbleData.setExpanded(false);
                return;
            }
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 4717174592761508124L, 6, Double.valueOf(f2), Long.valueOf(i));
            }
            bubbleStackView.mExpandedViewAnimationController.animateBackToExpanded();
            if (bubbleStackView.mScrimAnimation == null) {
                bubbleStackView.showScrim(true, null);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RelativeStackPosition {
        public final boolean mOnLeft;
        public final float mVerticalOffsetPercent;

        public RelativeStackPosition(PointF pointF, RectF rectF) {
            this.mOnLeft = pointF.x < rectF.width() / 2.0f;
            this.mVerticalOffsetPercent = Math.max(0.0f, Math.min(1.0f, (pointF.y - rectF.top) / rectF.height()));
        }

        public final PointF getAbsolutePositionInRegion(RectF rectF) {
            return new PointF(this.mOnLeft ? rectF.left : rectF.right, (rectF.height() * this.mVerticalOffsetPercent) + rectF.top);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StackViewState {
        public int numberOfBubbles;
        public boolean onLeft;
        public int selectedIndex;
    }

    public static void $r8$lambda$IqtEhCPUiJmY5XbDTuX9Ey7r4cM(BubbleStackView bubbleStackView) {
        if (bubbleStackView.mIsExpanded && bubbleStackView.getExpandedView() != null) {
            BubbleViewProvider bubbleViewProvider = bubbleStackView.mBubbleData.mSelectedBubble;
            boolean z = false;
            if ((bubbleViewProvider instanceof Bubble) && ((Bubble) bubbleViewProvider).mShortcutInfo != null) {
                Context context = ((FrameLayout) bubbleStackView).mContext;
                boolean z2 = (!context.getSharedPreferences(context.getPackageName(), 0).getBoolean("HasSeenBubblesManageOnboarding", false) || BubbleDebugConfig.forceShowUserEducation(((FrameLayout) bubbleStackView).mContext)) && bubbleStackView.getExpandedView() != null;
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -7905516184626643472L, 3, Boolean.valueOf(z2));
                }
                if (z2 && BubbleDebugConfig.neverShowUserEducation(((FrameLayout) bubbleStackView).mContext)) {
                    Log.w("Bubbles", "Want to show manage edu, but it is forced hidden");
                } else {
                    z = z2;
                }
            }
            if (z) {
                if (bubbleStackView.mManageEduView == null) {
                    ManageEducationView manageEducationView = new ManageEducationView(((FrameLayout) bubbleStackView).mContext, bubbleStackView.mPositioner);
                    bubbleStackView.mManageEduView = manageEducationView;
                    bubbleStackView.addView(manageEducationView);
                }
                BubbleExpandedView expandedView = bubbleStackView.getExpandedView();
                if (expandedView != null) {
                    bubbleStackView.mManageEduView.show(expandedView, bubbleStackView.mStackAnimationController.isStackOnLeftSide());
                }
            }
        }
        bubbleStackView.updateOverflowDotVisibility(true);
    }

    /* renamed from: $r8$lambda$Pshq06SD_DiAmjLSXEQ-3_CrIvE, reason: not valid java name */
    public static void m898$r8$lambda$Pshq06SD_DiAmjLSXEQ3_CrIvE(BubbleStackView bubbleStackView) {
        bubbleStackView.showManageMenu(false);
        BubbleData bubbleData = bubbleStackView.mBubbleData;
        BubbleViewProvider bubbleViewProvider = bubbleData.mSelectedBubble;
        if (bubbleViewProvider == null || !bubbleData.hasBubbleInStackWithKey(bubbleViewProvider.getKey())) {
            return;
        }
        Bubble bubble = (Bubble) bubbleViewProvider;
        Intent settingsIntent = bubble.getSettingsIntent(((FrameLayout) bubbleStackView).mContext);
        bubbleStackView.mBubbleData.setExpanded(false);
        ((FrameLayout) bubbleStackView).mContext.startActivityAsUser(settingsIntent, bubble.mUser);
        bubbleStackView.logBubbleEvent(bubbleViewProvider, 9);
    }

    public static void $r8$lambda$kPR5FW85iC9j27VRHv6SqSdUz9E(BubbleStackView bubbleStackView) {
        RelativeStackPosition relativeStackPosition;
        BubblePositioner bubblePositioner = bubbleStackView.mPositioner;
        Context context = ((FrameLayout) bubbleStackView).mContext;
        bubblePositioner.update(DeviceConfig.create(context, (WindowManager) context.getSystemService(WindowManager.class)));
        bubbleStackView.onDisplaySizeChanged();
        bubbleStackView.mExpandedAnimationController.updateResources();
        ExpandedAnimationController expandedAnimationController = bubbleStackView.mExpandedAnimationController;
        if (expandedAnimationController.mLayout != null) {
            expandedAnimationController.updateBubblePositions();
        }
        StackAnimationController stackAnimationController = bubbleStackView.mStackAnimationController;
        PhysicsAnimationLayout physicsAnimationLayout = stackAnimationController.mLayout;
        if (physicsAnimationLayout != null) {
            stackAnimationController.mBubblePaddingTop = physicsAnimationLayout.getContext().getResources().getDimensionPixelSize(R.dimen.bubble_padding_top);
            PhysicsAnimationLayout physicsAnimationLayout2 = stackAnimationController.mLayout;
            if (physicsAnimationLayout2 != null && stackAnimationController.mMagnetizedStack != null) {
                int i = physicsAnimationLayout2.getResources().getDisplayMetrics().widthPixels;
                stackAnimationController.mMagnetizedStack.flingToTargetWidthPercent = i >= 2000 ? 6.0f : i >= 1500 ? 4.5f : 3.0f;
            }
        }
        bubbleStackView.mBubbleOverflow.updateResources();
        if (!bubbleStackView.isStackEduVisible() && (relativeStackPosition = bubbleStackView.mRelativeStackPositionBeforeRotation) != null) {
            StackAnimationController stackAnimationController2 = bubbleStackView.mStackAnimationController;
            stackAnimationController2.setStackPosition(relativeStackPosition.getAbsolutePositionInRegion(stackAnimationController2.mPositioner.getAllowableStackPositionRegion(stackAnimationController2.mBubbleCountSupplier.f$0.getBubbleCount())));
            bubbleStackView.mRelativeStackPositionBeforeRotation = null;
        }
        if (bubbleStackView.mIsExpanded) {
            bubbleStackView.hideFlyoutImmediate();
            bubbleStackView.mExpandedViewContainer.setAlpha(0.0f);
            bubbleStackView.updateExpandedView();
            bubbleStackView.updateOverflowVisibility();
            bubbleStackView.updatePointerPosition(false);
            bubbleStackView.requestUpdate();
            if (bubbleStackView.mShowingManage) {
                bubbleStackView.post(new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView, 10));
            } else {
                bubbleStackView.showManageMenu(false);
            }
            PointF expandedBubbleXY = bubbleStackView.mPositioner.getExpandedBubbleXY(bubbleStackView.getBubbleIndex(bubbleStackView.mExpandedBubble), bubbleStackView.getState());
            BubblePositioner bubblePositioner2 = bubbleStackView.mPositioner;
            float expandedViewY = bubblePositioner2.getExpandedViewY(bubbleStackView.mExpandedBubble, bubblePositioner2.showBubblesVertically() ? expandedBubbleXY.y : expandedBubbleXY.x);
            bubbleStackView.mExpandedViewContainer.setTranslationX(0.0f);
            bubbleStackView.mExpandedViewContainer.setTranslationY(expandedViewY);
            bubbleStackView.mExpandedViewContainer.setAlpha(1.0f);
        }
        bubbleStackView.removeOnLayoutChangeListener(bubbleStackView.mOrientationChangedListener);
    }

    /* renamed from: -$$Nest$manimateDismissBubble, reason: not valid java name */
    public static void m899$$Nest$manimateDismissBubble(BubbleStackView bubbleStackView, View view, boolean z) {
        bubbleStackView.mViewBeingDismissed = view;
        if (view == null) {
            return;
        }
        if (z) {
            bubbleStackView.mDismissBubbleAnimator.removeAllListeners();
            bubbleStackView.mDismissBubbleAnimator.start();
        } else {
            bubbleStackView.mDismissBubbleAnimator.removeAllListeners();
            bubbleStackView.mDismissBubbleAnimator.addListener(new AnonymousClass13(bubbleStackView, 2));
            bubbleStackView.mDismissBubbleAnimator.reverse();
        }
    }

    /* renamed from: -$$Nest$mresetDismissAnimator, reason: not valid java name */
    public static void m900$$Nest$mresetDismissAnimator(BubbleStackView bubbleStackView) {
        bubbleStackView.mDismissBubbleAnimator.removeAllListeners();
        bubbleStackView.mDismissBubbleAnimator.cancel();
        View view = bubbleStackView.mViewBeingDismissed;
        if (view != null) {
            view.setAlpha(1.0f);
            bubbleStackView.mViewBeingDismissed = null;
        }
        DismissView dismissView = bubbleStackView.mDismissView;
        if (dismissView != null) {
            dismissView.circle.setScaleX(1.0f);
            bubbleStackView.mDismissView.circle.setScaleY(1.0f);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r1v11, types: [com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda22] */
    /* JADX WARN: Type inference failed for: r2v12, types: [com.android.wm.shell.bubbles.BubbleStackView$2] */
    /* JADX WARN: Type inference failed for: r2v13, types: [com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda15] */
    /* JADX WARN: Type inference failed for: r2v17, types: [com.android.wm.shell.bubbles.BubbleStackView$6] */
    /* JADX WARN: Type inference failed for: r2v18, types: [com.android.wm.shell.bubbles.BubbleStackView$7] */
    /* JADX WARN: Type inference failed for: r2v19, types: [com.android.wm.shell.bubbles.BubbleStackView$7] */
    /* JADX WARN: Type inference failed for: r2v21, types: [com.android.wm.shell.bubbles.BubbleStackView$6] */
    /* JADX WARN: Type inference failed for: r2v22, types: [com.android.wm.shell.bubbles.BubbleStackView$7] */
    public BubbleStackView(Context context, BubbleStackViewManager$Companion$fromBubbleController$1 bubbleStackViewManager$Companion$fromBubbleController$1, BubblePositioner bubblePositioner, BubbleData bubbleData, FloatingContentCoordinator floatingContentCoordinator, BubbleController bubbleController, ShellExecutor shellExecutor) {
        super(context);
        PhysicsAnimationLayout physicsAnimationLayout;
        final int i = 1;
        final int i2 = 0;
        final int i3 = 2;
        this.mScaleInSpringConfig = new PhysicsAnimator.SpringConfig(300.0f, 0.9f);
        this.mScaleOutSpringConfig = new PhysicsAnimator.SpringConfig(900.0f, 1.0f);
        this.mTranslateSpringConfig = new PhysicsAnimator.SpringConfig(50.0f, 1.0f);
        this.mStackViewState = new StackViewState();
        this.mExpandedViewContainerMatrix = new AnimatableScaleMatrix();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mAnimatingOutSurfaceAlphaAnimator = ofFloat;
        this.mHideFlyout = new BubbleStackView$$ExternalSyntheticLambda2(this, i3);
        this.mBubbleToExpandAfterFlyoutCollapse = null;
        this.mStackOnLeftOrWillBe = true;
        this.mIsGestureInProgress = false;
        this.mTemporarilyInvisible = false;
        this.mIsDraggingStack = false;
        this.mExpandedViewTemporarilyHidden = false;
        this.mRemovingLastBubbleWhileExpanded = false;
        this.mSensitiveNotificationProtectionActive = false;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mExpandedViewAlphaAnimator = ofFloat2;
        this.mPointerIndexDown = -1;
        this.mShouldReorderBubblesAfterGestureCompletes = false;
        this.mViewUpdatedRequested = false;
        this.mIsExpansionAnimating = false;
        this.mIsBubbleSwitchAnimating = false;
        this.mTempRect = new Rect();
        this.mSystemGestureExclusionRects = Collections.singletonList(new Rect());
        this.mViewUpdater = new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.wm.shell.bubbles.BubbleStackView.2
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                BubbleStackView.this.getViewTreeObserver().removeOnPreDrawListener(BubbleStackView.this.mViewUpdater);
                BubbleStackView.this.updateExpandedView();
                BubbleStackView.this.mViewUpdatedRequested = false;
                return true;
            }
        };
        this.mSystemGestureExcludeUpdater = new ViewTreeObserver.OnDrawListener() { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda15
            @Override // android.view.ViewTreeObserver.OnDrawListener
            public final void onDraw() {
                BubbleStackView bubbleStackView = BubbleStackView.this;
                Rect rect = (Rect) bubbleStackView.mSystemGestureExclusionRects.get(0);
                if (bubbleStackView.getBubbleCount() <= 0) {
                    rect.setEmpty();
                    bubbleStackView.mBubbleContainer.setSystemGestureExclusionRects(Collections.emptyList());
                } else {
                    View childAt = bubbleStackView.mBubbleContainer.getChildAt(0);
                    rect.set(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
                    rect.offset((int) (childAt.getTranslationX() + 0.5f), (int) (childAt.getTranslationY() + 0.5f));
                    bubbleStackView.mBubbleContainer.setSystemGestureExclusionRects(bubbleStackView.mSystemGestureExclusionRects);
                }
            }
        };
        SpringAnimation springAnimation = new SpringAnimation(this, new FloatPropertyCompat() { // from class: com.android.wm.shell.bubbles.BubbleStackView.3
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                return BubbleStackView.this.mFlyoutDragDeltaX;
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                BubbleStackView.this.setFlyoutStateForDragLength(f);
            }
        });
        this.mFlyoutTransitionSpring = springAnimation;
        this.mFlyoutDragDeltaX = 0.0f;
        DynamicAnimation.OnAnimationEndListener onAnimationEndListener = new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda16
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                BubbleStackView bubbleStackView = BubbleStackView.this;
                if (bubbleStackView.mFlyoutDragDeltaX == 0.0f) {
                    bubbleStackView.mFlyout.postDelayed(bubbleStackView.mHideFlyout, 5000L);
                    return;
                }
                BubbleFlyoutView bubbleFlyoutView = bubbleStackView.mFlyout;
                BubbleStackView$$ExternalSyntheticLambda27 bubbleStackView$$ExternalSyntheticLambda27 = bubbleFlyoutView.mOnHide;
                if (bubbleStackView$$ExternalSyntheticLambda27 != null) {
                    bubbleStackView$$ExternalSyntheticLambda27.run();
                    bubbleFlyoutView.mOnHide = null;
                }
                bubbleFlyoutView.setVisibility(8);
            }
        };
        this.mIndividualBubbleMagnetListener = new AnonymousClass4(this, i2);
        this.mStackMagnetListener = new AnonymousClass4(this, i);
        this.mBubbleClickListener = new View.OnClickListener(this) { // from class: com.android.wm.shell.bubbles.BubbleStackView.6
            public final /* synthetic */ BubbleStackView this$0;

            {
                this.this$0 = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x008a  */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void onClick(android.view.View r7) {
                /*
                    r6 = this;
                    int r0 = r2
                    switch(r0) {
                        case 0: goto L2c;
                        default: goto L5;
                    }
                L5:
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    boolean r7 = r7.maybeShowStackEdu()
                    if (r7 == 0) goto L13
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    r0 = 0
                    r7.mBubbleToExpandAfterFlyoutCollapse = r0
                    goto L1b
                L13:
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    com.android.wm.shell.bubbles.BubbleData r0 = r7.mBubbleData
                    com.android.wm.shell.bubbles.BubbleViewProvider r0 = r0.mSelectedBubble
                    r7.mBubbleToExpandAfterFlyoutCollapse = r0
                L1b:
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    com.android.wm.shell.bubbles.BubbleFlyoutView r0 = r7.mFlyout
                    com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda2 r7 = r7.mHideFlyout
                    r0.removeCallbacks(r7)
                    com.android.wm.shell.bubbles.BubbleStackView r6 = r6.this$0
                    com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda2 r6 = r6.mHideFlyout
                    r6.run()
                    return
                L2c:
                    com.android.wm.shell.bubbles.BubbleStackView r0 = r6.this$0
                    r1 = 0
                    r0.mIsDraggingStack = r1
                    r2 = 0
                    r0.mMagnetizedObject = r2
                    boolean r3 = r0.mIsExpansionAnimating
                    if (r3 != 0) goto L9c
                    boolean r3 = r0.mIsBubbleSwitchAnimating
                    if (r3 == 0) goto L3d
                    goto L9c
                L3d:
                    com.android.wm.shell.bubbles.BubbleData r0 = r0.mBubbleData
                    com.android.wm.shell.bubbles.Bubble r7 = r0.getBubbleWithView(r7)
                    if (r7 != 0) goto L46
                    goto L9c
                L46:
                    com.android.wm.shell.bubbles.BubbleStackView r0 = r6.this$0
                    com.android.wm.shell.bubbles.BubbleViewProvider r0 = r0.mExpandedBubble
                    r3 = 1
                    if (r0 == 0) goto L5b
                    java.lang.String r0 = r0.getKey()
                    java.lang.String r4 = r7.mKey
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L5b
                    r0 = r3
                    goto L5c
                L5b:
                    r0 = r1
                L5c:
                    com.android.wm.shell.bubbles.BubbleStackView r4 = r6.this$0
                    boolean r5 = r4.mIsExpanded
                    if (r5 == 0) goto L6b
                    com.android.wm.shell.bubbles.animation.ExpandedAnimationController r4 = r4.mExpandedAnimationController
                    r4.mBubbleDraggedOutEnough = r1
                    r4.mMagnetizedBubbleDraggingOut = r2
                    r4.updateBubblePositions()
                L6b:
                    com.android.wm.shell.bubbles.BubbleStackView r2 = r6.this$0
                    boolean r4 = r2.mIsExpanded
                    if (r4 == 0) goto L84
                    if (r0 != 0) goto L84
                    com.android.wm.shell.bubbles.BubbleData r6 = r2.mBubbleData
                    com.android.wm.shell.bubbles.BubbleViewProvider r0 = r6.mSelectedBubble
                    if (r7 == r0) goto L80
                    r6.setSelectedBubbleInternal(r7)
                    r6.dispatchPendingChanges()
                    goto L9c
                L80:
                    r2.setSelectedBubble(r7)
                    goto L9c
                L84:
                    boolean r7 = r2.maybeShowStackEdu()
                    if (r7 != 0) goto L98
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    boolean r0 = r7.mShowedUserEducationInTouchListenerActive
                    if (r0 != 0) goto L98
                    com.android.wm.shell.bubbles.BubbleData r7 = r7.mBubbleData
                    boolean r0 = r7.mExpanded
                    r0 = r0 ^ r3
                    r7.setExpanded(r0)
                L98:
                    com.android.wm.shell.bubbles.BubbleStackView r6 = r6.this$0
                    r6.mShowedUserEducationInTouchListenerActive = r1
                L9c:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.BubbleStackView.AnonymousClass6.onClick(android.view.View):void");
            }
        };
        this.mBubbleTouchListener = new RelativeTouchListener(this) { // from class: com.android.wm.shell.bubbles.BubbleStackView.7
            public final /* synthetic */ BubbleStackView this$0;

            {
                this.this$0 = this;
            }

            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public void onCancel() {
                switch (i2) {
                    case 0:
                        this.this$0.getClass();
                        break;
                }
            }

            /* JADX WARN: Type inference failed for: r5v6, types: [com.android.wm.shell.bubbles.animation.StackAnimationController$2, com.android.wm.shell.shared.magnetictarget.MagnetizedObject] */
            /* JADX WARN: Type inference failed for: r6v5, types: [com.android.wm.shell.bubbles.animation.ExpandedAnimationController$1, com.android.wm.shell.shared.magnetictarget.MagnetizedObject] */
            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public final boolean onDown(final View view, MotionEvent motionEvent) {
                switch (i2) {
                    case 0:
                        BubbleStackView bubbleStackView = this.this$0;
                        if (!bubbleStackView.mIsExpansionAnimating) {
                            bubbleStackView.mShowedUserEducationInTouchListenerActive = false;
                            if (!bubbleStackView.maybeShowStackEdu()) {
                                if (this.this$0.isStackEduVisible()) {
                                    this.this$0.mStackEduView.hide(false);
                                }
                                BubbleStackView bubbleStackView2 = this.this$0;
                                if (bubbleStackView2.mShowingManage) {
                                    bubbleStackView2.showManageMenu(false);
                                }
                                BubbleStackView bubbleStackView3 = this.this$0;
                                boolean z = bubbleStackView3.mBubbleData.mExpanded;
                                DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_Y;
                                DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_X;
                                float f = 3.0f;
                                if (z) {
                                    ManageEducationView manageEducationView = bubbleStackView3.mManageEduView;
                                    if (manageEducationView != null) {
                                        manageEducationView.hide();
                                    }
                                    BubbleStackView bubbleStackView4 = this.this$0;
                                    final ExpandedAnimationController expandedAnimationController = bubbleStackView4.mExpandedAnimationController;
                                    MagnetizedObject.MagneticTarget magneticTarget = bubbleStackView4.mMagneticTarget;
                                    AnonymousClass4 anonymousClass4 = bubbleStackView4.mIndividualBubbleMagnetListener;
                                    expandedAnimationController.mLayout.cancelAnimationsOnView(view);
                                    final Context context2 = expandedAnimationController.mLayout.getContext();
                                    ?? r6 = new MagnetizedObject(context2, view) { // from class: com.android.wm.shell.bubbles.animation.ExpandedAnimationController.1
                                        {
                                            DynamicAnimation.AnonymousClass1 anonymousClass13 = DynamicAnimation.TRANSLATION_X;
                                            DynamicAnimation.AnonymousClass1 anonymousClass14 = DynamicAnimation.TRANSLATION_Y;
                                        }

                                        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                        public final float getHeight(Object obj) {
                                            return expandedAnimationController.mBubbleSizePx;
                                        }

                                        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                        public final void getLocationOnScreen(Object obj, int[] iArr) {
                                            iArr[0] = (int) view.getTranslationX();
                                            iArr[1] = (int) view.getTranslationY();
                                        }

                                        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                        public final float getWidth(Object obj) {
                                            return expandedAnimationController.mBubbleSizePx;
                                        }
                                    };
                                    expandedAnimationController.mMagnetizedBubbleDraggingOut = r6;
                                    r6.associatedTargets.add(magneticTarget);
                                    magneticTarget.targetView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget));
                                    ExpandedAnimationController.AnonymousClass1 anonymousClass13 = expandedAnimationController.mMagnetizedBubbleDraggingOut;
                                    anonymousClass13.magnetListener = anonymousClass4;
                                    anonymousClass13.hapticsEnabled = true;
                                    anonymousClass13.flingToTargetMinVelocity = 6000.0f;
                                    int i4 = expandedAnimationController.mLayout.getContext().getResources().getDisplayMetrics().widthPixels;
                                    ExpandedAnimationController.AnonymousClass1 anonymousClass14 = expandedAnimationController.mMagnetizedBubbleDraggingOut;
                                    if (i4 >= 2000) {
                                        f = 6.0f;
                                    } else if (i4 >= 1500) {
                                        f = 4.5f;
                                    }
                                    anonymousClass14.flingToTargetWidthPercent = f;
                                    this.this$0.mManager.$controller.hideCurrentInputMethod();
                                    BubbleStackView bubbleStackView5 = this.this$0;
                                    bubbleStackView5.mMagnetizedObject = bubbleStackView5.mExpandedAnimationController.mMagnetizedBubbleDraggingOut;
                                } else {
                                    StackAnimationController stackAnimationController = bubbleStackView3.mStackAnimationController;
                                    stackAnimationController.cancelStackPositionAnimation(anonymousClass12);
                                    stackAnimationController.cancelStackPositionAnimation(anonymousClass1);
                                    stackAnimationController.mLayout.mEndActionForProperty.remove(anonymousClass12);
                                    stackAnimationController.mLayout.mEndActionForProperty.remove(anonymousClass1);
                                    BubbleStackView bubbleStackView6 = this.this$0;
                                    bubbleStackView6.mBubbleContainer.setActiveController(bubbleStackView6.mStackAnimationController);
                                    this.this$0.hideFlyoutImmediate();
                                    BubbleStackView bubbleStackView7 = this.this$0;
                                    final StackAnimationController stackAnimationController2 = bubbleStackView7.mStackAnimationController;
                                    if (stackAnimationController2.mMagnetizedStack == null) {
                                        final Context context3 = stackAnimationController2.mLayout.getContext();
                                        final StackAnimationController.StackPositionProperty stackPositionProperty = stackAnimationController2.new StackPositionProperty(anonymousClass12);
                                        final StackAnimationController.StackPositionProperty stackPositionProperty2 = stackAnimationController2.new StackPositionProperty(anonymousClass1);
                                        ?? r5 = new MagnetizedObject(context3, stackAnimationController2, stackPositionProperty, stackPositionProperty2) { // from class: com.android.wm.shell.bubbles.animation.StackAnimationController.2
                                            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                            public final float getHeight(Object obj) {
                                                return StackAnimationController.this.mBubbleSize;
                                            }

                                            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                            public final void getLocationOnScreen(Object obj, int[] iArr) {
                                                PointF pointF = StackAnimationController.this.mStackPosition;
                                                iArr[0] = (int) pointF.x;
                                                iArr[1] = (int) pointF.y;
                                            }

                                            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                            public final float getWidth(Object obj) {
                                                return StackAnimationController.this.mBubbleSize;
                                            }
                                        };
                                        stackAnimationController2.mMagnetizedStack = r5;
                                        r5.hapticsEnabled = true;
                                        r5.flingToTargetMinVelocity = 4000.0f;
                                        PhysicsAnimationLayout physicsAnimationLayout2 = stackAnimationController2.mLayout;
                                        if (physicsAnimationLayout2 != null) {
                                            int i5 = physicsAnimationLayout2.getResources().getDisplayMetrics().widthPixels;
                                            StackAnimationController.AnonymousClass2 anonymousClass2 = stackAnimationController2.mMagnetizedStack;
                                            if (i5 >= 2000) {
                                                f = 6.0f;
                                            } else if (i5 >= 1500) {
                                                f = 4.5f;
                                            }
                                            anonymousClass2.flingToTargetWidthPercent = f;
                                        }
                                    }
                                    bubbleStackView7.mMagnetizedObject = stackAnimationController2.mMagnetizedStack;
                                    this.this$0.mMagnetizedObject.associatedTargets.clear();
                                    BubbleStackView bubbleStackView8 = this.this$0;
                                    MagnetizedObject magnetizedObject = bubbleStackView8.mMagnetizedObject;
                                    MagnetizedObject.MagneticTarget magneticTarget2 = bubbleStackView8.mMagneticTarget;
                                    magnetizedObject.associatedTargets.add(magneticTarget2);
                                    magneticTarget2.targetView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget2));
                                    BubbleStackView bubbleStackView9 = this.this$0;
                                    bubbleStackView9.mMagnetizedObject.magnetListener = bubbleStackView9.mStackMagnetListener;
                                    bubbleStackView9.mIsDraggingStack = true;
                                    bubbleStackView9.updateTemporarilyInvisibleAnimation(false);
                                }
                                MagnetizedObject magnetizedObject2 = this.this$0.mMagnetizedObject;
                                if (magnetizedObject2 != null) {
                                    magnetizedObject2.maybeConsumeMotionEvent(motionEvent);
                                    break;
                                }
                            } else {
                                this.this$0.mShowedUserEducationInTouchListenerActive = true;
                                break;
                            }
                        }
                        break;
                    case 1:
                        BubbleStackView bubbleStackView10 = this.this$0;
                        bubbleStackView10.mFlyout.removeCallbacks(bubbleStackView10.mHideFlyout);
                        break;
                    default:
                        AnonymousClass4 anonymousClass42 = this.this$0.mSwipeUpListener;
                        motionEvent.getX();
                        motionEvent.getY();
                        anonymousClass42.getClass();
                        break;
                }
                return true;
            }

            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public final void onMove(View view, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
                BubbleViewProvider bubbleViewProvider;
                BubbleViewProvider bubbleViewProvider2;
                switch (i2) {
                    case 0:
                        BubbleStackView bubbleStackView = this.this$0;
                        if (!bubbleStackView.mIsExpansionAnimating && !bubbleStackView.mShowedUserEducationInTouchListenerActive) {
                            bubbleStackView.mDismissView.show();
                            BubbleStackView bubbleStackView2 = this.this$0;
                            boolean z = true;
                            if (bubbleStackView2.mIsExpanded && (bubbleViewProvider = bubbleStackView2.mExpandedBubble) != null && view.equals(bubbleViewProvider.getIconView$1())) {
                                BubbleStackView bubbleStackView3 = this.this$0;
                                if (!bubbleStackView3.mExpandedViewTemporarilyHidden && (bubbleViewProvider2 = bubbleStackView3.mExpandedBubble) != null && bubbleViewProvider2.getExpandedView() != null) {
                                    bubbleStackView3.mExpandedViewTemporarilyHidden = true;
                                    AnimatableScaleMatrix animatableScaleMatrix = bubbleStackView3.mExpandedViewContainerMatrix;
                                    Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                                    PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(animatableScaleMatrix);
                                    companion.spring(AnimatableScaleMatrix.SCALE_X, 449.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                    companion.spring(AnimatableScaleMatrix.SCALE_Y, 449.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                    companion.updateListeners.add(new BubbleStackView$$ExternalSyntheticLambda8(bubbleStackView3, 2));
                                    companion.start();
                                    bubbleStackView3.mExpandedViewAlphaAnimator.reverse();
                                }
                            }
                            MagnetizedObject magnetizedObject = this.this$0.mMagnetizedObject;
                            if (magnetizedObject == null || !magnetizedObject.maybeConsumeMotionEvent(motionEvent)) {
                                this.this$0.updateBubbleShadows(true);
                                BubbleStackView bubbleStackView4 = this.this$0;
                                boolean z2 = bubbleStackView4.mBubbleData.mExpanded;
                                DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_Y;
                                DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_X;
                                if (!z2) {
                                    if (bubbleStackView4.isStackEduVisible()) {
                                        this.this$0.mStackEduView.hide(false);
                                    }
                                    StackAnimationController stackAnimationController = this.this$0.mStackAnimationController;
                                    float f5 = f + f3;
                                    float f6 = f2 + f4;
                                    if (stackAnimationController.mSpringToTouchOnNextMotionEvent) {
                                        stackAnimationController.springStack(f5, f6, 12000.0f);
                                        stackAnimationController.mSpringToTouchOnNextMotionEvent = false;
                                        stackAnimationController.mFirstBubbleSpringingToTouch = true;
                                    } else if (stackAnimationController.mFirstBubbleSpringingToTouch) {
                                        SpringAnimation springAnimation2 = (SpringAnimation) stackAnimationController.mStackPositionAnimations.get(anonymousClass12);
                                        SpringAnimation springAnimation3 = (SpringAnimation) stackAnimationController.mStackPositionAnimations.get(anonymousClass1);
                                        if (springAnimation2.mRunning || springAnimation3.mRunning) {
                                            springAnimation2.animateToFinalPosition(f5);
                                            springAnimation3.animateToFinalPosition(f6);
                                        } else {
                                            stackAnimationController.mFirstBubbleSpringingToTouch = false;
                                        }
                                    }
                                    if (!stackAnimationController.mFirstBubbleSpringingToTouch && !stackAnimationController.isStackStuckToTarget()) {
                                        stackAnimationController.mAnimatingToBounds.setEmpty();
                                        stackAnimationController.mPreImeY = -1.4E-45f;
                                        stackAnimationController.moveFirstBubbleWithStackFollowing(anonymousClass12, f5);
                                        stackAnimationController.moveFirstBubbleWithStackFollowing(anonymousClass1, f6);
                                        stackAnimationController.mIsMovingFromFlinging = false;
                                        break;
                                    }
                                } else {
                                    ExpandedAnimationController expandedAnimationController = bubbleStackView4.mExpandedAnimationController;
                                    float f7 = f + f3;
                                    float f8 = f2 + f4;
                                    ExpandedAnimationController.AnonymousClass1 anonymousClass13 = expandedAnimationController.mMagnetizedBubbleDraggingOut;
                                    if (anonymousClass13 != null) {
                                        if (expandedAnimationController.mSpringToTouchOnNextMotionEvent) {
                                            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = expandedAnimationController.animationForChild((View) anonymousClass13.underlyingObject);
                                            animationForChild.mPathAnimator = null;
                                            animationForChild.property(anonymousClass12, f7, new Runnable[0]);
                                            animationForChild.translationY(f8, new Runnable[0]);
                                            animationForChild.mStiffness = 10000.0f;
                                            animationForChild.start(new Runnable[0]);
                                            expandedAnimationController.mSpringToTouchOnNextMotionEvent = false;
                                            expandedAnimationController.mSpringingBubbleToTouch = true;
                                        } else if (expandedAnimationController.mSpringingBubbleToTouch) {
                                            expandedAnimationController.mLayout.getClass();
                                            if (PhysicsAnimationLayout.arePropertiesAnimatingOnView(view, anonymousClass12, anonymousClass1)) {
                                                PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild2 = expandedAnimationController.animationForChild((View) expandedAnimationController.mMagnetizedBubbleDraggingOut.underlyingObject);
                                                animationForChild2.mPathAnimator = null;
                                                animationForChild2.property(anonymousClass12, f7, new Runnable[0]);
                                                animationForChild2.translationY(f8, new Runnable[0]);
                                                animationForChild2.mStiffness = 10000.0f;
                                                animationForChild2.start(new Runnable[0]);
                                            } else {
                                                expandedAnimationController.mSpringingBubbleToTouch = false;
                                            }
                                        }
                                        if (!expandedAnimationController.mSpringingBubbleToTouch && !expandedAnimationController.mMagnetizedBubbleDraggingOut.getObjectStuckToTarget()) {
                                            view.setTranslationX(f7);
                                            view.setTranslationY(f8);
                                        }
                                        float expandedViewYTopAligned = expandedAnimationController.mPositioner.getExpandedViewYTopAligned();
                                        float f9 = expandedAnimationController.mBubbleSizePx;
                                        if (f8 <= expandedViewYTopAligned + f9 && f8 >= expandedViewYTopAligned - f9) {
                                            z = false;
                                        }
                                        if (z != expandedAnimationController.mBubbleDraggedOutEnough) {
                                            expandedAnimationController.updateBubblePositions();
                                            expandedAnimationController.mBubbleDraggedOutEnough = z;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                        this.this$0.setFlyoutStateForDragLength(f3);
                        break;
                    default:
                        this.this$0.mSwipeUpListener.onMove(f4);
                        break;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public final void onUp(View view, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
                boolean z = false;
                char c = 1;
                switch (i2) {
                    case 0:
                        BubbleStackView bubbleStackView = this.this$0;
                        if (!bubbleStackView.mIsExpansionAnimating) {
                            if (!bubbleStackView.mShowedUserEducationInTouchListenerActive) {
                                MagnetizedObject magnetizedObject = bubbleStackView.mMagnetizedObject;
                                if (magnetizedObject == null || !magnetizedObject.maybeConsumeMotionEvent(motionEvent)) {
                                    BubbleStackView bubbleStackView2 = this.this$0;
                                    if (bubbleStackView2.mBubbleData.mExpanded) {
                                        bubbleStackView2.mExpandedAnimationController.snapBubbleBack(view, f3, f4);
                                        BubbleStackView bubbleStackView3 = this.this$0;
                                        if (bubbleStackView3.mExpandedViewTemporarilyHidden) {
                                            bubbleStackView3.mExpandedViewTemporarilyHidden = false;
                                            AnimatableScaleMatrix animatableScaleMatrix = bubbleStackView3.mExpandedViewContainerMatrix;
                                            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                                            PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(animatableScaleMatrix);
                                            companion.spring(AnimatableScaleMatrix.SCALE_X, 499.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                            companion.spring(AnimatableScaleMatrix.SCALE_Y, 499.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                            companion.updateListeners.add(new BubbleStackView$$ExternalSyntheticLambda8(bubbleStackView3, c == true ? 1 : 0));
                                            companion.start();
                                            bubbleStackView3.mExpandedViewAlphaAnimator.start();
                                        }
                                    } else {
                                        boolean z2 = bubbleStackView2.mStackOnLeftOrWillBe;
                                        bubbleStackView2.mStackOnLeftOrWillBe = bubbleStackView2.mStackAnimationController.flingStackThenSpringToEdge(f + f2, f3, f4) <= 0.0f;
                                        BubbleStackView bubbleStackView4 = this.this$0;
                                        bubbleStackView4.updateBadges(z2 != bubbleStackView4.mStackOnLeftOrWillBe);
                                        this.this$0.logBubbleEvent(null, 7);
                                    }
                                    this.this$0.mDismissView.hide();
                                }
                                BubbleStackView bubbleStackView5 = this.this$0;
                                bubbleStackView5.mIsDraggingStack = false;
                                bubbleStackView5.mMagnetizedObject = null;
                                bubbleStackView5.updateTemporarilyInvisibleAnimation(false);
                                this.this$0.getClass();
                                break;
                            } else {
                                bubbleStackView.mShowedUserEducationInTouchListenerActive = false;
                                break;
                            }
                        }
                        break;
                    case 1:
                        boolean isStackOnLeftSide = this.this$0.mStackAnimationController.isStackOnLeftSide();
                        Object[] objArr = !isStackOnLeftSide ? f3 <= 2000.0f : f3 >= -2000.0f;
                        Object[] objArr2 = !isStackOnLeftSide ? f2 <= ((float) this.this$0.mFlyout.getWidth()) * 0.25f : f2 >= ((float) (-this.this$0.mFlyout.getWidth())) * 0.25f;
                        Object[] objArr3 = !isStackOnLeftSide ? f3 >= 0.0f : f3 <= 0.0f;
                        if (objArr != false || (objArr2 != false && objArr3 == false)) {
                            z = true;
                        }
                        BubbleStackView bubbleStackView6 = this.this$0;
                        bubbleStackView6.mFlyout.removeCallbacks(bubbleStackView6.mHideFlyout);
                        this.this$0.animateFlyoutCollapsed(f3, z);
                        this.this$0.maybeShowStackEdu();
                        break;
                    default:
                        this.this$0.mSwipeUpListener.onUp(f4);
                        break;
                }
            }
        };
        this.mContainerSwipeListener = new RelativeTouchListener(this) { // from class: com.android.wm.shell.bubbles.BubbleStackView.7
            public final /* synthetic */ BubbleStackView this$0;

            {
                this.this$0 = this;
            }

            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public void onCancel() {
                switch (i3) {
                    case 0:
                        this.this$0.getClass();
                        break;
                }
            }

            /* JADX WARN: Type inference failed for: r5v6, types: [com.android.wm.shell.bubbles.animation.StackAnimationController$2, com.android.wm.shell.shared.magnetictarget.MagnetizedObject] */
            /* JADX WARN: Type inference failed for: r6v5, types: [com.android.wm.shell.bubbles.animation.ExpandedAnimationController$1, com.android.wm.shell.shared.magnetictarget.MagnetizedObject] */
            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public final boolean onDown(final View view, MotionEvent motionEvent) {
                switch (i3) {
                    case 0:
                        BubbleStackView bubbleStackView = this.this$0;
                        if (!bubbleStackView.mIsExpansionAnimating) {
                            bubbleStackView.mShowedUserEducationInTouchListenerActive = false;
                            if (!bubbleStackView.maybeShowStackEdu()) {
                                if (this.this$0.isStackEduVisible()) {
                                    this.this$0.mStackEduView.hide(false);
                                }
                                BubbleStackView bubbleStackView2 = this.this$0;
                                if (bubbleStackView2.mShowingManage) {
                                    bubbleStackView2.showManageMenu(false);
                                }
                                BubbleStackView bubbleStackView3 = this.this$0;
                                boolean z = bubbleStackView3.mBubbleData.mExpanded;
                                DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_Y;
                                DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_X;
                                float f = 3.0f;
                                if (z) {
                                    ManageEducationView manageEducationView = bubbleStackView3.mManageEduView;
                                    if (manageEducationView != null) {
                                        manageEducationView.hide();
                                    }
                                    BubbleStackView bubbleStackView4 = this.this$0;
                                    final ExpandedAnimationController expandedAnimationController = bubbleStackView4.mExpandedAnimationController;
                                    MagnetizedObject.MagneticTarget magneticTarget = bubbleStackView4.mMagneticTarget;
                                    AnonymousClass4 anonymousClass4 = bubbleStackView4.mIndividualBubbleMagnetListener;
                                    expandedAnimationController.mLayout.cancelAnimationsOnView(view);
                                    final Context context2 = expandedAnimationController.mLayout.getContext();
                                    ?? r6 = new MagnetizedObject(context2, view) { // from class: com.android.wm.shell.bubbles.animation.ExpandedAnimationController.1
                                        {
                                            DynamicAnimation.AnonymousClass1 anonymousClass13 = DynamicAnimation.TRANSLATION_X;
                                            DynamicAnimation.AnonymousClass1 anonymousClass14 = DynamicAnimation.TRANSLATION_Y;
                                        }

                                        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                        public final float getHeight(Object obj) {
                                            return expandedAnimationController.mBubbleSizePx;
                                        }

                                        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                        public final void getLocationOnScreen(Object obj, int[] iArr) {
                                            iArr[0] = (int) view.getTranslationX();
                                            iArr[1] = (int) view.getTranslationY();
                                        }

                                        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                        public final float getWidth(Object obj) {
                                            return expandedAnimationController.mBubbleSizePx;
                                        }
                                    };
                                    expandedAnimationController.mMagnetizedBubbleDraggingOut = r6;
                                    r6.associatedTargets.add(magneticTarget);
                                    magneticTarget.targetView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget));
                                    ExpandedAnimationController.AnonymousClass1 anonymousClass13 = expandedAnimationController.mMagnetizedBubbleDraggingOut;
                                    anonymousClass13.magnetListener = anonymousClass4;
                                    anonymousClass13.hapticsEnabled = true;
                                    anonymousClass13.flingToTargetMinVelocity = 6000.0f;
                                    int i4 = expandedAnimationController.mLayout.getContext().getResources().getDisplayMetrics().widthPixels;
                                    ExpandedAnimationController.AnonymousClass1 anonymousClass14 = expandedAnimationController.mMagnetizedBubbleDraggingOut;
                                    if (i4 >= 2000) {
                                        f = 6.0f;
                                    } else if (i4 >= 1500) {
                                        f = 4.5f;
                                    }
                                    anonymousClass14.flingToTargetWidthPercent = f;
                                    this.this$0.mManager.$controller.hideCurrentInputMethod();
                                    BubbleStackView bubbleStackView5 = this.this$0;
                                    bubbleStackView5.mMagnetizedObject = bubbleStackView5.mExpandedAnimationController.mMagnetizedBubbleDraggingOut;
                                } else {
                                    StackAnimationController stackAnimationController = bubbleStackView3.mStackAnimationController;
                                    stackAnimationController.cancelStackPositionAnimation(anonymousClass12);
                                    stackAnimationController.cancelStackPositionAnimation(anonymousClass1);
                                    stackAnimationController.mLayout.mEndActionForProperty.remove(anonymousClass12);
                                    stackAnimationController.mLayout.mEndActionForProperty.remove(anonymousClass1);
                                    BubbleStackView bubbleStackView6 = this.this$0;
                                    bubbleStackView6.mBubbleContainer.setActiveController(bubbleStackView6.mStackAnimationController);
                                    this.this$0.hideFlyoutImmediate();
                                    BubbleStackView bubbleStackView7 = this.this$0;
                                    final StackAnimationController stackAnimationController2 = bubbleStackView7.mStackAnimationController;
                                    if (stackAnimationController2.mMagnetizedStack == null) {
                                        final Context context3 = stackAnimationController2.mLayout.getContext();
                                        final StackAnimationController.StackPositionProperty stackPositionProperty = stackAnimationController2.new StackPositionProperty(anonymousClass12);
                                        final StackAnimationController.StackPositionProperty stackPositionProperty2 = stackAnimationController2.new StackPositionProperty(anonymousClass1);
                                        ?? r5 = new MagnetizedObject(context3, stackAnimationController2, stackPositionProperty, stackPositionProperty2) { // from class: com.android.wm.shell.bubbles.animation.StackAnimationController.2
                                            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                            public final float getHeight(Object obj) {
                                                return StackAnimationController.this.mBubbleSize;
                                            }

                                            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                            public final void getLocationOnScreen(Object obj, int[] iArr) {
                                                PointF pointF = StackAnimationController.this.mStackPosition;
                                                iArr[0] = (int) pointF.x;
                                                iArr[1] = (int) pointF.y;
                                            }

                                            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                            public final float getWidth(Object obj) {
                                                return StackAnimationController.this.mBubbleSize;
                                            }
                                        };
                                        stackAnimationController2.mMagnetizedStack = r5;
                                        r5.hapticsEnabled = true;
                                        r5.flingToTargetMinVelocity = 4000.0f;
                                        PhysicsAnimationLayout physicsAnimationLayout2 = stackAnimationController2.mLayout;
                                        if (physicsAnimationLayout2 != null) {
                                            int i5 = physicsAnimationLayout2.getResources().getDisplayMetrics().widthPixels;
                                            StackAnimationController.AnonymousClass2 anonymousClass2 = stackAnimationController2.mMagnetizedStack;
                                            if (i5 >= 2000) {
                                                f = 6.0f;
                                            } else if (i5 >= 1500) {
                                                f = 4.5f;
                                            }
                                            anonymousClass2.flingToTargetWidthPercent = f;
                                        }
                                    }
                                    bubbleStackView7.mMagnetizedObject = stackAnimationController2.mMagnetizedStack;
                                    this.this$0.mMagnetizedObject.associatedTargets.clear();
                                    BubbleStackView bubbleStackView8 = this.this$0;
                                    MagnetizedObject magnetizedObject = bubbleStackView8.mMagnetizedObject;
                                    MagnetizedObject.MagneticTarget magneticTarget2 = bubbleStackView8.mMagneticTarget;
                                    magnetizedObject.associatedTargets.add(magneticTarget2);
                                    magneticTarget2.targetView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget2));
                                    BubbleStackView bubbleStackView9 = this.this$0;
                                    bubbleStackView9.mMagnetizedObject.magnetListener = bubbleStackView9.mStackMagnetListener;
                                    bubbleStackView9.mIsDraggingStack = true;
                                    bubbleStackView9.updateTemporarilyInvisibleAnimation(false);
                                }
                                MagnetizedObject magnetizedObject2 = this.this$0.mMagnetizedObject;
                                if (magnetizedObject2 != null) {
                                    magnetizedObject2.maybeConsumeMotionEvent(motionEvent);
                                    break;
                                }
                            } else {
                                this.this$0.mShowedUserEducationInTouchListenerActive = true;
                                break;
                            }
                        }
                        break;
                    case 1:
                        BubbleStackView bubbleStackView10 = this.this$0;
                        bubbleStackView10.mFlyout.removeCallbacks(bubbleStackView10.mHideFlyout);
                        break;
                    default:
                        AnonymousClass4 anonymousClass42 = this.this$0.mSwipeUpListener;
                        motionEvent.getX();
                        motionEvent.getY();
                        anonymousClass42.getClass();
                        break;
                }
                return true;
            }

            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public final void onMove(View view, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
                BubbleViewProvider bubbleViewProvider;
                BubbleViewProvider bubbleViewProvider2;
                switch (i3) {
                    case 0:
                        BubbleStackView bubbleStackView = this.this$0;
                        if (!bubbleStackView.mIsExpansionAnimating && !bubbleStackView.mShowedUserEducationInTouchListenerActive) {
                            bubbleStackView.mDismissView.show();
                            BubbleStackView bubbleStackView2 = this.this$0;
                            boolean z = true;
                            if (bubbleStackView2.mIsExpanded && (bubbleViewProvider = bubbleStackView2.mExpandedBubble) != null && view.equals(bubbleViewProvider.getIconView$1())) {
                                BubbleStackView bubbleStackView3 = this.this$0;
                                if (!bubbleStackView3.mExpandedViewTemporarilyHidden && (bubbleViewProvider2 = bubbleStackView3.mExpandedBubble) != null && bubbleViewProvider2.getExpandedView() != null) {
                                    bubbleStackView3.mExpandedViewTemporarilyHidden = true;
                                    AnimatableScaleMatrix animatableScaleMatrix = bubbleStackView3.mExpandedViewContainerMatrix;
                                    Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                                    PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(animatableScaleMatrix);
                                    companion.spring(AnimatableScaleMatrix.SCALE_X, 449.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                    companion.spring(AnimatableScaleMatrix.SCALE_Y, 449.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                    companion.updateListeners.add(new BubbleStackView$$ExternalSyntheticLambda8(bubbleStackView3, 2));
                                    companion.start();
                                    bubbleStackView3.mExpandedViewAlphaAnimator.reverse();
                                }
                            }
                            MagnetizedObject magnetizedObject = this.this$0.mMagnetizedObject;
                            if (magnetizedObject == null || !magnetizedObject.maybeConsumeMotionEvent(motionEvent)) {
                                this.this$0.updateBubbleShadows(true);
                                BubbleStackView bubbleStackView4 = this.this$0;
                                boolean z2 = bubbleStackView4.mBubbleData.mExpanded;
                                DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_Y;
                                DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_X;
                                if (!z2) {
                                    if (bubbleStackView4.isStackEduVisible()) {
                                        this.this$0.mStackEduView.hide(false);
                                    }
                                    StackAnimationController stackAnimationController = this.this$0.mStackAnimationController;
                                    float f5 = f + f3;
                                    float f6 = f2 + f4;
                                    if (stackAnimationController.mSpringToTouchOnNextMotionEvent) {
                                        stackAnimationController.springStack(f5, f6, 12000.0f);
                                        stackAnimationController.mSpringToTouchOnNextMotionEvent = false;
                                        stackAnimationController.mFirstBubbleSpringingToTouch = true;
                                    } else if (stackAnimationController.mFirstBubbleSpringingToTouch) {
                                        SpringAnimation springAnimation2 = (SpringAnimation) stackAnimationController.mStackPositionAnimations.get(anonymousClass12);
                                        SpringAnimation springAnimation3 = (SpringAnimation) stackAnimationController.mStackPositionAnimations.get(anonymousClass1);
                                        if (springAnimation2.mRunning || springAnimation3.mRunning) {
                                            springAnimation2.animateToFinalPosition(f5);
                                            springAnimation3.animateToFinalPosition(f6);
                                        } else {
                                            stackAnimationController.mFirstBubbleSpringingToTouch = false;
                                        }
                                    }
                                    if (!stackAnimationController.mFirstBubbleSpringingToTouch && !stackAnimationController.isStackStuckToTarget()) {
                                        stackAnimationController.mAnimatingToBounds.setEmpty();
                                        stackAnimationController.mPreImeY = -1.4E-45f;
                                        stackAnimationController.moveFirstBubbleWithStackFollowing(anonymousClass12, f5);
                                        stackAnimationController.moveFirstBubbleWithStackFollowing(anonymousClass1, f6);
                                        stackAnimationController.mIsMovingFromFlinging = false;
                                        break;
                                    }
                                } else {
                                    ExpandedAnimationController expandedAnimationController = bubbleStackView4.mExpandedAnimationController;
                                    float f7 = f + f3;
                                    float f8 = f2 + f4;
                                    ExpandedAnimationController.AnonymousClass1 anonymousClass13 = expandedAnimationController.mMagnetizedBubbleDraggingOut;
                                    if (anonymousClass13 != null) {
                                        if (expandedAnimationController.mSpringToTouchOnNextMotionEvent) {
                                            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = expandedAnimationController.animationForChild((View) anonymousClass13.underlyingObject);
                                            animationForChild.mPathAnimator = null;
                                            animationForChild.property(anonymousClass12, f7, new Runnable[0]);
                                            animationForChild.translationY(f8, new Runnable[0]);
                                            animationForChild.mStiffness = 10000.0f;
                                            animationForChild.start(new Runnable[0]);
                                            expandedAnimationController.mSpringToTouchOnNextMotionEvent = false;
                                            expandedAnimationController.mSpringingBubbleToTouch = true;
                                        } else if (expandedAnimationController.mSpringingBubbleToTouch) {
                                            expandedAnimationController.mLayout.getClass();
                                            if (PhysicsAnimationLayout.arePropertiesAnimatingOnView(view, anonymousClass12, anonymousClass1)) {
                                                PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild2 = expandedAnimationController.animationForChild((View) expandedAnimationController.mMagnetizedBubbleDraggingOut.underlyingObject);
                                                animationForChild2.mPathAnimator = null;
                                                animationForChild2.property(anonymousClass12, f7, new Runnable[0]);
                                                animationForChild2.translationY(f8, new Runnable[0]);
                                                animationForChild2.mStiffness = 10000.0f;
                                                animationForChild2.start(new Runnable[0]);
                                            } else {
                                                expandedAnimationController.mSpringingBubbleToTouch = false;
                                            }
                                        }
                                        if (!expandedAnimationController.mSpringingBubbleToTouch && !expandedAnimationController.mMagnetizedBubbleDraggingOut.getObjectStuckToTarget()) {
                                            view.setTranslationX(f7);
                                            view.setTranslationY(f8);
                                        }
                                        float expandedViewYTopAligned = expandedAnimationController.mPositioner.getExpandedViewYTopAligned();
                                        float f9 = expandedAnimationController.mBubbleSizePx;
                                        if (f8 <= expandedViewYTopAligned + f9 && f8 >= expandedViewYTopAligned - f9) {
                                            z = false;
                                        }
                                        if (z != expandedAnimationController.mBubbleDraggedOutEnough) {
                                            expandedAnimationController.updateBubblePositions();
                                            expandedAnimationController.mBubbleDraggedOutEnough = z;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                        this.this$0.setFlyoutStateForDragLength(f3);
                        break;
                    default:
                        this.this$0.mSwipeUpListener.onMove(f4);
                        break;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public final void onUp(View view, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
                boolean z = false;
                char c = 1;
                switch (i3) {
                    case 0:
                        BubbleStackView bubbleStackView = this.this$0;
                        if (!bubbleStackView.mIsExpansionAnimating) {
                            if (!bubbleStackView.mShowedUserEducationInTouchListenerActive) {
                                MagnetizedObject magnetizedObject = bubbleStackView.mMagnetizedObject;
                                if (magnetizedObject == null || !magnetizedObject.maybeConsumeMotionEvent(motionEvent)) {
                                    BubbleStackView bubbleStackView2 = this.this$0;
                                    if (bubbleStackView2.mBubbleData.mExpanded) {
                                        bubbleStackView2.mExpandedAnimationController.snapBubbleBack(view, f3, f4);
                                        BubbleStackView bubbleStackView3 = this.this$0;
                                        if (bubbleStackView3.mExpandedViewTemporarilyHidden) {
                                            bubbleStackView3.mExpandedViewTemporarilyHidden = false;
                                            AnimatableScaleMatrix animatableScaleMatrix = bubbleStackView3.mExpandedViewContainerMatrix;
                                            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                                            PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(animatableScaleMatrix);
                                            companion.spring(AnimatableScaleMatrix.SCALE_X, 499.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                            companion.spring(AnimatableScaleMatrix.SCALE_Y, 499.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                            companion.updateListeners.add(new BubbleStackView$$ExternalSyntheticLambda8(bubbleStackView3, c == true ? 1 : 0));
                                            companion.start();
                                            bubbleStackView3.mExpandedViewAlphaAnimator.start();
                                        }
                                    } else {
                                        boolean z2 = bubbleStackView2.mStackOnLeftOrWillBe;
                                        bubbleStackView2.mStackOnLeftOrWillBe = bubbleStackView2.mStackAnimationController.flingStackThenSpringToEdge(f + f2, f3, f4) <= 0.0f;
                                        BubbleStackView bubbleStackView4 = this.this$0;
                                        bubbleStackView4.updateBadges(z2 != bubbleStackView4.mStackOnLeftOrWillBe);
                                        this.this$0.logBubbleEvent(null, 7);
                                    }
                                    this.this$0.mDismissView.hide();
                                }
                                BubbleStackView bubbleStackView5 = this.this$0;
                                bubbleStackView5.mIsDraggingStack = false;
                                bubbleStackView5.mMagnetizedObject = null;
                                bubbleStackView5.updateTemporarilyInvisibleAnimation(false);
                                this.this$0.getClass();
                                break;
                            } else {
                                bubbleStackView.mShowedUserEducationInTouchListenerActive = false;
                                break;
                            }
                        }
                        break;
                    case 1:
                        boolean isStackOnLeftSide = this.this$0.mStackAnimationController.isStackOnLeftSide();
                        Object[] objArr = !isStackOnLeftSide ? f3 <= 2000.0f : f3 >= -2000.0f;
                        Object[] objArr2 = !isStackOnLeftSide ? f2 <= ((float) this.this$0.mFlyout.getWidth()) * 0.25f : f2 >= ((float) (-this.this$0.mFlyout.getWidth())) * 0.25f;
                        Object[] objArr3 = !isStackOnLeftSide ? f3 >= 0.0f : f3 <= 0.0f;
                        if (objArr != false || (objArr2 != false && objArr3 == false)) {
                            z = true;
                        }
                        BubbleStackView bubbleStackView6 = this.this$0;
                        bubbleStackView6.mFlyout.removeCallbacks(bubbleStackView6.mHideFlyout);
                        this.this$0.animateFlyoutCollapsed(f3, z);
                        this.this$0.maybeShowStackEdu();
                        break;
                    default:
                        this.this$0.mSwipeUpListener.onUp(f4);
                        break;
                }
            }
        };
        this.mSwipeUpListener = new AnonymousClass4(this, i3);
        this.mFlyoutClickListener = new View.OnClickListener(this) { // from class: com.android.wm.shell.bubbles.BubbleStackView.6
            public final /* synthetic */ BubbleStackView this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                /*
                    this = this;
                    int r0 = r2
                    switch(r0) {
                        case 0: goto L2c;
                        default: goto L5;
                    }
                L5:
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    boolean r7 = r7.maybeShowStackEdu()
                    if (r7 == 0) goto L13
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    r0 = 0
                    r7.mBubbleToExpandAfterFlyoutCollapse = r0
                    goto L1b
                L13:
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    com.android.wm.shell.bubbles.BubbleData r0 = r7.mBubbleData
                    com.android.wm.shell.bubbles.BubbleViewProvider r0 = r0.mSelectedBubble
                    r7.mBubbleToExpandAfterFlyoutCollapse = r0
                L1b:
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    com.android.wm.shell.bubbles.BubbleFlyoutView r0 = r7.mFlyout
                    com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda2 r7 = r7.mHideFlyout
                    r0.removeCallbacks(r7)
                    com.android.wm.shell.bubbles.BubbleStackView r6 = r6.this$0
                    com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda2 r6 = r6.mHideFlyout
                    r6.run()
                    return
                L2c:
                    com.android.wm.shell.bubbles.BubbleStackView r0 = r6.this$0
                    r1 = 0
                    r0.mIsDraggingStack = r1
                    r2 = 0
                    r0.mMagnetizedObject = r2
                    boolean r3 = r0.mIsExpansionAnimating
                    if (r3 != 0) goto L9c
                    boolean r3 = r0.mIsBubbleSwitchAnimating
                    if (r3 == 0) goto L3d
                    goto L9c
                L3d:
                    com.android.wm.shell.bubbles.BubbleData r0 = r0.mBubbleData
                    com.android.wm.shell.bubbles.Bubble r7 = r0.getBubbleWithView(r7)
                    if (r7 != 0) goto L46
                    goto L9c
                L46:
                    com.android.wm.shell.bubbles.BubbleStackView r0 = r6.this$0
                    com.android.wm.shell.bubbles.BubbleViewProvider r0 = r0.mExpandedBubble
                    r3 = 1
                    if (r0 == 0) goto L5b
                    java.lang.String r0 = r0.getKey()
                    java.lang.String r4 = r7.mKey
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L5b
                    r0 = r3
                    goto L5c
                L5b:
                    r0 = r1
                L5c:
                    com.android.wm.shell.bubbles.BubbleStackView r4 = r6.this$0
                    boolean r5 = r4.mIsExpanded
                    if (r5 == 0) goto L6b
                    com.android.wm.shell.bubbles.animation.ExpandedAnimationController r4 = r4.mExpandedAnimationController
                    r4.mBubbleDraggedOutEnough = r1
                    r4.mMagnetizedBubbleDraggingOut = r2
                    r4.updateBubblePositions()
                L6b:
                    com.android.wm.shell.bubbles.BubbleStackView r2 = r6.this$0
                    boolean r4 = r2.mIsExpanded
                    if (r4 == 0) goto L84
                    if (r0 != 0) goto L84
                    com.android.wm.shell.bubbles.BubbleData r6 = r2.mBubbleData
                    com.android.wm.shell.bubbles.BubbleViewProvider r0 = r6.mSelectedBubble
                    if (r7 == r0) goto L80
                    r6.setSelectedBubbleInternal(r7)
                    r6.dispatchPendingChanges()
                    goto L9c
                L80:
                    r2.setSelectedBubble(r7)
                    goto L9c
                L84:
                    boolean r7 = r2.maybeShowStackEdu()
                    if (r7 != 0) goto L98
                    com.android.wm.shell.bubbles.BubbleStackView r7 = r6.this$0
                    boolean r0 = r7.mShowedUserEducationInTouchListenerActive
                    if (r0 != 0) goto L98
                    com.android.wm.shell.bubbles.BubbleData r7 = r7.mBubbleData
                    boolean r0 = r7.mExpanded
                    r0 = r0 ^ r3
                    r7.setExpanded(r0)
                L98:
                    com.android.wm.shell.bubbles.BubbleStackView r6 = r6.this$0
                    r6.mShowedUserEducationInTouchListenerActive = r1
                L9c:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.BubbleStackView.AnonymousClass6.onClick(android.view.View):void");
            }
        };
        this.mFlyoutTouchListener = new RelativeTouchListener(this) { // from class: com.android.wm.shell.bubbles.BubbleStackView.7
            public final /* synthetic */ BubbleStackView this$0;

            {
                this.this$0 = this;
            }

            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public void onCancel() {
                switch (i) {
                    case 0:
                        this.this$0.getClass();
                        break;
                }
            }

            /* JADX WARN: Type inference failed for: r5v6, types: [com.android.wm.shell.bubbles.animation.StackAnimationController$2, com.android.wm.shell.shared.magnetictarget.MagnetizedObject] */
            /* JADX WARN: Type inference failed for: r6v5, types: [com.android.wm.shell.bubbles.animation.ExpandedAnimationController$1, com.android.wm.shell.shared.magnetictarget.MagnetizedObject] */
            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public final boolean onDown(final View view, MotionEvent motionEvent) {
                switch (i) {
                    case 0:
                        BubbleStackView bubbleStackView = this.this$0;
                        if (!bubbleStackView.mIsExpansionAnimating) {
                            bubbleStackView.mShowedUserEducationInTouchListenerActive = false;
                            if (!bubbleStackView.maybeShowStackEdu()) {
                                if (this.this$0.isStackEduVisible()) {
                                    this.this$0.mStackEduView.hide(false);
                                }
                                BubbleStackView bubbleStackView2 = this.this$0;
                                if (bubbleStackView2.mShowingManage) {
                                    bubbleStackView2.showManageMenu(false);
                                }
                                BubbleStackView bubbleStackView3 = this.this$0;
                                boolean z = bubbleStackView3.mBubbleData.mExpanded;
                                DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_Y;
                                DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_X;
                                float f = 3.0f;
                                if (z) {
                                    ManageEducationView manageEducationView = bubbleStackView3.mManageEduView;
                                    if (manageEducationView != null) {
                                        manageEducationView.hide();
                                    }
                                    BubbleStackView bubbleStackView4 = this.this$0;
                                    final ExpandedAnimationController expandedAnimationController = bubbleStackView4.mExpandedAnimationController;
                                    MagnetizedObject.MagneticTarget magneticTarget = bubbleStackView4.mMagneticTarget;
                                    AnonymousClass4 anonymousClass4 = bubbleStackView4.mIndividualBubbleMagnetListener;
                                    expandedAnimationController.mLayout.cancelAnimationsOnView(view);
                                    final Context context2 = expandedAnimationController.mLayout.getContext();
                                    ?? r6 = new MagnetizedObject(context2, view) { // from class: com.android.wm.shell.bubbles.animation.ExpandedAnimationController.1
                                        {
                                            DynamicAnimation.AnonymousClass1 anonymousClass13 = DynamicAnimation.TRANSLATION_X;
                                            DynamicAnimation.AnonymousClass1 anonymousClass14 = DynamicAnimation.TRANSLATION_Y;
                                        }

                                        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                        public final float getHeight(Object obj) {
                                            return expandedAnimationController.mBubbleSizePx;
                                        }

                                        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                        public final void getLocationOnScreen(Object obj, int[] iArr) {
                                            iArr[0] = (int) view.getTranslationX();
                                            iArr[1] = (int) view.getTranslationY();
                                        }

                                        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                        public final float getWidth(Object obj) {
                                            return expandedAnimationController.mBubbleSizePx;
                                        }
                                    };
                                    expandedAnimationController.mMagnetizedBubbleDraggingOut = r6;
                                    r6.associatedTargets.add(magneticTarget);
                                    magneticTarget.targetView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget));
                                    ExpandedAnimationController.AnonymousClass1 anonymousClass13 = expandedAnimationController.mMagnetizedBubbleDraggingOut;
                                    anonymousClass13.magnetListener = anonymousClass4;
                                    anonymousClass13.hapticsEnabled = true;
                                    anonymousClass13.flingToTargetMinVelocity = 6000.0f;
                                    int i4 = expandedAnimationController.mLayout.getContext().getResources().getDisplayMetrics().widthPixels;
                                    ExpandedAnimationController.AnonymousClass1 anonymousClass14 = expandedAnimationController.mMagnetizedBubbleDraggingOut;
                                    if (i4 >= 2000) {
                                        f = 6.0f;
                                    } else if (i4 >= 1500) {
                                        f = 4.5f;
                                    }
                                    anonymousClass14.flingToTargetWidthPercent = f;
                                    this.this$0.mManager.$controller.hideCurrentInputMethod();
                                    BubbleStackView bubbleStackView5 = this.this$0;
                                    bubbleStackView5.mMagnetizedObject = bubbleStackView5.mExpandedAnimationController.mMagnetizedBubbleDraggingOut;
                                } else {
                                    StackAnimationController stackAnimationController = bubbleStackView3.mStackAnimationController;
                                    stackAnimationController.cancelStackPositionAnimation(anonymousClass12);
                                    stackAnimationController.cancelStackPositionAnimation(anonymousClass1);
                                    stackAnimationController.mLayout.mEndActionForProperty.remove(anonymousClass12);
                                    stackAnimationController.mLayout.mEndActionForProperty.remove(anonymousClass1);
                                    BubbleStackView bubbleStackView6 = this.this$0;
                                    bubbleStackView6.mBubbleContainer.setActiveController(bubbleStackView6.mStackAnimationController);
                                    this.this$0.hideFlyoutImmediate();
                                    BubbleStackView bubbleStackView7 = this.this$0;
                                    final StackAnimationController stackAnimationController2 = bubbleStackView7.mStackAnimationController;
                                    if (stackAnimationController2.mMagnetizedStack == null) {
                                        final Context context3 = stackAnimationController2.mLayout.getContext();
                                        final StackAnimationController.StackPositionProperty stackPositionProperty = stackAnimationController2.new StackPositionProperty(anonymousClass12);
                                        final StackAnimationController.StackPositionProperty stackPositionProperty2 = stackAnimationController2.new StackPositionProperty(anonymousClass1);
                                        ?? r5 = new MagnetizedObject(context3, stackAnimationController2, stackPositionProperty, stackPositionProperty2) { // from class: com.android.wm.shell.bubbles.animation.StackAnimationController.2
                                            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                            public final float getHeight(Object obj) {
                                                return StackAnimationController.this.mBubbleSize;
                                            }

                                            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                            public final void getLocationOnScreen(Object obj, int[] iArr) {
                                                PointF pointF = StackAnimationController.this.mStackPosition;
                                                iArr[0] = (int) pointF.x;
                                                iArr[1] = (int) pointF.y;
                                            }

                                            @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
                                            public final float getWidth(Object obj) {
                                                return StackAnimationController.this.mBubbleSize;
                                            }
                                        };
                                        stackAnimationController2.mMagnetizedStack = r5;
                                        r5.hapticsEnabled = true;
                                        r5.flingToTargetMinVelocity = 4000.0f;
                                        PhysicsAnimationLayout physicsAnimationLayout2 = stackAnimationController2.mLayout;
                                        if (physicsAnimationLayout2 != null) {
                                            int i5 = physicsAnimationLayout2.getResources().getDisplayMetrics().widthPixels;
                                            StackAnimationController.AnonymousClass2 anonymousClass2 = stackAnimationController2.mMagnetizedStack;
                                            if (i5 >= 2000) {
                                                f = 6.0f;
                                            } else if (i5 >= 1500) {
                                                f = 4.5f;
                                            }
                                            anonymousClass2.flingToTargetWidthPercent = f;
                                        }
                                    }
                                    bubbleStackView7.mMagnetizedObject = stackAnimationController2.mMagnetizedStack;
                                    this.this$0.mMagnetizedObject.associatedTargets.clear();
                                    BubbleStackView bubbleStackView8 = this.this$0;
                                    MagnetizedObject magnetizedObject = bubbleStackView8.mMagnetizedObject;
                                    MagnetizedObject.MagneticTarget magneticTarget2 = bubbleStackView8.mMagneticTarget;
                                    magnetizedObject.associatedTargets.add(magneticTarget2);
                                    magneticTarget2.targetView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget2));
                                    BubbleStackView bubbleStackView9 = this.this$0;
                                    bubbleStackView9.mMagnetizedObject.magnetListener = bubbleStackView9.mStackMagnetListener;
                                    bubbleStackView9.mIsDraggingStack = true;
                                    bubbleStackView9.updateTemporarilyInvisibleAnimation(false);
                                }
                                MagnetizedObject magnetizedObject2 = this.this$0.mMagnetizedObject;
                                if (magnetizedObject2 != null) {
                                    magnetizedObject2.maybeConsumeMotionEvent(motionEvent);
                                    break;
                                }
                            } else {
                                this.this$0.mShowedUserEducationInTouchListenerActive = true;
                                break;
                            }
                        }
                        break;
                    case 1:
                        BubbleStackView bubbleStackView10 = this.this$0;
                        bubbleStackView10.mFlyout.removeCallbacks(bubbleStackView10.mHideFlyout);
                        break;
                    default:
                        AnonymousClass4 anonymousClass42 = this.this$0.mSwipeUpListener;
                        motionEvent.getX();
                        motionEvent.getY();
                        anonymousClass42.getClass();
                        break;
                }
                return true;
            }

            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public final void onMove(View view, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
                BubbleViewProvider bubbleViewProvider;
                BubbleViewProvider bubbleViewProvider2;
                switch (i) {
                    case 0:
                        BubbleStackView bubbleStackView = this.this$0;
                        if (!bubbleStackView.mIsExpansionAnimating && !bubbleStackView.mShowedUserEducationInTouchListenerActive) {
                            bubbleStackView.mDismissView.show();
                            BubbleStackView bubbleStackView2 = this.this$0;
                            boolean z = true;
                            if (bubbleStackView2.mIsExpanded && (bubbleViewProvider = bubbleStackView2.mExpandedBubble) != null && view.equals(bubbleViewProvider.getIconView$1())) {
                                BubbleStackView bubbleStackView3 = this.this$0;
                                if (!bubbleStackView3.mExpandedViewTemporarilyHidden && (bubbleViewProvider2 = bubbleStackView3.mExpandedBubble) != null && bubbleViewProvider2.getExpandedView() != null) {
                                    bubbleStackView3.mExpandedViewTemporarilyHidden = true;
                                    AnimatableScaleMatrix animatableScaleMatrix = bubbleStackView3.mExpandedViewContainerMatrix;
                                    Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                                    PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(animatableScaleMatrix);
                                    companion.spring(AnimatableScaleMatrix.SCALE_X, 449.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                    companion.spring(AnimatableScaleMatrix.SCALE_Y, 449.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                    companion.updateListeners.add(new BubbleStackView$$ExternalSyntheticLambda8(bubbleStackView3, 2));
                                    companion.start();
                                    bubbleStackView3.mExpandedViewAlphaAnimator.reverse();
                                }
                            }
                            MagnetizedObject magnetizedObject = this.this$0.mMagnetizedObject;
                            if (magnetizedObject == null || !magnetizedObject.maybeConsumeMotionEvent(motionEvent)) {
                                this.this$0.updateBubbleShadows(true);
                                BubbleStackView bubbleStackView4 = this.this$0;
                                boolean z2 = bubbleStackView4.mBubbleData.mExpanded;
                                DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_Y;
                                DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_X;
                                if (!z2) {
                                    if (bubbleStackView4.isStackEduVisible()) {
                                        this.this$0.mStackEduView.hide(false);
                                    }
                                    StackAnimationController stackAnimationController = this.this$0.mStackAnimationController;
                                    float f5 = f + f3;
                                    float f6 = f2 + f4;
                                    if (stackAnimationController.mSpringToTouchOnNextMotionEvent) {
                                        stackAnimationController.springStack(f5, f6, 12000.0f);
                                        stackAnimationController.mSpringToTouchOnNextMotionEvent = false;
                                        stackAnimationController.mFirstBubbleSpringingToTouch = true;
                                    } else if (stackAnimationController.mFirstBubbleSpringingToTouch) {
                                        SpringAnimation springAnimation2 = (SpringAnimation) stackAnimationController.mStackPositionAnimations.get(anonymousClass12);
                                        SpringAnimation springAnimation3 = (SpringAnimation) stackAnimationController.mStackPositionAnimations.get(anonymousClass1);
                                        if (springAnimation2.mRunning || springAnimation3.mRunning) {
                                            springAnimation2.animateToFinalPosition(f5);
                                            springAnimation3.animateToFinalPosition(f6);
                                        } else {
                                            stackAnimationController.mFirstBubbleSpringingToTouch = false;
                                        }
                                    }
                                    if (!stackAnimationController.mFirstBubbleSpringingToTouch && !stackAnimationController.isStackStuckToTarget()) {
                                        stackAnimationController.mAnimatingToBounds.setEmpty();
                                        stackAnimationController.mPreImeY = -1.4E-45f;
                                        stackAnimationController.moveFirstBubbleWithStackFollowing(anonymousClass12, f5);
                                        stackAnimationController.moveFirstBubbleWithStackFollowing(anonymousClass1, f6);
                                        stackAnimationController.mIsMovingFromFlinging = false;
                                        break;
                                    }
                                } else {
                                    ExpandedAnimationController expandedAnimationController = bubbleStackView4.mExpandedAnimationController;
                                    float f7 = f + f3;
                                    float f8 = f2 + f4;
                                    ExpandedAnimationController.AnonymousClass1 anonymousClass13 = expandedAnimationController.mMagnetizedBubbleDraggingOut;
                                    if (anonymousClass13 != null) {
                                        if (expandedAnimationController.mSpringToTouchOnNextMotionEvent) {
                                            PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild = expandedAnimationController.animationForChild((View) anonymousClass13.underlyingObject);
                                            animationForChild.mPathAnimator = null;
                                            animationForChild.property(anonymousClass12, f7, new Runnable[0]);
                                            animationForChild.translationY(f8, new Runnable[0]);
                                            animationForChild.mStiffness = 10000.0f;
                                            animationForChild.start(new Runnable[0]);
                                            expandedAnimationController.mSpringToTouchOnNextMotionEvent = false;
                                            expandedAnimationController.mSpringingBubbleToTouch = true;
                                        } else if (expandedAnimationController.mSpringingBubbleToTouch) {
                                            expandedAnimationController.mLayout.getClass();
                                            if (PhysicsAnimationLayout.arePropertiesAnimatingOnView(view, anonymousClass12, anonymousClass1)) {
                                                PhysicsAnimationLayout.PhysicsPropertyAnimator animationForChild2 = expandedAnimationController.animationForChild((View) expandedAnimationController.mMagnetizedBubbleDraggingOut.underlyingObject);
                                                animationForChild2.mPathAnimator = null;
                                                animationForChild2.property(anonymousClass12, f7, new Runnable[0]);
                                                animationForChild2.translationY(f8, new Runnable[0]);
                                                animationForChild2.mStiffness = 10000.0f;
                                                animationForChild2.start(new Runnable[0]);
                                            } else {
                                                expandedAnimationController.mSpringingBubbleToTouch = false;
                                            }
                                        }
                                        if (!expandedAnimationController.mSpringingBubbleToTouch && !expandedAnimationController.mMagnetizedBubbleDraggingOut.getObjectStuckToTarget()) {
                                            view.setTranslationX(f7);
                                            view.setTranslationY(f8);
                                        }
                                        float expandedViewYTopAligned = expandedAnimationController.mPositioner.getExpandedViewYTopAligned();
                                        float f9 = expandedAnimationController.mBubbleSizePx;
                                        if (f8 <= expandedViewYTopAligned + f9 && f8 >= expandedViewYTopAligned - f9) {
                                            z = false;
                                        }
                                        if (z != expandedAnimationController.mBubbleDraggedOutEnough) {
                                            expandedAnimationController.updateBubblePositions();
                                            expandedAnimationController.mBubbleDraggedOutEnough = z;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                        this.this$0.setFlyoutStateForDragLength(f3);
                        break;
                    default:
                        this.this$0.mSwipeUpListener.onMove(f4);
                        break;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
            public final void onUp(View view, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
                boolean z = false;
                char c = 1;
                switch (i) {
                    case 0:
                        BubbleStackView bubbleStackView = this.this$0;
                        if (!bubbleStackView.mIsExpansionAnimating) {
                            if (!bubbleStackView.mShowedUserEducationInTouchListenerActive) {
                                MagnetizedObject magnetizedObject = bubbleStackView.mMagnetizedObject;
                                if (magnetizedObject == null || !magnetizedObject.maybeConsumeMotionEvent(motionEvent)) {
                                    BubbleStackView bubbleStackView2 = this.this$0;
                                    if (bubbleStackView2.mBubbleData.mExpanded) {
                                        bubbleStackView2.mExpandedAnimationController.snapBubbleBack(view, f3, f4);
                                        BubbleStackView bubbleStackView3 = this.this$0;
                                        if (bubbleStackView3.mExpandedViewTemporarilyHidden) {
                                            bubbleStackView3.mExpandedViewTemporarilyHidden = false;
                                            AnimatableScaleMatrix animatableScaleMatrix = bubbleStackView3.mExpandedViewContainerMatrix;
                                            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                                            PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(animatableScaleMatrix);
                                            companion.spring(AnimatableScaleMatrix.SCALE_X, 499.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                            companion.spring(AnimatableScaleMatrix.SCALE_Y, 499.99997f, 0.0f, bubbleStackView3.mScaleOutSpringConfig);
                                            companion.updateListeners.add(new BubbleStackView$$ExternalSyntheticLambda8(bubbleStackView3, c == true ? 1 : 0));
                                            companion.start();
                                            bubbleStackView3.mExpandedViewAlphaAnimator.start();
                                        }
                                    } else {
                                        boolean z2 = bubbleStackView2.mStackOnLeftOrWillBe;
                                        bubbleStackView2.mStackOnLeftOrWillBe = bubbleStackView2.mStackAnimationController.flingStackThenSpringToEdge(f + f2, f3, f4) <= 0.0f;
                                        BubbleStackView bubbleStackView4 = this.this$0;
                                        bubbleStackView4.updateBadges(z2 != bubbleStackView4.mStackOnLeftOrWillBe);
                                        this.this$0.logBubbleEvent(null, 7);
                                    }
                                    this.this$0.mDismissView.hide();
                                }
                                BubbleStackView bubbleStackView5 = this.this$0;
                                bubbleStackView5.mIsDraggingStack = false;
                                bubbleStackView5.mMagnetizedObject = null;
                                bubbleStackView5.updateTemporarilyInvisibleAnimation(false);
                                this.this$0.getClass();
                                break;
                            } else {
                                bubbleStackView.mShowedUserEducationInTouchListenerActive = false;
                                break;
                            }
                        }
                        break;
                    case 1:
                        boolean isStackOnLeftSide = this.this$0.mStackAnimationController.isStackOnLeftSide();
                        Object[] objArr = !isStackOnLeftSide ? f3 <= 2000.0f : f3 >= -2000.0f;
                        Object[] objArr2 = !isStackOnLeftSide ? f2 <= ((float) this.this$0.mFlyout.getWidth()) * 0.25f : f2 >= ((float) (-this.this$0.mFlyout.getWidth())) * 0.25f;
                        Object[] objArr3 = !isStackOnLeftSide ? f3 >= 0.0f : f3 <= 0.0f;
                        if (objArr != false || (objArr2 != false && objArr3 == false)) {
                            z = true;
                        }
                        BubbleStackView bubbleStackView6 = this.this$0;
                        bubbleStackView6.mFlyout.removeCallbacks(bubbleStackView6.mHideFlyout);
                        this.this$0.animateFlyoutCollapsed(f3, z);
                        this.this$0.maybeShowStackEdu();
                        break;
                    default:
                        this.this$0.mSwipeUpListener.onUp(f4);
                        break;
                }
            }
        };
        this.mShowingManage = false;
        this.mShowedUserEducationInTouchListenerActive = false;
        this.mManageSpringConfig = new PhysicsAnimator.SpringConfig(1500.0f, 0.75f);
        this.mAnimateTemporarilyInvisibleImmediate = new BubbleStackView$$ExternalSyntheticLambda2(this, 3);
        new BubbleStackView$$ExternalSyntheticLambda2(this, 4);
        this.mMainExecutor = shellExecutor;
        this.mManager = bubbleStackViewManager$Companion$fromBubbleController$1;
        this.mPositioner = bubblePositioner;
        this.mBubbleData = bubbleData;
        this.mSysuiProxyProvider = bubbleController;
        Resources resources = getResources();
        this.mBubbleSize = resources.getDimensionPixelSize(R.dimen.bubble_size);
        int i4 = bubblePositioner.mBubbleElevation;
        this.mBubbleElevation = i4;
        this.mBubbleTouchPadding = resources.getDimensionPixelSize(R.dimen.bubble_touch_padding);
        this.mExpandedViewPadding = resources.getDimensionPixelSize(R.dimen.bubble_expanded_view_padding);
        TypedArray obtainStyledAttributes = ((FrameLayout) this).mContext.obtainStyledAttributes(new int[]{android.R.attr.dialogCornerRadius});
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.mCornerRadius = dimensionPixelSize;
        obtainStyledAttributes.recycle();
        BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = new BubbleStackView$$ExternalSyntheticLambda2(this, 5);
        StackAnimationController stackAnimationController = new StackAnimationController(floatingContentCoordinator, new BubbleStackView$$ExternalSyntheticLambda20(this), bubbleStackView$$ExternalSyntheticLambda2, new BubbleStackView$$ExternalSyntheticLambda2(this, 6), bubblePositioner);
        this.mStackAnimationController = stackAnimationController;
        this.mExpandedAnimationController = new ExpandedAnimationController(bubblePositioner, bubbleStackView$$ExternalSyntheticLambda2, this);
        this.mExpandedViewAnimationController = new ExpandedViewAnimationControllerImpl(context, bubblePositioner);
        this.mSurfaceSynchronizer = DEFAULT_SURFACE_SYNCHRONIZER;
        setLayoutDirection(0);
        PhysicsAnimationLayout physicsAnimationLayout2 = new PhysicsAnimationLayout(context);
        this.mBubbleContainer = physicsAnimationLayout2;
        physicsAnimationLayout2.setActiveController(stackAnimationController);
        float f = i4;
        physicsAnimationLayout2.setElevation(f);
        physicsAnimationLayout2.setClipChildren(false);
        addView(physicsAnimationLayout2, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout = new FrameLayout(context);
        this.mExpandedViewContainer = frameLayout;
        frameLayout.setElevation(f);
        frameLayout.setClipChildren(false);
        addView(frameLayout);
        FrameLayout frameLayout2 = new FrameLayout(getContext());
        this.mAnimatingOutSurfaceContainer = frameLayout2;
        frameLayout2.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        addView(frameLayout2);
        SurfaceView surfaceView = new SurfaceView(getContext());
        this.mAnimatingOutSurfaceView = surfaceView;
        surfaceView.setZOrderOnTop(true);
        surfaceView.setCornerRadius(ScreenDecorationsUtils.supportsRoundedCornersOnWindows(((FrameLayout) this).mContext.getResources()) ? dimensionPixelSize : 0.0f);
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: com.android.wm.shell.bubbles.BubbleStackView.12
            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                BubbleStackView.this.mAnimatingOutSurfaceReady = true;
            }

            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                BubbleStackView.this.mAnimatingOutSurfaceReady = false;
            }

            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceChanged(SurfaceHolder surfaceHolder, int i5, int i6, int i7) {
            }
        });
        frameLayout2.addView(surfaceView);
        frameLayout2.setPadding(frameLayout.getPaddingLeft(), frameLayout.getPaddingTop(), frameLayout.getPaddingRight(), frameLayout.getPaddingBottom());
        setUpManageMenu();
        setUpFlyout();
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(200.0f);
        springForce.setDampingRatio(0.75f);
        springAnimation.mSpring = springForce;
        springAnimation.addEndListener(onAnimationEndListener);
        setUpDismissView();
        setClipChildren(false);
        setFocusable(true);
        physicsAnimationLayout2.bringToFront();
        BubbleOverflow bubbleOverflow = bubbleData.mOverflow;
        this.mBubbleOverflow = bubbleOverflow;
        this.mShowingOverflow = true;
        BadgedImageView iconView$1 = bubbleOverflow.getIconView$1();
        if (iconView$1 != null && (physicsAnimationLayout = (PhysicsAnimationLayout) iconView$1.getParent()) != null) {
            physicsAnimationLayout.removeViewNoAnimation(iconView$1);
        }
        PhysicsAnimationLayout physicsAnimationLayout3 = this.mBubbleContainer;
        BadgedImageView iconView$12 = this.mBubbleOverflow.getIconView$1();
        int childCount = this.mBubbleContainer.getChildCount();
        int i5 = this.mBubbleSize;
        physicsAnimationLayout3.addViewInternal(iconView$12, childCount, new FrameLayout.LayoutParams(i5, i5), false);
        updateOverflow();
        this.mBubbleOverflow.getIconView$1().setOnClickListener(new BubbleStackView$$ExternalSyntheticLambda12(this, 4));
        View view = new View(getContext());
        this.mScrim = view;
        view.setImportantForAccessibility(2);
        view.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.system_neutral1_1000)));
        addView(view);
        view.setAlpha(0.0f);
        View view2 = new View(getContext());
        this.mManageMenuScrim = view2;
        view2.setImportantForAccessibility(2);
        view2.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.system_neutral1_1000)));
        addView(view2, new FrameLayout.LayoutParams(-1, -1));
        view2.setAlpha(0.0f);
        view2.setVisibility(4);
        this.mOrientationChangedListener = new View.OnLayoutChangeListener() { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda22
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view3, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
                BubbleStackView.$r8$lambda$kPR5FW85iC9j27VRHv6SqSdUz9E(BubbleStackView.this);
            }
        };
        final float dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.dismiss_circle_small) / getResources().getDimensionPixelSize(R.dimen.dismiss_circle_size);
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.mDismissBubbleAnimator = ofFloat3;
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda23
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BubbleStackView bubbleStackView = BubbleStackView.this;
                float f2 = dimensionPixelSize2;
                bubbleStackView.getClass();
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                DismissView dismissView = bubbleStackView.mDismissView;
                if (dismissView != null) {
                    dismissView.setPivotX((dismissView.getRight() - bubbleStackView.mDismissView.getLeft()) / 2.0f);
                    bubbleStackView.mDismissView.setPivotY((r1.getBottom() - bubbleStackView.mDismissView.getTop()) / 2.0f);
                    float max = Math.max(floatValue, f2);
                    bubbleStackView.mDismissView.circle.setScaleX(max);
                    bubbleStackView.mDismissView.circle.setScaleY(max);
                }
                View view3 = bubbleStackView.mViewBeingDismissed;
                if (view3 != null) {
                    view3.setAlpha(Math.max(floatValue, 0.7f));
                }
            }
        });
        setOnClickListener(new BubbleStackView$$ExternalSyntheticLambda12(this, i2));
        ViewPropertyAnimator animate = animate();
        TimeInterpolator timeInterpolator = Interpolators.PANEL_CLOSE_ACCELERATED;
        animate.setInterpolator(timeInterpolator).setDuration(320L);
        ofFloat2.setDuration(150L);
        ofFloat2.setInterpolator(timeInterpolator);
        ofFloat2.addListener(new AnonymousClass13(this, i2));
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda13
            public final /* synthetic */ BubbleStackView f$0;

            {
                this.f$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i6 = i2;
                BubbleStackView bubbleStackView = this.f$0;
                switch (i6) {
                    case 0:
                        BubbleExpandedView expandedView = bubbleStackView.getExpandedView();
                        if (expandedView != null) {
                            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            expandedView.setContentAlpha(floatValue);
                            expandedView.mPointerView.setAlpha(floatValue);
                            expandedView.setAlpha(floatValue);
                            break;
                        }
                        break;
                    default:
                        if (!bubbleStackView.mExpandedViewTemporarilyHidden) {
                            bubbleStackView.mAnimatingOutSurfaceView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        }
                        break;
                }
            }
        });
        ofFloat.setDuration(150L);
        ofFloat.setInterpolator(timeInterpolator);
        final int i6 = 1;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda13
            public final /* synthetic */ BubbleStackView f$0;

            {
                this.f$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i62 = i6;
                BubbleStackView bubbleStackView = this.f$0;
                switch (i62) {
                    case 0:
                        BubbleExpandedView expandedView = bubbleStackView.getExpandedView();
                        if (expandedView != null) {
                            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                            expandedView.setContentAlpha(floatValue);
                            expandedView.mPointerView.setAlpha(floatValue);
                            expandedView.setAlpha(floatValue);
                            break;
                        }
                        break;
                    default:
                        if (!bubbleStackView.mExpandedViewTemporarilyHidden) {
                            bubbleStackView.mAnimatingOutSurfaceView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        }
                        break;
                }
            }
        });
        ofFloat.addListener(new AnonymousClass13(this, i6));
    }

    public final void addBubble(Bubble bubble) {
        boolean z = getBubbleCount() == 0;
        if (z && shouldShowStackEdu()) {
            this.mStackAnimationController.setStackPosition(this.mPositioner.getDefaultStartPosition(false));
        }
        if (bubble.mIconView == null) {
            return;
        }
        if (z && bubble.mIsAppBubble) {
            BubblePositioner bubblePositioner = this.mPositioner;
            PointF defaultStartPosition = bubblePositioner.getDefaultStartPosition(false);
            PointF pointF = bubblePositioner.mRestingStackPosition;
            if (pointF == null || pointF.equals(defaultStartPosition)) {
                PointF defaultStartPosition2 = this.mPositioner.getDefaultStartPosition(true);
                this.mStackOnLeftOrWillBe = this.mPositioner.isStackOnLeft(defaultStartPosition2);
                this.mStackAnimationController.setStackPosition(defaultStartPosition2);
                this.mExpandedAnimationController.mCollapsePoint = defaultStartPosition2;
                bubble.mIconView.setTranslationX(this.mStackAnimationController.mStackPosition.x);
                PhysicsAnimationLayout physicsAnimationLayout = this.mBubbleContainer;
                BadgedImageView badgedImageView = bubble.mIconView;
                int i = this.mPositioner.mBubbleSize;
                physicsAnimationLayout.addViewInternal(badgedImageView, 0, new FrameLayout.LayoutParams(i, i), false);
                BadgedImageView badgedImageView2 = bubble.mIconView;
                boolean z2 = !this.mStackOnLeftOrWillBe;
                badgedImageView2.mBadgeOnLeft = z2;
                badgedImageView2.mDotOnLeft = z2;
                badgedImageView2.invalidate();
                badgedImageView2.showBadge();
                bubble.mIconView.setOnClickListener(this.mBubbleClickListener);
                bubble.mIconView.setOnTouchListener(this.mBubbleTouchListener);
                updateBubbleShadows(this.mIsExpanded);
                animateInFlyoutForBubble(bubble);
                requestUpdate();
                logBubbleEvent(bubble, 1);
            }
        }
        if (z) {
            this.mStackOnLeftOrWillBe = this.mStackAnimationController.isStackOnLeftSide();
        }
        bubble.mIconView.setTranslationX(this.mStackAnimationController.mStackPosition.x);
        PhysicsAnimationLayout physicsAnimationLayout2 = this.mBubbleContainer;
        BadgedImageView badgedImageView3 = bubble.mIconView;
        int i2 = this.mPositioner.mBubbleSize;
        physicsAnimationLayout2.addViewInternal(badgedImageView3, 0, new FrameLayout.LayoutParams(i2, i2), false);
        BadgedImageView badgedImageView22 = bubble.mIconView;
        boolean z22 = !this.mStackOnLeftOrWillBe;
        badgedImageView22.mBadgeOnLeft = z22;
        badgedImageView22.mDotOnLeft = z22;
        badgedImageView22.invalidate();
        badgedImageView22.showBadge();
        bubble.mIconView.setOnClickListener(this.mBubbleClickListener);
        bubble.mIconView.setOnTouchListener(this.mBubbleTouchListener);
        updateBubbleShadows(this.mIsExpanded);
        animateInFlyoutForBubble(bubble);
        requestUpdate();
        logBubbleEvent(bubble, 1);
    }

    public final void animateFlyoutCollapsed(float f, boolean z) {
        float f2;
        boolean isStackOnLeftSide = this.mStackAnimationController.isStackOnLeftSide();
        this.mFlyoutTransitionSpring.mSpring.setStiffness(this.mBubbleToExpandAfterFlyoutCollapse != null ? 1500.0f : 200.0f);
        SpringAnimation springAnimation = this.mFlyoutTransitionSpring;
        springAnimation.mValue = this.mFlyoutDragDeltaX;
        springAnimation.mStartValueIsSet = true;
        springAnimation.mVelocity = f;
        if (z) {
            int width = this.mFlyout.getWidth();
            if (isStackOnLeftSide) {
                width = -width;
            }
            f2 = width;
        } else {
            f2 = 0.0f;
        }
        springAnimation.animateToFinalPosition(f2);
    }

    public void animateInFlyoutForBubble(Bubble bubble) {
        Bubble.FlyoutMessage flyoutMessage = bubble.mFlyoutMessage;
        BadgedImageView badgedImageView = bubble.mIconView;
        BadgedImageView.SuppressionFlag suppressionFlag = BadgedImageView.SuppressionFlag.FLYOUT_VISIBLE;
        if (flyoutMessage == null || flyoutMessage.message == null || !bubble.showFlyout() || isStackEduVisible() || this.mIsExpanded || this.mIsExpansionAnimating || this.mIsGestureInProgress || this.mSensitiveNotificationProtectionActive || this.mBubbleToExpandAfterFlyoutCollapse != null || badgedImageView == null) {
            if (badgedImageView == null || this.mFlyout.getVisibility() == 0) {
                return;
            }
            badgedImageView.removeDotSuppressionFlag(suppressionFlag);
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -5321455237929157307L, 0, String.valueOf(bubble.mKey));
        }
        this.mFlyoutDragDeltaX = 0.0f;
        this.mFlyout.removeCallbacks(this.mAnimateInFlyout);
        BubbleStackView$$ExternalSyntheticLambda27 bubbleStackView$$ExternalSyntheticLambda27 = this.mAfterFlyoutHidden;
        if (bubbleStackView$$ExternalSyntheticLambda27 != null) {
            bubbleStackView$$ExternalSyntheticLambda27.run();
            this.mAfterFlyoutHidden = null;
        }
        this.mAfterFlyoutHidden = new BubbleStackView$$ExternalSyntheticLambda27(this, bubble, 0);
        BadgedImageView badgedImageView2 = bubble.mIconView;
        if (badgedImageView2.mDotSuppressionFlags.add(suppressionFlag)) {
            badgedImageView2.updateDotVisibility(false);
        }
        post(new BubbleStackView$$ExternalSyntheticLambda27(this, bubble, 1));
        this.mFlyout.removeCallbacks(this.mHideFlyout);
        this.mFlyout.postDelayed(this.mHideFlyout, 5000L);
        logBubbleEvent(bubble, 16);
    }

    public final void animateShadows() {
        int bubbleCount = getBubbleCount();
        for (int i = 0; i < bubbleCount; i++) {
            BadgedImageView badgedImageView = (BadgedImageView) this.mBubbleContainer.getChildAt(i);
            if (i >= 2) {
                badgedImageView.animate().translationZ(0.0f).start();
            }
        }
    }

    public final void dismissBubbleIfExists(BubbleViewProvider bubbleViewProvider) {
        if (bubbleViewProvider == null || !this.mBubbleData.hasBubbleInStackWithKey(bubbleViewProvider.getKey())) {
            return;
        }
        if (this.mIsExpanded && Collections.unmodifiableList(this.mBubbleData.mBubbles).size() > 1 && bubbleViewProvider.equals(this.mExpandedBubble)) {
            this.mIsBubbleSwitchAnimating = true;
        }
        this.mBubbleData.dismissBubbleWithKey(1, bubbleViewProvider.getKey());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 && motionEvent.getActionIndex() != this.mPointerIndexDown) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            this.mPointerIndexDown = motionEvent.getActionIndex();
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.mPointerIndexDown = -1;
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (!dispatchTouchEvent && !this.mIsExpanded && this.mIsGestureInProgress) {
            dispatchTouchEvent = onTouch(this, motionEvent);
        }
        boolean z = (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) ? false : true;
        this.mIsGestureInProgress = z;
        if (this.mShouldReorderBubblesAfterGestureCompletes && !z) {
            this.mShouldReorderBubblesAfterGestureCompletes = false;
            updateBubbleOrderInternal(Collections.unmodifiableList(this.mBubbleData.mBubbles), false);
        }
        return dispatchTouchEvent;
    }

    public final int getBubbleCount() {
        int childCount = this.mBubbleContainer.getChildCount();
        return this.mShowingOverflow ? childCount - 1 : childCount;
    }

    public final int getBubbleIndex(BubbleViewProvider bubbleViewProvider) {
        if (bubbleViewProvider == null) {
            return -1;
        }
        return this.mBubbleContainer.indexOfChild(bubbleViewProvider.getIconView$1());
    }

    public BubbleViewProvider getExpandedBubble() {
        return this.mExpandedBubble;
    }

    public final BubbleExpandedView getExpandedView() {
        BubbleViewProvider bubbleViewProvider = this.mExpandedBubble;
        if (bubbleViewProvider != null) {
            return bubbleViewProvider.getExpandedView();
        }
        return null;
    }

    public final StackViewState getState() {
        this.mStackViewState.numberOfBubbles = this.mBubbleContainer.getChildCount();
        this.mStackViewState.selectedIndex = getBubbleIndex(this.mExpandedBubble);
        StackViewState stackViewState = this.mStackViewState;
        stackViewState.onLeft = this.mStackOnLeftOrWillBe;
        return stackViewState;
    }

    public final void hideFlyoutImmediate() {
        this.mFlyout.removeCallbacks(this.mAnimateInFlyout);
        BubbleStackView$$ExternalSyntheticLambda27 bubbleStackView$$ExternalSyntheticLambda27 = this.mAfterFlyoutHidden;
        if (bubbleStackView$$ExternalSyntheticLambda27 != null) {
            bubbleStackView$$ExternalSyntheticLambda27.run();
            this.mAfterFlyoutHidden = null;
        }
        this.mFlyout.removeCallbacks(this.mAnimateInFlyout);
        this.mFlyout.removeCallbacks(this.mHideFlyout);
        BubbleFlyoutView bubbleFlyoutView = this.mFlyout;
        BubbleStackView$$ExternalSyntheticLambda27 bubbleStackView$$ExternalSyntheticLambda272 = bubbleFlyoutView.mOnHide;
        if (bubbleStackView$$ExternalSyntheticLambda272 != null) {
            bubbleStackView$$ExternalSyntheticLambda272.run();
            bubbleFlyoutView.mOnHide = null;
        }
        bubbleFlyoutView.setVisibility(8);
    }

    public boolean isManageEduVisible() {
        ManageEducationView manageEducationView = this.mManageEduView;
        return manageEducationView != null && manageEducationView.getVisibility() == 0;
    }

    public boolean isManageMenuDontBubbleVisible() {
        ViewGroup viewGroup = this.mManageDontBubbleView;
        return viewGroup != null && viewGroup.getVisibility() == 0;
    }

    public boolean isManageMenuSettingsVisible() {
        ViewGroup viewGroup = this.mManageSettingsView;
        return viewGroup != null && viewGroup.getVisibility() == 0;
    }

    public boolean isStackEduVisible() {
        StackEducationView stackEducationView = this.mStackEduView;
        return stackEducationView != null && stackEducationView.getVisibility() == 0;
    }

    public final void logBubbleEvent(BubbleViewProvider bubbleViewProvider, int i) {
        String str = (bubbleViewProvider == null || !(bubbleViewProvider instanceof Bubble)) ? "null" : ((Bubble) bubbleViewProvider).mPackageName;
        BubbleData bubbleData = this.mBubbleData;
        int bubbleCount = getBubbleCount();
        int bubbleIndex = getBubbleIndex(bubbleViewProvider);
        BigDecimal bigDecimal = new BigDecimal(this.mPositioner.mPositionRect.width() > 0 ? this.mStackAnimationController.mStackPosition.x / r1 : 0.0f);
        RoundingMode roundingMode = RoundingMode.CEILING;
        RoundingMode roundingMode2 = RoundingMode.HALF_UP;
        float floatValue = bigDecimal.setScale(4, roundingMode2).floatValue();
        float floatValue2 = new BigDecimal(this.mPositioner.mPositionRect.height() > 0 ? this.mStackAnimationController.mStackPosition.y / r1 : 0.0f).setScale(4, roundingMode2).floatValue();
        BubbleLogger bubbleLogger = bubbleData.mLogger;
        if (bubbleViewProvider == null) {
            bubbleLogger.getClass();
            FrameworkStatsLog.write(149, str, (String) null, 0, 0, bubbleCount, i, floatValue, floatValue2, false, false, false);
        } else if (bubbleViewProvider.getKey().equals("Overflow")) {
            if (i == 3) {
                bubbleLogger.mUiEventLogger.log(BubbleLogger.Event.BUBBLE_OVERFLOW_SELECTED, bubbleData.mCurrentUserId, str);
            }
        } else {
            Bubble bubble = (Bubble) bubbleViewProvider;
            bubbleLogger.getClass();
            FrameworkStatsLog.write(149, str, bubble.mChannelId, bubble.mNotificationId, bubbleIndex, bubbleCount, i, floatValue, floatValue2, bubble.showInShade(), false, false);
        }
    }

    public final boolean maybeShowStackEdu() {
        if (!shouldShowStackEdu() || this.mIsExpanded) {
            return false;
        }
        if (this.mStackEduView == null) {
            BubbleStackViewManager$Companion$fromBubbleController$1 bubbleStackViewManager$Companion$fromBubbleController$1 = this.mManager;
            Objects.requireNonNull(bubbleStackViewManager$Companion$fromBubbleController$1);
            this.mStackEducationViewManager = new BubbleStackView$$ExternalSyntheticLambda36(bubbleStackViewManager$Companion$fromBubbleController$1);
            StackEducationView stackEducationView = new StackEducationView(((FrameLayout) this).mContext, this.mPositioner, this.mStackEducationViewManager);
            this.mStackEduView = stackEducationView;
            addView(stackEducationView);
        }
        return showStackEdu();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        WindowManager windowManager = (WindowManager) ((FrameLayout) this).mContext.getSystemService(WindowManager.class);
        BubblePositioner bubblePositioner = this.mPositioner;
        Context context = ((FrameLayout) this).mContext;
        Objects.requireNonNull(windowManager);
        bubblePositioner.update(DeviceConfig.create(context, windowManager));
        getViewTreeObserver().addOnComputeInternalInsetsListener(this);
        getViewTreeObserver().addOnDrawListener(this.mSystemGestureExcludeUpdater);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
    
        if (r1.mExpanded != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onComputeInternalInsets(android.view.ViewTreeObserver.InternalInsetsInfo r6) {
        /*
            r5 = this;
            r0 = 3
            r6.setTouchableInsets(r0)
            android.graphics.Rect r0 = r5.mTempRect
            r0.setEmpty()
            android.graphics.Rect r0 = r5.mTempRect
            boolean r1 = r5.isStackEduVisible()
            r2 = 0
            if (r1 == 0) goto L1e
            int r1 = r5.getWidth()
            int r3 = r5.getHeight()
            r0.set(r2, r2, r1, r3)
            goto L79
        L1e:
            boolean r1 = r5.mIsExpanded
            if (r1 != 0) goto L52
            int r1 = r5.getBubbleCount()
            if (r1 > 0) goto L32
            com.android.wm.shell.bubbles.BubbleData r1 = r5.mBubbleData
            boolean r3 = r1.mShowingOverflow
            if (r3 == 0) goto L64
            boolean r1 = r1.mExpanded
            if (r1 == 0) goto L64
        L32:
            com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout r1 = r5.mBubbleContainer
            android.view.View r1 = r1.getChildAt(r2)
            r1.getBoundsOnScreen(r0)
            int r1 = r0.top
            int r2 = r5.mBubbleTouchPadding
            int r1 = r1 - r2
            r0.top = r1
            int r1 = r0.left
            int r1 = r1 - r2
            r0.left = r1
            int r1 = r0.right
            int r1 = r1 + r2
            r0.right = r1
            int r1 = r0.bottom
            int r1 = r1 + r2
            r0.bottom = r1
            goto L64
        L52:
            com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout r1 = r5.mBubbleContainer
            r1.getBoundsOnScreen(r0)
            int r1 = r0.bottom
            com.android.wm.shell.bubbles.BubblePositioner r3 = r5.mPositioner
            boolean r4 = r3.mImeVisible
            if (r4 == 0) goto L61
            int r2 = r3.mImeHeight
        L61:
            int r1 = r1 - r2
            r0.bottom = r1
        L64:
            com.android.wm.shell.bubbles.BubbleFlyoutView r1 = r5.mFlyout
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L79
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            com.android.wm.shell.bubbles.BubbleFlyoutView r2 = r5.mFlyout
            r2.getBoundsOnScreen(r1)
            r0.union(r1)
        L79:
            android.graphics.Region r6 = r6.touchableRegion
            android.graphics.Rect r5 = r5.mTempRect
            r6.set(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.BubbleStackView.onComputeInternalInsets(android.view.ViewTreeObserver$InternalInsetsInfo):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnPreDrawListener(this.mViewUpdater);
        getViewTreeObserver().removeOnDrawListener(this.mSystemGestureExcludeUpdater);
        getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
    }

    public final void onDisplaySizeChanged() {
        int i = 1;
        updateOverflow();
        setUpFlyout();
        setUpDismissView();
        updateUserEdu();
        this.mBubbleSize = this.mPositioner.mBubbleSize;
        for (Bubble bubble : Collections.unmodifiableList(this.mBubbleData.mBubbles)) {
            BadgedImageView badgedImageView = bubble.mIconView;
            if (badgedImageView == null) {
                Log.w("Bubbles", "Display size changed. Icon null: " + bubble);
            } else {
                int i2 = this.mBubbleSize;
                badgedImageView.setLayoutParams(new FrameLayout.LayoutParams(i2, i2));
                BubbleExpandedView bubbleExpandedView = bubble.mExpandedView;
                if (bubbleExpandedView != null) {
                    bubbleExpandedView.updateDimensions();
                }
            }
        }
        if (this.mShowingOverflow) {
            BadgedImageView iconView$1 = this.mBubbleOverflow.getIconView$1();
            int i3 = this.mBubbleSize;
            iconView$1.setLayoutParams(new FrameLayout.LayoutParams(i3, i3));
        }
        this.mExpandedAnimationController.updateResources();
        StackAnimationController stackAnimationController = this.mStackAnimationController;
        PhysicsAnimationLayout physicsAnimationLayout = stackAnimationController.mLayout;
        if (physicsAnimationLayout != null) {
            stackAnimationController.mBubblePaddingTop = physicsAnimationLayout.getContext().getResources().getDimensionPixelSize(R.dimen.bubble_padding_top);
            PhysicsAnimationLayout physicsAnimationLayout2 = stackAnimationController.mLayout;
            if (physicsAnimationLayout2 != null && stackAnimationController.mMagnetizedStack != null) {
                int i4 = physicsAnimationLayout2.getResources().getDisplayMetrics().widthPixels;
                stackAnimationController.mMagnetizedStack.flingToTargetWidthPercent = i4 >= 2000 ? 6.0f : i4 >= 1500 ? 4.5f : 3.0f;
            }
        }
        this.mDismissView.updateResources();
        this.mMagneticTarget.magneticFieldRadiusPx = this.mBubbleSize * 2;
        if (!isStackEduVisible()) {
            StackAnimationController stackAnimationController2 = this.mStackAnimationController;
            PointF restingPosition = this.mPositioner.getRestingPosition();
            RectF allowableStackPositionRegion = this.mPositioner.getAllowableStackPositionRegion(getBubbleCount());
            boolean z = restingPosition.x < allowableStackPositionRegion.width() / 2.0f;
            float max = Math.max(0.0f, Math.min(1.0f, (restingPosition.y - allowableStackPositionRegion.top) / allowableStackPositionRegion.height()));
            RectF allowableStackPositionRegion2 = stackAnimationController2.mPositioner.getAllowableStackPositionRegion(stackAnimationController2.mBubbleCountSupplier.f$0.getBubbleCount());
            stackAnimationController2.setStackPosition(new PointF(z ? allowableStackPositionRegion2.left : allowableStackPositionRegion2.right, (allowableStackPositionRegion2.height() * max) + allowableStackPositionRegion2.top));
        }
        if (this.mIsExpanded) {
            updateExpandedView();
        }
        setUpManageMenu();
        if (this.mShowingManage) {
            post(new BubbleStackView$$ExternalSyntheticLambda2(this, i));
        }
    }

    public final void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        setupLocalMenu(accessibilityNodeInfo);
    }

    public final void onOrientationChanged() {
        this.mRelativeStackPositionBeforeRotation = new RelativeStackPosition(this.mPositioner.getRestingPosition(), this.mPositioner.getAllowableStackPositionRegion(getBubbleCount()));
        addOnLayoutChangeListener(this.mOrientationChangedListener);
        hideFlyoutImmediate();
    }

    public final boolean performAccessibilityActionInternal(int i, Bundle bundle) {
        if (super.performAccessibilityActionInternal(i, bundle)) {
            return true;
        }
        RectF allowableStackPositionRegion = this.mPositioner.getAllowableStackPositionRegion(getBubbleCount());
        if (i == 1048576) {
            this.mBubbleData.dismissAll(6);
            announceForAccessibility(getResources().getString(R.string.accessibility_bubble_dismissed));
            return true;
        }
        if (i == 524288) {
            this.mBubbleData.setExpanded(false);
            return true;
        }
        if (i == 262144) {
            this.mBubbleData.setExpanded(true);
            return true;
        }
        if (i == R.id.action_move_top_left) {
            this.mStackAnimationController.springStack(allowableStackPositionRegion.left, allowableStackPositionRegion.top, 700.0f);
            return true;
        }
        if (i == R.id.action_move_top_right) {
            this.mStackAnimationController.springStack(allowableStackPositionRegion.right, allowableStackPositionRegion.top, 700.0f);
            return true;
        }
        if (i == R.id.action_move_bottom_left) {
            this.mStackAnimationController.springStack(allowableStackPositionRegion.left, allowableStackPositionRegion.bottom, 700.0f);
            return true;
        }
        if (i != R.id.action_move_bottom_right) {
            return false;
        }
        this.mStackAnimationController.springStack(allowableStackPositionRegion.right, allowableStackPositionRegion.bottom, 700.0f);
        return true;
    }

    public final void releaseAnimatingOutBubbleBuffer() {
        ScreenCapture.ScreenshotHardwareBuffer screenshotHardwareBuffer = this.mAnimatingOutBubbleBuffer;
        if (screenshotHardwareBuffer == null || screenshotHardwareBuffer.getHardwareBuffer().isClosed()) {
            return;
        }
        this.mAnimatingOutBubbleBuffer.getHardwareBuffer().close();
    }

    public final void requestUpdate() {
        if (this.mViewUpdatedRequested || this.mIsExpansionAnimating) {
            return;
        }
        this.mViewUpdatedRequested = true;
        getViewTreeObserver().addOnPreDrawListener(this.mViewUpdater);
        invalidate();
    }

    public final void screenshotAnimatingOutBubbleIntoSurface(BubbleStackView$$ExternalSyntheticLambda37 bubbleStackView$$ExternalSyntheticLambda37) {
        float paddingLeft;
        int[] locationOnScreen;
        int i = 1;
        BubbleExpandedView expandedView = getExpandedView();
        if (!this.mIsExpanded || expandedView == null) {
            bubbleStackView$$ExternalSyntheticLambda37.accept(Boolean.FALSE);
            return;
        }
        if (this.mAnimatingOutBubbleBuffer != null) {
            releaseAnimatingOutBubbleBuffer();
        }
        try {
            this.mAnimatingOutBubbleBuffer = expandedView.snapshotActivitySurface();
        } catch (Exception e) {
            Log.wtf("Bubbles", e);
            bubbleStackView$$ExternalSyntheticLambda37.accept(Boolean.FALSE);
        }
        ScreenCapture.ScreenshotHardwareBuffer screenshotHardwareBuffer = this.mAnimatingOutBubbleBuffer;
        if (screenshotHardwareBuffer == null || screenshotHardwareBuffer.getHardwareBuffer() == null) {
            bubbleStackView$$ExternalSyntheticLambda37.accept(Boolean.FALSE);
            return;
        }
        FrameLayout frameLayout = this.mAnimatingOutSurfaceContainer;
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        PhysicsAnimator.Companion.getInstance(frameLayout).cancel();
        this.mAnimatingOutSurfaceContainer.setScaleX(1.0f);
        this.mAnimatingOutSurfaceContainer.setScaleY(1.0f);
        if (this.mPositioner.showBubblesVertically() && this.mStackOnLeftOrWillBe) {
            int paddingLeft2 = this.mExpandedViewContainer.getPaddingLeft();
            BubblePositioner bubblePositioner = this.mPositioner;
            paddingLeft = (bubblePositioner.mPointerHeight - bubblePositioner.mPointerOverlap) + paddingLeft2;
        } else {
            paddingLeft = this.mExpandedViewContainer.getPaddingLeft();
        }
        this.mAnimatingOutSurfaceContainer.setTranslationX(paddingLeft);
        this.mAnimatingOutSurfaceContainer.setTranslationY(0.0f);
        if (expandedView.mIsOverflow) {
            locationOnScreen = expandedView.mOverflowView.getLocationOnScreen();
        } else {
            TaskView taskView = expandedView.mTaskView;
            locationOnScreen = taskView != null ? taskView.getLocationOnScreen() : new int[]{0, 0};
        }
        this.mAnimatingOutSurfaceContainer.setTranslationY(locationOnScreen[1] - this.mAnimatingOutSurfaceView.getLocationOnScreen()[1]);
        this.mAnimatingOutSurfaceView.getLayoutParams().width = this.mAnimatingOutBubbleBuffer.getHardwareBuffer().getWidth();
        this.mAnimatingOutSurfaceView.getLayoutParams().height = this.mAnimatingOutBubbleBuffer.getHardwareBuffer().getHeight();
        this.mAnimatingOutSurfaceView.requestLayout();
        post(new BubbleStackView$$ExternalSyntheticLambda0(this, bubbleStackView$$ExternalSyntheticLambda37, i));
    }

    public final void setFlyoutStateForDragLength(float f) {
        if (this.mFlyout.getWidth() <= 0) {
            return;
        }
        boolean isStackOnLeftSide = this.mStackAnimationController.isStackOnLeftSide();
        this.mFlyoutDragDeltaX = f;
        if (isStackOnLeftSide) {
            f = -f;
        }
        float width = f / this.mFlyout.getWidth();
        float f2 = 0.0f;
        this.mFlyout.setCollapsePercent(Math.min(1.0f, Math.max(0.0f, width)));
        if (width < 0.0f || width > 1.0f) {
            boolean z = false;
            boolean z2 = width > 1.0f;
            if ((isStackOnLeftSide && width > 1.0f) || (!isStackOnLeftSide && width < 0.0f)) {
                z = true;
            }
            f2 = (this.mFlyout.getWidth() / (8.0f / (z2 ? 2 : 1))) * (z2 ? width - 1.0f : width * (-1.0f)) * (z ? -1 : 1);
        }
        BubbleFlyoutView bubbleFlyoutView = this.mFlyout;
        bubbleFlyoutView.setTranslationX(bubbleFlyoutView.mRestingTranslationX + f2);
    }

    public final void setSelectedBubble(BubbleViewProvider bubbleViewProvider) {
        if (bubbleViewProvider == null) {
            this.mBubbleData.mShowingOverflow = false;
            return;
        }
        if (this.mExpandedBubble == bubbleViewProvider) {
            return;
        }
        if (bubbleViewProvider.getKey().equals("Overflow")) {
            this.mBubbleData.mShowingOverflow = true;
        } else {
            this.mBubbleData.mShowingOverflow = false;
        }
        if (this.mIsExpanded && this.mIsExpansionAnimating) {
            ((HandlerExecutor) this.mMainExecutor).removeCallbacks(this.mDelayedAnimation);
            this.mIsExpansionAnimating = false;
            this.mIsBubbleSwitchAnimating = false;
            SurfaceView surfaceView = this.mAnimatingOutSurfaceView;
            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
            PhysicsAnimator.Companion.getInstance(surfaceView).cancel();
            PhysicsAnimator.Companion.getInstance(this.mExpandedViewContainerMatrix).cancel();
            this.mExpandedViewContainer.setAnimationMatrix(null);
        }
        showManageMenu(false);
        BubbleExpandedView expandedView = getExpandedView();
        if (!this.mIsExpanded || expandedView == null || this.mExpandedViewTemporarilyHidden) {
            showNewlySelectedBubble(bubbleViewProvider);
            return;
        }
        expandedView.setSurfaceZOrderedOnTop(true);
        try {
            screenshotAnimatingOutBubbleIntoSurface(new BubbleStackView$$ExternalSyntheticLambda37(this, bubbleViewProvider));
        } catch (Exception e) {
            showNewlySelectedBubble(bubbleViewProvider);
            e.printStackTrace();
        }
    }

    public final void setUpDismissView() {
        DismissView dismissView = this.mDismissView;
        if (dismissView != null) {
            removeView(dismissView);
        }
        DismissView dismissView2 = new DismissView(getContext());
        this.mDismissView = dismissView2;
        DismissViewUtils.setup(dismissView2);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bubble_elevation);
        addView(this.mDismissView);
        this.mDismissView.setElevation(dimensionPixelSize);
        this.mMagneticTarget = new MagnetizedObject.MagneticTarget(this.mDismissView.circle, Settings.Secure.getInt(getContext().getContentResolver(), "bubble_dismiss_radius", this.mBubbleSize * 2));
        this.mBubbleContainer.bringToFront();
    }

    public final void setUpFlyout() {
        BubbleFlyoutView bubbleFlyoutView = this.mFlyout;
        if (bubbleFlyoutView != null) {
            removeView(bubbleFlyoutView);
        }
        BubbleFlyoutView bubbleFlyoutView2 = new BubbleFlyoutView(getContext(), this.mPositioner);
        this.mFlyout = bubbleFlyoutView2;
        bubbleFlyoutView2.setVisibility(8);
        this.mFlyout.setOnClickListener(this.mFlyoutClickListener);
        this.mFlyout.setOnTouchListener(this.mFlyoutTouchListener);
        addView(this.mFlyout, new FrameLayout.LayoutParams(-2, -2));
    }

    public final void setUpManageMenu() {
        int i = 3;
        int i2 = 1;
        ViewGroup viewGroup = this.mManageMenu;
        if (viewGroup != null) {
            removeView(viewGroup);
        }
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.bubble_manage_menu, (ViewGroup) this, false);
        this.mManageMenu = viewGroup2;
        viewGroup2.setVisibility(4);
        TypedArray obtainStyledAttributes = ((FrameLayout) this).mContext.obtainStyledAttributes(new int[]{android.R.^attr-private.materialColorSurfaceBright});
        int color = obtainStyledAttributes.getColor(0, -1);
        obtainStyledAttributes.recycle();
        this.mManageMenu.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        ViewGroup viewGroup3 = this.mManageMenu;
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        PhysicsAnimator.Companion.getInstance(viewGroup3).defaultSpring = this.mManageSpringConfig;
        this.mManageMenu.setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.wm.shell.bubbles.BubbleStackView.15
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), BubbleStackView.this.mCornerRadius);
            }
        });
        this.mManageMenu.setClipToOutline(true);
        this.mManageMenu.findViewById(R.id.bubble_manage_menu_dismiss_container).setOnClickListener(new BubbleStackView$$ExternalSyntheticLambda12(this, i2));
        this.mManageMenu.findViewById(R.id.bubble_manage_menu_dont_bubble_container).setOnClickListener(new BubbleStackView$$ExternalSyntheticLambda12(this, 2));
        this.mManageDontBubbleView = (ViewGroup) this.mManageMenu.findViewById(R.id.bubble_manage_menu_dont_bubble_container);
        ViewGroup viewGroup4 = (ViewGroup) this.mManageMenu.findViewById(R.id.bubble_manage_menu_settings_container);
        this.mManageSettingsView = viewGroup4;
        viewGroup4.setOnClickListener(new BubbleStackView$$ExternalSyntheticLambda12(this, i));
        this.mManageSettingsIcon = (ImageView) this.mManageMenu.findViewById(R.id.bubble_manage_menu_settings_icon);
        this.mManageSettingsText = (TextView) this.mManageMenu.findViewById(R.id.bubble_manage_menu_settings_name);
        this.mManageMenu.setLayoutDirection(3);
        addView(this.mManageMenu);
    }

    public final void setupLocalMenu(AccessibilityNodeInfo accessibilityNodeInfo) {
        Resources resources = ((FrameLayout) this).mContext.getResources();
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_top_left, resources.getString(R.string.bubble_accessibility_action_move_top_left)));
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_top_right, resources.getString(R.string.bubble_accessibility_action_move_top_right)));
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_bottom_left, resources.getString(R.string.bubble_accessibility_action_move_bottom_left)));
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_bottom_right, resources.getString(R.string.bubble_accessibility_action_move_bottom_right)));
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_DISMISS);
        if (this.mIsExpanded) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COLLAPSE);
        } else {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
        }
    }

    public final boolean shouldShowStackEdu() {
        BubbleViewProvider bubbleViewProvider = this.mBubbleData.mSelectedBubble;
        boolean z = true;
        if (!((bubbleViewProvider instanceof Bubble) && ((Bubble) bubbleViewProvider).mShortcutInfo != null)) {
            return false;
        }
        Context context = ((FrameLayout) this).mContext;
        if (context.getSharedPreferences(context.getPackageName(), 0).getBoolean("HasSeenBubblesOnboarding", false) && !BubbleDebugConfig.forceShowUserEducation(((FrameLayout) this).mContext)) {
            z = false;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -2015221824233916174L, 3, Boolean.valueOf(z));
        }
        if (!z || !BubbleDebugConfig.neverShowUserEducation(((FrameLayout) this).mContext)) {
            return z;
        }
        Log.w("Bubbles", "Want to show stack edu, but it is forced hidden");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showManageMenu(boolean z) {
        float f;
        int i = 1;
        boolean z2 = false;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        if ((this.mManageMenu.getVisibility() == 0) == z) {
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            BubbleViewProvider bubbleViewProvider = this.mExpandedBubble;
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 8308446801302991999L, 3, Boolean.valueOf(z), String.valueOf(bubbleViewProvider != null ? bubbleViewProvider.getKey() : "null"));
        }
        this.mShowingManage = z;
        BubbleExpandedView expandedView = getExpandedView();
        if (expandedView == null) {
            this.mManageMenu.setVisibility(4);
            this.mManageMenuScrim.setVisibility(4);
            BubblesManager.AnonymousClass5 anonymousClass5 = this.mSysuiProxyProvider.mSysuiProxy;
            anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda9(anonymousClass5, anonymousClass5.val$sysUiState, z2, objArr2 == true ? 1 : 0));
            return;
        }
        if (z) {
            this.mManageMenuScrim.setVisibility(0);
            this.mManageMenuScrim.setTranslationZ(this.mManageMenu.getElevation() - 1.0f);
        }
        BubbleStackView$$ExternalSyntheticLambda3 bubbleStackView$$ExternalSyntheticLambda3 = new BubbleStackView$$ExternalSyntheticLambda3(this, z, i);
        BubblesManager.AnonymousClass5 anonymousClass52 = this.mSysuiProxyProvider.mSysuiProxy;
        anonymousClass52.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda9(anonymousClass52, anonymousClass52.val$sysUiState, z, objArr == true ? 1 : 0));
        this.mManageMenuScrim.animate().setInterpolator(z ? Interpolators.ALPHA_IN : Interpolators.ALPHA_OUT).alpha(z ? 0.32f : 0.0f).withEndAction(bubbleStackView$$ExternalSyntheticLambda3).start();
        if (z) {
            Bubble bubbleInStackWithKey = this.mBubbleData.getBubbleInStackWithKey(this.mExpandedBubble.getKey());
            if (bubbleInStackWithKey == null || bubbleInStackWithKey.mIsAppBubble) {
                this.mManageDontBubbleView.setVisibility(8);
                this.mManageSettingsView.setVisibility(8);
            } else {
                this.mManageDontBubbleView.setVisibility(0);
                this.mManageSettingsIcon.setImageBitmap(bubbleInStackWithKey.mRawBadgeBitmap);
                this.mManageSettingsText.setText(getResources().getString(R.string.bubbles_app_settings, bubbleInStackWithKey.mAppName));
                this.mManageSettingsView.setVisibility(0);
            }
        }
        TaskView taskView = expandedView.mTaskView;
        if (taskView != null) {
            Rect rect = this.mShowingManage ? new Rect(0, 0, getWidth(), getHeight()) : null;
            taskView.mObscuredTouchRegion = rect != null ? new Region(rect) : null;
        }
        Object[] objArr3 = getResources().getConfiguration().getLayoutDirection() == 0;
        expandedView.mManageButton.getBoundsOnScreen(this.mTempRect);
        float marginStart = ((LinearLayout.LayoutParams) expandedView.mManageButton.getLayoutParams()).getMarginStart();
        if (objArr3 == true) {
            f = this.mTempRect.left;
        } else {
            f = this.mTempRect.right + marginStart;
            marginStart = this.mManageMenu.getWidth();
        }
        float f2 = f - marginStart;
        float f3 = 0.0f;
        for (int i2 = 0; i2 < this.mManageMenu.getChildCount(); i2++) {
            if (this.mManageMenu.getChildAt(i2).getVisibility() == 0) {
                f3 += r11.getHeight();
            }
        }
        float f4 = this.mTempRect.bottom - f3;
        float width = (this.mManageMenu.getWidth() * (objArr3 != false ? 1 : -1)) / 4.0f;
        DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_Y;
        DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_X;
        DynamicAnimation.AnonymousClass1 anonymousClass13 = DynamicAnimation.SCALE_Y;
        DynamicAnimation.AnonymousClass1 anonymousClass14 = DynamicAnimation.SCALE_X;
        DynamicAnimation.AnonymousClass1 anonymousClass15 = DynamicAnimation.ALPHA;
        if (!z) {
            ViewGroup viewGroup = this.mManageMenu;
            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
            PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(viewGroup);
            companion.spring(anonymousClass15, 0.0f);
            companion.spring(anonymousClass14, 0.5f);
            companion.spring(anonymousClass13, 0.5f);
            companion.spring(anonymousClass12, f2 - width);
            companion.spring(anonymousClass1, (f3 / 4.0f) + f4);
            companion.withEndActions(new BubbleStackView$$ExternalSyntheticLambda2(this, 9));
            companion.start();
            return;
        }
        this.mManageMenu.setScaleX(0.5f);
        this.mManageMenu.setScaleY(0.5f);
        this.mManageMenu.setTranslationX(f2 - width);
        this.mManageMenu.setTranslationY((f3 / 4.0f) + f4);
        this.mManageMenu.setAlpha(0.0f);
        ViewGroup viewGroup2 = this.mManageMenu;
        Function2 function22 = PhysicsAnimator.onAnimatorCreated;
        PhysicsAnimator companion2 = PhysicsAnimator.Companion.getInstance(viewGroup2);
        companion2.spring(anonymousClass15, 1.0f);
        companion2.spring(anonymousClass14, 1.0f);
        companion2.spring(anonymousClass13, 1.0f);
        companion2.spring(anonymousClass12, f2);
        companion2.spring(anonymousClass1, f4);
        companion2.withEndActions(new BubbleStackView$$ExternalSyntheticLambda2(this, 8));
        companion2.start();
        this.mManageMenu.setVisibility(0);
    }

    public final void showNewlySelectedBubble(final BubbleViewProvider bubbleViewProvider) {
        final BubbleViewProvider bubbleViewProvider2 = this.mExpandedBubble;
        this.mExpandedBubble = bubbleViewProvider;
        ExpandedViewAnimationControllerImpl expandedViewAnimationControllerImpl = this.mExpandedViewAnimationController;
        BubbleExpandedView expandedView = getExpandedView();
        if (expandedViewAnimationControllerImpl.mExpandedView != null) {
            AnimatorSet animatorSet = expandedViewAnimationControllerImpl.mCollapseAnimation;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            SpringAnimation springAnimation = expandedViewAnimationControllerImpl.mBackToExpandedAnimation;
            if (springAnimation != null) {
                springAnimation.cancel();
            }
            expandedViewAnimationControllerImpl.reset();
        }
        expandedViewAnimationControllerImpl.mExpandedView = expandedView;
        String key = bubbleViewProvider2 != null ? bubbleViewProvider2.getKey() : "null";
        BubbleViewProvider bubbleViewProvider3 = this.mExpandedBubble;
        String key2 = bubbleViewProvider3 != null ? bubbleViewProvider3.getKey() : "null";
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 6917798123885378812L, 48, String.valueOf(key2), String.valueOf(key), Boolean.valueOf(this.mIsExpanded));
        }
        if (this.mIsExpanded) {
            this.mManager.$controller.hideCurrentInputMethod();
            this.mExpandedViewContainer.setAlpha(0.0f);
            AnonymousClass1 anonymousClass1 = this.mSurfaceSynchronizer;
            Runnable runnable = new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda46
                @Override // java.lang.Runnable
                public final void run() {
                    BubbleStackView bubbleStackView = BubbleStackView.this;
                    BubbleViewProvider bubbleViewProvider4 = bubbleViewProvider2;
                    BubbleViewProvider bubbleViewProvider5 = bubbleViewProvider;
                    if (bubbleViewProvider4 != null) {
                        bubbleStackView.getClass();
                        bubbleViewProvider4.setTaskViewVisibility();
                    }
                    bubbleStackView.updateExpandedBubble();
                    bubbleStackView.requestUpdate();
                    bubbleStackView.logBubbleEvent(bubbleViewProvider4, 4);
                    bubbleStackView.logBubbleEvent(bubbleViewProvider5, 3);
                    BubbleController$$ExternalSyntheticLambda2 bubbleController$$ExternalSyntheticLambda2 = bubbleStackView.mExpandListener;
                    if (bubbleController$$ExternalSyntheticLambda2 != null && bubbleViewProvider4 != null) {
                        bubbleController$$ExternalSyntheticLambda2.onBubbleExpandChanged(bubbleViewProvider4.getKey(), false);
                    }
                    BubbleController$$ExternalSyntheticLambda2 bubbleController$$ExternalSyntheticLambda22 = bubbleStackView.mExpandListener;
                    if (bubbleController$$ExternalSyntheticLambda22 == null || bubbleViewProvider5 == null) {
                        return;
                    }
                    bubbleController$$ExternalSyntheticLambda22.onBubbleExpandChanged(bubbleViewProvider5.getKey(), true);
                }
            };
            anonymousClass1.getClass();
            Choreographer.getInstance().postFrameCallback(new AnonymousClass1.ChoreographerFrameCallbackC02691(runnable));
        }
    }

    public final void showScrim(boolean z, final BubbleStackView$$ExternalSyntheticLambda38 bubbleStackView$$ExternalSyntheticLambda38) {
        AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.android.wm.shell.bubbles.BubbleStackView.18
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                BubbleStackView.this.mScrimAnimation = null;
                BubbleStackView$$ExternalSyntheticLambda38 bubbleStackView$$ExternalSyntheticLambda382 = bubbleStackView$$ExternalSyntheticLambda38;
                if (bubbleStackView$$ExternalSyntheticLambda382 != null) {
                    bubbleStackView$$ExternalSyntheticLambda382.run();
                }
            }
        };
        ViewPropertyAnimator viewPropertyAnimator = this.mScrimAnimation;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        if (z) {
            ViewPropertyAnimator animate = this.mScrim.animate();
            this.mScrimAnimation = animate;
            animate.setInterpolator(Interpolators.ALPHA_IN).alpha(0.32f).setListener(animatorListenerAdapter).start();
        } else {
            ViewPropertyAnimator animate2 = this.mScrim.animate();
            this.mScrimAnimation = animate2;
            animate2.alpha(0.0f).setInterpolator(Interpolators.ALPHA_OUT).setListener(animatorListenerAdapter).start();
        }
    }

    public final boolean showStackEdu() {
        this.mBubbleContainer.bringToFront();
        final PointF startPosition = this.mPositioner.getStartPosition(this.mStackAnimationController.isStackOnLeftSide() ? BubblePositioner.StackPinnedEdge.LEFT : BubblePositioner.StackPinnedEdge.RIGHT);
        this.mStackAnimationController.springStack(startPosition.x, startPosition.y, 700.0f);
        final StackEducationView stackEducationView = this.mStackEduView;
        stackEducationView.isHiding = false;
        if (stackEducationView.getVisibility() == 0) {
            return false;
        }
        stackEducationView.manager.updateWindowFlagsForBackpress(true);
        ViewGroup.LayoutParams layoutParams = stackEducationView.getLayoutParams();
        DeviceConfig deviceConfig = stackEducationView.positioner.mDeviceConfig;
        layoutParams.width = (deviceConfig.isLargeScreen || deviceConfig.isLandscape) ? stackEducationView.getContext().getResources().getDimensionPixelSize(R.dimen.bubbles_user_education_width) : -1;
        final boolean isStackOnLeft = stackEducationView.positioner.isStackOnLeft(startPosition);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ((View) stackEducationView.view$delegate.getValue()).getLayoutParams();
        int dimensionPixelSize = stackEducationView.getResources().getDimensionPixelSize(R.dimen.bubble_user_education_margin_horizontal);
        marginLayoutParams.leftMargin = isStackOnLeft ? 0 : dimensionPixelSize;
        if (!isStackOnLeft) {
            dimensionPixelSize = 0;
        }
        marginLayoutParams.rightMargin = dimensionPixelSize;
        final int dimensionPixelSize2 = stackEducationView.getContext().getResources().getDimensionPixelSize(R.dimen.bubble_user_education_stack_padding);
        stackEducationView.setAlpha(0.0f);
        stackEducationView.setVisibility(0);
        ((View) stackEducationView.view$delegate.getValue()).setBackgroundResource(isStackOnLeft ? R.drawable.bubble_stack_user_education_bg : R.drawable.bubble_stack_user_education_bg_rtl);
        stackEducationView.post(new Runnable() { // from class: com.android.wm.shell.bubbles.StackEducationView$show$2
            @Override // java.lang.Runnable
            public final void run() {
                StackEducationView.this.requestFocus();
                View view = (View) StackEducationView.this.view$delegate.getValue();
                boolean z = isStackOnLeft;
                StackEducationView stackEducationView2 = StackEducationView.this;
                int i = dimensionPixelSize2;
                PointF pointF = startPosition;
                if (z) {
                    view.setPadding(stackEducationView2.positioner.mBubbleSize + i, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                    view.setTranslationX(0.0f);
                } else {
                    view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), stackEducationView2.positioner.mBubbleSize + i, view.getPaddingBottom());
                    view.setTranslationX((stackEducationView2.positioner.mScreenRect.right - view.getWidth()) - i);
                }
                view.setTranslationY((pointF.y + (stackEducationView2.positioner.mBubbleSize / 2)) - (view.getHeight() / 2));
                StackEducationView.this.animate().setDuration(200L).setInterpolator(Interpolators.FAST_OUT_SLOW_IN).alpha(1.0f);
            }
        });
        stackEducationView.getContext().getSharedPreferences(stackEducationView.getContext().getPackageName(), 0).edit().putBoolean("HasSeenBubblesOnboarding", true).apply();
        return true;
    }

    public final void startMonitoringSwipeUpGesture() {
        stopMonitoringSwipeUpGestureInternal();
        if (((FrameLayout) this).mContext.getResources().getInteger(android.R.integer.config_navBarInteractionMode) == 2) {
            Context context = ((FrameLayout) this).mContext;
            BubblePositioner bubblePositioner = this.mPositioner;
            BubblesNavBarGestureTracker bubblesNavBarGestureTracker = new BubblesNavBarGestureTracker();
            this.mBubblesNavBarGestureTracker = bubblesNavBarGestureTracker;
            AnonymousClass4 anonymousClass4 = this.mSwipeUpListener;
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 6702023880190540565L, 0, null);
            }
            BubblesNavBarInputEventReceiver bubblesNavBarInputEventReceiver = bubblesNavBarGestureTracker.mInputEventReceiver;
            if (bubblesNavBarInputEventReceiver != null) {
                bubblesNavBarInputEventReceiver.dispose();
                bubblesNavBarGestureTracker.mInputEventReceiver = null;
            }
            InputMonitor inputMonitor = bubblesNavBarGestureTracker.mInputMonitor;
            if (inputMonitor != null) {
                inputMonitor.dispose();
                bubblesNavBarGestureTracker.mInputMonitor = null;
            }
            InputMonitor monitorGestureInput = ((InputManager) context.getSystemService(InputManager.class)).monitorGestureInput("bubbles-gesture", context.getDisplayId());
            bubblesNavBarGestureTracker.mInputMonitor = monitorGestureInput;
            bubblesNavBarGestureTracker.mInputEventReceiver = new BubblesNavBarInputEventReceiver(monitorGestureInput.getInputChannel(), Choreographer.getInstance(), new BubblesNavBarMotionEventHandler(context, bubblePositioner, new BubblesNavBarGestureTracker$$ExternalSyntheticLambda0(bubblesNavBarGestureTracker), anonymousClass4));
            setOnTouchListener(this.mContainerSwipeListener);
        }
    }

    public final void stopMonitoringSwipeUpGestureInternal() {
        BubblesNavBarGestureTracker bubblesNavBarGestureTracker = this.mBubblesNavBarGestureTracker;
        if (bubblesNavBarGestureTracker != null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -3631850868647903454L, 0, null);
            }
            BubblesNavBarInputEventReceiver bubblesNavBarInputEventReceiver = bubblesNavBarGestureTracker.mInputEventReceiver;
            if (bubblesNavBarInputEventReceiver != null) {
                bubblesNavBarInputEventReceiver.dispose();
                bubblesNavBarGestureTracker.mInputEventReceiver = null;
            }
            InputMonitor inputMonitor = bubblesNavBarGestureTracker.mInputMonitor;
            if (inputMonitor != null) {
                inputMonitor.dispose();
                bubblesNavBarGestureTracker.mInputMonitor = null;
            }
            this.mBubblesNavBarGestureTracker = null;
            setOnTouchListener(null);
        }
    }

    public final void updateBadges(boolean z) {
        int bubbleCount = getBubbleCount();
        for (int i = 0; i < bubbleCount; i++) {
            BadgedImageView badgedImageView = (BadgedImageView) this.mBubbleContainer.getChildAt(i);
            if (this.mIsExpanded) {
                badgedImageView.showDotAndBadge(this.mPositioner.showBubblesVertically() && !this.mStackOnLeftOrWillBe);
            } else if (z) {
                if (i == 0) {
                    badgedImageView.showDotAndBadge(!this.mStackOnLeftOrWillBe);
                } else {
                    badgedImageView.hideDotAndBadge(!this.mStackOnLeftOrWillBe);
                }
            }
        }
    }

    public final void updateBubbleOrderInternal(List list, boolean z) {
        boolean z2;
        int i = 0;
        final BubbleStackView$$ExternalSyntheticLambda25 bubbleStackView$$ExternalSyntheticLambda25 = new BubbleStackView$$ExternalSyntheticLambda25(i, this, list);
        if (this.mIsExpanded || this.mIsExpansionAnimating) {
            bubbleStackView$$ExternalSyntheticLambda25.run();
            updateBadges(false);
            updateBubbleShadows(true);
        } else {
            List list2 = (List) list.stream().map(new BubbleStackView$$ExternalSyntheticLambda26()).collect(Collectors.toList());
            final StackAnimationController stackAnimationController = this.mStackAnimationController;
            stackAnimationController.getClass();
            final StackAnimationController$$ExternalSyntheticLambda2 stackAnimationController$$ExternalSyntheticLambda2 = new StackAnimationController$$ExternalSyntheticLambda2(i, stackAnimationController, list2);
            boolean z3 = false;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                final View view = (View) list2.get(i2);
                if (view != null) {
                    if (i2 == stackAnimationController.mLayout.indexOfChild(view)) {
                        stackAnimationController.moveToFinalIndex(view, i2, bubbleStackView$$ExternalSyntheticLambda25);
                        z2 = false;
                    } else {
                        if (i2 == 0) {
                            view.setTag(R.id.reorder_animator_tag, view.animate().translationY(stackAnimationController.mStackPosition.y - stackAnimationController.mSwapAnimationOffset).setDuration(300L).withEndAction(new Runnable() { // from class: com.android.wm.shell.bubbles.animation.StackAnimationController$$ExternalSyntheticLambda6
                                @Override // java.lang.Runnable
                                public final void run() {
                                    StackAnimationController stackAnimationController2 = StackAnimationController.this;
                                    StackAnimationController$$ExternalSyntheticLambda2 stackAnimationController$$ExternalSyntheticLambda22 = stackAnimationController$$ExternalSyntheticLambda2;
                                    View view2 = view;
                                    BubbleStackView$$ExternalSyntheticLambda25 bubbleStackView$$ExternalSyntheticLambda252 = bubbleStackView$$ExternalSyntheticLambda25;
                                    stackAnimationController2.getClass();
                                    stackAnimationController$$ExternalSyntheticLambda22.run();
                                    stackAnimationController2.moveToFinalIndex(view2, 0, bubbleStackView$$ExternalSyntheticLambda252);
                                }
                            }));
                        } else {
                            stackAnimationController.moveToFinalIndex(view, i2, bubbleStackView$$ExternalSyntheticLambda25);
                        }
                        z2 = true;
                    }
                    z3 |= z2;
                }
            }
            if (!z3) {
                stackAnimationController$$ExternalSyntheticLambda2.run();
            }
        }
        if (z) {
            updatePointerPosition(false);
        }
    }

    public final void updateBubbleShadows(boolean z) {
        int i;
        int childCount = this.mBubbleContainer.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            BadgedImageView badgedImageView = (BadgedImageView) this.mBubbleContainer.getChildAt(i2);
            BubbleViewProvider bubbleViewProvider = badgedImageView.mBubble;
            boolean equals = "Overflow".equals(bubbleViewProvider != null ? bubbleViewProvider.getKey() : null);
            MagnetizedObject magnetizedObject = this.mMagnetizedObject;
            if (magnetizedObject == null || !magnetizedObject.underlyingObject.equals(badgedImageView)) {
                BubblePositioner bubblePositioner = this.mPositioner;
                bubblePositioner.getClass();
                float f = 0.0f;
                if (!equals) {
                    if (z) {
                        i = bubblePositioner.mMaxBubbles;
                    } else if (i2 < 2) {
                        i = bubblePositioner.mMaxBubbles * bubblePositioner.mBubbleElevation;
                    }
                    f = i - i2;
                }
                badgedImageView.setZ(f);
            } else {
                badgedImageView.setZ((this.mPositioner.mMaxBubbles * this.mBubbleElevation) + 1);
            }
        }
    }

    public final void updateExpandedBubble() {
        this.mExpandedViewContainer.removeAllViews();
        BubbleExpandedView expandedView = getExpandedView();
        if (!this.mIsExpanded || expandedView == null) {
            return;
        }
        expandedView.setContentVisibility(false);
        expandedView.setAnimating(!this.mIsExpansionAnimating);
        this.mExpandedViewContainerMatrix.setScaleX(0.0f);
        this.mExpandedViewContainerMatrix.setScaleY(0.0f);
        this.mExpandedViewContainerMatrix.setTranslate(0.0f, 0.0f);
        this.mExpandedViewContainer.setVisibility(4);
        this.mExpandedViewContainer.setAlpha(0.0f);
        this.mExpandedViewContainer.addView(expandedView);
        if (this.mIsExpansionAnimating) {
            return;
        }
        this.mIsBubbleSwitchAnimating = true;
        AnonymousClass1 anonymousClass1 = this.mSurfaceSynchronizer;
        BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = new BubbleStackView$$ExternalSyntheticLambda2(this, 13);
        anonymousClass1.getClass();
        Choreographer.getInstance().postFrameCallback(new AnonymousClass1.ChoreographerFrameCallbackC02691(bubbleStackView$$ExternalSyntheticLambda2));
    }

    public final void updateExpandedView() {
        BubbleViewProvider bubbleViewProvider = this.mExpandedBubble;
        int[] expandedViewContainerPadding = this.mPositioner.getExpandedViewContainerPadding(this.mStackAnimationController.isStackOnLeftSide(), bubbleViewProvider != null && "Overflow".equals(bubbleViewProvider.getKey()));
        this.mExpandedViewContainer.setPadding(expandedViewContainerPadding[0], expandedViewContainerPadding[1], expandedViewContainerPadding[2], expandedViewContainerPadding[3]);
        BubbleExpandedView expandedView = getExpandedView();
        if (expandedView != null) {
            PointF expandedBubbleXY = this.mPositioner.getExpandedBubbleXY(getBubbleIndex(this.mExpandedBubble), getState());
            FrameLayout frameLayout = this.mExpandedViewContainer;
            BubblePositioner bubblePositioner = this.mPositioner;
            frameLayout.setTranslationY(bubblePositioner.getExpandedViewY(this.mExpandedBubble, bubblePositioner.showBubblesVertically() ? expandedBubbleXY.y : expandedBubbleXY.x));
            this.mExpandedViewContainer.setTranslationX(0.0f);
            if (expandedView.mTaskView != null) {
                boolean isStackOnLeft = expandedView.mPositioner.isStackOnLeft(expandedView.mStackView.mStackAnimationController.mStackPosition);
                BubblePositioner bubblePositioner2 = expandedView.mPositioner;
                int[] expandedViewContainerPadding2 = bubblePositioner2.getExpandedViewContainerPadding(isStackOnLeft, false);
                int width = ((bubblePositioner2.mScreenRect.width() - expandedViewContainerPadding2[0]) - expandedViewContainerPadding2[2]) - (bubblePositioner2.showBubblesVertically() ? bubblePositioner2.mPointerHeight - bubblePositioner2.mPointerOverlap : 0);
                if (expandedView.mTaskView.getWidth() != width) {
                    expandedView.mTaskView.setLayoutParams(new FrameLayout.LayoutParams(width, -1));
                }
            }
            expandedView.mExpandedViewContainerLocation = this.mExpandedViewContainer.getLocationOnScreen();
            expandedView.updateHeight();
            TaskView taskView = expandedView.mTaskView;
            if (taskView != null && taskView.getVisibility() == 0 && expandedView.mTaskView.isAttachedToWindow()) {
                expandedView.post(new BubbleExpandedView$$ExternalSyntheticLambda2(expandedView, 1));
            }
            if (expandedView.mIsOverflow) {
                expandedView.post(new BubbleExpandedView$$ExternalSyntheticLambda2(expandedView, 2));
            }
            updatePointerPosition(false);
        }
        this.mStackOnLeftOrWillBe = this.mStackAnimationController.isStackOnLeftSide();
    }

    public final void updateOverflow() {
        BubbleOverflow bubbleOverflow = this.mBubbleOverflow;
        bubbleOverflow.updateResources();
        BubbleExpandedView bubbleExpandedView = bubbleOverflow.expandedView;
        if (bubbleExpandedView != null) {
            bubbleExpandedView.applyThemeAttrs();
        }
        BubbleBarExpandedView bubbleBarExpandedView = bubbleOverflow.bubbleBarExpandedView;
        if (bubbleBarExpandedView != null) {
            bubbleBarExpandedView.applyThemeAttrs();
        }
        BadgedImageView iconView$1 = bubbleOverflow.getIconView$1();
        if (iconView$1 != null) {
            iconView$1.mBubbleIcon.setImageResource(R.drawable.bubble_ic_overflow_button);
        }
        bubbleOverflow.updateBtnTheme();
        if (this.mShowingOverflow) {
            this.mBubbleContainer.reorderView(this.mBubbleOverflow.getIconView$1(), this.mBubbleContainer.getChildCount() - 1);
        }
        updateOverflowVisibility();
    }

    public final void updateOverflowDotVisibility(boolean z) {
        if (this.mShowingOverflow) {
            BubbleOverflow bubbleOverflow = this.mBubbleOverflow;
            if (bubbleOverflow.showDot) {
                bubbleOverflow.getIconView$1().animateDotScale(z ? 1.0f : 0.0f, new BubbleStackView$$ExternalSyntheticLambda3(this, z, 0));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
    
        if (r0.mExpanded != false) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateOverflowVisibility() {
        /*
            r2 = this;
            boolean r0 = r2.mShowingOverflow
            if (r0 == 0) goto L14
            boolean r0 = r2.mIsExpanded
            if (r0 != 0) goto L12
            com.android.wm.shell.bubbles.BubbleData r0 = r2.mBubbleData
            boolean r1 = r0.mShowingOverflow
            if (r1 == 0) goto L14
            boolean r0 = r0.mExpanded
            if (r0 == 0) goto L14
        L12:
            r0 = 0
            goto L16
        L14:
            r0 = 8
        L16:
            com.android.wm.shell.bubbles.BubbleOverflow r2 = r2.mBubbleOverflow
            com.android.wm.shell.bubbles.BadgedImageView r2 = r2.overflowBtn
            if (r2 != 0) goto L1d
            goto L20
        L1d:
            r2.setVisibility(r0)
        L20:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.BubbleStackView.updateOverflowVisibility():void");
    }

    public final void updatePointerPosition(boolean z) {
        int bubbleIndex;
        BubbleExpandedView expandedView = getExpandedView();
        BubbleViewProvider bubbleViewProvider = this.mExpandedBubble;
        if (bubbleViewProvider == null || expandedView == null || (bubbleIndex = getBubbleIndex(bubbleViewProvider)) == -1) {
            return;
        }
        PointF expandedBubbleXY = this.mPositioner.getExpandedBubbleXY(bubbleIndex, getState());
        expandedView.setPointerPosition(this.mPositioner.showBubblesVertically() ? expandedBubbleXY.y : expandedBubbleXY.x, this.mStackOnLeftOrWillBe, z);
    }

    public final void updateTemporarilyInvisibleAnimation(boolean z) {
        removeCallbacks(this.mAnimateTemporarilyInvisibleImmediate);
        if (this.mIsDraggingStack) {
            return;
        }
        postDelayed(this.mAnimateTemporarilyInvisibleImmediate, (!(this.mTemporarilyInvisible && this.mFlyout.getVisibility() != 0) || z) ? 0L : 1000L);
    }

    public final void updateUserEdu() {
        if (isStackEduVisible()) {
            StackEducationView stackEducationView = this.mStackEduView;
            if (!stackEducationView.isHiding) {
                removeView(stackEducationView);
                BubbleStackViewManager$Companion$fromBubbleController$1 bubbleStackViewManager$Companion$fromBubbleController$1 = this.mManager;
                Objects.requireNonNull(bubbleStackViewManager$Companion$fromBubbleController$1);
                this.mStackEducationViewManager = new BubbleStackView$$ExternalSyntheticLambda36(bubbleStackViewManager$Companion$fromBubbleController$1);
                StackEducationView stackEducationView2 = new StackEducationView(((FrameLayout) this).mContext, this.mPositioner, this.mStackEducationViewManager);
                this.mStackEduView = stackEducationView2;
                addView(stackEducationView2);
                showStackEdu();
            }
        }
        if (isManageEduVisible()) {
            removeView(this.mManageEduView);
            ManageEducationView manageEducationView = new ManageEducationView(((FrameLayout) this).mContext, this.mPositioner);
            this.mManageEduView = manageEducationView;
            addView(manageEducationView);
            BubbleExpandedView expandedView = getExpandedView();
            if (expandedView == null) {
                return;
            }
            this.mManageEduView.show(expandedView, this.mStackAnimationController.isStackOnLeftSide());
        }
    }
}
