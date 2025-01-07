package com.android.keyguard;

import android.telephony.SubscriptionInfo;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardUpdateMonitor$$ExternalSyntheticLambda4 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
        return ((SubscriptionInfo) obj).getProfileClass() != 1;
    }
}
