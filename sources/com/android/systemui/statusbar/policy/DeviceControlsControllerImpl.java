package com.android.systemui.statusbar.policy;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.Favorites;
import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.qs.pipeline.domain.autoaddable.DeviceControlsAutoAddable;
import com.android.systemui.qs.pipeline.domain.autoaddable.DeviceControlsAutoAddable$autoAddSignal$1$callback$1;
import com.android.systemui.qs.pipeline.domain.model.AutoAddSignal;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptySet;
import kotlinx.coroutines.channels.ProducerCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceControlsControllerImpl {
    public DeviceControlsAutoAddable$autoAddSignal$1$callback$1 callback;
    public final Context context;
    public final ControlsComponent controlsComponent;
    public final DeviceControlsControllerImpl$listingCallback$1 listingCallback = new ControlsListingController.ControlsListingCallback() { // from class: com.android.systemui.statusbar.policy.DeviceControlsControllerImpl$listingCallback$1
        @Override // com.android.systemui.controls.management.ControlsListingController.ControlsListingCallback
        public final void onServicesUpdated(List list) {
            if (list.isEmpty()) {
                return;
            }
            DeviceControlsControllerImpl deviceControlsControllerImpl = DeviceControlsControllerImpl.this;
            String[] stringArray = deviceControlsControllerImpl.context.getResources().getStringArray(R.array.config_controlsPreferredPackages);
            SharedPreferences sharedPreferences = ((UserTrackerImpl) deviceControlsControllerImpl.userContextProvider).getUserContext().getSharedPreferences("controls_prefs", 0);
            Set<String> set = EmptySet.INSTANCE;
            Set<String> stringSet = sharedPreferences.getStringSet("SeedingCompleted", set);
            if (stringSet != null) {
                set = stringSet;
            }
            ControlsController controlsController = (ControlsController) deviceControlsControllerImpl.controlsComponent.controlsController.get();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < Math.min(2, stringArray.length); i++) {
                String str = stringArray[i];
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) it.next();
                    if (str.equals(controlsServiceInfo.componentName.getPackageName()) && !set.contains(str)) {
                        ComponentName componentName = controlsServiceInfo.componentName;
                        ((ControlsControllerImpl) controlsController).getClass();
                        if (((ArrayList) Favorites.getControlsForComponent(componentName)).size() > 0) {
                            DeviceControlsControllerImpl.addPackageToSeededSet(sharedPreferences, str);
                        } else if (controlsServiceInfo.panelActivity != null) {
                            DeviceControlsControllerImpl.addPackageToSeededSet(sharedPreferences, str);
                        } else {
                            arrayList.add(controlsServiceInfo.componentName);
                        }
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            ((ControlsControllerImpl) controlsController).seedFavoritesForComponents(arrayList, new DeviceControlsControllerImpl$seedFavorites$2(deviceControlsControllerImpl, sharedPreferences));
        }
    };
    public Integer position;
    public final SecureSettings secureSettings;
    public final UserContextProvider userContextProvider;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.policy.DeviceControlsControllerImpl$listingCallback$1] */
    public DeviceControlsControllerImpl(Context context, ControlsComponent controlsComponent, UserContextProvider userContextProvider, SecureSettings secureSettings) {
        this.context = context;
        this.controlsComponent = controlsComponent;
        this.userContextProvider = userContextProvider;
        this.secureSettings = secureSettings;
    }

    public static void addPackageToSeededSet(SharedPreferences sharedPreferences, String str) {
        Set<String> set = EmptySet.INSTANCE;
        Set<String> stringSet = sharedPreferences.getStringSet("SeedingCompleted", set);
        if (stringSet != null) {
            set = stringSet;
        }
        Set<String> mutableSet = CollectionsKt.toMutableSet(set);
        mutableSet.add(str);
        sharedPreferences.edit().putStringSet("SeedingCompleted", mutableSet).apply();
    }

    public final void fireControlsUpdate() {
        Log.i("DeviceControlsControllerImpl", "Setting DeviceControlsTile position: " + this.position);
        DeviceControlsAutoAddable$autoAddSignal$1$callback$1 deviceControlsAutoAddable$autoAddSignal$1$callback$1 = this.callback;
        if (deviceControlsAutoAddable$autoAddSignal$1$callback$1 != null) {
            Integer num = this.position;
            DeviceControlsAutoAddable deviceControlsAutoAddable = deviceControlsAutoAddable$autoAddSignal$1$callback$1.this$0;
            if (num != null) {
                ((ProducerCoroutine) deviceControlsAutoAddable$autoAddSignal$1$callback$1.$$this$conflatedCallbackFlow)._channel.mo1790trySendJP2dKIU(new AutoAddSignal.Add(num.intValue(), deviceControlsAutoAddable.spec));
            }
            deviceControlsAutoAddable.deviceControlsController.removeCallback();
        }
    }

    public final void removeCallback() {
        this.position = null;
        this.callback = null;
        this.controlsComponent.controlsListingController.ifPresent(new DeviceControlsControllerImpl$setCallback$1(this, 2));
    }
}
