package com.android.systemui.keyguard.data.repository;

import androidx.core.animation.Animator;
import androidx.core.animation.ValueAnimator;
import java.util.ArrayList;
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
final class LightRevealScrimRepositoryImpl$revealAmount$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ LightRevealScrimRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LightRevealScrimRepositoryImpl$revealAmount$1(LightRevealScrimRepositoryImpl lightRevealScrimRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lightRevealScrimRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LightRevealScrimRepositoryImpl$revealAmount$1 lightRevealScrimRepositoryImpl$revealAmount$1 = new LightRevealScrimRepositoryImpl$revealAmount$1(this.this$0, continuation);
        lightRevealScrimRepositoryImpl$revealAmount$1.L$0 = obj;
        return lightRevealScrimRepositoryImpl$revealAmount$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LightRevealScrimRepositoryImpl$revealAmount$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.core.animation.Animator$AnimatorUpdateListener, com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$revealAmount$1$updateListener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final LightRevealScrimRepositoryImpl lightRevealScrimRepositoryImpl = this.this$0;
            final ?? r1 = new Animator.AnimatorUpdateListener() { // from class: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$revealAmount$1$updateListener$1
                @Override // androidx.core.animation.Animator.AnimatorUpdateListener
                public final void onAnimationUpdate(Animator animator) {
                    Float f = (Float) ((ValueAnimator) animator).getAnimatedValue();
                    float floatValue = f.floatValue();
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(f);
                    if (floatValue <= 0.0f || floatValue >= 1.0f) {
                        lightRevealScrimRepositoryImpl.scrimLogger.d(LightRevealScrimRepositoryImpl.TAG, "revealAmount", f);
                    }
                }
            };
            lightRevealScrimRepositoryImpl.revealAmountAnimator.addUpdateListener(r1);
            final LightRevealScrimRepositoryImpl lightRevealScrimRepositoryImpl2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl$revealAmount$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ValueAnimator valueAnimator = LightRevealScrimRepositoryImpl.this.revealAmountAnimator;
                    Animator.AnimatorUpdateListener animatorUpdateListener = r1;
                    ArrayList arrayList = valueAnimator.mUpdateListeners;
                    if (arrayList != null) {
                        arrayList.remove(animatorUpdateListener);
                        if (valueAnimator.mUpdateListeners.size() == 0) {
                            valueAnimator.mUpdateListeners = null;
                        }
                    }
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
