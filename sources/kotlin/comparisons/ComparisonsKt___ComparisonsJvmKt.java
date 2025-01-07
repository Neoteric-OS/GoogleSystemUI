package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ComparisonsKt___ComparisonsJvmKt {
    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0] */
    public static ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0 compareBy(final Function1... function1Arr) {
        if (function1Arr.length > 0) {
            return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    for (Function1 function1 : function1Arr) {
                        int compareValues = ComparisonsKt___ComparisonsJvmKt.compareValues((Comparable) function1.invoke(obj), (Comparable) function1.invoke(obj2));
                        if (compareValues != 0) {
                            return compareValues;
                        }
                    }
                    return 0;
                }
            };
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public static int compareValues(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    public static int maxOf(int[] iArr, int i) {
        for (int i2 : iArr) {
            i = Math.max(i, i2);
        }
        return i;
    }
}
