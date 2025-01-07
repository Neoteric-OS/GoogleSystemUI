package com.android.systemui.keyguard.ui.viewmodel;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow.FlowBuilder;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import dagger.Lazy;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerToGoneFlows {
    public final KeyguardTransitionAnimationFlow animationFlow;
    public final PrimaryBouncerInteractor primaryBouncerInteractor;
    public final ShadeInteractor shadeInteractor;
    public final SysuiStatusBarStateController statusBarStateController;

    public BouncerToGoneFlows(SysuiStatusBarStateController sysuiStatusBarStateController, PrimaryBouncerInteractor primaryBouncerInteractor, Lazy lazy, ShadeInteractor shadeInteractor, KeyguardTransitionAnimationFlow keyguardTransitionAnimationFlow) {
        this.statusBarStateController = sysuiStatusBarStateController;
        this.primaryBouncerInteractor = primaryBouncerInteractor;
        this.shadeInteractor = shadeInteractor;
        this.animationFlow = keyguardTransitionAnimationFlow;
    }

    /* renamed from: createScrimAlphaFlow-KLykuaI, reason: not valid java name */
    public final ChannelFlowTransformLatest m830createScrimAlphaFlowKLykuaI(long j, KeyguardState keyguardState, Function0 function0) {
        Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        Ref$BooleanRef ref$BooleanRef2 = new Ref$BooleanRef();
        Ref$BooleanRef ref$BooleanRef3 = new Ref$BooleanRef();
        if (keyguardState == KeyguardState.PRIMARY_BOUNCER) {
            Edge.Companion companion = Edge.Companion;
        } else {
            Edge.Companion companion2 = Edge.Companion;
            SceneKey sceneKey = Scenes.Bouncer;
        }
        KeyguardTransitionAnimationFlow keyguardTransitionAnimationFlow = this.animationFlow;
        keyguardTransitionAnimationFlow.getClass();
        return FlowKt.transformLatest(FlowKt.distinctUntilChanged(new BouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$map$1(((ShadeInteractorImpl) this.shadeInteractor).baseShadeInteractor.getAnyExpansion(), 0)), new BouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$flatMapLatest$1(null, keyguardTransitionAnimationFlow.new FlowBuilder(j, new Edge.StateToState(keyguardState, KeyguardState.GONE)), j, ref$BooleanRef2, this, ref$BooleanRef3, function0, ref$BooleanRef));
    }

    /* renamed from: showAllNotifications-VtjQ1oo, reason: not valid java name */
    public final Flow m831showAllNotificationsVtjQ1oo(long j, KeyguardState keyguardState) {
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        if (keyguardState == KeyguardState.PRIMARY_BOUNCER) {
            Edge.Companion companion = Edge.Companion;
        } else {
            Edge.Companion companion2 = Edge.Companion;
            SceneKey sceneKey = Scenes.Bouncer;
        }
        KeyguardTransitionAnimationFlow keyguardTransitionAnimationFlow = this.animationFlow;
        keyguardTransitionAnimationFlow.getClass();
        return FlowKt.distinctUntilChanged(new BouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$map$1(KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(keyguardTransitionAnimationFlow.new FlowBuilder(j, new Edge.StateToState(keyguardState, KeyguardState.GONE)), j, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.BouncerToGoneFlows$showAllNotifications$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Number) obj).floatValue();
                return Float.valueOf(Ref$BooleanRef.this.element ? 1.0f : 0.0f);
            }
        }, 0L, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.BouncerToGoneFlows$showAllNotifications$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Ref$BooleanRef.this.element = ((StatusBarStateControllerImpl) this.statusBarStateController).mLeaveOpenOnKeyguardHide;
                return Unit.INSTANCE;
            }
        }, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.BouncerToGoneFlows$showAllNotifications$3
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(0.0f);
            }
        }, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.BouncerToGoneFlows$showAllNotifications$4
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(0.0f);
            }
        }, null, null, 196), 1));
    }
}
