package com.google.android.setupcompat.internal;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class TemplateLayout extends FrameLayout {
    public float xFraction;

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        throw null;
    }

    public float getXFraction() {
        return this.xFraction;
    }

    public void setXFraction(float f) {
        this.xFraction = f;
        int width = getWidth();
        if (width != 0) {
            setTranslationX(width * f);
        }
    }
}
