package com.android.systemui.statusbar;

import android.os.RemoteException;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.NotificationVisibility;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationClickNotifier {
    public final Executor backgroundExecutor;
    public final IStatusBarService barService;
    public final List listeners = new ArrayList();
    public final Executor mainExecutor;

    public NotificationClickNotifier(IStatusBarService iStatusBarService, Executor executor, Executor executor2) {
        this.barService = iStatusBarService;
        this.mainExecutor = executor;
        this.backgroundExecutor = executor2;
    }

    public final void onNotificationClick(String str, NotificationVisibility notificationVisibility) {
        try {
            this.barService.onNotificationClick(str, notificationVisibility);
        } catch (RemoteException unused) {
        }
        this.mainExecutor.execute(new NotificationClickNotifier$onNotificationClick$1(this, str, 0));
    }
}
