package com.android.systemui.keyguard.ui.binder;

import android.view.View;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInLayer;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSmartspaceViewModel;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardSmartspaceViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
    final /* synthetic */ KeyguardClockViewModel $clockViewModel;
    final /* synthetic */ ConstraintLayout $keyguardRootView;
    final /* synthetic */ KeyguardSmartspaceViewModel $smartspaceViewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardSmartspaceViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
        final /* synthetic */ KeyguardClockViewModel $clockViewModel;
        final /* synthetic */ ConstraintLayout $keyguardRootView;
        final /* synthetic */ KeyguardSmartspaceViewModel $smartspaceViewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardSmartspaceViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C01361 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
            final /* synthetic */ KeyguardClockViewModel $clockViewModel;
            final /* synthetic */ ConstraintLayout $keyguardRootView;
            final /* synthetic */ KeyguardSmartspaceViewModel $smartspaceViewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01361(ConstraintLayout constraintLayout, KeyguardBlueprintInteractor keyguardBlueprintInteractor, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, Continuation continuation) {
                super(2, continuation);
                this.$clockViewModel = keyguardClockViewModel;
                this.$keyguardRootView = constraintLayout;
                this.$smartspaceViewModel = keyguardSmartspaceViewModel;
                this.$blueprintInteractor = keyguardBlueprintInteractor;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                KeyguardClockViewModel keyguardClockViewModel = this.$clockViewModel;
                return new C01361(this.$keyguardRootView, this.$blueprintInteractor, keyguardClockViewModel, this.$smartspaceViewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((C01361) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                final KeyguardClockViewModel keyguardClockViewModel = this.$clockViewModel;
                ReadonlyStateFlow readonlyStateFlow = keyguardClockViewModel.hasCustomWeatherDataDisplay;
                final ConstraintLayout constraintLayout = this.$keyguardRootView;
                final KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = this.$smartspaceViewModel;
                final KeyguardBlueprintInteractor keyguardBlueprintInteractor = this.$blueprintInteractor;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardSmartspaceViewBinder.bind.1.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        ((Boolean) obj2).getClass();
                        KeyguardClockViewModel keyguardClockViewModel2 = keyguardClockViewModel;
                        boolean booleanValue = ((Boolean) ((StateFlowImpl) keyguardClockViewModel2.hasCustomWeatherDataDisplay.$$delegate_0).getValue()).booleanValue();
                        ConstraintLayout constraintLayout2 = ConstraintLayout.this;
                        KeyguardSmartspaceViewModel keyguardSmartspaceViewModel2 = keyguardSmartspaceViewModel;
                        if (booleanValue) {
                            AodBurnInLayer aodBurnInLayer = (AodBurnInLayer) constraintLayout2.requireViewById(R.id.burn_in_layer);
                            if (keyguardSmartspaceViewModel2.isSmartspaceEnabled && keyguardSmartspaceViewModel2.isDateWeatherDecoupled) {
                                aodBurnInLayer.removeView(constraintLayout2.requireViewById(R.id.date_smartspace_view));
                            }
                        } else {
                            AodBurnInLayer aodBurnInLayer2 = (AodBurnInLayer) constraintLayout2.requireViewById(R.id.burn_in_layer);
                            if (keyguardSmartspaceViewModel2.isSmartspaceEnabled && keyguardSmartspaceViewModel2.isDateWeatherDecoupled) {
                                aodBurnInLayer2.addView(constraintLayout2.requireViewById(R.id.date_smartspace_view));
                            }
                        }
                        AodBurnInLayer aodBurnInLayer3 = keyguardClockViewModel2.burnInLayer;
                        if (aodBurnInLayer3 != null) {
                            aodBurnInLayer3.updatePostLayout();
                        }
                        keyguardBlueprintInteractor.refreshBlueprint(new IntraBlueprintTransition.Config(IntraBlueprintTransition.Type.SmartspaceVisibility, null, 8));
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardSmartspaceViewBinder$bind$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardBlueprintInteractor $blueprintInteractor;
            final /* synthetic */ KeyguardClockViewModel $clockViewModel;
            final /* synthetic */ ConstraintLayout $keyguardRootView;
            final /* synthetic */ KeyguardSmartspaceViewModel $smartspaceViewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(ConstraintLayout constraintLayout, KeyguardBlueprintInteractor keyguardBlueprintInteractor, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, Continuation continuation) {
                super(2, continuation);
                this.$smartspaceViewModel = keyguardSmartspaceViewModel;
                this.$keyguardRootView = constraintLayout;
                this.$clockViewModel = keyguardClockViewModel;
                this.$blueprintInteractor = keyguardBlueprintInteractor;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = this.$smartspaceViewModel;
                return new AnonymousClass2(this.$keyguardRootView, this.$blueprintInteractor, this.$clockViewModel, keyguardSmartspaceViewModel, continuation);
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
                ReadonlyStateFlow readonlyStateFlow = this.$smartspaceViewModel.bcSmartspaceVisibility;
                final ConstraintLayout constraintLayout = this.$keyguardRootView;
                final KeyguardClockViewModel keyguardClockViewModel = this.$clockViewModel;
                final KeyguardBlueprintInteractor keyguardBlueprintInteractor = this.$blueprintInteractor;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardSmartspaceViewBinder.bind.1.1.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        ((Number) obj2).intValue();
                        ConstraintLayout constraintLayout2 = ConstraintLayout.this;
                        AodBurnInLayer aodBurnInLayer = (AodBurnInLayer) constraintLayout2.requireViewById(R.id.burn_in_layer);
                        View requireViewById = constraintLayout2.requireViewById(R.id.bc_smartspace_view);
                        if (requireViewById.getVisibility() == 0) {
                            aodBurnInLayer.addView(requireViewById);
                        } else {
                            aodBurnInLayer.removeView(requireViewById);
                        }
                        AodBurnInLayer aodBurnInLayer2 = keyguardClockViewModel.burnInLayer;
                        if (aodBurnInLayer2 != null) {
                            aodBurnInLayer2.updatePostLayout();
                        }
                        keyguardBlueprintInteractor.refreshBlueprint(new IntraBlueprintTransition.Config(IntraBlueprintTransition.Type.SmartspaceVisibility, null, 8));
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ConstraintLayout constraintLayout, KeyguardBlueprintInteractor keyguardBlueprintInteractor, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, Continuation continuation) {
            super(2, continuation);
            this.$clockViewModel = keyguardClockViewModel;
            this.$keyguardRootView = constraintLayout;
            this.$smartspaceViewModel = keyguardSmartspaceViewModel;
            this.$blueprintInteractor = keyguardBlueprintInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            KeyguardClockViewModel keyguardClockViewModel = this.$clockViewModel;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$keyguardRootView, this.$blueprintInteractor, keyguardClockViewModel, this.$smartspaceViewModel, continuation);
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
            KeyguardClockViewModel keyguardClockViewModel = this.$clockViewModel;
            CoroutineTracingKt.launch$default(coroutineScope, null, new C01361(this.$keyguardRootView, this.$blueprintInteractor, keyguardClockViewModel, this.$smartspaceViewModel, null), 6);
            KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = this.$smartspaceViewModel;
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass2(this.$keyguardRootView, this.$blueprintInteractor, this.$clockViewModel, keyguardSmartspaceViewModel, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardSmartspaceViewBinder$bind$1(ConstraintLayout constraintLayout, KeyguardBlueprintInteractor keyguardBlueprintInteractor, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, Continuation continuation) {
        super(3, continuation);
        this.$clockViewModel = keyguardClockViewModel;
        this.$keyguardRootView = constraintLayout;
        this.$smartspaceViewModel = keyguardSmartspaceViewModel;
        this.$blueprintInteractor = keyguardBlueprintInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardClockViewModel keyguardClockViewModel = this.$clockViewModel;
        ConstraintLayout constraintLayout = this.$keyguardRootView;
        KeyguardSmartspaceViewModel keyguardSmartspaceViewModel = this.$smartspaceViewModel;
        KeyguardSmartspaceViewBinder$bind$1 keyguardSmartspaceViewBinder$bind$1 = new KeyguardSmartspaceViewBinder$bind$1(constraintLayout, this.$blueprintInteractor, keyguardClockViewModel, keyguardSmartspaceViewModel, (Continuation) obj3);
        keyguardSmartspaceViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return keyguardSmartspaceViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            KeyguardClockViewModel keyguardClockViewModel = this.$clockViewModel;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$keyguardRootView, this.$blueprintInteractor, keyguardClockViewModel, this.$smartspaceViewModel, null);
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
