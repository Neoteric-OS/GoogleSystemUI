package com.android.systemui.statusbar.data.model;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.phone.BoundsPair;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarAppearance {
    public final List appearanceRegions;
    public final BoundsPair bounds;
    public final StatusBarMode mode;
    public final boolean navbarColorManagedByIme;

    public StatusBarAppearance(StatusBarMode statusBarMode, BoundsPair boundsPair, List list, boolean z) {
        this.mode = statusBarMode;
        this.bounds = boundsPair;
        this.appearanceRegions = list;
        this.navbarColorManagedByIme = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StatusBarAppearance)) {
            return false;
        }
        StatusBarAppearance statusBarAppearance = (StatusBarAppearance) obj;
        return this.mode == statusBarAppearance.mode && Intrinsics.areEqual(this.bounds, statusBarAppearance.bounds) && Intrinsics.areEqual(this.appearanceRegions, statusBarAppearance.appearanceRegions) && this.navbarColorManagedByIme == statusBarAppearance.navbarColorManagedByIme;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.navbarColorManagedByIme) + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((this.bounds.hashCode() + (this.mode.hashCode() * 31)) * 31, 31, this.appearanceRegions);
    }

    public final String toString() {
        return "StatusBarAppearance(mode=" + this.mode + ", bounds=" + this.bounds + ", appearanceRegions=" + this.appearanceRegions + ", navbarColorManagedByIme=" + this.navbarColorManagedByIme + ")";
    }
}
