package com.android.systemui.statusbar.chips.ui.model;

import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MultipleOngoingActivityChipsModel {
    public final OngoingActivityChipModel primary;
    public final OngoingActivityChipModel secondary;

    public MultipleOngoingActivityChipsModel(OngoingActivityChipModel ongoingActivityChipModel, OngoingActivityChipModel ongoingActivityChipModel2) {
        this.primary = ongoingActivityChipModel;
        this.secondary = ongoingActivityChipModel2;
        if ((ongoingActivityChipModel instanceof OngoingActivityChipModel.Hidden) && (ongoingActivityChipModel2 instanceof OngoingActivityChipModel.Shown)) {
            throw new IllegalArgumentException("`secondary` cannot be Shown if `primary` is Hidden");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MultipleOngoingActivityChipsModel)) {
            return false;
        }
        MultipleOngoingActivityChipsModel multipleOngoingActivityChipsModel = (MultipleOngoingActivityChipsModel) obj;
        return Intrinsics.areEqual(this.primary, multipleOngoingActivityChipsModel.primary) && Intrinsics.areEqual(this.secondary, multipleOngoingActivityChipsModel.secondary);
    }

    public final int hashCode() {
        return this.secondary.hashCode() + (this.primary.hashCode() * 31);
    }

    public final String toString() {
        return "MultipleOngoingActivityChipsModel(primary=" + this.primary + ", secondary=" + this.secondary + ")";
    }
}
