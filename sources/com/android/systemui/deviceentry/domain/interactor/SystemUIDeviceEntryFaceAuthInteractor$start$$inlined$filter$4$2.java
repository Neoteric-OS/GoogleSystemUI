package com.android.systemui.deviceentry.domain.interactor;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2$1, reason: invalid class name */
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
            return SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2.this.emit(null, this);
        }
    }

    public SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2(FlowCollector flowCollector) {
        this.$this_unsafeFlow = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2$1 r0 = (com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2$1 r0 = new com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5b
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2f:
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r7
            com.android.systemui.util.kotlin.WithPrev r8 = (com.android.systemui.util.kotlin.WithPrev) r8
            java.lang.Object r2 = r8.previousValue
            com.android.systemui.user.data.model.SelectedUserModel r2 = (com.android.systemui.user.data.model.SelectedUserModel) r2
            java.lang.Object r8 = r8.newValue
            com.android.systemui.user.data.model.SelectedUserModel r8 = (com.android.systemui.user.data.model.SelectedUserModel) r8
            com.android.systemui.user.data.model.SelectionStatus r2 = r2.selectionStatus
            com.android.systemui.user.data.model.SelectionStatus r4 = com.android.systemui.user.data.model.SelectionStatus.SELECTION_IN_PROGRESS
            r5 = 0
            if (r2 != r4) goto L46
            r2 = r3
            goto L47
        L46:
            r2 = r5
        L47:
            com.android.systemui.user.data.model.SelectionStatus r8 = r8.selectionStatus
            if (r8 != r4) goto L4c
            r5 = r3
        L4c:
            if (r2 == 0) goto L5b
            if (r5 != 0) goto L5b
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
            java.lang.Object r6 = r6.emit(r7, r0)
            if (r6 != r1) goto L5b
            return r1
        L5b:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor$start$$inlined$filter$4$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
