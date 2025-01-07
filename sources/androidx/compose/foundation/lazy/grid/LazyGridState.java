package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.gestures.snapping.LazyGridSnapLayoutInfoProviderKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyGridState implements ScrollableState {
    public static final SaverKt$Saver$1 Saver = ListSaverKt.listSaver(new Function2() { // from class: androidx.compose.foundation.lazy.grid.LazyGridState$Companion$Saver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            LazyGridState lazyGridState = (LazyGridState) obj2;
            return CollectionsKt__CollectionsKt.listOf(Integer.valueOf(lazyGridState.scrollPosition.getIndex()), Integer.valueOf(lazyGridState.scrollPosition.getScrollOffset()));
        }
    }, new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridState$Companion$Saver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            List list = (List) obj;
            return new LazyGridState(((Number) list.get(0)).intValue(), ((Number) list.get(1)).intValue());
        }
    });
    public final MutableState canScrollBackward$delegate;
    public final MutableState canScrollForward$delegate;
    public final MutableState measurementScopeInvalidator;
    public final MutableState placementScopeInvalidator;
    public final LazyLayoutPrefetchState prefetchState;
    public LayoutNode remeasurement;
    public final LazyGridScrollPosition scrollPosition;
    public float scrollToBeConsumed;
    public final LazyGridPrefetchStrategy prefetchStrategy = new DefaultLazyGridPrefetchStrategy();
    public final MutableState layoutInfoState = SnapshotStateKt.mutableStateOf(LazyGridStateKt.EmptyLazyGridLayoutInfo, SnapshotStateKt.neverEqualPolicy());
    public final MutableInteractionSource internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
    public final ScrollableState scrollableState = ScrollableStateKt.ScrollableState(new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridState$scrollableState$1
        {
            super(1);
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01b5  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x01c8  */
        /* JADX WARN: Type inference failed for: r5v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
        @Override // kotlin.jvm.functions.Function1
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invoke(java.lang.Object r31) {
            /*
                Method dump skipped, instructions count: 499
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridState$scrollableState$1.invoke(java.lang.Object):java.lang.Object");
        }
    });
    public final boolean prefetchingEnabled = true;
    public final LazyGridState$remeasurementModifier$1 remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.lazy.grid.LazyGridState$remeasurementModifier$1
        @Override // androidx.compose.ui.layout.RemeasurementModifier
        public final void onRemeasurementAvailable(LayoutNode layoutNode) {
            LazyGridState.this.remeasurement = layoutNode;
        }
    };
    public final AwaitFirstLayoutModifier awaitLayoutModifier = new AwaitFirstLayoutModifier();
    public final LazyLayoutItemAnimator itemAnimator = new LazyLayoutItemAnimator();
    public final LazyLayoutBeyondBoundsInfo beyondBoundsInfo = new LazyLayoutBeyondBoundsInfo();
    public final LazyGridState$prefetchScope$1 prefetchScope = new LazyGridState$prefetchScope$1(this);
    public final LazyLayoutPinnedItemList pinnedItems = new LazyLayoutPinnedItemList();

    /* JADX WARN: Type inference failed for: r3v7, types: [androidx.compose.foundation.lazy.grid.LazyGridState$remeasurementModifier$1] */
    public LazyGridState(final int i, int i2) {
        MutableState mutableStateOf;
        MutableState mutableStateOf2;
        this.scrollPosition = new LazyGridScrollPosition(i, i2);
        this.prefetchState = new LazyLayoutPrefetchState(new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridState$prefetchState$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                NestedPrefetchScope nestedPrefetchScope = (NestedPrefetchScope) obj;
                LazyGridPrefetchStrategy lazyGridPrefetchStrategy = LazyGridState.this.prefetchStrategy;
                int i3 = i;
                Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
                Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot), currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null);
                DefaultLazyGridPrefetchStrategy defaultLazyGridPrefetchStrategy = (DefaultLazyGridPrefetchStrategy) lazyGridPrefetchStrategy;
                int i4 = 0;
                while (true) {
                    defaultLazyGridPrefetchStrategy.getClass();
                    if (i4 >= 2) {
                        return Unit.INSTANCE;
                    }
                    nestedPrefetchScope.schedulePrefetch(i3 + i4);
                    i4++;
                }
            }
        });
        mutableStateOf = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.placementScopeInvalidator = mutableStateOf;
        mutableStateOf2 = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.measurementScopeInvalidator = mutableStateOf2;
        Boolean bool = Boolean.FALSE;
        this.canScrollForward$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.canScrollBackward$delegate = SnapshotStateKt.mutableStateOf$default(bool);
    }

    public static Object animateScrollToItem$default(LazyGridState lazyGridState, int i, Continuation continuation) {
        lazyGridState.getClass();
        Object scroll = lazyGridState.scroll(MutatePriority.Default, new LazyGridState$animateScrollToItem$2(lazyGridState, i, 0, null), (ContinuationImpl) continuation);
        return scroll == CoroutineSingletons.COROUTINE_SUSPENDED ? scroll : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void applyMeasureResult$foundation_release(androidx.compose.foundation.lazy.grid.LazyGridMeasureResult r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridState.applyMeasureResult$foundation_release(androidx.compose.foundation.lazy.grid.LazyGridMeasureResult, boolean):void");
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final float dispatchRawDelta(float f) {
        return this.scrollableState.dispatchRawDelta(f);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollBackward() {
        return ((Boolean) ((SnapshotMutableStateImpl) this.canScrollBackward$delegate).getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollForward() {
        return ((Boolean) ((SnapshotMutableStateImpl) this.canScrollForward$delegate).getValue()).booleanValue();
    }

    public final LazyGridLayoutInfo getLayoutInfo() {
        return (LazyGridLayoutInfo) ((SnapshotMutableStateImpl) this.layoutInfoState).getValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    /* JADX WARN: Type inference failed for: r14v4, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final void notifyPrefetchOnScroll(float f, LazyGridLayoutInfo lazyGridLayoutInfo) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.prefetchingEnabled) {
            DefaultLazyGridPrefetchStrategy defaultLazyGridPrefetchStrategy = (DefaultLazyGridPrefetchStrategy) this.prefetchStrategy;
            defaultLazyGridPrefetchStrategy.getClass();
            LazyGridMeasureResult lazyGridMeasureResult = (LazyGridMeasureResult) lazyGridLayoutInfo;
            if (lazyGridMeasureResult.visibleItemsInfo.isEmpty()) {
                return;
            }
            boolean z = f < 0.0f;
            Orientation orientation = Orientation.Vertical;
            Orientation orientation2 = lazyGridMeasureResult.orientation;
            if (z) {
                LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) CollectionsKt.last(lazyGridMeasureResult.visibleItemsInfo);
                i = (orientation2 == orientation ? ((LazyGridMeasuredItem) lazyGridItemInfo).row : ((LazyGridMeasuredItem) lazyGridItemInfo).column) + 1;
                i2 = ((LazyGridMeasuredItem) ((LazyGridItemInfo) CollectionsKt.last(lazyGridMeasureResult.visibleItemsInfo))).index + 1;
            } else {
                LazyGridItemInfo lazyGridItemInfo2 = (LazyGridItemInfo) CollectionsKt.first(lazyGridMeasureResult.visibleItemsInfo);
                i = (orientation2 == orientation ? ((LazyGridMeasuredItem) lazyGridItemInfo2).row : ((LazyGridMeasuredItem) lazyGridItemInfo2).column) - 1;
                i2 = ((LazyGridMeasuredItem) ((LazyGridItemInfo) CollectionsKt.first(lazyGridMeasureResult.visibleItemsInfo))).index - 1;
            }
            if (i2 < 0 || i2 >= lazyGridMeasureResult.totalItemsCount) {
                return;
            }
            int i6 = defaultLazyGridPrefetchStrategy.lineToPrefetch;
            MutableVector mutableVector = defaultLazyGridPrefetchStrategy.currentLinePrefetchHandles;
            if (i != i6 && i >= 0) {
                if (defaultLazyGridPrefetchStrategy.wasScrollingForward != z && (i5 = mutableVector.size) > 0) {
                    Object[] objArr = mutableVector.content;
                    int i7 = 0;
                    do {
                        ((LazyLayoutPrefetchState.PrefetchHandle) objArr[i7]).cancel();
                        i7++;
                    } while (i7 < i5);
                }
                defaultLazyGridPrefetchStrategy.wasScrollingForward = z;
                defaultLazyGridPrefetchStrategy.lineToPrefetch = i;
                mutableVector.clear();
                LazyGridState$prefetchScope$1 lazyGridState$prefetchScope$1 = this.prefetchScope;
                lazyGridState$prefetchScope$1.getClass();
                ArrayList arrayList = new ArrayList();
                LazyGridState lazyGridState = lazyGridState$prefetchScope$1.this$0;
                Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    List list = (List) ((LazyGridMeasureResult) ((SnapshotMutableStateImpl) lazyGridState.layoutInfoState).getValue()).prefetchInfoRetriever.invoke(Integer.valueOf(i));
                    int size = list.size();
                    int i8 = 0;
                    while (i8 < size) {
                        Pair pair = (Pair) list.get(i8);
                        LazyGridState lazyGridState2 = lazyGridState;
                        arrayList.add(lazyGridState.prefetchState.m137schedulePrefetch0kLqBqw(((Constraints) pair.getSecond()).value, ((Number) pair.getFirst()).intValue()));
                        i8++;
                        lazyGridState = lazyGridState2;
                    }
                    Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    mutableVector.addAll(mutableVector.size, (List) arrayList);
                } catch (Throwable th) {
                    Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            if (!z) {
                if (lazyGridMeasureResult.viewportStartOffset - LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis((LazyGridItemInfo) CollectionsKt.first(lazyGridMeasureResult.visibleItemsInfo), orientation2) >= f || (i3 = mutableVector.size) <= 0) {
                    return;
                }
                Object[] objArr2 = mutableVector.content;
                int i9 = 0;
                do {
                    ((LazyLayoutPrefetchState.PrefetchHandle) objArr2[i9]).markAsUrgent();
                    i9++;
                } while (i9 < i3);
                return;
            }
            LazyGridItemInfo lazyGridItemInfo3 = (LazyGridItemInfo) CollectionsKt.last(lazyGridMeasureResult.visibleItemsInfo);
            if (((LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis(lazyGridItemInfo3, orientation2) + ((int) (orientation2 == orientation ? ((LazyGridMeasuredItem) lazyGridItemInfo3).size & 4294967295L : ((LazyGridMeasuredItem) lazyGridItemInfo3).size >> 32))) + lazyGridMeasureResult.mainAxisItemSpacing) - lazyGridMeasureResult.viewportEndOffset >= (-f) || (i4 = mutableVector.size) <= 0) {
                return;
            }
            Object[] objArr3 = mutableVector.content;
            int i10 = 0;
            do {
                ((LazyLayoutPrefetchState.PrefetchHandle) objArr3[i10]).markAsUrgent();
                i10++;
            } while (i10 < i4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0069 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object scroll(androidx.compose.foundation.MutatePriority r6, kotlin.jvm.functions.Function2 r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof androidx.compose.foundation.lazy.grid.LazyGridState$scroll$1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.lazy.grid.LazyGridState$scroll$1 r0 = (androidx.compose.foundation.lazy.grid.LazyGridState$scroll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.lazy.grid.LazyGridState$scroll$1 r0 = new androidx.compose.foundation.lazy.grid.LazyGridState$scroll$1
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L44
            if (r2 == r4) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6a
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            java.lang.Object r5 = r0.L$2
            r7 = r5
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r5 = r0.L$1
            r6 = r5
            androidx.compose.foundation.MutatePriority r6 = (androidx.compose.foundation.MutatePriority) r6
            java.lang.Object r5 = r0.L$0
            androidx.compose.foundation.lazy.grid.LazyGridState r5 = (androidx.compose.foundation.lazy.grid.LazyGridState) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L58
        L44:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r4
            androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier r8 = r5.awaitLayoutModifier
            java.lang.Object r8 = r8.waitForFirstLayout(r0)
            if (r8 != r1) goto L58
            return r1
        L58:
            androidx.compose.foundation.gestures.ScrollableState r5 = r5.scrollableState
            r8 = 0
            r0.L$0 = r8
            r0.L$1 = r8
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r5 = r5.scroll(r6, r7, r0)
            if (r5 != r1) goto L6a
            return r1
        L6a:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridState.scroll(androidx.compose.foundation.MutatePriority, kotlin.jvm.functions.Function2, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object scrollToItem(int i, int i2, Continuation continuation) {
        Object scroll = scroll(MutatePriority.Default, new LazyGridState$scrollToItem$2(this, i, i2, null), (ContinuationImpl) continuation);
        return scroll == CoroutineSingletons.COROUTINE_SUSPENDED ? scroll : Unit.INSTANCE;
    }

    public final void snapToItemIndexInternal$foundation_release(int i, int i2) {
        LazyGridScrollPosition lazyGridScrollPosition = this.scrollPosition;
        if (lazyGridScrollPosition.getIndex() != i || lazyGridScrollPosition.getScrollOffset() != i2) {
            LazyLayoutItemAnimator lazyLayoutItemAnimator = this.itemAnimator;
            lazyLayoutItemAnimator.releaseAnimations();
            lazyLayoutItemAnimator.keyIndexMap = null;
            lazyLayoutItemAnimator.firstVisibleIndex = -1;
        }
        lazyGridScrollPosition.update(i, i2);
        lazyGridScrollPosition.lastKnownFirstItemKey = null;
        LayoutNode layoutNode = this.remeasurement;
        if (layoutNode != null) {
            layoutNode.forceRemeasure();
        }
    }
}
