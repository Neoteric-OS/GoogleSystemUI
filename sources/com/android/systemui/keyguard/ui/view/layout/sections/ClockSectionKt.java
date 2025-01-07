package com.android.systemui.keyguard.ui.view.layout.sections;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ClockSectionKt {
    public static final void setVisibility(ConstraintSet constraintSet, Iterable iterable, int i) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            constraintSet.setVisibility(((View) it.next()).getId(), i);
        }
    }
}
