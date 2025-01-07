package com.google.android.setupdesign.items;

import android.graphics.Rect;
import android.graphics.drawable.LayerDrawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
class RecyclerItemAdapter$PatchedLayerDrawable extends LayerDrawable {
    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        return super.getPadding(rect) && !(rect.left == 0 && rect.top == 0 && rect.right == 0 && rect.bottom == 0);
    }
}
