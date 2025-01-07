package com.android.systemui.statusbar.chips.ui.viewmodel;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OngoingActivityChipsViewModel$special$$inlined$map$2$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ OngoingActivityChipsViewModel this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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
            return OngoingActivityChipsViewModel$special$$inlined$map$2$2.this.emit(null, this);
        }
    }

    public OngoingActivityChipsViewModel$special$$inlined$map$2$2(FlowCollector flowCollector, OngoingActivityChipsViewModel ongoingActivityChipsViewModel) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = ongoingActivityChipsViewModel;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$2$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$2$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$2$2$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L30
            if (r2 != r3) goto L28
            kotlin.ResultKt.throwOnFailure(r6)
            goto L99
        L28:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L30:
            kotlin.ResultKt.throwOnFailure(r6)
            com.android.systemui.util.kotlin.WithPrev r5 = (com.android.systemui.util.kotlin.WithPrev) r5
            java.lang.Object r6 = r5.previousValue
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel r6 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel) r6
            java.lang.Object r5 = r5.newValue
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel) r5
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel$Hidden r2 = com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.DEFAULT_INTERNAL_HIDDEN_MODEL
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel r2 = r4.this$0
            r2.getClass()
            boolean r2 = r6 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Shown
            if (r2 == 0) goto L80
            boolean r2 = r5 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Hidden
            if (r2 == 0) goto L80
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel$Shown r6 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Shown) r6
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$ChipType r6 = r6.type
            int r6 = r6.ordinal()
            if (r6 == 0) goto L7b
            if (r6 == r3) goto L76
            r2 = 2
            if (r6 == r2) goto L71
            r2 = 3
            if (r6 == r2) goto L6c
            r2 = 4
            if (r6 != r2) goto L66
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel$Hidden r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Hidden) r5
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden r5 = r5.demoRon
            goto L8e
        L66:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        L6c:
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel$Hidden r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Hidden) r5
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden r5 = r5.call
            goto L8e
        L71:
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel$Hidden r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Hidden) r5
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden r5 = r5.castToOtherDevice
            goto L8e
        L76:
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel$Hidden r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Hidden) r5
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden r5 = r5.shareToApp
            goto L8e
        L7b:
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel$Hidden r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Hidden) r5
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden r5 = r5.screenRecord
            goto L8e
        L80:
            boolean r6 = r5 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Shown
            if (r6 == 0) goto L89
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel$Shown r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.InternalChipModel.Shown) r5
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown r5 = r5.model
            goto L8e
        L89:
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden r5 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden
            r5.<init>(r3)
        L8e:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
            java.lang.Object r4 = r4.emit(r5, r0)
            if (r4 != r1) goto L99
            return r1
        L99:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$2$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
