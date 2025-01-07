package com.android.systemui.keyguard.ui.binder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.animation.Interpolators;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.common.shared.model.TintedIcon;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.common.ui.ConfigurationStateImpl;
import com.android.systemui.common.ui.ConfigurationStateImpl$getDimensionPixelSize$$inlined$map$1;
import com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryHapticsInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryHapticsInteractor$special$$inlined$map$2;
import com.android.systemui.deviceentry.shared.model.BiometricMessage;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractorKt;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1;
import com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$2;
import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import com.android.systemui.keyguard.ui.viewmodel.AodBurnInViewModel;
import com.android.systemui.keyguard.ui.viewmodel.BurnInParameters;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel$special$$inlined$map$1;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel$special$$inlined$map$4;
import com.android.systemui.keyguard.ui.viewmodel.OccludingAppDeviceEntryMessageViewModel;
import com.android.systemui.keyguard.ui.viewmodel.ViewStateAccessor;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.temporarydisplay.ViewPriority;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.temporarydisplay.chipbar.ChipbarInfo;
import com.android.systemui.util.ui.AnimatedValue;
import com.android.wm.shell.R;
import com.google.android.msdl.domain.MSDLPlayer;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardRootViewBinder$bind$3 extends SuspendLambda implements Function3 {
    final /* synthetic */ KeyguardBlueprintViewModel $blueprintViewModel;
    final /* synthetic */ MutableStateFlow $burnInParams;
    final /* synthetic */ Map $childViews;
    final /* synthetic */ ChipbarCoordinator $chipbarCoordinator;
    final /* synthetic */ KeyguardClockInteractor $clockInteractor;
    final /* synthetic */ ConfigurationState $configuration;
    final /* synthetic */ DeviceEntryHapticsInteractor $deviceEntryHapticsInteractor;
    final /* synthetic */ InteractionJankMonitor $interactionJankMonitor;
    final /* synthetic */ KeyguardViewMediator $keyguardViewMediator;
    final /* synthetic */ MSDLPlayer $msdlPlayer;
    final /* synthetic */ OccludingAppDeviceEntryMessageViewModel $occludingAppDeviceEntryMessageViewModel;
    final /* synthetic */ ScreenOffAnimationController $screenOffAnimationController;
    final /* synthetic */ ShadeInteractor $shadeInteractor;
    final /* synthetic */ VibratorHelper $vibratorHelper;
    final /* synthetic */ ViewGroup $view;
    final /* synthetic */ KeyguardRootViewModel $viewModel;
    final /* synthetic */ ViewStateAccessor $viewState;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LifecycleOwner $$this$repeatWhenAttached;
        final /* synthetic */ KeyguardBlueprintViewModel $blueprintViewModel;
        final /* synthetic */ MutableStateFlow $burnInParams;
        final /* synthetic */ Map $childViews;
        final /* synthetic */ ChipbarCoordinator $chipbarCoordinator;
        final /* synthetic */ KeyguardClockInteractor $clockInteractor;
        final /* synthetic */ ConfigurationState $configuration;
        final /* synthetic */ DeviceEntryHapticsInteractor $deviceEntryHapticsInteractor;
        final /* synthetic */ InteractionJankMonitor $interactionJankMonitor;
        final /* synthetic */ KeyguardViewMediator $keyguardViewMediator;
        final /* synthetic */ MSDLPlayer $msdlPlayer;
        final /* synthetic */ OccludingAppDeviceEntryMessageViewModel $occludingAppDeviceEntryMessageViewModel;
        final /* synthetic */ ScreenOffAnimationController $screenOffAnimationController;
        final /* synthetic */ ShadeInteractor $shadeInteractor;
        final /* synthetic */ VibratorHelper $vibratorHelper;
        final /* synthetic */ ViewGroup $view;
        final /* synthetic */ KeyguardRootViewModel $viewModel;
        final /* synthetic */ ViewStateAccessor $viewState;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$10, reason: invalid class name */
        final class AnonymousClass10 extends SuspendLambda implements Function2 {
            final /* synthetic */ ShadeInteractor $shadeInteractor;
            final /* synthetic */ ViewGroup $view;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass10(ShadeInteractor shadeInteractor, ViewGroup viewGroup, Continuation continuation) {
                super(2, continuation);
                this.$shadeInteractor = shadeInteractor;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass10(this.$shadeInteractor, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass10) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                ReadonlyStateFlow readonlyStateFlow = ((ShadeInteractorImpl) this.$shadeInteractor).isAnyFullyExpanded;
                AnonymousClass2.C01311 c01311 = new AnonymousClass2.C01311(1, this.$view);
                this.label = 1;
                readonlyStateFlow.collect(c01311, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$11, reason: invalid class name */
        final class AnonymousClass11 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableStateFlow $burnInParams;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass11(MutableStateFlow mutableStateFlow, KeyguardRootViewModel keyguardRootViewModel, Continuation continuation) {
                super(2, continuation);
                this.$burnInParams = mutableStateFlow;
                this.$viewModel = keyguardRootViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass11(this.$burnInParams, this.$viewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass11) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                MutableStateFlow mutableStateFlow = this.$burnInParams;
                AnonymousClass2.C01311 c01311 = new AnonymousClass2.C01311(2, this.$viewModel);
                this.label = 1;
                ((StateFlowImpl) mutableStateFlow).collect(c01311, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$12, reason: invalid class name */
        final class AnonymousClass12 extends SuspendLambda implements Function2 {
            final /* synthetic */ DeviceEntryHapticsInteractor $deviceEntryHapticsInteractor;
            final /* synthetic */ MSDLPlayer $msdlPlayer;
            final /* synthetic */ VibratorHelper $vibratorHelper;
            final /* synthetic */ ViewGroup $view;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$12$1, reason: invalid class name and collision with other inner class name */
            public final class C01301 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ VibratorHelper $vibratorHelper;
                public final /* synthetic */ ViewGroup $view;

                public /* synthetic */ C01301(MSDLPlayer mSDLPlayer, VibratorHelper vibratorHelper, ViewGroup viewGroup, int i) {
                    this.$r8$classId = i;
                    this.$vibratorHelper = vibratorHelper;
                    this.$view = viewGroup;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ViewGroup viewGroup = this.$view;
                            this.$vibratorHelper.getClass();
                            viewGroup.performHapticFeedback(10004);
                            break;
                        default:
                            ViewGroup viewGroup2 = this.$view;
                            this.$vibratorHelper.getClass();
                            viewGroup2.performHapticFeedback(10005);
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass12(DeviceEntryHapticsInteractor deviceEntryHapticsInteractor, MSDLPlayer mSDLPlayer, VibratorHelper vibratorHelper, ViewGroup viewGroup, Continuation continuation) {
                super(2, continuation);
                this.$deviceEntryHapticsInteractor = deviceEntryHapticsInteractor;
                this.$msdlPlayer = mSDLPlayer;
                this.$vibratorHelper = vibratorHelper;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass12(this.$deviceEntryHapticsInteractor, this.$msdlPlayer, this.$vibratorHelper, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass12) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DeviceEntryHapticsInteractor$special$$inlined$map$2 deviceEntryHapticsInteractor$special$$inlined$map$2 = this.$deviceEntryHapticsInteractor.playSuccessHaptic;
                    C01301 c01301 = new C01301(this.$msdlPlayer, this.$vibratorHelper, this.$view, 0);
                    this.label = 1;
                    if (deviceEntryHapticsInteractor$special$$inlined$map$2.collect(c01301, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$13, reason: invalid class name */
        final class AnonymousClass13 extends SuspendLambda implements Function2 {
            final /* synthetic */ DeviceEntryHapticsInteractor $deviceEntryHapticsInteractor;
            final /* synthetic */ MSDLPlayer $msdlPlayer;
            final /* synthetic */ VibratorHelper $vibratorHelper;
            final /* synthetic */ ViewGroup $view;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass13(DeviceEntryHapticsInteractor deviceEntryHapticsInteractor, MSDLPlayer mSDLPlayer, VibratorHelper vibratorHelper, ViewGroup viewGroup, Continuation continuation) {
                super(2, continuation);
                this.$deviceEntryHapticsInteractor = deviceEntryHapticsInteractor;
                this.$msdlPlayer = mSDLPlayer;
                this.$vibratorHelper = vibratorHelper;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass13(this.$deviceEntryHapticsInteractor, this.$msdlPlayer, this.$vibratorHelper, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass13) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DeviceEntryHapticsInteractor$special$$inlined$map$2 deviceEntryHapticsInteractor$special$$inlined$map$2 = this.$deviceEntryHapticsInteractor.playErrorHaptic;
                    AnonymousClass12.C01301 c01301 = new AnonymousClass12.C01301(this.$msdlPlayer, this.$vibratorHelper, this.$view, 1);
                    this.label = 1;
                    if (deviceEntryHapticsInteractor$special$$inlined$map$2.collect(c01301, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ ChipbarCoordinator $chipbarCoordinator;
            final /* synthetic */ OccludingAppDeviceEntryMessageViewModel $occludingAppDeviceEntryMessageViewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$2$1, reason: invalid class name and collision with other inner class name */
            public final class C01311 implements FlowCollector {
                public final /* synthetic */ Object $chipbarCoordinator;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ C01311(int i, Object obj) {
                    this.$r8$classId = i;
                    this.$chipbarCoordinator = obj;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit = Unit.INSTANCE;
                    Object obj2 = this.$chipbarCoordinator;
                    switch (this.$r8$classId) {
                        case 0:
                            BiometricMessage biometricMessage = (BiometricMessage) obj;
                            ChipbarCoordinator chipbarCoordinator = (ChipbarCoordinator) obj2;
                            if ((biometricMessage != null ? biometricMessage.message : null) == null) {
                                Intrinsics.checkNotNull(chipbarCoordinator);
                                chipbarCoordinator.removeView("occluding_app_device_entry_unlock_msg", "occludingAppMsgNull");
                                break;
                            } else {
                                Intrinsics.checkNotNull(chipbarCoordinator);
                                KeyguardRootViewBinder keyguardRootViewBinder = KeyguardRootViewBinder.INSTANCE;
                                String str = biometricMessage.message;
                                TintedIcon tintedIcon = new TintedIcon(new Icon.Resource(R.drawable.ic_lock, null), Integer.valueOf(android.R.^attr-private.materialColorOnSecondaryFixed));
                                Text.Loaded loaded = new Text.Loaded(str);
                                ViewPriority viewPriority = ViewPriority.NORMAL;
                                chipbarCoordinator.displayView(new ChipbarInfo(tintedIcon, loaded, "OccludingAppUnlockMsgChip", "OCCLUDING_APP_UNLOCK_MSG_CHIP", 3500, "occluding_app_device_entry_unlock_msg", null));
                                break;
                            }
                        case 1:
                            ((ViewGroup) obj2).setVisibility(((Boolean) obj).booleanValue() ? 4 : 0);
                            break;
                        default:
                            BurnInParameters burnInParameters = (BurnInParameters) obj;
                            AodBurnInViewModel aodBurnInViewModel = ((KeyguardRootViewModel) obj2).aodBurnInViewModel;
                            aodBurnInViewModel.getClass();
                            int i = burnInParameters.minViewY;
                            int i2 = burnInParameters.topInset;
                            if (i < i2) {
                                Log.w("AodBurnInViewModel", "minViewY is below topInset: " + burnInParameters);
                                burnInParameters = BurnInParameters.copy$default(burnInParameters, 0, i2, null, 5);
                            }
                            StateFlowImpl stateFlowImpl = aodBurnInViewModel.burnInParams;
                            stateFlowImpl.getClass();
                            stateFlowImpl.updateState(null, burnInParameters);
                            break;
                    }
                    return unit;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(OccludingAppDeviceEntryMessageViewModel occludingAppDeviceEntryMessageViewModel, ChipbarCoordinator chipbarCoordinator, Continuation continuation) {
                super(2, continuation);
                this.$occludingAppDeviceEntryMessageViewModel = occludingAppDeviceEntryMessageViewModel;
                this.$chipbarCoordinator = chipbarCoordinator;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$occludingAppDeviceEntryMessageViewModel, this.$chipbarCoordinator, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ChannelFlowTransformLatest channelFlowTransformLatest;
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    OccludingAppDeviceEntryMessageViewModel occludingAppDeviceEntryMessageViewModel = this.$occludingAppDeviceEntryMessageViewModel;
                    if (occludingAppDeviceEntryMessageViewModel != null && (channelFlowTransformLatest = occludingAppDeviceEntryMessageViewModel.message) != null) {
                        C01311 c01311 = new C01311(0, this.$chipbarCoordinator);
                        this.label = 1;
                        if (channelFlowTransformLatest.collect(c01311, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ Map $childViews;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(KeyguardRootViewModel keyguardRootViewModel, Map map, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardRootViewModel;
                this.$childViews = map;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$viewModel, this.$childViews, continuation);
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
                    KeyguardRootViewModel$special$$inlined$map$1 keyguardRootViewModel$special$$inlined$map$1 = this.$viewModel.burnInLayerVisibility;
                    KeyguardRootViewBinder$bind$2.AnonymousClass1.AnonymousClass3.C01291 c01291 = new KeyguardRootViewBinder$bind$2.AnonymousClass1.AnonymousClass3.C01291(2, this.$childViews);
                    this.label = 1;
                    if (keyguardRootViewModel$special$$inlined$map$1.collect(c01291, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ Map $childViews;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(KeyguardRootViewModel keyguardRootViewModel, Map map, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardRootViewModel;
                this.$childViews = map;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$viewModel, this.$childViews, continuation);
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
                    SafeFlow safeFlow = this.$viewModel.burnInLayerAlpha;
                    KeyguardRootViewBinder$bind$2.AnonymousClass1.AnonymousClass3.C01291 c01291 = new KeyguardRootViewBinder$bind$2.AnonymousClass1.AnonymousClass3.C01291(3, this.$childViews);
                    this.label = 1;
                    if (safeFlow.collect(c01291, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ Map $childViews;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            final /* synthetic */ ViewStateAccessor $viewState;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(KeyguardRootViewModel keyguardRootViewModel, ViewStateAccessor viewStateAccessor, Map map, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardRootViewModel;
                this.$viewState = viewStateAccessor;
                this.$childViews = map;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$viewModel, this.$viewState, this.$childViews, continuation);
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
                    KeyguardRootViewModel keyguardRootViewModel = this.$viewModel;
                    Flow lockscreenAlpha = keyguardRootViewModel.aodToLockscreenTransitionViewModel.lockscreenAlpha(this.$viewState);
                    KeyguardRootViewBinder$bind$2.AnonymousClass1.AnonymousClass3.C01291 c01291 = new KeyguardRootViewBinder$bind$2.AnonymousClass1.AnonymousClass3.C01291(4, this.$childViews);
                    this.label = 1;
                    if (((KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1) lockscreenAlpha).collect(c01291, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$6, reason: invalid class name */
        final class AnonymousClass6 extends SuspendLambda implements Function2 {
            final /* synthetic */ Map $childViews;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass6(KeyguardRootViewModel keyguardRootViewModel, Map map, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardRootViewModel;
                this.$childViews = map;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass6(this.$viewModel, this.$childViews, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                KeyguardRootViewModel$special$$inlined$map$4 keyguardRootViewModel$special$$inlined$map$4 = this.$viewModel.scale;
                KeyguardRootViewBinder$bind$2.AnonymousClass1.AnonymousClass3.C01291 c01291 = new KeyguardRootViewBinder$bind$2.AnonymousClass1.AnonymousClass3.C01291(5, this.$childViews);
                this.label = 1;
                keyguardRootViewModel$special$$inlined$map$4.collect(c01291, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$7, reason: invalid class name */
        final class AnonymousClass7 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardBlueprintViewModel $blueprintViewModel;
            final /* synthetic */ Map $childViews;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(KeyguardBlueprintViewModel keyguardBlueprintViewModel, Map map, KeyguardRootViewModel keyguardRootViewModel, Continuation continuation) {
                super(2, continuation);
                this.$blueprintViewModel = keyguardBlueprintViewModel;
                this.$childViews = map;
                this.$viewModel = keyguardRootViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass7(this.$blueprintViewModel, this.$childViews, this.$viewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass7) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                ReadonlyStateFlow readonlyStateFlow = this.$blueprintViewModel.currentTransition;
                KeyguardRootViewBinder$bind$2.AnonymousClass1.C01271.C01281 c01281 = new KeyguardRootViewBinder$bind$2.AnonymousClass1.C01271.C01281(2, this.$childViews, this.$viewModel);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c01281, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$8, reason: invalid class name */
        final class AnonymousClass8 extends SuspendLambda implements Function2 {
            final /* synthetic */ KeyguardBlueprintViewModel $blueprintViewModel;
            final /* synthetic */ Map $childViews;
            final /* synthetic */ ConfigurationState $configuration;
            final /* synthetic */ ScreenOffAnimationController $screenOffAnimationController;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3$1$8$1, reason: invalid class name and collision with other inner class name */
            public final class C01321 implements FlowCollector {
                public final /* synthetic */ Object $blueprintViewModel;
                public final /* synthetic */ Object $childViews;
                public final /* synthetic */ Object $iconsAppearTranslationPx;
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ Object $screenOffAnimationController;

                public /* synthetic */ C01321(Object obj, Object obj2, Object obj3, Object obj4, int i) {
                    this.$r8$classId = i;
                    this.$blueprintViewModel = obj;
                    this.$childViews = obj2;
                    this.$iconsAppearTranslationPx = obj3;
                    this.$screenOffAnimationController = obj4;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Object obj2;
                    Object obj3;
                    Object obj4;
                    String str;
                    Unit unit = Unit.INSTANCE;
                    Object obj5 = this.$childViews;
                    Object obj6 = this.$blueprintViewModel;
                    Object obj7 = this.$iconsAppearTranslationPx;
                    switch (this.$r8$classId) {
                        case 0:
                            final AnimatedValue animatedValue = (AnimatedValue) obj;
                            if (animatedValue instanceof AnimatedValue.Animating) {
                                obj2 = ((AnimatedValue.Animating) animatedValue).value;
                            } else {
                                if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                obj2 = ((AnimatedValue.NotAnimating) animatedValue).value;
                            }
                            if (((Boolean) obj2).booleanValue()) {
                                ((KeyguardBlueprintViewModel) obj6).keyguardBlueprintInteractor.refreshBlueprint(IntraBlueprintTransition.Type.NoTransition);
                            }
                            KeyguardRootViewBinder keyguardRootViewBinder = KeyguardRootViewBinder.INSTANCE;
                            final View view = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.aod_notification_icon_container, (Map) obj5);
                            if (view != null) {
                                ((Number) ((StateFlow) obj7).getValue()).intValue();
                                view.animate().cancel();
                                AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$setAodNotifIconContainerIsVisible$animatorListener$1
                                    /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationEnd(Animator animator) {
                                        AnimatedValue animatedValue2 = AnimatedValue.this;
                                        if (animatedValue2 instanceof AnimatedValue.Animating) {
                                            ((AnimatedValue.Animating) animatedValue2).onStopAnimating.invoke();
                                        }
                                    }
                                };
                                boolean z = animatedValue instanceof AnimatedValue.Animating;
                                int i = 4;
                                if (z) {
                                    if (animatedValue instanceof AnimatedValue.Animating) {
                                        obj3 = ((AnimatedValue.Animating) animatedValue).value;
                                    } else {
                                        if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                                            throw new NoWhenBranchMatchedException();
                                        }
                                        obj3 = ((AnimatedValue.NotAnimating) animatedValue).value;
                                    }
                                    if (((Boolean) obj3).booleanValue()) {
                                        view.animate().cancel();
                                        if (view.getVisibility() == 4) {
                                            view.setAlpha(0.0f);
                                            view.setVisibility(0);
                                        }
                                        view.animate().alpha(1.0f).setDuration(210L).setStartDelay(0).setInterpolator(Interpolators.ALPHA_IN).setListener(animatorListenerAdapter);
                                        if (view.hasOverlappingRendering() && view.getLayerType() != 2) {
                                            view.animate().withLayer();
                                        }
                                    } else {
                                        view.animate().cancel();
                                        view.animate().alpha(0.0f).setDuration(210L).setInterpolator(Interpolators.ALPHA_OUT).setStartDelay(0).withEndAction(new Runnable() { // from class: com.android.systemui.statusbar.CrossFadeHelper$$ExternalSyntheticLambda0
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                View view2 = view;
                                                if (view2.getVisibility() != 8) {
                                                    view2.setVisibility(4);
                                                }
                                            }
                                        }).setListener(animatorListenerAdapter);
                                        if (view.hasOverlappingRendering()) {
                                            view.animate().withLayer();
                                        }
                                    }
                                } else {
                                    if (z) {
                                        obj4 = ((AnimatedValue.Animating) animatedValue).value;
                                    } else {
                                        if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                                            throw new NoWhenBranchMatchedException();
                                        }
                                        obj4 = ((AnimatedValue.NotAnimating) animatedValue).value;
                                    }
                                    if (((Boolean) obj4).booleanValue()) {
                                        view.setAlpha(1.0f);
                                        i = 0;
                                    } else {
                                        view.setAlpha(0.0f);
                                    }
                                    view.setVisibility(i);
                                }
                            }
                            return unit;
                        default:
                            int ordinal = ((TransitionStep) obj).transitionState.ordinal();
                            if (ordinal == 0) {
                                ClockController clockController = ((KeyguardClockInteractor) obj6).clock$receiver.clock;
                                if (clockController == null || (str = clockController.getConfig().getId()) == null) {
                                    Log.e(KeyguardClockInteractorKt.TAG, "No clock is available");
                                    str = "CLOCK_MISSING";
                                }
                                ((InteractionJankMonitor) obj7).begin(InteractionJankMonitor.Configuration.Builder.withView(41, (ViewGroup) obj5).setTag(str));
                            } else if (ordinal == 2) {
                                KeyguardViewMediator keyguardViewMediator = (KeyguardViewMediator) this.$screenOffAnimationController;
                                if (keyguardViewMediator != null) {
                                    keyguardViewMediator.maybeHandlePendingLock();
                                }
                                ((InteractionJankMonitor) obj7).end(41);
                            } else if (ordinal == 3) {
                                ((InteractionJankMonitor) obj7).cancel(41);
                            }
                            return unit;
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass8(ConfigurationState configurationState, KeyguardRootViewModel keyguardRootViewModel, KeyguardBlueprintViewModel keyguardBlueprintViewModel, Map map, ScreenOffAnimationController screenOffAnimationController, Continuation continuation) {
                super(2, continuation);
                this.$configuration = configurationState;
                this.$viewModel = keyguardRootViewModel;
                this.$blueprintViewModel = keyguardBlueprintViewModel;
                this.$childViews = map;
                this.$screenOffAnimationController = screenOffAnimationController;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass8 anonymousClass8 = new AnonymousClass8(this.$configuration, this.$viewModel, this.$blueprintViewModel, this.$childViews, this.$screenOffAnimationController, continuation);
                anonymousClass8.L$0 = obj;
                return anonymousClass8;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass8) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    ConfigurationStateImpl$getDimensionPixelSize$$inlined$map$1 dimensionPixelSize = ((ConfigurationStateImpl) this.$configuration).getDimensionPixelSize(R.dimen.shelf_appear_translation);
                    this.label = 1;
                    obj = FlowKt.stateIn(dimensionPixelSize, coroutineScope, this);
                    if (obj == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
                    }
                    ResultKt.throwOnFailure(obj);
                }
                StateFlow stateFlow = (StateFlow) obj;
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.isNotifIconContainerVisible;
                C01321 c01321 = new C01321(this.$blueprintViewModel, this.$childViews, stateFlow, this.$screenOffAnimationController, 0);
                this.label = 2;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c01321, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ViewGroup viewGroup, InteractionJankMonitor interactionJankMonitor, DeviceEntryHapticsInteractor deviceEntryHapticsInteractor, VibratorHelper vibratorHelper, LifecycleOwner lifecycleOwner, OccludingAppDeviceEntryMessageViewModel occludingAppDeviceEntryMessageViewModel, ChipbarCoordinator chipbarCoordinator, KeyguardRootViewModel keyguardRootViewModel, Map map, ViewStateAccessor viewStateAccessor, KeyguardBlueprintViewModel keyguardBlueprintViewModel, ConfigurationState configurationState, ScreenOffAnimationController screenOffAnimationController, KeyguardClockInteractor keyguardClockInteractor, KeyguardViewMediator keyguardViewMediator, ShadeInteractor shadeInteractor, MutableStateFlow mutableStateFlow, MSDLPlayer mSDLPlayer, Continuation continuation) {
            super(2, continuation);
            this.$view = viewGroup;
            this.$interactionJankMonitor = interactionJankMonitor;
            this.$deviceEntryHapticsInteractor = deviceEntryHapticsInteractor;
            this.$vibratorHelper = vibratorHelper;
            this.$$this$repeatWhenAttached = lifecycleOwner;
            this.$occludingAppDeviceEntryMessageViewModel = occludingAppDeviceEntryMessageViewModel;
            this.$chipbarCoordinator = chipbarCoordinator;
            this.$viewModel = keyguardRootViewModel;
            this.$childViews = map;
            this.$viewState = viewStateAccessor;
            this.$blueprintViewModel = keyguardBlueprintViewModel;
            this.$configuration = configurationState;
            this.$screenOffAnimationController = screenOffAnimationController;
            this.$clockInteractor = keyguardClockInteractor;
            this.$keyguardViewMediator = keyguardViewMediator;
            this.$shadeInteractor = shadeInteractor;
            this.$burnInParams = mutableStateFlow;
            this.$msdlPlayer = mSDLPlayer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$interactionJankMonitor, this.$deviceEntryHapticsInteractor, this.$vibratorHelper, this.$$this$repeatWhenAttached, this.$occludingAppDeviceEntryMessageViewModel, this.$chipbarCoordinator, this.$viewModel, this.$childViews, this.$viewState, this.$blueprintViewModel, this.$configuration, this.$screenOffAnimationController, this.$clockInteractor, this.$keyguardViewMediator, this.$shadeInteractor, this.$burnInParams, this.$msdlPlayer, continuation);
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
            VibratorHelper vibratorHelper;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$occludingAppDeviceEntryMessageViewModel, this.$chipbarCoordinator, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.$childViews, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$viewModel, this.$childViews, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$viewModel, this.$viewState, this.$childViews, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass6(this.$viewModel, this.$childViews, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass7(this.$blueprintViewModel, this.$childViews, this.$viewModel, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass8(this.$configuration, this.$viewModel, this.$blueprintViewModel, this.$childViews, this.$screenOffAnimationController, null), 3);
            InteractionJankMonitor interactionJankMonitor = this.$interactionJankMonitor;
            if (interactionJankMonitor != null) {
                BuildersKt.launch$default(coroutineScope, null, null, new KeyguardRootViewBinder$bind$3$1$9$1(this.$viewModel, this.$clockInteractor, this.$view, interactionJankMonitor, this.$keyguardViewMediator, null), 3);
            }
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass10(this.$shadeInteractor, this.$view, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass11(this.$burnInParams, this.$viewModel, null), 3);
            DeviceEntryHapticsInteractor deviceEntryHapticsInteractor = this.$deviceEntryHapticsInteractor;
            if (deviceEntryHapticsInteractor != null && (vibratorHelper = this.$vibratorHelper) != null) {
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass12(deviceEntryHapticsInteractor, this.$msdlPlayer, vibratorHelper, this.$view, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass13(this.$deviceEntryHapticsInteractor, this.$msdlPlayer, this.$vibratorHelper, this.$view, null), 3);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardRootViewBinder$bind$3(ViewGroup viewGroup, InteractionJankMonitor interactionJankMonitor, DeviceEntryHapticsInteractor deviceEntryHapticsInteractor, VibratorHelper vibratorHelper, OccludingAppDeviceEntryMessageViewModel occludingAppDeviceEntryMessageViewModel, ChipbarCoordinator chipbarCoordinator, KeyguardRootViewModel keyguardRootViewModel, Map map, ViewStateAccessor viewStateAccessor, KeyguardBlueprintViewModel keyguardBlueprintViewModel, ConfigurationState configurationState, ScreenOffAnimationController screenOffAnimationController, KeyguardClockInteractor keyguardClockInteractor, KeyguardViewMediator keyguardViewMediator, ShadeInteractor shadeInteractor, MutableStateFlow mutableStateFlow, MSDLPlayer mSDLPlayer, Continuation continuation) {
        super(3, continuation);
        this.$view = viewGroup;
        this.$interactionJankMonitor = interactionJankMonitor;
        this.$deviceEntryHapticsInteractor = deviceEntryHapticsInteractor;
        this.$vibratorHelper = vibratorHelper;
        this.$occludingAppDeviceEntryMessageViewModel = occludingAppDeviceEntryMessageViewModel;
        this.$chipbarCoordinator = chipbarCoordinator;
        this.$viewModel = keyguardRootViewModel;
        this.$childViews = map;
        this.$viewState = viewStateAccessor;
        this.$blueprintViewModel = keyguardBlueprintViewModel;
        this.$configuration = configurationState;
        this.$screenOffAnimationController = screenOffAnimationController;
        this.$clockInteractor = keyguardClockInteractor;
        this.$keyguardViewMediator = keyguardViewMediator;
        this.$shadeInteractor = shadeInteractor;
        this.$burnInParams = mutableStateFlow;
        this.$msdlPlayer = mSDLPlayer;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardRootViewBinder$bind$3 keyguardRootViewBinder$bind$3 = new KeyguardRootViewBinder$bind$3(this.$view, this.$interactionJankMonitor, this.$deviceEntryHapticsInteractor, this.$vibratorHelper, this.$occludingAppDeviceEntryMessageViewModel, this.$chipbarCoordinator, this.$viewModel, this.$childViews, this.$viewState, this.$blueprintViewModel, this.$configuration, this.$screenOffAnimationController, this.$clockInteractor, this.$keyguardViewMediator, this.$shadeInteractor, this.$burnInParams, this.$msdlPlayer, (Continuation) obj3);
        keyguardRootViewBinder$bind$3.L$0 = (LifecycleOwner) obj;
        return keyguardRootViewBinder$bind$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$interactionJankMonitor, this.$deviceEntryHapticsInteractor, this.$vibratorHelper, lifecycleOwner, this.$occludingAppDeviceEntryMessageViewModel, this.$chipbarCoordinator, this.$viewModel, this.$childViews, this.$viewState, this.$blueprintViewModel, this.$configuration, this.$screenOffAnimationController, this.$clockInteractor, this.$keyguardViewMediator, this.$shadeInteractor, this.$burnInParams, this.$msdlPlayer, null);
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
