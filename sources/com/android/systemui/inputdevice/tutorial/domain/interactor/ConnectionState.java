package com.android.systemui.inputdevice.tutorial.domain.interactor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectionState {
    public final boolean keyboardConnected;
    public final boolean touchpadConnected;

    public ConnectionState(boolean z, boolean z2) {
        this.keyboardConnected = z;
        this.touchpadConnected = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConnectionState)) {
            return false;
        }
        ConnectionState connectionState = (ConnectionState) obj;
        return this.keyboardConnected == connectionState.keyboardConnected && this.touchpadConnected == connectionState.touchpadConnected;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.touchpadConnected) + (Boolean.hashCode(this.keyboardConnected) * 31);
    }

    public final String toString() {
        return "ConnectionState(keyboardConnected=" + this.keyboardConnected + ", touchpadConnected=" + this.touchpadConnected + ")";
    }
}
