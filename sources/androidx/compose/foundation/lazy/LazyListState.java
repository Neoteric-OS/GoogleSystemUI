package androidx.compose.foundation.lazy;

import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.node.LayoutNode;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyListState implements ScrollableState {
    public static final SaverKt$Saver$1 Saver = ListSaverKt.listSaver(new Function2() { // from class: androidx.compose.foundation.lazy.LazyListState$Companion$Saver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            LazyListState lazyListState = (LazyListState) obj2;
            return CollectionsKt__CollectionsKt.listOf(Integer.valueOf(lazyListState.scrollPosition.getIndex()), Integer.valueOf(lazyListState.scrollPosition.getScrollOffset()));
        }
    }, new Function1() { // from class: androidx.compose.foundation.lazy.LazyListState$Companion$Saver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            List list = (List) obj;
            return new LazyListState(((Number) list.get(0)).intValue(), ((Number) list.get(1)).intValue());
        }
    });
    public AnimationState _scrollDeltaBetweenPasses;
    public final AwaitFirstLayoutModifier awaitLayoutModifier;
    public final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;
    public final MutableState canScrollBackward$delegate;
    public final MutableState canScrollForward$delegate;
    public boolean hasLookaheadPassOccurred;
    public final MutableInteractionSource internalInteractionSource;
    public final LazyLayoutItemAnimator itemAnimator;
    public final MutableState layoutInfoState;
    public final MutableState measurementScopeInvalidator;
    public final LazyLayoutPinnedItemList pinnedItems;
    public final MutableState placementScopeInvalidator;
    public LazyListMeasureResult postLookaheadLayoutInfo;
    public final LazyListState$prefetchScope$1 prefetchScope;
    public final LazyLayoutPrefetchState prefetchState;
    public final LazyListPrefetchStrategy prefetchStrategy;
    public final boolean prefetchingEnabled;
    public LayoutNode remeasurement;
    public final LazyListState$remeasurementModifier$1 remeasurementModifier;
    public final LazyListScrollPosition scrollPosition;
    public float scrollToBeConsumed;
    public final ScrollableState scrollableState;

    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.compose.foundation.lazy.LazyListState$remeasurementModifier$1] */
    public LazyListState(final int i, int i2) {
        MutableState mutableStateOf;
        MutableState mutableStateOf2;
        DefaultLazyListPrefetchStrategy defaultLazyListPrefetchStrategy = new DefaultLazyListPrefetchStrategy();
        defaultLazyListPrefetchStrategy.indexToPrefetch = -1;
        this.prefetchStrategy = defaultLazyListPrefetchStrategy;
        this.scrollPosition = new LazyListScrollPosition(i, i2);
        this.layoutInfoState = SnapshotStateKt.mutableStateOf(LazyListStateKt.EmptyLazyListMeasureResult, SnapshotStateKt.neverEqualPolicy());
        this.internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
        this.scrollableState = ScrollableStateKt.ScrollableState(new Function1() { // from class: androidx.compose.foundation.lazy.LazyListState$scrollableState$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LazyListMeasureResult lazyListMeasureResult;
                float floatValue = ((Number) obj).floatValue();
                LazyListState lazyListState = LazyListState.this;
                float f = -floatValue;
                if ((f >= 0.0f || lazyListState.getCanScrollForward()) && (f <= 0.0f || lazyListState.getCanScrollBackward())) {
                    if (Math.abs(lazyListState.scrollToBeConsumed) > 0.5f) {
                        InlineClassHelperKt.throwIllegalStateException("entered drag with non-zero pending scroll");
                    }
                    float f2 = lazyListState.scrollToBeConsumed + f;
                    lazyListState.scrollToBeConsumed = f2;
                    if (Math.abs(f2) > 0.5f) {
                        float f3 = lazyListState.scrollToBeConsumed;
                        int round = Math.round(f3);
                        LazyListMeasureResult copyWithScrollDeltaWithoutRemeasure = ((LazyListMeasureResult) ((SnapshotMutableStateImpl) lazyListState.layoutInfoState).getValue()).copyWithScrollDeltaWithoutRemeasure(round, !lazyListState.hasLookaheadPassOccurred);
                        if (copyWithScrollDeltaWithoutRemeasure != null && (lazyListMeasureResult = lazyListState.postLookaheadLayoutInfo) != null) {
                            LazyListMeasureResult copyWithScrollDeltaWithoutRemeasure2 = lazyListMeasureResult.copyWithScrollDeltaWithoutRemeasure(round, true);
                            if (copyWithScrollDeltaWithoutRemeasure2 != null) {
                                lazyListState.postLookaheadLayoutInfo = copyWithScrollDeltaWithoutRemeasure2;
                            } else {
                                copyWithScrollDeltaWithoutRemeasure = null;
                            }
                        }
                        if (copyWithScrollDeltaWithoutRemeasure != null) {
                            lazyListState.applyMeasureResult$foundation_release(copyWithScrollDeltaWithoutRemeasure, lazyListState.hasLookaheadPassOccurred, true);
                            lazyListState.placementScopeInvalidator.setValue(Unit.INSTANCE);
                            lazyListState.notifyPrefetchOnScroll(f3 - lazyListState.scrollToBeConsumed, copyWithScrollDeltaWithoutRemeasure);
                        } else {
                            LayoutNode layoutNode = lazyListState.remeasurement;
                            if (layoutNode != null) {
                                layoutNode.forceRemeasure();
                            }
                            lazyListState.notifyPrefetchOnScroll(f3 - lazyListState.scrollToBeConsumed, lazyListState.getLayoutInfo());
                        }
                    }
                    if (Math.abs(lazyListState.scrollToBeConsumed) > 0.5f) {
                        f -= lazyListState.scrollToBeConsumed;
                        lazyListState.scrollToBeConsumed = 0.0f;
                    }
                } else {
                    f = 0.0f;
                }
                return Float.valueOf(-f);
            }
        });
        this.prefetchingEnabled = true;
        this.remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.lazy.LazyListState$remeasurementModifier$1
            @Override // androidx.compose.ui.layout.RemeasurementModifier
            public final void onRemeasurementAvailable(LayoutNode layoutNode) {
                LazyListState.this.remeasurement = layoutNode;
            }
        };
        this.awaitLayoutModifier = new AwaitFirstLayoutModifier();
        this.itemAnimator = new LazyLayoutItemAnimator();
        this.beyondBoundsInfo = new LazyLayoutBeyondBoundsInfo();
        this.prefetchState = new LazyLayoutPrefetchState(new Function1() { // from class: androidx.compose.foundation.lazy.LazyListState$prefetchState$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                NestedPrefetchScope nestedPrefetchScope = (NestedPrefetchScope) obj;
                LazyListPrefetchStrategy lazyListPrefetchStrategy = LazyListState.this.prefetchStrategy;
                int i3 = i;
                Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
                Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot), currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null);
                for (int i4 = 0; i4 < 2; i4++) {
                    nestedPrefetchScope.schedulePrefetch(i3 + i4);
                }
                return Unit.INSTANCE;
            }
        });
        this.prefetchScope = new LazyListState$prefetchScope$1(this);
        this.pinnedItems = new LazyLayoutPinnedItemList();
        mutableStateOf = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.measurementScopeInvalidator = mutableStateOf;
        Boolean bool = Boolean.FALSE;
        this.canScrollForward$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.canScrollBackward$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        mutableStateOf2 = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.placementScopeInvalidator = mutableStateOf2;
        TwoWayConverter twoWayConverter = VectorConvertersKt.FloatToVector;
        this._scrollDeltaBetweenPasses = AnimationStateKt.AnimationState$default();
    }

    public final void applyMeasureResult$foundation_release(LazyListMeasureResult lazyListMeasureResult, boolean z, boolean z2) {
        if (!z && this.hasLookaheadPassOccurred) {
            this.postLookaheadLayoutInfo = lazyListMeasureResult;
            return;
        }
        if (z) {
            this.hasLookaheadPassOccurred = true;
        }
        LazyListMeasuredItem lazyListMeasuredItem = lazyListMeasureResult.firstVisibleItem;
        int i = lazyListMeasuredItem != null ? lazyListMeasuredItem.index : 0;
        int i2 = lazyListMeasureResult.firstVisibleItemScrollOffset;
        ((SnapshotMutableStateImpl) this.canScrollBackward$delegate).setValue(Boolean.valueOf((i == 0 && i2 == 0) ? false : true));
        ((SnapshotMutableStateImpl) this.canScrollForward$delegate).setValue(Boolean.valueOf(lazyListMeasureResult.canScrollForward));
        this.scrollToBeConsumed -= lazyListMeasureResult.consumedScroll;
        ((SnapshotMutableStateImpl) this.layoutInfoState).setValue(lazyListMeasureResult);
        LazyListScrollPosition lazyListScrollPosition = this.scrollPosition;
        if (z2) {
            lazyListScrollPosition.getClass();
            if (i2 < 0.0f) {
                InlineClassHelperKt.throwIllegalStateException("scrollOffset should be non-negative");
            }
            ((SnapshotMutableIntStateImpl) lazyListScrollPosition.scrollOffset$delegate).setIntValue(i2);
        } else {
            lazyListScrollPosition.getClass();
            lazyListScrollPosition.lastKnownFirstItemKey = lazyListMeasuredItem != null ? lazyListMeasuredItem.key : null;
            if (lazyListScrollPosition.hadFirstNotEmptyLayout || lazyListMeasureResult.totalItemsCount > 0) {
                lazyListScrollPosition.hadFirstNotEmptyLayout = true;
                if (i2 < 0.0f) {
                    InlineClassHelperKt.throwIllegalStateException("scrollOffset should be non-negative");
                }
                lazyListScrollPosition.update(lazyListMeasuredItem != null ? lazyListMeasuredItem.index : 0, i2);
            }
            if (this.prefetchingEnabled) {
                DefaultLazyListPrefetchStrategy defaultLazyListPrefetchStrategy = (DefaultLazyListPrefetchStrategy) this.prefetchStrategy;
                if (defaultLazyListPrefetchStrategy.indexToPrefetch != -1 && !lazyListMeasureResult.visibleItemsInfo.isEmpty()) {
                    if (defaultLazyListPrefetchStrategy.indexToPrefetch != (defaultLazyListPrefetchStrategy.wasScrollingForward ? ((LazyListMeasuredItem) ((LazyListItemInfo) CollectionsKt.last(lazyListMeasureResult.visibleItemsInfo))).index + 1 : ((LazyListMeasuredItem) ((LazyListItemInfo) CollectionsKt.first(lazyListMeasureResult.visibleItemsInfo))).index - 1)) {
                        defaultLazyListPrefetchStrategy.indexToPrefetch = -1;
                        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle = defaultLazyListPrefetchStrategy.currentPrefetchHandle;
                        if (prefetchHandle != null) {
                            prefetchHandle.cancel();
                        }
                        defaultLazyListPrefetchStrategy.currentPrefetchHandle = null;
                    }
                }
            }
        }
        if (z) {
            float mo51toPx0680j_4 = lazyListMeasureResult.density.mo51toPx0680j_4(LazyListStateKt.DeltaThresholdForScrollAnimation);
            float f = lazyListMeasureResult.scrollBackAmount;
            if (f <= mo51toPx0680j_4) {
                return;
            }
            Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
            Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                float floatValue = ((Number) ((SnapshotMutableStateImpl) this._scrollDeltaBetweenPasses.value$delegate).getValue()).floatValue();
                AnimationState animationState = this._scrollDeltaBetweenPasses;
                boolean z3 = animationState.isRunning;
                CoroutineScope coroutineScope = lazyListMeasureResult.coroutineScope;
                if (z3) {
                    this._scrollDeltaBetweenPasses = AnimationStateKt.copy$default(animationState, floatValue - f, 0.0f, 30);
                    BuildersKt.launch$default(coroutineScope, null, null, new LazyListState$updateScrollDeltaForPostLookahead$2$1(this, null), 3);
                } else {
                    this._scrollDeltaBetweenPasses = new AnimationState(VectorConvertersKt.FloatToVector, Float.valueOf(-f), null, 60);
                    BuildersKt.launch$default(coroutineScope, null, null, new LazyListState$updateScrollDeltaForPostLookahead$2$2(this, null), 3);
                }
                Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            } catch (Throwable th) {
                Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                throw th;
            }
        }
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

    public final LazyListLayoutInfo getLayoutInfo() {
        return (LazyListLayoutInfo) ((SnapshotMutableStateImpl) this.layoutInfoState).getValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    public final void notifyPrefetchOnScroll(float f, LazyListLayoutInfo lazyListLayoutInfo) {
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle2;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle3;
        if (this.prefetchingEnabled) {
            DefaultLazyListPrefetchStrategy defaultLazyListPrefetchStrategy = (DefaultLazyListPrefetchStrategy) this.prefetchStrategy;
            LazyListMeasureResult lazyListMeasureResult = (LazyListMeasureResult) lazyListLayoutInfo;
            if (lazyListMeasureResult.visibleItemsInfo.isEmpty()) {
                return;
            }
            boolean z = f < 0.0f;
            int i = z ? ((LazyListMeasuredItem) ((LazyListItemInfo) CollectionsKt.last(lazyListMeasureResult.visibleItemsInfo))).index + 1 : ((LazyListMeasuredItem) ((LazyListItemInfo) CollectionsKt.first(lazyListMeasureResult.visibleItemsInfo))).index - 1;
            if (i < 0 || i >= lazyListMeasureResult.totalItemsCount) {
                return;
            }
            if (i != defaultLazyListPrefetchStrategy.indexToPrefetch) {
                if (defaultLazyListPrefetchStrategy.wasScrollingForward != z && (prefetchHandle3 = defaultLazyListPrefetchStrategy.currentPrefetchHandle) != null) {
                    prefetchHandle3.cancel();
                }
                defaultLazyListPrefetchStrategy.wasScrollingForward = z;
                defaultLazyListPrefetchStrategy.indexToPrefetch = i;
                LazyListState lazyListState = this.prefetchScope.this$0;
                Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    long j = ((LazyListMeasureResult) ((SnapshotMutableStateImpl) lazyListState.layoutInfoState).getValue()).childConstraints;
                    Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    defaultLazyListPrefetchStrategy.currentPrefetchHandle = lazyListState.prefetchState.m137schedulePrefetch0kLqBqw(j, i);
                } catch (Throwable th) {
                    Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            if (!z) {
                if (lazyListMeasureResult.viewportStartOffset - ((LazyListMeasuredItem) ((LazyListItemInfo) CollectionsKt.first(lazyListMeasureResult.visibleItemsInfo))).offset >= f || (prefetchHandle = defaultLazyListPrefetchStrategy.currentPrefetchHandle) == null) {
                    return;
                }
                prefetchHandle.markAsUrgent();
                return;
            }
            LazyListMeasuredItem lazyListMeasuredItem = (LazyListMeasuredItem) ((LazyListItemInfo) CollectionsKt.last(lazyListMeasureResult.visibleItemsInfo));
            if (((lazyListMeasuredItem.offset + lazyListMeasuredItem.size) + lazyListMeasureResult.mainAxisItemSpacing) - lazyListMeasureResult.viewportEndOffset >= (-f) || (prefetchHandle2 = defaultLazyListPrefetchStrategy.currentPrefetchHandle) == null) {
                return;
            }
            prefetchHandle2.markAsUrgent();
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
            boolean r0 = r8 instanceof androidx.compose.foundation.lazy.LazyListState$scroll$1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.lazy.LazyListState$scroll$1 r0 = (androidx.compose.foundation.lazy.LazyListState$scroll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.lazy.LazyListState$scroll$1 r0 = new androidx.compose.foundation.lazy.LazyListState$scroll$1
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
            androidx.compose.foundation.lazy.LazyListState r5 = (androidx.compose.foundation.lazy.LazyListState) r5
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListState.scroll(androidx.compose.foundation.MutatePriority, kotlin.jvm.functions.Function2, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
