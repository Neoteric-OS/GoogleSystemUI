package com.android.systemui.volume.panel.component.selector.ui.composable;

import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelRadioButtonBarScopeImpl {
    public final List items;
    public final List mutableItems;
    public int selectedIndex = -1;

    public VolumePanelRadioButtonBarScopeImpl() {
        ArrayList arrayList = new ArrayList();
        this.mutableItems = arrayList;
        this.items = arrayList;
    }
}
