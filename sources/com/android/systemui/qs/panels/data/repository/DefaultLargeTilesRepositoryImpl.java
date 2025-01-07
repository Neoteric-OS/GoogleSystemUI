package com.android.systemui.qs.panels.data.repository;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.Set;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultLargeTilesRepositoryImpl {
    public final Set defaultLargeTiles = SetsKt.setOf(TileSpec.Companion.create("internet"), TileSpec.Companion.create("bt"), TileSpec.Companion.create("dnd"), TileSpec.Companion.create("cast"));
}
