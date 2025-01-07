package com.android.systemui.keyguard.ui.viewmodel;

import android.view.animation.Interpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: com.android.systemui.keyguard.ui.viewmodel.BouncerToGoneFlows$createScrimAlphaFlow-KLykuaI$$inlined$flatMapLatest$1, reason: invalid class name */
/* loaded from: classes.dex */
public final class BouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ long $duration$inlined;
    final /* synthetic */ Ref$BooleanRef $isShadeExpanded$inlined;
    final /* synthetic */ Ref$BooleanRef $leaveShadeOpen$inlined;
    final /* synthetic */ KeyguardTransitionAnimationFlow.FlowBuilder $transitionAnimation$inlined;
    final /* synthetic */ Function0 $willRunAnimationOnKeyguard$inlined;
    final /* synthetic */ Ref$BooleanRef $willRunDismissFromKeyguard$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ BouncerToGoneFlows this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$flatMapLatest$1(Continuation continuation, KeyguardTransitionAnimationFlow.FlowBuilder flowBuilder, long j, Ref$BooleanRef ref$BooleanRef, BouncerToGoneFlows bouncerToGoneFlows, Ref$BooleanRef ref$BooleanRef2, Function0 function0, Ref$BooleanRef ref$BooleanRef3) {
        super(3, continuation);
        this.$transitionAnimation$inlined = flowBuilder;
        this.$duration$inlined = j;
        this.$leaveShadeOpen$inlined = ref$BooleanRef;
        this.this$0 = bouncerToGoneFlows;
        this.$willRunDismissFromKeyguard$inlined = ref$BooleanRef2;
        this.$willRunAnimationOnKeyguard$inlined = function0;
        this.$isShadeExpanded$inlined = ref$BooleanRef3;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$flatMapLatest$1 bouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$flatMapLatest$1 = new BouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$flatMapLatest$1((Continuation) obj3, this.$transitionAnimation$inlined, this.$duration$inlined, this.$leaveShadeOpen$inlined, this.this$0, this.$willRunDismissFromKeyguard$inlined, this.$willRunAnimationOnKeyguard$inlined, this.$isShadeExpanded$inlined);
        bouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        bouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$flatMapLatest$1.L$1 = obj2;
        return bouncerToGoneFlows$createScrimAlphaFlowKLykuaI$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            final boolean booleanValue = ((Boolean) this.L$1).booleanValue();
            Interpolator interpolator = Interpolators.EMPHASIZED_ACCELERATE;
            KeyguardTransitionAnimationFlow.FlowBuilder flowBuilder = this.$transitionAnimation$inlined;
            long j = this.$duration$inlined;
            BouncerToGoneFlows$createScrimAlphaFlow$2$1 bouncerToGoneFlows$createScrimAlphaFlow$2$1 = new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.BouncerToGoneFlows$createScrimAlphaFlow$2$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Float.valueOf(1.0f - ((Number) obj2).floatValue());
                }
            };
            final Ref$BooleanRef ref$BooleanRef = this.$leaveShadeOpen$inlined;
            final BouncerToGoneFlows bouncerToGoneFlows = this.this$0;
            final Ref$BooleanRef ref$BooleanRef2 = this.$willRunDismissFromKeyguard$inlined;
            final Function0 function0 = this.$willRunAnimationOnKeyguard$inlined;
            final Ref$BooleanRef ref$BooleanRef3 = this.$isShadeExpanded$inlined;
            Function0 function02 = new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.BouncerToGoneFlows$createScrimAlphaFlow$2$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Ref$BooleanRef.this.element = ((StatusBarStateControllerImpl) bouncerToGoneFlows.statusBarStateController).mLeaveOpenOnKeyguardHide;
                    ref$BooleanRef2.element = ((Boolean) function0.invoke()).booleanValue();
                    ref$BooleanRef3.element = booleanValue;
                    return Unit.INSTANCE;
                }
            };
            Intrinsics.checkNotNull(interpolator);
            KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 m825sharedFlow74qcysc$default = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, j, bouncerToGoneFlows$createScrimAlphaFlow$2$1, 0L, function02, null, null, interpolator, null, 180);
            Ref$BooleanRef ref$BooleanRef4 = this.$willRunDismissFromKeyguard$inlined;
            Ref$BooleanRef ref$BooleanRef5 = this.$isShadeExpanded$inlined;
            Ref$BooleanRef ref$BooleanRef6 = this.$leaveShadeOpen$inlined;
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object collect = m825sharedFlow74qcysc$default.collect(new BouncerToGoneFlows$createScrimAlphaFlow_KLykuaI$lambda$4$$inlined$map$1$2(flowCollector, ref$BooleanRef4, ref$BooleanRef5, ref$BooleanRef6), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
