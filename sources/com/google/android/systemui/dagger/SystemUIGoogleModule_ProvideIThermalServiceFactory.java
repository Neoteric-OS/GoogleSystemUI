package com.google.android.systemui.dagger;

import android.os.IThermalService;
import android.os.ServiceManager;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SystemUIGoogleModule_ProvideIThermalServiceFactory implements Provider {
    public static IThermalService provideIThermalService() {
        IThermalService asInterface = IThermalService.Stub.asInterface(ServiceManager.getService("thermalservice"));
        Preconditions.checkNotNullFromProvides(asInterface);
        return asInterface;
    }
}
