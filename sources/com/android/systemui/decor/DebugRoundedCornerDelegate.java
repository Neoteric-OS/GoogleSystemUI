package com.android.systemui.decor;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Size;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DebugRoundedCornerDelegate implements RoundedCornerResDelegate {
    public PathDrawable bottomRoundedDrawable;
    public Size bottomRoundedSize;
    public int color;
    public boolean hasBottom;
    public boolean hasTop;
    public Paint paint;
    public float physicalPixelDisplaySizeRatio;
    public PathDrawable topRoundedDrawable;
    public Size topRoundedSize;

    public final void applyNewDebugCorners(DebugRoundedCornerModel debugRoundedCornerModel, DebugRoundedCornerModel debugRoundedCornerModel2) {
        if (debugRoundedCornerModel != null) {
            this.hasTop = true;
            Paint paint = this.paint;
            this.topRoundedDrawable = new PathDrawable(debugRoundedCornerModel.path, debugRoundedCornerModel.width, debugRoundedCornerModel.height, debugRoundedCornerModel.scaleX, debugRoundedCornerModel.scaleY, paint);
            this.topRoundedSize = new Size(debugRoundedCornerModel.width, debugRoundedCornerModel.height);
        }
        if (debugRoundedCornerModel2 != null) {
            this.hasBottom = true;
            Paint paint2 = this.paint;
            this.bottomRoundedDrawable = new PathDrawable(debugRoundedCornerModel2.path, debugRoundedCornerModel2.width, debugRoundedCornerModel2.height, debugRoundedCornerModel2.scaleX, debugRoundedCornerModel2.scaleY, paint2);
            this.bottomRoundedSize = new Size(debugRoundedCornerModel2.width, debugRoundedCornerModel2.height);
        }
    }

    @Override // com.android.systemui.decor.RoundedCornerResDelegate
    public final Drawable getBottomRoundedDrawable() {
        return this.bottomRoundedDrawable;
    }

    @Override // com.android.systemui.decor.RoundedCornerResDelegate
    public final Size getBottomRoundedSize() {
        return this.bottomRoundedSize;
    }

    @Override // com.android.systemui.decor.RoundedCornerResDelegate
    public final boolean getHasBottom() {
        return this.hasBottom;
    }

    @Override // com.android.systemui.decor.RoundedCornerResDelegate
    public final boolean getHasTop() {
        return this.hasTop;
    }

    @Override // com.android.systemui.decor.RoundedCornerResDelegate
    public final Drawable getTopRoundedDrawable() {
        return this.topRoundedDrawable;
    }

    @Override // com.android.systemui.decor.RoundedCornerResDelegate
    public final Size getTopRoundedSize() {
        return this.topRoundedSize;
    }

    @Override // com.android.systemui.decor.RoundedCornerResDelegate
    public final void updateDisplayUniqueId(Integer num, String str) {
    }
}
