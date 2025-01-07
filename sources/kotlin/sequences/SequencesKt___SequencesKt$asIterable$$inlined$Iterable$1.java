package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.DelimitedRangesSequence;
import kotlin.text.DelimitedRangesSequence$iterator$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 implements Iterable, KMappedMarker {
    public final /* synthetic */ DelimitedRangesSequence $this_asIterable$inlined;

    public SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(DelimitedRangesSequence delimitedRangesSequence) {
        this.$this_asIterable$inlined = delimitedRangesSequence;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new DelimitedRangesSequence$iterator$1(this.$this_asIterable$inlined);
    }
}
