package com.android.systemui.accessibility.floatingmenu;

import android.graphics.drawable.LayerDrawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InstantInsetLayerDrawable extends LayerDrawable {
    @Override // android.graphics.drawable.LayerDrawable
    public final void setLayerInset(int i, int i2, int i3, int i4, int i5) {
        super.setLayerInset(i, i2, i3, i4, i5);
        onBoundsChange(getBounds());
    }
}
