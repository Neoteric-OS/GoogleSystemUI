package com.android.systemui.keyguard.ui.binder;

import android.transition.TransitionManager;
import android.view.View;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.shared.model.BurnInModel;
import com.android.systemui.keyguard.shared.model.ClockSize;
import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInLayer;
import com.android.systemui.keyguard.ui.view.layout.sections.ClockSection;
import com.android.systemui.keyguard.ui.viewmodel.AodBurnInViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.plugins.clocks.AodClockBurnInModel;
import com.android.systemui.plugins.clocks.ClockController;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardClockViewBinder$bind$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ AodBurnInViewModel $aodBurnInViewModel;
    final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
    final /* synthetic */ ClockSection $clockSection;
    final /* synthetic */ ConstraintLayout $keyguardRootView;
    final /* synthetic */ KeyguardRootViewModel $rootViewModel;
    final /* synthetic */ KeyguardClockViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ AodBurnInViewModel $aodBurnInViewModel;
        final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
        final /* synthetic */ ClockSection $clockSection;
        final /* synthetic */ ConstraintLayout $keyguardRootView;
        final /* synthetic */ KeyguardRootViewModel $rootViewModel;
        final /* synthetic */ KeyguardClockViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$1, reason: invalid class name and collision with other inner class name */
        final class C01111 extends SuspendLambda implements Function2 {
            final /* synthetic */ ClockSection $clockSection;
            final /* synthetic */ ConstraintLayout $keyguardRootView;
            final /* synthetic */ KeyguardClockViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C01121 implements FlowCollector {
                public final /* synthetic */ Object $clockSection;
                public final /* synthetic */ ConstraintLayout $keyguardRootView;
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ KeyguardClockViewModel $viewModel;

                public /* synthetic */ C01121(ConstraintLayout constraintLayout, KeyguardClockViewModel keyguardClockViewModel, Object obj, int i) {
                    this.$r8$classId = i;
                    this.$keyguardRootView = constraintLayout;
                    this.$viewModel = keyguardClockViewModel;
                    this.$clockSection = obj;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit = Unit.INSTANCE;
                    Object obj2 = this.$clockSection;
                    KeyguardClockViewModel keyguardClockViewModel = this.$viewModel;
                    ConstraintLayout constraintLayout = this.$keyguardRootView;
                    switch (this.$r8$classId) {
                        case 0:
                            ClockController clockController = (ClockController) obj;
                            KeyguardClockViewBinder keyguardClockViewBinder = KeyguardClockViewBinder.INSTANCE;
                            AodBurnInLayer aodBurnInLayer = keyguardClockViewModel.burnInLayer;
                            if (!Intrinsics.areEqual(KeyguardClockViewBinder.lastClock, clockController)) {
                                ClockController clockController2 = KeyguardClockViewBinder.lastClock;
                                if (clockController2 != null) {
                                    for (View view : clockController2.getSmallClock().getLayout().getViews()) {
                                        if (aodBurnInLayer != null) {
                                            aodBurnInLayer.removeView(view);
                                        }
                                        constraintLayout.removeView(view);
                                    }
                                    Iterator it = clockController2.getLargeClock().getLayout().getViews().iterator();
                                    while (it.hasNext()) {
                                        constraintLayout.removeView((View) it.next());
                                    }
                                }
                                KeyguardClockViewBinder.lastClock = clockController;
                            }
                            KeyguardClockViewBinder keyguardClockViewBinder2 = KeyguardClockViewBinder.INSTANCE;
                            keyguardClockViewBinder2.addClockViews(clockController, constraintLayout);
                            keyguardClockViewBinder2.updateBurnInLayer(constraintLayout, keyguardClockViewModel, (ClockSize) ((StateFlowImpl) keyguardClockViewModel.clockSize.$$delegate_0).getValue());
                            ConstraintSet constraintSet = new ConstraintSet();
                            constraintSet.clone(constraintLayout);
                            ((ClockSection) obj2).applyConstraints(constraintSet);
                            TransitionManager.beginDelayedTransition(constraintLayout);
                            constraintSet.applyTo(constraintLayout);
                            break;
                        default:
                            KeyguardClockViewBinder.INSTANCE.updateBurnInLayer(constraintLayout, keyguardClockViewModel, (ClockSize) obj);
                            ((KeyguardBlueprintInteractor) obj2).refreshBlueprint(IntraBlueprintTransition.Type.ClockSize);
                            break;
                    }
                    return unit;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01111(KeyguardClockViewModel keyguardClockViewModel, ConstraintLayout constraintLayout, ClockSection clockSection, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardClockViewModel;
                this.$keyguardRootView = constraintLayout;
                this.$clockSection = clockSection;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01111(this.$viewModel, this.$keyguardRootView, this.$clockSection, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((C01111) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
                }
                ResultKt.throwOnFailure(obj);
                KeyguardClockViewModel keyguardClockViewModel = this.$viewModel;
                ReadonlyStateFlow readonlyStateFlow = keyguardClockViewModel.currentClock;
                C01121 c01121 = new C01121(this.$keyguardRootView, keyguardClockViewModel, this.$clockSection, 0);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c01121, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
            final /* synthetic */ ConstraintLayout $keyguardRootView;
            final /* synthetic */ KeyguardClockViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(KeyguardClockViewModel keyguardClockViewModel, ConstraintLayout constraintLayout, KeyguardBlueprintInteractor keyguardBlueprintInteractor, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardClockViewModel;
                this.$keyguardRootView = constraintLayout;
                this.$blueprintInteractor = keyguardBlueprintInteractor;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.$keyguardRootView, this.$blueprintInteractor, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
                }
                ResultKt.throwOnFailure(obj);
                KeyguardClockViewModel keyguardClockViewModel = this.$viewModel;
                ReadonlyStateFlow readonlyStateFlow = keyguardClockViewModel.clockSize;
                C01111.C01121 c01121 = new C01111.C01121(this.$keyguardRootView, keyguardClockViewModel, this.$blueprintInteractor, 1);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c01121, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
            final /* synthetic */ KeyguardClockViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$3$1, reason: invalid class name and collision with other inner class name */
            public final class C01131 implements FlowCollector {
                public final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ KeyguardClockViewModel $viewModel;

                public /* synthetic */ C01131(KeyguardClockViewModel keyguardClockViewModel, KeyguardBlueprintInteractor keyguardBlueprintInteractor, int i) {
                    this.$r8$classId = i;
                    this.$viewModel = keyguardClockViewModel;
                    this.$blueprintInteractor = keyguardBlueprintInteractor;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ((Boolean) obj).getClass();
                            ClockController clockController = (ClockController) ((StateFlowImpl) this.$viewModel.currentClock.$$delegate_0).getValue();
                            if (clockController != null) {
                                boolean hasCustomPositionUpdatedAnimation = clockController.getLargeClock().getConfig().getHasCustomPositionUpdatedAnimation();
                                KeyguardBlueprintInteractor keyguardBlueprintInteractor = this.$blueprintInteractor;
                                if (!hasCustomPositionUpdatedAnimation || clockController.getConfig().getUseCustomClockScene()) {
                                    keyguardBlueprintInteractor.refreshBlueprint(IntraBlueprintTransition.Type.DefaultTransition);
                                } else {
                                    keyguardBlueprintInteractor.refreshBlueprint(IntraBlueprintTransition.Type.DefaultClockStepping);
                                }
                            }
                            break;
                        default:
                            ((Boolean) obj).getClass();
                            ClockController clockController2 = (ClockController) ((StateFlowImpl) this.$viewModel.currentClock.$$delegate_0).getValue();
                            if (clockController2 != null && clockController2.getConfig().getUseCustomClockScene()) {
                                this.$blueprintInteractor.refreshBlueprint(IntraBlueprintTransition.Type.DefaultTransition);
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(KeyguardClockViewModel keyguardClockViewModel, KeyguardBlueprintInteractor keyguardBlueprintInteractor, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardClockViewModel;
                this.$blueprintInteractor = keyguardBlueprintInteractor;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$viewModel, this.$blueprintInteractor, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
                }
                ResultKt.throwOnFailure(obj);
                KeyguardClockViewModel keyguardClockViewModel = this.$viewModel;
                ReadonlyStateFlow readonlyStateFlow = keyguardClockViewModel.clockShouldBeCentered;
                C01131 c01131 = new C01131(keyguardClockViewModel, this.$blueprintInteractor, 0);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c01131, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
            final /* synthetic */ KeyguardRootViewModel $rootViewModel;
            final /* synthetic */ KeyguardClockViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function3 {
                /* synthetic */ boolean Z$0;
                /* synthetic */ boolean Z$1;
                int label;

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    boolean booleanValue2 = ((Boolean) obj2).booleanValue();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(3, (Continuation) obj3);
                    anonymousClass2.Z$0 = booleanValue;
                    anonymousClass2.Z$1 = booleanValue2;
                    return anonymousClass2.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Boolean.valueOf(this.Z$0 && this.Z$1);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(KeyguardClockViewModel keyguardClockViewModel, KeyguardRootViewModel keyguardRootViewModel, KeyguardBlueprintInteractor keyguardBlueprintInteractor, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardClockViewModel;
                this.$rootViewModel = keyguardRootViewModel;
                this.$blueprintInteractor = keyguardBlueprintInteractor;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$viewModel, this.$rootViewModel, this.$blueprintInteractor, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ReadonlyStateFlow readonlyStateFlow = this.$viewModel.hasAodIcons;
                    final ReadonlyStateFlow readonlyStateFlow2 = this.$rootViewModel.isNotifIconContainerVisible;
                    Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, new Flow() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$invokeSuspend$$inlined$map$1

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$invokeSuspend$$inlined$map$1$2, reason: invalid class name */
                        public final class AnonymousClass2 implements FlowCollector {
                            public final /* synthetic */ FlowCollector $this_unsafeFlow;

                            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$invokeSuspend$$inlined$map$1$2$1, reason: invalid class name */
                            public final class AnonymousClass1 extends ContinuationImpl {
                                Object L$0;
                                int label;
                                /* synthetic */ Object result;

                                public AnonymousClass1(Continuation continuation) {
                                    super(continuation);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    this.result = obj;
                                    this.label |= Integer.MIN_VALUE;
                                    return AnonymousClass2.this.emit(null, this);
                                }
                            }

                            public AnonymousClass2(FlowCollector flowCollector) {
                                this.$this_unsafeFlow = flowCollector;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                            /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                                /*
                                    r4 = this;
                                    boolean r0 = r6 instanceof com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                    if (r0 == 0) goto L13
                                    r0 = r6
                                    com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$invokeSuspend$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                                    int r1 = r0.label
                                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                    r3 = r1 & r2
                                    if (r3 == 0) goto L13
                                    int r1 = r1 - r2
                                    r0.label = r1
                                    goto L18
                                L13:
                                    com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$invokeSuspend$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$invokeSuspend$$inlined$map$1$2$1
                                    r0.<init>(r6)
                                L18:
                                    java.lang.Object r6 = r0.result
                                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                    int r2 = r0.label
                                    r3 = 1
                                    if (r2 == 0) goto L2f
                                    if (r2 != r3) goto L27
                                    kotlin.ResultKt.throwOnFailure(r6)
                                    goto L50
                                L27:
                                    java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                    java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                    r4.<init>(r5)
                                    throw r4
                                L2f:
                                    kotlin.ResultKt.throwOnFailure(r6)
                                    com.android.systemui.util.ui.AnimatedValue r5 = (com.android.systemui.util.ui.AnimatedValue) r5
                                    boolean r6 = r5 instanceof com.android.systemui.util.ui.AnimatedValue.Animating
                                    if (r6 == 0) goto L3d
                                    com.android.systemui.util.ui.AnimatedValue$Animating r5 = (com.android.systemui.util.ui.AnimatedValue.Animating) r5
                                    java.lang.Object r5 = r5.value
                                    goto L45
                                L3d:
                                    boolean r6 = r5 instanceof com.android.systemui.util.ui.AnimatedValue.NotAnimating
                                    if (r6 == 0) goto L53
                                    com.android.systemui.util.ui.AnimatedValue$NotAnimating r5 = (com.android.systemui.util.ui.AnimatedValue.NotAnimating) r5
                                    java.lang.Object r5 = r5.value
                                L45:
                                    r0.label = r3
                                    kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                                    java.lang.Object r4 = r4.emit(r5, r0)
                                    if (r4 != r1) goto L50
                                    return r1
                                L50:
                                    kotlin.Unit r4 = kotlin.Unit.INSTANCE
                                    return r4
                                L53:
                                    kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
                                    r4.<init>()
                                    throw r4
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$4$invokeSuspend$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                            }
                        }

                        @Override // kotlinx.coroutines.flow.Flow
                        public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                            ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                            return CoroutineSingletons.COROUTINE_SUSPENDED;
                        }
                    }, new AnonymousClass2(3, null)));
                    AnonymousClass3.C01131 c01131 = new AnonymousClass3.C01131(this.$viewModel, this.$blueprintInteractor, 1);
                    this.label = 1;
                    if (distinctUntilChanged.collect(c01131, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder$bind$2$1$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ AodBurnInViewModel $aodBurnInViewModel;
            final /* synthetic */ KeyguardClockViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(AodBurnInViewModel aodBurnInViewModel, KeyguardClockViewModel keyguardClockViewModel, Continuation continuation) {
                super(2, continuation);
                this.$aodBurnInViewModel = aodBurnInViewModel;
                this.$viewModel = keyguardClockViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$aodBurnInViewModel, this.$viewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
                }
                ResultKt.throwOnFailure(obj);
                ReadonlyStateFlow readonlyStateFlow = this.$aodBurnInViewModel.movement;
                final KeyguardClockViewModel keyguardClockViewModel = this.$viewModel;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardClockViewBinder.bind.2.1.5.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        BurnInModel burnInModel = (BurnInModel) obj2;
                        ClockController clockController = (ClockController) ((StateFlowImpl) KeyguardClockViewModel.this.currentClock.$$delegate_0).getValue();
                        if (clockController != null) {
                            clockController.getLargeClock().getLayout().applyAodBurnIn(new AodClockBurnInModel(burnInModel.scale, burnInModel.translationX, burnInModel.translationY));
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(KeyguardClockViewModel keyguardClockViewModel, ConstraintLayout constraintLayout, ClockSection clockSection, KeyguardBlueprintInteractor keyguardBlueprintInteractor, KeyguardRootViewModel keyguardRootViewModel, AodBurnInViewModel aodBurnInViewModel, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = keyguardClockViewModel;
            this.$keyguardRootView = constraintLayout;
            this.$clockSection = clockSection;
            this.$blueprintInteractor = keyguardBlueprintInteractor;
            this.$rootViewModel = keyguardRootViewModel;
            this.$aodBurnInViewModel = aodBurnInViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$keyguardRootView, this.$clockSection, this.$blueprintInteractor, this.$rootViewModel, this.$aodBurnInViewModel, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt.launch$default(coroutineScope, null, null, new C01111(this.$viewModel, this.$keyguardRootView, this.$clockSection, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$keyguardRootView, this.$blueprintInteractor, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.$blueprintInteractor, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$viewModel, this.$rootViewModel, this.$blueprintInteractor, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$aodBurnInViewModel, this.$viewModel, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardClockViewBinder$bind$2(KeyguardClockViewModel keyguardClockViewModel, ConstraintLayout constraintLayout, ClockSection clockSection, KeyguardBlueprintInteractor keyguardBlueprintInteractor, KeyguardRootViewModel keyguardRootViewModel, AodBurnInViewModel aodBurnInViewModel, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = keyguardClockViewModel;
        this.$keyguardRootView = constraintLayout;
        this.$clockSection = clockSection;
        this.$blueprintInteractor = keyguardBlueprintInteractor;
        this.$rootViewModel = keyguardRootViewModel;
        this.$aodBurnInViewModel = aodBurnInViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardClockViewBinder$bind$2 keyguardClockViewBinder$bind$2 = new KeyguardClockViewBinder$bind$2(this.$viewModel, this.$keyguardRootView, this.$clockSection, this.$blueprintInteractor, this.$rootViewModel, this.$aodBurnInViewModel, (Continuation) obj3);
        keyguardClockViewBinder$bind$2.L$0 = (LifecycleOwner) obj;
        return keyguardClockViewBinder$bind$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$keyguardRootView, this.$clockSection, this.$blueprintInteractor, this.$rootViewModel, this.$aodBurnInViewModel, null);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
