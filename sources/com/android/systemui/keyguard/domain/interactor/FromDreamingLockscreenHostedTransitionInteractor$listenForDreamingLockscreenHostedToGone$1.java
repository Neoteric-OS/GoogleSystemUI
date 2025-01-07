package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1;
import com.android.systemui.keyguard.shared.model.BiometricUnlockMode;
import com.android.systemui.keyguard.shared.model.BiometricUnlockModel;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromDreamingLockscreenHostedTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FromDreamingLockscreenHostedTransitionInteractor this$0;

        public /* synthetic */ AnonymousClass2(FromDreamingLockscreenHostedTransitionInteractor fromDreamingLockscreenHostedTransitionInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = fromDreamingLockscreenHostedTransitionInteractor;
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
                    Object startTransitionTo$default2 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.DOZING, null, null, null, continuation, 14);
                    if (startTransitionTo$default2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                case 2:
                    Object startTransitionTo$default3 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.LOCKSCREEN, null, null, null, continuation, 14);
                    if (startTransitionTo$default3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                case 3:
                    Object startTransitionTo$default4 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.OCCLUDED, null, null, null, continuation, 14);
                    if (startTransitionTo$default4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                default:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default5 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.PRIMARY_BOUNCER, null, null, null, continuation, 14);
                    if (startTransitionTo$default5 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1(FromDreamingLockscreenHostedTransitionInteractor fromDreamingLockscreenHostedTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromDreamingLockscreenHostedTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        FromDreamingLockscreenHostedTransitionInteractor fromDreamingLockscreenHostedTransitionInteractor = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = fromDreamingLockscreenHostedTransitionInteractor.keyguardInteractor.biometricUnlockState;
        AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(((BiometricUnlockModel) obj2).mode == BiometricUnlockMode.WAKE_AND_UNLOCK_FROM_DREAM);
            }
        };
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(fromDreamingLockscreenHostedTransitionInteractor, 0);
        this.label = 1;
        readonlyStateFlow.collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass2, fromDreamingLockscreenHostedTransitionInteractor, anonymousClass1), this);
        return coroutineSingletons;
    }
}
