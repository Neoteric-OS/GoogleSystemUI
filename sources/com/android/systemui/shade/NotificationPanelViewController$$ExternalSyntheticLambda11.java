package com.android.systemui.shade;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationPanelViewController$$ExternalSyntheticLambda11 {
    public final /* synthetic */ NotificationPanelViewController f$0;

    public void onExpansionHeightSetToMax(boolean z) {
        NotificationPanelViewController notificationPanelViewController = this.f$0;
        if (z) {
            notificationPanelViewController.requestScrollerTopPaddingUpdate();
        }
        notificationPanelViewController.updateExpandedHeightToMaxHeight();
    }
}
