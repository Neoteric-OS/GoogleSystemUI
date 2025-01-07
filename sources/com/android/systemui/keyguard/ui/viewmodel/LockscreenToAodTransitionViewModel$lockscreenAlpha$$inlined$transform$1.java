package com.android.systemui.keyguard.ui.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ FlowCollector $$this$flow;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        public final class C01431 extends ContinuationImpl {
            int label;
            /* synthetic */ Object result;

            public C01431(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass1.this.emit(null, this);
            }
        }

        public AnonymousClass1(FlowCollector flowCollector) {
            this.$$this$flow = flowCollector;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1.AnonymousClass1.C01431
                if (r0 == 0) goto L13
                r0 = r6
                com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1$1$1 r0 = (com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1.AnonymousClass1.C01431) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1$1$1 r0 = new com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1$1$1
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L2f
                if (r2 != r3) goto L27
                kotlin.ResultKt.throwOnFailure(r6)
                goto L5a
            L27:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L2f:
                kotlin.ResultKt.throwOnFailure(r6)
                kotlin.Pair r5 = (kotlin.Pair) r5
                java.lang.Object r6 = r5.component1()
                java.lang.Number r6 = (java.lang.Number) r6
                float r6 = r6.floatValue()
                java.lang.Object r5 = r5.component2()
                com.android.systemui.power.shared.model.WakefulnessModel r5 = (com.android.systemui.power.shared.model.WakefulnessModel) r5
                com.android.systemui.power.shared.model.WakeSleepReason r5 = r5.lastSleepReason
                com.android.systemui.power.shared.model.WakeSleepReason r2 = com.android.systemui.power.shared.model.WakeSleepReason.FOLD
                if (r5 == r2) goto L5a
                java.lang.Float r5 = new java.lang.Float
                r5.<init>(r6)
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r4 = r4.$$this$flow
                java.lang.Object r4 = r4.emit(r5, r0)
                if (r4 != r1) goto L5a
                return r1
            L5a:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1(Flow flow, Continuation continuation) {
        super(2, continuation);
        this.$this_transform = flow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1 lockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1 = new LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1(this.$this_transform, continuation);
        lockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1.L$0 = obj;
        return lockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LockscreenToAodTransitionViewModel$lockscreenAlpha$$inlined$transform$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow flow = this.$this_transform;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector);
            this.label = 1;
            if (flow.collect(anonymousClass1, this) == coroutineSingletons) {
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
