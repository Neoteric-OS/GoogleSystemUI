package androidx.compose.runtime;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Trace;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.changelist.ComposerChangeListWriter;
import androidx.compose.runtime.changelist.FixupList;
import androidx.compose.runtime.changelist.Operation;
import androidx.compose.runtime.changelist.Operations;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.PersistentCompositionLocalHashMap;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComposerImpl implements Composer {
    public final MutableScatterSet.MutableSetWrapper abandonSet;
    public final AbstractApplier applier;
    public final ComposerChangeListWriter changeListWriter;
    public final ChangeList changes;
    public int childrenComposing;
    public final CompositionImpl composition;
    public int compositionToken;
    public int compoundKeyHash;
    public ChangeList deferredChanges;
    public final ComposerImpl$derivedStateObserver$1 derivedStateObserver;
    public boolean forceRecomposeScopes;
    public int groupNodeCount;
    public Anchor insertAnchor;
    public FixupList insertFixups;
    public SlotTable insertTable;
    public boolean inserting;
    public final ArrayList invalidateStack;
    public boolean isComposing;
    public final ChangeList lateChanges;
    public int[] nodeCountOverrides;
    public MutableIntIntMap nodeCountVirtualOverrides;
    public boolean nodeExpected;
    public int nodeIndex;
    public final CompositionContext parentContext;
    public Pending pending;
    public PersistentCompositionLocalMap providerCache;
    public MutableIntObjectMap providerUpdates;
    public boolean providersInvalid;
    public int rGroupIndex;
    public SlotReader reader;
    public boolean reusing;
    public final SlotTable slotTable;
    public boolean sourceMarkersEnabled;
    public SlotWriter writer;
    public boolean writerHasAProvider;
    public final ArrayList pendingStack = new ArrayList();
    public final IntStack parentStateStack = new IntStack();
    public final List invalidations = new ArrayList();
    public final IntStack entersStack = new IntStack();
    public PersistentCompositionLocalMap parentProvider = PersistentCompositionLocalHashMap.Empty;
    public final IntStack providersInvalidStack = new IntStack();
    public int reusingGroup = -1;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class CompositionContextImpl extends CompositionContext {
        public final boolean collectingParameterInformation;
        public final boolean collectingSourceInformation;
        public final Set composers = new LinkedHashSet();
        public final MutableState compositionLocalScope$delegate = new ParcelableSnapshotMutableState(PersistentCompositionLocalHashMap.Empty, ReferentialEqualityPolicy.INSTANCE);
        public final int compoundHashKey;
        public Set inspectionTables;
        public final CompositionObserverHolder observerHolder;

        public CompositionContextImpl(int i, boolean z, boolean z2, CompositionObserverHolder compositionObserverHolder) {
            this.compoundHashKey = i;
            this.collectingParameterInformation = z;
            this.collectingSourceInformation = z2;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void composeInitial$runtime_release(CompositionImpl compositionImpl, ComposableLambdaImpl composableLambdaImpl) {
            ComposerImpl.this.parentContext.composeInitial$runtime_release(compositionImpl, composableLambdaImpl);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void deletedMovableContent$runtime_release(MovableContentStateReference movableContentStateReference) {
            ComposerImpl.this.parentContext.deletedMovableContent$runtime_release(movableContentStateReference);
        }

        public final void dispose() {
            if (this.composers.isEmpty()) {
                return;
            }
            Set set = this.inspectionTables;
            if (set != null) {
                for (ComposerImpl composerImpl : this.composers) {
                    Iterator it = ((HashSet) set).iterator();
                    while (it.hasNext()) {
                        ((Set) it.next()).remove(composerImpl.slotTable);
                    }
                }
            }
            this.composers.clear();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void doneComposing$runtime_release() {
            ComposerImpl composerImpl = ComposerImpl.this;
            composerImpl.childrenComposing--;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final boolean getCollectingCallByInformation$runtime_release() {
            return ComposerImpl.this.parentContext.getCollectingCallByInformation$runtime_release();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final boolean getCollectingParameterInformation$runtime_release() {
            return this.collectingParameterInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final boolean getCollectingSourceInformation$runtime_release() {
            return this.collectingSourceInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final PersistentCompositionLocalMap getCompositionLocalScope$runtime_release() {
            return (PersistentCompositionLocalMap) ((SnapshotMutableStateImpl) this.compositionLocalScope$delegate).getValue();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final int getCompoundHashKey$runtime_release() {
            return this.compoundHashKey;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final CoroutineContext getEffectCoroutineContext() {
            return ComposerImpl.this.parentContext.getEffectCoroutineContext();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void insertMovableContent$runtime_release(MovableContentStateReference movableContentStateReference) {
            ComposerImpl.this.parentContext.insertMovableContent$runtime_release(movableContentStateReference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void invalidate$runtime_release(CompositionImpl compositionImpl) {
            ComposerImpl composerImpl = ComposerImpl.this;
            composerImpl.parentContext.invalidate$runtime_release(composerImpl.composition);
            composerImpl.parentContext.invalidate$runtime_release(compositionImpl);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void movableContentStateReleased$runtime_release(MovableContentStateReference movableContentStateReference, MovableContentState movableContentState) {
            ComposerImpl.this.parentContext.movableContentStateReleased$runtime_release(movableContentStateReference, movableContentState);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference movableContentStateReference) {
            return ComposerImpl.this.parentContext.movableContentStateResolve$runtime_release(movableContentStateReference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void recordInspectionTable$runtime_release(Set set) {
            Set set2 = this.inspectionTables;
            if (set2 == null) {
                set2 = new HashSet();
                this.inspectionTables = set2;
            }
            set2.add(set);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void registerComposer$runtime_release(ComposerImpl composerImpl) {
            this.composers.add(composerImpl);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void reportRemovedComposition$runtime_release(CompositionImpl compositionImpl) {
            ComposerImpl.this.parentContext.reportRemovedComposition$runtime_release(compositionImpl);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void startComposing$runtime_release() {
            ComposerImpl.this.childrenComposing++;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void unregisterComposer$runtime_release(Composer composer) {
            Set set = this.inspectionTables;
            if (set != null) {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((Set) it.next()).remove(((ComposerImpl) composer).slotTable);
                }
            }
            TypeIntrinsics.asMutableCollection(this.composers).remove(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public final void unregisterComposition$runtime_release(CompositionImpl compositionImpl) {
            ComposerImpl.this.parentContext.unregisterComposition$runtime_release(compositionImpl);
        }
    }

    /* JADX WARN: Type inference failed for: r1v11, types: [androidx.compose.runtime.ComposerImpl$derivedStateObserver$1] */
    public ComposerImpl(AbstractApplier abstractApplier, CompositionContext compositionContext, SlotTable slotTable, MutableScatterSet.MutableSetWrapper mutableSetWrapper, ChangeList changeList, ChangeList changeList2, CompositionImpl compositionImpl) {
        this.applier = abstractApplier;
        this.parentContext = compositionContext;
        this.slotTable = slotTable;
        this.abandonSet = mutableSetWrapper;
        this.changes = changeList;
        this.lateChanges = changeList2;
        this.composition = compositionImpl;
        this.sourceMarkersEnabled = compositionContext.getCollectingSourceInformation$runtime_release() || compositionContext.getCollectingCallByInformation$runtime_release();
        this.derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.ComposerImpl$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public final void done() {
                ComposerImpl composerImpl = ComposerImpl.this;
                composerImpl.childrenComposing--;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public final void start() {
                ComposerImpl.this.childrenComposing++;
            }
        };
        this.invalidateStack = new ArrayList();
        SlotReader openReader = slotTable.openReader();
        openReader.close();
        this.reader = openReader;
        SlotTable slotTable2 = new SlotTable();
        if (compositionContext.getCollectingSourceInformation$runtime_release()) {
            slotTable2.collectSourceInformation();
        }
        if (compositionContext.getCollectingCallByInformation$runtime_release()) {
            slotTable2.calledByMap = new MutableIntObjectMap();
        }
        this.insertTable = slotTable2;
        SlotWriter openWriter = slotTable2.openWriter();
        openWriter.close(true);
        this.writer = openWriter;
        this.changeListWriter = new ComposerChangeListWriter(this, changeList);
        SlotReader openReader2 = this.insertTable.openReader();
        try {
            Anchor anchor = openReader2.anchor(0);
            openReader2.close();
            this.insertAnchor = anchor;
            this.insertFixups = new FixupList();
        } catch (Throwable th) {
            openReader2.close();
            throw th;
        }
    }

    public static final int reportFreeMovableContent$reportGroup(ComposerImpl composerImpl, int i, boolean z, int i2) {
        SlotReader slotReader = composerImpl.reader;
        int[] iArr = slotReader.groups;
        int i3 = i * 5;
        boolean z2 = (iArr[i3 + 1] & 134217728) != 0;
        ComposerChangeListWriter composerChangeListWriter = composerImpl.changeListWriter;
        if (!z2) {
            if (!SlotTableKt.access$containsMark(iArr, i)) {
                if (SlotTableKt.access$isNode(iArr, i)) {
                    return 1;
                }
                return SlotTableKt.access$nodeCount(iArr, i);
            }
            int i4 = iArr[i3 + 3] + i;
            int i5 = 0;
            for (int i6 = i + 1; i6 < i4; i6 += iArr[(i6 * 5) + 3]) {
                boolean access$isNode = SlotTableKt.access$isNode(iArr, i6);
                if (access$isNode) {
                    composerChangeListWriter.realizeNodeMovementOperations();
                    Object node = slotReader.node(i6);
                    composerChangeListWriter.realizeNodeMovementOperations();
                    composerChangeListWriter.pendingDownNodes.add(node);
                }
                i5 += reportFreeMovableContent$reportGroup(composerImpl, i6, access$isNode || z, access$isNode ? 0 : i2 + i5);
                if (access$isNode) {
                    composerChangeListWriter.realizeNodeMovementOperations();
                    composerChangeListWriter.moveUp();
                }
            }
            if (SlotTableKt.access$isNode(iArr, i)) {
                return 1;
            }
            return i5;
        }
        int i7 = iArr[i3];
        Object objectKey = slotReader.objectKey(iArr, i);
        CompositionContext compositionContext = composerImpl.parentContext;
        if (i7 != 126665345 || !(objectKey instanceof MovableContent)) {
            if (i7 != 206 || !Intrinsics.areEqual(objectKey, ComposerKt.reference)) {
                if (SlotTableKt.access$isNode(iArr, i)) {
                    return 1;
                }
                return SlotTableKt.access$nodeCount(iArr, i);
            }
            Object groupGet = slotReader.groupGet(i, 0);
            CompositionContextHolder compositionContextHolder = groupGet instanceof CompositionContextHolder ? (CompositionContextHolder) groupGet : null;
            if (compositionContextHolder != null) {
                for (ComposerImpl composerImpl2 : compositionContextHolder.ref.composers) {
                    SlotTable slotTable = composerImpl2.slotTable;
                    if (slotTable.groupsSize > 0 && SlotTableKt.access$containsMark(slotTable.groups, 0)) {
                        CompositionImpl compositionImpl = composerImpl2.composition;
                        synchronized (compositionImpl.lock) {
                            compositionImpl.drainPendingModificationsOutOfBandLocked();
                            MutableScatterMap mutableScatterMap = compositionImpl.invalidations;
                            compositionImpl.invalidations = ScopeMap.m269constructorimpl$default();
                            try {
                                compositionImpl.composer.m257updateComposerInvalidationsRY85e9Y(mutableScatterMap);
                            } catch (Exception e) {
                                compositionImpl.invalidations = mutableScatterMap;
                                throw e;
                            }
                        }
                        ChangeList changeList = new ChangeList();
                        composerImpl2.deferredChanges = changeList;
                        SlotReader openReader = composerImpl2.slotTable.openReader();
                        try {
                            composerImpl2.reader = openReader;
                            ComposerChangeListWriter composerChangeListWriter2 = composerImpl2.changeListWriter;
                            ChangeList changeList2 = composerChangeListWriter2.changeList;
                            try {
                                composerChangeListWriter2.changeList = changeList;
                                reportFreeMovableContent$reportGroup(composerImpl2, 0, false, 0);
                                composerImpl2.changeListWriter.realizeNodeMovementOperations();
                                ComposerChangeListWriter composerChangeListWriter3 = composerImpl2.changeListWriter;
                                composerChangeListWriter3.pushPendingUpsAndDowns();
                                if (composerChangeListWriter3.startedGroup) {
                                    ChangeList changeList3 = composerChangeListWriter3.changeList;
                                    changeList3.getClass();
                                    changeList3.operations.push(Operation.SkipToEndOfCurrentGroup.INSTANCE);
                                    if (composerChangeListWriter3.startedGroup) {
                                        composerChangeListWriter3.realizeOperationLocation(false);
                                        composerChangeListWriter3.realizeOperationLocation(false);
                                        ChangeList changeList4 = composerChangeListWriter3.changeList;
                                        changeList4.getClass();
                                        changeList4.operations.push(Operation.EndCurrentGroup.INSTANCE);
                                        composerChangeListWriter3.startedGroup = false;
                                        composerChangeListWriter2.changeList = changeList2;
                                    }
                                }
                                composerChangeListWriter2.changeList = changeList2;
                            } catch (Throwable th) {
                                composerChangeListWriter2.changeList = changeList2;
                                throw th;
                            }
                        } finally {
                            openReader.close();
                        }
                    }
                    compositionContext.reportRemovedComposition$runtime_release(composerImpl2.composition);
                }
            }
            return SlotTableKt.access$nodeCount(iArr, i);
        }
        MovableContent movableContent = (MovableContent) objectKey;
        Object groupGet2 = slotReader.groupGet(i, 0);
        Anchor anchor = slotReader.anchor(i);
        int i8 = iArr[i3 + 3] + i;
        List list = composerImpl.invalidations;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ArrayList arrayList = new ArrayList();
        int findLocation = ComposerKt.findLocation(i, list);
        if (findLocation < 0) {
            findLocation = -(findLocation + 1);
        }
        while (true) {
            ArrayList arrayList2 = (ArrayList) list;
            if (findLocation >= arrayList2.size()) {
                break;
            }
            Invalidation invalidation = (Invalidation) arrayList2.get(findLocation);
            if (invalidation.location >= i8) {
                break;
            }
            arrayList.add(invalidation);
            findLocation++;
        }
        ArrayList arrayList3 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            Invalidation invalidation2 = (Invalidation) arrayList.get(i9);
            arrayList3.add(new Pair(invalidation2.scope, invalidation2.instances));
        }
        MovableContentStateReference movableContentStateReference = new MovableContentStateReference(movableContent, groupGet2, composerImpl.composition, composerImpl.slotTable, anchor, arrayList3, composerImpl.currentCompositionLocalScope(i));
        compositionContext.deletedMovableContent$runtime_release(movableContentStateReference);
        composerChangeListWriter.recordSlotEditing();
        ChangeList changeList5 = composerChangeListWriter.changeList;
        changeList5.getClass();
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        Operations operations = changeList5.operations;
        operations.pushOp(releaseMovableGroupAtCurrent);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 0, composerImpl.composition);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 1, compositionContext);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 2, movableContentStateReference);
        int i10 = operations.pushedIntMask;
        int i11 = releaseMovableGroupAtCurrent.ints;
        int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i11);
        int i12 = releaseMovableGroupAtCurrent.objects;
        if (i10 != access$createExpectedArgMask || operations.pushedObjectMask != Operations.access$createExpectedArgMask(operations, i12)) {
            StringBuilder sb = new StringBuilder();
            int i13 = 0;
            for (int i14 = 0; i14 < i11; i14++) {
                if (((1 << i14) & operations.pushedIntMask) != 0) {
                    if (i13 > 0) {
                        sb.append(", ");
                    }
                    sb.append(releaseMovableGroupAtCurrent.mo260intParamNamew8GmfQM(i14));
                    i13++;
                }
            }
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            int i15 = 0;
            for (int i16 = 0; i16 < i12; i16++) {
                if (((1 << i16) & operations.pushedObjectMask) != 0) {
                    if (i13 > 0) {
                        sb3.append(", ");
                    }
                    sb3.append(releaseMovableGroupAtCurrent.mo261objectParamName31yXWZQ(i16));
                    i15++;
                }
            }
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder("Error while pushing ");
            sb5.append(releaseMovableGroupAtCurrent);
            sb5.append(". Not all arguments were provided. Missing ");
            sb5.append(i13);
            sb5.append(" int arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i15, " object arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
        }
        if (!z) {
            return SlotTableKt.access$nodeCount(iArr, i);
        }
        composerChangeListWriter.realizeNodeMovementOperations();
        composerChangeListWriter.pushPendingUpsAndDowns();
        ComposerImpl composerImpl3 = composerChangeListWriter.composer;
        int access$nodeCount = SlotTableKt.access$isNode(composerImpl3.reader.groups, i) ? 1 : SlotTableKt.access$nodeCount(composerImpl3.reader.groups, i);
        if (access$nodeCount > 0) {
            composerChangeListWriter.removeNode(i2, access$nodeCount);
        }
        return 0;
    }

    public final void abortRoot() {
        cleanUpCompose();
        this.pendingStack.clear();
        this.parentStateStack.tos = 0;
        this.entersStack.tos = 0;
        this.providersInvalidStack.tos = 0;
        this.providerUpdates = null;
        FixupList fixupList = this.insertFixups;
        fixupList.pendingOperations.clear();
        fixupList.operations.clear();
        this.compoundKeyHash = 0;
        this.childrenComposing = 0;
        this.nodeExpected = false;
        this.inserting = false;
        this.reusing = false;
        this.isComposing = false;
        this.reusingGroup = -1;
        SlotReader slotReader = this.reader;
        if (!slotReader.closed) {
            slotReader.close();
        }
        if (this.writer.closed) {
            return;
        }
        forceFreshInsertTable();
    }

    public final void apply(Object obj, Function2 function2) {
        int i = 1;
        if (this.inserting) {
            FixupList fixupList = this.insertFixups;
            fixupList.getClass();
            Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
            Operations operations = fixupList.operations;
            operations.pushOp(updateNode);
            Operations.WriteScope.m267setObjectDKhxnng(operations, 0, obj);
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, function2);
            Operations.WriteScope.m267setObjectDKhxnng(operations, 1, function2);
            int i2 = operations.pushedIntMask;
            int i3 = updateNode.ints;
            int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i3);
            int i4 = updateNode.objects;
            if (i2 == access$createExpectedArgMask && operations.pushedObjectMask == Operations.access$createExpectedArgMask(operations, i4)) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                if (((1 << i6) & operations.pushedIntMask) != 0) {
                    if (i5 > 0) {
                        sb.append(", ");
                    }
                    sb.append(updateNode.mo260intParamNamew8GmfQM(i6));
                    i5++;
                }
            }
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            int i7 = 0;
            int i8 = 0;
            while (i8 < i4) {
                if (((i << i8) & operations.pushedObjectMask) != 0) {
                    if (i5 > 0) {
                        sb3.append(", ");
                    }
                    sb3.append(updateNode.mo261objectParamName31yXWZQ(i8));
                    i7++;
                }
                i8++;
                i = 1;
            }
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder("Error while pushing ");
            sb5.append(updateNode);
            sb5.append(". Not all arguments were provided. Missing ");
            sb5.append(i5);
            sb5.append(" int arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i7, " object arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
            return;
        }
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        composerChangeListWriter.pushPendingUpsAndDowns();
        ChangeList changeList = composerChangeListWriter.changeList;
        changeList.getClass();
        Operation.UpdateNode updateNode2 = Operation.UpdateNode.INSTANCE;
        Operations operations2 = changeList.operations;
        operations2.pushOp(updateNode2);
        Operations.WriteScope.m267setObjectDKhxnng(operations2, 0, obj);
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, function2);
        Operations.WriteScope.m267setObjectDKhxnng(operations2, 1, function2);
        int i9 = operations2.pushedIntMask;
        int i10 = updateNode2.ints;
        int access$createExpectedArgMask2 = Operations.access$createExpectedArgMask(operations2, i10);
        int i11 = updateNode2.objects;
        if (i9 == access$createExpectedArgMask2 && operations2.pushedObjectMask == Operations.access$createExpectedArgMask(operations2, i11)) {
            return;
        }
        StringBuilder sb6 = new StringBuilder();
        int i12 = 0;
        for (int i13 = 0; i13 < i10; i13++) {
            if (((1 << i13) & operations2.pushedIntMask) != 0) {
                if (i12 > 0) {
                    sb6.append(", ");
                }
                sb6.append(updateNode2.mo260intParamNamew8GmfQM(i13));
                i12++;
            }
        }
        String sb7 = sb6.toString();
        StringBuilder sb8 = new StringBuilder();
        int i14 = 0;
        for (int i15 = 0; i15 < i11; i15++) {
            if (((1 << i15) & operations2.pushedObjectMask) != 0) {
                if (i12 > 0) {
                    sb8.append(", ");
                }
                sb8.append(updateNode2.mo261objectParamName31yXWZQ(i15));
                i14++;
            }
        }
        String sb9 = sb8.toString();
        StringBuilder sb10 = new StringBuilder("Error while pushing ");
        sb10.append(updateNode2);
        sb10.append(". Not all arguments were provided. Missing ");
        sb10.append(i12);
        sb10.append(" int arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb7, ") and ", i14, " object arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb9, ").");
    }

    public final boolean changed(Object obj) {
        if (Intrinsics.areEqual(nextSlot(), obj)) {
            return false;
        }
        updateValue(obj);
        return true;
    }

    public final boolean changedInstance(Object obj) {
        if (nextSlot() == obj) {
            return false;
        }
        updateValue(obj);
        return true;
    }

    public final void cleanUpCompose() {
        this.pending = null;
        this.nodeIndex = 0;
        this.groupNodeCount = 0;
        this.compoundKeyHash = 0;
        this.nodeExpected = false;
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        composerChangeListWriter.startedGroup = false;
        composerChangeListWriter.startedGroups.tos = 0;
        composerChangeListWriter.writersReaderDelta = 0;
        this.invalidateStack.clear();
        this.nodeCountOverrides = null;
        this.nodeCountVirtualOverrides = null;
    }

    public final int compoundKeyOf(int i, int i2, int i3, int i4) {
        int i5;
        Object aux;
        if (i == i3) {
            return i4;
        }
        SlotReader slotReader = this.reader;
        boolean access$hasObjectKey = SlotTableKt.access$hasObjectKey(slotReader.groups, i);
        int[] iArr = slotReader.groups;
        if (access$hasObjectKey) {
            Object objectKey = slotReader.objectKey(iArr, i);
            i5 = objectKey != null ? objectKey instanceof Enum ? ((Enum) objectKey).ordinal() : objectKey instanceof MovableContent ? 126665345 : objectKey.hashCode() : 0;
        } else {
            int i6 = iArr[i * 5];
            if (i6 == 207 && (aux = slotReader.aux(iArr, i)) != null && !aux.equals(Composer.Companion.Empty)) {
                i6 = aux.hashCode();
            }
            i5 = i6;
        }
        if (i5 == 126665345) {
            return i5;
        }
        int i7 = this.reader.groups[(i * 5) + 2];
        if (i7 != i3) {
            i4 = compoundKeyOf(i7, rGroupIndexOf(i7), i3, i4);
        }
        if (SlotTableKt.access$hasObjectKey(this.reader.groups, i)) {
            i2 = 0;
        }
        return Integer.rotateLeft(Integer.rotateLeft(i4, 3) ^ i5, 3) ^ i2;
    }

    public final Object consume(CompositionLocal compositionLocal) {
        return CompositionLocalMapKt.read(currentCompositionLocalScope(), compositionLocal);
    }

    public final void createNode(Function0 function0) {
        FixupList fixupList;
        int i;
        Anchor anchor;
        int i2;
        int i3;
        int i4;
        if (!this.nodeExpected) {
            ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected");
        }
        this.nodeExpected = false;
        if (!this.inserting) {
            ComposerKt.composeImmediateRuntimeError("createNode() can only be called when inserting");
        }
        IntStack intStack = this.parentStateStack;
        int i5 = intStack.slots[intStack.tos - 1];
        SlotWriter slotWriter = this.writer;
        Anchor anchor2 = slotWriter.anchor(slotWriter.parent);
        this.groupNodeCount++;
        FixupList fixupList2 = this.insertFixups;
        Operation.InsertNodeFixup insertNodeFixup = Operation.InsertNodeFixup.INSTANCE;
        Operations operations = fixupList2.operations;
        operations.pushOp(insertNodeFixup);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 0, function0);
        Operations.WriteScope.m266setIntA6tL2VI(operations, 0, i5);
        Operations.WriteScope.m267setObjectDKhxnng(operations, 1, anchor2);
        if (operations.pushedIntMask == Operations.access$createExpectedArgMask(operations, 1) && operations.pushedObjectMask == Operations.access$createExpectedArgMask(operations, 2)) {
            fixupList = fixupList2;
            i = i5;
            anchor = anchor2;
        } else {
            StringBuilder sb = new StringBuilder();
            if ((operations.pushedIntMask & 1) != 0) {
                sb.append(insertNodeFixup.mo260intParamNamew8GmfQM(0));
                i4 = 1;
            } else {
                i4 = 0;
            }
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            fixupList = fixupList2;
            i = i5;
            anchor = anchor2;
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 2; i7 < i8; i8 = 2) {
                if (((1 << i7) & operations.pushedObjectMask) != 0) {
                    if (i4 > 0) {
                        sb3.append(", ");
                    }
                    sb3.append(insertNodeFixup.mo261objectParamName31yXWZQ(i7));
                    i6++;
                }
                i7++;
            }
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder("Error while pushing ");
            sb5.append(insertNodeFixup);
            sb5.append(". Not all arguments were provided. Missing ");
            sb5.append(i4);
            sb5.append(" int arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i6, " object arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
        }
        Operation.PostInsertNodeFixup postInsertNodeFixup = Operation.PostInsertNodeFixup.INSTANCE;
        Operations operations2 = fixupList.pendingOperations;
        operations2.pushOp(postInsertNodeFixup);
        Operations.WriteScope.m266setIntA6tL2VI(operations2, 0, i);
        Operations.WriteScope.m267setObjectDKhxnng(operations2, 0, anchor);
        if (operations2.pushedIntMask == Operations.access$createExpectedArgMask(operations2, 1) && operations2.pushedObjectMask == Operations.access$createExpectedArgMask(operations2, 1)) {
            return;
        }
        StringBuilder sb6 = new StringBuilder();
        if ((operations2.pushedIntMask & 1) != 0) {
            sb6.append(postInsertNodeFixup.mo260intParamNamew8GmfQM(0));
            i2 = 1;
        } else {
            i2 = 0;
        }
        String sb7 = sb6.toString();
        StringBuilder sb8 = new StringBuilder();
        if ((operations2.pushedObjectMask & 1) != 0) {
            if (i2 > 0) {
                sb8.append(", ");
            }
            sb8.append(postInsertNodeFixup.mo261objectParamName31yXWZQ(0));
            i3 = 1;
        } else {
            i3 = 0;
        }
        String sb9 = sb8.toString();
        StringBuilder sb10 = new StringBuilder("Error while pushing ");
        sb10.append(postInsertNodeFixup);
        sb10.append(". Not all arguments were provided. Missing ");
        sb10.append(i2);
        sb10.append(" int arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb7, ") and ", i3, " object arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb9, ").");
    }

    public final PersistentCompositionLocalMap currentCompositionLocalScope(int i) {
        PersistentCompositionLocalMap persistentCompositionLocalMap;
        Object obj;
        Object obj2;
        if (this.inserting && this.writerHasAProvider) {
            int i2 = this.writer.parent;
            while (i2 > 0) {
                SlotWriter slotWriter = this.writer;
                if (slotWriter.groups[slotWriter.groupIndexToAddress(i2) * 5] == 202) {
                    SlotWriter slotWriter2 = this.writer;
                    int groupIndexToAddress = slotWriter2.groupIndexToAddress(i2);
                    if (SlotTableKt.access$hasObjectKey(slotWriter2.groups, groupIndexToAddress)) {
                        Object[] objArr = slotWriter2.slots;
                        int[] iArr = slotWriter2.groups;
                        int i3 = groupIndexToAddress * 5;
                        obj = objArr[SlotTableKt.countOneBits(iArr[i3 + 1] >> 30) + iArr[i3 + 4]];
                    } else {
                        obj = null;
                    }
                    if (Intrinsics.areEqual(obj, ComposerKt.compositionLocalMap)) {
                        SlotWriter slotWriter3 = this.writer;
                        int groupIndexToAddress2 = slotWriter3.groupIndexToAddress(i2);
                        if (SlotTableKt.access$hasAux(slotWriter3.groups, groupIndexToAddress2)) {
                            Object[] objArr2 = slotWriter3.slots;
                            int[] iArr2 = slotWriter3.groups;
                            obj2 = objArr2[SlotTableKt.countOneBits(iArr2[(groupIndexToAddress2 * 5) + 1] >> 29) + slotWriter3.dataIndex(iArr2, groupIndexToAddress2)];
                        } else {
                            obj2 = Composer.Companion.Empty;
                        }
                        PersistentCompositionLocalMap persistentCompositionLocalMap2 = (PersistentCompositionLocalMap) obj2;
                        this.providerCache = persistentCompositionLocalMap2;
                        return persistentCompositionLocalMap2;
                    }
                }
                SlotWriter slotWriter4 = this.writer;
                i2 = slotWriter4.parent(slotWriter4.groups, i2);
            }
        }
        if (this.reader.groupsSize > 0) {
            while (i > 0) {
                SlotReader slotReader = this.reader;
                int i4 = i * 5;
                int[] iArr3 = slotReader.groups;
                if (iArr3[i4] == 202 && Intrinsics.areEqual(slotReader.objectKey(iArr3, i), ComposerKt.compositionLocalMap)) {
                    MutableIntObjectMap mutableIntObjectMap = this.providerUpdates;
                    if (mutableIntObjectMap == null || (persistentCompositionLocalMap = (PersistentCompositionLocalMap) mutableIntObjectMap.get(i)) == null) {
                        SlotReader slotReader2 = this.reader;
                        persistentCompositionLocalMap = (PersistentCompositionLocalMap) slotReader2.aux(slotReader2.groups, i);
                    }
                    this.providerCache = persistentCompositionLocalMap;
                    return persistentCompositionLocalMap;
                }
                i = this.reader.groups[i4 + 2];
            }
        }
        PersistentCompositionLocalMap persistentCompositionLocalMap3 = this.parentProvider;
        this.providerCache = persistentCompositionLocalMap3;
        return persistentCompositionLocalMap3;
    }

    public final void deactivateToEndGroup(boolean z) {
        if (this.groupNodeCount != 0) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling dactivateToEndGroup");
        }
        if (this.inserting) {
            return;
        }
        if (!z) {
            skipReaderToGroupEnd();
            return;
        }
        SlotReader slotReader = this.reader;
        int i = slotReader.currentGroup;
        int i2 = slotReader.currentEnd;
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        composerChangeListWriter.getClass();
        composerChangeListWriter.realizeOperationLocation(false);
        ChangeList changeList = composerChangeListWriter.changeList;
        changeList.getClass();
        changeList.operations.push(Operation.DeactivateCurrentGroup.INSTANCE);
        ComposerKt.access$removeRange(i, i2, this.invalidations);
        this.reader.skipToGroupEnd();
    }

    /* renamed from: doCompose-aFTiNEg, reason: not valid java name */
    public final void m254doComposeaFTiNEg(MutableScatterMap mutableScatterMap, ComposableLambdaImpl composableLambdaImpl) {
        if (this.isComposing) {
            ComposerKt.composeImmediateRuntimeError("Reentrant composition is not supported");
        }
        Trace.beginSection("Compose:recompose");
        try {
            this.compositionToken = SnapshotKt.currentSnapshot().getId();
            this.providerUpdates = null;
            m257updateComposerInvalidationsRY85e9Y(mutableScatterMap);
            this.nodeIndex = 0;
            this.isComposing = true;
            try {
                startRoot();
                Object nextSlot = nextSlot();
                if (nextSlot != composableLambdaImpl && composableLambdaImpl != null) {
                    updateValue(composableLambdaImpl);
                }
                ComposerImpl$derivedStateObserver$1 composerImpl$derivedStateObserver$1 = this.derivedStateObserver;
                MutableVector derivedStateObservers = SnapshotStateKt.derivedStateObservers();
                try {
                    derivedStateObservers.add(composerImpl$derivedStateObserver$1);
                    if (composableLambdaImpl != null) {
                        startGroup(200, ComposerKt.invocation);
                        TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, composableLambdaImpl);
                        composableLambdaImpl.invoke((Object) this, (Object) 1);
                        end(false);
                    } else if (!this.providersInvalid || nextSlot == null || nextSlot.equals(Composer.Companion.Empty)) {
                        skipCurrentGroup();
                    } else {
                        startGroup(200, ComposerKt.invocation);
                        TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, nextSlot);
                        Function2 function2 = (Function2) nextSlot;
                        TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, function2);
                        function2.invoke(this, 1);
                        end(false);
                    }
                    derivedStateObservers.removeAt(derivedStateObservers.size - 1);
                    endRoot();
                    this.isComposing = false;
                    this.invalidations.clear();
                    ComposerKt.runtimeCheck(this.writer.closed);
                    forceFreshInsertTable();
                } catch (Throwable th) {
                    derivedStateObservers.removeAt(derivedStateObservers.size - 1);
                    throw th;
                }
            } catch (Throwable th2) {
                this.isComposing = false;
                this.invalidations.clear();
                abortRoot();
                ComposerKt.runtimeCheck(this.writer.closed);
                forceFreshInsertTable();
                throw th2;
            }
        } finally {
            Trace.endSection();
        }
    }

    public final void doRecordDownsFor(int i, int i2) {
        if (i <= 0 || i == i2) {
            return;
        }
        doRecordDownsFor(this.reader.groups[(i * 5) + 2], i2);
        if (SlotTableKt.access$isNode(this.reader.groups, i)) {
            Object node = this.reader.node(i);
            ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
            composerChangeListWriter.realizeNodeMovementOperations();
            composerChangeListWriter.pendingDownNodes.add(node);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:184:0x04be  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x070f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void end(boolean r42) {
        /*
            Method dump skipped, instructions count: 1981
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.end(boolean):void");
    }

    public final void endDefaults() {
        end(false);
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null) {
            int i = currentRecomposeScope$runtime_release.flags;
            if ((i & 1) != 0) {
                currentRecomposeScope$runtime_release.flags = i | 2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x01e3, code lost:
    
        if (r0.forceRecomposeScopes != false) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.runtime.RecomposeScopeImpl endRestartGroup() {
        /*
            Method dump skipped, instructions count: 527
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.endRestartGroup():androidx.compose.runtime.RecomposeScopeImpl");
    }

    public final void endReusableGroup() {
        if (this.reusing && this.reader.parent == this.reusingGroup) {
            this.reusingGroup = -1;
            this.reusing = false;
        }
        end(false);
    }

    public final void endRoot() {
        end(false);
        this.parentContext.doneComposing$runtime_release();
        end(false);
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        if (composerChangeListWriter.startedGroup) {
            composerChangeListWriter.realizeOperationLocation(false);
            composerChangeListWriter.realizeOperationLocation(false);
            ChangeList changeList = composerChangeListWriter.changeList;
            changeList.getClass();
            changeList.operations.push(Operation.EndCurrentGroup.INSTANCE);
            composerChangeListWriter.startedGroup = false;
        }
        composerChangeListWriter.pushPendingUpsAndDowns();
        if (composerChangeListWriter.startedGroups.tos != 0) {
            ComposerKt.composeImmediateRuntimeError("Missed recording an endGroup()");
        }
        if (!this.pendingStack.isEmpty()) {
            ComposerKt.composeImmediateRuntimeError("Start/end imbalance");
        }
        cleanUpCompose();
        this.reader.close();
        int pop = this.providersInvalidStack.pop();
        OpaqueKey opaqueKey = ComposerKt.invocation;
        this.providersInvalid = pop != 0;
    }

    public final void enterGroup(boolean z, Pending pending) {
        this.pendingStack.add(this.pending);
        this.pending = pending;
        int i = this.groupNodeCount;
        IntStack intStack = this.parentStateStack;
        intStack.push(i);
        intStack.push(this.rGroupIndex);
        intStack.push(this.nodeIndex);
        if (z) {
            this.nodeIndex = 0;
        }
        this.groupNodeCount = 0;
        this.rGroupIndex = 0;
    }

    public final void forceFreshInsertTable() {
        SlotTable slotTable = new SlotTable();
        if (this.sourceMarkersEnabled) {
            slotTable.collectSourceInformation();
        }
        if (this.parentContext.getCollectingCallByInformation$runtime_release()) {
            slotTable.calledByMap = new MutableIntObjectMap();
        }
        this.insertTable = slotTable;
        SlotWriter openWriter = slotTable.openWriter();
        openWriter.close(true);
        this.writer = openWriter;
    }

    public final RecomposeScopeImpl getCurrentRecomposeScope$runtime_release() {
        ArrayList arrayList = this.invalidateStack;
        if (this.childrenComposing != 0 || arrayList.isEmpty()) {
            return null;
        }
        return (RecomposeScopeImpl) CascadingMenuPopup$$ExternalSyntheticOutline0.m(arrayList, 1);
    }

    public final boolean getDefaultsInvalid() {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        return (getSkipping() && !this.providersInvalid && ((currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release()) == null || (currentRecomposeScope$runtime_release.flags & 4) == 0)) ? false : true;
    }

    public final boolean getSkipping() {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        return (this.inserting || this.reusing || this.providersInvalid || (currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release()) == null || (currentRecomposeScope$runtime_release.flags & 8) != 0) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x010a A[Catch: all -> 0x0124, TryCatch #4 {all -> 0x0124, blocks: (B:22:0x018d, B:48:0x00ce, B:51:0x00ed, B:52:0x00ef, B:55:0x0101, B:57:0x010a, B:59:0x0113, B:60:0x0126, B:85:0x018a, B:87:0x01dc, B:88:0x01df, B:123:0x01e1, B:124:0x01e4, B:130:0x00da, B:138:0x01ea, B:54:0x00fa), top: B:47:0x00ce, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void insertMovableContentGuarded(java.util.List r24) {
        /*
            Method dump skipped, instructions count: 515
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.insertMovableContentGuarded(java.util.List):void");
    }

    public final void invokeMovableContentLambda(final MovableContent movableContent, PersistentCompositionLocalMap persistentCompositionLocalMap, final Object obj, boolean z) {
        startMovableGroup(126665345, movableContent);
        nextSlot();
        updateValue(obj);
        int i = this.compoundKeyHash;
        try {
            this.compoundKeyHash = 126665345;
            if (this.inserting) {
                SlotWriter.markGroup$default(this.writer);
            }
            boolean z2 = (this.inserting || Intrinsics.areEqual(this.reader.getGroupAux(), persistentCompositionLocalMap)) ? false : true;
            if (z2) {
                recordProviderUpdate(persistentCompositionLocalMap);
            }
            m256startBaiHCIY(202, 0, ComposerKt.compositionLocalMap, persistentCompositionLocalMap);
            this.providerCache = null;
            if (!this.inserting || z) {
                boolean z3 = this.providersInvalid;
                this.providersInvalid = z2;
                ComposableLambdaImpl composableLambdaImpl = new ComposableLambdaImpl(316014703, true, new Function2() { // from class: androidx.compose.runtime.ComposerImpl$invokeMovableContentLambda$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        Composer composer = (Composer) obj2;
                        if ((((Number) obj3).intValue() & 3) == 2) {
                            ComposerImpl composerImpl = (ComposerImpl) composer;
                            if (composerImpl.getSkipping()) {
                                composerImpl.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey = ComposerKt.invocation;
                        MovableContent.this.content.invoke(obj, (Object) composer, (Object) 0);
                        return Unit.INSTANCE;
                    }
                });
                TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, composableLambdaImpl);
                composableLambdaImpl.invoke((Object) this, (Object) 1);
                this.providersInvalid = z3;
            } else {
                this.writerHasAProvider = true;
                SlotWriter slotWriter = this.writer;
                this.parentContext.insertMovableContent$runtime_release(new MovableContentStateReference(movableContent, obj, this.composition, this.insertTable, slotWriter.anchor(slotWriter.parent(slotWriter.groups, slotWriter.parent)), EmptyList.INSTANCE, currentCompositionLocalScope()));
            }
            end(false);
            this.providerCache = null;
            this.compoundKeyHash = i;
            end(false);
        } catch (Throwable th) {
            end(false);
            this.providerCache = null;
            this.compoundKeyHash = i;
            end(false);
            throw th;
        }
    }

    public final Object nextSlot() {
        boolean z = this.inserting;
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (!z) {
            Object next = this.reader.next();
            return (!this.reusing || (next instanceof ReusableRememberObserver)) ? next : composer$Companion$Empty$1;
        }
        if (!this.nodeExpected) {
            return composer$Companion$Empty$1;
        }
        ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected");
        return composer$Companion$Empty$1;
    }

    public final int rGroupIndexOf(int i) {
        int access$parentAnchor = SlotTableKt.access$parentAnchor(this.reader.groups, i) + 1;
        int i2 = 0;
        while (access$parentAnchor < i) {
            if (!SlotTableKt.access$hasObjectKey(this.reader.groups, access$parentAnchor)) {
                i2++;
            }
            access$parentAnchor += SlotTableKt.access$groupSize(this.reader.groups, access$parentAnchor);
        }
        return i2;
    }

    /* renamed from: recompose-aFTiNEg$runtime_release, reason: not valid java name */
    public final boolean m255recomposeaFTiNEg$runtime_release(MutableScatterMap mutableScatterMap) {
        Operations operations = this.changes.operations;
        if (!operations.isEmpty()) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        if (mutableScatterMap._size <= 0 && this.invalidations.isEmpty()) {
            return false;
        }
        m254doComposeaFTiNEg(mutableScatterMap, null);
        return operations.isNotEmpty();
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005b, code lost:
    
        if (r10 == null) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object recomposeMovableContent(androidx.compose.runtime.CompositionImpl r9, androidx.compose.runtime.CompositionImpl r10, java.lang.Integer r11, java.util.List r12, kotlin.jvm.functions.Function0 r13) {
        /*
            r8 = this;
            boolean r0 = r8.isComposing
            int r1 = r8.nodeIndex
            r2 = 1
            r8.isComposing = r2     // Catch: java.lang.Throwable -> L28
            r2 = 0
            r8.nodeIndex = r2     // Catch: java.lang.Throwable -> L28
            int r3 = r12.size()     // Catch: java.lang.Throwable -> L28
            r4 = r2
        Lf:
            r5 = 0
            if (r4 >= r3) goto L30
            java.lang.Object r6 = r12.get(r4)     // Catch: java.lang.Throwable -> L28
            kotlin.Pair r6 = (kotlin.Pair) r6     // Catch: java.lang.Throwable -> L28
            java.lang.Object r7 = r6.component1()     // Catch: java.lang.Throwable -> L28
            androidx.compose.runtime.RecomposeScopeImpl r7 = (androidx.compose.runtime.RecomposeScopeImpl) r7     // Catch: java.lang.Throwable -> L28
            java.lang.Object r6 = r6.component2()     // Catch: java.lang.Throwable -> L28
            if (r6 == 0) goto L2a
            r8.tryImminentInvalidation$runtime_release(r7, r6)     // Catch: java.lang.Throwable -> L28
            goto L2d
        L28:
            r9 = move-exception
            goto L66
        L2a:
            r8.tryImminentInvalidation$runtime_release(r7, r5)     // Catch: java.lang.Throwable -> L28
        L2d:
            int r4 = r4 + 1
            goto Lf
        L30:
            if (r9 == 0) goto L5d
            if (r11 == 0) goto L39
            int r11 = r11.intValue()     // Catch: java.lang.Throwable -> L28
            goto L3a
        L39:
            r11 = -1
        L3a:
            if (r10 == 0) goto L57
            boolean r12 = r10.equals(r9)     // Catch: java.lang.Throwable -> L28
            if (r12 != 0) goto L57
            if (r11 < 0) goto L57
            r9.invalidationDelegate = r10     // Catch: java.lang.Throwable -> L28
            r9.invalidationDelegateGroup = r11     // Catch: java.lang.Throwable -> L28
            java.lang.Object r10 = r13.invoke()     // Catch: java.lang.Throwable -> L51
            r9.invalidationDelegate = r5     // Catch: java.lang.Throwable -> L28
            r9.invalidationDelegateGroup = r2     // Catch: java.lang.Throwable -> L28
            goto L5b
        L51:
            r10 = move-exception
            r9.invalidationDelegate = r5     // Catch: java.lang.Throwable -> L28
            r9.invalidationDelegateGroup = r2     // Catch: java.lang.Throwable -> L28
            throw r10     // Catch: java.lang.Throwable -> L28
        L57:
            java.lang.Object r10 = r13.invoke()     // Catch: java.lang.Throwable -> L28
        L5b:
            if (r10 != 0) goto L61
        L5d:
            java.lang.Object r10 = r13.invoke()     // Catch: java.lang.Throwable -> L28
        L61:
            r8.isComposing = r0
            r8.nodeIndex = r1
            return r10
        L66:
            r8.isComposing = r0
            r8.nodeIndex = r1
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.recomposeMovableContent(androidx.compose.runtime.CompositionImpl, androidx.compose.runtime.CompositionImpl, java.lang.Integer, java.util.List, kotlin.jvm.functions.Function0):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0039, code lost:
    
        if (r3.location < r5) goto L11;
     */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0268 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0270  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void recomposeToGroupEnd() {
        /*
            Method dump skipped, instructions count: 853
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.recomposeToGroupEnd():void");
    }

    public final void recordDelete() {
        reportFreeMovableContent$reportGroup(this, this.reader.currentGroup, false, 0);
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        composerChangeListWriter.realizeNodeMovementOperations();
        composerChangeListWriter.realizeOperationLocation(false);
        composerChangeListWriter.recordSlotEditing();
        ChangeList changeList = composerChangeListWriter.changeList;
        changeList.getClass();
        changeList.operations.push(Operation.RemoveCurrentGroup.INSTANCE);
        int i = composerChangeListWriter.writersReaderDelta;
        SlotReader slotReader = composerChangeListWriter.composer.reader;
        composerChangeListWriter.writersReaderDelta = slotReader.groups[(slotReader.currentGroup * 5) + 3] + i;
    }

    public final void recordProviderUpdate(PersistentCompositionLocalMap persistentCompositionLocalMap) {
        MutableIntObjectMap mutableIntObjectMap = this.providerUpdates;
        if (mutableIntObjectMap == null) {
            mutableIntObjectMap = new MutableIntObjectMap();
            this.providerUpdates = mutableIntObjectMap;
        }
        mutableIntObjectMap.set(this.reader.currentGroup, persistentCompositionLocalMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void recordUpsAndDowns(int r8, int r9, int r10) {
        /*
            r7 = this;
            androidx.compose.runtime.SlotReader r0 = r7.reader
            androidx.compose.runtime.OpaqueKey r1 = androidx.compose.runtime.ComposerKt.invocation
            if (r8 != r9) goto L9
        L6:
            r10 = r8
            goto L73
        L9:
            if (r8 == r10) goto L73
            if (r9 != r10) goto Lf
            goto L73
        Lf:
            int[] r1 = r0.groups
            int r2 = r8 * 5
            int r2 = r2 + 2
            r2 = r1[r2]
            if (r2 != r9) goto L1c
            r10 = r9
            goto L73
        L1c:
            int r3 = r9 * 5
            int r3 = r3 + 2
            r3 = r1[r3]
            if (r3 != r8) goto L25
            goto L6
        L25:
            if (r2 != r3) goto L29
            r10 = r2
            goto L73
        L29:
            r2 = 0
            r3 = r8
            r4 = r2
        L2c:
            int[] r5 = r0.groups
            if (r3 <= 0) goto L39
            if (r3 == r10) goto L39
            int r3 = androidx.compose.runtime.SlotTableKt.access$parentAnchor(r5, r3)
            int r4 = r4 + 1
            goto L2c
        L39:
            r3 = r9
            r6 = r2
        L3b:
            if (r3 <= 0) goto L46
            if (r3 == r10) goto L46
            int r3 = androidx.compose.runtime.SlotTableKt.access$parentAnchor(r5, r3)
            int r6 = r6 + 1
            goto L3b
        L46:
            int r10 = r4 - r6
            r5 = r8
            r3 = r2
        L4a:
            if (r3 >= r10) goto L55
            int r5 = r5 * 5
            int r5 = r5 + 2
            r5 = r1[r5]
            int r3 = r3 + 1
            goto L4a
        L55:
            int r6 = r6 - r4
            r10 = r9
        L57:
            if (r2 >= r6) goto L62
            int r10 = r10 * 5
            int r10 = r10 + 2
            r10 = r1[r10]
            int r2 = r2 + 1
            goto L57
        L62:
            r2 = r10
            r10 = r5
        L64:
            if (r10 == r2) goto L73
            int r10 = r10 * 5
            int r10 = r10 + 2
            r10 = r1[r10]
            int r2 = r2 * 5
            int r2 = r2 + 2
            r2 = r1[r2]
            goto L64
        L73:
            if (r8 <= 0) goto L8d
            if (r8 == r10) goto L8d
            int[] r1 = r0.groups
            boolean r1 = androidx.compose.runtime.SlotTableKt.access$isNode(r1, r8)
            if (r1 == 0) goto L84
            androidx.compose.runtime.changelist.ComposerChangeListWriter r1 = r7.changeListWriter
            r1.moveUp()
        L84:
            int[] r1 = r0.groups
            int r8 = r8 * 5
            int r8 = r8 + 2
            r8 = r1[r8]
            goto L73
        L8d:
            r7.doRecordDownsFor(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.recordUpsAndDowns(int, int, int):void");
    }

    public final Object rememberedValue() {
        boolean z = this.inserting;
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (!z) {
            Object next = this.reader.next();
            return (!this.reusing || (next instanceof ReusableRememberObserver)) ? next instanceof RememberObserverHolder ? ((RememberObserverHolder) next).wrapped : next : composer$Companion$Empty$1;
        }
        if (!this.nodeExpected) {
            return composer$Companion$Empty$1;
        }
        ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected");
        return composer$Companion$Empty$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void skipCurrentGroup() {
        /*
            r12 = this;
            java.util.List r0 = r12.invalidations
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L15
            int r0 = r12.groupNodeCount
            androidx.compose.runtime.SlotReader r1 = r12.reader
            int r1 = r1.skipGroup()
            int r1 = r1 + r0
            r12.groupNodeCount = r1
            goto Ldf
        L15:
            androidx.compose.runtime.SlotReader r0 = r12.reader
            int r1 = r0.getGroupKey()
            int r2 = r0.currentGroup
            int r3 = r0.currentEnd
            r4 = 0
            int[] r5 = r0.groups
            if (r2 >= r3) goto L29
            java.lang.Object r2 = r0.objectKey(r5, r2)
            goto L2a
        L29:
            r2 = r4
        L2a:
            java.lang.Object r3 = r0.getGroupAux()
            int r6 = r12.rGroupIndex
            androidx.compose.runtime.Composer$Companion$Empty$1 r7 = androidx.compose.runtime.Composer.Companion.Empty
            r8 = 207(0xcf, float:2.9E-43)
            r9 = 3
            if (r2 != 0) goto L63
            if (r3 == 0) goto L54
            if (r1 != r8) goto L54
            boolean r10 = r3.equals(r7)
            if (r10 != 0) goto L54
            int r10 = r3.hashCode()
            int r11 = r12.compoundKeyHash
            int r11 = java.lang.Integer.rotateLeft(r11, r9)
            r10 = r10 ^ r11
            int r10 = java.lang.Integer.rotateLeft(r10, r9)
            r10 = r10 ^ r6
            r12.compoundKeyHash = r10
            goto L7f
        L54:
            int r10 = r12.compoundKeyHash
            int r10 = java.lang.Integer.rotateLeft(r10, r9)
            r10 = r10 ^ r1
            int r10 = java.lang.Integer.rotateLeft(r10, r9)
            r10 = r10 ^ r6
        L60:
            r12.compoundKeyHash = r10
            goto L7f
        L63:
            boolean r10 = r2 instanceof java.lang.Enum
            if (r10 == 0) goto L7a
            r10 = r2
            java.lang.Enum r10 = (java.lang.Enum) r10
            int r10 = r10.ordinal()
        L6e:
            int r11 = r12.compoundKeyHash
            int r11 = java.lang.Integer.rotateLeft(r11, r9)
            r10 = r10 ^ r11
            int r10 = java.lang.Integer.rotateLeft(r10, r9)
            goto L60
        L7a:
            int r10 = r2.hashCode()
            goto L6e
        L7f:
            int r10 = r0.currentGroup
            boolean r5 = androidx.compose.runtime.SlotTableKt.access$isNode(r5, r10)
            r12.startReaderGroup(r4, r5)
            r12.recomposeToGroupEnd()
            r0.endGroup()
            if (r2 != 0) goto Lc4
            if (r3 == 0) goto Lb1
            if (r1 != r8) goto Lb1
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto Lb1
            int r0 = r3.hashCode()
            int r1 = r12.compoundKeyHash
            r1 = r1 ^ r6
            int r1 = java.lang.Integer.rotateRight(r1, r9)
            int r0 = java.lang.Integer.hashCode(r0)
            r0 = r0 ^ r1
            int r0 = java.lang.Integer.rotateRight(r0, r9)
            r12.compoundKeyHash = r0
            goto Ldf
        Lb1:
            int r0 = r12.compoundKeyHash
            r0 = r0 ^ r6
            int r0 = java.lang.Integer.rotateRight(r0, r9)
            int r1 = java.lang.Integer.hashCode(r1)
            r0 = r0 ^ r1
        Lbd:
            int r0 = java.lang.Integer.rotateRight(r0, r9)
            r12.compoundKeyHash = r0
            goto Ldf
        Lc4:
            boolean r0 = r2 instanceof java.lang.Enum
            if (r0 == 0) goto Lda
            java.lang.Enum r2 = (java.lang.Enum) r2
            int r0 = r2.ordinal()
        Lce:
            int r1 = r12.compoundKeyHash
            int r1 = java.lang.Integer.rotateRight(r1, r9)
            int r0 = java.lang.Integer.hashCode(r0)
            r0 = r0 ^ r1
            goto Lbd
        Lda:
            int r0 = r2.hashCode()
            goto Lce
        Ldf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.skipCurrentGroup():void");
    }

    public final void skipReaderToGroupEnd() {
        SlotReader slotReader = this.reader;
        int i = slotReader.parent;
        this.groupNodeCount = i >= 0 ? SlotTableKt.access$nodeCount(slotReader.groups, i) : 0;
        this.reader.skipToGroupEnd();
    }

    public final void skipToGroupEnd() {
        if (this.groupNodeCount != 0) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling skipAndEndGroup");
        }
        if (this.inserting) {
            return;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null) {
            int i = currentRecomposeScope$runtime_release.flags;
            if ((i & 128) == 0) {
                currentRecomposeScope$runtime_release.flags = i | 16;
            }
        }
        if (this.invalidations.isEmpty()) {
            skipReaderToGroupEnd();
        } else {
            recomposeToGroupEnd();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bf  */
    /* renamed from: start-BaiHCIY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m256startBaiHCIY(int r26, int r27, java.lang.Object r28, java.lang.Object r29) {
        /*
            Method dump skipped, instructions count: 952
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.m256startBaiHCIY(int, int, java.lang.Object, java.lang.Object):void");
    }

    public final void startDefaults() {
        m256startBaiHCIY(-127, 0, null, null);
    }

    public final void startGroup(int i, OpaqueKey opaqueKey) {
        m256startBaiHCIY(i, 0, opaqueKey, null);
    }

    public final void startMovableGroup(int i, Object obj) {
        m256startBaiHCIY(i, 0, obj, null);
    }

    public final void startReaderGroup(Object obj, boolean z) {
        if (z) {
            SlotReader slotReader = this.reader;
            if (slotReader.emptyCount <= 0) {
                if (!SlotTableKt.access$isNode(slotReader.groups, slotReader.currentGroup)) {
                    PreconditionsKt.throwIllegalArgumentException("Expected a node group");
                }
                slotReader.startGroup();
                return;
            }
            return;
        }
        if (obj != null && this.reader.getGroupAux() != obj) {
            ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
            composerChangeListWriter.getClass();
            composerChangeListWriter.realizeOperationLocation(false);
            ChangeList changeList = composerChangeListWriter.changeList;
            changeList.getClass();
            Operation.UpdateAuxData updateAuxData = Operation.UpdateAuxData.INSTANCE;
            Operations operations = changeList.operations;
            operations.pushOp(updateAuxData);
            Operations.WriteScope.m267setObjectDKhxnng(operations, 0, obj);
            int i = operations.pushedIntMask;
            int i2 = updateAuxData.ints;
            int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i2);
            int i3 = updateAuxData.objects;
            if (i != access$createExpectedArgMask || operations.pushedObjectMask != Operations.access$createExpectedArgMask(operations, i3)) {
                StringBuilder sb = new StringBuilder();
                int i4 = 0;
                for (int i5 = 0; i5 < i2; i5++) {
                    if (((1 << i5) & operations.pushedIntMask) != 0) {
                        if (i4 > 0) {
                            sb.append(", ");
                        }
                        sb.append(updateAuxData.mo260intParamNamew8GmfQM(i5));
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
                        sb3.append(updateAuxData.mo261objectParamName31yXWZQ(i7));
                        i6++;
                    }
                }
                String sb4 = sb3.toString();
                StringBuilder sb5 = new StringBuilder("Error while pushing ");
                sb5.append(updateAuxData);
                sb5.append(". Not all arguments were provided. Missing ");
                sb5.append(i4);
                sb5.append(" int arguments (");
                ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i6, " object arguments (");
                ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
            }
        }
        this.reader.startGroup();
    }

    public final void startReplaceGroup(int i) {
        int i2;
        int i3;
        if (this.pending != null) {
            m256startBaiHCIY(i, 0, null, null);
            return;
        }
        if (this.nodeExpected) {
            ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected");
        }
        this.compoundKeyHash = this.rGroupIndex ^ Integer.rotateLeft(Integer.rotateLeft(this.compoundKeyHash, 3) ^ i, 3);
        this.rGroupIndex++;
        SlotReader slotReader = this.reader;
        boolean z = this.inserting;
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (z) {
            slotReader.emptyCount++;
            this.writer.startGroup(i, composer$Companion$Empty$1, false, composer$Companion$Empty$1);
            enterGroup(false, null);
            return;
        }
        if (slotReader.getGroupKey() == i && ((i3 = slotReader.currentGroup) >= slotReader.currentEnd || !SlotTableKt.access$hasObjectKey(slotReader.groups, i3))) {
            slotReader.startGroup();
            enterGroup(false, null);
            return;
        }
        if (slotReader.emptyCount <= 0 && (i2 = slotReader.currentGroup) != slotReader.currentEnd) {
            int i4 = this.nodeIndex;
            recordDelete();
            this.changeListWriter.removeNode(i4, slotReader.skipGroup());
            ComposerKt.access$removeRange(i2, slotReader.currentGroup, this.invalidations);
        }
        slotReader.emptyCount++;
        this.inserting = true;
        this.providerCache = null;
        if (this.writer.closed) {
            SlotWriter openWriter = this.insertTable.openWriter();
            this.writer = openWriter;
            openWriter.skipToGroupEnd();
            this.writerHasAProvider = false;
            this.providerCache = null;
        }
        SlotWriter slotWriter = this.writer;
        slotWriter.beginInsert();
        int i5 = slotWriter.currentGroup;
        slotWriter.startGroup(i, composer$Companion$Empty$1, false, composer$Companion$Empty$1);
        this.insertAnchor = slotWriter.anchor(i5);
        enterGroup(false, null);
    }

    public final void startReplaceableGroup(int i) {
        m256startBaiHCIY(i, 0, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.runtime.ComposerImpl startRestartGroup(int r12) {
        /*
            Method dump skipped, instructions count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.startRestartGroup(int):androidx.compose.runtime.ComposerImpl");
    }

    public final void startReusableGroup(Object obj) {
        if (!this.inserting && this.reader.getGroupKey() == 207 && !Intrinsics.areEqual(this.reader.getGroupAux(), obj) && this.reusingGroup < 0) {
            this.reusingGroup = this.reader.currentGroup;
            this.reusing = true;
        }
        m256startBaiHCIY(207, 0, null, obj);
    }

    public final void startReusableNode() {
        m256startBaiHCIY(125, 2, null, null);
        this.nodeExpected = true;
    }

    public final void startRoot() {
        this.rGroupIndex = 0;
        SlotTable slotTable = this.slotTable;
        this.reader = slotTable.openReader();
        m256startBaiHCIY(100, 0, null, null);
        CompositionContext compositionContext = this.parentContext;
        compositionContext.startComposing$runtime_release();
        this.parentProvider = compositionContext.getCompositionLocalScope$runtime_release();
        boolean z = this.providersInvalid;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        this.providersInvalidStack.push(z ? 1 : 0);
        this.providersInvalid = changed(this.parentProvider);
        this.providerCache = null;
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = compositionContext.getCollectingParameterInformation$runtime_release();
        }
        if (!this.sourceMarkersEnabled) {
            this.sourceMarkersEnabled = compositionContext.getCollectingSourceInformation$runtime_release();
        }
        Set set = (Set) CompositionLocalMapKt.read(this.parentProvider, InspectionTablesKt.LocalInspectionTables);
        if (set != null) {
            set.add(slotTable);
            compositionContext.recordInspectionTable$runtime_release(set);
        }
        m256startBaiHCIY(compositionContext.getCompoundHashKey$runtime_release(), 0, null, null);
    }

    public final boolean tryImminentInvalidation$runtime_release(RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        Anchor anchor = recomposeScopeImpl.anchor;
        if (anchor == null) {
            return false;
        }
        int anchorIndex = this.reader.table.anchorIndex(anchor);
        if (!this.isComposing || anchorIndex < this.reader.currentGroup) {
            return false;
        }
        List list = this.invalidations;
        int findLocation = ComposerKt.findLocation(anchorIndex, list);
        if (findLocation < 0) {
            int i = -(findLocation + 1);
            if (!(obj instanceof DerivedState)) {
                obj = null;
            }
            list.add(i, new Invalidation(recomposeScopeImpl, anchorIndex, obj));
        } else {
            Invalidation invalidation = (Invalidation) ((ArrayList) list).get(findLocation);
            if (obj instanceof DerivedState) {
                Object obj2 = invalidation.instances;
                if (obj2 == null) {
                    invalidation.instances = obj;
                } else if (obj2 instanceof MutableScatterSet) {
                    ((MutableScatterSet) obj2).add(obj);
                } else {
                    int i2 = ScatterSetKt.$r8$clinit;
                    MutableScatterSet mutableScatterSet = new MutableScatterSet(2);
                    mutableScatterSet.plusAssign(obj2);
                    mutableScatterSet.plusAssign(obj);
                    invalidation.instances = mutableScatterSet;
                }
            } else {
                invalidation.instances = null;
            }
        }
        return true;
    }

    /* renamed from: updateComposerInvalidations-RY85e9Y, reason: not valid java name */
    public final void m257updateComposerInvalidationsRY85e9Y(MutableScatterMap mutableScatterMap) {
        Object[] objArr = mutableScatterMap.keys;
        Object[] objArr2 = mutableScatterMap.values;
        long[] jArr = mutableScatterMap.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            Object obj = objArr[i4];
                            Object obj2 = objArr2[i4];
                            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj;
                            Anchor anchor = recomposeScopeImpl.anchor;
                            if (anchor != null) {
                                int i5 = anchor.location;
                                List list = this.invalidations;
                                if (obj2 == ScopeInvalidated.INSTANCE) {
                                    obj2 = null;
                                }
                                list.add(new Invalidation(recomposeScopeImpl, i5, obj2));
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    }
                }
                if (i == length) {
                    break;
                } else {
                    i++;
                }
            }
        }
        CollectionsKt__MutableCollectionsJVMKt.sortWith(this.invalidations, ComposerKt.InvalidationLocationAscending);
    }

    public final void updateNodeCount(int i, int i2) {
        if (updatedNodeCount(i) != i2) {
            if (i < 0) {
                MutableIntIntMap mutableIntIntMap = this.nodeCountVirtualOverrides;
                if (mutableIntIntMap == null) {
                    mutableIntIntMap = new MutableIntIntMap();
                    this.nodeCountVirtualOverrides = mutableIntIntMap;
                }
                mutableIntIntMap.set(i, i2);
                return;
            }
            int[] iArr = this.nodeCountOverrides;
            if (iArr == null) {
                int i3 = this.reader.groupsSize;
                int[] iArr2 = new int[i3];
                Arrays.fill(iArr2, 0, i3, -1);
                this.nodeCountOverrides = iArr2;
                iArr = iArr2;
            }
            iArr[i] = i2;
        }
    }

    public final void updateNodeCountOverrides(int i, int i2) {
        int updatedNodeCount = updatedNodeCount(i);
        if (updatedNodeCount != i2) {
            int i3 = i2 - updatedNodeCount;
            int size = this.pendingStack.size() - 1;
            while (i != -1) {
                int updatedNodeCount2 = updatedNodeCount(i) + i3;
                updateNodeCount(i, updatedNodeCount2);
                int i4 = size;
                while (true) {
                    if (-1 < i4) {
                        Pending pending = (Pending) this.pendingStack.get(i4);
                        if (pending != null && pending.updateNodeCount(i, updatedNodeCount2)) {
                            size = i4 - 1;
                            break;
                        }
                        i4--;
                    } else {
                        break;
                    }
                }
                if (i < 0) {
                    i = this.reader.parent;
                } else if (SlotTableKt.access$isNode(this.reader.groups, i)) {
                    return;
                } else {
                    i = SlotTableKt.access$parentAnchor(this.reader.groups, i);
                }
            }
        }
    }

    public final PersistentCompositionLocalHashMap updateProviderMapGroup(PersistentCompositionLocalMap persistentCompositionLocalMap, PersistentCompositionLocalHashMap persistentCompositionLocalHashMap) {
        PersistentCompositionLocalHashMap persistentCompositionLocalHashMap2 = (PersistentCompositionLocalHashMap) persistentCompositionLocalMap;
        persistentCompositionLocalHashMap2.getClass();
        PersistentCompositionLocalHashMap.Builder builder = new PersistentCompositionLocalHashMap.Builder(persistentCompositionLocalHashMap2);
        builder.map = persistentCompositionLocalHashMap2;
        builder.putAll(persistentCompositionLocalHashMap);
        PersistentCompositionLocalHashMap build = builder.build();
        startGroup(204, ComposerKt.providerMaps);
        nextSlot();
        updateValue(build);
        nextSlot();
        updateValue(persistentCompositionLocalHashMap);
        end(false);
        return build;
    }

    public final void updateRememberedValue(Object obj) {
        int i;
        SlotReader slotReader;
        int i2;
        SlotWriter slotWriter;
        if (obj instanceof RememberObserver) {
            if (this.inserting) {
                ChangeList changeList = this.changeListWriter.changeList;
                changeList.getClass();
                Operation.Remember remember = Operation.Remember.INSTANCE;
                Operations operations = changeList.operations;
                operations.pushOp(remember);
                Operations.WriteScope.m267setObjectDKhxnng(operations, 0, (RememberObserver) obj);
                int i3 = operations.pushedIntMask;
                int i4 = remember.ints;
                int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i4);
                int i5 = remember.objects;
                if (i3 != access$createExpectedArgMask || operations.pushedObjectMask != Operations.access$createExpectedArgMask(operations, i5)) {
                    StringBuilder sb = new StringBuilder();
                    int i6 = 0;
                    for (int i7 = 0; i7 < i4; i7++) {
                        if (((1 << i7) & operations.pushedIntMask) != 0) {
                            if (i6 > 0) {
                                sb.append(", ");
                            }
                            sb.append(remember.mo260intParamNamew8GmfQM(i7));
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
                            sb3.append(remember.mo261objectParamName31yXWZQ(i9));
                            i8++;
                        }
                    }
                    String sb4 = sb3.toString();
                    StringBuilder sb5 = new StringBuilder("Error while pushing ");
                    sb5.append(remember);
                    sb5.append(". Not all arguments were provided. Missing ");
                    sb5.append(i6);
                    sb5.append(" int arguments (");
                    ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i8, " object arguments (");
                    ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
                }
            }
            this.abandonSet.add(obj);
            RememberObserver rememberObserver = (RememberObserver) obj;
            Anchor anchor = null;
            if (this.inserting) {
                SlotWriter slotWriter2 = this.writer;
                OpaqueKey opaqueKey = ComposerKt.invocation;
                int i10 = slotWriter2.currentGroup;
                if (i10 > slotWriter2.parent + 1) {
                    int i11 = i10 - 1;
                    int parent = slotWriter2.parent(slotWriter2.groups, i11);
                    while (true) {
                        i2 = i11;
                        i11 = parent;
                        slotWriter = this.writer;
                        if (i11 == slotWriter.parent || i11 < 0) {
                            break;
                        } else {
                            parent = slotWriter.parent(slotWriter.groups, i11);
                        }
                    }
                    anchor = slotWriter.anchor(i2);
                }
            } else {
                SlotReader slotReader2 = this.reader;
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                int i12 = slotReader2.currentGroup;
                if (i12 > slotReader2.parent + 1) {
                    int i13 = i12 - 1;
                    int i14 = slotReader2.groups[(i13 * 5) + 2];
                    while (true) {
                        i = i13;
                        i13 = i14;
                        slotReader = this.reader;
                        if (i13 == slotReader.parent || i13 < 0) {
                            break;
                        } else {
                            i14 = slotReader.groups[(i13 * 5) + 2];
                        }
                    }
                    anchor = slotReader.anchor(i);
                }
            }
            RememberObserverHolder rememberObserverHolder = new RememberObserverHolder();
            rememberObserverHolder.wrapped = rememberObserver;
            rememberObserverHolder.after = anchor;
            obj = rememberObserverHolder;
        }
        updateValue(obj);
    }

    public final void updateValue(Object obj) {
        int i;
        int i2;
        if (this.inserting) {
            this.writer.update(obj);
            return;
        }
        SlotReader slotReader = this.reader;
        boolean z = slotReader.hadNext;
        int i3 = 1;
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        int i4 = 0;
        if (!z) {
            Anchor anchor = slotReader.anchor(slotReader.parent);
            ChangeList changeList = composerChangeListWriter.changeList;
            changeList.getClass();
            Operation.AppendValue appendValue = Operation.AppendValue.INSTANCE;
            Operations operations = changeList.operations;
            operations.pushOp(appendValue);
            Operations.WriteScope.m267setObjectDKhxnng(operations, 0, anchor);
            Operations.WriteScope.m267setObjectDKhxnng(operations, 1, obj);
            int i5 = operations.pushedIntMask;
            int i6 = appendValue.ints;
            int access$createExpectedArgMask = Operations.access$createExpectedArgMask(operations, i6);
            int i7 = appendValue.objects;
            if (i5 == access$createExpectedArgMask && operations.pushedObjectMask == Operations.access$createExpectedArgMask(operations, i7)) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            int i8 = 0;
            for (int i9 = 0; i9 < i6; i9++) {
                if (((1 << i9) & operations.pushedIntMask) != 0) {
                    if (i8 > 0) {
                        sb.append(", ");
                    }
                    sb.append(appendValue.mo260intParamNamew8GmfQM(i9));
                    i8++;
                }
            }
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            int i10 = 0;
            int i11 = 0;
            while (i11 < i7) {
                if (((i3 << i11) & operations.pushedObjectMask) != 0) {
                    if (i8 > 0) {
                        sb3.append(", ");
                    }
                    sb3.append(appendValue.mo261objectParamName31yXWZQ(i11));
                    i10++;
                }
                i11++;
                i3 = 1;
            }
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder("Error while pushing ");
            sb5.append(appendValue);
            sb5.append(". Not all arguments were provided. Missing ");
            sb5.append(i8);
            sb5.append(" int arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb2, ") and ", i10, " object arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb5, sb4, ").");
            return;
        }
        int access$slotAnchor = (slotReader.currentSlot - SlotTableKt.access$slotAnchor(slotReader.groups, slotReader.parent)) - 1;
        if (composerChangeListWriter.composer.reader.parent - composerChangeListWriter.writersReaderDelta >= 0) {
            composerChangeListWriter.realizeOperationLocation(true);
            ChangeList changeList2 = composerChangeListWriter.changeList;
            Operation.UpdateValue updateValue = Operation.UpdateValue.INSTANCE;
            Operations operations2 = changeList2.operations;
            operations2.pushOp(updateValue);
            Operations.WriteScope.m267setObjectDKhxnng(operations2, 0, obj);
            Operations.WriteScope.m266setIntA6tL2VI(operations2, 0, access$slotAnchor);
            if (operations2.pushedIntMask == Operations.access$createExpectedArgMask(operations2, 1) && operations2.pushedObjectMask == Operations.access$createExpectedArgMask(operations2, 1)) {
                return;
            }
            StringBuilder sb6 = new StringBuilder();
            if ((operations2.pushedIntMask & 1) != 0) {
                sb6.append(updateValue.mo260intParamNamew8GmfQM(0));
                i = 1;
            } else {
                i = 0;
            }
            String sb7 = sb6.toString();
            StringBuilder sb8 = new StringBuilder();
            if ((operations2.pushedObjectMask & 1) != 0) {
                if (i > 0) {
                    sb8.append(", ");
                }
                sb8.append(updateValue.mo261objectParamName31yXWZQ(0));
            } else {
                i3 = 0;
            }
            String sb9 = sb8.toString();
            StringBuilder sb10 = new StringBuilder("Error while pushing ");
            sb10.append(updateValue);
            sb10.append(". Not all arguments were provided. Missing ");
            sb10.append(i);
            sb10.append(" int arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb7, ") and ", i3, " object arguments (");
            ComposerImpl$$ExternalSyntheticOutline0.m(sb10, sb9, ").");
            return;
        }
        SlotReader slotReader2 = this.reader;
        Anchor anchor2 = slotReader2.anchor(slotReader2.parent);
        ChangeList changeList3 = composerChangeListWriter.changeList;
        Operation.UpdateAnchoredValue updateAnchoredValue = Operation.UpdateAnchoredValue.INSTANCE;
        Operations operations3 = changeList3.operations;
        operations3.pushOp(updateAnchoredValue);
        Operations.WriteScope.m267setObjectDKhxnng(operations3, 0, obj);
        Operations.WriteScope.m267setObjectDKhxnng(operations3, 1, anchor2);
        Operations.WriteScope.m266setIntA6tL2VI(operations3, 0, access$slotAnchor);
        if (operations3.pushedIntMask == Operations.access$createExpectedArgMask(operations3, 1) && operations3.pushedObjectMask == Operations.access$createExpectedArgMask(operations3, 2)) {
            return;
        }
        StringBuilder sb11 = new StringBuilder();
        if ((operations3.pushedIntMask & 1) != 0) {
            sb11.append(updateAnchoredValue.mo260intParamNamew8GmfQM(0));
            i2 = 1;
        } else {
            i2 = 0;
        }
        String sb12 = sb11.toString();
        StringBuilder sb13 = new StringBuilder();
        int i12 = 0;
        for (int i13 = 2; i4 < i13; i13 = 2) {
            if (((1 << i4) & operations3.pushedObjectMask) != 0) {
                if (i2 > 0) {
                    sb13.append(", ");
                }
                sb13.append(updateAnchoredValue.mo261objectParamName31yXWZQ(i4));
                i12++;
            }
            i4++;
        }
        String sb14 = sb13.toString();
        StringBuilder sb15 = new StringBuilder("Error while pushing ");
        sb15.append(updateAnchoredValue);
        sb15.append(". Not all arguments were provided. Missing ");
        sb15.append(i2);
        sb15.append(" int arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb15, sb12, ") and ", i12, " object arguments (");
        ComposerImpl$$ExternalSyntheticOutline0.m(sb15, sb14, ").");
    }

    public final int updatedNodeCount(int i) {
        int i2;
        if (i >= 0) {
            int[] iArr = this.nodeCountOverrides;
            return (iArr == null || (i2 = iArr[i]) < 0) ? SlotTableKt.access$nodeCount(this.reader.groups, i) : i2;
        }
        MutableIntIntMap mutableIntIntMap = this.nodeCountVirtualOverrides;
        if (mutableIntIntMap == null || mutableIntIntMap.findKeyIndex(i) < 0) {
            return 0;
        }
        int findKeyIndex = mutableIntIntMap.findKeyIndex(i);
        if (findKeyIndex >= 0) {
            return mutableIntIntMap.values[findKeyIndex];
        }
        throw new NoSuchElementException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Cannot find value for key "));
    }

    public final void useNode() {
        if (!this.nodeExpected) {
            ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected");
        }
        this.nodeExpected = false;
        if (this.inserting) {
            ComposerKt.composeImmediateRuntimeError("useNode() called while inserting");
        }
        SlotReader slotReader = this.reader;
        Object node = slotReader.node(slotReader.parent);
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        composerChangeListWriter.realizeNodeMovementOperations();
        composerChangeListWriter.pendingDownNodes.add(node);
        if (this.reusing && (node instanceof ComposeNodeLifecycleCallback)) {
            composerChangeListWriter.pushPendingUpsAndDowns();
            ChangeList changeList = composerChangeListWriter.changeList;
            changeList.getClass();
            if (node instanceof ComposeNodeLifecycleCallback) {
                changeList.operations.push(Operation.UseCurrentNode.INSTANCE);
            }
        }
    }

    public final boolean changed(boolean z) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Boolean) && z == ((Boolean) nextSlot).booleanValue()) {
            return false;
        }
        updateValue(Boolean.valueOf(z));
        return true;
    }

    public final boolean changed(float f) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Float) && f == ((Number) nextSlot).floatValue()) {
            return false;
        }
        updateValue(Float.valueOf(f));
        return true;
    }

    public final boolean changed(long j) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Long) && j == ((Number) nextSlot).longValue()) {
            return false;
        }
        updateValue(Long.valueOf(j));
        return true;
    }

    public final boolean changed(int i) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Integer) && i == ((Number) nextSlot).intValue()) {
            return false;
        }
        updateValue(Integer.valueOf(i));
        return true;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class CompositionContextHolder implements ReusableRememberObserver {
        public final CompositionContextImpl ref;

        public CompositionContextHolder(CompositionContextImpl compositionContextImpl) {
            this.ref = compositionContextImpl;
        }

        @Override // androidx.compose.runtime.RememberObserver
        public final void onAbandoned() {
            this.ref.dispose();
        }

        @Override // androidx.compose.runtime.RememberObserver
        public final void onForgotten() {
            this.ref.dispose();
        }

        @Override // androidx.compose.runtime.RememberObserver
        public final void onRemembered() {
        }
    }

    public final PersistentCompositionLocalMap currentCompositionLocalScope() {
        PersistentCompositionLocalMap persistentCompositionLocalMap = this.providerCache;
        return persistentCompositionLocalMap != null ? persistentCompositionLocalMap : currentCompositionLocalScope(this.reader.parent);
    }
}
