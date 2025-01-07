package androidx.compose.ui.unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextUnitKt {
    public static final long getSp(double d) {
        return pack((float) d, 4294967296L);
    }

    public static final long pack(float f, long j) {
        long floatToRawIntBits = j | (Float.floatToRawIntBits(f) & 4294967295L);
        TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
        return floatToRawIntBits;
    }

    public static final long getSp(int i) {
        return pack(i, 4294967296L);
    }
}
