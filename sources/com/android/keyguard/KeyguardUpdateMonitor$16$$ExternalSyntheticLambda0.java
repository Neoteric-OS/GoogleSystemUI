package com.android.keyguard;

import com.android.keyguard.KeyguardUpdateMonitor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardUpdateMonitor$16$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardUpdateMonitor.AnonymousClass16 f$0;

    public /* synthetic */ KeyguardUpdateMonitor$16$$ExternalSyntheticLambda0(KeyguardUpdateMonitor.AnonymousClass16 anonymousClass16, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass16;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        KeyguardUpdateMonitor.AnonymousClass16 anonymousClass16 = this.f$0;
        anonymousClass16.getClass();
        switch (i) {
            case 0:
                int i2 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                anonymousClass16.this$0.updateFingerprintListeningState(2);
                break;
            default:
                int i3 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                anonymousClass16.this$0.updateFingerprintListeningState(2);
                break;
        }
    }
}
