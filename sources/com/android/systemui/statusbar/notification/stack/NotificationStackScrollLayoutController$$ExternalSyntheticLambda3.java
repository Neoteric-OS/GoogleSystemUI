package com.android.systemui.statusbar.notification.stack;

import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationStackScrollLayoutController$$ExternalSyntheticLambda3 implements Function1 {
    public final /* synthetic */ NotificationStackScrollLayoutController f$0;

    public /* synthetic */ NotificationStackScrollLayoutController$$ExternalSyntheticLambda3(NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        this.f$0 = notificationStackScrollLayoutController;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.f$0;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        KeyguardMediaController keyguardMediaController = notificationStackScrollLayoutController.mKeyguardMediaController;
        if (booleanValue) {
            notificationStackScrollLayout.generateAddAnimation(keyguardMediaController.singlePaneContainer);
        } else {
            notificationStackScrollLayout.generateRemoveAnimation(keyguardMediaController.singlePaneContainer);
        }
        notificationStackScrollLayout.requestChildrenUpdate();
        return Unit.INSTANCE;
    }
}
