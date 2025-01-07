package com.google.android.systemui;

import android.content.Context;
import android.provider.DeviceConfig;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.Dumpable;
import com.android.systemui.VendorServices;
import com.android.wm.shell.R;
import com.android.wm.shell.sysui.ShellInterface;
import com.google.android.systemui.autorotate.AutorotateDataService;
import com.google.android.systemui.coversheet.CoversheetService;
import com.google.android.systemui.elmyra.ElmyraService;
import com.google.android.systemui.elmyra.ServiceConfigurationGoogle;
import com.google.android.systemui.input.TouchContextService;
import com.google.android.systemui.screenprotector.ScreenProtectorNotifierService;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GoogleServices extends VendorServices {
    public final AutorotateDataService mAutorotateDataService;
    public final Lazy mColumbusStarter;
    public final Context mContext;
    public final Lazy mServiceConfigurationGoogle;
    public final ArrayList mServices = new ArrayList();
    public final ShellInterface mShellInterface;
    public final UiEventLogger mUiEventLogger;

    public GoogleServices(Context context, Lazy lazy, UiEventLogger uiEventLogger, Lazy lazy2, AutorotateDataService autorotateDataService, ShellInterface shellInterface) {
        this.mContext = context;
        this.mServiceConfigurationGoogle = lazy;
        this.mUiEventLogger = uiEventLogger;
        this.mColumbusStarter = lazy2;
        this.mAutorotateDataService = autorotateDataService;
        this.mShellInterface = shellInterface;
    }

    public final void addService(Object obj) {
        if (obj != null) {
            this.mServices.add(obj);
        }
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.mServices.size(); i++) {
            if (this.mServices.get(i) instanceof Dumpable) {
                ((Dumpable) this.mServices.get(i)).dump(printWriter, strArr);
            }
        }
    }

    @Override // com.android.systemui.VendorServices, com.android.systemui.CoreStartable
    public final void start() {
        addService(new DisplayCutoutEmulationAdapter(this.mContext));
        addService(new CoversheetService(this.mContext));
        final AutorotateDataService autorotateDataService = this.mAutorotateDataService;
        autorotateDataService.mLatencyTracker = LatencyTracker.getInstance(autorotateDataService.mContext);
        autorotateDataService.readFlagsToControlSensorLogging();
        DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.google.android.systemui.autorotate.AutorotateDataService$$ExternalSyntheticLambda0
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                AutorotateDataService autorotateDataService2 = AutorotateDataService.this;
                autorotateDataService2.getClass();
                Set keyset = properties.getKeyset();
                if (keyset.contains("log_raw_sensor_data") || keyset.contains("log_rotation_preindication")) {
                    autorotateDataService2.readFlagsToControlSensorLogging();
                }
            }
        };
        autorotateDataService.mDeviceConfig.getClass();
        DeviceConfig.addOnPropertiesChangedListener("window_manager", autorotateDataService.mMainExecutor, onPropertiesChangedListener);
        addService(autorotateDataService);
        if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.context_hub") && this.mContext.getPackageManager().hasSystemFeature("android.hardware.sensor.assist")) {
            addService(new ElmyraService(this.mContext, (ServiceConfigurationGoogle) this.mServiceConfigurationGoogle.get(), this.mUiEventLogger));
        }
        if (this.mContext.getPackageManager().hasSystemFeature("com.google.android.feature.QUICK_TAP")) {
            addService(this.mColumbusStarter.get());
        }
        if (this.mContext.getResources().getBoolean(R.bool.config_screen_protector_notification_enabled)) {
            addService(new ScreenProtectorNotifierService(this.mContext));
        }
        if (this.mContext.getResources().getBoolean(R.bool.config_touch_context_enabled)) {
            addService(new TouchContextService(this.mContext, this.mShellInterface));
        }
    }
}
