package com.android.systemui.statusbar.data.repository;

import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RemoteInputRepositoryImpl {
    public final NotificationRemoteInputManager notificationRemoteInputManager;
    public final Flow isRemoteInputActive = FlowConflatedKt.conflatedCallbackFlow(new RemoteInputRepositoryImpl$isRemoteInputActive$1(this, null));
    public final StateFlowImpl remoteInputRowBottomBound = StateFlowKt.MutableStateFlow(null);

    public RemoteInputRepositoryImpl(NotificationRemoteInputManager notificationRemoteInputManager) {
        this.notificationRemoteInputManager = notificationRemoteInputManager;
    }
}
