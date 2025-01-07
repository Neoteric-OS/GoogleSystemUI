package com.android.systemui.statusbar.notification.stack.data.repository;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationViewHeightRepository {
    public final StateFlowImpl isCurrentGestureInGuts;
    public final StateFlowImpl isCurrentGestureOverscroll;
    public final StateFlowImpl syntheticScroll = StateFlowKt.MutableStateFlow(Float.valueOf(0.0f));

    public NotificationViewHeightRepository() {
        Boolean bool = Boolean.FALSE;
        this.isCurrentGestureOverscroll = StateFlowKt.MutableStateFlow(bool);
        this.isCurrentGestureInGuts = StateFlowKt.MutableStateFlow(bool);
    }
}
