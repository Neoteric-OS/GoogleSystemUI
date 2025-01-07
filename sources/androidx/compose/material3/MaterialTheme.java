package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MaterialTheme {
    public static ColorScheme getColorScheme(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return (ColorScheme) ((ComposerImpl) composer).consume(ColorSchemeKt.LocalColorScheme);
    }

    public static MotionScheme getMotionScheme(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return (MotionScheme) ((ComposerImpl) composer).consume(MotionSchemeKt.LocalMotionScheme);
    }

    public static Shapes getShapes(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return (Shapes) ((ComposerImpl) composer).consume(ShapesKt.LocalShapes);
    }

    public static Typography getTypography(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return (Typography) ((ComposerImpl) composer).consume(TypographyKt.LocalTypography);
    }
}
