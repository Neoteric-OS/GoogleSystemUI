package com.android.systemui.util.wakelock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ToIntFunction;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ClientTrackingWakeLock$activeClients$1 implements ToIntFunction {
    public static final ClientTrackingWakeLock$activeClients$1 INSTANCE = new ClientTrackingWakeLock$activeClients$1();

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((AtomicInteger) obj).get();
    }
}
