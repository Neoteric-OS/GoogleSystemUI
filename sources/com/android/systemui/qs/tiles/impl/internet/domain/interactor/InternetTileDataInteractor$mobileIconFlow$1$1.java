package com.android.systemui.qs.tiles.impl.internet.domain.interactor;

import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class InternetTileDataInteractor$mobileIconFlow$1$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        InternetTileDataInteractor$mobileIconFlow$1$1 internetTileDataInteractor$mobileIconFlow$1$1 = new InternetTileDataInteractor$mobileIconFlow$1$1(4, (Continuation) obj4);
        internetTileDataInteractor$mobileIconFlow$1$1.L$0 = (NetworkNameModel) obj;
        internetTileDataInteractor$mobileIconFlow$1$1.L$1 = (SignalIconModel) obj2;
        internetTileDataInteractor$mobileIconFlow$1$1.L$2 = (CharSequence) obj3;
        return internetTileDataInteractor$mobileIconFlow$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new Triple((NetworkNameModel) this.L$0, (SignalIconModel) this.L$1, (CharSequence) this.L$2);
    }
}
