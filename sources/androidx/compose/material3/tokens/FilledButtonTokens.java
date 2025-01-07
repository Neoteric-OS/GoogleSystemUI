package androidx.compose.material3.tokens;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FilledButtonTokens {
    public static final float ContainerElevation;
    public static final ColorSchemeKeyTokens DisabledContainerColor;
    public static final float DisabledContainerElevation;
    public static final float DisabledContainerOpacity;
    public static final ColorSchemeKeyTokens DisabledLabelTextColor;
    public static final float DisabledLabelTextOpacity;
    public static final float FocusedContainerElevation;
    public static final float HoveredContainerElevation;
    public static final ColorSchemeKeyTokens LabelTextColor;
    public static final float PressedContainerElevation;

    static {
        float f = ElevationTokens.Level0;
        ContainerElevation = f;
        DisabledContainerColor = ColorSchemeKeyTokens.OnSurface;
        DisabledContainerElevation = f;
        DisabledContainerOpacity = 0.1f;
        DisabledLabelTextColor = ColorSchemeKeyTokens.OnSurfaceVariant;
        DisabledLabelTextOpacity = 0.38f;
        FocusedContainerElevation = f;
        HoveredContainerElevation = ElevationTokens.Level1;
        LabelTextColor = ColorSchemeKeyTokens.OnPrimary;
        PressedContainerElevation = f;
    }
}
