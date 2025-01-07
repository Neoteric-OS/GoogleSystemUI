package com.android.wm.shell.desktopmode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.LayerDrawable;
import android.util.DisplayMetrics;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.view.animation.DecelerateInterpolator;
import android.window.InputTransferToken;
import com.android.internal.policy.SystemBarUtils;
import com.android.wm.shell.R;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.desktopmode.DesktopModeVisualIndicator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeVisualIndicator {
    public final Context mContext;
    public IndicatorType mCurrentType = IndicatorType.NO_INDICATOR;
    public final DisplayController mDisplayController;
    public final DragStartState mDragStartState;
    public SurfaceControl mLeash;
    public final RootTaskDisplayAreaOrganizer mRootTdaOrganizer;
    public final SyncTransactionQueue mSyncQueue;
    public final ActivityManager.RunningTaskInfo mTaskInfo;
    public final SurfaceControl mTaskSurface;
    public View mView;
    public SurfaceControlViewHost mViewHost;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.desktopmode.DesktopModeVisualIndicator$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object val$finishCallback;

        public /* synthetic */ AnonymousClass1(int i, Object obj) {
            this.$r8$classId = i;
            this.val$finishCallback = obj;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    ((DesktopTasksController$dragToDesktopStateListener$1$removeVisualIndicator$1) this.val$finishCallback).run();
                    break;
                default:
                    ((VisualIndicatorAnimator) this.val$finishCallback).mView.getBackground().setBounds(((VisualIndicatorAnimator) this.val$finishCallback).mEndBounds);
                    break;
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DragStartState {
        public static final /* synthetic */ DragStartState[] $VALUES;
        public static final DragStartState DRAGGED_INTENT;
        public static final DragStartState FROM_FREEFORM;
        public static final DragStartState FROM_FULLSCREEN;
        public static final DragStartState FROM_SPLIT;

        static {
            DragStartState dragStartState = new DragStartState("FROM_FREEFORM", 0);
            FROM_FREEFORM = dragStartState;
            DragStartState dragStartState2 = new DragStartState("FROM_SPLIT", 1);
            FROM_SPLIT = dragStartState2;
            DragStartState dragStartState3 = new DragStartState("FROM_FULLSCREEN", 2);
            FROM_FULLSCREEN = dragStartState3;
            DragStartState dragStartState4 = new DragStartState("DRAGGED_INTENT", 3);
            DRAGGED_INTENT = dragStartState4;
            $VALUES = new DragStartState[]{dragStartState, dragStartState2, dragStartState3, dragStartState4};
        }

        public static DragStartState getDragStartState(ActivityManager.RunningTaskInfo runningTaskInfo) {
            if (runningTaskInfo.getWindowingMode() == 1) {
                return FROM_FULLSCREEN;
            }
            if (runningTaskInfo.getWindowingMode() == 6) {
                return FROM_SPLIT;
            }
            if (runningTaskInfo.isFreeform()) {
                return FROM_FREEFORM;
            }
            return null;
        }

        public static DragStartState valueOf(String str) {
            return (DragStartState) Enum.valueOf(DragStartState.class, str);
        }

        public static DragStartState[] values() {
            return (DragStartState[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IndicatorType {
        public static final /* synthetic */ IndicatorType[] $VALUES;
        public static final IndicatorType NO_INDICATOR;
        public static final IndicatorType TO_DESKTOP_INDICATOR;
        public static final IndicatorType TO_FULLSCREEN_INDICATOR;
        public static final IndicatorType TO_SPLIT_LEFT_INDICATOR;
        public static final IndicatorType TO_SPLIT_RIGHT_INDICATOR;

        static {
            IndicatorType indicatorType = new IndicatorType("NO_INDICATOR", 0);
            NO_INDICATOR = indicatorType;
            IndicatorType indicatorType2 = new IndicatorType("TO_DESKTOP_INDICATOR", 1);
            TO_DESKTOP_INDICATOR = indicatorType2;
            IndicatorType indicatorType3 = new IndicatorType("TO_FULLSCREEN_INDICATOR", 2);
            TO_FULLSCREEN_INDICATOR = indicatorType3;
            IndicatorType indicatorType4 = new IndicatorType("TO_SPLIT_LEFT_INDICATOR", 3);
            TO_SPLIT_LEFT_INDICATOR = indicatorType4;
            IndicatorType indicatorType5 = new IndicatorType("TO_SPLIT_RIGHT_INDICATOR", 4);
            TO_SPLIT_RIGHT_INDICATOR = indicatorType5;
            $VALUES = new IndicatorType[]{indicatorType, indicatorType2, indicatorType3, indicatorType4, indicatorType5};
        }

        public static IndicatorType valueOf(String str) {
            return (IndicatorType) Enum.valueOf(IndicatorType.class, str);
        }

        public static IndicatorType[] values() {
            return (IndicatorType[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VisualIndicatorAnimator extends ValueAnimator {
        public final Rect mEndBounds;
        public final RectEvaluator mRectEvaluator;
        public final Rect mStartBounds;
        public final View mView;

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        final class AlphaAnimType {
            public static final /* synthetic */ AlphaAnimType[] $VALUES;
            public static final AlphaAnimType ALPHA_FADE_IN_ANIM;
            public static final AlphaAnimType ALPHA_FADE_OUT_ANIM;
            public static final AlphaAnimType ALPHA_NO_CHANGE_ANIM;

            static {
                AlphaAnimType alphaAnimType = new AlphaAnimType("ALPHA_FADE_IN_ANIM", 0);
                ALPHA_FADE_IN_ANIM = alphaAnimType;
                AlphaAnimType alphaAnimType2 = new AlphaAnimType("ALPHA_FADE_OUT_ANIM", 1);
                ALPHA_FADE_OUT_ANIM = alphaAnimType2;
                AlphaAnimType alphaAnimType3 = new AlphaAnimType("ALPHA_NO_CHANGE_ANIM", 2);
                ALPHA_NO_CHANGE_ANIM = alphaAnimType3;
                $VALUES = new AlphaAnimType[]{alphaAnimType, alphaAnimType2, alphaAnimType3};
            }

            public static AlphaAnimType valueOf(String str) {
                return (AlphaAnimType) Enum.valueOf(AlphaAnimType.class, str);
            }

            public static AlphaAnimType[] values() {
                return (AlphaAnimType[]) $VALUES.clone();
            }
        }

        public VisualIndicatorAnimator(Rect rect, Rect rect2, View view) {
            this.mView = view;
            this.mStartBounds = new Rect(rect);
            this.mEndBounds = rect2;
            setFloatValues(0.0f, 1.0f);
            this.mRectEvaluator = new RectEvaluator(new Rect());
        }

        public static Rect getIndicatorBounds(DisplayLayout displayLayout, IndicatorType indicatorType) {
            int i = displayLayout.mStableInsets.top;
            int ordinal = indicatorType.ordinal();
            if (ordinal == 1) {
                float f = 1.0f - DesktopTasksController.DESKTOP_MODE_INITIAL_BOUNDS_SCALE;
                int i2 = displayLayout.mWidth;
                int i3 = displayLayout.mHeight;
                return new Rect((int) ((i2 * f) / 2.0f), (int) ((i3 * f) / 2.0f), (int) (i2 - ((i2 * f) / 2.0f)), (int) (i3 - ((f * i3) / 2.0f)));
            }
            if (ordinal == 2) {
                return new Rect(i, i, displayLayout.mWidth - i, displayLayout.mHeight - i);
            }
            if (ordinal == 3) {
                return new Rect(i, i, (displayLayout.mWidth / 2) - i, displayLayout.mHeight - i);
            }
            if (ordinal != 4) {
                throw new IllegalArgumentException("Invalid indicator type provided.");
            }
            int i4 = displayLayout.mWidth;
            return new Rect((i4 / 2) + i, i, i4 - i, displayLayout.mHeight - i);
        }

        public static Rect getMaxBounds(Rect rect) {
            return new Rect((int) (rect.left - (rect.width() * 0.015f)), (int) (rect.top - (rect.height() * 0.015f)), (int) ((rect.width() * 0.015f) + rect.right), (int) ((rect.height() * 0.015f) + rect.bottom));
        }

        public static void setupIndicatorAnimation(final VisualIndicatorAnimator visualIndicatorAnimator, final AlphaAnimType alphaAnimType) {
            visualIndicatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.desktopmode.DesktopModeVisualIndicator$VisualIndicatorAnimator$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DesktopModeVisualIndicator.VisualIndicatorAnimator visualIndicatorAnimator2 = DesktopModeVisualIndicator.VisualIndicatorAnimator.this;
                    DesktopModeVisualIndicator.VisualIndicatorAnimator.AlphaAnimType alphaAnimType2 = alphaAnimType;
                    if (visualIndicatorAnimator2.mView == null) {
                        visualIndicatorAnimator2.cancel();
                        return;
                    }
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    View view = visualIndicatorAnimator2.mView;
                    if (!visualIndicatorAnimator2.mStartBounds.equals(visualIndicatorAnimator2.mEndBounds)) {
                        view.getBackground().setBounds(visualIndicatorAnimator2.mRectEvaluator.evaluate(animatedFraction, visualIndicatorAnimator2.mStartBounds, visualIndicatorAnimator2.mEndBounds));
                    }
                    if (alphaAnimType2 == DesktopModeVisualIndicator.VisualIndicatorAnimator.AlphaAnimType.ALPHA_FADE_IN_ANIM) {
                        DesktopModeVisualIndicator.VisualIndicatorAnimator.updateIndicatorAlpha(visualIndicatorAnimator2.mView, valueAnimator.getAnimatedFraction());
                    } else if (alphaAnimType2 == DesktopModeVisualIndicator.VisualIndicatorAnimator.AlphaAnimType.ALPHA_FADE_OUT_ANIM) {
                        DesktopModeVisualIndicator.VisualIndicatorAnimator.updateIndicatorAlpha(visualIndicatorAnimator2.mView, 1.0f - valueAnimator.getAnimatedFraction());
                    }
                }
            });
            visualIndicatorAnimator.addListener(new AnonymousClass1(1, visualIndicatorAnimator));
            visualIndicatorAnimator.setDuration(200L);
        }

        public static void updateIndicatorAlpha(View view, float f) {
            LayerDrawable layerDrawable = (LayerDrawable) view.getBackground();
            float f2 = f * 255.0f;
            layerDrawable.findDrawableByLayerId(R.id.indicator_stroke).setAlpha((int) f2);
            layerDrawable.findDrawableByLayerId(R.id.indicator_solid).setAlpha((int) (f2 * 0.35f));
        }
    }

    public DesktopModeVisualIndicator(SyncTransactionQueue syncTransactionQueue, ActivityManager.RunningTaskInfo runningTaskInfo, DisplayController displayController, Context context, SurfaceControl surfaceControl, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, DragStartState dragStartState) {
        this.mSyncQueue = syncTransactionQueue;
        this.mTaskInfo = runningTaskInfo;
        this.mDisplayController = displayController;
        this.mContext = context;
        this.mTaskSurface = surfaceControl;
        this.mRootTdaOrganizer = rootTaskDisplayAreaOrganizer;
        this.mDragStartState = dragStartState;
    }

    public Region calculateFullscreenRegion(DisplayLayout displayLayout, int i) {
        Region region = new Region();
        DragStartState dragStartState = DragStartState.FROM_FREEFORM;
        DragStartState dragStartState2 = DragStartState.DRAGGED_INTENT;
        DragStartState dragStartState3 = this.mDragStartState;
        int statusBarHeight = (dragStartState3 == dragStartState || dragStartState3 == dragStartState2) ? SystemBarUtils.getStatusBarHeight(this.mContext) : displayLayout.mStableInsets.top * 2;
        if (dragStartState3 == dragStartState) {
            float f = displayLayout.mWidth * this.mContext.getResources().getFloat(R.dimen.desktop_mode_fullscreen_region_scale);
            int i2 = displayLayout.mWidth;
            float f2 = f / 2.0f;
            region.union(new Rect((int) ((i2 / 2.0f) - f2), -i, (int) ((i2 / 2.0f) + f2), statusBarHeight));
        }
        if (dragStartState3 == DragStartState.FROM_FULLSCREEN || dragStartState3 == DragStartState.FROM_SPLIT || dragStartState3 == dragStartState2) {
            region.union(new Rect(0, -i, displayLayout.mWidth, statusBarHeight));
        }
        return region;
    }

    public Region calculateSplitLeftRegion(DisplayLayout displayLayout, int i, int i2) {
        Region region = new Region();
        region.union(new Rect(0, this.mDragStartState == DragStartState.FROM_FREEFORM ? this.mContext.getResources().getDimensionPixelSize(R.dimen.desktop_mode_split_from_desktop_height) : -i2, i, displayLayout.mHeight));
        return region;
    }

    public Region calculateSplitRightRegion(DisplayLayout displayLayout, int i, int i2) {
        Region region = new Region();
        int dimensionPixelSize = this.mDragStartState == DragStartState.FROM_FREEFORM ? this.mContext.getResources().getDimensionPixelSize(R.dimen.desktop_mode_split_from_desktop_height) : -i2;
        int i3 = displayLayout.mWidth;
        region.union(new Rect(i3 - i, dimensionPixelSize, i3, displayLayout.mHeight));
        return region;
    }

    public Region calculateToDesktopRegion(DisplayLayout displayLayout, Region region, Region region2, Region region3) {
        Region region4 = new Region();
        if (this.mDragStartState != DragStartState.FROM_FREEFORM) {
            region4.union(new Rect(0, 0, displayLayout.mWidth, displayLayout.mHeight));
            Region.Op op = Region.Op.DIFFERENCE;
            region4.op(region, op);
            region4.op(region2, op);
            region4.op(region3, op);
        }
        return region4;
    }

    public final void fadeOutIndicator(DesktopTasksController$dragToDesktopStateListener$1$removeVisualIndicator$1 desktopTasksController$dragToDesktopStateListener$1$removeVisualIndicator$1) {
        View view = this.mView;
        Rect indicatorBounds = VisualIndicatorAnimator.getIndicatorBounds(this.mDisplayController.getDisplayLayout(this.mTaskInfo.displayId), this.mCurrentType);
        Rect maxBounds = VisualIndicatorAnimator.getMaxBounds(indicatorBounds);
        view.getBackground().setBounds(maxBounds);
        VisualIndicatorAnimator visualIndicatorAnimator = new VisualIndicatorAnimator(maxBounds, indicatorBounds, view);
        visualIndicatorAnimator.setInterpolator(new DecelerateInterpolator());
        VisualIndicatorAnimator.setupIndicatorAnimation(visualIndicatorAnimator, VisualIndicatorAnimator.AlphaAnimType.ALPHA_FADE_OUT_ANIM);
        visualIndicatorAnimator.start();
        if (desktopTasksController$dragToDesktopStateListener$1$removeVisualIndicator$1 != null) {
            visualIndicatorAnimator.addListener(new AnonymousClass1(0, desktopTasksController$dragToDesktopStateListener$1$removeVisualIndicator$1));
        }
        this.mCurrentType = IndicatorType.NO_INDICATOR;
    }

    public final void releaseVisualIndicator(SurfaceControl.Transaction transaction) {
        SurfaceControlViewHost surfaceControlViewHost = this.mViewHost;
        if (surfaceControlViewHost == null) {
            return;
        }
        if (surfaceControlViewHost != null) {
            surfaceControlViewHost.release();
            this.mViewHost = null;
        }
        SurfaceControl surfaceControl = this.mLeash;
        if (surfaceControl != null) {
            transaction.remove(surfaceControl);
            this.mLeash = null;
        }
    }

    public final IndicatorType updateIndicatorType(PointF pointF) {
        int i = this.mTaskInfo.displayId;
        DisplayController displayController = this.mDisplayController;
        DisplayLayout displayLayout = displayController.getDisplayLayout(i);
        IndicatorType indicatorType = IndicatorType.NO_INDICATOR;
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.desktop_mode_transition_region_thickness);
        int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.desktop_mode_freeform_decor_caption_height);
        Region calculateFullscreenRegion = calculateFullscreenRegion(displayLayout, dimensionPixelSize2);
        Region calculateSplitLeftRegion = calculateSplitLeftRegion(displayLayout, dimensionPixelSize, dimensionPixelSize2);
        Region calculateSplitRightRegion = calculateSplitRightRegion(displayLayout, dimensionPixelSize, dimensionPixelSize2);
        Region calculateToDesktopRegion = calculateToDesktopRegion(displayLayout, calculateSplitLeftRegion, calculateSplitRightRegion, calculateFullscreenRegion);
        IndicatorType indicatorType2 = calculateFullscreenRegion.contains((int) pointF.x, (int) pointF.y) ? IndicatorType.TO_FULLSCREEN_INDICATOR : indicatorType;
        if (calculateSplitLeftRegion.contains((int) pointF.x, (int) pointF.y)) {
            indicatorType2 = IndicatorType.TO_SPLIT_LEFT_INDICATOR;
        }
        if (calculateSplitRightRegion.contains((int) pointF.x, (int) pointF.y)) {
            indicatorType2 = IndicatorType.TO_SPLIT_RIGHT_INDICATOR;
        }
        if (calculateToDesktopRegion.contains((int) pointF.x, (int) pointF.y)) {
            indicatorType2 = IndicatorType.TO_DESKTOP_INDICATOR;
        }
        if (this.mDragStartState != DragStartState.DRAGGED_INTENT && this.mCurrentType != indicatorType2) {
            if (this.mView == null) {
                final SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
                int i2 = displayMetrics.widthPixels;
                int i3 = displayMetrics.heightPixels;
                this.mView = new View(this.mContext);
                SurfaceControl.Builder builder = new SurfaceControl.Builder();
                this.mRootTdaOrganizer.attachToDisplayArea(this.mTaskInfo.displayId, builder);
                SurfaceControl build = builder.setName("Desktop Mode Visual Indicator").setContainerLayer().setCallsite("DesktopModeVisualIndicator.createView").build();
                this.mLeash = build;
                transaction.show(build);
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i2, i3, 2, 8, -2);
                layoutParams.setTitle("Desktop Mode Visual Indicator");
                layoutParams.setTrustedOverlay();
                SurfaceControlViewHost surfaceControlViewHost = new SurfaceControlViewHost(this.mContext, displayController.getDisplay(this.mTaskInfo.displayId), new WindowlessWindowManager(this.mTaskInfo.configuration, this.mLeash, (InputTransferToken) null), "DesktopModeVisualIndicator");
                this.mViewHost = surfaceControlViewHost;
                surfaceControlViewHost.setView(this.mView, layoutParams);
                transaction.setRelativeLayer(this.mLeash, this.mTaskSurface, -1);
                this.mSyncQueue.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.wm.shell.desktopmode.DesktopModeVisualIndicator$$ExternalSyntheticLambda0
                    @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
                    public final void runWithTransaction(SurfaceControl.Transaction transaction2) {
                        SurfaceControl.Transaction transaction3 = transaction;
                        transaction2.merge(transaction3);
                        transaction3.close();
                    }
                });
            }
            if (this.mCurrentType == indicatorType) {
                this.mView.setBackgroundResource(R.drawable.desktop_windowing_transition_background);
                View view = this.mView;
                Rect indicatorBounds = VisualIndicatorAnimator.getIndicatorBounds(displayController.getDisplayLayout(this.mTaskInfo.displayId), indicatorType2);
                view.getBackground().setBounds(indicatorBounds);
                VisualIndicatorAnimator visualIndicatorAnimator = new VisualIndicatorAnimator(indicatorBounds, VisualIndicatorAnimator.getMaxBounds(indicatorBounds), view);
                visualIndicatorAnimator.setInterpolator(new DecelerateInterpolator());
                VisualIndicatorAnimator.setupIndicatorAnimation(visualIndicatorAnimator, VisualIndicatorAnimator.AlphaAnimType.ALPHA_FADE_IN_ANIM);
                visualIndicatorAnimator.start();
                this.mCurrentType = indicatorType2;
            } else if (indicatorType2 == indicatorType) {
                fadeOutIndicator(null);
            } else {
                View view2 = this.mView;
                DisplayLayout displayLayout2 = displayController.getDisplayLayout(this.mTaskInfo.displayId);
                VisualIndicatorAnimator visualIndicatorAnimator2 = new VisualIndicatorAnimator(VisualIndicatorAnimator.getIndicatorBounds(displayLayout2, this.mCurrentType), VisualIndicatorAnimator.getIndicatorBounds(displayLayout2, indicatorType2), view2);
                visualIndicatorAnimator2.setInterpolator(new DecelerateInterpolator());
                VisualIndicatorAnimator.setupIndicatorAnimation(visualIndicatorAnimator2, VisualIndicatorAnimator.AlphaAnimType.ALPHA_NO_CHANGE_ANIM);
                this.mCurrentType = indicatorType2;
                visualIndicatorAnimator2.start();
            }
        }
        return indicatorType2;
    }
}
