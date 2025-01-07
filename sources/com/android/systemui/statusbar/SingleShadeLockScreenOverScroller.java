package com.android.systemui.statusbar;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.MathUtils;
import com.android.app.animation.Interpolators;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SingleShadeLockScreenOverScroller implements LockScreenShadeOverScroller {
    public final Context context;
    public float expansionDragDownAmount;
    public int maxOverScrollAmount;
    public final NotificationStackScrollLayoutController nsslController;
    public final SysuiStatusBarStateController statusBarStateController;
    public int totalDistanceForFullShadeTransition;

    public SingleShadeLockScreenOverScroller(ConfigurationController configurationController, Context context, SysuiStatusBarStateController sysuiStatusBarStateController, NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        this.context = context;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.nsslController = notificationStackScrollLayoutController;
        Resources resources = context.getResources();
        this.totalDistanceForFullShadeTransition = resources.getDimensionPixelSize(R.dimen.lockscreen_shade_qs_transition_distance);
        this.maxOverScrollAmount = resources.getDimensionPixelSize(R.dimen.lockscreen_shade_max_over_scroll_amount);
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.SingleShadeLockScreenOverScroller.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                SingleShadeLockScreenOverScroller singleShadeLockScreenOverScroller = SingleShadeLockScreenOverScroller.this;
                Resources resources2 = singleShadeLockScreenOverScroller.context.getResources();
                singleShadeLockScreenOverScroller.totalDistanceForFullShadeTransition = resources2.getDimensionPixelSize(R.dimen.lockscreen_shade_qs_transition_distance);
                singleShadeLockScreenOverScroller.maxOverScrollAmount = resources2.getDimensionPixelSize(R.dimen.lockscreen_shade_max_over_scroll_amount);
            }
        });
    }

    @Override // com.android.systemui.statusbar.LockScreenShadeOverScroller
    public final void setExpansionDragDownAmount(float f) {
        float f2;
        if (f == this.expansionDragDownAmount) {
            return;
        }
        this.expansionDragDownAmount = f;
        int i = ((StatusBarStateControllerImpl) this.statusBarStateController).mState;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.nsslController;
        if (i == 1) {
            float height = notificationStackScrollLayoutController.mView.getHeight();
            f2 = Interpolators.getOvershootInterpolation(MathUtils.saturate(this.expansionDragDownAmount / height), this.totalDistanceForFullShadeTransition / height) * this.maxOverScrollAmount;
        } else {
            f2 = 0.0f;
        }
        notificationStackScrollLayoutController.setOverScrollAmount((int) f2);
    }
}
