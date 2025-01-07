package androidx.compose.ui.graphics;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class GraphicsLayerModifierKt {
    public static final Modifier graphicsLayer(Modifier modifier, Function1 function1) {
        return modifier.then(new BlockGraphicsLayerElement(function1));
    }

    /* renamed from: graphicsLayer-Ap8cVGQ$default, reason: not valid java name */
    public static Modifier m376graphicsLayerAp8cVGQ$default(Modifier modifier, float f, float f2, float f3, float f4, float f5, float f6, float f7, Shape shape, boolean z, AndroidRenderEffect androidRenderEffect, int i) {
        float f8 = (i & 1) != 0 ? 1.0f : f;
        float f9 = (i & 2) != 0 ? 1.0f : f2;
        float f10 = (i & 4) != 0 ? 1.0f : f3;
        float f11 = (i & 8) != 0 ? 0.0f : f4;
        float f12 = (i & 16) != 0 ? 0.0f : f5;
        float f13 = (i & 32) != 0 ? 0.0f : f6;
        float f14 = (i & 256) != 0 ? 0.0f : f7;
        long j = TransformOrigin.Center;
        Shape shape2 = (i & 2048) != 0 ? RectangleShapeKt.RectangleShape : shape;
        boolean z2 = (i & 4096) != 0 ? false : z;
        AndroidRenderEffect androidRenderEffect2 = (i & 8192) != 0 ? null : androidRenderEffect;
        long j2 = GraphicsLayerScopeKt.DefaultShadowColor;
        return modifier.then(new GraphicsLayerElement(f8, f9, f10, f11, f12, f13, f14, j, shape2, z2, androidRenderEffect2, j2, j2, (i & 65536) != 0 ? 0 : 1));
    }
}
