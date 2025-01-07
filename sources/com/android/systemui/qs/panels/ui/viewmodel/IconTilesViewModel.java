package com.android.systemui.qs.panels.ui.viewmodel;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface IconTilesViewModel {
    StateFlow getLargeTiles();

    boolean isIconTile(TileSpec tileSpec);

    void resize(TileSpec tileSpec, boolean z);
}
