package com.android.systemui.keyguard.ui.binder;

import android.view.ViewGroup;
import android.widget.TextView;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardIndicationAreaViewModel;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardIndicationAreaBinder$bind$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ MutableStateFlow $configurationBasedDimensions;
    final /* synthetic */ KeyguardIndicationController $indicationController;
    final /* synthetic */ TextView $indicationText;
    final /* synthetic */ TextView $indicationTextBottom;
    final /* synthetic */ ViewGroup $view;
    final /* synthetic */ KeyguardIndicationAreaViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ MutableStateFlow $configurationBasedDimensions;
        final /* synthetic */ KeyguardIndicationController $indicationController;
        final /* synthetic */ TextView $indicationText;
        final /* synthetic */ TextView $indicationTextBottom;
        final /* synthetic */ ViewGroup $view;
        final /* synthetic */ KeyguardIndicationAreaViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$1, reason: invalid class name and collision with other inner class name */
        final class C01151 extends SuspendLambda implements Function2 {
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardIndicationAreaViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01151(KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, ViewGroup viewGroup, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardIndicationAreaViewModel;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01151(this.$viewModel, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                C01151 c01151 = (C01151) create((CoroutineScope) obj, (Continuation) obj2);
                Unit unit = Unit.INSTANCE;
                c01151.invokeSuspend(unit);
                return unit;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0 && i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardIndicationAreaViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$2$1, reason: invalid class name and collision with other inner class name */
            public final class C01161 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ Object $view;

                public /* synthetic */ C01161(int i, Object obj) {
                    this.$r8$classId = i;
                    this.$view = obj;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ((ViewGroup) this.$view).setTranslationX(((Number) obj).floatValue());
                            break;
                        case 1:
                            int intValue = ((Number) obj).intValue();
                            ((ViewGroup) this.$view).setPadding(intValue, 0, intValue, 0);
                            break;
                        case 2:
                            ((ViewGroup) this.$view).setTranslationY(((Number) obj).floatValue());
                            break;
                        default:
                            ((KeyguardIndicationController) this.$view).setVisible(((Boolean) obj).booleanValue());
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, ViewGroup viewGroup, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardIndicationAreaViewModel;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.$view, continuation);
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
                    Flow flow = this.$viewModel.indicationAreaTranslationX;
                    C01161 c01161 = new C01161(0, this.$view);
                    this.label = 1;
                    if (flow.collect(c01161, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableStateFlow $configurationBasedDimensions;
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardIndicationAreaViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$3$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function3 {
                /* synthetic */ int I$0;
                /* synthetic */ boolean Z$0;
                int label;

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    int intValue = ((Number) obj2).intValue();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(3, (Continuation) obj3);
                    anonymousClass2.Z$0 = booleanValue;
                    anonymousClass2.I$0 = intValue;
                    return anonymousClass2.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    boolean z = this.Z$0;
                    int i = this.I$0;
                    if (!z) {
                        i = 0;
                    }
                    return new Integer(i);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(ViewGroup viewGroup, KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, Continuation continuation, MutableStateFlow mutableStateFlow) {
                super(2, continuation);
                this.$viewModel = keyguardIndicationAreaViewModel;
                this.$configurationBasedDimensions = mutableStateFlow;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$view, this.$viewModel, continuation, this.$configurationBasedDimensions);
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
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.$viewModel.isIndicationAreaPadded, new KeyguardIndicationAreaBinder$bind$2$1$3$invokeSuspend$$inlined$map$1(this.$configurationBasedDimensions, 0), new AnonymousClass2(3, null));
                    AnonymousClass2.C01161 c01161 = new AnonymousClass2.C01161(1, this.$view);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(c01161, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableStateFlow $configurationBasedDimensions;
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardIndicationAreaViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(ViewGroup viewGroup, KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, Continuation continuation, MutableStateFlow mutableStateFlow) {
                super(2, continuation);
                this.$configurationBasedDimensions = mutableStateFlow;
                this.$viewModel = keyguardIndicationAreaViewModel;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                MutableStateFlow mutableStateFlow = this.$configurationBasedDimensions;
                return new AnonymousClass4(this.$view, this.$viewModel, continuation, mutableStateFlow);
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
                    ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(new KeyguardIndicationAreaBinder$bind$2$1$3$invokeSuspend$$inlined$map$1(this.$configurationBasedDimensions, 1), new KeyguardIndicationAreaBinder$bind$2$1$4$invokeSuspend$$inlined$flatMapLatest$1(null, this.$viewModel));
                    AnonymousClass2.C01161 c01161 = new AnonymousClass2.C01161(2, this.$view);
                    this.label = 1;
                    if (transformLatest.collect(c01161, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableStateFlow $configurationBasedDimensions;
            final /* synthetic */ TextView $indicationText;
            final /* synthetic */ TextView $indicationTextBottom;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$5$1, reason: invalid class name and collision with other inner class name */
            public final class C01171 implements FlowCollector {
                public final /* synthetic */ Object $indicationText;
                public final /* synthetic */ Object $indicationTextBottom;
                public final /* synthetic */ int $r8$classId = 0;

                public C01171(TextView textView, TextView textView2) {
                    this.$indicationText = textView;
                    this.$indicationTextBottom = textView2;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit = Unit.INSTANCE;
                    Object obj2 = this.$indicationText;
                    Object obj3 = this.$indicationTextBottom;
                    switch (this.$r8$classId) {
                        case 0:
                            KeyguardIndicationAreaBinder.ConfigurationBasedDimensions configurationBasedDimensions = (KeyguardIndicationAreaBinder.ConfigurationBasedDimensions) obj;
                            ((TextView) obj2).setTextSize(0, configurationBasedDimensions.indicationTextSizePx);
                            ((TextView) obj3).setTextSize(0, configurationBasedDimensions.indicationTextSizePx);
                            break;
                        default:
                            ViewGroup viewGroup = (ViewGroup) obj3;
                            KeyguardIndicationAreaBinder.ConfigurationBasedDimensions configurationBasedDimensions2 = new KeyguardIndicationAreaBinder.ConfigurationBasedDimensions(viewGroup.getResources().getDimensionPixelOffset(R.dimen.default_burn_in_prevention_offset), viewGroup.getResources().getDimensionPixelOffset(R.dimen.keyguard_indication_area_padding), viewGroup.getResources().getDimensionPixelSize(android.R.dimen.timepicker_center_dot_radius));
                            StateFlowImpl stateFlowImpl = (StateFlowImpl) ((MutableStateFlow) obj2);
                            stateFlowImpl.getClass();
                            stateFlowImpl.updateState(null, configurationBasedDimensions2);
                            break;
                    }
                    return unit;
                }

                public C01171(MutableStateFlow mutableStateFlow, ViewGroup viewGroup) {
                    this.$indicationText = mutableStateFlow;
                    this.$indicationTextBottom = viewGroup;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(MutableStateFlow mutableStateFlow, TextView textView, TextView textView2, Continuation continuation) {
                super(2, continuation);
                this.$configurationBasedDimensions = mutableStateFlow;
                this.$indicationText = textView;
                this.$indicationTextBottom = textView2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$configurationBasedDimensions, this.$indicationText, this.$indicationTextBottom, continuation);
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
                MutableStateFlow mutableStateFlow = this.$configurationBasedDimensions;
                C01171 c01171 = new C01171(this.$indicationText, this.$indicationTextBottom);
                this.label = 1;
                ((StateFlowImpl) mutableStateFlow).collect(c01171, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$6, reason: invalid class name */
        final class AnonymousClass6 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableStateFlow $configurationBasedDimensions;
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardIndicationAreaViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass6(ViewGroup viewGroup, KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, Continuation continuation, MutableStateFlow mutableStateFlow) {
                super(2, continuation);
                this.$viewModel = keyguardIndicationAreaViewModel;
                this.$configurationBasedDimensions = mutableStateFlow;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass6(this.$view, this.$viewModel, continuation, this.$configurationBasedDimensions);
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
                    FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = this.$viewModel.configurationChange;
                    AnonymousClass5.C01171 c01171 = new AnonymousClass5.C01171(this.$configurationBasedDimensions, this.$view);
                    this.label = 1;
                    if (flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.collect(c01171, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$2$1$7, reason: invalid class name */
        final class AnonymousClass7 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardIndicationController $indicationController;
            final /* synthetic */ KeyguardIndicationAreaViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, KeyguardIndicationController keyguardIndicationController, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardIndicationAreaViewModel;
                this.$indicationController = keyguardIndicationController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass7(this.$viewModel, this.$indicationController, continuation);
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
                    Flow flow = this.$viewModel.visible;
                    AnonymousClass2.C01161 c01161 = new AnonymousClass2.C01161(3, this.$indicationController);
                    this.label = 1;
                    if (flow.collect(c01161, this) == coroutineSingletons) {
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
        public AnonymousClass1(KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, ViewGroup viewGroup, MutableStateFlow mutableStateFlow, TextView textView, TextView textView2, KeyguardIndicationController keyguardIndicationController, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = keyguardIndicationAreaViewModel;
            this.$view = viewGroup;
            this.$configurationBasedDimensions = mutableStateFlow;
            this.$indicationText = textView;
            this.$indicationTextBottom = textView2;
            this.$indicationController = keyguardIndicationController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$view, this.$configurationBasedDimensions, this.$indicationText, this.$indicationTextBottom, this.$indicationController, continuation);
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
            CoroutineTracingKt.launch$default(coroutineScope, null, new C01151(this.$viewModel, this.$view, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass2(this.$viewModel, this.$view, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass3(this.$view, this.$viewModel, null, this.$configurationBasedDimensions), 6);
            MutableStateFlow mutableStateFlow = this.$configurationBasedDimensions;
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass4(this.$view, this.$viewModel, null, mutableStateFlow), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass5(this.$configurationBasedDimensions, this.$indicationText, this.$indicationTextBottom, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass6(this.$view, this.$viewModel, null, this.$configurationBasedDimensions), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass7(this.$viewModel, this.$indicationController, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardIndicationAreaBinder$bind$2(KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, ViewGroup viewGroup, MutableStateFlow mutableStateFlow, TextView textView, TextView textView2, KeyguardIndicationController keyguardIndicationController, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = keyguardIndicationAreaViewModel;
        this.$view = viewGroup;
        this.$configurationBasedDimensions = mutableStateFlow;
        this.$indicationText = textView;
        this.$indicationTextBottom = textView2;
        this.$indicationController = keyguardIndicationController;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardIndicationAreaBinder$bind$2 keyguardIndicationAreaBinder$bind$2 = new KeyguardIndicationAreaBinder$bind$2(this.$viewModel, this.$view, this.$configurationBasedDimensions, this.$indicationText, this.$indicationTextBottom, this.$indicationController, (Continuation) obj3);
        keyguardIndicationAreaBinder$bind$2.L$0 = (LifecycleOwner) obj;
        return keyguardIndicationAreaBinder$bind$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$view, this.$configurationBasedDimensions, this.$indicationText, this.$indicationTextBottom, this.$indicationController, null);
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
