package androidx.compose.ui.graphics.vector;

import android.graphics.Path;
import android.graphics.PathMeasure;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPathMeasure;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PathComponent extends VNode {
    public Brush fill;
    public boolean isTrimPathDirty;
    public final AndroidPath path;
    public final Lazy pathMeasure$delegate;
    public AndroidPath renderPath;
    public Brush stroke;
    public float strokeLineWidth;
    public Stroke strokeStyle;
    public float trimPathOffset;
    public float trimPathStart;
    public float fillAlpha = 1.0f;
    public List pathData = VectorKt.EmptyPath;
    public float strokeAlpha = 1.0f;
    public int strokeLineCap = 0;
    public int strokeLineJoin = 0;
    public float strokeLineMiter = 4.0f;
    public float trimPathEnd = 1.0f;
    public boolean isPathDirty = true;
    public boolean isStrokeDirty = true;

    public PathComponent() {
        AndroidPath Path = AndroidPath_androidKt.Path();
        this.path = Path;
        this.renderPath = Path;
        this.pathMeasure$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.ui.graphics.vector.PathComponent$pathMeasure$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new AndroidPathMeasure(new PathMeasure());
            }
        });
    }

    @Override // androidx.compose.ui.graphics.vector.VNode
    public final void draw(DrawScope drawScope) {
        if (this.isPathDirty) {
            PathParserKt.toPath(this.pathData, this.path);
            updateRenderPath();
        } else if (this.isTrimPathDirty) {
            updateRenderPath();
        }
        this.isPathDirty = false;
        this.isTrimPathDirty = false;
        Brush brush = this.fill;
        if (brush != null) {
            DrawScope.m425drawPathGBMwjPU$default(drawScope, this.renderPath, brush, this.fillAlpha, null, 56);
        }
        Brush brush2 = this.stroke;
        if (brush2 != null) {
            Stroke stroke = this.strokeStyle;
            if (this.isStrokeDirty || stroke == null) {
                stroke = new Stroke(this.strokeLineWidth, this.strokeLineMiter, this.strokeLineCap, this.strokeLineJoin, 16);
                this.strokeStyle = stroke;
                this.isStrokeDirty = false;
            }
            DrawScope.m425drawPathGBMwjPU$default(drawScope, this.renderPath, brush2, this.strokeAlpha, stroke, 48);
        }
    }

    public final String toString() {
        return this.path.toString();
    }

    public final void updateRenderPath() {
        float f = this.trimPathStart;
        AndroidPath androidPath = this.path;
        if (f == 0.0f && this.trimPathEnd == 1.0f) {
            this.renderPath = androidPath;
            return;
        }
        if (Intrinsics.areEqual(this.renderPath, androidPath)) {
            this.renderPath = AndroidPath_androidKt.Path();
        } else {
            int i = this.renderPath.internalPath.getFillType() == Path.FillType.EVEN_ODD ? 1 : 0;
            this.renderPath.internalPath.rewind();
            this.renderPath.m354setFillTypeoQ8Xj4U(i);
        }
        Lazy lazy = this.pathMeasure$delegate;
        ((AndroidPathMeasure) ((androidx.compose.ui.graphics.PathMeasure) lazy.getValue())).internalPathMeasure.setPath(androidPath != null ? androidPath.internalPath : null, false);
        float length = ((AndroidPathMeasure) ((androidx.compose.ui.graphics.PathMeasure) lazy.getValue())).internalPathMeasure.getLength();
        float f2 = this.trimPathStart;
        float f3 = this.trimPathOffset;
        float f4 = ((f2 + f3) % 1.0f) * length;
        float f5 = ((this.trimPathEnd + f3) % 1.0f) * length;
        if (f4 <= f5) {
            ((AndroidPathMeasure) ((androidx.compose.ui.graphics.PathMeasure) lazy.getValue())).getSegment(f4, f5, this.renderPath);
        } else {
            ((AndroidPathMeasure) ((androidx.compose.ui.graphics.PathMeasure) lazy.getValue())).getSegment(f4, length, this.renderPath);
            ((AndroidPathMeasure) ((androidx.compose.ui.graphics.PathMeasure) lazy.getValue())).getSegment(0.0f, f5, this.renderPath);
        }
    }
}
