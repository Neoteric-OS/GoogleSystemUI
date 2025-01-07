package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardWakeDirectlyToGoneInteractor$listenForWakeToClearCanIgnoreAuth$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardWakeDirectlyToGoneInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardWakeDirectlyToGoneInteractor$listenForWakeToClearCanIgnoreAuth$1(KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardWakeDirectlyToGoneInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardWakeDirectlyToGoneInteractor$listenForWakeToClearCanIgnoreAuth$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardWakeDirectlyToGoneInteractor$listenForWakeToClearCanIgnoreAuth$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow isInTransitionWhere = this.this$0.transitionInteractor.isInTransitionWhere(new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$listenForWakeToClearCanIgnoreAuth$1.1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    KeyguardState.Companion.getClass();
                    return Boolean.valueOf(KeyguardState.Companion.deviceIsAsleepInState((KeyguardState) obj2));
                }
            }, new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$listenForWakeToClearCanIgnoreAuth$1.2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    KeyguardState.Companion.getClass();
                    return Boolean.valueOf(KeyguardState.Companion.deviceIsAwakeInState((KeyguardState) obj2));
                }
            });
            KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1.AnonymousClass4 anonymousClass4 = new KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1.AnonymousClass4(this.this$0, 1);
            this.label = 1;
            if (isInTransitionWhere.collect(anonymousClass4, this) == coroutineSingletons) {
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
