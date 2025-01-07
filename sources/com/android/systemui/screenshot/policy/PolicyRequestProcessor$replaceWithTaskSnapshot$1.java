package com.android.systemui.screenshot.policy;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class PolicyRequestProcessor$replaceWithTaskSnapshot$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PolicyRequestProcessor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PolicyRequestProcessor$replaceWithTaskSnapshot$1(PolicyRequestProcessor policyRequestProcessor, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = policyRequestProcessor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.replaceWithTaskSnapshot(null, null, null, 0, null, this);
    }
}
