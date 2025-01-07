package com.android.systemui.biometrics;

import com.android.systemui.biometrics.AuthController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthController$4$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ AuthController$4$$ExternalSyntheticLambda0(AuthController.AnonymousClass4 anonymousClass4, int i, int i2, boolean z) {
        this.f$0 = anonymousClass4;
        this.f$1 = i;
        this.f$2 = i2;
        this.f$3 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                AuthController.AnonymousClass4 anonymousClass4 = (AuthController.AnonymousClass4) this.f$0;
                AuthController.m783$$Nest$mhandleEnrollmentsChanged(anonymousClass4.this$0, 2, this.f$1, this.f$2, this.f$3);
                break;
            default:
                AuthController.AnonymousClass4 anonymousClass42 = (AuthController.AnonymousClass4) this.f$0;
                AuthController.m783$$Nest$mhandleEnrollmentsChanged(anonymousClass42.this$0, 8, this.f$1, this.f$2, this.f$3);
                break;
        }
    }

    public /* synthetic */ AuthController$4$$ExternalSyntheticLambda0(AuthController.AnonymousClass4 anonymousClass4, int i, int i2, boolean z, byte b) {
        this.f$0 = anonymousClass4;
        this.f$1 = i;
        this.f$2 = i2;
        this.f$3 = z;
    }
}
