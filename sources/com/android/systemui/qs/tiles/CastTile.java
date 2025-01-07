package com.android.systemui.qs.tiles;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaRouter;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import androidx.lifecycle.LifecycleKt;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
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
import com.android.systemui.statusbar.connectivity.IconState;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.SignalCallback;
import com.android.systemui.statusbar.connectivity.WifiIndicators;
import com.android.systemui.statusbar.pipeline.shared.data.model.DefaultConnectionModel;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl;
import com.android.systemui.statusbar.policy.CastController$Callback;
import com.android.systemui.statusbar.policy.CastControllerImpl;
import com.android.systemui.statusbar.policy.CastDevice;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CastTile extends QSTileImpl {
    public boolean mCastTransportAllowed;
    public final CastControllerImpl mController;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public final AnonymousClass2 mHotspotCallback;
    public boolean mHotspotConnected;
    public final KeyguardStateController mKeyguard;
    public final CastTile$$ExternalSyntheticLambda0 mNetworkModelConsumer;
    public final AnonymousClass1 mSignalCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Callback implements CastController$Callback, KeyguardStateController.Callback {
        public Callback() {
        }

        @Override // com.android.systemui.statusbar.policy.CastController$Callback
        public final void onCastDevicesChanged() {
            CastTile.this.refreshState(null);
        }

        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            CastTile.this.refreshState(null);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DialogHolder {
        public Dialog mDialog;
    }

    static {
        new Intent("android.settings.CAST_SETTINGS");
    }

    public CastTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, CastControllerImpl castControllerImpl, KeyguardStateController keyguardStateController, NetworkController networkController, HotspotController hotspotController, DialogTransitionAnimator dialogTransitionAnimator, ConnectivityRepositoryImpl connectivityRepositoryImpl, TileJavaAdapter tileJavaAdapter, FeatureFlags featureFlags) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        Callback callback = new Callback();
        Consumer consumer = new Consumer() { // from class: com.android.systemui.qs.tiles.CastTile$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CastTile castTile = CastTile.this;
                DefaultConnectionModel defaultConnectionModel = (DefaultConnectionModel) obj;
                castTile.getClass();
                boolean z = (defaultConnectionModel.wifi.isDefault || defaultConnectionModel.ethernet.isDefault) && !defaultConnectionModel.mobile.isDefault;
                if (z != castTile.mCastTransportAllowed) {
                    castTile.mCastTransportAllowed = z;
                    if (castTile.mHotspotConnected) {
                        return;
                    }
                    castTile.refreshState(null);
                }
            }
        };
        SignalCallback signalCallback = new SignalCallback() { // from class: com.android.systemui.qs.tiles.CastTile.1
            @Override // com.android.systemui.statusbar.connectivity.SignalCallback
            public final void setWifiIndicators(WifiIndicators wifiIndicators) {
                IconState iconState;
                boolean z = wifiIndicators.enabled && (iconState = wifiIndicators.qsIcon) != null && iconState.visible;
                CastTile castTile = CastTile.this;
                if (z != castTile.mCastTransportAllowed) {
                    castTile.mCastTransportAllowed = z;
                    if (castTile.mHotspotConnected) {
                        return;
                    }
                    castTile.refreshState(null);
                }
            }
        };
        HotspotController.Callback callback2 = new HotspotController.Callback() { // from class: com.android.systemui.qs.tiles.CastTile.2
            @Override // com.android.systemui.statusbar.policy.HotspotController.Callback
            public final void onHotspotChanged(int i, boolean z) {
                boolean z2 = z && i > 0;
                CastTile castTile = CastTile.this;
                if (z2 != castTile.mHotspotConnected) {
                    castTile.mHotspotConnected = z2;
                    if (castTile.mCastTransportAllowed) {
                        return;
                    }
                    castTile.refreshState(null);
                }
            }
        };
        this.mController = castControllerImpl;
        this.mKeyguard = keyguardStateController;
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        castControllerImpl.getClass();
        castControllerImpl.observe(this.mLifecycle, callback);
        keyguardStateController.getClass();
        keyguardStateController.observe(this.mLifecycle, callback);
        if (((FeatureFlagsClassicRelease) featureFlags).isEnabled(Flags.SIGNAL_CALLBACK_DEPRECATION)) {
            ReadonlyStateFlow readonlyStateFlow = connectivityRepositoryImpl.defaultConnections;
            tileJavaAdapter.getClass();
            BuildersKt.launch$default(LifecycleKt.getCoroutineScope(getLifecycle()), null, null, new TileJavaAdapter$bind$1(this, readonlyStateFlow, consumer, null), 3);
        } else {
            networkController.getClass();
            networkController.observe(this.mLifecycle, signalCallback);
        }
        hotspotController.getClass();
        hotspotController.observe(this.mLifecycle, callback2);
    }

    public final List getActiveDevices() {
        ArrayList arrayList = new ArrayList();
        for (CastDevice castDevice : this.mController.getCastDevices()) {
            if (castDevice.isCasting) {
                arrayList.add(castDevice);
            }
        }
        return arrayList;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.settings.CAST_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 114;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_cast_title);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        if (((QSTile.BooleanState) this.mState).state == 0) {
            return;
        }
        List activeDevices = getActiveDevices();
        List activeDevices2 = getActiveDevices();
        if (!activeDevices2.isEmpty() && !(((CastDevice) ((ArrayList) activeDevices2).get(0)).tag instanceof MediaRouter.RouteInfo)) {
            this.mController.stopCasting((CastDevice) ((ArrayList) activeDevices).get(0));
        } else if (((KeyguardStateControllerImpl) this.mKeyguard).mShowing) {
            this.mActivityStarter.postQSRunnableDismissingKeyguard(new Runnable() { // from class: com.android.systemui.qs.tiles.CastTile$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CastTile castTile = CastTile.this;
                    castTile.getClass();
                    castTile.mUiHandler.post(new CastTile$$ExternalSyntheticLambda2(castTile, null));
                }
            });
        } else {
            this.mUiHandler.post(new CastTile$$ExternalSyntheticLambda2(this, expandable));
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (this.DEBUG) {
            Log.d(this.TAG, KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("handleSetListening ", z));
        }
        if (z) {
            return;
        }
        synchronized (this.mController.mDiscoveringLock) {
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        String string = this.mContext.getString(R.string.quick_settings_cast_title);
        booleanState.label = string;
        booleanState.contentDescription = string;
        booleanState.stateDescription = "";
        booleanState.value = false;
        Iterator it = this.mController.getCastDevices().iterator();
        boolean z = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CastDevice castDevice = (CastDevice) it.next();
            CastDevice.CastState castState = castDevice.state;
            if (castState == CastDevice.CastState.Connected) {
                booleanState.value = true;
                String str = castDevice.name;
                if (str == null) {
                    str = this.mContext.getString(R.string.quick_settings_cast_device_default_name);
                }
                booleanState.secondaryLabel = str;
                booleanState.stateDescription = ((Object) booleanState.stateDescription) + "," + this.mContext.getString(R.string.accessibility_cast_name, booleanState.label);
                z = false;
            } else if (castState == CastDevice.CastState.Connecting) {
                z = true;
            }
        }
        if (z && !booleanState.value) {
            booleanState.secondaryLabel = this.mContext.getString(R.string.quick_settings_connecting);
        }
        booleanState.icon = QSTileImpl.ResourceIcon.get(booleanState.value ? R.drawable.ic_cast_connected : R.drawable.ic_cast);
        if ((this.mCastTransportAllowed || this.mHotspotConnected) || booleanState.value) {
            boolean z2 = booleanState.value;
            booleanState.state = z2 ? 2 : 1;
            if (!z2) {
                booleanState.secondaryLabel = "";
            }
            booleanState.expandedAccessibilityClassName = Button.class.getName();
            List activeDevices = getActiveDevices();
            booleanState.forceExpandIcon = activeDevices.isEmpty() || (((CastDevice) ((ArrayList) activeDevices).get(0)).tag instanceof MediaRouter.RouteInfo);
        } else {
            booleanState.state = 0;
            booleanState.secondaryLabel = this.mContext.getString(R.string.quick_settings_cast_no_network);
            booleanState.forceExpandIcon = false;
        }
        booleanState.stateDescription = ((Object) booleanState.stateDescription) + ", " + ((Object) booleanState.secondaryLabel);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUserSwitch(int i) {
        handleRefreshState(null);
        this.mController.mMediaRouter.rebindAsUser(i);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.handlesLongClick = false;
        return booleanState;
    }
}
