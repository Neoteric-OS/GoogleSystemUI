package androidx.compose.ui.text.font;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidFont implements Font {
    public final TypefaceLoader typefaceLoader;
    public final FontVariation$Settings variationSettings;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TypefaceLoader {
    }

    public AndroidFont(FontVariation$Settings fontVariation$Settings) {
        this.variationSettings = fontVariation$Settings;
    }
}
