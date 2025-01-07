package com.android.systemui.keyboard.shortcut.ui.composable;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderModifierNodeElement;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.Shape;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SurfacesKt {
    /* renamed from: access$surface-XO-JAsU, reason: not valid java name */
    public static final Modifier m814access$surfaceXOJAsU(Modifier modifier, Shape shape, long j, BorderStroke borderStroke, float f) {
        Modifier then = f > 0.0f ? modifier.then(GraphicsLayerModifierKt.m376graphicsLayerAp8cVGQ$default(Modifier.Companion.$$INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, shape, false, null, 124895)) : modifier;
        if (borderStroke != null) {
            then = then.then(new BorderModifierNodeElement(borderStroke.width, borderStroke.brush, shape));
        }
        return BackgroundKt.m25backgroundbw27NRU(then, j, shape);
    }

    /* renamed from: access$surfaceColorAtElevation-CLU3JFs, reason: not valid java name */
    public static final long m815access$surfaceColorAtElevationCLU3JFs(long j, float f, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(1038469993);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ColorScheme colorScheme = MaterialTheme.getColorScheme(composerImpl);
        composerImpl.startReplaceGroup(-2055033482);
        boolean booleanValue = ((Boolean) composerImpl.consume(ColorSchemeKt.LocalTonalElevationEnabled)).booleanValue();
        if (Color.m363equalsimpl0(j, colorScheme.surface) && booleanValue) {
            j = ColorSchemeKt.m205surfaceColorAtElevation3ABfNKs(colorScheme, f);
        }
        composerImpl.end(false);
        composerImpl.end(false);
        return j;
    }
}
