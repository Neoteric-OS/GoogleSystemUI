package com.android.systemui.statusbar.notification.stack;

import android.view.View;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationStackSizeCalculator$childrenSequence$1 extends Lambda implements Function1 {
    public static final NotificationStackSizeCalculator$childrenSequence$1 INSTANCE = new NotificationStackSizeCalculator$childrenSequence$1();

    public NotificationStackSizeCalculator$childrenSequence$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return (ExpandableView) ((View) obj);
    }
}
