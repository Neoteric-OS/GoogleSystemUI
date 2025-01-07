package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableObjectList extends ObjectList {
    public MutableObjectList(int i) {
        this.content = i == 0 ? ObjectListKt.EmptyArray : new Object[i];
    }

    public final void add(Object obj) {
        int i = this._size + 1;
        Object[] objArr = this.content;
        if (objArr.length < i) {
            this.content = Arrays.copyOf(objArr, Math.max(i, (objArr.length * 3) / 2));
        }
        Object[] objArr2 = this.content;
        int i2 = this._size;
        objArr2[i2] = obj;
        this._size = i2 + 1;
    }

    public final Object removeAt(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this._size)) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("Index ", " must be in 0..", i);
            m.append(this._size - 1);
            RuntimeHelpersKt.throwIndexOutOfBoundsException(m.toString());
            throw null;
        }
        Object[] objArr = this.content;
        Object obj = objArr[i];
        if (i != i2 - 1) {
            ArraysKt.copyInto(i, i + 1, i2, objArr, objArr);
        }
        int i3 = this._size - 1;
        this._size = i3;
        objArr[i3] = null;
        return obj;
    }

    public /* synthetic */ MutableObjectList() {
        this(16);
    }
}
