package com.android.systemui.qs.external;

import android.content.ComponentName;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileServiceKey {
    public final ComponentName componentName;
    public final String string;
    public final int user;

    public TileServiceKey(int i, ComponentName componentName) {
        this.componentName = componentName;
        this.user = i;
        this.string = componentName.flattenToString() + ":" + i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileServiceKey)) {
            return false;
        }
        TileServiceKey tileServiceKey = (TileServiceKey) obj;
        return Intrinsics.areEqual(this.componentName, tileServiceKey.componentName) && this.user == tileServiceKey.user;
    }

    public final int hashCode() {
        return Integer.hashCode(this.user) + (this.componentName.hashCode() * 31);
    }

    public final String toString() {
        return this.string;
    }
}
