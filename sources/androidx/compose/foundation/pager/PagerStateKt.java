package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.layout.MeasureResult;
import java.util.Map;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PagerStateKt {
    public static final float DefaultPositionThreshold = 56;
    public static final PagerMeasureResult EmptyLayoutInfo = new PagerMeasureResult(EmptyList.INSTANCE, 0, 0, 0, Orientation.Horizontal, 0, 0, 0, SnapPosition.Start.INSTANCE, new MeasureResult() { // from class: androidx.compose.foundation.pager.PagerStateKt$EmptyLayoutInfo$1
        public final Map alignmentLines = MapsKt.emptyMap();

        @Override // androidx.compose.ui.layout.MeasureResult
        public final Map getAlignmentLines() {
            return this.alignmentLines;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public final int getHeight() {
            return 0;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public final int getWidth() {
            return 0;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public final void placeChildren() {
        }
    }, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE));
    public static final PagerStateKt$UnitDensity$1 UnitDensity = new PagerStateKt$UnitDensity$1();

    public static final long calculateNewMaxScrollOffset(PagerLayoutInfo pagerLayoutInfo, int i) {
        PagerMeasureResult pagerMeasureResult = (PagerMeasureResult) pagerLayoutInfo;
        long j = (i * (pagerMeasureResult.pageSpacing + pagerMeasureResult.pageSize)) + (-pagerMeasureResult.viewportStartOffset) + pagerMeasureResult.afterContentPadding;
        Orientation orientation = Orientation.Horizontal;
        Orientation orientation2 = pagerMeasureResult.orientation;
        long m143getViewportSizeYbymL2g = pagerMeasureResult.m143getViewportSizeYbymL2g();
        int i2 = (int) (orientation2 == orientation ? m143getViewportSizeYbymL2g >> 32 : m143getViewportSizeYbymL2g & 4294967295L);
        pagerMeasureResult.snapPosition.getClass();
        long coerceIn = j - (i2 - RangesKt.coerceIn(0, 0, i2));
        if (coerceIn < 0) {
            return 0L;
        }
        return coerceIn;
    }

    public static final PagerState rememberPagerState(final Function0 function0, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Object[] objArr = new Object[0];
        SaverKt$Saver$1 saverKt$Saver$1 = DefaultPagerState.Saver;
        boolean changed = ((ComposerImpl) composer).changed(0.0f) | ((ComposerImpl) composer).changed(function0);
        ComposerImpl composerImpl = (ComposerImpl) composer;
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function0() { // from class: androidx.compose.foundation.pager.PagerStateKt$rememberPagerState$1$1
                final /* synthetic */ int $initialPage = 0;
                final /* synthetic */ float $initialPageOffsetFraction = 0.0f;

                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new DefaultPagerState(this.$initialPage, this.$initialPageOffsetFraction, Function0.this);
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        DefaultPagerState defaultPagerState = (DefaultPagerState) RememberSaveableKt.rememberSaveable(objArr, saverKt$Saver$1, (Function0) rememberedValue, composerImpl, 0, 4);
        ((SnapshotMutableStateImpl) defaultPagerState.pageCountState).setValue(function0);
        return defaultPagerState;
    }
}
