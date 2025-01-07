package com.android.systemui.biometrics.ui.binder;

import android.animation.Animator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.LifecycleOwner;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.biometrics.shared.model.BiometricModalities;
import com.android.systemui.biometrics.ui.binder.Spaghetti;
import com.android.systemui.biometrics.ui.viewmodel.FingerprintStartMode;
import com.android.systemui.biometrics.ui.viewmodel.PromptAuthState;
import com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel;
import com.android.systemui.biometrics.ui.viewmodel.PromptMessage;
import com.android.systemui.biometrics.ui.viewmodel.PromptSize;
import com.android.systemui.biometrics.ui.viewmodel.PromptViewModel;
import com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$1;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.wm.shell.R;
import com.google.android.msdl.domain.MSDLPlayer;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BiometricViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ AccessibilityManager $accessibilityManager;
    final /* synthetic */ Spaghetti $adapter;
    final /* synthetic */ View $backgroundView;
    final /* synthetic */ Ref$BooleanRef $boundSize;
    final /* synthetic */ Button $cancelButton;
    final /* synthetic */ Button $confirmationButton;
    final /* synthetic */ Button $credentialFallbackButton;
    final /* synthetic */ LinearLayout $customizedViewContainer;
    final /* synthetic */ TextView $descriptionView;
    final /* synthetic */ LottieAnimationView $iconView;
    final /* synthetic */ TextView $indicatorMessageView;
    final /* synthetic */ Animator.AnimatorListener $jankListener;
    final /* synthetic */ Spaghetti.Callback $legacyCallback;
    final /* synthetic */ TextView $logoDescriptionView;
    final /* synthetic */ ImageView $logoView;
    final /* synthetic */ MSDLPlayer $msdlPlayer;
    final /* synthetic */ Button $negativeButton;
    final /* synthetic */ Button $retryButton;
    final /* synthetic */ TextView $subtitleView;
    final /* synthetic */ int $textColorError;
    final /* synthetic */ int $textColorHint;
    final /* synthetic */ TextView $titleView;
    final /* synthetic */ View $udfpsGuidanceView;
    final /* synthetic */ VibratorHelper $vibratorHelper;
    final /* synthetic */ View $view;
    final /* synthetic */ PromptViewModel $viewModel;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$4, reason: invalid class name */
    public final class AnonymousClass4 implements View.OnClickListener {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ PromptViewModel $viewModel;

        public /* synthetic */ AnonymousClass4(PromptViewModel promptViewModel, int i) {
            this.$r8$classId = i;
            this.$viewModel = promptViewModel;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            switch (this.$r8$classId) {
                case 0:
                    this.$viewModel.confirmAuthenticated();
                    break;
                default:
                    this.$viewModel.confirmAuthenticated();
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$6, reason: invalid class name */
    final class AnonymousClass6 extends SuspendLambda implements Function2 {
        final /* synthetic */ LottieAnimationView $iconView;
        final /* synthetic */ PromptViewModel $viewModel;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$6$1, reason: invalid class name */
        public final class AnonymousClass1 implements FlowCollector {
            public final /* synthetic */ LottieAnimationView $iconView;
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ PromptViewModel $viewModel;

            public /* synthetic */ AnonymousClass1(LottieAnimationView lottieAnimationView, PromptViewModel promptViewModel, int i) {
                this.$r8$classId = i;
                this.$iconView = lottieAnimationView;
                this.$viewModel = promptViewModel;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        if (!((Boolean) obj).booleanValue()) {
                            PromptIconViewModel promptIconViewModel = this.$viewModel.iconViewModel;
                            LottieAnimationView lottieAnimationView = this.$iconView;
                            RepeatWhenAttachedKt.repeatWhenAttached(lottieAnimationView, EmptyCoroutineContext.INSTANCE, new PromptIconViewBinder$bind$1(promptIconViewModel, lottieAnimationView, null));
                        }
                        break;
                    default:
                        boolean booleanValue = ((Boolean) obj).booleanValue();
                        LottieAnimationView lottieAnimationView2 = this.$iconView;
                        if (booleanValue) {
                            final PromptViewModel promptViewModel = this.$viewModel;
                            lottieAnimationView2.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$10$1$1
                                @Override // android.view.View.OnTouchListener
                                public final boolean onTouch(View view, MotionEvent motionEvent) {
                                    PromptViewModel promptViewModel2 = PromptViewModel.this;
                                    promptViewModel2.getClass();
                                    int actionMasked = motionEvent.getActionMasked();
                                    StateFlowImpl stateFlowImpl = promptViewModel2._isOverlayTouched;
                                    if (actionMasked != 0) {
                                        if (motionEvent.getActionMasked() == 1) {
                                            Boolean bool = Boolean.FALSE;
                                            stateFlowImpl.getClass();
                                            stateFlowImpl.updateState(null, bool);
                                        }
                                        return false;
                                    }
                                    Boolean bool2 = Boolean.TRUE;
                                    stateFlowImpl.getClass();
                                    stateFlowImpl.updateState(null, bool2);
                                    if (!((PromptAuthState) promptViewModel2._isAuthenticated.getValue()).needsUserConfirmation) {
                                        return true;
                                    }
                                    promptViewModel2.confirmAuthenticated();
                                    return true;
                                }
                            });
                            lottieAnimationView2.setOnClickListener(new AnonymousClass4(promptViewModel, 1));
                        } else {
                            lottieAnimationView2.setOnTouchListener(null);
                            lottieAnimationView2.setOnClickListener(null);
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(PromptViewModel promptViewModel, LottieAnimationView lottieAnimationView, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = promptViewModel;
            this.$iconView = lottieAnimationView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass6(this.$viewModel, this.$iconView, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PromptViewModel promptViewModel = this.$viewModel;
                Flow flow = promptViewModel.hideSensorIcon;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$iconView, promptViewModel, 0);
                this.label = 1;
                if (flow.collect(anonymousClass1, this) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$7, reason: invalid class name */
    final class AnonymousClass7 extends SuspendLambda implements Function2 {
        final /* synthetic */ Spaghetti.Callback $legacyCallback;
        final /* synthetic */ PromptViewModel $viewModel;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$7$1, reason: invalid class name */
        public final class AnonymousClass1 implements FlowCollector {
            public final /* synthetic */ Spaghetti.Callback $legacyCallback;
            public final /* synthetic */ Object $oldMode;
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ AnonymousClass1(Object obj, Spaghetti.Callback callback, int i) {
                this.$r8$classId = i;
                this.$oldMode = obj;
                this.$legacyCallback = callback;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        FingerprintStartMode fingerprintStartMode = (FingerprintStartMode) obj;
                        if (((FingerprintStartMode) this.$oldMode) == FingerprintStartMode.Pending && fingerprintStartMode == FingerprintStartMode.Delayed) {
                            this.$legacyCallback.onStartDelayedFingerprintSensor();
                        }
                        break;
                    default:
                        final boolean booleanValue = ((Boolean) obj).booleanValue();
                        View view = (View) this.$oldMode;
                        final Spaghetti.Callback callback = this.$legacyCallback;
                        view.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$1$2$1
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                if (booleanValue) {
                                    callback.onUserCanceled();
                                } else {
                                    Log.w("BiometricViewBinder", "Ignoring background click");
                                }
                            }
                        });
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(PromptViewModel promptViewModel, Spaghetti.Callback callback, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = promptViewModel;
            this.$legacyCallback = callback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass7(this.$viewModel, this.$legacyCallback, continuation);
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
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.fingerprintStartMode;
                this.label = 1;
                obj = FlowKt.first(readonlyStateFlow, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            ReadonlyStateFlow readonlyStateFlow2 = this.$viewModel.fingerprintStartMode;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1((FingerprintStartMode) obj, this.$legacyCallback, 0);
            this.label = 2;
            ((StateFlowImpl) readonlyStateFlow2.$$delegate_0).collect(anonymousClass1, this);
            return coroutineSingletons;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8, reason: invalid class name */
    final class AnonymousClass8 extends SuspendLambda implements Function2 {
        final /* synthetic */ AccessibilityManager $accessibilityManager;
        final /* synthetic */ View $backgroundView;
        final /* synthetic */ Button $cancelButton;
        final /* synthetic */ Button $confirmationButton;
        final /* synthetic */ Button $credentialFallbackButton;
        final /* synthetic */ LottieAnimationView $iconView;
        final /* synthetic */ TextView $indicatorMessageView;
        final /* synthetic */ Spaghetti.Callback $legacyCallback;
        final /* synthetic */ BiometricModalities $modalities;
        final /* synthetic */ MSDLPlayer $msdlPlayer;
        final /* synthetic */ Button $negativeButton;
        final /* synthetic */ Button $retryButton;
        final /* synthetic */ TextView $subtitleView;
        final /* synthetic */ int $textColorError;
        final /* synthetic */ int $textColorHint;
        final /* synthetic */ View $udfpsGuidanceView;
        final /* synthetic */ VibratorHelper $vibratorHelper;
        final /* synthetic */ View $view;
        final /* synthetic */ PromptViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $backgroundView;
            final /* synthetic */ Spaghetti.Callback $legacyCallback;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$1$1, reason: invalid class name and collision with other inner class name */
            final class C00261 extends SuspendLambda implements Function3 {
                /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                int label;

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    C00261 c00261 = new C00261(3, (Continuation) obj3);
                    c00261.L$0 = (PromptAuthState) obj;
                    c00261.L$1 = (PromptSize) obj2;
                    return c00261.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    PromptAuthState promptAuthState = (PromptAuthState) this.L$0;
                    PromptSize promptSize = (PromptSize) this.L$1;
                    boolean z = false;
                    if (!promptAuthState.isAuthenticated && promptSize != PromptSize.SMALL && promptSize != PromptSize.LARGE) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(PromptViewModel promptViewModel, View view, Spaghetti.Callback callback, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$backgroundView = view;
                this.$legacyCallback = callback;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.$viewModel, this.$backgroundView, this.$legacyCallback, continuation);
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
                    PromptViewModel promptViewModel = this.$viewModel;
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(promptViewModel.isAuthenticated, promptViewModel.size, new C00261(3, null));
                    AnonymousClass7.AnonymousClass1 anonymousClass1 = new AnonymousClass7.AnonymousClass1(this.$backgroundView, this.$legacyCallback, 1);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(anonymousClass1, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$10, reason: invalid class name */
        final class AnonymousClass10 extends SuspendLambda implements Function2 {
            final /* synthetic */ LottieAnimationView $iconView;
            final /* synthetic */ BiometricModalities $modalities;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass10(PromptViewModel promptViewModel, LottieAnimationView lottieAnimationView, BiometricModalities biometricModalities, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$iconView = lottieAnimationView;
                this.$modalities = biometricModalities;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass10(this.$viewModel, this.$iconView, this.$modalities, continuation);
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
                    PromptViewModel promptViewModel = this.$viewModel;
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = promptViewModel.isIconConfirmButton;
                    AnonymousClass6.AnonymousClass1 anonymousClass1 = new AnonymousClass6.AnonymousClass1(this.$iconView, promptViewModel, 1);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(anonymousClass1, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1 && i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$11, reason: invalid class name */
        final class AnonymousClass11 extends SuspendLambda implements Function2 {
            final /* synthetic */ AccessibilityManager $accessibilityManager;
            final /* synthetic */ View $backgroundView;
            final /* synthetic */ LottieAnimationView $iconView;
            final /* synthetic */ Spaghetti.Callback $legacyCallback;
            final /* synthetic */ BiometricModalities $modalities;
            final /* synthetic */ TextView $subtitleView;
            final /* synthetic */ View $view;
            final /* synthetic */ PromptViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass11(PromptViewModel promptViewModel, TextView textView, View view, AccessibilityManager accessibilityManager, BiometricModalities biometricModalities, LottieAnimationView lottieAnimationView, View view2, Spaghetti.Callback callback, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$subtitleView = textView;
                this.$backgroundView = view;
                this.$accessibilityManager = accessibilityManager;
                this.$modalities = biometricModalities;
                this.$iconView = lottieAnimationView;
                this.$view = view2;
                this.$legacyCallback = callback;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass11 anonymousClass11 = new AnonymousClass11(this.$viewModel, this.$subtitleView, this.$backgroundView, this.$accessibilityManager, this.$modalities, this.$iconView, this.$view, this.$legacyCallback, continuation);
                anonymousClass11.L$0 = obj;
                return anonymousClass11;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass11) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                PromptViewModel promptViewModel = this.$viewModel;
                ReadonlyStateFlow readonlyStateFlow = promptViewModel.isAuthenticated;
                FlowCollector flowCollector = new FlowCollector(this.$subtitleView, this.$backgroundView, this.$accessibilityManager, this.$modalities, this.$iconView, this.$view, coroutineScope, promptViewModel, this.$legacyCallback) { // from class: com.android.systemui.biometrics.ui.binder.BiometricViewBinder.bind.1.8.11.1
                    public final /* synthetic */ CoroutineScope $$this$launch;
                    public final /* synthetic */ View $backgroundView;
                    public final /* synthetic */ Spaghetti.Callback $legacyCallback;
                    public final /* synthetic */ TextView $subtitleView;
                    public final /* synthetic */ View $view;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$11$1$2, reason: invalid class name */
                    final class AnonymousClass2 extends SuspendLambda implements Function2 {
                        final /* synthetic */ PromptAuthState $authState;
                        final /* synthetic */ Spaghetti.Callback $legacyCallback;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass2(PromptAuthState promptAuthState, Spaghetti.Callback callback, Continuation continuation) {
                            super(2, continuation);
                            this.$authState = promptAuthState;
                            this.$legacyCallback = callback;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object obj, Continuation continuation) {
                            return new AnonymousClass2(this.$authState, this.$legacyCallback, continuation);
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
                                long j = this.$authState.delay;
                                this.label = 1;
                                if (DelayKt.delay(j, this) == coroutineSingletons) {
                                    return coroutineSingletons;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            if (this.$authState.isAuthenticatedAndExplicitlyConfirmed()) {
                                this.$legacyCallback.onAuthenticatedAndConfirmed();
                            } else {
                                this.$legacyCallback.onAuthenticated();
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    {
                        this.$view = r6;
                        this.$$this$launch = coroutineScope;
                        this.$legacyCallback = r9;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        PromptAuthState promptAuthState = (PromptAuthState) obj2;
                        if (promptAuthState.isAuthenticated) {
                            this.$subtitleView.setImportantForAccessibility(2);
                            this.$backgroundView.setOnClickListener(null);
                            this.$backgroundView.setImportantForAccessibility(2);
                        }
                        if (promptAuthState.isAuthenticated && !promptAuthState.needsUserConfirmation) {
                            View view = this.$view;
                            view.announceForAccessibility(view.getResources().getString(R.string.biometric_dialog_authenticated));
                            BuildersKt.launch$default(this.$$this$launch, null, null, new AnonymousClass2(promptAuthState, this.$legacyCallback, null), 3);
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$12, reason: invalid class name */
        final class AnonymousClass12 extends SuspendLambda implements Function2 {
            final /* synthetic */ AccessibilityManager $accessibilityManager;
            final /* synthetic */ TextView $indicatorMessageView;
            final /* synthetic */ int $textColorError;
            final /* synthetic */ int $textColorHint;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass12(PromptViewModel promptViewModel, TextView textView, int i, int i2, AccessibilityManager accessibilityManager, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$indicatorMessageView = textView;
                this.$textColorError = i;
                this.$textColorHint = i2;
                this.$accessibilityManager = accessibilityManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass12(this.$viewModel, this.$indicatorMessageView, this.$textColorError, this.$textColorHint, this.$accessibilityManager, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass12) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.message;
                final TextView textView = this.$indicatorMessageView;
                final int i2 = this.$textColorError;
                final int i3 = this.$textColorHint;
                final AccessibilityManager accessibilityManager = this.$accessibilityManager;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.biometrics.ui.binder.BiometricViewBinder.bind.1.8.12.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        PromptMessage promptMessage = (PromptMessage) obj2;
                        boolean z = promptMessage instanceof PromptMessage.Error;
                        TextView textView2 = textView;
                        promptMessage.getClass();
                        textView2.setText(promptMessage instanceof PromptMessage.Error ? ((PromptMessage.Error) promptMessage).errorMessage : promptMessage instanceof PromptMessage.Help ? ((PromptMessage.Help) promptMessage).helpMessage : "");
                        textView.setTextColor(z ? i2 : i3);
                        textView.setSelected((accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) ? false : true);
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$14, reason: invalid class name */
        final class AnonymousClass14 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $view;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass14(PromptViewModel promptViewModel, View view, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$view = view;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass14(this.$viewModel, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass14) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ReadonlySharedFlow readonlySharedFlow = this.$viewModel.accessibilityHint;
                    AnonymousClass2.AnonymousClass1 anonymousClass1 = new AnonymousClass2.AnonymousClass1(1, this.$view);
                    this.label = 1;
                    if (readonlySharedFlow.$$delegate_0.collect(anonymousClass1, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$15, reason: invalid class name */
        final class AnonymousClass15 extends SuspendLambda implements Function2 {
            final /* synthetic */ MSDLPlayer $msdlPlayer;
            final /* synthetic */ VibratorHelper $vibratorHelper;
            final /* synthetic */ View $view;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass15(PromptViewModel promptViewModel, VibratorHelper vibratorHelper, View view, MSDLPlayer mSDLPlayer, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$vibratorHelper = vibratorHelper;
                this.$view = view;
                this.$msdlPlayer = mSDLPlayer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass15(this.$viewModel, this.$vibratorHelper, this.$view, this.$msdlPlayer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass15) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                PromptViewModel promptViewModel = this.$viewModel;
                ReadonlyStateFlow readonlyStateFlow = promptViewModel.hapticsToPlay;
                FlowCollector flowCollector = new FlowCollector(this.$view, this.$msdlPlayer, promptViewModel) { // from class: com.android.systemui.biometrics.ui.binder.BiometricViewBinder.bind.1.8.15.1
                    public final /* synthetic */ View $view;
                    public final /* synthetic */ PromptViewModel $viewModel;

                    {
                        this.$viewModel = promptViewModel;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Object value;
                        PromptViewModel.HapticsToPlay hapticsToPlay = (PromptViewModel.HapticsToPlay) obj2;
                        if (hapticsToPlay instanceof PromptViewModel.HapticsToPlay.HapticConstant) {
                            PromptViewModel.HapticsToPlay.HapticConstant hapticConstant = (PromptViewModel.HapticsToPlay.HapticConstant) hapticsToPlay;
                            hapticConstant.getClass();
                            View view = this.$view;
                            VibratorHelper.this.getClass();
                            view.performHapticFeedback(hapticConstant.constant);
                        } else {
                            boolean z = hapticsToPlay instanceof PromptViewModel.HapticsToPlay.None;
                        }
                        StateFlowImpl stateFlowImpl = this.$viewModel._hapticsToPlay;
                        do {
                            value = stateFlowImpl.getValue();
                        } while (!stateFlowImpl.compareAndSet(value, PromptViewModel.HapticsToPlay.None.INSTANCE));
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$16, reason: invalid class name */
        final class AnonymousClass16 extends SuspendLambda implements Function2 {
            final /* synthetic */ Spaghetti.Callback $legacyCallback;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$16$2, reason: invalid class name */
            final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function3 {
                public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

                public AnonymousClass2() {
                    super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    Boolean bool2 = (Boolean) obj2;
                    bool2.booleanValue();
                    return new Pair(bool, bool2);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass16(PromptViewModel promptViewModel, Spaghetti.Callback callback, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$legacyCallback = callback;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass16(this.$viewModel, this.$legacyCallback, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass16) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PromptViewModel promptViewModel = this.$viewModel;
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(promptViewModel.canTryAgainNow, promptViewModel.hasFingerOnSensor, AnonymousClass2.INSTANCE);
                    AnonymousClass2.AnonymousClass1 anonymousClass1 = new AnonymousClass2.AnonymousClass1(2, this.$legacyCallback);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(anonymousClass1, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ TextView $indicatorMessageView;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$2$1, reason: invalid class name */
            public final class AnonymousClass1 implements FlowCollector {
                public final /* synthetic */ Object $indicatorMessageView;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ AnonymousClass1(int i, Object obj) {
                    this.$r8$classId = i;
                    this.$indicatorMessageView = obj;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ((TextView) this.$indicatorMessageView).setVisibility(((Boolean) obj).booleanValue() ? 0 : 4);
                            break;
                        case 1:
                            String str = (String) obj;
                            if (!StringsKt__StringsJVMKt.isBlank(str)) {
                                ((View) this.$indicatorMessageView).announceForAccessibility(str);
                            }
                            break;
                        default:
                            Pair pair = (Pair) obj;
                            boolean booleanValue = ((Boolean) pair.component1()).booleanValue();
                            boolean booleanValue2 = ((Boolean) pair.component2()).booleanValue();
                            if (booleanValue && booleanValue2) {
                                ((Spaghetti.Callback) this.$indicatorMessageView).onButtonTryAgain();
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(PromptViewModel promptViewModel, TextView textView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$indicatorMessageView = textView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.$indicatorMessageView, continuation);
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
                    FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = this.$viewModel.isIndicatorMessageVisible;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(0, this.$indicatorMessageView);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1.collect(anonymousClass1, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ Button $credentialFallbackButton;
            final /* synthetic */ View $view;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ Button $credentialFallbackButton;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ AnonymousClass2(Button button, int i) {
                    this.$r8$classId = i;
                    this.$credentialFallbackButton = button;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            this.$credentialFallbackButton.setText((String) obj);
                            break;
                        case 1:
                            this.$credentialFallbackButton.setText((String) obj);
                            break;
                        case 2:
                            this.$credentialFallbackButton.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                        case 3:
                            this.$credentialFallbackButton.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                        case 4:
                            this.$credentialFallbackButton.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                        case 5:
                            this.$credentialFallbackButton.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                        default:
                            this.$credentialFallbackButton.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(PromptViewModel promptViewModel, View view, Button button, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$view = view;
                this.$credentialFallbackButton = button;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$viewModel, this.$view, this.$credentialFallbackButton, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = this.$viewModel.credentialKind;
                    View view = this.$view;
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$credentialFallbackButton, 0);
                    this.label = 1;
                    Object collect = flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(new BiometricViewBinder$bind$1$8$3$invokeSuspend$$inlined$map$1$2(anonymousClass2, view), this);
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ Button $negativeButton;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(PromptViewModel promptViewModel, Button button, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$negativeButton = button;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$viewModel, this.$negativeButton, continuation);
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
                    PromptViewModel$special$$inlined$map$1 promptViewModel$special$$inlined$map$1 = this.$viewModel.negativeButtonText;
                    AnonymousClass3.AnonymousClass2 anonymousClass2 = new AnonymousClass3.AnonymousClass2(this.$negativeButton, 1);
                    this.label = 1;
                    if (promptViewModel$special$$inlined$map$1.collect(anonymousClass2, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ Button $confirmationButton;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(PromptViewModel promptViewModel, Button button, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$confirmationButton = button;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$viewModel, this.$confirmationButton, continuation);
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
                    FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = this.$viewModel.isConfirmButtonVisible;
                    AnonymousClass3.AnonymousClass2 anonymousClass2 = new AnonymousClass3.AnonymousClass2(this.$confirmationButton, 2);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1.collect(anonymousClass2, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$6, reason: invalid class name */
        final class AnonymousClass6 extends SuspendLambda implements Function2 {
            final /* synthetic */ Button $cancelButton;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass6(PromptViewModel promptViewModel, Button button, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$cancelButton = button;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass6(this.$viewModel, this.$cancelButton, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = this.$viewModel.isCancelButtonVisible;
                    AnonymousClass3.AnonymousClass2 anonymousClass2 = new AnonymousClass3.AnonymousClass2(this.$cancelButton, 3);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1.collect(anonymousClass2, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$7, reason: invalid class name */
        final class AnonymousClass7 extends SuspendLambda implements Function2 {
            final /* synthetic */ Button $negativeButton;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(PromptViewModel promptViewModel, Button button, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$negativeButton = button;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass7(this.$viewModel, this.$negativeButton, continuation);
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
                    FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = this.$viewModel.isNegativeButtonVisible;
                    AnonymousClass3.AnonymousClass2 anonymousClass2 = new AnonymousClass3.AnonymousClass2(this.$negativeButton, 4);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1.collect(anonymousClass2, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$8, reason: invalid class name and collision with other inner class name */
        final class C00278 extends SuspendLambda implements Function2 {
            final /* synthetic */ Button $retryButton;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00278(PromptViewModel promptViewModel, Button button, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$retryButton = button;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00278(this.$viewModel, this.$retryButton, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00278) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = this.$viewModel.isTryAgainButtonVisible;
                    AnonymousClass3.AnonymousClass2 anonymousClass2 = new AnonymousClass3.AnonymousClass2(this.$retryButton, 5);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(anonymousClass2, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$9, reason: invalid class name */
        final class AnonymousClass9 extends SuspendLambda implements Function2 {
            final /* synthetic */ Button $credentialFallbackButton;
            final /* synthetic */ PromptViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass9(PromptViewModel promptViewModel, Button button, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = promptViewModel;
                this.$credentialFallbackButton = button;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass9(this.$viewModel, this.$credentialFallbackButton, continuation);
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
                    FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = this.$viewModel.isCredentialButtonVisible;
                    AnonymousClass3.AnonymousClass2 anonymousClass2 = new AnonymousClass3.AnonymousClass2(this.$credentialFallbackButton, 6);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1.collect(anonymousClass2, this) == coroutineSingletons) {
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
        public AnonymousClass8(View view, PromptViewModel promptViewModel, View view2, Spaghetti.Callback callback, TextView textView, View view3, Button button, Button button2, Button button3, Button button4, Button button5, LottieAnimationView lottieAnimationView, BiometricModalities biometricModalities, TextView textView2, AccessibilityManager accessibilityManager, int i, int i2, VibratorHelper vibratorHelper, MSDLPlayer mSDLPlayer, Continuation continuation) {
            super(2, continuation);
            this.$udfpsGuidanceView = view;
            this.$viewModel = promptViewModel;
            this.$backgroundView = view2;
            this.$legacyCallback = callback;
            this.$indicatorMessageView = textView;
            this.$view = view3;
            this.$credentialFallbackButton = button;
            this.$negativeButton = button2;
            this.$confirmationButton = button3;
            this.$cancelButton = button4;
            this.$retryButton = button5;
            this.$iconView = lottieAnimationView;
            this.$modalities = biometricModalities;
            this.$subtitleView = textView2;
            this.$accessibilityManager = accessibilityManager;
            this.$textColorError = i;
            this.$textColorHint = i2;
            this.$vibratorHelper = vibratorHelper;
            this.$msdlPlayer = mSDLPlayer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass8 anonymousClass8 = new AnonymousClass8(this.$udfpsGuidanceView, this.$viewModel, this.$backgroundView, this.$legacyCallback, this.$indicatorMessageView, this.$view, this.$credentialFallbackButton, this.$negativeButton, this.$confirmationButton, this.$cancelButton, this.$retryButton, this.$iconView, this.$modalities, this.$subtitleView, this.$accessibilityManager, this.$textColorError, this.$textColorHint, this.$vibratorHelper, this.$msdlPlayer, continuation);
            anonymousClass8.L$0 = obj;
            return anonymousClass8;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass8 anonymousClass8 = (AnonymousClass8) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass8.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$viewModel, this.$backgroundView, this.$legacyCallback, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$indicatorMessageView, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.$view, this.$credentialFallbackButton, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$viewModel, this.$negativeButton, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$viewModel, this.$confirmationButton, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass6(this.$viewModel, this.$cancelButton, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass7(this.$viewModel, this.$negativeButton, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new C00278(this.$viewModel, this.$retryButton, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass9(this.$viewModel, this.$credentialFallbackButton, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass10(this.$viewModel, this.$iconView, this.$modalities, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass11(this.$viewModel, this.$subtitleView, this.$backgroundView, this.$accessibilityManager, this.$modalities, this.$iconView, this.$view, this.$legacyCallback, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass12(this.$viewModel, this.$indicatorMessageView, this.$textColorError, this.$textColorHint, this.$accessibilityManager, null), 3);
            View view = this.$udfpsGuidanceView;
            final PromptViewModel promptViewModel = this.$viewModel;
            final AccessibilityManager accessibilityManager = this.$accessibilityManager;
            view.setOnHoverListener(new View.OnHoverListener() { // from class: com.android.systemui.biometrics.ui.binder.BiometricViewBinder.bind.1.8.13

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1$8$13$1, reason: invalid class name */
                final class AnonymousClass1 extends SuspendLambda implements Function2 {
                    final /* synthetic */ AccessibilityManager $accessibilityManager;
                    final /* synthetic */ MotionEvent $event;
                    final /* synthetic */ PromptViewModel $viewModel;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass1(PromptViewModel promptViewModel, MotionEvent motionEvent, AccessibilityManager accessibilityManager, Continuation continuation) {
                        super(2, continuation);
                        this.$viewModel = promptViewModel;
                        this.$event = motionEvent;
                        this.$accessibilityManager = accessibilityManager;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new AnonymousClass1(this.$viewModel, this.$event, this.$accessibilityManager, continuation);
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
                            PromptViewModel promptViewModel = this.$viewModel;
                            MotionEvent motionEvent = this.$event;
                            boolean isTouchExplorationEnabled = this.$accessibilityManager.isTouchExplorationEnabled();
                            this.label = 1;
                            if (promptViewModel.onAnnounceAccessibilityHint(motionEvent, isTouchExplorationEnabled, this) == coroutineSingletons) {
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

                @Override // android.view.View.OnHoverListener
                public final boolean onHover(View view2, MotionEvent motionEvent) {
                    BuildersKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(promptViewModel, motionEvent, accessibilityManager, null), 3);
                    return false;
                }
            });
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass14(this.$viewModel, this.$view, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass15(this.$viewModel, this.$vibratorHelper, this.$view, this.$msdlPlayer, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass16(this.$viewModel, this.$legacyCallback, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiometricViewBinder$bind$1(PromptViewModel promptViewModel, View view, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, LinearLayout linearLayout, Spaghetti.Callback callback, Button button, Button button2, Button button3, Button button4, Button button5, Spaghetti spaghetti, Ref$BooleanRef ref$BooleanRef, Animator.AnimatorListener animatorListener, LottieAnimationView lottieAnimationView, View view2, View view3, TextView textView5, AccessibilityManager accessibilityManager, int i, int i2, VibratorHelper vibratorHelper, MSDLPlayer mSDLPlayer, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = promptViewModel;
        this.$view = view;
        this.$logoView = imageView;
        this.$logoDescriptionView = textView;
        this.$titleView = textView2;
        this.$subtitleView = textView3;
        this.$descriptionView = textView4;
        this.$customizedViewContainer = linearLayout;
        this.$legacyCallback = callback;
        this.$negativeButton = button;
        this.$cancelButton = button2;
        this.$credentialFallbackButton = button3;
        this.$confirmationButton = button4;
        this.$retryButton = button5;
        this.$adapter = spaghetti;
        this.$boundSize = ref$BooleanRef;
        this.$jankListener = animatorListener;
        this.$iconView = lottieAnimationView;
        this.$udfpsGuidanceView = view2;
        this.$backgroundView = view3;
        this.$indicatorMessageView = textView5;
        this.$accessibilityManager = accessibilityManager;
        this.$textColorError = i;
        this.$textColorHint = i2;
        this.$vibratorHelper = vibratorHelper;
        this.$msdlPlayer = mSDLPlayer;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BiometricViewBinder$bind$1 biometricViewBinder$bind$1 = new BiometricViewBinder$bind$1(this.$viewModel, this.$view, this.$logoView, this.$logoDescriptionView, this.$titleView, this.$subtitleView, this.$descriptionView, this.$customizedViewContainer, this.$legacyCallback, this.$negativeButton, this.$cancelButton, this.$credentialFallbackButton, this.$confirmationButton, this.$retryButton, this.$adapter, this.$boundSize, this.$jankListener, this.$iconView, this.$udfpsGuidanceView, this.$backgroundView, this.$indicatorMessageView, this.$accessibilityManager, this.$textColorError, this.$textColorHint, this.$vibratorHelper, this.$msdlPlayer, (Continuation) obj3);
        biometricViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return biometricViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x05dd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x03a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0389 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x036f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x034f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02eb A[LOOP:0: B:59:0x02e5->B:61:0x02eb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0313 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01f8  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r83) {
        /*
            Method dump skipped, instructions count: 1526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.binder.BiometricViewBinder$bind$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
