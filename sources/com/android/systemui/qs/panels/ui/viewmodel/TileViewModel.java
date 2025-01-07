package com.android.systemui.qs.panels.ui.viewmodel;

import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileViewModel {
    public final TileSpec spec;
    public final Flow state = FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new TileViewModel$state$2(this, null), FlowConflatedKt.conflatedCallbackFlow(new TileViewModel$state$1(this, null))));
    public final QSTile tile;

    public TileViewModel(QSTile qSTile, TileSpec tileSpec) {
        this.tile = qSTile;
        this.spec = tileSpec;
    }
}
