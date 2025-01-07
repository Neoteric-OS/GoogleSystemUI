package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SuspendPointerInputElement extends ModifierNodeElement {
    public final Object key1;
    public final Object key2;
    public final Object[] keys;
    public final PointerInputEventHandler pointerInputEventHandler;

    public SuspendPointerInputElement(Object obj, Object obj2, Object[] objArr, PointerInputEventHandler pointerInputEventHandler, int i) {
        obj = (i & 1) != 0 ? null : obj;
        obj2 = (i & 2) != 0 ? null : obj2;
        objArr = (i & 4) != 0 ? null : objArr;
        this.key1 = obj;
        this.key2 = obj2;
        this.keys = objArr;
        this.pointerInputEventHandler = pointerInputEventHandler;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new SuspendingPointerInputModifierNodeImpl(this.key1, this.key2, this.keys, this.pointerInputEventHandler);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuspendPointerInputElement)) {
            return false;
        }
        SuspendPointerInputElement suspendPointerInputElement = (SuspendPointerInputElement) obj;
        if (!Intrinsics.areEqual(this.key1, suspendPointerInputElement.key1) || !Intrinsics.areEqual(this.key2, suspendPointerInputElement.key2)) {
            return false;
        }
        Object[] objArr = this.keys;
        if (objArr != null) {
            Object[] objArr2 = suspendPointerInputElement.keys;
            if (objArr2 == null || !Arrays.equals(objArr, objArr2)) {
                return false;
            }
        } else if (suspendPointerInputElement.keys != null) {
            return false;
        }
        return this.pointerInputEventHandler == suspendPointerInputElement.pointerInputEventHandler;
    }

    public final int hashCode() {
        Object obj = this.key1;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Object obj2 = this.key2;
        int hashCode2 = (hashCode + (obj2 != null ? obj2.hashCode() : 0)) * 31;
        Object[] objArr = this.keys;
        return this.pointerInputEventHandler.hashCode() + ((hashCode2 + (objArr != null ? Arrays.hashCode(objArr) : 0)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = (SuspendingPointerInputModifierNodeImpl) node;
        Object obj = suspendingPointerInputModifierNodeImpl.key1;
        Object obj2 = this.key1;
        boolean z = !Intrinsics.areEqual(obj, obj2);
        suspendingPointerInputModifierNodeImpl.key1 = obj2;
        Object obj3 = suspendingPointerInputModifierNodeImpl.key2;
        Object obj4 = this.key2;
        if (!Intrinsics.areEqual(obj3, obj4)) {
            z = true;
        }
        suspendingPointerInputModifierNodeImpl.key2 = obj4;
        Object[] objArr = suspendingPointerInputModifierNodeImpl.keys;
        Object[] objArr2 = this.keys;
        if (objArr != null && objArr2 == null) {
            z = true;
        }
        if (objArr == null && objArr2 != null) {
            z = true;
        }
        if (objArr != null && objArr2 != null && !Arrays.equals(objArr2, objArr)) {
            z = true;
        }
        suspendingPointerInputModifierNodeImpl.keys = objArr2;
        Class<?> cls = suspendingPointerInputModifierNodeImpl._pointerInputEventHandler.getClass();
        PointerInputEventHandler pointerInputEventHandler = this.pointerInputEventHandler;
        if (cls == pointerInputEventHandler.getClass() ? z : true) {
            suspendingPointerInputModifierNodeImpl.resetPointerInputHandler();
        }
        suspendingPointerInputModifierNodeImpl._pointerInputEventHandler = pointerInputEventHandler;
    }
}
