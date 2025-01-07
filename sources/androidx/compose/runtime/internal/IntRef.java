package androidx.compose.runtime.internal;

import kotlin.text.CharsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IntRef {
    public int element = 0;

    public final String toString() {
        StringBuilder sb = new StringBuilder("IntRef(element = ");
        sb.append(this.element);
        sb.append(")@");
        int hashCode = hashCode();
        CharsKt.checkRadix(16);
        sb.append(Integer.toString(hashCode, 16));
        return sb.toString();
    }
}
