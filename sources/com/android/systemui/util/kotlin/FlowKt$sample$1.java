package com.android.systemui.util.kotlin;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FlowKt$sample$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Flow $other;
    final /* synthetic */ Flow $this_sample;
    final /* synthetic */ Function3 $transform;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.util.kotlin.FlowKt$sample$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ FlowCollector $$this$flow;
        final /* synthetic */ Flow $other;
        final /* synthetic */ Flow $this_sample;
        final /* synthetic */ Function3 $transform;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.util.kotlin.FlowKt$sample$1$1$1, reason: invalid class name and collision with other inner class name */
        public final class C02631 implements FlowCollector {
            public final /* synthetic */ FlowCollector $$this$flow;
            public final /* synthetic */ Object $noVal;
            public final /* synthetic */ AtomicReference $sampledRef;
            public final /* synthetic */ Function3 $transform;

            public C02631(AtomicReference atomicReference, Object obj, FlowCollector flowCollector, Function3 function3) {
                this.$sampledRef = atomicReference;
                this.$noVal = obj;
                this.$$this$flow = flowCollector;
                this.$transform = function3;
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0068 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:19:0x003c  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                /*
                    r6 = this;
                    boolean r0 = r8 instanceof com.android.systemui.util.kotlin.FlowKt$sample$1$1$1$emit$1
                    if (r0 == 0) goto L13
                    r0 = r8
                    com.android.systemui.util.kotlin.FlowKt$sample$1$1$1$emit$1 r0 = (com.android.systemui.util.kotlin.FlowKt$sample$1$1$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    com.android.systemui.util.kotlin.FlowKt$sample$1$1$1$emit$1 r0 = new com.android.systemui.util.kotlin.FlowKt$sample$1$1$1$emit$1
                    r0.<init>(r6, r8)
                L18:
                    java.lang.Object r8 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r2 = r0.label
                    kotlin.Unit r3 = kotlin.Unit.INSTANCE
                    r4 = 2
                    r5 = 1
                    if (r2 == 0) goto L3c
                    if (r2 == r5) goto L34
                    if (r2 != r4) goto L2c
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L69
                L2c:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L34:
                    java.lang.Object r6 = r0.L$0
                    kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L5d
                L3c:
                    kotlin.ResultKt.throwOnFailure(r8)
                    java.util.concurrent.atomic.AtomicReference r8 = r6.$sampledRef
                    java.lang.Object r8 = r8.get()
                    java.lang.Object r2 = r6.$noVal
                    boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r2)
                    if (r2 != 0) goto L69
                    kotlinx.coroutines.flow.FlowCollector r2 = r6.$$this$flow
                    r0.L$0 = r2
                    r0.label = r5
                    kotlin.jvm.functions.Function3 r6 = r6.$transform
                    java.lang.Object r8 = r6.invoke(r7, r8, r0)
                    if (r8 != r1) goto L5c
                    return r1
                L5c:
                    r6 = r2
                L5d:
                    r7 = 0
                    r0.L$0 = r7
                    r0.label = r4
                    java.lang.Object r6 = r6.emit(r8, r0)
                    if (r6 != r1) goto L69
                    return r1
                L69:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.kotlin.FlowKt$sample$1.AnonymousClass1.C02631.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Flow flow, Flow flow2, FlowCollector flowCollector, Function3 function3, Continuation continuation) {
            super(2, continuation);
            this.$this_sample = flow;
            this.$other = flow2;
            this.$$this$flow = flowCollector;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_sample, this.$other, this.$$this$flow, this.$transform, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Job job;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Object obj2 = new Object();
                AtomicReference atomicReference = new AtomicReference(obj2);
                StandaloneCoroutine launch$default = BuildersKt.launch$default(coroutineScope, Dispatchers.Unconfined, null, new FlowKt$sample$1$1$job$1(this.$other, atomicReference, null), 2);
                Flow flow = this.$this_sample;
                C02631 c02631 = new C02631(atomicReference, obj2, this.$$this$flow, this.$transform);
                this.L$0 = launch$default;
                this.label = 1;
                if (flow.collect(c02631, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                job = launch$default;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                job = (Job) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            job.cancel(null);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt$sample$1(Flow flow, Flow flow2, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_sample = flow;
        this.$other = flow2;
        this.$transform = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlowKt$sample$1 flowKt$sample$1 = new FlowKt$sample$1(this.$this_sample, this.$other, this.$transform, continuation);
        flowKt$sample$1.L$0 = obj;
        return flowKt$sample$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt$sample$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_sample, this.$other, (FlowCollector) this.L$0, this.$transform, null);
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(this, anonymousClass1) == coroutineSingletons) {
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
