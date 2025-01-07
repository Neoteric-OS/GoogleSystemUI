package com.android.systemui.qs.panels.ui.model;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.foundation.lazy.grid.GridItemSpan;
import androidx.compose.foundation.lazy.grid.LazyGridSpanKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SpacerGridCell implements GridCell {
    public final int row;
    public final String s;
    public final long span;

    public SpacerGridCell(int i) {
        long GridItemSpan = LazyGridSpanKt.GridItemSpan(1);
        this.row = i;
        this.span = GridItemSpan;
        this.s = "spacer";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpacerGridCell)) {
            return false;
        }
        SpacerGridCell spacerGridCell = (SpacerGridCell) obj;
        return this.row == spacerGridCell.row && this.span == spacerGridCell.span && Intrinsics.areEqual(this.s, spacerGridCell.s);
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

    public final int hashCode() {
        return this.s.hashCode() + Scale$$ExternalSyntheticOutline0.m(Integer.hashCode(this.row) * 31, 31, this.span);
    }

    public final String toString() {
        String m127toStringimpl = GridItemSpan.m127toStringimpl(this.span);
        StringBuilder sb = new StringBuilder("SpacerGridCell(row=");
        sb.append(this.row);
        sb.append(", span=");
        sb.append(m127toStringimpl);
        sb.append(", s=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.s, ")");
    }
}
