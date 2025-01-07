package com.android.systemui.biometrics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AuthController f$0;

    public /* synthetic */ AuthController$$ExternalSyntheticLambda2(AuthController authController, int i) {
        this.$r8$classId = i;
        this.f$0 = authController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        AuthController authController = this.f$0;
        switch (i) {
            case 0:
                authController.updateUdfpsLocation();
                break;
            default:
                authController.cancelIfOwnerIsNotInForeground();
                break;
        }
    }
}
