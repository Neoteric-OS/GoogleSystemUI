package com.android.systemui.qs.tiles;

import android.app.UiModeManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.LocationControllerImpl;
import com.android.wm.shell.R;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UiModeNightTile extends QSTileImpl implements ConfigurationController.ConfigurationListener, BatteryController.BatteryStateChangeCallback {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
    public final BatteryController mBatteryController;
    public final LocationController mLocationController;
    public final UiModeManager mUiModeManager;

    public UiModeNightTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, ConfigurationController configurationController, BatteryController batteryController, LocationController locationController) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mBatteryController = batteryController;
        this.mUiModeManager = (UiModeManager) ((QSHostAdapter) qSHost).getUserContext().getSystemService(UiModeManager.class);
        this.mLocationController = locationController;
        configurationController.observe(this.mLifecycle, this);
        batteryController.observe(this.mLifecycle, this);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.settings.DARK_THEME_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 1706;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return ((QSTile.BooleanState) this.mState).label;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        QSTile.State state = this.mState;
        if (((QSTile.BooleanState) state).state == 0) {
            return;
        }
        boolean z = !((QSTile.BooleanState) state).value;
        this.mUiModeManager.setNightModeActivated(z);
        refreshState(Boolean.valueOf(z));
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        int nightMode = this.mUiModeManager.getNightMode();
        boolean z = ((BatteryControllerImpl) this.mBatteryController).mPowerSave;
        boolean z2 = (this.mContext.getResources().getConfiguration().uiMode & 48) == 32;
        if (z) {
            booleanState.secondaryLabel = this.mContext.getResources().getString(R.string.quick_settings_dark_mode_secondary_label_battery_saver);
        } else if (nightMode == 0 && ((LocationControllerImpl) this.mLocationController).isLocationEnabled$1()) {
            booleanState.secondaryLabel = this.mContext.getResources().getString(z2 ? R.string.quick_settings_dark_mode_secondary_label_until_sunrise : R.string.quick_settings_dark_mode_secondary_label_on_at_sunset);
        } else if (nightMode == 3) {
            int nightModeCustomType = this.mUiModeManager.getNightModeCustomType();
            if (nightModeCustomType == 0) {
                boolean is24HourFormat = DateFormat.is24HourFormat(this.mContext);
                LocalTime customNightModeEnd = z2 ? this.mUiModeManager.getCustomNightModeEnd() : this.mUiModeManager.getCustomNightModeStart();
                booleanState.secondaryLabel = this.mContext.getResources().getString(z2 ? R.string.quick_settings_dark_mode_secondary_label_until : R.string.quick_settings_dark_mode_secondary_label_on_at, is24HourFormat ? customNightModeEnd.toString() : formatter.format(customNightModeEnd));
            } else if (nightModeCustomType == 1) {
                booleanState.secondaryLabel = this.mContext.getResources().getString(z2 ? R.string.quick_settings_dark_mode_secondary_label_until_bedtime_ends : R.string.quick_settings_dark_mode_secondary_label_on_at_bedtime);
            } else {
                booleanState.secondaryLabel = null;
            }
        } else {
            booleanState.secondaryLabel = null;
        }
        booleanState.value = z2;
        booleanState.label = this.mContext.getString(R.string.quick_settings_ui_mode_night_label);
        booleanState.contentDescription = TextUtils.isEmpty(booleanState.secondaryLabel) ? booleanState.label : TextUtils.concat(booleanState.label, ", ", booleanState.secondaryLabel);
        if (z) {
            booleanState.state = 0;
        } else {
            booleanState.state = booleanState.value ? 2 : 1;
        }
        booleanState.icon = QSTileImpl.ResourceIcon.get(booleanState.state == 2 ? R.drawable.qs_light_dark_theme_icon_on : R.drawable.qs_light_dark_theme_icon_off);
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onPowerSaveChanged(boolean z) {
        refreshState(null);
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onUiModeChanged() {
        refreshState(null);
    }
}
