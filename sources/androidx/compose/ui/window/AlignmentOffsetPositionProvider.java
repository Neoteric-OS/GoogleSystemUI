package androidx.compose.ui.window;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AlignmentOffsetPositionProvider implements PopupPositionProvider {
    public final Alignment alignment;
    public final long offset;

    public AlignmentOffsetPositionProvider(Alignment alignment, long j) {
        this.alignment = alignment;
        this.offset = j;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* renamed from: calculatePosition-llwVHH4 */
    public final long mo42calculatePositionllwVHH4(IntRect intRect, long j, LayoutDirection layoutDirection, long j2) {
        long mo274alignKFBX0sM = this.alignment.mo274alignKFBX0sM(0L, (intRect.getWidth() << 32) | (intRect.getHeight() & 4294967295L), layoutDirection);
        long mo274alignKFBX0sM2 = this.alignment.mo274alignKFBX0sM(0L, j2, layoutDirection);
        long j3 = ((-((int) (mo274alignKFBX0sM2 >> 32))) << 32) | ((-((int) (mo274alignKFBX0sM2 & 4294967295L))) & 4294967295L);
        long j4 = this.offset;
        return IntOffset.m676plusqkQi6aY(IntOffset.m676plusqkQi6aY(IntOffset.m676plusqkQi6aY(intRect.m680getTopLeftnOccac(), mo274alignKFBX0sM), j3), ((((int) (j4 >> 32)) * (layoutDirection == LayoutDirection.Ltr ? 1 : -1)) << 32) | (((int) (j4 & 4294967295L)) & 4294967295L));
    }
}
