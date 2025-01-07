package com.android.systemui.statusbar.notification.interruption;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.plugins.clocks.WeatherData;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AvalancheProvider {
    public final Set avalancheTriggerIntents;
    public final AvalancheProvider$broadcastReceiver$1 broadcastReceiver;
    public final UiEventLogger uiEventLogger;

    public AvalancheProvider(BroadcastDispatcher broadcastDispatcher, UiEventLogger uiEventLogger) {
        this.uiEventLogger = uiEventLogger;
        String[] strArr = {"android.intent.action.AIRPLANE_MODE", "android.intent.action.BOOT_COMPLETED", "android.intent.action.MANAGED_PROFILE_AVAILABLE", "android.intent.action.USER_SWITCHED"};
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(4));
        for (int i = 0; i < 4; i++) {
            linkedHashSet.add(strArr[i]);
        }
        this.avalancheTriggerIntents = linkedHashSet;
        new BroadcastReceiver() { // from class: com.android.systemui.statusbar.notification.interruption.AvalancheProvider$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                if (CollectionsKt.contains(AvalancheProvider.this.avalancheTriggerIntents, intent.getAction())) {
                    if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.AIRPLANE_MODE") && intent.getBooleanExtra(WeatherData.STATE_KEY, false)) {
                        AvalancheProvider.this.getClass();
                        Log.d("AvalancheProvider", "broadcastReceiver: ignore airplane mode on");
                        return;
                    }
                    AvalancheProvider.this.getClass();
                    FragmentManagerViewModel$$ExternalSyntheticOutline0.m("broadcastReceiver received intent.action=", intent.getAction(), "AvalancheProvider");
                    AvalancheProvider.this.uiEventLogger.log(AvalancheSuppressor$AvalancheEvent.AVALANCHE_SUPPRESSOR_RECEIVED_TRIGGERING_EVENT);
                    AvalancheProvider avalancheProvider = AvalancheProvider.this;
                    System.currentTimeMillis();
                    avalancheProvider.getClass();
                }
            }
        };
    }
}
