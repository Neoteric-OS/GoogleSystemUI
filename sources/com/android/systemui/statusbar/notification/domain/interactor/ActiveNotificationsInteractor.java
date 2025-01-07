package com.android.systemui.statusbar.notification.domain.interactor;

import com.android.systemui.statusbar.notification.data.repository.ActiveNotificationListRepository;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActiveNotificationsInteractor {
    public final ActiveNotificationsInteractor$special$$inlined$map$1 activeNotificationRanks;
    public final ActiveNotificationsInteractor$special$$inlined$map$1 allRepresentativeNotifications;
    public final Flow areAnyNotificationsPresent;
    public final Flow hasClearableAlertingNotifications;
    public final Flow hasClearableNotifications;
    public final Flow hasNonClearableSilentNotifications;
    public final Flow ongoingCallNotification;
    public final ActiveNotificationListRepository repository;
    public final Flow topLevelRepresentativeNotifications;

    public ActiveNotificationsInteractor(ActiveNotificationListRepository activeNotificationListRepository, CoroutineDispatcher coroutineDispatcher) {
        this.repository = activeNotificationListRepository;
        StateFlowImpl stateFlowImpl = activeNotificationListRepository.activeNotifications;
        this.topLevelRepresentativeNotifications = FlowKt.flowOn(new ActiveNotificationsInteractor$special$$inlined$map$1(stateFlowImpl, 0), coroutineDispatcher);
        ActiveNotificationsInteractor$special$$inlined$map$1 activeNotificationsInteractor$special$$inlined$map$1 = new ActiveNotificationsInteractor$special$$inlined$map$1(stateFlowImpl, 1);
        this.allRepresentativeNotifications = activeNotificationsInteractor$special$$inlined$map$1;
        this.ongoingCallNotification = FlowKt.flowOn(FlowKt.distinctUntilChanged(new ActiveNotificationsInteractor$special$$inlined$map$1(activeNotificationsInteractor$special$$inlined$map$1, 7)), coroutineDispatcher);
        this.areAnyNotificationsPresent = FlowKt.flowOn(FlowKt.distinctUntilChanged(new ActiveNotificationsInteractor$special$$inlined$map$1(stateFlowImpl, 2)), coroutineDispatcher);
        this.activeNotificationRanks = new ActiveNotificationsInteractor$special$$inlined$map$1(stateFlowImpl, 3);
        StateFlowImpl stateFlowImpl2 = activeNotificationListRepository.notifStats;
        this.hasClearableNotifications = FlowKt.flowOn(FlowKt.distinctUntilChanged(new ActiveNotificationsInteractor$special$$inlined$map$1(stateFlowImpl2, 4)), coroutineDispatcher);
        this.hasClearableAlertingNotifications = FlowKt.flowOn(FlowKt.distinctUntilChanged(new ActiveNotificationsInteractor$special$$inlined$map$1(stateFlowImpl2, 5)), coroutineDispatcher);
        this.hasNonClearableSilentNotifications = FlowKt.flowOn(FlowKt.distinctUntilChanged(new ActiveNotificationsInteractor$special$$inlined$map$1(stateFlowImpl2, 6)), coroutineDispatcher);
    }
}
