package com.android.systemui.qs.tiles;

import android.content.Intent;
import android.net.TetheringManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.ConcurrentUtils;
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
import com.android.systemui.statusbar.policy.DataSaverController$Listener;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.HotspotControllerImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HotspotTile extends QSTileImpl {
    public final DataSaverControllerImpl mDataSaverController;
    public final HotspotController mHotspotController;
    public boolean mListening;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CallbackInfo {
        public boolean isDataSaverEnabled;
        public boolean isHotspotEnabled;
        public int numConnectedDevices;

        public final String toString() {
            StringBuilder sb = new StringBuilder("CallbackInfo[isHotspotEnabled=");
            sb.append(this.isHotspotEnabled);
            sb.append(",numConnectedDevices=");
            sb.append(this.numConnectedDevices);
            sb.append(",isDataSaverEnabled=");
            return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.isDataSaverEnabled, ']');
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HotspotAndDataSaverCallbacks implements HotspotController.Callback, DataSaverController$Listener {
        public final CallbackInfo mCallbackInfo = new CallbackInfo();

        public HotspotAndDataSaverCallbacks() {
        }

        @Override // com.android.systemui.statusbar.policy.DataSaverController$Listener
        public final void onDataSaverChanged(boolean z) {
            CallbackInfo callbackInfo = this.mCallbackInfo;
            callbackInfo.isDataSaverEnabled = z;
            HotspotTile.this.refreshState(callbackInfo);
        }

        @Override // com.android.systemui.statusbar.policy.HotspotController.Callback
        public final void onHotspotAvailabilityChanged(boolean z) {
            if (z) {
                return;
            }
            HotspotTile hotspotTile = HotspotTile.this;
            Log.d(hotspotTile.TAG, "Tile removed. Hotspot no longer available");
            ((QSHostAdapter) hotspotTile.mHost).removeTile(hotspotTile.mTileSpec);
        }

        @Override // com.android.systemui.statusbar.policy.HotspotController.Callback
        public final void onHotspotChanged(int i, boolean z) {
            CallbackInfo callbackInfo = this.mCallbackInfo;
            callbackInfo.isHotspotEnabled = z;
            callbackInfo.numConnectedDevices = i;
            HotspotTile.this.refreshState(callbackInfo);
        }
    }

    public HotspotTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, HotspotController hotspotController, DataSaverControllerImpl dataSaverControllerImpl) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        HotspotAndDataSaverCallbacks hotspotAndDataSaverCallbacks = new HotspotAndDataSaverCallbacks();
        this.mHotspotController = hotspotController;
        this.mDataSaverController = dataSaverControllerImpl;
        hotspotController.getClass();
        hotspotController.observe(this.mLifecycle, hotspotAndDataSaverCallbacks);
        dataSaverControllerImpl.getClass();
        dataSaverControllerImpl.observe(this.mLifecycle, hotspotAndDataSaverCallbacks);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("com.android.settings.WIFI_TETHER_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 120;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_hotspot_label);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        boolean z = ((QSTile.BooleanState) this.mState).value;
        if (z || !this.mDataSaverController.mPolicyManager.getRestrictBackground()) {
            refreshState(z ? null : QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING);
            final HotspotControllerImpl hotspotControllerImpl = (HotspotControllerImpl) this.mHotspotController;
            if (hotspotControllerImpl.mWaitingForTerminalState) {
                if (HotspotControllerImpl.DEBUG) {
                    Log.d("HotspotController", "Ignoring setHotspotEnabled; waiting for terminal state.");
                }
            } else {
                if (z) {
                    hotspotControllerImpl.mTetheringManager.stopTethering(0);
                    return;
                }
                hotspotControllerImpl.mWaitingForTerminalState = true;
                if (HotspotControllerImpl.DEBUG) {
                    Log.d("HotspotController", "Starting tethering");
                }
                hotspotControllerImpl.mTetheringManager.startTethering(new TetheringManager.TetheringRequest.Builder(0).setShouldShowEntitlementUi(false).build(), ConcurrentUtils.DIRECT_EXECUTOR, new TetheringManager.StartTetheringCallback() { // from class: com.android.systemui.statusbar.policy.HotspotControllerImpl.2
                    public AnonymousClass2() {
                    }

                    public final void onTetheringFailed(int i) {
                        if (HotspotControllerImpl.DEBUG) {
                            Log.d("HotspotController", "onTetheringFailed");
                        }
                        HotspotControllerImpl.this.maybeResetSoftApState();
                        HotspotControllerImpl.this.fireHotspotChangedCallback();
                    }
                });
            }
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        if (z) {
            refreshState(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x003b  */
    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleUpdateState(com.android.systemui.plugins.qs.QSTile.State r8, java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.HotspotTile.handleUpdateState(com.android.systemui.plugins.qs.QSTile$State, java.lang.Object):void");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return ((HotspotControllerImpl) this.mHotspotController).isHotspotSupported();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }
}
