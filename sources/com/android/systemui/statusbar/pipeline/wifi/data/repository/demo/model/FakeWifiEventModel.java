package com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface FakeWifiEventModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CarrierMerged implements FakeWifiEventModel {
        public final int activity;
        public final int level;
        public final int numberOfLevels;
        public final int subscriptionId;

        public CarrierMerged(int i, int i2, int i3, int i4) {
            this.subscriptionId = i;
            this.level = i2;
            this.numberOfLevels = i3;
            this.activity = i4;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CarrierMerged)) {
                return false;
            }
            CarrierMerged carrierMerged = (CarrierMerged) obj;
            return this.subscriptionId == carrierMerged.subscriptionId && this.level == carrierMerged.level && this.numberOfLevels == carrierMerged.numberOfLevels && this.activity == carrierMerged.activity;
        }

        public final int hashCode() {
            return Integer.hashCode(this.activity) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.numberOfLevels, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.level, Integer.hashCode(this.subscriptionId) * 31, 31), 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("CarrierMerged(subscriptionId=");
            sb.append(this.subscriptionId);
            sb.append(", level=");
            sb.append(this.level);
            sb.append(", numberOfLevels=");
            sb.append(this.numberOfLevels);
            sb.append(", activity=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.activity, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Wifi implements FakeWifiEventModel {
        public final int activity;
        public final WifiNetworkModel.HotspotDeviceType hotspotDeviceType;
        public final Integer level;
        public final String ssid;
        public final Boolean validated;

        public Wifi(Integer num, int i, String str, Boolean bool, WifiNetworkModel.HotspotDeviceType hotspotDeviceType) {
            this.level = num;
            this.activity = i;
            this.ssid = str;
            this.validated = bool;
            this.hotspotDeviceType = hotspotDeviceType;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Wifi)) {
                return false;
            }
            Wifi wifi = (Wifi) obj;
            return Intrinsics.areEqual(this.level, wifi.level) && this.activity == wifi.activity && Intrinsics.areEqual(this.ssid, wifi.ssid) && Intrinsics.areEqual(this.validated, wifi.validated) && this.hotspotDeviceType == wifi.hotspotDeviceType;
        }

        public final int hashCode() {
            Integer num = this.level;
            int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.activity, (num == null ? 0 : num.hashCode()) * 31, 31);
            String str = this.ssid;
            int hashCode = (m + (str == null ? 0 : str.hashCode())) * 31;
            Boolean bool = this.validated;
            return this.hotspotDeviceType.hashCode() + ((hashCode + (bool != null ? bool.hashCode() : 0)) * 31);
        }

        public final String toString() {
            return "Wifi(level=" + this.level + ", activity=" + this.activity + ", ssid=" + this.ssid + ", validated=" + this.validated + ", hotspotDeviceType=" + this.hotspotDeviceType + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WifiDisabled implements FakeWifiEventModel {
        public static final WifiDisabled INSTANCE = new WifiDisabled();
    }
}
