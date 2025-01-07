package com.android.systemui.qs.panels.ui.compose.selection;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Selection {
    public final boolean manual;
    public final TileSpec tileSpec;

    public Selection(TileSpec tileSpec, boolean z) {
        this.tileSpec = tileSpec;
        this.manual = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Selection)) {
            return false;
        }
        Selection selection = (Selection) obj;
        return Intrinsics.areEqual(this.tileSpec, selection.tileSpec) && this.manual == selection.manual;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.manual) + (this.tileSpec.hashCode() * 31);
    }

    public final String toString() {
        return "Selection(tileSpec=" + this.tileSpec + ", manual=" + this.manual + ")";
    }
}
