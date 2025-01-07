package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.graphics.RenderNode;
import android.view.ViewParent;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.RectangleShapeKt$RectangleShape$1;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.node.OwnedLayer;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RenderNodeLayer implements OwnedLayer {
    public static final Function2 getMatrix = new Function2() { // from class: androidx.compose.ui.platform.RenderNodeLayer$Companion$getMatrix$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((RenderNodeApi29) ((DeviceRenderNode) obj)).renderNode.getMatrix((Matrix) obj2);
            return Unit.INSTANCE;
        }
    };
    public Function2 drawBlock;
    public boolean drawnWithZ;
    public Function0 invalidateParentLayer;
    public boolean isDestroyed;
    public boolean isDirty;
    public int mutatedFields;
    public final AndroidComposeView ownerView;
    public final RenderNodeApi29 renderNode;
    public AndroidPaint softwareLayerPaint;
    public final OutlineResolver outlineResolver = new OutlineResolver();
    public final LayerMatrixCache matrixCache = new LayerMatrixCache(getMatrix);
    public final CanvasHolder canvasHolder = new CanvasHolder();
    public long transformOrigin = TransformOrigin.Center;

    public RenderNodeLayer(AndroidComposeView androidComposeView, Function2 function2, Function0 function0) {
        this.ownerView = androidComposeView;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
        RenderNodeApi29 renderNodeApi29 = new RenderNodeApi29();
        renderNodeApi29.renderNode.setHasOverlappingRendering(true);
        renderNodeApi29.renderNode.setClipToBounds(false);
        this.renderNode = renderNodeApi29;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void destroy() {
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        if (renderNodeApi29.renderNode.hasDisplayList()) {
            renderNodeApi29.renderNode.discardDisplayList();
        }
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.isDestroyed = true;
        setDirty$1(false);
        AndroidComposeView androidComposeView = this.ownerView;
        androidComposeView.observationClearRequested = true;
        androidComposeView.recycle$ui_release(this);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void drawLayer(Canvas canvas, GraphicsLayer graphicsLayer) {
        android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
        android.graphics.Canvas canvas3 = ((AndroidCanvas) canvas).internalCanvas;
        boolean isHardwareAccelerated = canvas3.isHardwareAccelerated();
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        if (isHardwareAccelerated) {
            updateDisplayList();
            boolean z = renderNodeApi29.renderNode.getElevation() > 0.0f;
            this.drawnWithZ = z;
            if (z) {
                canvas.enableZ();
            }
            canvas3.drawRenderNode(renderNodeApi29.renderNode);
            if (this.drawnWithZ) {
                canvas.disableZ();
                return;
            }
            return;
        }
        float left = renderNodeApi29.renderNode.getLeft();
        float top = renderNodeApi29.renderNode.getTop();
        float right = renderNodeApi29.renderNode.getRight();
        float bottom = renderNodeApi29.renderNode.getBottom();
        if (renderNodeApi29.renderNode.getAlpha() < 1.0f) {
            AndroidPaint androidPaint = this.softwareLayerPaint;
            if (androidPaint == null) {
                androidPaint = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = androidPaint;
            }
            androidPaint.setAlpha(renderNodeApi29.renderNode.getAlpha());
            canvas3.saveLayer(left, top, right, bottom, androidPaint.internalPaint);
        } else {
            canvas.save();
        }
        canvas.translate(left, top);
        canvas.mo336concat58bKbWc(this.matrixCache.m567calculateMatrixGrdbGEg(renderNodeApi29));
        if (renderNodeApi29.renderNode.getClipToOutline() || renderNodeApi29.renderNode.getClipToBounds()) {
            this.outlineResolver.clipToOutline(canvas);
        }
        Function2 function2 = this.drawBlock;
        if (function2 != null) {
            function2.invoke(canvas, null);
        }
        canvas.restore();
        setDirty$1(false);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: getUnderlyingMatrix-sQKQjiQ */
    public final float[] mo543getUnderlyingMatrixsQKQjiQ() {
        return this.matrixCache.m567calculateMatrixGrdbGEg(this.renderNode);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void invalidate() {
        if (this.isDirty || this.isDestroyed) {
            return;
        }
        this.ownerView.invalidate();
        setDirty$1(true);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: inverseTransform-58bKbWc */
    public final void mo544inverseTransform58bKbWc(float[] fArr) {
        float[] m566calculateInverseMatrixbWbORWo = this.matrixCache.m566calculateInverseMatrixbWbORWo(this.renderNode);
        if (m566calculateInverseMatrixbWbORWo != null) {
            androidx.compose.ui.graphics.Matrix.m383timesAssign58bKbWc(fArr, m566calculateInverseMatrixbWbORWo);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: isInLayer-k-4lQ0M */
    public final boolean mo545isInLayerk4lQ0M(long j) {
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (4294967295L & j));
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        if (renderNodeApi29.renderNode.getClipToBounds()) {
            return 0.0f <= intBitsToFloat && intBitsToFloat < ((float) renderNodeApi29.renderNode.getWidth()) && 0.0f <= intBitsToFloat2 && intBitsToFloat2 < ((float) renderNodeApi29.renderNode.getHeight());
        }
        if (renderNodeApi29.renderNode.getClipToOutline()) {
            return this.outlineResolver.m570isInOutlinek4lQ0M(j);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void mapBounds(MutableRect mutableRect, boolean z) {
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        if (!z) {
            float[] m567calculateMatrixGrdbGEg = layerMatrixCache.m567calculateMatrixGrdbGEg(renderNodeApi29);
            if (layerMatrixCache.isIdentity) {
                return;
            }
            androidx.compose.ui.graphics.Matrix.m381mapimpl(m567calculateMatrixGrdbGEg, mutableRect);
            return;
        }
        float[] m566calculateInverseMatrixbWbORWo = layerMatrixCache.m566calculateInverseMatrixbWbORWo(renderNodeApi29);
        if (m566calculateInverseMatrixbWbORWo != null) {
            if (layerMatrixCache.isIdentity) {
                return;
            }
            androidx.compose.ui.graphics.Matrix.m381mapimpl(m566calculateInverseMatrixbWbORWo, mutableRect);
        } else {
            mutableRect.left = 0.0f;
            mutableRect.top = 0.0f;
            mutableRect.right = 0.0f;
            mutableRect.bottom = 0.0f;
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: mapOffset-8S9VItk */
    public final long mo546mapOffset8S9VItk(long j, boolean z) {
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        if (!z) {
            return !layerMatrixCache.isIdentity ? androidx.compose.ui.graphics.Matrix.m380mapMKHz9U(j, layerMatrixCache.m567calculateMatrixGrdbGEg(renderNodeApi29)) : j;
        }
        float[] m566calculateInverseMatrixbWbORWo = layerMatrixCache.m566calculateInverseMatrixbWbORWo(renderNodeApi29);
        if (m566calculateInverseMatrixbWbORWo == null) {
            return 9187343241974906880L;
        }
        return !layerMatrixCache.isIdentity ? androidx.compose.ui.graphics.Matrix.m380mapMKHz9U(j, m566calculateInverseMatrixbWbORWo) : j;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: move--gyyYBs */
    public final void mo547movegyyYBs(long j) {
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        int left = renderNodeApi29.renderNode.getLeft();
        int top = renderNodeApi29.renderNode.getTop();
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        if (left == i && top == i2) {
            return;
        }
        if (left != i) {
            renderNodeApi29.renderNode.offsetLeftAndRight(i - left);
        }
        if (top != i2) {
            renderNodeApi29.renderNode.offsetTopAndBottom(i2 - top);
        }
        AndroidComposeView androidComposeView = this.ownerView;
        ViewParent parent = androidComposeView.getParent();
        if (parent != null) {
            parent.onDescendantInvalidated(androidComposeView, androidComposeView);
        }
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: resize-ozmzZPI */
    public final void mo548resizeozmzZPI(long j) {
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        float m399getPivotFractionXimpl = TransformOrigin.m399getPivotFractionXimpl(this.transformOrigin) * i;
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        renderNodeApi29.renderNode.setPivotX(m399getPivotFractionXimpl);
        renderNodeApi29.renderNode.setPivotY(TransformOrigin.m400getPivotFractionYimpl(this.transformOrigin) * i2);
        if (renderNodeApi29.renderNode.setPosition(renderNodeApi29.renderNode.getLeft(), renderNodeApi29.renderNode.getTop(), renderNodeApi29.renderNode.getLeft() + i, renderNodeApi29.renderNode.getTop() + i2)) {
            renderNodeApi29.renderNode.setOutline(this.outlineResolver.getAndroidOutline());
            if (!this.isDirty && !this.isDestroyed) {
                this.ownerView.invalidate();
                setDirty$1(true);
            }
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void reuseLayer(Function2 function2, Function0 function0) {
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        layerMatrixCache.isDirty = false;
        layerMatrixCache.isInverseDirty = false;
        layerMatrixCache.isIdentity = true;
        layerMatrixCache.isInverseValid = true;
        androidx.compose.ui.graphics.Matrix.m382resetimpl(layerMatrixCache.matrixCache);
        androidx.compose.ui.graphics.Matrix.m382resetimpl(layerMatrixCache.inverseMatrixCache);
        setDirty$1(false);
        this.isDestroyed = false;
        this.drawnWithZ = false;
        this.transformOrigin = TransformOrigin.Center;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
    }

    public final void setDirty$1(boolean z) {
        if (z != this.isDirty) {
            this.isDirty = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: transform-58bKbWc */
    public final void mo549transform58bKbWc(float[] fArr) {
        androidx.compose.ui.graphics.Matrix.m383timesAssign58bKbWc(fArr, this.matrixCache.m567calculateMatrixGrdbGEg(this.renderNode));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0027  */
    @Override // androidx.compose.ui.node.OwnedLayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateDisplayList() {
        /*
            r7 = this;
            boolean r0 = r7.isDirty
            androidx.compose.ui.platform.RenderNodeApi29 r1 = r7.renderNode
            if (r0 != 0) goto Le
            android.graphics.RenderNode r0 = r1.renderNode
            boolean r0 = r0.hasDisplayList()
            if (r0 != 0) goto L58
        Le:
            android.graphics.RenderNode r0 = r1.renderNode
            boolean r0 = r0.getClipToOutline()
            if (r0 == 0) goto L22
            androidx.compose.ui.platform.OutlineResolver r0 = r7.outlineResolver
            boolean r2 = r0.usePathForClip
            if (r2 == 0) goto L22
            r0.updateCache()
            androidx.compose.ui.graphics.Path r0 = r0.outlinePath
            goto L23
        L22:
            r0 = 0
        L23:
            kotlin.jvm.functions.Function2 r2 = r7.drawBlock
            if (r2 == 0) goto L54
            androidx.compose.ui.platform.RenderNodeLayer$updateDisplayList$1$1 r3 = new androidx.compose.ui.platform.RenderNodeLayer$updateDisplayList$1$1
            r3.<init>()
            android.graphics.RenderNode r2 = r1.renderNode
            android.graphics.RecordingCanvas r2 = r2.beginRecording()
            androidx.compose.ui.graphics.CanvasHolder r4 = r7.canvasHolder
            androidx.compose.ui.graphics.AndroidCanvas r5 = r4.androidCanvas
            android.graphics.Canvas r6 = r5.internalCanvas
            r5.internalCanvas = r2
            if (r0 == 0) goto L43
            r5.save()
            r2 = 1
            r5.mo334clipPathmtrdDE(r0, r2)
        L43:
            r3.invoke(r5)
            if (r0 == 0) goto L4b
            r5.restore()
        L4b:
            androidx.compose.ui.graphics.AndroidCanvas r0 = r4.androidCanvas
            r0.internalCanvas = r6
            android.graphics.RenderNode r0 = r1.renderNode
            r0.endRecording()
        L54:
            r0 = 0
            r7.setDirty$1(r0)
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.RenderNodeLayer.updateDisplayList():void");
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void updateLayerProperties(ReusableGraphicsLayerScope reusableGraphicsLayerScope) {
        Function0 function0;
        int i = reusableGraphicsLayerScope.mutatedFields | this.mutatedFields;
        int i2 = i & 4096;
        if (i2 != 0) {
            this.transformOrigin = reusableGraphicsLayerScope.transformOrigin;
        }
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        boolean clipToOutline = renderNodeApi29.renderNode.getClipToOutline();
        OutlineResolver outlineResolver = this.outlineResolver;
        boolean z = clipToOutline && outlineResolver.usePathForClip;
        if ((i & 1) != 0) {
            renderNodeApi29.renderNode.setScaleX(reusableGraphicsLayerScope.scaleX);
        }
        if ((i & 2) != 0) {
            renderNodeApi29.renderNode.setScaleY(reusableGraphicsLayerScope.scaleY);
        }
        if ((i & 4) != 0) {
            renderNodeApi29.renderNode.setAlpha(reusableGraphicsLayerScope.alpha);
        }
        if ((i & 8) != 0) {
            renderNodeApi29.renderNode.setTranslationX(reusableGraphicsLayerScope.translationX);
        }
        if ((i & 16) != 0) {
            renderNodeApi29.renderNode.setTranslationY(reusableGraphicsLayerScope.translationY);
        }
        if ((i & 32) != 0) {
            renderNodeApi29.renderNode.setElevation(reusableGraphicsLayerScope.shadowElevation);
        }
        if ((i & 64) != 0) {
            renderNodeApi29.renderNode.setAmbientShadowColor(ColorKt.m373toArgb8_81llA(reusableGraphicsLayerScope.ambientShadowColor));
        }
        if ((i & 128) != 0) {
            renderNodeApi29.renderNode.setSpotShadowColor(ColorKt.m373toArgb8_81llA(reusableGraphicsLayerScope.spotShadowColor));
        }
        if ((i & 1024) != 0) {
            renderNodeApi29.renderNode.setRotationZ(reusableGraphicsLayerScope.rotationZ);
        }
        if ((i & 256) != 0) {
            renderNodeApi29.renderNode.setRotationX(0.0f);
        }
        if ((i & 512) != 0) {
            renderNodeApi29.renderNode.setRotationY(0.0f);
        }
        if ((i & 2048) != 0) {
            renderNodeApi29.renderNode.setCameraDistance(reusableGraphicsLayerScope.cameraDistance);
        }
        if (i2 != 0) {
            renderNodeApi29.renderNode.setPivotX(TransformOrigin.m399getPivotFractionXimpl(this.transformOrigin) * renderNodeApi29.renderNode.getWidth());
            renderNodeApi29.renderNode.setPivotY(TransformOrigin.m400getPivotFractionYimpl(this.transformOrigin) * renderNodeApi29.renderNode.getHeight());
        }
        boolean z2 = reusableGraphicsLayerScope.clip;
        RectangleShapeKt$RectangleShape$1 rectangleShapeKt$RectangleShape$1 = RectangleShapeKt.RectangleShape;
        boolean z3 = z2 && reusableGraphicsLayerScope.shape != rectangleShapeKt$RectangleShape$1;
        if ((i & 24576) != 0) {
            renderNodeApi29.renderNode.setClipToOutline(z3);
            renderNodeApi29.renderNode.setClipToBounds(reusableGraphicsLayerScope.clip && reusableGraphicsLayerScope.shape == rectangleShapeKt$RectangleShape$1);
        }
        if ((131072 & i) != 0) {
            RenderEffect renderEffect = reusableGraphicsLayerScope.renderEffect;
            renderNodeApi29.renderNode.setRenderEffect(null);
        }
        if ((32768 & i) != 0) {
            int i3 = reusableGraphicsLayerScope.compositingStrategy;
            RenderNode renderNode = renderNodeApi29.renderNode;
            if (CompositingStrategy.m374equalsimpl0(i3, 1)) {
                renderNode.setUseCompositingLayer(true, null);
                renderNode.setHasOverlappingRendering(true);
            } else if (CompositingStrategy.m374equalsimpl0(i3, 2)) {
                renderNode.setUseCompositingLayer(false, null);
                renderNode.setHasOverlappingRendering(false);
            } else {
                renderNode.setUseCompositingLayer(false, null);
                renderNode.setHasOverlappingRendering(true);
            }
        }
        boolean m571updateS_szKao = this.outlineResolver.m571updateS_szKao(reusableGraphicsLayerScope.outline, reusableGraphicsLayerScope.alpha, z3, reusableGraphicsLayerScope.shadowElevation, reusableGraphicsLayerScope.size);
        if (outlineResolver.cacheIsDirty) {
            renderNodeApi29.renderNode.setOutline(outlineResolver.getAndroidOutline());
        }
        boolean z4 = z3 && outlineResolver.usePathForClip;
        AndroidComposeView androidComposeView = this.ownerView;
        if (z == z4 && (!z4 || !m571updateS_szKao)) {
            ViewParent parent = androidComposeView.getParent();
            if (parent != null) {
                parent.onDescendantInvalidated(androidComposeView, androidComposeView);
            }
        } else if (!this.isDirty && !this.isDestroyed) {
            androidComposeView.invalidate();
            setDirty$1(true);
        }
        if (!this.drawnWithZ && renderNodeApi29.renderNode.getElevation() > 0.0f && (function0 = this.invalidateParentLayer) != null) {
            function0.invoke();
        }
        if ((i & 7963) != 0) {
            this.matrixCache.invalidate();
        }
        this.mutatedFields = reusableGraphicsLayerScope.mutatedFields;
    }
}
