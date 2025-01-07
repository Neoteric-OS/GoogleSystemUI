package com.android.systemui.display.data.repository;

import com.android.systemui.display.data.DisplayEvent;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DisplayRepositoryImpl$enabledDisplayIds$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DisplayRepositoryImpl$enabledDisplayIds$1 displayRepositoryImpl$enabledDisplayIds$1 = new DisplayRepositoryImpl$enabledDisplayIds$1(3, (Continuation) obj3);
        displayRepositoryImpl$enabledDisplayIds$1.L$0 = (Set) obj;
        displayRepositoryImpl$enabledDisplayIds$1.L$1 = (DisplayEvent) obj2;
        return displayRepositoryImpl$enabledDisplayIds$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Set set = (Set) this.L$0;
        DisplayEvent displayEvent = (DisplayEvent) this.L$1;
        int displayId = displayEvent.getDisplayId();
        if (displayEvent instanceof DisplayEvent.Removed) {
            return SetsKt.minus(set, new Integer(displayId));
        }
        if (displayEvent instanceof DisplayEvent.Added ? true : displayEvent instanceof DisplayEvent.Changed) {
            return SetsKt.plus(set, new Integer(displayId));
        }
        throw new NoWhenBranchMatchedException();
    }
}
