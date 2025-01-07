package com.android.settingslib.bluetooth;

import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CachedBluetoothDevice$$ExternalSyntheticLambda1 implements BiConsumer {
    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        final CachedBluetoothDevice.Callback callback = (CachedBluetoothDevice.Callback) obj;
        Objects.requireNonNull(callback);
        ((Executor) obj2).execute(new Runnable() { // from class: com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                CachedBluetoothDevice.Callback.this.onDeviceAttributesChanged();
            }
        });
    }
}
