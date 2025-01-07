package com.android.systemui.statusbar.notification.row;

import android.net.Uri;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController;
import com.android.systemui.statusbar.notification.row.NotificationSettingsController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationSettingsController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationSettingsController.Listener f$0;
    public final /* synthetic */ Uri f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ NotificationSettingsController$$ExternalSyntheticLambda3(NotificationSettingsController.Listener listener, Uri uri, int i, String str, int i2) {
        this.$r8$classId = i2;
        this.f$0 = listener;
        this.f$1 = uri;
        this.f$2 = i;
        this.f$3 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                NotificationSettingsController.Listener listener = this.f$0;
                ExpandableNotificationRowController.AnonymousClass1 anonymousClass1 = (ExpandableNotificationRowController.AnonymousClass1) listener;
                anonymousClass1.onSettingChanged(this.f$2, this.f$1, this.f$3);
                break;
            default:
                NotificationSettingsController.Listener listener2 = this.f$0;
                ExpandableNotificationRowController.AnonymousClass1 anonymousClass12 = (ExpandableNotificationRowController.AnonymousClass1) listener2;
                anonymousClass12.onSettingChanged(this.f$2, this.f$1, this.f$3);
                break;
        }
    }
}
