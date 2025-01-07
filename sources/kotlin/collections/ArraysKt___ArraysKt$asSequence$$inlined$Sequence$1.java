package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.ArrayIntIterator;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.sequences.Sequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1 implements Sequence {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $this_asSequence$inlined;

    public /* synthetic */ ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1(int i, Object obj) {
        this.$r8$classId = i;
        this.$this_asSequence$inlined = obj;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        switch (this.$r8$classId) {
            case 0:
                return new ArrayIterator((Object[]) this.$this_asSequence$inlined);
            default:
                return new ArrayIntIterator((int[]) this.$this_asSequence$inlined);
        }
    }
}
