package com.android.systemui.keyguard.ui.binder;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.common.ui.view.LongPressHandlingView;
import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import com.android.systemui.keyguard.ui.viewmodel.BurnInOffsets;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.wm.shell.R;
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
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryIconViewBinder$bind$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ CoroutineScope $applicationScope;
    final /* synthetic */ ImageView $bgView;
    final /* synthetic */ LongPressHandlingView $longPressHandlingView;
    final /* synthetic */ VibratorHelper $vibratorHelper;
    final /* synthetic */ DeviceEntryIconView $view;
    final /* synthetic */ DeviceEntryIconViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ CoroutineScope $applicationScope;
        final /* synthetic */ ImageView $bgView;
        final /* synthetic */ LongPressHandlingView $longPressHandlingView;
        final /* synthetic */ VibratorHelper $vibratorHelper;
        final /* synthetic */ DeviceEntryIconView $view;
        final /* synthetic */ DeviceEntryIconViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$1, reason: invalid class name and collision with other inner class name */
        final class C00971 extends SuspendLambda implements Function2 {
            final /* synthetic */ LongPressHandlingView $longPressHandlingView;
            final /* synthetic */ DeviceEntryIconView $view;
            final /* synthetic */ DeviceEntryIconViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00971(DeviceEntryIconViewModel deviceEntryIconViewModel, LongPressHandlingView longPressHandlingView, DeviceEntryIconView deviceEntryIconView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = deviceEntryIconViewModel;
                this.$longPressHandlingView = longPressHandlingView;
                this.$view = deviceEntryIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00971(this.$viewModel, this.$longPressHandlingView, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00971) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = this.$viewModel.isVisible;
                    AnonymousClass3.C00981 c00981 = new AnonymousClass3.C00981(this.$longPressHandlingView, this.$view, 1);
                    this.label = 1;
                    if (flow.collect(c00981, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ LongPressHandlingView $longPressHandlingView;
            final /* synthetic */ DeviceEntryIconViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(DeviceEntryIconViewModel deviceEntryIconViewModel, LongPressHandlingView longPressHandlingView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = deviceEntryIconViewModel;
                this.$longPressHandlingView = longPressHandlingView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.$longPressHandlingView, continuation);
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
                    FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = this.$viewModel.isLongPressEnabled;
                    AnonymousClass6.C01041 c01041 = new AnonymousClass6.C01041(this.$longPressHandlingView);
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$unsafeFlow$1.collect(c01041, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ LongPressHandlingView $longPressHandlingView;
            final /* synthetic */ DeviceEntryIconView $view;
            final /* synthetic */ DeviceEntryIconViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$3$1, reason: invalid class name and collision with other inner class name */
            public final class C00981 implements FlowCollector {
                public final /* synthetic */ LongPressHandlingView $longPressHandlingView;
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ DeviceEntryIconView $view;

                public /* synthetic */ C00981(LongPressHandlingView longPressHandlingView, DeviceEntryIconView deviceEntryIconView, int i) {
                    this.$r8$classId = i;
                    this.$longPressHandlingView = longPressHandlingView;
                    this.$view = deviceEntryIconView;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            boolean booleanValue = ((Boolean) obj).booleanValue();
                            final DeviceEntryIconView deviceEntryIconView = this.$view;
                            this.$longPressHandlingView.getInteractionHandler().longPressDuration = booleanValue ? new Function0() { // from class: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder.bind.2.1.3.1.1
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Long.valueOf(DeviceEntryIconView.this.getResources().getInteger(R.integer.config_udfpsDeviceEntryIconLongPress));
                                }
                            } : new Function0() { // from class: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder.bind.2.1.3.1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Long.valueOf(DeviceEntryIconView.this.getResources().getInteger(R.integer.config_lockIconLongPress));
                                }
                            };
                            break;
                        default:
                            boolean booleanValue2 = ((Boolean) obj).booleanValue();
                            this.$longPressHandlingView.setVisibility(!booleanValue2 ? 4 : 0);
                            this.$view.setClickable(booleanValue2);
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(DeviceEntryIconViewModel deviceEntryIconViewModel, LongPressHandlingView longPressHandlingView, DeviceEntryIconView deviceEntryIconView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = deviceEntryIconViewModel;
                this.$longPressHandlingView = longPressHandlingView;
                this.$view = deviceEntryIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$viewModel, this.$longPressHandlingView, this.$view, continuation);
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
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.isUdfpsSupported;
                C00981 c00981 = new C00981(this.$longPressHandlingView, this.$view, 0);
                this.label = 1;
                readonlyStateFlow.collect(c00981, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ CoroutineScope $applicationScope;
            final /* synthetic */ VibratorHelper $vibratorHelper;
            final /* synthetic */ DeviceEntryIconView $view;
            final /* synthetic */ DeviceEntryIconViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(DeviceEntryIconViewModel deviceEntryIconViewModel, DeviceEntryIconView deviceEntryIconView, VibratorHelper vibratorHelper, CoroutineScope coroutineScope, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = deviceEntryIconViewModel;
                this.$view = deviceEntryIconView;
                this.$vibratorHelper = vibratorHelper;
                this.$applicationScope = coroutineScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$viewModel, this.$view, this.$vibratorHelper, this.$applicationScope, continuation);
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
                    final DeviceEntryIconViewModel deviceEntryIconViewModel = this.$viewModel;
                    ChannelFlowTransformLatest channelFlowTransformLatest = deviceEntryIconViewModel.accessibilityDelegateHint;
                    final DeviceEntryIconView deviceEntryIconView = this.$view;
                    final VibratorHelper vibratorHelper = this.$vibratorHelper;
                    final CoroutineScope coroutineScope = this.$applicationScope;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder.bind.2.1.4.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            DeviceEntryIconView.AccessibilityHintType accessibilityHintType = (DeviceEntryIconView.AccessibilityHintType) obj2;
                            final DeviceEntryIconView deviceEntryIconView2 = DeviceEntryIconView.this;
                            deviceEntryIconView2.accessibilityHintType = accessibilityHintType;
                            if (accessibilityHintType != DeviceEntryIconView.AccessibilityHintType.NONE) {
                                final VibratorHelper vibratorHelper2 = vibratorHelper;
                                final CoroutineScope coroutineScope2 = coroutineScope;
                                final DeviceEntryIconViewModel deviceEntryIconViewModel2 = deviceEntryIconViewModel;
                                deviceEntryIconView2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder.bind.2.1.4.1.1

                                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                                    /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$4$1$1$1, reason: invalid class name and collision with other inner class name */
                                    final class C01021 extends SuspendLambda implements Function2 {
                                        final /* synthetic */ DeviceEntryIconView $view;
                                        final /* synthetic */ DeviceEntryIconViewModel $viewModel;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        public C01021(DeviceEntryIconView deviceEntryIconView, DeviceEntryIconViewModel deviceEntryIconViewModel, Continuation continuation) {
                                            super(2, continuation);
                                            this.$view = deviceEntryIconView;
                                            this.$viewModel = deviceEntryIconViewModel;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation create(Object obj, Continuation continuation) {
                                            return new C01021(this.$view, this.$viewModel, continuation);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(Object obj, Object obj2) {
                                            return ((C01021) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object obj) {
                                            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                                            int i = this.label;
                                            if (i == 0) {
                                                ResultKt.throwOnFailure(obj);
                                                this.$view.clearFocus();
                                                this.$view.clearAccessibilityFocus();
                                                DeviceEntryIconViewModel deviceEntryIconViewModel = this.$viewModel;
                                                this.label = 1;
                                                if (deviceEntryIconViewModel.onUserInteraction(this) == coroutineSingletons) {
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

                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view) {
                                        VibratorHelper vibratorHelper3 = vibratorHelper2;
                                        DeviceEntryIconView deviceEntryIconView3 = deviceEntryIconView2;
                                        vibratorHelper3.getClass();
                                        deviceEntryIconView3.performHapticFeedback(16);
                                        BuildersKt.launch$default(coroutineScope2, null, null, new C01021(deviceEntryIconView2, deviceEntryIconViewModel2, null), 3);
                                    }
                                });
                            } else {
                                deviceEntryIconView2.setOnClickListener(null);
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (channelFlowTransformLatest.collect(flowCollector, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ ImageView $bgView;
            final /* synthetic */ DeviceEntryIconViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$5$1, reason: invalid class name and collision with other inner class name */
            public final class C01031 implements FlowCollector {
                public final /* synthetic */ ImageView $bgView;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ C01031(ImageView imageView, int i) {
                    this.$r8$classId = i;
                    this.$bgView = imageView;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            if (((Boolean) obj).booleanValue()) {
                                this.$bgView.setVisibility(0);
                            } else {
                                this.$bgView.setVisibility(8);
                            }
                            break;
                        case 1:
                            this.$bgView.setAlpha(((Number) obj).floatValue());
                            break;
                        default:
                            this.$bgView.setImageTintList(ColorStateList.valueOf(((Number) obj).intValue()));
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(DeviceEntryIconViewModel deviceEntryIconViewModel, ImageView imageView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = deviceEntryIconViewModel;
                this.$bgView = imageView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$viewModel, this.$bgView, continuation);
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
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.useBackgroundProtection;
                C01031 c01031 = new C01031(this.$bgView, 0);
                this.label = 1;
                readonlyStateFlow.collect(c01031, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$6, reason: invalid class name */
        final class AnonymousClass6 extends SuspendLambda implements Function2 {
            final /* synthetic */ DeviceEntryIconView $view;
            final /* synthetic */ DeviceEntryIconViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$6$1, reason: invalid class name and collision with other inner class name */
            public final class C01041 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ Object $view;

                public C01041(LongPressHandlingView longPressHandlingView) {
                    this.$r8$classId = 2;
                    this.$view = longPressHandlingView;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            BurnInOffsets burnInOffsets = (BurnInOffsets) obj;
                            float f = burnInOffsets.x;
                            DeviceEntryIconView deviceEntryIconView = (DeviceEntryIconView) this.$view;
                            deviceEntryIconView.setTranslationX(f);
                            deviceEntryIconView.setTranslationY(burnInOffsets.y);
                            deviceEntryIconView.aodFpDrawable.setProgress(burnInOffsets.progress);
                            break;
                        case 1:
                            ((DeviceEntryIconView) this.$view).setAlpha(((Number) obj).floatValue());
                            break;
                        default:
                            ((LongPressHandlingView) this.$view).getInteractionHandler().isLongPressHandlingEnabled = ((Boolean) obj).booleanValue();
                            break;
                    }
                    return Unit.INSTANCE;
                }

                public /* synthetic */ C01041(DeviceEntryIconView deviceEntryIconView, int i) {
                    this.$r8$classId = i;
                    this.$view = deviceEntryIconView;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass6(DeviceEntryIconView deviceEntryIconView, DeviceEntryIconViewModel deviceEntryIconViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = deviceEntryIconViewModel;
                this.$view = deviceEntryIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass6(this.$view, this.$viewModel, continuation);
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
                    Flow flow = this.$viewModel.burnInOffsets;
                    C01041 c01041 = new C01041(this.$view, 0);
                    this.label = 1;
                    if (flow.collect(c01041, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$2$1$7, reason: invalid class name */
        final class AnonymousClass7 extends SuspendLambda implements Function2 {
            final /* synthetic */ DeviceEntryIconView $view;
            final /* synthetic */ DeviceEntryIconViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(DeviceEntryIconView deviceEntryIconView, DeviceEntryIconViewModel deviceEntryIconViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = deviceEntryIconViewModel;
                this.$view = deviceEntryIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass7(this.$view, this.$viewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass7) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.deviceEntryViewAlpha;
                AnonymousClass6.C01041 c01041 = new AnonymousClass6.C01041(this.$view, 1);
                this.label = 1;
                readonlyStateFlow.collect(c01041, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DeviceEntryIconViewModel deviceEntryIconViewModel, LongPressHandlingView longPressHandlingView, DeviceEntryIconView deviceEntryIconView, VibratorHelper vibratorHelper, CoroutineScope coroutineScope, ImageView imageView, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = deviceEntryIconViewModel;
            this.$longPressHandlingView = longPressHandlingView;
            this.$view = deviceEntryIconView;
            this.$vibratorHelper = vibratorHelper;
            this.$applicationScope = coroutineScope;
            this.$bgView = imageView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$longPressHandlingView, this.$view, this.$vibratorHelper, this.$applicationScope, this.$bgView, continuation);
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
            CoroutineTracingKt.launch$default(coroutineScope, null, new C00971(this.$viewModel, this.$longPressHandlingView, this.$view, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass2(this.$viewModel, this.$longPressHandlingView, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass3(this.$viewModel, this.$longPressHandlingView, this.$view, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass4(this.$viewModel, this.$view, this.$vibratorHelper, this.$applicationScope, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass5(this.$viewModel, this.$bgView, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass6(this.$view, this.$viewModel, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass7(this.$view, this.$viewModel, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryIconViewBinder$bind$2(DeviceEntryIconViewModel deviceEntryIconViewModel, LongPressHandlingView longPressHandlingView, DeviceEntryIconView deviceEntryIconView, VibratorHelper vibratorHelper, CoroutineScope coroutineScope, ImageView imageView, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = deviceEntryIconViewModel;
        this.$longPressHandlingView = longPressHandlingView;
        this.$view = deviceEntryIconView;
        this.$vibratorHelper = vibratorHelper;
        this.$applicationScope = coroutineScope;
        this.$bgView = imageView;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DeviceEntryIconViewBinder$bind$2 deviceEntryIconViewBinder$bind$2 = new DeviceEntryIconViewBinder$bind$2(this.$viewModel, this.$longPressHandlingView, this.$view, this.$vibratorHelper, this.$applicationScope, this.$bgView, (Continuation) obj3);
        deviceEntryIconViewBinder$bind$2.L$0 = (LifecycleOwner) obj;
        return deviceEntryIconViewBinder$bind$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$longPressHandlingView, this.$view, this.$vibratorHelper, this.$applicationScope, this.$bgView, null);
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
