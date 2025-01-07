package com.android.wm.shell.unfold.animation;

import android.animation.RectEvaluator;
import android.animation.TypeEvaluator;
import android.app.TaskInfo;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Trace;
import android.util.SparseArray;
import android.view.InsetsSource;
import android.view.InsetsState;
import android.view.SurfaceControl;
import android.view.WindowInsets;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.splitscreen.SplitScreen;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.splitscreen.SplitScreenController$SplitScreenImpl$$ExternalSyntheticLambda0;
import com.android.wm.shell.sysui.ConfigurationChangeListener;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.unfold.UnfoldBackgroundController;
import com.android.wm.shell.unfold.animation.SplitTaskUnfoldAnimator;
import dagger.Lazy;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitTaskUnfoldAnimator implements UnfoldTaskAnimator, DisplayInsetsController.OnInsetsChangedListener, SplitScreen.SplitScreenListener, ConfigurationChangeListener {
    public static final TypeEvaluator RECT_EVALUATOR = new RectEvaluator(new Rect());
    public final Context mContext;
    public final DisplayInsetsController mDisplayInsetsController;
    public final ShellExecutor mExecutor;
    public InsetsSource mExpandedTaskbarInsetsSource;
    public final ShellController mShellController;
    public final Lazy mSplitScreenController;
    public final UnfoldBackgroundController mUnfoldBackgroundController;
    public float mWindowCornerRadiusPx;
    public final SparseArray mAnimationContextByTaskId = new SparseArray();
    public final Rect mMainStageBounds = new Rect();
    public final Rect mSideStageBounds = new Rect();
    public final Rect mRootStageBounds = new Rect();
    public int mMainStagePosition = -1;
    public int mSideStagePosition = -1;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationContext {
        public final SurfaceControl mLeash;
        public final Rect mStartCropRect = new Rect();
        public final Rect mEndCropRect = new Rect();
        public final Rect mCurrentCropRect = new Rect();
        public int mStageType = -1;

        public AnimationContext(SurfaceControl surfaceControl) {
            this.mLeash = surfaceControl;
            update();
        }

        public final void update() {
            int i;
            Insets of;
            int i2;
            int i3 = this.mStageType;
            SplitTaskUnfoldAnimator splitTaskUnfoldAnimator = SplitTaskUnfoldAnimator.this;
            this.mStartCropRect.set(i3 == 0 ? splitTaskUnfoldAnimator.mMainStageBounds : splitTaskUnfoldAnimator.mSideStageBounds);
            InsetsSource insetsSource = splitTaskUnfoldAnimator.mExpandedTaskbarInsetsSource;
            int i4 = 0;
            boolean z = insetsSource != null;
            if (z) {
                Rect rect = this.mStartCropRect;
                rect.inset(insetsSource.calculateVisibleInsets(rect));
            }
            this.mStartCropRect.offsetTo(0, 0);
            this.mEndCropRect.set(this.mStartCropRect);
            int max = (int) (Math.max(this.mEndCropRect.width(), this.mEndCropRect.height()) * 0.05f);
            if (splitTaskUnfoldAnimator.mRootStageBounds.width() > splitTaskUnfoldAnimator.mRootStageBounds.height()) {
                int i5 = z ? 0 : max;
                if ((this.mStageType == 0 ? splitTaskUnfoldAnimator.mMainStagePosition : splitTaskUnfoldAnimator.mSideStagePosition) == 0) {
                    i2 = 0;
                    i4 = max;
                } else {
                    i2 = max;
                }
                of = Insets.of(i4, max, i2, i5);
            } else {
                if ((this.mStageType == 0 ? splitTaskUnfoldAnimator.mMainStagePosition : splitTaskUnfoldAnimator.mSideStagePosition) == 0) {
                    i = 0;
                    i4 = max;
                } else {
                    i = z ? 0 : max;
                }
                of = Insets.of(max, i4, max, i);
            }
            this.mStartCropRect.inset(of);
        }
    }

    public SplitTaskUnfoldAnimator(Context context, DisplayInsetsController displayInsetsController, ShellExecutor shellExecutor, ShellController shellController, UnfoldBackgroundController unfoldBackgroundController, Lazy lazy) {
        this.mDisplayInsetsController = displayInsetsController;
        this.mExecutor = shellExecutor;
        this.mContext = context;
        this.mShellController = shellController;
        this.mUnfoldBackgroundController = unfoldBackgroundController;
        this.mSplitScreenController = lazy;
        this.mWindowCornerRadiusPx = ScreenDecorationsUtils.getWindowCornerRadius(context);
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void applyAnimationProgress(float f, SurfaceControl.Transaction transaction) {
        for (int size = this.mAnimationContextByTaskId.size() - 1; size >= 0; size--) {
            AnimationContext animationContext = (AnimationContext) this.mAnimationContextByTaskId.valueAt(size);
            if (animationContext.mStageType != -1) {
                animationContext.mCurrentCropRect.set((Rect) RECT_EVALUATOR.evaluate(f, animationContext.mStartCropRect, animationContext.mEndCropRect));
                transaction.setWindowCrop(animationContext.mLeash, animationContext.mCurrentCropRect).setCornerRadius(animationContext.mLeash, this.mWindowCornerRadiusPx);
            }
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
        updateContexts();
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final boolean isApplicableTask(TaskInfo taskInfo) {
        return taskInfo.hasParentTask() && taskInfo.isRunning && taskInfo.realActivity != null && taskInfo.getWindowingMode() == 6;
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onConfigurationChanged(Configuration configuration) {
        Trace.beginSection("SplitTaskUnfoldAnimator#onConfigurationChanged");
        this.mWindowCornerRadiusPx = ScreenDecorationsUtils.getWindowCornerRadius(this.mContext);
        Trace.endSection();
    }

    @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
    public final void onSplitBoundsChanged(Rect rect, Rect rect2, Rect rect3) {
        this.mRootStageBounds.set(rect);
        this.mMainStageBounds.set(rect2);
        this.mSideStageBounds.set(rect3);
        updateContexts();
    }

    @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
    public final void onSplitVisibilityChanged(boolean z) {
        this.mUnfoldBackgroundController.mSplitScreenVisible = z;
    }

    @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
    public final void onStagePositionChanged(int i, int i2) {
        if (i == 0) {
            this.mMainStagePosition = i2;
        } else {
            this.mSideStagePosition = i2;
        }
        updateContexts();
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void onTaskAppeared(TaskInfo taskInfo, SurfaceControl surfaceControl) {
        this.mAnimationContextByTaskId.put(taskInfo.taskId, new AnimationContext(surfaceControl));
    }

    @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
    public final void onTaskStageChanged(int i, int i2, boolean z) {
        AnimationContext animationContext = (AnimationContext) this.mAnimationContextByTaskId.get(i);
        if (animationContext != null) {
            animationContext.mStageType = i2;
            animationContext.update();
        }
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void onTaskVanished(TaskInfo taskInfo) {
        this.mAnimationContextByTaskId.remove(taskInfo.taskId);
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void prepareFinishTransaction(SurfaceControl.Transaction transaction) {
        UnfoldBackgroundController unfoldBackgroundController = this.mUnfoldBackgroundController;
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
        this.mUnfoldBackgroundController.ensureBackground(transaction);
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void resetAllSurfaces(SurfaceControl.Transaction transaction) {
        for (int size = this.mAnimationContextByTaskId.size() - 1; size >= 0; size--) {
            AnimationContext animationContext = (AnimationContext) this.mAnimationContextByTaskId.valueAt(size);
            transaction.setWindowCrop(animationContext.mLeash, null).setCornerRadius(animationContext.mLeash, 0.0f);
        }
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void resetSurface(TaskInfo taskInfo, SurfaceControl.Transaction transaction) {
        AnimationContext animationContext = (AnimationContext) this.mAnimationContextByTaskId.get(taskInfo.taskId);
        if (animationContext != null) {
            transaction.setWindowCrop(animationContext.mLeash, null).setCornerRadius(animationContext.mLeash, 0.0f);
        }
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void start() {
        final SplitScreenController.SplitScreenImpl splitScreenImpl = ((SplitScreenController) ((Optional) this.mSplitScreenController.get()).get()).mImpl;
        if (splitScreenImpl.mExecutors.containsKey(this)) {
            return;
        }
        ShellExecutor shellExecutor = SplitScreenController.this.mMainExecutor;
        final ShellExecutor shellExecutor2 = this.mExecutor;
        ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$SplitScreenImpl$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                SplitScreenController.SplitScreenImpl splitScreenImpl2 = SplitScreenController.SplitScreenImpl.this;
                SplitTaskUnfoldAnimator splitTaskUnfoldAnimator = this;
                ShellExecutor shellExecutor3 = shellExecutor2;
                if (splitScreenImpl2.mExecutors.size() == 0) {
                    SplitScreenController.this.registerSplitScreenListener(splitScreenImpl2.mListener);
                }
                splitScreenImpl2.mExecutors.put(splitTaskUnfoldAnimator, shellExecutor3);
            }
        });
        ((HandlerExecutor) shellExecutor2).execute(new SplitScreenController$SplitScreenImpl$$ExternalSyntheticLambda0(splitScreenImpl, this, 1));
    }

    @Override // com.android.wm.shell.unfold.animation.UnfoldTaskAnimator
    public final void stop() {
        SplitScreenController.SplitScreenImpl splitScreenImpl = ((SplitScreenController) ((Optional) this.mSplitScreenController.get()).get()).mImpl;
        ((HandlerExecutor) SplitScreenController.this.mMainExecutor).execute(new SplitScreenController$SplitScreenImpl$$ExternalSyntheticLambda0(splitScreenImpl, this, 0));
    }

    public final void updateContexts() {
        for (int size = this.mAnimationContextByTaskId.size() - 1; size >= 0; size--) {
            ((AnimationContext) this.mAnimationContextByTaskId.valueAt(size)).update();
        }
    }
}
