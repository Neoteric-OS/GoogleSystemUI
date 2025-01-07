package com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel;

import android.content.Context;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper;
import com.android.systemui.statusbar.chips.screenrecord.domain.interactor.ScreenRecordChipInteractor;
import com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel.ShareToAppChipViewModel;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import com.android.systemui.statusbar.chips.ui.viewmodel.ChipTransitionHelper;
import com.android.systemui.util.kotlin.FlowKt;
import com.android.systemui.util.time.SystemClock;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedLazily;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenRecordChipViewModel {
    public final ReadonlyStateFlow chip;
    public final ChipTransitionHelper chipTransitionHelper;
    public final ReadonlyStateFlow chipWithConsistentTimer;
    public final Context context;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final EndMediaProjectionDialogHelper endMediaProjectionDialogHelper;
    public final ScreenRecordChipInteractor interactor;
    public final LogBuffer logger;
    public final ShareToAppChipViewModel shareToAppChipViewModel;
    public final ReadonlyStateFlow simpleChip;
    public final SystemClock systemClock;

    public ScreenRecordChipViewModel(CoroutineScope coroutineScope, Context context, ScreenRecordChipInteractor screenRecordChipInteractor, ShareToAppChipViewModel shareToAppChipViewModel, SystemClock systemClock, EndMediaProjectionDialogHelper endMediaProjectionDialogHelper, DialogTransitionAnimator dialogTransitionAnimator, LogBuffer logBuffer) {
        this.context = context;
        this.interactor = screenRecordChipInteractor;
        this.shareToAppChipViewModel = shareToAppChipViewModel;
        this.systemClock = systemClock;
        this.endMediaProjectionDialogHelper = endMediaProjectionDialogHelper;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.logger = logBuffer;
        final ReadonlyStateFlow readonlyStateFlow = screenRecordChipInteractor.screenRecordState;
        Flow flow = new Flow() { // from class: com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ScreenRecordChipViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ScreenRecordChipViewModel screenRecordChipViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = screenRecordChipViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x00e0 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r23, kotlin.coroutines.Continuation r24) {
                    /*
                        Method dump skipped, instructions count: 234
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        StartedLazily startedLazily = SharingStarted.Companion.Lazily;
        final SafeFlow pairwise = FlowKt.pairwise(kotlinx.coroutines.flow.FlowKt.stateIn(flow, coroutineScope, startedLazily, new OngoingActivityChipModel.Hidden(true)), new OngoingActivityChipModel.Hidden(true));
        ReadonlyStateFlow stateIn = kotlinx.coroutines.flow.FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
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
                        boolean r0 = r12 instanceof com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r12
                        com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r12)
                    L18:
                        java.lang.Object r12 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r12)
                        goto L62
                    L27:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r12)
                        com.android.systemui.util.kotlin.WithPrev r11 = (com.android.systemui.util.kotlin.WithPrev) r11
                        java.lang.Object r12 = r11.previousValue
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel r12 = (com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel) r12
                        java.lang.Object r11 = r11.newValue
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel r11 = (com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel) r11
                        boolean r2 = r12 instanceof com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown.Timer
                        if (r2 == 0) goto L57
                        boolean r2 = r11 instanceof com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown.Timer
                        if (r2 == 0) goto L57
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$Timer r11 = (com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown.Timer) r11
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$Timer r12 = (com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel.Shown.Timer) r12
                        long r7 = r12.startTimeMs
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$ChipIcon r5 = r11.icon
                        android.view.View$OnClickListener r9 = r11.onClickListener
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$Timer r12 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Shown$Timer
                        com.android.systemui.statusbar.chips.ui.model.ColorsModel r6 = r11.colors
                        r4 = r12
                        r4.<init>(r5, r6, r7, r9)
                        r11 = r12
                    L57:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r10 = r10.$this_unsafeFlow
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto L62
                        return r1
                    L62:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = SafeFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineScope, startedLazily, new OngoingActivityChipModel.Hidden(true));
        ChipTransitionHelper chipTransitionHelper = new ChipTransitionHelper(coroutineScope);
        this.chipTransitionHelper = chipTransitionHelper;
        this.chip = chipTransitionHelper.createChipFlow(stateIn);
    }
}
