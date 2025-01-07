package com.android.systemui.statusbar.notification.stack;

import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationStackScrollLayout$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ NotificationStackScrollLayout f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ NotificationStackScrollLayout$$ExternalSyntheticLambda4(NotificationStackScrollLayout notificationStackScrollLayout, ArrayList arrayList, int i) {
        this.f$0 = notificationStackScrollLayout;
        this.f$1 = arrayList;
        this.f$2 = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        final NotificationStackScrollLayout notificationStackScrollLayout = this.f$0;
        final ArrayList arrayList = this.f$1;
        final int i = this.f$2;
        int i2 = NotificationStackScrollLayout.$r8$clinit;
        notificationStackScrollLayout.getClass();
        if (((Boolean) obj).booleanValue()) {
            notificationStackScrollLayout.post(new Runnable() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    NotificationStackScrollLayout notificationStackScrollLayout2 = NotificationStackScrollLayout.this;
                    ArrayList arrayList2 = arrayList;
                    int i3 = i;
                    int i4 = NotificationStackScrollLayout.$r8$clinit;
                    notificationStackScrollLayout2.onClearAllAnimationsEnd(i3, arrayList2);
                }
            });
        } else {
            notificationStackScrollLayout.onClearAllAnimationsEnd(i, arrayList);
        }
    }
}
