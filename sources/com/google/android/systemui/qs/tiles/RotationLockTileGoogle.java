package com.google.android.systemui.qs.tiles;

import android.R;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorPrivacyManager;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.view.RotationPolicy;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.UserSettingObserver;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.RotationLockTile$$ExternalSyntheticLambda0;
import com.android.systemui.qs.tiles.RotationLockTile$1;
import com.android.systemui.qs.tiles.RotationLockTile$2;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.statusbar.policy.RotationLockControllerImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.qs.tiles.RotationLockTileGoogle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RotationLockTileGoogle extends QSTileImpl implements BatteryController.BatteryStateChangeCallback {
    public final boolean mAllowRotationResolver;
    public final BatteryController mBatteryController;
    public final RotationLockTile$2 mCallback;
    public final RotationLockController mController;
    public final DevicePostureController mDevicePostureController;
    public final boolean mIsPerDeviceStateRotationLockEnabled;
    public final SensorPrivacyManager mPrivacyManager;
    public final RotationLockTile$$ExternalSyntheticLambda0 mSensorPrivacyChangedListener;
    public final RotationLockTile$1 mSetting;

    /* JADX WARN: Type inference failed for: r2v3, types: [com.android.systemui.qs.tiles.RotationLockTile$1] */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.qs.tiles.RotationLockTile$$ExternalSyntheticLambda0] */
    public RotationLockTileGoogle(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, RotationLockController rotationLockController, SensorPrivacyManager sensorPrivacyManager, BatteryController batteryController, final SecureSettings secureSettings, String[] strArr, DevicePostureController devicePostureController) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        QSTileImpl.ResourceIcon.get(R.drawable.ic_popup_sync_5);
        RotationLockController.RotationLockControllerCallback rotationLockControllerCallback = new RotationLockController.RotationLockControllerCallback() { // from class: com.android.systemui.qs.tiles.RotationLockTile$2
            @Override // com.android.systemui.statusbar.policy.RotationLockController.RotationLockControllerCallback
            public final void onRotationLockStateChanged(boolean z) {
                RotationLockTileGoogle.this.refreshState(Boolean.valueOf(z));
            }
        };
        this.mSensorPrivacyChangedListener = new SensorPrivacyManager.OnSensorPrivacyChangedListener() { // from class: com.android.systemui.qs.tiles.RotationLockTile$$ExternalSyntheticLambda0
            public final void onSensorPrivacyChanged(int i, boolean z) {
                RotationLockTileGoogle.this.refreshState(null);
            }
        };
        this.mController = rotationLockController;
        rotationLockController.getClass();
        rotationLockController.observe(this.mLifecycle, rotationLockControllerCallback);
        this.mPrivacyManager = sensorPrivacyManager;
        this.mBatteryController = batteryController;
        final int userId = ((QSHostAdapter) qSHost).getUserContext().getUserId();
        final QSTileImpl.H h = this.mHandler;
        this.mSetting = new UserSettingObserver(secureSettings, h, userId) { // from class: com.android.systemui.qs.tiles.RotationLockTile$1
            @Override // com.android.systemui.qs.UserSettingObserver
            public final void handleValueChanged(int i) {
                RotationLockTileGoogle.this.handleRefreshState(null);
            }
        };
        batteryController.observe(this.mLifecycle, this);
        this.mAllowRotationResolver = this.mContext.getResources().getBoolean(R.bool.config_allowRotationResolver);
        this.mDevicePostureController = devicePostureController;
        this.mIsPerDeviceStateRotationLockEnabled = !(strArr.length == 0);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.settings.AUTO_ROTATE_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 123;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return ((QSTile.BooleanState) this.mState).label;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        boolean z = ((QSTile.BooleanState) this.mState).value;
        ((RotationLockControllerImpl) this.mController).mRotationPolicy.setRotationLock("RotationLockTile#handleClick", z);
        refreshState(Boolean.valueOf(!z));
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleDestroy() {
        super.handleDestroy();
        setListening(false);
        this.mPrivacyManager.removeSensorPrivacyListener(2, this.mSensorPrivacyChangedListener);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleInitialize() {
        this.mPrivacyManager.addSensorPrivacyListener(2, this.mSensorPrivacyChangedListener);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        setListening(z);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUserSwitch(int i) {
        setUserId(i);
        handleRefreshState(null);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onPowerSaveChanged(boolean z) {
        refreshState(null);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        PackageManager packageManager;
        String rotationResolverPackageName;
        RotationLockControllerImpl rotationLockControllerImpl = (RotationLockControllerImpl) this.mController;
        boolean isRotationLocked = RotationPolicy.isRotationLocked(rotationLockControllerImpl.mRotationPolicy.context);
        boolean z = ((BatteryControllerImpl) this.mBatteryController).mPowerSave;
        boolean isSensorPrivacyEnabled = this.mPrivacyManager.isSensorPrivacyEnabled(2);
        boolean z2 = false;
        if (this.mAllowRotationResolver && !z && !isSensorPrivacyEnabled && (rotationResolverPackageName = (packageManager = this.mContext.getPackageManager()).getRotationResolverPackageName()) != null && packageManager.checkPermission("android.permission.CAMERA", rotationResolverPackageName) == 0 && rotationLockControllerImpl.mRotationPolicy.secureSettings.getInt(0, "camera_autorotate") == 1) {
            z2 = true;
        }
        booleanState.value = !isRotationLocked;
        booleanState.label = this.mContext.getString(com.android.wm.shell.R.string.quick_settings_rotation_unlocked_label);
        booleanState.icon = QSTileImpl.ResourceIcon.get(com.android.wm.shell.R.drawable.qs_auto_rotate_icon_off);
        booleanState.contentDescription = this.mContext.getString(com.android.wm.shell.R.string.accessibility_quick_settings_rotation);
        if (isRotationLocked) {
            booleanState.secondaryLabel = "";
        } else {
            booleanState.secondaryLabel = z2 ? this.mContext.getResources().getString(com.android.wm.shell.R.string.rotation_lock_camera_rotation_on) : "";
            booleanState.icon = QSTileImpl.ResourceIcon.get(com.android.wm.shell.R.drawable.qs_auto_rotate_icon_on);
        }
        booleanState.stateDescription = booleanState.secondaryLabel;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        booleanState.state = booleanState.value ? 2 : 1;
        if (this.mIsPerDeviceStateRotationLockEnabled) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mContext.getResources().getStringArray(com.android.wm.shell.R.array.tile_states_rotation)[booleanState.state]);
            sb.append(" / ");
            if (((DevicePostureControllerImpl) this.mDevicePostureController).getDevicePosture() == 1) {
                sb.append(this.mContext.getString(com.android.wm.shell.R.string.quick_settings_rotation_posture_folded));
            } else {
                sb.append(this.mContext.getString(com.android.wm.shell.R.string.quick_settings_rotation_posture_unfolded));
            }
            String sb2 = sb.toString();
            booleanState.secondaryLabel = sb2;
            booleanState.stateDescription = sb2;
        }
    }
}
