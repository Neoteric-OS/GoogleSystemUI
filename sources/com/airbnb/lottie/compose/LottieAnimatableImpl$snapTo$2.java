package com.airbnb.lottie.compose;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import com.airbnb.lottie.LottieComposition;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LottieAnimatableImpl$snapTo$2 extends SuspendLambda implements Function1 {
    final /* synthetic */ LottieComposition $composition;
    final /* synthetic */ int $iteration;
    final /* synthetic */ float $progress;
    final /* synthetic */ boolean $resetLastFrameNanos;
    int label;
    final /* synthetic */ LottieAnimatableImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LottieAnimatableImpl$snapTo$2(LottieAnimatableImpl lottieAnimatableImpl, LottieComposition lottieComposition, float f, int i, boolean z, Continuation continuation) {
        super(1, continuation);
        this.this$0 = lottieAnimatableImpl;
        this.$composition = lottieComposition;
        this.$progress = f;
        this.$iteration = i;
        this.$resetLastFrameNanos = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LottieAnimatableImpl$snapTo$2 lottieAnimatableImpl$snapTo$2 = new LottieAnimatableImpl$snapTo$2(this.this$0, this.$composition, this.$progress, this.$iteration, this.$resetLastFrameNanos, (Continuation) obj);
        Unit unit = Unit.INSTANCE;
        lottieAnimatableImpl$snapTo$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LottieAnimatableImpl lottieAnimatableImpl = this.this$0;
        ((SnapshotMutableStateImpl) lottieAnimatableImpl.composition$delegate).setValue(this.$composition);
        this.this$0.updateProgress(this.$progress);
        this.this$0.setIteration(this.$iteration);
        ((SnapshotMutableStateImpl) this.this$0.isPlaying$delegate).setValue(Boolean.valueOf(false));
        if (this.$resetLastFrameNanos) {
            ((SnapshotMutableStateImpl) this.this$0.lastFrameNanos$delegate).setValue(Long.MIN_VALUE);
        }
        return Unit.INSTANCE;
    }
}
