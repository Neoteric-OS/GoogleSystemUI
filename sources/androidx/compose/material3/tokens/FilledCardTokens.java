package androidx.compose.material3.tokens;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FilledCardTokens {
    public static final float ContainerElevation;
    public static final ShapeKeyTokens ContainerShape;
    public static final ColorSchemeKeyTokens DisabledContainerColor;
    public static final float DisabledContainerElevation;
    public static final float DisabledContainerOpacity;
    public static final float DraggedContainerElevation;
    public static final float FocusContainerElevation;
    public static final float HoverContainerElevation;
    public static final float PressedContainerElevation;

    static {
        float f = ElevationTokens.Level0;
        ContainerElevation = f;
        ContainerShape = ShapeKeyTokens.CornerMedium;
        DisabledContainerColor = ColorSchemeKeyTokens.SurfaceVariant;
        DisabledContainerElevation = f;
        DisabledContainerOpacity = 0.38f;
        DraggedContainerElevation = ElevationTokens.Level3;
        FocusContainerElevation = f;
        HoverContainerElevation = ElevationTokens.Level1;
        PressedContainerElevation = f;
    }
}
