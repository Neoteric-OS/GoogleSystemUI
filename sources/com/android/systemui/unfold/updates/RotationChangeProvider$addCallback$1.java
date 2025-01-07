package com.android.systemui.unfold.updates;

import android.os.RemoteException;
import com.android.systemui.unfold.updates.RotationChangeProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RotationChangeProvider$addCallback$1 implements Runnable {
    public final /* synthetic */ RotationChangeProvider.RotationListener $listener;
    public final /* synthetic */ RotationChangeProvider this$0;

    public RotationChangeProvider$addCallback$1(RotationChangeProvider rotationChangeProvider, RotationChangeProvider.RotationListener rotationListener) {
        this.this$0 = rotationChangeProvider;
        this.$listener = rotationListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.this$0.listeners.isEmpty()) {
            RotationChangeProvider rotationChangeProvider = this.this$0;
            rotationChangeProvider.getClass();
            try {
                rotationChangeProvider.displayManager.registerDisplayListener(rotationChangeProvider.displayListener, rotationChangeProvider.callbackHandler);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        this.this$0.listeners.add(this.$listener);
    }
}
