package com.android.systemui.statusbar.pipeline.satellite.ui.binder;

import android.widget.ImageView;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.ui.binder.IconViewBinder;
import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModel;
import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModelImpl;
import com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView;
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
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceBasedSatelliteIconBinder$bind$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ SingleBindableStatusBarIconView $view;
    final /* synthetic */ DeviceBasedSatelliteViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.satellite.ui.binder.DeviceBasedSatelliteIconBinder$bind$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LifecycleOwner $$this$withDefaultBinding;
        final /* synthetic */ SingleBindableStatusBarIconView $view;
        final /* synthetic */ DeviceBasedSatelliteViewModel $viewModel;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.satellite.ui.binder.DeviceBasedSatelliteIconBinder$bind$2$1$1, reason: invalid class name and collision with other inner class name */
        final class C02331 extends SuspendLambda implements Function2 {
            final /* synthetic */ SingleBindableStatusBarIconView $view;
            final /* synthetic */ DeviceBasedSatelliteViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02331(DeviceBasedSatelliteViewModel deviceBasedSatelliteViewModel, SingleBindableStatusBarIconView singleBindableStatusBarIconView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = deviceBasedSatelliteViewModel;
                this.$view = singleBindableStatusBarIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C02331(this.$viewModel, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((C02331) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                ReadonlyStateFlow readonlyStateFlow = ((DeviceBasedSatelliteViewModelImpl) this.$viewModel).icon;
                final SingleBindableStatusBarIconView singleBindableStatusBarIconView = this.$view;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.pipeline.satellite.ui.binder.DeviceBasedSatelliteIconBinder.bind.2.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Icon icon = (Icon) obj2;
                        SingleBindableStatusBarIconView singleBindableStatusBarIconView2 = SingleBindableStatusBarIconView.this;
                        if (icon == null) {
                            ImageView imageView = singleBindableStatusBarIconView2.iconView;
                            if (imageView == null) {
                                imageView = null;
                            }
                            imageView.setImageDrawable(null);
                        } else {
                            ImageView imageView2 = singleBindableStatusBarIconView2.iconView;
                            IconViewBinder.bind(icon, imageView2 != null ? imageView2 : null);
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LifecycleOwner lifecycleOwner, DeviceBasedSatelliteViewModel deviceBasedSatelliteViewModel, SingleBindableStatusBarIconView singleBindableStatusBarIconView, Continuation continuation) {
            super(2, continuation);
            this.$$this$withDefaultBinding = lifecycleOwner;
            this.$viewModel = deviceBasedSatelliteViewModel;
            this.$view = singleBindableStatusBarIconView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$$this$withDefaultBinding, this.$viewModel, this.$view, continuation);
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
                LifecycleOwner lifecycleOwner = this.$$this$withDefaultBinding;
                Lifecycle.State state = Lifecycle.State.STARTED;
                C02331 c02331 = new C02331(this.$viewModel, this.$view, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c02331, this) == coroutineSingletons) {
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
    public DeviceBasedSatelliteIconBinder$bind$2(DeviceBasedSatelliteViewModel deviceBasedSatelliteViewModel, SingleBindableStatusBarIconView singleBindableStatusBarIconView, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = deviceBasedSatelliteViewModel;
        this.$view = singleBindableStatusBarIconView;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DeviceBasedSatelliteIconBinder$bind$2 deviceBasedSatelliteIconBinder$bind$2 = new DeviceBasedSatelliteIconBinder$bind$2(this.$viewModel, this.$view, (Continuation) obj3);
        deviceBasedSatelliteIconBinder$bind$2.L$0 = (LifecycleOwner) obj;
        Unit unit = Unit.INSTANCE;
        deviceBasedSatelliteIconBinder$bind$2.invokeSuspend(unit);
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
        return Unit.INSTANCE;
    }
}
