package com.google.android.systemui.power;

import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventClient$logWithCaller$1 {
    public final /* synthetic */ BatteryEventClient this$0;

    public BatteryEventClient$logWithCaller$1(BatteryEventClient batteryEventClient) {
        this.this$0 = batteryEventClient;
    }

    public final void d(String str) {
        Log.d("BatteryEventClient", "[" + this.this$0.callerTag + "] " + str);
    }

    public final void w(String str) {
        Log.w("BatteryEventClient", "[" + this.this$0.callerTag + "] " + str);
    }
}
