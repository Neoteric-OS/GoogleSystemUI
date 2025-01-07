package com.android.systemui.statusbar.phone;

import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardDismissUtil {
    public final ActivityStarter mActivityStarter;
    public final KeyguardStateController mKeyguardStateController;
    public final SysuiStatusBarStateController mStatusBarStateController;

    public KeyguardDismissUtil(KeyguardStateController keyguardStateController, SysuiStatusBarStateController sysuiStatusBarStateController, ActivityStarter activityStarter) {
        this.mKeyguardStateController = keyguardStateController;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mActivityStarter = activityStarter;
    }

    public final void executeWhenUnlocked(ActivityStarter.OnDismissAction onDismissAction, boolean z, boolean z2) {
        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing && z) {
            ((StatusBarStateControllerImpl) this.mStatusBarStateController).mLeaveOpenOnKeyguardHide = true;
        }
        this.mActivityStarter.dismissKeyguardThenExecute(onDismissAction, null, z2);
    }
}
