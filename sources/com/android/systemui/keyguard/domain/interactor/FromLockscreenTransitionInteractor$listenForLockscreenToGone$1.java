package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionModeOnCanceled;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromLockscreenTransitionInteractor$listenForLockscreenToGone$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromLockscreenTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor$listenForLockscreenToGone$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FromLockscreenTransitionInteractor this$0;

        public /* synthetic */ AnonymousClass2(FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = fromLockscreenTransitionInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.GONE, null, TransitionModeOnCanceled.RESET, null, continuation, 10);
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
                    Object startTransitionTo$default3 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.OCCLUDED, null, null, null, continuation, 14);
                    if (startTransitionTo$default3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                default:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default4 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.PRIMARY_BOUNCER, null, null, "#listenForLockscreenToPrimaryBouncer", continuation, 6);
                    if (startTransitionTo$default4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromLockscreenTransitionInteractor$listenForLockscreenToGone$1(FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromLockscreenTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromLockscreenTransitionInteractor$listenForLockscreenToGone$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromLockscreenTransitionInteractor$listenForLockscreenToGone$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return unit;
        }
        ResultKt.throwOnFailure(obj);
        FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor = this.this$0;
        MutableStateFlow mutableStateFlow = fromLockscreenTransitionInteractor.keyguardInteractor.isKeyguardGoingAway;
        AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor$listenForLockscreenToGone$1.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Boolean bool = (Boolean) obj2;
                bool.booleanValue();
                return bool;
            }
        };
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(fromLockscreenTransitionInteractor, 0);
        this.label = 1;
        ((StateFlowImpl) mutableStateFlow).collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass2, fromLockscreenTransitionInteractor, anonymousClass1), this);
        return coroutineSingletons;
    }
}
