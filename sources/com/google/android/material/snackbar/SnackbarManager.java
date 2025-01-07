package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SnackbarManager {
    public static SnackbarManager snackbarManager;
    public final Object lock = new Object();

    public SnackbarManager() {
        new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.SnackbarManager.1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                if (message.what != 0) {
                    return false;
                }
                SnackbarManager snackbarManager2 = SnackbarManager.this;
                if (message.obj != null) {
                    throw new ClassCastException();
                }
                synchronized (snackbarManager2.lock) {
                    throw null;
                }
            }
        });
    }
}
