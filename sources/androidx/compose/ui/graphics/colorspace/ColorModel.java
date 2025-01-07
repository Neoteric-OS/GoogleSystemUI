package androidx.compose.ui.graphics.colorspace;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ColorModel {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long Cmyk;
    public static final long Lab;
    public static final long Rgb;
    public static final long Xyz;

    static {
        long j = 3;
        long j2 = j << 32;
        Rgb = (0 & 4294967295L) | j2;
        Xyz = (1 & 4294967295L) | j2;
        Lab = j2 | (2 & 4294967295L);
        Cmyk = (j & 4294967295L) | (4 << 32);
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m402equalsimpl0(long j, long j2) {
        return j == j2;
    }
}
