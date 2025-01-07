package com.android.systemui.qs.pipeline.domain.model;

import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileModel {
    public final TileSpec spec;
    public final QSTile tile;

    public TileModel(QSTile qSTile, TileSpec tileSpec) {
        this.spec = tileSpec;
        this.tile = qSTile;
        if (!Intrinsics.areEqual(tileSpec.getSpec(), qSTile.getTileSpec())) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileModel)) {
            return false;
        }
        TileModel tileModel = (TileModel) obj;
        return Intrinsics.areEqual(this.spec, tileModel.spec) && Intrinsics.areEqual(this.tile, tileModel.tile);
    }

    public final int hashCode() {
        return this.tile.hashCode() + (this.spec.hashCode() * 31);
    }

    public final String toString() {
        return "TileModel(spec=" + this.spec + ", tile=" + this.tile + ")";
    }
}
