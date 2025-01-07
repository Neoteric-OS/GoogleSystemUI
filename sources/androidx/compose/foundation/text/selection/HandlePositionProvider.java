package androidx.compose.foundation.text.selection;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HandlePositionProvider implements PopupPositionProvider {
    public final Alignment handleReferencePoint;
    public final OffsetProvider positionProvider;
    public long prevPosition = 0;

    public HandlePositionProvider(Alignment alignment, OffsetProvider offsetProvider) {
        this.handleReferencePoint = alignment;
        this.positionProvider = offsetProvider;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* renamed from: calculatePosition-llwVHH4 */
    public final long mo42calculatePositionllwVHH4(IntRect intRect, long j, LayoutDirection layoutDirection, long j2) {
        long mo156provideF1C5BW0 = this.positionProvider.mo156provideF1C5BW0();
        if ((9223372034707292159L & mo156provideF1C5BW0) == 9205357640488583168L) {
            mo156provideF1C5BW0 = this.prevPosition;
        }
        this.prevPosition = mo156provideF1C5BW0;
        return IntOffset.m676plusqkQi6aY(IntOffset.m676plusqkQi6aY(intRect.m680getTopLeftnOccac(), IntOffsetKt.m679roundk4lQ0M(mo156provideF1C5BW0)), this.handleReferencePoint.mo274alignKFBX0sM(j2, 0L, layoutDirection));
    }
}
