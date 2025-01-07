package com.android.systemui.qs.pipeline.domain.interactor;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserTilesAndComponents {
    public final Set installedComponents;
    public final List tiles;
    public final int userId;

    public UserTilesAndComponents(int i, List list, Set set) {
        this.userId = i;
        this.tiles = list;
        this.installedComponents = set;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserTilesAndComponents)) {
            return false;
        }
        UserTilesAndComponents userTilesAndComponents = (UserTilesAndComponents) obj;
        return this.userId == userTilesAndComponents.userId && Intrinsics.areEqual(this.tiles, userTilesAndComponents.tiles) && Intrinsics.areEqual(this.installedComponents, userTilesAndComponents.installedComponents);
    }

    public final int hashCode() {
        return this.installedComponents.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(Integer.hashCode(this.userId) * 31, 31, this.tiles);
    }

    public final String toString() {
        return "UserTilesAndComponents(userId=" + this.userId + ", tiles=" + this.tiles + ", installedComponents=" + this.installedComponents + ")";
    }
}
