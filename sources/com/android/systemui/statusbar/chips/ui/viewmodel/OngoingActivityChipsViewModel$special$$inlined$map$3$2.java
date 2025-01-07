package com.android.systemui.statusbar.chips.ui.viewmodel;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OngoingActivityChipsViewModel$special$$inlined$map$3$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ OngoingActivityChipsViewModel this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$3$2$1, reason: invalid class name */
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
            return OngoingActivityChipsViewModel$special$$inlined$map$3$2.this.emit(null, this);
        }
    }

    public OngoingActivityChipsViewModel$special$$inlined$map$3$2(FlowCollector flowCollector, OngoingActivityChipsViewModel ongoingActivityChipsViewModel) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = ongoingActivityChipsViewModel;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$3$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$3$2$1 r0 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$3$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$3$2$1 r0 = new com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$3$2$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5f
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$ChipBundle r6 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.ChipBundle) r6
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel r7 = r5.this$0
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$MostImportantChipResult r6 = com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.access$pickMostImportantChip(r7, r6)
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel r2 = r6.mostImportantChip
            boolean r4 = r2 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Hidden
            if (r4 == 0) goto L46
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalMultipleOngoingActivityChipsModel r6 = new com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalMultipleOngoingActivityChipsModel
            r6.<init>(r2, r2)
            goto L54
        L46:
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$ChipBundle r6 = r6.remainingChips
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$MostImportantChipResult r6 = com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.access$pickMostImportantChip(r7, r6)
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalMultipleOngoingActivityChipsModel r7 = new com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalMultipleOngoingActivityChipsModel
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel r6 = r6.mostImportantChip
            r7.<init>(r2, r6)
            r6 = r7
        L54:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
            java.lang.Object r5 = r5.emit(r6, r0)
            if (r5 != r1) goto L5f
            return r1
        L5f:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$3$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
