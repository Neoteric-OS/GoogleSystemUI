package com.android.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qrcodescanner.controller.QRCodeScannerController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QRCodeScannerTile extends QSTileImpl {
    public final AnonymousClass1 mCallback;
    public final CharSequence mLabel;
    public final QRCodeScannerController mQRCodeScannerController;

    public QRCodeScannerTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, QRCodeScannerController qRCodeScannerController) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mLabel = this.mContext.getString(R.string.qr_code_scanner_title);
        QRCodeScannerController.Callback callback = new QRCodeScannerController.Callback() { // from class: com.android.systemui.qs.tiles.QRCodeScannerTile.1
            @Override // com.android.systemui.qrcodescanner.controller.QRCodeScannerController.Callback
            public final void onQRCodeScannerActivityChanged() {
                QRCodeScannerTile.this.refreshState(null);
            }
        };
        this.mQRCodeScannerController = qRCodeScannerController;
        qRCodeScannerController.observe(this.mLifecycle, callback);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 0;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mLabel;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        Intent intent = this.mQRCodeScannerController.mIntent;
        if (intent == null) {
            Log.e("QRCodeScanner", "Expected a non-null intent");
        } else {
            this.mActivityStarter.startActivity(intent, true, expandable == null ? null : expandable.activityTransitionController(32), true);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleDestroy() {
        super.handleDestroy();
        this.mQRCodeScannerController.unregisterQRCodeScannerChangeObservers(0);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleInitialize() {
        this.mQRCodeScannerController.registerQRCodeScannerChangeObservers(0);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        String string = this.mContext.getString(R.string.qr_code_scanner_title);
        state.label = string;
        state.contentDescription = string;
        state.icon = QSTileImpl.ResourceIcon.get(R.drawable.ic_qr_code_scanner);
        boolean isAbleToLaunchScannerActivity = this.mQRCodeScannerController.isAbleToLaunchScannerActivity();
        state.state = isAbleToLaunchScannerActivity ? 1 : 0;
        state.secondaryLabel = !isAbleToLaunchScannerActivity ? this.mContext.getString(R.string.qr_code_scanner_updating_secondary_label) : null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return this.mQRCodeScannerController.isCameraAvailable();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        QSTile.State state = new QSTile.State();
        state.handlesLongClick = false;
        return state;
    }
}
