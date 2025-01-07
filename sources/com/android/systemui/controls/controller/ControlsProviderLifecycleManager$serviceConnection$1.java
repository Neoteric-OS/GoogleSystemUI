package com.android.systemui.controls.controller;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.service.controls.IControlsProvider;
import android.util.ArraySet;
import android.util.Log;
import com.android.systemui.controls.controller.ControlsProviderLifecycleManager;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsProviderLifecycleManager$serviceConnection$1 implements ServiceConnection {
    public final AtomicBoolean connected = new AtomicBoolean(false);
    public final /* synthetic */ ControlsProviderLifecycleManager this$0;

    public ControlsProviderLifecycleManager$serviceConnection$1(ControlsProviderLifecycleManager controlsProviderLifecycleManager) {
        this.this$0 = controlsProviderLifecycleManager;
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        super.onBindingDied(componentName);
        Log.d(this.this$0.TAG, "onBindingDied " + componentName);
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0;
        ((ExecutorImpl) controlsProviderLifecycleManager.executor).execute(new ControlsProviderLifecycleManager$ServiceMethod$run$1(controlsProviderLifecycleManager, 3));
    }

    @Override // android.content.ServiceConnection
    public final void onNullBinding(ComponentName componentName) {
        Log.d(this.this$0.TAG, "onNullBinding " + componentName);
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0;
        controlsProviderLifecycleManager.wrapper = null;
        ((ExecutorImpl) controlsProviderLifecycleManager.executor).execute(new ControlsProviderLifecycleManager$ServiceMethod$run$1(controlsProviderLifecycleManager, 4));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ArraySet arraySet;
        Log.d(this.this$0.TAG, "onServiceConnected " + componentName);
        this.this$0.wrapper = new ServiceWrapper(IControlsProvider.Stub.asInterface(iBinder));
        PackageUpdateMonitor packageUpdateMonitor = this.this$0.packageUpdateMonitor;
        if (packageUpdateMonitor.monitoring.compareAndSet(false, true)) {
            packageUpdateMonitor.register(packageUpdateMonitor.context, packageUpdateMonitor.user, packageUpdateMonitor.bgHandler);
        }
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0;
        synchronized (controlsProviderLifecycleManager.queuedServiceMethods) {
            arraySet = new ArraySet(controlsProviderLifecycleManager.queuedServiceMethods);
            ((ArraySet) controlsProviderLifecycleManager.queuedServiceMethods).clear();
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ControlsProviderLifecycleManager.ServiceMethod serviceMethod = (ControlsProviderLifecycleManager.ServiceMethod) it.next();
            if (!serviceMethod.callWrapper$frameworks__base__packages__SystemUI__android_common__SystemUI_core()) {
                ControlsProviderLifecycleManager controlsProviderLifecycleManager2 = ControlsProviderLifecycleManager.this;
                synchronized (controlsProviderLifecycleManager2.queuedServiceMethods) {
                    ((ArraySet) controlsProviderLifecycleManager2.queuedServiceMethods).add(serviceMethod);
                }
                ((ExecutorImpl) controlsProviderLifecycleManager2.executor).execute(new ControlsProviderLifecycleManager$ServiceMethod$run$1(controlsProviderLifecycleManager2, 0));
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Log.d(this.this$0.TAG, "onServiceDisconnected " + componentName);
        this.this$0.wrapper = null;
    }
}
