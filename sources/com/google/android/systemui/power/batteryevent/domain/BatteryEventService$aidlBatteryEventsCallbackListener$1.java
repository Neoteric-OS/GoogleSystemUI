package com.google.android.systemui.power.batteryevent.domain;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventService$aidlBatteryEventsCallbackListener$1 extends RemoteCallbackList {
    public final /* synthetic */ BatteryEventService this$0;

    public BatteryEventService$aidlBatteryEventsCallbackListener$1(BatteryEventService batteryEventService) {
        this.this$0 = batteryEventService;
    }

    @Override // android.os.RemoteCallbackList
    public final void onCallbackDied(IInterface iInterface, Object obj) {
        IBatteryEventsListener iBatteryEventsListener = (IBatteryEventsListener) iInterface;
        super.onCallbackDied(iBatteryEventsListener, obj);
        this.this$0.batteryEventsCallbackCache.remove(iBatteryEventsListener);
    }

    @Override // android.os.RemoteCallbackList
    public final boolean unregister(IBatteryEventsListener iBatteryEventsListener) {
        boolean unregister = super.unregister((BatteryEventService$aidlBatteryEventsCallbackListener$1) iBatteryEventsListener);
        this.this$0.batteryEventsCallbackCache.remove(iBatteryEventsListener);
        return unregister;
    }
}
