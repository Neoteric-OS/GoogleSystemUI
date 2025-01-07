package com.android.systemui.volume.panel.ui.viewmodel;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelState {
    public final boolean isLargeScreen;
    public final int orientation;

    public VolumePanelState(int i, boolean z) {
        this.orientation = i;
        this.isLargeScreen = z;
        if (i != 1 && i != 2 && i != 0 && i != 3) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown orientation: ").toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VolumePanelState)) {
            return false;
        }
        VolumePanelState volumePanelState = (VolumePanelState) obj;
        return this.orientation == volumePanelState.orientation && this.isLargeScreen == volumePanelState.isLargeScreen;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isLargeScreen) + (Integer.hashCode(this.orientation) * 31);
    }

    public final String toString() {
        return "VolumePanelState(orientation=" + this.orientation + ", isLargeScreen=" + this.isLargeScreen + ")";
    }
}
