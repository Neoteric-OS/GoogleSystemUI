package com.android.systemui.recents;

import android.app.ActivityTaskManager;
import android.os.RemoteException;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class OverviewProxyService$1$$ExternalSyntheticLambda20 implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        try {
            ActivityTaskManager.getService().stopSystemLockTaskMode();
        } catch (RemoteException unused) {
            Log.e("OverviewProxyService", "Failed to stop screen pinning");
        }
    }
}
