package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RadialGradient;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LayoutNodeDrawScope implements DrawScope, ContentDrawScope {
    public final CanvasDrawScope canvasDrawScope = new CanvasDrawScope();
    public DrawModifierNode drawNode;

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-V9BoPsw */
    public final void mo408drawCircleV9BoPsw(RadialGradient radialGradient, float f, long j) {
        this.canvasDrawScope.mo408drawCircleV9BoPsw(radialGradient, f, j);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-VaOC9Bg */
    public final void mo409drawCircleVaOC9Bg(long j, float f, long j2, float f2) {
        this.canvasDrawScope.mo409drawCircleVaOC9Bg(j, f, j2, f2);
    }

    public final void drawContent() {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        Canvas canvas = canvasDrawScope.drawContext.getCanvas();
        DelegatableNode delegatableNode = this.drawNode;
        Intrinsics.checkNotNull(delegatableNode);
        Modifier.Node node = (Modifier.Node) delegatableNode;
        Modifier.Node node2 = node.node.child;
        if (node2 != null && (node2.aggregateChildKindSet & 4) != 0) {
            while (node2 != null) {
                int i = node2.kindSet;
                if ((i & 2) != 0) {
                    break;
                } else if ((i & 4) != 0) {
                    break;
                } else {
                    node2 = node2.child;
                }
            }
        }
        node2 = null;
        if (node2 == null) {
            NodeCoordinator m503requireCoordinator64DMado = DelegatableNodeKt.m503requireCoordinator64DMado(delegatableNode, 4);
            if (m503requireCoordinator64DMado.getTail() == node.node) {
                m503requireCoordinator64DMado = m503requireCoordinator64DMado.wrapped;
                Intrinsics.checkNotNull(m503requireCoordinator64DMado);
            }
            m503requireCoordinator64DMado.performDraw(canvas, canvasDrawScope.drawContext.graphicsLayer);
            return;
        }
        MutableVector mutableVector = null;
        while (node2 != null) {
            if (node2 instanceof DrawModifierNode) {
                DrawModifierNode drawModifierNode = (DrawModifierNode) node2;
                GraphicsLayer graphicsLayer = canvasDrawScope.drawContext.graphicsLayer;
                NodeCoordinator m503requireCoordinator64DMado2 = DelegatableNodeKt.m503requireCoordinator64DMado(drawModifierNode, 4);
                long m685toSizeozmzZPI = IntSizeKt.m685toSizeozmzZPI(m503requireCoordinator64DMado2.measuredSize);
                LayoutNode layoutNode = m503requireCoordinator64DMado2.layoutNode;
                layoutNode.getClass();
                ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).sharedDrawScope.m510drawDirecteZhPAX0$ui_release(canvas, m685toSizeozmzZPI, m503requireCoordinator64DMado2, drawModifierNode, graphicsLayer);
            } else if ((node2.kindSet & 4) != 0 && (node2 instanceof DelegatingNode)) {
                int i2 = 0;
                for (Modifier.Node node3 = ((DelegatingNode) node2).delegate; node3 != null; node3 = node3.child) {
                    if ((node3.kindSet & 4) != 0) {
                        i2++;
                        if (i2 == 1) {
                            node2 = node3;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16]);
                            }
                            if (node2 != null) {
                                mutableVector.add(node2);
                                node2 = null;
                            }
                            mutableVector.add(node3);
                        }
                    }
                }
                if (i2 == 1) {
                }
            }
            node2 = DelegatableNodeKt.access$pop(mutableVector);
        }
    }

    /* renamed from: drawDirect-eZhPAX0$ui_release, reason: not valid java name */
    public final void m510drawDirecteZhPAX0$ui_release(Canvas canvas, long j, NodeCoordinator nodeCoordinator, DrawModifierNode drawModifierNode, GraphicsLayer graphicsLayer) {
        DrawModifierNode drawModifierNode2 = this.drawNode;
        this.drawNode = drawModifierNode;
        LayoutDirection layoutDirection = nodeCoordinator.layoutNode.layoutDirection;
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope$drawContext$1.this$0.drawParams;
        Density density = drawParams.density;
        LayoutDirection layoutDirection2 = drawParams.layoutDirection;
        Canvas canvas2 = canvasDrawScope$drawContext$1.getCanvas();
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$12 = canvasDrawScope.drawContext;
        long m418getSizeNHjbRc = canvasDrawScope$drawContext$12.m418getSizeNHjbRc();
        GraphicsLayer graphicsLayer2 = canvasDrawScope$drawContext$12.graphicsLayer;
        canvasDrawScope$drawContext$12.setDensity(nodeCoordinator);
        canvasDrawScope$drawContext$12.setLayoutDirection(layoutDirection);
        canvasDrawScope$drawContext$12.setCanvas(canvas);
        canvasDrawScope$drawContext$12.m419setSizeuvyYCjk(j);
        canvasDrawScope$drawContext$12.graphicsLayer = graphicsLayer;
        canvas.save();
        try {
            drawModifierNode.draw(this);
            canvas.restore();
            canvasDrawScope$drawContext$12.setDensity(density);
            canvasDrawScope$drawContext$12.setLayoutDirection(layoutDirection2);
            canvasDrawScope$drawContext$12.setCanvas(canvas2);
            canvasDrawScope$drawContext$12.m419setSizeuvyYCjk(m418getSizeNHjbRc);
            canvasDrawScope$drawContext$12.graphicsLayer = graphicsLayer2;
            this.drawNode = drawModifierNode2;
        } catch (Throwable th) {
            canvas.restore();
            canvasDrawScope$drawContext$12.setDensity(density);
            canvasDrawScope$drawContext$12.setLayoutDirection(layoutDirection2);
            canvasDrawScope$drawContext$12.setCanvas(canvas2);
            canvasDrawScope$drawContext$12.m419setSizeuvyYCjk(m418getSizeNHjbRc);
            canvasDrawScope$drawContext$12.graphicsLayer = graphicsLayer2;
            throw th;
        }
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-AZ2fEMs */
    public final void mo410drawImageAZ2fEMs(ImageBitmap imageBitmap, long j, long j2, long j3, float f, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo410drawImageAZ2fEMs(imageBitmap, j, j2, j3, f, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawLine-NGM6Ib0 */
    public final void mo412drawLineNGM6Ib0(long j, long j2, long j3, float f, int i, float f2) {
        this.canvasDrawScope.mo412drawLineNGM6Ib0(j, j2, j3, f, i, f2);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-GBMwjPU */
    public final void mo413drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle drawStyle, int i) {
        this.canvasDrawScope.mo413drawPathGBMwjPU(path, brush, f, drawStyle, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-LG529CI */
    public final void mo414drawPathLG529CI(AndroidPath androidPath, long j) {
        this.canvasDrawScope.mo414drawPathLG529CI(androidPath, j);
    }

    /* renamed from: drawRect-AsUm42w, reason: not valid java name */
    public final void m511drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle drawStyle) {
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        canvasDrawScope.drawParams.canvas.drawRect(Float.intBitsToFloat(i), Float.intBitsToFloat(i2), Float.intBitsToFloat((int) (j2 >> 32)) + Float.intBitsToFloat(i), Float.intBitsToFloat((int) (j2 & 4294967295L)) + Float.intBitsToFloat(i2), canvasDrawScope.m407configurePaintswdJneE(brush, drawStyle, f, null, 3, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-n-J9OG0 */
    public final void mo415drawRectnJ9OG0(long j, long j2, long j3, float f, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo415drawRectnJ9OG0(j, j2, j3, f, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-ZuiqVtQ */
    public final void mo416drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle) {
        this.canvasDrawScope.mo416drawRoundRectZuiqVtQ(brush, j, j2, j3, f, drawStyle);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-u-Aw5IA */
    public final void mo417drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle drawStyle, float f) {
        this.canvasDrawScope.mo417drawRoundRectuAw5IA(j, j2, j3, j4, drawStyle, f);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: getCenter-F1C5BW0 */
    public final long mo431getCenterF1C5BW0() {
        return this.canvasDrawScope.mo431getCenterF1C5BW0();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.canvasDrawScope.getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public final CanvasDrawScope$drawContext$1 getDrawContext() {
        return this.canvasDrawScope.drawContext;
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public final float getFontScale() {
        return this.canvasDrawScope.getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public final LayoutDirection getLayoutDirection() {
        return this.canvasDrawScope.drawParams.layoutDirection;
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: getSize-NH-jbRc */
    public final long mo432getSizeNHjbRc() {
        return this.canvasDrawScope.mo432getSizeNHjbRc();
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx-0680j_4 */
    public final int mo45roundToPx0680j_4(float f) {
        return this.canvasDrawScope.mo45roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* renamed from: toDp-GaN1DYA */
    public final float mo46toDpGaN1DYA(long j) {
        return this.canvasDrawScope.mo46toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public final float mo48toDpu2uoSUM(int i) {
        return this.canvasDrawScope.mo48toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDpSize-k-rfVVM */
    public final long mo49toDpSizekrfVVM(long j) {
        return this.canvasDrawScope.mo49toDpSizekrfVVM(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx--R2X_6o */
    public final float mo50toPxR2X_6o(long j) {
        return this.canvasDrawScope.mo50toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx-0680j_4 */
    public final float mo51toPx0680j_4(float f) {
        return this.canvasDrawScope.getDensity() * f;
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSize-XkaWNTQ */
    public final long mo52toSizeXkaWNTQ(long j) {
        return this.canvasDrawScope.mo52toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* renamed from: toSp-0xMU5do */
    public final long mo53toSp0xMU5do(float f) {
        return this.canvasDrawScope.mo53toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public final long mo54toSpkPz2Gy4(float f) {
        return this.canvasDrawScope.mo54toSpkPz2Gy4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public final float mo47toDpu2uoSUM(float f) {
        return f / this.canvasDrawScope.getDensity();
    }
}
