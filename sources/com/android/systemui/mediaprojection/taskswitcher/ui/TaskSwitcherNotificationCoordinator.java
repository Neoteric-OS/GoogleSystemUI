package com.android.systemui.mediaprojection.taskswitcher.ui;

import android.app.NotificationManager;
import android.content.Context;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.mediaprojection.taskswitcher.ui.viewmodel.TaskSwitcherNotificationViewModel;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TaskSwitcherNotificationCoordinator {
    public final BroadcastDispatcher broadcastDispatcher;
    public final Context context;
    public final NotificationManager notificationManager;
    public final TaskSwitcherNotificationViewModel viewModel;

    public TaskSwitcherNotificationCoordinator(Context context, NotificationManager notificationManager, CoroutineScope coroutineScope, TaskSwitcherNotificationViewModel taskSwitcherNotificationViewModel, BroadcastDispatcher broadcastDispatcher) {
    }
}
