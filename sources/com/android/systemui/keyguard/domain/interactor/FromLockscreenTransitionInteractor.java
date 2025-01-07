package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import com.android.app.animation.Interpolators;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.power.shared.model.WakeSleepReason;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.shade.data.repository.ShadeRepository;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FromLockscreenTransitionInteractor extends TransitionInteractor {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long DEFAULT_DURATION;
    public static final long TO_AOD_DURATION;
    public static final long TO_AOD_FOLD_DURATION;
    public static final long TO_DOZING_DURATION;
    public static final long TO_DREAMING_DURATION;
    public static final long TO_DREAMING_HOSTED_DURATION;
    public static final long TO_GLANCEABLE_HUB_DURATION;
    public static final long TO_GONE_DURATION;
    public static final long TO_OCCLUDED_DURATION;
    public static final long TO_PRIMARY_BOUNCER_DURATION;
    public final GlanceableHubTransitions glanceableHubTransitions;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final CoroutineScope scope;
    public final ShadeRepository shadeRepository;
    public final Flow surfaceBehindVisibility;
    public final SwipeToDismissInteractor swipeToDismissInteractor;
    public final KeyguardTransitionRepositoryImpl transitionRepository;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        long duration = DurationKt.toDuration(400, durationUnit);
        DEFAULT_DURATION = duration;
        TO_DOZING_DURATION = DurationKt.toDuration(500, durationUnit);
        TO_DREAMING_DURATION = DurationKt.toDuration(933, durationUnit);
        TO_DREAMING_HOSTED_DURATION = DurationKt.toDuration(933, durationUnit);
        TO_OCCLUDED_DURATION = DurationKt.toDuration(550, durationUnit);
        TO_AOD_DURATION = DurationKt.toDuration(500, durationUnit);
        TO_AOD_FOLD_DURATION = DurationKt.toDuration(1100, durationUnit);
        TO_PRIMARY_BOUNCER_DURATION = duration;
        TO_GONE_DURATION = DurationKt.toDuration(633, durationUnit);
        TO_GLANCEABLE_HUB_DURATION = DurationKt.toDuration(1, DurationUnit.SECONDS);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FromLockscreenTransitionInteractor(com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r10, com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r11, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r12, kotlinx.coroutines.CoroutineScope r13, kotlinx.coroutines.CoroutineDispatcher r14, kotlinx.coroutines.CoroutineDispatcher r15, com.android.systemui.keyguard.domain.interactor.KeyguardInteractor r16, com.android.systemui.shade.data.repository.ShadeRepository r17, com.android.systemui.power.domain.interactor.PowerInteractor r18, com.android.systemui.keyguard.domain.interactor.GlanceableHubTransitions r19, com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor r20, com.android.systemui.keyguard.domain.interactor.SwipeToDismissInteractor r21, com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor r22) {
        /*
            r9 = this;
            r7 = r9
            com.android.systemui.keyguard.shared.model.KeyguardState r8 = com.android.systemui.keyguard.shared.model.KeyguardState.LOCKSCREEN
            r0 = r9
            r1 = r8
            r2 = r12
            r3 = r15
            r4 = r18
            r5 = r22
            r6 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r0 = r10
            r7.transitionRepository = r0
            r0 = r11
            r7.internalTransitionInteractor = r0
            r0 = r13
            r7.scope = r0
            r0 = r17
            r7.shadeRepository = r0
            com.android.systemui.keyguard.shared.model.Edge$Companion r0 = com.android.systemui.keyguard.shared.model.Edge.Companion
            com.android.compose.animation.scene.SceneKey r0 = com.android.systemui.scene.shared.model.Scenes.Bouncer
            com.android.systemui.keyguard.shared.model.Edge$StateToScene r0 = new com.android.systemui.keyguard.shared.model.Edge$StateToScene
            com.android.systemui.keyguard.shared.model.KeyguardState r0 = com.android.systemui.keyguard.shared.model.KeyguardState.GONE
            com.android.systemui.keyguard.shared.model.Edge$StateToState r1 = new com.android.systemui.keyguard.shared.model.Edge$StateToState
            r1.<init>(r8, r0)
            r0 = r12
            kotlinx.coroutines.flow.Flow r0 = r12.transition(r1)
            com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor$special$$inlined$map$1 r1 = new com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor$special$$inlined$map$1
            r1.<init>()
            com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor$surfaceBehindVisibility$2 r0 = new com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor$surfaceBehindVisibility$2
            r2 = 2
            r3 = 0
            r0.<init>(r2, r3)
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 r2 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1
            r2.<init>(r0, r1)
            kotlinx.coroutines.flow.Flow r0 = kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(r2)
            r7.surfaceBehindVisibility = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor.<init>(com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl, com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor, kotlinx.coroutines.CoroutineScope, kotlinx.coroutines.CoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher, com.android.systemui.keyguard.domain.interactor.KeyguardInteractor, com.android.systemui.shade.data.repository.ShadeRepository, com.android.systemui.power.domain.interactor.PowerInteractor, com.android.systemui.keyguard.domain.interactor.GlanceableHubTransitions, com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor, com.android.systemui.keyguard.domain.interactor.SwipeToDismissInteractor, com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor):void");
    }

    public final void dismissKeyguard() {
        CoroutineTracingKt.launch$default(this.scope, null, new FromLockscreenTransitionInteractor$dismissKeyguard$1(this, null), 6);
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState) {
        long j;
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(Interpolators.LINEAR);
        int ordinal = keyguardState.ordinal();
        if (ordinal == 1) {
            j = TO_DOZING_DURATION;
        } else if (ordinal != 2) {
            j = ordinal != 3 ? ordinal != 4 ? ordinal != 8 ? ordinal != 11 ? DEFAULT_DURATION : TO_OCCLUDED_DURATION : TO_GLANCEABLE_HUB_DURATION : ((WakefulnessModel) ((StateFlowImpl) this.powerInteractor.detailedWakefulness.$$delegate_0).getValue()).lastSleepReason == WakeSleepReason.FOLD ? TO_AOD_FOLD_DURATION : TO_AOD_DURATION : TO_DREAMING_HOSTED_DURATION;
        } else {
            int i = Duration.$r8$clinit;
            j = Duration.m1780plusLRDsOJo(TO_DREAMING_DURATION, DurationKt.toDuration(100, DurationUnit.MILLISECONDS));
        }
        valueAnimator.setDuration(Duration.m1777getInWholeMillisecondsimpl(j));
        return valueAnimator;
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final InternalKeyguardTransitionInteractor getInternalTransitionInteractor() {
        return this.internalTransitionInteractor;
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final KeyguardTransitionRepositoryImpl getTransitionRepository() {
        return this.transitionRepository;
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final void start() {
        FromLockscreenTransitionInteractor$listenForLockscreenToGone$1 fromLockscreenTransitionInteractor$listenForLockscreenToGone$1 = new FromLockscreenTransitionInteractor$listenForLockscreenToGone$1(this, null);
        CoroutineScope coroutineScope = this.scope;
        CoroutineTracingKt.launch$default(coroutineScope, null, fromLockscreenTransitionInteractor$listenForLockscreenToGone$1, 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromLockscreenTransitionInteractor$listenForLockscreenToOccludedOrDreaming$2(this, null), 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromLockscreenTransitionInteractor$listenForLockscreenToAodOrDozing$1(this, null), 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromLockscreenTransitionInteractor$listenForLockscreenToPrimaryBouncer$1(this, null), 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromLockscreenTransitionInteractor$listenForLockscreenToDreaming$1(this, SetsKt.setOf(KeyguardState.AOD, KeyguardState.DOZING), null), 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromLockscreenTransitionInteractor$listenForLockscreenToPrimaryBouncerDragging$1(this, new Ref$ObjectRef(), null), 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromLockscreenTransitionInteractor$listenForLockscreenToAlternateBouncer$1(this, null), 6);
        listenForTransitionToCamera(coroutineScope, this.keyguardInteractor);
    }
}
