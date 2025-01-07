package com.android.keyguard;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class LegacyLockIconViewController$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LegacyLockIconViewController f$0;

    public /* synthetic */ LegacyLockIconViewController$$ExternalSyntheticLambda8(LegacyLockIconViewController legacyLockIconViewController, int i) {
        this.$r8$classId = i;
        this.f$0 = legacyLockIconViewController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        LegacyLockIconViewController legacyLockIconViewController = this.f$0;
        switch (i) {
            case 0:
                legacyLockIconViewController.onLongPress();
                break;
            default:
                legacyLockIconViewController.updateIsUdfpsEnrolled();
                legacyLockIconViewController.updateConfiguration$2();
                break;
        }
    }
}
