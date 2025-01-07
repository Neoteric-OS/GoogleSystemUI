package com.android.systemui.statusbar.domain.interactor;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OccludedState {
    public final boolean occluded;

    public OccludedState(boolean z) {
        this.occluded = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OccludedState) {
            return this.occluded == ((OccludedState) obj).occluded;
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + (Boolean.hashCode(this.occluded) * 31);
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("OccludedState(occluded="), this.occluded, ", animate=false)");
    }
}
