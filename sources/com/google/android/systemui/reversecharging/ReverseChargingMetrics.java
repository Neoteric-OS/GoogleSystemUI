package com.google.android.systemui.reversecharging;

import android.frameworks.stats.IStats;
import android.frameworks.stats.VendorAtom;
import android.os.ServiceManager;
import android.util.Log;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ReverseChargingMetrics {
    public static final boolean DEBUG = Log.isLoggable("ReverseChargingMetrics", 3);

    public static void reportVendorAtom(VendorAtom vendorAtom) {
        Optional ofNullable;
        try {
            String m = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), IStats.DESCRIPTOR, "/default");
            if (ServiceManager.isDeclared(m)) {
                ofNullable = Optional.ofNullable(IStats.Stub.asInterface(ServiceManager.waitForDeclaredService(m)));
            } else {
                Log.e("ReverseChargingMetrics", "IStats is not registered");
                ofNullable = Optional.empty();
            }
            if (ofNullable.isPresent()) {
                ((IStats.Stub.Proxy) ((IStats) ofNullable.get())).reportVendorAtom(vendorAtom);
                if (DEBUG) {
                    Log.i("ReverseChargingMetrics", "Report vendor atom OK, " + vendorAtom);
                }
            }
        } catch (Exception e) {
            Log.e("ReverseChargingMetrics", "Failed to log atom to IStats service, " + e);
        }
    }
}
