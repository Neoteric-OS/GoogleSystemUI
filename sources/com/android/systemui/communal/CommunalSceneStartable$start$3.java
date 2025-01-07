package com.android.systemui.communal;

import com.android.systemui.util.settings.SystemSettingsImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalSceneStartable$start$3 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ CommunalSceneStartable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalSceneStartable$start$3(CommunalSceneStartable communalSceneStartable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalSceneStartable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalSceneStartable$start$3(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalSceneStartable$start$3 communalSceneStartable$start$3 = (CommunalSceneStartable$start$3) create((Unit) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalSceneStartable$start$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CommunalSceneStartable communalSceneStartable = this.this$0;
        SystemSettingsImpl systemSettingsImpl = communalSceneStartable.systemSettings;
        CommunalSceneStartable.Companion.getClass();
        communalSceneStartable.screenTimeout = systemSettingsImpl.getInt(CommunalSceneStartable.DEFAULT_SCREEN_TIMEOUT, "screen_off_timeout");
        return Unit.INSTANCE;
    }
}
