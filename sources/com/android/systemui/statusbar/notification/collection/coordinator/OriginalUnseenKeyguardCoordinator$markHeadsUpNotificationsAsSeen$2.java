package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OriginalUnseenKeyguardCoordinator$markHeadsUpNotificationsAsSeen$2 implements Predicate {
    public static final OriginalUnseenKeyguardCoordinator$markHeadsUpNotificationsAsSeen$2 INSTANCE = new OriginalUnseenKeyguardCoordinator$markHeadsUpNotificationsAsSeen$2();

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((NotificationEntry) obj).isRowPinned();
    }
}
