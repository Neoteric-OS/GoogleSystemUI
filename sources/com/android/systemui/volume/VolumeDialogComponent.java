package com.android.systemui.volume;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.media.VolumePolicy;
import android.os.Bundle;
import android.util.Log;
import com.android.settingslib.applications.InterestingConfigChanges;
import com.android.systemui.Prefs;
import com.android.systemui.demomode.DemoMode;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.VolumeDialog;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.qs.tiles.DndTile;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl$ExtensionBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl.ExtensionImpl;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl.ExtensionImpl.PluginItem;
import com.android.systemui.tuner.TunerService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumeDialogComponent implements VolumeComponent, TunerService.Tunable {
    public final ActivityStarter mActivityStarter;
    public final Context mContext;
    public final VolumeDialogControllerImpl mController;
    public final boolean mDefaultVolumeDownToEnterSilent;
    public final boolean mDefaultVolumeUpToExitSilent;
    public VolumeDialog mDialog;
    public final KeyguardViewMediator mKeyguardViewMediator;
    public VolumePolicy mVolumePolicy;
    public static final Intent ZEN_SETTINGS = new Intent("android.settings.ZEN_MODE_SETTINGS");
    public static final Intent ZEN_PRIORITY_SETTINGS = new Intent("android.settings.ZEN_MODE_PRIORITY_SETTINGS");
    public final InterestingConfigChanges mConfigChanges = new InterestingConfigChanges(-1073741308);
    public final AnonymousClass1 mVolumeDialogCallback = new VolumeDialog.Callback() { // from class: com.android.systemui.volume.VolumeDialogComponent.1
        @Override // com.android.systemui.plugins.VolumeDialog.Callback
        public final void onZenPrioritySettingsClicked() {
            VolumeDialogComponent.this.mActivityStarter.startActivity(VolumeDialogComponent.ZEN_PRIORITY_SETTINGS, true, true);
        }

        @Override // com.android.systemui.plugins.VolumeDialog.Callback
        public final void onZenSettingsClicked() {
            VolumeDialogComponent.this.mActivityStarter.startActivity(VolumeDialogComponent.ZEN_SETTINGS, true, true);
        }
    };

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.volume.VolumeDialogComponent$1] */
    public VolumeDialogComponent(Context context, KeyguardViewMediator keyguardViewMediator, ActivityStarter activityStarter, VolumeDialogControllerImpl volumeDialogControllerImpl, DemoModeController demoModeController, PluginDependencyProvider pluginDependencyProvider, ExtensionControllerImpl extensionControllerImpl, TunerService tunerService, final VolumeDialogImpl volumeDialogImpl) {
        this.mContext = context;
        this.mKeyguardViewMediator = keyguardViewMediator;
        this.mActivityStarter = activityStarter;
        this.mController = volumeDialogControllerImpl;
        synchronized (volumeDialogControllerImpl) {
            volumeDialogControllerImpl.mUserActivityListener = this;
        }
        pluginDependencyProvider.allowPluginDependency(VolumeDialogController.class);
        extensionControllerImpl.getClass();
        ExtensionControllerImpl.ExtensionImpl extensionImpl = extensionControllerImpl.new ExtensionImpl();
        extensionImpl.mProducers.add(extensionImpl.new PluginItem(PluginManager.Helper.getAction(VolumeDialog.class), VolumeDialog.class));
        extensionImpl.mProducers.add(new ExtensionControllerImpl.ExtensionImpl.Default(new Supplier() { // from class: com.android.systemui.volume.VolumeDialogComponent$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return VolumeDialogImpl.this;
            }
        }));
        extensionImpl.mCallbacks.add(new Consumer() { // from class: com.android.systemui.volume.VolumeDialogComponent$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                VolumeDialogComponent volumeDialogComponent = VolumeDialogComponent.this;
                VolumeDialog volumeDialog = (VolumeDialog) obj;
                VolumeDialog volumeDialog2 = volumeDialogComponent.mDialog;
                if (volumeDialog2 != null) {
                    volumeDialog2.destroy();
                }
                volumeDialogComponent.mDialog = volumeDialog;
                volumeDialog.init(2020, volumeDialogComponent.mVolumeDialogCallback);
            }
        });
        Collections.sort(extensionImpl.mProducers, Comparator.comparingInt(new ExtensionControllerImpl$ExtensionBuilder$$ExternalSyntheticLambda0()));
        ExtensionControllerImpl.ExtensionImpl.m887$$Nest$mnotifyChanged(extensionImpl);
        boolean z = context.getResources().getBoolean(R.bool.config_windowManagerHalfFoldAutoRotateOverride);
        this.mDefaultVolumeDownToEnterSilent = z;
        boolean z2 = context.getResources().getBoolean(R.bool.config_windowManagerPauseRotationWhenUnfolding);
        this.mDefaultVolumeUpToExitSilent = z2;
        VolumePolicy volumePolicy = new VolumePolicy(z, z2, false, 400);
        this.mVolumePolicy = volumePolicy;
        volumeDialogControllerImpl.setVolumePolicy(volumePolicy);
        if (D.BUG) {
            Log.d(VolumeDialogControllerImpl.TAG, "showDndTile");
        }
        Context context2 = volumeDialogControllerImpl.mContext;
        Intent intent = DndTile.ZEN_SETTINGS;
        Prefs.putBoolean(context2, "DndTileVisible", true);
        tunerService.addTunable(this, "sysui_volume_down_silent", "sysui_volume_up_silent", "sysui_do_not_disturb");
        demoModeController.addCallback((DemoMode) this);
    }

    @Override // com.android.systemui.demomode.DemoMode
    public final List demoCommands() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("volume");
        return arrayList;
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, String str2) {
        VolumePolicy volumePolicy = this.mVolumePolicy;
        boolean z = volumePolicy.volumeDownToEnterSilent;
        boolean z2 = volumePolicy.volumeUpToExitSilent;
        boolean z3 = volumePolicy.doNotDisturbWhenSilent;
        if ("sysui_volume_down_silent".equals(str)) {
            z = TunerService.parseIntegerSwitch(str2, this.mDefaultVolumeDownToEnterSilent);
        } else if ("sysui_volume_up_silent".equals(str)) {
            z2 = TunerService.parseIntegerSwitch(str2, this.mDefaultVolumeUpToExitSilent);
        } else if ("sysui_do_not_disturb".equals(str)) {
            z3 = TunerService.parseIntegerSwitch(str2, false);
        }
        VolumePolicy volumePolicy2 = new VolumePolicy(z, z2, z3, this.mVolumePolicy.vibrateToSilentDebounce);
        this.mVolumePolicy = volumePolicy2;
        this.mController.setVolumePolicy(volumePolicy2);
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void dispatchDemoCommand(Bundle bundle, String str) {
    }
}
