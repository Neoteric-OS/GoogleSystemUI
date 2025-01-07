package com.android.systemui.statusbar.notification.row;

import android.R;
import android.view.View;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationContentView$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ NotificationContentView f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        View findViewById;
        View findViewById2;
        NotificationContentView notificationContentView = this.f$0;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        notificationContentView.mRemoteInputVisible = booleanValue;
        notificationContentView.setClipChildren(!booleanValue);
        int i = booleanValue ? 4 : 0;
        View view = notificationContentView.mExpandedChild;
        if (view != null && (findViewById2 = view.findViewById(R.id.alarm)) != null) {
            findViewById2.setImportantForAccessibility(i);
        }
        View view2 = notificationContentView.mHeadsUpChild;
        if (view2 == null || (findViewById = view2.findViewById(R.id.alarm)) == null) {
            return;
        }
        findViewById.setImportantForAccessibility(i);
    }
}
