package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import com.android.app.animation.Interpolators;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
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
public final class FromAodTransitionInteractor extends TransitionInteractor {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long DEFAULT_DURATION;
    public static final long TO_GONE_DURATION;
    public static final long TO_LOCKSCREEN_DURATION;
    public static final long TO_OCCLUDED_DURATION;
    public static final long TO_PRIMARY_BOUNCER_DURATION;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final CoroutineScope scope;
    public final KeyguardTransitionRepositoryImpl transitionRepository;
    public final KeyguardWakeDirectlyToGoneInteractor wakeToGoneInteractor;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        long duration = DurationKt.toDuration(500, durationUnit);
        DEFAULT_DURATION = duration;
        TO_GONE_DURATION = duration;
        TO_LOCKSCREEN_DURATION = DurationKt.toDuration(500, durationUnit);
        TO_OCCLUDED_DURATION = DurationKt.toDuration(550, durationUnit);
        TO_PRIMARY_BOUNCER_DURATION = duration;
    }

    public FromAodTransitionInteractor(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, KeyguardInteractor keyguardInteractor, PowerInteractor powerInteractor, KeyguardOcclusionInteractor keyguardOcclusionInteractor, KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor) {
        super(KeyguardState.AOD, keyguardTransitionInteractor, coroutineDispatcher2, powerInteractor, keyguardOcclusionInteractor, keyguardInteractor);
        this.transitionRepository = keyguardTransitionRepositoryImpl;
        this.internalTransitionInteractor = internalKeyguardTransitionInteractor;
        this.scope = coroutineScope;
        this.wakeToGoneInteractor = keyguardWakeDirectlyToGoneInteractor;
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(Interpolators.LINEAR);
        int ordinal = keyguardState.ordinal();
        valueAnimator.setDuration(Duration.m1777getInWholeMillisecondsimpl(ordinal != 6 ? ordinal != 7 ? ordinal != 9 ? ordinal != 11 ? DEFAULT_DURATION : TO_OCCLUDED_DURATION : TO_GONE_DURATION : TO_LOCKSCREEN_DURATION : TO_PRIMARY_BOUNCER_DURATION));
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
        FromAodTransitionInteractor$listenForAodToAwake$1 fromAodTransitionInteractor$listenForAodToAwake$1 = new FromAodTransitionInteractor$listenForAodToAwake$1(this, null);
        CoroutineScope coroutineScope = this.scope;
        CoroutineTracingKt.launch$default(coroutineScope, null, fromAodTransitionInteractor$listenForAodToAwake$1, 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromAodTransitionInteractor$listenForAodToOccluded$1(this, null), 6);
        CoroutineTracingKt.launch$default(coroutineScope, null, new FromAodTransitionInteractor$listenForAodToPrimaryBouncer$1(this, null), 6);
        listenForTransitionToCamera(coroutineScope, this.keyguardInteractor);
    }
}
