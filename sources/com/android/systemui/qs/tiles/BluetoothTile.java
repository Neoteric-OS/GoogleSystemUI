package com.android.systemui.qs.tiles;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import androidx.lifecycle.LifecycleKt;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.satellite.SatelliteDialogUtils;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.BluetoothController$Callback;
import com.android.systemui.statusbar.policy.BluetoothControllerImpl;
import com.android.systemui.util.PluralMessageFormaterKt;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BluetoothTile extends QSTileImpl {
    public static final String TAG;
    public final AnonymousClass1 mCallback;
    Job mClickJob;
    public final BluetoothControllerImpl mController;
    public final BluetoothTileDialogViewModel mDialogViewModel;
    public final Executor mExecutor;
    public final FeatureFlags mFeatureFlags;
    public final BluetoothTile$$ExternalSyntheticLambda0 mMetadataChangedListener;
    public CachedBluetoothDevice mMetadataRegisteredDevice;

    static {
        new Intent("android.settings.BLUETOOTH_SETTINGS");
        TAG = "BluetoothTile";
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.qs.tiles.BluetoothTile$$ExternalSyntheticLambda0] */
    public BluetoothTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BluetoothControllerImpl bluetoothControllerImpl, FeatureFlags featureFlags, BluetoothTileDialogViewModel bluetoothTileDialogViewModel) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mMetadataRegisteredDevice = null;
        BluetoothController$Callback bluetoothController$Callback = new BluetoothController$Callback() { // from class: com.android.systemui.qs.tiles.BluetoothTile.1
            @Override // com.android.systemui.statusbar.policy.BluetoothController$Callback
            public final void onBluetoothDevicesChanged() {
                BluetoothTile.this.refreshState(null);
            }

            @Override // com.android.systemui.statusbar.policy.BluetoothController$Callback
            public final void onBluetoothStateChange() {
                BluetoothTile.this.refreshState(null);
            }
        };
        this.mMetadataChangedListener = new BluetoothAdapter.OnMetadataChangedListener() { // from class: com.android.systemui.qs.tiles.BluetoothTile$$ExternalSyntheticLambda0
            public final void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                BluetoothTile bluetoothTile = BluetoothTile.this;
                if (i == 18) {
                    bluetoothTile.refreshState(null);
                } else {
                    bluetoothTile.getClass();
                }
            }
        };
        this.mController = bluetoothControllerImpl;
        bluetoothControllerImpl.observe(this.mLifecycle, bluetoothController$Callback);
        this.mExecutor = new HandlerExecutor(handler);
        this.mFeatureFlags = featureFlags;
        this.mDialogViewModel = bluetoothTileDialogViewModel;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.settings.BLUETOOTH_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 113;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_bluetooth_label);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(final Expandable expandable) {
        Job job = this.mClickJob;
        if (job == null || job.isCompleted()) {
            this.mClickJob = SatelliteDialogUtils.mayStartSatelliteWarningDialog(this.mContext, LifecycleKt.getCoroutineScope(getLifecycle()), 1, new Function1() { // from class: com.android.systemui.qs.tiles.BluetoothTile$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    BluetoothTile bluetoothTile = BluetoothTile.this;
                    bluetoothTile.getClass();
                    if (!((Boolean) obj).booleanValue()) {
                        return null;
                    }
                    if (((FeatureFlagsClassicRelease) bluetoothTile.mFeatureFlags).isEnabled(Flags.BLUETOOTH_QS_TILE_DIALOG)) {
                        bluetoothTile.mDialogViewModel.showDialog(expandable);
                        return null;
                    }
                    bluetoothTile.toggleBluetooth();
                    return null;
                }
            });
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSecondaryClick(Expandable expandable) {
        BluetoothControllerImpl bluetoothControllerImpl = this.mController;
        UserManager userManager = bluetoothControllerImpl.mUserManager;
        int i = bluetoothControllerImpl.mCurrentUser;
        if (!userManager.hasUserRestriction("no_config_bluetooth", UserHandle.of(i)) && !bluetoothControllerImpl.mUserManager.hasUserRestriction("no_bluetooth", UserHandle.of(i))) {
            toggleBluetooth();
        } else {
            this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.BLUETOOTH_SETTINGS"), 0);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (z) {
            return;
        }
        stopListeningToStaleDeviceMetadata();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        ArrayList arrayList;
        String str;
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        checkIfRestrictionEnforcedByAdminOnly(booleanState, "no_bluetooth");
        boolean z = obj == QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING;
        BluetoothControllerImpl bluetoothControllerImpl = this.mController;
        boolean z2 = z || bluetoothControllerImpl.mEnabled;
        int i = bluetoothControllerImpl.mConnectionState;
        boolean z3 = i == 2;
        boolean z4 = i == 1;
        boolean z5 = z || z4 || bluetoothControllerImpl.mState == 11;
        booleanState.isTransient = z5;
        if (!z2 || !z3 || z5) {
            stopListeningToStaleDeviceMetadata();
        }
        booleanState.dualTarget = true;
        booleanState.value = z2;
        booleanState.label = this.mContext.getString(R.string.quick_settings_bluetooth_label);
        boolean z6 = booleanState.isTransient;
        if (z4) {
            str = this.mContext.getString(R.string.quick_settings_connecting);
        } else if (z6) {
            str = this.mContext.getString(R.string.quick_settings_bluetooth_secondary_label_transient);
        } else {
            synchronized (bluetoothControllerImpl.mConnectedDevices) {
                arrayList = new ArrayList(bluetoothControllerImpl.mConnectedDevices);
            }
            if (z2 && z3 && !arrayList.isEmpty()) {
                if (arrayList.size() > 1) {
                    stopListeningToStaleDeviceMetadata();
                    str = PluralMessageFormaterKt.icuMessageFormat(this.mContext.getResources(), R.string.quick_settings_hotspot_secondary_label_num_devices, arrayList.size());
                } else {
                    CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) arrayList.get(0);
                    int intMetaData = BluetoothUtils.getIntMetaData(cachedBluetoothDevice.mDevice, 18);
                    if (intMetaData <= -1) {
                        stopListeningToStaleDeviceMetadata();
                        intMetaData = cachedBluetoothDevice.getMinBatteryLevelWithMemberDevices();
                    } else if (cachedBluetoothDevice != this.mMetadataRegisteredDevice) {
                        stopListeningToStaleDeviceMetadata();
                        try {
                            Executor executor = this.mExecutor;
                            BluetoothTile$$ExternalSyntheticLambda0 bluetoothTile$$ExternalSyntheticLambda0 = this.mMetadataChangedListener;
                            BluetoothAdapter bluetoothAdapter = bluetoothControllerImpl.mAdapter;
                            if (bluetoothAdapter != null) {
                                bluetoothAdapter.addOnMetadataChangedListener(cachedBluetoothDevice.mDevice, executor, bluetoothTile$$ExternalSyntheticLambda0);
                            }
                            this.mMetadataRegisteredDevice = cachedBluetoothDevice;
                        } catch (IllegalArgumentException unused) {
                            Log.e(TAG, "Battery metadata listener already registered for device.");
                        }
                    }
                    if (intMetaData > -1) {
                        str = this.mContext.getString(R.string.quick_settings_bluetooth_secondary_label_battery_level, Utils.formatPercentage(intMetaData));
                    } else {
                        BluetoothClass bluetoothClass = cachedBluetoothDevice.mDevice.getBluetoothClass();
                        if (bluetoothClass != null) {
                            if (cachedBluetoothDevice.isHearingAidDevice()) {
                                str = this.mContext.getString(R.string.quick_settings_bluetooth_secondary_label_hearing_aids);
                            } else if (bluetoothClass.doesClassMatch(1)) {
                                str = this.mContext.getString(R.string.quick_settings_bluetooth_secondary_label_audio);
                            } else if (bluetoothClass.doesClassMatch(0)) {
                                str = this.mContext.getString(R.string.quick_settings_bluetooth_secondary_label_headset);
                            } else if (bluetoothClass.doesClassMatch(3)) {
                                str = this.mContext.getString(R.string.quick_settings_bluetooth_secondary_label_input);
                            }
                        }
                    }
                }
            }
            str = null;
        }
        booleanState.secondaryLabel = TextUtils.emptyIfNull(str);
        booleanState.contentDescription = this.mContext.getString(R.string.accessibility_quick_settings_bluetooth);
        booleanState.stateDescription = "";
        if (z2) {
            if (z3) {
                booleanState.icon = QSTileImpl.ResourceIcon.get(R.drawable.qs_bluetooth_icon_on);
                if (!TextUtils.isEmpty(bluetoothControllerImpl.getConnectedDeviceName())) {
                    booleanState.label = bluetoothControllerImpl.getConnectedDeviceName();
                }
                booleanState.stateDescription = this.mContext.getString(R.string.accessibility_bluetooth_name, booleanState.label) + ", " + ((Object) booleanState.secondaryLabel);
            } else if (booleanState.isTransient) {
                booleanState.icon = QSTileImpl.ResourceIcon.get(R.drawable.qs_bluetooth_icon_search);
                booleanState.stateDescription = booleanState.secondaryLabel;
            } else {
                booleanState.icon = QSTileImpl.ResourceIcon.get(R.drawable.qs_bluetooth_icon_off);
                booleanState.stateDescription = this.mContext.getString(R.string.accessibility_not_connected);
            }
            booleanState.state = 2;
        } else {
            booleanState.icon = QSTileImpl.ResourceIcon.get(R.drawable.qs_bluetooth_icon_off);
            booleanState.state = 1;
        }
        booleanState.expandedAccessibilityClassName = Button.class.getName();
        booleanState.forceExpandIcon = ((FeatureFlagsClassicRelease) this.mFeatureFlags).isEnabled(Flags.BLUETOOTH_QS_TILE_DIALOG);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return this.mController.mLocalBluetoothManager != null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.handlesSecondaryClick = true;
        return booleanState;
    }

    public final void stopListeningToStaleDeviceMetadata() {
        CachedBluetoothDevice cachedBluetoothDevice = this.mMetadataRegisteredDevice;
        if (cachedBluetoothDevice == null) {
            return;
        }
        try {
            BluetoothControllerImpl bluetoothControllerImpl = this.mController;
            BluetoothTile$$ExternalSyntheticLambda0 bluetoothTile$$ExternalSyntheticLambda0 = this.mMetadataChangedListener;
            BluetoothAdapter bluetoothAdapter = bluetoothControllerImpl.mAdapter;
            if (bluetoothAdapter != null) {
                bluetoothAdapter.removeOnMetadataChangedListener(cachedBluetoothDevice.mDevice, bluetoothTile$$ExternalSyntheticLambda0);
            }
            this.mMetadataRegisteredDevice = null;
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "Battery metadata listener already unregistered for device.");
        }
    }

    public final void toggleBluetooth() {
        boolean z = ((QSTile.BooleanState) this.mState).value;
        refreshState(z ? null : QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING);
        LocalBluetoothManager localBluetoothManager = this.mController.mLocalBluetoothManager;
        if (localBluetoothManager != null) {
            LocalBluetoothAdapter localBluetoothAdapter = localBluetoothManager.mLocalAdapter;
            if (!z ? localBluetoothAdapter.mAdapter.enable() : localBluetoothAdapter.mAdapter.disable()) {
                localBluetoothAdapter.setBluetoothStateInt(!z ? 11 : 13);
            } else if (localBluetoothAdapter.mAdapter.getState() != localBluetoothAdapter.mState) {
                localBluetoothAdapter.setBluetoothStateInt(localBluetoothAdapter.mAdapter.getState());
            }
        }
    }
}
