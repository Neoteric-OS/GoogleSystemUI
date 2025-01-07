package com.android.systemui.qs.tiles.impl.modes.domain.model;

import android.R;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModesTileModel {
    public final List activeModes;
    public final Icon.Loaded icon;
    public final boolean isActivated;

    public ModesTileModel(boolean z, List list, Icon.Loaded loaded) {
        this.isActivated = z;
        this.activeModes = list;
        this.icon = loaded;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ModesTileModel)) {
            return false;
        }
        ModesTileModel modesTileModel = (ModesTileModel) obj;
        return this.isActivated == modesTileModel.isActivated && this.activeModes.equals(modesTileModel.activeModes) && this.icon.equals(modesTileModel.icon) && Integer.valueOf(R.drawable.jog_dial_bg).equals(Integer.valueOf(R.drawable.jog_dial_bg));
    }

    public final int hashCode() {
        return Integer.valueOf(R.drawable.jog_dial_bg).hashCode() + ((this.icon.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.isActivated) * 31, 31, this.activeModes)) * 31);
    }

    public final String toString() {
        return "ModesTileModel(isActivated=" + this.isActivated + ", activeModes=" + this.activeModes + ", icon=" + this.icon + ", iconResId=" + Integer.valueOf(R.drawable.jog_dial_bg) + ")";
    }
}
