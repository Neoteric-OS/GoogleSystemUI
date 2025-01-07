package com.android.systemui.statusbar.notification.row;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationGutsManager$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ExpandableNotificationRow f$0;

    public /* synthetic */ NotificationGutsManager$1$$ExternalSyntheticLambda0(ExpandableNotificationRow expandableNotificationRow) {
        this.f$0 = expandableNotificationRow;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ExpandableNotificationRow expandableNotificationRow = this.f$0;
        expandableNotificationRow.resetTranslation();
        expandableNotificationRow.updateContentAccessibilityImportanceForGuts(false);
    }
}
