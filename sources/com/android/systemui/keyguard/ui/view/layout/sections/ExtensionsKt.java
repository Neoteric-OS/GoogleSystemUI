package com.android.systemui.keyguard.ui.view.layout.sections;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ExtensionsKt {
    public static final void removeView(ConstraintLayout constraintLayout, int i) {
        View findViewById = constraintLayout.findViewById(i);
        if (findViewById != null) {
            constraintLayout.removeView(findViewById);
        }
    }
}
