package com.google.android.systemui.dreamliner;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DockInfo {
    public int accessoryType;
    public String manufacturer;
    public String model;
    public String serialNumber;

    public final String toString() {
        return this.manufacturer + ", " + this.model + ", " + this.serialNumber + ", " + this.accessoryType;
    }
}
