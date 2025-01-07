package androidx.compose.runtime;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SourceInformationSlotTableGroup implements Iterable, KMappedMarker {
    public final RelativeGroupPath identityPath;
    public final int parent;
    public final GroupSourceInformation sourceInformation;
    public final SlotTable table;

    public SourceInformationSlotTableGroup(SlotTable slotTable, int i, GroupSourceInformation groupSourceInformation, RelativeGroupPath relativeGroupPath) {
        this.table = slotTable;
        this.parent = i;
        this.identityPath = relativeGroupPath;
        groupSourceInformation.getClass();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new SourceInformationGroupIterator(this.table, this.parent, this.sourceInformation, this.identityPath);
    }
}
