package com.android.systemui.statusbar.notification.domain.interactor;

import com.android.systemui.statusbar.notification.data.repository.HeadsUpNotificationIconViewStateRepository;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HeadsUpNotificationIconInteractor {
    public final StateFlowImpl isolatedIconLocation;
    public final StateFlowImpl isolatedNotification;
    public final HeadsUpNotificationIconViewStateRepository repository;

    public HeadsUpNotificationIconInteractor(HeadsUpNotificationIconViewStateRepository headsUpNotificationIconViewStateRepository) {
        this.repository = headsUpNotificationIconViewStateRepository;
        this.isolatedIconLocation = headsUpNotificationIconViewStateRepository.isolatedIconLocation;
        this.isolatedNotification = headsUpNotificationIconViewStateRepository.isolatedNotification;
    }
}
