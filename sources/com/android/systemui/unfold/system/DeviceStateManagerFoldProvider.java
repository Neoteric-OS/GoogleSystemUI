package com.android.systemui.unfold.system;

import android.content.Context;
import android.hardware.devicestate.DeviceStateManager;
import com.android.systemui.unfold.updates.FoldProvider;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceStateManagerFoldProvider implements FoldProvider {
    public final ConcurrentHashMap callbacks = new ConcurrentHashMap();
    public final Context context;
    public final DeviceStateManager deviceStateManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FoldStateListener extends DeviceStateManager.FoldStateListener {
    }

    public DeviceStateManagerFoldProvider(DeviceStateManager deviceStateManager, Context context) {
        this.deviceStateManager = deviceStateManager;
        this.context = context;
    }

    @Override // com.android.systemui.unfold.updates.FoldProvider
    public final void registerCallback(final FoldProvider.FoldCallback foldCallback, Executor executor) {
        FoldStateListener foldStateListener = new FoldStateListener(this.context, new Consumer() { // from class: com.android.systemui.unfold.system.DeviceStateManagerFoldProvider.FoldStateListener.1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Boolean bool = (Boolean) obj;
                FoldProvider.FoldCallback foldCallback2 = FoldProvider.FoldCallback.this;
                Intrinsics.checkNotNull(bool);
                foldCallback2.onFoldUpdated(bool.booleanValue());
            }
        });
        this.deviceStateManager.registerCallback(executor, foldStateListener);
        this.callbacks.put(foldCallback, foldStateListener);
    }

    @Override // com.android.systemui.unfold.updates.FoldProvider
    public final void unregisterCallback(FoldProvider.FoldCallback foldCallback) {
        DeviceStateManager.DeviceStateCallback deviceStateCallback = (DeviceStateManager.DeviceStateCallback) this.callbacks.remove(foldCallback);
        if (deviceStateCallback != null) {
            this.deviceStateManager.unregisterCallback(deviceStateCallback);
        }
    }
}
