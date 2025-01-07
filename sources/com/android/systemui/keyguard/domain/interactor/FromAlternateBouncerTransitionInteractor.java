package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.wm.shell.shared.animation.Interpolators;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FromAlternateBouncerTransitionInteractor extends TransitionInteractor {
    public static final long TO_AOD_DURATION;
    public static final long TO_DOZING_DURATION;
    public static final long TO_GONE_DURATION;
    public static final long TO_LOCKSCREEN_DURATION;
    public static final long TO_OCCLUDED_DURATION;
    public static final long TO_PRIMARY_BOUNCER_DURATION;
    public static final long TRANSITION_DURATION_MS;
    public final CommunalInteractor communalInteractor;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final PrimaryBouncerInteractor primaryBouncerInteractor;
    public final CoroutineScope scope;
    public final Flow surfaceBehindVisibility;
    public final KeyguardTransitionRepositoryImpl transitionRepository;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        long duration = DurationKt.toDuration(300, durationUnit);
        TRANSITION_DURATION_MS = duration;
        TO_AOD_DURATION = duration;
        TO_DOZING_DURATION = duration;
        TO_GONE_DURATION = DurationKt.toDuration(500, durationUnit);
        TO_LOCKSCREEN_DURATION = DurationKt.toDuration(300, durationUnit);
        TO_OCCLUDED_DURATION = duration;
        TO_PRIMARY_BOUNCER_DURATION = duration;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FromAlternateBouncerTransitionInteractor(com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r10, com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r11, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r12, kotlinx.coroutines.CoroutineScope r13, kotlinx.coroutines.CoroutineDispatcher r14, kotlinx.coroutines.CoroutineDispatcher r15, com.android.systemui.keyguard.domain.interactor.KeyguardInteractor r16, com.android.systemui.communal.domain.interactor.CommunalInteractor r17, com.android.systemui.power.domain.interactor.PowerInteractor r18, com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor r19, com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor r20) {
        /*
            r9 = this;
            r7 = r9
            com.android.systemui.keyguard.shared.model.KeyguardState r8 = com.android.systemui.keyguard.shared.model.KeyguardState.ALTERNATE_BOUNCER
            r0 = r9
            r1 = r8
            r2 = r12
            r3 = r15
            r4 = r18
            r5 = r19
            r6 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r0 = r10
            r7.transitionRepository = r0
            r0 = r11
            r7.internalTransitionInteractor = r0
            r0 = r13
            r7.scope = r0
            r0 = r17
            r7.communalInteractor = r0
            r0 = r20
            r7.primaryBouncerInteractor = r0
            com.android.systemui.keyguard.shared.model.Edge$Companion r0 = com.android.systemui.keyguard.shared.model.Edge.Companion
            com.android.compose.animation.scene.SceneKey r0 = com.android.systemui.scene.shared.model.Scenes.Bouncer
            com.android.systemui.keyguard.shared.model.Edge$StateToScene r0 = new com.android.systemui.keyguard.shared.model.Edge$StateToScene
            com.android.systemui.keyguard.shared.model.KeyguardState r0 = com.android.systemui.keyguard.shared.model.KeyguardState.GONE
            com.android.systemui.keyguard.shared.model.Edge$StateToState r1 = new com.android.systemui.keyguard.shared.model.Edge$StateToState
            r1.<init>(r8, r0)
            r0 = r12
            kotlinx.coroutines.flow.Flow r0 = r12.transition(r1)
            com.android.systemui.keyguard.domain.interactor.FromAlternateBouncerTransitionInteractor$special$$inlined$map$1 r1 = new com.android.systemui.keyguard.domain.interactor.FromAlternateBouncerTransitionInteractor$special$$inlined$map$1
            r2 = 0
            r1.<init>(r0, r2)
            com.android.systemui.keyguard.domain.interactor.FromAlternateBouncerTransitionInteractor$surfaceBehindVisibility$2 r0 = new com.android.systemui.keyguard.domain.interactor.FromAlternateBouncerTransitionInteractor$surfaceBehindVisibility$2
            r2 = 2
            r3 = 0
            r0.<init>(r2, r3)
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 r2 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1
            r2.<init>(r0, r1)
            kotlinx.coroutines.flow.Flow r0 = kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(r2)
            r7.surfaceBehindVisibility = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.FromAlternateBouncerTransitionInteractor.<init>(com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl, com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor, kotlinx.coroutines.CoroutineScope, kotlinx.coroutines.CoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher, com.android.systemui.keyguard.domain.interactor.KeyguardInteractor, com.android.systemui.communal.domain.interactor.CommunalInteractor, com.android.systemui.power.domain.interactor.PowerInteractor, com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor, com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor):void");
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(Interpolators.LINEAR);
        int ordinal = keyguardState.ordinal();
        valueAnimator.setDuration(Duration.m1777getInWholeMillisecondsimpl(ordinal != 1 ? ordinal != 4 ? ordinal != 9 ? ordinal != 11 ? ordinal != 6 ? ordinal != 7 ? TRANSITION_DURATION_MS : TO_LOCKSCREEN_DURATION : TO_PRIMARY_BOUNCER_DURATION : TO_OCCLUDED_DURATION : TO_GONE_DURATION : TO_AOD_DURATION : TO_DOZING_DURATION));
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
        FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1 fromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1 = new FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1(this, null);
        CoroutineScope coroutineScope = this.scope;
        BuildersKt.launch$default(coroutineScope, null, null, fromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1, 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToLockscreenHubAodOrDozing$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToPrimaryBouncer$1(this, null), 3);
        listenForTransitionToCamera(coroutineScope, this.keyguardInteractor);
    }
}
