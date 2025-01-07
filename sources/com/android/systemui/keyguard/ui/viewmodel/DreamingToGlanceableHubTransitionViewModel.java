package com.android.systemui.keyguard.ui.viewmodel;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow.FlowBuilder;
import com.android.systemui.keyguard.ui.transitions.DeviceEntryIconTransition;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamingToGlanceableHubTransitionViewModel implements DeviceEntryIconTransition {
    public static final long TO_GLANCEABLE_HUB_DURATION;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 deviceEntryParentViewAlpha;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 dreamAlpha;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 dreamOverlayAlpha;
    public final ChannelFlowTransformLatest dreamOverlayTranslationX;
    public final DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1 showUmo;
    public final KeyguardTransitionAnimationFlow.FlowBuilder transitionAnimation;

    static {
        int i = Duration.$r8$clinit;
        TO_GLANCEABLE_HUB_DURATION = DurationKt.toDuration(1, DurationUnit.SECONDS);
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1] */
    public DreamingToGlanceableHubTransitionViewModel(ConfigurationInteractor configurationInteractor, KeyguardTransitionAnimationFlow keyguardTransitionAnimationFlow) {
        Edge.Companion companion = Edge.Companion;
        KeyguardState keyguardState = KeyguardState.DREAMING;
        SceneKey sceneKey = Scenes.Bouncer;
        keyguardTransitionAnimationFlow.getClass();
        Edge.StateToState stateToState = new Edge.StateToState(keyguardState, KeyguardState.GLANCEABLE_HUB);
        long j = TO_GLANCEABLE_HUB_DURATION;
        KeyguardTransitionAnimationFlow.FlowBuilder flowBuilder = keyguardTransitionAnimationFlow.new FlowBuilder(j, stateToState);
        this.transitionAnimation = flowBuilder;
        this.dreamOverlayTranslationX = FlowKt.transformLatest(configurationInteractor.directionalDimensionPixelSize(R.dimen.dreaming_to_hub_transition_dream_overlay_translation_x), new DreamingToGlanceableHubTransitionViewModel$special$$inlined$flatMapLatest$1(null, this));
        this.dreamAlpha = flowBuilder.immediatelyTransitionTo(1.0f);
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        this.dreamOverlayAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(167, durationUnit), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$dreamOverlayAlpha$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(1.0f - ((Number) obj).floatValue());
            }
        }, 0L, null, null, null, null, "DREAMING->GLANCEABLE_HUB: dreamOverlayAlpha", 124);
        final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 m825sharedFlow74qcysc$default = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, j, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$showUmo$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(((Number) obj).floatValue());
            }
        }, 0L, null, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$showUmo$2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(0.0f);
            }
        }, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$showUmo$3
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(1.0f);
            }
        }, null, null, 204);
        this.showUmo = new Flow() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L50
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        float r5 = r5.floatValue()
                        r6 = 0
                        int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                        if (r5 != 0) goto L3f
                        r5 = r3
                        goto L40
                    L3f:
                        r5 = 0
                    L40:
                        r5 = r5 ^ r3
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L50
                        return r1
                    L50:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        this.deviceEntryParentViewAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(167, durationUnit), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$deviceEntryParentViewAlpha$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(((Number) obj).floatValue());
            }
        }, DurationKt.toDuration(167, durationUnit), null, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$deviceEntryParentViewAlpha$2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(1.0f);
            }
        }, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToGlanceableHubTransitionViewModel$deviceEntryParentViewAlpha$3
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(1.0f);
            }
        }, null, null, 200);
    }

    @Override // com.android.systemui.keyguard.ui.transitions.DeviceEntryIconTransition
    public final Flow getDeviceEntryParentViewAlpha() {
        return this.deviceEntryParentViewAlpha;
    }
}
