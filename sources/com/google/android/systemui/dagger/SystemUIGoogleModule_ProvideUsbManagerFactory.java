package com.google.android.systemui.dagger;

import android.content.Context;
import android.hardware.usb.UsbManager;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SystemUIGoogleModule_ProvideUsbManagerFactory implements Provider {
    public static Optional provideUsbManager(Context context) {
        Optional ofNullable = Optional.ofNullable((UsbManager) context.getSystemService(UsbManager.class));
        Preconditions.checkNotNullFromProvides(ofNullable);
        return ofNullable;
    }
}
