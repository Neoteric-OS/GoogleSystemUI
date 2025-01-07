package com.android.systemui.shade;

import com.android.systemui.Dumpable;
import com.android.systemui.keyguard.shared.model.TransitionState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.BrightnessMirrorController$$ExternalSyntheticLambda0;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationPanelViewController$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Dumpable f$0;

    public /* synthetic */ NotificationPanelViewController$$ExternalSyntheticLambda2(Dumpable dumpable, int i) {
        this.$r8$classId = i;
        this.f$0 = dumpable;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Dumpable dumpable = this.f$0;
        switch (i) {
            case 0:
                NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) dumpable;
                notificationPanelViewController.getClass();
                notificationPanelViewController.mIsOcclusionTransitionRunning = ((TransitionStep) obj).transitionState == TransitionState.RUNNING;
                break;
            case 1:
                BrightnessMirrorController$$ExternalSyntheticLambda0 brightnessMirrorController$$ExternalSyntheticLambda0 = ((NotificationPanelViewController) dumpable).mPanelAlphaEndAction;
                if (brightnessMirrorController$$ExternalSyntheticLambda0 != null) {
                    brightnessMirrorController$$ExternalSyntheticLambda0.run();
                    break;
                }
                break;
            case 2:
                NotificationPanelViewController notificationPanelViewController2 = (NotificationPanelViewController) dumpable;
                notificationPanelViewController2.getClass();
                notificationPanelViewController2.mIsOcclusionTransitionRunning = ((TransitionStep) obj).transitionState == TransitionState.RUNNING;
                break;
            case 3:
                NotificationPanelViewController notificationPanelViewController3 = (NotificationPanelViewController) dumpable;
                notificationPanelViewController3.getClass();
                if (((Boolean) obj).booleanValue()) {
                    notificationPanelViewController3.updateSystemUiStateFlags();
                    break;
                }
                break;
            case 4:
                NotificationPanelViewController notificationPanelViewController4 = (NotificationPanelViewController) dumpable;
                notificationPanelViewController4.getClass();
                notificationPanelViewController4.mIsOcclusionTransitionRunning = ((TransitionStep) obj).transitionState == TransitionState.RUNNING;
                break;
            case 5:
                ((NotificationPanelViewController) dumpable).getClass();
                break;
            case 6:
                NotificationPanelViewController notificationPanelViewController5 = (NotificationPanelViewController) dumpable;
                notificationPanelViewController5.getClass();
                notificationPanelViewController5.mIsOcclusionTransitionRunning = ((TransitionStep) obj).transitionState == TransitionState.RUNNING;
                break;
            case 7:
                NotificationPanelViewController notificationPanelViewController6 = (NotificationPanelViewController) dumpable;
                notificationPanelViewController6.getClass();
                notificationPanelViewController6.mIsOcclusionTransitionRunning = ((TransitionStep) obj).transitionState == TransitionState.RUNNING;
                break;
            case 8:
                NotificationPanelViewController notificationPanelViewController7 = (NotificationPanelViewController) dumpable;
                notificationPanelViewController7.getClass();
                notificationPanelViewController7.mIsOcclusionTransitionRunning = ((TransitionStep) obj).transitionState == TransitionState.RUNNING;
                break;
            case 9:
                NotificationPanelViewController notificationPanelViewController8 = (NotificationPanelViewController) dumpable;
                notificationPanelViewController8.getClass();
                notificationPanelViewController8.mIsOcclusionTransitionRunning = ((TransitionStep) obj).transitionState == TransitionState.RUNNING;
                break;
            case 10:
                NotificationPanelViewController notificationPanelViewController9 = (NotificationPanelViewController) dumpable;
                notificationPanelViewController9.getClass();
                notificationPanelViewController9.mIsOcclusionTransitionRunning = ((TransitionStep) obj).transitionState == TransitionState.RUNNING;
                break;
            default:
                ((NotificationStackScrollLayoutController) dumpable).mView.mAmbientState.mTrackedHeadsUpRow = (ExpandableNotificationRow) obj;
                break;
        }
    }

    public /* synthetic */ NotificationPanelViewController$$ExternalSyntheticLambda2(NotificationPanelViewController notificationPanelViewController, NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        this.$r8$classId = 5;
        this.f$0 = notificationPanelViewController;
    }
}
