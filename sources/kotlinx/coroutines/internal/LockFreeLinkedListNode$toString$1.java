package kotlinx.coroutines.internal;

import kotlin.jvm.internal.PropertyReference0Impl;
import kotlinx.coroutines.DebugStringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class LockFreeLinkedListNode$toString$1 extends PropertyReference0Impl {
    @Override // kotlin.reflect.KProperty0
    public final Object get() {
        return DebugStringsKt.getClassSimpleName(this.receiver);
    }
}
