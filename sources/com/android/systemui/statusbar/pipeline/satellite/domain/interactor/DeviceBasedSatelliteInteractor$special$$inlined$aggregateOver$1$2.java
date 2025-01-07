package com.android.systemui.statusbar.pipeline.satellite.domain.interactor;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2$1, reason: invalid class name */
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
            return DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2.this.emit(null, this);
        }
    }

    public DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2(FlowCollector flowCollector) {
        this.$this_unsafeFlow = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r10)
            goto L71
        L27:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2f:
            kotlin.ResultKt.throwOnFailure(r10)
            java.util.List r9 = (java.util.List) r9
            java.util.ArrayList r10 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r9, r2)
            r10.<init>(r2)
            java.util.Iterator r9 = r9.iterator()
        L43:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L66
            java.lang.Object r2 = r9.next()
            com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor r2 = (com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor) r2
            com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl r2 = (com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl) r2
            kotlinx.coroutines.flow.StateFlow r4 = r2.isInService
            com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor$allConnectionsOos$1$1 r5 = new com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor$allConnectionsOos$1$1
            r6 = 4
            r7 = 0
            r5.<init>(r6, r7)
            kotlinx.coroutines.flow.StateFlow r6 = r2.isEmergencyOnly
            kotlinx.coroutines.flow.StateFlow r2 = r2.isNonTerrestrial
            kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 r2 = kotlinx.coroutines.flow.FlowKt.combine(r4, r6, r2, r5)
            r10.add(r2)
            goto L43
        L66:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
            java.lang.Object r8 = r8.emit(r10, r0)
            if (r8 != r1) goto L71
            return r1
        L71:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
