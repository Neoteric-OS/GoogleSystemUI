package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.RecordingCanvas;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.DrawContextKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GraphicsLayer {
    public Outline androidOutline;
    public final GraphicsLayerV29 impl;
    public androidx.compose.ui.graphics.Outline internalOutline;
    public boolean isReleased;
    public AndroidPath outlinePath;
    public int parentLayerUsages;
    public long pivotOffset;
    public AndroidPath roundRectClipPath;
    public float roundRectCornerRadius;
    public long size;
    public AndroidPaint softwareLayerPaint;
    public long topLeft;
    public boolean usePathForClip;
    public Density density = DrawContextKt.DefaultDensity;
    public LayoutDirection layoutDirection = LayoutDirection.Ltr;
    public Lambda drawBlock = new Function1() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$drawBlock$1
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Unit.INSTANCE;
        }
    };
    public boolean outlineDirty = true;
    public long roundRectOutlineTopLeft = 0;
    public long roundRectOutlineSize = 9205357640488583168L;
    public final ChildLayerDependenciesTracker childDependenciesTracker = new ChildLayerDependenciesTracker();

    static {
        int i = LayerManager.$r8$clinit;
        int i2 = LayerManager.$r8$clinit;
    }

    public GraphicsLayer(GraphicsLayerV29 graphicsLayerV29) {
        this.impl = graphicsLayerV29;
        graphicsLayerV29.setClip(false);
        this.topLeft = 0L;
        this.size = 0L;
        this.pivotOffset = 9205357640488583168L;
    }

    public final void configureOutline() {
        if (this.outlineDirty) {
            GraphicsLayerV29 graphicsLayerV29 = this.impl;
            if (graphicsLayerV29.clip || graphicsLayerV29.shadowElevation > 0.0f) {
                AndroidPath androidPath = this.outlinePath;
                if (androidPath != null) {
                    Outline outline = this.androidOutline;
                    if (outline == null) {
                        outline = new Outline();
                        this.androidOutline = outline;
                    }
                    outline.setPath(androidPath.internalPath);
                    this.usePathForClip = !outline.canClip();
                    this.outlinePath = androidPath;
                    outline.setAlpha(graphicsLayerV29.alpha);
                    graphicsLayerV29.renderNode.setOutline(outline);
                    graphicsLayerV29.outlineIsProvided = true;
                    graphicsLayerV29.applyClip$1();
                } else {
                    Outline outline2 = this.androidOutline;
                    if (outline2 == null) {
                        outline2 = new Outline();
                        this.androidOutline = outline2;
                    }
                    long m685toSizeozmzZPI = IntSizeKt.m685toSizeozmzZPI(this.size);
                    long j = this.roundRectOutlineTopLeft;
                    long j2 = this.roundRectOutlineSize;
                    if (j2 != 9205357640488583168L) {
                        m685toSizeozmzZPI = j2;
                    }
                    int i = (int) (j >> 32);
                    int i2 = (int) (j & 4294967295L);
                    outline2.setRoundRect(Math.round(Float.intBitsToFloat(i)), Math.round(Float.intBitsToFloat(i2)), Math.round(Float.intBitsToFloat((int) (m685toSizeozmzZPI >> 32)) + Float.intBitsToFloat(i)), Math.round(Float.intBitsToFloat((int) (m685toSizeozmzZPI & 4294967295L)) + Float.intBitsToFloat(i2)), this.roundRectCornerRadius);
                    outline2.setAlpha(graphicsLayerV29.alpha);
                    graphicsLayerV29.renderNode.setOutline(outline2);
                    graphicsLayerV29.outlineIsProvided = true;
                    graphicsLayerV29.applyClip$1();
                }
            } else {
                graphicsLayerV29.renderNode.setOutline(null);
                graphicsLayerV29.outlineIsProvided = false;
                graphicsLayerV29.applyClip$1();
            }
        }
        this.outlineDirty = false;
    }

    public final void discardContentIfReleasedAndHaveNoParentLayerUsages() {
        if (this.isReleased && this.parentLayerUsages == 0) {
            ChildLayerDependenciesTracker childLayerDependenciesTracker = this.childDependenciesTracker;
            GraphicsLayer graphicsLayer = childLayerDependenciesTracker.dependency;
            if (graphicsLayer != null) {
                graphicsLayer.onRemovedFromParentLayer();
                childLayerDependenciesTracker.dependency = null;
            }
            MutableScatterSet mutableScatterSet = childLayerDependenciesTracker.dependenciesSet;
            if (mutableScatterSet != null) {
                Object[] objArr = mutableScatterSet.elements;
                long[] jArr = mutableScatterSet.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i = 0;
                    while (true) {
                        long j = jArr[i];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i2 = 8 - ((~(i - length)) >>> 31);
                            for (int i3 = 0; i3 < i2; i3++) {
                                if ((255 & j) < 128) {
                                    ((GraphicsLayer) objArr[(i << 3) + i3]).onRemovedFromParentLayer();
                                }
                                j >>= 8;
                            }
                            if (i2 != 8) {
                                break;
                            }
                        }
                        if (i == length) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                mutableScatterSet.clear();
            }
            this.impl.renderNode.discardDisplayList();
        }
    }

    public final androidx.compose.ui.graphics.Outline getOutline() {
        androidx.compose.ui.graphics.Outline rectangle;
        androidx.compose.ui.graphics.Outline outline = this.internalOutline;
        AndroidPath androidPath = this.outlinePath;
        if (outline != null) {
            return outline;
        }
        if (androidPath != null) {
            Outline.Generic generic = new Outline.Generic(androidPath);
            this.internalOutline = generic;
            return generic;
        }
        long m685toSizeozmzZPI = IntSizeKt.m685toSizeozmzZPI(this.size);
        long j = this.roundRectOutlineTopLeft;
        long j2 = this.roundRectOutlineSize;
        if (j2 != 9205357640488583168L) {
            m685toSizeozmzZPI = j2;
        }
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
        float intBitsToFloat3 = Float.intBitsToFloat((int) (m685toSizeozmzZPI >> 32)) + intBitsToFloat;
        float intBitsToFloat4 = Float.intBitsToFloat((int) (m685toSizeozmzZPI & 4294967295L)) + intBitsToFloat2;
        if (this.roundRectCornerRadius > 0.0f) {
            rectangle = new Outline.Rounded(RoundRectKt.m325RoundRectgG7oq9Y(intBitsToFloat, intBitsToFloat2, intBitsToFloat3, intBitsToFloat4, (Float.floatToRawIntBits(r0) << 32) | (4294967295L & Float.floatToRawIntBits(r0))));
        } else {
            rectangle = new Outline.Rectangle(new Rect(intBitsToFloat, intBitsToFloat2, intBitsToFloat3, intBitsToFloat4));
        }
        this.internalOutline = rectangle;
        return rectangle;
    }

    public final void onRemovedFromParentLayer() {
        this.parentLayerUsages--;
        discardContentIfReleasedAndHaveNoParentLayerUsages();
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final void recordInternal() {
        ChildLayerDependenciesTracker childLayerDependenciesTracker = this.childDependenciesTracker;
        childLayerDependenciesTracker.oldDependency = childLayerDependenciesTracker.dependency;
        MutableScatterSet mutableScatterSet = childLayerDependenciesTracker.dependenciesSet;
        if (mutableScatterSet != null && mutableScatterSet.isNotEmpty()) {
            MutableScatterSet mutableScatterSet2 = childLayerDependenciesTracker.oldDependenciesSet;
            if (mutableScatterSet2 == null) {
                int i = ScatterSetKt.$r8$clinit;
                mutableScatterSet2 = new MutableScatterSet();
                childLayerDependenciesTracker.oldDependenciesSet = mutableScatterSet2;
            }
            mutableScatterSet2.plusAssign((ScatterSet) mutableScatterSet);
            mutableScatterSet.clear();
        }
        childLayerDependenciesTracker.trackingInProgress = true;
        Density density = this.density;
        LayoutDirection layoutDirection = this.layoutDirection;
        ?? r4 = this.drawBlock;
        GraphicsLayerV29 graphicsLayerV29 = this.impl;
        CanvasDrawScope canvasDrawScope = graphicsLayerV29.canvasDrawScope;
        RecordingCanvas beginRecording = graphicsLayerV29.renderNode.beginRecording();
        try {
            CanvasHolder canvasHolder = graphicsLayerV29.canvasHolder;
            AndroidCanvas androidCanvas = canvasHolder.androidCanvas;
            Canvas canvas = androidCanvas.internalCanvas;
            androidCanvas.internalCanvas = beginRecording;
            CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
            canvasDrawScope$drawContext$1.setDensity(density);
            canvasDrawScope$drawContext$1.setLayoutDirection(layoutDirection);
            canvasDrawScope$drawContext$1.graphicsLayer = this;
            canvasDrawScope$drawContext$1.m419setSizeuvyYCjk(graphicsLayerV29.size);
            canvasDrawScope$drawContext$1.setCanvas(androidCanvas);
            r4.invoke(canvasDrawScope);
            canvasHolder.androidCanvas.internalCanvas = canvas;
            graphicsLayerV29.renderNode.endRecording();
            childLayerDependenciesTracker.trackingInProgress = false;
            GraphicsLayer graphicsLayer = childLayerDependenciesTracker.oldDependency;
            if (graphicsLayer != null) {
                graphicsLayer.onRemovedFromParentLayer();
            }
            MutableScatterSet mutableScatterSet3 = childLayerDependenciesTracker.oldDependenciesSet;
            if (mutableScatterSet3 == null || !mutableScatterSet3.isNotEmpty()) {
                return;
            }
            Object[] objArr = mutableScatterSet3.elements;
            long[] jArr = mutableScatterSet3.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i2 = 0;
                while (true) {
                    long j = jArr[i2];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i3 = 8 - ((~(i2 - length)) >>> 31);
                        for (int i4 = 0; i4 < i3; i4++) {
                            if ((255 & j) < 128) {
                                ((GraphicsLayer) objArr[(i2 << 3) + i4]).onRemovedFromParentLayer();
                            }
                            j >>= 8;
                        }
                        if (i3 != 8) {
                            break;
                        }
                    }
                    if (i2 == length) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            mutableScatterSet3.clear();
        } catch (Throwable th) {
            graphicsLayerV29.renderNode.endRecording();
            throw th;
        }
    }

    public final void setAlpha(float f) {
        GraphicsLayerV29 graphicsLayerV29 = this.impl;
        if (graphicsLayerV29.alpha == f) {
            return;
        }
        graphicsLayerV29.alpha = f;
        graphicsLayerV29.renderNode.setAlpha(f);
    }

    /* renamed from: setRoundRectOutline-TNW_H78, reason: not valid java name */
    public final void m434setRoundRectOutlineTNW_H78(float f, long j, long j2) {
        if (Offset.m310equalsimpl0(this.roundRectOutlineTopLeft, j) && Size.m326equalsimpl0(this.roundRectOutlineSize, j2) && this.roundRectCornerRadius == f && this.outlinePath == null) {
            return;
        }
        this.internalOutline = null;
        this.outlinePath = null;
        this.outlineDirty = true;
        this.usePathForClip = false;
        this.roundRectOutlineTopLeft = j;
        this.roundRectOutlineSize = j2;
        this.roundRectCornerRadius = f;
        configureOutline();
    }
}
