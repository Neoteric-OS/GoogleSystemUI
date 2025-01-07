package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.util.kotlin.Utils;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromAodTransitionInteractor$listenForAodToAwake$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromAodTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromAodTransitionInteractor$listenForAodToAwake$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FromAodTransitionInteractor this$0;

        public AnonymousClass2(FromAodTransitionInteractor fromAodTransitionInteractor) {
            this.this$0 = fromAodTransitionInteractor;
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0073, code lost:
        
            if (r13 == false) goto L27;
         */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0124  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x01a2 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x00ee  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x008f  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(kotlin.Triple r14, kotlin.coroutines.Continuation r15) {
            /*
                Method dump skipped, instructions count: 420
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.FromAodTransitionInteractor$listenForAodToAwake$1.AnonymousClass2.emit(kotlin.Triple, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromAodTransitionInteractor$listenForAodToAwake$1(FromAodTransitionInteractor fromAodTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromAodTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromAodTransitionInteractor$listenForAodToAwake$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromAodTransitionInteractor$listenForAodToAwake$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Utils.Companion companion = Utils.Companion;
            FromAodTransitionInteractor fromAodTransitionInteractor = this.this$0;
            Flow debounce = FlowKt.debounce(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1(fromAodTransitionInteractor.powerInteractor.detailedWakefulness, fromAodTransitionInteractor, new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromAodTransitionInteractor$listenForAodToAwake$1.1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(((WakefulnessModel) obj2).isAwake());
                }
            }), 50L);
            FromAodTransitionInteractor fromAodTransitionInteractor2 = this.this$0;
            SafeFlow sample = companion.sample(debounce, fromAodTransitionInteractor2.transitionInteractor.startedKeyguardTransitionStep, fromAodTransitionInteractor2.wakeToGoneInteractor.canWakeDirectlyToGone);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0);
            this.label = 1;
            if (sample.collect(anonymousClass2, this) == coroutineSingletons) {
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
