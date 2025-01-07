package com.android.systemui.qs.panels.ui.viewmodel;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.AnnotatedString;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.shared.model.CategoryAndName;
import com.android.systemui.qs.shared.model.TileCategory;
import kotlin.collections.builders.SetBuilder;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EditTileViewModel implements CategoryAndName {
    public final AnnotatedString appName;
    public final SetBuilder availableEditActions;
    public final TileCategory category;
    public final Icon icon;
    public final boolean isCurrent;
    public final AnnotatedString label;
    public final TileSpec tileSpec;

    public EditTileViewModel(TileSpec tileSpec, Icon icon, AnnotatedString annotatedString, AnnotatedString annotatedString2, boolean z, SetBuilder setBuilder, TileCategory tileCategory) {
        this.tileSpec = tileSpec;
        this.icon = icon;
        this.label = annotatedString;
        this.appName = annotatedString2;
        this.isCurrent = z;
        this.availableEditActions = setBuilder;
        this.category = tileCategory;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EditTileViewModel)) {
            return false;
        }
        EditTileViewModel editTileViewModel = (EditTileViewModel) obj;
        return this.tileSpec.equals(editTileViewModel.tileSpec) && this.icon.equals(editTileViewModel.icon) && this.label.equals(editTileViewModel.label) && Intrinsics.areEqual(this.appName, editTileViewModel.appName) && this.isCurrent == editTileViewModel.isCurrent && Intrinsics.areEqual(this.availableEditActions, editTileViewModel.availableEditActions) && this.category == editTileViewModel.category;
    }

    @Override // com.android.systemui.qs.shared.model.CategoryAndName
    public final TileCategory getCategory() {
        return this.category;
    }

    @Override // com.android.systemui.qs.shared.model.CategoryAndName
    public final String getName() {
        return this.label.text;
    }

    public final int hashCode() {
        int hashCode = (this.label.hashCode() + ((this.icon.hashCode() + (this.tileSpec.hashCode() * 31)) * 31)) * 31;
        AnnotatedString annotatedString = this.appName;
        return this.category.hashCode() + ((this.availableEditActions.hashCode() + TransitionData$$ExternalSyntheticOutline0.m((hashCode + (annotatedString == null ? 0 : annotatedString.hashCode())) * 31, 31, this.isCurrent)) * 31);
    }

    public final String toString() {
        return "EditTileViewModel(tileSpec=" + this.tileSpec + ", icon=" + this.icon + ", label=" + ((Object) this.label) + ", appName=" + ((Object) this.appName) + ", isCurrent=" + this.isCurrent + ", availableEditActions=" + this.availableEditActions + ", category=" + this.category + ")";
    }
}
