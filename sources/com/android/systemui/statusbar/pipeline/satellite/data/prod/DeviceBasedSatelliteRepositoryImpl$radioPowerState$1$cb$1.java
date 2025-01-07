package com.android.systemui.statusbar.pipeline.satellite.data.prod;

import android.telephony.TelephonyCallback;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteRepositoryImpl$radioPowerState$1$cb$1 extends TelephonyCallback implements TelephonyCallback.RadioPowerStateListener {
    public final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public DeviceBasedSatelliteRepositoryImpl$radioPowerState$1$cb$1(ProducerScope producerScope) {
        this.$$this$conflatedCallbackFlow = producerScope;
    }

    public final void onRadioPowerStateChanged(int i) {
        ((ProducerCoroutine) this.$$this$conflatedCallbackFlow).mo1790trySendJP2dKIU(Integer.valueOf(i));
    }
}
