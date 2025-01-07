package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MergingSequence implements Sequence {
    public final Sequence sequence1;
    public final Sequence sequence2;
    public final Function2 transform;

    public MergingSequence(Sequence sequence, Sequence sequence2, Function2 function2) {
        this.sequence1 = sequence;
        this.sequence2 = sequence2;
        this.transform = function2;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return new MergingSequence$iterator$1(this);
    }
}
