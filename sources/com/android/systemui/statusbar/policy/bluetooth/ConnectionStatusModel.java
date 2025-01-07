package com.android.systemui.statusbar.policy.bluetooth;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConnectionStatusModel {
    public final List connectedDevices;
    public final int maxConnectionState;

    public ConnectionStatusModel(int i, List list) {
        this.maxConnectionState = i;
        this.connectedDevices = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConnectionStatusModel)) {
            return false;
        }
        ConnectionStatusModel connectionStatusModel = (ConnectionStatusModel) obj;
        return this.maxConnectionState == connectionStatusModel.maxConnectionState && Intrinsics.areEqual(this.connectedDevices, connectionStatusModel.connectedDevices);
    }

    public final int hashCode() {
        return this.connectedDevices.hashCode() + (Integer.hashCode(this.maxConnectionState) * 31);
    }

    public final String toString() {
        return "ConnectionStatusModel(maxConnectionState=" + this.maxConnectionState + ", connectedDevices=" + this.connectedDevices + ")";
    }
}
