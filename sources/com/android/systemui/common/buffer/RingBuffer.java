package com.android.systemui.common.buffer;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RingBuffer implements Iterable, KMappedMarker {
    public final List buffer;
    public final Lambda factory;
    public final int maxSize;
    public long omega;

    /* JADX WARN: Multi-variable type inference failed */
    public RingBuffer(int i, Function0 function0) {
        this.maxSize = i;
        this.factory = (Lambda) function0;
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(null);
        }
        this.buffer = arrayList;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public final Object advance() {
        long j = this.omega;
        int i = (int) (j % this.maxSize);
        this.omega = j + 1;
        Object obj = ((ArrayList) this.buffer).get(i);
        if (obj != null) {
            return obj;
        }
        Object invoke = this.factory.invoke();
        this.buffer.set(i, invoke);
        return invoke;
    }

    public final Object get(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException(GenericDocument$$ExternalSyntheticOutline0.m("Index ", " is out of bounds", i));
        }
        Object obj = ((ArrayList) this.buffer).get((int) ((Math.max(this.omega, this.maxSize) + i) % this.maxSize));
        Intrinsics.checkNotNull(obj);
        return obj;
    }

    public final int getSize() {
        long j = this.omega;
        int i = this.maxSize;
        return j < ((long) i) ? (int) j : i;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new RingBuffer$iterator$1(this);
    }
}
