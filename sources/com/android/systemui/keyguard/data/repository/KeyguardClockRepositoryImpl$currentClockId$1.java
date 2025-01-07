package com.android.systemui.keyguard.data.repository;

import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.util.Assert;
import com.android.systemui.util.ThreadAssert;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardClockRepositoryImpl$currentClockId$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ KeyguardClockRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardClockRepositoryImpl$currentClockId$1(KeyguardClockRepositoryImpl keyguardClockRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardClockRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        KeyguardClockRepositoryImpl$currentClockId$1 keyguardClockRepositoryImpl$currentClockId$1 = new KeyguardClockRepositoryImpl$currentClockId$1(this.this$0, continuation);
        keyguardClockRepositoryImpl$currentClockId$1.L$0 = obj;
        return keyguardClockRepositoryImpl$currentClockId$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardClockRepositoryImpl$currentClockId$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.keyguard.data.repository.KeyguardClockRepositoryImpl$currentClockId$1$listener$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final KeyguardClockRepositoryImpl keyguardClockRepositoryImpl = this.this$0;
            final ?? r1 = new ClockRegistry.ClockChangeListener() { // from class: com.android.systemui.keyguard.data.repository.KeyguardClockRepositoryImpl$currentClockId$1$listener$1
                @Override // com.android.systemui.shared.clocks.ClockRegistry.ClockChangeListener
                public final void onCurrentClockChanged() {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(keyguardClockRepositoryImpl.clockRegistry.getCurrentClockId());
                }
            };
            ClockRegistry clockRegistry = keyguardClockRepositoryImpl.clockRegistry;
            ThreadAssert threadAssert = clockRegistry.f43assert;
            Assert.isMainThread();
            clockRegistry.clockChangeListeners.add(r1);
            ((ProducerCoroutine) producerScope).mo1790trySendJP2dKIU(this.this$0.clockRegistry.getCurrentClockId());
            final KeyguardClockRepositoryImpl keyguardClockRepositoryImpl2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.data.repository.KeyguardClockRepositoryImpl$currentClockId$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    KeyguardClockRepositoryImpl.this.clockRegistry.unregisterClockChangeListener(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
