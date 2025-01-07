package com.android.systemui.statusbar.pipeline.wifi.ui.binder;

import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.common.ui.binder.IconViewBinder;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.pipeline.wifi.ui.model.WifiIcon;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.LocationBasedWifiViewModel;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class WifiViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ View $activityContainerView;
    final /* synthetic */ ImageView $activityInView;
    final /* synthetic */ ImageView $activityOutView;
    final /* synthetic */ View $airplaneSpacer;
    final /* synthetic */ MutableStateFlow $decorTint;
    final /* synthetic */ StatusBarIconView $dotView;
    final /* synthetic */ ViewGroup $groupView;
    final /* synthetic */ MutableStateFlow $iconTint;
    final /* synthetic */ ImageView $iconView;
    final /* synthetic */ Ref$BooleanRef $isCollecting;
    final /* synthetic */ View $signalSpacer;
    final /* synthetic */ ViewGroup $view;
    final /* synthetic */ LocationBasedWifiViewModel $viewModel;
    final /* synthetic */ MutableStateFlow $visibilityState;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ View $activityContainerView;
        final /* synthetic */ ImageView $activityInView;
        final /* synthetic */ ImageView $activityOutView;
        final /* synthetic */ View $airplaneSpacer;
        final /* synthetic */ MutableStateFlow $decorTint;
        final /* synthetic */ StatusBarIconView $dotView;
        final /* synthetic */ ViewGroup $groupView;
        final /* synthetic */ MutableStateFlow $iconTint;
        final /* synthetic */ ImageView $iconView;
        final /* synthetic */ Ref$BooleanRef $isCollecting;
        final /* synthetic */ View $signalSpacer;
        final /* synthetic */ ViewGroup $view;
        final /* synthetic */ LocationBasedWifiViewModel $viewModel;
        final /* synthetic */ MutableStateFlow $visibilityState;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C02441 extends SuspendLambda implements Function2 {
            final /* synthetic */ StatusBarIconView $dotView;
            final /* synthetic */ ViewGroup $groupView;
            final /* synthetic */ MutableStateFlow $visibilityState;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C02451 implements FlowCollector {
                public final /* synthetic */ Object $dotView;
                public final /* synthetic */ ViewGroup $groupView;
                public final /* synthetic */ int $r8$classId = 1;

                public C02451(ViewGroup viewGroup, ImageView imageView) {
                    this.$groupView = viewGroup;
                    this.$dotView = imageView;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            int intValue = ((Number) obj).intValue();
                            ViewGroup viewGroup = this.$groupView;
                            StatusBarIconView statusBarIconView = (StatusBarIconView) this.$dotView;
                            if (intValue == 0) {
                                viewGroup.setVisibility(0);
                                statusBarIconView.setVisibility(8);
                            } else if (intValue == 1) {
                                viewGroup.setVisibility(4);
                                statusBarIconView.setVisibility(0);
                            } else if (intValue == 2) {
                                viewGroup.setVisibility(4);
                                statusBarIconView.setVisibility(4);
                            }
                            break;
                        default:
                            WifiIcon wifiIcon = (WifiIcon) obj;
                            boolean z = wifiIcon instanceof WifiIcon.Visible;
                            this.$groupView.setVisibility(z ? 0 : 8);
                            if (z) {
                                IconViewBinder.bind(((WifiIcon.Visible) wifiIcon).icon, (ImageView) this.$dotView);
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }

                public C02451(ViewGroup viewGroup, StatusBarIconView statusBarIconView) {
                    this.$groupView = viewGroup;
                    this.$dotView = statusBarIconView;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02441(MutableStateFlow mutableStateFlow, ViewGroup viewGroup, StatusBarIconView statusBarIconView, Continuation continuation) {
                super(2, continuation);
                this.$visibilityState = mutableStateFlow;
                this.$groupView = viewGroup;
                this.$dotView = statusBarIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C02441(this.$visibilityState, this.$groupView, this.$dotView, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((C02441) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                MutableStateFlow mutableStateFlow = this.$visibilityState;
                C02451 c02451 = new C02451(this.$groupView, this.$dotView);
                this.label = 1;
                ((StateFlowImpl) mutableStateFlow).collect(c02451, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$10, reason: invalid class name */
        final class AnonymousClass10 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $airplaneSpacer;
            final /* synthetic */ LocationBasedWifiViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass10(LocationBasedWifiViewModel locationBasedWifiViewModel, View view, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = locationBasedWifiViewModel;
                this.$airplaneSpacer = view;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass10(this.$viewModel, this.$airplaneSpacer, continuation);
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
                    Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.$viewModel.commonImpl.isAirplaneSpacerVisible());
                    AnonymousClass9.C02481 c02481 = new AnonymousClass9.C02481(this.$airplaneSpacer, 1);
                    this.label = 1;
                    if (distinctUntilChanged.collect(c02481, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$11, reason: invalid class name */
        final class AnonymousClass11 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $signalSpacer;
            final /* synthetic */ LocationBasedWifiViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass11(LocationBasedWifiViewModel locationBasedWifiViewModel, View view, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = locationBasedWifiViewModel;
                this.$signalSpacer = view;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass11(this.$viewModel, this.$signalSpacer, continuation);
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
                    Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.$viewModel.commonImpl.isSignalSpacerVisible());
                    AnonymousClass9.C02481 c02481 = new AnonymousClass9.C02481(this.$signalSpacer, 2);
                    this.label = 1;
                    if (distinctUntilChanged.collect(c02481, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ ImageView $iconView;
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ LocationBasedWifiViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(LocationBasedWifiViewModel locationBasedWifiViewModel, ViewGroup viewGroup, ImageView imageView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = locationBasedWifiViewModel;
                this.$view = viewGroup;
                this.$iconView = imageView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.$view, this.$iconView, continuation);
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
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow wifiIcon = this.$viewModel.commonImpl.getWifiIcon();
                    C02441.C02451 c02451 = new C02441.C02451(this.$view, this.$iconView);
                    this.label = 1;
                    if (wifiIcon.collect(c02451, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ ImageView $activityInView;
            final /* synthetic */ ImageView $activityOutView;
            final /* synthetic */ StatusBarIconView $dotView;
            final /* synthetic */ MutableStateFlow $iconTint;
            final /* synthetic */ ImageView $iconView;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(MutableStateFlow mutableStateFlow, ImageView imageView, ImageView imageView2, ImageView imageView3, StatusBarIconView statusBarIconView, Continuation continuation) {
                super(2, continuation);
                this.$iconTint = mutableStateFlow;
                this.$iconView = imageView;
                this.$activityInView = imageView2;
                this.$activityOutView = imageView3;
                this.$dotView = statusBarIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$iconTint, this.$iconView, this.$activityInView, this.$activityOutView, this.$dotView, continuation);
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
                MutableStateFlow mutableStateFlow = this.$iconTint;
                final ImageView imageView = this.$iconView;
                final ImageView imageView2 = this.$activityInView;
                final ImageView imageView3 = this.$activityOutView;
                final StatusBarIconView statusBarIconView = this.$dotView;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder.bind.1.1.3.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        int intValue = ((Number) obj2).intValue();
                        ColorStateList valueOf = ColorStateList.valueOf(intValue);
                        imageView.setImageTintList(valueOf);
                        imageView2.setImageTintList(valueOf);
                        imageView3.setImageTintList(valueOf);
                        statusBarIconView.setDecorColor(intValue);
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) mutableStateFlow).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableStateFlow $decorTint;
            final /* synthetic */ StatusBarIconView $dotView;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(MutableStateFlow mutableStateFlow, StatusBarIconView statusBarIconView, Continuation continuation) {
                super(2, continuation);
                this.$decorTint = mutableStateFlow;
                this.$dotView = statusBarIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$decorTint, this.$dotView, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                MutableStateFlow mutableStateFlow = this.$decorTint;
                AnonymousClass7.C02471 c02471 = new AnonymousClass7.C02471(this.$dotView);
                this.label = 1;
                ((StateFlowImpl) mutableStateFlow).collect(c02471, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$7, reason: invalid class name */
        final class AnonymousClass7 extends SuspendLambda implements Function2 {
            final /* synthetic */ ImageView $activityInView;
            final /* synthetic */ LocationBasedWifiViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$7$1, reason: invalid class name and collision with other inner class name */
            public final class C02471 implements FlowCollector {
                public final /* synthetic */ Object $activityInView;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ C02471(ImageView imageView, int i) {
                    this.$r8$classId = i;
                    this.$activityInView = imageView;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ((ImageView) this.$activityInView).setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                        case 1:
                            ((ImageView) this.$activityInView).setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                        default:
                            ((StatusBarIconView) this.$activityInView).setDecorColor(((Number) obj).intValue());
                            break;
                    }
                    return Unit.INSTANCE;
                }

                public C02471(StatusBarIconView statusBarIconView) {
                    this.$r8$classId = 2;
                    this.$activityInView = statusBarIconView;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(LocationBasedWifiViewModel locationBasedWifiViewModel, ImageView imageView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = locationBasedWifiViewModel;
                this.$activityInView = imageView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass7(this.$viewModel, this.$activityInView, continuation);
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
                    Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.$viewModel.commonImpl.isActivityInViewVisible());
                    C02471 c02471 = new C02471(this.$activityInView, 0);
                    this.label = 1;
                    if (distinctUntilChanged.collect(c02471, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$8, reason: invalid class name */
        final class AnonymousClass8 extends SuspendLambda implements Function2 {
            final /* synthetic */ ImageView $activityOutView;
            final /* synthetic */ LocationBasedWifiViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass8(LocationBasedWifiViewModel locationBasedWifiViewModel, ImageView imageView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = locationBasedWifiViewModel;
                this.$activityOutView = imageView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass8(this.$viewModel, this.$activityOutView, continuation);
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
                    Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.$viewModel.commonImpl.isActivityOutViewVisible());
                    AnonymousClass7.C02471 c02471 = new AnonymousClass7.C02471(this.$activityOutView, 1);
                    this.label = 1;
                    if (distinctUntilChanged.collect(c02471, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$9, reason: invalid class name */
        final class AnonymousClass9 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $activityContainerView;
            final /* synthetic */ LocationBasedWifiViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.binder.WifiViewBinder$bind$1$1$9$1, reason: invalid class name and collision with other inner class name */
            public final class C02481 implements FlowCollector {
                public final /* synthetic */ View $activityContainerView;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ C02481(View view, int i) {
                    this.$r8$classId = i;
                    this.$activityContainerView = view;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            this.$activityContainerView.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                        case 1:
                            this.$activityContainerView.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                        default:
                            this.$activityContainerView.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass9(LocationBasedWifiViewModel locationBasedWifiViewModel, View view, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = locationBasedWifiViewModel;
                this.$activityContainerView = view;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass9(this.$viewModel, this.$activityContainerView, continuation);
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
                    Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.$viewModel.commonImpl.isActivityContainerVisible());
                    C02481 c02481 = new C02481(this.$activityContainerView, 0);
                    this.label = 1;
                    if (distinctUntilChanged.collect(c02481, this) == coroutineSingletons) {
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
        public AnonymousClass1(Ref$BooleanRef ref$BooleanRef, MutableStateFlow mutableStateFlow, ViewGroup viewGroup, StatusBarIconView statusBarIconView, LocationBasedWifiViewModel locationBasedWifiViewModel, ViewGroup viewGroup2, ImageView imageView, MutableStateFlow mutableStateFlow2, ImageView imageView2, ImageView imageView3, MutableStateFlow mutableStateFlow3, View view, View view2, View view3, Continuation continuation) {
            super(2, continuation);
            this.$isCollecting = ref$BooleanRef;
            this.$visibilityState = mutableStateFlow;
            this.$groupView = viewGroup;
            this.$dotView = statusBarIconView;
            this.$viewModel = locationBasedWifiViewModel;
            this.$view = viewGroup2;
            this.$iconView = imageView;
            this.$iconTint = mutableStateFlow2;
            this.$activityInView = imageView2;
            this.$activityOutView = imageView3;
            this.$decorTint = mutableStateFlow3;
            this.$activityContainerView = view;
            this.$airplaneSpacer = view2;
            this.$signalSpacer = view3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$isCollecting, this.$visibilityState, this.$groupView, this.$dotView, this.$viewModel, this.$view, this.$iconView, this.$iconTint, this.$activityInView, this.$activityOutView, this.$decorTint, this.$activityContainerView, this.$airplaneSpacer, this.$signalSpacer, continuation);
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
                this.$isCollecting.element = true;
                BuildersKt.launch$default(coroutineScope, null, null, new C02441(this.$visibilityState, this.$groupView, this.$dotView, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$view, this.$iconView, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$iconTint, this.$iconView, this.$activityInView, this.$activityOutView, this.$dotView, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$decorTint, this.$dotView, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass7(this.$viewModel, this.$activityInView, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass8(this.$viewModel, this.$activityOutView, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass9(this.$viewModel, this.$activityContainerView, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass10(this.$viewModel, this.$airplaneSpacer, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass11(this.$viewModel, this.$signalSpacer, null), 3);
                this.label = 1;
                DelayKt.awaitCancellation(this);
                return coroutineSingletons;
            } catch (Throwable th) {
                this.$isCollecting.element = false;
                throw th;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiViewBinder$bind$1(Ref$BooleanRef ref$BooleanRef, MutableStateFlow mutableStateFlow, ViewGroup viewGroup, StatusBarIconView statusBarIconView, LocationBasedWifiViewModel locationBasedWifiViewModel, ViewGroup viewGroup2, ImageView imageView, MutableStateFlow mutableStateFlow2, ImageView imageView2, ImageView imageView3, MutableStateFlow mutableStateFlow3, View view, View view2, View view3, Continuation continuation) {
        super(3, continuation);
        this.$isCollecting = ref$BooleanRef;
        this.$visibilityState = mutableStateFlow;
        this.$groupView = viewGroup;
        this.$dotView = statusBarIconView;
        this.$viewModel = locationBasedWifiViewModel;
        this.$view = viewGroup2;
        this.$iconView = imageView;
        this.$iconTint = mutableStateFlow2;
        this.$activityInView = imageView2;
        this.$activityOutView = imageView3;
        this.$decorTint = mutableStateFlow3;
        this.$activityContainerView = view;
        this.$airplaneSpacer = view2;
        this.$signalSpacer = view3;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        WifiViewBinder$bind$1 wifiViewBinder$bind$1 = new WifiViewBinder$bind$1(this.$isCollecting, this.$visibilityState, this.$groupView, this.$dotView, this.$viewModel, this.$view, this.$iconView, this.$iconTint, this.$activityInView, this.$activityOutView, this.$decorTint, this.$activityContainerView, this.$airplaneSpacer, this.$signalSpacer, (Continuation) obj3);
        wifiViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return wifiViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$isCollecting, this.$visibilityState, this.$groupView, this.$dotView, this.$viewModel, this.$view, this.$iconView, this.$iconTint, this.$activityInView, this.$activityOutView, this.$decorTint, this.$activityContainerView, this.$airplaneSpacer, this.$signalSpacer, null);
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
