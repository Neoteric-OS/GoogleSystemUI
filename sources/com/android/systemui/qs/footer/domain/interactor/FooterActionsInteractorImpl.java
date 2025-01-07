package com.android.systemui.qs.footer.domain.interactor;

import android.app.admin.DevicePolicyEventLogger;
import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.view.View;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.FgsManagerController;
import com.android.systemui.qs.QSSecurityFooterUtils;
import com.android.systemui.qs.footer.data.repository.ForegroundServicesRepositoryImpl;
import com.android.systemui.security.data.repository.SecurityRepositoryImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.SecurityControllerImpl;
import com.android.systemui.user.data.repository.UserSwitcherRepositoryImpl;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.wm.shell.R;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FooterActionsInteractorImpl implements FooterActionsInteractor {
    public final ActivityStarter activityStarter;
    public final Flow deviceMonitoringDialogRequests;
    public final DeviceProvisionedController deviceProvisionedController;
    public final FgsManagerController fgsManagerController;
    public final Flow foregroundServicesCount;
    public final ChannelFlowTransformLatest hasNewForegroundServices;
    public final MetricsLogger metricsLogger;
    public final QSSecurityFooterUtils qsSecurityFooterUtils;
    public final FooterActionsInteractorImpl$special$$inlined$map$1 securityButtonConfig;
    public final UiEventLogger uiEventLogger;
    public final UserSwitcherInteractor userSwitcherInteractor;
    public final Flow userSwitcherStatus;

    public FooterActionsInteractorImpl(ActivityStarter activityStarter, MetricsLogger metricsLogger, UiEventLogger uiEventLogger, DeviceProvisionedController deviceProvisionedController, QSSecurityFooterUtils qSSecurityFooterUtils, FgsManagerController fgsManagerController, UserSwitcherInteractor userSwitcherInteractor, SecurityRepositoryImpl securityRepositoryImpl, ForegroundServicesRepositoryImpl foregroundServicesRepositoryImpl, UserSwitcherRepositoryImpl userSwitcherRepositoryImpl, BroadcastDispatcher broadcastDispatcher, CoroutineDispatcher coroutineDispatcher) {
        this.activityStarter = activityStarter;
        this.metricsLogger = metricsLogger;
        this.uiEventLogger = uiEventLogger;
        this.deviceProvisionedController = deviceProvisionedController;
        this.qsSecurityFooterUtils = qSSecurityFooterUtils;
        this.fgsManagerController = fgsManagerController;
        this.userSwitcherInteractor = userSwitcherInteractor;
        this.securityButtonConfig = new FooterActionsInteractorImpl$special$$inlined$map$1(securityRepositoryImpl.security, coroutineDispatcher, this);
        this.foregroundServicesCount = foregroundServicesRepositoryImpl.foregroundServicesCount;
        this.hasNewForegroundServices = foregroundServicesRepositoryImpl.hasNewChanges;
        this.userSwitcherStatus = userSwitcherRepositoryImpl.userSwitcherStatus;
        this.deviceMonitoringDialogRequests = broadcastDispatcher.broadcastFlow(new IntentFilter("android.app.action.SHOW_DEVICE_MONITORING_DIALOG"), UserHandle.ALL);
    }

    public final void showDeviceMonitoringDialog(final Context context, final Expandable expandable) {
        final QSSecurityFooterUtils qSSecurityFooterUtils = this.qsSecurityFooterUtils;
        qSSecurityFooterUtils.mShouldUseSettingsButton.set(false);
        qSSecurityFooterUtils.mBgHandler.post(new Runnable() { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda29
            @Override // java.lang.Runnable
            public final void run() {
                final QSSecurityFooterUtils qSSecurityFooterUtils2 = QSSecurityFooterUtils.this;
                final Context context2 = context;
                final Expandable expandable2 = expandable;
                final String settingsButton = qSSecurityFooterUtils2.getSettingsButton();
                final View createDialogView = qSSecurityFooterUtils2.createDialogView(context2);
                qSSecurityFooterUtils2.mMainHandler.post(new Runnable() { // from class: com.android.systemui.qs.QSSecurityFooterUtils$$ExternalSyntheticLambda30
                    @Override // java.lang.Runnable
                    public final void run() {
                        QSSecurityFooterUtils qSSecurityFooterUtils3 = QSSecurityFooterUtils.this;
                        Context context3 = context2;
                        String str = settingsButton;
                        View view = createDialogView;
                        Expandable expandable3 = expandable2;
                        qSSecurityFooterUtils3.getClass();
                        SystemUIDialog systemUIDialog = new SystemUIDialog(context3, 0, true);
                        qSSecurityFooterUtils3.mDialog = systemUIDialog;
                        systemUIDialog.requestWindowFeature(1);
                        qSSecurityFooterUtils3.mDialog.setButton(-1, qSSecurityFooterUtils3.mContext.getString(R.string.ok), qSSecurityFooterUtils3);
                        SystemUIDialog systemUIDialog2 = qSSecurityFooterUtils3.mDialog;
                        if (!qSSecurityFooterUtils3.mShouldUseSettingsButton.get()) {
                            SecurityControllerImpl securityControllerImpl = (SecurityControllerImpl) qSSecurityFooterUtils3.mSecurityController;
                            securityControllerImpl.getClass();
                            str = securityControllerImpl.mDevicePolicyManager.getProfileOwnerOrDeviceOwnerSupervisionComponent(new UserHandle(securityControllerImpl.mCurrentUserId)) != null ? qSSecurityFooterUtils3.mContext.getString(R.string.monitoring_button_view_controls) : null;
                        }
                        systemUIDialog2.setButton(-2, str, qSSecurityFooterUtils3);
                        qSSecurityFooterUtils3.mDialog.setView(view);
                        DialogTransitionAnimator.Controller m = expandable3 != null ? BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "managed_device_info", expandable3) : null;
                        if (m != null) {
                            qSSecurityFooterUtils3.mDialogTransitionAnimator.show(qSSecurityFooterUtils3.mDialog, m, false);
                        } else {
                            qSSecurityFooterUtils3.mDialog.show();
                        }
                    }
                });
            }
        });
        if (expandable != null) {
            DevicePolicyEventLogger.createEvent(57).write();
        }
    }
}
