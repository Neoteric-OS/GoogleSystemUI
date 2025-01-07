package com.android.systemui.qs.panels.shared.model;

import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.shared.model.TileCategory;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EditTileData {
    public final Text.Loaded appName;
    public final TileCategory category;
    public final Icon icon;
    public final Text label;
    public final TileSpec tileSpec;

    public EditTileData(TileSpec tileSpec, Icon icon, Text text, Text.Loaded loaded, TileCategory tileCategory) {
        this.tileSpec = tileSpec;
        this.icon = icon;
        this.label = text;
        this.appName = loaded;
        this.category = tileCategory;
        if ((tileSpec instanceof TileSpec.PlatformTileSpec) && loaded == null) {
            return;
        }
        if (!(tileSpec instanceof TileSpec.CustomTileSpec) || loaded == null) {
            throw new IllegalStateException(("tileSpec: " + tileSpec + " - appName: " + loaded + ". appName must be non-null for custom tiles and only for custom tiles.").toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EditTileData)) {
            return false;
        }
        EditTileData editTileData = (EditTileData) obj;
        return this.tileSpec.equals(editTileData.tileSpec) && this.icon.equals(editTileData.icon) && this.label.equals(editTileData.label) && Intrinsics.areEqual(this.appName, editTileData.appName) && this.category == editTileData.category;
    }

    public final int hashCode() {
        int hashCode = (this.label.hashCode() + ((this.icon.hashCode() + (this.tileSpec.hashCode() * 31)) * 31)) * 31;
        Text.Loaded loaded = this.appName;
        return this.category.hashCode() + ((hashCode + (loaded == null ? 0 : loaded.hashCode())) * 31);
    }

    public final String toString() {
        return "EditTileData(tileSpec=" + this.tileSpec + ", icon=" + this.icon + ", label=" + this.label + ", appName=" + this.appName + ", category=" + this.category + ")";
    }
}
