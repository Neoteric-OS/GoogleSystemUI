package com.android.systemui.statusbar.pipeline.mobile.ui.binder;

import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Space;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.settingslib.graph.SignalDrawable;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.ui.binder.IconViewBinder;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger;
import com.android.systemui.statusbar.pipeline.mobile.ui.VerboseMobileViewLogger;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import kotlin.KotlinNothingValueException;
import kotlin.NoWhenBranchMatchedException;
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
final class MobileIconBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ View $activityContainer;
    final /* synthetic */ ImageView $activityIn;
    final /* synthetic */ ImageView $activityOut;
    final /* synthetic */ MutableStateFlow $decorTint;
    final /* synthetic */ StatusBarIconView $dotView;
    final /* synthetic */ MutableStateFlow $iconTint;
    final /* synthetic */ ImageView $iconView;
    final /* synthetic */ Ref$BooleanRef $isCollecting;
    final /* synthetic */ MobileViewLogger $logger;
    final /* synthetic */ SignalDrawable $mobileDrawable;
    final /* synthetic */ ViewGroup $mobileGroupView;
    final /* synthetic */ FrameLayout $networkTypeContainer;
    final /* synthetic */ ImageView $networkTypeView;
    final /* synthetic */ Space $roamingSpace;
    final /* synthetic */ ImageView $roamingView;
    final /* synthetic */ ViewGroup $view;
    final /* synthetic */ LocationBasedMobileViewModel $viewModel;
    final /* synthetic */ MutableStateFlow $visibilityState;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LifecycleOwner $$this$repeatWhenAttached;
        final /* synthetic */ ViewGroup $view;
        final /* synthetic */ LocationBasedMobileViewModel $viewModel;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C02171 extends SuspendLambda implements Function2 {
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ LocationBasedMobileViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$1$1$1, reason: invalid class name and collision with other inner class name */
            final class C02181 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewGroup $view;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                public final class C02191 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId = 1;
                    public final /* synthetic */ Object $view;
                    public final /* synthetic */ Object $viewModel;

                    public C02191(ImageView imageView, Space space) {
                        this.$viewModel = imageView;
                        this.$view = space;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                boolean booleanValue = ((Boolean) obj).booleanValue();
                                LocationBasedMobileViewModel locationBasedMobileViewModel = (LocationBasedMobileViewModel) this.$viewModel;
                                VerboseMobileViewLogger verboseMobileViewLogger = locationBasedMobileViewModel.verboseLogger;
                                if (verboseMobileViewLogger != null) {
                                    verboseMobileViewLogger.logBinderReceivedVisibility(locationBasedMobileViewModel.commonImpl.getSubscriptionId(), (ViewGroup) this.$view, booleanValue);
                                }
                                ((ViewGroup) this.$view).setVisibility(booleanValue ? 0 : 8);
                                ((ViewGroup) this.$view).requestLayout();
                                break;
                            default:
                                boolean booleanValue2 = ((Boolean) obj).booleanValue();
                                ((ImageView) this.$viewModel).setVisibility(booleanValue2 ? 0 : 8);
                                ((Space) this.$view).setVisibility(booleanValue2 ? 0 : 8);
                                break;
                        }
                        return Unit.INSTANCE;
                    }

                    public C02191(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup) {
                        this.$viewModel = locationBasedMobileViewModel;
                        this.$view = viewGroup;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C02181(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$view = viewGroup;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C02181(this.$viewModel, this.$view, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((C02181) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    return CoroutineSingletons.COROUTINE_SUSPENDED;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow isVisible = this.$viewModel.isVisible();
                        C02191 c02191 = new C02191(this.$viewModel, this.$view);
                        this.label = 1;
                        if (isVisible.collect(c02191, this) == coroutineSingletons) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02171(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = locationBasedMobileViewModel;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C02171 c02171 = new C02171(this.$viewModel, this.$view, continuation);
                c02171.L$0 = obj;
                return c02171;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                C02171 c02171 = (C02171) create((CoroutineScope) obj, (Continuation) obj2);
                Unit unit = Unit.INSTANCE;
                c02171.invokeSuspend(unit);
                return unit;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                BuildersKt.launch$default((CoroutineScope) this.L$0, null, null, new C02181(this.$viewModel, this.$view, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LifecycleOwner lifecycleOwner, LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, Continuation continuation) {
            super(2, continuation);
            this.$$this$repeatWhenAttached = lifecycleOwner;
            this.$viewModel = locationBasedMobileViewModel;
            this.$view = viewGroup;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$$this$repeatWhenAttached, this.$viewModel, this.$view, continuation);
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
                LifecycleOwner lifecycleOwner = this.$$this$repeatWhenAttached;
                Lifecycle.State state = Lifecycle.State.CREATED;
                C02171 c02171 = new C02171(this.$viewModel, this.$view, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c02171, this) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ LifecycleOwner $$this$repeatWhenAttached;
        final /* synthetic */ View $activityContainer;
        final /* synthetic */ ImageView $activityIn;
        final /* synthetic */ ImageView $activityOut;
        final /* synthetic */ MutableStateFlow $decorTint;
        final /* synthetic */ StatusBarIconView $dotView;
        final /* synthetic */ MutableStateFlow $iconTint;
        final /* synthetic */ ImageView $iconView;
        final /* synthetic */ Ref$BooleanRef $isCollecting;
        final /* synthetic */ MobileViewLogger $logger;
        final /* synthetic */ SignalDrawable $mobileDrawable;
        final /* synthetic */ ViewGroup $mobileGroupView;
        final /* synthetic */ FrameLayout $networkTypeContainer;
        final /* synthetic */ ImageView $networkTypeView;
        final /* synthetic */ Space $roamingSpace;
        final /* synthetic */ ImageView $roamingView;
        final /* synthetic */ ViewGroup $view;
        final /* synthetic */ LocationBasedMobileViewModel $viewModel;
        final /* synthetic */ MutableStateFlow $visibilityState;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $activityContainer;
            final /* synthetic */ ImageView $activityIn;
            final /* synthetic */ ImageView $activityOut;
            final /* synthetic */ MutableStateFlow $decorTint;
            final /* synthetic */ StatusBarIconView $dotView;
            final /* synthetic */ MutableStateFlow $iconTint;
            final /* synthetic */ ImageView $iconView;
            final /* synthetic */ Ref$BooleanRef $isCollecting;
            final /* synthetic */ MobileViewLogger $logger;
            final /* synthetic */ SignalDrawable $mobileDrawable;
            final /* synthetic */ ViewGroup $mobileGroupView;
            final /* synthetic */ FrameLayout $networkTypeContainer;
            final /* synthetic */ ImageView $networkTypeView;
            final /* synthetic */ Space $roamingSpace;
            final /* synthetic */ ImageView $roamingView;
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ LocationBasedMobileViewModel $viewModel;
            final /* synthetic */ MutableStateFlow $visibilityState;
            private /* synthetic */ Object L$0;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$1, reason: invalid class name and collision with other inner class name */
            final class C02201 extends SuspendLambda implements Function2 {
                final /* synthetic */ StatusBarIconView $dotView;
                final /* synthetic */ ViewGroup $mobileGroupView;
                final /* synthetic */ ViewGroup $view;
                final /* synthetic */ MutableStateFlow $visibilityState;
                int label;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$1$1, reason: invalid class name and collision with other inner class name */
                public final class C02211 implements FlowCollector {
                    public final /* synthetic */ Object $dotView;
                    public final /* synthetic */ Object $mobileGroupView;
                    public final /* synthetic */ int $r8$classId = 0;
                    public final /* synthetic */ Object $view;

                    public C02211(ViewGroup viewGroup, StatusBarIconView statusBarIconView, ViewGroup viewGroup2) {
                        this.$mobileGroupView = viewGroup;
                        this.$dotView = statusBarIconView;
                        this.$view = viewGroup2;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                int intValue = ((Number) obj).intValue();
                                ViewGroup viewGroup = (ViewGroup) this.$mobileGroupView;
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
                                ((ViewGroup) this.$view).requestLayout();
                                break;
                            default:
                                Icon.Resource resource = (Icon.Resource) obj;
                                ((FrameLayout) this.$mobileGroupView).setBackgroundResource(resource != null ? resource.res : 0);
                                Integer num = resource != null ? new Integer(resource.res) : null;
                                MutableStateFlow mutableStateFlow = (MutableStateFlow) this.$view;
                                if (num != null) {
                                    StateFlowImpl stateFlowImpl = (StateFlowImpl) mutableStateFlow;
                                    ((FrameLayout) this.$mobileGroupView).setBackgroundTintList(ColorStateList.valueOf(((Colors) stateFlowImpl.getValue()).tint));
                                    ((ImageView) this.$dotView).setImageTintList(ColorStateList.valueOf(((Colors) stateFlowImpl.getValue()).contrast));
                                } else {
                                    ((ImageView) this.$dotView).setImageTintList(ColorStateList.valueOf(((Colors) ((StateFlowImpl) mutableStateFlow).getValue()).tint));
                                }
                                break;
                        }
                        return Unit.INSTANCE;
                    }

                    public C02211(FrameLayout frameLayout, MutableStateFlow mutableStateFlow, ImageView imageView) {
                        this.$mobileGroupView = frameLayout;
                        this.$view = mutableStateFlow;
                        this.$dotView = imageView;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C02201(MutableStateFlow mutableStateFlow, ViewGroup viewGroup, StatusBarIconView statusBarIconView, ViewGroup viewGroup2, Continuation continuation) {
                    super(2, continuation);
                    this.$visibilityState = mutableStateFlow;
                    this.$mobileGroupView = viewGroup;
                    this.$dotView = statusBarIconView;
                    this.$view = viewGroup2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C02201(this.$visibilityState, this.$mobileGroupView, this.$dotView, this.$view, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((C02201) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    C02211 c02211 = new C02211(this.$mobileGroupView, this.$dotView, this.$view);
                    this.label = 1;
                    ((StateFlowImpl) mutableStateFlow).collect(c02211, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$10, reason: invalid class name */
            final class AnonymousClass10 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $activityOut;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass10(LocationBasedMobileViewModel locationBasedMobileViewModel, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$activityOut = imageView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass10(this.$viewModel, this.$activityOut, continuation);
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
                        Flow activityOutVisible = this.$viewModel.commonImpl.getActivityOutVisible();
                        AnonymousClass9.C02261 c02261 = new AnonymousClass9.C02261(this.$activityOut, 1);
                        this.label = 1;
                        if (activityOutVisible.collect(c02261, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$11, reason: invalid class name */
            final class AnonymousClass11 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $activityContainer;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass11(LocationBasedMobileViewModel locationBasedMobileViewModel, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$activityContainer = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass11(this.$viewModel, this.$activityContainer, continuation);
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
                        Flow activityContainerVisible = this.$viewModel.commonImpl.getActivityContainerVisible();
                        AnonymousClass3.C02251 c02251 = new AnonymousClass3.C02251(this.$activityContainer);
                        this.label = 1;
                        if (activityContainerVisible.collect(c02251, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$12, reason: invalid class name */
            final class AnonymousClass12 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $activityIn;
                final /* synthetic */ ImageView $activityOut;
                final /* synthetic */ StatusBarIconView $dotView;
                final /* synthetic */ MutableStateFlow $iconTint;
                final /* synthetic */ ImageView $iconView;
                final /* synthetic */ FrameLayout $networkTypeContainer;
                final /* synthetic */ ImageView $networkTypeView;
                final /* synthetic */ ImageView $roamingView;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass12(MutableStateFlow mutableStateFlow, ImageView imageView, LocationBasedMobileViewModel locationBasedMobileViewModel, FrameLayout frameLayout, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, StatusBarIconView statusBarIconView, Continuation continuation) {
                    super(2, continuation);
                    this.$iconTint = mutableStateFlow;
                    this.$iconView = imageView;
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$networkTypeContainer = frameLayout;
                    this.$networkTypeView = imageView2;
                    this.$roamingView = imageView3;
                    this.$activityIn = imageView4;
                    this.$activityOut = imageView5;
                    this.$dotView = statusBarIconView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass12(this.$iconTint, this.$iconView, this.$viewModel, this.$networkTypeContainer, this.$networkTypeView, this.$roamingView, this.$activityIn, this.$activityOut, this.$dotView, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass12) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    final LocationBasedMobileViewModel locationBasedMobileViewModel = this.$viewModel;
                    final FrameLayout frameLayout = this.$networkTypeContainer;
                    final ImageView imageView2 = this.$networkTypeView;
                    final ImageView imageView3 = this.$roamingView;
                    final ImageView imageView4 = this.$activityIn;
                    final ImageView imageView5 = this.$activityOut;
                    final StatusBarIconView statusBarIconView = this.$dotView;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder.bind.1.2.1.12.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            Colors colors = (Colors) obj2;
                            ColorStateList valueOf = ColorStateList.valueOf(colors.tint);
                            ColorStateList valueOf2 = ColorStateList.valueOf(colors.contrast);
                            imageView.setImageTintList(valueOf);
                            if (locationBasedMobileViewModel.commonImpl.getNetworkTypeBackground().getValue() != null) {
                                frameLayout.setBackgroundTintList(valueOf);
                                imageView2.setImageTintList(valueOf2);
                            } else {
                                imageView2.setImageTintList(valueOf);
                            }
                            imageView3.setImageTintList(valueOf);
                            imageView4.setImageTintList(valueOf);
                            imageView5.setImageTintList(valueOf);
                            statusBarIconView.setDecorColor(colors.tint);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    ((StateFlowImpl) mutableStateFlow).collect(flowCollector, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$13, reason: invalid class name */
            final class AnonymousClass13 extends SuspendLambda implements Function2 {
                final /* synthetic */ MutableStateFlow $decorTint;
                final /* synthetic */ StatusBarIconView $dotView;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass13(MutableStateFlow mutableStateFlow, StatusBarIconView statusBarIconView, Continuation continuation) {
                    super(2, continuation);
                    this.$decorTint = mutableStateFlow;
                    this.$dotView = statusBarIconView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass13(this.$decorTint, this.$dotView, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass13) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    AnonymousClass3.C02251 c02251 = new AnonymousClass3.C02251(this.$dotView);
                    this.label = 1;
                    ((StateFlowImpl) mutableStateFlow).collect(c02251, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$2, reason: invalid class name and collision with other inner class name */
            final class C02232 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $iconView;
                final /* synthetic */ SignalDrawable $mobileDrawable;
                final /* synthetic */ ViewGroup $view;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$2$1, reason: invalid class name and collision with other inner class name */
                public final class C02241 implements FlowCollector {
                    public final /* synthetic */ ImageView $iconView;
                    public final /* synthetic */ Object $mobileDrawable;
                    public final /* synthetic */ int $r8$classId = 1;
                    public final /* synthetic */ ViewGroup $view;
                    public final /* synthetic */ LocationBasedMobileViewModel $viewModel;

                    public C02241(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, FrameLayout frameLayout, ImageView imageView) {
                        this.$viewModel = locationBasedMobileViewModel;
                        this.$view = viewGroup;
                        this.$mobileDrawable = frameLayout;
                        this.$iconView = imageView;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        int state;
                        Unit unit = Unit.INSTANCE;
                        LocationBasedMobileViewModel locationBasedMobileViewModel = this.$viewModel;
                        Object obj2 = this.$mobileDrawable;
                        switch (this.$r8$classId) {
                            case 0:
                                SignalIconModel signalIconModel = (SignalIconModel) obj;
                                VerboseMobileViewLogger verboseMobileViewLogger = locationBasedMobileViewModel.verboseLogger;
                                if (verboseMobileViewLogger != null) {
                                    verboseMobileViewLogger.logBinderReceivedSignalIcon(this.$view, locationBasedMobileViewModel.commonImpl.getSubscriptionId(), signalIconModel);
                                }
                                if (!(signalIconModel instanceof SignalIconModel.Cellular)) {
                                    if (signalIconModel instanceof SignalIconModel.Satellite) {
                                        IconViewBinder.bind(((SignalIconModel.Satellite) signalIconModel).icon, this.$iconView);
                                        break;
                                    }
                                } else {
                                    SignalDrawable signalDrawable = (SignalDrawable) obj2;
                                    this.$iconView.setImageDrawable(signalDrawable);
                                    SignalIconModel.Cellular cellular = (SignalIconModel.Cellular) signalIconModel;
                                    boolean z = cellular.carrierNetworkChange;
                                    int i = cellular.numberOfLevels;
                                    if (z) {
                                        int i2 = SignalDrawable.$r8$clinit;
                                        state = (i << 8) | 196608;
                                    } else {
                                        state = SignalDrawable.getState(cellular.level, i, cellular.showExclamationMark);
                                    }
                                    signalDrawable.setLevel(state);
                                    break;
                                }
                                break;
                            default:
                                Icon.Resource resource = (Icon.Resource) obj;
                                VerboseMobileViewLogger verboseMobileViewLogger2 = locationBasedMobileViewModel.verboseLogger;
                                if (verboseMobileViewLogger2 != null) {
                                    verboseMobileViewLogger2.logBinderReceivedNetworkTypeIcon(this.$view, locationBasedMobileViewModel.commonImpl.getSubscriptionId(), resource);
                                }
                                if (resource != null) {
                                    IconViewBinder.bind(resource, this.$iconView);
                                }
                                int visibility = ((FrameLayout) obj2).getVisibility();
                                ((FrameLayout) obj2).setVisibility(resource != null ? 0 : 8);
                                if (visibility != ((FrameLayout) obj2).getVisibility()) {
                                    this.$view.requestLayout();
                                    break;
                                }
                                break;
                        }
                        return unit;
                    }

                    public C02241(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, ImageView imageView, SignalDrawable signalDrawable) {
                        this.$viewModel = locationBasedMobileViewModel;
                        this.$view = viewGroup;
                        this.$iconView = imageView;
                        this.$mobileDrawable = signalDrawable;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C02232(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, ImageView imageView, SignalDrawable signalDrawable, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$view = viewGroup;
                    this.$iconView = imageView;
                    this.$mobileDrawable = signalDrawable;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C02232(this.$viewModel, this.$view, this.$iconView, this.$mobileDrawable, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((C02232) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.$viewModel.commonImpl.getIcon());
                        C02241 c02241 = new C02241(this.$viewModel, this.$view, this.$iconView, this.$mobileDrawable);
                        this.label = 1;
                        if (distinctUntilChanged.collect(c02241, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$3, reason: invalid class name */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ ViewGroup $view;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$3$1, reason: invalid class name and collision with other inner class name */
                public final class C02251 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId = 1;
                    public final /* synthetic */ Object $view;

                    public C02251(View view) {
                        this.$view = view;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        String string;
                        switch (this.$r8$classId) {
                            case 0:
                                ContentDescription contentDescription = (ContentDescription) obj;
                                ViewGroup viewGroup = (ViewGroup) this.$view;
                                if (contentDescription == null) {
                                    string = null;
                                } else if (contentDescription instanceof ContentDescription.Loaded) {
                                    string = ((ContentDescription.Loaded) contentDescription).description;
                                } else {
                                    if (!(contentDescription instanceof ContentDescription.Resource)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    string = viewGroup.getContext().getResources().getString(((ContentDescription.Resource) contentDescription).res);
                                }
                                viewGroup.setContentDescription(string);
                                return Unit.INSTANCE;
                            case 1:
                                ((View) this.$view).setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                                return Unit.INSTANCE;
                            default:
                                ((StatusBarIconView) this.$view).setDecorColor(((Number) obj).intValue());
                                return Unit.INSTANCE;
                        }
                    }

                    public C02251(ViewGroup viewGroup) {
                        this.$view = viewGroup;
                    }

                    public C02251(StatusBarIconView statusBarIconView) {
                        this.$view = statusBarIconView;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$view = viewGroup;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$viewModel, this.$view, continuation);
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
                        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.$viewModel.commonImpl.getContentDescription());
                        C02251 c02251 = new C02251(this.$view);
                        this.label = 1;
                        if (distinctUntilChanged.collect(c02251, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$4, reason: invalid class name */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                final /* synthetic */ FrameLayout $networkTypeContainer;
                final /* synthetic */ ImageView $networkTypeView;
                final /* synthetic */ ViewGroup $view;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(LocationBasedMobileViewModel locationBasedMobileViewModel, ViewGroup viewGroup, FrameLayout frameLayout, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$view = viewGroup;
                    this.$networkTypeContainer = frameLayout;
                    this.$networkTypeView = imageView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.$viewModel, this.$view, this.$networkTypeContainer, this.$networkTypeView, continuation);
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
                        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.$viewModel.commonImpl.getNetworkTypeIcon());
                        C02232.C02241 c02241 = new C02232.C02241(this.$viewModel, this.$view, this.$networkTypeContainer, this.$networkTypeView);
                        this.label = 1;
                        if (distinctUntilChanged.collect(c02241, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$5, reason: invalid class name */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                final /* synthetic */ MutableStateFlow $iconTint;
                final /* synthetic */ FrameLayout $networkTypeContainer;
                final /* synthetic */ ImageView $networkTypeView;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(LocationBasedMobileViewModel locationBasedMobileViewModel, FrameLayout frameLayout, MutableStateFlow mutableStateFlow, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$networkTypeContainer = frameLayout;
                    this.$iconTint = mutableStateFlow;
                    this.$networkTypeView = imageView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass5(this.$viewModel, this.$networkTypeContainer, this.$iconTint, this.$networkTypeView, continuation);
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
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow networkTypeBackground = this.$viewModel.commonImpl.getNetworkTypeBackground();
                        C02201.C02211 c02211 = new C02201.C02211(this.$networkTypeContainer, this.$iconTint, this.$networkTypeView);
                        this.label = 1;
                        if (networkTypeBackground.collect(c02211, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$6, reason: invalid class name */
            final class AnonymousClass6 extends SuspendLambda implements Function2 {
                final /* synthetic */ Space $roamingSpace;
                final /* synthetic */ ImageView $roamingView;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass6(LocationBasedMobileViewModel locationBasedMobileViewModel, ImageView imageView, Space space, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$roamingView = imageView;
                    this.$roamingSpace = space;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass6(this.$viewModel, this.$roamingView, this.$roamingSpace, continuation);
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
                        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(this.$viewModel.commonImpl.getRoaming());
                        AnonymousClass1.C02171.C02181.C02191 c02191 = new AnonymousClass1.C02171.C02181.C02191(this.$roamingView, this.$roamingSpace);
                        this.label = 1;
                        if (distinctUntilChanged.collect(c02191, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$9, reason: invalid class name */
            final class AnonymousClass9 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $activityIn;
                final /* synthetic */ LocationBasedMobileViewModel $viewModel;
                int label;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$1$2$1$9$1, reason: invalid class name and collision with other inner class name */
                public final class C02261 implements FlowCollector {
                    public final /* synthetic */ ImageView $activityIn;
                    public final /* synthetic */ int $r8$classId;

                    public /* synthetic */ C02261(ImageView imageView, int i) {
                        this.$r8$classId = i;
                        this.$activityIn = imageView;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                this.$activityIn.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                                break;
                            default:
                                this.$activityIn.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass9(LocationBasedMobileViewModel locationBasedMobileViewModel, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedMobileViewModel;
                    this.$activityIn = imageView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass9(this.$viewModel, this.$activityIn, continuation);
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
                        Flow activityInVisible = this.$viewModel.commonImpl.getActivityInVisible();
                        C02261 c02261 = new C02261(this.$activityIn, 0);
                        this.label = 1;
                        if (activityInVisible.collect(c02261, this) == coroutineSingletons) {
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
            public AnonymousClass1(View view, ViewGroup viewGroup, ViewGroup viewGroup2, FrameLayout frameLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, Space space, SignalDrawable signalDrawable, StatusBarIconView statusBarIconView, MobileViewLogger mobileViewLogger, LocationBasedMobileViewModel locationBasedMobileViewModel, Continuation continuation, Ref$BooleanRef ref$BooleanRef, MutableStateFlow mutableStateFlow, MutableStateFlow mutableStateFlow2, MutableStateFlow mutableStateFlow3) {
                super(2, continuation);
                this.$logger = mobileViewLogger;
                this.$view = viewGroup;
                this.$viewModel = locationBasedMobileViewModel;
                this.$isCollecting = ref$BooleanRef;
                this.$visibilityState = mutableStateFlow;
                this.$mobileGroupView = viewGroup2;
                this.$dotView = statusBarIconView;
                this.$iconView = imageView;
                this.$mobileDrawable = signalDrawable;
                this.$networkTypeContainer = frameLayout;
                this.$networkTypeView = imageView2;
                this.$iconTint = mutableStateFlow2;
                this.$roamingView = imageView3;
                this.$roamingSpace = space;
                this.$activityIn = imageView4;
                this.$activityOut = imageView5;
                this.$activityContainer = view;
                this.$decorTint = mutableStateFlow3;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                MobileViewLogger mobileViewLogger = this.$logger;
                ViewGroup viewGroup = this.$view;
                LocationBasedMobileViewModel locationBasedMobileViewModel = this.$viewModel;
                Ref$BooleanRef ref$BooleanRef = this.$isCollecting;
                MutableStateFlow mutableStateFlow = this.$visibilityState;
                ViewGroup viewGroup2 = this.$mobileGroupView;
                StatusBarIconView statusBarIconView = this.$dotView;
                ImageView imageView = this.$iconView;
                SignalDrawable signalDrawable = this.$mobileDrawable;
                FrameLayout frameLayout = this.$networkTypeContainer;
                ImageView imageView2 = this.$networkTypeView;
                MutableStateFlow mutableStateFlow2 = this.$iconTint;
                ImageView imageView3 = this.$roamingView;
                Space space = this.$roamingSpace;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$activityContainer, viewGroup, viewGroup2, frameLayout, imageView, imageView2, imageView3, this.$activityIn, this.$activityOut, space, signalDrawable, statusBarIconView, mobileViewLogger, locationBasedMobileViewModel, continuation, ref$BooleanRef, mutableStateFlow, mutableStateFlow2, this.$decorTint);
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
                    this.$logger.logCollectionStarted(this.$view, this.$viewModel);
                    this.$isCollecting.element = true;
                    BuildersKt.launch$default(coroutineScope, null, null, new C02201(this.$visibilityState, this.$mobileGroupView, this.$dotView, this.$view, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new C02232(this.$viewModel, this.$view, this.$iconView, this.$mobileDrawable, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.$view, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$viewModel, this.$view, this.$networkTypeContainer, this.$networkTypeView, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$viewModel, this.$networkTypeContainer, this.$iconTint, this.$networkTypeView, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass6(this.$viewModel, this.$roamingView, this.$roamingSpace, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass9(this.$viewModel, this.$activityIn, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass10(this.$viewModel, this.$activityOut, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass11(this.$viewModel, this.$activityContainer, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass12(this.$iconTint, this.$iconView, this.$viewModel, this.$networkTypeContainer, this.$networkTypeView, this.$roamingView, this.$activityIn, this.$activityOut, this.$dotView, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass13(this.$decorTint, this.$dotView, null), 3);
                    this.label = 1;
                    DelayKt.awaitCancellation(this);
                    return coroutineSingletons;
                } catch (Throwable th) {
                    this.$isCollecting.element = false;
                    this.$logger.logCollectionStopped(this.$view, this.$viewModel);
                    throw th;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(LifecycleOwner lifecycleOwner, MobileViewLogger mobileViewLogger, ViewGroup viewGroup, LocationBasedMobileViewModel locationBasedMobileViewModel, Ref$BooleanRef ref$BooleanRef, MutableStateFlow mutableStateFlow, ViewGroup viewGroup2, StatusBarIconView statusBarIconView, ImageView imageView, SignalDrawable signalDrawable, FrameLayout frameLayout, ImageView imageView2, MutableStateFlow mutableStateFlow2, ImageView imageView3, Space space, ImageView imageView4, ImageView imageView5, View view, MutableStateFlow mutableStateFlow3, Continuation continuation) {
            super(2, continuation);
            this.$$this$repeatWhenAttached = lifecycleOwner;
            this.$logger = mobileViewLogger;
            this.$view = viewGroup;
            this.$viewModel = locationBasedMobileViewModel;
            this.$isCollecting = ref$BooleanRef;
            this.$visibilityState = mutableStateFlow;
            this.$mobileGroupView = viewGroup2;
            this.$dotView = statusBarIconView;
            this.$iconView = imageView;
            this.$mobileDrawable = signalDrawable;
            this.$networkTypeContainer = frameLayout;
            this.$networkTypeView = imageView2;
            this.$iconTint = mutableStateFlow2;
            this.$roamingView = imageView3;
            this.$roamingSpace = space;
            this.$activityIn = imageView4;
            this.$activityOut = imageView5;
            this.$activityContainer = view;
            this.$decorTint = mutableStateFlow3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$$this$repeatWhenAttached, this.$logger, this.$view, this.$viewModel, this.$isCollecting, this.$visibilityState, this.$mobileGroupView, this.$dotView, this.$iconView, this.$mobileDrawable, this.$networkTypeContainer, this.$networkTypeView, this.$iconTint, this.$roamingView, this.$roamingSpace, this.$activityIn, this.$activityOut, this.$activityContainer, this.$decorTint, continuation);
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
                LifecycleOwner lifecycleOwner = this.$$this$repeatWhenAttached;
                Lifecycle.State state = Lifecycle.State.STARTED;
                MobileViewLogger mobileViewLogger = this.$logger;
                ViewGroup viewGroup = this.$view;
                LocationBasedMobileViewModel locationBasedMobileViewModel = this.$viewModel;
                Ref$BooleanRef ref$BooleanRef = this.$isCollecting;
                MutableStateFlow mutableStateFlow = this.$visibilityState;
                ViewGroup viewGroup2 = this.$mobileGroupView;
                StatusBarIconView statusBarIconView = this.$dotView;
                ImageView imageView = this.$iconView;
                SignalDrawable signalDrawable = this.$mobileDrawable;
                FrameLayout frameLayout = this.$networkTypeContainer;
                ImageView imageView2 = this.$networkTypeView;
                MutableStateFlow mutableStateFlow2 = this.$iconTint;
                ImageView imageView3 = this.$roamingView;
                Space space = this.$roamingSpace;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$activityContainer, viewGroup, viewGroup2, frameLayout, imageView, imageView2, imageView3, this.$activityIn, this.$activityOut, space, signalDrawable, statusBarIconView, mobileViewLogger, locationBasedMobileViewModel, null, ref$BooleanRef, mutableStateFlow, mutableStateFlow2, this.$decorTint);
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MobileIconBinder$bind$1(View view, ViewGroup viewGroup, ViewGroup viewGroup2, FrameLayout frameLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, Space space, SignalDrawable signalDrawable, StatusBarIconView statusBarIconView, MobileViewLogger mobileViewLogger, LocationBasedMobileViewModel locationBasedMobileViewModel, Continuation continuation, Ref$BooleanRef ref$BooleanRef, MutableStateFlow mutableStateFlow, MutableStateFlow mutableStateFlow2, MutableStateFlow mutableStateFlow3) {
        super(3, continuation);
        this.$viewModel = locationBasedMobileViewModel;
        this.$view = viewGroup;
        this.$logger = mobileViewLogger;
        this.$isCollecting = ref$BooleanRef;
        this.$visibilityState = mutableStateFlow;
        this.$mobileGroupView = viewGroup2;
        this.$dotView = statusBarIconView;
        this.$iconView = imageView;
        this.$mobileDrawable = signalDrawable;
        this.$networkTypeContainer = frameLayout;
        this.$networkTypeView = imageView2;
        this.$iconTint = mutableStateFlow2;
        this.$roamingView = imageView3;
        this.$roamingSpace = space;
        this.$activityIn = imageView4;
        this.$activityOut = imageView5;
        this.$activityContainer = view;
        this.$decorTint = mutableStateFlow3;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        LocationBasedMobileViewModel locationBasedMobileViewModel = this.$viewModel;
        ViewGroup viewGroup = this.$view;
        MobileViewLogger mobileViewLogger = this.$logger;
        Ref$BooleanRef ref$BooleanRef = this.$isCollecting;
        MutableStateFlow mutableStateFlow = this.$visibilityState;
        ViewGroup viewGroup2 = this.$mobileGroupView;
        StatusBarIconView statusBarIconView = this.$dotView;
        ImageView imageView = this.$iconView;
        SignalDrawable signalDrawable = this.$mobileDrawable;
        FrameLayout frameLayout = this.$networkTypeContainer;
        ImageView imageView2 = this.$networkTypeView;
        MutableStateFlow mutableStateFlow2 = this.$iconTint;
        ImageView imageView3 = this.$roamingView;
        Space space = this.$roamingSpace;
        ImageView imageView4 = this.$activityIn;
        ImageView imageView5 = this.$activityOut;
        MobileIconBinder$bind$1 mobileIconBinder$bind$1 = new MobileIconBinder$bind$1(this.$activityContainer, viewGroup, viewGroup2, frameLayout, imageView, imageView2, imageView3, imageView4, imageView5, space, signalDrawable, statusBarIconView, mobileViewLogger, locationBasedMobileViewModel, (Continuation) obj3, ref$BooleanRef, mutableStateFlow, mutableStateFlow2, this.$decorTint);
        mobileIconBinder$bind$1.L$0 = (LifecycleOwner) obj;
        Unit unit = Unit.INSTANCE;
        mobileIconBinder$bind$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), null, null, new AnonymousClass1(lifecycleOwner, this.$viewModel, this.$view, null), 3);
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(lifecycleOwner.getLifecycle()), null, null, new AnonymousClass2(lifecycleOwner, this.$logger, this.$view, this.$viewModel, this.$isCollecting, this.$visibilityState, this.$mobileGroupView, this.$dotView, this.$iconView, this.$mobileDrawable, this.$networkTypeContainer, this.$networkTypeView, this.$iconTint, this.$roamingView, this.$roamingSpace, this.$activityIn, this.$activityOut, this.$activityContainer, this.$decorTint, null), 3);
        return Unit.INSTANCE;
    }
}
