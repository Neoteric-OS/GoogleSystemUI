package com.android.systemui.util.wakelock;

import java.util.function.IntBinaryOperator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ClientTrackingWakeLock$activeClients$2 implements IntBinaryOperator {
    public static final ClientTrackingWakeLock$activeClients$2 INSTANCE = new ClientTrackingWakeLock$activeClients$2();

    @Override // java.util.function.IntBinaryOperator
    public final int applyAsInt(int i, int i2) {
        return Integer.sum(i, i2);
    }
}
