package androidx.compose.ui.graphics.painter;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.AndroidImageBitmap;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BitmapPainter extends Painter {
    public float alpha;
    public ColorFilter colorFilter;
    public int filterQuality;
    public final ImageBitmap image;
    public final long size;
    public final long srcSize;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BitmapPainter(androidx.compose.ui.graphics.ImageBitmap r8) {
        /*
            r7 = this;
            r0 = r8
            androidx.compose.ui.graphics.AndroidImageBitmap r0 = (androidx.compose.ui.graphics.AndroidImageBitmap) r0
            android.graphics.Bitmap r1 = r0.bitmap
            int r1 = r1.getWidth()
            android.graphics.Bitmap r0 = r0.bitmap
            int r0 = r0.getHeight()
            long r1 = (long) r1
            r3 = 32
            long r1 = r1 << r3
            long r3 = (long) r0
            r5 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r3 = r3 & r5
            long r0 = r1 | r3
            r7.<init>(r8, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.painter.BitmapPainter.<init>(androidx.compose.ui.graphics.ImageBitmap):void");
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final boolean applyAlpha(float f) {
        this.alpha = f;
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final boolean applyColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BitmapPainter)) {
            return false;
        }
        BitmapPainter bitmapPainter = (BitmapPainter) obj;
        return Intrinsics.areEqual(this.image, bitmapPainter.image) && IntOffset.m674equalsimpl0(0L, 0L) && IntSize.m683equalsimpl0(this.srcSize, bitmapPainter.srcSize) && FilterQuality.m375equalsimpl0(this.filterQuality, bitmapPainter.filterQuality);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* renamed from: getIntrinsicSize-NH-jbRc, reason: not valid java name */
    public final long mo436getIntrinsicSizeNHjbRc() {
        return IntSizeKt.m685toSizeozmzZPI(this.size);
    }

    public final int hashCode() {
        return Integer.hashCode(this.filterQuality) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(this.image.hashCode() * 31, 31, 0L), 31, this.srcSize);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final void onDraw(DrawScope drawScope) {
        int round = Math.round(Float.intBitsToFloat((int) (drawScope.mo432getSizeNHjbRc() >> 32)));
        int round2 = Math.round(Float.intBitsToFloat((int) (drawScope.mo432getSizeNHjbRc() & 4294967295L)));
        float f = this.alpha;
        drawScope.mo410drawImageAZ2fEMs(this.image, 0L, r6, (r22 & 16) != 0 ? this.srcSize : (round << 32) | (round2 & 4294967295L), (r22 & 32) != 0 ? 1.0f : f, this.colorFilter, (r22 & 512) != 0 ? 1 : this.filterQuality);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BitmapPainter(image=");
        sb.append(this.image);
        sb.append(", srcOffset=");
        sb.append((Object) IntOffset.m677toStringimpl(0L));
        sb.append(", srcSize=");
        sb.append((Object) IntSize.m684toStringimpl(this.srcSize));
        sb.append(", filterQuality=");
        int i = this.filterQuality;
        sb.append((Object) (FilterQuality.m375equalsimpl0(i, 0) ? "None" : FilterQuality.m375equalsimpl0(i, 1) ? "Low" : FilterQuality.m375equalsimpl0(i, 2) ? "Medium" : FilterQuality.m375equalsimpl0(i, 3) ? "High" : "Unknown"));
        sb.append(')');
        return sb.toString();
    }

    public BitmapPainter(ImageBitmap imageBitmap, long j) {
        int i;
        int i2;
        this.image = imageBitmap;
        this.srcSize = j;
        this.filterQuality = 1;
        if (((int) 0) >= 0 && ((int) 0) >= 0 && (i = (int) (j >> 32)) >= 0 && (i2 = (int) (4294967295L & j)) >= 0) {
            AndroidImageBitmap androidImageBitmap = (AndroidImageBitmap) imageBitmap;
            if (i <= androidImageBitmap.bitmap.getWidth() && i2 <= androidImageBitmap.bitmap.getHeight()) {
                this.size = j;
                this.alpha = 1.0f;
                return;
            }
        }
        throw new IllegalArgumentException("Failed requirement.");
    }
}
