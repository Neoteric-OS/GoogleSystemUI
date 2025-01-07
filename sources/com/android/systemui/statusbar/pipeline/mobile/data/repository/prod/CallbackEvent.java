package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyDisplayInfo;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface CallbackEvent {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnCarrierNetworkChange implements CallbackEvent {
        public final boolean active;

        public OnCarrierNetworkChange(boolean z) {
            this.active = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnCarrierNetworkChange) && this.active == ((OnCarrierNetworkChange) obj).active;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.active);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("OnCarrierNetworkChange(active="), this.active, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnCarrierRoamingNtnModeChanged implements CallbackEvent {
        public final boolean active;

        public OnCarrierRoamingNtnModeChanged(boolean z) {
            this.active = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnCarrierRoamingNtnModeChanged) && this.active == ((OnCarrierRoamingNtnModeChanged) obj).active;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.active);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("OnCarrierRoamingNtnModeChanged(active="), this.active, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnDataActivity implements CallbackEvent {
        public final int direction;

        public OnDataActivity(int i) {
            this.direction = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnDataActivity) && this.direction == ((OnDataActivity) obj).direction;
        }

        public final int hashCode() {
            return Integer.hashCode(this.direction);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("OnDataActivity(direction="), this.direction, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnDataConnectionStateChanged implements CallbackEvent {
        public final int dataState;

        public OnDataConnectionStateChanged(int i) {
            this.dataState = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnDataConnectionStateChanged) && this.dataState == ((OnDataConnectionStateChanged) obj).dataState;
        }

        public final int hashCode() {
            return Integer.hashCode(this.dataState);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("OnDataConnectionStateChanged(dataState="), this.dataState, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnDataEnabledChanged implements CallbackEvent {
        public final boolean enabled;

        public OnDataEnabledChanged(boolean z) {
            this.enabled = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnDataEnabledChanged) && this.enabled == ((OnDataEnabledChanged) obj).enabled;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.enabled);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("OnDataEnabledChanged(enabled="), this.enabled, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnDisplayInfoChanged implements CallbackEvent {
        public final TelephonyDisplayInfo telephonyDisplayInfo;

        public OnDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) {
            this.telephonyDisplayInfo = telephonyDisplayInfo;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnDisplayInfoChanged) && Intrinsics.areEqual(this.telephonyDisplayInfo, ((OnDisplayInfoChanged) obj).telephonyDisplayInfo);
        }

        public final int hashCode() {
            return this.telephonyDisplayInfo.hashCode();
        }

        public final String toString() {
            return "OnDisplayInfoChanged(telephonyDisplayInfo=" + this.telephonyDisplayInfo + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnServiceStateChanged implements CallbackEvent {
        public final ServiceState serviceState;

        public OnServiceStateChanged(ServiceState serviceState) {
            this.serviceState = serviceState;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnServiceStateChanged) && Intrinsics.areEqual(this.serviceState, ((OnServiceStateChanged) obj).serviceState);
        }

        public final int hashCode() {
            return this.serviceState.hashCode();
        }

        public final String toString() {
            return "OnServiceStateChanged(serviceState=" + this.serviceState + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnSignalStrengthChanged implements CallbackEvent {
        public final SignalStrength signalStrength;

        public OnSignalStrengthChanged(SignalStrength signalStrength) {
            this.signalStrength = signalStrength;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnSignalStrengthChanged) && Intrinsics.areEqual(this.signalStrength, ((OnSignalStrengthChanged) obj).signalStrength);
        }

        public final int hashCode() {
            return this.signalStrength.hashCode();
        }

        public final String toString() {
            return "OnSignalStrengthChanged(signalStrength=" + this.signalStrength + ")";
        }
    }
}
