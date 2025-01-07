package com.android.systemui.statusbar.notification.icon.domain.interactor;

import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AlwaysOnDisplayNotificationIconsInteractor {
    public final Flow aodNotifs;

    public AlwaysOnDisplayNotificationIconsInteractor(CoroutineContext coroutineContext, DeviceEntryInteractor deviceEntryInteractor, NotificationIconsInteractor notificationIconsInteractor) {
        this.aodNotifs = FlowKt.flowOn(FlowKt.transformLatest(deviceEntryInteractor.isBypassEnabled, new AlwaysOnDisplayNotificationIconsInteractor$special$$inlined$flatMapLatest$1(null, notificationIconsInteractor)), coroutineContext);
    }
}
