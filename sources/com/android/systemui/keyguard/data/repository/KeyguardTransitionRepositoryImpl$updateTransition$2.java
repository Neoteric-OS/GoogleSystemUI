package com.android.systemui.keyguard.data.repository;

import com.android.systemui.keyguard.shared.model.TransitionState;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardTransitionRepositoryImpl$updateTransition$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ TransitionState $state;
    final /* synthetic */ UUID $transitionId;
    final /* synthetic */ float $value;
    int label;
    final /* synthetic */ KeyguardTransitionRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardTransitionRepositoryImpl$updateTransition$2(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, UUID uuid, float f, TransitionState transitionState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardTransitionRepositoryImpl;
        this.$transitionId = uuid;
        this.$value = f;
        this.$state = transitionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardTransitionRepositoryImpl$updateTransition$2(this.this$0, this.$transitionId, this.$value, this.$state, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardTransitionRepositoryImpl$updateTransition$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0._currentTransitionMutex.unlock(null);
            KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl = this.this$0;
            UUID uuid = this.$transitionId;
            float f = this.$value;
            TransitionState transitionState = this.$state;
            this.label = 1;
            KeyguardTransitionRepositoryImpl.access$updateTransitionInternal(keyguardTransitionRepositoryImpl, uuid, f, transitionState);
            if (unit == coroutineSingletons) {
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
