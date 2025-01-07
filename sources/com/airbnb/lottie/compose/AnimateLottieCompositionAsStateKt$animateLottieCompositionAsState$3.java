package com.airbnb.lottie.compose;

import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import com.airbnb.lottie.LottieComposition;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AnimateLottieCompositionAsStateKt$animateLottieCompositionAsState$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ float $actualSpeed;
    final /* synthetic */ LottieAnimatable $animatable;
    final /* synthetic */ LottieCancellationBehavior $cancellationBehavior;
    final /* synthetic */ LottieClipSpec $clipSpec;
    final /* synthetic */ LottieComposition $composition;
    final /* synthetic */ boolean $isPlaying;
    final /* synthetic */ int $iterations;
    final /* synthetic */ boolean $restartOnPlay;
    final /* synthetic */ boolean $reverseOnRepeat;
    final /* synthetic */ boolean $useCompositionFrameRate;
    final /* synthetic */ MutableState $wasPlaying$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimateLottieCompositionAsStateKt$animateLottieCompositionAsState$3(boolean z, boolean z2, LottieAnimatable lottieAnimatable, LottieComposition lottieComposition, int i, boolean z3, float f, LottieCancellationBehavior lottieCancellationBehavior, boolean z4, MutableState mutableState, Continuation continuation) {
        super(2, continuation);
        this.$isPlaying = z;
        this.$restartOnPlay = z2;
        this.$animatable = lottieAnimatable;
        this.$composition = lottieComposition;
        this.$iterations = i;
        this.$reverseOnRepeat = z3;
        this.$actualSpeed = f;
        this.$cancellationBehavior = lottieCancellationBehavior;
        this.$useCompositionFrameRate = z4;
        this.$wasPlaying$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new AnimateLottieCompositionAsStateKt$animateLottieCompositionAsState$3(this.$isPlaying, this.$restartOnPlay, this.$animatable, this.$composition, this.$iterations, this.$reverseOnRepeat, this.$actualSpeed, this.$cancellationBehavior, this.$useCompositionFrameRate, this.$wasPlaying$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AnimateLottieCompositionAsStateKt$animateLottieCompositionAsState$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$isPlaying && !((Boolean) this.$wasPlaying$delegate.getValue()).booleanValue() && this.$restartOnPlay) {
                LottieAnimatable lottieAnimatable = this.$animatable;
                this.label = 1;
                LottieAnimatableImpl lottieAnimatableImpl = (LottieAnimatableImpl) lottieAnimatable;
                LottieComposition lottieComposition = (LottieComposition) ((SnapshotMutableStateImpl) lottieAnimatableImpl.composition$delegate).getValue();
                if (((SnapshotMutableStateImpl) lottieAnimatableImpl.clipSpec$delegate).getValue() != null) {
                    throw new ClassCastException();
                }
                float floatValue = ((Number) ((SnapshotMutableStateImpl) lottieAnimatableImpl.speed$delegate).getValue()).floatValue();
                float f = 1.0f;
                if ((floatValue >= 0.0f || lottieComposition != null) && (lottieComposition == null || floatValue >= 0.0f)) {
                    f = 0.0f;
                }
                Object mutate$default = MutatorMutex.mutate$default(lottieAnimatableImpl.mutex, new LottieAnimatableImpl$snapTo$2(lottieAnimatableImpl, (LottieComposition) ((SnapshotMutableStateImpl) lottieAnimatableImpl.composition$delegate).getValue(), f, 1, !(f == ((Number) ((SnapshotMutableStateImpl) lottieAnimatableImpl.progress$delegate).getValue()).floatValue()), null), this);
                if (mutate$default != coroutineSingletons) {
                    mutate$default = unit;
                }
                if (mutate$default != coroutineSingletons) {
                    mutate$default = unit;
                }
                if (mutate$default == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.$wasPlaying$delegate.setValue(Boolean.valueOf(this.$isPlaying));
        if (!this.$isPlaying) {
            return unit;
        }
        LottieAnimatable lottieAnimatable2 = this.$animatable;
        LottieComposition lottieComposition2 = this.$composition;
        int i2 = this.$iterations;
        boolean z = this.$reverseOnRepeat;
        float f2 = this.$actualSpeed;
        LottieAnimatableImpl lottieAnimatableImpl2 = (LottieAnimatableImpl) lottieAnimatable2;
        float floatValue2 = ((Number) ((SnapshotMutableStateImpl) lottieAnimatableImpl2.progress$delegate).getValue()).floatValue();
        LottieCancellationBehavior lottieCancellationBehavior = this.$cancellationBehavior;
        boolean z2 = this.$useCompositionFrameRate;
        this.label = 2;
        int iteration = lottieAnimatableImpl2.getIteration();
        lottieAnimatableImpl2.getClass();
        Object mutate$default2 = MutatorMutex.mutate$default(lottieAnimatableImpl2.mutex, new LottieAnimatableImpl$animate$2(lottieAnimatableImpl2, iteration, i2, z, f2, lottieComposition2, floatValue2, z2, false, lottieCancellationBehavior, null), this);
        if (mutate$default2 != coroutineSingletons) {
            mutate$default2 = unit;
        }
        return mutate$default2 == coroutineSingletons ? coroutineSingletons : unit;
    }
}
