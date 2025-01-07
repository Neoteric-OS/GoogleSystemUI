package com.android.wm.shell.bubbles.bar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.Bubble;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda14;
import com.android.wm.shell.bubbles.BubbleDebugConfig;
import com.android.wm.shell.bubbles.BubbleEducationController;
import com.android.wm.shell.bubbles.BubbleExpandedViewManager;
import com.android.wm.shell.bubbles.BubbleExpandedViewManager$Companion$fromBubbleController$1;
import com.android.wm.shell.bubbles.BubbleOverflowContainerView;
import com.android.wm.shell.bubbles.BubblePositioner;
import com.android.wm.shell.bubbles.BubbleTaskView;
import com.android.wm.shell.bubbles.BubbleTaskViewHelper;
import com.android.wm.shell.bubbles.BubbleViewInfoTask;
import com.android.wm.shell.bubbles.BubbleViewProvider;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.bubbles.bar.BubbleBarMenuView;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.bubbles.BubbleBarLocation;
import com.android.wm.shell.shared.bubbles.BubblePopupView;
import com.android.wm.shell.shared.handles.RegionSamplingHelper;
import com.android.wm.shell.taskview.TaskView;
import com.android.wm.shell.taskview.TaskViewTaskController;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BubbleBarExpandedView extends FrameLayout {
    public static final AnonymousClass1 CORNER_RADIUS = new AnonymousClass1("cornerRadius");
    public Executor mBackgroundExecutor;
    public Bubble mBubble;
    public BubbleTaskViewHelper mBubbleTaskViewHelper;
    public int mCaptionHeight;
    public float mCurrentCornerRadius;
    public float mDraggedCornerRadius;
    public BubbleBarHandleView mHandleView;
    public boolean mIsAnimating;
    public boolean mIsContentVisible;
    public boolean mIsDragging;
    public BubbleBarLayerView$$ExternalSyntheticLambda0 mLayerBoundsSupplier;
    public BubbleBarLayerView.AnonymousClass2 mListener;
    public final int[] mLoc;
    public Executor mMainExecutor;
    public BubbleExpandedViewManager mManager;
    public BubbleBarMenuViewController mMenuViewController;
    public BubbleOverflowContainerView mOverflowView;
    public BubblePositioner mPositioner;
    public RegionSamplingHelper mRegionSamplingHelper;
    public BubbleViewInfoTask.AnonymousClass1 mRegionSamplingProvider;
    public float mRestingCornerRadius;
    public final Rect mSampleRect;
    public TaskView mTaskView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.bar.BubbleBarExpandedView$1, reason: invalid class name */
    public final class AnonymousClass1 extends FloatProperty {
        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((BubbleBarExpandedView) obj).mCurrentCornerRadius);
        }

        @Override // android.util.FloatProperty
        public final void setValue(Object obj, float f) {
            BubbleBarExpandedView bubbleBarExpandedView = (BubbleBarExpandedView) obj;
            if (bubbleBarExpandedView.mCurrentCornerRadius != f) {
                bubbleBarExpandedView.mCurrentCornerRadius = f;
                TaskView taskView = bubbleBarExpandedView.mTaskView;
                if (taskView != null) {
                    taskView.setCornerRadius(f);
                }
                bubbleBarExpandedView.invalidateOutline();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.bar.BubbleBarExpandedView$3, reason: invalid class name */
    public final class AnonymousClass3 implements RegionSamplingHelper.SamplingCallback {
        public /* synthetic */ AnonymousClass3() {
        }

        @Override // com.android.wm.shell.shared.handles.RegionSamplingHelper.SamplingCallback
        public Rect getSampledRegion() {
            return BubbleBarExpandedView.this.getCaptionSampleRect();
        }

        @Override // com.android.wm.shell.shared.handles.RegionSamplingHelper.SamplingCallback
        public boolean isSamplingEnabled() {
            AnonymousClass1 anonymousClass1 = BubbleBarExpandedView.CORNER_RADIUS;
            return BubbleBarExpandedView.this.shouldSampleRegion();
        }

        public void onMenuVisibilityChanged(boolean z) {
            BubbleBarLayerView$$ExternalSyntheticLambda0 bubbleBarLayerView$$ExternalSyntheticLambda0;
            BubbleBarExpandedView bubbleBarExpandedView = BubbleBarExpandedView.this;
            TaskView taskView = bubbleBarExpandedView.mTaskView;
            if (taskView != null && (bubbleBarLayerView$$ExternalSyntheticLambda0 = bubbleBarExpandedView.mLayerBoundsSupplier) != null) {
                Rect rect = z ? (Rect) bubbleBarLayerView$$ExternalSyntheticLambda0.get() : null;
                taskView.mObscuredTouchRegion = rect != null ? new Region(rect) : null;
            }
            if (z) {
                bubbleBarExpandedView.mHandleView.setFocusable(false);
                bubbleBarExpandedView.mHandleView.setImportantForAccessibility(2);
            } else {
                bubbleBarExpandedView.mHandleView.setFocusable(true);
                bubbleBarExpandedView.mHandleView.setImportantForAccessibility(0);
            }
        }

        @Override // com.android.wm.shell.shared.handles.RegionSamplingHelper.SamplingCallback
        public void onRegionDarknessChanged(boolean z) {
            BubbleBarHandleView bubbleBarHandleView = BubbleBarExpandedView.this.mHandleView;
            if (bubbleBarHandleView != null) {
                int i = z ? bubbleBarHandleView.mHandleLightColor : bubbleBarHandleView.mHandleDarkColor;
                if (i == bubbleBarHandleView.mCurrentColor) {
                    return;
                }
                ObjectAnimator objectAnimator = bubbleBarHandleView.mColorChangeAnim;
                if (objectAnimator != null) {
                    objectAnimator.cancel();
                }
                bubbleBarHandleView.mCurrentColor = i;
                ObjectAnimator ofArgb = ObjectAnimator.ofArgb(bubbleBarHandleView, "backgroundColor", i);
                bubbleBarHandleView.mColorChangeAnim = ofArgb;
                ofArgb.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarHandleView.2
                    public AnonymousClass2() {
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        BubbleBarHandleView.this.mColorChangeAnim = null;
                    }
                });
                bubbleBarHandleView.mColorChangeAnim.setDuration(120L);
                bubbleBarHandleView.mColorChangeAnim.start();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HandleViewAccessibilityDelegate extends View.AccessibilityDelegate {
        public HandleViewAccessibilityDelegate() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, BubbleBarExpandedView.this.getResources().getString(R.string.bubble_accessibility_action_expand_menu)));
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COLLAPSE);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_DISMISS);
            if (BubbleBarExpandedView.this.mPositioner.isBubbleBarOnLeft()) {
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_bubble_bar_right, BubbleBarExpandedView.this.getResources().getString(R.string.bubble_accessibility_action_move_bar_right)));
            } else {
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_bubble_bar_left, BubbleBarExpandedView.this.getResources().getString(R.string.bubble_accessibility_action_move_bar_left)));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            if (i == 524288) {
                ((BubbleExpandedViewManager$Companion$fromBubbleController$1) BubbleBarExpandedView.this.mManager).$controller.collapseStack();
                return true;
            }
            if (i == 1048576) {
                BubbleBarExpandedView bubbleBarExpandedView = BubbleBarExpandedView.this;
                BubbleExpandedViewManager bubbleExpandedViewManager = bubbleBarExpandedView.mManager;
                Bubble bubble = bubbleBarExpandedView.mBubble;
                BubbleController bubbleController = ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleExpandedViewManager).$controller;
                bubbleController.getClass();
                bubbleController.mBubbleData.dismissBubbleWithKey(1, bubble.mKey);
                return true;
            }
            if (i == R.id.action_move_bubble_bar_left) {
                BubbleExpandedViewManager bubbleExpandedViewManager2 = BubbleBarExpandedView.this.mManager;
                ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleExpandedViewManager2).$controller.setBubbleBarLocation(BubbleBarLocation.LEFT);
                return true;
            }
            if (i != R.id.action_move_bubble_bar_right) {
                return false;
            }
            BubbleExpandedViewManager bubbleExpandedViewManager3 = BubbleBarExpandedView.this.mManager;
            ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleExpandedViewManager3).$controller.setBubbleBarLocation(BubbleBarLocation.RIGHT);
            return true;
        }
    }

    public BubbleBarExpandedView(Context context) {
        this(context, null);
    }

    public final void applyThemeAttrs() {
        this.mCaptionHeight = getResources().getDimensionPixelSize(R.dimen.bubble_bar_expanded_view_caption_height);
        this.mRestingCornerRadius = getResources().getDimensionPixelSize(R.dimen.bubble_bar_expanded_view_corner_radius);
        this.mDraggedCornerRadius = getResources().getDimensionPixelSize(R.dimen.bubble_bar_expanded_view_corner_radius_dragged);
        float f = this.mRestingCornerRadius;
        this.mCurrentCornerRadius = f;
        TaskView taskView = this.mTaskView;
        if (taskView != null) {
            taskView.setCornerRadius(f);
            TaskView taskView2 = this.mTaskView;
            Insets of = Insets.of(0, this.mCaptionHeight, 0, 0);
            taskView2.mCaptionInsets = of;
            if (of == null) {
                TaskViewTaskController taskViewTaskController = taskView2.mTaskViewTaskController;
                Rect rect = taskViewTaskController.mCaptionInsets;
                if (rect == null || !rect.equals(null)) {
                    taskViewTaskController.mCaptionInsets = null;
                    taskViewTaskController.applyCaptionInsetsIfNeeded();
                }
            }
        }
    }

    public final Rect getCaptionSampleRect() {
        TaskView taskView = this.mTaskView;
        if (taskView == null) {
            return null;
        }
        taskView.getLocationOnScreen(this.mLoc);
        Rect rect = this.mSampleRect;
        int[] iArr = this.mLoc;
        int i = iArr[0];
        rect.set(i, iArr[1], this.mTaskView.getWidth() + i, this.mLoc[1] + this.mCaptionHeight);
        return this.mSampleRect;
    }

    public RegionSamplingHelper getRegionSamplingHelper() {
        return this.mRegionSamplingHelper;
    }

    public final void initialize(BubbleExpandedViewManager bubbleExpandedViewManager, BubblePositioner bubblePositioner, boolean z, BubbleTaskView bubbleTaskView, Executor executor, Executor executor2, BubbleViewInfoTask.AnonymousClass1 anonymousClass1) {
        TaskViewTaskController taskViewTaskController;
        Rect rect;
        this.mManager = bubbleExpandedViewManager;
        this.mPositioner = bubblePositioner;
        this.mMainExecutor = executor;
        this.mBackgroundExecutor = executor2;
        this.mRegionSamplingProvider = anonymousClass1;
        if (z) {
            BubbleOverflowContainerView bubbleOverflowContainerView = (BubbleOverflowContainerView) LayoutInflater.from(getContext()).inflate(R.layout.bubble_overflow_container, (ViewGroup) null);
            this.mOverflowView = bubbleOverflowContainerView;
            bubbleOverflowContainerView.mExpandedViewManager = bubbleExpandedViewManager;
            bubbleOverflowContainerView.mPositioner = bubblePositioner;
            addView(bubbleOverflowContainerView);
            this.mHandleView.setVisibility(8);
        } else {
            this.mTaskView = bubbleTaskView.taskView;
            this.mBubbleTaskViewHelper = new BubbleTaskViewHelper(((FrameLayout) this).mContext, bubbleExpandedViewManager, this, bubbleTaskView, this);
            if (this.mTaskView.getParent() != null) {
                ((ViewGroup) this.mTaskView.getParent()).removeView(this.mTaskView);
            }
            addView(this.mTaskView, new FrameLayout.LayoutParams(-1, -1));
            this.mTaskView.setEnableSurfaceClipping(true);
            this.mTaskView.setCornerRadius(this.mCurrentCornerRadius);
            this.mTaskView.setVisibility(0);
            TaskView taskView = this.mTaskView;
            Insets of = Insets.of(0, this.mCaptionHeight, 0, 0);
            taskView.mCaptionInsets = of;
            if (of == null && ((rect = (taskViewTaskController = taskView.mTaskViewTaskController).mCaptionInsets) == null || !rect.equals(null))) {
                taskViewTaskController.mCaptionInsets = null;
                taskViewTaskController.applyCaptionInsetsIfNeeded();
            }
            bringChildToFront(this.mHandleView);
            this.mHandleView.setAccessibilityDelegate(new HandleViewAccessibilityDelegate());
        }
        BubbleBarMenuViewController bubbleBarMenuViewController = new BubbleBarMenuViewController(((FrameLayout) this).mContext, this);
        this.mMenuViewController = bubbleBarMenuViewController;
        bubbleBarMenuViewController.mListener = new AnonymousClass3();
        this.mHandleView.setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarExpandedView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                final int i = 1;
                final int i2 = 2;
                final int i3 = 0;
                final BubbleBarMenuViewController bubbleBarMenuViewController2 = BubbleBarExpandedView.this.mMenuViewController;
                if (bubbleBarMenuViewController2.mMenuView == null || bubbleBarMenuViewController2.mScrimView == null) {
                    LayoutInflater from = LayoutInflater.from(bubbleBarMenuViewController2.mContext);
                    BubbleBarExpandedView bubbleBarExpandedView = bubbleBarMenuViewController2.mRootView;
                    BubbleBarMenuView bubbleBarMenuView = (BubbleBarMenuView) from.inflate(R.layout.bubble_bar_menu_view, (ViewGroup) bubbleBarExpandedView, false);
                    bubbleBarMenuViewController2.mMenuView = bubbleBarMenuView;
                    bubbleBarMenuView.setAlpha(0.0f);
                    bubbleBarMenuViewController2.mMenuView.setPivotY(0.0f);
                    bubbleBarMenuViewController2.mMenuView.setScaleY(0.5f);
                    BubbleBarMenuView bubbleBarMenuView2 = bubbleBarMenuViewController2.mMenuView;
                    final BubbleBarMenuViewController$$ExternalSyntheticLambda3 bubbleBarMenuViewController$$ExternalSyntheticLambda3 = new BubbleBarMenuViewController$$ExternalSyntheticLambda3(bubbleBarMenuViewController2, i2);
                    bubbleBarMenuView2.mBubbleSectionView.setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarMenuView$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            BubbleBarMenuViewController$$ExternalSyntheticLambda3 bubbleBarMenuViewController$$ExternalSyntheticLambda32 = BubbleBarMenuViewController$$ExternalSyntheticLambda3.this;
                            int i4 = BubbleBarMenuView.$r8$clinit;
                            bubbleBarMenuViewController$$ExternalSyntheticLambda32.run();
                        }
                    });
                    Bubble bubble = bubbleBarMenuViewController2.mBubble;
                    if (bubble != null) {
                        BubbleBarMenuView bubbleBarMenuView3 = bubbleBarMenuViewController2.mMenuView;
                        bubbleBarMenuView3.getClass();
                        Icon icon = bubble.mIcon;
                        if (icon != null) {
                            bubbleBarMenuView3.mBubbleIconView.setImageIcon(icon);
                        } else {
                            bubbleBarMenuView3.mBubbleIconView.setImageBitmap(bubble.mBubbleBitmap);
                        }
                        bubbleBarMenuView3.mBubbleTitleView.setText(bubble.mTitle);
                        BubbleBarMenuView bubbleBarMenuView4 = bubbleBarMenuViewController2.mMenuView;
                        final Bubble bubble2 = bubbleBarMenuViewController2.mBubble;
                        ArrayList arrayList = new ArrayList();
                        Resources resources = bubbleBarMenuViewController2.mContext.getResources();
                        TypedArray obtainStyledAttributes = bubbleBarMenuViewController2.mContext.obtainStyledAttributes(new int[]{android.R.^attr-private.materialColorOnSurface});
                        try {
                            int color = obtainStyledAttributes.getColor(0, 0);
                            obtainStyledAttributes.close();
                            if (bubble2.mShortcutInfo != null) {
                                arrayList.add(new BubbleBarMenuView.MenuAction(Icon.createWithResource(bubbleBarMenuViewController2.mContext, R.drawable.bubble_ic_stop_bubble), resources.getString(R.string.bubbles_dont_bubble_conversation), color, new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarMenuViewController$$ExternalSyntheticLambda0
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view2) {
                                        BubbleBarLayerView.AnonymousClass2 anonymousClass2;
                                        Context context;
                                        Context context2;
                                        switch (i3) {
                                            case 0:
                                                BubbleBarMenuViewController bubbleBarMenuViewController3 = bubbleBarMenuViewController2;
                                                Bubble bubble3 = bubble2;
                                                bubbleBarMenuViewController3.hideMenu(true);
                                                BubbleBarExpandedView.AnonymousClass3 anonymousClass3 = bubbleBarMenuViewController3.mListener;
                                                if (anonymousClass3 != null && (anonymousClass2 = BubbleBarExpandedView.this.mListener) != null) {
                                                    String str = bubble3.mKey;
                                                    BubbleController$$ExternalSyntheticLambda14 bubbleController$$ExternalSyntheticLambda14 = BubbleBarLayerView.this.mUnBubbleConversationCallback;
                                                    if (bubbleController$$ExternalSyntheticLambda14 != null) {
                                                        bubbleController$$ExternalSyntheticLambda14.accept(str);
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 1:
                                                BubbleBarMenuViewController bubbleBarMenuViewController4 = bubbleBarMenuViewController2;
                                                Bubble bubble4 = bubble2;
                                                bubbleBarMenuViewController4.hideMenu(true);
                                                BubbleBarExpandedView.AnonymousClass3 anonymousClass32 = bubbleBarMenuViewController4.mListener;
                                                if (anonymousClass32 != null) {
                                                    BubbleBarExpandedView bubbleBarExpandedView2 = BubbleBarExpandedView.this;
                                                    ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleBarExpandedView2.mManager).$controller.collapseStack();
                                                    context = ((FrameLayout) bubbleBarExpandedView2).mContext;
                                                    context2 = ((FrameLayout) bubbleBarExpandedView2).mContext;
                                                    context.startActivityAsUser(bubble4.getSettingsIntent(context2), bubble4.mUser);
                                                    break;
                                                }
                                                break;
                                            default:
                                                BubbleBarMenuViewController bubbleBarMenuViewController5 = bubbleBarMenuViewController2;
                                                Bubble bubble5 = bubble2;
                                                bubbleBarMenuViewController5.hideMenu(true);
                                                BubbleBarExpandedView.AnonymousClass3 anonymousClass33 = bubbleBarMenuViewController5.mListener;
                                                if (anonymousClass33 != null) {
                                                    BubbleController bubbleController = ((BubbleExpandedViewManager$Companion$fromBubbleController$1) BubbleBarExpandedView.this.mManager).$controller;
                                                    bubbleController.getClass();
                                                    bubbleController.mBubbleData.dismissBubbleWithKey(1, bubble5.mKey);
                                                    break;
                                                }
                                                break;
                                        }
                                    }
                                }));
                                Bitmap bitmap = bubble2.mRawBadgeBitmap;
                                arrayList.add(new BubbleBarMenuView.MenuAction(bitmap != null ? Icon.createWithBitmap(bitmap) : null, resources.getString(R.string.bubbles_app_settings, bubble2.mAppName), 0, new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarMenuViewController$$ExternalSyntheticLambda0
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view2) {
                                        BubbleBarLayerView.AnonymousClass2 anonymousClass2;
                                        Context context;
                                        Context context2;
                                        switch (i) {
                                            case 0:
                                                BubbleBarMenuViewController bubbleBarMenuViewController3 = bubbleBarMenuViewController2;
                                                Bubble bubble3 = bubble2;
                                                bubbleBarMenuViewController3.hideMenu(true);
                                                BubbleBarExpandedView.AnonymousClass3 anonymousClass3 = bubbleBarMenuViewController3.mListener;
                                                if (anonymousClass3 != null && (anonymousClass2 = BubbleBarExpandedView.this.mListener) != null) {
                                                    String str = bubble3.mKey;
                                                    BubbleController$$ExternalSyntheticLambda14 bubbleController$$ExternalSyntheticLambda14 = BubbleBarLayerView.this.mUnBubbleConversationCallback;
                                                    if (bubbleController$$ExternalSyntheticLambda14 != null) {
                                                        bubbleController$$ExternalSyntheticLambda14.accept(str);
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 1:
                                                BubbleBarMenuViewController bubbleBarMenuViewController4 = bubbleBarMenuViewController2;
                                                Bubble bubble4 = bubble2;
                                                bubbleBarMenuViewController4.hideMenu(true);
                                                BubbleBarExpandedView.AnonymousClass3 anonymousClass32 = bubbleBarMenuViewController4.mListener;
                                                if (anonymousClass32 != null) {
                                                    BubbleBarExpandedView bubbleBarExpandedView2 = BubbleBarExpandedView.this;
                                                    ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleBarExpandedView2.mManager).$controller.collapseStack();
                                                    context = ((FrameLayout) bubbleBarExpandedView2).mContext;
                                                    context2 = ((FrameLayout) bubbleBarExpandedView2).mContext;
                                                    context.startActivityAsUser(bubble4.getSettingsIntent(context2), bubble4.mUser);
                                                    break;
                                                }
                                                break;
                                            default:
                                                BubbleBarMenuViewController bubbleBarMenuViewController5 = bubbleBarMenuViewController2;
                                                Bubble bubble5 = bubble2;
                                                bubbleBarMenuViewController5.hideMenu(true);
                                                BubbleBarExpandedView.AnonymousClass3 anonymousClass33 = bubbleBarMenuViewController5.mListener;
                                                if (anonymousClass33 != null) {
                                                    BubbleController bubbleController = ((BubbleExpandedViewManager$Companion$fromBubbleController$1) BubbleBarExpandedView.this.mManager).$controller;
                                                    bubbleController.getClass();
                                                    bubbleController.mBubbleData.dismissBubbleWithKey(1, bubble5.mKey);
                                                    break;
                                                }
                                                break;
                                        }
                                    }
                                }));
                            }
                            arrayList.add(new BubbleBarMenuView.MenuAction(Icon.createWithResource(resources, R.drawable.ic_remove_no_shadow), resources.getString(R.string.bubble_dismiss_text), color, new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarMenuViewController$$ExternalSyntheticLambda0
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view2) {
                                    BubbleBarLayerView.AnonymousClass2 anonymousClass2;
                                    Context context;
                                    Context context2;
                                    switch (i2) {
                                        case 0:
                                            BubbleBarMenuViewController bubbleBarMenuViewController3 = bubbleBarMenuViewController2;
                                            Bubble bubble3 = bubble2;
                                            bubbleBarMenuViewController3.hideMenu(true);
                                            BubbleBarExpandedView.AnonymousClass3 anonymousClass3 = bubbleBarMenuViewController3.mListener;
                                            if (anonymousClass3 != null && (anonymousClass2 = BubbleBarExpandedView.this.mListener) != null) {
                                                String str = bubble3.mKey;
                                                BubbleController$$ExternalSyntheticLambda14 bubbleController$$ExternalSyntheticLambda14 = BubbleBarLayerView.this.mUnBubbleConversationCallback;
                                                if (bubbleController$$ExternalSyntheticLambda14 != null) {
                                                    bubbleController$$ExternalSyntheticLambda14.accept(str);
                                                    break;
                                                }
                                            }
                                            break;
                                        case 1:
                                            BubbleBarMenuViewController bubbleBarMenuViewController4 = bubbleBarMenuViewController2;
                                            Bubble bubble4 = bubble2;
                                            bubbleBarMenuViewController4.hideMenu(true);
                                            BubbleBarExpandedView.AnonymousClass3 anonymousClass32 = bubbleBarMenuViewController4.mListener;
                                            if (anonymousClass32 != null) {
                                                BubbleBarExpandedView bubbleBarExpandedView2 = BubbleBarExpandedView.this;
                                                ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleBarExpandedView2.mManager).$controller.collapseStack();
                                                context = ((FrameLayout) bubbleBarExpandedView2).mContext;
                                                context2 = ((FrameLayout) bubbleBarExpandedView2).mContext;
                                                context.startActivityAsUser(bubble4.getSettingsIntent(context2), bubble4.mUser);
                                                break;
                                            }
                                            break;
                                        default:
                                            BubbleBarMenuViewController bubbleBarMenuViewController5 = bubbleBarMenuViewController2;
                                            Bubble bubble5 = bubble2;
                                            bubbleBarMenuViewController5.hideMenu(true);
                                            BubbleBarExpandedView.AnonymousClass3 anonymousClass33 = bubbleBarMenuViewController5.mListener;
                                            if (anonymousClass33 != null) {
                                                BubbleController bubbleController = ((BubbleExpandedViewManager$Companion$fromBubbleController$1) BubbleBarExpandedView.this.mManager).$controller;
                                                bubbleController.getClass();
                                                bubbleController.mBubbleData.dismissBubbleWithKey(1, bubble5.mKey);
                                                break;
                                            }
                                            break;
                                    }
                                }
                            }));
                            bubbleBarMenuView4.updateActions(arrayList);
                        } catch (Throwable th) {
                            if (obtainStyledAttributes != null) {
                                try {
                                    obtainStyledAttributes.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    }
                    View view2 = new View(bubbleBarMenuViewController2.mContext);
                    bubbleBarMenuViewController2.mScrimView = view2;
                    view2.setImportantForAccessibility(2);
                    bubbleBarMenuViewController2.mScrimView.setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarMenuViewController$$ExternalSyntheticLambda6
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view3) {
                            BubbleBarMenuViewController.this.hideMenu(true);
                        }
                    });
                    bubbleBarExpandedView.addView(bubbleBarMenuViewController2.mScrimView);
                    bubbleBarExpandedView.addView(bubbleBarMenuViewController2.mMenuView);
                }
                PhysicsAnimator physicsAnimator = bubbleBarMenuViewController2.mMenuAnimator;
                if (physicsAnimator != null) {
                    physicsAnimator.cancel();
                    bubbleBarMenuViewController2.mMenuAnimator = null;
                }
                bubbleBarMenuViewController2.mMenuView.setVisibility(0);
                bubbleBarMenuViewController2.mScrimView.setVisibility(0);
                bubbleBarMenuViewController2.animateTransition(new BubbleBarMenuViewController$$ExternalSyntheticLambda3(bubbleBarMenuViewController2, i3), true);
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        Executor executor;
        BubbleViewInfoTask.AnonymousClass1 anonymousClass1;
        super.onAttachedToWindow();
        RegionSamplingHelper regionSamplingHelper = this.mRegionSamplingHelper;
        if (regionSamplingHelper != null) {
            regionSamplingHelper.stopAndDestroy();
        }
        Executor executor2 = this.mMainExecutor;
        if (executor2 == null || (executor = this.mBackgroundExecutor) == null || (anonymousClass1 = this.mRegionSamplingProvider) == null) {
            return;
        }
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass1.getClass();
        this.mRegionSamplingHelper = new RegionSamplingHelper(this, anonymousClass3, executor2, executor);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mMenuViewController.hideMenu(false);
        RegionSamplingHelper regionSamplingHelper = this.mRegionSamplingHelper;
        if (regionSamplingHelper != null) {
            regionSamplingHelper.stopAndDestroy();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        Context context = getContext();
        setElevation(getResources().getDimensionPixelSize(R.dimen.bubble_elevation));
        this.mCaptionHeight = context.getResources().getDimensionPixelSize(R.dimen.bubble_bar_expanded_view_caption_height);
        this.mHandleView = (BubbleBarHandleView) findViewById(R.id.bubble_bar_handle_view);
        applyThemeAttrs();
        setClipToOutline(true);
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarExpandedView.2
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), BubbleBarExpandedView.this.mCurrentCornerRadius);
            }
        });
        setOnTouchListener(new BubbleBarExpandedView$$ExternalSyntheticLambda1());
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        TaskView taskView = this.mTaskView;
        if (taskView != null) {
            taskView.layout(i, i2, i3, taskView.getMeasuredHeight() + i2);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mTaskView != null) {
            measureChild(this.mTaskView, i, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), View.MeasureSpec.getMode(i2)));
        }
    }

    public final void onTaskCreated() {
        BubbleBarLayerView bubbleBarLayerView;
        final BubbleEducationViewController bubbleEducationViewController;
        BubbleBarExpandedView bubbleBarExpandedView;
        int i = 1;
        setContentVisibility(true);
        BubbleBarLayerView.AnonymousClass2 anonymousClass2 = this.mListener;
        if (anonymousClass2 == null || (bubbleEducationViewController = (bubbleBarLayerView = BubbleBarLayerView.this).mEducationViewController) == null || (bubbleBarExpandedView = bubbleBarLayerView.mExpandedView) == null) {
            return;
        }
        BubbleEducationController bubbleEducationController = (BubbleEducationController) bubbleEducationViewController.controller$delegate.getValue();
        if (BubbleDebugConfig.neverShowUserEducation(bubbleEducationController.context)) {
            return;
        }
        BubbleViewProvider bubbleViewProvider = anonymousClass2.val$b;
        if (!(bubbleViewProvider instanceof Bubble) || ((Bubble) bubbleViewProvider).mShortcutInfo == null) {
            return;
        }
        if (!bubbleEducationController.prefs.getBoolean("HasSeenBubblesManageOnboarding", false) || BubbleDebugConfig.forceShowUserEducation(bubbleEducationController.context)) {
            BubbleEducationViewController.hideEducation$default(bubbleEducationViewController, false);
            BubblePopupView createEducationView = bubbleEducationViewController.createEducationView(R.layout.bubble_bar_manage_education, bubbleBarExpandedView);
            createEducationView.setPivotY(0.0f);
            if (!createEducationView.isLaidOut() || createEducationView.isLayoutRequested()) {
                createEducationView.addOnLayoutChangeListener(new BubbleEducationViewController$showManageEducation$lambda$3$$inlined$doOnLayout$1());
            } else {
                createEducationView.setPivotX(createEducationView.getWidth() / 2.0f);
            }
            createEducationView.setOnClickListener(new BubbleEducationViewController$scrimView$2$1$1(i, bubbleEducationViewController));
            bubbleEducationViewController.educationView = createEducationView;
            bubbleEducationViewController.rootView = bubbleBarExpandedView;
            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
            PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(createEducationView);
            companion.defaultSpring = (PhysicsAnimator.SpringConfig) bubbleEducationViewController.springConfig$delegate.getValue();
            bubbleEducationViewController.animator = companion;
            bubbleBarExpandedView.addView((View) bubbleEducationViewController.scrimView$delegate.getValue());
            bubbleBarExpandedView.addView(bubbleEducationViewController.educationView);
            bubbleEducationViewController.animateTransition(new Function0() { // from class: com.android.wm.shell.bubbles.bar.BubbleEducationViewController$showManageEducation$3
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TaskView taskView;
                    BubbleBarLayerView$$ExternalSyntheticLambda0 bubbleBarLayerView$$ExternalSyntheticLambda0;
                    SharedPreferences.Editor edit = ((BubbleEducationController) BubbleEducationViewController.this.controller$delegate.getValue()).prefs.edit();
                    edit.putBoolean("HasSeenBubblesManageOnboarding", true);
                    edit.apply();
                    BubbleBarExpandedView bubbleBarExpandedView2 = BubbleEducationViewController.this.listener.f$0.mExpandedView;
                    if (bubbleBarExpandedView2 != null && (taskView = bubbleBarExpandedView2.mTaskView) != null && (bubbleBarLayerView$$ExternalSyntheticLambda0 = bubbleBarExpandedView2.mLayerBoundsSupplier) != null) {
                        taskView.mObscuredTouchRegion = new Region((Rect) bubbleBarLayerView$$ExternalSyntheticLambda0.get());
                    }
                    return Unit.INSTANCE;
                }
            }, true);
        }
    }

    public final void setAnimating(boolean z) {
        this.mIsAnimating = z;
        if (z) {
            updateSamplingState();
        }
        if (z) {
            return;
        }
        setContentVisibility(this.mIsContentVisible);
    }

    public final void setContentVisibility(boolean z) {
        this.mIsContentVisible = z;
        TaskView taskView = this.mTaskView;
        if (taskView == null || this.mIsAnimating) {
            return;
        }
        taskView.setAlpha(z ? 1.0f : 0.0f);
        RegionSamplingHelper regionSamplingHelper = this.mRegionSamplingHelper;
        if (regionSamplingHelper != null) {
            regionSamplingHelper.mWindowVisible = z;
            regionSamplingHelper.updateSamplingListener();
        }
        updateSamplingState();
    }

    public final boolean shouldSampleRegion() {
        TaskView taskView = this.mTaskView;
        return (taskView == null || taskView.mTaskViewTaskController.mTaskInfo == null || this.mIsDragging || this.mIsAnimating || !this.mIsContentVisible) ? false : true;
    }

    public final void updateSamplingState() {
        if (this.mRegionSamplingHelper == null) {
            return;
        }
        if (shouldSampleRegion()) {
            this.mRegionSamplingHelper.start(getCaptionSampleRect());
        } else {
            this.mRegionSamplingHelper.stop();
        }
    }

    public BubbleBarExpandedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleBarExpandedView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BubbleBarExpandedView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mSampleRect = new Rect();
        this.mLoc = new int[2];
        this.mRestingCornerRadius = 0.0f;
        this.mDraggedCornerRadius = 0.0f;
        this.mCurrentCornerRadius = 0.0f;
        this.mIsContentVisible = false;
    }
}
