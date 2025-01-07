package com.android.systemui.statusbar.events;

import androidx.core.animation.Animator;
import androidx.core.animation.AnimatorListenerAdapter;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemStatusAnimationSchedulerImpl$runChipAppearAnimation$1 extends AnimatorListenerAdapter {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SystemStatusAnimationSchedulerImpl this$0;

    public /* synthetic */ SystemStatusAnimationSchedulerImpl$runChipAppearAnimation$1(SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl, int i) {
        this.$r8$classId = i;
        this.this$0 = systemStatusAnimationSchedulerImpl;
    }

    @Override // androidx.core.animation.AnimatorListenerAdapter, androidx.core.animation.Animator.AnimatorListener
    public final void onAnimationEnd$1(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                StateFlowImpl stateFlowImpl = this.this$0.animationState;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, 3);
                break;
            default:
                SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl = this.this$0;
                StateFlowImpl stateFlowImpl2 = systemStatusAnimationSchedulerImpl.animationState;
                Integer valueOf = Integer.valueOf(systemStatusAnimationSchedulerImpl.hasPersistentDot ? 5 : systemStatusAnimationSchedulerImpl.scheduledEvent.getValue() != null ? 1 : 0);
                stateFlowImpl2.getClass();
                stateFlowImpl2.updateState(null, valueOf);
                StatusBarWindowControllerImpl statusBarWindowControllerImpl = systemStatusAnimationSchedulerImpl.statusBarWindowController;
                StatusBarWindowControllerImpl.State state = statusBarWindowControllerImpl.mCurrentState;
                state.mForceStatusBarVisible = false;
                statusBarWindowControllerImpl.apply(state);
                break;
        }
    }
}
