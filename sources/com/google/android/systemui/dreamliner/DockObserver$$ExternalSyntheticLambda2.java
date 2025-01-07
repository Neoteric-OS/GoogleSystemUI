package com.google.android.systemui.dreamliner;

import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DockObserver$$ExternalSyntheticLambda2 {
    public final /* synthetic */ DockObserver f$0;

    public final void onFanLevelChanged(int i) {
        DockObserver dockObserver = this.f$0;
        dockObserver.getClass();
        Log.d("DLObserver", "notify l=" + i + ", isDocked=" + dockObserver.isDocked());
        if (dockObserver.isDocked()) {
            dockObserver.mProtectedBroadcastSender.sendBroadcastAsUser(new Intent("com.google.android.systemui.dreamliner.ACTION_UPDATE_FAN_LEVEL").putExtra("fan_level", i).addFlags(1073741824), UserHandle.ALL);
        }
    }
}
