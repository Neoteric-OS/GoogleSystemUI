package com.android.systemui.communal.ui.viewmodel;

import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalViewModel$latestCommunalContent$1$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        CommunalViewModel$latestCommunalContent$1$1 communalViewModel$latestCommunalContent$1$1 = new CommunalViewModel$latestCommunalContent$1$1(4, (Continuation) obj4);
        communalViewModel$latestCommunalContent$1$1.L$0 = (List) obj;
        communalViewModel$latestCommunalContent$1$1.L$1 = (List) obj2;
        communalViewModel$latestCommunalContent$1$1.L$2 = (List) obj3;
        return communalViewModel$latestCommunalContent$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        return CollectionsKt.plus((Iterable) this.L$2, (Collection) CollectionsKt.plus((Iterable) this.L$1, (Collection) list));
    }
}
