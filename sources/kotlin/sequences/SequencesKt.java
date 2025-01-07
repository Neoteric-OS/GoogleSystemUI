package kotlin.sequences;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__AppendableKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SequencesKt extends SequencesKt__SequencesJVMKt {
    public static Sequence asSequence(final Iterator it) {
        return new ConstrainedOnceSequence(new Sequence() { // from class: kotlin.sequences.SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public final Iterator iterator() {
                return it;
            }
        });
    }

    public static DistinctSequence distinct(FilteringSequence filteringSequence) {
        return new DistinctSequence(filteringSequence, SequencesKt___SequencesKt$distinct$1.INSTANCE);
    }

    public static FilteringSequence filter(Sequence sequence, Function1 function1) {
        return new FilteringSequence(sequence, true, function1);
    }

    public static FilteringSequence filterNotNull(CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1 collectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1) {
        return new FilteringSequence(collectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1, false, SequencesKt___SequencesKt$filterNotNull$1.INSTANCE);
    }

    public static Object firstOrNull(FilteringSequence filteringSequence) {
        FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(filteringSequence);
        if (filteringSequence$iterator$1.hasNext()) {
            return filteringSequence$iterator$1.next();
        }
        return null;
    }

    public static FlatteningSequence flatMap(Sequence sequence, Function1 function1) {
        return new FlatteningSequence(sequence, function1, SequencesKt___SequencesKt$flatMap$2.INSTANCE);
    }

    public static SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 flatMapIndexedIterable(SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 sequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1, Function2 function2) {
        return new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(new SequencesKt__SequencesKt$flatMapIndexed$1(sequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1, function2, SequencesKt___SequencesKt$flatMapIndexed$1.INSTANCE, null));
    }

    public static final FlatteningSequence flatten(Sequence sequence) {
        SequencesKt__SequencesKt$flatten$1 sequencesKt__SequencesKt$flatten$1 = new Function1() { // from class: kotlin.sequences.SequencesKt__SequencesKt$flatten$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ((Sequence) obj).iterator();
            }
        };
        if (!(sequence instanceof TransformingSequence)) {
            return new FlatteningSequence(sequence, new Function1() { // from class: kotlin.sequences.SequencesKt__SequencesKt$flatten$3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return obj;
                }
            }, sequencesKt__SequencesKt$flatten$1);
        }
        TransformingSequence transformingSequence = (TransformingSequence) sequence;
        return new FlatteningSequence(transformingSequence.sequence, transformingSequence.transformer, sequencesKt__SequencesKt$flatten$1);
    }

    public static Sequence generateSequence(final Function0 function0) {
        return new ConstrainedOnceSequence(new GeneratorSequence(function0, new Function1() { // from class: kotlin.sequences.SequencesKt__SequencesKt$generateSequence$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Function0.this.invoke();
            }
        }));
    }

    public static String joinToString$default(Sequence sequence, CharSequence charSequence, CharSequence charSequence2, int i) {
        if ((i & 2) != 0) {
            charSequence2 = "";
        }
        String str = (i & 4) == 0 ? ")" : "";
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence2);
        int i2 = 0;
        for (Object obj : sequence) {
            i2++;
            if (i2 > 1) {
                sb.append(charSequence);
            }
            StringsKt__AppendableKt.appendElement(sb, obj, null);
        }
        sb.append((CharSequence) str);
        return sb.toString();
    }

    public static FilteringSequence mapNotNull(Sequence sequence, Function1 function1) {
        return new FilteringSequence(new TransformingSequence(sequence, function1), false, SequencesKt___SequencesKt$filterNotNull$1.INSTANCE);
    }

    public static Sequence sequenceOf(Object... objArr) {
        return objArr.length == 0 ? EmptySequence.INSTANCE : ArraysKt.asSequence(objArr);
    }

    public static Sequence take(Sequence sequence, int i) {
        if (i >= 0) {
            return i == 0 ? EmptySequence.INSTANCE : sequence instanceof DropTakeSequence ? ((DropTakeSequence) sequence).take(i) : new TakeSequence(sequence, i);
        }
        throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Requested element count ", " is less than zero.", i).toString());
    }

    public static List toList(Sequence sequence) {
        Iterator it = sequence.iterator();
        if (!it.hasNext()) {
            return EmptyList.INSTANCE;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return Collections.singletonList(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static List toMutableList(Sequence sequence) {
        ArrayList arrayList = new ArrayList();
        Iterator it = sequence.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static Set toSet(Sequence sequence) {
        Iterator it = sequence.iterator();
        if (!it.hasNext()) {
            return EmptySet.INSTANCE;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return Collections.singleton(next);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(next);
        while (it.hasNext()) {
            linkedHashSet.add(it.next());
        }
        return linkedHashSet;
    }

    public static MergingSequence zip(Sequence sequence, Sequence sequence2) {
        return new MergingSequence(sequence, sequence2, new Function2() { // from class: kotlin.sequences.SequencesKt___SequencesKt$zip$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return new Pair(obj, obj2);
            }
        });
    }

    public static Sequence generateSequence(final Object obj, Function1 function1) {
        if (obj == null) {
            return EmptySequence.INSTANCE;
        }
        return new GeneratorSequence(new Function0() { // from class: kotlin.sequences.SequencesKt__SequencesKt$generateSequence$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return obj;
            }
        }, function1);
    }
}
