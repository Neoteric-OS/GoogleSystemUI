package com.android.systemui.statusbar.phone;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.systemui.statusbar.pipeline.satellite.ui.DeviceBasedSatelliteBindableIcon$initializer$1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class StatusBarIconHolder {
    public StatusBarIcon icon;
    public int tag;
    public int type;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BindableIconHolder extends StatusBarIconHolder {
        public final DeviceBasedSatelliteBindableIcon$initializer$1 initializer;
        public boolean isVisible = true;
        public final String slot;

        public BindableIconHolder(DeviceBasedSatelliteBindableIcon$initializer$1 deviceBasedSatelliteBindableIcon$initializer$1, String str) {
            this.initializer = deviceBasedSatelliteBindableIcon$initializer$1;
            this.slot = str;
        }

        @Override // com.android.systemui.statusbar.phone.StatusBarIconHolder
        public final int getType() {
            return 5;
        }

        @Override // com.android.systemui.statusbar.phone.StatusBarIconHolder
        public final boolean isVisible() {
            return this.isVisible;
        }

        @Override // com.android.systemui.statusbar.phone.StatusBarIconHolder
        public final void setVisible(boolean z) {
            this.isVisible = z;
        }

        @Override // com.android.systemui.statusbar.phone.StatusBarIconHolder
        public final String toString() {
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("StatusBarIconHolder(type=BINDABLE, slot="), this.slot, ")");
        }
    }

    public int getType() {
        return this.type;
    }

    public boolean isVisible() {
        int type = getType();
        if (type != 0) {
            return (type == 3 || type != 4) ? true : true;
        }
        StatusBarIcon statusBarIcon = this.icon;
        Intrinsics.checkNotNull(statusBarIcon);
        return statusBarIcon.visible;
    }

    public void setVisible(boolean z) {
        if (isVisible() != z && getType() == 0) {
            StatusBarIcon statusBarIcon = this.icon;
            Intrinsics.checkNotNull(statusBarIcon);
            statusBarIcon.visible = z;
        }
    }

    public String toString() {
        int type = getType();
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(GenericDocument$$ExternalSyntheticOutline0.m("StatusBarIconHolder(type=", type != 0 ? type != 3 ? type != 4 ? "UNKNOWN" : "WIFI_NEW" : "MOBILE_NEW" : "ICON", " tag=", this.tag, " visible="), isVisible(), ")");
    }
}
