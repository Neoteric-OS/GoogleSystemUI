package com.android.systemui.statusbar.pipeline.wifi.data.repository.prod;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiRepositoryImpl$lifecycle$1$1 implements Runnable {
    public final /* synthetic */ Object $it;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ WifiRepositoryImpl$lifecycle$1$1(int i, Object obj) {
        this.$r8$classId = i;
        this.$it = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((LifecycleRegistry) this.$it).setCurrentState(Lifecycle.State.CREATED);
                break;
            case 1:
                ((WifiRepositoryImpl) this.$it).lifecycle.setCurrentState(Lifecycle.State.STARTED);
                break;
            default:
                ((WifiRepositoryImpl) this.$it).lifecycle.setCurrentState(Lifecycle.State.CREATED);
                break;
        }
    }
}
