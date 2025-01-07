package androidx.compose.foundation.gestures.snapping;

import androidx.compose.foundation.pager.PagerSnapDistanceMaxPages;
import androidx.compose.foundation.pager.PagerState;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$1 {
    public final /* synthetic */ Function3 $calculateFinalSnappingBound;
    public final /* synthetic */ PagerSnapDistanceMaxPages $pagerSnapDistance;
    public final /* synthetic */ PagerState $pagerState;

    public PagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$1(PagerState pagerState, Function3 function3, PagerSnapDistanceMaxPages pagerSnapDistanceMaxPages) {
        this.$pagerState = pagerState;
        this.$calculateFinalSnappingBound = function3;
    }
}
