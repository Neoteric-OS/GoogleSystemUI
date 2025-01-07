package com.google.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RefreshRateInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ KeyguardInteractor $keyguardInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ RefreshRateInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefreshRateInteractor$special$$inlined$flatMapLatest$1(Continuation continuation, KeyguardInteractor keyguardInteractor, RefreshRateInteractor refreshRateInteractor) {
        super(3, continuation);
        this.$keyguardInteractor$inlined = keyguardInteractor;
        this.this$0 = refreshRateInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        RefreshRateInteractor$special$$inlined$flatMapLatest$1 refreshRateInteractor$special$$inlined$flatMapLatest$1 = new RefreshRateInteractor$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$keyguardInteractor$inlined, this.this$0);
        refreshRateInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        refreshRateInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return refreshRateInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = ((Boolean) this.L$1).booleanValue() ? new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.$keyguardInteractor$inlined.isKeyguardVisible, this.this$0.alternateBouncerInteractor.isVisible, new RefreshRateInteractor$requestOverridingMaxRefreshRate$1$1(3, null)) : new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowKt__ZipKt$combine$$inlined$unsafeFlow$1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
