package com.android.systemui.shade;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeHeaderController$onViewAttached$2 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ShadeHeaderController this$0;

    public /* synthetic */ ShadeHeaderController$onViewAttached$2(ShadeHeaderController shadeHeaderController, int i) {
        this.$r8$classId = i;
        this.this$0 = shadeHeaderController;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.launchClockActivity$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
                break;
            default:
                NotificationPanelViewController$$ExternalSyntheticLambda0 notificationPanelViewController$$ExternalSyntheticLambda0 = this.this$0.shadeCollapseAction;
                if (notificationPanelViewController$$ExternalSyntheticLambda0 != null) {
                    notificationPanelViewController$$ExternalSyntheticLambda0.run();
                    break;
                }
                break;
        }
    }
}
