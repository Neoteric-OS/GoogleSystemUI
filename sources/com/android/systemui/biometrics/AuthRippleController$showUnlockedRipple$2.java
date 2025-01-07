package com.android.systemui.biometrics;

import com.android.systemui.shade.NotificationShadeWindowControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthRippleController$showUnlockedRipple$2 implements Runnable {
    public final /* synthetic */ AuthRippleController this$0;

    public AuthRippleController$showUnlockedRipple$2(AuthRippleController authRippleController) {
        this.this$0 = authRippleController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AuthRippleController authRippleController = this.this$0;
        ((NotificationShadeWindowControllerImpl) authRippleController.notificationShadeWindowController).setForcePluginOpen(authRippleController, false);
    }
}
