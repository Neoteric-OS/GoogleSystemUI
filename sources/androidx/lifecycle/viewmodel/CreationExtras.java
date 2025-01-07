package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CreationExtras {
    public final Map extras = new LinkedHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Empty extends CreationExtras {
        public static final Empty INSTANCE = new Empty();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof CreationExtras) && Intrinsics.areEqual(this.extras, ((CreationExtras) obj).extras);
    }

    public final int hashCode() {
        return this.extras.hashCode();
    }

    public final String toString() {
        return "CreationExtras(extras=" + this.extras + ')';
    }
}
