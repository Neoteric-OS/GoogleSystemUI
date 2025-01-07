package com.android.systemui.qs.panels.ui.viewmodel;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.shared.model.TileCategory;
import kotlin.collections.builders.SetBuilder;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnloadedEditTileViewModel {
    public final Text.Loaded appName;
    public final SetBuilder availableEditActions;
    public final TileCategory category;
    public final Icon icon;
    public final boolean isCurrent;
    public final Text label;
    public final TileSpec tileSpec;

    public UnloadedEditTileViewModel(TileSpec tileSpec, Icon icon, Text text, Text.Loaded loaded, boolean z, SetBuilder setBuilder, TileCategory tileCategory) {
        this.tileSpec = tileSpec;
        this.icon = icon;
        this.label = text;
        this.appName = loaded;
        this.isCurrent = z;
        this.availableEditActions = setBuilder;
        this.category = tileCategory;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UnloadedEditTileViewModel)) {
            return false;
        }
        UnloadedEditTileViewModel unloadedEditTileViewModel = (UnloadedEditTileViewModel) obj;
        return this.tileSpec.equals(unloadedEditTileViewModel.tileSpec) && this.icon.equals(unloadedEditTileViewModel.icon) && this.label.equals(unloadedEditTileViewModel.label) && Intrinsics.areEqual(this.appName, unloadedEditTileViewModel.appName) && this.isCurrent == unloadedEditTileViewModel.isCurrent && Intrinsics.areEqual(this.availableEditActions, unloadedEditTileViewModel.availableEditActions) && this.category == unloadedEditTileViewModel.category;
    }

    public final int hashCode() {
        int hashCode = (this.label.hashCode() + ((this.icon.hashCode() + (this.tileSpec.hashCode() * 31)) * 31)) * 31;
        Text.Loaded loaded = this.appName;
        return this.category.hashCode() + ((this.availableEditActions.hashCode() + TransitionData$$ExternalSyntheticOutline0.m((hashCode + (loaded == null ? 0 : loaded.hashCode())) * 31, 31, this.isCurrent)) * 31);
    }

    public final String toString() {
        return "UnloadedEditTileViewModel(tileSpec=" + this.tileSpec + ", icon=" + this.icon + ", label=" + this.label + ", appName=" + this.appName + ", isCurrent=" + this.isCurrent + ", availableEditActions=" + this.availableEditActions + ", category=" + this.category + ")";
    }
}
