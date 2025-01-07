package com.android.systemui.statusbar.notification.shelf.ui.viewbinder;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationShelfViewBinder$registerViewListenersWhileAttached$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NotificationShelfViewBinder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationShelfViewBinder$registerViewListenersWhileAttached$1(NotificationShelfViewBinder notificationShelfViewBinder, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = notificationShelfViewBinder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        NotificationShelfViewBinder.access$registerViewListenersWhileAttached(this.this$0, null, null, this);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }
}
