package com.android.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.safetycenter.SafetyCenterManager;
import android.text.TextUtils;
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
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SensorPrivacyToggleTile extends QSTileImpl implements IndividualSensorPrivacyController.Callback {
    public final Boolean mIsSafetyCenterEnabled;
    public final KeyguardStateController mKeyguard;
    public final IndividualSensorPrivacyController mSensorPrivacyController;

    public SensorPrivacyToggleTile(Handler handler, Looper looper, SafetyCenterManager safetyCenterManager, MetricsLogger metricsLogger, ActivityStarter activityStarter, FalsingManager falsingManager, StatusBarStateController statusBarStateController, QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, QSLogger qSLogger, IndividualSensorPrivacyController individualSensorPrivacyController, KeyguardStateController keyguardStateController) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mSensorPrivacyController = individualSensorPrivacyController;
        this.mKeyguard = keyguardStateController;
        this.mIsSafetyCenterEnabled = Boolean.valueOf(safetyCenterManager.isSafetyCenterEnabled());
        individualSensorPrivacyController.observe(this.mLifecycle, this);
    }

    public abstract int getIconRes(boolean z);

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return this.mIsSafetyCenterEnabled.booleanValue() ? new Intent("android.settings.PRIVACY_CONTROLS") : new Intent("android.settings.PRIVACY_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 0;
    }

    public abstract String getRestriction();

    public abstract int getSensorId();

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        int sensorId = getSensorId();
        IndividualSensorPrivacyControllerImpl individualSensorPrivacyControllerImpl = (IndividualSensorPrivacyControllerImpl) this.mSensorPrivacyController;
        final boolean isSensorBlocked = individualSensorPrivacyControllerImpl.isSensorBlocked(sensorId);
        if (individualSensorPrivacyControllerImpl.mSensorPrivacyManager.requiresAuthentication()) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguard;
            if (keyguardStateControllerImpl.mSecure && keyguardStateControllerImpl.mShowing) {
                this.mActivityStarter.postQSRunnableDismissingKeyguard(new Runnable() { // from class: com.android.systemui.qs.tiles.SensorPrivacyToggleTile$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        SensorPrivacyToggleTile sensorPrivacyToggleTile = SensorPrivacyToggleTile.this;
                        ((IndividualSensorPrivacyControllerImpl) sensorPrivacyToggleTile.mSensorPrivacyController).setSensorBlocked(1, sensorPrivacyToggleTile.getSensorId(), !isSensorBlocked);
                    }
                });
                return;
            }
        }
        individualSensorPrivacyControllerImpl.setSensorBlocked(1, getSensorId(), !isSensorBlocked);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        boolean isSensorBlocked = obj == null ? ((IndividualSensorPrivacyControllerImpl) this.mSensorPrivacyController).isSensorBlocked(getSensorId()) : ((Boolean) obj).booleanValue();
        checkIfRestrictionEnforcedByAdminOnly(booleanState, getRestriction());
        booleanState.icon = QSTileImpl.ResourceIcon.get(getIconRes(isSensorBlocked));
        booleanState.state = isSensorBlocked ? 1 : 2;
        booleanState.value = !isSensorBlocked;
        booleanState.label = getTileLabel();
        if (isSensorBlocked) {
            booleanState.secondaryLabel = this.mContext.getString(R.string.quick_settings_camera_mic_blocked);
        } else {
            booleanState.secondaryLabel = this.mContext.getString(R.string.quick_settings_camera_mic_available);
        }
        booleanState.contentDescription = TextUtils.concat(booleanState.label, ", ", booleanState.secondaryLabel);
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.statusbar.policy.IndividualSensorPrivacyController.Callback
    public final void onSensorBlockedChanged(int i, boolean z) {
        if (i == getSensorId()) {
            refreshState(Boolean.valueOf(z));
        }
    }
}
