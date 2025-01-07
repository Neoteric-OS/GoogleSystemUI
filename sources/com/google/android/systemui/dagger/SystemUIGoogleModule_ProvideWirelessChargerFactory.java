package com.google.android.systemui.dagger;

import android.content.Context;
import com.google.android.systemui.dreamliner.WirelessChargerImpl;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SystemUIGoogleModule_ProvideWirelessChargerFactory implements Provider {
    public static Optional provideWirelessCharger(Context context) {
        WirelessChargerImpl wirelessChargerImpl = new WirelessChargerImpl(context);
        Optional of = wirelessChargerImpl.initHALInterface() ? Optional.of(wirelessChargerImpl) : Optional.empty();
        Preconditions.checkNotNullFromProvides(of);
        return of;
    }
}
