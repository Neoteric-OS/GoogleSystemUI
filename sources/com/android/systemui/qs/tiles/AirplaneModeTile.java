package com.android.systemui.qs.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.sysprop.TelephonyProperties;
import android.widget.Switch;
import androidx.lifecycle.LifecycleKt;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.satellite.SatelliteDialogUtils;
import com.android.systemui.animation.Expandable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.SettingObserver;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.wm.shell.R;
import dagger.Lazy;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AirplaneModeTile extends QSTileImpl {
    public final BroadcastDispatcher mBroadcastDispatcher;
    Job mClickJob;
    public final Lazy mLazyConnectivityManager;
    public boolean mListening;
    public final AnonymousClass2 mReceiver;
    public final AnonymousClass1 mSetting;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.qs.tiles.AirplaneModeTile$2] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.qs.tiles.AirplaneModeTile$1] */
    public AirplaneModeTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BroadcastDispatcher broadcastDispatcher, Lazy lazy, GlobalSettings globalSettings) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.systemui.qs.tiles.AirplaneModeTile.2
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                if ("android.intent.action.AIRPLANE_MODE".equals(intent.getAction())) {
                    AirplaneModeTile.this.refreshState(null);
                }
            }
        };
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mLazyConnectivityManager = lazy;
        this.mSetting = new SettingObserver(globalSettings, this.mHandler) { // from class: com.android.systemui.qs.tiles.AirplaneModeTile.1
            @Override // com.android.systemui.qs.SettingObserver
            public final void handleValueChanged(int i, boolean z) {
                AirplaneModeTile.this.handleRefreshState(Integer.valueOf(i));
            }
        };
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.settings.AIRPLANE_MODE_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 112;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.airplane_mode);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        final boolean z = ((QSTile.BooleanState) this.mState).value;
        MetricsLogger.action(this.mContext, 112, !z);
        if (!z && ((Boolean) TelephonyProperties.in_ecm_mode().orElse(Boolean.FALSE)).booleanValue()) {
            this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.telephony.action.SHOW_NOTICE_ECM_BLOCK_OTHERS"), 0);
            return;
        }
        Job job = this.mClickJob;
        if (job == null || job.isCompleted()) {
            this.mClickJob = SatelliteDialogUtils.mayStartSatelliteWarningDialog(this.mContext, LifecycleKt.getCoroutineScope(getLifecycle()), 2, new Function1() { // from class: com.android.systemui.qs.tiles.AirplaneModeTile$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    AirplaneModeTile airplaneModeTile = AirplaneModeTile.this;
                    airplaneModeTile.getClass();
                    if (!((Boolean) obj).booleanValue()) {
                        return null;
                    }
                    ((ConnectivityManager) airplaneModeTile.mLazyConnectivityManager.get()).setAirplaneMode(!z);
                    return null;
                }
            });
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        AnonymousClass2 anonymousClass2 = this.mReceiver;
        BroadcastDispatcher broadcastDispatcher = this.mBroadcastDispatcher;
        if (z) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            broadcastDispatcher.registerReceiver(anonymousClass2, intentFilter);
        } else {
            broadcastDispatcher.unregisterReceiver(anonymousClass2);
        }
        setListening(z);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        int i;
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        checkIfRestrictionEnforcedByAdminOnly(booleanState, "no_airplane_mode");
        if (obj instanceof Integer) {
            i = ((Integer) obj).intValue();
        } else {
            AnonymousClass1 anonymousClass1 = this.mSetting;
            i = anonymousClass1.mListening ? anonymousClass1.mObservedValue : anonymousClass1.mSettingsProxy.getInt(anonymousClass1.mDefaultValue, anonymousClass1.mSettingName);
        }
        boolean z = i != 0;
        booleanState.value = z;
        booleanState.label = this.mContext.getString(R.string.airplane_mode);
        booleanState.icon = QSTileImpl.ResourceIcon.get(booleanState.value ? R.drawable.qs_airplane_icon_on : R.drawable.qs_airplane_icon_off);
        booleanState.state = z ? 2 : 1;
        booleanState.contentDescription = booleanState.label;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }
}
