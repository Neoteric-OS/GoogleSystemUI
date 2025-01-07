package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $isEnabled;
    int label;
    final /* synthetic */ FlagEnabled this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1(FlagEnabled flagEnabled, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = flagEnabled;
        this.$isEnabled = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1(this.this$0, this.$isEnabled, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1 flagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1 = (FlagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        flagEnabled$settingsChangeListener$1$onColumbusEnabledChange$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FlagEnabled flagEnabled = this.this$0;
        flagEnabled.columbusEnabled = this.$isEnabled;
        BuildersKt.launch$default(flagEnabled.coroutineScope, null, null, new FlagEnabled$updateBlocking$1(flagEnabled, null), 3);
        return Unit.INSTANCE;
    }
}
