package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DistinctSequence implements Sequence {
    public final Function1 keySelector;
    public final FilteringSequence source;

    public DistinctSequence(FilteringSequence filteringSequence, Function1 function1) {
        this.source = filteringSequence;
        this.keySelector = function1;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return new DistinctIterator(new FilteringSequence$iterator$1(this.source), this.keySelector);
    }
}
