package com.google.android.systemui.fingerprint;

import com.android.systemui.biometrics.FingerprintReEnrollNotification;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FingerprintReEnrollNotificationGoogle implements FingerprintReEnrollNotification {
    @Override // com.android.systemui.biometrics.FingerprintReEnrollNotification
    public final boolean isFingerprintReEnrollForced(int i) {
        return i == 1050;
    }

    @Override // com.android.systemui.biometrics.FingerprintReEnrollNotification
    public final boolean isFingerprintReEnrollRequested(int i) {
        return i == 1040 || i == 1050;
    }
}
