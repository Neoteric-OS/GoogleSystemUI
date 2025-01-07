package com.android.systemui.statusbar.phone;

import android.view.View;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class HeadsUpAppearanceController$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ HeadsUpAppearanceController f$0;

    public /* synthetic */ HeadsUpAppearanceController$$ExternalSyntheticLambda0(HeadsUpAppearanceController headsUpAppearanceController, int i) {
        this.$r8$classId = i;
        this.f$0 = headsUpAppearanceController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        HeadsUpAppearanceController headsUpAppearanceController = this.f$0;
        switch (i) {
            case 0:
                ExpandableNotificationRow expandableNotificationRow = headsUpAppearanceController.mTrackedChild;
                headsUpAppearanceController.mTrackedChild = (ExpandableNotificationRow) obj;
                if (expandableNotificationRow != null) {
                    NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
                    HeadsUpAppearanceController.updateHeader(notificationEntry);
                    headsUpAppearanceController.updateHeadsUpAndPulsingRoundness(notificationEntry);
                    break;
                }
                break;
            case 1:
                headsUpAppearanceController.hide((View) obj, 4, null);
                break;
            case 2:
                headsUpAppearanceController.show((View) obj);
                break;
            default:
                NotificationEntry notificationEntry2 = (NotificationEntry) obj;
                HeadsUpAppearanceController.updateHeader(notificationEntry2);
                headsUpAppearanceController.updateHeadsUpAndPulsingRoundness(notificationEntry2);
                break;
        }
    }
}
