package com.android.systemui.statusbar.policy;

import android.content.SharedPreferences;
import android.util.Log;
import com.android.systemui.controls.controller.SeedResponse;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceControlsControllerImpl$seedFavorites$2 implements Consumer {
    public final /* synthetic */ SharedPreferences $prefs;
    public final /* synthetic */ DeviceControlsControllerImpl this$0;

    public DeviceControlsControllerImpl$seedFavorites$2(DeviceControlsControllerImpl deviceControlsControllerImpl, SharedPreferences sharedPreferences) {
        this.this$0 = deviceControlsControllerImpl;
        this.$prefs = sharedPreferences;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        SeedResponse seedResponse = (SeedResponse) obj;
        Log.d("DeviceControlsControllerImpl", "Controls seeded: " + seedResponse);
        if (seedResponse.accepted) {
            DeviceControlsControllerImpl deviceControlsControllerImpl = this.this$0;
            SharedPreferences sharedPreferences = this.$prefs;
            String str = seedResponse.packageName;
            deviceControlsControllerImpl.getClass();
            DeviceControlsControllerImpl.addPackageToSeededSet(sharedPreferences, str);
            DeviceControlsControllerImpl deviceControlsControllerImpl2 = this.this$0;
            if (deviceControlsControllerImpl2.position == null) {
                deviceControlsControllerImpl2.position = 7;
            }
            this.this$0.fireControlsUpdate();
            DeviceControlsControllerImpl deviceControlsControllerImpl3 = this.this$0;
            deviceControlsControllerImpl3.controlsComponent.controlsListingController.ifPresent(new DeviceControlsControllerImpl$setCallback$1(deviceControlsControllerImpl3, 3));
        }
    }
}
