package com.android.systemui.display.data.repository;

import android.util.Log;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DisplayRepositoryImpl$pendingDisplayIds$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        DisplayRepositoryImpl$pendingDisplayIds$1 displayRepositoryImpl$pendingDisplayIds$1 = new DisplayRepositoryImpl$pendingDisplayIds$1(4, (Continuation) obj4);
        displayRepositoryImpl$pendingDisplayIds$1.L$0 = (Set) obj;
        displayRepositoryImpl$pendingDisplayIds$1.L$1 = (Set) obj2;
        displayRepositoryImpl$pendingDisplayIds$1.L$2 = (Set) obj3;
        return displayRepositoryImpl$pendingDisplayIds$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Set set = (Set) this.L$0;
        Set set2 = (Set) this.L$1;
        Set set3 = (Set) this.L$2;
        boolean z = DisplayRepositoryImpl.DEBUG;
        if (DisplayRepositoryImpl.DEBUG) {
            Log.d("DisplayRepository", "combining enabled=" + set + ", connectedExternalDisplayIds=" + set2 + ", ignored=" + set3);
        }
        return SetsKt.minus(SetsKt.minus(set2, (Iterable) set), (Iterable) set3);
    }
}
