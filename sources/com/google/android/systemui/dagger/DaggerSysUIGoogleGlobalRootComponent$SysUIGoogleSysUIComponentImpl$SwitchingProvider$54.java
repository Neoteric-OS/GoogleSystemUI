package com.google.android.systemui.dagger;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.accessibility.hearingaid.HearingDevicesDialogDelegate;
import com.android.systemui.accessibility.hearingaid.HearingDevicesUiEventLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$54 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$54(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final HearingDevicesDialogDelegate create(int i, boolean z) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        Context context = (Context) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        SystemUIDialog.Factory factory = (SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get();
        ActivityStarter activityStarter = (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get();
        DialogTransitionAnimator dialogTransitionAnimator = (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider.get();
        LocalBluetoothManager localBluetoothManager = (LocalBluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        return new HearingDevicesDialogDelegate(context, z, i, factory, activityStarter, dialogTransitionAnimator, localBluetoothManager, (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), (HearingDevicesUiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.hearingDevicesUiEventLoggerProvider.get());
    }
}
