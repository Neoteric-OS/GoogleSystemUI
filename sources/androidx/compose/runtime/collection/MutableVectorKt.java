package androidx.compose.runtime.collection;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MutableVectorKt {
    public static final void checkIndex(int i, List list) {
        int size = list.size();
        if (i < 0 || i >= size) {
            throwListIndexOutOfBoundsException(i, size);
        }
    }

    public static final void checkSubIndex(int i, int i2, List list) {
        if (i > i2) {
            throwReversedIndicesException(i, i2);
        }
        if (i < 0) {
            throwNegativeIndexException(i);
        }
        if (i2 > list.size()) {
            throwOutOfRangeException(i2, list.size());
        }
    }

    private static final void throwListIndexOutOfBoundsException(int i, int i2) {
        throw new IndexOutOfBoundsException(MutableVectorKt$$ExternalSyntheticOutline0.m(i, i2, "Index ", " is out of bounds. The list has ", " elements."));
    }

    private static final void throwNegativeIndexException(int i) {
        throw new IndexOutOfBoundsException(GenericDocument$$ExternalSyntheticOutline0.m("fromIndex (", ") is less than 0.", i));
    }

    private static final void throwOutOfRangeException(int i, int i2) {
        throw new IndexOutOfBoundsException("toIndex (" + i + ") is more than than the list size (" + i2 + ')');
    }

    private static final void throwReversedIndicesException(int i, int i2) {
        throw new IllegalArgumentException(MutableVectorKt$$ExternalSyntheticOutline0.m(i, i2, "Indices are out of order. fromIndex (", ") is greater than toIndex (", ")."));
    }
}
