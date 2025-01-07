package androidx.compose.runtime.external.kotlinx.collections.immutable.internal;

import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ListImplementation {
    public static final void checkElementIndex$runtime_release(int i, int i2) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
        }
    }

    public static final void checkPositionIndex$runtime_release(int i, int i2) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
        }
    }

    public static final void checkRangeIndexes$runtime_release(int i, int i2, int i3) {
        if (i < 0 || i2 > i3) {
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "fromIndex: ", ", toIndex: ", ", size: ");
            m.append(i3);
            throw new IndexOutOfBoundsException(m.toString());
        }
        if (i > i2) {
            throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("fromIndex: ", i, i2, " > toIndex: "));
        }
    }
}
