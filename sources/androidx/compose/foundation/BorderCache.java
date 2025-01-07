package androidx.compose.foundation;

import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidImageBitmap;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BorderCache {
    public AndroidImageBitmap imageBitmap = null;
    public AndroidCanvas canvas = null;
    public CanvasDrawScope canvasDrawScope = null;
    public AndroidPath borderPath = null;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BorderCache)) {
            return false;
        }
        BorderCache borderCache = (BorderCache) obj;
        return Intrinsics.areEqual(this.imageBitmap, borderCache.imageBitmap) && Intrinsics.areEqual(this.canvas, borderCache.canvas) && Intrinsics.areEqual(this.canvasDrawScope, borderCache.canvasDrawScope) && Intrinsics.areEqual(this.borderPath, borderCache.borderPath);
    }

    public final int hashCode() {
        AndroidImageBitmap androidImageBitmap = this.imageBitmap;
        int hashCode = (androidImageBitmap == null ? 0 : androidImageBitmap.hashCode()) * 31;
        AndroidCanvas androidCanvas = this.canvas;
        int hashCode2 = (hashCode + (androidCanvas == null ? 0 : androidCanvas.hashCode())) * 31;
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        int hashCode3 = (hashCode2 + (canvasDrawScope == null ? 0 : canvasDrawScope.hashCode())) * 31;
        AndroidPath androidPath = this.borderPath;
        return hashCode3 + (androidPath != null ? androidPath.hashCode() : 0);
    }

    public final String toString() {
        return "BorderCache(imageBitmap=" + this.imageBitmap + ", canvas=" + this.canvas + ", canvasDrawScope=" + this.canvasDrawScope + ", borderPath=" + this.borderPath + ')';
    }
}
