package com.android.systemui.bouncer.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import dagger.Lazy;
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
/* loaded from: classes.dex */
public final class AlternateBouncerInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Lazy $keyguardTransitionInteractor$inlined;
    final /* synthetic */ Lazy $sceneInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ AlternateBouncerInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlternateBouncerInteractor$special$$inlined$flatMapLatest$1(Continuation continuation, Lazy lazy, Lazy lazy2, AlternateBouncerInteractor alternateBouncerInteractor) {
        super(3, continuation);
        this.$keyguardTransitionInteractor$inlined = lazy;
        this.$sceneInteractor$inlined = lazy2;
        this.this$0 = alternateBouncerInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AlternateBouncerInteractor$special$$inlined$flatMapLatest$1 alternateBouncerInteractor$special$$inlined$flatMapLatest$1 = new AlternateBouncerInteractor$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$keyguardTransitionInteractor$inlined, this.$sceneInteractor$inlined, this.this$0);
        alternateBouncerInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        alternateBouncerInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return alternateBouncerInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow transformLatest = ((Boolean) this.L$1).booleanValue() ? FlowKt.transformLatest(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(((KeyguardTransitionInteractor) this.$keyguardTransitionInteractor$inlined.get()).currentKeyguardState, ((SceneInteractor) this.$sceneInteractor$inlined.get()).currentScene, AlternateBouncerInteractor$canShowAlternateBouncer$1$2.INSTANCE), new AlternateBouncerInteractor$canShowAlternateBouncer$lambda$5$$inlined$flatMapLatest$1(null, this.this$0)) : new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, transformLatest, this) == coroutineSingletons) {
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
