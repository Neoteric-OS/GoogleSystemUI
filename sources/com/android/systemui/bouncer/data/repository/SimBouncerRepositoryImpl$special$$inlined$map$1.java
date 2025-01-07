package com.android.systemui.bouncer.data.repository;

import com.android.keyguard.KeyguardUpdateMonitor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SimBouncerRepositoryImpl$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ KeyguardUpdateMonitor $keyguardUpdateMonitor$inlined;
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ SimBouncerRepositoryImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ KeyguardUpdateMonitor $keyguardUpdateMonitor$inlined;
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ SimBouncerRepositoryImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            Object L$0;
            Object L$1;
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

        public AnonymousClass2(FlowCollector flowCollector, SimBouncerRepositoryImpl simBouncerRepositoryImpl, KeyguardUpdateMonitor keyguardUpdateMonitor) {
            this.$this_unsafeFlow = flowCollector;
            this.this$0 = simBouncerRepositoryImpl;
            this.$keyguardUpdateMonitor$inlined = keyguardUpdateMonitor;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x00b2  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00c9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00bb  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x009f A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00a0  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
            /*
                Method dump skipped, instructions count: 205
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public SimBouncerRepositoryImpl$special$$inlined$map$1(Flow flow, SimBouncerRepositoryImpl simBouncerRepositoryImpl, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = simBouncerRepositoryImpl;
        this.$keyguardUpdateMonitor$inlined = keyguardUpdateMonitor;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this.this$0, this.$keyguardUpdateMonitor$inlined), continuation);
        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
    }
}
