package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerImpl$$ExternalSyntheticOutline0;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.IntStack;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.SlotReader;
import androidx.compose.runtime.changelist.Operation;
import androidx.compose.runtime.changelist.Operations;
import androidx.compose.runtime.internal.IntRef;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComposerChangeListWriter {
    public ChangeList changeList;
    public final ComposerImpl composer;
    public int moveCount;
    public int pendingUps;
    public boolean startedGroup;
    public int writersReaderDelta;
    public final IntStack startedGroups = new IntStack();
    public boolean implicitRootStart = true;
    public final ArrayList pendingDownNodes = new ArrayList();
    public int removeFrom = -1;
    public int moveFrom = -1;
    public int moveTo = -1;

    public ComposerChangeListWriter(ComposerImpl composerImpl, ChangeList changeList) {
        this.composer = composerImpl;
        this.changeList = changeList;
    }

    public final void copyNodesToNewAnchorLocation(List list, IntRef intRef) {
        ChangeList changeList = this.changeList;
        changeList.getClass();
        if (list.isEmpty()) {
            return;
        }
        Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        Operations operations = changeList.operations;
        operations.pushOp(copyNodesToNewAnchorLocation);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 1, list);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 0, intRef);
        int i = operations.pushedIntMask;
        int i2 = copyNodesToNewAnchorLocation.ints;
        int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i2);
        int i3 = copyNodesToNewAnchorLocation.objects;
        if (i == access$createExpectedArgMask && operations.pushedObjectMask == Operations.access$createExpectedArgMask(operations, i3)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            if (((1 << i5) & operations.pushedIntMask) != 0) {
                if (i4 > 0) {
                    sb.append(", ");
                }
                sb.append(copyNodesToNewAnchorLocation.mo260intParamNamew8GmfQM(i5));
                i4++;
            }
        }
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        int i6 = 0;
        for (int i7 = 0; i7 < i3; i7++) {
            if (((1 << i7) & operations.pushedObjectMask) != 0) {
                if (i4 > 0) {
                    sb3.append(", ");
                }
                sb3.append(copyNodesToNewAnchorLocation.mo261objectParamName31yXWZQ(i7));
                i6++;
            }
        }
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder("Error while pushing ");
        sb5.append(copyNodesToNewAnchorLocation);
        sb5.append(". Not all arguments were provided. Missing ");
        sb5.append(i4);
        sb5.append(" int arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i6, " object arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
    }

    public final void copySlotTableToAnchorLocation(MovableContentState movableContentState, CompositionContext compositionContext, MovableContentStateReference movableContentStateReference, MovableContentStateReference movableContentStateReference2) {
        ChangeList changeList = this.changeList;
        changeList.getClass();
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        Operations operations = changeList.operations;
        operations.pushOp(copySlotTableToAnchorLocation);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 0, movableContentState);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 1, compositionContext);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 3, movableContentStateReference2);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 2, movableContentStateReference);
        int i = operations.pushedIntMask;
        int i2 = copySlotTableToAnchorLocation.ints;
        int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i2);
        int i3 = copySlotTableToAnchorLocation.objects;
        if (i == access$createExpectedArgMask && operations.pushedObjectMask == Operations.access$createExpectedArgMask(operations, i3)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            if (((1 << i5) & operations.pushedIntMask) != 0) {
                if (i4 > 0) {
                    sb.append(", ");
                }
                sb.append(copySlotTableToAnchorLocation.mo260intParamNamew8GmfQM(i5));
                i4++;
            }
        }
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        int i6 = 0;
        for (int i7 = 0; i7 < i3; i7++) {
            if (((1 << i7) & operations.pushedObjectMask) != 0) {
                if (i4 > 0) {
                    sb3.append(", ");
                }
                sb3.append(copySlotTableToAnchorLocation.mo261objectParamName31yXWZQ(i7));
                i6++;
            }
        }
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder("Error while pushing ");
        sb5.append(copySlotTableToAnchorLocation);
        sb5.append(". Not all arguments were provided. Missing ");
        sb5.append(i4);
        sb5.append(" int arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i6, " object arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
    }

    public final void determineMovableContentNodeIndex(IntRef intRef, Anchor anchor) {
        pushPendingUpsAndDowns();
        ChangeList changeList = this.changeList;
        changeList.getClass();
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        Operations operations = changeList.operations;
        operations.pushOp(determineMovableContentNodeIndex);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 0, intRef);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 1, anchor);
        int i = operations.pushedIntMask;
        int i2 = determineMovableContentNodeIndex.ints;
        int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i2);
        int i3 = determineMovableContentNodeIndex.objects;
        if (i == access$createExpectedArgMask && operations.pushedObjectMask == Operations.access$createExpectedArgMask(operations, i3)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            if (((1 << i5) & operations.pushedIntMask) != 0) {
                if (i4 > 0) {
                    sb.append(", ");
                }
                sb.append(determineMovableContentNodeIndex.mo260intParamNamew8GmfQM(i5));
                i4++;
            }
        }
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        int i6 = 0;
        for (int i7 = 0; i7 < i3; i7++) {
            if (((1 << i7) & operations.pushedObjectMask) != 0) {
                if (i4 > 0) {
                    sb3.append(", ");
                }
                sb3.append(determineMovableContentNodeIndex.mo261objectParamName31yXWZQ(i7));
                i6++;
            }
        }
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder("Error while pushing ");
        sb5.append(determineMovableContentNodeIndex);
        sb5.append(". Not all arguments were provided. Missing ");
        sb5.append(i4);
        sb5.append(" int arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i6, " object arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
    }

    public final void includeOperationsIn(ChangeList changeList, IntRef intRef) {
        ChangeList changeList2 = this.changeList;
        changeList2.getClass();
        if (changeList.operations.isNotEmpty()) {
            Operation.ApplyChangeList applyChangeList = Operation.ApplyChangeList.INSTANCE;
            Operations operations = changeList2.operations;
            operations.pushOp(applyChangeList);
            Operations.WriteScope.m267setObjectDKhxnng(operations, 0, changeList);
            Operations.WriteScope.m267setObjectDKhxnng(operations, 1, intRef);
            int i = operations.pushedIntMask;
            int i2 = applyChangeList.ints;
            int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i2);
            int i3 = applyChangeList.objects;
            if (i == access$createExpectedArgMask && operations.pushedObjectMask == Operations.access$createExpectedArgMask(operations, i3)) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                if (((1 << i5) & operations.pushedIntMask) != 0) {
                    if (i4 > 0) {
                        sb.append(", ");
                    }
                    sb.append(applyChangeList.mo260intParamNamew8GmfQM(i5));
                    i4++;
                }
            }
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                if (((1 << i7) & operations.pushedObjectMask) != 0) {
                    if (i4 > 0) {
                        sb3.append(", ");
                    }
                    sb3.append(applyChangeList.mo261objectParamName31yXWZQ(i7));
                    i6++;
                }
            }
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder("Error while pushing ");
            sb5.append(applyChangeList);
            sb5.append(". Not all arguments were provided. Missing ");
            sb5.append(i4);
            sb5.append(" int arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i6, " object arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
        }
    }

    public final void moveUp() {
        realizeNodeMovementOperations();
        if (this.pendingDownNodes.isEmpty()) {
            this.pendingUps++;
        } else {
            this.pendingDownNodes.remove(r1.size() - 1);
        }
    }

    public final void pushPendingUpsAndDowns() {
        int i;
        int i2 = this.pendingUps;
        int i3 = 0;
        int i4 = 1;
        if (i2 > 0) {
            ChangeList changeList = this.changeList;
            changeList.getClass();
            Operation.Ups ups = Operation.Ups.INSTANCE;
            Operations operations = changeList.operations;
            operations.pushOp(ups);
            Operations.WriteScope.m266setIntA6tL2VI(operations, 0, i2);
            int i5 = operations.pushedIntMask;
            int i6 = ups.ints;
            int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i6);
            int i7 = ups.objects;
            if (i5 == access$createExpectedArgMask && operations.pushedObjectMask == Operations.access$createExpectedArgMask(operations, i7)) {
                i = 0;
            } else {
                StringBuilder sb = new StringBuilder();
                int i8 = 0;
                while (i8 < i6) {
                    if (((i4 << i8) & operations.pushedIntMask) != 0) {
                        if (i3 > 0) {
                            sb.append(", ");
                        }
                        sb.append(ups.mo260intParamNamew8GmfQM(i8));
                        i3++;
                    }
                    i8++;
                    i4 = 1;
                }
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                int i9 = 0;
                int i10 = 0;
                while (i9 < i7) {
                    int i11 = i7;
                    if (((1 << i9) & operations.pushedObjectMask) != 0) {
                        if (i3 > 0) {
                            sb3.append(", ");
                        }
                        sb3.append(ups.mo261objectParamName31yXWZQ(i9));
                        i10++;
                    }
                    i9++;
                    i7 = i11;
                }
                String sb4 = sb3.toString();
                StringBuilder sb5 = new StringBuilder("Error while pushing ");
                sb5.append(ups);
                sb5.append(". Not all arguments were provided. Missing ");
                sb5.append(i3);
                sb5.append(" int arguments (");
                ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i10, " object arguments (");
                ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
                i = 0;
            }
            this.pendingUps = i;
        }
        if (this.pendingDownNodes.isEmpty()) {
            return;
        }
        ChangeList changeList2 = this.changeList;
        ArrayList arrayList = this.pendingDownNodes;
        int size = arrayList.size();
        Object[] objArr = new Object[size];
        for (int i12 = 0; i12 < size; i12++) {
            objArr[i12] = arrayList.get(i12);
        }
        changeList2.getClass();
        if (size != 0) {
            Operation.Downs downs = Operation.Downs.INSTANCE;
            Operations operations2 = changeList2.operations;
            operations2.pushOp(downs);
            Operations.WriteScope.m267setObjectDKhxnng(operations2, 0, objArr);
            int i13 = operations2.pushedIntMask;
            int i14 = downs.ints;
            int access$createExpectedArgMask2 = Operations.access$createExpectedArgMask(operations2, i14);
            int i15 = downs.objects;
            if (i13 != access$createExpectedArgMask2 || operations2.pushedObjectMask != Operations.access$createExpectedArgMask(operations2, i15)) {
                StringBuilder sb6 = new StringBuilder();
                int i16 = 0;
                for (int i17 = 0; i17 < i14; i17++) {
                    if (((1 << i17) & operations2.pushedIntMask) != 0) {
                        if (i16 > 0) {
                            sb6.append(", ");
                        }
                        sb6.append(downs.mo260intParamNamew8GmfQM(i17));
                        i16++;
                    }
                }
                String sb7 = sb6.toString();
                StringBuilder sb8 = new StringBuilder();
                int i18 = 0;
                int i19 = 0;
                while (i18 < i15) {
                    int i20 = i15;
                    if (((1 << i18) & operations2.pushedObjectMask) != 0) {
                        if (i16 > 0) {
                            sb8.append(", ");
                        }
                        sb8.append(downs.mo261objectParamName31yXWZQ(i18));
                        i19++;
                    }
                    i18++;
                    i15 = i20;
                }
                String sb9 = sb8.toString();
                StringBuilder sb10 = new StringBuilder("Error while pushing ");
                sb10.append(downs);
                sb10.append(". Not all arguments were provided. Missing ");
                sb10.append(i16);
                sb10.append(" int arguments (");
                ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb7, ") and ", i19, " object arguments (");
                ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb9, ").");
            }
        }
        this.pendingDownNodes.clear();
    }

    public final void realizeNodeMovementOperations() {
        int i = this.moveCount;
        if (i > 0) {
            int i2 = this.removeFrom;
            int i3 = 1;
            if (i2 >= 0) {
                pushPendingUpsAndDowns();
                ChangeList changeList = this.changeList;
                changeList.getClass();
                Operation.RemoveNode removeNode = Operation.RemoveNode.INSTANCE;
                Operations operations = changeList.operations;
                operations.pushOp(removeNode);
                Operations.WriteScope.m266setIntA6tL2VI(operations, 0, i2);
                Operations.WriteScope.m266setIntA6tL2VI(operations, 1, i);
                int i4 = operations.pushedIntMask;
                int i5 = removeNode.ints;
                int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i5);
                int i6 = removeNode.objects;
                if (i4 != access$createExpectedArgMask || operations.pushedObjectMask != Operations.access$createExpectedArgMask(operations, i6)) {
                    StringBuilder sb = new StringBuilder();
                    int i7 = 0;
                    int i8 = 0;
                    while (i8 < i5) {
                        if (((i3 << i8) & operations.pushedIntMask) != 0) {
                            if (i7 > 0) {
                                sb.append(", ");
                            }
                            sb.append(removeNode.mo260intParamNamew8GmfQM(i8));
                            i7++;
                        }
                        i8++;
                        i3 = 1;
                    }
                    String sb2 = sb.toString();
                    StringBuilder sb3 = new StringBuilder();
                    int i9 = 0;
                    int i10 = 0;
                    while (i9 < i6) {
                        int i11 = i6;
                        if (((1 << i9) & operations.pushedObjectMask) != 0) {
                            if (i7 > 0) {
                                sb3.append(", ");
                            }
                            sb3.append(removeNode.mo261objectParamName31yXWZQ(i9));
                            i10++;
                        }
                        i9++;
                        i6 = i11;
                    }
                    String sb4 = sb3.toString();
                    StringBuilder sb5 = new StringBuilder("Error while pushing ");
                    sb5.append(removeNode);
                    sb5.append(". Not all arguments were provided. Missing ");
                    sb5.append(i7);
                    sb5.append(" int arguments (");
                    ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i10, " object arguments (");
                    ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
                }
                this.removeFrom = -1;
            } else {
                int i12 = this.moveTo;
                int i13 = this.moveFrom;
                pushPendingUpsAndDowns();
                ChangeList changeList2 = this.changeList;
                changeList2.getClass();
                Operation.MoveNode moveNode = Operation.MoveNode.INSTANCE;
                Operations operations2 = changeList2.operations;
                operations2.pushOp(moveNode);
                Operations.WriteScope.m266setIntA6tL2VI(operations2, 1, i12);
                Operations.WriteScope.m266setIntA6tL2VI(operations2, 0, i13);
                Operations.WriteScope.m266setIntA6tL2VI(operations2, 2, i);
                int i14 = operations2.pushedIntMask;
                int i15 = moveNode.ints;
                int access$createExpectedArgMask2 = Operations.access$createExpectedArgMask(operations2, i15);
                int i16 = moveNode.objects;
                if (i14 != access$createExpectedArgMask2 || operations2.pushedObjectMask != Operations.access$createExpectedArgMask(operations2, i16)) {
                    StringBuilder sb6 = new StringBuilder();
                    int i17 = 0;
                    for (int i18 = 0; i18 < i15; i18++) {
                        if (((1 << i18) & operations2.pushedIntMask) != 0) {
                            if (i17 > 0) {
                                sb6.append(", ");
                            }
                            sb6.append(moveNode.mo260intParamNamew8GmfQM(i18));
                            i17++;
                        }
                    }
                    String sb7 = sb6.toString();
                    StringBuilder sb8 = new StringBuilder();
                    int i19 = 0;
                    int i20 = 0;
                    while (i19 < i16) {
                        int i21 = i16;
                        if (((1 << i19) & operations2.pushedObjectMask) != 0) {
                            if (i17 > 0) {
                                sb8.append(", ");
                            }
                            sb8.append(moveNode.mo261objectParamName31yXWZQ(i19));
                            i20++;
                        }
                        i19++;
                        i16 = i21;
                    }
                    String sb9 = sb8.toString();
                    StringBuilder sb10 = new StringBuilder("Error while pushing ");
                    sb10.append(moveNode);
                    sb10.append(". Not all arguments were provided. Missing ");
                    sb10.append(i17);
                    sb10.append(" int arguments (");
                    ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb7, ") and ", i20, " object arguments (");
                    ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb9, ").");
                }
                this.moveFrom = -1;
                this.moveTo = -1;
            }
            this.moveCount = 0;
        }
    }

    public final void realizeOperationLocation(boolean z) {
        ComposerImpl composerImpl = this.composer;
        int i = z ? composerImpl.reader.parent : composerImpl.reader.currentGroup;
        int i2 = i - this.writersReaderDelta;
        if (i2 < 0) {
            ComposerKt.composeImmediateRuntimeError("Tried to seek backward");
        }
        if (i2 > 0) {
            ChangeList changeList = this.changeList;
            changeList.getClass();
            Operation.AdvanceSlotsBy advanceSlotsBy = Operation.AdvanceSlotsBy.INSTANCE;
            Operations operations = changeList.operations;
            operations.pushOp(advanceSlotsBy);
            Operations.WriteScope.m266setIntA6tL2VI(operations, 0, i2);
            int i3 = operations.pushedIntMask;
            int i4 = advanceSlotsBy.ints;
            int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i4);
            int i5 = advanceSlotsBy.objects;
            if (i3 != access$createExpectedArgMask || operations.pushedObjectMask != Operations.access$createExpectedArgMask(operations, i5)) {
                StringBuilder sb = new StringBuilder();
                int i6 = 0;
                for (int i7 = 0; i7 < i4; i7++) {
                    if (((1 << i7) & operations.pushedIntMask) != 0) {
                        if (i6 > 0) {
                            sb.append(", ");
                        }
                        sb.append(advanceSlotsBy.mo260intParamNamew8GmfQM(i7));
                        i6++;
                    }
                }
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                int i8 = 0;
                for (int i9 = 0; i9 < i5; i9++) {
                    if (((1 << i9) & operations.pushedObjectMask) != 0) {
                        if (i6 > 0) {
                            sb3.append(", ");
                        }
                        sb3.append(advanceSlotsBy.mo261objectParamName31yXWZQ(i9));
                        i8++;
                    }
                }
                String sb4 = sb3.toString();
                StringBuilder sb5 = new StringBuilder("Error while pushing ");
                sb5.append(advanceSlotsBy);
                sb5.append(". Not all arguments were provided. Missing ");
                sb5.append(i6);
                sb5.append(" int arguments (");
                ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i8, " object arguments (");
                ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
            }
            this.writersReaderDelta = i;
        }
    }

    public final void recordSlotEditing() {
        SlotReader slotReader = this.composer.reader;
        if (slotReader.groupsSize > 0) {
            int i = slotReader.parent;
            IntStack intStack = this.startedGroups;
            int i2 = intStack.tos;
            if ((i2 > 0 ? intStack.slots[i2 - 1] : -2) != i) {
                if (!this.startedGroup && this.implicitRootStart) {
                    realizeOperationLocation(false);
                    ChangeList changeList = this.changeList;
                    changeList.getClass();
                    changeList.operations.push(Operation.EnsureRootGroupStarted.INSTANCE);
                    this.startedGroup = true;
                }
                if (i > 0) {
                    Anchor anchor = slotReader.anchor(i);
                    intStack.push(i);
                    realizeOperationLocation(false);
                    ChangeList changeList2 = this.changeList;
                    changeList2.getClass();
                    Operation.EnsureGroupStarted ensureGroupStarted = Operation.EnsureGroupStarted.INSTANCE;
                    Operations operations = changeList2.operations;
                    operations.pushOp(ensureGroupStarted);
                    Operations.WriteScope.m267setObjectDKhxnng(operations, 0, anchor);
                    int i3 = operations.pushedIntMask;
                    int i4 = ensureGroupStarted.ints;
                    int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i4);
                    int i5 = ensureGroupStarted.objects;
                    if (i3 != access$createExpectedArgMask || operations.pushedObjectMask != Operations.access$createExpectedArgMask(operations, i5)) {
                        StringBuilder sb = new StringBuilder();
                        int i6 = 0;
                        for (int i7 = 0; i7 < i4; i7++) {
                            if (((1 << i7) & operations.pushedIntMask) != 0) {
                                if (i6 > 0) {
                                    sb.append(", ");
                                }
                                sb.append(ensureGroupStarted.mo260intParamNamew8GmfQM(i7));
                                i6++;
                            }
                        }
                        String sb2 = sb.toString();
                        StringBuilder sb3 = new StringBuilder();
                        int i8 = 0;
                        for (int i9 = 0; i9 < i5; i9++) {
                            if (((1 << i9) & operations.pushedObjectMask) != 0) {
                                if (i6 > 0) {
                                    sb3.append(", ");
                                }
                                sb3.append(ensureGroupStarted.mo261objectParamName31yXWZQ(i9));
                                i8++;
                            }
                        }
                        String sb4 = sb3.toString();
                        StringBuilder sb5 = new StringBuilder("Error while pushing ");
                        sb5.append(ensureGroupStarted);
                        sb5.append(". Not all arguments were provided. Missing ");
                        sb5.append(i6);
                        sb5.append(" int arguments (");
                        ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i8, " object arguments (");
                        ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
                    }
                    this.startedGroup = true;
                }
            }
        }
    }

    public final void removeNode(int i, int i2) {
        if (i2 > 0) {
            if (!(i >= 0)) {
                ComposerKt.composeImmediateRuntimeError("Invalid remove index " + i);
            }
            if (this.removeFrom == i) {
                this.moveCount += i2;
                return;
            }
            realizeNodeMovementOperations();
            this.removeFrom = i;
            this.moveCount = i2;
        }
    }
}
