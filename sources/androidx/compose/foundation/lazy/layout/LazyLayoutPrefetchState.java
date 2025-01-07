package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.PrefetchHandleProvider;
import androidx.compose.foundation.lazy.layout.PrefetchHandleProvider.HandleAndRequestImpl;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyLayoutPrefetchState {
    public final Lambda onNestedPrefetch;
    public PrefetchHandleProvider prefetchHandleProvider;
    public final PrefetchMetrics prefetchMetrics = new PrefetchMetrics();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class NestedPrefetchScopeImpl implements NestedPrefetchScope {
        public final List _requests = new ArrayList();

        public NestedPrefetchScopeImpl() {
        }

        @Override // androidx.compose.foundation.lazy.layout.NestedPrefetchScope
        public final void schedulePrefetch(int i) {
            long j = LazyLayoutPrefetchStateKt.ZeroConstraints;
            LazyLayoutPrefetchState lazyLayoutPrefetchState = LazyLayoutPrefetchState.this;
            PrefetchHandleProvider prefetchHandleProvider = lazyLayoutPrefetchState.prefetchHandleProvider;
            if (prefetchHandleProvider == null) {
                return;
            }
            this._requests.add(prefetchHandleProvider.new HandleAndRequestImpl(i, j, lazyLayoutPrefetchState.prefetchMetrics));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface PrefetchHandle {
        void cancel();

        void markAsUrgent();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LazyLayoutPrefetchState(Function1 function1) {
        this.onNestedPrefetch = (Lambda) function1;
    }

    /* renamed from: schedulePrefetch-0kLqBqw, reason: not valid java name */
    public final PrefetchHandle m137schedulePrefetch0kLqBqw(long j, int i) {
        PrefetchHandleProvider prefetchHandleProvider = this.prefetchHandleProvider;
        if (prefetchHandleProvider == null) {
            return DummyHandle.INSTANCE;
        }
        PrefetchHandleProvider.HandleAndRequestImpl handleAndRequestImpl = prefetchHandleProvider.new HandleAndRequestImpl(i, j, this.prefetchMetrics);
        prefetchHandleProvider.executor.schedulePrefetch(handleAndRequestImpl);
        return handleAndRequestImpl;
    }
}
