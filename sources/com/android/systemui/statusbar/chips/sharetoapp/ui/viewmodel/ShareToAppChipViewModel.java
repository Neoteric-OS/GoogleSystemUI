package com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel;

import android.content.Context;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor;
import com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import com.android.systemui.statusbar.chips.ui.viewmodel.ChipTransitionHelper;
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
public final class ShareToAppChipViewModel {
    public final ReadonlyStateFlow chip;
    public final ChipTransitionHelper chipTransitionHelper;
    public final Context context;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final EndMediaProjectionDialogHelper endMediaProjectionDialogHelper;
    public final ReadonlyStateFlow internalChip;
    public final LogBuffer logger;
    public final MediaProjectionChipInteractor mediaProjectionChipInteractor;
    public final SystemClock systemClock;

    public ShareToAppChipViewModel(CoroutineScope coroutineScope, Context context, MediaProjectionChipInteractor mediaProjectionChipInteractor, SystemClock systemClock, EndMediaProjectionDialogHelper endMediaProjectionDialogHelper, DialogTransitionAnimator dialogTransitionAnimator, LogBuffer logBuffer) {
        this.context = context;
        this.mediaProjectionChipInteractor = mediaProjectionChipInteractor;
        this.systemClock = systemClock;
        this.endMediaProjectionDialogHelper = endMediaProjectionDialogHelper;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.logger = logBuffer;
        final ReadonlyStateFlow readonlyStateFlow = mediaProjectionChipInteractor.projection;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel.ShareToAppChipViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel.ShareToAppChipViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ShareToAppChipViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel.ShareToAppChipViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ShareToAppChipViewModel shareToAppChipViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = shareToAppChipViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x00d8 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r23, kotlin.coroutines.Continuation r24) {
                    /*
                        Method dump skipped, instructions count: 226
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel.ShareToAppChipViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.Lazily, new OngoingActivityChipModel.Hidden(true));
        ChipTransitionHelper chipTransitionHelper = new ChipTransitionHelper(coroutineScope);
        this.chipTransitionHelper = chipTransitionHelper;
        this.chip = chipTransitionHelper.createChipFlow(stateIn);
    }
}
