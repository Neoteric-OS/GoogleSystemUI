package com.airbnb.lottie.compose;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import com.airbnb.lottie.LottieComposition;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.NonCancellable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LottieAnimatableImpl$animate$2 extends SuspendLambda implements Function1 {
    final /* synthetic */ LottieCancellationBehavior $cancellationBehavior;
    final /* synthetic */ LottieClipSpec $clipSpec;
    final /* synthetic */ LottieComposition $composition;
    final /* synthetic */ boolean $continueFromPreviousAnimate;
    final /* synthetic */ float $initialProgress;
    final /* synthetic */ int $iteration;
    final /* synthetic */ int $iterations;
    final /* synthetic */ boolean $reverseOnRepeat;
    final /* synthetic */ float $speed;
    final /* synthetic */ boolean $useCompositionFrameRate;
    int label;
    final /* synthetic */ LottieAnimatableImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LottieCancellationBehavior $cancellationBehavior;
        final /* synthetic */ int $iteration;
        final /* synthetic */ int $iterations;
        final /* synthetic */ Job $parentJob;
        int label;
        final /* synthetic */ LottieAnimatableImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2$1$WhenMappings */
        public abstract /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[LottieCancellationBehavior.values().length];
                try {
                    LottieCancellationBehavior lottieCancellationBehavior = LottieCancellationBehavior.Immediately;
                    iArr[1] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LottieCancellationBehavior lottieCancellationBehavior, Job job, int i, int i2, LottieAnimatableImpl lottieAnimatableImpl, Continuation continuation) {
            super(2, continuation);
            this.$cancellationBehavior = lottieCancellationBehavior;
            this.$parentJob = job;
            this.$iterations = i;
            this.$iteration = i2;
            this.this$0 = lottieAnimatableImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$cancellationBehavior, this.$parentJob, this.$iterations, this.$iteration, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0032  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r4.label
                r2 = 1
                if (r1 == 0) goto L15
                if (r1 != r2) goto Ld
                kotlin.ResultKt.throwOnFailure(r5)
                goto L5e
            Ld:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L15:
                kotlin.ResultKt.throwOnFailure(r5)
            L18:
                com.airbnb.lottie.compose.LottieCancellationBehavior r5 = r4.$cancellationBehavior
                int[] r1 = com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0
                int r5 = r5.ordinal()
                r5 = r1[r5]
                if (r5 != r2) goto L32
                kotlinx.coroutines.Job r5 = r4.$parentJob
                boolean r5 = r5.isActive()
                if (r5 == 0) goto L2f
                int r5 = r4.$iterations
                goto L34
            L2f:
                int r5 = r4.$iteration
                goto L34
            L32:
                int r5 = r4.$iterations
            L34:
                com.airbnb.lottie.compose.LottieAnimatableImpl r1 = r4.this$0
                r4.label = r2
                r1.getClass()
                r3 = 2147483647(0x7fffffff, float:NaN)
                if (r5 != r3) goto L4a
                com.airbnb.lottie.compose.LottieAnimatableImpl$doFrame$2 r3 = new com.airbnb.lottie.compose.LottieAnimatableImpl$doFrame$2
                r3.<init>()
                java.lang.Object r5 = androidx.compose.animation.core.InfiniteAnimationPolicyKt.withInfiniteAnimationFrameNanos(r3, r4)
                goto L5b
            L4a:
                com.airbnb.lottie.compose.LottieAnimatableImpl$doFrame$3 r3 = new com.airbnb.lottie.compose.LottieAnimatableImpl$doFrame$3
                r3.<init>()
                kotlin.coroutines.CoroutineContext r5 = r4.getContext()
                androidx.compose.runtime.MonotonicFrameClock r5 = androidx.compose.runtime.MonotonicFrameClockKt.getMonotonicFrameClock(r5)
                java.lang.Object r5 = r5.withFrameNanos(r3, r4)
            L5b:
                if (r5 != r0) goto L5e
                return r0
            L5e:
                java.lang.Boolean r5 = (java.lang.Boolean) r5
                boolean r5 = r5.booleanValue()
                if (r5 != 0) goto L18
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LottieAnimatableImpl$animate$2(LottieAnimatableImpl lottieAnimatableImpl, int i, int i2, boolean z, float f, LottieComposition lottieComposition, float f2, boolean z2, boolean z3, LottieCancellationBehavior lottieCancellationBehavior, Continuation continuation) {
        super(1, continuation);
        this.this$0 = lottieAnimatableImpl;
        this.$iteration = i;
        this.$iterations = i2;
        this.$reverseOnRepeat = z;
        this.$speed = f;
        this.$composition = lottieComposition;
        this.$initialProgress = f2;
        this.$useCompositionFrameRate = z2;
        this.$continueFromPreviousAnimate = z3;
        this.$cancellationBehavior = lottieCancellationBehavior;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new LottieAnimatableImpl$animate$2(this.this$0, this.$iteration, this.$iterations, this.$reverseOnRepeat, this.$speed, this.$composition, this.$initialProgress, this.$useCompositionFrameRate, this.$continueFromPreviousAnimate, this.$cancellationBehavior, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineContext coroutineContext;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.setIteration(this.$iteration);
                LottieAnimatableImpl lottieAnimatableImpl = this.this$0;
                int i2 = this.$iterations;
                ((SnapshotMutableStateImpl) lottieAnimatableImpl.iterations$delegate).setValue(Integer.valueOf(i2));
                LottieAnimatableImpl lottieAnimatableImpl2 = this.this$0;
                boolean z = this.$reverseOnRepeat;
                ((SnapshotMutableStateImpl) lottieAnimatableImpl2.reverseOnRepeat$delegate).setValue(Boolean.valueOf(z));
                LottieAnimatableImpl lottieAnimatableImpl3 = this.this$0;
                float f = this.$speed;
                ((SnapshotMutableStateImpl) lottieAnimatableImpl3.speed$delegate).setValue(Float.valueOf(f));
                ((SnapshotMutableStateImpl) this.this$0.clipSpec$delegate).setValue(null);
                LottieAnimatableImpl lottieAnimatableImpl4 = this.this$0;
                ((SnapshotMutableStateImpl) lottieAnimatableImpl4.composition$delegate).setValue(this.$composition);
                this.this$0.updateProgress(this.$initialProgress);
                LottieAnimatableImpl lottieAnimatableImpl5 = this.this$0;
                boolean z2 = this.$useCompositionFrameRate;
                ((SnapshotMutableStateImpl) lottieAnimatableImpl5.useCompositionFrameRate$delegate).setValue(Boolean.valueOf(z2));
                if (!this.$continueFromPreviousAnimate) {
                    ((SnapshotMutableStateImpl) this.this$0.lastFrameNanos$delegate).setValue(Long.MIN_VALUE);
                }
                if (this.$composition == null) {
                    return unit;
                }
                if (Float.isInfinite(this.$speed)) {
                    LottieAnimatableImpl lottieAnimatableImpl6 = this.this$0;
                    lottieAnimatableImpl6.updateProgress(((Number) lottieAnimatableImpl6.endProgress$delegate.getValue()).floatValue());
                    ((SnapshotMutableStateImpl) this.this$0.isPlaying$delegate).setValue(Boolean.valueOf(false));
                    this.this$0.setIteration(this.$iterations);
                    return unit;
                }
                ((SnapshotMutableStateImpl) this.this$0.isPlaying$delegate).setValue(Boolean.valueOf(true));
                int ordinal = this.$cancellationBehavior.ordinal();
                if (ordinal == 0) {
                    coroutineContext = EmptyCoroutineContext.INSTANCE;
                } else {
                    if (ordinal != 1) {
                        throw new NoWhenBranchMatchedException();
                    }
                    coroutineContext = NonCancellable.INSTANCE;
                }
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$cancellationBehavior, JobKt.getJob(getContext()), this.$iterations, this.$iteration, this.this$0, null);
                this.label = 1;
                if (BuildersKt.withContext(coroutineContext, anonymousClass1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            JobKt.ensureActive(getContext());
            return unit;
        } finally {
            ((SnapshotMutableStateImpl) this.this$0.isPlaying$delegate).setValue(Boolean.valueOf(false));
        }
    }
}
