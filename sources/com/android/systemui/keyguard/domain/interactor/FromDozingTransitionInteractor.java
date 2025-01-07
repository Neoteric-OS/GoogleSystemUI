package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import android.app.DreamManager;
import com.android.app.animation.Interpolators;
import com.android.app.viewcapture.data.ViewNode;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
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
public final class FromDozingTransitionInteractor extends TransitionInteractor {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long DEFAULT_DURATION;
    public static final long TO_GLANCEABLE_HUB_DURATION;
    public static final long TO_GONE_DURATION;
    public static final long TO_LOCKSCREEN_DURATION;
    public static final long TO_OCCLUDED_DURATION;
    public static final long TO_PRIMARY_BOUNCER_DURATION;
    public final CommunalInteractor communalInteractor;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final DeviceEntryInteractor deviceEntryInteractor;
    public final DreamManager dreamManager;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final CoroutineScope scope;
    public final KeyguardTransitionRepositoryImpl transitionRepository;
    public final KeyguardWakeDirectlyToGoneInteractor wakeToGoneInteractor;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        long duration = DurationKt.toDuration(500, durationUnit);
        DEFAULT_DURATION = duration;
        TO_GLANCEABLE_HUB_DURATION = duration;
        TO_GONE_DURATION = duration;
        TO_LOCKSCREEN_DURATION = duration;
        TO_OCCLUDED_DURATION = DurationKt.toDuration(550, durationUnit);
        TO_PRIMARY_BOUNCER_DURATION = duration;
    }

    public FromDozingTransitionInteractor(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, KeyguardInteractor keyguardInteractor, PowerInteractor powerInteractor, CommunalInteractor communalInteractor, CommunalSceneInteractor communalSceneInteractor, KeyguardOcclusionInteractor keyguardOcclusionInteractor, DeviceEntryInteractor deviceEntryInteractor, KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor, DreamManager dreamManager) {
        super(KeyguardState.DOZING, keyguardTransitionInteractor, coroutineDispatcher2, powerInteractor, keyguardOcclusionInteractor, keyguardInteractor);
        this.transitionRepository = keyguardTransitionRepositoryImpl;
        this.internalTransitionInteractor = internalKeyguardTransitionInteractor;
        this.scope = coroutineScope;
        this.communalInteractor = communalInteractor;
        this.communalSceneInteractor = communalSceneInteractor;
        this.deviceEntryInteractor = deviceEntryInteractor;
        this.dreamManager = dreamManager;
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState) {
        long j;
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(Interpolators.LINEAR);
        switch (keyguardState.ordinal()) {
            case 6:
                j = TO_PRIMARY_BOUNCER_DURATION;
                break;
            case 7:
                j = TO_LOCKSCREEN_DURATION;
                break;
            case 8:
                j = TO_GLANCEABLE_HUB_DURATION;
                break;
            case 9:
                j = TO_GONE_DURATION;
                break;
            case 10:
            default:
                j = DEFAULT_DURATION;
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                j = TO_OCCLUDED_DURATION;
                break;
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
        FromDozingTransitionInteractor$listenForDozingToAny$1 fromDozingTransitionInteractor$listenForDozingToAny$1 = new FromDozingTransitionInteractor$listenForDozingToAny$1(this, null);
        CoroutineScope coroutineScope = this.scope;
        BuildersKt.launch$default(coroutineScope, null, null, fromDozingTransitionInteractor$listenForDozingToAny$1, 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromDozingTransitionInteractor$listenForDozingToGoneViaBiometrics$1(this, null), 3);
        listenForTransitionToCamera(coroutineScope, this.keyguardInteractor);
    }
}
