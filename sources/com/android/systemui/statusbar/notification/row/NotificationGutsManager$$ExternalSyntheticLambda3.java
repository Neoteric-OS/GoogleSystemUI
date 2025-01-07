package com.android.systemui.statusbar.notification.row;

import android.app.NotificationChannel;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationGutsManager$$ExternalSyntheticLambda3 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationGutsManager f$0;
    public final /* synthetic */ NotificationGuts f$1;
    public final /* synthetic */ StatusBarNotification f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ ExpandableNotificationRow f$4;

    public /* synthetic */ NotificationGutsManager$$ExternalSyntheticLambda3(NotificationGutsManager notificationGutsManager, NotificationGuts notificationGuts, StatusBarNotification statusBarNotification, String str, ExpandableNotificationRow expandableNotificationRow, int i) {
        this.$r8$classId = i;
        this.f$0 = notificationGutsManager;
        this.f$1 = notificationGuts;
        this.f$2 = statusBarNotification;
        this.f$3 = str;
        this.f$4 = expandableNotificationRow;
    }

    public void onClick(NotificationChannel notificationChannel, int i) {
        switch (this.$r8$classId) {
            case 1:
                StatusBarNotification statusBarNotification = this.f$2;
                NotificationGutsManager notificationGutsManager = this.f$0;
                notificationGutsManager.mMetricsLogger.action(205);
                this.f$1.resetFalsingCheck();
                StatusBarNotificationPresenter.AnonymousClass2 anonymousClass2 = notificationGutsManager.mOnSettingsClickListener;
                String key = statusBarNotification.getKey();
                anonymousClass2.getClass();
                try {
                    StatusBarNotificationPresenter.this.mBarService.onNotificationSettingsViewed(key);
                } catch (RemoteException unused) {
                }
                notificationGutsManager.startAppNotificationSettingsActivity(this.f$3, i, notificationChannel, this.f$4);
                break;
            default:
                StatusBarNotification statusBarNotification2 = this.f$2;
                NotificationGutsManager notificationGutsManager2 = this.f$0;
                notificationGutsManager2.mMetricsLogger.action(205);
                this.f$1.resetFalsingCheck();
                StatusBarNotificationPresenter.AnonymousClass2 anonymousClass22 = notificationGutsManager2.mOnSettingsClickListener;
                String key2 = statusBarNotification2.getKey();
                anonymousClass22.getClass();
                try {
                    StatusBarNotificationPresenter.this.mBarService.onNotificationSettingsViewed(key2);
                } catch (RemoteException unused2) {
                }
                notificationGutsManager2.startAppNotificationSettingsActivity(this.f$3, i, notificationChannel, this.f$4);
                break;
        }
    }
}
