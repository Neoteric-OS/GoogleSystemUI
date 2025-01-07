package com.google.android.systemui.dagger;

import android.safetycenter.SafetyCenterManager;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.impl.sensorprivacy.domain.SensorPrivacyToggleTileUserActionInteractor;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$49 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$49(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final SensorPrivacyToggleTileUserActionInteractor create(int i) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl = (QSTileIntentUserInputHandlerImpl) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).qSTileIntentUserInputHandlerImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        return new SensorPrivacyToggleTileUserActionInteractor(qSTileIntentUserInputHandlerImpl, (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (IndividualSensorPrivacyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideIndividualSensorPrivacyControllerProvider.get(), (SafetyCenterManager) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideSafetyCenterManagerProvider.get(), i);
    }
}
