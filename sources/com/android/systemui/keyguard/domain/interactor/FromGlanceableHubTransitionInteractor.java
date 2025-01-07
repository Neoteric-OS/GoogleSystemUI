package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import com.android.app.animation.Interpolators;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FromGlanceableHubTransitionInteractor extends TransitionInteractor {
    public static final long DEFAULT_DURATION;
    public static final long TO_BOUNCER_DURATION;
    public static final long TO_LOCKSCREEN_DURATION;
    public static final long TO_OCCLUDED_DURATION;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final GlanceableHubTransitions glanceableHubTransitions;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final CoroutineScope scope;
    public final KeyguardTransitionRepositoryImpl transitionRepository;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        DEFAULT_DURATION = DurationKt.toDuration(400, durationUnit);
        TO_LOCKSCREEN_DURATION = DurationKt.toDuration(1, DurationUnit.SECONDS);
        TO_BOUNCER_DURATION = DurationKt.toDuration(400, durationUnit);
        TO_OCCLUDED_DURATION = DurationKt.toDuration(450, durationUnit);
    }

    public FromGlanceableHubTransitionInteractor(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, GlanceableHubTransitions glanceableHubTransitions, CommunalSettingsInteractor communalSettingsInteractor, KeyguardInteractor keyguardInteractor, CommunalSceneInteractor communalSceneInteractor, KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, PowerInteractor powerInteractor, KeyguardOcclusionInteractor keyguardOcclusionInteractor) {
        super(KeyguardState.GLANCEABLE_HUB, keyguardTransitionInteractor, coroutineDispatcher, powerInteractor, keyguardOcclusionInteractor, keyguardInteractor);
        this.scope = coroutineScope;
        this.communalSettingsInteractor = communalSettingsInteractor;
        this.communalSceneInteractor = communalSceneInteractor;
        this.transitionRepository = keyguardTransitionRepositoryImpl;
        this.internalTransitionInteractor = internalKeyguardTransitionInteractor;
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(Interpolators.LINEAR);
        int ordinal = keyguardState.ordinal();
        long j = TO_BOUNCER_DURATION;
        if (ordinal != 5 && ordinal != 6) {
            j = ordinal != 7 ? ordinal != 11 ? DEFAULT_DURATION : TO_OCCLUDED_DURATION : TO_LOCKSCREEN_DURATION;
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
        if (this.communalSettingsInteractor.isCommunalFlagEnabled()) {
            FromGlanceableHubTransitionInteractor$listenForHubToDozing$1 fromGlanceableHubTransitionInteractor$listenForHubToDozing$1 = new FromGlanceableHubTransitionInteractor$listenForHubToDozing$1(this, null);
            CoroutineScope coroutineScope = this.scope;
            BuildersKt.launch$default(coroutineScope, null, null, fromGlanceableHubTransitionInteractor$listenForHubToDozing$1, 3);
            CoroutineTracingKt.launch$default(coroutineScope, null, new FromGlanceableHubTransitionInteractor$listenForHubToPrimaryBouncer$1(this, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new FromGlanceableHubTransitionInteractor$listenForHubToAlternateBouncer$1(this, null), 6);
            BuildersKt.launch$default(coroutineScope, null, null, new FromGlanceableHubTransitionInteractor$listenForHubToOccluded$2(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new FromGlanceableHubTransitionInteractor$listenForHubToGone$1(this, null), 3);
        }
    }
}
