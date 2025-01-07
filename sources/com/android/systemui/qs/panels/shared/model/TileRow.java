package com.android.systemui.qs.panels.shared.model;

import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileRow {
    public final List _tiles = new ArrayList();
    public int availableColumns;
    public final int columns;

    public TileRow(int i) {
        this.columns = i;
        this.availableColumns = i;
    }
}
