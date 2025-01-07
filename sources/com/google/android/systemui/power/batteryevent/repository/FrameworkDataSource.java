package com.google.android.systemui.power.batteryevent.repository;

import android.content.Context;
import android.os.PowerManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FrameworkDataSource {
    public final Context context;
    public boolean lastBatterySaverState;
    public boolean lastExtremeBatterySaverState;
    public final PowerManager powerManager;

    public FrameworkDataSource(Context context, PowerManager powerManager) {
        this.context = context;
        this.powerManager = powerManager;
    }
}
