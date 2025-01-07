package com.google.android.systemui.columbus;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusServiceProxy extends Service {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final List columbusServiceListeners = new ArrayList();
    public final ColumbusServiceProxy$binder$1 binder = new ColumbusServiceProxy$binder$1(this);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ColumbusServiceListener implements IBinder.DeathRecipient {
        public IColumbusServiceListener listener;
        public IBinder token;

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            Log.w("Columbus/ColumbusProxy", "ColumbusServiceListener binder died");
            this.token = null;
            this.listener = null;
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}
