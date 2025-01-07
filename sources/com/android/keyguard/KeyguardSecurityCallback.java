package com.android.keyguard;

import com.android.keyguard.KeyguardSecurityModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface KeyguardSecurityCallback {
    default void onCancelClicked() {
    }

    default void onUserInput() {
    }

    default void reset() {
    }

    default void showCurrentSecurityScreen() {
    }

    default void userActivity() {
    }

    default void onAttemptLockoutStart(long j) {
    }

    default void dismiss(int i, KeyguardSecurityModel.SecurityMode securityMode) {
    }

    default void reportUnlockAttempt(int i, int i2, boolean z) {
    }
}
