package com.android.systemui.statusbar.chips.call.ui.viewmodel;

import android.R;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.chips.call.domain.interactor.CallChipInteractor;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import com.android.systemui.util.time.SystemClock;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CallChipViewModel {
    public static final Icon.Resource phoneIcon = new Icon.Resource(R.drawable.ic_perm_group_status_bar, new ContentDescription.Resource(com.android.wm.shell.R.string.ongoing_phone_call_content_description));
    public final ActivityStarter activityStarter;
    public final ReadonlyStateFlow chip;
    public final LogBuffer logger;

    public CallChipViewModel(CoroutineScope coroutineScope, CallChipInteractor callChipInteractor, final SystemClock systemClock, ActivityStarter activityStarter, LogBuffer logBuffer) {
        this.activityStarter = activityStarter;
        this.logger = logBuffer;
        final ReadonlyStateFlow readonlyStateFlow = callChipInteractor.ongoingCallState;
        this.chip = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ SystemClock $systemClock$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ CallChipViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, CallChipViewModel callChipViewModel, SystemClock systemClock) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = callChipViewModel;
                    this.$systemClock$inlined = systemClock;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r12, kotlin.coroutines.Continuation r13) {
                    /*
                        r11 = this;
                        boolean r0 = r13 instanceof com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r13
                        com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r13)
                    L18:
                        java.lang.Object r13 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r13)
                        goto Lb2
                    L28:
                        java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                        java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                        r11.<init>(r12)
                        throw r11
                    L30:
                        kotlin.ResultKt.throwOnFailure(r13)
                        com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel r12 = (com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel) r12
                        boolean r13 = r12 instanceof com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel.NoCall
                        if (r13 == 0) goto L40
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden r12 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden
                        r12.<init>(r3)
                        goto La7
                    L40:
                        boolean r13 = r12 instanceof com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel.InCall
                        if (r13 == 0) goto Lb5
                        com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel$InCall r12 = (com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel.InCall) r12
                        com.android.systemui.statusbar.StatusBarIconView r13 = r12.notificationIconView
                        if (r13 == 0) goto L51
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$ChipIcon$StatusBarView r2 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$ChipIcon$StatusBarView
                        r2.<init>(r13)
                    L4f:
                        r5 = r2
                        goto L59
                    L51:
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$ChipIcon$SingleColorIcon r2 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$ChipIcon$SingleColorIcon
                        com.android.systemui.common.shared.model.Icon$Resource r13 = com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel.phoneIcon
                        r2.<init>(r13)
                        goto L4f
                    L59:
                        long r6 = r12.startTimeMs
                        r8 = 0
                        int r13 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                        com.android.systemui.statusbar.chips.ui.model.ColorsModel$Themed r2 = com.android.systemui.statusbar.chips.ui.model.ColorsModel.Themed.INSTANCE
                        r4 = 0
                        com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel r8 = r11.this$0
                        if (r13 > 0) goto L7a
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$IconOnly r13 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$IconOnly
                        r8.getClass()
                        android.app.PendingIntent r6 = r12.intent
                        if (r6 != 0) goto L70
                        goto L75
                    L70:
                        com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$getOnClickListener$1 r4 = new com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$getOnClickListener$1
                        r4.<init>(r8, r12)
                    L75:
                        r13.<init>(r5, r2, r4)
                    L78:
                        r12 = r13
                        goto La7
                    L7a:
                        com.android.systemui.util.time.SystemClock r13 = r11.$systemClock$inlined
                        com.android.systemui.util.time.SystemClockImpl r13 = (com.android.systemui.util.time.SystemClockImpl) r13
                        r13.getClass()
                        long r9 = java.lang.System.currentTimeMillis()
                        long r6 = r6 - r9
                        r13.getClass()
                        long r9 = android.os.SystemClock.elapsedRealtime()
                        long r9 = r9 + r6
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$Timer r13 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$Timer
                        r8.getClass()
                        android.app.PendingIntent r6 = r12.intent
                        if (r6 != 0) goto L99
                    L97:
                        r12 = r4
                        goto L9f
                    L99:
                        com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$getOnClickListener$1 r4 = new com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$getOnClickListener$1
                        r4.<init>(r8, r12)
                        goto L97
                    L9f:
                        r4 = r13
                        r6 = r2
                        r7 = r9
                        r9 = r12
                        r4.<init>(r5, r6, r7, r9)
                        goto L78
                    La7:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r11 = r11.$this_unsafeFlow
                        java.lang.Object r11 = r11.emit(r12, r0)
                        if (r11 != r1) goto Lb2
                        return r1
                    Lb2:
                        kotlin.Unit r11 = kotlin.Unit.INSTANCE
                        return r11
                    Lb5:
                        kotlin.NoWhenBranchMatchedException r11 = new kotlin.NoWhenBranchMatchedException
                        r11.<init>()
                        throw r11
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this, systemClock), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new OngoingActivityChipModel.Hidden(true));
    }
}
