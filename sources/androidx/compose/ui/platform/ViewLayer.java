package androidx.compose.ui.platform;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.View;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.RectangleShapeKt$RectangleShape$1;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.node.OwnedLayer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewLayer extends View implements OwnedLayer {
    public static boolean hasRetrievedMethod;
    public static Field recreateDisplayList;
    public static boolean shouldUseDispatchDraw;
    public static Method updateDisplayListIfDirtyMethod;
    public final CanvasHolder canvasHolder;
    public Rect clipBoundsCache;
    public boolean clipToBounds;
    public final DrawChildContainer container;
    public Function2 drawBlock;
    public boolean drawnWithZ;
    public Function0 invalidateParentLayer;
    public boolean isInvalidated;
    public boolean mHasOverlappingRendering;
    public long mTransformOrigin;
    public final LayerMatrixCache matrixCache;
    public int mutatedFields;
    public final OutlineResolver outlineResolver;
    public final AndroidComposeView ownerView;
    public static final Function2 getMatrix = new Function2() { // from class: androidx.compose.ui.platform.ViewLayer$Companion$getMatrix$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((Matrix) obj2).set(((View) obj).getMatrix());
            return Unit.INSTANCE;
        }
    };
    public static final ViewLayer$Companion$OutlineProvider$1 OutlineProvider = new ViewLayer$Companion$OutlineProvider$1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static void updateDisplayList(View view) {
            try {
                if (!ViewLayer.hasRetrievedMethod) {
                    ViewLayer.hasRetrievedMethod = true;
                    ViewLayer.updateDisplayListIfDirtyMethod = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass()).invoke(View.class, "updateDisplayListIfDirty", new Class[0]);
                    ViewLayer.recreateDisplayList = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(View.class, "mRecreateDisplayList");
                    Method method = ViewLayer.updateDisplayListIfDirtyMethod;
                    if (method != null) {
                        method.setAccessible(true);
                    }
                    Field field = ViewLayer.recreateDisplayList;
                    if (field != null) {
                        field.setAccessible(true);
                    }
                }
                Field field2 = ViewLayer.recreateDisplayList;
                if (field2 != null) {
                    field2.setBoolean(view, true);
                }
                Method method2 = ViewLayer.updateDisplayListIfDirtyMethod;
                if (method2 != null) {
                    method2.invoke(view, null);
                }
            } catch (Throwable unused) {
                ViewLayer.shouldUseDispatchDraw = true;
            }
        }
    }

    public ViewLayer(AndroidComposeView androidComposeView, DrawChildContainer drawChildContainer, Function2 function2, Function0 function0) {
        super(androidComposeView.getContext());
        this.ownerView = androidComposeView;
        this.container = drawChildContainer;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
        this.outlineResolver = new OutlineResolver();
        this.canvasHolder = new CanvasHolder();
        this.matrixCache = new LayerMatrixCache(getMatrix);
        this.mTransformOrigin = TransformOrigin.Center;
        this.mHasOverlappingRendering = true;
        setWillNotDraw(false);
        drawChildContainer.addView(this);
        View.generateViewId();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void destroy() {
        setInvalidated(false);
        AndroidComposeView androidComposeView = this.ownerView;
        androidComposeView.observationClearRequested = true;
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        androidComposeView.recycle$ui_release(this);
        this.container.removeViewInLayout(this);
    }

    @Override // android.view.View
    public final void dispatchDraw(Canvas canvas) {
        boolean z;
        CanvasHolder canvasHolder = this.canvasHolder;
        AndroidCanvas androidCanvas = canvasHolder.androidCanvas;
        Canvas canvas2 = androidCanvas.internalCanvas;
        androidCanvas.internalCanvas = canvas;
        if (getManualClipPath() == null && canvas.isHardwareAccelerated()) {
            z = false;
        } else {
            androidCanvas.save();
            this.outlineResolver.clipToOutline(androidCanvas);
            z = true;
        }
        Function2 function2 = this.drawBlock;
        if (function2 != null) {
            function2.invoke(androidCanvas, null);
        }
        if (z) {
            androidCanvas.restore();
        }
        canvasHolder.androidCanvas.internalCanvas = canvas2;
        setInvalidated(false);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void drawLayer(androidx.compose.ui.graphics.Canvas canvas, GraphicsLayer graphicsLayer) {
        boolean z = getElevation() > 0.0f;
        this.drawnWithZ = z;
        if (z) {
            canvas.enableZ();
        }
        this.container.drawChild$ui_release(canvas, this, getDrawingTime());
        if (this.drawnWithZ) {
            canvas.disableZ();
        }
    }

    public final Path getManualClipPath() {
        if (getClipToOutline()) {
            OutlineResolver outlineResolver = this.outlineResolver;
            if (outlineResolver.usePathForClip) {
                outlineResolver.updateCache();
                return outlineResolver.outlinePath;
            }
        }
        return null;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: getUnderlyingMatrix-sQKQjiQ */
    public final float[] mo543getUnderlyingMatrixsQKQjiQ() {
        return this.matrixCache.m567calculateMatrixGrdbGEg(this);
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return this.mHasOverlappingRendering;
    }

    @Override // android.view.View, androidx.compose.ui.node.OwnedLayer
    public final void invalidate() {
        if (this.isInvalidated) {
            return;
        }
        setInvalidated(true);
        super.invalidate();
        this.ownerView.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: inverseTransform-58bKbWc */
    public final void mo544inverseTransform58bKbWc(float[] fArr) {
        float[] m566calculateInverseMatrixbWbORWo = this.matrixCache.m566calculateInverseMatrixbWbORWo(this);
        if (m566calculateInverseMatrixbWbORWo != null) {
            androidx.compose.ui.graphics.Matrix.m383timesAssign58bKbWc(fArr, m566calculateInverseMatrixbWbORWo);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: isInLayer-k-4lQ0M */
    public final boolean mo545isInLayerk4lQ0M(long j) {
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (4294967295L & j));
        if (this.clipToBounds) {
            return 0.0f <= intBitsToFloat && intBitsToFloat < ((float) getWidth()) && 0.0f <= intBitsToFloat2 && intBitsToFloat2 < ((float) getHeight());
        }
        if (getClipToOutline()) {
            return this.outlineResolver.m570isInOutlinek4lQ0M(j);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void mapBounds(MutableRect mutableRect, boolean z) {
        if (!z) {
            LayerMatrixCache layerMatrixCache = this.matrixCache;
            float[] m567calculateMatrixGrdbGEg = layerMatrixCache.m567calculateMatrixGrdbGEg(this);
            if (layerMatrixCache.isIdentity) {
                return;
            }
            androidx.compose.ui.graphics.Matrix.m381mapimpl(m567calculateMatrixGrdbGEg, mutableRect);
            return;
        }
        LayerMatrixCache layerMatrixCache2 = this.matrixCache;
        float[] m566calculateInverseMatrixbWbORWo = layerMatrixCache2.m566calculateInverseMatrixbWbORWo(this);
        if (m566calculateInverseMatrixbWbORWo != null) {
            if (layerMatrixCache2.isIdentity) {
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
        if (!z) {
            LayerMatrixCache layerMatrixCache = this.matrixCache;
            return !layerMatrixCache.isIdentity ? androidx.compose.ui.graphics.Matrix.m380mapMKHz9U(j, layerMatrixCache.m567calculateMatrixGrdbGEg(this)) : j;
        }
        LayerMatrixCache layerMatrixCache2 = this.matrixCache;
        float[] m566calculateInverseMatrixbWbORWo = layerMatrixCache2.m566calculateInverseMatrixbWbORWo(this);
        if (m566calculateInverseMatrixbWbORWo == null) {
            return 9187343241974906880L;
        }
        return !layerMatrixCache2.isIdentity ? androidx.compose.ui.graphics.Matrix.m380mapMKHz9U(j, m566calculateInverseMatrixbWbORWo) : j;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: move--gyyYBs */
    public final void mo547movegyyYBs(long j) {
        int i = (int) (j >> 32);
        if (i != getLeft()) {
            offsetLeftAndRight(i - getLeft());
            this.matrixCache.invalidate();
        }
        int i2 = (int) (j & 4294967295L);
        if (i2 != getTop()) {
            offsetTopAndBottom(i2 - getTop());
            this.matrixCache.invalidate();
        }
    }

    public final void resetClipBounds() {
        Rect rect;
        if (this.clipToBounds) {
            Rect rect2 = this.clipBoundsCache;
            if (rect2 == null) {
                this.clipBoundsCache = new Rect(0, 0, getWidth(), getHeight());
            } else {
                Intrinsics.checkNotNull(rect2);
                rect2.set(0, 0, getWidth(), getHeight());
            }
            rect = this.clipBoundsCache;
        } else {
            rect = null;
        }
        setClipBounds(rect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: resize-ozmzZPI */
    public final void mo548resizeozmzZPI(long j) {
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        if (i == getWidth() && i2 == getHeight()) {
            return;
        }
        setPivotX(TransformOrigin.m399getPivotFractionXimpl(this.mTransformOrigin) * i);
        setPivotY(TransformOrigin.m400getPivotFractionYimpl(this.mTransformOrigin) * i2);
        setOutlineProvider(this.outlineResolver.getAndroidOutline() != null ? OutlineProvider : null);
        layout(getLeft(), getTop(), getLeft() + i, getTop() + i2);
        resetClipBounds();
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void reuseLayer(Function2 function2, Function0 function0) {
        this.container.addView(this);
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        layerMatrixCache.isDirty = false;
        layerMatrixCache.isInverseDirty = false;
        layerMatrixCache.isIdentity = true;
        layerMatrixCache.isInverseValid = true;
        androidx.compose.ui.graphics.Matrix.m382resetimpl(layerMatrixCache.matrixCache);
        androidx.compose.ui.graphics.Matrix.m382resetimpl(layerMatrixCache.inverseMatrixCache);
        this.clipToBounds = false;
        this.drawnWithZ = false;
        this.mTransformOrigin = TransformOrigin.Center;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
    }

    public final void setInvalidated(boolean z) {
        if (z != this.isInvalidated) {
            this.isInvalidated = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: transform-58bKbWc */
    public final void mo549transform58bKbWc(float[] fArr) {
        androidx.compose.ui.graphics.Matrix.m383timesAssign58bKbWc(fArr, this.matrixCache.m567calculateMatrixGrdbGEg(this));
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void updateDisplayList() {
        if (!this.isInvalidated || shouldUseDispatchDraw) {
            return;
        }
        Companion.updateDisplayList(this);
        setInvalidated(false);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void updateLayerProperties(ReusableGraphicsLayerScope reusableGraphicsLayerScope) {
        Function0 function0;
        int i = reusableGraphicsLayerScope.mutatedFields | this.mutatedFields;
        if ((i & 4096) != 0) {
            long j = reusableGraphicsLayerScope.transformOrigin;
            this.mTransformOrigin = j;
            setPivotX(TransformOrigin.m399getPivotFractionXimpl(j) * getWidth());
            setPivotY(TransformOrigin.m400getPivotFractionYimpl(this.mTransformOrigin) * getHeight());
        }
        if ((i & 1) != 0) {
            setScaleX(reusableGraphicsLayerScope.scaleX);
        }
        if ((i & 2) != 0) {
            setScaleY(reusableGraphicsLayerScope.scaleY);
        }
        if ((i & 4) != 0) {
            setAlpha(reusableGraphicsLayerScope.alpha);
        }
        if ((i & 8) != 0) {
            setTranslationX(reusableGraphicsLayerScope.translationX);
        }
        if ((i & 16) != 0) {
            setTranslationY(reusableGraphicsLayerScope.translationY);
        }
        if ((i & 32) != 0) {
            setElevation(reusableGraphicsLayerScope.shadowElevation);
        }
        if ((i & 1024) != 0) {
            setRotation(reusableGraphicsLayerScope.rotationZ);
        }
        if ((i & 256) != 0) {
            setRotationX(0.0f);
        }
        if ((i & 512) != 0) {
            setRotationY(0.0f);
        }
        if ((i & 2048) != 0) {
            setCameraDistance(reusableGraphicsLayerScope.cameraDistance * getResources().getDisplayMetrics().densityDpi);
        }
        boolean z = true;
        boolean z2 = getManualClipPath() != null;
        boolean z3 = reusableGraphicsLayerScope.clip;
        RectangleShapeKt$RectangleShape$1 rectangleShapeKt$RectangleShape$1 = RectangleShapeKt.RectangleShape;
        boolean z4 = z3 && reusableGraphicsLayerScope.shape != rectangleShapeKt$RectangleShape$1;
        if ((i & 24576) != 0) {
            this.clipToBounds = z3 && reusableGraphicsLayerScope.shape == rectangleShapeKt$RectangleShape$1;
            resetClipBounds();
            setClipToOutline(z4);
        }
        boolean m571updateS_szKao = this.outlineResolver.m571updateS_szKao(reusableGraphicsLayerScope.outline, reusableGraphicsLayerScope.alpha, z4, reusableGraphicsLayerScope.shadowElevation, reusableGraphicsLayerScope.size);
        OutlineResolver outlineResolver = this.outlineResolver;
        if (outlineResolver.cacheIsDirty) {
            setOutlineProvider(outlineResolver.getAndroidOutline() != null ? OutlineProvider : null);
        }
        boolean z5 = getManualClipPath() != null;
        if (z2 != z5 || (z5 && m571updateS_szKao)) {
            invalidate();
        }
        if (!this.drawnWithZ && getElevation() > 0.0f && (function0 = this.invalidateParentLayer) != null) {
            function0.invoke();
        }
        if ((i & 7963) != 0) {
            this.matrixCache.invalidate();
        }
        if ((i & 64) != 0) {
            setOutlineAmbientShadowColor(ColorKt.m373toArgb8_81llA(reusableGraphicsLayerScope.ambientShadowColor));
        }
        if ((i & 128) != 0) {
            setOutlineSpotShadowColor(ColorKt.m373toArgb8_81llA(reusableGraphicsLayerScope.spotShadowColor));
        }
        if ((131072 & i) != 0) {
            RenderEffect renderEffect = reusableGraphicsLayerScope.renderEffect;
            setRenderEffect(null);
        }
        if ((i & 32768) != 0) {
            int i2 = reusableGraphicsLayerScope.compositingStrategy;
            if (CompositingStrategy.m374equalsimpl0(i2, 1)) {
                setLayerType(2, null);
            } else if (CompositingStrategy.m374equalsimpl0(i2, 2)) {
                setLayerType(0, null);
                z = false;
            } else {
                setLayerType(0, null);
            }
            this.mHasOverlappingRendering = z;
        }
        this.mutatedFields = reusableGraphicsLayerScope.mutatedFields;
    }

    @Override // android.view.View
    public final void forceLayout() {
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }
}
