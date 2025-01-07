package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TransformingIndexedSequence implements Sequence {
    public final Sequence sequence;
    public final Lambda transformer;

    /* JADX WARN: Multi-variable type inference failed */
    public TransformingIndexedSequence(Sequence sequence, Function2 function2) {
        this.sequence = sequence;
        this.transformer = (Lambda) function2;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return new TransformingIndexedSequence$iterator$1(this);
    }
}
