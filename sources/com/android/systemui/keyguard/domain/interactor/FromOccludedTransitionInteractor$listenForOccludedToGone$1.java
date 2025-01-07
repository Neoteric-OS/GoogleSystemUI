package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.util.kotlin.FlowKt;
import com.android.systemui.util.kotlin.Quint;
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

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromOccludedTransitionInteractor$listenForOccludedToGone$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromOccludedTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromOccludedTransitionInteractor$listenForOccludedToGone$1$2, reason: invalid class name */
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
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromOccludedTransitionInteractor$listenForOccludedToGone$1$4, reason: invalid class name */
    public final class AnonymousClass4 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FromOccludedTransitionInteractor this$0;

        public /* synthetic */ AnonymousClass4(FromOccludedTransitionInteractor fromOccludedTransitionInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = fromOccludedTransitionInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    Object startTransitionTo$default = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.GONE, null, null, null, continuation, 14);
                    if (startTransitionTo$default != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                case 1:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default2 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.ALTERNATE_BOUNCER, null, null, null, continuation, 14);
                    if (startTransitionTo$default2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                case 2:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default3 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.DREAMING, null, null, null, continuation, 14);
                    if (startTransitionTo$default3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                case 3:
                    Quint quint = (Quint) obj;
                    boolean booleanValue = ((Boolean) quint.third).booleanValue();
                    boolean booleanValue2 = ((Boolean) quint.fourth).booleanValue();
                    ((Boolean) quint.fifth).getClass();
                    Object access$startTransitionToLockscreenOrHub = FromOccludedTransitionInteractor.access$startTransitionToLockscreenOrHub(this.this$0, booleanValue, booleanValue2, continuation);
                    if (access$startTransitionToLockscreenOrHub != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                default:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default4 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.PRIMARY_BOUNCER, null, null, null, continuation, 14);
                    if (startTransitionTo$default4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromOccludedTransitionInteractor$listenForOccludedToGone$1(FromOccludedTransitionInteractor fromOccludedTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromOccludedTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromOccludedTransitionInteractor$listenForOccludedToGone$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromOccludedTransitionInteractor$listenForOccludedToGone$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FromOccludedTransitionInteractor fromOccludedTransitionInteractor = this.this$0;
            KeyguardInteractor keyguardInteractor = fromOccludedTransitionInteractor.keyguardInteractor;
            SafeFlow sample = FlowKt.sample(keyguardInteractor.isKeyguardOccluded, keyguardInteractor.isKeyguardShowing, AnonymousClass2.INSTANCE);
            AnonymousClass3 anonymousClass3 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromOccludedTransitionInteractor$listenForOccludedToGone$1.3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Pair pair = (Pair) obj2;
                    return Boolean.valueOf((((Boolean) pair.component1()).booleanValue() || ((Boolean) pair.component2()).booleanValue()) ? false : true);
                }
            };
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, 0);
            this.label = 1;
            Object collect = sample.collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass4, fromOccludedTransitionInteractor, anonymousClass3), this);
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
