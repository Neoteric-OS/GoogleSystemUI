package com.android.systemui.qs.tiles;

import com.android.systemui.DejankUtils;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyControllerImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CameraToggleTile extends SensorPrivacyToggleTile {
    @Override // com.android.systemui.qs.tiles.SensorPrivacyToggleTile
    public final int getIconRes(boolean z) {
        return z ? R.drawable.qs_camera_access_icon_off : R.drawable.qs_camera_access_icon_on;
    }

    @Override // com.android.systemui.qs.tiles.SensorPrivacyToggleTile
    public final String getRestriction() {
        return "disallow_camera_toggle";
    }

    @Override // com.android.systemui.qs.tiles.SensorPrivacyToggleTile
    public final int getSensorId() {
        return 2;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_camera_label);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return ((IndividualSensorPrivacyControllerImpl) this.mSensorPrivacyController).mSensorPrivacyManager.supportsSensorToggle(2) && ((Boolean) DejankUtils.whitelistIpcs(new CameraToggleTile$$ExternalSyntheticLambda0())).booleanValue();
    }
}
