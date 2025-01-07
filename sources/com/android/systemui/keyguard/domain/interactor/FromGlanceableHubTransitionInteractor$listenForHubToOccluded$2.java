package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.FromGlanceableHubTransitionInteractor$listenForHubToGone$1;
import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1;
import com.android.systemui.util.kotlin.Utils;
import com.android.systemui.util.kotlin.Utils$Companion$sample$$inlined$combine$1;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromGlanceableHubTransitionInteractor$listenForHubToOccluded$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromGlanceableHubTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromGlanceableHubTransitionInteractor$listenForHubToOccluded$2$2, reason: invalid class name */
    final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function3 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            Boolean bool2 = (Boolean) obj2;
            bool2.booleanValue();
            return new Pair(bool, bool2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromGlanceableHubTransitionInteractor$listenForHubToOccluded$2(FromGlanceableHubTransitionInteractor fromGlanceableHubTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromGlanceableHubTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromGlanceableHubTransitionInteractor$listenForHubToOccluded$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromGlanceableHubTransitionInteractor$listenForHubToOccluded$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FromGlanceableHubTransitionInteractor fromGlanceableHubTransitionInteractor = this.this$0;
            KeyguardInteractor keyguardInteractor = fromGlanceableHubTransitionInteractor.keyguardInteractor;
            FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardInteractor.isKeyguardOccluded, keyguardInteractor.isDreaming, AnonymousClass2.INSTANCE);
            int i2 = Duration.$r8$clinit;
            Utils$Companion$sample$$inlined$combine$1 sampleFilter = Utils.Companion.sampleFilter(FlowKt.debounce(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, DelayKt.m1785toDelayMillisLRDsOJo(DurationKt.toDuration(200, DurationUnit.MILLISECONDS))), this.this$0.communalSceneInteractor.isLaunchingWidget, new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromGlanceableHubTransitionInteractor$listenForHubToOccluded$2.3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(!((Boolean) obj2).booleanValue());
                }
            });
            AnonymousClass4 anonymousClass4 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromGlanceableHubTransitionInteractor$listenForHubToOccluded$2.4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Pair pair = (Pair) obj2;
                    return Boolean.valueOf(((Boolean) pair.component1()).booleanValue() && !((Boolean) pair.component2()).booleanValue());
                }
            };
            FromGlanceableHubTransitionInteractor$listenForHubToGone$1.AnonymousClass4 anonymousClass42 = new FromGlanceableHubTransitionInteractor$listenForHubToGone$1.AnonymousClass4(this.this$0, 3);
            this.label = 1;
            Object collect = sampleFilter.collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass42, fromGlanceableHubTransitionInteractor, anonymousClass4), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
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
