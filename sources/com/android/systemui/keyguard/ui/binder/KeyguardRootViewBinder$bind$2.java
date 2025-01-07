package com.android.systemui.keyguard.ui.binder;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.common.shared.model.NotificationContainerBounds;
import com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.StateToValue;
import com.android.systemui.keyguard.ui.viewmodel.BurnInScaleViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel$special$$inlined$map$4;
import com.android.systemui.keyguard.ui.viewmodel.TransitionData;
import com.android.systemui.keyguard.ui.viewmodel.ViewStateAccessor;
import com.android.wm.shell.R;
import java.util.Map;
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
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardRootViewBinder$bind$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ Map $childViews;
    final /* synthetic */ ViewGroup $view;
    final /* synthetic */ KeyguardRootViewModel $viewModel;
    final /* synthetic */ ViewStateAccessor $viewState;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Map $childViews;
        final /* synthetic */ ViewGroup $view;
        final /* synthetic */ KeyguardRootViewModel $viewModel;
        final /* synthetic */ ViewStateAccessor $viewState;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$2$1$1, reason: invalid class name and collision with other inner class name */
        final class C01271 extends SuspendLambda implements Function2 {
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$2$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C01281 implements FlowCollector {
                public final /* synthetic */ Object $clipBounds;
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ Object $view;

                public /* synthetic */ C01281(int i, Object obj, Object obj2) {
                    this.$r8$classId = i;
                    this.$view = obj;
                    this.$clipBounds = obj2;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit = Unit.INSTANCE;
                    Object obj2 = this.$clipBounds;
                    Object obj3 = this.$view;
                    switch (this.$r8$classId) {
                        case 0:
                            Integer num = (Integer) obj;
                            if (num != null) {
                                Rect rect = (Rect) obj2;
                                ViewGroup viewGroup = (ViewGroup) obj3;
                                rect.top = num.intValue();
                                rect.left = viewGroup.getLeft();
                                rect.right = viewGroup.getRight();
                                rect.bottom = viewGroup.getBottom();
                                ((ViewGroup) obj3).setClipBounds((Rect) obj2);
                                break;
                            } else {
                                ((ViewGroup) obj3).setClipBounds(null);
                                break;
                            }
                        case 1:
                            float floatValue = ((Number) obj).floatValue();
                            ((ViewGroup) obj3).setAlpha(floatValue);
                            KeyguardRootViewBinder keyguardRootViewBinder = KeyguardRootViewBinder.INSTANCE;
                            View view = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.keyguard_status_view, (Map) obj2);
                            if (view != null) {
                                view.setAlpha(floatValue);
                            }
                            View view2 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.burn_in_layer, (Map) obj2);
                            if (view2 != null) {
                                view2.setAlpha(floatValue);
                                break;
                            }
                            break;
                        default:
                            if (((TransitionData) obj) == null) {
                                KeyguardRootViewBinder keyguardRootViewBinder2 = KeyguardRootViewBinder.INSTANCE;
                                View view3 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.nssl_placeholder, (Map) obj3);
                                if (view3 != null) {
                                    float top = view3.getTop();
                                    float bottom = view3.getBottom();
                                    KeyguardRootViewModel keyguardRootViewModel = (KeyguardRootViewModel) obj2;
                                    keyguardRootViewModel.getClass();
                                    NotificationContainerBounds notificationContainerBounds = new NotificationContainerBounds(top, bottom, true);
                                    StateFlowImpl stateFlowImpl = keyguardRootViewModel.keyguardInteractor._notificationPlaceholderBounds;
                                    stateFlowImpl.getClass();
                                    stateFlowImpl.updateState(null, notificationContainerBounds);
                                    break;
                                }
                            }
                            break;
                    }
                    return unit;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01271(KeyguardRootViewModel keyguardRootViewModel, ViewGroup viewGroup, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardRootViewModel;
                this.$view = viewGroup;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01271(this.$viewModel, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01271) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Rect rect = new Rect();
                    Flow flow = this.$viewModel.topClippingBounds;
                    C01281 c01281 = new C01281(0, this.$view, rect);
                    this.label = 1;
                    if (flow.collect(c01281, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$2$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ Map $childViews;
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            final /* synthetic */ ViewStateAccessor $viewState;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(ViewGroup viewGroup, KeyguardRootViewModel keyguardRootViewModel, ViewStateAccessor viewStateAccessor, Map map, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardRootViewModel;
                this.$viewState = viewStateAccessor;
                this.$view = viewGroup;
                this.$childViews = map;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$view, this.$viewModel, this.$viewState, this.$childViews, continuation);
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
                    Flow alpha = this.$viewModel.alpha(this.$viewState);
                    C01271.C01281 c01281 = new C01271.C01281(1, this.$view, this.$childViews);
                    this.label = 1;
                    if (alpha.collect(c01281, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$2$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ Map $childViews;
            final /* synthetic */ KeyguardRootViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$2$1$3$1, reason: invalid class name and collision with other inner class name */
            public final class C01291 implements FlowCollector {
                public final /* synthetic */ Map $childViews;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ C01291(int i, Map map) {
                    this.$r8$classId = i;
                    this.$childViews = map;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    KeyguardState keyguardState;
                    Unit unit = Unit.INSTANCE;
                    switch (this.$r8$classId) {
                        case 0:
                            float floatValue = ((Number) obj).floatValue();
                            Map map = this.$childViews;
                            KeyguardRootViewBinder keyguardRootViewBinder = KeyguardRootViewBinder.INSTANCE;
                            View view = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.burn_in_layer, map);
                            if (view != null) {
                                view.setTranslationY(floatValue);
                            }
                            View view2 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.lockscreen_clock_view_large, this.$childViews);
                            if (view2 != null) {
                                view2.setTranslationY(floatValue);
                            }
                            View view3 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.aod_notification_icon_container, this.$childViews);
                            if (view3 != null) {
                                view3.setTranslationY(floatValue);
                                break;
                            }
                            break;
                        case 1:
                            StateToValue stateToValue = (StateToValue) obj;
                            Float f = stateToValue.value;
                            if (f != null) {
                                float floatValue2 = f.floatValue();
                                KeyguardState keyguardState2 = KeyguardState.AOD;
                                KeyguardState keyguardState3 = stateToValue.from;
                                if (keyguardState3 != keyguardState2 && (keyguardState = stateToValue.to) != keyguardState2) {
                                    KeyguardState keyguardState4 = KeyguardState.GLANCEABLE_HUB;
                                    if (keyguardState3 == keyguardState4 || keyguardState == keyguardState4) {
                                        for (Map.Entry entry : this.$childViews.entrySet()) {
                                            int intValue = ((Number) entry.getKey()).intValue();
                                            View view4 = (View) entry.getValue();
                                            KeyguardRootViewBinder keyguardRootViewBinder2 = KeyguardRootViewBinder.INSTANCE;
                                            if (intValue != R.id.keyguard_indication_area && intValue != R.id.start_button && intValue != R.id.end_button && intValue != R.id.lock_icon_view && intValue != R.id.device_entry_icon_view) {
                                                view4.setTranslationX(floatValue2);
                                            }
                                        }
                                        break;
                                    }
                                } else {
                                    Map map2 = this.$childViews;
                                    KeyguardRootViewBinder keyguardRootViewBinder3 = KeyguardRootViewBinder.INSTANCE;
                                    View view5 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.burn_in_layer, map2);
                                    if (view5 != null) {
                                        view5.setTranslationX(floatValue2);
                                    }
                                    View view6 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.aod_notification_icon_container, this.$childViews);
                                    if (view6 != null) {
                                        view6.setTranslationX(floatValue2);
                                        break;
                                    }
                                }
                            }
                            break;
                        case 2:
                            int intValue2 = ((Number) obj).intValue();
                            Map map3 = this.$childViews;
                            KeyguardRootViewBinder keyguardRootViewBinder4 = KeyguardRootViewBinder.INSTANCE;
                            View view7 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.burn_in_layer, map3);
                            if (view7 != null) {
                                view7.setVisibility(intValue2);
                                break;
                            }
                            break;
                        case 3:
                            float floatValue3 = ((Number) obj).floatValue();
                            Map map4 = this.$childViews;
                            KeyguardRootViewBinder keyguardRootViewBinder5 = KeyguardRootViewBinder.INSTANCE;
                            View view8 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.keyguard_status_view, map4);
                            if (view8 != null) {
                                view8.setAlpha(floatValue3);
                                break;
                            }
                            break;
                        case 4:
                            float floatValue4 = ((Number) obj).floatValue();
                            Map map5 = this.$childViews;
                            KeyguardRootViewBinder keyguardRootViewBinder6 = KeyguardRootViewBinder.INSTANCE;
                            View view9 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.keyguard_status_view, map5);
                            if (view9 != null) {
                                view9.setAlpha(floatValue4);
                                break;
                            }
                            break;
                        default:
                            BurnInScaleViewModel burnInScaleViewModel = (BurnInScaleViewModel) obj;
                            if (burnInScaleViewModel.scaleClockOnly) {
                                Map map6 = this.$childViews;
                                KeyguardRootViewBinder keyguardRootViewBinder7 = KeyguardRootViewBinder.INSTANCE;
                                View view10 = (View) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(R.id.lockscreen_clock_view_large, map6);
                                if (view10 != null) {
                                    float f2 = burnInScaleViewModel.scale;
                                    view10.setScaleX(f2);
                                    view10.setScaleY(f2);
                                    break;
                                }
                            }
                            break;
                    }
                    return unit;
                }
            }

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
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
                KeyguardRootViewModel$special$$inlined$map$4 keyguardRootViewModel$special$$inlined$map$4 = this.$viewModel.translationY;
                C01291 c01291 = new C01291(0, this.$childViews);
                this.label = 1;
                keyguardRootViewModel$special$$inlined$map$4.collect(c01291, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$2$1$4, reason: invalid class name */
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
                    ChannelLimitedFlowMerge channelLimitedFlowMerge = this.$viewModel.translationX;
                    AnonymousClass3.C01291 c01291 = new AnonymousClass3.C01291(1, this.$childViews);
                    this.label = 1;
                    if (channelLimitedFlowMerge.collect(c01291, this) == coroutineSingletons) {
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
        public AnonymousClass1(ViewGroup viewGroup, KeyguardRootViewModel keyguardRootViewModel, ViewStateAccessor viewStateAccessor, Map map, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = keyguardRootViewModel;
            this.$view = viewGroup;
            this.$viewState = viewStateAccessor;
            this.$childViews = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$viewModel, this.$viewState, this.$childViews, continuation);
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
            CoroutineTracingKt.launch$default(coroutineScope, null, new C01271(this.$viewModel, this.$view, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass2(this.$view, this.$viewModel, this.$viewState, this.$childViews, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass3(this.$viewModel, this.$childViews, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass4(this.$viewModel, this.$childViews, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardRootViewBinder$bind$2(ViewGroup viewGroup, KeyguardRootViewModel keyguardRootViewModel, ViewStateAccessor viewStateAccessor, Map map, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = keyguardRootViewModel;
        this.$view = viewGroup;
        this.$viewState = viewStateAccessor;
        this.$childViews = map;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardRootViewModel keyguardRootViewModel = this.$viewModel;
        KeyguardRootViewBinder$bind$2 keyguardRootViewBinder$bind$2 = new KeyguardRootViewBinder$bind$2(this.$view, keyguardRootViewModel, this.$viewState, this.$childViews, (Continuation) obj3);
        keyguardRootViewBinder$bind$2.L$0 = (LifecycleOwner) obj;
        return keyguardRootViewBinder$bind$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$viewModel, this.$viewState, this.$childViews, null);
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
