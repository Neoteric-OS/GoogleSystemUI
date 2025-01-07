package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CanvasDrawScope$drawContext$1 {
    public GraphicsLayer graphicsLayer;
    public final /* synthetic */ CanvasDrawScope this$0;
    public final CanvasDrawScopeKt$asDrawTransform$1 transform = new CanvasDrawScopeKt$asDrawTransform$1(this);

    public CanvasDrawScope$drawContext$1(CanvasDrawScope canvasDrawScope) {
        this.this$0 = canvasDrawScope;
    }

    public final Canvas getCanvas() {
        return this.this$0.drawParams.canvas;
    }

    /* renamed from: getSize-NH-jbRc, reason: not valid java name */
    public final long m418getSizeNHjbRc() {
        return this.this$0.drawParams.size;
    }

    public final void setCanvas(Canvas canvas) {
        this.this$0.drawParams.canvas = canvas;
    }

    public final void setDensity(Density density) {
        this.this$0.drawParams.density = density;
    }

    public final void setLayoutDirection(LayoutDirection layoutDirection) {
        this.this$0.drawParams.layoutDirection = layoutDirection;
    }

    /* renamed from: setSize-uvyYCjk, reason: not valid java name */
    public final void m419setSizeuvyYCjk(long j) {
        this.this$0.drawParams.size = j;
    }
}
