package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import com.android.app.animation.Interpolators;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.communal.shared.model.CommunalTransitionKeys;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FromOccludedTransitionInteractor extends TransitionInteractor {
    public static final long DEFAULT_DURATION;
    public static final long TO_ALTERNATE_BOUNCER_DURATION;
    public static final long TO_AOD_DURATION;
    public static final long TO_DOZING_DURATION;
    public static final long TO_GLANCEABLE_HUB_DURATION;
    public static final long TO_LOCKSCREEN_DURATION;
    public final CommunalInteractor communalInteractor;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final CoroutineScope scope;
    public final KeyguardTransitionRepositoryImpl transitionRepository;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KeyguardState.values().length];
            try {
                KeyguardState.Companion companion = KeyguardState.Companion;
                iArr[5] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                KeyguardState.Companion companion2 = KeyguardState.Companion;
                iArr[8] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                KeyguardState.Companion companion3 = KeyguardState.Companion;
                iArr[7] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        long duration = DurationKt.toDuration(500, durationUnit);
        DEFAULT_DURATION = duration;
        TO_ALTERNATE_BOUNCER_DURATION = duration;
        TO_AOD_DURATION = duration;
        TO_DOZING_DURATION = duration;
        TO_GLANCEABLE_HUB_DURATION = DurationKt.toDuration(250, durationUnit);
        TO_LOCKSCREEN_DURATION = DurationKt.toDuration(933, durationUnit);
    }

    public FromOccludedTransitionInteractor(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, KeyguardInteractor keyguardInteractor, PowerInteractor powerInteractor, CommunalInteractor communalInteractor, CommunalSceneInteractor communalSceneInteractor, KeyguardOcclusionInteractor keyguardOcclusionInteractor) {
        super(KeyguardState.OCCLUDED, keyguardTransitionInteractor, coroutineDispatcher2, powerInteractor, keyguardOcclusionInteractor, keyguardInteractor);
        this.transitionRepository = keyguardTransitionRepositoryImpl;
        this.internalTransitionInteractor = internalKeyguardTransitionInteractor;
        this.scope = coroutineScope;
        this.communalInteractor = communalInteractor;
        this.communalSceneInteractor = communalSceneInteractor;
    }

    public static final Object access$startTransitionToLockscreenOrHub(FromOccludedTransitionInteractor fromOccludedTransitionInteractor, boolean z, boolean z2, Continuation continuation) {
        fromOccludedTransitionInteractor.getClass();
        Unit unit = Unit.INSTANCE;
        if (z || z2) {
            CommunalSceneInteractor.changeScene$default(fromOccludedTransitionInteractor.communalSceneInteractor, CommunalScenes.Communal, "occluded to hub", CommunalTransitionKeys.SimpleFade, 8);
            return unit;
        }
        Object startTransitionTo$default = TransitionInteractor.startTransitionTo$default(fromOccludedTransitionInteractor, KeyguardState.LOCKSCREEN, null, null, null, continuation, 14);
        return startTransitionTo$default == CoroutineSingletons.COROUTINE_SUSPENDED ? startTransitionTo$default : unit;
    }

    public final void dismissFromOccluded() {
        BuildersKt.launch$default(this.scope, null, null, new FromOccludedTransitionInteractor$dismissFromOccluded$1(this, null), 3);
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(WhenMappings.$EnumSwitchMapping$0[keyguardState.ordinal()] == 1 ? Interpolators.FAST_OUT_SLOW_IN : Interpolators.LINEAR);
        int ordinal = keyguardState.ordinal();
        valueAnimator.setDuration(Duration.m1777getInWholeMillisecondsimpl(ordinal != 5 ? ordinal != 7 ? ordinal != 8 ? DEFAULT_DURATION : TO_GLANCEABLE_HUB_DURATION : TO_LOCKSCREEN_DURATION : TO_ALTERNATE_BOUNCER_DURATION));
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
        FromOccludedTransitionInteractor$listenForOccludedToLockscreenOrHub$2 fromOccludedTransitionInteractor$listenForOccludedToLockscreenOrHub$2 = new FromOccludedTransitionInteractor$listenForOccludedToLockscreenOrHub$2(this, null);
        CoroutineScope coroutineScope = this.scope;
        BuildersKt.launch$default(coroutineScope, null, null, fromOccludedTransitionInteractor$listenForOccludedToLockscreenOrHub$2, 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromOccludedTransitionInteractor$listenForOccludedToDreaming$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromOccludedTransitionInteractor$listenForOccludedToAsleep$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromOccludedTransitionInteractor$listenForOccludedToGone$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromOccludedTransitionInteractor$listenForOccludedToAlternateBouncer$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromOccludedTransitionInteractor$listenForOccludedToPrimaryBouncer$1(this, null), 3);
    }
}
