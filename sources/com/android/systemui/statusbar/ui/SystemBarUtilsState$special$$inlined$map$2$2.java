package com.android.systemui.statusbar.ui;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemBarUtilsState$special$$inlined$map$2$2 implements FlowCollector {
    public final /* synthetic */ SystemBarUtilsProxyImpl $proxy$inlined;
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.ui.SystemBarUtilsState$special$$inlined$map$2$2$1, reason: invalid class name */
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
            return SystemBarUtilsState$special$$inlined$map$2$2.this.emit(null, this);
        }
    }

    public SystemBarUtilsState$special$$inlined$map$2$2(FlowCollector flowCollector, SystemBarUtilsProxyImpl systemBarUtilsProxyImpl) {
        this.$this_unsafeFlow = flowCollector;
        this.$proxy$inlined = systemBarUtilsProxyImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r4, kotlin.coroutines.Continuation r5) {
        /*
            r3 = this;
            boolean r4 = r5 instanceof com.android.systemui.statusbar.ui.SystemBarUtilsState$special$$inlined$map$2$2.AnonymousClass1
            if (r4 == 0) goto L13
            r4 = r5
            com.android.systemui.statusbar.ui.SystemBarUtilsState$special$$inlined$map$2$2$1 r4 = (com.android.systemui.statusbar.ui.SystemBarUtilsState$special$$inlined$map$2$2.AnonymousClass1) r4
            int r0 = r4.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r0 & r1
            if (r2 == 0) goto L13
            int r0 = r0 - r1
            r4.label = r0
            goto L18
        L13:
            com.android.systemui.statusbar.ui.SystemBarUtilsState$special$$inlined$map$2$2$1 r4 = new com.android.systemui.statusbar.ui.SystemBarUtilsState$special$$inlined$map$2$2$1
            r4.<init>(r5)
        L18:
            java.lang.Object r5 = r4.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            kotlin.ResultKt.throwOnFailure(r5)
            goto L48
        L27:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L2f:
            kotlin.ResultKt.throwOnFailure(r5)
            com.android.systemui.statusbar.ui.SystemBarUtilsProxyImpl r5 = r3.$proxy$inlined
            int r5 = r5.getStatusBarHeaderHeightKeyguard()
            java.lang.Integer r1 = new java.lang.Integer
            r1.<init>(r5)
            r4.label = r2
            kotlinx.coroutines.flow.FlowCollector r3 = r3.$this_unsafeFlow
            java.lang.Object r3 = r3.emit(r1, r4)
            if (r3 != r0) goto L48
            return r0
        L48:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.ui.SystemBarUtilsState$special$$inlined$map$2$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
