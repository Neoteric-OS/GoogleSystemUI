package com.google.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.policy.BatteryController;
import com.google.android.systemui.reversecharging.ReverseChargingViewController;
import dagger.Lazy;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CentralSurfacesGoogleModule_ProvideReverseChargingViewControllerOptionalFactory implements Provider {
    public static Optional provideReverseChargingViewControllerOptional(BatteryController batteryController, Lazy lazy) {
        Optional of = batteryController.isReverseSupported() ? Optional.of((ReverseChargingViewController) lazy.get()) : Optional.empty();
        Preconditions.checkNotNullFromProvides(of);
        return of;
    }
}
