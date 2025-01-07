package com.android.systemui.keyguard;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationTarget;
import android.view.ViewGroup;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.keyguard.data.repository.ShowWhenLockedActivityInfo;
import com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.concurrent.Executor;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowManagerOcclusionManager {
    public final ActivityTransitionAnimator activityTransitionAnimator;
    public final Context context;
    public final InteractionJankMonitor interactionJankMonitor;
    public final KeyguardOcclusionInteractor keyguardOcclusionInteractor;
    public final Lazy keyguardViewController;
    public final WindowManagerOcclusionManager$occludeAnimationController$1 occludeAnimationController;
    public WindowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1 occludeAnimationFinishedCallback;
    public final WindowManagerOcclusionManager$occludeAnimationRunner$1 occludeAnimationRunner;
    public final int powerButtonY;
    public WindowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1 unoccludeAnimationFinishedCallback;
    public final WindowManagerOcclusionManager$unoccludeAnimationRunner$1 unoccludeAnimationRunner;
    public final float windowCornerRadius;

    /* JADX WARN: Type inference failed for: r1v6, types: [com.android.systemui.keyguard.WindowManagerOcclusionManager$occludeAnimationController$1] */
    public WindowManagerOcclusionManager(KeyguardOcclusionInteractor keyguardOcclusionInteractor, ActivityTransitionAnimator activityTransitionAnimator, Lazy lazy, Context context, InteractionJankMonitor interactionJankMonitor, Executor executor) {
        this.keyguardOcclusionInteractor = keyguardOcclusionInteractor;
        this.activityTransitionAnimator = activityTransitionAnimator;
        this.keyguardViewController = lazy;
        this.context = context;
        this.interactionJankMonitor = interactionJankMonitor;
        this.powerButtonY = context.getResources().getDimensionPixelSize(R.dimen.physical_power_button_center_screen_location_y);
        this.windowCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(context);
        new IRemoteAnimationRunner.Stub() { // from class: com.android.systemui.keyguard.WindowManagerOcclusionManager$occludeAnimationRunner$1
            public final void onAnimationCancelled() {
                Log.d("WindowManagerOcclusion", "occludeAnimationRunner#onAnimationCancelled");
            }

            public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
                Log.d("WindowManagerOcclusion", "occludeAnimationRunner#onAnimationStart");
                WindowManagerOcclusionManager.this.occludeAnimationFinishedCallback = new WindowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1(iRemoteAnimationFinishedCallback, WindowManagerOcclusionManager.this, 0);
                KeyguardOcclusionInteractor keyguardOcclusionInteractor2 = WindowManagerOcclusionManager.this.keyguardOcclusionInteractor;
                RemoteAnimationTarget remoteAnimationTarget = remoteAnimationTargetArr.length == 0 ? null : remoteAnimationTargetArr[0];
                ActivityManager.RunningTaskInfo runningTaskInfo = remoteAnimationTarget != null ? remoteAnimationTarget.taskInfo : null;
                StateFlowImpl stateFlowImpl = keyguardOcclusionInteractor2.repository.showWhenLockedActivityInfo;
                ShowWhenLockedActivityInfo showWhenLockedActivityInfo = new ShowWhenLockedActivityInfo(runningTaskInfo, true);
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, showWhenLockedActivityInfo);
                WindowManagerOcclusionManager windowManagerOcclusionManager = WindowManagerOcclusionManager.this;
                windowManagerOcclusionManager.activityTransitionAnimator.createRunner(windowManagerOcclusionManager.occludeAnimationController).onAnimationStart(i, remoteAnimationTargetArr, remoteAnimationTargetArr2, remoteAnimationTargetArr3, WindowManagerOcclusionManager.this.occludeAnimationFinishedCallback);
            }
        };
        new WindowManagerOcclusionManager$unoccludeAnimationRunner$1(this, executor);
        this.occludeAnimationController = new ActivityTransitionAnimator.Controller() { // from class: com.android.systemui.keyguard.WindowManagerOcclusionManager$occludeAnimationController$1
            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final TransitionAnimator.State createAnimatorState() {
                int width = getTransitionContainer().getWidth();
                int height = getTransitionContainer().getHeight();
                WindowManagerOcclusionManager windowManagerOcclusionManager = WindowManagerOcclusionManager.this;
                if (((Boolean) ((StateFlowImpl) windowManagerOcclusionManager.keyguardOcclusionInteractor.showWhenLockedActivityLaunchedFromPowerGesture.$$delegate_0).getValue()).booleanValue()) {
                    float f = width;
                    float f2 = windowManagerOcclusionManager.powerButtonY;
                    float f3 = (height / 3.0f) / 2.0f;
                    float f4 = windowManagerOcclusionManager.windowCornerRadius;
                    return new TransitionAnimator.State((int) (f2 - f3), (int) (f2 + f3), (int) (f - (f / 3.0f)), width, f4, f4);
                }
                float f5 = height;
                float f6 = f5 / 2.0f;
                float f7 = width;
                float f8 = f7 / 2.0f;
                float f9 = f5 - f6;
                int i = ((int) f9) / 2;
                float f10 = 2;
                float f11 = f7 - f8;
                float f12 = windowManagerOcclusionManager.windowCornerRadius;
                return new TransitionAnimator.State(i, (int) ((f9 / f10) + f6), ((int) f11) / 2, (int) ((f11 / f10) + f8), f12, f12);
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final ViewGroup getTransitionContainer() {
                return (ViewGroup) ((StatusBarKeyguardViewManager) WindowManagerOcclusionManager.this.keyguardViewController.get()).getViewRootImpl().getView();
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final boolean isLaunching() {
                return true;
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final void setTransitionContainer(ViewGroup viewGroup) {
            }
        };
    }

    public static /* synthetic */ void getOccludeAnimationController$annotations() {
    }
}
