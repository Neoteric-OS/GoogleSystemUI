package com.google.android.systemui.dagger;

import android.content.Context;
import com.android.wm.shell.R;
import com.google.android.systemui.reversecharging.ReverseWirelessCharger;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SystemUIGoogleModule_ProvideReverseWirelessChargerFactory implements Provider {
    public static Optional provideReverseWirelessCharger(Context context) {
        Optional of = context.getResources().getBoolean(R.bool.config_wlc_support_enabled) ? Optional.of(new ReverseWirelessCharger(context)) : Optional.empty();
        Preconditions.checkNotNullFromProvides(of);
        return of;
    }
}
