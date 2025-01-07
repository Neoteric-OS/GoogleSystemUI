package androidx.compose.runtime;

import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.Composer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SlotWriter {
    public ArrayList anchors;
    public MutableIntObjectMap calledByMap;
    public boolean closed;
    public int currentGroup;
    public int currentGroupEnd;
    public int currentSlot;
    public int currentSlotEnd;
    public MutableIntObjectMap deferredSlotWrites;
    public final IntStack endStack;
    public int groupGapLen;
    public int groupGapStart;
    public int[] groups;
    public int insertCount;
    public int nodeCount;
    public final IntStack nodeCountStack;
    public int parent;
    public PrioritySet pendingRecalculateMarks;
    public Object[] slots;
    public int slotsGapLen;
    public int slotsGapOwner;
    public int slotsGapStart;
    public HashMap sourceInformationMap;
    public final IntStack startStack;
    public final SlotTable table;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        /* JADX WARN: Multi-variable type inference failed */
        public static List moveGroup(SlotWriter slotWriter, int i, SlotWriter slotWriter2, boolean z, boolean z2, boolean z3) {
            EmptyList emptyList;
            boolean removeGroups;
            int i2;
            int i3;
            int i4;
            int groupSize = slotWriter.groupSize(i);
            int i5 = i + groupSize;
            int dataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i));
            int dataIndex2 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i5));
            int i6 = dataIndex2 - dataIndex;
            boolean z4 = i >= 0 && (slotWriter.groups[(slotWriter.groupIndexToAddress(i) * 5) + 1] & 201326592) != 0;
            slotWriter2.insertGroups(groupSize);
            slotWriter2.insertSlots(i6, slotWriter2.currentGroup);
            if (slotWriter.groupGapStart < i5) {
                slotWriter.moveGroupGapTo(i5);
            }
            if (slotWriter.slotsGapStart < dataIndex2) {
                slotWriter.moveSlotGapTo(dataIndex2, i5);
            }
            int[] iArr = slotWriter2.groups;
            int i7 = slotWriter2.currentGroup;
            int i8 = i7 * 5;
            ArraysKt.copyInto(i8, i * 5, i5 * 5, slotWriter.groups, iArr);
            Object[] objArr = slotWriter2.slots;
            int i9 = slotWriter2.currentSlot;
            ArraysKt.copyInto(i9, dataIndex, dataIndex2, slotWriter.slots, objArr);
            int i10 = slotWriter2.parent;
            iArr[i8 + 2] = i10;
            int i11 = i7 - i;
            int i12 = i7 + groupSize;
            int dataIndex3 = i9 - slotWriter2.dataIndex(iArr, i7);
            int i13 = slotWriter2.slotsGapOwner;
            int i14 = slotWriter2.slotsGapLen;
            int length = objArr.length;
            boolean z5 = z4;
            int i15 = i13;
            int i16 = i7;
            while (i16 < i12) {
                if (i16 != i7) {
                    int i17 = (i16 * 5) + 2;
                    iArr[i17] = iArr[i17] + i11;
                }
                int i18 = i12;
                int dataIndex4 = slotWriter2.dataIndex(iArr, i16) + dataIndex3;
                if (i15 < i16) {
                    i3 = i7;
                    i4 = 0;
                } else {
                    i3 = i7;
                    i4 = slotWriter2.slotsGapStart;
                }
                iArr[(i16 * 5) + 4] = SlotWriter.dataIndexToDataAnchor(dataIndex4, i4, i14, length);
                if (i16 == i15) {
                    i15++;
                }
                i16++;
                i7 = i3;
                i12 = i18;
            }
            int i19 = i7;
            int i20 = i12;
            slotWriter2.slotsGapOwner = i15;
            int access$locationOf = SlotTableKt.access$locationOf(slotWriter.anchors, i, slotWriter.getSize$runtime_release());
            int access$locationOf2 = SlotTableKt.access$locationOf(slotWriter.anchors, i5, slotWriter.getSize$runtime_release());
            if (access$locationOf < access$locationOf2) {
                ArrayList arrayList = slotWriter.anchors;
                ArrayList arrayList2 = new ArrayList(access$locationOf2 - access$locationOf);
                for (int i21 = access$locationOf; i21 < access$locationOf2; i21++) {
                    Anchor anchor = (Anchor) arrayList.get(i21);
                    anchor.location += i11;
                    arrayList2.add(anchor);
                }
                slotWriter2.anchors.addAll(SlotTableKt.access$locationOf(slotWriter2.anchors, slotWriter2.currentGroup, slotWriter2.getSize$runtime_release()), arrayList2);
                arrayList.subList(access$locationOf, access$locationOf2).clear();
                emptyList = arrayList2;
            } else {
                emptyList = EmptyList.INSTANCE;
            }
            if (!emptyList.isEmpty()) {
                HashMap hashMap = slotWriter.sourceInformationMap;
                HashMap hashMap2 = slotWriter2.sourceInformationMap;
                if (hashMap != null && hashMap2 != null) {
                    int size = emptyList.size();
                    for (int i22 = 0; i22 < size; i22++) {
                    }
                }
            }
            int i23 = slotWriter2.parent;
            slotWriter2.sourceInformationOf(i10);
            int parent = slotWriter.parent(slotWriter.groups, i);
            if (!z3) {
                i2 = 1;
                removeGroups = false;
            } else if (z) {
                boolean z6 = parent >= 0;
                if (z6) {
                    slotWriter.startGroup();
                    slotWriter.advanceBy(parent - slotWriter.currentGroup);
                    slotWriter.startGroup();
                }
                slotWriter.advanceBy(i - slotWriter.currentGroup);
                boolean removeGroup = slotWriter.removeGroup();
                if (z6) {
                    slotWriter.skipToGroupEnd();
                    slotWriter.endGroup();
                    slotWriter.skipToGroupEnd();
                    slotWriter.endGroup();
                }
                removeGroups = removeGroup;
                i2 = 1;
            } else {
                removeGroups = slotWriter.removeGroups(i, groupSize);
                i2 = 1;
                slotWriter.removeSlots(dataIndex, i6, i - 1);
            }
            if (removeGroups) {
                ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
            }
            slotWriter2.nodeCount += SlotTableKt.access$isNode(iArr, i19) ? i2 : SlotTableKt.access$nodeCount(iArr, i19);
            if (z2) {
                slotWriter2.currentGroup = i20;
                slotWriter2.currentSlot = i9 + i6;
            }
            if (z5) {
                slotWriter2.updateContainsMark(i10);
            }
            return emptyList;
        }
    }

    public SlotWriter(SlotTable slotTable) {
        this.table = slotTable;
        int[] iArr = slotTable.groups;
        this.groups = iArr;
        Object[] objArr = slotTable.slots;
        this.slots = objArr;
        this.anchors = slotTable.anchors;
        this.sourceInformationMap = slotTable.sourceInformationMap;
        this.calledByMap = slotTable.calledByMap;
        int i = slotTable.groupsSize;
        this.groupGapStart = i;
        this.groupGapLen = (iArr.length / 5) - i;
        int i2 = slotTable.slotsSize;
        this.slotsGapStart = i2;
        this.slotsGapLen = objArr.length - i2;
        this.slotsGapOwner = i;
        this.startStack = new IntStack();
        this.endStack = new IntStack();
        this.nodeCountStack = new IntStack();
        this.currentGroupEnd = i;
        this.parent = -1;
    }

    public static int dataIndexToDataAnchor(int i, int i2, int i3, int i4) {
        return i > i2 ? -(((i4 - i3) - i) + 1) : i;
    }

    public static void markGroup$default(SlotWriter slotWriter) {
        int i = slotWriter.parent;
        int groupIndexToAddress = slotWriter.groupIndexToAddress(i);
        int[] iArr = slotWriter.groups;
        int i2 = (groupIndexToAddress * 5) + 1;
        int i3 = iArr[i2];
        if ((i3 & 134217728) != 0) {
            return;
        }
        iArr[i2] = i3 | 134217728;
        if (SlotTableKt.access$containsMark(iArr, groupIndexToAddress)) {
            return;
        }
        slotWriter.updateContainsMark(slotWriter.parent(slotWriter.groups, i));
    }

    public final void advanceBy(int i) {
        boolean z = false;
        if (!(i >= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek backwards");
        }
        if (!(this.insertCount <= 0)) {
            PreconditionsKt.throwIllegalStateException("Cannot call seek() while inserting");
        }
        if (i == 0) {
            return;
        }
        int i2 = this.currentGroup + i;
        if (i2 >= this.parent && i2 <= this.currentGroupEnd) {
            z = true;
        }
        if (!z) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek outside the current group (" + this.parent + '-' + this.currentGroupEnd + ')');
        }
        this.currentGroup = i2;
        int dataIndex = dataIndex(this.groups, groupIndexToAddress(i2));
        this.currentSlot = dataIndex;
        this.currentSlotEnd = dataIndex;
    }

    public final Anchor anchor(int i) {
        ArrayList arrayList = this.anchors;
        int search = SlotTableKt.search(arrayList, i, getSize$runtime_release());
        if (search >= 0) {
            return (Anchor) arrayList.get(search);
        }
        if (i > this.groupGapStart) {
            i = -(getSize$runtime_release() - i);
        }
        Anchor anchor = new Anchor(i);
        arrayList.add(-(search + 1), anchor);
        return anchor;
    }

    public final int anchorIndex(Anchor anchor) {
        int i = anchor.location;
        return i < 0 ? i + getSize$runtime_release() : i;
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
        }
    }

    public final void close(boolean z) {
        this.closed = true;
        if (z && this.startStack.tos == 0) {
            moveGroupGapTo(getSize$runtime_release());
            moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
            int i = this.slotsGapStart;
            Arrays.fill(this.slots, i, this.slotsGapLen + i, (Object) null);
            recalculateMarks();
        }
        int[] iArr = this.groups;
        int i2 = this.groupGapStart;
        Object[] objArr = this.slots;
        int i3 = this.slotsGapStart;
        ArrayList arrayList = this.anchors;
        HashMap hashMap = this.sourceInformationMap;
        MutableIntObjectMap mutableIntObjectMap = this.calledByMap;
        SlotTable slotTable = this.table;
        slotTable.getClass();
        if (!slotTable.writer) {
            PreconditionsKt.throwIllegalArgumentException("Unexpected writer close()");
        }
        slotTable.writer = false;
        slotTable.groups = iArr;
        slotTable.groupsSize = i2;
        slotTable.slots = objArr;
        slotTable.slotsSize = i3;
        slotTable.anchors = arrayList;
        slotTable.sourceInformationMap = hashMap;
        slotTable.calledByMap = mutableIntObjectMap;
    }

    public final int dataIndex(int[] iArr, int i) {
        if (i >= getCapacity()) {
            return this.slots.length - this.slotsGapLen;
        }
        int access$dataAnchor = SlotTableKt.access$dataAnchor(iArr, i);
        return access$dataAnchor < 0 ? (this.slots.length - this.slotsGapLen) + access$dataAnchor + 1 : access$dataAnchor;
    }

    public final int dataIndexToDataAddress(int i) {
        return i < this.slotsGapStart ? i : i + this.slotsGapLen;
    }

    public final void endGroup() {
        MutableObjectList mutableObjectList;
        boolean z = this.insertCount > 0;
        int i = this.currentGroup;
        int i2 = this.currentGroupEnd;
        int i3 = this.parent;
        int groupIndexToAddress = groupIndexToAddress(i3);
        int i4 = this.nodeCount;
        int i5 = i - i3;
        boolean access$isNode = SlotTableKt.access$isNode(this.groups, groupIndexToAddress);
        IntStack intStack = this.nodeCountStack;
        if (z) {
            MutableIntObjectMap mutableIntObjectMap = this.deferredSlotWrites;
            if (mutableIntObjectMap != null && (mutableObjectList = (MutableObjectList) mutableIntObjectMap.get(i3)) != null) {
                Object[] objArr = mutableObjectList.content;
                int i6 = mutableObjectList._size;
                for (int i7 = 0; i7 < i6; i7++) {
                    rawUpdate(objArr[i7]);
                }
            }
            SlotTableKt.access$updateGroupSize(groupIndexToAddress, i5, this.groups);
            SlotTableKt.access$updateNodeCount(groupIndexToAddress, i4, this.groups);
            int pop = intStack.pop();
            if (access$isNode) {
                i4 = 1;
            }
            this.nodeCount = pop + i4;
            int parent = parent(this.groups, i3);
            this.parent = parent;
            int size$runtime_release = parent < 0 ? getSize$runtime_release() : groupIndexToAddress(parent + 1);
            int dataIndex = size$runtime_release >= 0 ? dataIndex(this.groups, size$runtime_release) : 0;
            this.currentSlot = dataIndex;
            this.currentSlotEnd = dataIndex;
            return;
        }
        if (i != i2) {
            ComposerKt.composeImmediateRuntimeError("Expected to be at the end of a group");
        }
        int[] iArr = this.groups;
        int i8 = iArr[(groupIndexToAddress * 5) + 3];
        int access$nodeCount = SlotTableKt.access$nodeCount(iArr, groupIndexToAddress);
        SlotTableKt.access$updateGroupSize(groupIndexToAddress, i5, this.groups);
        SlotTableKt.access$updateNodeCount(groupIndexToAddress, i4, this.groups);
        int pop2 = this.startStack.pop();
        this.currentGroupEnd = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.parent = pop2;
        int parent2 = parent(this.groups, i3);
        int pop3 = intStack.pop();
        this.nodeCount = pop3;
        if (parent2 == pop2) {
            this.nodeCount = pop3 + (access$isNode ? 0 : i4 - access$nodeCount);
            return;
        }
        int i9 = i5 - i8;
        int i10 = access$isNode ? 0 : i4 - access$nodeCount;
        if (i9 != 0 || i10 != 0) {
            while (parent2 != 0 && parent2 != pop2 && (i10 != 0 || i9 != 0)) {
                int groupIndexToAddress2 = groupIndexToAddress(parent2);
                if (i9 != 0) {
                    int[] iArr2 = this.groups;
                    SlotTableKt.access$updateGroupSize(groupIndexToAddress2, iArr2[(groupIndexToAddress2 * 5) + 3] + i9, iArr2);
                }
                if (i10 != 0) {
                    int[] iArr3 = this.groups;
                    SlotTableKt.access$updateNodeCount(groupIndexToAddress2, SlotTableKt.access$nodeCount(iArr3, groupIndexToAddress2) + i10, iArr3);
                }
                if (SlotTableKt.access$isNode(this.groups, groupIndexToAddress2)) {
                    i10 = 0;
                }
                parent2 = parent(this.groups, parent2);
            }
        }
        this.nodeCount += i10;
    }

    public final void endInsert() {
        if (this.insertCount <= 0) {
            PreconditionsKt.throwIllegalStateException("Unbalanced begin/end insert");
        }
        int i = this.insertCount - 1;
        this.insertCount = i;
        if (i == 0) {
            if (this.nodeCountStack.tos != this.startStack.tos) {
                ComposerKt.composeImmediateRuntimeError("startGroup/endGroup mismatch while inserting");
            }
            this.currentGroupEnd = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        }
    }

    public final void ensureStarted(int i) {
        boolean z = false;
        if (!(this.insertCount <= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot call ensureStarted() while inserting");
        }
        int i2 = this.parent;
        if (i2 != i) {
            if (i >= i2 && i < this.currentGroupEnd) {
                z = true;
            }
            if (!z) {
                ComposerKt.composeImmediateRuntimeError("Started group at " + i + " must be a subgroup of the group at " + i2);
            }
            int i3 = this.currentGroup;
            int i4 = this.currentSlot;
            int i5 = this.currentSlotEnd;
            this.currentGroup = i;
            startGroup();
            this.currentGroup = i3;
            this.currentSlot = i4;
            this.currentSlotEnd = i5;
        }
    }

    public final void fixParentAnchorsFor(int i, int i2, int i3) {
        if (i >= this.groupGapStart) {
            i = -((getSize$runtime_release() - i) + 2);
        }
        while (i3 < i2) {
            this.groups[(groupIndexToAddress(i3) * 5) + 2] = i;
            int i4 = this.groups[(groupIndexToAddress(i3) * 5) + 3] + i3;
            fixParentAnchorsFor(i3, i4, i3 + 1);
            i3 = i4;
        }
    }

    public final int getCapacity() {
        return this.groups.length / 5;
    }

    public final int getSize$runtime_release() {
        return getCapacity() - this.groupGapLen;
    }

    public final int getSlotsSize() {
        return this.slots.length - this.slotsGapLen;
    }

    public final int groupIndexToAddress(int i) {
        return i < this.groupGapStart ? i : i + this.groupGapLen;
    }

    public final int groupSize(int i) {
        return SlotTableKt.access$groupSize(this.groups, groupIndexToAddress(i));
    }

    public final boolean indexInGroup(int i, int i2) {
        int capacity;
        int groupSize;
        if (i2 == this.parent) {
            capacity = this.currentGroupEnd;
        } else {
            IntStack intStack = this.startStack;
            int i3 = intStack.tos;
            if (i2 > (i3 > 0 ? intStack.slots[i3 - 1] : 0)) {
                groupSize = groupSize(i2);
            } else {
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        i4 = -1;
                        break;
                    }
                    if (intStack.slots[i4] == i2) {
                        break;
                    }
                    i4++;
                }
                if (i4 < 0) {
                    groupSize = groupSize(i2);
                } else {
                    capacity = (getCapacity() - this.groupGapLen) - this.endStack.slots[i4];
                }
            }
            capacity = groupSize + i2;
        }
        return i > i2 && i < capacity;
    }

    public final void insertGroups(int i) {
        if (i > 0) {
            int i2 = this.currentGroup;
            moveGroupGapTo(i2);
            int i3 = this.groupGapStart;
            int i4 = this.groupGapLen;
            int[] iArr = this.groups;
            int length = iArr.length / 5;
            int i5 = length - i4;
            if (i4 < i) {
                int max = Math.max(Math.max(length * 2, i5 + i), 32);
                int[] iArr2 = new int[max * 5];
                int i6 = max - i5;
                ArraysKt.copyInto(0, 0, i3 * 5, iArr, iArr2);
                ArraysKt.copyInto((i3 + i6) * 5, (i4 + i3) * 5, length * 5, iArr, iArr2);
                this.groups = iArr2;
                i4 = i6;
            }
            int i7 = this.currentGroupEnd;
            if (i7 >= i3) {
                this.currentGroupEnd = i7 + i;
            }
            int i8 = i3 + i;
            this.groupGapStart = i8;
            this.groupGapLen = i4 - i;
            int dataIndexToDataAnchor = dataIndexToDataAnchor(i5 > 0 ? dataIndex(this.groups, groupIndexToAddress(i2 + i)) : 0, this.slotsGapOwner >= i3 ? this.slotsGapStart : 0, this.slotsGapLen, this.slots.length);
            for (int i9 = i3; i9 < i8; i9++) {
                this.groups[(i9 * 5) + 4] = dataIndexToDataAnchor;
            }
            int i10 = this.slotsGapOwner;
            if (i10 >= i3) {
                this.slotsGapOwner = i10 + i;
            }
        }
    }

    public final void insertSlots(int i, int i2) {
        if (i > 0) {
            moveSlotGapTo(this.currentSlot, i2);
            int i3 = this.slotsGapStart;
            int i4 = this.slotsGapLen;
            if (i4 < i) {
                Object[] objArr = this.slots;
                int length = objArr.length;
                int i5 = length - i4;
                int max = Math.max(Math.max(length * 2, i5 + i), 32);
                Object[] objArr2 = new Object[max];
                for (int i6 = 0; i6 < max; i6++) {
                    objArr2[i6] = null;
                }
                int i7 = max - i5;
                ArraysKt.copyInto(0, 0, i3, objArr, objArr2);
                ArraysKt.copyInto(i3 + i7, i4 + i3, length, objArr, objArr2);
                this.slots = objArr2;
                i4 = i7;
            }
            int i8 = this.currentSlotEnd;
            if (i8 >= i3) {
                this.currentSlotEnd = i8 + i;
            }
            this.slotsGapStart = i3 + i;
            this.slotsGapLen = i4 - i;
        }
    }

    public final void moveFrom(SlotTable slotTable, int i) {
        ComposerKt.runtimeCheck(this.insertCount > 0);
        if (i == 0 && this.currentGroup == 0 && this.table.groupsSize == 0) {
            int[] iArr = slotTable.groups;
            int i2 = iArr[(i * 5) + 3];
            int i3 = slotTable.groupsSize;
            if (i2 == i3) {
                int[] iArr2 = this.groups;
                Object[] objArr = this.slots;
                ArrayList arrayList = this.anchors;
                HashMap hashMap = this.sourceInformationMap;
                MutableIntObjectMap mutableIntObjectMap = this.calledByMap;
                Object[] objArr2 = slotTable.slots;
                int i4 = slotTable.slotsSize;
                HashMap hashMap2 = slotTable.sourceInformationMap;
                MutableIntObjectMap mutableIntObjectMap2 = slotTable.calledByMap;
                this.groups = iArr;
                this.slots = objArr2;
                this.anchors = slotTable.anchors;
                this.groupGapStart = i3;
                this.groupGapLen = (iArr.length / 5) - i3;
                this.slotsGapStart = i4;
                this.slotsGapLen = objArr2.length - i4;
                this.slotsGapOwner = i3;
                this.sourceInformationMap = hashMap2;
                this.calledByMap = mutableIntObjectMap2;
                slotTable.groups = iArr2;
                slotTable.groupsSize = 0;
                slotTable.slots = objArr;
                slotTable.slotsSize = 0;
                slotTable.anchors = arrayList;
                slotTable.sourceInformationMap = hashMap;
                slotTable.calledByMap = mutableIntObjectMap;
                return;
            }
        }
        SlotWriter openWriter = slotTable.openWriter();
        try {
            Companion.moveGroup(openWriter, i, this, true, true, false);
            openWriter.close(true);
        } catch (Throwable th) {
            openWriter.close(false);
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
    
        r2 = r8.groups;
        r3 = r9 * 5;
        r4 = r0 * 5;
        r5 = r1 * 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0065, code lost:
    
        if (r9 >= r1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0067, code lost:
    
        kotlin.collections.ArraysKt.copyInto(r4 + r3, r3, r5, r2, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006c, code lost:
    
        kotlin.collections.ArraysKt.copyInto(r5, r5 + r4, r3 + r4, r2, r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void moveGroupGapTo(int r9) {
        /*
            r8 = this;
            int r0 = r8.groupGapLen
            int r1 = r8.groupGapStart
            if (r1 == r9) goto Lad
            java.util.ArrayList r2 = r8.anchors
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L5b
            int r2 = r8.groupGapLen
            int r3 = r8.getCapacity()
            int r3 = r3 - r2
            if (r1 >= r9) goto L39
            java.util.ArrayList r2 = r8.anchors
            int r2 = androidx.compose.runtime.SlotTableKt.access$locationOf(r2, r1, r3)
        L1d:
            java.util.ArrayList r4 = r8.anchors
            int r4 = r4.size()
            if (r2 >= r4) goto L5b
            java.util.ArrayList r4 = r8.anchors
            java.lang.Object r4 = r4.get(r2)
            androidx.compose.runtime.Anchor r4 = (androidx.compose.runtime.Anchor) r4
            int r5 = r4.location
            if (r5 >= 0) goto L5b
            int r5 = r5 + r3
            if (r5 >= r9) goto L5b
            r4.location = r5
            int r2 = r2 + 1
            goto L1d
        L39:
            java.util.ArrayList r2 = r8.anchors
            int r2 = androidx.compose.runtime.SlotTableKt.access$locationOf(r2, r9, r3)
        L3f:
            java.util.ArrayList r4 = r8.anchors
            int r4 = r4.size()
            if (r2 >= r4) goto L5b
            java.util.ArrayList r4 = r8.anchors
            java.lang.Object r4 = r4.get(r2)
            androidx.compose.runtime.Anchor r4 = (androidx.compose.runtime.Anchor) r4
            int r5 = r4.location
            if (r5 < 0) goto L5b
            int r5 = r3 - r5
            int r5 = -r5
            r4.location = r5
            int r2 = r2 + 1
            goto L3f
        L5b:
            if (r0 <= 0) goto L72
            int[] r2 = r8.groups
            int r3 = r9 * 5
            int r4 = r0 * 5
            int r5 = r1 * 5
            if (r9 >= r1) goto L6c
            int r4 = r4 + r3
            kotlin.collections.ArraysKt.copyInto(r4, r3, r5, r2, r2)
            goto L72
        L6c:
            int r6 = r5 + r4
            int r3 = r3 + r4
            kotlin.collections.ArraysKt.copyInto(r5, r6, r3, r2, r2)
        L72:
            if (r9 >= r1) goto L76
            int r1 = r9 + r0
        L76:
            int r2 = r8.getCapacity()
            if (r1 >= r2) goto L7e
            r3 = 1
            goto L7f
        L7e:
            r3 = 0
        L7f:
            androidx.compose.runtime.ComposerKt.runtimeCheck(r3)
        L82:
            if (r1 >= r2) goto Lad
            int[] r3 = r8.groups
            int r4 = r1 * 5
            int r4 = r4 + 2
            r3 = r3[r4]
            r5 = -2
            if (r3 <= r5) goto L91
            r6 = r3
            goto L97
        L91:
            int r6 = r8.getSize$runtime_release()
            int r6 = r6 + r3
            int r6 = r6 - r5
        L97:
            if (r6 >= r9) goto L9a
            goto La1
        L9a:
            int r7 = r8.getSize$runtime_release()
            int r7 = r7 - r6
            int r7 = r7 - r5
            int r6 = -r7
        La1:
            if (r6 == r3) goto La7
            int[] r3 = r8.groups
            r3[r4] = r6
        La7:
            int r1 = r1 + 1
            if (r1 != r9) goto L82
            int r1 = r1 + r0
            goto L82
        Lad:
            r8.groupGapStart = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SlotWriter.moveGroupGapTo(int):void");
    }

    public final void moveSlotGapTo(int i, int i2) {
        int i3 = this.slotsGapLen;
        int i4 = this.slotsGapStart;
        int i5 = this.slotsGapOwner;
        if (i4 != i) {
            Object[] objArr = this.slots;
            if (i < i4) {
                ArraysKt.copyInto(i + i3, i, i4, objArr, objArr);
            } else {
                ArraysKt.copyInto(i4, i4 + i3, i + i3, objArr, objArr);
            }
        }
        int min = Math.min(i2 + 1, getSize$runtime_release());
        if (i5 != min) {
            int length = this.slots.length - i3;
            if (min < i5) {
                int groupIndexToAddress = groupIndexToAddress(min);
                int groupIndexToAddress2 = groupIndexToAddress(i5);
                int i6 = this.groupGapStart;
                while (groupIndexToAddress < groupIndexToAddress2) {
                    int i7 = (groupIndexToAddress * 5) + 4;
                    int i8 = this.groups[i7];
                    if (i8 < 0) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a positive anchor");
                    }
                    this.groups[i7] = -((length - i8) + 1);
                    groupIndexToAddress++;
                    if (groupIndexToAddress == i6) {
                        groupIndexToAddress += this.groupGapLen;
                    }
                }
            } else {
                int groupIndexToAddress3 = groupIndexToAddress(i5);
                int groupIndexToAddress4 = groupIndexToAddress(min);
                while (groupIndexToAddress3 < groupIndexToAddress4) {
                    int i9 = (groupIndexToAddress3 * 5) + 4;
                    int i10 = this.groups[i9];
                    if (i10 >= 0) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a negative anchor");
                    }
                    this.groups[i9] = i10 + length + 1;
                    groupIndexToAddress3++;
                    if (groupIndexToAddress3 == this.groupGapStart) {
                        groupIndexToAddress3 += this.groupGapLen;
                    }
                }
            }
            this.slotsGapOwner = min;
        }
        this.slotsGapStart = i;
    }

    public final List moveTo(Anchor anchor, SlotWriter slotWriter) {
        ComposerKt.runtimeCheck(slotWriter.insertCount > 0);
        ComposerKt.runtimeCheck(this.insertCount == 0);
        ComposerKt.runtimeCheck(anchor.getValid());
        int anchorIndex = anchorIndex(anchor) + 1;
        int i = this.currentGroup;
        ComposerKt.runtimeCheck(i <= anchorIndex && anchorIndex < this.currentGroupEnd);
        int parent = parent(this.groups, anchorIndex);
        int groupSize = groupSize(anchorIndex);
        int access$nodeCount = SlotTableKt.access$isNode(this.groups, groupIndexToAddress(anchorIndex)) ? 1 : SlotTableKt.access$nodeCount(this.groups, groupIndexToAddress(anchorIndex));
        List moveGroup = Companion.moveGroup(this, anchorIndex, slotWriter, false, false, true);
        updateContainsMark(parent);
        boolean z = access$nodeCount > 0;
        while (parent >= i) {
            int groupIndexToAddress = groupIndexToAddress(parent);
            int[] iArr = this.groups;
            SlotTableKt.access$updateGroupSize(groupIndexToAddress, iArr[(groupIndexToAddress * 5) + 3] - groupSize, iArr);
            if (z) {
                if (SlotTableKt.access$isNode(this.groups, groupIndexToAddress)) {
                    z = false;
                } else {
                    int[] iArr2 = this.groups;
                    SlotTableKt.access$updateNodeCount(groupIndexToAddress, SlotTableKt.access$nodeCount(iArr2, groupIndexToAddress) - access$nodeCount, iArr2);
                }
            }
            parent = parent(this.groups, parent);
        }
        if (z) {
            ComposerKt.runtimeCheck(this.nodeCount >= access$nodeCount);
            this.nodeCount -= access$nodeCount;
        }
        return moveGroup;
    }

    public final int parent(int[] iArr, int i) {
        int access$parentAnchor = SlotTableKt.access$parentAnchor(iArr, groupIndexToAddress(i));
        return access$parentAnchor > -2 ? access$parentAnchor : getSize$runtime_release() + access$parentAnchor + 2;
    }

    public final Object rawUpdate(Object obj) {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        Object obj2 = objArr[dataIndexToDataAddress(i)];
        if (this.currentSlot > this.currentSlotEnd) {
            ComposerKt.composeImmediateRuntimeError("Writing to an invalid slot");
        }
        this.slots[dataIndexToDataAddress(this.currentSlot - 1)] = obj;
        return obj2;
    }

    public final void recalculateMarks() {
        boolean z;
        PrioritySet prioritySet = this.pendingRecalculateMarks;
        if (prioritySet != null) {
            while (!prioritySet.list.isEmpty()) {
                int takeMax = prioritySet.takeMax();
                int groupIndexToAddress = groupIndexToAddress(takeMax);
                int i = takeMax + 1;
                int groupSize = groupSize(takeMax) + takeMax;
                while (true) {
                    if (i >= groupSize) {
                        z = false;
                        break;
                    } else {
                        if ((this.groups[(groupIndexToAddress(i) * 5) + 1] & 201326592) != 0) {
                            z = true;
                            break;
                        }
                        i += groupSize(i);
                    }
                }
                if (SlotTableKt.access$containsMark(this.groups, groupIndexToAddress) != z) {
                    int[] iArr = this.groups;
                    int i2 = (groupIndexToAddress * 5) + 1;
                    if (z) {
                        iArr[i2] = iArr[i2] | 67108864;
                    } else {
                        iArr[i2] = iArr[i2] & (-67108865);
                    }
                    int parent = parent(iArr, takeMax);
                    if (parent >= 0) {
                        prioritySet.add(parent);
                    }
                }
            }
        }
    }

    public final boolean removeGroup() {
        if (this.insertCount != 0) {
            ComposerKt.composeImmediateRuntimeError("Cannot remove group while inserting");
        }
        int i = this.currentGroup;
        int i2 = this.currentSlot;
        int dataIndex = dataIndex(this.groups, groupIndexToAddress(i));
        int skipGroup = skipGroup();
        sourceInformationOf(this.parent);
        PrioritySet prioritySet = this.pendingRecalculateMarks;
        if (prioritySet != null) {
            while (!prioritySet.list.isEmpty() && ((Number) CollectionsKt.first(prioritySet.list)).intValue() >= i) {
                prioritySet.takeMax();
            }
        }
        boolean removeGroups = removeGroups(i, this.currentGroup - i);
        removeSlots(dataIndex, this.currentSlot - dataIndex, i - 1);
        this.currentGroup = i;
        this.currentSlot = i2;
        this.nodeCount -= skipGroup;
        return removeGroups;
    }

    public final boolean removeGroups(int i, int i2) {
        if (i2 > 0) {
            ArrayList arrayList = this.anchors;
            moveGroupGapTo(i);
            if (!arrayList.isEmpty()) {
                HashMap hashMap = this.sourceInformationMap;
                int i3 = i + i2;
                int access$locationOf = SlotTableKt.access$locationOf(this.anchors, i3, getCapacity() - this.groupGapLen);
                if (access$locationOf >= this.anchors.size()) {
                    access$locationOf--;
                }
                int i4 = access$locationOf + 1;
                int i5 = 0;
                while (access$locationOf >= 0) {
                    Anchor anchor = (Anchor) this.anchors.get(access$locationOf);
                    int anchorIndex = anchorIndex(anchor);
                    if (anchorIndex < i) {
                        break;
                    }
                    if (anchorIndex < i3) {
                        anchor.location = Integer.MIN_VALUE;
                        if (hashMap != null) {
                        }
                        if (i5 == 0) {
                            i5 = access$locationOf + 1;
                        }
                        i4 = access$locationOf;
                    }
                    access$locationOf--;
                }
                r0 = i4 < i5;
                if (r0) {
                    this.anchors.subList(i4, i5).clear();
                }
            }
            this.groupGapStart = i;
            this.groupGapLen += i2;
            int i6 = this.slotsGapOwner;
            if (i6 > i) {
                this.slotsGapOwner = Math.max(i, i6 - i2);
            }
            int i7 = this.currentGroupEnd;
            if (i7 >= this.groupGapStart) {
                this.currentGroupEnd = i7 - i2;
            }
            int i8 = this.parent;
            if (i8 >= 0 && SlotTableKt.access$containsMark(this.groups, groupIndexToAddress(i8))) {
                updateContainsMark(i8);
            }
        }
        return r0;
    }

    public final void removeSlots(int i, int i2, int i3) {
        if (i2 > 0) {
            int i4 = this.slotsGapLen;
            int i5 = i + i2;
            moveSlotGapTo(i5, i3);
            this.slotsGapStart = i;
            this.slotsGapLen = i4 + i2;
            Arrays.fill(this.slots, i, i5, (Object) null);
            int i6 = this.currentSlotEnd;
            if (i6 >= i) {
                this.currentSlotEnd = i6 - i2;
            }
        }
    }

    public final int skipGroup() {
        int groupIndexToAddress = groupIndexToAddress(this.currentGroup);
        int access$groupSize = SlotTableKt.access$groupSize(this.groups, groupIndexToAddress) + this.currentGroup;
        this.currentGroup = access$groupSize;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(access$groupSize));
        if (SlotTableKt.access$isNode(this.groups, groupIndexToAddress)) {
            return 1;
        }
        return SlotTableKt.access$nodeCount(this.groups, groupIndexToAddress);
    }

    public final void skipToGroupEnd() {
        int i = this.currentGroupEnd;
        this.currentGroup = i;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(i));
    }

    public final int slotIndex(int[] iArr, int i) {
        if (i >= getCapacity()) {
            return this.slots.length - this.slotsGapLen;
        }
        int access$slotAnchor = SlotTableKt.access$slotAnchor(iArr, i);
        return access$slotAnchor < 0 ? (this.slots.length - this.slotsGapLen) + access$slotAnchor + 1 : access$slotAnchor;
    }

    public final int slotIndexOfGroupSlotIndex(int i, int i2) {
        int slotIndex = slotIndex(this.groups, groupIndexToAddress(i));
        int i3 = slotIndex + i2;
        if (!(i3 >= slotIndex && i3 < dataIndex(this.groups, groupIndexToAddress(i + 1)))) {
            ComposerKt.composeImmediateRuntimeError("Write to an invalid slot index " + i2 + " for group " + i);
        }
        return i3;
    }

    public final int slotsEndAllIndex$runtime_release(int i) {
        return dataIndex(this.groups, groupIndexToAddress(groupSize(i) + i));
    }

    public final GroupSourceInformation sourceInformationOf(int i) {
        Anchor tryAnchor$runtime_release;
        HashMap hashMap = this.sourceInformationMap;
        if (hashMap == null || (tryAnchor$runtime_release = tryAnchor$runtime_release(i)) == null) {
            return null;
        }
        return (GroupSourceInformation) hashMap.get(tryAnchor$runtime_release);
    }

    public final void startGroup() {
        if (this.insertCount != 0) {
            ComposerKt.composeImmediateRuntimeError("Key must be supplied when inserting");
        }
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        startGroup(0, composer$Companion$Empty$1, false, composer$Companion$Empty$1);
    }

    public final String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime_release() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    public final Anchor tryAnchor$runtime_release(int i) {
        ArrayList arrayList;
        int search;
        if (i < 0 || i >= getSize$runtime_release() || (search = SlotTableKt.search((arrayList = this.anchors), i, getSize$runtime_release())) < 0) {
            return null;
        }
        return (Anchor) arrayList.get(search);
    }

    public final void update(Object obj) {
        if (this.insertCount <= 0 || this.currentSlot == this.slotsGapStart) {
            rawUpdate(obj);
            return;
        }
        MutableIntObjectMap mutableIntObjectMap = this.deferredSlotWrites;
        if (mutableIntObjectMap == null) {
            mutableIntObjectMap = new MutableIntObjectMap();
        }
        this.deferredSlotWrites = mutableIntObjectMap;
        int i = this.parent;
        Object obj2 = mutableIntObjectMap.get(i);
        if (obj2 == null) {
            obj2 = new MutableObjectList();
            mutableIntObjectMap.set(i, obj2);
        }
        ((MutableObjectList) obj2).add(obj);
    }

    public final void updateAux(Object obj) {
        int groupIndexToAddress = groupIndexToAddress(this.currentGroup);
        if (!SlotTableKt.access$hasAux(this.groups, groupIndexToAddress)) {
            ComposerKt.composeImmediateRuntimeError("Updating the data of a group that was not created with a data slot");
        }
        Object[] objArr = this.slots;
        int[] iArr = this.groups;
        objArr[dataIndexToDataAddress(SlotTableKt.countOneBits(iArr[(groupIndexToAddress * 5) + 1] >> 29) + dataIndex(iArr, groupIndexToAddress))] = obj;
    }

    public final void updateContainsMark(int i) {
        if (i >= 0) {
            PrioritySet prioritySet = this.pendingRecalculateMarks;
            if (prioritySet == null) {
                prioritySet = new PrioritySet();
                this.pendingRecalculateMarks = prioritySet;
            }
            prioritySet.add(i);
        }
    }

    public final void updateNodeOfGroup(int i, Object obj) {
        int groupIndexToAddress = groupIndexToAddress(i);
        int[] iArr = this.groups;
        if (!(groupIndexToAddress < iArr.length && SlotTableKt.access$isNode(iArr, groupIndexToAddress))) {
            ComposerKt.composeImmediateRuntimeError("Updating the node of a group at " + i + " that was not created with as a node group");
        }
        this.slots[dataIndexToDataAddress(dataIndex(this.groups, groupIndexToAddress))] = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void startGroup(int i, Object obj, boolean z, Object obj2) {
        int i2;
        int i3 = this.parent;
        Object[] objArr = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (objArr != false) {
            int i4 = this.currentGroup;
            int dataIndex = dataIndex(this.groups, groupIndexToAddress(i4));
            insertGroups(1);
            this.currentSlot = dataIndex;
            this.currentSlotEnd = dataIndex;
            int groupIndexToAddress = groupIndexToAddress(i4);
            int i5 = obj != composer$Companion$Empty$1 ? 1 : 0;
            int i6 = (z || obj2 == composer$Companion$Empty$1) ? 0 : 1;
            int dataIndexToDataAnchor = dataIndexToDataAnchor(dataIndex, this.slotsGapStart, this.slotsGapLen, this.slots.length);
            if (dataIndexToDataAnchor >= 0 && this.slotsGapOwner < i4) {
                dataIndexToDataAnchor = -(((this.slots.length - this.slotsGapLen) - dataIndexToDataAnchor) + 1);
            }
            int[] iArr = this.groups;
            int i7 = this.parent;
            int i8 = z ? 1073741824 : 0;
            int i9 = i5 != 0 ? 536870912 : 0;
            int i10 = i6 != 0 ? 268435456 : 0;
            int i11 = groupIndexToAddress * 5;
            iArr[i11] = i;
            iArr[i11 + 1] = i8 | i9 | i10;
            iArr[i11 + 2] = i7;
            iArr[i11 + 3] = 0;
            iArr[i11 + 4] = dataIndexToDataAnchor;
            int i12 = (z ? 1 : 0) + i5 + i6;
            if (i12 > 0) {
                insertSlots(i12, i4);
                Object[] objArr2 = this.slots;
                int i13 = this.currentSlot;
                if (z) {
                    objArr2[i13] = obj2;
                    i13++;
                }
                if (i5 != 0) {
                    objArr2[i13] = obj;
                    i13++;
                }
                if (i6 != 0) {
                    objArr2[i13] = obj2;
                    i13++;
                }
                this.currentSlot = i13;
            }
            this.nodeCount = 0;
            i2 = i4 + 1;
            this.parent = i4;
            this.currentGroup = i2;
            if (i3 >= 0) {
                sourceInformationOf(i3);
            }
        } else {
            this.startStack.push(i3);
            this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
            int i14 = this.currentGroup;
            int groupIndexToAddress2 = groupIndexToAddress(i14);
            if (!Intrinsics.areEqual(obj2, composer$Companion$Empty$1)) {
                if (z) {
                    updateNodeOfGroup(this.currentGroup, obj2);
                } else {
                    updateAux(obj2);
                }
            }
            this.currentSlot = slotIndex(this.groups, groupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            this.nodeCount = SlotTableKt.access$nodeCount(this.groups, groupIndexToAddress2);
            this.parent = i14;
            this.currentGroup = i14 + 1;
            i2 = i14 + this.groups[(groupIndexToAddress2 * 5) + 3];
        }
        this.currentGroupEnd = i2;
    }
}
