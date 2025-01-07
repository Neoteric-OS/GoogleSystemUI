package androidx.compose.ui.platform;

import android.view.ViewParent;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.MatrixKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.graphics.layer.GraphicsLayerV29;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Function;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GraphicsLayerOwnerLayer implements OwnedLayer {
    public final GraphicsContext context;
    public Function2 drawBlock;
    public boolean drawnWithEnabledZ;
    public GraphicsLayer graphicsLayer;
    public Function0 invalidateParentLayer;
    public float[] inverseMatrixCache;
    public boolean isDestroyed;
    public boolean isDirty;
    public boolean isInverseMatrixDirty;
    public boolean isMatrixDirty;
    public int mutatedFields;
    public Outline outline;
    public final AndroidComposeView ownerView;
    public long size;
    public AndroidPaint softwareLayerPaint;
    public AndroidPath tmpPath;
    public final float[] matrixCache = Matrix.m379constructorimpl$default();
    public Density density = DensityKt.Density$default();
    public LayoutDirection layoutDirection = LayoutDirection.Ltr;
    public final CanvasDrawScope scope = new CanvasDrawScope();
    public long transformOrigin = TransformOrigin.Center;
    public boolean isIdentity = true;
    public final Function1 recordLambda = new Function1() { // from class: androidx.compose.ui.platform.GraphicsLayerOwnerLayer$recordLambda$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            DrawScope drawScope = (DrawScope) obj;
            GraphicsLayerOwnerLayer graphicsLayerOwnerLayer = GraphicsLayerOwnerLayer.this;
            Canvas canvas = drawScope.getDrawContext().getCanvas();
            Function2 function2 = graphicsLayerOwnerLayer.drawBlock;
            if (function2 != null) {
                function2.invoke(canvas, drawScope.getDrawContext().graphicsLayer);
            }
            return Unit.INSTANCE;
        }
    };

    public GraphicsLayerOwnerLayer(GraphicsLayer graphicsLayer, GraphicsContext graphicsContext, AndroidComposeView androidComposeView, Function2 function2, Function0 function0) {
        this.graphicsLayer = graphicsLayer;
        this.context = graphicsContext;
        this.ownerView = androidComposeView;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
        long j = Integer.MAX_VALUE;
        this.size = (j & 4294967295L) | (j << 32);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void destroy() {
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.isDestroyed = true;
        boolean z = this.isDirty;
        AndroidComposeView androidComposeView = this.ownerView;
        if (z) {
            this.isDirty = false;
            androidComposeView.notifyLayerIsDirty$ui_release(this, false);
        }
        GraphicsContext graphicsContext = this.context;
        if (graphicsContext != null) {
            graphicsContext.releaseGraphicsLayer(this.graphicsLayer);
            androidComposeView.recycle$ui_release(this);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void drawLayer(Canvas canvas, GraphicsLayer graphicsLayer) {
        android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
        android.graphics.Canvas canvas3 = ((AndroidCanvas) canvas).internalCanvas;
        if (canvas3.isHardwareAccelerated()) {
            updateDisplayList();
            this.drawnWithEnabledZ = this.graphicsLayer.impl.shadowElevation > 0.0f;
            CanvasDrawScope canvasDrawScope = this.scope;
            CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
            canvasDrawScope$drawContext$1.setCanvas(canvas);
            canvasDrawScope$drawContext$1.graphicsLayer = graphicsLayer;
            GraphicsLayerKt.drawLayer(canvasDrawScope, this.graphicsLayer);
            return;
        }
        GraphicsLayer graphicsLayer2 = this.graphicsLayer;
        long j = graphicsLayer2.topLeft;
        float f = (int) (j >> 32);
        float f2 = (int) (j & 4294967295L);
        long j2 = this.size;
        float f3 = ((int) (j2 >> 32)) + f;
        float f4 = f2 + ((int) (j2 & 4294967295L));
        if (graphicsLayer2.impl.alpha < 1.0f) {
            AndroidPaint androidPaint = this.softwareLayerPaint;
            if (androidPaint == null) {
                androidPaint = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = androidPaint;
            }
            androidPaint.setAlpha(this.graphicsLayer.impl.alpha);
            canvas3.saveLayer(f, f2, f3, f4, androidPaint.internalPaint);
        } else {
            canvas.save();
        }
        canvas.translate(f, f2);
        canvas.mo336concat58bKbWc(m564getMatrixsQKQjiQ());
        GraphicsLayer graphicsLayer3 = this.graphicsLayer;
        boolean z = graphicsLayer3.impl.clip;
        if (z && z) {
            Outline outline = graphicsLayer3.getOutline();
            if (outline instanceof Outline.Rectangle) {
                Canvas.m360clipRectmtrdDE$default(canvas, ((Outline.Rectangle) outline).rect);
            } else if (outline instanceof Outline.Rounded) {
                AndroidPath androidPath = this.tmpPath;
                if (androidPath == null) {
                    androidPath = AndroidPath_androidKt.Path();
                    this.tmpPath = androidPath;
                }
                androidPath.reset();
                Path.addRoundRect$default(androidPath, ((Outline.Rounded) outline).roundRect);
                canvas.mo334clipPathmtrdDE(androidPath, 1);
            } else if (outline instanceof Outline.Generic) {
                canvas.mo334clipPathmtrdDE(((Outline.Generic) outline).path, 1);
            }
        }
        Function2 function2 = this.drawBlock;
        if (function2 != null) {
            function2.invoke(canvas, null);
        }
        canvas.restore();
    }

    /* renamed from: getInverseMatrix-3i98HWw, reason: not valid java name */
    public final float[] m563getInverseMatrix3i98HWw() {
        float[] fArr = this.inverseMatrixCache;
        if (fArr == null) {
            fArr = Matrix.m379constructorimpl$default();
            this.inverseMatrixCache = fArr;
        }
        if (!this.isInverseMatrixDirty) {
            if (Float.isNaN(fArr[0])) {
                return null;
            }
            return fArr;
        }
        this.isInverseMatrixDirty = false;
        float[] m564getMatrixsQKQjiQ = m564getMatrixsQKQjiQ();
        if (this.isIdentity) {
            return m564getMatrixsQKQjiQ;
        }
        if (InvertMatrixKt.m565invertToJiSxe2E(m564getMatrixsQKQjiQ, fArr)) {
            return fArr;
        }
        fArr[0] = Float.NaN;
        return null;
    }

    /* renamed from: getMatrix-sQKQjiQ, reason: not valid java name */
    public final float[] m564getMatrixsQKQjiQ() {
        boolean z = this.isMatrixDirty;
        float[] fArr = this.matrixCache;
        if (z) {
            GraphicsLayer graphicsLayer = this.graphicsLayer;
            long j = graphicsLayer.pivotOffset;
            if ((9223372034707292159L & j) == 9205357640488583168L) {
                j = SizeKt.m332getCenteruvyYCjk(IntSizeKt.m685toSizeozmzZPI(this.size));
            }
            float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
            float intBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
            GraphicsLayerV29 graphicsLayerV29 = graphicsLayer.impl;
            float f = graphicsLayerV29.translationX;
            float f2 = graphicsLayerV29.translationY;
            float f3 = graphicsLayerV29.rotationZ;
            float f4 = graphicsLayerV29.scaleX;
            float f5 = graphicsLayerV29.scaleY;
            double d = 0.5f;
            float floor = 0.0f - ((float) Math.floor(d));
            float abs = Math.abs(floor) * 2.0f;
            float f6 = 1.0f - abs;
            float f7 = ((floor * 8.0f) * f6) / (1.25f - (abs * f6));
            double d2 = 0.75f;
            float floor2 = 0.25f - ((float) Math.floor(d2));
            float abs2 = Math.abs(floor2) * 2.0f;
            float f8 = 1.0f - abs2;
            float f9 = ((floor2 * 8.0f) * f8) / (1.25f - (abs2 * f8));
            float f10 = -f7;
            float f11 = (f2 * f9) - (1.0f * f7);
            float f12 = (1.0f * f9) + (f2 * f7);
            float floor3 = 0.0f - ((float) Math.floor(d));
            float abs3 = Math.abs(floor3) * 2.0f;
            float f13 = 1.0f - abs3;
            float f14 = ((floor3 * 8.0f) * f13) / (1.25f - (abs3 * f13));
            float floor4 = 0.25f - ((float) Math.floor(d2));
            float abs4 = Math.abs(floor4) * 2.0f;
            float f15 = 1.0f - abs4;
            float f16 = ((floor4 * 8.0f) * f15) / (1.25f - (abs4 * f15));
            float f17 = -f14;
            float f18 = f7 * f14;
            float f19 = f7 * f16;
            float f20 = f9 * f14;
            float f21 = f9 * f16;
            float f22 = (f12 * f14) + (f * f16);
            float f23 = (f12 * f16) + ((-f) * f14);
            float f24 = f3 * 0.0027777778f;
            float floor5 = f24 - ((float) Math.floor(f24 + 0.5f));
            float abs5 = Math.abs(floor5) * 2.0f;
            float f25 = 1.0f - abs5;
            float f26 = ((floor5 * 8.0f) * f25) / (1.25f - (abs5 * f25));
            float floor6 = (f24 + 0.25f) - ((float) Math.floor(0.5f + r4));
            float abs6 = Math.abs(floor6) * 2.0f;
            float f27 = 1.0f - abs6;
            float f28 = ((floor6 * 8.0f) * f27) / (1.25f - (abs6 * f27));
            float f29 = -f26;
            float f30 = (f28 * f18) + (f29 * f16);
            float f31 = ((f18 * f26) + (f16 * f28)) * f4;
            float f32 = f26 * f9 * f4;
            float f33 = ((f26 * f19) + (f28 * f17)) * f4;
            float f34 = f30 * f5;
            float f35 = f9 * f28 * f5;
            float f36 = ((f28 * f19) + (f29 * f17)) * f5;
            float f37 = f20 * 1.0f;
            float f38 = f10 * 1.0f;
            float f39 = f21 * 1.0f;
            if (fArr.length >= 16) {
                fArr[0] = f31;
                fArr[1] = f32;
                fArr[2] = f33;
                fArr[3] = 0.0f;
                fArr[4] = f34;
                fArr[5] = f35;
                fArr[6] = f36;
                fArr[7] = 0.0f;
                fArr[8] = f37;
                fArr[9] = f38;
                fArr[10] = f39;
                fArr[11] = 0.0f;
                float f40 = -intBitsToFloat;
                fArr[12] = ((f31 * f40) - (f34 * intBitsToFloat2)) + f22 + intBitsToFloat;
                fArr[13] = ((f32 * f40) - (f35 * intBitsToFloat2)) + f11 + intBitsToFloat2;
                fArr[14] = ((f40 * f33) - (intBitsToFloat2 * f36)) + f23;
                fArr[15] = 1.0f;
            }
            this.isMatrixDirty = false;
            this.isIdentity = MatrixKt.m385isIdentity58bKbWc(fArr);
        }
        return fArr;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: getUnderlyingMatrix-sQKQjiQ */
    public final float[] mo543getUnderlyingMatrixsQKQjiQ() {
        return m564getMatrixsQKQjiQ();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void invalidate() {
        if (this.isDirty || this.isDestroyed) {
            return;
        }
        AndroidComposeView androidComposeView = this.ownerView;
        androidComposeView.invalidate();
        if (true != this.isDirty) {
            this.isDirty = true;
            androidComposeView.notifyLayerIsDirty$ui_release(this, true);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: inverseTransform-58bKbWc */
    public final void mo544inverseTransform58bKbWc(float[] fArr) {
        float[] m563getInverseMatrix3i98HWw = m563getInverseMatrix3i98HWw();
        if (m563getInverseMatrix3i98HWw != null) {
            Matrix.m383timesAssign58bKbWc(fArr, m563getInverseMatrix3i98HWw);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: isInLayer-k-4lQ0M */
    public final boolean mo545isInLayerk4lQ0M(long j) {
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
        GraphicsLayer graphicsLayer = this.graphicsLayer;
        if (graphicsLayer.impl.clip) {
            return ShapeContainingUtilKt.isInOutline(graphicsLayer.getOutline(), intBitsToFloat, intBitsToFloat2);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void mapBounds(MutableRect mutableRect, boolean z) {
        float[] m563getInverseMatrix3i98HWw = z ? m563getInverseMatrix3i98HWw() : m564getMatrixsQKQjiQ();
        if (this.isIdentity) {
            return;
        }
        if (m563getInverseMatrix3i98HWw != null) {
            Matrix.m381mapimpl(m563getInverseMatrix3i98HWw, mutableRect);
            return;
        }
        mutableRect.left = 0.0f;
        mutableRect.top = 0.0f;
        mutableRect.right = 0.0f;
        mutableRect.bottom = 0.0f;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: mapOffset-8S9VItk */
    public final long mo546mapOffset8S9VItk(long j, boolean z) {
        float[] m564getMatrixsQKQjiQ;
        if (z) {
            m564getMatrixsQKQjiQ = m563getInverseMatrix3i98HWw();
            if (m564getMatrixsQKQjiQ == null) {
                return 9187343241974906880L;
            }
        } else {
            m564getMatrixsQKQjiQ = m564getMatrixsQKQjiQ();
        }
        return this.isIdentity ? j : Matrix.m380mapMKHz9U(j, m564getMatrixsQKQjiQ);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: move--gyyYBs */
    public final void mo547movegyyYBs(long j) {
        GraphicsLayer graphicsLayer = this.graphicsLayer;
        if (!IntOffset.m674equalsimpl0(graphicsLayer.topLeft, j)) {
            graphicsLayer.topLeft = j;
            long j2 = graphicsLayer.size;
            int i = (int) (j >> 32);
            int i2 = (int) (j & 4294967295L);
            GraphicsLayerV29 graphicsLayerV29 = graphicsLayer.impl;
            graphicsLayerV29.renderNode.setPosition(i, i2, ((int) (j2 >> 32)) + i, ((int) (4294967295L & j2)) + i2);
            graphicsLayerV29.size = IntSizeKt.m685toSizeozmzZPI(j2);
        }
        AndroidComposeView androidComposeView = this.ownerView;
        ViewParent parent = androidComposeView.getParent();
        if (parent != null) {
            parent.onDescendantInvalidated(androidComposeView, androidComposeView);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: resize-ozmzZPI */
    public final void mo548resizeozmzZPI(long j) {
        if (IntSize.m683equalsimpl0(j, this.size)) {
            return;
        }
        this.size = j;
        if (this.isDirty || this.isDestroyed) {
            return;
        }
        AndroidComposeView androidComposeView = this.ownerView;
        androidComposeView.invalidate();
        if (true != this.isDirty) {
            this.isDirty = true;
            androidComposeView.notifyLayerIsDirty$ui_release(this, true);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void reuseLayer(Function2 function2, Function0 function0) {
        GraphicsContext graphicsContext = this.context;
        if (graphicsContext == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("currently reuse is only supported when we manage the layer lifecycle");
            throw null;
        }
        if (!this.graphicsLayer.isReleased) {
            InlineClassHelperKt.throwIllegalArgumentException("layer should have been released before reuse");
        }
        this.graphicsLayer = graphicsContext.createGraphicsLayer();
        this.isDestroyed = false;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
        this.isMatrixDirty = false;
        this.isInverseMatrixDirty = false;
        this.isIdentity = true;
        Matrix.m382resetimpl(this.matrixCache);
        float[] fArr = this.inverseMatrixCache;
        if (fArr != null) {
            Matrix.m382resetimpl(fArr);
        }
        this.transformOrigin = TransformOrigin.Center;
        this.drawnWithEnabledZ = false;
        long j = Integer.MAX_VALUE;
        this.size = (j & 4294967295L) | (j << 32);
        this.outline = null;
        this.mutatedFields = 0;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: transform-58bKbWc */
    public final void mo549transform58bKbWc(float[] fArr) {
        Matrix.m383timesAssign58bKbWc(fArr, m564getMatrixsQKQjiQ());
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void updateDisplayList() {
        if (this.isDirty) {
            if (!TransformOrigin.m398equalsimpl0(this.transformOrigin, TransformOrigin.Center) && !IntSize.m683equalsimpl0(this.graphicsLayer.size, this.size)) {
                GraphicsLayer graphicsLayer = this.graphicsLayer;
                float m399getPivotFractionXimpl = TransformOrigin.m399getPivotFractionXimpl(this.transformOrigin) * ((int) (this.size >> 32));
                float m400getPivotFractionYimpl = TransformOrigin.m400getPivotFractionYimpl(this.transformOrigin) * ((int) (this.size & 4294967295L));
                long floatToRawIntBits = (Float.floatToRawIntBits(m400getPivotFractionYimpl) & 4294967295L) | (Float.floatToRawIntBits(m399getPivotFractionXimpl) << 32);
                if (!Offset.m310equalsimpl0(graphicsLayer.pivotOffset, floatToRawIntBits)) {
                    graphicsLayer.pivotOffset = floatToRawIntBits;
                    GraphicsLayerV29 graphicsLayerV29 = graphicsLayer.impl;
                    if ((9223372034707292159L & floatToRawIntBits) == 9205357640488583168L) {
                        graphicsLayerV29.renderNode.resetPivot();
                    } else {
                        graphicsLayerV29.renderNode.setPivotX(Float.intBitsToFloat((int) (floatToRawIntBits >> 32)));
                        graphicsLayerV29.renderNode.setPivotY(Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L)));
                    }
                }
            }
            GraphicsLayer graphicsLayer2 = this.graphicsLayer;
            Density density = this.density;
            LayoutDirection layoutDirection = this.layoutDirection;
            long j = this.size;
            Function function = this.recordLambda;
            if (!IntSize.m683equalsimpl0(graphicsLayer2.size, j)) {
                graphicsLayer2.size = j;
                long j2 = graphicsLayer2.topLeft;
                int i = (int) (j2 >> 32);
                int i2 = (int) (j2 & 4294967295L);
                GraphicsLayerV29 graphicsLayerV292 = graphicsLayer2.impl;
                graphicsLayerV292.renderNode.setPosition(i, i2, ((int) (j >> 32)) + i, ((int) (j & 4294967295L)) + i2);
                graphicsLayerV292.size = IntSizeKt.m685toSizeozmzZPI(j);
                if (graphicsLayer2.roundRectOutlineSize == 9205357640488583168L) {
                    graphicsLayer2.outlineDirty = true;
                    graphicsLayer2.configureOutline();
                }
            }
            graphicsLayer2.density = density;
            graphicsLayer2.layoutDirection = layoutDirection;
            graphicsLayer2.drawBlock = (Lambda) function;
            graphicsLayer2.recordInternal();
            if (this.isDirty) {
                this.isDirty = false;
                this.ownerView.notifyLayerIsDirty$ui_release(this, false);
            }
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void updateLayerProperties(ReusableGraphicsLayerScope reusableGraphicsLayerScope) {
        AndroidComposeView androidComposeView;
        ViewParent parent;
        int i;
        Function0 function0;
        int i2 = reusableGraphicsLayerScope.mutatedFields | this.mutatedFields;
        this.layoutDirection = reusableGraphicsLayerScope.layoutDirection;
        this.density = reusableGraphicsLayerScope.graphicsDensity;
        int i3 = i2 & 4096;
        if (i3 != 0) {
            this.transformOrigin = reusableGraphicsLayerScope.transformOrigin;
        }
        if ((i2 & 1) != 0) {
            GraphicsLayer graphicsLayer = this.graphicsLayer;
            float f = reusableGraphicsLayerScope.scaleX;
            GraphicsLayerV29 graphicsLayerV29 = graphicsLayer.impl;
            if (graphicsLayerV29.scaleX != f) {
                graphicsLayerV29.scaleX = f;
                graphicsLayerV29.renderNode.setScaleX(f);
            }
        }
        if ((i2 & 2) != 0) {
            GraphicsLayer graphicsLayer2 = this.graphicsLayer;
            float f2 = reusableGraphicsLayerScope.scaleY;
            GraphicsLayerV29 graphicsLayerV292 = graphicsLayer2.impl;
            if (graphicsLayerV292.scaleY != f2) {
                graphicsLayerV292.scaleY = f2;
                graphicsLayerV292.renderNode.setScaleY(f2);
            }
        }
        if ((i2 & 4) != 0) {
            this.graphicsLayer.setAlpha(reusableGraphicsLayerScope.alpha);
        }
        if ((i2 & 8) != 0) {
            GraphicsLayer graphicsLayer3 = this.graphicsLayer;
            float f3 = reusableGraphicsLayerScope.translationX;
            GraphicsLayerV29 graphicsLayerV293 = graphicsLayer3.impl;
            if (graphicsLayerV293.translationX != f3) {
                graphicsLayerV293.translationX = f3;
                graphicsLayerV293.renderNode.setTranslationX(f3);
            }
        }
        if ((i2 & 16) != 0) {
            GraphicsLayer graphicsLayer4 = this.graphicsLayer;
            float f4 = reusableGraphicsLayerScope.translationY;
            GraphicsLayerV29 graphicsLayerV294 = graphicsLayer4.impl;
            if (graphicsLayerV294.translationY != f4) {
                graphicsLayerV294.translationY = f4;
                graphicsLayerV294.renderNode.setTranslationY(f4);
            }
        }
        boolean z = false;
        if ((i2 & 32) != 0) {
            GraphicsLayer graphicsLayer5 = this.graphicsLayer;
            float f5 = reusableGraphicsLayerScope.shadowElevation;
            GraphicsLayerV29 graphicsLayerV295 = graphicsLayer5.impl;
            if (graphicsLayerV295.shadowElevation != f5) {
                graphicsLayerV295.shadowElevation = f5;
                graphicsLayerV295.renderNode.setElevation(f5);
                graphicsLayerV295.setClip(graphicsLayerV295.clip || f5 > 0.0f);
                graphicsLayer5.outlineDirty = true;
                graphicsLayer5.configureOutline();
            }
            if (reusableGraphicsLayerScope.shadowElevation > 0.0f && !this.drawnWithEnabledZ && (function0 = this.invalidateParentLayer) != null) {
                function0.invoke();
            }
        }
        if ((i2 & 64) != 0) {
            GraphicsLayer graphicsLayer6 = this.graphicsLayer;
            long j = reusableGraphicsLayerScope.ambientShadowColor;
            GraphicsLayerV29 graphicsLayerV296 = graphicsLayer6.impl;
            if (!Color.m363equalsimpl0(j, graphicsLayerV296.ambientShadowColor)) {
                graphicsLayerV296.ambientShadowColor = j;
                graphicsLayerV296.renderNode.setAmbientShadowColor(ColorKt.m373toArgb8_81llA(j));
            }
        }
        if ((i2 & 128) != 0) {
            GraphicsLayer graphicsLayer7 = this.graphicsLayer;
            long j2 = reusableGraphicsLayerScope.spotShadowColor;
            GraphicsLayerV29 graphicsLayerV297 = graphicsLayer7.impl;
            if (!Color.m363equalsimpl0(j2, graphicsLayerV297.spotShadowColor)) {
                graphicsLayerV297.spotShadowColor = j2;
                graphicsLayerV297.renderNode.setSpotShadowColor(ColorKt.m373toArgb8_81llA(j2));
            }
        }
        if ((i2 & 1024) != 0) {
            GraphicsLayer graphicsLayer8 = this.graphicsLayer;
            float f6 = reusableGraphicsLayerScope.rotationZ;
            GraphicsLayerV29 graphicsLayerV298 = graphicsLayer8.impl;
            if (graphicsLayerV298.rotationZ != f6) {
                graphicsLayerV298.rotationZ = f6;
                graphicsLayerV298.renderNode.setRotationZ(f6);
            }
        }
        if ((i2 & 256) != 0) {
            GraphicsLayerV29 graphicsLayerV299 = this.graphicsLayer.impl;
        }
        if ((i2 & 512) != 0) {
            GraphicsLayerV29 graphicsLayerV2910 = this.graphicsLayer.impl;
        }
        if ((i2 & 2048) != 0) {
            GraphicsLayer graphicsLayer9 = this.graphicsLayer;
            float f7 = reusableGraphicsLayerScope.cameraDistance;
            GraphicsLayerV29 graphicsLayerV2911 = graphicsLayer9.impl;
            if (graphicsLayerV2911.cameraDistance != f7) {
                graphicsLayerV2911.cameraDistance = f7;
                graphicsLayerV2911.renderNode.setCameraDistance(f7);
            }
        }
        if (i3 != 0) {
            if (TransformOrigin.m398equalsimpl0(this.transformOrigin, TransformOrigin.Center)) {
                GraphicsLayer graphicsLayer10 = this.graphicsLayer;
                if (!Offset.m310equalsimpl0(graphicsLayer10.pivotOffset, 9205357640488583168L)) {
                    graphicsLayer10.pivotOffset = 9205357640488583168L;
                    graphicsLayer10.impl.renderNode.resetPivot();
                }
            } else {
                GraphicsLayer graphicsLayer11 = this.graphicsLayer;
                long floatToRawIntBits = (Float.floatToRawIntBits(TransformOrigin.m400getPivotFractionYimpl(this.transformOrigin) * ((int) (this.size & 4294967295L))) & 4294967295L) | (Float.floatToRawIntBits(TransformOrigin.m399getPivotFractionXimpl(this.transformOrigin) * ((int) (this.size >> 32))) << 32);
                if (!Offset.m310equalsimpl0(graphicsLayer11.pivotOffset, floatToRawIntBits)) {
                    graphicsLayer11.pivotOffset = floatToRawIntBits;
                    GraphicsLayerV29 graphicsLayerV2912 = graphicsLayer11.impl;
                    if ((9223372034707292159L & floatToRawIntBits) == 9205357640488583168L) {
                        graphicsLayerV2912.renderNode.resetPivot();
                    } else {
                        graphicsLayerV2912.renderNode.setPivotX(Float.intBitsToFloat((int) (floatToRawIntBits >> 32)));
                        graphicsLayerV2912.renderNode.setPivotY(Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L)));
                    }
                }
            }
        }
        if ((i2 & 16384) != 0) {
            GraphicsLayer graphicsLayer12 = this.graphicsLayer;
            boolean z2 = reusableGraphicsLayerScope.clip;
            GraphicsLayerV29 graphicsLayerV2913 = graphicsLayer12.impl;
            if (graphicsLayerV2913.clip != z2) {
                graphicsLayerV2913.setClip(z2);
                graphicsLayer12.outlineDirty = true;
                graphicsLayer12.configureOutline();
            }
        }
        if ((131072 & i2) != 0) {
            GraphicsLayer graphicsLayer13 = this.graphicsLayer;
            RenderEffect renderEffect = reusableGraphicsLayerScope.renderEffect;
            GraphicsLayerV29 graphicsLayerV2914 = graphicsLayer13.impl;
            if (!Intrinsics.areEqual(graphicsLayerV2914.renderEffect, renderEffect)) {
                graphicsLayerV2914.renderEffect = renderEffect;
                graphicsLayerV2914.renderNode.setRenderEffect(null);
            }
        }
        if ((32768 & i2) != 0) {
            GraphicsLayer graphicsLayer14 = this.graphicsLayer;
            int i4 = reusableGraphicsLayerScope.compositingStrategy;
            if (CompositingStrategy.m374equalsimpl0(i4, 0)) {
                i = 0;
            } else if (CompositingStrategy.m374equalsimpl0(i4, 1)) {
                i = 1;
            } else {
                i = 2;
                if (!CompositingStrategy.m374equalsimpl0(i4, 2)) {
                    throw new IllegalStateException("Not supported composition strategy");
                }
            }
            GraphicsLayerV29 graphicsLayerV2915 = graphicsLayer14.impl;
            if (!androidx.compose.ui.graphics.layer.CompositingStrategy.m433equalsimpl0(graphicsLayerV2915.compositingStrategy, i)) {
                graphicsLayerV2915.compositingStrategy = i;
                if (androidx.compose.ui.graphics.layer.CompositingStrategy.m433equalsimpl0(i, 1) || !BlendMode.m357equalsimpl0(graphicsLayerV2915.blendMode, 3)) {
                    GraphicsLayerV29.m435applyCompositingStrategyZ1X6vPc(graphicsLayerV2915.renderNode, 1);
                } else {
                    RenderEffect renderEffect2 = graphicsLayerV2915.renderEffect;
                    GraphicsLayerV29.m435applyCompositingStrategyZ1X6vPc(graphicsLayerV2915.renderNode, graphicsLayerV2915.compositingStrategy);
                }
            }
        }
        if ((i2 & 7963) != 0) {
            this.isMatrixDirty = true;
            this.isInverseMatrixDirty = true;
        }
        if (!Intrinsics.areEqual(this.outline, reusableGraphicsLayerScope.outline)) {
            Outline outline = reusableGraphicsLayerScope.outline;
            this.outline = outline;
            if (outline != null) {
                GraphicsLayer graphicsLayer15 = this.graphicsLayer;
                if (outline instanceof Outline.Rectangle) {
                    Rect rect = ((Outline.Rectangle) outline).rect;
                    long floatToRawIntBits2 = Float.floatToRawIntBits(rect.left);
                    float f8 = rect.top;
                    graphicsLayer15.m434setRoundRectOutlineTNW_H78(0.0f, (floatToRawIntBits2 << 32) | (Float.floatToRawIntBits(f8) & 4294967295L), (Float.floatToRawIntBits(rect.right - r6) << 32) | (Float.floatToRawIntBits(rect.bottom - f8) & 4294967295L));
                } else if (outline instanceof Outline.Generic) {
                    graphicsLayer15.internalOutline = null;
                    graphicsLayer15.roundRectOutlineSize = 9205357640488583168L;
                    graphicsLayer15.roundRectOutlineTopLeft = 0L;
                    graphicsLayer15.roundRectCornerRadius = 0.0f;
                    graphicsLayer15.outlineDirty = true;
                    graphicsLayer15.usePathForClip = false;
                    graphicsLayer15.outlinePath = ((Outline.Generic) outline).path;
                    graphicsLayer15.configureOutline();
                } else if (outline instanceof Outline.Rounded) {
                    Outline.Rounded rounded = (Outline.Rounded) outline;
                    AndroidPath androidPath = rounded.roundRectPath;
                    if (androidPath != null) {
                        graphicsLayer15.internalOutline = null;
                        graphicsLayer15.roundRectOutlineSize = 9205357640488583168L;
                        graphicsLayer15.roundRectOutlineTopLeft = 0L;
                        graphicsLayer15.roundRectCornerRadius = 0.0f;
                        graphicsLayer15.outlineDirty = true;
                        graphicsLayer15.usePathForClip = false;
                        graphicsLayer15.outlinePath = androidPath;
                        graphicsLayer15.configureOutline();
                    } else {
                        graphicsLayer15.m434setRoundRectOutlineTNW_H78(Float.intBitsToFloat((int) (rounded.roundRect.bottomLeftCornerRadius >> 32)), (Float.floatToRawIntBits(r5.left) << 32) | (Float.floatToRawIntBits(r5.top) & 4294967295L), (Float.floatToRawIntBits(r5.getWidth()) << 32) | (4294967295L & Float.floatToRawIntBits(r5.getHeight())));
                    }
                }
                boolean z3 = outline instanceof Outline.Generic;
            }
            z = true;
        }
        this.mutatedFields = reusableGraphicsLayerScope.mutatedFields;
        if ((i2 != 0 || z) && (parent = (androidComposeView = this.ownerView).getParent()) != null) {
            parent.onDescendantInvalidated(androidComposeView, androidComposeView);
        }
    }
}
