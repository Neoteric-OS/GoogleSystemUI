package com.android.systemui.util.sensors;

import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ProximityCheck$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ ThresholdSensorEvent f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ThresholdSensorEvent thresholdSensorEvent = this.f$0;
        ((Consumer) obj).accept(thresholdSensorEvent == null ? null : Boolean.valueOf(thresholdSensorEvent.mBelow));
    }
}
