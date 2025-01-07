package com.android.systemui.qs.tiles;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.Prefs;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.DataSaverController$Listener;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DataSaverTile extends QSTileImpl implements DataSaverController$Listener {
    public final DataSaverControllerImpl mDataSaverController;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public final SystemUIDialog.Factory mSystemUIDialogFactory;

    public DataSaverTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, DataSaverControllerImpl dataSaverControllerImpl, DialogTransitionAnimator dialogTransitionAnimator, SystemUIDialog.Factory factory) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mDataSaverController = dataSaverControllerImpl;
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mSystemUIDialogFactory = factory;
        dataSaverControllerImpl.observe(this.mLifecycle, this);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.settings.DATA_SAVER_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 284;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.data_saver);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(final Expandable expandable) {
        if (((QSTile.BooleanState) this.mState).value || Prefs.getBoolean(this.mContext, "QsDataSaverDialogShown")) {
            toggleDataSaver();
        } else {
            this.mUiHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.DataSaverTile$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    final DataSaverTile dataSaverTile = DataSaverTile.this;
                    Expandable expandable2 = expandable;
                    SystemUIDialog create = dataSaverTile.mSystemUIDialogFactory.create();
                    create.setTitle(android.R.string.data_usage_warning_body);
                    create.setMessage(android.R.string.data_usage_restricted_body);
                    create.setPositiveButton(android.R.string.data_usage_restricted_title, new DialogInterface.OnClickListener() { // from class: com.android.systemui.qs.tiles.DataSaverTile$$ExternalSyntheticLambda1
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            DataSaverTile dataSaverTile2 = DataSaverTile.this;
                            dataSaverTile2.toggleDataSaver();
                            Prefs.putBoolean(dataSaverTile2.mContext, "QsDataSaverDialogShown", true);
                        }
                    });
                    create.setNeutralButton(android.R.string.cancel, null, true);
                    SystemUIDialog.setShowForAllUsers(create);
                    if (expandable2 == null) {
                        create.show();
                        return;
                    }
                    DialogTransitionAnimator.Controller m = BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "start_data_saver", expandable2);
                    if (m != null) {
                        dataSaverTile.mDialogTransitionAnimator.show(create, m, false);
                    } else {
                        create.show();
                    }
                }
            });
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        boolean booleanValue = obj instanceof Boolean ? ((Boolean) obj).booleanValue() : this.mDataSaverController.mPolicyManager.getRestrictBackground();
        booleanState.value = booleanValue;
        booleanState.state = booleanValue ? 2 : 1;
        String string = this.mContext.getString(R.string.data_saver);
        booleanState.label = string;
        booleanState.contentDescription = string;
        booleanState.icon = QSTileImpl.ResourceIcon.get(booleanState.value ? R.drawable.qs_data_saver_icon_on : R.drawable.qs_data_saver_icon_off);
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.statusbar.policy.DataSaverController$Listener
    public final void onDataSaverChanged(boolean z) {
        refreshState(Boolean.valueOf(z));
    }

    public final void toggleDataSaver() {
        QSTile.BooleanState booleanState = (QSTile.BooleanState) this.mState;
        DataSaverControllerImpl dataSaverControllerImpl = this.mDataSaverController;
        booleanState.value = !dataSaverControllerImpl.mPolicyManager.getRestrictBackground();
        dataSaverControllerImpl.setDataSaverEnabled(((QSTile.BooleanState) this.mState).value);
        refreshState(Boolean.valueOf(((QSTile.BooleanState) this.mState).value));
    }
}
