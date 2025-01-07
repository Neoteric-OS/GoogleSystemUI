package androidx.compose.runtime.changelist;

import androidx.collection.MutableIntObjectMap;
import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.InvalidationResult;
import androidx.compose.runtime.MovableContent;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.OffsetApplier;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeOwner;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotTableKt;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.changelist.Operations;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Operation {
    public final int ints;
    public final int objects;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AdvanceSlotsBy extends Operation {
        public static final AdvanceSlotsBy INSTANCE = new AdvanceSlotsBy(1, 0, 2);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            slotWriter.advanceBy(opIterator.m264getIntw8GmfQM(0));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "distance" : super.mo260intParamNamew8GmfQM(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppendValue extends Operation {
        public static final AppendValue INSTANCE = new AppendValue(0, 2, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            Anchor anchor = (Anchor) opIterator.m265getObject31yXWZQ(0);
            Object m265getObject31yXWZQ = opIterator.m265getObject31yXWZQ(1);
            if (m265getObject31yXWZQ instanceof RememberObserverHolder) {
                rememberEventDispatcher.currentRememberingList.add(((RememberObserverHolder) m265getObject31yXWZQ).wrapped);
            }
            if (slotWriter.insertCount != 0) {
                ComposerKt.composeImmediateRuntimeError("Can only append a slot if not current inserting");
            }
            int i = slotWriter.currentSlot;
            int i2 = slotWriter.currentSlotEnd;
            int anchorIndex = slotWriter.anchorIndex(anchor);
            int dataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(anchorIndex + 1));
            slotWriter.currentSlot = dataIndex;
            slotWriter.currentSlotEnd = dataIndex;
            slotWriter.insertSlots(1, anchorIndex);
            if (i >= dataIndex) {
                i++;
                i2++;
            }
            slotWriter.slots[dataIndex] = m265getObject31yXWZQ;
            slotWriter.currentSlot = i;
            slotWriter.currentSlotEnd = i2;
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "anchor" : ObjectParameter.m263equalsimpl0(i, 1) ? "value" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ApplyChangeList extends Operation {
        public static final ApplyChangeList INSTANCE = new ApplyChangeList(0, 2, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            IntRef intRef = (IntRef) opIterator.m265getObject31yXWZQ(1);
            int i = intRef != null ? intRef.element : 0;
            ChangeList changeList = (ChangeList) opIterator.m265getObject31yXWZQ(0);
            if (i > 0) {
                applier = new OffsetApplier(applier, i);
            }
            changeList.executeAndFlushAllPendingChanges(applier, slotWriter, rememberEventDispatcher);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "changes" : ObjectParameter.m263equalsimpl0(i, 1) ? "effectiveNodeIndex" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CopyNodesToNewAnchorLocation extends Operation {
        public static final CopyNodesToNewAnchorLocation INSTANCE = new CopyNodesToNewAnchorLocation(0, 2, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            int i = ((IntRef) opIterator.m265getObject31yXWZQ(0)).element;
            List list = (List) opIterator.m265getObject31yXWZQ(1);
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                Object obj = list.get(i2);
                int i3 = i + i2;
                applier.insertBottomUp(i3, obj);
                applier.insertTopDown(i3, obj);
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "effectiveNodeIndex" : ObjectParameter.m263equalsimpl0(i, 1) ? "nodes" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CopySlotTableToAnchorLocation extends Operation {
        public static final CopySlotTableToAnchorLocation INSTANCE = new CopySlotTableToAnchorLocation(0, 4, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            MovableContentStateReference movableContentStateReference = (MovableContentStateReference) opIterator.m265getObject31yXWZQ(2);
            MovableContentStateReference movableContentStateReference2 = (MovableContentStateReference) opIterator.m265getObject31yXWZQ(3);
            CompositionContext compositionContext = (CompositionContext) opIterator.m265getObject31yXWZQ(1);
            MovableContentState movableContentState = (MovableContentState) opIterator.m265getObject31yXWZQ(0);
            if (movableContentState == null && (movableContentState = compositionContext.movableContentStateResolve$runtime_release(movableContentStateReference)) == null) {
                ComposerKt.composeRuntimeError("Could not resolve state for movable content");
                throw new KotlinNothingValueException();
            }
            ComposerKt.runtimeCheck(slotWriter.insertCount <= 0 && slotWriter.groupSize(slotWriter.currentGroup + 1) == 1);
            int i = slotWriter.currentGroup;
            int i2 = slotWriter.currentSlot;
            int i3 = slotWriter.currentSlotEnd;
            slotWriter.advanceBy(1);
            slotWriter.startGroup();
            slotWriter.beginInsert();
            SlotWriter openWriter = movableContentState.slotTable.openWriter();
            try {
                List moveGroup = SlotWriter.Companion.moveGroup(openWriter, 2, slotWriter, false, true, true);
                openWriter.close(true);
                slotWriter.endInsert();
                slotWriter.endGroup();
                slotWriter.currentGroup = i;
                slotWriter.currentSlot = i2;
                slotWriter.currentSlotEnd = i3;
                RecomposeScopeImpl.Companion.adoptAnchoredScopes$runtime_release(slotWriter, moveGroup, movableContentStateReference2.composition);
            } catch (Throwable th) {
                openWriter.close(false);
                throw th;
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "resolvedState" : ObjectParameter.m263equalsimpl0(i, 1) ? "resolvedCompositionContext" : ObjectParameter.m263equalsimpl0(i, 2) ? "from" : ObjectParameter.m263equalsimpl0(i, 3) ? "to" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeactivateCurrentGroup extends Operation {
        public static final DeactivateCurrentGroup INSTANCE;

        static {
            int i = 0;
            INSTANCE = new DeactivateCurrentGroup(i, i, 3);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            ComposerKt.deactivateCurrentGroup(slotWriter, rememberEventDispatcher);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DetermineMovableContentNodeIndex extends Operation {
        public static final DetermineMovableContentNodeIndex INSTANCE = new DetermineMovableContentNodeIndex(0, 2, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            int i;
            IntRef intRef = (IntRef) opIterator.m265getObject31yXWZQ(0);
            int anchorIndex = slotWriter.anchorIndex((Anchor) opIterator.m265getObject31yXWZQ(1));
            ComposerKt.runtimeCheck(slotWriter.currentGroup < anchorIndex);
            OperationKt.positionToParentOf(slotWriter, applier, anchorIndex);
            int i2 = slotWriter.currentGroup;
            int i3 = slotWriter.parent;
            while (i3 >= 0 && !SlotTableKt.access$isNode(slotWriter.groups, slotWriter.groupIndexToAddress(i3))) {
                i3 = slotWriter.parent(slotWriter.groups, i3);
            }
            int i4 = i3 + 1;
            int i5 = 0;
            while (i4 < i2) {
                if (slotWriter.indexInGroup(i2, i4)) {
                    if (SlotTableKt.access$isNode(slotWriter.groups, slotWriter.groupIndexToAddress(i4))) {
                        i5 = 0;
                    }
                    i4++;
                } else {
                    i5 += SlotTableKt.access$isNode(slotWriter.groups, slotWriter.groupIndexToAddress(i4)) ? 1 : SlotTableKt.access$nodeCount(slotWriter.groups, slotWriter.groupIndexToAddress(i4));
                    i4 += slotWriter.groupSize(i4);
                }
            }
            while (true) {
                i = slotWriter.currentGroup;
                if (i >= anchorIndex) {
                    break;
                }
                if (slotWriter.indexInGroup(anchorIndex, i)) {
                    int i6 = slotWriter.currentGroup;
                    if (i6 < slotWriter.currentGroupEnd && SlotTableKt.access$isNode(slotWriter.groups, slotWriter.groupIndexToAddress(i6))) {
                        int groupIndexToAddress = slotWriter.groupIndexToAddress(slotWriter.currentGroup);
                        applier.down(SlotTableKt.access$isNode(slotWriter.groups, groupIndexToAddress) ? slotWriter.slots[slotWriter.dataIndexToDataAddress(slotWriter.dataIndex(slotWriter.groups, groupIndexToAddress))] : null);
                        i5 = 0;
                    }
                    slotWriter.startGroup();
                } else {
                    i5 += slotWriter.skipGroup();
                }
            }
            ComposerKt.runtimeCheck(i == anchorIndex);
            intRef.element = i5;
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "effectiveNodeIndexOut" : ObjectParameter.m263equalsimpl0(i, 1) ? "anchor" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Downs extends Operation {
        public static final Downs INSTANCE;

        static {
            int i = 1;
            INSTANCE = new Downs(0, i, i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            for (Object obj : (Object[]) opIterator.m265getObject31yXWZQ(0)) {
                applier.down(obj);
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "nodes" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EndCompositionScope extends Operation {
        public static final EndCompositionScope INSTANCE = new EndCompositionScope(0, 2, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            ((Function1) opIterator.m265getObject31yXWZQ(0)).invoke((Composition) opIterator.m265getObject31yXWZQ(1));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "anchor" : ObjectParameter.m263equalsimpl0(i, 1) ? "composition" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EndCurrentGroup extends Operation {
        public static final EndCurrentGroup INSTANCE;

        static {
            int i = 0;
            INSTANCE = new EndCurrentGroup(i, i, 3);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            slotWriter.endGroup();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EndMovableContentPlacement extends Operation {
        public static final EndMovableContentPlacement INSTANCE;

        static {
            int i = 0;
            INSTANCE = new EndMovableContentPlacement(i, i, 3);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            OperationKt.positionToParentOf(slotWriter, applier, 0);
            slotWriter.endGroup();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EndResumingScope extends Operation {
        public static final EndResumingScope INSTANCE;

        static {
            int i = 1;
            INSTANCE = new EndResumingScope(0, i, i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "scope" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EnsureGroupStarted extends Operation {
        public static final EnsureGroupStarted INSTANCE;

        static {
            int i = 1;
            INSTANCE = new EnsureGroupStarted(0, i, i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            Anchor anchor = (Anchor) opIterator.m265getObject31yXWZQ(0);
            anchor.getClass();
            slotWriter.ensureStarted(slotWriter.anchorIndex(anchor));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "anchor" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EnsureRootGroupStarted extends Operation {
        public static final EnsureRootGroupStarted INSTANCE;

        static {
            int i = 0;
            INSTANCE = new EnsureRootGroupStarted(i, i, 3);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            slotWriter.ensureStarted(0);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InsertNodeFixup extends Operation {
        public static final InsertNodeFixup INSTANCE = new InsertNodeFixup(1, 2);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            Object invoke = ((Function0) opIterator.m265getObject31yXWZQ(0)).invoke();
            Anchor anchor = (Anchor) opIterator.m265getObject31yXWZQ(1);
            int m264getIntw8GmfQM = opIterator.m264getIntw8GmfQM(0);
            anchor.getClass();
            slotWriter.updateNodeOfGroup(slotWriter.anchorIndex(anchor), invoke);
            applier.insertTopDown(m264getIntw8GmfQM, invoke);
            applier.down(invoke);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "insertIndex" : super.mo260intParamNamew8GmfQM(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "factory" : ObjectParameter.m263equalsimpl0(i, 1) ? "groupAnchor" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InsertSlots extends Operation {
        public static final InsertSlots INSTANCE = new InsertSlots(0, 2, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            SlotTable slotTable = (SlotTable) opIterator.m265getObject31yXWZQ(1);
            Anchor anchor = (Anchor) opIterator.m265getObject31yXWZQ(0);
            slotWriter.beginInsert();
            anchor.getClass();
            slotWriter.moveFrom(slotTable, slotTable.anchorIndex(anchor));
            slotWriter.endInsert();
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "anchor" : ObjectParameter.m263equalsimpl0(i, 1) ? "from" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InsertSlotsWithFixups extends Operation {
        public static final InsertSlotsWithFixups INSTANCE = new InsertSlotsWithFixups(0, 3, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            SlotTable slotTable = (SlotTable) opIterator.m265getObject31yXWZQ(1);
            Anchor anchor = (Anchor) opIterator.m265getObject31yXWZQ(0);
            FixupList fixupList = (FixupList) opIterator.m265getObject31yXWZQ(2);
            SlotWriter openWriter = slotTable.openWriter();
            try {
                if (!fixupList.pendingOperations.isEmpty()) {
                    ComposerKt.composeImmediateRuntimeError("FixupList has pending fixup operations that were not realized. Were there mismatched insertNode() and endNodeInsert() calls?");
                }
                fixupList.operations.executeAndFlushAllPendingOperations(applier, openWriter, rememberEventDispatcher);
                openWriter.close(true);
                slotWriter.beginInsert();
                anchor.getClass();
                slotWriter.moveFrom(slotTable, slotTable.anchorIndex(anchor));
                slotWriter.endInsert();
            } catch (Throwable th) {
                openWriter.close(false);
                throw th;
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "anchor" : ObjectParameter.m263equalsimpl0(i, 1) ? "from" : ObjectParameter.m263equalsimpl0(i, 2) ? "fixups" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class IntParameter {
        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m262equalsimpl0(int i, int i2) {
            return i == i2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MoveCurrentGroup extends Operation {
        public static final MoveCurrentGroup INSTANCE = new MoveCurrentGroup(1, 0, 2);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            Anchor anchor;
            int anchorIndex;
            int m264getIntw8GmfQM = opIterator.m264getIntw8GmfQM(0);
            if (slotWriter.insertCount != 0) {
                ComposerKt.composeImmediateRuntimeError("Cannot move a group while inserting");
            }
            if (!(m264getIntw8GmfQM >= 0)) {
                ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
            }
            if (m264getIntw8GmfQM == 0) {
                return;
            }
            int i = slotWriter.currentGroup;
            int i2 = slotWriter.parent;
            int i3 = slotWriter.currentGroupEnd;
            int i4 = i;
            while (m264getIntw8GmfQM > 0) {
                i4 += slotWriter.groups[(slotWriter.groupIndexToAddress(i4) * 5) + 3];
                if (i4 > i3) {
                    ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
                }
                m264getIntw8GmfQM--;
            }
            int i5 = slotWriter.groups[(slotWriter.groupIndexToAddress(i4) * 5) + 3];
            int dataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.currentGroup));
            int dataIndex2 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i4));
            int i6 = i4 + i5;
            int dataIndex3 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i6));
            int i7 = dataIndex3 - dataIndex2;
            slotWriter.insertSlots(i7, Math.max(slotWriter.currentGroup - 1, 0));
            slotWriter.insertGroups(i5);
            int[] iArr = slotWriter.groups;
            int groupIndexToAddress = slotWriter.groupIndexToAddress(i6) * 5;
            ArraysKt.copyInto(slotWriter.groupIndexToAddress(i) * 5, groupIndexToAddress, (i5 * 5) + groupIndexToAddress, iArr, iArr);
            if (i7 > 0) {
                Object[] objArr = slotWriter.slots;
                ArraysKt.copyInto(dataIndex, slotWriter.dataIndexToDataAddress(dataIndex2 + i7), slotWriter.dataIndexToDataAddress(dataIndex3 + i7), objArr, objArr);
            }
            int i8 = dataIndex2 + i7;
            int i9 = i8 - dataIndex;
            int i10 = slotWriter.slotsGapStart;
            int i11 = slotWriter.slotsGapLen;
            int length = slotWriter.slots.length;
            int i12 = slotWriter.slotsGapOwner;
            int i13 = i + i5;
            int i14 = i;
            while (i14 < i13) {
                int groupIndexToAddress2 = slotWriter.groupIndexToAddress(i14);
                int i15 = i10;
                int i16 = i9;
                iArr[(groupIndexToAddress2 * 5) + 4] = SlotWriter.dataIndexToDataAnchor(SlotWriter.dataIndexToDataAnchor(slotWriter.dataIndex(iArr, groupIndexToAddress2) - i9, i12 < groupIndexToAddress2 ? 0 : i15, i11, length), slotWriter.slotsGapStart, slotWriter.slotsGapLen, slotWriter.slots.length);
                i14++;
                i10 = i15;
                i9 = i16;
                i11 = i11;
                length = length;
            }
            int i17 = i6 + i5;
            int size$runtime_release = slotWriter.getSize$runtime_release();
            int access$locationOf = SlotTableKt.access$locationOf(slotWriter.anchors, i6, size$runtime_release);
            ArrayList arrayList = new ArrayList();
            if (access$locationOf >= 0) {
                while (access$locationOf < slotWriter.anchors.size() && (anchorIndex = slotWriter.anchorIndex((anchor = (Anchor) slotWriter.anchors.get(access$locationOf)))) >= i6 && anchorIndex < i17) {
                    arrayList.add(anchor);
                    slotWriter.anchors.remove(access$locationOf);
                }
            }
            int i18 = i - i6;
            int size = arrayList.size();
            for (int i19 = 0; i19 < size; i19++) {
                Anchor anchor2 = (Anchor) arrayList.get(i19);
                int anchorIndex2 = slotWriter.anchorIndex(anchor2) + i18;
                if (anchorIndex2 >= slotWriter.groupGapStart) {
                    anchor2.location = -(size$runtime_release - anchorIndex2);
                } else {
                    anchor2.location = anchorIndex2;
                }
                slotWriter.anchors.add(SlotTableKt.access$locationOf(slotWriter.anchors, anchorIndex2, size$runtime_release), anchor2);
            }
            if (slotWriter.removeGroups(i6, i5)) {
                ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
            }
            slotWriter.fixParentAnchorsFor(i2, slotWriter.currentGroupEnd, i);
            if (i7 > 0) {
                slotWriter.removeSlots(i8, i7, i6 - 1);
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "offset" : super.mo260intParamNamew8GmfQM(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MoveNode extends Operation {
        public static final MoveNode INSTANCE = new MoveNode(3, 0, 2);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            applier.move(opIterator.m264getIntw8GmfQM(0), opIterator.m264getIntw8GmfQM(1), opIterator.m264getIntw8GmfQM(2));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "from" : IntParameter.m262equalsimpl0(i, 1) ? "to" : IntParameter.m262equalsimpl0(i, 2) ? "count" : super.mo260intParamNamew8GmfQM(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ObjectParameter {
        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m263equalsimpl0(int i, int i2) {
            return i == i2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PostInsertNodeFixup extends Operation {
        public static final PostInsertNodeFixup INSTANCE = new PostInsertNodeFixup(1, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            Anchor anchor = (Anchor) opIterator.m265getObject31yXWZQ(0);
            int m264getIntw8GmfQM = opIterator.m264getIntw8GmfQM(0);
            applier.up();
            anchor.getClass();
            int groupIndexToAddress = slotWriter.groupIndexToAddress(slotWriter.anchorIndex(anchor));
            applier.insertBottomUp(m264getIntw8GmfQM, SlotTableKt.access$isNode(slotWriter.groups, groupIndexToAddress) ? slotWriter.slots[slotWriter.dataIndexToDataAddress(slotWriter.dataIndex(slotWriter.groups, groupIndexToAddress))] : null);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "insertIndex" : super.mo260intParamNamew8GmfQM(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "groupAnchor" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ReleaseMovableGroupAtCurrent extends Operation {
        public static final ReleaseMovableGroupAtCurrent INSTANCE = new ReleaseMovableGroupAtCurrent(0, 3, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            final ControlledComposition controlledComposition = (ControlledComposition) opIterator.m265getObject31yXWZQ(0);
            CompositionContext compositionContext = (CompositionContext) opIterator.m265getObject31yXWZQ(1);
            final MovableContentStateReference movableContentStateReference = (MovableContentStateReference) opIterator.m265getObject31yXWZQ(2);
            SlotTable slotTable = new SlotTable();
            if (slotWriter.sourceInformationMap != null) {
                slotTable.collectSourceInformation();
            }
            if (slotWriter.calledByMap != null) {
                slotTable.calledByMap = new MutableIntObjectMap();
            }
            SlotWriter openWriter = slotTable.openWriter();
            try {
                openWriter.beginInsert();
                MovableContent movableContent = movableContentStateReference.content;
                Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
                openWriter.startGroup(126665345, movableContent, false, composer$Companion$Empty$1);
                SlotWriter.markGroup$default(openWriter);
                openWriter.update(movableContentStateReference.parameter);
                List moveTo = slotWriter.moveTo(movableContentStateReference.anchor, openWriter);
                openWriter.skipGroup();
                openWriter.endGroup();
                openWriter.endInsert();
                openWriter.close(true);
                MovableContentState movableContentState = new MovableContentState(slotTable);
                if (!moveTo.isEmpty()) {
                    int size = moveTo.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        }
                        Anchor anchor = (Anchor) moveTo.get(i);
                        if (slotTable.ownsAnchor(anchor)) {
                            int anchorIndex = slotTable.anchorIndex(anchor);
                            int access$slotAnchor = SlotTableKt.access$slotAnchor(slotTable.groups, anchorIndex);
                            int i2 = anchorIndex + 1;
                            if (((i2 < slotTable.groupsSize ? slotTable.groups[(i2 * 5) + 4] : slotTable.slots.length) - access$slotAnchor > 0 ? slotTable.slots[access$slotAnchor] : composer$Companion$Empty$1) instanceof RecomposeScopeImpl) {
                                RecomposeScopeOwner recomposeScopeOwner = new RecomposeScopeOwner() { // from class: androidx.compose.runtime.changelist.OperationKt$releaseMovableGroupAtCurrent$movableContentRecomposeScopeOwner$1
                                    @Override // androidx.compose.runtime.RecomposeScopeOwner
                                    public final InvalidationResult invalidate(RecomposeScopeImpl recomposeScopeImpl, Object obj) {
                                        InvalidationResult invalidationResult;
                                        ControlledComposition controlledComposition2 = ControlledComposition.this;
                                        RecomposeScopeOwner recomposeScopeOwner2 = controlledComposition2 instanceof RecomposeScopeOwner ? (RecomposeScopeOwner) controlledComposition2 : null;
                                        InvalidationResult invalidationResult2 = InvalidationResult.IGNORED;
                                        if (recomposeScopeOwner2 == null || (invalidationResult = recomposeScopeOwner2.invalidate(recomposeScopeImpl, obj)) == null) {
                                            invalidationResult = invalidationResult2;
                                        }
                                        if (invalidationResult != invalidationResult2) {
                                            return invalidationResult;
                                        }
                                        MovableContentStateReference movableContentStateReference2 = movableContentStateReference;
                                        movableContentStateReference2.invalidations = CollectionsKt.plus(movableContentStateReference2.invalidations, new Pair(recomposeScopeImpl, obj));
                                        return InvalidationResult.SCHEDULED;
                                    }

                                    @Override // androidx.compose.runtime.RecomposeScopeOwner
                                    public final void recomposeScopeReleased() {
                                    }

                                    @Override // androidx.compose.runtime.RecomposeScopeOwner
                                    public final void recordReadOf(Object obj) {
                                    }
                                };
                                openWriter = slotTable.openWriter();
                                try {
                                    RecomposeScopeImpl.Companion.adoptAnchoredScopes$runtime_release(openWriter, moveTo, recomposeScopeOwner);
                                    openWriter.close(true);
                                    break;
                                } finally {
                                }
                            }
                        }
                        i++;
                    }
                }
                compositionContext.movableContentStateReleased$runtime_release(movableContentStateReference, movableContentState);
            } finally {
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "composition" : ObjectParameter.m263equalsimpl0(i, 1) ? "parentCompositionContext" : ObjectParameter.m263equalsimpl0(i, 2) ? "reference" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Remember extends Operation {
        public static final Remember INSTANCE;

        static {
            int i = 1;
            INSTANCE = new Remember(0, i, i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            rememberEventDispatcher.currentRememberingList.add((RememberObserver) opIterator.m265getObject31yXWZQ(0));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "value" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RemoveCurrentGroup extends Operation {
        public static final RemoveCurrentGroup INSTANCE;

        static {
            int i = 0;
            INSTANCE = new RemoveCurrentGroup(i, i, 3);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            ComposerKt.removeCurrentGroup(slotWriter, rememberEventDispatcher);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RemoveNode extends Operation {
        public static final RemoveNode INSTANCE;

        static {
            int i = 2;
            INSTANCE = new RemoveNode(i, 0, i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            applier.remove(opIterator.m264getIntw8GmfQM(0), opIterator.m264getIntw8GmfQM(1));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "removeIndex" : IntParameter.m262equalsimpl0(i, 1) ? "count" : super.mo260intParamNamew8GmfQM(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ResetSlots extends Operation {
        public static final ResetSlots INSTANCE;

        static {
            int i = 0;
            INSTANCE = new ResetSlots(i, i, 3);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            if (slotWriter.insertCount != 0) {
                ComposerKt.composeImmediateRuntimeError("Cannot reset when inserting");
            }
            slotWriter.recalculateMarks();
            slotWriter.currentGroup = 0;
            slotWriter.currentGroupEnd = slotWriter.getCapacity() - slotWriter.groupGapLen;
            slotWriter.currentSlot = 0;
            slotWriter.currentSlotEnd = 0;
            slotWriter.nodeCount = 0;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SideEffect extends Operation {
        public static final SideEffect INSTANCE;

        static {
            int i = 1;
            INSTANCE = new SideEffect(0, i, i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            rememberEventDispatcher.sideEffects.add((Function0) opIterator.m265getObject31yXWZQ(0));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "effect" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SkipToEndOfCurrentGroup extends Operation {
        public static final SkipToEndOfCurrentGroup INSTANCE;

        static {
            int i = 0;
            INSTANCE = new SkipToEndOfCurrentGroup(i, i, 3);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            slotWriter.skipToGroupEnd();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StartResumingScope extends Operation {
        public static final StartResumingScope INSTANCE;

        static {
            int i = 1;
            INSTANCE = new StartResumingScope(0, i, i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "scope" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TrimParentValues extends Operation {
        public static final TrimParentValues INSTANCE = new TrimParentValues(1, 0, 2);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            int i;
            int i2;
            int m264getIntw8GmfQM = opIterator.m264getIntw8GmfQM(0);
            int slotsSize = slotWriter.getSlotsSize();
            int i3 = slotWriter.parent;
            int slotIndex = slotWriter.slotIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i3));
            int dataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i3 + 1));
            for (int max = Math.max(slotIndex, dataIndex - m264getIntw8GmfQM); max < dataIndex; max++) {
                Object obj = slotWriter.slots[slotWriter.dataIndexToDataAddress(max)];
                if (obj instanceof RememberObserverHolder) {
                    int i4 = slotsSize - max;
                    RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
                    Anchor anchor = rememberObserverHolder.after;
                    if (anchor == null || !anchor.getValid()) {
                        i = -1;
                        i2 = -1;
                    } else {
                        i = slotWriter.anchorIndex(anchor);
                        i2 = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(i);
                    }
                    rememberEventDispatcher.recordLeaving(rememberObserverHolder.wrapped, i4, i, i2);
                } else if (obj instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj).release();
                }
            }
            ComposerKt.runtimeCheck(m264getIntw8GmfQM > 0);
            int i5 = slotWriter.parent;
            int slotIndex2 = slotWriter.slotIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i5));
            int dataIndex2 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i5 + 1)) - m264getIntw8GmfQM;
            ComposerKt.runtimeCheck(dataIndex2 >= slotIndex2);
            slotWriter.removeSlots(dataIndex2, m264getIntw8GmfQM, i5);
            int i6 = slotWriter.currentSlot;
            if (i6 >= slotIndex2) {
                slotWriter.currentSlot = i6 - m264getIntw8GmfQM;
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "count" : super.mo260intParamNamew8GmfQM(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UpdateAnchoredValue extends Operation {
        public static final UpdateAnchoredValue INSTANCE = new UpdateAnchoredValue(1, 2);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            int i;
            int i2;
            Object m265getObject31yXWZQ = opIterator.m265getObject31yXWZQ(0);
            Anchor anchor = (Anchor) opIterator.m265getObject31yXWZQ(1);
            int m264getIntw8GmfQM = opIterator.m264getIntw8GmfQM(0);
            if (m265getObject31yXWZQ instanceof RememberObserverHolder) {
                rememberEventDispatcher.currentRememberingList.add(((RememberObserverHolder) m265getObject31yXWZQ).wrapped);
            }
            int anchorIndex = slotWriter.anchorIndex(anchor);
            int dataIndexToDataAddress = slotWriter.dataIndexToDataAddress(slotWriter.slotIndexOfGroupSlotIndex(anchorIndex, m264getIntw8GmfQM));
            Object[] objArr = slotWriter.slots;
            Object obj = objArr[dataIndexToDataAddress];
            objArr[dataIndexToDataAddress] = m265getObject31yXWZQ;
            if (!(obj instanceof RememberObserverHolder)) {
                if (obj instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj).release();
                    return;
                }
                return;
            }
            int slotsSize = slotWriter.getSlotsSize() - slotWriter.slotIndexOfGroupSlotIndex(anchorIndex, m264getIntw8GmfQM);
            RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
            Anchor anchor2 = rememberObserverHolder.after;
            if (anchor2 == null || !anchor2.getValid()) {
                i = -1;
                i2 = -1;
            } else {
                i = slotWriter.anchorIndex(anchor2);
                i2 = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(i);
            }
            rememberEventDispatcher.recordLeaving(rememberObserverHolder.wrapped, slotsSize, i, i2);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "groupSlotIndex" : super.mo260intParamNamew8GmfQM(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "value" : ObjectParameter.m263equalsimpl0(i, 1) ? "anchor" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UpdateAuxData extends Operation {
        public static final UpdateAuxData INSTANCE;

        static {
            int i = 1;
            INSTANCE = new UpdateAuxData(0, i, i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            slotWriter.updateAux(opIterator.m265getObject31yXWZQ(0));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "data" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UpdateNode extends Operation {
        public static final UpdateNode INSTANCE = new UpdateNode(0, 2, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            ((Function2) opIterator.m265getObject31yXWZQ(1)).invoke(applier.getCurrent(), opIterator.m265getObject31yXWZQ(0));
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "value" : ObjectParameter.m263equalsimpl0(i, 1) ? "block" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UpdateValue extends Operation {
        public static final UpdateValue INSTANCE = new UpdateValue(1, 1);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            Object m265getObject31yXWZQ = opIterator.m265getObject31yXWZQ(0);
            int m264getIntw8GmfQM = opIterator.m264getIntw8GmfQM(0);
            if (m265getObject31yXWZQ instanceof RememberObserverHolder) {
                rememberEventDispatcher.currentRememberingList.add(((RememberObserverHolder) m265getObject31yXWZQ).wrapped);
            }
            int dataIndexToDataAddress = slotWriter.dataIndexToDataAddress(slotWriter.slotIndexOfGroupSlotIndex(slotWriter.currentGroup, m264getIntw8GmfQM));
            Object[] objArr = slotWriter.slots;
            Object obj = objArr[dataIndexToDataAddress];
            objArr[dataIndexToDataAddress] = m265getObject31yXWZQ;
            if (obj instanceof RememberObserverHolder) {
                rememberEventDispatcher.recordLeaving(((RememberObserverHolder) obj).wrapped, slotWriter.getSlotsSize() - slotWriter.slotIndexOfGroupSlotIndex(slotWriter.currentGroup, m264getIntw8GmfQM), -1, -1);
            } else if (obj instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) obj).release();
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "groupSlotIndex" : super.mo260intParamNamew8GmfQM(i);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: objectParamName-31yXWZQ */
        public final String mo261objectParamName31yXWZQ(int i) {
            return ObjectParameter.m263equalsimpl0(i, 0) ? "value" : super.mo261objectParamName31yXWZQ(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Ups extends Operation {
        public static final Ups INSTANCE = new Ups(1, 0, 2);

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            int m264getIntw8GmfQM = opIterator.m264getIntw8GmfQM(0);
            for (int i = 0; i < m264getIntw8GmfQM; i++) {
                applier.up();
            }
        }

        @Override // androidx.compose.runtime.changelist.Operation
        /* renamed from: intParamName-w8GmfQM */
        public final String mo260intParamNamew8GmfQM(int i) {
            return IntParameter.m262equalsimpl0(i, 0) ? "count" : super.mo260intParamNamew8GmfQM(i);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UseCurrentNode extends Operation {
        public static final UseCurrentNode INSTANCE;

        static {
            int i = 0;
            INSTANCE = new UseCurrentNode(i, i, 3);
        }

        @Override // androidx.compose.runtime.changelist.Operation
        public final void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
            applier.reuse();
        }
    }

    public Operation(int i, int i2) {
        this.ints = i;
        this.objects = i2;
    }

    public abstract void execute(Operations.OpIterator opIterator, Applier applier, SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher);

    /* renamed from: intParamName-w8GmfQM, reason: not valid java name */
    public String mo260intParamNamew8GmfQM(int i) {
        return "IntParameter(" + i + ')';
    }

    /* renamed from: objectParamName-31yXWZQ, reason: not valid java name */
    public String mo261objectParamName31yXWZQ(int i) {
        return "ObjectParameter(" + i + ')';
    }

    public final String toString() {
        String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        return simpleName == null ? "" : simpleName;
    }

    public /* synthetic */ Operation(int i, int i2, int i3) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }
}
