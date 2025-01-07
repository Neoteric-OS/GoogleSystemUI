package androidx.room;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.room.AmbiguousColumnResolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.collections.builders.ListBuilder;
import kotlin.collections.builders.SetBuilder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AmbiguousColumnResolver {
    public static final AmbiguousColumnResolver INSTANCE = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Match {
        public final List resultIndices;
        public final IntRange resultRange;

        public Match(IntRange intRange, List list) {
            this.resultRange = intRange;
            this.resultIndices = list;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ResultColumn {
        public final int index;
        public final String name;

        public ResultColumn(String str, int i) {
            this.name = str;
            this.index = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ResultColumn)) {
                return false;
            }
            ResultColumn resultColumn = (ResultColumn) obj;
            return Intrinsics.areEqual(this.name, resultColumn.name) && this.index == resultColumn.index;
        }

        public final int hashCode() {
            return Integer.hashCode(this.index) + (this.name.hashCode() * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("ResultColumn(name=");
            sb.append(this.name);
            sb.append(", index=");
            return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.index, ')');
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Solution implements Comparable {
        public static final Solution NO_SOLUTION = new Solution(Integer.MAX_VALUE, Integer.MAX_VALUE, EmptyList.INSTANCE);
        public final int coverageOffset;
        public final List matches;
        public final int overlaps;

        public Solution(int i, int i2, List list) {
            this.matches = list;
            this.coverageOffset = i;
            this.overlaps = i2;
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            Solution solution = (Solution) obj;
            int compare = Intrinsics.compare(this.overlaps, solution.overlaps);
            return compare != 0 ? compare : Intrinsics.compare(this.coverageOffset, solution.coverageOffset);
        }
    }

    public static void dfs(int i, List list, List list2, Function1 function1) {
        ArrayList arrayList = (ArrayList) list;
        if (i == arrayList.size()) {
            function1.invoke(CollectionsKt.toList(list2));
            return;
        }
        Iterator it = ((Iterable) arrayList.get(i)).iterator();
        while (it.hasNext()) {
            list2.add(it.next());
            dfs(i + 1, list, list2, function1);
            list2.remove(CollectionsKt__CollectionsKt.getLastIndex(list2));
        }
    }

    public static final int[][] resolve(List list, String[][] strArr) {
        int i = 0;
        String[] strArr2 = (String[]) list.toArray(new String[0]);
        int length = strArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            String str = strArr2[i2];
            if (str.charAt(0) == '`' && str.charAt(str.length() - 1) == '`') {
                str = str.substring(1, str.length() - 1);
            }
            strArr2[i2] = str.toLowerCase(Locale.ROOT);
        }
        int length2 = strArr.length;
        for (int i3 = 0; i3 < length2; i3++) {
            int length3 = strArr[i3].length;
            for (int i4 = 0; i4 < length3; i4++) {
                String[] strArr3 = strArr[i3];
                strArr3[i4] = strArr3[i4].toLowerCase(Locale.ROOT);
            }
        }
        SetBuilder setBuilder = new SetBuilder();
        for (String[] strArr4 : strArr) {
            setBuilder.addAll(Arrays.asList(strArr4));
        }
        SetBuilder build = setBuilder.build();
        ListBuilder listBuilder = new ListBuilder();
        int length4 = strArr2.length;
        int i5 = 0;
        int i6 = 0;
        while (i5 < length4) {
            String str2 = strArr2[i5];
            int i7 = i6 + 1;
            if (build.contains(str2)) {
                listBuilder.add(new ResultColumn(str2, i6));
            }
            i5++;
            i6 = i7;
        }
        ListBuilder build2 = listBuilder.build();
        int length5 = strArr.length;
        final ArrayList arrayList = new ArrayList(length5);
        for (int i8 = 0; i8 < length5; i8++) {
            arrayList.add(new ArrayList());
        }
        int length6 = strArr.length;
        int i9 = 0;
        final int i10 = 0;
        while (i9 < length6) {
            final String[] strArr5 = strArr[i9];
            int i11 = i10 + 1;
            Function3 function3 = new Function3() { // from class: androidx.room.AmbiguousColumnResolver$resolve$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Object obj4;
                    int intValue = ((Number) obj).intValue();
                    int intValue2 = ((Number) obj2).intValue();
                    List list2 = (List) obj3;
                    String[] strArr6 = strArr5;
                    ArrayList arrayList2 = new ArrayList(strArr6.length);
                    int length7 = strArr6.length;
                    int i12 = 0;
                    while (true) {
                        if (i12 >= length7) {
                            ((List) arrayList.get(i10)).add(new AmbiguousColumnResolver.Match(new IntRange(intValue, intValue2 - 1, 1), arrayList2));
                            break;
                        }
                        String str3 = strArr6[i12];
                        Iterator it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj4 = null;
                                break;
                            }
                            obj4 = it.next();
                            if (Intrinsics.areEqual(str3, ((AmbiguousColumnResolver.ResultColumn) obj4).name)) {
                                break;
                            }
                        }
                        AmbiguousColumnResolver.ResultColumn resultColumn = (AmbiguousColumnResolver.ResultColumn) obj4;
                        if (resultColumn == null) {
                            break;
                        }
                        arrayList2.add(Integer.valueOf(resultColumn.index));
                        i12++;
                    }
                    return Unit.INSTANCE;
                }
            };
            int length7 = strArr5.length;
            int i12 = i;
            int i13 = i12;
            while (i12 < length7) {
                i13 += strArr5[i12].hashCode();
                i12++;
            }
            int length8 = strArr5.length;
            ListIterator listIterator = ((ListBuilder) build2.subList(i, length8)).listIterator(i);
            int i14 = i;
            while (true) {
                ListBuilder.Itr itr = (ListBuilder.Itr) listIterator;
                if (!itr.hasNext()) {
                    break;
                }
                i14 += ((ResultColumn) itr.next()).name.hashCode();
            }
            int i15 = i;
            while (true) {
                if (i13 == i14) {
                    function3.invoke(Integer.valueOf(i15), Integer.valueOf(length8), build2.subList(i15, length8));
                }
                int i16 = i15 + 1;
                int i17 = length8 + 1;
                if (i17 > build2.getSize()) {
                    break;
                }
                i14 = (i14 - ((ResultColumn) build2.get(i15)).name.hashCode()) + ((ResultColumn) build2.get(length8)).name.hashCode();
                i15 = i16;
                length8 = i17;
            }
            if (((List) arrayList.get(i10)).isEmpty()) {
                ArrayList arrayList2 = new ArrayList(strArr5.length);
                for (String str3 : strArr5) {
                    ListBuilder listBuilder2 = new ListBuilder();
                    ListIterator listIterator2 = build2.listIterator(0);
                    while (true) {
                        ListBuilder.Itr itr2 = (ListBuilder.Itr) listIterator2;
                        if (!itr2.hasNext()) {
                            break;
                        }
                        ResultColumn resultColumn = (ResultColumn) itr2.next();
                        if (Intrinsics.areEqual(str3, resultColumn.name)) {
                            listBuilder2.add(Integer.valueOf(resultColumn.index));
                        }
                    }
                    ListBuilder build3 = listBuilder2.build();
                    if (build3.isEmpty()) {
                        throw new IllegalStateException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Column ", str3, " not found in result").toString());
                    }
                    arrayList2.add(build3);
                }
                dfs(0, arrayList2, new ArrayList(), new Function1() { // from class: androidx.room.AmbiguousColumnResolver$resolve$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        List list2 = (List) obj;
                        Iterator it = list2.iterator();
                        if (!it.hasNext()) {
                            throw new NoSuchElementException();
                        }
                        int intValue = ((Number) it.next()).intValue();
                        while (it.hasNext()) {
                            int intValue2 = ((Number) it.next()).intValue();
                            if (intValue > intValue2) {
                                intValue = intValue2;
                            }
                        }
                        Iterator it2 = list2.iterator();
                        if (!it2.hasNext()) {
                            throw new NoSuchElementException();
                        }
                        int intValue3 = ((Number) it2.next()).intValue();
                        while (it2.hasNext()) {
                            int intValue4 = ((Number) it2.next()).intValue();
                            if (intValue3 < intValue4) {
                                intValue3 = intValue4;
                            }
                        }
                        ((List) arrayList.get(i10)).add(new AmbiguousColumnResolver.Match(new IntRange(intValue, intValue3, 1), list2));
                        return Unit.INSTANCE;
                    }
                });
            }
            i9++;
            i10 = i11;
            i = 0;
        }
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((List) it.next()).isEmpty()) {
                    throw new IllegalStateException("Failed to find matches for all mappings");
                }
            }
        }
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = Solution.NO_SOLUTION;
        dfs(0, arrayList, new ArrayList(), new Function1() { // from class: androidx.room.AmbiguousColumnResolver$resolve$4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List<AmbiguousColumnResolver.Match> list2 = (List) obj;
                AmbiguousColumnResolver.Solution solution = AmbiguousColumnResolver.Solution.NO_SOLUTION;
                int i18 = 0;
                int i19 = 0;
                for (AmbiguousColumnResolver.Match match : list2) {
                    IntRange intRange = match.resultRange;
                    i19 += ((intRange.last - intRange.first) + 1) - match.resultIndices.size();
                }
                Iterator it2 = list2.iterator();
                if (!it2.hasNext()) {
                    throw new NoSuchElementException();
                }
                int i20 = ((AmbiguousColumnResolver.Match) it2.next()).resultRange.first;
                while (it2.hasNext()) {
                    int i21 = ((AmbiguousColumnResolver.Match) it2.next()).resultRange.first;
                    if (i20 > i21) {
                        i20 = i21;
                    }
                }
                Iterator it3 = list2.iterator();
                if (!it3.hasNext()) {
                    throw new NoSuchElementException();
                }
                int i22 = ((AmbiguousColumnResolver.Match) it3.next()).resultRange.last;
                while (it3.hasNext()) {
                    int i23 = ((AmbiguousColumnResolver.Match) it3.next()).resultRange.last;
                    if (i22 < i23) {
                        i22 = i23;
                    }
                }
                Iterable intRange2 = new IntRange(i20, i22, 1);
                if (!(intRange2 instanceof Collection) || !((Collection) intRange2).isEmpty()) {
                    IntProgressionIterator it4 = intRange2.iterator();
                    int i24 = 0;
                    while (it4.hasNext) {
                        int nextInt = it4.nextInt();
                        Iterator it5 = list2.iterator();
                        int i25 = 0;
                        while (true) {
                            if (it5.hasNext()) {
                                IntRange intRange3 = ((AmbiguousColumnResolver.Match) it5.next()).resultRange;
                                if (intRange3.first <= nextInt && nextInt <= intRange3.last) {
                                    i25++;
                                }
                                if (i25 > 1) {
                                    i24++;
                                    if (i24 < 0) {
                                        CollectionsKt__CollectionsKt.throwCountOverflow();
                                        throw null;
                                    }
                                }
                            }
                        }
                    }
                    i18 = i24;
                }
                AmbiguousColumnResolver.Solution solution2 = new AmbiguousColumnResolver.Solution(i19, i18, list2);
                AmbiguousColumnResolver.Solution solution3 = (AmbiguousColumnResolver.Solution) Ref$ObjectRef.this.element;
                int compare = Intrinsics.compare(i18, solution3.overlaps);
                if (compare == 0) {
                    compare = Intrinsics.compare(i19, solution3.coverageOffset);
                }
                if (compare < 0) {
                    Ref$ObjectRef.this.element = solution2;
                }
                return Unit.INSTANCE;
            }
        });
        List list2 = ((Solution) ref$ObjectRef.element).matches;
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(CollectionsKt.toIntArray(((Match) it2.next()).resultIndices));
        }
        return (int[][]) arrayList3.toArray(new int[0][]);
    }
}
