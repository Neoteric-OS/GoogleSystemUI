package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SlotTableGroup implements Iterable, KMappedMarker {
    public final int group;
    public final SlotTable table;
    public final int version;

    public SlotTableGroup(SlotTable slotTable, int i, int i2) {
        this.table = slotTable;
        this.group = i;
        this.version = i2;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int i;
        ArrayList arrayList;
        int search;
        if (this.table.version != this.version) {
            SlotTableKt.throwConcurrentModificationException();
        }
        SlotTable slotTable = this.table;
        int i2 = this.group;
        HashMap hashMap = slotTable.sourceInformationMap;
        Anchor anchor = null;
        if (hashMap != null) {
            if (slotTable.writer) {
                ComposerKt.composeImmediateRuntimeError("use active SlotWriter to crate an anchor for location instead");
            }
            if (i2 >= 0 && i2 < (i = slotTable.groupsSize) && (search = SlotTableKt.search((arrayList = slotTable.anchors), i2, i)) >= 0) {
                anchor = (Anchor) arrayList.get(search);
            }
            if (anchor != null) {
            }
        }
        SlotTable slotTable2 = this.table;
        int i3 = this.group;
        return new GroupIterator(slotTable2, i3 + 1, slotTable2.groups[(i3 * 5) + 3] + i3);
    }
}
