package com.android.compose.modifiers;

import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.RectangleShapeKt$RectangleShape$1;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FadingBackground extends InspectorValueInfo implements DrawModifier {
    public final Function0 alpha;
    public final SolidColor brush;
    public LayoutDirection lastLayoutDirection;
    public Outline lastOutline;
    public Size lastSize;
    public final Shape shape;

    public FadingBackground(SolidColor solidColor, Shape shape, Function0 function0) {
        this.brush = solidColor;
        this.shape = shape;
        this.alpha = function0;
    }

    @Override // androidx.compose.ui.draw.DrawModifier
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        Outline mo37createOutlinePq9zytI;
        RectangleShapeKt$RectangleShape$1 rectangleShapeKt$RectangleShape$1 = RectangleShapeKt.RectangleShape;
        Function0 function0 = this.alpha;
        Shape shape = this.shape;
        SolidColor solidColor = this.brush;
        if (shape == rectangleShapeKt$RectangleShape$1) {
            DrawScope.m426drawRectAsUm42w$default(layoutNodeDrawScope, solidColor, 0L, 0L, ((Number) function0.invoke()).floatValue(), null, 118);
        } else {
            CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
            long mo432getSizeNHjbRc = canvasDrawScope.mo432getSizeNHjbRc();
            Size size = this.lastSize;
            if (size != null && mo432getSizeNHjbRc == size.packedValue && layoutNodeDrawScope.getLayoutDirection() == this.lastLayoutDirection) {
                mo37createOutlinePq9zytI = this.lastOutline;
                Intrinsics.checkNotNull(mo37createOutlinePq9zytI);
            } else {
                mo37createOutlinePq9zytI = shape.mo37createOutlinePq9zytI(canvasDrawScope.mo432getSizeNHjbRc(), layoutNodeDrawScope.getLayoutDirection(), layoutNodeDrawScope);
            }
            OutlineKt.m386drawOutlinehn5TExg$default(layoutNodeDrawScope, mo37createOutlinePq9zytI, solidColor, ((Number) function0.invoke()).floatValue());
            this.lastOutline = mo37createOutlinePq9zytI;
            this.lastSize = new Size(canvasDrawScope.mo432getSizeNHjbRc());
            this.lastLayoutDirection = layoutNodeDrawScope.getLayoutDirection();
        }
        layoutNodeDrawScope.drawContent();
    }

    public final boolean equals(Object obj) {
        FadingBackground fadingBackground = obj instanceof FadingBackground ? (FadingBackground) obj : null;
        return fadingBackground != null && this.brush.equals(fadingBackground.brush) && Intrinsics.areEqual(this.alpha, fadingBackground.alpha) && this.shape.equals(fadingBackground.shape);
    }

    public final int hashCode() {
        return this.shape.hashCode() + ((this.alpha.hashCode() + (this.brush.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "FadingBackground(brush=" + this.brush + ", alpha = " + this.alpha + ", shape=" + this.shape + ")";
    }
}
