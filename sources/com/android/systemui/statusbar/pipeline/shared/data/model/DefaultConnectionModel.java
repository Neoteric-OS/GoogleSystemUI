package com.android.systemui.statusbar.pipeline.shared.data.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultConnectionModel {
    public final CarrierMerged carrierMerged;
    public final Ethernet ethernet;
    public final boolean isValidated;
    public final Mobile mobile;
    public final Wifi wifi;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CarrierMerged {
        public final boolean isDefault;

        public CarrierMerged(boolean z) {
            this.isDefault = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof CarrierMerged) && this.isDefault == ((CarrierMerged) obj).isDefault;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isDefault);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("CarrierMerged(isDefault="), this.isDefault, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Ethernet {
        public final boolean isDefault;

        public Ethernet(boolean z) {
            this.isDefault = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Ethernet) && this.isDefault == ((Ethernet) obj).isDefault;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isDefault);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("Ethernet(isDefault="), this.isDefault, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Mobile {
        public final boolean isDefault;

        public Mobile(boolean z) {
            this.isDefault = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Mobile) && this.isDefault == ((Mobile) obj).isDefault;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isDefault);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("Mobile(isDefault="), this.isDefault, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Wifi {
        public final boolean isDefault;

        public Wifi(boolean z) {
            this.isDefault = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Wifi) && this.isDefault == ((Wifi) obj).isDefault;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isDefault);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("Wifi(isDefault="), this.isDefault, ")");
        }
    }

    public DefaultConnectionModel(Wifi wifi, Mobile mobile, CarrierMerged carrierMerged, Ethernet ethernet, boolean z) {
        this.wifi = wifi;
        this.mobile = mobile;
        this.carrierMerged = carrierMerged;
        this.ethernet = ethernet;
        this.isValidated = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DefaultConnectionModel)) {
            return false;
        }
        DefaultConnectionModel defaultConnectionModel = (DefaultConnectionModel) obj;
        return Intrinsics.areEqual(this.wifi, defaultConnectionModel.wifi) && Intrinsics.areEqual(this.mobile, defaultConnectionModel.mobile) && Intrinsics.areEqual(this.carrierMerged, defaultConnectionModel.carrierMerged) && Intrinsics.areEqual(this.ethernet, defaultConnectionModel.ethernet) && this.isValidated == defaultConnectionModel.isValidated;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isValidated) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.wifi.isDefault) * 31, 31, this.mobile.isDefault), 31, this.carrierMerged.isDefault), 31, this.ethernet.isDefault);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DefaultConnectionModel(wifi=");
        sb.append(this.wifi);
        sb.append(", mobile=");
        sb.append(this.mobile);
        sb.append(", carrierMerged=");
        sb.append(this.carrierMerged);
        sb.append(", ethernet=");
        sb.append(this.ethernet);
        sb.append(", isValidated=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isValidated, ")");
    }
}
