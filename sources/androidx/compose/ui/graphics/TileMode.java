package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TileMode {
    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m396equalsimpl0(int i) {
        return i == 0;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m397toStringimpl() {
        return m396equalsimpl0(0) ? "Clamp" : m396equalsimpl0(1) ? "Repeated" : m396equalsimpl0(2) ? "Mirror" : m396equalsimpl0(3) ? "Decal" : "Unknown";
    }
}
