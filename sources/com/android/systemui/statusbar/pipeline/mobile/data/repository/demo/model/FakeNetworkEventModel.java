package com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.settingslib.SignalIcon$MobileIconGroup;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface FakeNetworkEventModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Mobile implements FakeNetworkEventModel {
        public final Integer activity;
        public final Integer carrierId;
        public final boolean carrierNetworkChange;
        public final SignalIcon$MobileIconGroup dataType;
        public final boolean inflateStrength;
        public final Integer level;
        public final String name;
        public final boolean ntn;
        public final boolean roaming;
        public final boolean slice;
        public final Integer subId;

        public Mobile(Integer num, SignalIcon$MobileIconGroup signalIcon$MobileIconGroup, Integer num2, Integer num3, boolean z, Integer num4, boolean z2, boolean z3, String str, boolean z4, boolean z5) {
            this.level = num;
            this.dataType = signalIcon$MobileIconGroup;
            this.subId = num2;
            this.carrierId = num3;
            this.inflateStrength = z;
            this.activity = num4;
            this.carrierNetworkChange = z2;
            this.roaming = z3;
            this.name = str;
            this.slice = z4;
            this.ntn = z5;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Mobile)) {
                return false;
            }
            Mobile mobile = (Mobile) obj;
            return Intrinsics.areEqual(this.level, mobile.level) && Intrinsics.areEqual(this.dataType, mobile.dataType) && Intrinsics.areEqual(this.subId, mobile.subId) && Intrinsics.areEqual(this.carrierId, mobile.carrierId) && this.inflateStrength == mobile.inflateStrength && Intrinsics.areEqual(this.activity, mobile.activity) && this.carrierNetworkChange == mobile.carrierNetworkChange && this.roaming == mobile.roaming && Intrinsics.areEqual(this.name, mobile.name) && this.slice == mobile.slice && this.ntn == mobile.ntn;
        }

        public final int hashCode() {
            Integer num = this.level;
            int hashCode = (num == null ? 0 : num.hashCode()) * 31;
            SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = this.dataType;
            int hashCode2 = (hashCode + (signalIcon$MobileIconGroup == null ? 0 : signalIcon$MobileIconGroup.hashCode())) * 31;
            Integer num2 = this.subId;
            int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.carrierId;
            int m = TransitionData$$ExternalSyntheticOutline0.m((hashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31, 31, this.inflateStrength);
            Integer num4 = this.activity;
            return Boolean.hashCode(this.ntn) + TransitionData$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.name, TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((m + (num4 != null ? num4.hashCode() : 0)) * 31, 31, this.carrierNetworkChange), 31, this.roaming), 31), 31, this.slice);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Mobile(level=");
            sb.append(this.level);
            sb.append(", dataType=");
            sb.append(this.dataType);
            sb.append(", subId=");
            sb.append(this.subId);
            sb.append(", carrierId=");
            sb.append(this.carrierId);
            sb.append(", inflateStrength=");
            sb.append(this.inflateStrength);
            sb.append(", activity=");
            sb.append(this.activity);
            sb.append(", carrierNetworkChange=");
            sb.append(this.carrierNetworkChange);
            sb.append(", roaming=");
            sb.append(this.roaming);
            sb.append(", name=");
            sb.append(this.name);
            sb.append(", slice=");
            sb.append(this.slice);
            sb.append(", ntn=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.ntn, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MobileDisabled implements FakeNetworkEventModel {
        public final Integer subId;

        public MobileDisabled(Integer num) {
            this.subId = num;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MobileDisabled) && Intrinsics.areEqual(this.subId, ((MobileDisabled) obj).subId);
        }

        public final int hashCode() {
            Integer num = this.subId;
            if (num == null) {
                return 0;
            }
            return num.hashCode();
        }

        public final String toString() {
            return "MobileDisabled(subId=" + this.subId + ")";
        }
    }
}
