package com.android.systemui.statusbar.data.repository;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationListenerSettingsRepository {
    public final StateFlowImpl showSilentStatusIcons = StateFlowKt.MutableStateFlow(Boolean.TRUE);
}
