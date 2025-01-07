package com.android.systemui.statusbar.notification.logging;

import android.app.Notification;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationMemoryUsage {
    public final Notification notification;
    public final String notificationKey;
    public final NotificationObjectUsage objectUsage;
    public final String packageName;
    public final int uid;
    public final List viewUsage;

    public NotificationMemoryUsage(String str, int i, String str2, Notification notification, NotificationObjectUsage notificationObjectUsage, List list) {
        this.packageName = str;
        this.uid = i;
        this.notificationKey = str2;
        this.notification = notification;
        this.objectUsage = notificationObjectUsage;
        this.viewUsage = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationMemoryUsage)) {
            return false;
        }
        NotificationMemoryUsage notificationMemoryUsage = (NotificationMemoryUsage) obj;
        return Intrinsics.areEqual(this.packageName, notificationMemoryUsage.packageName) && this.uid == notificationMemoryUsage.uid && Intrinsics.areEqual(this.notificationKey, notificationMemoryUsage.notificationKey) && Intrinsics.areEqual(this.notification, notificationMemoryUsage.notification) && Intrinsics.areEqual(this.objectUsage, notificationMemoryUsage.objectUsage) && Intrinsics.areEqual(this.viewUsage, notificationMemoryUsage.viewUsage);
    }

    public final int hashCode() {
        return this.viewUsage.hashCode() + ((this.objectUsage.hashCode() + ((this.notification.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.notificationKey, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.uid, this.packageName.hashCode() * 31, 31), 31)) * 31)) * 31);
    }

    public final String toString() {
        return "NotificationMemoryUsage(packageName=" + this.packageName + ", uid=" + this.uid + ", notificationKey=" + this.notificationKey + ", notification=" + this.notification + ", objectUsage=" + this.objectUsage + ", viewUsage=" + this.viewUsage + ")";
    }
}
