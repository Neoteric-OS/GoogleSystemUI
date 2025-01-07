package com.android.systemui.statusbar.notification.shared;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActiveNotificationGroupModel extends ActiveNotificationEntryModel {
    public final List children;
    public final String key;
    public final ActiveNotificationModel summary;

    public ActiveNotificationGroupModel(String str, ActiveNotificationModel activeNotificationModel, List list) {
        this.key = str;
        this.summary = activeNotificationModel;
        this.children = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActiveNotificationGroupModel)) {
            return false;
        }
        ActiveNotificationGroupModel activeNotificationGroupModel = (ActiveNotificationGroupModel) obj;
        return Intrinsics.areEqual(this.key, activeNotificationGroupModel.key) && Intrinsics.areEqual(this.summary, activeNotificationGroupModel.summary) && Intrinsics.areEqual(this.children, activeNotificationGroupModel.children);
    }

    public final int hashCode() {
        return this.children.hashCode() + ((this.summary.hashCode() + (this.key.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "ActiveNotificationGroupModel(key=" + this.key + ", summary=" + this.summary + ", children=" + this.children + ")";
    }
}
