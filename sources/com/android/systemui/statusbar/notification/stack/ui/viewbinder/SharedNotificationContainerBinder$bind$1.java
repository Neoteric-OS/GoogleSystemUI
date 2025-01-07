package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.statusbar.notification.stack.ui.view.SharedNotificationContainer;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
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
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SharedNotificationContainerBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ SharedNotificationContainer $view;
    final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SharedNotificationContainerBinder this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ SharedNotificationContainer $view;
        final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SharedNotificationContainerBinder this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C02071 extends SuspendLambda implements Function2 {
            final /* synthetic */ SharedNotificationContainer $view;
            final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
            int label;
            final /* synthetic */ SharedNotificationContainerBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02071(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainer sharedNotificationContainer, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sharedNotificationContainerViewModel;
                this.$view = sharedNotificationContainer;
                this.this$0 = sharedNotificationContainerBinder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C02071(this.$viewModel, this.$view, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C02071) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SafeFlow safeFlow = this.$viewModel.configurationBasedDimensions;
                    final SharedNotificationContainer sharedNotificationContainer = this.$view;
                    final SharedNotificationContainerBinder sharedNotificationContainerBinder = this.this$0;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder.bind.1.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            SharedNotificationContainerViewModel.ConfigurationBasedDimensions configurationBasedDimensions = (SharedNotificationContainerViewModel.ConfigurationBasedDimensions) obj2;
                            boolean z = configurationBasedDimensions.useSplitShade;
                            SharedNotificationContainer sharedNotificationContainer2 = SharedNotificationContainer.this;
                            sharedNotificationContainer2.getClass();
                            ConstraintSet constraintSet = new ConstraintSet();
                            constraintSet.clone(sharedNotificationContainer2.baseConstraintSet);
                            constraintSet.connect(R.id.notification_stack_scroller, 6, z ? R.id.nssl_guideline : 0, 6, configurationBasedDimensions.marginStart);
                            constraintSet.connect(R.id.notification_stack_scroller, 7, 0, 7, configurationBasedDimensions.marginEnd);
                            constraintSet.connect(R.id.notification_stack_scroller, 4, 0, 4, configurationBasedDimensions.marginBottom);
                            constraintSet.connect(R.id.notification_stack_scroller, 3, 0, 3, configurationBasedDimensions.marginTop);
                            constraintSet.applyTo(sharedNotificationContainer2);
                            SharedNotificationContainerBinder sharedNotificationContainerBinder2 = sharedNotificationContainerBinder;
                            sharedNotificationContainerBinder2.controller.setOverExpansion(0.0f);
                            sharedNotificationContainerBinder2.controller.setOverScrollAmount(0);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (safeFlow.collect(flowCollector, this) == coroutineSingletons) {
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
        public AnonymousClass1(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainer sharedNotificationContainer, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = sharedNotificationContainerViewModel;
            this.$view = sharedNotificationContainer;
            this.this$0 = sharedNotificationContainerBinder;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$view, this.this$0, continuation);
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
            BuildersKt.launch$default((CoroutineScope) this.L$0, null, null, new C02071(this.$viewModel, this.$view, this.this$0, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedNotificationContainerBinder$bind$1(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainer sharedNotificationContainer, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = sharedNotificationContainerViewModel;
        this.$view = sharedNotificationContainer;
        this.this$0 = sharedNotificationContainerBinder;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SharedNotificationContainerBinder$bind$1 sharedNotificationContainerBinder$bind$1 = new SharedNotificationContainerBinder$bind$1(this.$viewModel, this.$view, this.this$0, (Continuation) obj3);
        sharedNotificationContainerBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return sharedNotificationContainerBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$view, this.this$0, null);
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
