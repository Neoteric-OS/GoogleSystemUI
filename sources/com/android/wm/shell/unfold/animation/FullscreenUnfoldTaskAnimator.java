package com.android.wm.shell.unfold.animation;

import android.animation.RectEvaluator;
import android.animation.TypeEvaluator;
import android.app.TaskInfo;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Trace;
import android.util.MathUtils;
import android.util.SparseArray;
import android.view.InsetsSource;
import android.view.InsetsState;
import android.view.SurfaceControl;
import android.view.WindowInsets;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.sysui.ConfigurationChangeListener;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.unfold.UnfoldBackgroundController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FullscreenUnfoldTaskAnimator implements UnfoldTaskAnimator, DisplayInsetsController.OnInsetsChangedListener, ConfigurationChangeListener {
    public static final float[] FLOAT_9 = new float[9];
    public static final TypeEvaluator RECT_EVALUATOR = new RectEvaluator(new Rect());
    public final SparseArray mAnimationContextByTaskId = new SparseArray();
    public final UnfoldBackgroundController mBackgroundController;
    public final Context mContext;
    public final DisplayInsetsController mDisplayInsetsController;
    public InsetsSource mExpandedTaskbarInsetsSource;
    public final ShellController mShellController;
    public float mWindowCornerRadiusPx;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationContext {
        public final SurfaceControl mLeash;
        public TaskInfo mTaskInfo;
        public final Rect mStartCropRect = new Rect();
        public final Rect mEndCropRect = new Rect();
        public final Rect mCurrentCropRect = new Rect();
        public final Matrix mMatrix = new Matrix();

        public AnimationContext(SurfaceControl surfaceControl, InsetsSource insetsSource, TaskInfo taskInfo) {
            this.mLeash = surfaceControl;
            update(insetsSource, taskInfo);
        }

        public final void update(InsetsSource insetsSource, TaskInfo taskInfo) {
            this.mTaskInfo = taskInfo;
            this.mStartCropRect.set(taskInfo.getConfiguration().windowConfiguration.getBounds());
            if (insetsSource != null) {
                Rect rect = this.mStartCropRect;
                rect.inset(insetsSource.calculateVisibleInsets(rect));
            }
            this.mEndCropRect.set(this.mStartCropRect);
            int width = (int) (this.mEndCropRect.width() * 0.08f);
            Rect rect2 = this.mStartCropRect;
            Rect rect3 = this.mEndCropRect;
            rect2.left = rect3.left + width;
            rect2.right = rect3.right - width;
            int height = (int) (rect3.height() * 0.03f);
            Rect rect4 = this.mStartCropRect;
            Rect rect5 = this.mEndCropRect;
            rect4.top = rect5.top + height;
            rect4.bottom = rect5.bottom - height;
        }
    }

    public FullscreenUnfoldTaskAnimator(Context context, UnfoldBackgroundController unfoldBackgroundController, ShellController shellController, DisplayInsetsController displayInsetsController) {
        this.mContext = context;
        this.mDisplayInsetsController = displayInsetsController;
        this.mBackgroundController = unfoldBackgroundController;
        this.mShellController = shellController;
        this.mWindowCornerRadiusPx = ScreenDecorationsUtils.getWindowCornerRadius(context);
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void applyAnimationProgress(float f, SurfaceControl.Transaction transaction) {
        if (this.mAnimationContextByTaskId.size() == 0) {
            return;
        }
        for (int size = this.mAnimationContextByTaskId.size() - 1; size >= 0; size--) {
            AnimationContext animationContext = (AnimationContext) this.mAnimationContextByTaskId.valueAt(size);
            animationContext.mCurrentCropRect.set((Rect) RECT_EVALUATOR.evaluate(f, animationContext.mStartCropRect, animationContext.mEndCropRect));
            float lerp = MathUtils.lerp(0.94f, 1.0f, f);
            animationContext.mMatrix.setScale(lerp, lerp, animationContext.mCurrentCropRect.exactCenterX(), animationContext.mCurrentCropRect.exactCenterY());
            transaction.setWindowCrop(animationContext.mLeash, animationContext.mCurrentCropRect).setMatrix(animationContext.mLeash, animationContext.mMatrix, FLOAT_9).setCornerRadius(animationContext.mLeash, this.mWindowCornerRadiusPx).show(animationContext.mLeash);
        }
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void clearTasks() {
        this.mAnimationContextByTaskId.clear();
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final boolean hasActiveTasks() {
        return this.mAnimationContextByTaskId.size() > 0;
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void init() {
        this.mDisplayInsetsController.addInsetsChangedListener(0, this);
        this.mShellController.addConfigurationChangeListener(this);
    }

    @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
    public final void insetsChanged(InsetsState insetsState) {
        InsetsSource insetsSource;
        int sourceSize = insetsState.sourceSize() - 1;
        while (true) {
            if (sourceSize < 0) {
                insetsSource = null;
                break;
            }
            insetsSource = insetsState.sourceAt(sourceSize);
            if (insetsSource.getType() == WindowInsets.Type.navigationBars() && insetsSource.hasFlags(2)) {
                break;
            } else {
                sourceSize--;
            }
        }
        this.mExpandedTaskbarInsetsSource = insetsSource;
        for (int size = this.mAnimationContextByTaskId.size() - 1; size >= 0; size--) {
            AnimationContext animationContext = (AnimationContext) this.mAnimationContextByTaskId.valueAt(size);
            animationContext.update(this.mExpandedTaskbarInsetsSource, animationContext.mTaskInfo);
        }
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final boolean isApplicableTask(TaskInfo taskInfo) {
        return (taskInfo == null || !taskInfo.isVisible() || taskInfo.realActivity == null || taskInfo.getWindowingMode() != 1 || taskInfo.getActivityType() == 2) ? false : true;
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onConfigurationChanged(Configuration configuration) {
        Trace.beginSection("FullscreenUnfoldTaskAnimator#onConfigurationChanged");
        this.mWindowCornerRadiusPx = ScreenDecorationsUtils.getWindowCornerRadius(this.mContext);
        Trace.endSection();
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void onTaskAppeared(TaskInfo taskInfo, SurfaceControl surfaceControl) {
        this.mAnimationContextByTaskId.put(taskInfo.taskId, new AnimationContext(surfaceControl, this.mExpandedTaskbarInsetsSource, taskInfo));
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void onTaskChanged(TaskInfo taskInfo) {
        AnimationContext animationContext = (AnimationContext) this.mAnimationContextByTaskId.get(taskInfo.taskId);
        if (animationContext != null) {
            animationContext.update(this.mExpandedTaskbarInsetsSource, taskInfo);
        }
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void onTaskVanished(TaskInfo taskInfo) {
        this.mAnimationContextByTaskId.remove(taskInfo.taskId);
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void prepareFinishTransaction(SurfaceControl.Transaction transaction) {
        UnfoldBackgroundController unfoldBackgroundController = this.mBackgroundController;
        SurfaceControl surfaceControl = unfoldBackgroundController.mBackgroundLayer;
        if (surfaceControl == null) {
            return;
        }
        if (surfaceControl.isValid()) {
            transaction.remove(unfoldBackgroundController.mBackgroundLayer);
        }
        unfoldBackgroundController.mBackgroundLayer = null;
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void prepareStartTransaction(SurfaceControl.Transaction transaction) {
        this.mBackgroundController.ensureBackground(transaction);
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void resetAllSurfaces(SurfaceControl.Transaction transaction) {
        for (int size = this.mAnimationContextByTaskId.size() - 1; size >= 0; size--) {
            resetSurface((AnimationContext) this.mAnimationContextByTaskId.valueAt(size), transaction);
        }
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void resetSurface(TaskInfo taskInfo, SurfaceControl.Transaction transaction) {
        AnimationContext animationContext = (AnimationContext) this.mAnimationContextByTaskId.get(taskInfo.taskId);
        if (animationContext != null) {
            resetSurface(animationContext, transaction);
        }
    }

    public static void resetSurface(AnimationContext animationContext, SurfaceControl.Transaction transaction) {
        SurfaceControl.Transaction matrix = transaction.setWindowCrop(animationContext.mLeash, null).setCornerRadius(animationContext.mLeash, 0.0f).setMatrix(animationContext.mLeash, 1.0f, 0.0f, 0.0f, 1.0f);
        SurfaceControl surfaceControl = animationContext.mLeash;
        Point point = animationContext.mTaskInfo.positionInParent;
        matrix.setPosition(surfaceControl, point.x, point.y);
    }
}
