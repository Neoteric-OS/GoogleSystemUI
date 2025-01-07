package com.android.systemui.qs.panels.ui.viewmodel;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.function.Supplier;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileUiState {
    public final AccessibilityUiState accessibilityUiState;
    public final boolean handlesSecondaryClick;
    public final Supplier icon;
    public final String label;
    public final String secondaryLabel;
    public final int state;

    public TileUiState(String str, String str2, int i, boolean z, Supplier supplier, AccessibilityUiState accessibilityUiState) {
        this.label = str;
        this.secondaryLabel = str2;
        this.state = i;
        this.handlesSecondaryClick = z;
        this.icon = supplier;
        this.accessibilityUiState = accessibilityUiState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileUiState)) {
            return false;
        }
        TileUiState tileUiState = (TileUiState) obj;
        return Intrinsics.areEqual(this.label, tileUiState.label) && Intrinsics.areEqual(this.secondaryLabel, tileUiState.secondaryLabel) && this.state == tileUiState.state && this.handlesSecondaryClick == tileUiState.handlesSecondaryClick && Intrinsics.areEqual(this.icon, tileUiState.icon) && Intrinsics.areEqual(this.accessibilityUiState, tileUiState.accessibilityUiState);
    }

    public final int hashCode() {
        return this.accessibilityUiState.hashCode() + ((this.icon.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.state, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.secondaryLabel, this.label.hashCode() * 31, 31), 31), 31, this.handlesSecondaryClick)) * 31);
    }

    public final String toString() {
        return "TileUiState(label=" + this.label + ", secondaryLabel=" + this.secondaryLabel + ", state=" + this.state + ", handlesSecondaryClick=" + this.handlesSecondaryClick + ", icon=" + this.icon + ", accessibilityUiState=" + this.accessibilityUiState + ")";
    }
}
