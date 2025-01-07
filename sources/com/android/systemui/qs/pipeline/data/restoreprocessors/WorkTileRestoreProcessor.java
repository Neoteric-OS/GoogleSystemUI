package com.android.systemui.qs.pipeline.data.restoreprocessors;

import android.util.SparseIntArray;
import com.android.systemui.qs.pipeline.data.model.RestoreProcessor;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WorkTileRestoreProcessor implements RestoreProcessor {
    public static final TileSpec TILE_SPEC = TileSpec.Companion.create("work");
    public final SparseIntArray lastRestorePosition = new SparseIntArray();
    public final SharedFlowImpl _removeTrackingForUser = SharedFlowKt.MutableSharedFlow$default(0, 10, null, 5);
}
