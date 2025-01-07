package com.android.systemui.keyguard.ui.binder;

import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.deviceentry.ui.binder.UdfpsAccessibilityOverlayBinder;
import com.android.systemui.deviceentry.ui.view.UdfpsAccessibilityOverlay;
import com.android.systemui.deviceentry.ui.viewmodel.UdfpsAccessibilityOverlayViewModel;
import com.android.systemui.keyguard.ui.SwipeUpAnywhereGestureHandler;
import com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$start$1;
import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerDependencies;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerUdfpsIconViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerViewModel$special$$inlined$map$1;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.log.LongPressHandlingViewLogger;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.scrim.ScrimView$$ExternalSyntheticLambda4;
import com.android.systemui.statusbar.gesture.TapGestureDetector;
import com.android.wm.shell.R;
import dagger.Lazy;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AlternateBouncerViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ AlternateBouncerDependencies $alternateBouncerDependencies;
    final /* synthetic */ ScrimView $scrim;
    final /* synthetic */ SwipeUpAnywhereGestureHandler $swipeUpAnywhereGestureHandler;
    final /* synthetic */ TapGestureDetector $tapGestureDetector;
    final /* synthetic */ AlternateBouncerViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ AlternateBouncerDependencies $alternateBouncerDependencies;
        final /* synthetic */ ScrimView $scrim;
        final /* synthetic */ SwipeUpAnywhereGestureHandler $swipeUpAnywhereGestureHandler;
        final /* synthetic */ TapGestureDetector $tapGestureDetector;
        final /* synthetic */ AlternateBouncerViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C00931 extends SuspendLambda implements Function2 {
            final /* synthetic */ AlternateBouncerDependencies $alternateBouncerDependencies;
            final /* synthetic */ SwipeUpAnywhereGestureHandler $swipeUpAnywhereGestureHandler;
            final /* synthetic */ TapGestureDetector $tapGestureDetector;
            final /* synthetic */ AlternateBouncerViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$bind$1$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C00941 implements FlowCollector {
                public final /* synthetic */ Object $alternateBouncerDependencies;
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ Object $swipeUpAnywhereGestureHandler;
                public final /* synthetic */ Object $tapGestureDetector;
                public final /* synthetic */ Object $viewModel;

                public /* synthetic */ C00941(Object obj, Object obj2, Object obj3, Object obj4, int i) {
                    this.$r8$classId = i;
                    this.$swipeUpAnywhereGestureHandler = obj;
                    this.$tapGestureDetector = obj2;
                    this.$alternateBouncerDependencies = obj3;
                    this.$viewModel = obj4;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit = Unit.INSTANCE;
                    Object obj2 = this.$viewModel;
                    Object obj3 = this.$alternateBouncerDependencies;
                    Object obj4 = this.$tapGestureDetector;
                    Object obj5 = this.$swipeUpAnywhereGestureHandler;
                    switch (this.$r8$classId) {
                        case 0:
                            TapGestureDetector tapGestureDetector = (TapGestureDetector) obj4;
                            SwipeUpAnywhereGestureHandler swipeUpAnywhereGestureHandler = (SwipeUpAnywhereGestureHandler) obj5;
                            if (!((Boolean) obj).booleanValue()) {
                                swipeUpAnywhereGestureHandler.removeOnGestureDetectedCallback("AlternateBouncer-SWIPE");
                                tapGestureDetector.removeOnGestureDetectedCallback("AlternateBouncer-TAP");
                                break;
                            } else {
                                final AlternateBouncerDependencies alternateBouncerDependencies = (AlternateBouncerDependencies) obj3;
                                final AlternateBouncerViewModel alternateBouncerViewModel = (AlternateBouncerViewModel) obj2;
                                swipeUpAnywhereGestureHandler.addOnGestureDetectedCallback("AlternateBouncer-SWIPE", new Function1() { // from class: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder.bind.1.1.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj6) {
                                        PowerInteractor.onUserTouch$default(AlternateBouncerDependencies.this.powerInteractor);
                                        alternateBouncerViewModel.statusBarKeyguardViewManager.showPrimaryBouncer(true);
                                        return Unit.INSTANCE;
                                    }
                                });
                                tapGestureDetector.addOnGestureDetectedCallback("AlternateBouncer-TAP", new Function1() { // from class: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder.bind.1.1.1.1.2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj6) {
                                        PowerInteractor.onUserTouch$default(AlternateBouncerDependencies.this.powerInteractor);
                                        alternateBouncerViewModel.statusBarKeyguardViewManager.showPrimaryBouncer(true);
                                        return Unit.INSTANCE;
                                    }
                                });
                                break;
                            }
                        default:
                            AlternateBouncerUdfpsIconViewModel.IconLocation iconLocation = (AlternateBouncerUdfpsIconViewModel.IconLocation) obj;
                            ConstraintLayout constraintLayout = (ConstraintLayout) obj5;
                            if (constraintLayout.getViewById(R.id.alternate_bouncer_udfps_accessibility_overlay) == null) {
                                UdfpsAccessibilityOverlay udfpsAccessibilityOverlay = new UdfpsAccessibilityOverlay(constraintLayout.getContext());
                                udfpsAccessibilityOverlay.setId(R.id.alternate_bouncer_udfps_accessibility_overlay);
                                constraintLayout.addView(udfpsAccessibilityOverlay);
                                UdfpsAccessibilityOverlayBinder.bind(udfpsAccessibilityOverlay, (UdfpsAccessibilityOverlayViewModel) ((Lazy) obj4).get());
                            }
                            if (constraintLayout.getViewById(R.id.alternate_bouncer_udfps_icon_view) == null) {
                                DeviceEntryIconView deviceEntryIconView = new DeviceEntryIconView(constraintLayout.getContext(), (LongPressHandlingViewLogger) obj3);
                                deviceEntryIconView.setId(R.id.alternate_bouncer_udfps_icon_view);
                                deviceEntryIconView.setContentDescription(deviceEntryIconView.getContext().getResources().getString(R.string.accessibility_fingerprint_label));
                                constraintLayout.addView(deviceEntryIconView);
                                AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel = (AlternateBouncerUdfpsIconViewModel) obj2;
                                ImageView imageView = deviceEntryIconView.iconView;
                                ImageView imageView2 = deviceEntryIconView.bgView;
                                RepeatWhenAttachedKt.repeatWhenAttached(deviceEntryIconView, EmptyCoroutineContext.INSTANCE, new AlternateBouncerUdfpsViewBinder$bind$1(deviceEntryIconView, alternateBouncerUdfpsIconViewModel, null));
                                RepeatWhenAttachedKt.repeatWhenAttached(imageView, EmptyCoroutineContext.INSTANCE, new AlternateBouncerUdfpsViewBinder$bind$2(alternateBouncerUdfpsIconViewModel, imageView, deviceEntryIconView, null));
                                imageView2.setVisibility(0);
                                RepeatWhenAttachedKt.repeatWhenAttached(imageView2, EmptyCoroutineContext.INSTANCE, new AlternateBouncerUdfpsViewBinder$bind$3(alternateBouncerUdfpsIconViewModel, imageView2, null));
                            }
                            ConstraintSet constraintSet = new ConstraintSet();
                            constraintSet.clone(constraintLayout);
                            constraintSet.constrainWidth(R.id.alternate_bouncer_udfps_icon_view, iconLocation.width);
                            constraintSet.constrainHeight(R.id.alternate_bouncer_udfps_icon_view, iconLocation.height);
                            constraintSet.connect(R.id.alternate_bouncer_udfps_icon_view, 3, 0, 3, iconLocation.top);
                            constraintSet.connect(R.id.alternate_bouncer_udfps_icon_view, 6, 0, 6, iconLocation.left);
                            constraintSet.constrainWidth(R.id.alternate_bouncer_udfps_accessibility_overlay, -1);
                            constraintSet.constrainHeight(R.id.alternate_bouncer_udfps_accessibility_overlay, -1);
                            constraintSet.applyTo(constraintLayout);
                            break;
                    }
                    return unit;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00931(AlternateBouncerViewModel alternateBouncerViewModel, SwipeUpAnywhereGestureHandler swipeUpAnywhereGestureHandler, TapGestureDetector tapGestureDetector, AlternateBouncerDependencies alternateBouncerDependencies, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = alternateBouncerViewModel;
                this.$swipeUpAnywhereGestureHandler = swipeUpAnywhereGestureHandler;
                this.$tapGestureDetector = tapGestureDetector;
                this.$alternateBouncerDependencies = alternateBouncerDependencies;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00931(this.$viewModel, this.$swipeUpAnywhereGestureHandler, this.$tapGestureDetector, this.$alternateBouncerDependencies, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00931) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AlternateBouncerViewModel alternateBouncerViewModel = this.$viewModel;
                    Flow flow = alternateBouncerViewModel.registerForDismissGestures;
                    C00941 c00941 = new C00941(this.$swipeUpAnywhereGestureHandler, this.$tapGestureDetector, this.$alternateBouncerDependencies, alternateBouncerViewModel, 0);
                    this.label = 1;
                    if (flow.collect(c00941, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$bind$1$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ ScrimView $scrim;
            final /* synthetic */ AlternateBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(AlternateBouncerViewModel alternateBouncerViewModel, ScrimView scrimView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = alternateBouncerViewModel;
                this.$scrim = scrimView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$viewModel, this.$scrim, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AlternateBouncerViewModel$special$$inlined$map$1 alternateBouncerViewModel$special$$inlined$map$1 = this.$viewModel.scrimAlpha;
                    AlternateBouncerViewBinder$start$1.AnonymousClass1 anonymousClass1 = new AlternateBouncerViewBinder$start$1.AnonymousClass1(1, this.$scrim);
                    this.label = 1;
                    if (alternateBouncerViewModel$special$$inlined$map$1.collect(anonymousClass1, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$bind$1$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ ScrimView $scrim;
            final /* synthetic */ AlternateBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(AlternateBouncerViewModel alternateBouncerViewModel, ScrimView scrimView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = alternateBouncerViewModel;
                this.$scrim = scrimView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$viewModel, this.$scrim, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = this.$viewModel.scrimColor;
                    ScrimView scrimView = this.$scrim;
                    this.label = 1;
                    int intValue = ((Number) flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.$value$inlined).intValue();
                    scrimView.getClass();
                    scrimView.executeOnExecutor(new ScrimView$$ExternalSyntheticLambda4(scrimView, intValue));
                    if (unit == coroutineSingletons) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AlternateBouncerViewModel alternateBouncerViewModel, SwipeUpAnywhereGestureHandler swipeUpAnywhereGestureHandler, TapGestureDetector tapGestureDetector, AlternateBouncerDependencies alternateBouncerDependencies, ScrimView scrimView, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = alternateBouncerViewModel;
            this.$swipeUpAnywhereGestureHandler = swipeUpAnywhereGestureHandler;
            this.$tapGestureDetector = tapGestureDetector;
            this.$alternateBouncerDependencies = alternateBouncerDependencies;
            this.$scrim = scrimView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$swipeUpAnywhereGestureHandler, this.$tapGestureDetector, this.$alternateBouncerDependencies, this.$scrim, continuation);
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
            StandaloneCoroutine launch$default = CoroutineTracingKt.launch$default(coroutineScope, null, new C00931(this.$viewModel, this.$swipeUpAnywhereGestureHandler, this.$tapGestureDetector, this.$alternateBouncerDependencies, null), 6);
            final SwipeUpAnywhereGestureHandler swipeUpAnywhereGestureHandler = this.$swipeUpAnywhereGestureHandler;
            final TapGestureDetector tapGestureDetector = this.$tapGestureDetector;
            launch$default.invokeOnCompletion(new Function1() { // from class: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder.bind.1.1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    SwipeUpAnywhereGestureHandler.this.removeOnGestureDetectedCallback("AlternateBouncer-SWIPE");
                    tapGestureDetector.removeOnGestureDetectedCallback("AlternateBouncer-TAP");
                    return Unit.INSTANCE;
                }
            });
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass3(this.$viewModel, this.$scrim, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass4(this.$viewModel, this.$scrim, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlternateBouncerViewBinder$bind$1(AlternateBouncerViewModel alternateBouncerViewModel, SwipeUpAnywhereGestureHandler swipeUpAnywhereGestureHandler, TapGestureDetector tapGestureDetector, AlternateBouncerDependencies alternateBouncerDependencies, ScrimView scrimView, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = alternateBouncerViewModel;
        this.$swipeUpAnywhereGestureHandler = swipeUpAnywhereGestureHandler;
        this.$tapGestureDetector = tapGestureDetector;
        this.$alternateBouncerDependencies = alternateBouncerDependencies;
        this.$scrim = scrimView;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AlternateBouncerViewBinder$bind$1 alternateBouncerViewBinder$bind$1 = new AlternateBouncerViewBinder$bind$1(this.$viewModel, this.$swipeUpAnywhereGestureHandler, this.$tapGestureDetector, this.$alternateBouncerDependencies, this.$scrim, (Continuation) obj3);
        alternateBouncerViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return alternateBouncerViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$swipeUpAnywhereGestureHandler, this.$tapGestureDetector, this.$alternateBouncerDependencies, this.$scrim, null);
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
