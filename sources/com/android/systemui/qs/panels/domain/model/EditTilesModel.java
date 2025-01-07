package com.android.systemui.qs.panels.domain.model;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EditTilesModel {
    public final List customTiles;
    public final List stockTiles;

    public EditTilesModel(List list, List list2) {
        this.stockTiles = list;
        this.customTiles = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EditTilesModel)) {
            return false;
        }
        EditTilesModel editTilesModel = (EditTilesModel) obj;
        return Intrinsics.areEqual(this.stockTiles, editTilesModel.stockTiles) && Intrinsics.areEqual(this.customTiles, editTilesModel.customTiles);
    }

    public final int hashCode() {
        return this.customTiles.hashCode() + (this.stockTiles.hashCode() * 31);
    }

    public final String toString() {
        return "EditTilesModel(stockTiles=" + this.stockTiles + ", customTiles=" + this.customTiles + ")";
    }
}
