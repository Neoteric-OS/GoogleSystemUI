package com.android.wm.shell.common.split;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Choreographer;
import android.view.Display;
import android.view.InsetsSourceControl;
import android.view.InsetsState;
import android.view.RoundedCorner;
import android.view.SurfaceControl;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.split.DividerSnapAlgorithm;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.splitscreen.StageCoordinator;
import com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda1;
import com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda13;
import com.android.wm.shell.splitscreen.StageTaskListener;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitLayout implements DisplayInsetsController.OnInsetsChangedListener {
    public final boolean mAllowLeftRightSplitInPortrait;
    public final Rect mBounds1;
    public final Rect mBounds2;
    public Context mContext;
    public int mDensity;
    public final boolean mDimNonImeSide;
    public final DisplayController mDisplayController;
    public final DisplayImeController mDisplayImeController;
    public final Rect mDividerBounds;
    public ValueAnimator mDividerFlingAnimator;
    public int mDividerInsets;
    public int mDividerPosition;
    public int mDividerSize;
    DividerSnapAlgorithm mDividerSnapAlgorithm;
    public int mDividerWindowWidth;
    public boolean mFreezeDividerWindow;
    public final Handler mHandler;
    public final ImePositionProcessor mImePositionProcessor;
    public boolean mInitialized;
    public final InsetsState mInsetsState;
    public final InteractionJankMonitor mInteractionJankMonitor;
    public final Rect mInvisibleBounds;
    public boolean mIsLargeScreen;
    public boolean mIsLeftRightSplit;
    public int mOrientation;
    public final Rect mRootBounds;
    public int mRotation;
    public final StageCoordinator mSplitLayoutHandler;
    public final SplitWindowManager mSplitWindowManager;
    public final ResizingEffectPolicy mSurfaceEffectPolicy;
    public AnimatorSet mSwapAnimator;
    public final ShellTaskOrganizer mTaskOrganizer;
    public final Rect mTempRect = new Rect();
    public int mUiMode;
    public final Rect mWinBounds1;
    public final Rect mWinBounds2;
    public WindowContainerToken mWinToken1;
    public WindowContainerToken mWinToken2;
    public static final Interpolator SHRINK_INTERPOLATOR = new PathInterpolator(0.2f, 0.0f, 0.0f, 1.0f);
    public static final Interpolator GROW_INTERPOLATOR = new PathInterpolator(0.45f, 0.0f, 0.5f, 1.0f);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ImePositionProcessor implements DisplayImeController.ImePositionProcessor {
        public float mDimValue1;
        public float mDimValue2;
        public final int mDisplayId;
        public int mEndImeTop;
        public boolean mHasImeFocus;
        public boolean mImeShown;
        public float mLastDim1;
        public float mLastDim2;
        public int mLastYOffset;
        public int mStartImeTop;
        public float mTargetDim1;
        public float mTargetDim2;
        public int mTargetYOffset;
        public int mYOffsetForIme;
        public final /* synthetic */ SplitLayout this$0;

        public ImePositionProcessor(int i, SplitLayout splitLayout) {
            this.this$0 = splitLayout;
            this.mDisplayId = i;
        }

        @Override // com.android.wm.shell.common.DisplayImeController.ImePositionProcessor
        public final void onImeControlTargetChanged(int i, boolean z) {
            if (i == this.mDisplayId && !z && this.mImeShown) {
                reset();
                SplitLayout splitLayout = this.this$0;
                DividerView dividerView = splitLayout.mSplitWindowManager.mDividerView;
                if (dividerView != null) {
                    dividerView.setInteractive("onImeControlTargetChanged", true, true);
                }
                StageCoordinator stageCoordinator = splitLayout.mSplitLayoutHandler;
                stageCoordinator.setLayoutOffsetTarget(0, splitLayout);
                stageCoordinator.onLayoutPositionChanging(splitLayout);
            }
        }

        @Override // com.android.wm.shell.common.DisplayImeController.ImePositionProcessor
        public final void onImeEndPositioning(int i, boolean z) {
            if (i == this.mDisplayId && this.mHasImeFocus && !z) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -9149866949618287148L, 3, Boolean.valueOf(z));
                }
                onProgress(1.0f);
                SplitLayout splitLayout = this.this$0;
                splitLayout.mSplitLayoutHandler.onLayoutPositionChanging(splitLayout);
            }
        }

        @Override // com.android.wm.shell.common.DisplayImeController.ImePositionProcessor
        public final void onImePositionChanged(int i, int i2) {
            if (i == this.mDisplayId && this.mHasImeFocus) {
                onProgress((i2 - this.mStartImeTop) / (this.mEndImeTop - r3));
                SplitLayout splitLayout = this.this$0;
                splitLayout.mSplitLayoutHandler.onLayoutPositionChanging(splitLayout);
            }
        }

        @Override // com.android.wm.shell.common.DisplayImeController.ImePositionProcessor
        public final int onImeStartPositioning(int i, int i2, int i3, boolean z, boolean z2) {
            int i4 = this.mDisplayId;
            if (i != i4) {
                return 0;
            }
            SplitLayout splitLayout = this.this$0;
            if (!splitLayout.mInitialized) {
                return 0;
            }
            WindowContainerToken imeTarget = splitLayout.mTaskOrganizer.getImeTarget(i4);
            StageCoordinator stageCoordinator = splitLayout.mSplitLayoutHandler;
            int splitItemPosition = stageCoordinator.getSplitItemPosition(imeTarget);
            boolean z3 = splitItemPosition != -1;
            this.mHasImeFocus = z3;
            if (!z3) {
                return 0;
            }
            int i5 = z ? i2 : i3;
            this.mStartImeTop = i5;
            if (z) {
                i2 = i3;
            }
            this.mEndImeTop = i2;
            this.mImeShown = z;
            this.mLastDim1 = this.mDimValue1;
            boolean z4 = splitLayout.mDimNonImeSide;
            float f = 0.0f;
            this.mTargetDim1 = (splitItemPosition == 1 && z && z4) ? 0.3f : 0.0f;
            this.mLastDim2 = this.mDimValue2;
            if (splitItemPosition == 0 && z && z4) {
                f = 0.3f;
            }
            this.mTargetDim2 = f;
            this.mLastYOffset = this.mYOffsetForIme;
            int i6 = (splitItemPosition != 1 || z2 || splitLayout.mIsLeftRightSplit || !z) ? 0 : -Math.min(Math.abs(i2 - i5), (int) (splitLayout.mBounds1.height() * 0.7f));
            this.mTargetYOffset = i6;
            int i7 = this.mLastYOffset;
            if (i6 != i7) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 506703508172978242L, 5, Long.valueOf(i7), Long.valueOf(i6));
                }
                int i8 = this.mTargetYOffset;
                if (i8 == 0) {
                    stageCoordinator.setLayoutOffsetTarget(0, splitLayout);
                } else {
                    stageCoordinator.setLayoutOffsetTarget(i8, splitLayout);
                }
            }
            boolean z5 = (this.mImeShown && this.mHasImeFocus && !z2) ? false : true;
            DividerView dividerView = splitLayout.mSplitWindowManager.mDividerView;
            if (dividerView != null) {
                dividerView.setInteractive("onImeStartPositioning", z5, true);
            }
            return this.mTargetYOffset != this.mLastYOffset ? 1 : 0;
        }

        public final void onProgress(float f) {
            float f2 = this.mLastDim1;
            this.mDimValue1 = AndroidFlingSpline$$ExternalSyntheticOutline0.m(this.mTargetDim1, f2, f, f2);
            float f3 = this.mLastDim2;
            this.mDimValue2 = AndroidFlingSpline$$ExternalSyntheticOutline0.m(this.mTargetDim2, f3, f, f3);
            float f4 = this.mLastYOffset;
            this.mYOffsetForIme = (int) AndroidFlingSpline$$ExternalSyntheticOutline0.m(this.mTargetYOffset, f4, f, f4);
        }

        public final void reset() {
            this.mHasImeFocus = false;
            this.mImeShown = false;
            this.mTargetYOffset = 0;
            this.mLastYOffset = 0;
            this.mYOffsetForIme = 0;
            this.mTargetDim1 = 0.0f;
            this.mLastDim1 = 0.0f;
            this.mDimValue1 = 0.0f;
            this.mTargetDim2 = 0.0f;
            this.mLastDim2 = 0.0f;
            this.mDimValue2 = 0.0f;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ResizingEffectPolicy {
        public int mShrinkSide = -1;
        public int mDismissingSide = -1;
        public final Point mParallaxOffset = new Point();
        public float mDismissingDimValue = 0.0f;
        public final Rect mContentBounds = new Rect();
        public final Rect mSurfaceBounds = new Rect();
        public final int mParallaxType = 2;

        public ResizingEffectPolicy() {
        }
    }

    public SplitLayout(Context context, Configuration configuration, StageCoordinator stageCoordinator, StageCoordinator.AnonymousClass1 anonymousClass1, DisplayController displayController, DisplayImeController displayImeController, ShellTaskOrganizer shellTaskOrganizer, Handler handler) {
        Rect rect = new Rect();
        this.mRootBounds = rect;
        this.mDividerBounds = new Rect();
        this.mBounds1 = new Rect();
        this.mBounds2 = new Rect();
        this.mInvisibleBounds = new Rect();
        this.mWinBounds1 = new Rect();
        this.mWinBounds2 = new Rect();
        this.mInsetsState = new InsetsState();
        this.mInitialized = false;
        this.mFreezeDividerWindow = false;
        this.mIsLargeScreen = false;
        this.mHandler = handler;
        this.mContext = context.createConfigurationContext(configuration);
        this.mOrientation = configuration.orientation;
        this.mRotation = configuration.windowConfiguration.getRotation();
        this.mDensity = configuration.densityDpi;
        this.mIsLargeScreen = configuration.smallestScreenWidthDp >= 600;
        this.mSplitLayoutHandler = stageCoordinator;
        this.mDisplayController = displayController;
        this.mDisplayImeController = displayImeController;
        this.mSplitWindowManager = new SplitWindowManager(this.mContext, configuration, anonymousClass1);
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mImePositionProcessor = new ImePositionProcessor(this.mContext.getDisplayId(), this);
        this.mSurfaceEffectPolicy = new ResizingEffectPolicy();
        Resources resources = this.mContext.getResources();
        this.mDimNonImeSide = resources.getBoolean(R.bool.config_dimNonImeAttachedSide);
        boolean z = resources.getBoolean(android.R.bool.config_letterboxIsVerticalReachabilityEnabled);
        this.mAllowLeftRightSplitInPortrait = z;
        this.mIsLeftRightSplit = SplitScreenUtils.isLeftRightSplit(z, configuration);
        updateDividerConfig(this.mContext);
        rect.set(configuration.windowConfiguration.getBounds());
        this.mDividerSnapAlgorithm = getSnapAlgorithm(this.mContext, rect);
        this.mInteractionJankMonitor = InteractionJankMonitor.getInstance();
        resetDividerPosition();
        updateInvisibleRect();
    }

    public final int calculateCurrentSnapPosition() {
        return this.mDividerSnapAlgorithm.snap(this.mDividerPosition, true).snapPosition;
    }

    public void flingDividerPosition(int i, int i2, int i3, final Runnable runnable) {
        if (i == i2) {
            if (runnable != null) {
                runnable.run();
            }
            this.mInteractionJankMonitor.end(52);
            return;
        }
        ValueAnimator valueAnimator = this.mDividerFlingAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator duration = ValueAnimator.ofInt(i, i2).setDuration(i3);
        this.mDividerFlingAnimator = duration;
        duration.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        DividerView dividerView = this.mSplitWindowManager.mDividerView;
        final boolean z = dividerView != null && dividerView.mMoving;
        this.mDividerFlingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.common.split.SplitLayout$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                SplitLayout splitLayout = SplitLayout.this;
                boolean z2 = z;
                splitLayout.getClass();
                splitLayout.updateDividerBounds(((Integer) valueAnimator2.getAnimatedValue()).intValue(), z2);
            }
        });
        this.mDividerFlingAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.common.split.SplitLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                SplitLayout.this.mDividerFlingAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
                SplitLayout.this.mInteractionJankMonitor.end(52);
                SplitLayout.this.mDividerFlingAnimator = null;
            }
        });
        this.mDividerFlingAnimator.start();
    }

    public final void flingDividerToCenter(final StageCoordinator$$ExternalSyntheticLambda1 stageCoordinator$$ExternalSyntheticLambda1) {
        final int i = this.mDividerSnapAlgorithm.mMiddleTarget.position;
        flingDividerPosition(this.mDividerPosition, i, 450, new Runnable() { // from class: com.android.wm.shell.common.split.SplitLayout$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SplitLayout splitLayout = SplitLayout.this;
                int i2 = i;
                StageCoordinator$$ExternalSyntheticLambda1 stageCoordinator$$ExternalSyntheticLambda12 = stageCoordinator$$ExternalSyntheticLambda1;
                splitLayout.setDividerPosition(i2, true);
                if (stageCoordinator$$ExternalSyntheticLambda12 != null) {
                    stageCoordinator$$ExternalSyntheticLambda12.run();
                }
            }
        });
    }

    public final void flingDividerToDismiss(final int i, final boolean z) {
        flingDividerPosition(this.mDividerPosition, z ? this.mDividerSnapAlgorithm.mDismissEndTarget.position : this.mDividerSnapAlgorithm.mDismissStartTarget.position, 450, new Runnable() { // from class: com.android.wm.shell.common.split.SplitLayout$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                SplitLayout splitLayout = SplitLayout.this;
                boolean z2 = z;
                splitLayout.mSplitLayoutHandler.onSnappedToDismiss(i, z2);
            }
        });
    }

    public final Rect getBounds1() {
        return new Rect(this.mBounds1);
    }

    public final Rect getBounds2() {
        return new Rect(this.mBounds2);
    }

    public final Rect getDisplayStableInsets(Context context) {
        DisplayLayout displayLayout = this.mDisplayController.getDisplayLayout(context.getDisplayId());
        return displayLayout != null ? displayLayout.mStableInsets : ((WindowManager) context.getSystemService(WindowManager.class)).getMaximumWindowMetrics().getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout()).toRect();
    }

    public final SurfaceControl getDividerLeash() {
        SplitWindowManager splitWindowManager = this.mSplitWindowManager;
        if (splitWindowManager == null) {
            return null;
        }
        return splitWindowManager.mLeash;
    }

    public final float getDividerPositionAsFraction() {
        float f;
        int i;
        if (this.mIsLeftRightSplit) {
            int i2 = this.mBounds1.right;
            f = (i2 + r3.left) / 2.0f;
            i = this.mBounds2.right;
        } else {
            int i3 = this.mBounds1.bottom;
            f = (i3 + r3.top) / 2.0f;
            i = this.mBounds2.bottom;
        }
        return Math.min(1.0f, Math.max(0.0f, f / i));
    }

    public final void getRefDividerBounds(Rect rect) {
        rect.set(this.mDividerBounds);
        Rect rect2 = this.mRootBounds;
        rect.offset(-rect2.left, -rect2.top);
    }

    public final DividerSnapAlgorithm getSnapAlgorithm(Context context, Rect rect) {
        Rect displayStableInsets = getDisplayStableInsets(context);
        if (!this.mIsLeftRightSplit) {
            int max = Math.max(displayStableInsets.top, displayStableInsets.bottom);
            displayStableInsets.set(displayStableInsets.left, max, displayStableInsets.right, max);
        }
        Resources resources = context.getResources();
        int width = rect.width();
        int height = rect.height();
        int i = this.mDividerSize;
        boolean z = this.mIsLeftRightSplit;
        return new DividerSnapAlgorithm(resources, width, height, i, !z, displayStableInsets, z ? 1 : 2);
    }

    public final void initDividerPosition(Rect rect, boolean z) {
        float width = this.mDividerPosition / (z ? rect.width() : rect.height());
        int width2 = this.mIsLeftRightSplit ? this.mRootBounds.width() : this.mRootBounds.height();
        DividerSnapAlgorithm dividerSnapAlgorithm = this.mDividerSnapAlgorithm;
        DividerSnapAlgorithm.SnapTarget snap = dividerSnapAlgorithm.snap((int) (width2 * width), false);
        if (snap == dividerSnapAlgorithm.mDismissStartTarget) {
            snap = dividerSnapAlgorithm.mFirstSplitTarget;
        } else if (snap == dividerSnapAlgorithm.mDismissEndTarget) {
            snap = dividerSnapAlgorithm.mLastSplitTarget;
        }
        int i = snap.position;
        this.mDividerPosition = i;
        updateBounds(i);
    }

    @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
    public final void insetsChanged(InsetsState insetsState) {
        DividerView dividerView;
        this.mInsetsState.set(insetsState);
        if (!this.mInitialized || this.mFreezeDividerWindow || (dividerView = this.mSplitWindowManager.mDividerView) == null) {
            return;
        }
        dividerView.onInsetsChanged(insetsState, true);
    }

    @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
    public final void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) {
        if (this.mInsetsState.equals(insetsState)) {
            return;
        }
        insetsChanged(insetsState);
    }

    public final ValueAnimator moveSurface(final SurfaceControl.Transaction transaction, StageTaskListener stageTaskListener, Rect rect, Rect rect2, final float f, final float f2, final boolean z, final boolean z2, final boolean z3) {
        boolean z4 = stageTaskListener != null;
        SurfaceControl dividerLeash = z4 ? stageTaskListener.mRootLeash : getDividerLeash();
        final ActivityManager.RunningTaskInfo runningTaskInfo = z4 ? stageTaskListener.mRootTaskInfo : null;
        final SplitDecorManager splitDecorManager = z4 ? stageTaskListener.mSplitDecorManager : null;
        final Rect rect3 = new Rect(rect);
        Rect rect4 = new Rect(rect2);
        final float f3 = rect4.left - rect3.left;
        final float f4 = rect4.top - rect3.top;
        final float width = rect4.width() - rect3.width();
        final float height = rect4.height() - rect3.height();
        float radius = this.mSplitWindowManager.mDividerView.getDisplay().getRoundedCorner(0) == null ? 0.0f : r0.getRadius();
        final float applyDimension = ((int) TypedValue.applyDimension(1, 14.0f, this.mContext.getResources().getDisplayMetrics())) * 2.0f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setInterpolator(Interpolators.LINEAR);
        final SurfaceControl surfaceControl = dividerLeash;
        final float f5 = radius;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.common.split.SplitLayout$$ExternalSyntheticLambda2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SplitLayout splitLayout = SplitLayout.this;
                SurfaceControl surfaceControl2 = surfaceControl;
                boolean z5 = z;
                SurfaceControl.Transaction transaction2 = transaction;
                float f6 = f5;
                Rect rect5 = rect3;
                float f7 = f3;
                float f8 = f4;
                float f9 = width;
                float f10 = height;
                boolean z6 = z2;
                float f11 = applyDimension;
                ActivityManager.RunningTaskInfo runningTaskInfo2 = runningTaskInfo;
                float f12 = f;
                float f13 = f2;
                boolean z7 = z3;
                SplitDecorManager splitDecorManager2 = splitDecorManager;
                splitLayout.getClass();
                if (surfaceControl2 == null) {
                    return;
                }
                if (z5) {
                    transaction2.setCornerRadius(surfaceControl2, f6);
                }
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                float interpolation = ((PathInterpolator) Interpolators.EMPHASIZED).getInterpolation(floatValue);
                float f14 = (f7 * interpolation) + rect5.left;
                float f15 = (f8 * interpolation) + rect5.top;
                int width2 = (int) ((f9 * interpolation) + rect5.width());
                int height2 = (int) ((f10 * interpolation) + rect5.height());
                if (z6) {
                    float f16 = height2;
                    float f17 = f11 / f16;
                    float f18 = width2;
                    float f19 = f11 / f18;
                    float interpolation2 = floatValue <= 0.166f ? ((PathInterpolator) SplitLayout.SHRINK_INTERPOLATOR).getInterpolation(floatValue / 0.166f) : 1.0f - ((PathInterpolator) SplitLayout.GROW_INTERPOLATOR).getInterpolation((floatValue - 0.166f) / 0.834f);
                    float f20 = f17 * interpolation2;
                    float f21 = 1.0f - f20;
                    float f22 = f19 * interpolation2;
                    float f23 = 1.0f - f22;
                    f14 += (f20 * f18) / 2.0f;
                    f15 += (f22 * f16) / 2.0f;
                    width2 = (int) (f18 * f21);
                    height2 = (int) (f16 * f23);
                    transaction2.setScale(surfaceControl2, f21, f23);
                }
                if (runningTaskInfo2 != null) {
                    transaction2.setLayer(surfaceControl2, z6 ? -20 : 10);
                } else {
                    transaction2.setLayer(surfaceControl2, 0);
                }
                if (f12 == 0.0f && f13 == 0.0f) {
                    transaction2.setPosition(surfaceControl2, f14, f15);
                    splitLayout.mTempRect.set((int) f14, (int) f15, (int) (f14 + width2), (int) (f15 + height2));
                    transaction2.setWindowCrop(surfaceControl2, width2, height2);
                    if (z7) {
                        splitDecorManager2.drawNextVeilFrameForSwapAnimation(runningTaskInfo2, splitLayout.mTempRect, transaction2, z6, surfaceControl2, 0.0f, 0.0f);
                    }
                } else {
                    int i = (int) (f12 * interpolation);
                    int i2 = (int) (interpolation * f13);
                    float f24 = i;
                    float f25 = i2;
                    transaction2.setPosition(surfaceControl2, f14 + f24, f15 + f25);
                    splitLayout.mTempRect.set(0, 0, width2, height2);
                    splitLayout.mTempRect.offsetTo(-i, -i2);
                    transaction2.setCrop(surfaceControl2, splitLayout.mTempRect);
                    if (z7) {
                        splitDecorManager2.drawNextVeilFrameForSwapAnimation(runningTaskInfo2, splitLayout.mTempRect, transaction2, z6, surfaceControl2, f24, f25);
                    }
                }
                transaction2.apply();
            }
        });
        return ofFloat;
    }

    public final void playSwapAnimation(SurfaceControl.Transaction transaction, StageTaskListener stageTaskListener, StageTaskListener stageTaskListener2, final StageCoordinator$$ExternalSyntheticLambda13 stageCoordinator$$ExternalSyntheticLambda13) {
        final Rect displayStableInsets = getDisplayStableInsets(this.mContext);
        boolean z = this.mIsLeftRightSplit;
        displayStableInsets.set(z ? displayStableInsets.left : 0, z ? 0 : displayStableInsets.top, z ? displayStableInsets.right : 0, z ? 0 : displayStableInsets.bottom);
        boolean z2 = (displayStableInsets.left == 0 && displayStableInsets.top == 0 && displayStableInsets.right == 0 && displayStableInsets.bottom == 0) ? false : true;
        DividerSnapAlgorithm dividerSnapAlgorithm = this.mDividerSnapAlgorithm;
        DividerSnapAlgorithm.SnapTarget snap = dividerSnapAlgorithm.snap(this.mIsLeftRightSplit ? this.mBounds2.width() : this.mBounds2.height(), false);
        if (snap == dividerSnapAlgorithm.mDismissStartTarget) {
            snap = dividerSnapAlgorithm.mFirstSplitTarget;
        } else if (snap == dividerSnapAlgorithm.mDismissEndTarget) {
            snap = dividerSnapAlgorithm.mLastSplitTarget;
        }
        final int i = snap.position;
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        Rect rect3 = new Rect();
        updateBounds(i, rect2, rect, rect3, false);
        Rect rect4 = this.mRootBounds;
        rect.offset(-rect4.left, -rect4.top);
        Rect rect5 = this.mRootBounds;
        rect2.offset(-rect5.left, -rect5.top);
        Rect rect6 = this.mRootBounds;
        rect3.offset(-rect6.left, -rect6.top);
        Rect bounds1 = getBounds1();
        Rect rect7 = this.mRootBounds;
        bounds1.offset(-rect7.left, -rect7.top);
        ValueAnimator moveSurface = moveSurface(transaction, stageTaskListener, bounds1, rect, -displayStableInsets.left, -displayStableInsets.top, true, true, z2);
        Rect bounds2 = getBounds2();
        Rect rect8 = this.mRootBounds;
        bounds2.offset(-rect8.left, -rect8.top);
        ValueAnimator moveSurface2 = moveSurface(transaction, stageTaskListener2, bounds2, rect2, displayStableInsets.left, displayStableInsets.top, true, false, z2);
        Rect rect9 = new Rect(this.mDividerBounds);
        Rect rect10 = this.mRootBounds;
        rect9.offset(-rect10.left, -rect10.top);
        ValueAnimator moveSurface3 = moveSurface(transaction, null, rect9, rect3, 0.0f, 0.0f, false, false, false);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mSwapAnimator = animatorSet;
        animatorSet.playTogether(moveSurface, moveSurface2, moveSurface3);
        this.mSwapAnimator.setDuration(500L);
        this.mSwapAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.common.split.SplitLayout.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                SplitLayout.this.mInteractionJankMonitor.cancel(82);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                SplitLayout splitLayout = SplitLayout.this;
                int i2 = i;
                splitLayout.mDividerPosition = i2;
                splitLayout.updateBounds(i2);
                stageCoordinator$$ExternalSyntheticLambda13.accept(displayStableInsets);
                SplitLayout.this.mInteractionJankMonitor.end(82);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                SplitLayout splitLayout = SplitLayout.this;
                InteractionJankMonitor interactionJankMonitor = splitLayout.mInteractionJankMonitor;
                SurfaceControl dividerLeash = splitLayout.getDividerLeash();
                SplitLayout splitLayout2 = SplitLayout.this;
                interactionJankMonitor.begin(dividerLeash, splitLayout2.mContext, splitLayout2.mHandler, 82);
            }
        });
        this.mSwapAnimator.start();
    }

    public final void release(SurfaceControl.Transaction transaction) {
        if (this.mInitialized) {
            this.mInitialized = false;
            this.mSplitWindowManager.release(transaction);
            DisplayImeController displayImeController = this.mDisplayImeController;
            ImePositionProcessor imePositionProcessor = this.mImePositionProcessor;
            synchronized (displayImeController.mPositionProcessors) {
                displayImeController.mPositionProcessors.remove(imePositionProcessor);
            }
            this.mImePositionProcessor.reset();
            ValueAnimator valueAnimator = this.mDividerFlingAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            resetDividerPosition();
        }
    }

    public final void resetDividerPosition() {
        int i = this.mDividerSnapAlgorithm.mMiddleTarget.position;
        this.mDividerPosition = i;
        updateBounds(i);
        this.mWinToken1 = null;
        this.mWinToken2 = null;
        this.mWinBounds1.setEmpty();
        this.mWinBounds2.setEmpty();
    }

    public final void rotateTo(int i) {
        boolean z = (((i - this.mRotation) + 4) % 4) % 2 != 0;
        this.mRotation = i;
        Rect rect = new Rect(this.mRootBounds);
        if (z) {
            Rect rect2 = this.mRootBounds;
            rect.set(rect2.top, rect2.left, rect2.bottom, rect2.right);
        }
        boolean z2 = this.mIsLargeScreen;
        boolean z3 = this.mRootBounds.width() >= this.mRootBounds.height();
        boolean z4 = this.mAllowLeftRightSplitInPortrait;
        if (z4 && z2) {
            z3 = !z3;
        }
        this.mTempRect.set(this.mRootBounds);
        this.mRootBounds.set(rect);
        boolean z5 = this.mIsLargeScreen;
        boolean z6 = this.mRootBounds.width() >= this.mRootBounds.height();
        if (z4 && z5) {
            z6 = !z6;
        }
        this.mIsLeftRightSplit = z6;
        this.mDividerSnapAlgorithm = getSnapAlgorithm(this.mContext, this.mRootBounds);
        initDividerPosition(this.mTempRect, z3);
    }

    public final void setDivideRatio(int i) {
        DividerSnapAlgorithm.SnapTarget snapTarget;
        Iterator it = this.mDividerSnapAlgorithm.mTargets.iterator();
        while (true) {
            if (!it.hasNext()) {
                snapTarget = null;
                break;
            } else {
                snapTarget = (DividerSnapAlgorithm.SnapTarget) it.next();
                if (snapTarget.snapPosition == i) {
                    break;
                }
            }
        }
        setDividerPosition(snapTarget != null ? snapTarget.position : this.mDividerSnapAlgorithm.mMiddleTarget.position, false);
    }

    public final void setDividerAtBorder(boolean z) {
        setDividerPosition(z ? this.mDividerSnapAlgorithm.mDismissStartTarget.position : this.mDividerSnapAlgorithm.mDismissEndTarget.position, false);
    }

    public final void setDividerPosition(int i, boolean z) {
        this.mDividerPosition = i;
        updateBounds(i);
        if (z) {
            this.mSplitLayoutHandler.onLayoutSizeChanged(this);
        }
    }

    public final void setTaskBounds(WindowContainerTransaction windowContainerTransaction, ActivityManager.RunningTaskInfo runningTaskInfo, Rect rect) {
        windowContainerTransaction.setBounds(runningTaskInfo.token, rect);
        WindowContainerToken windowContainerToken = runningTaskInfo.token;
        this.mTempRect.set(rect);
        this.mTempRect.inset(getDisplayStableInsets(this.mContext));
        windowContainerTransaction.setSmallestScreenWidthDp(windowContainerToken, (int) (Math.min(this.mTempRect.width(), this.mTempRect.height()) / this.mContext.getResources().getDisplayMetrics().density));
    }

    public final void snapToTarget(int i, final DividerSnapAlgorithm.SnapTarget snapTarget) {
        int i2 = snapTarget.snapPosition;
        int i3 = snapTarget.position;
        if (i2 == 11) {
            final int i4 = 0;
            flingDividerPosition(i, i3, 250, new Runnable() { // from class: com.android.wm.shell.common.split.SplitLayout$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    int i5 = i4;
                    SplitLayout splitLayout = this;
                    switch (i5) {
                        case 0:
                            splitLayout.mSplitLayoutHandler.onSnappedToDismiss(4, false);
                            break;
                        default:
                            splitLayout.mSplitLayoutHandler.onSnappedToDismiss(4, true);
                            break;
                    }
                }
            });
        } else if (i2 != 12) {
            flingDividerPosition(i, i3, 250, new Runnable() { // from class: com.android.wm.shell.common.split.SplitLayout$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    SplitLayout splitLayout = SplitLayout.this;
                    DividerSnapAlgorithm.SnapTarget snapTarget2 = snapTarget;
                    splitLayout.getClass();
                    splitLayout.setDividerPosition(snapTarget2.position, true);
                }
            });
        } else {
            final int i5 = 1;
            flingDividerPosition(i, i3, 250, new Runnable() { // from class: com.android.wm.shell.common.split.SplitLayout$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    int i52 = i5;
                    SplitLayout splitLayout = this;
                    switch (i52) {
                        case 0:
                            splitLayout.mSplitLayoutHandler.onSnappedToDismiss(4, false);
                            break;
                        default:
                            splitLayout.mSplitLayoutHandler.onSnappedToDismiss(4, true);
                            break;
                    }
                }
            });
        }
    }

    public final void update(SurfaceControl.Transaction transaction, boolean z) {
        boolean z2 = this.mInitialized;
        ImePositionProcessor imePositionProcessor = this.mImePositionProcessor;
        SplitWindowManager splitWindowManager = this.mSplitWindowManager;
        if (z2) {
            splitWindowManager.release(transaction);
            if (z) {
                imePositionProcessor.reset();
            }
            splitWindowManager.init(this, this.mInsetsState, true);
            this.mSplitLayoutHandler.onLayoutPositionChanging(this);
            return;
        }
        if (z2) {
            return;
        }
        this.mInitialized = true;
        splitWindowManager.init(this, this.mInsetsState, false);
        this.mDisplayImeController.addPositionProcessor(imePositionProcessor);
    }

    public final void updateBounds(int i) {
        updateBounds(i, this.mBounds1, this.mBounds2, this.mDividerBounds, true);
    }

    public final boolean updateConfiguration(Configuration configuration) {
        int rotation = configuration.windowConfiguration.getRotation();
        Rect bounds = configuration.windowConfiguration.getBounds();
        int i = configuration.orientation;
        int i2 = configuration.densityDpi;
        int i3 = configuration.uiMode;
        boolean z = this.mIsLeftRightSplit;
        if (this.mOrientation == i && this.mRotation == rotation && this.mDensity == i2 && this.mUiMode == i3 && this.mRootBounds.equals(bounds)) {
            return false;
        }
        this.mContext = this.mContext.createConfigurationContext(configuration);
        this.mSplitWindowManager.setConfiguration(configuration);
        this.mOrientation = i;
        this.mTempRect.set(this.mRootBounds);
        this.mRootBounds.set(bounds);
        this.mRotation = rotation;
        this.mDensity = i2;
        this.mUiMode = i3;
        this.mIsLargeScreen = configuration.smallestScreenWidthDp >= 600;
        this.mIsLeftRightSplit = SplitScreenUtils.isLeftRightSplit(this.mAllowLeftRightSplitInPortrait, configuration);
        this.mDividerSnapAlgorithm = getSnapAlgorithm(this.mContext, this.mRootBounds);
        updateDividerConfig(this.mContext);
        initDividerPosition(this.mTempRect, z);
        updateInvisibleRect();
        return true;
    }

    public final void updateDividerBounds(int i, boolean z) {
        updateBounds(i);
        Point point = this.mSurfaceEffectPolicy.mParallaxOffset;
        int i2 = point.x;
        int i3 = point.y;
        StageCoordinator stageCoordinator = this.mSplitLayoutHandler;
        TransactionPool transactionPool = stageCoordinator.mTransactionPool;
        SurfaceControl.Transaction acquire = transactionPool.acquire();
        acquire.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
        stageCoordinator.updateSurfaceBounds(this, acquire, z);
        Rect rect = stageCoordinator.mTempRect1;
        if (stageCoordinator.mSideStagePosition == 0) {
            rect.set(stageCoordinator.mSplitLayout.mBounds2);
        } else {
            rect.set(stageCoordinator.mSplitLayout.mBounds1);
        }
        Rect rect2 = stageCoordinator.mTempRect2;
        if (stageCoordinator.mSideStagePosition == 0) {
            rect2.set(stageCoordinator.mSplitLayout.mBounds1);
        } else {
            rect2.set(stageCoordinator.mSplitLayout.mBounds2);
        }
        stageCoordinator.mMainStage.onResizing(stageCoordinator.mTempRect1, stageCoordinator.mTempRect2, acquire, i2, i3, stageCoordinator.mShowDecorImmediately);
        stageCoordinator.mSideStage.onResizing(stageCoordinator.mTempRect2, stageCoordinator.mTempRect1, acquire, i2, i3, stageCoordinator.mShowDecorImmediately);
        acquire.apply();
        transactionPool.release(acquire);
    }

    public final void updateDividerConfig(Context context) {
        Resources resources = context.getResources();
        Display display = context.getDisplay();
        int dimensionPixelSize = resources.getDimensionPixelSize(android.R.dimen.edit_text_inset_top_material);
        RoundedCorner roundedCorner = display.getRoundedCorner(0);
        int max = roundedCorner != null ? Math.max(0, roundedCorner.getRadius()) : 0;
        RoundedCorner roundedCorner2 = display.getRoundedCorner(1);
        if (roundedCorner2 != null) {
            max = Math.max(max, roundedCorner2.getRadius());
        }
        RoundedCorner roundedCorner3 = display.getRoundedCorner(2);
        if (roundedCorner3 != null) {
            max = Math.max(max, roundedCorner3.getRadius());
        }
        RoundedCorner roundedCorner4 = display.getRoundedCorner(3);
        if (roundedCorner4 != null) {
            max = Math.max(max, roundedCorner4.getRadius());
        }
        this.mDividerInsets = Math.max(dimensionPixelSize, max);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.split_divider_bar_width);
        this.mDividerSize = dimensionPixelSize2;
        this.mDividerWindowWidth = (this.mDividerInsets * 2) + dimensionPixelSize2;
    }

    public final void updateInvisibleRect() {
        Rect rect = this.mInvisibleBounds;
        Rect rect2 = this.mRootBounds;
        int i = rect2.left;
        int i2 = rect2.top;
        boolean z = this.mIsLeftRightSplit;
        int i3 = rect2.right;
        if (z) {
            i3 /= 2;
        }
        int i4 = rect2.bottom;
        if (!z) {
            i4 /= 2;
        }
        rect.set(i, i2, i3, i4);
        Rect rect3 = this.mInvisibleBounds;
        boolean z2 = this.mIsLeftRightSplit;
        rect3.offset(z2 ? this.mRootBounds.right : 0, z2 ? 0 : this.mRootBounds.bottom);
    }

    public final void updateBounds(int i, Rect rect, Rect rect2, Rect rect3, boolean z) {
        int i2;
        float f;
        rect3.set(this.mRootBounds);
        rect.set(this.mRootBounds);
        rect2.set(this.mRootBounds);
        if (this.mIsLeftRightSplit) {
            i2 = i + this.mRootBounds.left;
            int i3 = i2 - this.mDividerInsets;
            rect3.left = i3;
            rect3.right = i3 + this.mDividerWindowWidth;
            rect.right = i2;
            rect2.left = this.mDividerSize + i2;
        } else {
            i2 = i + this.mRootBounds.top;
            int i4 = i2 - this.mDividerInsets;
            rect3.top = i4;
            rect3.bottom = i4 + this.mDividerWindowWidth;
            rect.bottom = i2;
            rect2.top = this.mDividerSize + i2;
        }
        DockedDividerUtils.sanitizeStackBounds(rect, true);
        int i5 = 0;
        DockedDividerUtils.sanitizeStackBounds(rect2, false);
        if (z) {
            boolean z2 = this.mIsLeftRightSplit;
            ResizingEffectPolicy resizingEffectPolicy = this.mSurfaceEffectPolicy;
            resizingEffectPolicy.mDismissingSide = -1;
            resizingEffectPolicy.mParallaxOffset.set(0, 0);
            resizingEffectPolicy.mDismissingDimValue = 0.0f;
            SplitLayout splitLayout = SplitLayout.this;
            DividerSnapAlgorithm dividerSnapAlgorithm = splitLayout.mDividerSnapAlgorithm;
            int i6 = dividerSnapAlgorithm.mFirstSplitTarget.position;
            if (i2 < i6) {
                resizingEffectPolicy.mDismissingSide = z2 ? 1 : 2;
                i5 = dividerSnapAlgorithm.mDismissStartTarget.position - i6;
            } else {
                int i7 = dividerSnapAlgorithm.mLastSplitTarget.position;
                if (i2 > i7) {
                    resizingEffectPolicy.mDismissingSide = z2 ? 3 : 4;
                    i5 = i7 - dividerSnapAlgorithm.mDismissEndTarget.position;
                }
            }
            if (!z2 ? i2 < splitLayout.mWinBounds1.bottom : i2 < splitLayout.mWinBounds1.right) {
                resizingEffectPolicy.mShrinkSide = z2 ? 3 : 4;
                resizingEffectPolicy.mContentBounds.set(splitLayout.mWinBounds2);
                resizingEffectPolicy.mSurfaceBounds.set(splitLayout.mBounds2);
            } else {
                resizingEffectPolicy.mShrinkSide = z2 ? 1 : 2;
                resizingEffectPolicy.mContentBounds.set(splitLayout.mWinBounds1);
                resizingEffectPolicy.mSurfaceBounds.set(splitLayout.mBounds1);
            }
            int i8 = resizingEffectPolicy.mDismissingSide;
            int i9 = resizingEffectPolicy.mParallaxType;
            if (i8 != -1) {
                DividerSnapAlgorithm dividerSnapAlgorithm2 = splitLayout.mDividerSnapAlgorithm;
                if (i2 < dividerSnapAlgorithm2.mFirstSplitTarget.position) {
                    boolean z3 = dividerSnapAlgorithm2.mIsHorizontalDivision;
                    f = 1.0f - ((i2 - (z3 ? dividerSnapAlgorithm2.mInsets.top : dividerSnapAlgorithm2.mInsets.left)) / (r1 - (z3 ? dividerSnapAlgorithm2.mInsets.top : dividerSnapAlgorithm2.mInsets.left)));
                } else {
                    f = i2 > dividerSnapAlgorithm2.mLastSplitTarget.position ? (i2 - r1) / ((dividerSnapAlgorithm2.mDismissEndTarget.position - r1) - dividerSnapAlgorithm2.mDividerSize) : 0.0f;
                }
                float max = Math.max(0.0f, Math.min(f, 1.0f));
                resizingEffectPolicy.mDismissingDimValue = Interpolators.DIM_INTERPOLATOR.getInterpolation(max);
                if (i9 == 1) {
                    int i10 = resizingEffectPolicy.mDismissingSide;
                    float interpolation = Interpolators.SLOWDOWN_INTERPOLATOR.getInterpolation(max) / 3.5f;
                    if (i10 == 2) {
                        interpolation /= 2.0f;
                    }
                    if (z2) {
                        resizingEffectPolicy.mParallaxOffset.x = (int) (interpolation * i5);
                    } else {
                        resizingEffectPolicy.mParallaxOffset.y = (int) (interpolation * i5);
                    }
                }
            }
            if (i9 == 2) {
                if (z2) {
                    resizingEffectPolicy.mParallaxOffset.x = (resizingEffectPolicy.mSurfaceBounds.width() - resizingEffectPolicy.mContentBounds.width()) / 2;
                } else {
                    resizingEffectPolicy.mParallaxOffset.y = (resizingEffectPolicy.mSurfaceBounds.height() - resizingEffectPolicy.mContentBounds.height()) / 2;
                }
            }
        }
    }
}
