package com.android.systemui.statusbar.notification.stack.ui.viewmodel;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SharedNotificationContainerViewModel$special$$inlined$map$5$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$5$2$1, reason: invalid class name */
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
            return SharedNotificationContainerViewModel$special$$inlined$map$5$2.this.emit(null, this);
        }
    }

    public SharedNotificationContainerViewModel$special$$inlined$map$5$2(FlowCollector flowCollector) {
        this.$this_unsafeFlow = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r11, kotlin.coroutines.Continuation r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$5$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r12
            com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$5$2$1 r0 = (com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$5$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$5$2$1 r0 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$5$2$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r12)
            goto L5e
        L27:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2f:
            kotlin.ResultKt.throwOnFailure(r12)
            com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$ConfigurationBasedDimensions r11 = (com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor.ConfigurationBasedDimensions) r11
            boolean r12 = r11.useLargeScreenHeader
            if (r12 == 0) goto L3c
            int r12 = r11.marginTopLargeScreen
        L3a:
            r7 = r12
            goto L3f
        L3c:
            int r12 = r11.marginTop
            goto L3a
        L3f:
            boolean r5 = r11.useSplitShade
            if (r5 == 0) goto L46
            r12 = 0
        L44:
            r6 = r12
            goto L49
        L46:
            int r12 = r11.marginHorizontal
            goto L44
        L49:
            com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$ConfigurationBasedDimensions r12 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$ConfigurationBasedDimensions
            int r8 = r11.marginHorizontal
            int r9 = r11.marginBottom
            r4 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r10 = r10.$this_unsafeFlow
            java.lang.Object r10 = r10.emit(r12, r0)
            if (r10 != r1) goto L5e
            return r1
        L5e:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$5$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
