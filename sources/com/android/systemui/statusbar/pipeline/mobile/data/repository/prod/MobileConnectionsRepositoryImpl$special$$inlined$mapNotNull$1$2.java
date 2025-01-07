package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ MobileConnectionsRepositoryImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2$1, reason: invalid class name */
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
            return MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2.this.emit(null, this);
        }
    }

    public MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2(FlowCollector flowCollector, MobileConnectionsRepositoryImpl mobileConnectionsRepositoryImpl) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = mobileConnectionsRepositoryImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 1
            if (r2 == 0) goto L31
            if (r2 != r4) goto L29
            kotlin.ResultKt.throwOnFailure(r9)
            goto L80
        L29:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L31:
            kotlin.ResultKt.throwOnFailure(r9)
            com.android.systemui.util.kotlin.WithPrev r8 = (com.android.systemui.util.kotlin.WithPrev) r8
            java.lang.Object r9 = r8.previousValue
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r8 = r8.newValue
            java.lang.Integer r8 = (java.lang.Integer) r8
            r2 = 0
            if (r9 == 0) goto L73
            if (r8 != 0) goto L44
            goto L73
        L44:
            com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionsRepositoryImpl r5 = r7.this$0
            android.telephony.SubscriptionManager r6 = r5.subscriptionManager
            int r9 = r9.intValue()
            android.telephony.SubscriptionInfo r9 = r6.getActiveSubscriptionInfo(r9)
            if (r9 == 0) goto L57
            android.os.ParcelUuid r9 = r9.getGroupUuid()
            goto L58
        L57:
            r9 = r2
        L58:
            android.telephony.SubscriptionManager r5 = r5.subscriptionManager
            int r8 = r8.intValue()
            android.telephony.SubscriptionInfo r8 = r5.getActiveSubscriptionInfo(r8)
            if (r8 == 0) goto L69
            android.os.ParcelUuid r8 = r8.getGroupUuid()
            goto L6a
        L69:
            r8 = r2
        L6a:
            if (r9 == 0) goto L73
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L73
            r2 = r3
        L73:
            if (r2 == 0) goto L80
            r0.label = r4
            kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
            java.lang.Object r7 = r7.emit(r2, r0)
            if (r7 != r1) goto L80
            return r1
        L80:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionsRepositoryImpl$special$$inlined$mapNotNull$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
