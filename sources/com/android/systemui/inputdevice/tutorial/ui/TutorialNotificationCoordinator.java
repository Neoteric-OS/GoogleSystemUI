package com.android.systemui.inputdevice.tutorial.ui;

import android.app.NotificationManager;
import android.content.Context;
import com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TutorialNotificationCoordinator {
    public final Context context;
    public final NotificationManager notificationManager;
    public final TutorialSchedulerInteractor tutorialSchedulerInteractor;

    public TutorialNotificationCoordinator(CoroutineScope coroutineScope, Context context, TutorialSchedulerInteractor tutorialSchedulerInteractor, NotificationManager notificationManager) {
    }
}
