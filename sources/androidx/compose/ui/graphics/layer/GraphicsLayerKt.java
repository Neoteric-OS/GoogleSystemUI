package androidx.compose.ui.graphics.layer;

import android.graphics.Matrix;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.InlineClassHelperKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class GraphicsLayerKt {
    public static final void drawLayer(DrawScope drawScope, GraphicsLayer graphicsLayer) {
        AndroidCanvas androidCanvas;
        GraphicsLayer graphicsLayer2;
        int i;
        boolean z;
        float f;
        float f2;
        Canvas canvas = drawScope.getDrawContext().getCanvas();
        GraphicsLayer graphicsLayer3 = drawScope.getDrawContext().graphicsLayer;
        if (graphicsLayer.isReleased) {
            return;
        }
        GraphicsLayerV29 graphicsLayerV29 = graphicsLayer.impl;
        if (!graphicsLayerV29.renderNode.hasDisplayList()) {
            try {
                graphicsLayer.recordInternal();
            } catch (Throwable unused) {
            }
        }
        graphicsLayer.configureOutline();
        boolean z2 = graphicsLayerV29.shadowElevation > 0.0f;
        if (z2) {
            canvas.enableZ();
        }
        android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
        AndroidCanvas androidCanvas2 = (AndroidCanvas) canvas;
        android.graphics.Canvas canvas3 = androidCanvas2.internalCanvas;
        boolean isHardwareAccelerated = canvas3.isHardwareAccelerated();
        if (isHardwareAccelerated) {
            androidCanvas = androidCanvas2;
            graphicsLayer2 = null;
        } else {
            canvas3.save();
            long j = graphicsLayer.topLeft;
            float f3 = (int) (j >> 32);
            float f4 = (int) (j & 4294967295L);
            long j2 = graphicsLayer.size;
            androidCanvas = androidCanvas2;
            float f5 = ((int) (j2 >> 32)) + f3;
            float f6 = ((int) (j2 & 4294967295L)) + f4;
            float f7 = graphicsLayerV29.alpha;
            int i2 = graphicsLayerV29.blendMode;
            if (f7 < 1.0f || !BlendMode.m357equalsimpl0(i2, 3) || CompositingStrategy.m433equalsimpl0(graphicsLayerV29.compositingStrategy, 1)) {
                AndroidPaint androidPaint = graphicsLayer.softwareLayerPaint;
                if (androidPaint == null) {
                    androidPaint = AndroidPaint_androidKt.Paint();
                    graphicsLayer.softwareLayerPaint = androidPaint;
                }
                androidPaint.setAlpha(f7);
                androidPaint.m347setBlendModes9anfk8(i2);
                androidPaint.setColorFilter(null);
                f = f4;
                f2 = f3;
                graphicsLayer2 = null;
                canvas3.saveLayer(f3, f4, f5, f6, androidPaint.internalPaint);
            } else {
                canvas3.save();
                f = f4;
                f2 = f3;
                graphicsLayer2 = null;
            }
            canvas3.translate(f2, f);
            Matrix matrix = graphicsLayerV29.matrix;
            if (matrix == null) {
                matrix = new Matrix();
                graphicsLayerV29.matrix = matrix;
            }
            graphicsLayerV29.renderNode.getMatrix(matrix);
            canvas3.concat(matrix);
        }
        boolean z3 = graphicsLayer.usePathForClip || (!isHardwareAccelerated && graphicsLayerV29.clip);
        if (z3) {
            canvas.save();
            Outline outline = graphicsLayer.getOutline();
            if (outline instanceof Outline.Rectangle) {
                Canvas.m360clipRectmtrdDE$default(canvas, outline.getBounds());
            } else if (outline instanceof Outline.Rounded) {
                AndroidPath androidPath = graphicsLayer.roundRectClipPath;
                if (androidPath != null) {
                    androidPath.internalPath.rewind();
                } else {
                    androidPath = AndroidPath_androidKt.Path();
                    graphicsLayer.roundRectClipPath = androidPath;
                }
                Path.addRoundRect$default(androidPath, ((Outline.Rounded) outline).roundRect);
                canvas.mo334clipPathmtrdDE(androidPath, 1);
            } else if (outline instanceof Outline.Generic) {
                canvas.mo334clipPathmtrdDE(((Outline.Generic) outline).path, 1);
            }
        }
        if (graphicsLayer3 != null) {
            ChildLayerDependenciesTracker childLayerDependenciesTracker = graphicsLayer3.childDependenciesTracker;
            if (!childLayerDependenciesTracker.trackingInProgress) {
                InlineClassHelperKt.throwIllegalArgumentException("Only add dependencies during a tracking");
            }
            MutableScatterSet mutableScatterSet = childLayerDependenciesTracker.dependenciesSet;
            if (mutableScatterSet != null) {
                mutableScatterSet.add(graphicsLayer);
            } else if (childLayerDependenciesTracker.dependency != null) {
                int i3 = ScatterSetKt.$r8$clinit;
                MutableScatterSet mutableScatterSet2 = new MutableScatterSet();
                GraphicsLayer graphicsLayer4 = childLayerDependenciesTracker.dependency;
                Intrinsics.checkNotNull(graphicsLayer4);
                mutableScatterSet2.add(graphicsLayer4);
                mutableScatterSet2.add(graphicsLayer);
                childLayerDependenciesTracker.dependenciesSet = mutableScatterSet2;
                childLayerDependenciesTracker.dependency = graphicsLayer2;
            } else {
                childLayerDependenciesTracker.dependency = graphicsLayer;
            }
            MutableScatterSet mutableScatterSet3 = childLayerDependenciesTracker.oldDependenciesSet;
            if (mutableScatterSet3 != null) {
                boolean remove = mutableScatterSet3.remove(graphicsLayer);
                i = 1;
                z = !remove;
            } else {
                i = 1;
                if (childLayerDependenciesTracker.oldDependency != graphicsLayer) {
                    z = true;
                } else {
                    childLayerDependenciesTracker.oldDependency = graphicsLayer2;
                    z = false;
                }
            }
            if (z) {
                graphicsLayer.parentLayerUsages += i;
            }
        }
        androidCanvas.internalCanvas.drawRenderNode(graphicsLayerV29.renderNode);
        if (z3) {
            canvas.restore();
        }
        if (z2) {
            canvas.disableZ();
        }
        if (isHardwareAccelerated) {
            return;
        }
        canvas3.restore();
    }
}
