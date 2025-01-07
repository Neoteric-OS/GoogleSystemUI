package com.android.systemui.common.ui.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ConfigurationInteractor$directionalDimensionPixelSize$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ int $originLayoutDirection;
    /* synthetic */ int I$0;
    /* synthetic */ int I$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfigurationInteractor$directionalDimensionPixelSize$1(int i, Continuation continuation) {
        super(3, continuation);
        this.$originLayoutDirection = i;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int intValue = ((Number) obj).intValue();
        int intValue2 = ((Number) obj2).intValue();
        ConfigurationInteractor$directionalDimensionPixelSize$1 configurationInteractor$directionalDimensionPixelSize$1 = new ConfigurationInteractor$directionalDimensionPixelSize$1(this.$originLayoutDirection, (Continuation) obj3);
        configurationInteractor$directionalDimensionPixelSize$1.I$0 = intValue;
        configurationInteractor$directionalDimensionPixelSize$1.I$1 = intValue2;
        return configurationInteractor$directionalDimensionPixelSize$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        int i = this.I$0;
        if (this.$originLayoutDirection != this.I$1) {
            i = -i;
        }
        return new Integer(i);
    }
}
