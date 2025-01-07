package com.google.android.systemui.qs.tiles;

import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tiles.BatterySaverTile;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatterySaverTileGoogle extends BatterySaverTile {
    public final UserTracker mContentResolverProvider;

    public BatterySaverTileGoogle(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BatteryController batteryController, SecureSettings secureSettings, UserTracker userTracker) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, batteryController, secureSettings);
        this.mContentResolverProvider = userTracker;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0065, code lost:
    
        if (r8.getBoolean("is_flipendo_aggressive", false) != false) goto L24;
     */
    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleUpdateState(com.android.systemui.plugins.qs.QSTile.State r7, java.lang.Object r8) {
        /*
            r6 = this;
            com.android.systemui.plugins.qs.QSTile$BooleanState r7 = (com.android.systemui.plugins.qs.QSTile.BooleanState) r7
            boolean r8 = r6.mPluggedIn
            r0 = 1
            r1 = 2
            r2 = 0
            if (r8 == 0) goto Lb
            r8 = r2
            goto L12
        Lb:
            boolean r8 = r6.mPowerSave
            if (r8 == 0) goto L11
            r8 = r1
            goto L12
        L11:
            r8 = r0
        L12:
            r7.state = r8
            boolean r8 = r6.mPowerSave
            if (r8 == 0) goto L1c
            r8 = 2131233323(0x7f080a2b, float:1.808278E38)
            goto L1f
        L1c:
            r8 = 2131233322(0x7f080a2a, float:1.8082778E38)
        L1f:
            com.android.systemui.plugins.qs.QSTile$Icon r8 = com.android.systemui.qs.tileimpl.QSTileImpl.ResourceIcon.get(r8)
            r7.icon = r8
            android.content.Context r8 = r6.mContext
            r3 = 2131951987(0x7f130173, float:1.9540404E38)
            java.lang.String r8 = r8.getString(r3)
            r7.label = r8
            java.lang.String r3 = ""
            r7.secondaryLabel = r3
            r7.contentDescription = r8
            boolean r8 = r6.mPowerSave
            r7.value = r8
            java.lang.Class<android.widget.Switch> r8 = android.widget.Switch.class
            java.lang.String r8 = r8.getName()
            r7.expandedAccessibilityClassName = r8
            int r8 = r7.state
            if (r8 != r1) goto L86
            com.android.systemui.settings.UserTracker r8 = r6.mContentResolverProvider
            com.android.systemui.settings.UserTrackerImpl r8 = (com.android.systemui.settings.UserTrackerImpl) r8
            android.content.Context r8 = r8.getUserContext()
            android.content.ContentResolver r8 = r8.getContentResolver()
            java.lang.String r1 = "com.google.android.flipendo.api"
            java.lang.String r3 = "get_flipendo_state"
            android.os.Bundle r4 = android.os.Bundle.EMPTY     // Catch: java.lang.Exception -> L68
            r5 = 0
            android.os.Bundle r8 = r8.call(r1, r3, r5, r4)     // Catch: java.lang.Exception -> L68
            if (r8 == 0) goto L6a
            java.lang.String r1 = "is_flipendo_aggressive"
            boolean r8 = r8.getBoolean(r1, r2)     // Catch: java.lang.Exception -> L68
            if (r8 == 0) goto L6a
            goto L6b
        L68:
            r8 = move-exception
            goto L6d
        L6a:
            r0 = r2
        L6b:
            r2 = r0
            goto L74
        L6d:
            java.lang.String r0 = "PowerUtils"
            java.lang.String r1 = "isFlipendoSelected() failed"
            android.util.Log.e(r0, r1, r8)
        L74:
            android.content.Context r6 = r6.mContext
            if (r2 == 0) goto L7c
            r8 = 2131952647(0x7f130407, float:1.9541743E38)
            goto L7f
        L7c:
            r8 = 2131954250(0x7f130a4a, float:1.9544994E38)
        L7f:
            java.lang.String r6 = r6.getString(r8)
            r7.secondaryLabel = r6
            goto L88
        L86:
            r7.secondaryLabel = r3
        L88:
            java.lang.CharSequence r6 = r7.secondaryLabel
            r7.stateDescription = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.qs.tiles.BatterySaverTileGoogle.handleUpdateState(com.android.systemui.plugins.qs.QSTile$State, java.lang.Object):void");
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onExtremeBatterySaverChanged(boolean z) {
    }
}
