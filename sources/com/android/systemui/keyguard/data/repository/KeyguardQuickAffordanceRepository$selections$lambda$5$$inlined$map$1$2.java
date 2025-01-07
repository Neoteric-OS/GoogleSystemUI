package com.android.systemui.keyguard.data.repository;

import java.util.Set;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2 implements FlowCollector {
    public final /* synthetic */ Set $configs$inlined;
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2$1, reason: invalid class name */
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
            return KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2.this.emit(null, this);
        }
    }

    public KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2(FlowCollector flowCollector, Set set) {
        this.$this_unsafeFlow = flowCollector;
        this.$configs$inlined = set;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r11)
            goto L98
        L27:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2f:
            kotlin.ResultKt.throwOnFailure(r11)
            java.util.Map r10 = (java.util.Map) r10
            java.util.LinkedHashMap r11 = new java.util.LinkedHashMap
            int r2 = r10.size()
            int r2 = kotlin.collections.MapsKt__MapsJVMKt.mapCapacity(r2)
            r11.<init>(r2)
            java.util.Set r10 = r10.entrySet()
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.Iterator r10 = r10.iterator()
        L4b:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L8d
            java.lang.Object r2 = r10.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r4 = r2.getKey()
            java.lang.Object r2 = r2.getValue()
            java.util.List r2 = (java.util.List) r2
            java.util.Set r5 = r9.$configs$inlined
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r5 = r5.iterator()
        L6e:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L89
            java.lang.Object r7 = r5.next()
            r8 = r7
            com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig r8 = (com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig) r8
            java.lang.String r8 = r8.getKey()
            boolean r8 = r2.contains(r8)
            if (r8 == 0) goto L6e
            r6.add(r7)
            goto L6e
        L89:
            r11.put(r4, r6)
            goto L4b
        L8d:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r9 = r9.$this_unsafeFlow
            java.lang.Object r9 = r9.emit(r11, r0)
            if (r9 != r1) goto L98
            return r1
        L98:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
