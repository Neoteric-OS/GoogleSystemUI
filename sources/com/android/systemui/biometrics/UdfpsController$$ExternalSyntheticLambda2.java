package com.android.systemui.biometrics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class UdfpsController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ UdfpsController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ float f$4;
    public final /* synthetic */ float f$5;

    public /* synthetic */ UdfpsController$$ExternalSyntheticLambda2(UdfpsController udfpsController, long j, int i, int i2, float f, float f2) {
        this.f$0 = udfpsController;
        this.f$1 = j;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = f;
        this.f$5 = f2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UdfpsController udfpsController = this.f$0;
        long j = this.f$1;
        int i = this.f$2;
        int i2 = this.f$3;
        float f = this.f$4;
        float f2 = this.f$5;
        udfpsController.mIsAodInterruptActive = true;
        udfpsController.mCancelAodFingerUpAction = udfpsController.mFgExecutor.executeDelayed(new UdfpsController$$ExternalSyntheticLambda0(0, udfpsController), 1000L);
        udfpsController.onFingerDown(j, -1, i, i2, f, f2, 0.0f, 0L, 0L, true);
    }
}
