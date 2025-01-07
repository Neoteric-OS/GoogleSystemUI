package androidx.compose.foundation;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.widget.EdgeEffect;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DrawStretchOverscrollModifier extends InspectorValueInfo implements DrawModifier {
    public RenderNode _renderNode;
    public final EdgeEffectWrapper edgeEffectWrapper;
    public final AndroidEdgeEffectOverscrollEffect overscrollEffect;

    public DrawStretchOverscrollModifier(AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect, EdgeEffectWrapper edgeEffectWrapper) {
        this.overscrollEffect = androidEdgeEffectOverscrollEffect;
        this.edgeEffectWrapper = edgeEffectWrapper;
    }

    public static boolean drawWithRotation(float f, EdgeEffect edgeEffect, Canvas canvas) {
        if (f == 0.0f) {
            return edgeEffect.draw(canvas);
        }
        int save = canvas.save();
        canvas.rotate(f);
        boolean draw = edgeEffect.draw(canvas);
        canvas.restoreToCount(save);
        return draw;
    }

    @Override // androidx.compose.ui.draw.DrawModifier
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        Orientation orientation;
        boolean z;
        Orientation orientation2;
        Orientation orientation3;
        float f;
        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
        long mo432getSizeNHjbRc = canvasDrawScope.mo432getSizeNHjbRc();
        AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect = this.overscrollEffect;
        boolean m326equalsimpl0 = Size.m326equalsimpl0(androidEdgeEffectOverscrollEffect.containerSize, 0L);
        boolean m326equalsimpl02 = Size.m326equalsimpl0(mo432getSizeNHjbRc, androidEdgeEffectOverscrollEffect.containerSize);
        androidEdgeEffectOverscrollEffect.containerSize = mo432getSizeNHjbRc;
        if (!m326equalsimpl02) {
            long roundToInt = (MathKt.roundToInt(Float.intBitsToFloat((int) (mo432getSizeNHjbRc & 4294967295L))) & 4294967295L) | (MathKt.roundToInt(Float.intBitsToFloat((int) (mo432getSizeNHjbRc >> 32))) << 32);
            EdgeEffectWrapper edgeEffectWrapper = androidEdgeEffectOverscrollEffect.edgeEffectWrapper;
            edgeEffectWrapper.size = roundToInt;
            EdgeEffect edgeEffect = edgeEffectWrapper.topEffect;
            if (edgeEffect != null) {
                edgeEffect.setSize((int) (roundToInt >> 32), (int) (roundToInt & 4294967295L));
            }
            EdgeEffect edgeEffect2 = edgeEffectWrapper.bottomEffect;
            if (edgeEffect2 != null) {
                edgeEffect2.setSize((int) (roundToInt >> 32), (int) (roundToInt & 4294967295L));
            }
            EdgeEffect edgeEffect3 = edgeEffectWrapper.leftEffect;
            if (edgeEffect3 != null) {
                edgeEffect3.setSize((int) (roundToInt & 4294967295L), (int) (roundToInt >> 32));
            }
            EdgeEffect edgeEffect4 = edgeEffectWrapper.rightEffect;
            if (edgeEffect4 != null) {
                edgeEffect4.setSize((int) (roundToInt & 4294967295L), (int) (roundToInt >> 32));
            }
            EdgeEffect edgeEffect5 = edgeEffectWrapper.topEffectNegation;
            if (edgeEffect5 != null) {
                edgeEffect5.setSize((int) (roundToInt >> 32), (int) (roundToInt & 4294967295L));
            }
            EdgeEffect edgeEffect6 = edgeEffectWrapper.bottomEffectNegation;
            if (edgeEffect6 != null) {
                edgeEffect6.setSize((int) (roundToInt >> 32), (int) (roundToInt & 4294967295L));
            }
            EdgeEffect edgeEffect7 = edgeEffectWrapper.leftEffectNegation;
            if (edgeEffect7 != null) {
                edgeEffect7.setSize((int) (roundToInt & 4294967295L), (int) (roundToInt >> 32));
            }
            EdgeEffect edgeEffect8 = edgeEffectWrapper.rightEffectNegation;
            if (edgeEffect8 != null) {
                edgeEffect8.setSize((int) (roundToInt & 4294967295L), (int) (roundToInt >> 32));
            }
        }
        if (!m326equalsimpl0 && !m326equalsimpl02) {
            androidEdgeEffectOverscrollEffect.animateToReleaseIfNeeded();
        }
        if (Size.m330isEmptyimpl(canvasDrawScope.mo432getSizeNHjbRc())) {
            layoutNodeDrawScope.drawContent();
            return;
        }
        ((SnapshotMutableStateImpl) androidEdgeEffectOverscrollEffect.redrawSignal).getValue();
        float mo51toPx0680j_4 = layoutNodeDrawScope.mo51toPx0680j_4(ClipScrollableContainerKt.MaxSupportedElevation);
        androidx.compose.ui.graphics.Canvas canvas = canvasDrawScope.drawContext.getCanvas();
        Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
        Canvas canvas3 = ((AndroidCanvas) canvas).internalCanvas;
        EdgeEffectWrapper edgeEffectWrapper2 = this.edgeEffectWrapper;
        boolean z2 = EdgeEffectWrapper.isAnimating(edgeEffectWrapper2.topEffect) || EdgeEffectWrapper.isStretched(edgeEffectWrapper2.topEffectNegation) || EdgeEffectWrapper.isAnimating(edgeEffectWrapper2.bottomEffect) || EdgeEffectWrapper.isStretched(edgeEffectWrapper2.bottomEffectNegation);
        boolean z3 = EdgeEffectWrapper.isAnimating(edgeEffectWrapper2.leftEffect) || EdgeEffectWrapper.isStretched(edgeEffectWrapper2.leftEffectNegation) || EdgeEffectWrapper.isAnimating(edgeEffectWrapper2.rightEffect) || EdgeEffectWrapper.isStretched(edgeEffectWrapper2.rightEffectNegation);
        if (z2 && z3) {
            getRenderNode().setPosition(0, 0, canvas3.getWidth(), canvas3.getHeight());
        } else if (z2) {
            getRenderNode().setPosition(0, 0, (MathKt.roundToInt(mo51toPx0680j_4) * 2) + canvas3.getWidth(), canvas3.getHeight());
        } else {
            if (!z3) {
                layoutNodeDrawScope.drawContent();
                return;
            }
            getRenderNode().setPosition(0, 0, canvas3.getWidth(), (MathKt.roundToInt(mo51toPx0680j_4) * 2) + canvas3.getHeight());
        }
        RecordingCanvas beginRecording = getRenderNode().beginRecording();
        boolean isStretched = EdgeEffectWrapper.isStretched(edgeEffectWrapper2.leftEffectNegation);
        Orientation orientation4 = Orientation.Horizontal;
        if (isStretched) {
            EdgeEffect edgeEffect9 = edgeEffectWrapper2.leftEffectNegation;
            if (edgeEffect9 == null) {
                edgeEffect9 = edgeEffectWrapper2.createEdgeEffect(orientation4);
                edgeEffectWrapper2.leftEffectNegation = edgeEffect9;
            }
            drawWithRotation(90.0f, edgeEffect9, beginRecording);
            edgeEffect9.finish();
        }
        if (EdgeEffectWrapper.isAnimating(edgeEffectWrapper2.leftEffect)) {
            EdgeEffect orCreateLeftEffect = edgeEffectWrapper2.getOrCreateLeftEffect();
            z = drawWithRotation(270.0f, orCreateLeftEffect, beginRecording);
            if (EdgeEffectWrapper.isStretched(edgeEffectWrapper2.leftEffect)) {
                float intBitsToFloat = Float.intBitsToFloat((int) (androidEdgeEffectOverscrollEffect.m20displacementF1C5BW0$foundation_release() & 4294967295L));
                EdgeEffect edgeEffect10 = edgeEffectWrapper2.leftEffectNegation;
                orientation = orientation4;
                if (edgeEffect10 == null) {
                    edgeEffect10 = edgeEffectWrapper2.createEdgeEffect(orientation);
                    edgeEffectWrapper2.leftEffectNegation = edgeEffect10;
                }
                EdgeEffectCompat.onPullDistanceCompat(edgeEffect10, EdgeEffectCompat.getDistanceCompat(orCreateLeftEffect), 1 - intBitsToFloat);
            } else {
                orientation = orientation4;
            }
        } else {
            orientation = orientation4;
            z = false;
        }
        boolean isStretched2 = EdgeEffectWrapper.isStretched(edgeEffectWrapper2.topEffectNegation);
        Orientation orientation5 = Orientation.Vertical;
        if (isStretched2) {
            EdgeEffect edgeEffect11 = edgeEffectWrapper2.topEffectNegation;
            if (edgeEffect11 == null) {
                edgeEffect11 = edgeEffectWrapper2.createEdgeEffect(orientation5);
                edgeEffectWrapper2.topEffectNegation = edgeEffect11;
            }
            drawWithRotation(180.0f, edgeEffect11, beginRecording);
            edgeEffect11.finish();
        }
        if (EdgeEffectWrapper.isAnimating(edgeEffectWrapper2.topEffect)) {
            EdgeEffect orCreateTopEffect = edgeEffectWrapper2.getOrCreateTopEffect();
            boolean z4 = drawWithRotation(0.0f, orCreateTopEffect, beginRecording) || z;
            if (EdgeEffectWrapper.isStretched(edgeEffectWrapper2.topEffect)) {
                orientation2 = orientation;
                float intBitsToFloat2 = Float.intBitsToFloat((int) (androidEdgeEffectOverscrollEffect.m20displacementF1C5BW0$foundation_release() >> 32));
                EdgeEffect edgeEffect12 = edgeEffectWrapper2.topEffectNegation;
                if (edgeEffect12 == null) {
                    edgeEffect12 = edgeEffectWrapper2.createEdgeEffect(orientation5);
                    edgeEffectWrapper2.topEffectNegation = edgeEffect12;
                }
                EdgeEffectCompat.onPullDistanceCompat(edgeEffect12, EdgeEffectCompat.getDistanceCompat(orCreateTopEffect), intBitsToFloat2);
            } else {
                orientation2 = orientation;
            }
            z = z4;
        } else {
            orientation2 = orientation;
        }
        if (EdgeEffectWrapper.isStretched(edgeEffectWrapper2.rightEffectNegation)) {
            EdgeEffect edgeEffect13 = edgeEffectWrapper2.rightEffectNegation;
            orientation3 = orientation2;
            if (edgeEffect13 == null) {
                edgeEffect13 = edgeEffectWrapper2.createEdgeEffect(orientation3);
                edgeEffectWrapper2.rightEffectNegation = edgeEffect13;
            }
            drawWithRotation(270.0f, edgeEffect13, beginRecording);
            edgeEffect13.finish();
        } else {
            orientation3 = orientation2;
        }
        if (EdgeEffectWrapper.isAnimating(edgeEffectWrapper2.rightEffect)) {
            EdgeEffect orCreateRightEffect = edgeEffectWrapper2.getOrCreateRightEffect();
            boolean z5 = drawWithRotation(90.0f, orCreateRightEffect, beginRecording) || z;
            if (EdgeEffectWrapper.isStretched(edgeEffectWrapper2.rightEffect)) {
                float intBitsToFloat3 = Float.intBitsToFloat((int) (androidEdgeEffectOverscrollEffect.m20displacementF1C5BW0$foundation_release() & 4294967295L));
                EdgeEffect edgeEffect14 = edgeEffectWrapper2.rightEffectNegation;
                if (edgeEffect14 == null) {
                    edgeEffect14 = edgeEffectWrapper2.createEdgeEffect(orientation3);
                    edgeEffectWrapper2.rightEffectNegation = edgeEffect14;
                }
                EdgeEffectCompat.onPullDistanceCompat(edgeEffect14, EdgeEffectCompat.getDistanceCompat(orCreateRightEffect), intBitsToFloat3);
            }
            z = z5;
        }
        if (EdgeEffectWrapper.isStretched(edgeEffectWrapper2.bottomEffectNegation)) {
            EdgeEffect edgeEffect15 = edgeEffectWrapper2.bottomEffectNegation;
            if (edgeEffect15 == null) {
                edgeEffect15 = edgeEffectWrapper2.createEdgeEffect(orientation5);
                edgeEffectWrapper2.bottomEffectNegation = edgeEffect15;
            }
            f = 0.0f;
            drawWithRotation(0.0f, edgeEffect15, beginRecording);
            edgeEffect15.finish();
        } else {
            f = 0.0f;
        }
        if (EdgeEffectWrapper.isAnimating(edgeEffectWrapper2.bottomEffect)) {
            EdgeEffect orCreateBottomEffect = edgeEffectWrapper2.getOrCreateBottomEffect();
            boolean z6 = drawWithRotation(180.0f, orCreateBottomEffect, beginRecording) || z;
            if (EdgeEffectWrapper.isStretched(edgeEffectWrapper2.bottomEffect)) {
                float intBitsToFloat4 = Float.intBitsToFloat((int) (androidEdgeEffectOverscrollEffect.m20displacementF1C5BW0$foundation_release() >> 32));
                EdgeEffect edgeEffect16 = edgeEffectWrapper2.bottomEffectNegation;
                if (edgeEffect16 == null) {
                    edgeEffect16 = edgeEffectWrapper2.createEdgeEffect(orientation5);
                    edgeEffectWrapper2.bottomEffectNegation = edgeEffect16;
                }
                EdgeEffectCompat.onPullDistanceCompat(edgeEffect16, EdgeEffectCompat.getDistanceCompat(orCreateBottomEffect), 1 - intBitsToFloat4);
            }
            z = z6;
        }
        if (z) {
            androidEdgeEffectOverscrollEffect.invalidateOverscroll$foundation_release();
        }
        float f2 = z3 ? f : mo51toPx0680j_4;
        if (z2) {
            mo51toPx0680j_4 = f;
        }
        LayoutDirection layoutDirection = layoutNodeDrawScope.getLayoutDirection();
        AndroidCanvas androidCanvas = new AndroidCanvas();
        androidCanvas.internalCanvas = beginRecording;
        long mo432getSizeNHjbRc2 = canvasDrawScope.mo432getSizeNHjbRc();
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope$drawContext$1.this$0.drawParams;
        Density density = drawParams.density;
        LayoutDirection layoutDirection2 = drawParams.layoutDirection;
        androidx.compose.ui.graphics.Canvas canvas4 = canvasDrawScope$drawContext$1.getCanvas();
        long m418getSizeNHjbRc = canvasDrawScope.drawContext.m418getSizeNHjbRc();
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$12 = canvasDrawScope.drawContext;
        GraphicsLayer graphicsLayer = canvasDrawScope$drawContext$12.graphicsLayer;
        canvasDrawScope$drawContext$12.setDensity(layoutNodeDrawScope);
        canvasDrawScope$drawContext$12.setLayoutDirection(layoutDirection);
        canvasDrawScope$drawContext$12.setCanvas(androidCanvas);
        canvasDrawScope$drawContext$12.m419setSizeuvyYCjk(mo432getSizeNHjbRc2);
        canvasDrawScope$drawContext$12.graphicsLayer = null;
        androidCanvas.save();
        try {
            canvasDrawScope.drawContext.transform.translate(f2, mo51toPx0680j_4);
            try {
                layoutNodeDrawScope.drawContent();
                float f3 = -f2;
                float f4 = -mo51toPx0680j_4;
                canvasDrawScope.drawContext.transform.translate(f3, f4);
                androidCanvas.restore();
                CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$13 = canvasDrawScope.drawContext;
                canvasDrawScope$drawContext$13.setDensity(density);
                canvasDrawScope$drawContext$13.setLayoutDirection(layoutDirection2);
                canvasDrawScope$drawContext$13.setCanvas(canvas4);
                canvasDrawScope$drawContext$13.m419setSizeuvyYCjk(m418getSizeNHjbRc);
                canvasDrawScope$drawContext$13.graphicsLayer = graphicsLayer;
                getRenderNode().endRecording();
                int save = canvas3.save();
                canvas3.translate(f3, f4);
                canvas3.drawRenderNode(getRenderNode());
                canvas3.restoreToCount(save);
            } catch (Throwable th) {
                canvasDrawScope.drawContext.transform.translate(-f2, -mo51toPx0680j_4);
                throw th;
            }
        } catch (Throwable th2) {
            androidCanvas.restore();
            CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$14 = canvasDrawScope.drawContext;
            canvasDrawScope$drawContext$14.setDensity(density);
            canvasDrawScope$drawContext$14.setLayoutDirection(layoutDirection2);
            canvasDrawScope$drawContext$14.setCanvas(canvas4);
            canvasDrawScope$drawContext$14.m419setSizeuvyYCjk(m418getSizeNHjbRc);
            canvasDrawScope$drawContext$14.graphicsLayer = graphicsLayer;
            throw th2;
        }
    }

    public final RenderNode getRenderNode() {
        RenderNode renderNode = this._renderNode;
        if (renderNode != null) {
            return renderNode;
        }
        RenderNode renderNode2 = new RenderNode("AndroidEdgeEffectOverscrollEffect");
        this._renderNode = renderNode2;
        return renderNode2;
    }
}
