package com.android.wm.shell.bubbles.bar;

import android.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.android.systemui.wmshell.BubblesManager;
import com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda9;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda14;
import com.android.wm.shell.bubbles.BubbleData;
import com.android.wm.shell.bubbles.BubbleEducationController;
import com.android.wm.shell.bubbles.BubbleExpandedViewManager$Companion$fromBubbleController$1;
import com.android.wm.shell.bubbles.BubblePositioner;
import com.android.wm.shell.bubbles.BubbleViewProvider;
import com.android.wm.shell.bubbles.DeviceConfig;
import com.android.wm.shell.bubbles.DismissViewUtils;
import com.android.wm.shell.bubbles.animation.AnimatableScaleMatrix;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.bubbles.BubblePopupDrawable;
import com.android.wm.shell.shared.bubbles.BubblePopupView;
import com.android.wm.shell.shared.bubbles.DismissView;
import com.android.wm.shell.taskview.TaskView;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleBarLayerView extends FrameLayout implements ViewTreeObserver.OnComputeInternalInsetsListener {
    public final BubbleBarAnimationHelper mAnimationHelper;
    public final BubbleController mBubbleController;
    public final BubbleData mBubbleData;
    public final BubbleExpandedViewPinController mBubbleExpandedViewPinController;
    public final DismissView mDismissView;
    public BubbleBarExpandedViewDragController mDragController;
    public final BubbleEducationViewController mEducationViewController;
    public BubbleViewProvider mExpandedBubble;
    public BubbleBarExpandedView mExpandedView;
    public final Rect mHandleTouchBounds;
    public boolean mIsExpanded;
    public final BubblePositioner mPositioner;
    public final View mScrimView;
    public final Rect mTempRect;
    public final Region mTouchableRegion;
    public BubbleController$$ExternalSyntheticLambda14 mUnBubbleConversationCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.bar.BubbleBarLayerView$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.bar.BubbleBarLayerView$2, reason: invalid class name */
    public final class AnonymousClass2 {
        public final /* synthetic */ BubbleViewProvider val$b;

        public AnonymousClass2(BubbleViewProvider bubbleViewProvider) {
            this.val$b = bubbleViewProvider;
        }
    }

    public BubbleBarLayerView(Context context, BubbleController bubbleController, BubbleData bubbleData) {
        super(context);
        this.mIsExpanded = false;
        this.mTouchableRegion = new Region();
        this.mTempRect = new Rect();
        this.mHandleTouchBounds = new Rect();
        this.mBubbleController = bubbleController;
        this.mBubbleData = bubbleData;
        BubblePositioner positioner = bubbleController.getPositioner();
        this.mPositioner = positioner;
        this.mAnimationHelper = new BubbleBarAnimationHelper(positioner);
        this.mEducationViewController = new BubbleEducationViewController(context, new BubbleBarLayerView$$ExternalSyntheticLambda1(this));
        View view = new View(getContext());
        this.mScrimView = view;
        view.setImportantForAccessibility(2);
        view.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.system_neutral1_1000)));
        addView(view);
        view.setAlpha(0.0f);
        view.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.system_neutral1_1000)));
        DismissView dismissView = this.mDismissView;
        if (dismissView != null) {
            removeView(dismissView);
        }
        DismissView dismissView2 = new DismissView(getContext());
        this.mDismissView = dismissView2;
        DismissViewUtils.setup(dismissView2);
        addView(this.mDismissView);
        BubbleExpandedViewPinController bubbleExpandedViewPinController = new BubbleExpandedViewPinController(context, this, positioner);
        this.mBubbleExpandedViewPinController = bubbleExpandedViewPinController;
        bubbleExpandedViewPinController.listener = new AnonymousClass1();
        setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarLayerView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BubbleBarLayerView.this.hideModalOrCollapse();
            }
        });
    }

    public final void collapse(BubbleBarLayerView$$ExternalSyntheticLambda7 bubbleBarLayerView$$ExternalSyntheticLambda7) {
        int i = 1;
        if (!this.mIsExpanded) {
            if (bubbleBarLayerView$$ExternalSyntheticLambda7 != null) {
                bubbleBarLayerView$$ExternalSyntheticLambda7.run();
                return;
            }
            return;
        }
        boolean z = false;
        this.mIsExpanded = false;
        BubbleBarExpandedView bubbleBarExpandedView = this.mExpandedView;
        BubbleEducationViewController bubbleEducationViewController = this.mEducationViewController;
        bubbleEducationViewController.getClass();
        BubbleEducationViewController.hideEducation$default(bubbleEducationViewController, true);
        final BubbleBarLayerView$$ExternalSyntheticLambda5 bubbleBarLayerView$$ExternalSyntheticLambda5 = new BubbleBarLayerView$$ExternalSyntheticLambda5(this, bubbleBarExpandedView, bubbleBarLayerView$$ExternalSyntheticLambda7);
        BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController = this.mDragController;
        if (bubbleBarExpandedViewDragController == null || !bubbleBarExpandedViewDragController.isStuckToDismiss) {
            BubbleBarAnimationHelper bubbleBarAnimationHelper = this.mAnimationHelper;
            bubbleBarAnimationHelper.mIsExpanded = false;
            final BubbleBarExpandedView expandedView = bubbleBarAnimationHelper.getExpandedView();
            if (expandedView == null) {
                Log.w("BubbleBarAnimationHelper", "Trying to animate collapse without a bubble");
            } else {
                expandedView.setScaleX(1.0f);
                expandedView.setScaleY(1.0f);
                BubblePositioner bubblePositioner = bubbleBarAnimationHelper.mPositioner;
                Rect rect = bubblePositioner.mPositionRect;
                float f = bubblePositioner.isBubbleBarOnLeft() ? rect.left : rect.right;
                float f2 = bubblePositioner.mBubbleBarTopOnScreen;
                AnimatableScaleMatrix animatableScaleMatrix = bubbleBarAnimationHelper.mExpandedViewContainerMatrix;
                animatableScaleMatrix.setScale(1.0f, 1.0f, f, f2);
                Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                PhysicsAnimator.Companion.getInstance(animatableScaleMatrix).cancel();
                PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(animatableScaleMatrix);
                AnimatableScaleMatrix.AnonymousClass1 anonymousClass1 = AnimatableScaleMatrix.SCALE_X;
                PhysicsAnimator.SpringConfig springConfig = bubbleBarAnimationHelper.mScaleOutSpringConfig;
                companion.spring(anonymousClass1, 374.99997f, 0.0f, springConfig);
                companion.spring(AnimatableScaleMatrix.SCALE_Y, 374.99997f, 0.0f, springConfig);
                companion.updateListeners.add(new BubbleBarAnimationHelper$$ExternalSyntheticLambda0(bubbleBarAnimationHelper, expandedView, i));
                companion.withEndActions(new Runnable() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarAnimationHelper$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        BubbleBarExpandedView bubbleBarExpandedView2 = BubbleBarExpandedView.this;
                        BubbleBarLayerView$$ExternalSyntheticLambda5 bubbleBarLayerView$$ExternalSyntheticLambda52 = bubbleBarLayerView$$ExternalSyntheticLambda5;
                        bubbleBarExpandedView2.setAnimationMatrix(null);
                        bubbleBarLayerView$$ExternalSyntheticLambda52.run();
                    }
                });
                companion.start();
                bubbleBarAnimationHelper.mExpandedViewAlphaAnimator.reverse();
            }
        } else {
            BubbleBarAnimationHelper bubbleBarAnimationHelper2 = this.mAnimationHelper;
            bubbleBarAnimationHelper2.mIsExpanded = false;
            BubbleBarExpandedView expandedView2 = bubbleBarAnimationHelper2.getExpandedView();
            if (expandedView2 == null) {
                Log.w("BubbleBarAnimationHelper", "Trying to animate dismiss without a bubble");
            } else {
                int i2 = bubbleBarAnimationHelper2.mPositioner.mScreenRect.bottom - expandedView2.getLocationOnScreen()[1];
                bubbleBarAnimationHelper2.cancelAnimations();
                expandedView2.animate().translationYBy(i2 * 2).setDuration(250L).withEndAction(bubbleBarLayerView$$ExternalSyntheticLambda5).start();
            }
        }
        BubblesManager.AnonymousClass5 anonymousClass5 = this.mBubbleController.mSysuiProxy;
        anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda9(anonymousClass5, anonymousClass5.val$sysUiState, z, i));
        this.mExpandedView = null;
        this.mDragController = null;
        setTouchDelegate(null);
        showScrim(false);
    }

    public final void hideModalOrCollapse() {
        BubbleBarExpandedView bubbleBarExpandedView;
        BubbleEducationViewController bubbleEducationViewController = this.mEducationViewController;
        if ((bubbleEducationViewController.educationView == null || bubbleEducationViewController.rootView == null) ? false : true) {
            bubbleEducationViewController.getClass();
            BubbleEducationViewController.hideEducation$default(bubbleEducationViewController, true);
            return;
        }
        if (this.mIsExpanded && (bubbleBarExpandedView = this.mExpandedView) != null) {
            BubbleBarMenuView bubbleBarMenuView = bubbleBarExpandedView.mMenuViewController.mMenuView;
            if (bubbleBarMenuView != null && bubbleBarMenuView.getVisibility() == 0) {
                bubbleBarExpandedView.mMenuViewController.hideMenu(true);
                return;
            }
            BubbleBarExpandedView bubbleBarExpandedView2 = this.mExpandedView;
            if (bubbleBarExpandedView2.mPositioner.mImeVisible) {
                ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleBarExpandedView2.mManager).$controller.hideCurrentInputMethod();
                return;
            }
        }
        this.mBubbleController.collapseStack();
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
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
    
        if (r1.rootView != null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onComputeInternalInsets(android.view.ViewTreeObserver.InternalInsetsInfo r4) {
        /*
            r3 = this;
            r0 = 3
            r4.setTouchableInsets(r0)
            android.graphics.Region r0 = r3.mTouchableRegion
            r0.setEmpty()
            android.graphics.Region r0 = r3.mTouchableRegion
            android.graphics.Rect r1 = r3.mTempRect
            r1.setEmpty()
            boolean r1 = r3.mIsExpanded
            if (r1 != 0) goto L1e
            com.android.wm.shell.bubbles.bar.BubbleEducationViewController r1 = r3.mEducationViewController
            com.android.wm.shell.shared.bubbles.BubblePopupView r2 = r1.educationView
            if (r2 == 0) goto L2a
            android.view.ViewGroup r1 = r1.rootView
            if (r1 == 0) goto L2a
        L1e:
            android.graphics.Rect r1 = r3.mTempRect
            r3.getBoundsOnScreen(r1)
            android.graphics.Rect r1 = r3.mTempRect
            android.graphics.Region$Op r2 = android.graphics.Region.Op.UNION
            r0.op(r1, r2)
        L2a:
            android.graphics.Region r4 = r4.touchableRegion
            android.graphics.Region r3 = r3.mTouchableRegion
            r4.set(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.bar.BubbleBarLayerView.onComputeInternalInsets(android.view.ViewTreeObserver$InternalInsetsInfo):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
        if (this.mExpandedView != null) {
            BubbleEducationViewController bubbleEducationViewController = this.mEducationViewController;
            bubbleEducationViewController.getClass();
            BubbleEducationViewController.hideEducation$default(bubbleEducationViewController, false);
            removeView(this.mExpandedView);
            this.mExpandedView = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void showExpandedView(BubbleViewProvider bubbleViewProvider) {
        boolean z = true;
        char c = 1;
        int i = 0;
        BubbleBarExpandedView bubbleBarExpandedView = bubbleViewProvider.getBubbleBarExpandedView();
        if (bubbleBarExpandedView == null) {
            return;
        }
        if (this.mExpandedBubble != null && this.mIsExpanded && bubbleViewProvider.getKey().equals(this.mExpandedBubble.getKey())) {
            return;
        }
        if (this.mExpandedBubble != null && !bubbleViewProvider.getKey().equals(this.mExpandedBubble.getKey())) {
            removeView(this.mExpandedView);
            this.mExpandedView = null;
        }
        if (this.mExpandedView == null) {
            if (bubbleBarExpandedView.getParent() != null) {
                this.mAnimationHelper.cancelAnimations();
                removeView(bubbleBarExpandedView);
            }
            this.mExpandedBubble = bubbleViewProvider;
            this.mExpandedView = bubbleBarExpandedView;
            boolean equals = bubbleViewProvider.getKey().equals("Overflow");
            BubblePositioner bubblePositioner = this.mPositioner;
            int i2 = equals ? bubblePositioner.mOverflowWidth : bubblePositioner.mExpandedViewLargeScreenWidth;
            int expandedViewHeightForBubbleBar = bubblePositioner.getExpandedViewHeightForBubbleBar(equals);
            this.mExpandedView.setVisibility(8);
            BubbleBarExpandedView bubbleBarExpandedView2 = this.mExpandedView;
            BubblePositioner bubblePositioner2 = this.mPositioner;
            bubbleBarExpandedView2.setY((bubblePositioner2.mBubbleBarTopOnScreen - bubblePositioner2.mExpandedViewPadding) - expandedViewHeightForBubbleBar);
            BubbleBarExpandedView bubbleBarExpandedView3 = this.mExpandedView;
            bubbleBarExpandedView3.mLayerBoundsSupplier = new BubbleBarLayerView$$ExternalSyntheticLambda0(this);
            bubbleBarExpandedView3.mListener = new AnonymousClass2(bubbleViewProvider);
            this.mDragController = new BubbleBarExpandedViewDragController(bubbleBarExpandedView3, this.mDismissView, this.mAnimationHelper, this.mPositioner, this.mBubbleExpandedViewPinController, new BubbleBarLayerView$$ExternalSyntheticLambda1(this));
            addView(this.mExpandedView, new FrameLayout.LayoutParams(i2, expandedViewHeightForBubbleBar, 3));
        }
        BubbleEducationViewController bubbleEducationViewController = this.mEducationViewController;
        if (((bubbleEducationViewController.educationView == null || bubbleEducationViewController.rootView == null) ? false : true) != false) {
            bubbleEducationViewController.getClass();
            BubbleEducationViewController.hideEducation$default(bubbleEducationViewController, true);
        }
        this.mIsExpanded = true;
        BubblesManager.AnonymousClass5 anonymousClass5 = this.mBubbleController.mSysuiProxy;
        anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda9(anonymousClass5, anonymousClass5.val$sysUiState, z, c == true ? 1 : 0));
        final BubbleBarAnimationHelper bubbleBarAnimationHelper = this.mAnimationHelper;
        BubbleViewProvider bubbleViewProvider2 = this.mExpandedBubble;
        final BubbleBarLayerView$$ExternalSyntheticLambda2 bubbleBarLayerView$$ExternalSyntheticLambda2 = new BubbleBarLayerView$$ExternalSyntheticLambda2(this);
        bubbleBarAnimationHelper.mExpandedBubble = bubbleViewProvider2;
        final BubbleBarExpandedView expandedView = bubbleBarAnimationHelper.getExpandedView();
        if (expandedView != null) {
            bubbleBarAnimationHelper.mIsExpanded = true;
            AnimatableScaleMatrix animatableScaleMatrix = bubbleBarAnimationHelper.mExpandedViewContainerMatrix;
            animatableScaleMatrix.setScaleX(0.0f);
            animatableScaleMatrix.setScaleY(0.0f);
            bubbleBarAnimationHelper.updateExpandedView();
            expandedView.setAnimating(true);
            expandedView.setContentVisibility(false);
            expandedView.setAlpha(0.0f);
            TaskView taskView = expandedView.mTaskView;
            if (taskView != null) {
                taskView.setAlpha(0.0f);
            }
            expandedView.setAlpha(0.0f);
            expandedView.setVisibility(0);
            BubblePositioner bubblePositioner3 = bubbleBarAnimationHelper.mPositioner;
            Rect rect = bubblePositioner3.mPositionRect;
            animatableScaleMatrix.setScale(0.9f, 0.9f, bubblePositioner3.isBubbleBarOnLeft() ? rect.left : rect.right, bubblePositioner3.mBubbleBarTopOnScreen);
            expandedView.setAnimationMatrix(animatableScaleMatrix);
            bubbleBarAnimationHelper.mExpandedViewAlphaAnimator.start();
            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
            PhysicsAnimator.Companion.getInstance(animatableScaleMatrix).cancel();
            PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(animatableScaleMatrix);
            AnimatableScaleMatrix.AnonymousClass1 anonymousClass1 = AnimatableScaleMatrix.SCALE_X;
            PhysicsAnimator.SpringConfig springConfig = bubbleBarAnimationHelper.mScaleInSpringConfig;
            companion.spring(anonymousClass1, 499.99997f, 0.0f, springConfig);
            companion.spring(AnimatableScaleMatrix.SCALE_Y, 499.99997f, 0.0f, springConfig);
            companion.updateListeners.add(new BubbleBarAnimationHelper$$ExternalSyntheticLambda0(bubbleBarAnimationHelper, expandedView, i));
            companion.withEndActions(new Runnable() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarAnimationHelper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BubbleBarAnimationHelper bubbleBarAnimationHelper2 = BubbleBarAnimationHelper.this;
                    BubbleBarExpandedView bubbleBarExpandedView4 = expandedView;
                    BubbleBarLayerView$$ExternalSyntheticLambda2 bubbleBarLayerView$$ExternalSyntheticLambda22 = bubbleBarLayerView$$ExternalSyntheticLambda2;
                    bubbleBarAnimationHelper2.getClass();
                    bubbleBarExpandedView4.setAnimationMatrix(null);
                    bubbleBarAnimationHelper2.updateExpandedView();
                    TaskView taskView2 = bubbleBarExpandedView4.mTaskView;
                    if (taskView2 != null) {
                        taskView2.setZOrderedOnTop(false, true);
                    }
                    bubbleBarLayerView$$ExternalSyntheticLambda22.run();
                }
            });
            companion.start();
        }
        showScrim(true);
    }

    public final void showScrim(boolean z) {
        if (z) {
            this.mScrimView.animate().setInterpolator(Interpolators.ALPHA_IN).alpha(0.2f).start();
        } else {
            this.mScrimView.animate().alpha(0.0f).setInterpolator(Interpolators.ALPHA_OUT).start();
        }
    }

    public final void showUserEducation(Point point) {
        BubblePopupDrawable.Config config;
        BubblePopupDrawable.Config config2;
        final BubbleEducationViewController bubbleEducationViewController = this.mEducationViewController;
        int i = 0;
        BubbleBarLayerView$$ExternalSyntheticLambda6 bubbleBarLayerView$$ExternalSyntheticLambda6 = new BubbleBarLayerView$$ExternalSyntheticLambda6(this, i);
        bubbleEducationViewController.getClass();
        BubbleEducationViewController.hideEducation$default(bubbleEducationViewController, false);
        BubblePopupView createEducationView = bubbleEducationViewController.createEducationView(com.android.wm.shell.R.layout.bubble_bar_stack_education, this);
        BubblePopupDrawable.ArrowDirection arrowDirection = BubblePopupDrawable.ArrowDirection.DOWN;
        BubblePopupDrawable bubblePopupDrawable = createEducationView.popupDrawable;
        if (bubblePopupDrawable != null) {
            bubblePopupDrawable.arrowDirection$delegate.setValue(bubblePopupDrawable, BubblePopupDrawable.$$delegatedProperties[0], arrowDirection);
            Rect rect = new Rect();
            bubblePopupDrawable.getPadding(rect);
            createEducationView.setPadding(rect.left, rect.top, rect.right, rect.bottom);
        }
        BubblePopupDrawable.ArrowPosition.End end = BubblePopupDrawable.ArrowPosition.End.INSTANCE;
        BubblePopupDrawable bubblePopupDrawable2 = createEducationView.popupDrawable;
        if (bubblePopupDrawable2 != null) {
            bubblePopupDrawable2.arrowPosition$delegate.setValue(bubblePopupDrawable2, BubblePopupDrawable.$$delegatedProperties[1], end);
            createEducationView.invalidate();
        }
        Rect rect2 = new Rect();
        getBoundsOnScreen(rect2);
        BubblePopupDrawable bubblePopupDrawable3 = createEducationView.popupDrawable;
        if (bubblePopupDrawable3 != null && (config2 = bubblePopupDrawable3.config) != null) {
            i = MathKt.roundToInt((config2.arrowWidth / 2.0f) + config2.cornerRadius);
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) createEducationView.getLayoutParams();
        layoutParams.bottomMargin = rect2.bottom - point.y;
        layoutParams.rightMargin = (rect2.right - point.x) - i;
        createEducationView.setLayoutParams(layoutParams);
        BubblePopupDrawable bubblePopupDrawable4 = createEducationView.popupDrawable;
        final float f = (bubblePopupDrawable4 == null || (config = bubblePopupDrawable4.config) == null) ? 0.0f : config.cornerRadius;
        if (!createEducationView.isLaidOut() || createEducationView.isLayoutRequested()) {
            createEducationView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleEducationViewController$showStackEducation$lambda$1$$inlined$doOnLayout$1
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    view.removeOnLayoutChangeListener(this);
                    view.setPivotX(view.getWidth() - f);
                    view.setPivotY(view.getHeight());
                }
            });
        } else {
            createEducationView.setPivotX(createEducationView.getWidth() - f);
            createEducationView.setPivotY(createEducationView.getHeight());
        }
        createEducationView.setOnClickListener(new BubbleEducationViewController$scrimView$2$1$1(2, bubbleBarLayerView$$ExternalSyntheticLambda6));
        bubbleEducationViewController.educationView = createEducationView;
        bubbleEducationViewController.rootView = this;
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(createEducationView);
        companion.defaultSpring = (PhysicsAnimator.SpringConfig) bubbleEducationViewController.springConfig$delegate.getValue();
        bubbleEducationViewController.animator = companion;
        addView((View) bubbleEducationViewController.scrimView$delegate.getValue());
        addView(bubbleEducationViewController.educationView);
        bubbleEducationViewController.animateTransition(new Function0() { // from class: com.android.wm.shell.bubbles.bar.BubbleEducationViewController$showStackEducation$3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TaskView taskView;
                BubbleBarLayerView$$ExternalSyntheticLambda0 bubbleBarLayerView$$ExternalSyntheticLambda0;
                SharedPreferences.Editor edit = ((BubbleEducationController) BubbleEducationViewController.this.controller$delegate.getValue()).prefs.edit();
                edit.putBoolean("HasSeenBubblesOnboarding", true);
                edit.apply();
                BubbleBarExpandedView bubbleBarExpandedView = BubbleEducationViewController.this.listener.f$0.mExpandedView;
                if (bubbleBarExpandedView != null && (taskView = bubbleBarExpandedView.mTaskView) != null && (bubbleBarLayerView$$ExternalSyntheticLambda0 = bubbleBarExpandedView.mLayerBoundsSupplier) != null) {
                    taskView.mObscuredTouchRegion = new Region((Rect) bubbleBarLayerView$$ExternalSyntheticLambda0.get());
                }
                return Unit.INSTANCE;
            }
        }, true);
    }

    public final void updateExpandedView() {
        BubbleViewProvider bubbleViewProvider;
        if (this.mExpandedView == null || (bubbleViewProvider = this.mExpandedBubble) == null) {
            return;
        }
        boolean equals = bubbleViewProvider.getKey().equals("Overflow");
        BubblePositioner bubblePositioner = this.mPositioner;
        bubblePositioner.getBubbleBarExpandedViewBounds(bubblePositioner.isBubbleBarOnLeft(), equals, this.mTempRect);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mExpandedView.getLayoutParams();
        layoutParams.width = this.mTempRect.width();
        layoutParams.height = this.mTempRect.height();
        this.mExpandedView.setLayoutParams(layoutParams);
        this.mExpandedView.setX(this.mTempRect.left);
        this.mExpandedView.setY(this.mTempRect.top);
        TaskView taskView = this.mExpandedView.mTaskView;
        if (taskView != null) {
            taskView.onLocationChanged();
        }
    }
}
