package com.android.systemui.statusbar.notification.row;

import com.android.systemui.statusbar.notification.row.NotifBindPipeline;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotifBindPipeline$$ExternalSyntheticLambda3 {
    public final /* synthetic */ Set f$0;
    public final /* synthetic */ NotifBindPipeline.BindCallback f$1;

    public /* synthetic */ NotifBindPipeline$$ExternalSyntheticLambda3(Set set, NotifBindPipeline.BindCallback bindCallback) {
        this.f$0 = set;
        this.f$1 = bindCallback;
    }

    public final void onCancel() {
        this.f$0.remove(this.f$1);
    }
}
