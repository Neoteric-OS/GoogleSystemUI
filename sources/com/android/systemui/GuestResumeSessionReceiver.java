package com.android.systemui;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.UserHandle;
import com.android.systemui.GuestResetOrExitSessionReceiver;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.qs.QSUserSwitcherEvent;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GuestResumeSessionReceiver {
    public static final String SETTING_GUEST_HAS_LOGGED_IN = "systemui.guest_has_logged_in";
    public final GuestSessionNotification mGuestSessionNotification;
    public final Executor mMainExecutor;
    public AlertDialog mNewSessionDialog;
    public final GuestResetOrExitSessionReceiver.ExitSessionDialogFactory mResetSessionDialogFactory;
    public final SecureSettings mSecureSettings;
    public final UserTracker.Callback mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.GuestResumeSessionReceiver.1
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            GuestResumeSessionReceiver guestResumeSessionReceiver = GuestResumeSessionReceiver.this;
            AlertDialog alertDialog = guestResumeSessionReceiver.mNewSessionDialog;
            if (alertDialog != null && alertDialog.isShowing()) {
                guestResumeSessionReceiver.mNewSessionDialog.cancel();
                guestResumeSessionReceiver.mNewSessionDialog = null;
            }
            UserInfo userInfo = ((UserTrackerImpl) guestResumeSessionReceiver.mUserTracker).getUserInfo();
            if (userInfo.isGuest()) {
                SecureSettings secureSettings = guestResumeSessionReceiver.mSecureSettings;
                int intForUser = secureSettings.getIntForUser(GuestResumeSessionReceiver.SETTING_GUEST_HAS_LOGGED_IN, 0, i);
                if (intForUser == 0) {
                    secureSettings.putIntForUser(GuestResumeSessionReceiver.SETTING_GUEST_HAS_LOGGED_IN, 1, i);
                    intForUser = 1;
                } else if (intForUser == 1) {
                    intForUser = 2;
                    secureSettings.putIntForUser(GuestResumeSessionReceiver.SETTING_GUEST_HAS_LOGGED_IN, 2, i);
                }
                GuestSessionNotification guestSessionNotification = guestResumeSessionReceiver.mGuestSessionNotification;
                boolean z = intForUser <= 1;
                if (userInfo.isGuest()) {
                    String string = userInfo.isEphemeral() ? guestSessionNotification.mContext.getString(R.string.guest_notification_ephemeral) : z ? guestSessionNotification.mContext.getString(R.string.guest_notification_non_ephemeral) : guestSessionNotification.mContext.getString(R.string.guest_notification_non_ephemeral_non_first_login);
                    Intent intent = new Intent("android.intent.action.GUEST_EXIT");
                    Intent intent2 = new Intent("android.settings.USER_SETTINGS");
                    Context context2 = guestSessionNotification.mContext;
                    UserHandle userHandle = UserHandle.SYSTEM;
                    PendingIntent broadcastAsUser = PendingIntent.getBroadcastAsUser(context2, 0, intent, 67108864, userHandle);
                    Notification.Builder contentIntent = new Notification.Builder(guestSessionNotification.mContext, PluginManager.NOTIFICATION_CHANNEL_ID).setSmallIcon(R.drawable.ic_account_circle).setContentTitle(guestSessionNotification.mContext.getString(R.string.guest_notification_session_active)).setContentText(string).setPriority(0).setOngoing(true).setContentIntent(PendingIntent.getActivityAsUser(guestSessionNotification.mContext, 0, intent2, 335544320, null, UserHandle.of(userInfo.id)));
                    if (!z) {
                        contentIntent.addAction(R.drawable.ic_sysbar_home, guestSessionNotification.mContext.getString(R.string.guest_reset_guest_confirm_button), PendingIntent.getBroadcastAsUser(guestSessionNotification.mContext, 0, new Intent("android.intent.action.GUEST_RESET"), 67108864, userHandle));
                    }
                    contentIntent.addAction(R.drawable.ic_sysbar_home, guestSessionNotification.mContext.getString(R.string.guest_exit_button), broadcastAsUser);
                    Bundle bundle = new Bundle();
                    bundle.putString("android.substName", guestSessionNotification.mContext.getString(R.string.guest_notification_app_name));
                    contentIntent.addExtras(bundle);
                    guestSessionNotification.mNotificationManager.notifyAsUser(null, 70, contentIntent.build(), UserHandle.of(userInfo.id));
                }
                if (intForUser > 1) {
                    SystemUIDialog create = guestResumeSessionReceiver.mResetSessionDialogFactory.create(i);
                    guestResumeSessionReceiver.mNewSessionDialog = create;
                    create.show();
                }
            }
        }
    };
    public final UserTracker mUserTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class ResetSessionDialog extends SystemUIDialog implements DialogInterface.OnClickListener {
        public static final int BUTTON_DONTWIPE = -1;
        public static final int BUTTON_WIPE = -2;

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            if (i == -2) {
                QSUserSwitcherEvent qSUserSwitcherEvent = QSUserSwitcherEvent.QS_USER_SWITCH;
                throw null;
            }
            if (i != -1) {
                return;
            }
            QSUserSwitcherEvent qSUserSwitcherEvent2 = QSUserSwitcherEvent.QS_USER_SWITCH;
            throw null;
        }
    }

    public GuestResumeSessionReceiver(Executor executor, UserTracker userTracker, SecureSettings secureSettings, GuestSessionNotification guestSessionNotification, GuestResetOrExitSessionReceiver.ExitSessionDialogFactory exitSessionDialogFactory) {
        this.mMainExecutor = executor;
        this.mUserTracker = userTracker;
        this.mSecureSettings = secureSettings;
        this.mGuestSessionNotification = guestSessionNotification;
        this.mResetSessionDialogFactory = exitSessionDialogFactory;
    }
}
