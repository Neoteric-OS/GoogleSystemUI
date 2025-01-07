package com.android.systemui.qs.panels.ui.viewmodel;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.state.ToggleableState;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AccessibilityUiState {
    public final int accessibilityRole;
    public final String clickLabel;
    public final String contentDescription;
    public final String stateDescription;
    public final ToggleableState toggleableState;

    public AccessibilityUiState(String str, String str2, int i, ToggleableState toggleableState, String str3) {
        this.contentDescription = str;
        this.stateDescription = str2;
        this.accessibilityRole = i;
        this.toggleableState = toggleableState;
        this.clickLabel = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityUiState)) {
            return false;
        }
        AccessibilityUiState accessibilityUiState = (AccessibilityUiState) obj;
        return this.contentDescription.equals(accessibilityUiState.contentDescription) && Intrinsics.areEqual(this.stateDescription, accessibilityUiState.stateDescription) && Role.m574equalsimpl0(this.accessibilityRole, accessibilityUiState.accessibilityRole) && this.toggleableState == accessibilityUiState.toggleableState && Intrinsics.areEqual(this.clickLabel, accessibilityUiState.clickLabel);
    }

    public final int hashCode() {
        int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.accessibilityRole, PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.stateDescription, this.contentDescription.hashCode() * 31, 31), 31);
        ToggleableState toggleableState = this.toggleableState;
        int hashCode = (m + (toggleableState == null ? 0 : toggleableState.hashCode())) * 31;
        String str = this.clickLabel;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public final String toString() {
        String m575toStringimpl = Role.m575toStringimpl(this.accessibilityRole);
        StringBuilder sb = new StringBuilder("AccessibilityUiState(contentDescription=");
        sb.append(this.contentDescription);
        sb.append(", stateDescription=");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(sb, this.stateDescription, ", accessibilityRole=", m575toStringimpl, ", toggleableState=");
        sb.append(this.toggleableState);
        sb.append(", clickLabel=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.clickLabel, ")");
    }
}
