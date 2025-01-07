package com.android.systemui.accessibility.data.repository;

import android.hardware.display.ColorDisplayManager;
import android.os.UserHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class NightDisplayRepository$setNightDisplayAutoMode$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $autoMode;
    final /* synthetic */ UserHandle $user;
    int label;
    final /* synthetic */ NightDisplayRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NightDisplayRepository$setNightDisplayAutoMode$2(NightDisplayRepository nightDisplayRepository, UserHandle userHandle, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = nightDisplayRepository;
        this.$user = userHandle;
        this.$autoMode = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new NightDisplayRepository$setNightDisplayAutoMode$2(this.this$0, this.$user, this.$autoMode, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        NightDisplayRepository$setNightDisplayAutoMode$2 nightDisplayRepository$setNightDisplayAutoMode$2 = (NightDisplayRepository$setNightDisplayAutoMode$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        nightDisplayRepository$setNightDisplayAutoMode$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ((ColorDisplayManager) this.this$0.colorDisplayManagerUserScopedService.forUser(this.$user)).setNightDisplayAutoMode(this.$autoMode);
        return Unit.INSTANCE;
    }
}
