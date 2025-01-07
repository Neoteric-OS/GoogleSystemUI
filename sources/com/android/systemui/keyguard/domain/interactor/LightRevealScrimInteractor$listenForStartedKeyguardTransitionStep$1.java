package com.android.systemui.keyguard.domain.interactor;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.core.animation.ValueAnimator;
import com.android.app.viewcapture.data.ViewNode;
import com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LightRevealScrimInteractor$listenForStartedKeyguardTransitionStep$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ LightRevealScrimInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LightRevealScrimInteractor$listenForStartedKeyguardTransitionStep$1(LightRevealScrimInteractor lightRevealScrimInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lightRevealScrimInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new LightRevealScrimInteractor$listenForStartedKeyguardTransitionStep$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((LightRevealScrimInteractor$listenForStartedKeyguardTransitionStep$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
        }
        ResultKt.throwOnFailure(obj);
        final LightRevealScrimInteractor lightRevealScrimInteractor = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = lightRevealScrimInteractor.transitionInteractor.startedKeyguardTransitionStep;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$listenForStartedKeyguardTransitionStep$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                TransitionStep transitionStep = (TransitionStep) obj2;
                LightRevealScrimInteractor lightRevealScrimInteractor2 = LightRevealScrimInteractor.this;
                lightRevealScrimInteractor2.scrimLogger.d(LightRevealScrimInteractor.TAG, "listenForStartedKeyguardTransitionStep", transitionStep);
                boolean z = true;
                switch (transitionStep.to.ordinal()) {
                    case 0:
                    case 1:
                    case 4:
                        z = false;
                        break;
                    case 2:
                    case 3:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                LightRevealScrimRepositoryImpl lightRevealScrimRepositoryImpl = (LightRevealScrimRepositoryImpl) lightRevealScrimInteractor2.lightRevealScrimRepository;
                if (!Boolean.valueOf(z).equals(lightRevealScrimRepositoryImpl.willBeOrIsRevealed)) {
                    lightRevealScrimRepositoryImpl.willBeOrIsRevealed = Boolean.valueOf(z);
                    ValueAnimator valueAnimator = lightRevealScrimRepositoryImpl.revealAmountAnimator;
                    valueAnimator.setDuration(500L);
                    if (!z || valueAnimator.mRunning) {
                        valueAnimator.reverse();
                    } else {
                        valueAnimator.start(false);
                    }
                    lightRevealScrimRepositoryImpl.scrimLogger.d(LightRevealScrimRepositoryImpl.TAG, "startRevealAmountAnimator, reveal", Boolean.valueOf(z));
                }
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        readonlyStateFlow.collect(flowCollector, this);
        return coroutineSingletons;
    }
}
