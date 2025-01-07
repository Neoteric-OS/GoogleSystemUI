package com.android.systemui.statusbar.notification.row.wrapper;

import android.R;
import android.app.PendingIntent;
import android.widget.Button;
import com.android.internal.widget.NotificationActionListLayout;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationTemplateViewWrapper$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ NotificationTemplateViewWrapper f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        NotificationTemplateViewWrapper notificationTemplateViewWrapper = this.f$0;
        PendingIntent pendingIntent = (PendingIntent) obj;
        notificationTemplateViewWrapper.mCancelledPendingIntents.add(Integer.valueOf(System.identityHashCode(pendingIntent.getTarget().asBinder())));
        NotificationActionListLayout notificationActionListLayout = notificationTemplateViewWrapper.mActions;
        if (notificationActionListLayout != null) {
            int childCount = notificationActionListLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                Button button = (Button) notificationTemplateViewWrapper.mActions.getChildAt(i);
                if (pendingIntent.equals((PendingIntent) button.getTag(R.id.personalInfo))) {
                    notificationTemplateViewWrapper.disableActionView(button);
                }
            }
        }
    }
}
