package com.android.systemui.biometrics.ui.binder;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.android.app.animation.Interpolators;
import com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$start$1;
import com.android.systemui.biometrics.ui.viewmodel.SideFpsOverlayViewModel;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SideFpsOverlayViewBinder$Companion$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ View $overlayView;
    final /* synthetic */ SideFpsOverlayViewModel $viewModel;
    final /* synthetic */ WindowManager $windowManager;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$Companion$bind$1$2, reason: invalid class name */
    public final class AnonymousClass2 extends View.AccessibilityDelegate {
        @Override // android.view.View.AccessibilityDelegate
        public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() == 32) {
                return true;
            }
            return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$Companion$bind$1$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ View $it;
        final /* synthetic */ LottieAnimationView $lottie;
        final /* synthetic */ SideFpsOverlayViewModel $viewModel;
        final /* synthetic */ WindowManager $windowManager;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$Companion$bind$1$3$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ LottieAnimationView $lottie;
            final /* synthetic */ SideFpsOverlayViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(SideFpsOverlayViewModel sideFpsOverlayViewModel, LottieAnimationView lottieAnimationView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sideFpsOverlayViewModel;
                this.$lottie = lottieAnimationView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.$viewModel, this.$lottie, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SafeFlow safeFlow = this.$viewModel.lottieCallbacks;
                    SideFpsOverlayViewBinder$start$1.AnonymousClass1 anonymousClass1 = new SideFpsOverlayViewBinder$start$1.AnonymousClass1(2, this.$lottie);
                    this.label = 1;
                    if (safeFlow.collect(anonymousClass1, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$Companion$bind$1$3$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $it;
            final /* synthetic */ LottieAnimationView $lottie;
            final /* synthetic */ SideFpsOverlayViewModel $viewModel;
            final /* synthetic */ WindowManager $windowManager;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(View view, WindowManager windowManager, LottieAnimationView lottieAnimationView, SideFpsOverlayViewModel sideFpsOverlayViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sideFpsOverlayViewModel;
                this.$windowManager = windowManager;
                this.$it = view;
                this.$lottie = lottieAnimationView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                SideFpsOverlayViewModel sideFpsOverlayViewModel = this.$viewModel;
                return new AnonymousClass2(this.$it, this.$windowManager, this.$lottie, sideFpsOverlayViewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = this.$viewModel.overlayViewParams;
                    final WindowManager windowManager = this.$windowManager;
                    final View view = this.$it;
                    final LottieAnimationView lottieAnimationView = this.$lottie;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder.Companion.bind.1.3.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            windowManager.updateViewLayout(view, (WindowManager.LayoutParams) obj2);
                            lottieAnimationView.resumeAnimation();
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1.collect(flowCollector, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$Companion$bind$1$3$3, reason: invalid class name and collision with other inner class name */
        final class C00453 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $it;
            final /* synthetic */ LottieAnimationView $lottie;
            final /* synthetic */ SideFpsOverlayViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00453(SideFpsOverlayViewModel sideFpsOverlayViewModel, View view, LottieAnimationView lottieAnimationView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sideFpsOverlayViewModel;
                this.$it = view;
                this.$lottie = lottieAnimationView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00453(this.$viewModel, this.$it, this.$lottie, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00453) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = this.$viewModel.overlayViewProperties;
                    final View view = this.$it;
                    final LottieAnimationView lottieAnimationView = this.$lottie;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder.Companion.bind.1.3.3.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            SideFpsOverlayViewModel.OverlayViewProperties overlayViewProperties = (SideFpsOverlayViewModel.OverlayViewProperties) obj2;
                            view.setRotation(overlayViewProperties.overlayViewRotation);
                            lottieAnimationView.setAnimation(overlayViewProperties.indicatorAsset);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(flowCollector, this) == coroutineSingletons) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(View view, WindowManager windowManager, LottieAnimationView lottieAnimationView, SideFpsOverlayViewModel sideFpsOverlayViewModel, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = sideFpsOverlayViewModel;
            this.$lottie = lottieAnimationView;
            this.$windowManager = windowManager;
            this.$it = view;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            SideFpsOverlayViewModel sideFpsOverlayViewModel = this.$viewModel;
            LottieAnimationView lottieAnimationView = this.$lottie;
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$it, this.$windowManager, lottieAnimationView, sideFpsOverlayViewModel, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass3 anonymousClass3 = (AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass3.invokeSuspend(unit);
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
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$viewModel, this.$lottie, null), 3);
            SideFpsOverlayViewModel sideFpsOverlayViewModel = this.$viewModel;
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$it, this.$windowManager, this.$lottie, sideFpsOverlayViewModel, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new C00453(this.$viewModel, this.$it, this.$lottie, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SideFpsOverlayViewBinder$Companion$bind$1(View view, SideFpsOverlayViewModel sideFpsOverlayViewModel, WindowManager windowManager, Continuation continuation) {
        super(3, continuation);
        this.$overlayView = view;
        this.$viewModel = sideFpsOverlayViewModel;
        this.$windowManager = windowManager;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SideFpsOverlayViewBinder$Companion$bind$1 sideFpsOverlayViewBinder$Companion$bind$1 = new SideFpsOverlayViewBinder$Companion$bind$1(this.$overlayView, this.$viewModel, this.$windowManager, (Continuation) obj3);
        sideFpsOverlayViewBinder$Companion$bind$1.L$0 = (LifecycleOwner) obj;
        sideFpsOverlayViewBinder$Companion$bind$1.L$1 = (View) obj2;
        return sideFpsOverlayViewBinder$Companion$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            View view = (View) this.L$1;
            LottieAnimationView lottieAnimationView = (LottieAnimationView) view.requireViewById(R.id.sidefps_animation);
            final View view2 = this.$overlayView;
            final SideFpsOverlayViewModel sideFpsOverlayViewModel = this.$viewModel;
            lottieAnimationView.addLottieOnCompositionLoadedListener(new LottieOnCompositionLoadedListener() { // from class: com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder$Companion$bind$1.1
                @Override // com.airbnb.lottie.LottieOnCompositionLoadedListener
                public final void onCompositionLoaded(LottieComposition lottieComposition) {
                    if (view2.getVisibility() != 0) {
                        Rect rect = lottieComposition.bounds;
                        StateFlowImpl stateFlowImpl = sideFpsOverlayViewModel._lottieBounds;
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, rect);
                        view2.setVisibility(0);
                    }
                }
            });
            view.setAlpha(0.0f);
            view.animate().alpha(1.0f).setDuration(650L).setInterpolator(Interpolators.ALPHA_IN).start();
            view.setAccessibilityDelegate(new AnonymousClass2());
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(view, this.$windowManager, lottieAnimationView, this.$viewModel, null);
            this.L$0 = null;
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass3, this) == coroutineSingletons) {
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
