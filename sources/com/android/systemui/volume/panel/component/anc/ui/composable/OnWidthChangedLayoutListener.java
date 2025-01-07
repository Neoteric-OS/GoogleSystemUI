package com.android.systemui.volume.panel.component.anc.ui.composable;

import android.view.View;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OnWidthChangedLayoutListener implements View.OnLayoutChangeListener {
    public final Function1 widthChanged;

    public OnWidthChangedLayoutListener(Function1 function1) {
        this.widthChanged = function1;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i3 - i;
        if (i7 - i5 != i9) {
            this.widthChanged.invoke(Integer.valueOf(i9));
        }
    }
}
