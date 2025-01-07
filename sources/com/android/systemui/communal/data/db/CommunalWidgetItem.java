package com.android.systemui.communal.data.db;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalWidgetItem {
    public final String componentName;
    public final long itemId;
    public final long uid;
    public final int userSerialNumber;
    public final int widgetId;

    public CommunalWidgetItem(long j, int i, String str, long j2, int i2) {
        this.uid = j;
        this.widgetId = i;
        this.componentName = str;
        this.itemId = j2;
        this.userSerialNumber = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommunalWidgetItem)) {
            return false;
        }
        CommunalWidgetItem communalWidgetItem = (CommunalWidgetItem) obj;
        return this.uid == communalWidgetItem.uid && this.widgetId == communalWidgetItem.widgetId && Intrinsics.areEqual(this.componentName, communalWidgetItem.componentName) && this.itemId == communalWidgetItem.itemId && this.userSerialNumber == communalWidgetItem.userSerialNumber;
    }

    public final int hashCode() {
        return Integer.hashCode(this.userSerialNumber) + Scale$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.componentName, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.widgetId, Long.hashCode(this.uid) * 31, 31), 31), 31, this.itemId);
    }

    public final String toString() {
        return "CommunalWidgetItem(uid=" + this.uid + ", widgetId=" + this.widgetId + ", componentName=" + this.componentName + ", itemId=" + this.itemId + ", userSerialNumber=" + this.userSerialNumber + ")";
    }
}
