package com.google.android.systemui.columbus.legacy;

import android.content.Context;
import android.os.PowerManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PowerManagerWrapper {
    public final PowerManager powerManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WakeLockWrapper {
        public final PowerManager.WakeLock wakeLock;

        public WakeLockWrapper(PowerManager.WakeLock wakeLock) {
            this.wakeLock = wakeLock;
        }
    }

    public PowerManagerWrapper(Context context) {
        this.powerManager = (PowerManager) context.getSystemService("power");
    }
}
