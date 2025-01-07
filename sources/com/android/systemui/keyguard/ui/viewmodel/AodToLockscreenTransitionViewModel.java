package com.android.systemui.keyguard.ui.viewmodel;

import android.util.MathUtils;
import com.android.systemui.keyguard.domain.interactor.FromAodTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow.FlowBuilder;
import com.android.systemui.keyguard.ui.transitions.DeviceEntryIconTransition;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$FloatRef;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AodToLockscreenTransitionViewModel implements DeviceEntryIconTransition {
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 deviceEntryBackgroundViewAlpha;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 deviceEntryParentViewAlpha;
    public boolean isShadeExpanded;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 notificationAlpha;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 shortcutsAlpha;
    public final KeyguardTransitionAnimationFlow.FlowBuilder transitionAnimation;

    public AodToLockscreenTransitionViewModel(final ShadeInteractor shadeInteractor, KeyguardTransitionAnimationFlow keyguardTransitionAnimationFlow) {
        long j = FromAodTransitionInteractor.TO_LOCKSCREEN_DURATION;
        Edge.Companion companion = Edge.Companion;
        KeyguardTransitionAnimationFlow.FlowBuilder flowBuilder = keyguardTransitionAnimationFlow.new FlowBuilder(j, AlternateBouncerToAodTransitionViewModel$$ExternalSyntheticOutline0.m(KeyguardState.AOD, KeyguardState.LOCKSCREEN, keyguardTransitionAnimationFlow));
        this.transitionAnimation = flowBuilder;
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        this.notificationAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(500, durationUnit), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel$notificationAlpha$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                float floatValue = ((Number) obj).floatValue();
                if (AodToLockscreenTransitionViewModel.this.isShadeExpanded) {
                    floatValue = 1.0f;
                }
                return Float.valueOf(floatValue);
            }
        }, 0L, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel$notificationAlpha$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AodToLockscreenTransitionViewModel.this.isShadeExpanded = ((Number) ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.getShadeExpansion().getValue()).floatValue() > 0.0f || ((Number) ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.getQsExpansion().getValue()).floatValue() > 0.0f;
                return Unit.INSTANCE;
            }
        }, null, null, null, null, 244);
        this.shortcutsAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(167, durationUnit), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel$shortcutsAlpha$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(((Number) obj).floatValue());
            }
        }, DurationKt.toDuration(67, durationUnit), null, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel$shortcutsAlpha$2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(0.0f);
            }
        }, null, null, null, 232);
        this.deviceEntryBackgroundViewAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(250, durationUnit), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel$deviceEntryBackgroundViewAlpha$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(((Number) obj).floatValue());
            }
        }, 0L, null, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel$deviceEntryBackgroundViewAlpha$2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(1.0f);
            }
        }, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel$deviceEntryBackgroundViewAlpha$3
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(1.0f);
            }
        }, null, null, 204);
        this.deviceEntryParentViewAlpha = flowBuilder.immediatelyTransitionTo(1.0f);
    }

    @Override // com.android.systemui.keyguard.ui.transitions.DeviceEntryIconTransition
    public final Flow getDeviceEntryParentViewAlpha() {
        return this.deviceEntryParentViewAlpha;
    }

    public final Flow lockscreenAlpha(final ViewStateAccessor viewStateAccessor) {
        final Ref$FloatRef ref$FloatRef = new Ref$FloatRef();
        ref$FloatRef.element = 1.0f;
        int i = Duration.$r8$clinit;
        return KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(this.transitionAnimation, DurationKt.toDuration(500, DurationUnit.MILLISECONDS), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel$lockscreenAlpha$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(MathUtils.lerp(Ref$FloatRef.this.element, 1.0f, ((Number) obj).floatValue()));
            }
        }, 0L, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel$lockscreenAlpha$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Ref$FloatRef.this.element = ((Number) viewStateAccessor.alpha.invoke()).floatValue();
                return Unit.INSTANCE;
            }
        }, null, null, null, null, 244);
    }
}
