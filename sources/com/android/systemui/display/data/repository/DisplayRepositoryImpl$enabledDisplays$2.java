package com.android.systemui.display.data.repository;

import android.util.Log;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DisplayRepositoryImpl$enabledDisplays$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DisplayRepositoryImpl$enabledDisplays$2 displayRepositoryImpl$enabledDisplays$2 = new DisplayRepositoryImpl$enabledDisplays$2(2, continuation);
        displayRepositoryImpl$enabledDisplays$2.L$0 = obj;
        return displayRepositoryImpl$enabledDisplays$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DisplayRepositoryImpl$enabledDisplays$2 displayRepositoryImpl$enabledDisplays$2 = (DisplayRepositoryImpl$enabledDisplays$2) create((Set) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        displayRepositoryImpl$enabledDisplays$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (((Set) this.L$0).isEmpty()) {
            Log.wtf("DisplayRepository", "No enabled displays. This should never happen.");
        }
        return Unit.INSTANCE;
    }
}
