package androidx.compose.ui.node;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
abstract class Snake {
    /* renamed from: getDiagonalSize-impl, reason: not valid java name */
    public static final int m550getDiagonalSizeimpl(int[] iArr) {
        return Math.min(iArr[2] - iArr[0], iArr[3] - iArr[1]);
    }
}
