package com.android.launcher3.icons;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PlaceHolderIconDrawable extends FastBitmapDrawable {
    public final Path mProgressPath;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PlaceHolderIconDrawable(com.android.launcher3.icons.BitmapInfo r6, android.content.Context r7) {
        /*
            r5 = this;
            android.graphics.Bitmap r0 = r6.icon
            int r6 = r6.color
            r5.<init>(r0, r6)
            com.android.launcher3.icons.GraphicsUtils$$ExternalSyntheticLambda0 r0 = com.android.launcher3.icons.GraphicsUtils.sOnNewBitmapRunnable
            int r0 = com.android.launcher3.icons.IconProvider.CONFIG_ICON_MASK_RES_ID
            r1 = 100
            if (r0 == 0) goto L2b
            java.lang.String r0 = r7.getString(r0)
            android.graphics.Path r0 = androidx.core.graphics.PathParser.createPathFromPathData(r0)
            float r1 = (float) r1
            r2 = 1120403456(0x42c80000, float:100.0)
            int r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r3 == 0) goto L4a
            android.graphics.Matrix r3 = new android.graphics.Matrix
            r3.<init>()
            float r1 = r1 / r2
            r3.setScale(r1, r1)
            r0.transform(r3)
            goto L4a
        L2b:
            android.graphics.drawable.AdaptiveIconDrawable r0 = new android.graphics.drawable.AdaptiveIconDrawable
            android.graphics.drawable.ColorDrawable r2 = new android.graphics.drawable.ColorDrawable
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2.<init>(r3)
            android.graphics.drawable.ColorDrawable r4 = new android.graphics.drawable.ColorDrawable
            r4.<init>(r3)
            r0.<init>(r2, r4)
            r2 = 0
            r0.setBounds(r2, r2, r1, r1)
            android.graphics.Path r1 = new android.graphics.Path
            android.graphics.Path r0 = r0.getIconMask()
            r1.<init>(r0)
            r0 = r1
        L4a:
            r5.mProgressPath = r0
            android.graphics.Paint r5 = r5.mPaint
            r0 = 2130969555(0x7f0403d3, float:1.7547795E38)
            int r7 = com.android.launcher3.icons.GraphicsUtils.getAttrColor(r0, r7)
            int r6 = androidx.core.graphics.ColorUtils.compositeColors(r7, r6)
            r5.setColor(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.PlaceHolderIconDrawable.<init>(com.android.launcher3.icons.BitmapInfo, android.content.Context):void");
    }

    @Override // com.android.launcher3.icons.FastBitmapDrawable
    public final void drawInternal(Canvas canvas, Rect rect) {
        int save = canvas.save();
        canvas.translate(rect.left, rect.top);
        canvas.scale(rect.width() / 100.0f, rect.height() / 100.0f);
        canvas.drawPath(this.mProgressPath, this.mPaint);
        canvas.restoreToCount(save);
    }
}
