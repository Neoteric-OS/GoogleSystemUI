package androidx.compose.foundation.lazy.layout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyLayoutScrollScopeKt {
    public static final float TargetDistance = 2500;
    public static final float BoundDistance = 1500;
    public static final float MinimumDistance = 50;

    public static final boolean access$animateScrollToItem$lambda$6$isOvershot(boolean z, LazyLayoutScrollScope lazyLayoutScrollScope, int i, int i2) {
        if (z) {
            if (lazyLayoutScrollScope.getFirstVisibleItemIndex() <= i && (lazyLayoutScrollScope.getFirstVisibleItemIndex() != i || lazyLayoutScrollScope.getFirstVisibleItemScrollOffset() <= i2)) {
                return false;
            }
        } else if (lazyLayoutScrollScope.getFirstVisibleItemIndex() >= i && (lazyLayoutScrollScope.getFirstVisibleItemIndex() != i || lazyLayoutScrollScope.getFirstVisibleItemScrollOffset() >= i2)) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e6 A[Catch: ItemFoundInScroll -> 0x01be, TRY_LEAVE, TryCatch #5 {ItemFoundInScroll -> 0x01be, blocks: (B:25:0x00e2, B:27:0x00e6, B:32:0x00f1, B:39:0x0111), top: B:24:0x00e2 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x019e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x022f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0086  */
    /* JADX WARN: Type inference failed for: r10v10, types: [androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x019f -> B:21:0x01a2). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animateScrollToItem(androidx.compose.foundation.lazy.grid.LazyGridScrollScopeKt$LazyLayoutScrollScope$1 r34, int r35, int r36, int r37, androidx.compose.ui.unit.Density r38, androidx.compose.foundation.gestures.ScrollScope r39, kotlin.coroutines.jvm.internal.ContinuationImpl r40) {
        /*
            Method dump skipped, instructions count: 569
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt.animateScrollToItem(androidx.compose.foundation.lazy.grid.LazyGridScrollScopeKt$LazyLayoutScrollScope$1, int, int, int, androidx.compose.ui.unit.Density, androidx.compose.foundation.gestures.ScrollScope, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static final boolean isItemVisible(LazyLayoutScrollScope lazyLayoutScrollScope, int i) {
        return i <= lazyLayoutScrollScope.getLastVisibleItemIndex() && lazyLayoutScrollScope.getFirstVisibleItemIndex() <= i;
    }
}
