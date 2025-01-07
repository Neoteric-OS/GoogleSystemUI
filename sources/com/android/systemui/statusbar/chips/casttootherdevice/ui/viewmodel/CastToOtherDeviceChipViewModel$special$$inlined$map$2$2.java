package com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CastToOtherDeviceChipViewModel$special$$inlined$map$2$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ CastToOtherDeviceChipViewModel this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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
            return CastToOtherDeviceChipViewModel$special$$inlined$map$2$2.this.emit(null, this);
        }
    }

    public CastToOtherDeviceChipViewModel$special$$inlined$map$2$2(FlowCollector flowCollector, CastToOtherDeviceChipViewModel castToOtherDeviceChipViewModel) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = castToOtherDeviceChipViewModel;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r21, kotlin.coroutines.Continuation r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            boolean r2 = r1 instanceof com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$special$$inlined$map$2$2.AnonymousClass1
            if (r2 == 0) goto L17
            r2 = r1
            com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$special$$inlined$map$2$2$1 r2 = (com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$special$$inlined$map$2$2.AnonymousClass1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$special$$inlined$map$2$2$1 r2 = new com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$special$$inlined$map$2$2$1
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L34
            if (r4 != r5) goto L2c
            kotlin.ResultKt.throwOnFailure(r1)
            goto Lbe
        L2c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L34:
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r21
            com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel r1 = (com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel) r1
            boolean r4 = r1 instanceof com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel.DoingNothing
            if (r4 == 0) goto L49
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden r1 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden
            r1.<init>(r5)
            r16 = r3
            r3 = r5
            goto Lb1
        L49:
            boolean r4 = r1 instanceof com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel.Casting
            if (r4 == 0) goto Lc1
            com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel$Casting r1 = (com.android.systemui.statusbar.chips.casttootherdevice.domain.model.MediaRouterCastModel.Casting) r1
            java.lang.String r1 = r1.deviceName
            com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel r4 = r0.this$0
            r4.getClass()
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$IconOnly r13 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$IconOnly
            com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$ChipIcon$SingleColorIcon r14 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$ChipIcon$SingleColorIcon
            com.android.systemui.common.shared.model.Icon$Resource r6 = new com.android.systemui.common.shared.model.Icon$Resource
            com.android.systemui.common.shared.model.ContentDescription$Resource r7 = new com.android.systemui.common.shared.model.ContentDescription$Resource
            r8 = 2131951710(0x7f13005e, float:1.9539842E38)
            r7.<init>(r8)
            r8 = 2131232336(0x7f080650, float:1.8080778E38)
            r6.<init>(r8, r7)
            r14.<init>(r6)
            com.android.systemui.statusbar.chips.ui.model.ColorsModel$Red r15 = com.android.systemui.statusbar.chips.ui.model.ColorsModel.Red.INSTANCE
            com.android.systemui.statusbar.chips.casttootherdevice.ui.view.EndGenericCastToOtherDeviceDialogDelegate r12 = new com.android.systemui.statusbar.chips.casttootherdevice.ui.view.EndGenericCastToOtherDeviceDialogDelegate
            android.content.Context r11 = r4.context
            com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$createGenericCastToOtherDeviceDialogDelegate$1 r10 = new com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$createGenericCastToOtherDeviceDialogDelegate$1
            java.lang.String r16 = "stopMediaRouterCastingFromDialog()V"
            r17 = 0
            r7 = 0
            java.lang.Class<com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel> r9 = com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel.class
            java.lang.String r18 = "stopMediaRouterCastingFromDialog"
            r6 = r10
            r8 = r4
            r5 = r10
            r10 = r18
            r19 = r11
            r11 = r16
            r16 = r3
            r3 = r12
            r12 = r17
            r6.<init>(r7, r8, r9, r10, r11, r12)
            com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper r6 = r4.endMediaProjectionDialogHelper
            r7 = r19
            r3.<init>(r6, r7, r1, r5)
            com.android.systemui.animation.DialogCuj r11 = new com.android.systemui.animation.DialogCuj
            r1 = 111(0x6f, float:1.56E-43)
            java.lang.String r5 = "Cast to other device audio only"
            r11.<init>(r1, r5)
            com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipViewModel$Companion$createDialogLaunchOnClickListener$1 r1 = new com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipViewModel$Companion$createDialogLaunchOnClickListener$1
            com.android.systemui.log.LogBuffer r7 = r4.logger
            java.lang.String r8 = "CastToOtherVM"
            com.android.systemui.animation.DialogTransitionAnimator r10 = r4.dialogTransitionAnimator
            r6 = r1
            r9 = r3
            r6.<init>(r7, r8, r9, r10, r11)
            r13.<init>(r14, r15, r1)
            r1 = r13
            r3 = 1
        Lb1:
            r2.label = r3
            kotlinx.coroutines.flow.FlowCollector r0 = r0.$this_unsafeFlow
            java.lang.Object r0 = r0.emit(r1, r2)
            r1 = r16
            if (r0 != r1) goto Lbe
            return r1
        Lbe:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lc1:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$special$$inlined$map$2$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
