package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardState$$inlined$filter$1;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.util.kotlin.Septuple;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromAlternateBouncerTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1$4, reason: invalid class name */
    public final class AnonymousClass4 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FromAlternateBouncerTransitionInteractor this$0;

        public /* synthetic */ AnonymousClass4(FromAlternateBouncerTransitionInteractor fromAlternateBouncerTransitionInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = fromAlternateBouncerTransitionInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    Object startTransitionTo$default = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.GONE, null, null, null, continuation, 14);
                    return startTransitionTo$default == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default : Unit.INSTANCE;
                case 1:
                    Septuple septuple = (Septuple) obj;
                    boolean booleanValue = ((Boolean) septuple.third).booleanValue();
                    boolean booleanValue2 = ((Boolean) septuple.fourth).booleanValue();
                    boolean booleanValue3 = ((Boolean) septuple.fifth).booleanValue();
                    boolean booleanValue4 = ((Boolean) septuple.sixth).booleanValue();
                    boolean booleanValue5 = ((Boolean) septuple.seventh).booleanValue();
                    Unit unit = Unit.INSTANCE;
                    if (booleanValue4) {
                        return unit;
                    }
                    Object startTransitionTo$default2 = TransitionInteractor.startTransitionTo$default(this.this$0, !booleanValue ? booleanValue2 ? KeyguardState.AOD : KeyguardState.DOZING : booleanValue3 ? KeyguardState.GLANCEABLE_HUB : booleanValue5 ? KeyguardState.OCCLUDED : KeyguardState.LOCKSCREEN, null, null, null, continuation, 14);
                    return startTransitionTo$default2 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default2 : unit;
                default:
                    ((Boolean) obj).getClass();
                    Object startTransitionTo$default3 = TransitionInteractor.startTransitionTo$default(this.this$0, KeyguardState.PRIMARY_BOUNCER, null, null, null, continuation, 14);
                    return startTransitionTo$default3 == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default3 : Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1(FromAlternateBouncerTransitionInteractor fromAlternateBouncerTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromAlternateBouncerTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FromAlternateBouncerTransitionInteractor fromAlternateBouncerTransitionInteractor = this.this$0;
            KeyguardInteractor keyguardInteractor = fromAlternateBouncerTransitionInteractor.keyguardInteractor;
            ChannelLimitedFlowMerge merge = FlowKt.merge(new FromAlternateBouncerTransitionInteractor$special$$inlined$map$1(new FromAlternateBouncerTransitionInteractor$special$$inlined$map$1(keyguardInteractor.isKeyguardGoingAway, 1), 2), FlowKt.transformLatest(keyguardInteractor.isKeyguardOccluded, new FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1$invokeSuspend$$inlined$flatMapLatest$1(fromAlternateBouncerTransitionInteractor, null)));
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, 0);
            this.label = 1;
            Object collect = merge.collect(new TransitionInteractor$filterRelevantKeyguardState$$inlined$filter$1.AnonymousClass2(anonymousClass4, fromAlternateBouncerTransitionInteractor), this);
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
