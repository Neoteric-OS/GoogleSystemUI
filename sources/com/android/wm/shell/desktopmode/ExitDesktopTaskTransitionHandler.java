package com.android.wm.shell.desktopmode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExitDesktopTaskTransitionHandler implements Transitions.TransitionHandler {
    public final Context mContext;
    public final Handler mHandler;
    public final InteractionJankMonitor mInteractionJankMonitor;
    public DesktopTasksController$mOnAnimationFinishedCallback$1 mOnAnimationFinishedCallback;
    public final List mPendingTransitionTokens;
    public Point mPosition;
    public final EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 mTransactionSupplier;
    public final Transitions mTransitions;

    public ExitDesktopTaskTransitionHandler(Transitions transitions, Context context, InteractionJankMonitor interactionJankMonitor, Handler handler) {
        EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 enterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 = new EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0();
        this.mPendingTransitionTokens = new ArrayList();
        this.mTransitions = transitions;
        this.mTransactionSupplier = enterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0;
        this.mContext = context;
        this.mInteractionJankMonitor = interactionJankMonitor;
        this.mHandler = handler;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        return null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        ActivityManager.RunningTaskInfo taskInfo;
        boolean z = false;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if ((change.getFlags() & 2) == 0 && (taskInfo = change.getTaskInfo()) != null && taskInfo.taskId != -1 && change.getMode() == 6) {
                z |= startChangeTransition(iBinder, transitionInfo.getType(), change, transaction, transaction2, transitionFinishCallback);
            }
        }
        this.mPendingTransitionTokens.remove(iBinder);
        return z;
    }

    public boolean startChangeTransition(IBinder iBinder, int i, TransitionInfo.Change change, SurfaceControl.Transaction transaction, final SurfaceControl.Transaction transaction2, final Transitions.TransitionFinishCallback transitionFinishCallback) {
        if (!this.mPendingTransitionTokens.contains(iBinder)) {
            return false;
        }
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        if (!CollectionsKt__CollectionsKt.listOf(1105, 1106, 1107, 1108).contains(Integer.valueOf(i)) || taskInfo.getWindowingMode() != 1) {
            return false;
        }
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        final SurfaceControl leash = change.getLeash();
        Rect endAbsBounds = change.getEndAbsBounds();
        this.mInteractionJankMonitor.begin(leash, this.mContext, this.mHandler, 108);
        transaction.hide(leash).setWindowCrop(leash, endAbsBounds.width(), endAbsBounds.height()).apply();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.setDuration(336L);
        Rect startAbsBounds = change.getStartAbsBounds();
        final float width = startAbsBounds.width() / i2;
        final float height = startAbsBounds.height() / i3;
        this.mTransactionSupplier.getClass();
        final SurfaceControl.Transaction transaction3 = new SurfaceControl.Transaction();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.desktopmode.ExitDesktopTaskTransitionHandler$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ExitDesktopTaskTransitionHandler exitDesktopTaskTransitionHandler = ExitDesktopTaskTransitionHandler.this;
                float f = width;
                float f2 = height;
                SurfaceControl.Transaction transaction4 = transaction3;
                SurfaceControl surfaceControl = leash;
                exitDesktopTaskTransitionHandler.getClass();
                float animatedFraction = valueAnimator2.getAnimatedFraction();
                float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(1.0f, f, animatedFraction, f);
                float m2 = AndroidFlingSpline$$ExternalSyntheticOutline0.m(1.0f, f2, animatedFraction, f2);
                Point point = exitDesktopTaskTransitionHandler.mPosition;
                float f3 = 1.0f - animatedFraction;
                transaction4.setPosition(surfaceControl, point.x * f3, point.y * f3).setScale(surfaceControl, m, m2).show(surfaceControl).apply();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.desktopmode.ExitDesktopTaskTransitionHandler.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                DesktopTasksController$mOnAnimationFinishedCallback$1 desktopTasksController$mOnAnimationFinishedCallback$1 = ExitDesktopTaskTransitionHandler.this.mOnAnimationFinishedCallback;
                if (desktopTasksController$mOnAnimationFinishedCallback$1 != null) {
                    desktopTasksController$mOnAnimationFinishedCallback$1.accept(transaction2);
                }
                ExitDesktopTaskTransitionHandler.this.mInteractionJankMonitor.end(108);
                ShellExecutor shellExecutor = ExitDesktopTaskTransitionHandler.this.mTransitions.mMainExecutor;
                final Transitions.TransitionFinishCallback transitionFinishCallback2 = transitionFinishCallback;
                ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.desktopmode.ExitDesktopTaskTransitionHandler$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Transitions.TransitionFinishCallback.this.onTransitionFinished(null);
                    }
                });
            }
        });
        valueAnimator.start();
        return true;
    }
}
