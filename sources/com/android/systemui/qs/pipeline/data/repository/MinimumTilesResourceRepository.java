package com.android.systemui.qs.pipeline.data.repository;

import android.content.res.Resources;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MinimumTilesResourceRepository {
    public final int minNumberOfTiles;

    public MinimumTilesResourceRepository(Resources resources) {
        this.minNumberOfTiles = resources.getInteger(R.integer.quick_settings_min_num_tiles);
    }
}
