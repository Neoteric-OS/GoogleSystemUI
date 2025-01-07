package com.android.systemui.statusbar.notification.row.ui.viewmodel;

import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.notification.stack.domain.interactor.NotificationStackInteractor;
import com.android.systemui.util.kotlin.FlowDumperImpl;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationViewFlipperViewModel extends FlowDumperImpl {
    public final SafeFlow isPaused;

    public NotificationViewFlipperViewModel(DumpManager dumpManager, NotificationStackInteractor notificationStackInteractor) {
        super(dumpManager);
        this.isPaused = (SafeFlow) dumpWhileCollecting(notificationStackInteractor.isShowingOnLockscreen, "isPaused");
    }
}
