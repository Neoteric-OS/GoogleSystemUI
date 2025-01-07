package com.android.systemui.statusbar.notification.row.ui.viewbinder;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.ActivatableNotificationViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ActivatableNotificationViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ TouchHandler $touchHandler;
    final /* synthetic */ ActivatableNotificationView $view;
    final /* synthetic */ ActivatableNotificationViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ TouchHandler $touchHandler;
        final /* synthetic */ ActivatableNotificationView $view;
        final /* synthetic */ ActivatableNotificationViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C02041 extends SuspendLambda implements Function2 {
            final /* synthetic */ TouchHandler $touchHandler;
            final /* synthetic */ ActivatableNotificationViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02041(ActivatableNotificationViewModel activatableNotificationViewModel, TouchHandler touchHandler, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = activatableNotificationViewModel;
                this.$touchHandler = touchHandler;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C02041(this.$viewModel, this.$touchHandler, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C02041) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow isTouchable = this.$viewModel.isTouchable();
                    final TouchHandler touchHandler = this.$touchHandler;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder.bind.1.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            TouchHandler.this.isTouchEnabled = ((Boolean) obj2).booleanValue();
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (isTouchable.collect(flowCollector, this) == coroutineSingletons) {
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
        public AnonymousClass1(ActivatableNotificationView activatableNotificationView, TouchHandler touchHandler, ActivatableNotificationViewModel activatableNotificationViewModel, Continuation continuation) {
            super(2, continuation);
            this.$view = activatableNotificationView;
            this.$touchHandler = touchHandler;
            this.$viewModel = activatableNotificationViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$touchHandler, this.$viewModel, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
            BuildersKt.launch$default((CoroutineScope) this.L$0, null, null, new C02041(this.$viewModel, this.$touchHandler, null), 3);
            ActivatableNotificationViewBinder activatableNotificationViewBinder = ActivatableNotificationViewBinder.INSTANCE;
            ActivatableNotificationView activatableNotificationView = this.$view;
            TouchHandler touchHandler = this.$touchHandler;
            this.label = 1;
            ActivatableNotificationViewBinder.access$registerListenersWhileAttached(activatableNotificationViewBinder, activatableNotificationView, touchHandler, this);
            return coroutineSingletons;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivatableNotificationViewBinder$bind$1(ActivatableNotificationView activatableNotificationView, TouchHandler touchHandler, ActivatableNotificationViewModel activatableNotificationViewModel, Continuation continuation) {
        super(3, continuation);
        this.$view = activatableNotificationView;
        this.$touchHandler = touchHandler;
        this.$viewModel = activatableNotificationViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ActivatableNotificationViewBinder$bind$1 activatableNotificationViewBinder$bind$1 = new ActivatableNotificationViewBinder$bind$1(this.$view, this.$touchHandler, this.$viewModel, (Continuation) obj3);
        activatableNotificationViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return activatableNotificationViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$touchHandler, this.$viewModel, null);
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
