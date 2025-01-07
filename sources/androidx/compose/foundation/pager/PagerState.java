package androidx.compose.foundation.pager;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PagerState implements ScrollableState {
    public float accumulator;
    public final AwaitFirstLayoutModifier awaitLayoutModifier;
    public final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;
    public final MutableState canScrollBackward$delegate;
    public final MutableState canScrollForward$delegate;
    public LazyLayoutPrefetchState.PrefetchHandle currentPrefetchHandle;
    public Density density;
    public int firstVisiblePage;
    public int firstVisiblePageOffset;
    public int indexToPrefetch;
    public final MutableInteractionSource internalInteractionSource;
    public final MutableState isLastScrollBackwardState;
    public final MutableState isLastScrollForwardState;
    public long maxScrollOffset;
    public final MutableState measurementScopeInvalidator;
    public long minScrollOffset;
    public final MutableState pagerLayoutInfoState;
    public final LazyLayoutPinnedItemList pinnedPages;
    public final MutableState placementScopeInvalidator;
    public final LazyLayoutPrefetchState prefetchState;
    public final boolean prefetchingEnabled;
    public long premeasureConstraints;
    public float previousPassDelta;
    public final MutableIntState programmaticScrollTargetPage$delegate;
    public final MutableState remeasurement$delegate;
    public final PagerState$remeasurementModifier$1 remeasurementModifier;
    public final PagerScrollPosition scrollPosition;
    public final ScrollableState scrollableState;
    public final State settledPage$delegate;
    public final MutableIntState settledPageState$delegate;
    public final MutableState upDownDifference$delegate;
    public boolean wasPrefetchingForward;

    /* JADX WARN: Type inference failed for: r5v9, types: [androidx.compose.foundation.pager.PagerState$remeasurementModifier$1] */
    public PagerState(int i, float f) {
        MutableState mutableStateOf;
        MutableState mutableStateOf2;
        double d = f;
        if (-0.5d > d || d > 0.5d) {
            InlineClassHelperKt.throwIllegalArgumentException("currentPageOffsetFraction " + f + " is not within the range -0.5 to 0.5");
        }
        this.upDownDifference$delegate = SnapshotStateKt.mutableStateOf$default(new Offset(0L));
        this.scrollPosition = new PagerScrollPosition(i, f, this);
        this.firstVisiblePage = i;
        this.maxScrollOffset = Long.MAX_VALUE;
        this.scrollableState = ScrollableStateKt.ScrollableState(new Function1() { // from class: androidx.compose.foundation.pager.PagerState$scrollableState$1
            {
                super(1);
            }

            /* JADX WARN: Removed duplicated region for block: B:56:0x019c  */
            /* JADX WARN: Removed duplicated region for block: B:58:0x01d8  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x01dd  */
            /* JADX WARN: Removed duplicated region for block: B:61:0x01a7  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r37) {
                /*
                    Method dump skipped, instructions count: 490
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerState$scrollableState$1.invoke(java.lang.Object):java.lang.Object");
            }
        });
        this.prefetchingEnabled = true;
        this.indexToPrefetch = -1;
        this.pagerLayoutInfoState = SnapshotStateKt.mutableStateOf(PagerStateKt.EmptyLayoutInfo, SnapshotStateKt.neverEqualPolicy());
        this.density = PagerStateKt.UnitDensity;
        this.internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
        this.programmaticScrollTargetPage$delegate = SnapshotIntStateKt.mutableIntStateOf(-1);
        this.settledPageState$delegate = SnapshotIntStateKt.mutableIntStateOf(i);
        this.settledPage$delegate = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: androidx.compose.foundation.pager.PagerState$settledPage$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(PagerState.this.scrollableState.isScrollInProgress() ? ((SnapshotMutableIntStateImpl) PagerState.this.settledPageState$delegate).getIntValue() : PagerState.this.getCurrentPage());
            }
        });
        SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: androidx.compose.foundation.pager.PagerState$targetPage$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                int currentPage;
                if (!PagerState.this.scrollableState.isScrollInProgress()) {
                    currentPage = PagerState.this.getCurrentPage();
                } else if (((SnapshotMutableIntStateImpl) PagerState.this.programmaticScrollTargetPage$delegate).getIntValue() != -1) {
                    currentPage = ((SnapshotMutableIntStateImpl) PagerState.this.programmaticScrollTargetPage$delegate).getIntValue();
                } else {
                    float abs = Math.abs(PagerState.this.scrollPosition.getCurrentPageOffsetFraction());
                    PagerState pagerState = PagerState.this;
                    currentPage = abs >= Math.abs(Math.min(pagerState.density.mo51toPx0680j_4(PagerStateKt.DefaultPositionThreshold), ((float) pagerState.getPageSize$foundation_release()) / 2.0f) / ((float) pagerState.getPageSize$foundation_release())) ? ((Boolean) ((SnapshotMutableStateImpl) PagerState.this.isLastScrollForwardState).getValue()).booleanValue() ? PagerState.this.firstVisiblePage + 1 : PagerState.this.firstVisiblePage : PagerState.this.getCurrentPage();
                }
                return Integer.valueOf(PagerState.this.coerceInPageRange(currentPage));
            }
        });
        this.prefetchState = new LazyLayoutPrefetchState(null);
        this.beyondBoundsInfo = new LazyLayoutBeyondBoundsInfo();
        this.awaitLayoutModifier = new AwaitFirstLayoutModifier();
        this.remeasurement$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.pager.PagerState$remeasurementModifier$1
            @Override // androidx.compose.ui.layout.RemeasurementModifier
            public final void onRemeasurementAvailable(LayoutNode layoutNode) {
                ((SnapshotMutableStateImpl) PagerState.this.remeasurement$delegate).setValue(layoutNode);
            }
        };
        this.premeasureConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15);
        this.pinnedPages = new LazyLayoutPinnedItemList();
        mutableStateOf = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.placementScopeInvalidator = mutableStateOf;
        mutableStateOf2 = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.measurementScopeInvalidator = mutableStateOf2;
        Boolean bool = Boolean.FALSE;
        this.canScrollForward$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.canScrollBackward$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.isLastScrollForwardState = SnapshotStateKt.mutableStateOf$default(bool);
        this.isLastScrollBackwardState = SnapshotStateKt.mutableStateOf$default(bool);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0086 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object scroll$suspendImpl(androidx.compose.foundation.pager.PagerState r6, androidx.compose.foundation.MutatePriority r7, kotlin.jvm.functions.Function2 r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            boolean r0 = r9 instanceof androidx.compose.foundation.pager.PagerState$scroll$1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.foundation.pager.PagerState$scroll$1 r0 = (androidx.compose.foundation.pager.PagerState$scroll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.pager.PagerState$scroll$1 r0 = new androidx.compose.foundation.pager.PagerState$scroll$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L4a
            if (r2 == r5) goto L38
            if (r2 != r4) goto L30
            java.lang.Object r6 = r0.L$0
            androidx.compose.foundation.pager.PagerState r6 = (androidx.compose.foundation.pager.PagerState) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L87
        L30:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L38:
            java.lang.Object r6 = r0.L$2
            r8 = r6
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r6 = r0.L$1
            r7 = r6
            androidx.compose.foundation.MutatePriority r7 = (androidx.compose.foundation.MutatePriority) r7
            java.lang.Object r6 = r0.L$0
            androidx.compose.foundation.pager.PagerState r6 = (androidx.compose.foundation.pager.PagerState) r6
            kotlin.ResultKt.throwOnFailure(r9)
            goto L62
        L4a:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r5
            androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier r9 = r6.awaitLayoutModifier
            java.lang.Object r9 = r9.waitForFirstLayout(r0)
            if (r9 != r1) goto L5e
            goto L5f
        L5e:
            r9 = r3
        L5f:
            if (r9 != r1) goto L62
            return r1
        L62:
            androidx.compose.foundation.gestures.ScrollableState r9 = r6.scrollableState
            boolean r9 = r9.isScrollInProgress()
            if (r9 != 0) goto L75
            int r9 = r6.getCurrentPage()
            androidx.compose.runtime.MutableIntState r2 = r6.settledPageState$delegate
            androidx.compose.runtime.SnapshotMutableIntStateImpl r2 = (androidx.compose.runtime.SnapshotMutableIntStateImpl) r2
            r2.setIntValue(r9)
        L75:
            androidx.compose.foundation.gestures.ScrollableState r9 = r6.scrollableState
            r0.L$0 = r6
            r2 = 0
            r0.L$1 = r2
            r0.L$2 = r2
            r0.label = r4
            java.lang.Object r7 = r9.scroll(r7, r8, r0)
            if (r7 != r1) goto L87
            return r1
        L87:
            androidx.compose.runtime.MutableIntState r6 = r6.programmaticScrollTargetPage$delegate
            androidx.compose.runtime.SnapshotMutableIntStateImpl r6 = (androidx.compose.runtime.SnapshotMutableIntStateImpl) r6
            r7 = -1
            r6.setIntValue(r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerState.scroll$suspendImpl(androidx.compose.foundation.pager.PagerState, androidx.compose.foundation.MutatePriority, kotlin.jvm.functions.Function2, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00c4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Type inference failed for: r14v6, types: [androidx.compose.animation.core.AnimationSpec] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateScrollToPage(int r13, androidx.compose.animation.core.SpringSpec r14, kotlin.coroutines.jvm.internal.ContinuationImpl r15) {
        /*
            r12 = this;
            boolean r0 = r15 instanceof androidx.compose.foundation.pager.PagerState$animateScrollToPage$1
            if (r0 == 0) goto L13
            r0 = r15
            androidx.compose.foundation.pager.PagerState$animateScrollToPage$1 r0 = (androidx.compose.foundation.pager.PagerState$animateScrollToPage$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.pager.PagerState$animateScrollToPage$1 r0 = new androidx.compose.foundation.pager.PagerState$animateScrollToPage$1
            r0.<init>(r12, r15)
        L18:
            java.lang.Object r15 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L46
            if (r2 == r5) goto L35
            if (r2 != r4) goto L2d
            kotlin.ResultKt.throwOnFailure(r15)
            goto Lc4
        L2d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L35:
            float r12 = r0.F$0
            int r13 = r0.I$0
            java.lang.Object r14 = r0.L$1
            androidx.compose.animation.core.AnimationSpec r14 = (androidx.compose.animation.core.AnimationSpec) r14
            java.lang.Object r2 = r0.L$0
            androidx.compose.foundation.pager.PagerState r2 = (androidx.compose.foundation.pager.PagerState) r2
            kotlin.ResultKt.throwOnFailure(r15)
            r9 = r14
            goto L7d
        L46:
            kotlin.ResultKt.throwOnFailure(r15)
            int r15 = r12.getCurrentPage()
            r2 = 0
            if (r13 != r15) goto L5b
            androidx.compose.foundation.pager.PagerScrollPosition r15 = r12.scrollPosition
            float r15 = r15.getCurrentPageOffsetFraction()
            int r15 = (r15 > r2 ? 1 : (r15 == r2 ? 0 : -1))
            if (r15 != 0) goto L5b
            goto L61
        L5b:
            int r15 = r12.getPageCount()
            if (r15 != 0) goto L62
        L61:
            return r3
        L62:
            r0.L$0 = r12
            r0.L$1 = r14
            r0.I$0 = r13
            r0.F$0 = r2
            r0.label = r5
            androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier r15 = r12.awaitLayoutModifier
            java.lang.Object r15 = r15.waitForFirstLayout(r0)
            if (r15 != r1) goto L75
            goto L76
        L75:
            r15 = r3
        L76:
            if (r15 != r1) goto L79
            return r1
        L79:
            r9 = r14
            r11 = r2
            r2 = r12
            r12 = r11
        L7d:
            double r14 = (double) r12
            r5 = -4620693217682128896(0xbfe0000000000000, double:-0.5)
            int r5 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r5 > 0) goto L8b
            r5 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r14 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r14 > 0) goto L8b
            goto La1
        L8b:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "pageOffsetFraction "
            r14.<init>(r15)
            r14.append(r12)
            java.lang.String r15 = " is not within the range -0.5 to 0.5"
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            androidx.compose.foundation.internal.InlineClassHelperKt.throwIllegalArgumentException(r14)
        La1:
            int r7 = r2.coerceInPageRange(r13)
            int r13 = r2.getPageSizeWithSpacing$foundation_release()
            float r13 = (float) r13
            float r8 = r12 * r13
            androidx.compose.foundation.pager.PagerState$animateScrollToPage$3 r12 = new androidx.compose.foundation.pager.PagerState$animateScrollToPage$3
            r10 = 0
            r5 = r12
            r6 = r2
            r5.<init>(r6, r7, r8, r9, r10)
            r13 = 0
            r0.L$0 = r13
            r0.L$1 = r13
            r0.label = r4
            androidx.compose.foundation.MutatePriority r13 = androidx.compose.foundation.MutatePriority.Default
            java.lang.Object r12 = r2.scroll(r13, r12, r0)
            if (r12 != r1) goto Lc4
            return r1
        Lc4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerState.animateScrollToPage(int, androidx.compose.animation.core.SpringSpec, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void applyMeasureResult$foundation_release(PagerMeasureResult pagerMeasureResult, boolean z) {
        float f = pagerMeasureResult.currentPageOffsetFraction;
        PagerScrollPosition pagerScrollPosition = this.scrollPosition;
        boolean z2 = true;
        if (z) {
            ((SnapshotMutableFloatStateImpl) pagerScrollPosition.currentPageOffsetFraction$delegate).setFloatValue(f);
        } else {
            pagerScrollPosition.getClass();
            MeasuredPage measuredPage = pagerMeasureResult.currentPage;
            pagerScrollPosition.lastKnownCurrentPageKey = measuredPage != null ? measuredPage.key : null;
            if (pagerScrollPosition.hadFirstNotEmptyLayout || !pagerMeasureResult.visiblePagesInfo.isEmpty()) {
                pagerScrollPosition.hadFirstNotEmptyLayout = true;
                int i = measuredPage != null ? measuredPage.index : 0;
                ((SnapshotMutableIntStateImpl) pagerScrollPosition.currentPage$delegate).setIntValue(i);
                pagerScrollPosition.nearestRangeState.update(i);
                ((SnapshotMutableFloatStateImpl) pagerScrollPosition.currentPageOffsetFraction$delegate).setFloatValue(f);
            }
            if (this.indexToPrefetch != -1 && !pagerMeasureResult.visiblePagesInfo.isEmpty()) {
                boolean z3 = this.wasPrefetchingForward;
                int i2 = pagerMeasureResult.beyondViewportPageCount;
                if (this.indexToPrefetch != (z3 ? ((MeasuredPage) ((PageInfo) CollectionsKt.last(pagerMeasureResult.visiblePagesInfo))).index + i2 + 1 : (((MeasuredPage) ((PageInfo) CollectionsKt.first(pagerMeasureResult.visiblePagesInfo))).index - i2) - 1)) {
                    this.indexToPrefetch = -1;
                    LazyLayoutPrefetchState.PrefetchHandle prefetchHandle = this.currentPrefetchHandle;
                    if (prefetchHandle != null) {
                        prefetchHandle.cancel();
                    }
                    this.currentPrefetchHandle = null;
                }
            }
        }
        ((SnapshotMutableStateImpl) this.pagerLayoutInfoState).setValue(pagerMeasureResult);
        ((SnapshotMutableStateImpl) this.canScrollForward$delegate).setValue(Boolean.valueOf(pagerMeasureResult.canScrollForward));
        MeasuredPage measuredPage2 = pagerMeasureResult.firstVisiblePage;
        int i3 = measuredPage2 != null ? measuredPage2.index : 0;
        int i4 = pagerMeasureResult.firstVisiblePageScrollOffset;
        if (i3 == 0 && i4 == 0) {
            z2 = false;
        }
        ((SnapshotMutableStateImpl) this.canScrollBackward$delegate).setValue(Boolean.valueOf(z2));
        if (measuredPage2 != null) {
            this.firstVisiblePage = measuredPage2.index;
        }
        this.firstVisiblePageOffset = i4;
        Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            if (Math.abs(this.previousPassDelta) > 0.5f && this.prefetchingEnabled && isGestureActionMatchesScroll(this.previousPassDelta)) {
                notifyPrefetch(this.previousPassDelta, pagerMeasureResult);
            }
            Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            this.maxScrollOffset = PagerStateKt.calculateNewMaxScrollOffset(pagerMeasureResult, getPageCount());
            getPageCount();
            int m143getViewportSizeYbymL2g = (int) (pagerMeasureResult.orientation == Orientation.Horizontal ? pagerMeasureResult.m143getViewportSizeYbymL2g() >> 32 : pagerMeasureResult.m143getViewportSizeYbymL2g() & 4294967295L);
            pagerMeasureResult.snapPosition.getClass();
            this.minScrollOffset = RangesKt.coerceIn(0, 0, m143getViewportSizeYbymL2g);
        } catch (Throwable th) {
            Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            throw th;
        }
    }

    public final int coerceInPageRange(int i) {
        if (getPageCount() > 0) {
            return RangesKt.coerceIn(i, 0, getPageCount() - 1);
        }
        return 0;
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

    public final int getCurrentPage() {
        return ((SnapshotMutableIntStateImpl) this.scrollPosition.currentPage$delegate).getIntValue();
    }

    public final PagerLayoutInfo getLayoutInfo() {
        return (PagerLayoutInfo) ((SnapshotMutableStateImpl) this.pagerLayoutInfoState).getValue();
    }

    public abstract int getPageCount();

    public final int getPageSize$foundation_release() {
        return ((PagerMeasureResult) ((SnapshotMutableStateImpl) this.pagerLayoutInfoState).getValue()).pageSize;
    }

    public final int getPageSizeWithSpacing$foundation_release() {
        return ((PagerMeasureResult) ((SnapshotMutableStateImpl) this.pagerLayoutInfoState).getValue()).pageSpacing + getPageSize$foundation_release();
    }

    /* renamed from: getUpDownDifference-F1C5BW0$foundation_release, reason: not valid java name */
    public final long m144getUpDownDifferenceF1C5BW0$foundation_release() {
        return ((Offset) ((SnapshotMutableStateImpl) this.upDownDifference$delegate).getValue()).packedValue;
    }

    public final boolean isGestureActionMatchesScroll(float f) {
        if (((PagerMeasureResult) getLayoutInfo()).orientation != Orientation.Vertical ? Math.signum(f) != Math.signum(-Float.intBitsToFloat((int) (m144getUpDownDifferenceF1C5BW0$foundation_release() >> 32))) : Math.signum(f) != Math.signum(-Float.intBitsToFloat((int) (m144getUpDownDifferenceF1C5BW0$foundation_release() & 4294967295L)))) {
            if (((int) Float.intBitsToFloat((int) (m144getUpDownDifferenceF1C5BW0$foundation_release() >> 32))) != 0 || ((int) Float.intBitsToFloat((int) (m144getUpDownDifferenceF1C5BW0$foundation_release() & 4294967295L))) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    public final void notifyPrefetch(float f, PagerMeasureResult pagerMeasureResult) {
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle2;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle3;
        if (this.prefetchingEnabled && !pagerMeasureResult.visiblePagesInfo.isEmpty()) {
            boolean z = f > 0.0f;
            int i = pagerMeasureResult.beyondViewportPageCount;
            int i2 = z ? ((MeasuredPage) ((PageInfo) CollectionsKt.last(pagerMeasureResult.visiblePagesInfo))).index + i + 1 : (((MeasuredPage) ((PageInfo) CollectionsKt.first(pagerMeasureResult.visiblePagesInfo))).index - i) - 1;
            if (i2 < 0 || i2 >= getPageCount()) {
                return;
            }
            if (i2 != this.indexToPrefetch) {
                if (this.wasPrefetchingForward != z && (prefetchHandle3 = this.currentPrefetchHandle) != null) {
                    prefetchHandle3.cancel();
                }
                this.wasPrefetchingForward = z;
                this.indexToPrefetch = i2;
                this.currentPrefetchHandle = this.prefetchState.m137schedulePrefetch0kLqBqw(this.premeasureConstraints, i2);
            }
            if (z) {
                if ((((MeasuredPage) ((PageInfo) CollectionsKt.last(pagerMeasureResult.visiblePagesInfo))).offset + (pagerMeasureResult.pageSize + pagerMeasureResult.pageSpacing)) - pagerMeasureResult.viewportEndOffset >= f || (prefetchHandle2 = this.currentPrefetchHandle) == null) {
                    return;
                }
                prefetchHandle2.markAsUrgent();
                return;
            }
            if (pagerMeasureResult.viewportStartOffset - ((MeasuredPage) ((PageInfo) CollectionsKt.first(pagerMeasureResult.visiblePagesInfo))).offset >= (-f) || (prefetchHandle = this.currentPrefetchHandle) == null) {
                return;
            }
            prefetchHandle.markAsUrgent();
        }
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final Object scroll(MutatePriority mutatePriority, Function2 function2, ContinuationImpl continuationImpl) {
        return scroll$suspendImpl(this, mutatePriority, function2, continuationImpl);
    }

    public final void snapToItem$foundation_release(float f, boolean z, int i) {
        PagerScrollPosition pagerScrollPosition = this.scrollPosition;
        ((SnapshotMutableIntStateImpl) pagerScrollPosition.currentPage$delegate).setIntValue(i);
        pagerScrollPosition.nearestRangeState.update(i);
        ((SnapshotMutableFloatStateImpl) pagerScrollPosition.currentPageOffsetFraction$delegate).setFloatValue(f);
        pagerScrollPosition.lastKnownCurrentPageKey = null;
        if (!z) {
            this.measurementScopeInvalidator.setValue(Unit.INSTANCE);
        } else {
            Remeasurement remeasurement = (Remeasurement) ((SnapshotMutableStateImpl) this.remeasurement$delegate).getValue();
            if (remeasurement != null) {
                ((LayoutNode) remeasurement).forceRemeasure();
            }
        }
    }
}
