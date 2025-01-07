package com.android.systemui.volume.panel.shared.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelGlobalState {
    public final boolean isVisible;

    public VolumePanelGlobalState(boolean z) {
        this.isVisible = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof VolumePanelGlobalState) && this.isVisible == ((VolumePanelGlobalState) obj).isVisible;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isVisible);
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("VolumePanelGlobalState(isVisible="), this.isVisible, ")");
    }
}
