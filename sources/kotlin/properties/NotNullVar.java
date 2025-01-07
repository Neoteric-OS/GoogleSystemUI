package kotlin.properties;

import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.CallableReference;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotNullVar {
    public Object value;

    /* JADX WARN: Multi-variable type inference failed */
    public final Object getValue(Object obj, KProperty kProperty) {
        Object obj2 = this.value;
        if (obj2 != null) {
            return obj2;
        }
        throw new IllegalStateException("Property " + ((CallableReference) kProperty).getName() + " should be initialized before get.");
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("NotNullProperty(");
        if (this.value != null) {
            str = "value=" + this.value;
        } else {
            str = "value not initialized yet";
        }
        return OpaqueKey$$ExternalSyntheticOutline0.m(sb, str, ')');
    }
}
