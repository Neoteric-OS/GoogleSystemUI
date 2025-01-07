package com.android.systemui.statusbar.notification.collection.coordinator;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DreamCoordinator$attachFilterOnDreamingStateChange$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DreamCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DreamCoordinator$attachFilterOnDreamingStateChange$1(DreamCoordinator dreamCoordinator, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = dreamCoordinator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        DreamCoordinator.access$attachFilterOnDreamingStateChange(this.this$0, this);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }
}
