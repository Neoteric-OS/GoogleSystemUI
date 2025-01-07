package androidx.datastore.preferences.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Preferences$Key {
    public final String name;

    public Preferences$Key(String str) {
        this.name = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Preferences$Key)) {
            return false;
        }
        return Intrinsics.areEqual(this.name, ((Preferences$Key) obj).name);
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final String toString() {
        return this.name;
    }
}
