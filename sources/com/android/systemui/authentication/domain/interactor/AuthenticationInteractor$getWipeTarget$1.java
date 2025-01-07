package com.android.systemui.authentication.domain.interactor;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AuthenticationInteractor$getWipeTarget$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AuthenticationInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthenticationInteractor$getWipeTarget$1(AuthenticationInteractor authenticationInteractor, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = authenticationInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AuthenticationInteractor.access$getWipeTarget(this.this$0, this);
    }
}
