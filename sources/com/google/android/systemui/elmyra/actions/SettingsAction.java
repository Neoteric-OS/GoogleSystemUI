package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import android.os.Binder;
import com.android.systemui.shade.ShadeController;
import com.android.wm.shell.R;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Arrays;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SettingsAction extends ServiceAction {
    public final LaunchOpa mLaunchOpa;
    public final String mSettingsPackageName;
    public final ShadeController mShadeController;

    public SettingsAction(Context context, Executor executor, ShadeController shadeController, LaunchOpa launchOpa) {
        super(context, executor, null);
        this.mSettingsPackageName = context.getResources().getString(R.string.settings_app_package_name);
        this.mShadeController = shadeController;
        this.mLaunchOpa = launchOpa;
    }

    @Override // com.google.android.systemui.elmyra.actions.ServiceAction
    public final boolean checkSupportedCaller() {
        String str = this.mSettingsPackageName;
        String[] packagesForUid = this.mContext.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid == null) {
            return false;
        }
        return Arrays.asList(packagesForUid).contains(str);
    }

    @Override // com.google.android.systemui.elmyra.actions.ServiceAction, com.google.android.systemui.elmyra.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.mShadeController.cancelExpansionAndCollapseShade();
        super.onTrigger(detectionProperties);
    }

    @Override // com.google.android.systemui.elmyra.actions.ServiceAction
    public final void triggerAction() {
        if (this.mLaunchOpa.isAvailable()) {
            this.mLaunchOpa.launchOpa(0L);
        }
    }
}
