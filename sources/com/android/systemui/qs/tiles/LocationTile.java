package com.android.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.LocationControllerImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LocationTile extends QSTileImpl {
    public final LocationController mController;
    public final KeyguardStateController mKeyguard;
    public final PanelInteractor mPanelInteractor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Callback implements LocationController.LocationChangeCallback, KeyguardStateController.Callback {
        public Callback() {
        }

        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            LocationTile.this.refreshState(null);
        }

        @Override // com.android.systemui.statusbar.policy.LocationController.LocationChangeCallback
        public final void onLocationSettingsChanged(boolean z) {
            LocationTile.this.refreshState(null);
        }
    }

    public LocationTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, LocationController locationController, KeyguardStateController keyguardStateController, PanelInteractor panelInteractor) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        Callback callback = new Callback();
        this.mController = locationController;
        this.mKeyguard = keyguardStateController;
        this.mPanelInteractor = panelInteractor;
        locationController.getClass();
        locationController.observe(this.mLifecycle, callback);
        keyguardStateController.getClass();
        keyguardStateController.observe(this.mLifecycle, callback);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 122;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_location_label);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguard;
        if (keyguardStateControllerImpl.mSecure && keyguardStateControllerImpl.mShowing) {
            this.mActivityStarter.postQSRunnableDismissingKeyguard(new Runnable() { // from class: com.android.systemui.qs.tiles.LocationTile$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LocationTile locationTile = LocationTile.this;
                    boolean z = ((QSTile.BooleanState) locationTile.mState).value;
                    ((PanelInteractorImpl) locationTile.mPanelInteractor).shadeController.postAnimateExpandQs();
                    ((LocationControllerImpl) locationTile.mController).setLocationEnabled(!z);
                }
            });
        } else {
            ((LocationControllerImpl) this.mController).setLocationEnabled(!((QSTile.BooleanState) this.mState).value);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        booleanState.value = ((LocationControllerImpl) this.mController).isLocationEnabled$1();
        checkIfRestrictionEnforcedByAdminOnly(booleanState, "no_share_location");
        if (!booleanState.disabledByPolicy) {
            checkIfRestrictionEnforcedByAdminOnly(booleanState, "no_config_location");
        }
        booleanState.icon = QSTileImpl.ResourceIcon.get(booleanState.value ? R.drawable.qs_location_icon_on : R.drawable.qs_location_icon_off);
        String string = this.mContext.getString(R.string.quick_settings_location_label);
        booleanState.label = string;
        booleanState.contentDescription = string;
        booleanState.state = booleanState.value ? 2 : 1;
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }
}
