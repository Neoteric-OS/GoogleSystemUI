package kotlin.sequences;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SequencesKt___SequencesKt$sortedWith$1 implements Sequence {
    public final /* synthetic */ Comparator $comparator;
    public final /* synthetic */ Sequence $this_sortedWith;

    public SequencesKt___SequencesKt$sortedWith$1(Sequence sequence, Comparator comparator) {
        this.$this_sortedWith = sequence;
        this.$comparator = comparator;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        List mutableList = SequencesKt.toMutableList(this.$this_sortedWith);
        CollectionsKt__MutableCollectionsJVMKt.sortWith(mutableList, this.$comparator);
        return mutableList.iterator();
    }
}
