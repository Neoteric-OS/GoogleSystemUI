package com.android.systemui.qs.pipeline.domain.interactor;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DataWithUserChange {
    public final Set installedComponents;
    public final List tiles;
    public final boolean userChange;
    public final int userId;

    public DataWithUserChange(int i, List list, Set set, boolean z) {
        this.userId = i;
        this.tiles = list;
        this.installedComponents = set;
        this.userChange = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataWithUserChange)) {
            return false;
        }
        DataWithUserChange dataWithUserChange = (DataWithUserChange) obj;
        return this.userId == dataWithUserChange.userId && Intrinsics.areEqual(this.tiles, dataWithUserChange.tiles) && Intrinsics.areEqual(this.installedComponents, dataWithUserChange.installedComponents) && this.userChange == dataWithUserChange.userChange;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.userChange) + ((this.installedComponents.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(Integer.hashCode(this.userId) * 31, 31, this.tiles)) * 31);
    }

    public final String toString() {
        return "DataWithUserChange(userId=" + this.userId + ", tiles=" + this.tiles + ", installedComponents=" + this.installedComponents + ", userChange=" + this.userChange + ")";
    }
}
