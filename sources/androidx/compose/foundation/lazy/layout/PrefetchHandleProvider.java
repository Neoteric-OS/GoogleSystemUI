package androidx.compose.foundation.lazy.layout;

import android.os.Trace;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectLongMap;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.AndroidPrefetchScheduler;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.NestedPrefetchScopeImpl;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNode$Companion$TraverseDescendantsAction;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrefetchHandleProvider {
    public final PrefetchScheduler executor;
    public final LazyLayoutItemContentFactory itemContentFactory;
    public final SubcomposeLayoutState subcomposeLayoutState;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class HandleAndRequestImpl implements LazyLayoutPrefetchState.PrefetchHandle, PrefetchRequest {
        public final long constraints;
        public boolean hasResolvedNestedPrefetches;
        public final int index;
        public boolean isCanceled;
        public boolean isMeasured;
        public boolean isUrgent;
        public NestedPrefetchController nestedPrefetchController;
        public SubcomposeLayoutState.PrecomposedSlotHandle precomposeHandle;
        public final PrefetchMetrics prefetchMetrics;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        final class NestedPrefetchController {
            public int requestIndex;
            public final List[] requestsByState;
            public int stateIndex;
            public final List states;

            public NestedPrefetchController(List list) {
                this.states = list;
                this.requestsByState = new List[list.size()];
                if (list.isEmpty()) {
                    InlineClassHelperKt.throwIllegalArgumentException("NestedPrefetchController shouldn't be created with no states");
                }
            }
        }

        public HandleAndRequestImpl(int i, long j, PrefetchMetrics prefetchMetrics) {
            this.index = i;
            this.constraints = j;
            this.prefetchMetrics = prefetchMetrics;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public final void cancel() {
            if (this.isCanceled) {
                return;
            }
            this.isCanceled = true;
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
            if (precomposedSlotHandle != null) {
                precomposedSlotHandle.dispose();
            }
            this.precomposeHandle = null;
        }

        /* JADX WARN: Type inference failed for: r11v6, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
        public final boolean execute(AndroidPrefetchScheduler.PrefetchRequestScopeImpl prefetchRequestScopeImpl) {
            List list;
            if (!isValid()) {
                return false;
            }
            Object contentType = ((LazyLayoutItemProvider) ((LazyLayoutKt$LazyLayout$1$itemContentFactory$1$1) PrefetchHandleProvider.this.itemContentFactory.itemProvider).invoke()).getContentType(this.index);
            boolean z = this.precomposeHandle != null;
            PrefetchMetrics prefetchMetrics = this.prefetchMetrics;
            if (!z) {
                long j = (contentType == null || prefetchMetrics.averageCompositionTimeNanosByContentType.findKeyIndex(contentType) < 0) ? prefetchMetrics.averageCompositionTimeNanos : prefetchMetrics.averageCompositionTimeNanosByContentType.get(contentType);
                long availableTimeNanos = prefetchRequestScopeImpl.availableTimeNanos();
                if ((!this.isUrgent || availableTimeNanos <= 0) && j >= availableTimeNanos) {
                    return true;
                }
                long nanoTime = System.nanoTime();
                Trace.beginSection("compose:lazy:prefetch:compose");
                try {
                    performComposition();
                    Trace.endSection();
                    long nanoTime2 = System.nanoTime() - nanoTime;
                    if (contentType != null) {
                        MutableObjectLongMap mutableObjectLongMap = prefetchMetrics.averageCompositionTimeNanosByContentType;
                        int findKeyIndex = mutableObjectLongMap.findKeyIndex(contentType);
                        prefetchMetrics.averageCompositionTimeNanosByContentType.set(PrefetchMetrics.access$calculateAverageTime(prefetchMetrics, nanoTime2, findKeyIndex >= 0 ? mutableObjectLongMap.values[findKeyIndex] : 0L), contentType);
                    }
                    prefetchMetrics.averageCompositionTimeNanos = PrefetchMetrics.access$calculateAverageTime(prefetchMetrics, nanoTime2, prefetchMetrics.averageCompositionTimeNanos);
                } finally {
                }
            }
            if (!this.isUrgent) {
                if (!this.hasResolvedNestedPrefetches) {
                    if (prefetchRequestScopeImpl.availableTimeNanos() <= 0) {
                        return true;
                    }
                    Trace.beginSection("compose:lazy:prefetch:resolve-nested");
                    try {
                        SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
                        if (precomposedSlotHandle == null) {
                            InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("Should precompose before resolving nested prefetch states");
                            throw null;
                        }
                        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                        precomposedSlotHandle.traverseDescendants(new Function1() { // from class: androidx.compose.foundation.lazy.layout.PrefetchHandleProvider$HandleAndRequestImpl$resolveNestedPrefetchStates$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LazyLayoutPrefetchState lazyLayoutPrefetchState = ((TraversablePrefetchStateNode) ((TraversableNode) obj)).prefetchState;
                                Ref$ObjectRef ref$ObjectRef2 = Ref$ObjectRef.this;
                                List list2 = (List) ref$ObjectRef2.element;
                                if (list2 != null) {
                                    list2.add(lazyLayoutPrefetchState);
                                } else {
                                    list2 = CollectionsKt__CollectionsKt.mutableListOf(lazyLayoutPrefetchState);
                                }
                                ref$ObjectRef2.element = list2;
                                return TraversableNode$Companion$TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
                            }
                        });
                        List list2 = (List) ref$ObjectRef.element;
                        this.nestedPrefetchController = list2 != null ? new NestedPrefetchController(list2) : null;
                        this.hasResolvedNestedPrefetches = true;
                    } finally {
                    }
                }
                NestedPrefetchController nestedPrefetchController = this.nestedPrefetchController;
                if (nestedPrefetchController != null && nestedPrefetchController.stateIndex < nestedPrefetchController.states.size()) {
                    if (HandleAndRequestImpl.this.isCanceled) {
                        InlineClassHelperKt.throwIllegalStateException("Should not execute nested prefetch on canceled request");
                    }
                    Trace.beginSection("compose:lazy:prefetch:nested");
                    while (nestedPrefetchController.stateIndex < nestedPrefetchController.states.size()) {
                        try {
                            if (nestedPrefetchController.requestsByState[nestedPrefetchController.stateIndex] == null) {
                                if (prefetchRequestScopeImpl.availableTimeNanos() <= 0) {
                                    return true;
                                }
                                List[] listArr = nestedPrefetchController.requestsByState;
                                int i = nestedPrefetchController.stateIndex;
                                LazyLayoutPrefetchState lazyLayoutPrefetchState = (LazyLayoutPrefetchState) nestedPrefetchController.states.get(i);
                                ?? r11 = lazyLayoutPrefetchState.onNestedPrefetch;
                                if (r11 == 0) {
                                    list = EmptyList.INSTANCE;
                                } else {
                                    LazyLayoutPrefetchState.NestedPrefetchScopeImpl nestedPrefetchScopeImpl = lazyLayoutPrefetchState.new NestedPrefetchScopeImpl();
                                    r11.invoke(nestedPrefetchScopeImpl);
                                    list = nestedPrefetchScopeImpl._requests;
                                }
                                listArr[i] = list;
                            }
                            List list3 = nestedPrefetchController.requestsByState[nestedPrefetchController.stateIndex];
                            Intrinsics.checkNotNull(list3);
                            while (nestedPrefetchController.requestIndex < list3.size()) {
                                if (((HandleAndRequestImpl) ((PrefetchRequest) list3.get(nestedPrefetchController.requestIndex))).execute(prefetchRequestScopeImpl)) {
                                    return true;
                                }
                                nestedPrefetchController.requestIndex++;
                            }
                            nestedPrefetchController.requestIndex = 0;
                            nestedPrefetchController.stateIndex++;
                        } finally {
                        }
                    }
                }
            }
            if (!this.isMeasured) {
                long j2 = this.constraints;
                int i2 = (int) (3 & j2);
                int i3 = (((i2 & 2) >> 1) * 3) + ((i2 & 1) << 1);
                int i4 = (((int) (j2 >> 33)) & ((1 << (i3 + 13)) - 1)) - 1;
                if (!(((((1 << (18 - i3)) - 1) & ((int) (j2 >> (i3 + 46)))) - 1 == 0) | (i4 == 0))) {
                    long j3 = (contentType == null || prefetchMetrics.averageMeasureTimeNanosByContentType.findKeyIndex(contentType) < 0) ? prefetchMetrics.averageMeasureTimeNanos : prefetchMetrics.averageMeasureTimeNanosByContentType.get(contentType);
                    long availableTimeNanos2 = prefetchRequestScopeImpl.availableTimeNanos();
                    if ((!this.isUrgent || availableTimeNanos2 <= 0) && j3 >= availableTimeNanos2) {
                        return true;
                    }
                    long nanoTime3 = System.nanoTime();
                    Trace.beginSection("compose:lazy:prefetch:measure");
                    try {
                        if (this.isCanceled) {
                            InlineClassHelperKt.throwIllegalArgumentException("Callers should check whether the request is still valid before calling performMeasure()");
                        }
                        if (this.isMeasured) {
                            InlineClassHelperKt.throwIllegalArgumentException("Request was already measured!");
                        }
                        this.isMeasured = true;
                        SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle2 = this.precomposeHandle;
                        if (precomposedSlotHandle2 == null) {
                            InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("performComposition() must be called before performMeasure()");
                            throw null;
                        }
                        int placeablesCount = precomposedSlotHandle2.getPlaceablesCount();
                        for (int i5 = 0; i5 < placeablesCount; i5++) {
                            precomposedSlotHandle2.mo490premeasure0kLqBqw(j2, i5);
                        }
                        Trace.endSection();
                        long nanoTime4 = System.nanoTime() - nanoTime3;
                        if (contentType != null) {
                            MutableObjectLongMap mutableObjectLongMap2 = prefetchMetrics.averageMeasureTimeNanosByContentType;
                            int findKeyIndex2 = mutableObjectLongMap2.findKeyIndex(contentType);
                            prefetchMetrics.averageMeasureTimeNanosByContentType.set(PrefetchMetrics.access$calculateAverageTime(prefetchMetrics, nanoTime4, findKeyIndex2 >= 0 ? mutableObjectLongMap2.values[findKeyIndex2] : 0L), contentType);
                        }
                        prefetchMetrics.averageMeasureTimeNanos = PrefetchMetrics.access$calculateAverageTime(prefetchMetrics, nanoTime4, prefetchMetrics.averageMeasureTimeNanos);
                    } finally {
                    }
                }
            }
            return false;
        }

        public final boolean isValid() {
            if (!this.isCanceled) {
                int itemCount = ((LazyLayoutItemProvider) ((LazyLayoutKt$LazyLayout$1$itemContentFactory$1$1) PrefetchHandleProvider.this.itemContentFactory.itemProvider).invoke()).getItemCount();
                int i = this.index;
                if (i >= 0 && i < itemCount) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public final void markAsUrgent() {
            this.isUrgent = true;
        }

        public final void performComposition() {
            if (!isValid()) {
                InlineClassHelperKt.throwIllegalArgumentException("Callers should check whether the request is still valid before calling performComposition()");
            }
            if (this.precomposeHandle != null) {
                InlineClassHelperKt.throwIllegalArgumentException("Request was already composed!");
            }
            PrefetchHandleProvider prefetchHandleProvider = PrefetchHandleProvider.this;
            LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) ((LazyLayoutKt$LazyLayout$1$itemContentFactory$1$1) prefetchHandleProvider.itemContentFactory.itemProvider).invoke();
            int i = this.index;
            Object key = lazyLayoutItemProvider.getKey(i);
            this.precomposeHandle = prefetchHandleProvider.subcomposeLayoutState.getState().precompose(key, prefetchHandleProvider.itemContentFactory.getContent(i, key, lazyLayoutItemProvider.getContentType(i)));
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("HandleAndRequestImpl { index = ");
            sb.append(this.index);
            sb.append(", constraints = ");
            sb.append((Object) Constraints.m658toStringimpl(this.constraints));
            sb.append(", isComposed = ");
            sb.append(this.precomposeHandle != null);
            sb.append(", isMeasured = ");
            sb.append(this.isMeasured);
            sb.append(", isCanceled = ");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isCanceled, " }");
        }
    }

    public PrefetchHandleProvider(LazyLayoutItemContentFactory lazyLayoutItemContentFactory, SubcomposeLayoutState subcomposeLayoutState, PrefetchScheduler prefetchScheduler) {
        this.itemContentFactory = lazyLayoutItemContentFactory;
        this.subcomposeLayoutState = subcomposeLayoutState;
        this.executor = prefetchScheduler;
    }
}
