package com.android.systemui.statusbar.notification.data.repository;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationsKeyguardViewStateRepository {
    public final StateFlowImpl areNotificationsFullyHidden;
    public final StateFlowImpl isPulseExpanding;

    public NotificationsKeyguardViewStateRepository() {
        Boolean bool = Boolean.FALSE;
        this.areNotificationsFullyHidden = StateFlowKt.MutableStateFlow(bool);
        this.isPulseExpanding = StateFlowKt.MutableStateFlow(bool);
    }
}
