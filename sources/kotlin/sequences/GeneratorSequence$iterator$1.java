package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GeneratorSequence$iterator$1 implements Iterator, KMappedMarker {
    public Object nextItem;
    public int nextState = -2;
    public final /* synthetic */ GeneratorSequence this$0;

    public GeneratorSequence$iterator$1(GeneratorSequence generatorSequence) {
        this.this$0 = generatorSequence;
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public final void calcNext$1() {
        Object invoke;
        if (this.nextState == -2) {
            invoke = this.this$0.getInitialValue.invoke();
        } else {
            Function1 function1 = this.this$0.getNextValue;
            Object obj = this.nextItem;
            Intrinsics.checkNotNull(obj);
            invoke = function1.invoke(obj);
        }
        this.nextItem = invoke;
        this.nextState = invoke == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.nextState < 0) {
            calcNext$1();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.nextState < 0) {
            calcNext$1();
        }
        if (this.nextState == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.nextItem;
        this.nextState = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
