package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TakeWhileSequence implements Sequence {
    public final Lambda predicate;
    public final SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 sequence;

    /* JADX WARN: Multi-variable type inference failed */
    public TakeWhileSequence(SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 sequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1, Function1 function1) {
        this.sequence = sequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1;
        this.predicate = (Lambda) function1;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return new TakeWhileSequence$iterator$1(this);
    }
}
