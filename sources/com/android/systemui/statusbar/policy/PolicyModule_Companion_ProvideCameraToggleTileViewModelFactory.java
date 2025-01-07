package com.android.systemui.statusbar.policy;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl;
import com.android.systemui.qs.tiles.impl.sensorprivacy.SensorPrivacyToggleTileDataInteractor;
import com.android.systemui.qs.tiles.impl.sensorprivacy.domain.SensorPrivacyToggleTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.sensorprivacy.ui.SensorPrivacyTileResources;
import com.android.systemui.qs.tiles.impl.sensorprivacy.ui.SensorPrivacyToggleTileMapper;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$47;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$48;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$49;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PolicyModule_Companion_ProvideCameraToggleTileViewModelFactory implements Provider {
    public static QSTileViewModelImpl provideCameraToggleTileViewModel(QSTileViewModelFactory$Static qSTileViewModelFactory$Static, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$47 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$47, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$48 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$48, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$49 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$49) {
        TileSpec create = TileSpec.Companion.create("cameratoggle");
        SensorPrivacyToggleTileUserActionInteractor create2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$49.create(2);
        SensorPrivacyToggleTileDataInteractor create3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$48.create(2);
        SensorPrivacyTileResources.CameraPrivacyTileResources cameraPrivacyTileResources = SensorPrivacyTileResources.CameraPrivacyTileResources.INSTANCE;
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$47.this$0;
        return qSTileViewModelFactory$Static.create(create, create2, create3, new SensorPrivacyToggleTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(switchingProvider.sysUIGoogleGlobalRootComponentImpl), ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).theme(), cameraPrivacyTileResources));
    }
}
