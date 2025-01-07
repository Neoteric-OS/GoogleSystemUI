package com.android.systemui.screenshot.data.model;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayContentModel {
    public final int displayId;
    public final List rootTasks;
    public final SystemUiState systemUiState;

    public DisplayContentModel(int i, SystemUiState systemUiState, List list) {
        this.displayId = i;
        this.systemUiState = systemUiState;
        this.rootTasks = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DisplayContentModel)) {
            return false;
        }
        DisplayContentModel displayContentModel = (DisplayContentModel) obj;
        return this.displayId == displayContentModel.displayId && Intrinsics.areEqual(this.systemUiState, displayContentModel.systemUiState) && Intrinsics.areEqual(this.rootTasks, displayContentModel.rootTasks);
    }

    public final int hashCode() {
        return this.rootTasks.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(Integer.hashCode(this.displayId) * 31, 31, this.systemUiState.shadeExpanded);
    }

    public final String toString() {
        return "DisplayContentModel(displayId=" + this.displayId + ", systemUiState=" + this.systemUiState + ", rootTasks=" + this.rootTasks + ")";
    }
}
