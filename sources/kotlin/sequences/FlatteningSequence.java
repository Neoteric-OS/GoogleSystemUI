package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlatteningSequence implements Sequence {
    public final Function1 iterator;
    public final Sequence sequence;
    public final Function1 transformer;

    public FlatteningSequence(Sequence sequence, Function1 function1, Function1 function12) {
        this.sequence = sequence;
        this.transformer = function1;
        this.iterator = function12;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return new FlatteningSequence$iterator$1(this);
    }
}
