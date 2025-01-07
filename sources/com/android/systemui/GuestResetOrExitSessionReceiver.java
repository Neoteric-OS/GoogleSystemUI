package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.qs.QSUserSwitcherEvent;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GuestResetOrExitSessionReceiver extends BroadcastReceiver {
    public final BroadcastDispatcher mBroadcastDispatcher;
    public SystemUIDialog mExitSessionDialog;
    public final ExitSessionDialogFactory mExitSessionDialogFactory;
    public SystemUIDialog mResetSessionDialog;
    public final ExitSessionDialogFactory mResetSessionDialogFactory;
    public final UserTracker mUserTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExitSessionDialogClickListener implements DialogInterface.OnClickListener {
        public final SystemUIDialog mDialog;
        public final boolean mIsEphemeral;
        public final int mUserId;
        public final UserSwitcherController mUserSwitcherController;

        public ExitSessionDialogClickListener(UserSwitcherController userSwitcherController, boolean z, int i, SystemUIDialog systemUIDialog) {
            this.mUserSwitcherController = userSwitcherController;
            this.mIsEphemeral = z;
            this.mUserId = i;
            this.mDialog = systemUIDialog;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            if (this.mIsEphemeral) {
                if (i == -1) {
                    UserSwitcherController userSwitcherController = this.mUserSwitcherController;
                    userSwitcherController.getMUserSwitcherInteractor().exitGuestUser(this.mUserId, -10000, false);
                    return;
                } else {
                    if (i == -3) {
                        this.mDialog.cancel();
                        return;
                    }
                    return;
                }
            }
            if (i == -1) {
                UserSwitcherController userSwitcherController2 = this.mUserSwitcherController;
                userSwitcherController2.getMUserSwitcherInteractor().exitGuestUser(this.mUserId, -10000, false);
            } else if (i == -2) {
                UserSwitcherController userSwitcherController3 = this.mUserSwitcherController;
                userSwitcherController3.getMUserSwitcherInteractor().exitGuestUser(this.mUserId, -10000, true);
            } else if (i == -3) {
                this.mDialog.cancel();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ResetSessionDialogClickListener implements DialogInterface.OnClickListener {
        public final SystemUIDialog mDialog;
        public final UiEventLogger mUiEventLogger;
        public final int mUserId;
        public final UserSwitcherController mUserSwitcherController;

        public ResetSessionDialogClickListener(UserSwitcherController userSwitcherController, UiEventLogger uiEventLogger, int i, SystemUIDialog systemUIDialog) {
            this.mUserSwitcherController = userSwitcherController;
            this.mUiEventLogger = uiEventLogger;
            this.mUserId = i;
            this.mDialog = systemUIDialog;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            if (i != -1) {
                if (i == -3) {
                    this.mDialog.cancel();
                }
            } else {
                this.mUiEventLogger.log(QSUserSwitcherEvent.QS_USER_GUEST_REMOVE);
                UserSwitcherController userSwitcherController = this.mUserSwitcherController;
                userSwitcherController.getMUserSwitcherInteractor().removeGuestUser(this.mUserId);
            }
        }
    }

    public GuestResetOrExitSessionReceiver(UserTracker userTracker, BroadcastDispatcher broadcastDispatcher, ExitSessionDialogFactory exitSessionDialogFactory, ExitSessionDialogFactory exitSessionDialogFactory2) {
        this.mUserTracker = userTracker;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mResetSessionDialogFactory = exitSessionDialogFactory;
        this.mExitSessionDialogFactory = exitSessionDialogFactory2;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        SystemUIDialog systemUIDialog = this.mResetSessionDialog;
        if (systemUIDialog != null && systemUIDialog.isShowing()) {
            this.mResetSessionDialog.cancel();
            this.mResetSessionDialog = null;
        }
        SystemUIDialog systemUIDialog2 = this.mExitSessionDialog;
        if (systemUIDialog2 != null && systemUIDialog2.isShowing()) {
            this.mExitSessionDialog.cancel();
            this.mExitSessionDialog = null;
        }
        UserInfo userInfo = ((UserTrackerImpl) this.mUserTracker).getUserInfo();
        if (userInfo.isGuest()) {
            if ("android.intent.action.GUEST_RESET".equals(action)) {
                SystemUIDialog create = this.mResetSessionDialogFactory.create(userInfo.id);
                this.mResetSessionDialog = create;
                create.show();
                return;
            }
            if ("android.intent.action.GUEST_EXIT".equals(action)) {
                ExitSessionDialogFactory exitSessionDialogFactory = this.mExitSessionDialogFactory;
                boolean isEphemeral = userInfo.isEphemeral();
                int i = userInfo.id;
                SystemUIDialog create2 = exitSessionDialogFactory.mDialogFactory.create();
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7) exitSessionDialogFactory.mClickListenerFactory;
                daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7.getClass();
                ExitSessionDialogClickListener exitSessionDialogClickListener = new ExitSessionDialogClickListener((UserSwitcherController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7.this$0.wMComponentImpl).userSwitcherControllerProvider.get(), isEphemeral, i, create2);
                if (isEphemeral) {
                    create2.setTitle(exitSessionDialogFactory.mResources.getString(R.string.guest_exit_dialog_title));
                    create2.setMessage(exitSessionDialogFactory.mResources.getString(R.string.guest_exit_dialog_message));
                    create2.setButton(-3, exitSessionDialogFactory.mResources.getString(android.R.string.cancel), exitSessionDialogClickListener);
                    create2.setButton(-1, exitSessionDialogFactory.mResources.getString(R.string.guest_exit_dialog_button), exitSessionDialogClickListener);
                } else {
                    create2.setTitle(exitSessionDialogFactory.mResources.getString(R.string.guest_exit_dialog_title_non_ephemeral));
                    create2.setMessage(exitSessionDialogFactory.mResources.getString(R.string.guest_exit_dialog_message_non_ephemeral));
                    create2.setButton(-3, exitSessionDialogFactory.mResources.getString(android.R.string.cancel), exitSessionDialogClickListener);
                    create2.setButton(-2, exitSessionDialogFactory.mResources.getString(R.string.guest_exit_clear_data_button), exitSessionDialogClickListener);
                    create2.setButton(-1, exitSessionDialogFactory.mResources.getString(R.string.guest_exit_save_data_button), exitSessionDialogClickListener);
                }
                create2.setCanceledOnTouchOutside(false);
                this.mExitSessionDialog = create2;
                create2.show();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExitSessionDialogFactory {
        public final Object mClickListenerFactory;
        public final SystemUIDialog.Factory mDialogFactory;
        public final Resources mResources;

        public ExitSessionDialogFactory(SystemUIDialog.Factory factory, Resources resources, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6) {
            this.mDialogFactory = factory;
            this.mResources = resources;
            this.mClickListenerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6;
        }

        public SystemUIDialog create(int i) {
            SystemUIDialog create = this.mDialogFactory.create();
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6) this.mClickListenerFactory;
            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6.getClass();
            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6.this$0;
            ResetSessionDialogClickListener resetSessionDialogClickListener = new ResetSessionDialogClickListener((UserSwitcherController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).userSwitcherControllerProvider.get(), (UiEventLogger) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), i, create);
            create.setTitle(R.string.guest_reset_and_restart_dialog_title);
            create.setMessage(this.mResources.getString(R.string.guest_reset_and_restart_dialog_message));
            create.setButton(-3, this.mResources.getString(android.R.string.cancel), resetSessionDialogClickListener);
            create.setButton(-1, this.mResources.getString(R.string.guest_reset_guest_confirm_button), resetSessionDialogClickListener);
            create.setCanceledOnTouchOutside(false);
            return create;
        }

        public ExitSessionDialogFactory(SystemUIDialog.Factory factory, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7, Resources resources) {
            this.mDialogFactory = factory;
            this.mClickListenerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7;
            this.mResources = resources;
        }
    }
}
