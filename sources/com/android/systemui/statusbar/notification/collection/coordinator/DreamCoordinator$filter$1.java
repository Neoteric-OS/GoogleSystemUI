package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DreamCoordinator$filter$1 extends NotifFilter {
    public boolean isFiltering;
    public final /* synthetic */ DreamCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DreamCoordinator$filter$1(DreamCoordinator dreamCoordinator) {
        super("LockscreenHostedDreamFilter");
        this.this$0 = dreamCoordinator;
    }

    @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
    public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
        return this.isFiltering;
    }
}
