package com.android.systemui.screenshot.data.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUiState {
    public final boolean shadeExpanded;

    public SystemUiState(boolean z) {
        this.shadeExpanded = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SystemUiState) && this.shadeExpanded == ((SystemUiState) obj).shadeExpanded;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.shadeExpanded);
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("SystemUiState(shadeExpanded="), this.shadeExpanded, ")");
    }
}
