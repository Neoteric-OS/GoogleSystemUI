package com.android.systemui.statusbar.connectivity;

import android.content.Context;
import com.android.systemui.statusbar.pipeline.mobile.util.MobileMappingsProxyImpl;
import com.android.systemui.util.CarrierConfigTracker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileSignalControllerFactory {
    public final CallbackHandler callbackHandler;
    public final CarrierConfigTracker carrierConfigTracker;
    public final Context context;
    public final MobileMappingsProxyImpl mobileMappings;

    public MobileSignalControllerFactory(Context context, CallbackHandler callbackHandler, CarrierConfigTracker carrierConfigTracker, MobileMappingsProxyImpl mobileMappingsProxyImpl) {
        this.context = context;
        this.callbackHandler = callbackHandler;
        this.mobileMappings = mobileMappingsProxyImpl;
    }
}
