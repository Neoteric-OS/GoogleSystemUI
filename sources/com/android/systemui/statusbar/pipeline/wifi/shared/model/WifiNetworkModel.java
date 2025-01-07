package com.android.systemui.statusbar.pipeline.wifi.shared.model;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import com.android.systemui.log.table.Diffable;
import com.android.systemui.log.table.TableLogBuffer;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WifiNetworkModel implements Diffable {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Active extends WifiNetworkModel {
        public final HotspotDeviceType hotspotDeviceType;
        public final boolean isValidated;
        public final int level;
        public final String ssid;

        public Active(boolean z, int i, String str, HotspotDeviceType hotspotDeviceType) {
            this.isValidated = z;
            this.level = i;
            this.ssid = str;
            this.hotspotDeviceType = hotspotDeviceType;
            if (i == -1 || i < 0 || i >= 5) {
                throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Wifi network was active but had invalid level. 0 <= wifi level <= 4 required; level was "), ". This should only be an issue if the caller incorrectly used `copy` to get a new instance. Please use the `of` method instead.").toString());
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Active)) {
                return false;
            }
            Active active = (Active) obj;
            return this.isValidated == active.isValidated && this.level == active.level && Intrinsics.areEqual(this.ssid, active.ssid) && this.hotspotDeviceType == active.hotspotDeviceType;
        }

        public final int hashCode() {
            int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.level, Boolean.hashCode(this.isValidated) * 31, 31);
            String str = this.ssid;
            return this.hotspotDeviceType.hashCode() + ((m + (str == null ? 0 : str.hashCode())) * 31);
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            WifiNetworkModel wifiNetworkModel = (WifiNetworkModel) obj;
            if (!(wifiNetworkModel instanceof Active)) {
                logFull(tableRowLoggerImpl);
                return;
            }
            Active active = (Active) wifiNetworkModel;
            boolean z = active.isValidated;
            boolean z2 = this.isValidated;
            if (z != z2) {
                tableRowLoggerImpl.logChange("isValidated", z2);
            }
            int i = active.level;
            int i2 = this.level;
            if (i != i2) {
                tableRowLoggerImpl.logChange(i2, "level");
            }
            String str = active.ssid;
            String str2 = this.ssid;
            if (!Intrinsics.areEqual(str, str2)) {
                tableRowLoggerImpl.logChange("ssid", str2);
            }
            HotspotDeviceType hotspotDeviceType = active.hotspotDeviceType;
            HotspotDeviceType hotspotDeviceType2 = this.hotspotDeviceType;
            if (hotspotDeviceType != hotspotDeviceType2) {
                tableRowLoggerImpl.logChange("hotspot", hotspotDeviceType2.name());
            }
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logFull(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            tableRowLoggerImpl.logChange("type", "Active");
            tableRowLoggerImpl.logChange("subscriptionId", (String) null);
            tableRowLoggerImpl.logChange("isValidated", this.isValidated);
            tableRowLoggerImpl.logChange(this.level, "level");
            tableRowLoggerImpl.logChange("maxLevel", (String) null);
            tableRowLoggerImpl.logChange("ssid", this.ssid);
            tableRowLoggerImpl.logChange("hotspot", this.hotspotDeviceType.name());
        }

        public final String toString() {
            return "Active(isValidated=" + this.isValidated + ", level=" + this.level + ", ssid=" + this.ssid + ", hotspotDeviceType=" + this.hotspotDeviceType + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CarrierMerged extends WifiNetworkModel {
        public final int level;
        public final int numberOfLevels;
        public final int subscriptionId;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class Companion {
            public static WifiNetworkModel of(int i, int i2, int i3) {
                return i != -1 ? (i2 == -1 || i2 < 0 || i2 > i3) ? new Invalid(ListImplementation$$ExternalSyntheticOutline0.m("Wifi network was carrier merged but had invalid level. 0 <= wifi level <= ", i3, i2, " required; level was ")) : new CarrierMerged(i, i2, i3) : new Invalid("Wifi network was carrier merged but had invalid sub ID");
            }
        }

        public CarrierMerged(int i, int i2, int i3) {
            this.subscriptionId = i;
            this.level = i2;
            this.numberOfLevels = i3;
            if (i2 == -1 || i2 < 0 || i2 > i3) {
                throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(ListImplementation$$ExternalSyntheticOutline0.m("Wifi network was carrier merged but had invalid level. 0 <= wifi level <= ", i3, i2, " required; level was "), ". This should only be an issue if the caller incorrectly used `copy` to get a new instance. Please use the `of` method instead.").toString());
            }
            if (i == -1) {
                throw new IllegalArgumentException("Wifi network was carrier merged but had invalid sub ID. This should only be an issue if the caller incorrectly used `copy` to get a new instance. Please use the `of` method instead.");
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CarrierMerged)) {
                return false;
            }
            CarrierMerged carrierMerged = (CarrierMerged) obj;
            return this.subscriptionId == carrierMerged.subscriptionId && this.level == carrierMerged.level && this.numberOfLevels == carrierMerged.numberOfLevels;
        }

        public final int hashCode() {
            return Integer.hashCode(this.numberOfLevels) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.level, Integer.hashCode(this.subscriptionId) * 31, 31);
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            WifiNetworkModel wifiNetworkModel = (WifiNetworkModel) obj;
            if (!(wifiNetworkModel instanceof CarrierMerged)) {
                logFull(tableRowLoggerImpl);
                return;
            }
            CarrierMerged carrierMerged = (CarrierMerged) wifiNetworkModel;
            int i = carrierMerged.subscriptionId;
            int i2 = this.subscriptionId;
            if (i != i2) {
                tableRowLoggerImpl.logChange(i2, "subscriptionId");
            }
            int i3 = carrierMerged.level;
            int i4 = this.level;
            if (i3 != i4) {
                tableRowLoggerImpl.logChange(i4, "level");
            }
            int i5 = carrierMerged.numberOfLevels;
            int i6 = this.numberOfLevels;
            if (i5 != i6) {
                tableRowLoggerImpl.logChange(i6, "maxLevel");
            }
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logFull(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            tableRowLoggerImpl.logChange("type", "CarrierMerged");
            tableRowLoggerImpl.logChange(this.subscriptionId, "subscriptionId");
            tableRowLoggerImpl.logChange("isValidated", true);
            tableRowLoggerImpl.logChange(this.level, "level");
            tableRowLoggerImpl.logChange(this.numberOfLevels, "maxLevel");
            tableRowLoggerImpl.logChange("ssid", (String) null);
            tableRowLoggerImpl.logChange("hotspot", (String) null);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("CarrierMerged(subscriptionId=");
            sb.append(this.subscriptionId);
            sb.append(", level=");
            sb.append(this.level);
            sb.append(", numberOfLevels=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.numberOfLevels, ")");
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HotspotDeviceType {
        public static final /* synthetic */ HotspotDeviceType[] $VALUES;
        public static final HotspotDeviceType AUTO;
        public static final HotspotDeviceType INVALID;
        public static final HotspotDeviceType LAPTOP;
        public static final HotspotDeviceType NONE;
        public static final HotspotDeviceType PHONE;
        public static final HotspotDeviceType TABLET;
        public static final HotspotDeviceType UNKNOWN;
        public static final HotspotDeviceType WATCH;

        static {
            HotspotDeviceType hotspotDeviceType = new HotspotDeviceType("NONE", 0);
            NONE = hotspotDeviceType;
            HotspotDeviceType hotspotDeviceType2 = new HotspotDeviceType("UNKNOWN", 1);
            UNKNOWN = hotspotDeviceType2;
            HotspotDeviceType hotspotDeviceType3 = new HotspotDeviceType("PHONE", 2);
            PHONE = hotspotDeviceType3;
            HotspotDeviceType hotspotDeviceType4 = new HotspotDeviceType("TABLET", 3);
            TABLET = hotspotDeviceType4;
            HotspotDeviceType hotspotDeviceType5 = new HotspotDeviceType("LAPTOP", 4);
            LAPTOP = hotspotDeviceType5;
            HotspotDeviceType hotspotDeviceType6 = new HotspotDeviceType("WATCH", 5);
            WATCH = hotspotDeviceType6;
            HotspotDeviceType hotspotDeviceType7 = new HotspotDeviceType("AUTO", 6);
            AUTO = hotspotDeviceType7;
            HotspotDeviceType hotspotDeviceType8 = new HotspotDeviceType("INVALID", 7);
            INVALID = hotspotDeviceType8;
            HotspotDeviceType[] hotspotDeviceTypeArr = {hotspotDeviceType, hotspotDeviceType2, hotspotDeviceType3, hotspotDeviceType4, hotspotDeviceType5, hotspotDeviceType6, hotspotDeviceType7, hotspotDeviceType8};
            $VALUES = hotspotDeviceTypeArr;
            EnumEntriesKt.enumEntries(hotspotDeviceTypeArr);
        }

        public static HotspotDeviceType valueOf(String str) {
            return (HotspotDeviceType) Enum.valueOf(HotspotDeviceType.class, str);
        }

        public static HotspotDeviceType[] values() {
            return (HotspotDeviceType[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Inactive extends WifiNetworkModel {
        public final String inactiveReason;

        public Inactive(String str) {
            this.inactiveReason = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Inactive) && Intrinsics.areEqual(this.inactiveReason, ((Inactive) obj).inactiveReason);
        }

        public final int hashCode() {
            String str = this.inactiveReason;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            WifiNetworkModel wifiNetworkModel = (WifiNetworkModel) obj;
            if (!(wifiNetworkModel instanceof Inactive)) {
                logFull(tableRowLoggerImpl);
                return;
            }
            String str = ((Inactive) wifiNetworkModel).inactiveReason;
            String str2 = this.inactiveReason;
            if (Intrinsics.areEqual(str2, str)) {
                return;
            }
            tableRowLoggerImpl.logChange("type", "Inactive[reason=" + str2 + "]");
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logFull(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            tableRowLoggerImpl.logChange("type", "Inactive[reason=" + this.inactiveReason + "]");
            tableRowLoggerImpl.logChange("subscriptionId", (String) null);
            tableRowLoggerImpl.logChange("isValidated", false);
            tableRowLoggerImpl.logChange("level", (String) null);
            tableRowLoggerImpl.logChange("maxLevel", (String) null);
            tableRowLoggerImpl.logChange("ssid", (String) null);
            tableRowLoggerImpl.logChange("hotspot", (String) null);
        }

        public final String toString() {
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("WifiNetwork.Inactive[reason="), this.inactiveReason, "]");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Invalid extends WifiNetworkModel {
        public final String invalidReason;

        public Invalid(String str) {
            this.invalidReason = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Invalid) && Intrinsics.areEqual(this.invalidReason, ((Invalid) obj).invalidReason);
        }

        public final int hashCode() {
            return this.invalidReason.hashCode();
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            WifiNetworkModel wifiNetworkModel = (WifiNetworkModel) obj;
            if (!(wifiNetworkModel instanceof Invalid)) {
                logFull(tableRowLoggerImpl);
                return;
            }
            String str = ((Invalid) wifiNetworkModel).invalidReason;
            String str2 = this.invalidReason;
            if (Intrinsics.areEqual(str2, str)) {
                return;
            }
            tableRowLoggerImpl.logChange("type", "Unavailable[reason=" + str2 + "]");
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logFull(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            tableRowLoggerImpl.logChange("type", "Unavailable[reason=" + this.invalidReason + "]");
            tableRowLoggerImpl.logChange("subscriptionId", (String) null);
            tableRowLoggerImpl.logChange("isValidated", false);
            tableRowLoggerImpl.logChange("level", (String) null);
            tableRowLoggerImpl.logChange("maxLevel", (String) null);
            tableRowLoggerImpl.logChange("ssid", (String) null);
            tableRowLoggerImpl.logChange("hotspot", (String) null);
        }

        public final String toString() {
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("WifiNetwork.Invalid[reason="), this.invalidReason, "]");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Unavailable extends WifiNetworkModel {
        public static final Unavailable INSTANCE = new Unavailable();

        @Override // com.android.systemui.log.table.Diffable
        public final void logDiffs(Object obj, TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            if (((WifiNetworkModel) obj) instanceof Unavailable) {
                return;
            }
            logFull(tableRowLoggerImpl);
        }

        @Override // com.android.systemui.log.table.Diffable
        public final void logFull(TableLogBuffer.TableRowLoggerImpl tableRowLoggerImpl) {
            tableRowLoggerImpl.logChange("type", "Unavailable");
            tableRowLoggerImpl.logChange("subscriptionId", (String) null);
            tableRowLoggerImpl.logChange("isValidated", false);
            tableRowLoggerImpl.logChange("level", (String) null);
            tableRowLoggerImpl.logChange("maxLevel", (String) null);
            tableRowLoggerImpl.logChange("ssid", (String) null);
            tableRowLoggerImpl.logChange("hotspot", (String) null);
        }

        public final String toString() {
            return "WifiNetwork.Unavailable";
        }
    }
}
