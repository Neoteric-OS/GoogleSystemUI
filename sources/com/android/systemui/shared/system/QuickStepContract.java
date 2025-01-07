package com.android.systemui.shared.system;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class QuickStepContract {
    public static boolean isBackGestureDisabled(long j, boolean z) {
        if ((8 & j) != 0 || (32768 & j) != 0 || (33554432 & j) != 0) {
            return false;
        }
        if ((34359738368L & j) != 0) {
            return (j & 2048) == 0;
        }
        if ((131072 & j) != 0) {
            j &= -3;
        }
        return (j & ((!z ? 66L : 64L) | 4)) != 0;
    }

    public static boolean isGesturalMode(int i) {
        return i == 2;
    }
}
