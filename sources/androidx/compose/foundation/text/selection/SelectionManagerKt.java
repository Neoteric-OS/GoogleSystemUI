package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SelectionManagerKt {
    public static final Rect visibleBounds(LayoutCoordinates layoutCoordinates) {
        long mo489windowToLocalMKHz9U = layoutCoordinates.mo489windowToLocalMKHz9U(LayoutCoordinatesKt.boundsInWindow(layoutCoordinates).m322getTopLeftF1C5BW0());
        long mo489windowToLocalMKHz9U2 = layoutCoordinates.mo489windowToLocalMKHz9U((Float.floatToRawIntBits(r0.right) << 32) | (Float.floatToRawIntBits(r0.bottom) & 4294967295L));
        return new Rect(Float.intBitsToFloat((int) (mo489windowToLocalMKHz9U >> 32)), Float.intBitsToFloat((int) (mo489windowToLocalMKHz9U & 4294967295L)), Float.intBitsToFloat((int) (mo489windowToLocalMKHz9U2 >> 32)), Float.intBitsToFloat((int) (mo489windowToLocalMKHz9U2 & 4294967295L)));
    }
}
