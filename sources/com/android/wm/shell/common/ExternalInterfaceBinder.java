package com.android.wm.shell.common;

import android.os.IBinder;
import android.util.Slog;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ExternalInterfaceBinder {
    static void executeRemoteCallWithTaskPermission(final RemoteCallable remoteCallable, String str, final Consumer consumer, boolean z) {
        if (remoteCallable == null) {
            return;
        }
        remoteCallable.getContext().enforceCallingPermission("android.permission.MANAGE_ACTIVITY_TASKS", str);
        if (!z) {
            final int i = 1;
            ((HandlerExecutor) remoteCallable.getRemoteCallExecutor()).execute(new Runnable() { // from class: com.android.wm.shell.common.ExternalInterfaceBinder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i) {
                        case 0:
                            consumer.accept(remoteCallable);
                            break;
                        default:
                            consumer.accept(remoteCallable);
                            break;
                    }
                }
            });
            return;
        }
        try {
            final int i2 = 0;
            remoteCallable.getRemoteCallExecutor().executeBlocking(new Runnable() { // from class: com.android.wm.shell.common.ExternalInterfaceBinder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            consumer.accept(remoteCallable);
                            break;
                        default:
                            consumer.accept(remoteCallable);
                            break;
                    }
                }
            });
        } catch (InterruptedException e) {
            Slog.e("ExternalInterfaceBinder", "Remote call failed", e);
        }
    }

    IBinder asBinder();

    void invalidate();
}
