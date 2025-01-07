package com.android.systemui.keyguard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.graphics.Matrix;
import android.os.RemoteException;
import android.util.Log;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationTarget;
import android.view.SyncRtSurfaceTransactionApplier;
import com.android.app.animation.Interpolators;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.keyguard.data.repository.ShowWhenLockedActivityInfo;
import com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import java.util.concurrent.Executor;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowManagerOcclusionManager$unoccludeAnimationRunner$1 extends IRemoteAnimationRunner.Stub {
    public final /* synthetic */ Executor $executor;
    public final /* synthetic */ WindowManagerOcclusionManager this$0;
    public ValueAnimator unoccludeAnimator;
    public final Matrix unoccludeMatrix = new Matrix();

    public WindowManagerOcclusionManager$unoccludeAnimationRunner$1(WindowManagerOcclusionManager windowManagerOcclusionManager, Executor executor) {
        this.this$0 = windowManagerOcclusionManager;
        this.$executor = executor;
    }

    public final void onAnimationCancelled() {
        Log.d("WindowManagerOcclusion", "unoccludeAnimationRunner#onAnimationCancelled");
        this.this$0.context.getMainExecutor().execute(new Runnable() { // from class: com.android.systemui.keyguard.WindowManagerOcclusionManager$unoccludeAnimationRunner$1$onAnimationCancelled$1
            @Override // java.lang.Runnable
            public final void run() {
                ValueAnimator valueAnimator = WindowManagerOcclusionManager$unoccludeAnimationRunner$1.this.unoccludeAnimator;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
            }
        });
        Log.d("WindowManagerOcclusion", "Unocclude animation cancelled.");
        this.this$0.interactionJankMonitor.cancel(64);
    }

    public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
        Log.d("WindowManagerOcclusion", "unoccludeAnimationRunner#onAnimationStart");
        this.this$0.unoccludeAnimationFinishedCallback = new WindowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1(iRemoteAnimationFinishedCallback, this.this$0, 1);
        KeyguardOcclusionInteractor keyguardOcclusionInteractor = this.this$0.keyguardOcclusionInteractor;
        RemoteAnimationTarget remoteAnimationTarget = remoteAnimationTargetArr.length == 0 ? null : remoteAnimationTargetArr[0];
        ActivityManager.RunningTaskInfo runningTaskInfo = remoteAnimationTarget != null ? remoteAnimationTarget.taskInfo : null;
        StateFlowImpl stateFlowImpl = keyguardOcclusionInteractor.repository.showWhenLockedActivityInfo;
        ShowWhenLockedActivityInfo showWhenLockedActivityInfo = new ShowWhenLockedActivityInfo(runningTaskInfo, false);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, showWhenLockedActivityInfo);
        WindowManagerOcclusionManager windowManagerOcclusionManager = this.this$0;
        windowManagerOcclusionManager.interactionJankMonitor.begin(InteractionJankMonitor.Configuration.Builder.withView(64, ((StatusBarKeyguardViewManager) windowManagerOcclusionManager.keyguardViewController.get()).getViewRootImpl().getView()).setTag("UNOCCLUDE"));
        if (remoteAnimationTargetArr.length == 0) {
            Log.d("WindowManagerOcclusion", "No apps provided to unocclude runner; skipping animation and unoccluding.");
            WindowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1 windowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1 = this.this$0.unoccludeAnimationFinishedCallback;
            if (windowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1 != null) {
                windowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1.onAnimationFinished();
                return;
            }
            return;
        }
        final RemoteAnimationTarget remoteAnimationTarget2 = remoteAnimationTargetArr[0];
        final SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier = new SyncRtSurfaceTransactionApplier(((StatusBarKeyguardViewManager) this.this$0.keyguardViewController.get()).getViewRootImpl().getView());
        Executor executor = this.$executor;
        final WindowManagerOcclusionManager windowManagerOcclusionManager2 = this.this$0;
        executor.execute(new Runnable() { // from class: com.android.systemui.keyguard.WindowManagerOcclusionManager$unoccludeAnimationRunner$1$onAnimationStart$2
            @Override // java.lang.Runnable
            public final void run() {
                ValueAnimator valueAnimator = this.unoccludeAnimator;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                WindowManagerOcclusionManager$unoccludeAnimationRunner$1 windowManagerOcclusionManager$unoccludeAnimationRunner$1 = this;
                ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
                final RemoteAnimationTarget remoteAnimationTarget3 = remoteAnimationTarget2;
                final WindowManagerOcclusionManager$unoccludeAnimationRunner$1 windowManagerOcclusionManager$unoccludeAnimationRunner$12 = this;
                final WindowManagerOcclusionManager windowManagerOcclusionManager3 = windowManagerOcclusionManager2;
                final SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier2 = syncRtSurfaceTransactionApplier;
                ofFloat.setDuration(250);
                ofFloat.setInterpolator(Interpolators.TOUCH_RESPONSE);
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.keyguard.WindowManagerOcclusionManager$unoccludeAnimationRunner$1$onAnimationStart$2$1$1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        float floatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                        windowManagerOcclusionManager$unoccludeAnimationRunner$12.unoccludeMatrix.setTranslate(0.0f, (1.0f - floatValue) * remoteAnimationTarget3.screenSpaceBounds.height() * 0.1f);
                        syncRtSurfaceTransactionApplier2.scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(remoteAnimationTarget3.leash).withAlpha(floatValue).withMatrix(windowManagerOcclusionManager$unoccludeAnimationRunner$12.unoccludeMatrix).withCornerRadius(windowManagerOcclusionManager3.windowCornerRadius).build()});
                    }
                });
                ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.keyguard.WindowManagerOcclusionManager$unoccludeAnimationRunner$1$onAnimationStart$2$1$2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        try {
                            WindowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1 windowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$12 = WindowManagerOcclusionManager.this.unoccludeAnimationFinishedCallback;
                            if (windowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$12 != null) {
                                windowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$12.onAnimationFinished();
                            }
                            windowManagerOcclusionManager$unoccludeAnimationRunner$12.unoccludeAnimator = null;
                            WindowManagerOcclusionManager.this.interactionJankMonitor.end(64);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
                ofFloat.start();
                windowManagerOcclusionManager$unoccludeAnimationRunner$1.unoccludeAnimator = ofFloat;
            }
        });
    }
}
