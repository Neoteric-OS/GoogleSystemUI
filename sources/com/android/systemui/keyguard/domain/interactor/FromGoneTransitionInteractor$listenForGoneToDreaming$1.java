package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.util.kotlin.FlowKt;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromGoneTransitionInteractor$listenForGoneToDreaming$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromGoneTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromGoneTransitionInteractor$listenForGoneToDreaming$1$2, reason: invalid class name */
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

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromGoneTransitionInteractor$listenForGoneToDreaming$1$4, reason: invalid class name */
    public final class AnonymousClass4 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FromGoneTransitionInteractor this$0;

        public /* synthetic */ AnonymousClass4(FromGoneTransitionInteractor fromGoneTransitionInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = fromGoneTransitionInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    Object startTransitionTo$default = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.DREAMING, null, null, null, continuation, 14);
                    return startTransitionTo$default == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default : Unit.INSTANCE;
                case 1:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default2 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.DREAMING_LOCKSCREEN_HOSTED, null, null, null, continuation, 14);
                    return startTransitionTo$default2 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default2 : Unit.INSTANCE;
                case 2:
                    Object startTransitionTo$default3 = TransitionInteractor.startTransitionTo$default(this.this$0, ((Boolean) ((Pair) obj).component2()).booleanValue() ? KeyguardState.GLANCEABLE_HUB : ((Boolean) ((StateFlowImpl) this.this$0.keyguardInteractor.isKeyguardOccluded).getValue()).booleanValue() ? KeyguardState.OCCLUDED : KeyguardState.LOCKSCREEN, null, null, null, continuation, 14);
                    return startTransitionTo$default3 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default3 : Unit.INSTANCE;
                default:
                    ((Number) obj).longValue();
                    boolean booleanValue = ((Boolean) ((StateFlowImpl) this.this$0.keyguardInteractor.isKeyguardOccluded).getValue()).booleanValue();
                    Unit unit = Unit.INSTANCE;
                    if (!booleanValue) {
                        return unit;
                    }
                    Object startTransitionTo$default4 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.OCCLUDED, null, null, "Dismissible keyguard with occlusion", continuation, 6);
                    return startTransitionTo$default4 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default4 : unit;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromGoneTransitionInteractor$listenForGoneToDreaming$1(FromGoneTransitionInteractor fromGoneTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromGoneTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromGoneTransitionInteractor$listenForGoneToDreaming$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromGoneTransitionInteractor$listenForGoneToDreaming$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FromGoneTransitionInteractor fromGoneTransitionInteractor = this.this$0;
            KeyguardInteractor keyguardInteractor = fromGoneTransitionInteractor.keyguardInteractor;
            SafeFlow sample = FlowKt.sample(keyguardInteractor.isAbleToDream, keyguardInteractor.isActiveDreamLockscreenHosted, AnonymousClass2.INSTANCE);
            AnonymousClass3 anonymousClass3 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromGoneTransitionInteractor$listenForGoneToDreaming$1.3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Pair pair = (Pair) obj2;
                    return Boolean.valueOf(((Boolean) pair.component1()).booleanValue() && !((Boolean) pair.component2()).booleanValue());
                }
            };
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, 0);
            this.label = 1;
            Object collect = sample.collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass4, fromGoneTransitionInteractor, anonymousClass3), this);
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
