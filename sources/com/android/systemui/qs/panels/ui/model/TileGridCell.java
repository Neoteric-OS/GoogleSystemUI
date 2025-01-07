package com.android.systemui.qs.panels.ui.model;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.lazy.grid.GridItemSpan;
import com.android.systemui.qs.panels.shared.model.SizedTile;
import com.android.systemui.qs.panels.ui.viewmodel.EditTileViewModel;
import com.android.systemui.qs.shared.model.CategoryAndName;
import com.android.systemui.qs.shared.model.TileCategory;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileGridCell implements GridCell, SizedTile, CategoryAndName {
    public final String key;
    public final int row;
    public final String s;
    public final long span;
    public final EditTileViewModel tile;
    public final int width;

    public TileGridCell(EditTileViewModel editTileViewModel, int i, int i2, long j, String str) {
        this.tile = editTileViewModel;
        this.row = i;
        this.width = i2;
        this.span = j;
        this.s = str;
        this.key = editTileViewModel.tileSpec.getSpec() + "-" + i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileGridCell)) {
            return false;
        }
        TileGridCell tileGridCell = (TileGridCell) obj;
        return Intrinsics.areEqual(this.tile, tileGridCell.tile) && this.row == tileGridCell.row && this.width == tileGridCell.width && this.span == tileGridCell.span && Intrinsics.areEqual(this.s, tileGridCell.s);
    }

    @Override // com.android.systemui.qs.shared.model.CategoryAndName
    public final TileCategory getCategory() {
        return this.tile.category;
    }

    @Override // com.android.systemui.qs.shared.model.CategoryAndName
    public final String getName() {
        return this.tile.label.text;
    }

    @Override // com.android.systemui.qs.panels.ui.model.GridCell
    public final int getRow() {
        return this.row;
    }

    @Override // com.android.systemui.qs.panels.ui.model.GridCell
    /* renamed from: getSpan-hRN5aJ8 */
    public final long mo848getSpanhRN5aJ8() {
        return this.span;
    }

    @Override // com.android.systemui.qs.panels.shared.model.SizedTile
    public final Object getTile() {
        return this.tile;
    }

    @Override // com.android.systemui.qs.panels.shared.model.SizedTile
    public final int getWidth() {
        return this.width;
    }

    public final int hashCode() {
        return this.s.hashCode() + Scale$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.width, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.row, this.tile.hashCode() * 31, 31), 31), 31, this.span);
    }

    public final String toString() {
        String m127toStringimpl = GridItemSpan.m127toStringimpl(this.span);
        StringBuilder sb = new StringBuilder("TileGridCell(tile=");
        sb.append(this.tile);
        sb.append(", row=");
        sb.append(this.row);
        sb.append(", width=");
        sb.append(this.width);
        sb.append(", span=");
        sb.append(m127toStringimpl);
        sb.append(", s=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.s, ")");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TileGridCell(com.android.systemui.qs.panels.shared.model.SizedTile r9, int r10) {
        /*
            r8 = this;
            java.lang.Object r0 = r9.getTile()
            r2 = r0
            com.android.systemui.qs.panels.ui.viewmodel.EditTileViewModel r2 = (com.android.systemui.qs.panels.ui.viewmodel.EditTileViewModel) r2
            int r4 = r9.getWidth()
            long r5 = androidx.compose.foundation.lazy.grid.LazyGridSpanKt.GridItemSpan(r4)
            com.android.systemui.qs.pipeline.shared.TileSpec r9 = r2.tileSpec
            java.lang.String r9 = r9.getSpec()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r9)
            java.lang.String r9 = "-"
            r0.append(r9)
            r0.append(r10)
            r0.append(r9)
            r0.append(r4)
            java.lang.String r7 = r0.toString()
            r1 = r8
            r3 = r10
            r1.<init>(r2, r3, r4, r5, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.panels.ui.model.TileGridCell.<init>(com.android.systemui.qs.panels.shared.model.SizedTile, int):void");
    }
}
