package com.android.systemui.qs.tiles;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.notification.modes.EnableZenModeDialog;
import com.android.systemui.Prefs;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
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
import com.android.systemui.qs.tiles.dialog.QSZenModeDialogMetricsLogger;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DndTile extends QSTileImpl {
    public static final Intent ZEN_SETTINGS = new Intent("android.settings.ZEN_MODE_SETTINGS");
    public final ZenModeController mController;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public boolean mListening;
    public final AnonymousClass2 mPrefListener;
    public final QSZenModeDialogMetricsLogger mQSZenDialogMetricsLogger;
    public final AnonymousClass1 mSettingZenDuration;
    public final SharedPreferences mSharedPreferences;
    public final AnonymousClass3 mZenCallback;

    static {
        new Intent("android.settings.ZEN_MODE_PRIORITY_SETTINGS");
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.qs.tiles.DndTile$2] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.qs.tiles.DndTile$1] */
    public DndTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, ZenModeController zenModeController, SharedPreferences sharedPreferences, SecureSettings secureSettings, DialogTransitionAnimator dialogTransitionAnimator) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mPrefListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.android.systemui.qs.tiles.DndTile.2
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
                if ("DndTileCombinedIcon".equals(str) || "DndTileVisible".equals(str)) {
                    DndTile.this.refreshState(null);
                }
            }
        };
        ZenModeController.Callback callback = new ZenModeController.Callback() { // from class: com.android.systemui.qs.tiles.DndTile.3
            @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
            public final void onZenChanged(int i) {
                DndTile dndTile = DndTile.this;
                Log.d(dndTile.TAG, "Zen changed to " + i + ". Requesting refresh of tile.");
                dndTile.refreshState(Integer.valueOf(i));
            }
        };
        this.mController = zenModeController;
        this.mSharedPreferences = sharedPreferences;
        zenModeController.observe(this.mLifecycle, callback);
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mSettingZenDuration = new UserSettingObserver(secureSettings, this.mUiHandler, ((QSHostAdapter) this.mHost).getUserId()) { // from class: com.android.systemui.qs.tiles.DndTile.1
            @Override // com.android.systemui.qs.UserSettingObserver
            public final void handleValueChanged(int i) {
                DndTile.this.refreshState(null);
            }
        };
        this.mQSZenDialogMetricsLogger = new QSZenModeDialogMetricsLogger(this.mContext);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return ZEN_SETTINGS;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 118;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_dnd_label);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(final Expandable expandable) {
        boolean z = ((QSTile.BooleanState) this.mState).value;
        String str = this.TAG;
        ZenModeController zenModeController = this.mController;
        if (z) {
            ((ZenModeControllerImpl) zenModeController).setZen(0, null, str);
            return;
        }
        int value = getValue();
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "show_zen_upgrade_notification", 0) != 0 && Settings.Secure.getInt(this.mContext.getContentResolver(), "zen_settings_updated", 0) != 1) {
            Settings.Secure.putInt(this.mContext.getContentResolver(), "show_zen_upgrade_notification", 0);
            ((ZenModeControllerImpl) zenModeController).setZen(1, null, str);
            Intent intent = new Intent("android.settings.ZEN_MODE_ONBOARDING");
            intent.addFlags(268468224);
            this.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
            return;
        }
        if (value == -1) {
            this.mUiHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.DndTile$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DndTile dndTile = DndTile.this;
                    Expandable expandable2 = expandable;
                    dndTile.getClass();
                    AlertDialog createDialog = new EnableZenModeDialog(dndTile.mContext, dndTile.mQSZenDialogMetricsLogger).createDialog();
                    SystemUIDialog.applyFlags(createDialog, true);
                    SystemUIDialog.setShowForAllUsers(createDialog);
                    SystemUIDialog.registerDismissListener(createDialog, null);
                    SystemUIDialog.setDialogSize(createDialog);
                    if (expandable2 == null) {
                        createDialog.show();
                        return;
                    }
                    DialogTransitionAnimator.Controller m = BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "start_zen_mode", expandable2);
                    if (m != null) {
                        dndTile.mDialogTransitionAnimator.show(createDialog, m, false);
                    } else {
                        createDialog.show();
                    }
                }
            });
        } else if (value == 0) {
            ((ZenModeControllerImpl) zenModeController).setZen(1, null, str);
        } else {
            ((ZenModeControllerImpl) zenModeController).setZen(1, ZenModeConfig.toTimeCondition(this.mContext, value, ((QSHostAdapter) this.mHost).getUserId(), true).id, str);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleDestroy() {
        super.handleDestroy();
        setListening(false);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSecondaryClick(Expandable expandable) {
        handleLongClick(expandable);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        AnonymousClass2 anonymousClass2 = this.mPrefListener;
        if (z) {
            Prefs.get(this.mContext).registerOnSharedPreferenceChangeListener(anonymousClass2);
        } else {
            Prefs.get(this.mContext).unregisterOnSharedPreferenceChangeListener(anonymousClass2);
        }
        setListening(z);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        ZenModeController zenModeController = this.mController;
        if (zenModeController == null) {
            return;
        }
        int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : ((ZenModeControllerImpl) zenModeController).mZenMode;
        boolean z = intValue != 0;
        booleanState.dualTarget = true;
        booleanState.value = z;
        booleanState.state = z ? 2 : 1;
        booleanState.icon = QSTileImpl.ResourceIcon.get(z ? R.drawable.qs_dnd_icon_on : R.drawable.qs_dnd_icon_off);
        booleanState.label = getTileLabel();
        booleanState.secondaryLabel = TextUtils.emptyIfNull(ZenModeConfig.getDescription(this.mContext, intValue != 0, ((ZenModeControllerImpl) this.mController).mConfig, false));
        checkIfRestrictionEnforcedByAdminOnly(booleanState, "no_adjust_volume");
        if (intValue == 1) {
            booleanState.contentDescription = this.mContext.getString(R.string.accessibility_quick_settings_dnd) + ", " + ((Object) booleanState.secondaryLabel);
        } else if (intValue == 2) {
            booleanState.contentDescription = this.mContext.getString(R.string.accessibility_quick_settings_dnd) + ", " + this.mContext.getString(R.string.accessibility_quick_settings_dnd_none_on) + ", " + ((Object) booleanState.secondaryLabel);
        } else if (intValue != 3) {
            booleanState.contentDescription = this.mContext.getString(R.string.accessibility_quick_settings_dnd);
        } else {
            booleanState.contentDescription = this.mContext.getString(R.string.accessibility_quick_settings_dnd) + ", " + this.mContext.getString(R.string.accessibility_quick_settings_dnd_alarms_on) + ", " + ((Object) booleanState.secondaryLabel);
        }
        booleanState.dualLabelContentDescription = this.mContext.getResources().getString(R.string.accessibility_quick_settings_open_settings, getTileLabel());
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        booleanState.forceExpandIcon = getValue() == -1;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUserSwitch(int i) {
        handleRefreshState(null);
        setUserId(i);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return this.mSharedPreferences.getBoolean("DndTileVisible", false);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }
}
