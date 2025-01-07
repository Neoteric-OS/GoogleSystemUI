package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow.FlowBuilder;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrimaryBouncerToGoneTransitionViewModel {
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 bouncerAlpha;
    public boolean leaveShadeOpen;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 lockscreenAlpha;
    public final KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 notificationAlpha;
    public final PrimaryBouncerInteractor primaryBouncerInteractor;
    public final ChannelFlowTransformLatest scrimAlpha;
    public final Flow showAllNotifications;
    public final SysuiStatusBarStateController statusBarStateController;
    public final KeyguardTransitionAnimationFlow.FlowBuilder transitionAnimation;
    public boolean willRunDismissFromKeyguard;

    public PrimaryBouncerToGoneTransitionViewModel(SysuiStatusBarStateController sysuiStatusBarStateController, PrimaryBouncerInteractor primaryBouncerInteractor, BouncerToGoneFlows bouncerToGoneFlows, KeyguardTransitionAnimationFlow keyguardTransitionAnimationFlow) {
        this.statusBarStateController = sysuiStatusBarStateController;
        this.primaryBouncerInteractor = primaryBouncerInteractor;
        long j = FromPrimaryBouncerTransitionInteractor.TO_GONE_DURATION;
        Edge.Companion companion = Edge.Companion;
        keyguardTransitionAnimationFlow.getClass();
        KeyguardState keyguardState = KeyguardState.PRIMARY_BOUNCER;
        KeyguardTransitionAnimationFlow.FlowBuilder flowBuilder = keyguardTransitionAnimationFlow.new FlowBuilder(j, new Edge.StateToState(keyguardState, KeyguardState.GONE));
        this.transitionAnimation = flowBuilder;
        this.showAllNotifications = bouncerToGoneFlows.m831showAllNotificationsVtjQ1oo(j, keyguardState);
        int i = Duration.$r8$clinit;
        this.notificationAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, DurationKt.toDuration(200, DurationUnit.MILLISECONDS), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel$notificationAlpha$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                float floatValue = ((Number) obj).floatValue();
                PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel = PrimaryBouncerToGoneTransitionViewModel.this;
                float f = 1.0f;
                if (!primaryBouncerToGoneTransitionViewModel.willRunDismissFromKeyguard && !primaryBouncerToGoneTransitionViewModel.leaveShadeOpen) {
                    f = 1.0f - floatValue;
                }
                return Float.valueOf(f);
            }
        }, 0L, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel$notificationAlpha$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel = PrimaryBouncerToGoneTransitionViewModel.this;
                primaryBouncerToGoneTransitionViewModel.leaveShadeOpen = ((StatusBarStateControllerImpl) primaryBouncerToGoneTransitionViewModel.statusBarStateController).mLeaveOpenOnKeyguardHide;
                primaryBouncerToGoneTransitionViewModel.willRunDismissFromKeyguard = primaryBouncerToGoneTransitionViewModel.primaryBouncerInteractor.willRunDismissFromKeyguard();
                return Unit.INSTANCE;
            }
        }, null, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel$notificationAlpha$3
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(1.0f);
            }
        }, null, null, 212);
        final PrimaryBouncerToGoneTransitionViewModel$bouncerAlpha$2 primaryBouncerToGoneTransitionViewModel$bouncerAlpha$2 = new PrimaryBouncerToGoneTransitionViewModel$bouncerAlpha$2(0, primaryBouncerInteractor, PrimaryBouncerInteractor.class, "willRunDismissFromKeyguard", "willRunDismissFromKeyguard()Z", 0);
        int i2 = Duration.$r8$clinit;
        this.bouncerAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(this.transitionAnimation, DurationKt.toDuration(200, DurationUnit.MILLISECONDS), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel$createBouncerAlphaFlow$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Float.valueOf(PrimaryBouncerToGoneTransitionViewModel.this.willRunDismissFromKeyguard ? 0.0f : 1.0f - ((Number) obj).floatValue());
            }
        }, 0L, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel$createBouncerAlphaFlow$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                PrimaryBouncerToGoneTransitionViewModel.this.willRunDismissFromKeyguard = ((Boolean) primaryBouncerToGoneTransitionViewModel$bouncerAlpha$2.invoke()).booleanValue();
                return Unit.INSTANCE;
            }
        }, null, null, null, null, 244);
        final PrimaryBouncerToGoneTransitionViewModel$lockscreenAlpha$2 primaryBouncerToGoneTransitionViewModel$lockscreenAlpha$2 = new PrimaryBouncerToGoneTransitionViewModel$lockscreenAlpha$2(0, primaryBouncerInteractor, PrimaryBouncerInteractor.class, "willRunDismissFromKeyguard", "willRunDismissFromKeyguard()Z", 0);
        int i3 = Duration.$r8$clinit;
        this.lockscreenAlpha = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(this.transitionAnimation, DurationKt.toDuration(50, DurationUnit.MILLISECONDS), new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel$createLockscreenAlpha$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Number) obj).floatValue();
                PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel = PrimaryBouncerToGoneTransitionViewModel.this;
                return Float.valueOf((primaryBouncerToGoneTransitionViewModel.willRunDismissFromKeyguard || primaryBouncerToGoneTransitionViewModel.leaveShadeOpen) ? 1.0f : 0.0f);
            }
        }, 0L, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel$createLockscreenAlpha$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel = PrimaryBouncerToGoneTransitionViewModel.this;
                primaryBouncerToGoneTransitionViewModel.leaveShadeOpen = ((StatusBarStateControllerImpl) primaryBouncerToGoneTransitionViewModel.statusBarStateController).mLeaveOpenOnKeyguardHide;
                primaryBouncerToGoneTransitionViewModel.willRunDismissFromKeyguard = ((Boolean) primaryBouncerToGoneTransitionViewModel$lockscreenAlpha$2.invoke()).booleanValue();
                return Unit.INSTANCE;
            }
        }, null, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel$createLockscreenAlpha$3
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(0.0f);
            }
        }, null, null, 212);
        this.scrimAlpha = bouncerToGoneFlows.m830createScrimAlphaFlowKLykuaI(j, keyguardState, new BouncerToGoneFlows$scrimAlpha$2(0, bouncerToGoneFlows.primaryBouncerInteractor, PrimaryBouncerInteractor.class, "willRunDismissFromKeyguard", "willRunDismissFromKeyguard()Z", 0));
    }
}
