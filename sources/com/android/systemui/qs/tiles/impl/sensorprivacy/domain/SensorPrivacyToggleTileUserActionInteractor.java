package com.android.systemui.qs.tiles.impl.sensorprivacy.domain;

import android.content.Intent;
import android.safetycenter.SafetyCenterManager;
import com.android.systemui.animation.Expandable;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.sensorprivacy.domain.model.SensorPrivacyToggleTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyControllerImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensorPrivacyToggleTileUserActionInteractor implements QSTileUserActionInteractor {
    public final ActivityStarter activityStarter;
    public final KeyguardInteractor keyguardInteractor;
    public Intent longClickIntent;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;
    public final SafetyCenterManager safetyCenterManager;
    public final int sensorId;
    public final IndividualSensorPrivacyController sensorPrivacyController;

    public SensorPrivacyToggleTileUserActionInteractor(QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl, KeyguardInteractor keyguardInteractor, ActivityStarter activityStarter, IndividualSensorPrivacyController individualSensorPrivacyController, SafetyCenterManager safetyCenterManager, int i) {
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
        this.keyguardInteractor = keyguardInteractor;
        this.activityStarter = activityStarter;
        this.sensorPrivacyController = individualSensorPrivacyController;
        this.safetyCenterManager = safetyCenterManager;
        this.sensorId = i;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        boolean z = qSTileUserAction instanceof QSTileUserAction.Click;
        Unit unit = Unit.INSTANCE;
        if (z) {
            SensorPrivacyToggleTileModel sensorPrivacyToggleTileModel = (SensorPrivacyToggleTileModel) qSTileInput.data;
            IndividualSensorPrivacyControllerImpl individualSensorPrivacyControllerImpl = (IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController;
            boolean requiresAuthentication = individualSensorPrivacyControllerImpl.mSensorPrivacyManager.requiresAuthentication();
            final boolean z2 = sensorPrivacyToggleTileModel.isBlocked;
            if (requiresAuthentication) {
                KeyguardInteractor keyguardInteractor = this.keyguardInteractor;
                if (((Boolean) ((StateFlowImpl) keyguardInteractor.isKeyguardDismissible).getValue()).booleanValue() && keyguardInteractor.isKeyguardShowing()) {
                    this.activityStarter.postQSRunnableDismissingKeyguard(new Runnable() { // from class: com.android.systemui.qs.tiles.impl.sensorprivacy.domain.SensorPrivacyToggleTileUserActionInteractor$handleInput$2$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            SensorPrivacyToggleTileUserActionInteractor sensorPrivacyToggleTileUserActionInteractor = SensorPrivacyToggleTileUserActionInteractor.this;
                            ((IndividualSensorPrivacyControllerImpl) sensorPrivacyToggleTileUserActionInteractor.sensorPrivacyController).setSensorBlocked(1, sensorPrivacyToggleTileUserActionInteractor.sensorId, !z2);
                        }
                    });
                    return unit;
                }
            }
            individualSensorPrivacyControllerImpl.setSensorBlocked(1, this.sensorId, !z2);
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            if (this.longClickIntent == null) {
                this.longClickIntent = new Intent(this.safetyCenterManager.isSafetyCenterEnabled() ? "android.settings.PRIVACY_CONTROLS" : "android.settings.PRIVACY_SETTINGS");
            }
            Expandable expandable = qSTileUserAction.getExpandable();
            Intent intent = this.longClickIntent;
            if (intent == null) {
                intent = null;
            }
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, expandable, intent);
        } else {
            boolean z3 = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return unit;
    }
}
