package com.android.systemui.unfold;

import android.os.Trace;
import com.android.systemui.util.kotlin.SuspendKt;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FoldLightRevealOverlayAnimation$init$4$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $isFolded;
    int label;
    final /* synthetic */ FoldLightRevealOverlayAnimation this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$4$2$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function1 {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ FoldLightRevealOverlayAnimation this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation, Continuation continuation) {
            super(1, continuation);
            this.this$0 = foldLightRevealOverlayAnimation;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new AnonymousClass1(this.this$0, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int nextInt;
            int i;
            String str;
            FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation;
            String str2;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i2 = this.label;
            Unit unit = Unit.INSTANCE;
            try {
                if (i2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation2 = this.this$0;
                    nextInt = ThreadLocalRandom.current().nextInt();
                    Trace.asyncTraceForTrackBegin(4096L, "FoldLightRevealOverlayAnimation", "prepareAndPlayFoldAnimation()", nextInt);
                    try {
                        FoldLightRevealOverlayAnimation$init$4$2$1$1$1$1 foldLightRevealOverlayAnimation$init$4$2$1$1$1$1 = new FoldLightRevealOverlayAnimation$init$4$2$1$1$1$1(foldLightRevealOverlayAnimation2, null);
                        this.L$0 = "FoldLightRevealOverlayAnimation";
                        this.L$1 = foldLightRevealOverlayAnimation2;
                        this.I$0 = nextInt;
                        this.label = 1;
                        if (TimeoutKt.withTimeout(2000L, foldLightRevealOverlayAnimation$init$4$2$1$1$1$1, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                        foldLightRevealOverlayAnimation = foldLightRevealOverlayAnimation2;
                        str2 = "FoldLightRevealOverlayAnimation";
                    } catch (Throwable th) {
                        th = th;
                        i = nextInt;
                        str = "FoldLightRevealOverlayAnimation";
                        Trace.asyncTraceForTrackEnd(4096L, str, i);
                        throw th;
                    }
                } else {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        i = this.I$0;
                        str = (String) this.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            Trace.asyncTraceForTrackEnd(4096L, str, i);
                            return unit;
                        } catch (Throwable th2) {
                            th = th2;
                            Trace.asyncTraceForTrackEnd(4096L, str, i);
                            throw th;
                        }
                    }
                    nextInt = this.I$0;
                    foldLightRevealOverlayAnimation = (FoldLightRevealOverlayAnimation) this.L$1;
                    str2 = (String) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th3) {
                        th = th3;
                        i = nextInt;
                        str = str2;
                        Trace.asyncTraceForTrackEnd(4096L, str, i);
                        throw th;
                    }
                }
                FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController = foldLightRevealOverlayAnimation.controller;
                if (fullscreenLightRevealAnimationController == null) {
                    fullscreenLightRevealAnimationController = null;
                }
                Object trackCuj = foldLightRevealOverlayAnimation.trackCuj(105, fullscreenLightRevealAnimationController.scrimView, new FoldLightRevealOverlayAnimation$playFoldLightRevealOverlayAnimation$2(foldLightRevealOverlayAnimation, null), this);
                if (trackCuj != coroutineSingletons) {
                    trackCuj = unit;
                }
                if (trackCuj == coroutineSingletons) {
                    return coroutineSingletons;
                }
                i = nextInt;
                str = str2;
                Trace.asyncTraceForTrackEnd(4096L, str, i);
                return unit;
            } catch (Throwable th4) {
                th = th4;
                i = nextInt;
                str = str2;
                Trace.asyncTraceForTrackEnd(4096L, str, i);
                throw th;
            }
            this.L$0 = str2;
            this.L$1 = null;
            this.I$0 = nextInt;
            this.label = 2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$4$2$1$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function1 {
        int label;
        final /* synthetic */ FoldLightRevealOverlayAnimation this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation, Continuation continuation) {
            super(1, continuation);
            this.this$0 = foldLightRevealOverlayAnimation;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new AnonymousClass2(this.this$0, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation = this.this$0;
                this.label = 1;
                if (FoldLightRevealOverlayAnimation.access$waitForGoToSleep(foldLightRevealOverlayAnimation, this) == coroutineSingletons) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FoldLightRevealOverlayAnimation$init$4$2$1(FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = foldLightRevealOverlayAnimation;
        this.$isFolded = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FoldLightRevealOverlayAnimation$init$4$2$1(this.this$0, this.$isFolded, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FoldLightRevealOverlayAnimation$init$4$2$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow areAnimationsEnabled = this.this$0.animationStatusRepository.areAnimationsEnabled();
            this.label = 1;
            obj = FlowKt.first(areAnimationsEnabled, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return unit;
            }
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue() && this.$isFolded) {
            Function1[] function1Arr = {new AnonymousClass1(this.this$0, null), new AnonymousClass2(this.this$0, null)};
            this.label = 2;
            if (SuspendKt.race(function1Arr, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        return unit;
    }
}
