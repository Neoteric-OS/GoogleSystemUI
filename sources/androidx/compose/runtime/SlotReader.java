package androidx.compose.runtime;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.runtime.Composer;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SlotReader {
    public boolean closed;
    public int currentEnd;
    public int currentGroup;
    public int currentSlot;
    public int currentSlotEnd;
    public final IntStack currentSlotStack;
    public int emptyCount;
    public final int[] groups;
    public final int groupsSize;
    public boolean hadNext;
    public int parent;
    public final Object[] slots;
    public final int slotsSize;
    public final SlotTable table;

    public SlotReader(SlotTable slotTable) {
        this.table = slotTable;
        this.groups = slotTable.groups;
        int i = slotTable.groupsSize;
        this.groupsSize = i;
        this.slots = slotTable.slots;
        this.slotsSize = slotTable.slotsSize;
        this.currentEnd = i;
        this.parent = -1;
        this.currentSlotStack = new IntStack();
    }

    public final Anchor anchor(int i) {
        ArrayList arrayList = this.table.anchors;
        int search = SlotTableKt.search(arrayList, i, this.groupsSize);
        if (search >= 0) {
            return (Anchor) arrayList.get(search);
        }
        Anchor anchor = new Anchor(i);
        arrayList.add(-(search + 1), anchor);
        return anchor;
    }

    public final Object aux(int[] iArr, int i) {
        int countOneBits;
        if (!SlotTableKt.access$hasAux(iArr, i)) {
            return Composer.Companion.Empty;
        }
        int i2 = i * 5;
        if (i2 >= iArr.length) {
            countOneBits = iArr.length;
        } else {
            countOneBits = SlotTableKt.countOneBits(iArr[i2 + 1] >> 29) + iArr[i2 + 4];
        }
        return this.slots[countOneBits];
    }

    public final void close() {
        this.closed = true;
        SlotTable slotTable = this.table;
        slotTable.getClass();
        if (this.table != slotTable || slotTable.readers <= 0) {
            ComposerKt.composeImmediateRuntimeError("Unexpected reader close()");
        }
        slotTable.readers--;
    }

    public final void endGroup() {
        if (this.emptyCount == 0) {
            if (!(this.currentGroup == this.currentEnd)) {
                ComposerKt.composeImmediateRuntimeError("endGroup() not called at the end of a group");
            }
            int i = this.parent;
            int[] iArr = this.groups;
            int access$parentAnchor = SlotTableKt.access$parentAnchor(iArr, i);
            this.parent = access$parentAnchor;
            int i2 = this.groupsSize;
            this.currentEnd = access$parentAnchor < 0 ? i2 : SlotTableKt.access$groupSize(iArr, access$parentAnchor) + access$parentAnchor;
            int pop = this.currentSlotStack.pop();
            if (pop < 0) {
                this.currentSlot = 0;
                this.currentSlotEnd = 0;
            } else {
                this.currentSlot = pop;
                this.currentSlotEnd = access$parentAnchor >= i2 - 1 ? this.slotsSize : SlotTableKt.access$dataAnchor(iArr, access$parentAnchor + 1);
            }
        }
    }

    public final Object getGroupAux() {
        int i = this.currentGroup;
        if (i < this.currentEnd) {
            return aux(this.groups, i);
        }
        return 0;
    }

    public final int getGroupKey() {
        int i = this.currentGroup;
        if (i >= this.currentEnd) {
            return 0;
        }
        return this.groups[i * 5];
    }

    public final Object groupGet(int i, int i2) {
        int[] iArr = this.groups;
        int access$slotAnchor = SlotTableKt.access$slotAnchor(iArr, i);
        int i3 = i + 1;
        int i4 = access$slotAnchor + i2;
        return i4 < (i3 < this.groupsSize ? iArr[(i3 * 5) + 4] : this.slotsSize) ? this.slots[i4] : Composer.Companion.Empty;
    }

    public final Object next() {
        int i;
        if (this.emptyCount > 0 || (i = this.currentSlot) >= this.currentSlotEnd) {
            this.hadNext = false;
            return Composer.Companion.Empty;
        }
        this.hadNext = true;
        this.currentSlot = i + 1;
        return this.slots[i];
    }

    public final Object node(int i) {
        int[] iArr = this.groups;
        if (!SlotTableKt.access$isNode(iArr, i)) {
            return null;
        }
        if (!SlotTableKt.access$isNode(iArr, i)) {
            return Composer.Companion.Empty;
        }
        return this.slots[iArr[(i * 5) + 4]];
    }

    public final Object objectKey(int[] iArr, int i) {
        if (!SlotTableKt.access$hasObjectKey(iArr, i)) {
            return null;
        }
        int i2 = i * 5;
        return this.slots[SlotTableKt.countOneBits(iArr[i2 + 1] >> 30) + iArr[i2 + 4]];
    }

    public final void reposition(int i) {
        if (!(this.emptyCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot reposition while in an empty region");
        }
        this.currentGroup = i;
        int[] iArr = this.groups;
        int i2 = this.groupsSize;
        int access$parentAnchor = i < i2 ? SlotTableKt.access$parentAnchor(iArr, i) : -1;
        this.parent = access$parentAnchor;
        if (access$parentAnchor < 0) {
            this.currentEnd = i2;
        } else {
            this.currentEnd = SlotTableKt.access$groupSize(iArr, access$parentAnchor) + access$parentAnchor;
        }
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final int skipGroup() {
        if (!(this.emptyCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot skip while in an empty region");
        }
        int i = this.currentGroup;
        int[] iArr = this.groups;
        int access$nodeCount = SlotTableKt.access$isNode(iArr, i) ? 1 : SlotTableKt.access$nodeCount(iArr, this.currentGroup);
        int i2 = this.currentGroup;
        this.currentGroup = SlotTableKt.access$groupSize(iArr, i2) + i2;
        return access$nodeCount;
    }

    public final void skipToGroupEnd() {
        if (!(this.emptyCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot skip the enclosing group while in an empty region");
        }
        this.currentGroup = this.currentEnd;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final void startGroup() {
        if (this.emptyCount <= 0) {
            int i = this.parent;
            int i2 = this.currentGroup;
            int[] iArr = this.groups;
            if (!(SlotTableKt.access$parentAnchor(iArr, i2) == i)) {
                PreconditionsKt.throwIllegalArgumentException("Invalid slot table detected");
            }
            int i3 = this.currentSlot;
            int i4 = this.currentSlotEnd;
            IntStack intStack = this.currentSlotStack;
            if (i3 == 0 && i4 == 0) {
                intStack.push(-1);
            } else {
                intStack.push(i3);
            }
            this.parent = i2;
            this.currentEnd = SlotTableKt.access$groupSize(iArr, i2) + i2;
            int i5 = i2 + 1;
            this.currentGroup = i5;
            this.currentSlot = SlotTableKt.access$slotAnchor(iArr, i2);
            this.currentSlotEnd = i2 >= this.groupsSize - 1 ? this.slotsSize : SlotTableKt.access$dataAnchor(iArr, i5);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SlotReader(current=");
        sb.append(this.currentGroup);
        sb.append(", key=");
        sb.append(getGroupKey());
        sb.append(", parent=");
        sb.append(this.parent);
        sb.append(", end=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.currentEnd, ')');
    }
}
