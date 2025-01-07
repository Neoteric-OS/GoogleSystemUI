package com.android.systemui.statusbar.notification.data.repository;

import com.android.systemui.statusbar.notification.collection.render.NotifStats;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActiveNotificationListRepository {
    public final StateFlowImpl activeNotifications = StateFlowKt.MutableStateFlow(new ActiveNotificationsStore(MapsKt.emptyMap(), MapsKt.emptyMap(), EmptyList.INSTANCE, MapsKt.emptyMap()));
    public final StateFlowImpl hasFilteredOutSeenNotifications = StateFlowKt.MutableStateFlow(Boolean.FALSE);
    public final StateFlowImpl notifStats = StateFlowKt.MutableStateFlow(NotifStats.empty);
    public final StateFlowImpl topOngoingNotificationKey = StateFlowKt.MutableStateFlow(null);
    public final StateFlowImpl topUnseenNotificationKey = StateFlowKt.MutableStateFlow(null);
}
