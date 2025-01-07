package com.android.wm.shell.bubbles;

import android.R;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.CornerPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.IntProperty;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.window.ScreenCapture;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleExpandedView;
import com.android.wm.shell.common.AlphaOptimizedButton;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TriangleShape;
import com.android.wm.shell.taskview.TaskView;
import com.android.wm.shell.taskview.TaskViewTaskController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BubbleExpandedView extends LinearLayout {
    public static final AnonymousClass2 BACKGROUND_ALPHA;
    public static final AnonymousClass1 BOTTOM_CLIP_PROPERTY = new AnonymousClass1("bottomClip");
    public static final AnonymousClass2 CONTENT_ALPHA;
    public static final AnonymousClass2 MANAGE_BUTTON_ALPHA;
    public int mBackgroundColorFloating;
    public int mBottomClip;
    public Bubble mBubble;
    public float mCornerRadius;
    public ShapeDrawable mCurrentPointer;
    public final FrameLayout mExpandedViewContainer;
    public int[] mExpandedViewContainerLocation;
    public boolean mImeVisible;
    public boolean mIsAnimating;
    public boolean mIsClipping;
    public boolean mIsContentVisible;
    public boolean mIsOverflow;
    public ShapeDrawable mLeftPointer;
    public AlphaOptimizedButton mManageButton;
    public BubbleExpandedViewManager mManager;
    public boolean mNeedsNewHeight;
    public BubbleOverflowContainerView mOverflowView;
    public PendingIntent mPendingIntent;
    public CornerPathEffect mPointerEffect;
    public int mPointerHeight;
    public float mPointerOverlap;
    public final PointF mPointerPos;
    public float mPointerRadius;
    public View mPointerView;
    public int mPointerWidth;
    public BubblePositioner mPositioner;
    public ShapeDrawable mRightPointer;
    public BubbleStackView mStackView;
    public int mTaskId;
    public TaskView mTaskView;
    public final AnonymousClass5 mTaskViewListener;
    public int mTopClip;
    public ShapeDrawable mTopPointer;
    public boolean mUsingMaxHeight;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleExpandedView$1, reason: invalid class name */
    public final class AnonymousClass1 extends IntProperty {
        @Override // android.util.Property
        public final Integer get(Object obj) {
            return Integer.valueOf(((BubbleExpandedView) obj).mBottomClip);
        }

        @Override // android.util.IntProperty
        public final void setValue(Object obj, int i) {
            BubbleExpandedView bubbleExpandedView = (BubbleExpandedView) obj;
            bubbleExpandedView.mBottomClip = i;
            bubbleExpandedView.onContainerClipUpdate();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleExpandedView$5, reason: invalid class name */
    public final class AnonymousClass5 implements TaskView.Listener {
        public boolean mInitialized = false;
        public boolean mDestroyed = false;

        public AnonymousClass5() {
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onBackPressedOnTaskRoot(int i) {
            BubbleStackView bubbleStackView;
            boolean z;
            BubbleExpandedView bubbleExpandedView = BubbleExpandedView.this;
            if (bubbleExpandedView.mTaskId == i && (z = (bubbleStackView = bubbleExpandedView.mStackView).mIsExpanded) && z) {
                if (bubbleStackView.mShowingManage) {
                    bubbleStackView.showManageMenu(false);
                } else if (bubbleStackView.isManageEduVisible()) {
                    bubbleStackView.mManageEduView.hide();
                } else {
                    bubbleStackView.mBubbleData.setExpanded(false);
                }
            }
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onInitialized() {
            boolean z = this.mDestroyed;
            BubbleExpandedView bubbleExpandedView = BubbleExpandedView.this;
            if (!z && !this.mInitialized) {
                final ActivityOptions makeCustomAnimation = ActivityOptions.makeCustomAnimation(bubbleExpandedView.getContext(), 0, 0);
                bubbleExpandedView.post(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleExpandedView$5$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        boolean z2;
                        Context context;
                        BubbleExpandedView.AnonymousClass5 anonymousClass5 = BubbleExpandedView.AnonymousClass5.this;
                        ActivityOptions activityOptions = makeCustomAnimation;
                        anonymousClass5.getClass();
                        boolean z3 = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
                        BubbleExpandedView bubbleExpandedView2 = BubbleExpandedView.this;
                        if (z3) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 1533690492202595624L, 0, String.valueOf(bubbleExpandedView2.getBubbleKey()));
                        }
                        try {
                            Rect rect = new Rect();
                            bubbleExpandedView2.mTaskView.getBoundsOnScreen(rect);
                            activityOptions.setTaskAlwaysOnTop(true);
                            activityOptions.setLaunchedFromBubble(true);
                            activityOptions.setPendingIntentBackgroundActivityStartMode(3);
                            Intent intent = new Intent();
                            intent.addFlags(524288);
                            intent.addFlags(134217728);
                            if (bubbleExpandedView2.mBubble.hasMetadataShortcutId()) {
                                z2 = true;
                            } else {
                                ShortcutInfo shortcutInfo = bubbleExpandedView2.mBubble.mShortcutInfo;
                                z2 = false;
                            }
                            Bubble bubble = bubbleExpandedView2.mBubble;
                            if (bubble.mIsAppBubble) {
                                context = ((LinearLayout) bubbleExpandedView2).mContext;
                                bubbleExpandedView2.mTaskView.startActivity(PendingIntent.getActivity(context.createContextAsUser(bubbleExpandedView2.mBubble.mUser, 4), 0, bubbleExpandedView2.mBubble.getAppBubbleIntent().addFlags(524288).addFlags(134217728), 201326592, null), null, activityOptions, rect);
                            } else {
                                if (bubbleExpandedView2.mIsOverflow || !z2) {
                                    bubble.mIntentActive = true;
                                    bubbleExpandedView2.mTaskView.startActivity(bubbleExpandedView2.mPendingIntent, intent, activityOptions, rect);
                                    return;
                                }
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
                                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, -7259845944232954472L, 0, String.valueOf(bubbleExpandedView2.getBubbleKey()));
                                }
                                activityOptions.setApplyActivityFlagsForBubbles(true);
                                bubbleExpandedView2.mTaskView.startShortcutActivity(bubbleExpandedView2.mBubble.mShortcutInfo, activityOptions, rect);
                            }
                        } catch (RuntimeException e) {
                            Log.w("Bubbles", "Exception while displaying bubble: " + bubbleExpandedView2.getBubbleKey() + ", " + e.getMessage() + "; removing bubble");
                            ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleExpandedView2.mManager).$controller.removeBubble(10, bubbleExpandedView2.getBubbleKey());
                        }
                    }
                });
                this.mInitialized = true;
            } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                boolean z2 = this.mInitialized;
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 3773084945322816131L, 15, Boolean.valueOf(z), Boolean.valueOf(z2), String.valueOf(bubbleExpandedView.getBubbleKey()));
            }
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onReleased() {
            this.mDestroyed = true;
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onTaskCreated(int i, ComponentName componentName) {
            boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
            BubbleExpandedView bubbleExpandedView = BubbleExpandedView.this;
            if (z) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -3890427939905127709L, 1, Long.valueOf(i), String.valueOf(bubbleExpandedView.getBubbleKey()));
            }
            bubbleExpandedView.mTaskId = i;
            Bubble bubble = bubbleExpandedView.mBubble;
            if (bubble != null && bubble.mIsAppBubble) {
                BubbleExpandedViewManager bubbleExpandedViewManager = bubbleExpandedView.mManager;
                String str = bubble.mKey;
                BubbleController.BubblesImpl.CachedState cachedState = ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleExpandedViewManager).$controller.mImpl.mCachedState;
                synchronized (cachedState) {
                    cachedState.mAppBubbleTaskIds.put(str, Integer.valueOf(i));
                }
            }
            bubbleExpandedView.setContentVisibility(true);
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onTaskRemovalStarted(int i) {
            boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
            BubbleExpandedView bubbleExpandedView = BubbleExpandedView.this;
            if (z) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -1943811537155114558L, 1, Long.valueOf(i), String.valueOf(bubbleExpandedView.getBubbleKey()));
            }
            Bubble bubble = bubbleExpandedView.mBubble;
            if (bubble != null) {
                ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleExpandedView.mManager).$controller.removeBubble(3, bubble.mKey);
            }
            TaskView taskView = bubbleExpandedView.mTaskView;
            if (taskView != null) {
                taskView.getHolder().removeCallback(taskView);
                taskView.mTaskViewTaskController.performRelease();
                bubbleExpandedView.removeView(bubbleExpandedView.mTaskView);
                bubbleExpandedView.mTaskView = null;
            }
        }

        @Override // com.android.wm.shell.taskview.TaskView.Listener
        public final void onTaskVisibilityChanged(int i, boolean z) {
            boolean z2 = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0];
            BubbleExpandedView bubbleExpandedView = BubbleExpandedView.this;
            if (z2) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -1456133808905439828L, 19, Boolean.valueOf(z), String.valueOf(bubbleExpandedView.getBubbleKey()), Long.valueOf(i));
            }
            bubbleExpandedView.setContentVisibility(z);
        }
    }

    public static /* synthetic */ void $r8$lambda$6ReYT94_VJPGlvOxZU2PLyj4qOQ(BubbleExpandedView bubbleExpandedView, boolean z, boolean z2, float f, boolean z3, boolean z4) {
        bubbleExpandedView.mCurrentPointer = z ? z2 ? bubbleExpandedView.mLeftPointer : bubbleExpandedView.mRightPointer : bubbleExpandedView.mTopPointer;
        bubbleExpandedView.updatePointerViewIfExists();
        if (z) {
            PointF pointF = bubbleExpandedView.mPointerPos;
            pointF.y = f - (bubbleExpandedView.mPointerWidth / 2.0f);
            if (z3) {
                pointF.x = z2 ? -((bubbleExpandedView.getWidth() - ((LinearLayout) bubbleExpandedView).mPaddingLeft) - bubbleExpandedView.mPointerOverlap) : bubbleExpandedView.mPointerHeight - bubbleExpandedView.mPointerOverlap;
            } else {
                pointF.x = z2 ? (-bubbleExpandedView.mPointerHeight) + bubbleExpandedView.mPointerOverlap : (bubbleExpandedView.getWidth() - ((LinearLayout) bubbleExpandedView).mPaddingRight) - bubbleExpandedView.mPointerOverlap;
            }
        } else {
            PointF pointF2 = bubbleExpandedView.mPointerPos;
            pointF2.y = bubbleExpandedView.mPointerOverlap;
            if (z3) {
                pointF2.x = (bubbleExpandedView.mPointerWidth / 2.0f) + (-((bubbleExpandedView.getWidth() - ((LinearLayout) bubbleExpandedView).mPaddingLeft) - f));
            } else {
                pointF2.x = f - (bubbleExpandedView.mPointerWidth / 2.0f);
            }
        }
        if (z4) {
            bubbleExpandedView.mPointerView.animate().translationX(bubbleExpandedView.mPointerPos.x).translationY(bubbleExpandedView.mPointerPos.y).start();
            return;
        }
        bubbleExpandedView.mPointerView.setTranslationY(bubbleExpandedView.mPointerPos.y);
        bubbleExpandedView.mPointerView.setTranslationX(bubbleExpandedView.mPointerPos.x);
        bubbleExpandedView.mPointerView.setVisibility(0);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.bubbles.BubbleExpandedView$2] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.wm.shell.bubbles.BubbleExpandedView$2] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.wm.shell.bubbles.BubbleExpandedView$2] */
    static {
        final int i = 0;
        CONTENT_ALPHA = new FloatProperty("contentAlpha") { // from class: com.android.wm.shell.bubbles.BubbleExpandedView.2
            @Override // android.util.Property
            public final Float get(Object obj) {
                float alpha;
                switch (i) {
                    case 0:
                        BubbleExpandedView bubbleExpandedView = (BubbleExpandedView) obj;
                        if (bubbleExpandedView.mIsOverflow) {
                            alpha = bubbleExpandedView.mOverflowView.getAlpha();
                        } else {
                            TaskView taskView = bubbleExpandedView.mTaskView;
                            alpha = taskView != null ? taskView.getAlpha() : 1.0f;
                        }
                        return Float.valueOf(alpha);
                    case 1:
                        return Float.valueOf(((BubbleExpandedView) obj).getAlpha());
                    default:
                        return Float.valueOf(((BubbleExpandedView) obj).mManageButton.getAlpha());
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i) {
                    case 0:
                        ((BubbleExpandedView) obj).setContentAlpha(f);
                        break;
                    case 1:
                        BubbleExpandedView bubbleExpandedView = (BubbleExpandedView) obj;
                        bubbleExpandedView.mPointerView.setAlpha(f);
                        bubbleExpandedView.setAlpha(f);
                        break;
                    default:
                        ((BubbleExpandedView) obj).mManageButton.setAlpha(f);
                        break;
                }
            }
        };
        final int i2 = 1;
        BACKGROUND_ALPHA = new FloatProperty("backgroundAlpha") { // from class: com.android.wm.shell.bubbles.BubbleExpandedView.2
            @Override // android.util.Property
            public final Float get(Object obj) {
                float alpha;
                switch (i2) {
                    case 0:
                        BubbleExpandedView bubbleExpandedView = (BubbleExpandedView) obj;
                        if (bubbleExpandedView.mIsOverflow) {
                            alpha = bubbleExpandedView.mOverflowView.getAlpha();
                        } else {
                            TaskView taskView = bubbleExpandedView.mTaskView;
                            alpha = taskView != null ? taskView.getAlpha() : 1.0f;
                        }
                        return Float.valueOf(alpha);
                    case 1:
                        return Float.valueOf(((BubbleExpandedView) obj).getAlpha());
                    default:
                        return Float.valueOf(((BubbleExpandedView) obj).mManageButton.getAlpha());
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i2) {
                    case 0:
                        ((BubbleExpandedView) obj).setContentAlpha(f);
                        break;
                    case 1:
                        BubbleExpandedView bubbleExpandedView = (BubbleExpandedView) obj;
                        bubbleExpandedView.mPointerView.setAlpha(f);
                        bubbleExpandedView.setAlpha(f);
                        break;
                    default:
                        ((BubbleExpandedView) obj).mManageButton.setAlpha(f);
                        break;
                }
            }
        };
        final int i3 = 2;
        MANAGE_BUTTON_ALPHA = new FloatProperty("manageButtonAlpha") { // from class: com.android.wm.shell.bubbles.BubbleExpandedView.2
            @Override // android.util.Property
            public final Float get(Object obj) {
                float alpha;
                switch (i3) {
                    case 0:
                        BubbleExpandedView bubbleExpandedView = (BubbleExpandedView) obj;
                        if (bubbleExpandedView.mIsOverflow) {
                            alpha = bubbleExpandedView.mOverflowView.getAlpha();
                        } else {
                            TaskView taskView = bubbleExpandedView.mTaskView;
                            alpha = taskView != null ? taskView.getAlpha() : 1.0f;
                        }
                        return Float.valueOf(alpha);
                    case 1:
                        return Float.valueOf(((BubbleExpandedView) obj).getAlpha());
                    default:
                        return Float.valueOf(((BubbleExpandedView) obj).mManageButton.getAlpha());
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i3) {
                    case 0:
                        ((BubbleExpandedView) obj).setContentAlpha(f);
                        break;
                    case 1:
                        BubbleExpandedView bubbleExpandedView = (BubbleExpandedView) obj;
                        bubbleExpandedView.mPointerView.setAlpha(f);
                        bubbleExpandedView.setAlpha(f);
                        break;
                    default:
                        ((BubbleExpandedView) obj).mManageButton.setAlpha(f);
                        break;
                }
            }
        };
    }

    public BubbleExpandedView(Context context) {
        this(context, null);
    }

    public final void applyThemeAttrs() {
        TypedArray obtainStyledAttributes = ((LinearLayout) this).mContext.obtainStyledAttributes(new int[]{R.attr.dialogCornerRadius, R.^attr-private.materialColorSurfaceBright, R.^attr-private.materialColorSurfaceContainerHigh});
        this.mCornerRadius = ScreenDecorationsUtils.supportsRoundedCornersOnWindows(((LinearLayout) this).mContext.getResources()) ? obtainStyledAttributes.getDimensionPixelSize(0, 0) : 0.0f;
        int color = obtainStyledAttributes.getColor(1, -1);
        this.mBackgroundColorFloating = color;
        this.mExpandedViewContainer.setBackgroundColor(color);
        int color2 = obtainStyledAttributes.getColor(2, -1);
        obtainStyledAttributes.recycle();
        AlphaOptimizedButton alphaOptimizedButton = this.mManageButton;
        if (alphaOptimizedButton != null) {
            alphaOptimizedButton.getBackground().setColorFilter(color2, PorterDuff.Mode.SRC_IN);
        }
        TaskView taskView = this.mTaskView;
        if (taskView != null) {
            taskView.setCornerRadius(this.mCornerRadius);
        }
        updatePointerViewIfExists();
        updateManageButtonIfExists();
    }

    public String getBubbleKey() {
        Bubble bubble = this.mBubble;
        if (bubble != null) {
            return bubble.mKey;
        }
        if (this.mIsOverflow) {
            return "Overflow";
        }
        return null;
    }

    public final int getContentHeight() {
        int height;
        int i;
        if (this.mIsOverflow) {
            height = this.mOverflowView.getHeight() - this.mTopClip;
            i = this.mBottomClip;
        } else {
            TaskView taskView = this.mTaskView;
            if (taskView == null) {
                return 0;
            }
            height = taskView.getHeight() - this.mTopClip;
            i = this.mBottomClip;
        }
        return height - i;
    }

    public BubbleOverflowContainerView getOverflow() {
        return this.mOverflowView;
    }

    public final void initialize(BubbleExpandedViewManager bubbleExpandedViewManager, BubbleStackView bubbleStackView, BubblePositioner bubblePositioner, boolean z, BubbleTaskView bubbleTaskView) {
        this.mManager = bubbleExpandedViewManager;
        this.mStackView = bubbleStackView;
        this.mIsOverflow = z;
        this.mPositioner = bubblePositioner;
        if (z) {
            BubbleOverflowContainerView bubbleOverflowContainerView = (BubbleOverflowContainerView) LayoutInflater.from(getContext()).inflate(com.android.wm.shell.R.layout.bubble_overflow_container, (ViewGroup) null);
            this.mOverflowView = bubbleOverflowContainerView;
            bubbleOverflowContainerView.mExpandedViewManager = bubbleExpandedViewManager;
            bubbleOverflowContainerView.mPositioner = bubblePositioner;
            this.mExpandedViewContainer.addView(this.mOverflowView, new FrameLayout.LayoutParams(-1, -1));
            this.mExpandedViewContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            bringChildToFront(this.mOverflowView);
            this.mManageButton.setVisibility(8);
            return;
        }
        TaskView taskView = bubbleTaskView.taskView;
        this.mTaskView = taskView;
        taskView.mCaptionInsets = null;
        TaskViewTaskController taskViewTaskController = taskView.mTaskViewTaskController;
        Rect rect = taskViewTaskController.mCaptionInsets;
        if (rect == null || !rect.equals(null)) {
            taskViewTaskController.mCaptionInsets = null;
            taskViewTaskController.applyCaptionInsetsIfNeeded();
        }
        bubbleTaskView.delegateListener = this.mTaskViewListener;
        boolean isStackOnLeft = this.mPositioner.isStackOnLeft(this.mStackView.mStackAnimationController.mStackPosition);
        BubblePositioner bubblePositioner2 = this.mPositioner;
        int[] expandedViewContainerPadding = bubblePositioner2.getExpandedViewContainerPadding(isStackOnLeft, false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(((bubblePositioner2.mScreenRect.width() - expandedViewContainerPadding[0]) - expandedViewContainerPadding[2]) - (bubblePositioner2.showBubblesVertically() ? bubblePositioner2.mPointerHeight - bubblePositioner2.mPointerOverlap : 0), -1);
        if (this.mTaskView.getParent() != null) {
            ((ViewGroup) this.mTaskView.getParent()).removeView(this.mTaskView);
        }
        this.mExpandedViewContainer.addView(this.mTaskView, layoutParams);
        bringChildToFront(this.mTaskView);
        if (bubbleTaskView.isCreated) {
            this.mTaskViewListener.onTaskCreated(bubbleTaskView.taskId, bubbleTaskView.componentName);
        }
    }

    public final void movePointerBy(float f) {
        this.mPointerView.setTranslationX(this.mPointerPos.x + f);
        this.mPointerView.setTranslationY(this.mPointerPos.y + 0.0f);
    }

    public final void onContainerClipUpdate() {
        if (this.mTopClip == 0 && this.mBottomClip == 0) {
            if (this.mIsClipping) {
                this.mIsClipping = false;
                TaskView taskView = this.mTaskView;
                if (taskView != null) {
                    taskView.setClipBounds(null);
                    this.mTaskView.setEnableSurfaceClipping(false);
                }
                this.mExpandedViewContainer.invalidateOutline();
                return;
            }
            return;
        }
        if (!this.mIsClipping) {
            this.mIsClipping = true;
            TaskView taskView2 = this.mTaskView;
            if (taskView2 != null) {
                taskView2.setEnableSurfaceClipping(true);
            }
        }
        this.mExpandedViewContainer.invalidateOutline();
        if (this.mTaskView != null) {
            this.mTaskView.setClipBounds(new Rect(0, this.mTopClip, this.mTaskView.getWidth(), this.mTaskView.getHeight() - this.mBottomClip));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mImeVisible = false;
        this.mNeedsNewHeight = false;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mManageButton = (AlphaOptimizedButton) LayoutInflater.from(getContext()).inflate(com.android.wm.shell.R.layout.bubble_manage_button, (ViewGroup) this, false);
        updateDimensions();
        View findViewById = findViewById(com.android.wm.shell.R.id.pointer_view);
        this.mPointerView = findViewById;
        this.mCurrentPointer = this.mTopPointer;
        findViewById.setVisibility(4);
        setContentVisibility(false);
        this.mExpandedViewContainer.setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.wm.shell.bubbles.BubbleExpandedView.6
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                BubbleExpandedView bubbleExpandedView = BubbleExpandedView.this;
                AnonymousClass1 anonymousClass1 = BubbleExpandedView.BOTTOM_CLIP_PROPERTY;
                bubbleExpandedView.getClass();
                int i = BubbleExpandedView.this.mTopClip;
                int width = view.getWidth();
                BubbleExpandedView.this.getClass();
                outline.setRoundRect(new Rect(0, i, width, view.getHeight() - BubbleExpandedView.this.mBottomClip), BubbleExpandedView.this.mCornerRadius);
            }
        });
        this.mExpandedViewContainer.setClipToOutline(true);
        this.mExpandedViewContainer.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        addView(this.mExpandedViewContainer);
        bringChildToFront(this.mManageButton);
        this.mManageButton.setOnClickListener(new BubbleExpandedView$$ExternalSyntheticLambda1(this));
        applyThemeAttrs();
        setClipToPadding(false);
        setOnTouchListener(new View.OnTouchListener() { // from class: com.android.wm.shell.bubbles.BubbleExpandedView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                BubbleExpandedView bubbleExpandedView = BubbleExpandedView.this;
                if (bubbleExpandedView.mTaskView == null) {
                    return false;
                }
                Rect rect = new Rect();
                bubbleExpandedView.mTaskView.getBoundsOnScreen(rect);
                if (motionEvent.getRawY() < rect.top || motionEvent.getRawY() > rect.bottom) {
                    return false;
                }
                return motionEvent.getRawX() < ((float) rect.left) || motionEvent.getRawX() > ((float) rect.right);
            }
        });
        setLayoutDirection(3);
    }

    public final void setAnimating(boolean z) {
        this.mIsAnimating = z;
        if (z) {
            return;
        }
        setContentVisibility(this.mIsContentVisible);
    }

    public final void setContentAlpha(float f) {
        if (this.mIsOverflow) {
            this.mOverflowView.setAlpha(f);
            return;
        }
        TaskView taskView = this.mTaskView;
        if (taskView != null) {
            taskView.setAlpha(f);
        }
    }

    public final void setContentVisibility(boolean z) {
        this.mIsContentVisible = z;
        TaskView taskView = this.mTaskView;
        if (taskView == null || this.mIsAnimating) {
            return;
        }
        taskView.setAlpha(z ? 1.0f : 0.0f);
        this.mPointerView.setAlpha(z ? 1.0f : 0.0f);
    }

    public final void setPointerPosition(float f, final boolean z, final boolean z2) {
        final boolean z3 = ((LinearLayout) this).mContext.getResources().getConfiguration().getLayoutDirection() == 1;
        final boolean showBubblesVertically = this.mPositioner.showBubblesVertically();
        setPadding((int) ((showBubblesVertically && z) ? this.mPointerHeight - this.mPointerOverlap : 0.0f), (int) (showBubblesVertically ? 0.0f : this.mPointerHeight - this.mPointerOverlap), (int) ((!showBubblesVertically || z) ? 0.0f : this.mPointerHeight - this.mPointerOverlap), 0);
        float pointerPosition = this.mPositioner.getPointerPosition(f);
        if (this.mPositioner.showBubblesVertically()) {
            pointerPosition -= this.mPositioner.getExpandedViewY(this.mBubble, f);
        }
        final float f2 = pointerPosition;
        post(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleExpandedView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BubbleExpandedView.$r8$lambda$6ReYT94_VJPGlvOxZU2PLyj4qOQ(BubbleExpandedView.this, showBubblesVertically, z, f2, z3, z2);
            }
        });
    }

    public final void setSurfaceZOrderedOnTop(boolean z) {
        TaskView taskView = this.mTaskView;
        if (taskView == null) {
            return;
        }
        taskView.setZOrderedOnTop(z, true);
    }

    public final ScreenCapture.ScreenshotHardwareBuffer snapshotActivitySurface() {
        if (!this.mIsOverflow) {
            TaskView taskView = this.mTaskView;
            if (taskView == null || taskView.getSurfaceControl() == null) {
                return null;
            }
            return ScreenCapture.captureLayers(this.mTaskView.getSurfaceControl(), new Rect(0, 0, this.mTaskView.getWidth(), this.mTaskView.getHeight()), 1.0f);
        }
        Picture picture = new Picture();
        BubbleOverflowContainerView bubbleOverflowContainerView = this.mOverflowView;
        bubbleOverflowContainerView.draw(picture.beginRecording(bubbleOverflowContainerView.getWidth(), this.mOverflowView.getHeight()));
        picture.endRecording();
        Bitmap createBitmap = Bitmap.createBitmap(picture);
        return new ScreenCapture.ScreenshotHardwareBuffer(createBitmap.getHardwareBuffer(), createBitmap.getColorSpace(), false, false);
    }

    public final void updateDimensions() {
        Resources resources = getResources();
        updateFontSize();
        this.mPointerWidth = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.bubble_pointer_width);
        this.mPointerHeight = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.bubble_pointer_height);
        this.mPointerRadius = getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.bubble_pointer_radius);
        this.mPointerEffect = new CornerPathEffect(this.mPointerRadius);
        this.mPointerOverlap = getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.bubble_pointer_overlap);
        float f = this.mPointerWidth;
        float f2 = this.mPointerHeight;
        int i = TriangleShape.$r8$clinit;
        Path path = new Path();
        path.moveTo(0.0f, f2);
        path.lineTo(f, f2);
        path.lineTo(f / 2.0f, 0.0f);
        path.close();
        this.mTopPointer = new ShapeDrawable(new TriangleShape(path, f, f2));
        this.mLeftPointer = new ShapeDrawable(TriangleShape.createHorizontal(this.mPointerWidth, this.mPointerHeight, true));
        this.mRightPointer = new ShapeDrawable(TriangleShape.createHorizontal(this.mPointerWidth, this.mPointerHeight, false));
        updatePointerViewIfExists();
        updateManageButtonIfExists();
    }

    public final void updateFontSize() {
        float dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(R.dimen.text_size_menu_material);
        AlphaOptimizedButton alphaOptimizedButton = this.mManageButton;
        if (alphaOptimizedButton != null) {
            alphaOptimizedButton.setTextSize(0, dimensionPixelSize);
        }
        BubbleOverflowContainerView bubbleOverflowContainerView = this.mOverflowView;
        if (bubbleOverflowContainerView != null) {
            bubbleOverflowContainerView.updateFontSize();
        }
    }

    public final void updateHeight() {
        if (this.mExpandedViewContainerLocation == null) {
            return;
        }
        Bubble bubble = this.mBubble;
        if ((bubble == null || this.mTaskView == null) && !this.mIsOverflow) {
            return;
        }
        float expandedViewHeight = this.mPositioner.getExpandedViewHeight(bubble);
        int maxExpandedViewHeight = this.mPositioner.getMaxExpandedViewHeight(this.mIsOverflow);
        float min = expandedViewHeight == -1.0f ? maxExpandedViewHeight : Math.min(expandedViewHeight, maxExpandedViewHeight);
        this.mUsingMaxHeight = min == ((float) maxExpandedViewHeight);
        FrameLayout.LayoutParams layoutParams = this.mIsOverflow ? (FrameLayout.LayoutParams) this.mOverflowView.getLayoutParams() : (FrameLayout.LayoutParams) this.mTaskView.getLayoutParams();
        this.mNeedsNewHeight = ((float) layoutParams.height) != min;
        if (this.mImeVisible) {
            return;
        }
        layoutParams.height = (int) min;
        if (this.mIsOverflow) {
            this.mOverflowView.setLayoutParams(layoutParams);
        } else {
            this.mTaskView.setLayoutParams(layoutParams);
        }
        this.mNeedsNewHeight = false;
    }

    public final void updateLocale() {
        AlphaOptimizedButton alphaOptimizedButton = this.mManageButton;
        if (alphaOptimizedButton != null) {
            alphaOptimizedButton.setText(((LinearLayout) this).mContext.getString(com.android.wm.shell.R.string.manage_bubbles_text));
        }
        BubbleOverflowContainerView bubbleOverflowContainerView = this.mOverflowView;
        if (bubbleOverflowContainerView != null) {
            bubbleOverflowContainerView.updateLocale();
        }
    }

    public final void updateManageButtonIfExists() {
        int i = 0;
        AlphaOptimizedButton alphaOptimizedButton = this.mManageButton;
        if (alphaOptimizedButton == null) {
            return;
        }
        int visibility = alphaOptimizedButton.getVisibility();
        removeView(this.mManageButton);
        AlphaOptimizedButton alphaOptimizedButton2 = (AlphaOptimizedButton) LayoutInflater.from(new ContextThemeWrapper(getContext(), R.style.Theme.DeviceDefault.DayNight)).inflate(com.android.wm.shell.R.layout.bubble_manage_button, (ViewGroup) this, false);
        this.mManageButton = alphaOptimizedButton2;
        addView(alphaOptimizedButton2);
        this.mManageButton.setVisibility(visibility);
        this.mManageButton.setOnClickListener(new BubbleExpandedView$$ExternalSyntheticLambda1(this));
        post(new BubbleExpandedView$$ExternalSyntheticLambda2(this, i));
    }

    public final void updatePointerViewIfExists() {
        View view = this.mPointerView;
        if (view == null) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        ShapeDrawable shapeDrawable = this.mCurrentPointer;
        if (shapeDrawable == this.mLeftPointer || shapeDrawable == this.mRightPointer) {
            layoutParams.width = this.mPointerHeight;
            layoutParams.height = this.mPointerWidth;
        } else {
            layoutParams.width = this.mPointerWidth;
            layoutParams.height = this.mPointerHeight;
        }
        shapeDrawable.setTint(this.mBackgroundColorFloating);
        Paint paint = this.mCurrentPointer.getPaint();
        paint.setColor(this.mBackgroundColorFloating);
        paint.setPathEffect(this.mPointerEffect);
        this.mPointerView.setLayoutParams(layoutParams);
        this.mPointerView.setBackground(this.mCurrentPointer);
    }

    public BubbleExpandedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleExpandedView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BubbleExpandedView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTaskId = -1;
        this.mIsContentVisible = false;
        this.mIsAnimating = false;
        this.mPointerPos = new PointF();
        this.mCornerRadius = 0.0f;
        this.mTopClip = 0;
        this.mBottomClip = 0;
        this.mExpandedViewContainer = new FrameLayout(getContext());
        this.mTaskViewListener = new AnonymousClass5();
    }
}
