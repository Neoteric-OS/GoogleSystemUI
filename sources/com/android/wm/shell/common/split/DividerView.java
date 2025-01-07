package com.android.wm.shell.common.split;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.DeviceConfig;
import android.util.AttributeSet;
import android.util.Property;
import android.view.GestureDetector;
import android.view.InsetsController;
import android.view.InsetsSource;
import android.view.InsetsState;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.SurfaceControlViewHost;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.R;
import com.android.wm.shell.common.split.DividerSnapAlgorithm;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class DividerView extends FrameLayout implements View.OnTouchListener {
    public static final AnonymousClass1 DIVIDER_HEIGHT_PROPERTY = new AnonymousClass1(Integer.class, "height");
    public final AnonymousClass2 mAnimatorListener;
    public final Rect mBackgroundRect;
    public DividerRoundedCorner mCorners;
    public FrameLayout mDividerBar;
    public final Rect mDividerBounds;
    public GestureDetector mDoubleTapDetector;
    public DividerHandleView mHandle;
    public final AnonymousClass3 mHandleDelegate;
    public int mHandleRegionHeight;
    public int mHandleRegionWidth;
    public boolean mHideHandle;
    public boolean mInteractive;
    public int mLastDraggingPosition;
    public boolean mMoving;
    public final Paint mPaint;
    public boolean mSetTouchRegion;
    public SplitLayout mSplitLayout;
    public SplitWindowManager mSplitWindowManager;
    public int mStartPos;
    public final Rect mTempRect;
    public int mTouchElevation;
    public final int mTouchSlop;
    public VelocityTracker mVelocityTracker;
    public SurfaceControlViewHost mViewHost;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.common.split.DividerView$1, reason: invalid class name */
    public final class AnonymousClass1 extends Property {
        @Override // android.util.Property
        public final Object get(Object obj) {
            return Integer.valueOf(((DividerView) obj).mDividerBar.getLayoutParams().height);
        }

        @Override // android.util.Property
        public final void set(Object obj, Object obj2) {
            DividerView dividerView = (DividerView) obj;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) dividerView.mDividerBar.getLayoutParams();
            marginLayoutParams.height = ((Integer) obj2).intValue();
            dividerView.mDividerBar.setLayoutParams(marginLayoutParams);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DoubleTapListener extends GestureDetector.SimpleOnGestureListener {
        public DoubleTapListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public final boolean onDoubleTap(MotionEvent motionEvent) {
            SplitLayout splitLayout = DividerView.this.mSplitLayout;
            if (splitLayout == null) {
                return true;
            }
            AnimatorSet animatorSet = splitLayout.mSwapAnimator;
            if (animatorSet != null && animatorSet.isRunning()) {
                return true;
            }
            splitLayout.mSplitLayoutHandler.switchSplitPosition("double tap");
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return true;
        }
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.wm.shell.common.split.DividerView$3] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.android.wm.shell.common.split.DividerView$2] */
    public DividerView(Context context) {
        super(context);
        this.mPaint = new Paint();
        this.mBackgroundRect = new Rect();
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mSetTouchRegion = true;
        this.mDividerBounds = new Rect();
        this.mTempRect = new Rect();
        this.mAnimatorListener = new AnimatorListenerAdapter() { // from class: com.android.wm.shell.common.split.DividerView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                DividerView.this.mSetTouchRegion = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                DividerView.this.mSetTouchRegion = true;
            }
        };
        this.mHandleDelegate = new View.AccessibilityDelegate() { // from class: com.android.wm.shell.common.split.DividerView.3
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                SplitLayout splitLayout = DividerView.this.mSplitLayout;
                DividerSnapAlgorithm dividerSnapAlgorithm = splitLayout.mDividerSnapAlgorithm;
                if (splitLayout.mIsLeftRightSplit) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_full)));
                    DividerSnapAlgorithm.SnapTarget snapTarget = dividerSnapAlgorithm.mFirstSplitTarget;
                    DividerSnapAlgorithm.SnapTarget snapTarget2 = dividerSnapAlgorithm.mMiddleTarget;
                    if (snapTarget != snapTarget2) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_70, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_70)));
                    }
                    if (dividerSnapAlgorithm.mTargets.size() - 2 > 1) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_50, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_50)));
                    }
                    if (dividerSnapAlgorithm.mLastSplitTarget != snapTarget2) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_30, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_30)));
                    }
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_rb_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_right_full)));
                    return;
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_full)));
                DividerSnapAlgorithm.SnapTarget snapTarget3 = dividerSnapAlgorithm.mFirstSplitTarget;
                DividerSnapAlgorithm.SnapTarget snapTarget4 = dividerSnapAlgorithm.mMiddleTarget;
                if (snapTarget3 != snapTarget4) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_70, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_70)));
                }
                if (dividerSnapAlgorithm.mTargets.size() - 2 > 1) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_50, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_50)));
                }
                if (dividerSnapAlgorithm.mLastSplitTarget != snapTarget4) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_30, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_30)));
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_rb_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_bottom_full)));
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                SplitLayout splitLayout = DividerView.this.mSplitLayout;
                DividerSnapAlgorithm dividerSnapAlgorithm = splitLayout.mDividerSnapAlgorithm;
                DividerSnapAlgorithm.SnapTarget snapTarget = i == R.id.action_move_tl_full ? dividerSnapAlgorithm.mDismissEndTarget : i == R.id.action_move_tl_70 ? dividerSnapAlgorithm.mLastSplitTarget : i == R.id.action_move_tl_50 ? dividerSnapAlgorithm.mMiddleTarget : i == R.id.action_move_tl_30 ? dividerSnapAlgorithm.mFirstSplitTarget : i == R.id.action_move_rb_full ? dividerSnapAlgorithm.mDismissStartTarget : null;
                if (snapTarget == null) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                splitLayout.snapToTarget(splitLayout.mDividerPosition, snapTarget);
                return true;
            }
        };
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.drawRect(this.mBackgroundRect, this.mPaint);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mDividerBar = (FrameLayout) findViewById(R.id.divider_bar);
        this.mHandle = (DividerHandleView) findViewById(R.id.docked_divider_handle);
        this.mCorners = (DividerRoundedCorner) findViewById(R.id.docked_divider_rounded_corner);
        this.mTouchElevation = getResources().getDimensionPixelSize(R.dimen.docked_stack_divider_lift_elevation);
        this.mDoubleTapDetector = new GestureDetector(getContext(), new DoubleTapListener());
        this.mInteractive = true;
        this.mHideHandle = false;
        setOnTouchListener(this);
        this.mHandle.setAccessibilityDelegate(this.mHandleDelegate);
        setWillNotDraw(false);
        this.mPaint.setColor(getResources().getColor(R.color.split_divider_background, null));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        if (!DeviceConfig.getBoolean("systemui", "cursor_hover_states_enabled", false)) {
            return false;
        }
        if (motionEvent.getAction() == 9) {
            setHovering();
            return true;
        }
        if (motionEvent.getAction() != 10) {
            return false;
        }
        releaseHovering();
        return true;
    }

    public final void onInsetsChanged(InsetsState insetsState, boolean z) {
        this.mTempRect.set(this.mSplitLayout.mDividerBounds);
        if (!insetsState.isSourceOrDefaultVisible(InsetsSource.ID_IME, WindowInsets.Type.ime())) {
            for (int sourceSize = insetsState.sourceSize() - 1; sourceSize >= 0; sourceSize--) {
                InsetsSource sourceAt = insetsState.sourceAt(sourceSize);
                if (sourceAt.getType() == WindowInsets.Type.navigationBars() && sourceAt.hasFlags(2)) {
                    Rect rect = this.mTempRect;
                    rect.inset(sourceAt.calculateVisibleInsets(rect));
                }
            }
        }
        if (this.mTempRect.equals(this.mDividerBounds)) {
            return;
        }
        if (z) {
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this, DIVIDER_HEIGHT_PROPERTY, this.mDividerBounds.height(), this.mTempRect.height());
            ofInt.setInterpolator(InsetsController.RESIZE_INTERPOLATOR);
            ofInt.setDuration(300L);
            ofInt.addListener(this.mAnimatorListener);
            ofInt.start();
        } else {
            DIVIDER_HEIGHT_PROPERTY.set(this, Integer.valueOf(this.mTempRect.height()));
            this.mSetTouchRegion = true;
        }
        this.mDividerBounds.set(this.mTempRect);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mSetTouchRegion) {
            int width = (this.mDividerBounds.width() - this.mHandleRegionWidth) / 2;
            int height = this.mDividerBounds.height();
            int i5 = this.mHandleRegionHeight;
            int i6 = (height - i5) / 2;
            this.mTempRect.set(width, i6, this.mHandleRegionWidth + width, i5 + i6);
            this.mSplitWindowManager.setTouchRegion(this.mTempRect);
            this.mSetTouchRegion = false;
        }
        if (z) {
            boolean z2 = this.mSplitLayout.mIsLeftRightSplit;
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.split_divider_bar_width);
            int width2 = z2 ? (getWidth() - dimensionPixelSize) / 2 : 0;
            int height2 = z2 ? 0 : (getHeight() - dimensionPixelSize) / 2;
            this.mBackgroundRect.set(width2, height2, z2 ? width2 + dimensionPixelSize : getWidth(), z2 ? getHeight() : height2 + dimensionPixelSize);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        return PointerIcon.getSystemIcon(getContext(), this.mSplitLayout.mIsLeftRightSplit ? 1014 : 1015);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
    
        if (r8 != 3) goto L60;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.common.split.DividerView.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void releaseHovering() {
        DividerHandleView dividerHandleView = this.mHandle;
        if (dividerHandleView.mHovering) {
            dividerHandleView.setInputState(dividerHandleView.mHoveringWidth, dividerHandleView.mHoveringHeight, false);
            dividerHandleView.mHovering = false;
        }
        this.mHandle.animate().setInterpolator(Interpolators.FAST_OUT_SLOW_IN).setDuration(200L).translationZ(0.0f).start();
    }

    public final void releaseTouching() {
        setSlippery(true);
        DividerHandleView dividerHandleView = this.mHandle;
        if (dividerHandleView.mTouching) {
            dividerHandleView.setInputState(dividerHandleView.mTouchingWidth, dividerHandleView.mTouchingHeight, false);
            dividerHandleView.mTouching = false;
        }
        this.mHandle.animate().setInterpolator(Interpolators.FAST_OUT_SLOW_IN).setDuration(200L).translationZ(0.0f).start();
    }

    public void setHovering() {
        DividerHandleView dividerHandleView = this.mHandle;
        if (true != dividerHandleView.mHovering) {
            dividerHandleView.setInputState(dividerHandleView.mHoveringWidth, dividerHandleView.mHoveringHeight, true);
            dividerHandleView.mHovering = true;
        }
        this.mHandle.animate().setInterpolator(Interpolators.TOUCH_RESPONSE).setDuration(150L).translationZ(this.mTouchElevation).start();
    }

    public final void setInteractive(String str, boolean z, boolean z2) {
        if (z == this.mInteractive) {
            return;
        }
        int i = 0;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 6072005070485667526L, 12, z ? "interactive" : "non-interactive", Boolean.valueOf(z2), str);
        }
        this.mInteractive = z;
        this.mHideHandle = z2;
        if (!z && z2 && this.mMoving) {
            SplitLayout splitLayout = this.mSplitLayout;
            final int i2 = splitLayout.mDividerPosition;
            splitLayout.flingDividerPosition(this.mLastDraggingPosition, i2, 250, new Runnable() { // from class: com.android.wm.shell.common.split.DividerView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DividerView dividerView = DividerView.this;
                    dividerView.mSplitLayout.setDividerPosition(i2, true);
                }
            });
            this.mMoving = false;
        }
        releaseTouching();
        DividerHandleView dividerHandleView = this.mHandle;
        if (!this.mInteractive && this.mHideHandle) {
            i = 4;
        }
        dividerHandleView.setVisibility(i);
    }

    public final void setSlippery(boolean z) {
        if (this.mViewHost == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();
        int i = layoutParams.flags;
        if (((i & 536870912) != 0) == z) {
            return;
        }
        if (z) {
            layoutParams.flags = i | 536870912;
        } else {
            layoutParams.flags = (-536870913) & i;
        }
        this.mViewHost.relayout(layoutParams);
    }

    public final void setup(SplitLayout splitLayout, SplitWindowManager splitWindowManager, SurfaceControlViewHost surfaceControlViewHost, InsetsState insetsState) {
        this.mSplitLayout = splitLayout;
        this.mSplitWindowManager = splitWindowManager;
        this.mViewHost = surfaceControlViewHost;
        this.mDividerBounds.set(splitLayout.mDividerBounds);
        onInsetsChanged(insetsState, false);
        boolean z = this.mSplitLayout.mIsLeftRightSplit;
        DividerHandleView dividerHandleView = this.mHandle;
        dividerHandleView.mIsLeftRightSplit = z;
        dividerHandleView.updateDimens();
        this.mCorners.mIsLeftRightSplit = z;
        Resources resources = getResources();
        int i = R.dimen.split_divider_handle_region_width;
        this.mHandleRegionWidth = resources.getDimensionPixelSize(z ? R.dimen.split_divider_handle_region_height : R.dimen.split_divider_handle_region_width);
        Resources resources2 = getResources();
        if (!z) {
            i = DesktopModeStatus.canEnterDesktopMode(((FrameLayout) this).mContext) ? R.dimen.desktop_mode_portrait_split_divider_handle_region_height : R.dimen.split_divider_handle_region_height;
        }
        this.mHandleRegionHeight = resources2.getDimensionPixelSize(i);
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.wm.shell.common.split.DividerView$3] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.android.wm.shell.common.split.DividerView$2] */
    public DividerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaint = new Paint();
        this.mBackgroundRect = new Rect();
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mSetTouchRegion = true;
        this.mDividerBounds = new Rect();
        this.mTempRect = new Rect();
        this.mAnimatorListener = new AnimatorListenerAdapter() { // from class: com.android.wm.shell.common.split.DividerView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                DividerView.this.mSetTouchRegion = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                DividerView.this.mSetTouchRegion = true;
            }
        };
        this.mHandleDelegate = new View.AccessibilityDelegate() { // from class: com.android.wm.shell.common.split.DividerView.3
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                SplitLayout splitLayout = DividerView.this.mSplitLayout;
                DividerSnapAlgorithm dividerSnapAlgorithm = splitLayout.mDividerSnapAlgorithm;
                if (splitLayout.mIsLeftRightSplit) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_full)));
                    DividerSnapAlgorithm.SnapTarget snapTarget = dividerSnapAlgorithm.mFirstSplitTarget;
                    DividerSnapAlgorithm.SnapTarget snapTarget2 = dividerSnapAlgorithm.mMiddleTarget;
                    if (snapTarget != snapTarget2) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_70, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_70)));
                    }
                    if (dividerSnapAlgorithm.mTargets.size() - 2 > 1) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_50, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_50)));
                    }
                    if (dividerSnapAlgorithm.mLastSplitTarget != snapTarget2) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_30, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_30)));
                    }
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_rb_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_right_full)));
                    return;
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_full)));
                DividerSnapAlgorithm.SnapTarget snapTarget3 = dividerSnapAlgorithm.mFirstSplitTarget;
                DividerSnapAlgorithm.SnapTarget snapTarget4 = dividerSnapAlgorithm.mMiddleTarget;
                if (snapTarget3 != snapTarget4) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_70, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_70)));
                }
                if (dividerSnapAlgorithm.mTargets.size() - 2 > 1) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_50, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_50)));
                }
                if (dividerSnapAlgorithm.mLastSplitTarget != snapTarget4) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_30, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_30)));
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_rb_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_bottom_full)));
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                SplitLayout splitLayout = DividerView.this.mSplitLayout;
                DividerSnapAlgorithm dividerSnapAlgorithm = splitLayout.mDividerSnapAlgorithm;
                DividerSnapAlgorithm.SnapTarget snapTarget = i == R.id.action_move_tl_full ? dividerSnapAlgorithm.mDismissEndTarget : i == R.id.action_move_tl_70 ? dividerSnapAlgorithm.mLastSplitTarget : i == R.id.action_move_tl_50 ? dividerSnapAlgorithm.mMiddleTarget : i == R.id.action_move_tl_30 ? dividerSnapAlgorithm.mFirstSplitTarget : i == R.id.action_move_rb_full ? dividerSnapAlgorithm.mDismissStartTarget : null;
                if (snapTarget == null) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                splitLayout.snapToTarget(splitLayout.mDividerPosition, snapTarget);
                return true;
            }
        };
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.wm.shell.common.split.DividerView$3] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.android.wm.shell.common.split.DividerView$2] */
    public DividerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint = new Paint();
        this.mBackgroundRect = new Rect();
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mSetTouchRegion = true;
        this.mDividerBounds = new Rect();
        this.mTempRect = new Rect();
        this.mAnimatorListener = new AnimatorListenerAdapter() { // from class: com.android.wm.shell.common.split.DividerView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                DividerView.this.mSetTouchRegion = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                DividerView.this.mSetTouchRegion = true;
            }
        };
        this.mHandleDelegate = new View.AccessibilityDelegate() { // from class: com.android.wm.shell.common.split.DividerView.3
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                SplitLayout splitLayout = DividerView.this.mSplitLayout;
                DividerSnapAlgorithm dividerSnapAlgorithm = splitLayout.mDividerSnapAlgorithm;
                if (splitLayout.mIsLeftRightSplit) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_full)));
                    DividerSnapAlgorithm.SnapTarget snapTarget = dividerSnapAlgorithm.mFirstSplitTarget;
                    DividerSnapAlgorithm.SnapTarget snapTarget2 = dividerSnapAlgorithm.mMiddleTarget;
                    if (snapTarget != snapTarget2) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_70, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_70)));
                    }
                    if (dividerSnapAlgorithm.mTargets.size() - 2 > 1) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_50, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_50)));
                    }
                    if (dividerSnapAlgorithm.mLastSplitTarget != snapTarget2) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_30, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_30)));
                    }
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_rb_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_right_full)));
                    return;
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_full)));
                DividerSnapAlgorithm.SnapTarget snapTarget3 = dividerSnapAlgorithm.mFirstSplitTarget;
                DividerSnapAlgorithm.SnapTarget snapTarget4 = dividerSnapAlgorithm.mMiddleTarget;
                if (snapTarget3 != snapTarget4) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_70, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_70)));
                }
                if (dividerSnapAlgorithm.mTargets.size() - 2 > 1) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_50, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_50)));
                }
                if (dividerSnapAlgorithm.mLastSplitTarget != snapTarget4) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_30, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_30)));
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_rb_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_bottom_full)));
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
                SplitLayout splitLayout = DividerView.this.mSplitLayout;
                DividerSnapAlgorithm dividerSnapAlgorithm = splitLayout.mDividerSnapAlgorithm;
                DividerSnapAlgorithm.SnapTarget snapTarget = i2 == R.id.action_move_tl_full ? dividerSnapAlgorithm.mDismissEndTarget : i2 == R.id.action_move_tl_70 ? dividerSnapAlgorithm.mLastSplitTarget : i2 == R.id.action_move_tl_50 ? dividerSnapAlgorithm.mMiddleTarget : i2 == R.id.action_move_tl_30 ? dividerSnapAlgorithm.mFirstSplitTarget : i2 == R.id.action_move_rb_full ? dividerSnapAlgorithm.mDismissStartTarget : null;
                if (snapTarget == null) {
                    return super.performAccessibilityAction(view, i2, bundle);
                }
                splitLayout.snapToTarget(splitLayout.mDividerPosition, snapTarget);
                return true;
            }
        };
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.wm.shell.common.split.DividerView$3] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.android.wm.shell.common.split.DividerView$2] */
    public DividerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mPaint = new Paint();
        this.mBackgroundRect = new Rect();
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mSetTouchRegion = true;
        this.mDividerBounds = new Rect();
        this.mTempRect = new Rect();
        this.mAnimatorListener = new AnimatorListenerAdapter() { // from class: com.android.wm.shell.common.split.DividerView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                DividerView.this.mSetTouchRegion = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                DividerView.this.mSetTouchRegion = true;
            }
        };
        this.mHandleDelegate = new View.AccessibilityDelegate() { // from class: com.android.wm.shell.common.split.DividerView.3
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                SplitLayout splitLayout = DividerView.this.mSplitLayout;
                DividerSnapAlgorithm dividerSnapAlgorithm = splitLayout.mDividerSnapAlgorithm;
                if (splitLayout.mIsLeftRightSplit) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_full)));
                    DividerSnapAlgorithm.SnapTarget snapTarget = dividerSnapAlgorithm.mFirstSplitTarget;
                    DividerSnapAlgorithm.SnapTarget snapTarget2 = dividerSnapAlgorithm.mMiddleTarget;
                    if (snapTarget != snapTarget2) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_70, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_70)));
                    }
                    if (dividerSnapAlgorithm.mTargets.size() - 2 > 1) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_50, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_50)));
                    }
                    if (dividerSnapAlgorithm.mLastSplitTarget != snapTarget2) {
                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_30, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_left_30)));
                    }
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_rb_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_right_full)));
                    return;
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_full)));
                DividerSnapAlgorithm.SnapTarget snapTarget3 = dividerSnapAlgorithm.mFirstSplitTarget;
                DividerSnapAlgorithm.SnapTarget snapTarget4 = dividerSnapAlgorithm.mMiddleTarget;
                if (snapTarget3 != snapTarget4) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_70, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_70)));
                }
                if (dividerSnapAlgorithm.mTargets.size() - 2 > 1) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_50, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_50)));
                }
                if (dividerSnapAlgorithm.mLastSplitTarget != snapTarget4) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_tl_30, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_top_30)));
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.action_move_rb_full, ((FrameLayout) DividerView.this).mContext.getString(R.string.accessibility_action_divider_bottom_full)));
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i22, Bundle bundle) {
                SplitLayout splitLayout = DividerView.this.mSplitLayout;
                DividerSnapAlgorithm dividerSnapAlgorithm = splitLayout.mDividerSnapAlgorithm;
                DividerSnapAlgorithm.SnapTarget snapTarget = i22 == R.id.action_move_tl_full ? dividerSnapAlgorithm.mDismissEndTarget : i22 == R.id.action_move_tl_70 ? dividerSnapAlgorithm.mLastSplitTarget : i22 == R.id.action_move_tl_50 ? dividerSnapAlgorithm.mMiddleTarget : i22 == R.id.action_move_tl_30 ? dividerSnapAlgorithm.mFirstSplitTarget : i22 == R.id.action_move_rb_full ? dividerSnapAlgorithm.mDismissStartTarget : null;
                if (snapTarget == null) {
                    return super.performAccessibilityAction(view, i22, bundle);
                }
                splitLayout.snapToTarget(splitLayout.mDividerPosition, snapTarget);
                return true;
            }
        };
    }
}
