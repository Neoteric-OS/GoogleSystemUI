package com.google.android.systemui.power.batteryevent.repository;

import android.util.Log;
import com.google.android.systemui.googlebattery.GoogleBatteryManager;
import com.google.android.systemui.power.batteryevent.common.HalDataType;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HalDataSource {
    public static final Map featureName = MapsKt.mapOf(new Pair(1, "Temp defend"), new Pair(3, "Dwell defend"));
    public final GoogleBatteryManagerWrapperImpl googleBatteryManager;
    public boolean lastDwellDefendStatus;
    public int lastGoogleBatteryDockDefendStatus;
    public boolean lastTempDefendStatus;

    static {
        CollectionsKt__CollectionsKt.arrayListOf(HalDataType.GOOGLE_BATTERY_DOCK_DEFEND_STATUS, HalDataType.GOOGLE_BATTERY_DWELL_DEFEND_STATUS, HalDataType.GOOGLE_BATTERY_TEMP_DEFEND_STATUS);
    }

    public HalDataSource(GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl) {
        this.googleBatteryManager = googleBatteryManagerWrapperImpl;
    }

    public final void destroyGoogleBattery(IGoogleBattery iGoogleBattery) {
        try {
            GoogleBatteryManager.destroyHalInterface(iGoogleBattery, HalDataSource$deathRecipient$1.INSTANCE);
        } catch (Exception e) {
            Log.w("GoogleBatteryDataSource", "destroyHalInterface failed: ", e);
        }
    }

    public final String fetchFeatureStatus(IGoogleBattery iGoogleBattery, final int i, boolean z) {
        String stringProperty;
        if (iGoogleBattery != null) {
            try {
                stringProperty = ((IGoogleBattery.Stub.Proxy) iGoogleBattery).getStringProperty(i);
            } catch (Exception e) {
                Map map = featureName;
                Log.w("GoogleBatteryDataSource", "retry fetchFeatureStatus: " + map.get(Integer.valueOf(i)));
                if (z) {
                    IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(HalDataSource$deathRecipient$1.INSTANCE);
                    if (initHalInterface == null) {
                        return "init google battery failed";
                    }
                    Object invoke = new Function1() { // from class: com.google.android.systemui.power.batteryevent.repository.HalDataSource$fetchFeatureStatus$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return HalDataSource.this.fetchFeatureStatus((IGoogleBattery) obj, i, false);
                        }
                    }.invoke(initHalInterface);
                    destroyGoogleBattery(initHalInterface);
                    return (String) invoke;
                }
                Log.w("GoogleBatteryDataSource", "fetchFeatureStatus: " + map.get(Integer.valueOf(i)) + " failed", e);
                String message = e.getMessage();
                return message == null ? "" : message;
            }
        } else {
            stringProperty = null;
        }
        if (stringProperty == null) {
            stringProperty = "null googleBattery";
        }
        return stringProperty;
    }
}
