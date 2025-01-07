package com.android.systemui.keyguard.domain.interactor;

import android.animation.ValueAnimator;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.wm.shell.shared.animation.Interpolators;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FromPrimaryBouncerTransitionInteractor extends TransitionInteractor {
    public static final long DEFAULT_DURATION;
    public static final long TO_AOD_DURATION;
    public static final long TO_DOZING_DURATION;
    public static final long TO_GLANCEABLE_HUB_DURATION;
    public static final long TO_GONE_DURATION;
    public static final long TO_GONE_SHORT_DURATION;
    public static final float TO_GONE_SURFACE_BEHIND_VISIBLE_THRESHOLD;
    public static final long TO_LOCKSCREEN_DURATION;
    public static final long TO_OCCLUDED_DURATION;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final KeyguardSecurityModel keyguardSecurityModel;
    public final CoroutineScope scope;
    public final SelectedUserInteractor selectedUserInteractor;
    public final Flow surfaceBehindVisibility;
    public final KeyguardTransitionRepositoryImpl transitionRepository;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        long duration = DurationKt.toDuration(300, durationUnit);
        DEFAULT_DURATION = duration;
        TO_AOD_DURATION = duration;
        TO_DOZING_DURATION = duration;
        TO_GONE_DURATION = DurationKt.toDuration(500, durationUnit);
        TO_GONE_SHORT_DURATION = DurationKt.toDuration(200, durationUnit);
        TO_LOCKSCREEN_DURATION = DurationKt.toDuration(450, durationUnit);
        TO_OCCLUDED_DURATION = DurationKt.toDuration(550, durationUnit);
        TO_GLANCEABLE_HUB_DURATION = duration;
        TO_GONE_SURFACE_BEHIND_VISIBLE_THRESHOLD = 0.1f;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FromPrimaryBouncerTransitionInteractor(com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r10, com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r11, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r12, kotlinx.coroutines.CoroutineScope r13, kotlinx.coroutines.CoroutineDispatcher r14, kotlinx.coroutines.CoroutineDispatcher r15, com.android.systemui.keyguard.domain.interactor.KeyguardInteractor r16, com.android.systemui.communal.domain.interactor.CommunalSceneInteractor r17, com.android.keyguard.KeyguardSecurityModel r18, com.android.systemui.user.domain.interactor.SelectedUserInteractor r19, com.android.systemui.power.domain.interactor.PowerInteractor r20, com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor r21) {
        /*
            r9 = this;
            r7 = r9
            com.android.systemui.keyguard.shared.model.KeyguardState r8 = com.android.systemui.keyguard.shared.model.KeyguardState.PRIMARY_BOUNCER
            r0 = r9
            r1 = r8
            r2 = r12
            r3 = r15
            r4 = r20
            r5 = r21
            r6 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r0 = r10
            r7.transitionRepository = r0
            r0 = r11
            r7.internalTransitionInteractor = r0
            r0 = r13
            r7.scope = r0
            r0 = r17
            r7.communalSceneInteractor = r0
            r0 = r18
            r7.keyguardSecurityModel = r0
            r0 = r19
            r7.selectedUserInteractor = r0
            com.android.systemui.keyguard.shared.model.Edge$Companion r0 = com.android.systemui.keyguard.shared.model.Edge.Companion
            com.android.systemui.keyguard.shared.model.KeyguardState r0 = com.android.systemui.keyguard.shared.model.KeyguardState.GONE
            com.android.systemui.keyguard.shared.model.Edge$StateToState r1 = new com.android.systemui.keyguard.shared.model.Edge$StateToState
            r1.<init>(r8, r0)
            r0 = r12
            kotlinx.coroutines.flow.Flow r0 = r12.transition(r1)
            com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$special$$inlined$map$1 r1 = new com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$special$$inlined$map$1
            r1.<init>()
            com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$surfaceBehindVisibility$2 r0 = new com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$surfaceBehindVisibility$2
            r2 = 2
            r3 = 0
            r0.<init>(r2, r3)
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 r2 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1
            r2.<init>(r0, r1)
            kotlinx.coroutines.flow.Flow r0 = kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(r2)
            r7.surfaceBehindVisibility = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor.<init>(com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl, com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor, kotlinx.coroutines.CoroutineScope, kotlinx.coroutines.CoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher, com.android.systemui.keyguard.domain.interactor.KeyguardInteractor, com.android.systemui.communal.domain.interactor.CommunalSceneInteractor, com.android.keyguard.KeyguardSecurityModel, com.android.systemui.user.domain.interactor.SelectedUserInteractor, com.android.systemui.power.domain.interactor.PowerInteractor, com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor):void");
    }

    public static final void access$closeHubImmediatelyIfNeeded(FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor) {
        CommunalSceneInteractor communalSceneInteractor = fromPrimaryBouncerTransitionInteractor.communalSceneInteractor;
        if (((Boolean) ((StateFlowImpl) communalSceneInteractor.isIdleOnCommunal.$$delegate_0).getValue()).booleanValue() && !((Boolean) ((StateFlowImpl) communalSceneInteractor.isLaunchingWidget.$$delegate_0).getValue()).booleanValue() && ((StateFlowImpl) communalSceneInteractor.editModeState.$$delegate_0).getValue() == null) {
            CommunalSceneInteractor.snapToScene$default(fromPrimaryBouncerTransitionInteractor.communalSceneInteractor, CommunalScenes.Blank, "FromPrimaryBouncerTransitionInteractor", 12);
        }
    }

    @Override // com.android.systemui.keyguard.domain.interactor.TransitionInteractor
    public final ValueAnimator getDefaultAnimatorForTransitionsToState(KeyguardState keyguardState) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(Interpolators.LINEAR);
        int ordinal = keyguardState.ordinal();
        valueAnimator.setDuration(Duration.m1777getInWholeMillisecondsimpl(ordinal != 1 ? ordinal != 4 ? ordinal != 11 ? ordinal != 7 ? ordinal != 8 ? ordinal != 9 ? DEFAULT_DURATION : TO_GONE_DURATION : TO_GLANCEABLE_HUB_DURATION : TO_LOCKSCREEN_DURATION : TO_OCCLUDED_DURATION : TO_AOD_DURATION : TO_DOZING_DURATION));
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
        FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1 fromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1 = new FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1(this, null);
        CoroutineScope coroutineScope = this.scope;
        BuildersKt.launch$default(coroutineScope, null, null, fromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1, 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToAsleep$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerNotShowing$2(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToDreamingLockscreenHosted$1(this, null), 3);
        listenForTransitionToCamera(coroutineScope, this.keyguardInteractor);
    }
}
