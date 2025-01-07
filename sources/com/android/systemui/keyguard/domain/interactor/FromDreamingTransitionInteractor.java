package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import android.app.DreamManager;
import com.android.app.animation.Interpolators;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
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
public final class FromDreamingTransitionInteractor extends TransitionInteractor {
    public static final long DEFAULT_DURATION;
    public static final long TO_AOD_DURATION;
    public static final long TO_GLANCEABLE_HUB_DURATION;
    public static final long TO_GONE_DURATION;
    public static final long TO_LOCKSCREEN_DURATION;
    public final CommunalInteractor communalInteractor;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final DeviceEntryInteractor deviceEntryInteractor;
    public final DreamManager dreamManager;
    public final GlanceableHubTransitions glanceableHubTransitions;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final CoroutineScope scope;
    public final KeyguardTransitionRepositoryImpl transitionRepository;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        long duration = DurationKt.toDuration(500, durationUnit);
        DEFAULT_DURATION = duration;
        TO_GLANCEABLE_HUB_DURATION = DurationKt.toDuration(1, DurationUnit.SECONDS);
        TO_LOCKSCREEN_DURATION = DurationKt.toDuration(1167, durationUnit);
        TO_AOD_DURATION = DurationKt.toDuration(300, durationUnit);
        TO_GONE_DURATION = duration;
    }

    public FromDreamingTransitionInteractor(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, KeyguardInteractor keyguardInteractor, GlanceableHubTransitions glanceableHubTransitions, CommunalInteractor communalInteractor, CommunalSceneInteractor communalSceneInteractor, CommunalSettingsInteractor communalSettingsInteractor, PowerInteractor powerInteractor, KeyguardOcclusionInteractor keyguardOcclusionInteractor, DreamManager dreamManager, DeviceEntryInteractor deviceEntryInteractor) {
        super(KeyguardState.DREAMING, keyguardTransitionInteractor, coroutineDispatcher2, powerInteractor, keyguardOcclusionInteractor, keyguardInteractor);
        this.transitionRepository = keyguardTransitionRepositoryImpl;
        this.internalTransitionInteractor = internalKeyguardTransitionInteractor;
        this.scope = coroutineScope;
        this.communalInteractor = communalInteractor;
        this.communalSceneInteractor = communalSceneInteractor;
        this.communalSettingsInteractor = communalSettingsInteractor;
        this.dreamManager = dreamManager;
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(Interpolators.LINEAR);
        int ordinal = keyguardState.ordinal();
        valueAnimator.setDuration(Duration.m1777getInWholeMillisecondsimpl(ordinal != 7 ? ordinal != 8 ? DEFAULT_DURATION : TO_GLANCEABLE_HUB_DURATION : TO_LOCKSCREEN_DURATION));
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
        FromDreamingTransitionInteractor$listenForDreamingToAlternateBouncer$1 fromDreamingTransitionInteractor$listenForDreamingToAlternateBouncer$1 = new FromDreamingTransitionInteractor$listenForDreamingToAlternateBouncer$1(this, null);
        CoroutineScope coroutineScope = this.scope;
        CoroutineTracingKt.launch$default(coroutineScope, null, fromDreamingTransitionInteractor$listenForDreamingToAlternateBouncer$1, 6);
        BuildersKt.launch$default(coroutineScope, null, null, new FromDreamingTransitionInteractor$listenForDreamingToOccluded$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromDreamingTransitionInteractor$listenForDreamingToGoneWhenDismissable$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromDreamingTransitionInteractor$listenForDreamingToGoneFromBiometricUnlock$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromDreamingTransitionInteractor$listenForDreamingToAodOrDozing$1(this, null), 3);
        listenForTransitionToCamera(coroutineScope, this.keyguardInteractor);
        if (this.communalSettingsInteractor.isCommunalFlagEnabled()) {
            BuildersKt.launch$default(coroutineScope, null, null, new FromDreamingTransitionInteractor$listenForDreamingToGlanceableHubFromPowerButton$1(this, null), 3);
        }
        BuildersKt.launch$default(coroutineScope, null, null, new FromDreamingTransitionInteractor$listenForDreamingToPrimaryBouncer$1(this, null), 3);
    }

    public final void startToLockscreenOrGlanceableHubTransition(boolean z) {
        BuildersKt.launch$default(this.scope, null, null, new FromDreamingTransitionInteractor$startToLockscreenOrGlanceableHubTransition$1(this, z, null), 3);
    }
}
