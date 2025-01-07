package com.android.systemui.util.wakelock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ClientTrackingWakeLock$acquire$count$1 implements Function {
    public static final ClientTrackingWakeLock$acquire$count$1 INSTANCE = new ClientTrackingWakeLock$acquire$count$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return new AtomicInteger(0);
    }
}
