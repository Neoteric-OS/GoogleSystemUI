package com.android.systemui.statusbar.pipeline.wifi.shared.model;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiScanEntry {
    public final String ssid;

    public WifiScanEntry(String str) {
        this.ssid = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WifiScanEntry) && Intrinsics.areEqual(this.ssid, ((WifiScanEntry) obj).ssid);
    }

    public final int hashCode() {
        return this.ssid.hashCode();
    }

    public final String toString() {
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("WifiScanEntry(ssid="), this.ssid, ")");
    }
}
