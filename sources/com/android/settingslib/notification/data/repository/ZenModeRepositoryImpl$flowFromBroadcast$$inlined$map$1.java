package com.android.settingslib.notification.data.repository;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1 implements Flow {
    public final /* synthetic */ Object $mapper$inlined;
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ Lambda $mapper$inlined;
        public final /* synthetic */ FlowCollector $this_unsafeFlow;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1$2$1, reason: invalid class name */
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

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(FlowCollector flowCollector, Function1 function1) {
            this.$this_unsafeFlow = flowCollector;
            this.$mapper$inlined = (Lambda) function1;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        /* JADX WARN: Type inference failed for: r6v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r6
                com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1$2$1 r0 = (com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1$2$1 r0 = new com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1$2$1
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L2f
                if (r2 != r3) goto L27
                kotlin.ResultKt.throwOnFailure(r6)
                goto L45
            L27:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L2f:
                kotlin.ResultKt.throwOnFailure(r6)
                android.content.Intent r5 = (android.content.Intent) r5
                kotlin.jvm.internal.Lambda r6 = r4.$mapper$inlined
                java.lang.Object r5 = r6.invoke(r5)
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                java.lang.Object r4 = r4.emit(r5, r0)
                if (r4 != r1) goto L45
                return r1
            L45:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1(ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1 zenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1, Function1 function1) {
        this.$this_unsafeTransform$inlined = zenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1;
        this.$mapper$inlined = (Lambda) function1;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                Object collect = ((ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1) this.$this_unsafeTransform$inlined).collect(new AnonymousClass2(flowCollector, (Lambda) this.$mapper$inlined), continuation);
                if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            default:
                Object collect2 = ((SharedFlow) this.$this_unsafeTransform$inlined).collect(new ZenModeRepositoryImpl$flowFromBroadcast$$inlined$filter$1$2((String) this.$mapper$inlined, flowCollector), continuation);
                if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
        }
        return Unit.INSTANCE;
    }

    public ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1(SharedFlow sharedFlow, String str) {
        this.$this_unsafeTransform$inlined = sharedFlow;
        this.$mapper$inlined = str;
    }
}
