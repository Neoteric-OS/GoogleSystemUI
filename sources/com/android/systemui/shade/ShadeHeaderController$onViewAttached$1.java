package com.android.systemui.shade;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeHeaderController$onViewAttached$1 implements View.OnLayoutChangeListener {
    public static final ShadeHeaderController$onViewAttached$1 INSTANCE = new ShadeHeaderController$onViewAttached$1();

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        view.setPivotX(view.isLayoutRtl() ? view.getWidth() : 0.0f);
        view.setPivotY(view.getHeight() / 2);
    }
}
