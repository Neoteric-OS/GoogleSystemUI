package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor$start$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardBlueprintInteractor$start$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardBlueprintInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardBlueprintInteractor$start$2(KeyguardBlueprintInteractor keyguardBlueprintInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardBlueprintInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardBlueprintInteractor$start$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardBlueprintInteractor$start$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            KeyguardBlueprintInteractor keyguardBlueprintInteractor = this.this$0;
            Flow flow = keyguardBlueprintInteractor.fingerprintPropertyInteractor.propertiesInitialized;
            KeyguardBlueprintInteractor$start$1.AnonymousClass1 anonymousClass1 = new KeyguardBlueprintInteractor$start$1.AnonymousClass1(keyguardBlueprintInteractor, 1);
            this.label = 1;
            Object collect = flow.collect(new KeyguardBlueprintInteractor$start$2$invokeSuspend$$inlined$filter$1$2(anonymousClass1), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
