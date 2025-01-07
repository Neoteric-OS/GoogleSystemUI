package androidx.compose.material3.tokens;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShapeKeyTokens {
    public static final /* synthetic */ ShapeKeyTokens[] $VALUES;
    public static final ShapeKeyTokens CornerExtraLargeTop;
    public static final ShapeKeyTokens CornerExtraSmall = null;
    public static final ShapeKeyTokens CornerExtraSmallTop;
    public static final ShapeKeyTokens CornerFull;
    public static final ShapeKeyTokens CornerMedium;
    public static final ShapeKeyTokens CornerNone;

    /* JADX INFO: Fake field, exist only in values array */
    ShapeKeyTokens EF0;

    static {
        ShapeKeyTokens shapeKeyTokens = new ShapeKeyTokens("CornerExtraLarge", 0);
        ShapeKeyTokens shapeKeyTokens2 = new ShapeKeyTokens("CornerExtraLargeTop", 1);
        CornerExtraLargeTop = shapeKeyTokens2;
        ShapeKeyTokens shapeKeyTokens3 = new ShapeKeyTokens("CornerExtraSmall", 2);
        ShapeKeyTokens shapeKeyTokens4 = new ShapeKeyTokens("CornerExtraSmallTop", 3);
        CornerExtraSmallTop = shapeKeyTokens4;
        ShapeKeyTokens shapeKeyTokens5 = new ShapeKeyTokens("CornerFull", 4);
        CornerFull = shapeKeyTokens5;
        ShapeKeyTokens shapeKeyTokens6 = new ShapeKeyTokens("CornerLarge", 5);
        ShapeKeyTokens shapeKeyTokens7 = new ShapeKeyTokens("CornerLargeEnd", 6);
        ShapeKeyTokens shapeKeyTokens8 = new ShapeKeyTokens("CornerLargeTop", 7);
        ShapeKeyTokens shapeKeyTokens9 = new ShapeKeyTokens("CornerMedium", 8);
        CornerMedium = shapeKeyTokens9;
        ShapeKeyTokens shapeKeyTokens10 = new ShapeKeyTokens("CornerNone", 9);
        CornerNone = shapeKeyTokens10;
        $VALUES = new ShapeKeyTokens[]{shapeKeyTokens, shapeKeyTokens2, shapeKeyTokens3, shapeKeyTokens4, shapeKeyTokens5, shapeKeyTokens6, shapeKeyTokens7, shapeKeyTokens8, shapeKeyTokens9, shapeKeyTokens10, new ShapeKeyTokens("CornerSmall", 10)};
    }

    public static ShapeKeyTokens valueOf(String str) {
        return (ShapeKeyTokens) Enum.valueOf(ShapeKeyTokens.class, str);
    }

    public static ShapeKeyTokens[] values() {
        return (ShapeKeyTokens[]) $VALUES.clone();
    }
}
