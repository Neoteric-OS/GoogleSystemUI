package com.android.systemui.qs.panels.ui.model;

import com.android.systemui.qs.panels.shared.model.SizedTile;
import com.android.systemui.qs.panels.shared.model.TileRowKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TileGridCellKt {
    public static final List toGridCells(final int i, final int i2, List list) {
        return SequencesKt.toList(SequencesKt.flatMapIndexedIterable(TileRowKt.splitInRowsSequence(i, list), new Function2() { // from class: com.android.systemui.qs.panels.ui.model.TileGridCellKt$toGridCells$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                List list2 = (List) obj2;
                int intValue = ((Number) obj).intValue() + i2;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    arrayList.add(new TileGridCell((SizedTile) it.next(), intValue));
                }
                int i3 = i;
                Iterator it2 = list2.iterator();
                int i4 = 0;
                while (it2.hasNext()) {
                    i4 += ((SizedTile) it2.next()).getWidth();
                }
                int i5 = i3 - i4;
                ArrayList arrayList2 = new ArrayList(arrayList);
                for (int i6 = 0; i6 < i5; i6++) {
                    arrayList2.add(new SpacerGridCell(intValue));
                }
                return arrayList2;
            }
        }));
    }
}
