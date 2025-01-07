package com.android.systemui.statusbar.notification.collection;

import android.util.ArrayMap;
import com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescer;
import java.util.Collections;
import java.util.Map;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotifCollection$$ExternalSyntheticLambda4 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ NotifCollection$$ExternalSyntheticLambda4(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return ((Map) this.f$0).keySet();
            default:
                return Collections.unmodifiableSet(((ArrayMap) ((GroupCoalescer) this.f$0).mCoalescedEvents).keySet());
        }
    }
}
