package com.android.systemui.shade;

import android.widget.FrameLayout;
import com.android.keyguard.KeyguardStatusView;
import com.android.keyguard.KeyguardStatusViewController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.KeyguardStatusBarViewController;
import com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcherController;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcherView;
import java.util.function.Consumer;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationPanelViewController$$ExternalSyntheticLambda41 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationPanelViewController f$0;
    public final /* synthetic */ NotificationStackScrollLayoutController f$1;

    public /* synthetic */ NotificationPanelViewController$$ExternalSyntheticLambda41(NotificationPanelViewController notificationPanelViewController, NotificationStackScrollLayoutController notificationStackScrollLayoutController, int i) {
        this.$r8$classId = i;
        this.f$0 = notificationPanelViewController;
        this.f$1 = notificationStackScrollLayoutController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                NotificationPanelViewController notificationPanelViewController = this.f$0;
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.f$1;
                Float f = (Float) obj;
                KeyguardStatusBarViewController keyguardStatusBarViewController = notificationPanelViewController.mKeyguardStatusBarViewController;
                keyguardStatusBarViewController.mExplicitAlpha = f.floatValue();
                keyguardStatusBarViewController.updateViewState();
                new NotificationPanelViewController$$ExternalSyntheticLambda41(notificationPanelViewController, notificationStackScrollLayoutController, 1).accept(f);
                break;
            default:
                NotificationPanelViewController notificationPanelViewController2 = this.f$0;
                NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = this.f$1;
                Float f2 = (Float) obj;
                KeyguardStatusViewController keyguardStatusViewController = notificationPanelViewController2.mKeyguardStatusViewController;
                float floatValue = f2.floatValue();
                keyguardStatusViewController.mKeyguardVisibilityHelper.getClass();
                ((KeyguardStatusView) keyguardStatusViewController.mView).setAlpha(floatValue);
                notificationStackScrollLayoutController2.mMaxAlphaForKeyguard = f2.floatValue();
                notificationStackScrollLayoutController2.mMaxAlphaForKeyguardSource = "NPVC.setTransitionAlpha()";
                notificationStackScrollLayoutController2.updateAlpha$1();
                StateFlowImpl stateFlowImpl = notificationPanelViewController2.mKeyguardInteractor.repository._keyguardAlpha;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, f2);
                notificationPanelViewController2.mLockIconViewController.setAlpha(f2.floatValue());
                KeyguardQsUserSwitchController keyguardQsUserSwitchController = notificationPanelViewController2.mKeyguardQsUserSwitchController;
                if (keyguardQsUserSwitchController != null) {
                    float floatValue2 = f2.floatValue();
                    keyguardQsUserSwitchController.mKeyguardVisibilityHelper.getClass();
                    ((FrameLayout) keyguardQsUserSwitchController.mView).setAlpha(floatValue2);
                }
                KeyguardUserSwitcherController keyguardUserSwitcherController = notificationPanelViewController2.mKeyguardUserSwitcherController;
                if (keyguardUserSwitcherController != null) {
                    float floatValue3 = f2.floatValue();
                    keyguardUserSwitcherController.mKeyguardVisibilityHelper.getClass();
                    ((KeyguardUserSwitcherView) keyguardUserSwitcherController.mView).setAlpha(floatValue3);
                    break;
                }
                break;
        }
    }
}
