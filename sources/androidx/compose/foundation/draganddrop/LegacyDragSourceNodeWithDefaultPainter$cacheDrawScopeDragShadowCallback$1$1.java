package androidx.compose.foundation.draganddrop;

import android.graphics.Canvas;
import android.graphics.Picture;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class LegacyDragSourceNodeWithDefaultPainter$cacheDrawScopeDragShadowCallback$1$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
        CacheDrawScopeDragShadowCallback cacheDrawScopeDragShadowCallback = (CacheDrawScopeDragShadowCallback) this.receiver;
        cacheDrawScopeDragShadowCallback.getClass();
        final Picture picture = new Picture();
        cacheDrawScopeDragShadowCallback.cachedPicture = picture;
        final int intBitsToFloat = (int) Float.intBitsToFloat((int) (cacheDrawScope.cacheParams.mo279getSizeNHjbRc() >> 32));
        final int intBitsToFloat2 = (int) Float.intBitsToFloat((int) (cacheDrawScope.cacheParams.mo279getSizeNHjbRc() & 4294967295L));
        return cacheDrawScope.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.draganddrop.CacheDrawScopeDragShadowCallback$cachePicture$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Canvas beginRecording = picture.beginRecording(intBitsToFloat, intBitsToFloat2);
                Canvas canvas = AndroidCanvas_androidKt.EmptyCanvas;
                AndroidCanvas androidCanvas = new AndroidCanvas();
                androidCanvas.internalCanvas = beginRecording;
                LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) ((ContentDrawScope) obj);
                LayoutDirection layoutDirection = layoutNodeDrawScope.getLayoutDirection();
                CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
                long mo432getSizeNHjbRc = canvasDrawScope.mo432getSizeNHjbRc();
                CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
                CanvasDrawScope.DrawParams drawParams = canvasDrawScope$drawContext$1.this$0.drawParams;
                Density density = drawParams.density;
                LayoutDirection layoutDirection2 = drawParams.layoutDirection;
                androidx.compose.ui.graphics.Canvas canvas2 = canvasDrawScope$drawContext$1.getCanvas();
                long m418getSizeNHjbRc = canvasDrawScope.drawContext.m418getSizeNHjbRc();
                CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$12 = canvasDrawScope.drawContext;
                GraphicsLayer graphicsLayer = canvasDrawScope$drawContext$12.graphicsLayer;
                canvasDrawScope$drawContext$12.setDensity(layoutNodeDrawScope);
                canvasDrawScope$drawContext$12.setLayoutDirection(layoutDirection);
                canvasDrawScope$drawContext$12.setCanvas(androidCanvas);
                canvasDrawScope$drawContext$12.m419setSizeuvyYCjk(mo432getSizeNHjbRc);
                canvasDrawScope$drawContext$12.graphicsLayer = null;
                androidCanvas.save();
                try {
                    layoutNodeDrawScope.drawContent();
                    androidCanvas.restore();
                    CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$13 = canvasDrawScope.drawContext;
                    canvasDrawScope$drawContext$13.setDensity(density);
                    canvasDrawScope$drawContext$13.setLayoutDirection(layoutDirection2);
                    canvasDrawScope$drawContext$13.setCanvas(canvas2);
                    canvasDrawScope$drawContext$13.m419setSizeuvyYCjk(m418getSizeNHjbRc);
                    canvasDrawScope$drawContext$13.graphicsLayer = graphicsLayer;
                    picture.endRecording();
                    ((AndroidCanvas) canvasDrawScope.drawContext.getCanvas()).internalCanvas.drawPicture(picture);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    androidCanvas.restore();
                    CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$14 = canvasDrawScope.drawContext;
                    canvasDrawScope$drawContext$14.setDensity(density);
                    canvasDrawScope$drawContext$14.setLayoutDirection(layoutDirection2);
                    canvasDrawScope$drawContext$14.setCanvas(canvas2);
                    canvasDrawScope$drawContext$14.m419setSizeuvyYCjk(m418getSizeNHjbRc);
                    canvasDrawScope$drawContext$14.graphicsLayer = graphicsLayer;
                    throw th;
                }
            }
        });
    }
}
