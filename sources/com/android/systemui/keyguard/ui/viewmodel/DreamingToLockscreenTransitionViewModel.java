package com.android.systemui.keyguard.ui.viewmodel;

import android.view.animation.Interpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.keyguard.domain.interactor.FromDreamingTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow.FlowBuilder;
import com.android.systemui.keyguard.ui.transitions.DeviceEntryIconTransition;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamingToLockscreenTransitionViewModel implements DeviceEntryIconTransition {
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 deviceEntryBackgroundViewAlpha;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 deviceEntryParentViewAlpha;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 dreamOverlayAlpha;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 lockscreenAlpha;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 shortcutsAlpha;
    public final KeyguardTransitionAnimationFlow.FlowBuilder transitionAnimation;

    public DreamingToLockscreenTransitionViewModel(KeyguardTransitionAnimationFlow keyguardTransitionAnimationFlow) {
        long j = FromDreamingTransitionInteractor.TO_LOCKSCREEN_DURATION;
        Edge.Companion companion = Edge.Companion;
        KeyguardTransitionAnimationFlow.FlowBuilder flowBuilder = keyguardTransitionAnimationFlow.new FlowBuilder(j, AlternateBouncerToAodTransitionViewModel$$ExternalSyntheticOutline0.m(KeyguardState.DREAMING, KeyguardState.LOCKSCREEN, keyguardTransitionAnimationFlow));
        this.transitionAnimation = flowBuilder;
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        this.dreamOverlayAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(250, durationUnit), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$dreamOverlayAlpha$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(1.0f - ((Number) obj).floatValue());
            }
        }, 0L, null, null, null, null, null, 252);
        this.lockscreenAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(250, durationUnit), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$lockscreenAlpha$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(((Number) obj).floatValue());
            }
        }, DurationKt.toDuration(233, durationUnit), null, null, null, null, null, 248);
        this.shortcutsAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(250, durationUnit), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$shortcutsAlpha$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(((Number) obj).floatValue());
            }
        }, DurationKt.toDuration(233, durationUnit), null, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$shortcutsAlpha$2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(0.0f);
            }
        }, null, null, null, 232);
        this.deviceEntryBackgroundViewAlpha = flowBuilder.immediatelyTransitionTo(1.0f);
        this.deviceEntryParentViewAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(250, durationUnit), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$deviceEntryParentViewAlpha$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(((Number) obj).floatValue());
            }
        }, DurationKt.toDuration(233, durationUnit), null, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$deviceEntryParentViewAlpha$2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(1.0f);
            }
        }, null, null, null, 232);
    }

    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 dreamOverlayTranslationY(final int i) {
        return KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(this.transitionAnimation, FromDreamingTransitionInteractor.TO_LOCKSCREEN_DURATION, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$dreamOverlayTranslationY$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(((Number) obj).floatValue() * i);
            }
        }, 0L, null, null, null, Interpolators.EMPHASIZED, null, 188);
    }

    @Override // com.android.systemui.keyguard.ui.transitions.DeviceEntryIconTransition
    public final Flow getDeviceEntryParentViewAlpha() {
        return this.deviceEntryParentViewAlpha;
    }

    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 lockscreenTranslationY(final int i) {
        long j = FromDreamingTransitionInteractor.TO_LOCKSCREEN_DURATION;
        Interpolator interpolator = Interpolators.EMPHASIZED;
        Function1 function1 = new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$lockscreenTranslationY$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf((((Number) obj).floatValue() * i) + (-r1));
            }
        };
        DreamingToLockscreenTransitionViewModel$lockscreenTranslationY$2 dreamingToLockscreenTransitionViewModel$lockscreenTranslationY$2 = new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$lockscreenTranslationY$2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(0.0f);
            }
        };
        DreamingToLockscreenTransitionViewModel$lockscreenTranslationY$3 dreamingToLockscreenTransitionViewModel$lockscreenTranslationY$3 = new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel$lockscreenTranslationY$3
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(0.0f);
            }
        };
        Intrinsics.checkNotNull(interpolator);
        return KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(this.transitionAnimation, j, function1, 0L, null, dreamingToLockscreenTransitionViewModel$lockscreenTranslationY$2, dreamingToLockscreenTransitionViewModel$lockscreenTranslationY$3, interpolator, null, 140);
    }
}
