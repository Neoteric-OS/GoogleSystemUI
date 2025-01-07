package com.android.systemui;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.android.systemui.process.ProcessWrapper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SystemUISecondaryUserService extends Service {
    public final ProcessWrapper mProcessWrapper;

    public SystemUISecondaryUserService(ProcessWrapper processWrapper) {
        this.mProcessWrapper = processWrapper;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.mProcessWrapper.getClass();
        if (!ProcessWrapper.isSystemUser()) {
            ((SystemUIApplication) getApplication()).startSecondaryUserServicesIfNeeded();
        } else {
            Log.w("SysUISecondaryService", "SecondaryServices started for System User. Stopping it.");
            stopSelf();
        }
    }
}
