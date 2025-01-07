package com.android.systemui.keyguard.domain.interactor;

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
public final class KeyguardInteractor$special$$inlined$transform$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$special$$inlined$transform$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ FlowCollector $$this$flow;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$special$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        public final class C00821 extends ContinuationImpl {
            int label;
            /* synthetic */ Object result;

            public C00821(Continuation continuation) {
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

        /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r11, kotlin.coroutines.Continuation r12) {
            /*
                r10 = this;
                boolean r0 = r12 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$special$$inlined$transform$1.AnonymousClass1.C00821
                if (r0 == 0) goto L13
                r0 = r12
                com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$special$$inlined$transform$1$1$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$special$$inlined$transform$1.AnonymousClass1.C00821) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$special$$inlined$transform$1$1$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$special$$inlined$transform$1$1$1
                r0.<init>(r12)
            L18:
                java.lang.Object r12 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L33
                if (r2 == r4) goto L2f
                if (r2 != r3) goto L27
                goto L2f
            L27:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r11)
                throw r10
            L2f:
                kotlin.ResultKt.throwOnFailure(r12)
                goto L9e
            L33:
                kotlin.ResultKt.throwOnFailure(r12)
                com.android.systemui.util.kotlin.Sextuple r11 = (com.android.systemui.util.kotlin.Sextuple) r11
                java.lang.Object r12 = r11.first
                java.lang.Number r12 = (java.lang.Number) r12
                float r12 = r12.floatValue()
                java.lang.Object r2 = r11.second
                com.android.systemui.keyguard.shared.model.StatusBarState r2 = (com.android.systemui.keyguard.shared.model.StatusBarState) r2
                java.lang.Object r5 = r11.third
                com.android.systemui.keyguard.shared.model.KeyguardState r5 = (com.android.systemui.keyguard.shared.model.KeyguardState) r5
                java.lang.Object r6 = r11.fourth
                com.android.systemui.keyguard.shared.model.TransitionStep r6 = (com.android.systemui.keyguard.shared.model.TransitionStep) r6
                java.lang.Object r6 = r11.fifth
                java.lang.Boolean r6 = (java.lang.Boolean) r6
                boolean r6 = r6.booleanValue()
                java.lang.Object r11 = r11.sixth
                java.lang.Boolean r11 = (java.lang.Boolean) r11
                boolean r11 = r11.booleanValue()
                com.android.systemui.keyguard.shared.model.StatusBarState r7 = com.android.systemui.keyguard.shared.model.StatusBarState.KEYGUARD
                r8 = 0
                r9 = 1065353216(0x3f800000, float:1.0)
                kotlinx.coroutines.flow.FlowCollector r10 = r10.$$this$flow
                if (r2 != r7) goto L85
                if (r6 == 0) goto L85
                com.android.systemui.keyguard.shared.model.KeyguardState r2 = com.android.systemui.keyguard.shared.model.KeyguardState.LOCKSCREEN
                if (r5 != r2) goto L85
                int r2 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
                if (r2 != 0) goto L70
                goto L85
            L70:
                r11 = 1064514355(0x3f733333, float:0.95)
                float r11 = android.util.MathUtils.constrainedMap(r8, r9, r11, r9, r12)
                java.lang.Float r12 = new java.lang.Float
                r12.<init>(r11)
                r0.label = r4
                java.lang.Object r10 = r10.emit(r12, r0)
                if (r10 != r1) goto L9e
                return r1
            L85:
                int r2 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
                if (r2 != 0) goto L8a
                goto L8e
            L8a:
                int r12 = (r12 > r9 ? 1 : (r12 == r9 ? 0 : -1))
                if (r12 != 0) goto L9e
            L8e:
                if (r11 != 0) goto L9e
                java.lang.Float r11 = new java.lang.Float
                r11.<init>(r9)
                r0.label = r3
                java.lang.Object r10 = r10.emit(r11, r0)
                if (r10 != r1) goto L9e
                return r1
            L9e:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$special$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardInteractor$special$$inlined$transform$1(Flow flow, Continuation continuation) {
        super(2, continuation);
        this.$this_transform = flow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        KeyguardInteractor$special$$inlined$transform$1 keyguardInteractor$special$$inlined$transform$1 = new KeyguardInteractor$special$$inlined$transform$1(this.$this_transform, continuation);
        keyguardInteractor$special$$inlined$transform$1.L$0 = obj;
        return keyguardInteractor$special$$inlined$transform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardInteractor$special$$inlined$transform$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
