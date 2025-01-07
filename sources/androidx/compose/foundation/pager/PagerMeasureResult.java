package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.ui.layout.MeasureResult;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PagerMeasureResult implements PagerLayoutInfo, MeasureResult {
    public final int afterContentPadding;
    public final int beyondViewportPageCount;
    public final boolean canScrollForward;
    public final CoroutineScope coroutineScope;
    public final MeasuredPage currentPage;
    public final float currentPageOffsetFraction;
    public final List extraPagesAfter;
    public final List extraPagesBefore;
    public final MeasuredPage firstVisiblePage;
    public final int firstVisiblePageScrollOffset;
    public final MeasureResult measureResult;
    public final Orientation orientation;
    public final int pageSize;
    public final int pageSpacing;
    public final boolean remeasureNeeded;
    public final boolean reverseLayout;
    public final SnapPosition snapPosition;
    public final int viewportEndOffset;
    public final int viewportStartOffset;
    public final List visiblePagesInfo;

    public PagerMeasureResult(List list, int i, int i2, int i3, Orientation orientation, int i4, int i5, boolean z, int i6, MeasuredPage measuredPage, MeasuredPage measuredPage2, float f, int i7, boolean z2, SnapPosition snapPosition, MeasureResult measureResult, boolean z3, List list2, List list3, CoroutineScope coroutineScope) {
        this.visiblePagesInfo = list;
        this.pageSize = i;
        this.pageSpacing = i2;
        this.afterContentPadding = i3;
        this.orientation = orientation;
        this.viewportStartOffset = i4;
        this.viewportEndOffset = i5;
        this.reverseLayout = z;
        this.beyondViewportPageCount = i6;
        this.firstVisiblePage = measuredPage;
        this.currentPage = measuredPage2;
        this.currentPageOffsetFraction = f;
        this.firstVisiblePageScrollOffset = i7;
        this.canScrollForward = z2;
        this.snapPosition = snapPosition;
        this.measureResult = measureResult;
        this.remeasureNeeded = z3;
        this.extraPagesBefore = list2;
        this.extraPagesAfter = list3;
        this.coroutineScope = coroutineScope;
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final Map getAlignmentLines() {
        return this.measureResult.getAlignmentLines();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final int getHeight() {
        return this.measureResult.getHeight();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final Function1 getRulers() {
        return this.measureResult.getRulers();
    }

    /* renamed from: getViewportSize-YbymL2g, reason: not valid java name */
    public final long m143getViewportSizeYbymL2g() {
        MeasureResult measureResult = this.measureResult;
        return (measureResult.getWidth() << 32) | (measureResult.getHeight() & 4294967295L);
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final int getWidth() {
        return this.measureResult.getWidth();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final void placeChildren() {
        this.measureResult.placeChildren();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PagerMeasureResult(kotlin.collections.EmptyList r22, int r23, int r24, int r25, androidx.compose.foundation.gestures.Orientation r26, int r27, int r28, int r29, androidx.compose.foundation.gestures.snapping.SnapPosition r30, androidx.compose.ui.layout.MeasureResult r31, kotlinx.coroutines.CoroutineScope r32) {
        /*
            r21 = this;
            kotlin.collections.EmptyList r19 = kotlin.collections.EmptyList.INSTANCE
            r8 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r17 = 0
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r6 = r27
            r7 = r28
            r9 = r29
            r15 = r30
            r16 = r31
            r18 = r19
            r20 = r32
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerMeasureResult.<init>(kotlin.collections.EmptyList, int, int, int, androidx.compose.foundation.gestures.Orientation, int, int, int, androidx.compose.foundation.gestures.snapping.SnapPosition, androidx.compose.ui.layout.MeasureResult, kotlinx.coroutines.CoroutineScope):void");
    }
}
