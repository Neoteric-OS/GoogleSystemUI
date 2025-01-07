package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import com.android.app.animation.Interpolators;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FromGoneTransitionInteractor extends TransitionInteractor {
    public static final long DEFAULT_DURATION;
    public static final long TO_AOD_DURATION;
    public static final long TO_DOZING_DURATION;
    public static final long TO_DREAMING_DURATION;
    public static final long TO_GLANCEABLE_HUB_DURATION;
    public static final long TO_LOCKSCREEN_DURATION;
    public static final long TO_OCCLUDED_DURATION;
    public final BiometricSettingsRepositoryImpl biometricSettingsRepository;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final KeyguardEnabledInteractor keyguardEnabledInteractor;
    public final KeyguardRepositoryImpl keyguardRepository;
    public final CoroutineScope scope;
    public final KeyguardTransitionRepositoryImpl transitionRepository;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        long duration = DurationKt.toDuration(500, durationUnit);
        DEFAULT_DURATION = duration;
        TO_AOD_DURATION = DurationKt.toDuration(1300, durationUnit);
        TO_DOZING_DURATION = DurationKt.toDuration(933, durationUnit);
        TO_DREAMING_DURATION = DurationKt.toDuration(933, durationUnit);
        TO_LOCKSCREEN_DURATION = duration;
        TO_GLANCEABLE_HUB_DURATION = duration;
        TO_OCCLUDED_DURATION = DurationKt.toDuration(100, durationUnit);
    }

    public FromGoneTransitionInteractor(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, KeyguardInteractor keyguardInteractor, PowerInteractor powerInteractor, CommunalSceneInteractor communalSceneInteractor, KeyguardOcclusionInteractor keyguardOcclusionInteractor, BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, KeyguardRepositoryImpl keyguardRepositoryImpl, KeyguardEnabledInteractor keyguardEnabledInteractor) {
        super(KeyguardState.GONE, keyguardTransitionInteractor, coroutineDispatcher2, powerInteractor, keyguardOcclusionInteractor, keyguardInteractor);
        this.transitionRepository = keyguardTransitionRepositoryImpl;
        this.internalTransitionInteractor = internalKeyguardTransitionInteractor;
        this.scope = coroutineScope;
        this.communalSceneInteractor = communalSceneInteractor;
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(Interpolators.LINEAR);
        int ordinal = keyguardState.ordinal();
        valueAnimator.setDuration(Duration.m1777getInWholeMillisecondsimpl(ordinal != 1 ? ordinal != 2 ? ordinal != 4 ? ordinal != 11 ? ordinal != 7 ? ordinal != 8 ? DEFAULT_DURATION : TO_GLANCEABLE_HUB_DURATION : TO_LOCKSCREEN_DURATION : TO_OCCLUDED_DURATION : TO_AOD_DURATION : TO_DREAMING_DURATION : TO_DOZING_DURATION));
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
        FromGoneTransitionInteractor$listenForGoneToAodOrDozing$1 fromGoneTransitionInteractor$listenForGoneToAodOrDozing$1 = new FromGoneTransitionInteractor$listenForGoneToAodOrDozing$1(this, null);
        CoroutineScope coroutineScope = this.scope;
        CoroutineTracingKt.launch$default(coroutineScope, null, fromGoneTransitionInteractor$listenForGoneToAodOrDozing$1, 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromGoneTransitionInteractor$listenForGoneToDreaming$1(this, null), 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromGoneTransitionInteractor$listenForGoneToLockscreenOrHubOrOccluded$3(this, null), 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromGoneTransitionInteractor$listenForGoneToOccluded$1(this, null), 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromGoneTransitionInteractor$listenForGoneToDreamingLockscreenHosted$1(this, null), 6);
    }
}
