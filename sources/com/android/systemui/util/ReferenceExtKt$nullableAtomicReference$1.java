package com.android.systemui.util;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReferenceExtKt$nullableAtomicReference$1 {
    public final AtomicReference t = new AtomicReference(null);

    public final void setValue(Object obj, KProperty kProperty, Object obj2) {
        this.t.set(obj2);
    }
}
