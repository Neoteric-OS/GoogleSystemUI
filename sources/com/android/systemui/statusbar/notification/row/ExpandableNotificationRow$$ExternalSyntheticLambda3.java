package com.android.systemui.statusbar.notification.row;

import android.view.View;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ExpandableNotificationRow$$ExternalSyntheticLambda3 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ExpandableNotificationRow f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ ExpandableNotificationRow$$ExternalSyntheticLambda3(ExpandableNotificationRow expandableNotificationRow, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = expandableNotificationRow;
        this.f$1 = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                ExpandableNotificationRow.m878$r8$lambda$m9ShXoKTwBLcNnosIeqOTeZZvE(this.f$0, (ExpandableNotificationRowController$$ExternalSyntheticLambda0) this.f$1, view);
                break;
            default:
                ExpandableNotificationRow expandableNotificationRow = this.f$0;
                NotificationMenuRow.NotificationMenuItem notificationMenuItem = (NotificationMenuRow.NotificationMenuItem) this.f$1;
                expandableNotificationRow.mNotificationGutsManager.closeAndSaveGuts(true, false, false, false);
                expandableNotificationRow.mNotificationGutsManager.openGuts(expandableNotificationRow, 0, 0, notificationMenuItem);
                expandableNotificationRow.mIsSnoozed = true;
                break;
        }
    }
}
