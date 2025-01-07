package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1;
import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1;
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
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToOccluded$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromDreamingLockscreenHostedTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToOccluded$1$2, reason: invalid class name */
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
    public FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToOccluded$1(FromDreamingLockscreenHostedTransitionInteractor fromDreamingLockscreenHostedTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromDreamingLockscreenHostedTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToOccluded$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToOccluded$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FromDreamingLockscreenHostedTransitionInteractor fromDreamingLockscreenHostedTransitionInteractor = this.this$0;
            KeyguardInteractor keyguardInteractor = fromDreamingLockscreenHostedTransitionInteractor.keyguardInteractor;
            SafeFlow sample = FlowKt.sample(keyguardInteractor.isActiveDreamLockscreenHosted, keyguardInteractor.isKeyguardOccluded, AnonymousClass2.INSTANCE);
            AnonymousClass3 anonymousClass3 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToOccluded$1.3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Pair pair = (Pair) obj2;
                    return Boolean.valueOf(((Boolean) pair.component2()).booleanValue() && !((Boolean) pair.component1()).booleanValue());
                }
            };
            FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1.AnonymousClass2 anonymousClass2 = new FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1.AnonymousClass2(this.this$0, 3);
            this.label = 1;
            Object collect = sample.collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass2, fromDreamingLockscreenHostedTransitionInteractor, anonymousClass3), this);
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
