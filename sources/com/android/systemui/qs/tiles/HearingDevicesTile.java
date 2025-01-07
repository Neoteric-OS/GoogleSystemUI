package com.android.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.accessibility.hearingaid.HearingDevicesChecker;
import com.android.systemui.accessibility.hearingaid.HearingDevicesChecker$$ExternalSyntheticLambda0;
import com.android.systemui.accessibility.hearingaid.HearingDevicesDialogManager;
import com.android.systemui.animation.Expandable;
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
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HearingDevicesTile extends QSTileImpl {
    public final AnonymousClass1 mCallback;
    public final HearingDevicesChecker mDevicesChecker;
    public final HearingDevicesDialogManager mDialogManager;

    public HearingDevicesTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, HearingDevicesDialogManager hearingDevicesDialogManager, HearingDevicesChecker hearingDevicesChecker, BluetoothControllerImpl bluetoothControllerImpl) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        BluetoothController$Callback bluetoothController$Callback = new BluetoothController$Callback() { // from class: com.android.systemui.qs.tiles.HearingDevicesTile.1
            @Override // com.android.systemui.statusbar.policy.BluetoothController$Callback
            public final void onBluetoothDevicesChanged() {
                HearingDevicesTile.this.refreshState(null);
            }

            @Override // com.android.systemui.statusbar.policy.BluetoothController$Callback
            public final void onBluetoothStateChange() {
                HearingDevicesTile.this.refreshState(null);
            }
        };
        this.mDialogManager = hearingDevicesDialogManager;
        this.mDevicesChecker = hearingDevicesChecker;
        bluetoothControllerImpl.observe(this.mLifecycle, bluetoothController$Callback);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.settings.HEARING_DEVICES_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_hearing_devices_label);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(final Expandable expandable) {
        this.mUiHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.HearingDevicesTile$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HearingDevicesTile hearingDevicesTile = HearingDevicesTile.this;
                hearingDevicesTile.mDialogManager.showDialog(expandable, 2);
            }
        });
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        int i = 1;
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        checkIfRestrictionEnforcedByAdminOnly(booleanState, "no_bluetooth");
        booleanState.label = this.mContext.getString(R.string.quick_settings_hearing_devices_label);
        booleanState.icon = QSTileImpl.ResourceIcon.get(R.drawable.qs_hearing_devices_icon);
        booleanState.forceExpandIcon = true;
        HearingDevicesChecker hearingDevicesChecker = this.mDevicesChecker;
        boolean isAnyPairedHearingDevice = hearingDevicesChecker.isAnyPairedHearingDevice();
        LocalBluetoothManager localBluetoothManager = hearingDevicesChecker.mLocalBluetoothManager;
        boolean z = false;
        if (localBluetoothManager != null && localBluetoothManager.mLocalAdapter.mAdapter.isEnabled()) {
            z = localBluetoothManager.mCachedDeviceManager.getCachedDevicesCopy().stream().anyMatch(new HearingDevicesChecker$$ExternalSyntheticLambda0(hearingDevicesChecker, i));
        }
        if (z) {
            booleanState.state = 2;
            booleanState.secondaryLabel = this.mContext.getString(R.string.quick_settings_hearing_devices_connected);
        } else if (isAnyPairedHearingDevice) {
            booleanState.state = 1;
            booleanState.secondaryLabel = this.mContext.getString(R.string.quick_settings_hearing_devices_disconnected);
        } else {
            booleanState.state = 1;
            booleanState.secondaryLabel = "";
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return true;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }
}
