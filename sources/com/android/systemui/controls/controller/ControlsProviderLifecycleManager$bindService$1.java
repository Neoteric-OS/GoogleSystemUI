package com.android.systemui.controls.controller;

import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsProviderLifecycleManager$bindService$1 implements Runnable {
    public final /* synthetic */ boolean $bind;
    public final /* synthetic */ boolean $forPanel;
    public final /* synthetic */ ControlsProviderLifecycleManager this$0;

    public ControlsProviderLifecycleManager$bindService$1(ControlsProviderLifecycleManager controlsProviderLifecycleManager, boolean z, boolean z2) {
        this.this$0 = controlsProviderLifecycleManager;
        this.$bind = z;
        this.$forPanel = z2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0;
        boolean z = this.$bind;
        boolean z2 = this.$forPanel;
        ControlsProviderLifecycleManager$serviceConnection$1 controlsProviderLifecycleManager$serviceConnection$1 = controlsProviderLifecycleManager.serviceConnection;
        controlsProviderLifecycleManager.requiresBound = z;
        if (!z) {
            controlsProviderLifecycleManager.unbindAndCleanup("unbind requested");
            PackageUpdateMonitor packageUpdateMonitor = controlsProviderLifecycleManager.packageUpdateMonitor;
            if (packageUpdateMonitor.monitoring.compareAndSet(true, false)) {
                packageUpdateMonitor.unregister();
                return;
            }
            return;
        }
        if (controlsProviderLifecycleManager.wrapper == null) {
            String str = "Binding service " + controlsProviderLifecycleManager.intent;
            String str2 = controlsProviderLifecycleManager.TAG;
            Log.d(str2, str);
            try {
                controlsProviderLifecycleManager.lastForPanel = z2;
                if (controlsProviderLifecycleManager$serviceConnection$1.connected.compareAndSet(false, true) ? controlsProviderLifecycleManager.context.bindServiceAsUser(controlsProviderLifecycleManager.intent, controlsProviderLifecycleManager$serviceConnection$1, z2 ? 257 : 67109121, controlsProviderLifecycleManager.user) : false) {
                    return;
                }
                Log.d(str2, "Couldn't bind to " + controlsProviderLifecycleManager.intent);
                ControlsProviderLifecycleManager$serviceConnection$1 controlsProviderLifecycleManager$serviceConnection$12 = controlsProviderLifecycleManager.serviceConnection;
                if (controlsProviderLifecycleManager$serviceConnection$12.connected.compareAndSet(true, false)) {
                    controlsProviderLifecycleManager.context.unbindService(controlsProviderLifecycleManager$serviceConnection$12);
                }
            } catch (SecurityException e) {
                Log.e(str2, "Failed to bind to service", e);
                controlsProviderLifecycleManager$serviceConnection$1.connected.set(false);
            }
        }
    }
}
