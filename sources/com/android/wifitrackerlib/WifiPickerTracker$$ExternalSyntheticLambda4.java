package com.android.wifitrackerlib;

import android.net.wifi.sharedconnectivity.app.HotspotNetwork;
import android.net.wifi.sharedconnectivity.app.KnownNetwork;
import android.util.Log;
import java.util.function.BinaryOperator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiPickerTracker$$ExternalSyntheticLambda4 implements BinaryOperator {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                KnownNetwork knownNetwork = (KnownNetwork) obj;
                Log.e("WifiPickerTracker", "Encountered duplicate key data in updateKnownNetworkEntryScans");
                return knownNetwork;
            default:
                HotspotNetwork hotspotNetwork = (HotspotNetwork) obj;
                Log.e("WifiPickerTracker", "Encountered duplicate key data in updateHotspotNetworkEntries");
                return hotspotNetwork;
        }
    }
}
