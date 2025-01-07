package com.android.systemui.util.kotlin;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FlowKt$pairwiseBy$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $getInitialValue;
    final /* synthetic */ Flow $this_pairwiseBy;
    final /* synthetic */ Function3 $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ FlowCollector $$this$flow;
        public final /* synthetic */ Ref$ObjectRef $previousValue;
        public final /* synthetic */ Function3 $transform;

        public AnonymousClass1(Function3 function3, Ref$ObjectRef ref$ObjectRef, FlowCollector flowCollector) {
            this.$$this$flow = flowCollector;
            this.$transform = function3;
            this.$previousValue = ref$ObjectRef;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0072 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
            /*
                r6 = this;
                boolean r0 = r8 instanceof com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3$1$emit$1
                if (r0 == 0) goto L13
                r0 = r8
                com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3$1$emit$1 r0 = (com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3$1$emit$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3$1$emit$1 r0 = new com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3$1$emit$1
                r0.<init>(r6, r8)
            L18:
                java.lang.Object r8 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L46
                if (r2 == r4) goto L38
                if (r2 != r3) goto L30
                java.lang.Object r6 = r0.L$1
                java.lang.Object r7 = r0.L$0
                com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3$1 r7 = (com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3.AnonymousClass1) r7
                kotlin.ResultKt.throwOnFailure(r8)
                goto L75
            L30:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L38:
                java.lang.Object r6 = r0.L$2
                kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                java.lang.Object r7 = r0.L$1
                java.lang.Object r2 = r0.L$0
                com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3$1 r2 = (com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3.AnonymousClass1) r2
                kotlin.ResultKt.throwOnFailure(r8)
                goto L63
            L46:
                kotlin.ResultKt.throwOnFailure(r8)
                kotlin.jvm.internal.Ref$ObjectRef r8 = r6.$previousValue
                java.lang.Object r8 = r8.element
                r0.L$0 = r6
                r0.L$1 = r7
                kotlinx.coroutines.flow.FlowCollector r2 = r6.$$this$flow
                r0.L$2 = r2
                r0.label = r4
                kotlin.jvm.functions.Function3 r4 = r6.$transform
                java.lang.Object r8 = r4.invoke(r8, r7, r0)
                if (r8 != r1) goto L60
                return r1
            L60:
                r5 = r2
                r2 = r6
                r6 = r5
            L63:
                r0.L$0 = r2
                r0.L$1 = r7
                r4 = 0
                r0.L$2 = r4
                r0.label = r3
                java.lang.Object r6 = r6.emit(r8, r0)
                if (r6 != r1) goto L73
                return r1
            L73:
                r6 = r7
                r7 = r2
            L75:
                kotlin.jvm.internal.Ref$ObjectRef r7 = r7.$previousValue
                r7.element = r6
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.kotlin.FlowKt$pairwiseBy$3.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt$pairwiseBy$3(Function1 function1, Flow flow, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$getInitialValue = function1;
        this.$this_pairwiseBy = flow;
        this.$transform = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlowKt$pairwiseBy$3 flowKt$pairwiseBy$3 = new FlowKt$pairwiseBy$3(this.$getInitialValue, this.$this_pairwiseBy, this.$transform, continuation);
        flowKt$pairwiseBy$3.L$0 = obj;
        return flowKt$pairwiseBy$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt$pairwiseBy$3) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        Ref$ObjectRef ref$ObjectRef;
        Ref$ObjectRef ref$ObjectRef2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
            ref$ObjectRef = new Ref$ObjectRef();
            Function1 function1 = this.$getInitialValue;
            this.L$0 = flowCollector;
            this.L$1 = ref$ObjectRef;
            this.L$2 = ref$ObjectRef;
            this.label = 1;
            obj = function1.invoke(this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
            ref$ObjectRef2 = ref$ObjectRef;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            ref$ObjectRef = (Ref$ObjectRef) this.L$2;
            ref$ObjectRef2 = (Ref$ObjectRef) this.L$1;
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        ref$ObjectRef.element = obj;
        Flow flow = this.$this_pairwiseBy;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$transform, ref$ObjectRef2, flowCollector);
        this.L$0 = null;
        this.L$1 = null;
        this.L$2 = null;
        this.label = 2;
        if (flow.collect(anonymousClass1, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
