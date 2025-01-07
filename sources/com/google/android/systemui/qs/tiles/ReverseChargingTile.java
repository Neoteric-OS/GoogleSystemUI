package com.google.android.systemui.qs.tiles;

import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IThermalEventListener;
import android.os.IThermalService;
import android.os.Looper;
import android.os.Temperature;
import android.provider.Settings;
import android.util.Log;
import android.widget.Switch;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.Prefs;
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
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReverseChargingTile extends QSTileImpl implements BatteryController.BatteryStateChangeCallback {
    public static final boolean DEBUG = Log.isLoggable("ReverseChargingTile", 3);
    public final BatteryController mBatteryController;
    public int mBatteryLevel;
    public boolean mListening;
    public boolean mOverHeat;
    public boolean mPowerSave;
    public boolean mReverse;
    public boolean mRtxDisabled;
    public final AnonymousClass2 mSettingsObserver;
    public final AnonymousClass1 mThermalEventListener;
    public final IThermalService mThermalService;
    public int mThresholdLevel;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.qs.tiles.ReverseChargingTile$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.qs.tiles.ReverseChargingTile$2] */
    public ReverseChargingTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BatteryController batteryController, IThermalService iThermalService) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mThermalEventListener = new IThermalEventListener.Stub() { // from class: com.google.android.systemui.qs.tiles.ReverseChargingTile.1
            public final void notifyThrottling(Temperature temperature) {
                int status = temperature.getStatus();
                ReverseChargingTile.this.mOverHeat = status >= 5;
                if (ReverseChargingTile.DEBUG) {
                    ExifInterface$$ExternalSyntheticOutline0.m("notifyThrottling(): status=", "ReverseChargingTile", status);
                }
            }
        };
        this.mSettingsObserver = new ContentObserver(this.mHandler) { // from class: com.google.android.systemui.qs.tiles.ReverseChargingTile.2
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                ReverseChargingTile.this.updateThresholdLevel();
            }
        };
        this.mBatteryController = batteryController;
        batteryController.observe(this.mLifecycle, this);
        this.mThermalService = iThermalService;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        Intent intent = new Intent("android.settings.REVERSE_CHARGING_SETTINGS");
        intent.setPackage("com.android.settings");
        return intent;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 0;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.reverse_charging_title);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        if (((QSTile.BooleanState) this.mState).state == 0) {
            return;
        }
        this.mReverse = !this.mReverse;
        if (DEBUG) {
            Log.d("ReverseChargingTile", "handleClick(): rtx=" + (this.mReverse ? 1 : 0) + ",this=" + this);
        }
        this.mBatteryController.setReverseState(this.mReverse);
        QSHostAdapter qSHostAdapter = (QSHostAdapter) this.mHost;
        if (Prefs.getBoolean(qSHostAdapter.getUserContext(), "HasSeenReverseBottomSheet")) {
            return;
        }
        Intent intent = new Intent("android.settings.REVERSE_CHARGING_BOTTOM_SHEET");
        intent.setPackage("com.android.settings");
        this.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
        Prefs.putBoolean(qSHostAdapter.getUserContext(), "HasSeenReverseBottomSheet", true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0051, code lost:
    
        android.util.Log.w("ReverseChargingTile", "isOverHeat(): current skin status = " + r4.getStatus() + ", temperature = " + r4.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0075, code lost:
    
        r5 = true;
     */
    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleSetListening(boolean r9) {
        /*
            r8 = this;
            super.handleSetListening(r9)
            boolean r0 = r8.mListening
            if (r0 != r9) goto L8
            return
        L8:
            r8.mListening = r9
            com.google.android.systemui.qs.tiles.ReverseChargingTile$1 r0 = r8.mThermalEventListener
            com.google.android.systemui.qs.tiles.ReverseChargingTile$2 r1 = r8.mSettingsObserver
            java.lang.String r2 = "ReverseChargingTile"
            if (r9 == 0) goto L90
            r8.updateThresholdLevel()
            android.content.Context r3 = r8.mContext
            android.content.ContentResolver r3 = r3.getContentResolver()
            java.lang.String r4 = "advanced_battery_usage_amount"
            android.net.Uri r4 = android.provider.Settings.Global.getUriFor(r4)
            r5 = 0
            r3.registerContentObserver(r4, r5, r1)
            r1 = 3
            android.os.IThermalService r3 = r8.mThermalService     // Catch: android.os.RemoteException -> L2c
            r3.registerThermalEventListenerWithType(r0, r1)     // Catch: android.os.RemoteException -> L2c
            goto L3e
        L2c:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Could not register thermal event listener, exception: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.e(r2, r0)
        L3e:
            android.os.IThermalService r0 = r8.mThermalService     // Catch: android.os.RemoteException -> L77
            android.os.Temperature[] r0 = r0.getCurrentTemperaturesWithType(r1)     // Catch: android.os.RemoteException -> L77
            int r1 = r0.length     // Catch: android.os.RemoteException -> L77
            r3 = r5
        L46:
            if (r3 >= r1) goto L8d
            r4 = r0[r3]     // Catch: android.os.RemoteException -> L77
            int r6 = r4.getStatus()     // Catch: android.os.RemoteException -> L77
            r7 = 5
            if (r6 < r7) goto L79
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L77
            r0.<init>()     // Catch: android.os.RemoteException -> L77
            java.lang.String r1 = "isOverHeat(): current skin status = "
            r0.append(r1)     // Catch: android.os.RemoteException -> L77
            int r1 = r4.getStatus()     // Catch: android.os.RemoteException -> L77
            r0.append(r1)     // Catch: android.os.RemoteException -> L77
            java.lang.String r1 = ", temperature = "
            r0.append(r1)     // Catch: android.os.RemoteException -> L77
            float r1 = r4.getValue()     // Catch: android.os.RemoteException -> L77
            r0.append(r1)     // Catch: android.os.RemoteException -> L77
            java.lang.String r0 = r0.toString()     // Catch: android.os.RemoteException -> L77
            android.util.Log.w(r2, r0)     // Catch: android.os.RemoteException -> L77
            r5 = 1
            goto L8d
        L77:
            r0 = move-exception
            goto L7c
        L79:
            int r3 = r3 + 1
            goto L46
        L7c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "isOverHeat(): "
            r1.<init>(r3)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.w(r2, r0)
        L8d:
            r8.mOverHeat = r5
            goto Lb1
        L90:
            android.content.Context r3 = r8.mContext
            android.content.ContentResolver r3 = r3.getContentResolver()
            r3.unregisterContentObserver(r1)
            android.os.IThermalService r1 = r8.mThermalService     // Catch: android.os.RemoteException -> L9f
            r1.unregisterThermalEventListener(r0)     // Catch: android.os.RemoteException -> L9f
            goto Lb1
        L9f:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Could not unregister thermal event listener, exception: "
            r1.<init>(r3)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.e(r2, r0)
        Lb1:
            boolean r0 = com.google.android.systemui.qs.tiles.ReverseChargingTile.DEBUG
            if (r0 == 0) goto Le4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "handleSetListening(): rtx="
            r0.<init>(r1)
            boolean r1 = r8.mReverse
            r0.append(r1)
            java.lang.String r1 = ",level="
            r0.append(r1)
            int r1 = r8.mBatteryLevel
            r0.append(r1)
            java.lang.String r1 = ",threshold="
            r0.append(r1)
            int r8 = r8.mThresholdLevel
            r0.append(r8)
            java.lang.String r8 = ",listening="
            r0.append(r8)
            r0.append(r9)
            java.lang.String r8 = r0.toString()
            android.util.Log.d(r2, r8)
        Le4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.qs.tiles.ReverseChargingTile.handleSetListening(boolean):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        boolean z = ((BatteryControllerImpl) this.mBatteryController).mWirelessCharging;
        int i = this.mBatteryLevel <= this.mThresholdLevel ? 1 : 0;
        Object[] objArr = this.mRtxDisabled || this.mOverHeat || this.mPowerSave || z || i != 0;
        boolean z2 = !objArr == true && this.mReverse;
        booleanState.value = z2;
        booleanState.state = objArr == true ? 0 : this.mReverse ? 2 : 1;
        booleanState.icon = QSTileImpl.ResourceIcon.get(z2 ? R.drawable.qs_battery_share_icon_on : R.drawable.qs_battery_share_icon_off);
        CharSequence tileLabel = getTileLabel();
        booleanState.label = tileLabel;
        booleanState.contentDescription = tileLabel;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        booleanState.secondaryLabel = this.mOverHeat ? this.mContext.getString(R.string.too_hot_label) : this.mPowerSave ? this.mContext.getString(R.string.quick_settings_dark_mode_secondary_label_battery_saver) : z ? this.mContext.getString(R.string.wireless_charging_label) : i != 0 ? this.mContext.getString(R.string.low_battery_label) : null;
        if (DEBUG) {
            StringBuilder sb = new StringBuilder("handleUpdateState(): disable=");
            sb.append(this.mRtxDisabled ? 1 : 0);
            sb.append(",ps=");
            ViewPager$$ExternalSyntheticOutline0.m(sb, this.mPowerSave ? 1 : 0, ",wlc=", z ? 1 : 0, ",low=");
            sb.append(i);
            sb.append(",over=");
            sb.append(this.mOverHeat ? 1 : 0);
            sb.append(",rtx=");
            sb.append(this.mReverse ? 1 : 0);
            sb.append(",this=");
            sb.append(this);
            Log.d("ReverseChargingTile", sb.toString());
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return this.mBatteryController.isReverseSupported();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        this.mBatteryLevel = i;
        this.mReverse = this.mBatteryController.isReverseOn();
        if (DEBUG) {
            StringBuilder sb = new StringBuilder("onBatteryLevelChanged(): rtx=");
            sb.append(this.mReverse ? 1 : 0);
            sb.append(",level=");
            sb.append(this.mBatteryLevel);
            sb.append(",threshold=");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, this.mThresholdLevel, "ReverseChargingTile");
        }
        refreshState(null);
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onPowerSaveChanged(boolean z) {
        this.mPowerSave = z;
        refreshState(null);
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onReverseChanged(int i, String str, boolean z) {
        if (DEBUG) {
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(z ? 1 : 0, i, "onReverseChanged(): rtx=", ",level=", ",name=");
            m.append(str);
            m.append(",this=");
            m.append(this);
            Log.d("ReverseChargingTile", m.toString());
        }
        this.mReverse = z;
        this.mRtxDisabled = !z && i == -100;
        refreshState(null);
    }

    public final void updateThresholdLevel() {
        this.mThresholdLevel = Settings.Global.getInt(this.mContext.getContentResolver(), "advanced_battery_usage_amount", 2) * 5;
        if (DEBUG) {
            StringBuilder sb = new StringBuilder("updateThresholdLevel(): rtx=");
            sb.append(this.mReverse ? 1 : 0);
            sb.append(",level=");
            sb.append(this.mBatteryLevel);
            sb.append(",threshold=");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, this.mThresholdLevel, "ReverseChargingTile");
        }
    }
}
