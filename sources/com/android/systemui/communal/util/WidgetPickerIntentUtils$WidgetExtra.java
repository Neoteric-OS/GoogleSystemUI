package com.android.systemui.communal.util;

import android.content.ComponentName;
import android.os.UserHandle;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WidgetPickerIntentUtils$WidgetExtra {
    public final ComponentName componentName;
    public final UserHandle user;

    public WidgetPickerIntentUtils$WidgetExtra(ComponentName componentName, UserHandle userHandle) {
        this.componentName = componentName;
        this.user = userHandle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WidgetPickerIntentUtils$WidgetExtra)) {
            return false;
        }
        WidgetPickerIntentUtils$WidgetExtra widgetPickerIntentUtils$WidgetExtra = (WidgetPickerIntentUtils$WidgetExtra) obj;
        return Intrinsics.areEqual(this.componentName, widgetPickerIntentUtils$WidgetExtra.componentName) && Intrinsics.areEqual(this.user, widgetPickerIntentUtils$WidgetExtra.user);
    }

    public final int hashCode() {
        ComponentName componentName = this.componentName;
        int hashCode = (componentName == null ? 0 : componentName.hashCode()) * 31;
        UserHandle userHandle = this.user;
        return hashCode + (userHandle != null ? userHandle.hashCode() : 0);
    }

    public final String toString() {
        return "WidgetExtra(componentName=" + this.componentName + ", user=" + this.user + ")";
    }
}
