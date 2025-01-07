package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class KeyguardStatusBarViewController$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardStatusBarViewController f$0;

    public /* synthetic */ KeyguardStatusBarViewController$$ExternalSyntheticLambda8(KeyguardStatusBarViewController keyguardStatusBarViewController, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardStatusBarViewController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        final KeyguardStatusBarViewController keyguardStatusBarViewController = this.f$0;
        switch (i) {
            case 0:
                TintedIconManager tintedIconManager = keyguardStatusBarViewController.mTintedIconManager;
                if (tintedIconManager != null) {
                    tintedIconManager.setBlockList(keyguardStatusBarViewController.getBlockedIcons());
                    break;
                }
                break;
            default:
                final boolean isUserSwitcherEnabled = keyguardStatusBarViewController.mUserManager.isUserSwitcherEnabled(keyguardStatusBarViewController.mView.getResources().getBoolean(R.bool.qs_show_user_switcher_for_single_user));
                keyguardStatusBarViewController.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ((KeyguardStatusBarView) KeyguardStatusBarViewController.this.mView).mIsUserSwitcherEnabled = isUserSwitcherEnabled;
                    }
                });
                break;
        }
    }
}
