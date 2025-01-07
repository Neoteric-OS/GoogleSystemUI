package kotlin.sequences;

import java.util.HashSet;
import java.util.Iterator;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DistinctIterator extends AbstractIterator {
    public final Function1 keySelector;
    public final HashSet observed = new HashSet();
    public final Iterator source;

    public DistinctIterator(Iterator it, Function1 function1) {
        this.source = it;
        this.keySelector = function1;
    }

    @Override // kotlin.collections.AbstractIterator
    public final void computeNext() {
        while (this.source.hasNext()) {
            Object next = this.source.next();
            ((SequencesKt___SequencesKt$distinct$1) this.keySelector).getClass();
            if (this.observed.add(next)) {
                setNext(next);
                return;
            }
        }
        done();
    }
}
