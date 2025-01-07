package com.google.android.systemui.power.batteryevent.aidl;

import android.os.IInterface;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface IBatteryEventsListener extends IInterface {
    void onBatteryEventChanged(int i, int i2, List list);
}
