package com.android.wm.shell.shared.bubbles;

import android.graphics.drawable.GradientDrawable;
import android.util.IntProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DismissView$GRADIENT_ALPHA$1 extends IntProperty {
    @Override // android.util.Property
    public final Integer get(Object obj) {
        return Integer.valueOf(((GradientDrawable) obj).getAlpha());
    }

    @Override // android.util.IntProperty
    public final void setValue(Object obj, int i) {
        ((GradientDrawable) obj).setAlpha(i);
    }
}
