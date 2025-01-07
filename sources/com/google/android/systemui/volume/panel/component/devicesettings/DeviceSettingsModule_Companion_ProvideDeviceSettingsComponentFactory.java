package com.google.android.systemui.volume.panel.component.devicesettings;

import android.content.Intent;
import android.os.Bundle;
import com.android.systemui.animation.Expandable;
import com.android.systemui.volume.domain.model.AudioOutputDevice;
import com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent;
import com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel;
import com.google.android.systemui.volume.panel.ui.VolumePanelGoogleUiEvent;
import dagger.internal.Provider;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DeviceSettingsModule_Companion_ProvideDeviceSettingsComponentFactory implements Provider {
    public static ButtonComponent provideDeviceSettingsComponent(final DeviceSettingsViewModel deviceSettingsViewModel) {
        return new ButtonComponent(deviceSettingsViewModel.buttonViewModel, new Function2() { // from class: com.google.android.systemui.volume.panel.component.devicesettings.DeviceSettingsModule$Companion$provideDeviceSettingsComponent$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Expandable expandable = (Expandable) obj;
                ((Number) obj2).intValue();
                DeviceSettingsViewModel deviceSettingsViewModel2 = DeviceSettingsViewModel.this;
                deviceSettingsViewModel2.uiEventLogger.log(VolumePanelGoogleUiEvent.VOLUME_PANEL_DEVICE_SETTINGS_BUTTON_CLICKED);
                deviceSettingsViewModel2.volumePanelViewModel.volumePanelGlobalStateInteractor.setVisible(false);
                AudioOutputDevice.Bluetooth bluetooth = (AudioOutputDevice.Bluetooth) ((StateFlowImpl) deviceSettingsViewModel2.pixelDeviceInteractor.activeNonPixelBluetoothMediaDevice.$$delegate_0).getValue();
                if (bluetooth != null) {
                    Intent intent = new Intent("com.android.settings.BLUETOOTH_DEVICE_DETAIL_SETTINGS");
                    Bundle bundle = new Bundle();
                    bundle.putString("device_address", bluetooth.cachedBluetoothDevice.mDevice.getAddress());
                    intent.putExtra(":settings:show_fragment_args", bundle);
                    deviceSettingsViewModel2.activityStarter.startActivity(intent.addFlags(268435456), true, expandable.activityTransitionController(null));
                }
                return Unit.INSTANCE;
            }
        });
    }
}
