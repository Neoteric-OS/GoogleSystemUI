package com.android.systemui.keyguard.ui.viewmodel;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.app.tracing.FlowTracing;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordancesCombinedViewModel {
    public final Flow areQuickAffordancesFullyOpaque;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 endButton;
    public final ChannelLimitedFlowMerge fadeInAlpha;
    public final ChannelLimitedFlowMerge fadeOutAlpha;
    public final KeyguardInteractor keyguardInteractor;
    public final StateFlowImpl previewAffordances;
    public final StateFlowImpl previewMode = StateFlowKt.MutableStateFlow(new PreviewMode(false, false));
    public final KeyguardQuickAffordanceInteractor quickAffordanceInteractor;
    public final StateFlowImpl selectedPreviewSlotId;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 shadeExpansionAlpha;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 startButton;
    public final ReadonlyStateFlow transitionAlpha;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PreviewMode {
        public final boolean isInPreviewMode;
        public final boolean shouldHighlightSelectedAffordance;

        public PreviewMode(boolean z, boolean z2) {
            this.isInPreviewMode = z;
            this.shouldHighlightSelectedAffordance = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PreviewMode)) {
                return false;
            }
            PreviewMode previewMode = (PreviewMode) obj;
            return this.isInPreviewMode == previewMode.isInPreviewMode && this.shouldHighlightSelectedAffordance == previewMode.shouldHighlightSelectedAffordance;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.shouldHighlightSelectedAffordance) + (Boolean.hashCode(this.isInPreviewMode) * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("PreviewMode(isInPreviewMode=");
            sb.append(this.isInPreviewMode);
            sb.append(", shouldHighlightSelectedAffordance=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.shouldHighlightSelectedAffordance, ")");
        }
    }

    public KeyguardQuickAffordancesCombinedViewModel(CoroutineScope coroutineScope, KeyguardQuickAffordanceInteractor keyguardQuickAffordanceInteractor, KeyguardInteractor keyguardInteractor, ShadeInteractor shadeInteractor, AodToLockscreenTransitionViewModel aodToLockscreenTransitionViewModel, DozingToLockscreenTransitionViewModel dozingToLockscreenTransitionViewModel, DreamingHostedToLockscreenTransitionViewModel dreamingHostedToLockscreenTransitionViewModel, DreamingToLockscreenTransitionViewModel dreamingToLockscreenTransitionViewModel, GoneToLockscreenTransitionViewModel goneToLockscreenTransitionViewModel, OccludedToLockscreenTransitionViewModel occludedToLockscreenTransitionViewModel, OffToLockscreenTransitionViewModel offToLockscreenTransitionViewModel, PrimaryBouncerToLockscreenTransitionViewModel primaryBouncerToLockscreenTransitionViewModel, GlanceableHubToLockscreenTransitionViewModel glanceableHubToLockscreenTransitionViewModel, LockscreenToAodTransitionViewModel lockscreenToAodTransitionViewModel, LockscreenToDozingTransitionViewModel lockscreenToDozingTransitionViewModel, LockscreenToDreamingHostedTransitionViewModel lockscreenToDreamingHostedTransitionViewModel, LockscreenToDreamingTransitionViewModel lockscreenToDreamingTransitionViewModel, LockscreenToGoneTransitionViewModel lockscreenToGoneTransitionViewModel, LockscreenToOccludedTransitionViewModel lockscreenToOccludedTransitionViewModel, LockscreenToPrimaryBouncerTransitionViewModel lockscreenToPrimaryBouncerTransitionViewModel, LockscreenToGlanceableHubTransitionViewModel lockscreenToGlanceableHubTransitionViewModel, KeyguardTransitionInteractor keyguardTransitionInteractor) {
        this.quickAffordanceInteractor = keyguardQuickAffordanceInteractor;
        this.keyguardInteractor = keyguardInteractor;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardTransitionInteractor.isFinishedIn$1(KeyguardState.LOCKSCREEN), ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.getAnyExpansion(), new KeyguardQuickAffordancesCombinedViewModel$shadeExpansionAlpha$1(3, null));
        this.selectedPreviewSlotId = StateFlowKt.MutableStateFlow("bottom_start");
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowKt.merge(FlowKt.merge(aodToLockscreenTransitionViewModel.shortcutsAlpha, dozingToLockscreenTransitionViewModel.shortcutsAlpha, dreamingHostedToLockscreenTransitionViewModel.shortcutsAlpha, dreamingToLockscreenTransitionViewModel.shortcutsAlpha, goneToLockscreenTransitionViewModel.shortcutsAlpha, occludedToLockscreenTransitionViewModel.shortcutsAlpha, offToLockscreenTransitionViewModel.shortcutsAlpha, primaryBouncerToLockscreenTransitionViewModel.shortcutsAlpha, glanceableHubToLockscreenTransitionViewModel.shortcutsAlpha), FlowKt.merge(lockscreenToAodTransitionViewModel.shortcutsAlpha, lockscreenToDozingTransitionViewModel.shortcutsAlpha, lockscreenToDreamingHostedTransitionViewModel.shortcutsAlpha, lockscreenToDreamingTransitionViewModel.shortcutsAlpha, lockscreenToGoneTransitionViewModel.shortcutsAlpha, lockscreenToOccludedTransitionViewModel.shortcutsAlpha, lockscreenToPrimaryBouncerTransitionViewModel.shortcutsAlpha, lockscreenToGlanceableHubTransitionViewModel.shortcutsAlpha, flowKt__ZipKt$combine$$inlined$unsafeFlow$1)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Float.valueOf(0.0f));
        this.transitionAlpha = stateIn;
        this.areQuickAffordancesFullyOpaque = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L51
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        float r5 = r5.floatValue()
                        r6 = 1064514355(0x3f733333, float:0.95)
                        int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                        if (r5 < 0) goto L41
                        r5 = r3
                        goto L42
                    L41:
                        r5 = 0
                    L42:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L51
                        return r1
                    L51:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        });
        this.previewAffordances = StateFlowKt.MutableStateFlow(MapsKt.emptyMap());
        this.startButton = button(KeyguardQuickAffordancePosition.BOTTOM_START, null);
        this.endButton = button(KeyguardQuickAffordancePosition.BOTTOM_END, null);
    }

    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 button(final KeyguardQuickAffordancePosition keyguardQuickAffordancePosition, String str) {
        FlowTracing flowTracing = FlowTracing.INSTANCE;
        return FlowTracing.traceEmissionCount$default(FlowKt.transformLatest(this.previewMode, new KeyguardQuickAffordancesCombinedViewModel$button$$inlined$flatMapLatest$1(null, this, keyguardQuickAffordancePosition, str)), new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel$button$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "QuickAfforcances#button".concat(KeyguardQuickAffordancePosition.this.toSlotId());
            }
        });
    }

    public final void enablePreviewMode(String str, boolean z) {
        PreviewMode previewMode = new PreviewMode(true, z);
        if (str == null) {
            str = "bottom_start";
        }
        StateFlowImpl stateFlowImpl = this.selectedPreviewSlotId;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, str);
        StateFlowImpl stateFlowImpl2 = this.previewMode;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, previewMode);
    }
}
