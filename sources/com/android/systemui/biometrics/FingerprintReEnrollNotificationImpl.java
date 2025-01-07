package com.android.systemui.biometrics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FingerprintReEnrollNotificationImpl implements FingerprintReEnrollNotification {
    @Override // com.android.systemui.biometrics.FingerprintReEnrollNotification
    public final boolean isFingerprintReEnrollForced(int i) {
        return i == 13;
    }

    @Override // com.android.systemui.biometrics.FingerprintReEnrollNotification
    public final boolean isFingerprintReEnrollRequested(int i) {
        return i == 12 || i == 13;
    }
}
