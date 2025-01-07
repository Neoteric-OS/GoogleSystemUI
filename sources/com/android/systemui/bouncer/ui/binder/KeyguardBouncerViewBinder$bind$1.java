package com.android.systemui.bouncer.ui.binder;

import android.content.res.ColorStateList;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.BouncerPanelExpansionCalculator;
import com.android.keyguard.KeyguardInputViewController;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityContainer;
import com.android.keyguard.KeyguardSecurityContainerController;
import com.android.keyguard.KeyguardSecurityContainerController$$ExternalSyntheticLambda2;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardSecurityViewFlipper;
import com.android.keyguard.KeyguardSecurityViewFlipperController;
import com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor$special$$inlined$filter$1;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor$special$$inlined$map$1;
import com.android.systemui.bouncer.shared.model.BouncerShowMessageModel;
import com.android.systemui.bouncer.ui.BouncerViewImpl;
import com.android.systemui.bouncer.ui.viewmodel.KeyguardBouncerViewModel;
import com.android.systemui.bouncer.ui.viewmodel.KeyguardBouncerViewModel$observeOnIsBackButtonEnabled$$inlined$map$1$2;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1;
import com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel;
import com.android.systemui.log.BouncerLogger;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.wm.shell.R;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBouncerViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ BouncerLogger $bouncerLogger;
    final /* synthetic */ BouncerMessageInteractor $bouncerMessageInteractor;
    final /* synthetic */ KeyguardBouncerViewBinder$bind$delegate$1 $delegate;
    final /* synthetic */ KeyguardMessageAreaController.Factory $messageAreaControllerFactory;
    final /* synthetic */ PrimaryBouncerToGoneTransitionViewModel $primaryBouncerToGoneTransitionViewModel;
    final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
    final /* synthetic */ SelectedUserInteractor $selectedUserInteractor;
    final /* synthetic */ ViewGroup $view;
    final /* synthetic */ KeyguardBouncerViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1, reason: invalid class name */
    public final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ BouncerLogger $bouncerLogger;
        final /* synthetic */ BouncerMessageInteractor $bouncerMessageInteractor;
        final /* synthetic */ KeyguardBouncerViewBinder$bind$delegate$1 $delegate;
        final /* synthetic */ KeyguardMessageAreaController.Factory $messageAreaControllerFactory;
        final /* synthetic */ PrimaryBouncerToGoneTransitionViewModel $primaryBouncerToGoneTransitionViewModel;
        final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
        final /* synthetic */ SelectedUserInteractor $selectedUserInteractor;
        final /* synthetic */ ViewGroup $view;
        final /* synthetic */ KeyguardBouncerViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        public final class C00511 extends SuspendLambda implements Function2 {
            final /* synthetic */ BouncerLogger $bouncerLogger;
            final /* synthetic */ BouncerMessageInteractor $bouncerMessageInteractor;
            final /* synthetic */ KeyguardMessageAreaController.Factory $messageAreaControllerFactory;
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00511(KeyguardBouncerViewModel keyguardBouncerViewModel, ViewGroup viewGroup, KeyguardSecurityContainerController keyguardSecurityContainerController, BouncerLogger bouncerLogger, BouncerMessageInteractor bouncerMessageInteractor, KeyguardMessageAreaController.Factory factory, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$view = viewGroup;
                this.$securityContainerController = keyguardSecurityContainerController;
                this.$bouncerLogger = bouncerLogger;
                this.$bouncerMessageInteractor = bouncerMessageInteractor;
                this.$messageAreaControllerFactory = factory;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00511(this.$viewModel, this.$view, this.$securityContainerController, this.$bouncerLogger, this.$bouncerMessageInteractor, this.$messageAreaControllerFactory, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00511) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.isShowing;
                final ViewGroup viewGroup = this.$view;
                final KeyguardSecurityContainerController keyguardSecurityContainerController = this.$securityContainerController;
                final BouncerLogger bouncerLogger = this.$bouncerLogger;
                final BouncerMessageInteractor bouncerMessageInteractor = this.$bouncerMessageInteractor;
                final KeyguardMessageAreaController.Factory factory = this.$messageAreaControllerFactory;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder.bind.1.1.1.1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                    public final class C00531 implements KeyguardSecurityViewFlipperController.OnViewInflatedCallback {
                        public final /* synthetic */ BouncerLogger $bouncerLogger;
                        public final /* synthetic */ BouncerMessageInteractor $bouncerMessageInteractor;
                        public final /* synthetic */ KeyguardMessageAreaController.Factory $messageAreaControllerFactory;
                        public final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;

                        public C00531(KeyguardSecurityContainerController keyguardSecurityContainerController, BouncerLogger bouncerLogger, BouncerMessageInteractor bouncerMessageInteractor, KeyguardMessageAreaController.Factory factory) {
                            this.$securityContainerController = keyguardSecurityContainerController;
                            this.$bouncerLogger = bouncerLogger;
                            this.$bouncerMessageInteractor = bouncerMessageInteractor;
                            this.$messageAreaControllerFactory = factory;
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:68:0x00cb, code lost:
                        
                            if ((r11 & 128) != 0) goto L35;
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:70:0x00d0, code lost:
                        
                            if (r2 == false) goto L35;
                         */
                        @Override // com.android.keyguard.KeyguardSecurityViewFlipperController.OnViewInflatedCallback
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final void onViewInflated(com.android.keyguard.KeyguardInputViewController r15) {
                            /*
                                Method dump skipped, instructions count: 312
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1.AnonymousClass1.C00511.C00521.C00531.onViewInflated(com.android.keyguard.KeyguardInputViewController):void");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        boolean booleanValue = ((Boolean) obj2).booleanValue();
                        viewGroup.setVisibility(booleanValue ? 0 : 4);
                        final KeyguardSecurityContainerController keyguardSecurityContainerController2 = keyguardSecurityContainerController;
                        if (booleanValue) {
                            View findViewById = ((KeyguardSecurityContainer) keyguardSecurityContainerController2.mView).findViewById(R.id.keyguard_bouncer_user_switcher);
                            if (findViewById != null) {
                                findViewById.setAlpha(0.0f);
                            }
                            final C00531 c00531 = new C00531(keyguardSecurityContainerController2, bouncerLogger, bouncerMessageInteractor, factory);
                            KeyguardSecurityViewFlipperController keyguardSecurityViewFlipperController = keyguardSecurityContainerController2.mSecurityViewFlipperController;
                            ((KeyguardSecurityViewFlipper) keyguardSecurityViewFlipperController.mView).removeAllViews();
                            keyguardSecurityViewFlipperController.mChildren.clear();
                            keyguardSecurityViewFlipperController.asynchronouslyInflateView(keyguardSecurityContainerController2.mCurrentSecurityMode, keyguardSecurityContainerController2.mKeyguardSecurityCallback, new KeyguardSecurityViewFlipperController.OnViewInflatedCallback() { // from class: com.android.keyguard.KeyguardSecurityContainerController$$ExternalSyntheticLambda0
                                @Override // com.android.keyguard.KeyguardSecurityViewFlipperController.OnViewInflatedCallback
                                public final void onViewInflated(KeyguardInputViewController keyguardInputViewController) {
                                    GlobalSettings globalSettings;
                                    FalsingManager falsingManager;
                                    UserSwitcherController userSwitcherController;
                                    KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) KeyguardSecurityContainerController.this.mView;
                                    KeyguardSecurityViewFlipper keyguardSecurityViewFlipper = (KeyguardSecurityViewFlipper) keyguardSecurityContainer.findViewById(R.id.view_flipper);
                                    keyguardSecurityContainer.mSecurityViewFlipper = keyguardSecurityViewFlipper;
                                    if (keyguardSecurityViewFlipper != null && (globalSettings = keyguardSecurityContainer.mGlobalSettings) != null && (falsingManager = keyguardSecurityContainer.mFalsingManager) != null && (userSwitcherController = keyguardSecurityContainer.mUserSwitcherController) != null) {
                                        keyguardSecurityContainer.mViewMode.init(keyguardSecurityContainer, globalSettings, keyguardSecurityViewFlipper, falsingManager, userSwitcherController, keyguardSecurityContainer.mFalsingA11yDelegate);
                                    }
                                    c00531.onViewInflated(keyguardInputViewController);
                                }
                            });
                        } else {
                            KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) keyguardSecurityContainerController2.mView;
                            keyguardSecurityContainer.setScaleX(1.0f);
                            keyguardSecurityContainer.setScaleY(1.0f);
                            Runnable runnable = keyguardSecurityContainerController2.mCancelAction;
                            if (runnable != null) {
                                runnable.run();
                            }
                            keyguardSecurityContainerController2.mDismissAction = null;
                            keyguardSecurityContainerController2.mCancelAction = null;
                            KeyguardSecurityContainer keyguardSecurityContainer2 = (KeyguardSecurityContainer) keyguardSecurityContainerController2.mView;
                            keyguardSecurityContainer2.mViewMode.reset();
                            keyguardSecurityContainer2.mDisappearAnimRunning = false;
                            Iterator it = keyguardSecurityContainerController2.mSecurityViewFlipperController.mChildren.iterator();
                            while (it.hasNext()) {
                                ((KeyguardInputViewController) it.next()).reset$1();
                            }
                            keyguardSecurityContainerController2.onPause();
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$10, reason: invalid class name */
        final class AnonymousClass10 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass10(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass10(this.$viewModel, this.$securityContainerController, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass10) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    KeyguardBouncerViewModel keyguardBouncerViewModel = this.$viewModel;
                    FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = keyguardBouncerViewModel.bouncerShowMessage;
                    AnonymousClass9.C00571 c00571 = new AnonymousClass9.C00571(this.$securityContainerController, keyguardBouncerViewModel, 1);
                    this.label = 1;
                    if (flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.collect(c00571, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$11, reason: invalid class name */
        final class AnonymousClass11 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ SelectedUserInteractor $selectedUserInteractor;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass11(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, SelectedUserInteractor selectedUserInteractor, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
                this.$selectedUserInteractor = selectedUserInteractor;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass11(this.$viewModel, this.$securityContainerController, this.$selectedUserInteractor, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass11) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final KeyguardBouncerViewModel keyguardBouncerViewModel = this.$viewModel;
                    FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = keyguardBouncerViewModel.keyguardAuthenticated;
                    final KeyguardSecurityContainerController keyguardSecurityContainerController = this.$securityContainerController;
                    final SelectedUserInteractor selectedUserInteractor = this.$selectedUserInteractor;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder.bind.1.1.11.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            ((Boolean) obj2).getClass();
                            KeyguardSecurityContainerController.this.mKeyguardSecurityCallback.finish(selectedUserInteractor.getSelectedUserId());
                            keyguardBouncerViewModel.interactor.repository._keyguardAuthenticatedBiometrics.setValue(null);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.collect(flowCollector, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$12, reason: invalid class name */
        final class AnonymousClass12 extends SuspendLambda implements Function2 {
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass12(KeyguardBouncerViewModel keyguardBouncerViewModel, ViewGroup viewGroup, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass12(this.$viewModel, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass12) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    KeyguardBouncerViewModel keyguardBouncerViewModel = this.$viewModel;
                    final ViewGroup viewGroup = this.$view;
                    Function0 function0 = new Function0() { // from class: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder.bind.1.1.12.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Integer.valueOf(viewGroup.getSystemUiVisibility());
                        }
                    };
                    FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = keyguardBouncerViewModel.interactor.isBackButtonEnabled;
                    AnonymousClass2.C00561 c00561 = new AnonymousClass2.C00561(6, viewGroup);
                    this.label = 1;
                    Object collect = flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.collect(new KeyguardBouncerViewModel$observeOnIsBackButtonEnabled$$inlined$map$1$2(c00561, function0), this);
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

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$2$1, reason: invalid class name and collision with other inner class name */
            public final class C00561 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ Object $securityContainerController;

                public /* synthetic */ C00561(int i, Object obj) {
                    this.$r8$classId = i;
                    this.$securityContainerController = obj;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ((KeyguardSecurityContainerController) this.$securityContainerController).onStartingToHide();
                            break;
                        case 1:
                            ((KeyguardSecurityContainerController) this.$securityContainerController).startDisappearAnimation((Runnable) obj);
                            break;
                        case 2:
                            float floatValue = ((Number) obj).floatValue();
                            KeyguardSecurityContainerController keyguardSecurityContainerController = (KeyguardSecurityContainerController) this.$securityContainerController;
                            keyguardSecurityContainerController.getClass();
                            float showBouncerProgress = BouncerPanelExpansionCalculator.showBouncerProgress(floatValue);
                            ((KeyguardSecurityContainer) keyguardSecurityContainerController.mView).setAlpha(MathUtils.constrain(1.0f - showBouncerProgress, 0.0f, 1.0f));
                            ((KeyguardSecurityContainer) keyguardSecurityContainerController.mView).setTranslationY(showBouncerProgress * keyguardSecurityContainerController.mTranslationY);
                            break;
                        case 3:
                            ((KeyguardSecurityContainer) ((KeyguardSecurityContainerController) this.$securityContainerController).mView).setAlpha(((Number) obj).floatValue());
                            break;
                        case 4:
                            ((KeyguardSecurityContainer) ((KeyguardSecurityContainerController) this.$securityContainerController).mView).mIsInteractable = ((Boolean) obj).booleanValue();
                            break;
                        case 5:
                            ((KeyguardSecurityContainer) ((KeyguardSecurityContainerController) this.$securityContainerController).mView).mViewMode.updatePositionByTouchX(((Number) obj).floatValue());
                            break;
                        default:
                            ((ViewGroup) this.$securityContainerController).setSystemUiVisibility(((Number) obj).intValue());
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.$securityContainerController, continuation);
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
                    PrimaryBouncerInteractor$special$$inlined$map$1 primaryBouncerInteractor$special$$inlined$map$1 = this.$viewModel.startingToHide;
                    C00561 c00561 = new C00561(0, this.$securityContainerController);
                    this.label = 1;
                    if (primaryBouncerInteractor$special$$inlined$map$1.collect(c00561, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$viewModel, this.$securityContainerController, continuation);
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
                    FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = this.$viewModel.startDisappearAnimation;
                    AnonymousClass2.C00561 c00561 = new AnonymousClass2.C00561(1, this.$securityContainerController);
                    this.label = 1;
                    if (flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.collect(c00561, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$viewModel, this.$securityContainerController, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlow = this.$viewModel.bouncerExpansionAmount;
                AnonymousClass2.C00561 c00561 = new AnonymousClass2.C00561(2, this.$securityContainerController);
                this.label = 1;
                ((StateFlowImpl) ((ReadonlyStateFlow) stateFlow).$$delegate_0).collect(c00561, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ PrimaryBouncerToGoneTransitionViewModel $primaryBouncerToGoneTransitionViewModel;
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, Continuation continuation) {
                super(2, continuation);
                this.$primaryBouncerToGoneTransitionViewModel = primaryBouncerToGoneTransitionViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$primaryBouncerToGoneTransitionViewModel, this.$securityContainerController, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 keyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 = this.$primaryBouncerToGoneTransitionViewModel.bouncerAlpha;
                    AnonymousClass2.C00561 c00561 = new AnonymousClass2.C00561(3, this.$securityContainerController);
                    this.label = 1;
                    if (keyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1.collect(c00561, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$6, reason: invalid class name */
        final class AnonymousClass6 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass6(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, ViewGroup viewGroup, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass6(this.$viewModel, this.$securityContainerController, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = this.$viewModel.bouncerExpansionAmount;
                    AnonymousClass9.C00571 c00571 = new AnonymousClass9.C00571(this.$securityContainerController, this.$view, 2);
                    this.label = 1;
                    Object collect = stateFlow.collect(new KeyguardBouncerViewBinder$bind$1$1$6$invokeSuspend$$inlined$filter$1$2(c00571), this);
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

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$7, reason: invalid class name */
        final class AnonymousClass7 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass7(this.$viewModel, this.$securityContainerController, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass7) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PrimaryBouncerInteractor$special$$inlined$map$1 primaryBouncerInteractor$special$$inlined$map$1 = this.$viewModel.isInteractable;
                    AnonymousClass2.C00561 c00561 = new AnonymousClass2.C00561(4, this.$securityContainerController);
                    this.label = 1;
                    if (primaryBouncerInteractor$special$$inlined$map$1.collect(c00561, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$8, reason: invalid class name */
        final class AnonymousClass8 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass8(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass8(this.$viewModel, this.$securityContainerController, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass8) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = this.$viewModel.keyguardPosition;
                    AnonymousClass2.C00561 c00561 = new AnonymousClass2.C00561(5, this.$securityContainerController);
                    this.label = 1;
                    if (flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.collect(c00561, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$9, reason: invalid class name */
        final class AnonymousClass9 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
            final /* synthetic */ KeyguardBouncerViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$1$1$9$1, reason: invalid class name and collision with other inner class name */
            public final class C00571 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ KeyguardSecurityContainerController $securityContainerController;
                public final /* synthetic */ Object $viewModel;

                public /* synthetic */ C00571(KeyguardSecurityContainerController keyguardSecurityContainerController, Object obj, int i) {
                    this.$r8$classId = i;
                    this.$securityContainerController = keyguardSecurityContainerController;
                    this.$viewModel = obj;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ((Boolean) obj).getClass();
                            this.$securityContainerController.updateResources();
                            KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = ((KeyguardBouncerViewModel) this.$viewModel).interactor.repository;
                            Boolean bool = Boolean.FALSE;
                            StateFlowImpl stateFlowImpl = keyguardBouncerRepositoryImpl._resourceUpdateRequests;
                            stateFlowImpl.getClass();
                            stateFlowImpl.updateState(null, bool);
                            break;
                        case 1:
                            BouncerShowMessageModel bouncerShowMessageModel = (BouncerShowMessageModel) obj;
                            String str = bouncerShowMessageModel.message;
                            ColorStateList colorStateList = bouncerShowMessageModel.colorStateList;
                            KeyguardSecurityContainerController keyguardSecurityContainerController = this.$securityContainerController;
                            if (keyguardSecurityContainerController.mCurrentSecurityMode != KeyguardSecurityModel.SecurityMode.None) {
                                keyguardSecurityContainerController.getCurrentSecurityController(new KeyguardSecurityContainerController$$ExternalSyntheticLambda2(str, colorStateList, true));
                            }
                            ((KeyguardBouncerViewModel) this.$viewModel).interactor.repository._showMessage.setValue(null);
                            break;
                        default:
                            ((Number) obj).floatValue();
                            KeyguardSecurityContainerController keyguardSecurityContainerController2 = this.$securityContainerController;
                            keyguardSecurityContainerController2.onResume(1);
                            ((ViewGroup) this.$viewModel).announceForAccessibility(keyguardSecurityContainerController2.getTitle());
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass9(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardSecurityContainerController keyguardSecurityContainerController, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardBouncerViewModel;
                this.$securityContainerController = keyguardSecurityContainerController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass9(this.$viewModel, this.$securityContainerController, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass9) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    KeyguardBouncerViewModel keyguardBouncerViewModel = this.$viewModel;
                    PrimaryBouncerInteractor$special$$inlined$filter$1 primaryBouncerInteractor$special$$inlined$filter$1 = keyguardBouncerViewModel.updateResources;
                    C00571 c00571 = new C00571(this.$securityContainerController, keyguardBouncerViewModel, 0);
                    this.label = 1;
                    if (primaryBouncerInteractor$special$$inlined$filter$1.collect(c00571, this) == coroutineSingletons) {
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
        public AnonymousClass1(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardBouncerViewBinder$bind$delegate$1 keyguardBouncerViewBinder$bind$delegate$1, ViewGroup viewGroup, KeyguardSecurityContainerController keyguardSecurityContainerController, BouncerLogger bouncerLogger, BouncerMessageInteractor bouncerMessageInteractor, KeyguardMessageAreaController.Factory factory, PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel, SelectedUserInteractor selectedUserInteractor, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = keyguardBouncerViewModel;
            this.$delegate = keyguardBouncerViewBinder$bind$delegate$1;
            this.$view = viewGroup;
            this.$securityContainerController = keyguardSecurityContainerController;
            this.$bouncerLogger = bouncerLogger;
            this.$bouncerMessageInteractor = bouncerMessageInteractor;
            this.$messageAreaControllerFactory = factory;
            this.$primaryBouncerToGoneTransitionViewModel = primaryBouncerToGoneTransitionViewModel;
            this.$selectedUserInteractor = selectedUserInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$delegate, this.$view, this.$securityContainerController, this.$bouncerLogger, this.$bouncerMessageInteractor, this.$messageAreaControllerFactory, this.$primaryBouncerToGoneTransitionViewModel, this.$selectedUserInteractor, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            try {
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    throw new KotlinNothingValueException();
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                KeyguardBouncerViewModel keyguardBouncerViewModel = this.$viewModel;
                KeyguardBouncerViewBinder$bind$delegate$1 keyguardBouncerViewBinder$bind$delegate$1 = this.$delegate;
                BouncerViewImpl bouncerViewImpl = keyguardBouncerViewModel.view;
                bouncerViewImpl.getClass();
                bouncerViewImpl._delegate = new WeakReference(keyguardBouncerViewBinder$bind$delegate$1);
                BuildersKt.launch$default(coroutineScope, null, null, new C00511(this.$viewModel, this.$view, this.$securityContainerController, this.$bouncerLogger, this.$bouncerMessageInteractor, this.$messageAreaControllerFactory, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$securityContainerController, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.$securityContainerController, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$viewModel, this.$securityContainerController, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$primaryBouncerToGoneTransitionViewModel, this.$securityContainerController, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass6(this.$viewModel, this.$securityContainerController, this.$view, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass7(this.$viewModel, this.$securityContainerController, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass8(this.$viewModel, this.$securityContainerController, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass9(this.$viewModel, this.$securityContainerController, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass10(this.$viewModel, this.$securityContainerController, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass11(this.$viewModel, this.$securityContainerController, this.$selectedUserInteractor, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass12(this.$viewModel, this.$view, null), 3);
                this.label = 1;
                DelayKt.awaitCancellation(this);
                return coroutineSingletons;
            } catch (Throwable th) {
                BouncerViewImpl bouncerViewImpl2 = this.$viewModel.view;
                bouncerViewImpl2.getClass();
                bouncerViewImpl2._delegate = new WeakReference(null);
                throw th;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardBouncerViewBinder$bind$1(KeyguardBouncerViewModel keyguardBouncerViewModel, KeyguardBouncerViewBinder$bind$delegate$1 keyguardBouncerViewBinder$bind$delegate$1, ViewGroup viewGroup, KeyguardSecurityContainerController keyguardSecurityContainerController, BouncerLogger bouncerLogger, BouncerMessageInteractor bouncerMessageInteractor, KeyguardMessageAreaController.Factory factory, PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel, SelectedUserInteractor selectedUserInteractor, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = keyguardBouncerViewModel;
        this.$delegate = keyguardBouncerViewBinder$bind$delegate$1;
        this.$view = viewGroup;
        this.$securityContainerController = keyguardSecurityContainerController;
        this.$bouncerLogger = bouncerLogger;
        this.$bouncerMessageInteractor = bouncerMessageInteractor;
        this.$messageAreaControllerFactory = factory;
        this.$primaryBouncerToGoneTransitionViewModel = primaryBouncerToGoneTransitionViewModel;
        this.$selectedUserInteractor = selectedUserInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardBouncerViewBinder$bind$1 keyguardBouncerViewBinder$bind$1 = new KeyguardBouncerViewBinder$bind$1(this.$viewModel, this.$delegate, this.$view, this.$securityContainerController, this.$bouncerLogger, this.$bouncerMessageInteractor, this.$messageAreaControllerFactory, this.$primaryBouncerToGoneTransitionViewModel, this.$selectedUserInteractor, (Continuation) obj3);
        keyguardBouncerViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return keyguardBouncerViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$delegate, this.$view, this.$securityContainerController, this.$bouncerLogger, this.$bouncerMessageInteractor, this.$messageAreaControllerFactory, this.$primaryBouncerToGoneTransitionViewModel, this.$selectedUserInteractor, null);
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
