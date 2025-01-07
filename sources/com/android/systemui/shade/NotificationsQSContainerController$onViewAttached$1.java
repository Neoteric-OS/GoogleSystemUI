package com.android.systemui.shade;

import com.android.systemui.plugins.qs.QS;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationsQSContainerController$onViewAttached$1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationsQSContainerController this$0;

    public /* synthetic */ NotificationsQSContainerController$onViewAttached$1(NotificationsQSContainerController notificationsQSContainerController, int i) {
        this.$r8$classId = i;
        this.this$0 = notificationsQSContainerController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ((QS) obj).setContainerController(this.this$0);
                break;
            default:
                this.this$0.updateResources();
                break;
        }
    }
}
