package com.android.systemui.keyboard.stickykeys.data.repository;

import java.util.LinkedHashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class StickyKeysRepositoryImpl$stickyKeys$3 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ StickyKeysRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StickyKeysRepositoryImpl$stickyKeys$3(StickyKeysRepositoryImpl stickyKeysRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = stickyKeysRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        StickyKeysRepositoryImpl$stickyKeys$3 stickyKeysRepositoryImpl$stickyKeys$3 = new StickyKeysRepositoryImpl$stickyKeys$3(this.this$0, continuation);
        stickyKeysRepositoryImpl$stickyKeys$3.L$0 = obj;
        return stickyKeysRepositoryImpl$stickyKeys$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        StickyKeysRepositoryImpl$stickyKeys$3 stickyKeysRepositoryImpl$stickyKeys$3 = (StickyKeysRepositoryImpl$stickyKeys$3) create((LinkedHashMap) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        stickyKeysRepositoryImpl$stickyKeys$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.stickyKeysLogger.logNewStickyKeysReceived((LinkedHashMap) this.L$0);
        return Unit.INSTANCE;
    }
}
