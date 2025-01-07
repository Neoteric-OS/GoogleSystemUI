package com.android.systemui.qs.pipeline.domain.interactor;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AutoAddInteractor$collectAutoAddSignalsForUser$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AutoAddInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoAddInteractor$collectAutoAddSignalsForUser$1(AutoAddInteractor autoAddInteractor, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = autoAddInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AutoAddInteractor.access$collectAutoAddSignalsForUser(this.this$0, null, 0, this);
    }
}
