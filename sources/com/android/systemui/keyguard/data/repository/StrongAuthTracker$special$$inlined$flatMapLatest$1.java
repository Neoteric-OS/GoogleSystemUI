package com.android.systemui.keyguard.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StrongAuthTracker$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ StrongAuthTracker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StrongAuthTracker$special$$inlined$flatMapLatest$1(Continuation continuation, StrongAuthTracker strongAuthTracker) {
        super(3, continuation);
        this.this$0 = strongAuthTracker;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        StrongAuthTracker$special$$inlined$flatMapLatest$1 strongAuthTracker$special$$inlined$flatMapLatest$1 = new StrongAuthTracker$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        strongAuthTracker$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        strongAuthTracker$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return strongAuthTracker$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            final int intValue = ((Number) this.L$1).intValue();
            final StrongAuthTracker strongAuthTracker = this.this$0;
            final StateFlowImpl stateFlowImpl = strongAuthTracker._authFlags;
            FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new StrongAuthTracker$currentUserAuthFlags$1$3(intValue, this.this$0, null), new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.keyguard.data.repository.StrongAuthTracker$currentUserAuthFlags$lambda$2$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.repository.StrongAuthTracker$currentUserAuthFlags$lambda$2$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;
                    public final /* synthetic */ int $userId$inlined;
                    public final /* synthetic */ StrongAuthTracker this$0;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.keyguard.data.repository.StrongAuthTracker$currentUserAuthFlags$lambda$2$$inlined$map$1$2$1, reason: invalid class name */
                    public final class AnonymousClass1 extends ContinuationImpl {
                        Object L$0;
                        int label;
                        /* synthetic */ Object result;

                        public AnonymousClass1(Continuation continuation) {
                            super(continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return AnonymousClass2.this.emit(null, this);
                        }
                    }

                    public AnonymousClass2(FlowCollector flowCollector, int i, StrongAuthTracker strongAuthTracker) {
                        this.$this_unsafeFlow = flowCollector;
                        this.$userId$inlined = i;
                        this.this$0 = strongAuthTracker;
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
                            boolean r0 = r6 instanceof com.android.systemui.keyguard.data.repository.StrongAuthTracker$currentUserAuthFlags$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.systemui.keyguard.data.repository.StrongAuthTracker$currentUserAuthFlags$lambda$2$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.repository.StrongAuthTracker$currentUserAuthFlags$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.keyguard.data.repository.StrongAuthTracker$currentUserAuthFlags$lambda$2$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.repository.StrongAuthTracker$currentUserAuthFlags$lambda$2$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L4c
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            com.android.systemui.keyguard.shared.model.AuthenticationFlags r5 = (com.android.systemui.keyguard.shared.model.AuthenticationFlags) r5
                            com.android.systemui.keyguard.shared.model.AuthenticationFlags r5 = new com.android.systemui.keyguard.shared.model.AuthenticationFlags
                            com.android.systemui.keyguard.data.repository.StrongAuthTracker r6 = r4.this$0
                            int r2 = r4.$userId$inlined
                            int r6 = r6.getStrongAuthForUser(r2)
                            r5.<init>(r2, r6)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L4c
                            return r1
                        L4c:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.StrongAuthTracker$currentUserAuthFlags$lambda$2$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector2, Continuation continuation) {
                    StateFlowImpl.this.collect(new AnonymousClass2(flowCollector2, intValue, strongAuthTracker), continuation);
                    return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }, new StrongAuthTracker$currentUserAuthFlags$1$2(2, null), 0));
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, this) == coroutineSingletons) {
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
