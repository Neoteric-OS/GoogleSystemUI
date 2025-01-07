package com.android.systemui.statusbar.notification.domain.interactor;

import com.android.systemui.statusbar.notification.data.repository.NotificationsKeyguardViewStateRepository;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationsKeyguardInteractor {
    public final StateFlowImpl areNotificationsFullyHidden;
    public final StateFlowImpl isPulseExpanding;
    public final NotificationsKeyguardViewStateRepository repository;

    public NotificationsKeyguardInteractor(NotificationsKeyguardViewStateRepository notificationsKeyguardViewStateRepository) {
        this.repository = notificationsKeyguardViewStateRepository;
        this.isPulseExpanding = notificationsKeyguardViewStateRepository.isPulseExpanding;
        this.areNotificationsFullyHidden = notificationsKeyguardViewStateRepository.areNotificationsFullyHidden;
    }
}
