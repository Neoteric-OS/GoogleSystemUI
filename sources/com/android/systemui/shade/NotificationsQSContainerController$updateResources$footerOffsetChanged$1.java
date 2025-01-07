package com.android.systemui.shade;

import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class NotificationsQSContainerController$updateResources$footerOffsetChanged$1 extends MutablePropertyReference0Impl {
    @Override // kotlin.reflect.KProperty0
    public final Object get() {
        return Integer.valueOf(((NotificationsQSContainerController) this.receiver).footerActionsOffset);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public final void set(Object obj) {
        ((NotificationsQSContainerController) this.receiver).footerActionsOffset = ((Number) obj).intValue();
    }
}
