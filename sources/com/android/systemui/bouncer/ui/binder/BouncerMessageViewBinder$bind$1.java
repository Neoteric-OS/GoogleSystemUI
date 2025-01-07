package com.android.systemui.bouncer.ui.binder;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.BouncerKeyguardMessageArea;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor;
import com.android.systemui.bouncer.shared.model.BouncerMessageModel;
import com.android.systemui.bouncer.ui.BouncerMessageView;
import com.android.systemui.log.BouncerLogger;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.policy.ConfigurationController;
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
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BouncerMessageViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ BouncerLogger $bouncerLogger;
    final /* synthetic */ KeyguardMessageAreaController.Factory $factory;
    final /* synthetic */ BouncerMessageInteractor $interactor;
    final /* synthetic */ BouncerMessageView $view;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bouncer.ui.binder.BouncerMessageViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ BouncerLogger $bouncerLogger;
        final /* synthetic */ BouncerMessageInteractor $interactor;
        final /* synthetic */ BouncerMessageView $view;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bouncer.ui.binder.BouncerMessageViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C00491 extends SuspendLambda implements Function2 {
            final /* synthetic */ BouncerLogger $bouncerLogger;
            final /* synthetic */ BouncerMessageInteractor $interactor;
            final /* synthetic */ BouncerMessageView $view;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00491(BouncerMessageInteractor bouncerMessageInteractor, BouncerMessageView bouncerMessageView, BouncerLogger bouncerLogger, Continuation continuation) {
                super(2, continuation);
                this.$interactor = bouncerMessageInteractor;
                this.$bouncerLogger = bouncerLogger;
                this.$view = bouncerMessageView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00491(this.$interactor, this.$view, this.$bouncerLogger, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00491) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                StateFlowImpl stateFlowImpl = this.$interactor.bouncerMessage;
                final BouncerLogger bouncerLogger = this.$bouncerLogger;
                final BouncerMessageView bouncerMessageView = this.$view;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.bouncer.ui.binder.BouncerMessageViewBinder.bind.1.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        BouncerMessageModel bouncerMessageModel = (BouncerMessageModel) obj2;
                        BouncerLogger.this.bouncerMessageUpdated(bouncerMessageModel);
                        BouncerMessageView bouncerMessageView2 = bouncerMessageView;
                        BouncerMessageViewBinder.access$updateView(bouncerMessageView2.primaryMessage, bouncerMessageView2.primaryMessageView, bouncerMessageModel != null ? bouncerMessageModel.message : null, true);
                        BouncerMessageViewBinder.access$updateView(bouncerMessageView2.secondaryMessage, bouncerMessageView2.secondaryMessageView, bouncerMessageModel != null ? bouncerMessageModel.secondaryMessage : null, false);
                        bouncerMessageView2.requestLayout();
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                stateFlowImpl.collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BouncerMessageInteractor bouncerMessageInteractor, BouncerMessageView bouncerMessageView, BouncerLogger bouncerLogger, Continuation continuation) {
            super(2, continuation);
            this.$bouncerLogger = bouncerLogger;
            this.$interactor = bouncerMessageInteractor;
            this.$view = bouncerMessageView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$interactor, this.$view, this.$bouncerLogger, continuation);
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
            BouncerLogger bouncerLogger = this.$bouncerLogger;
            bouncerLogger.getClass();
            bouncerLogger.buffer.log("BouncerLog", LogLevel.DEBUG, "Starting BouncerMessageInteractor.bouncerMessage collector", null);
            BuildersKt.launch$default(coroutineScope, null, null, new C00491(this.$interactor, this.$view, this.$bouncerLogger, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BouncerMessageViewBinder$bind$1(BouncerMessageView bouncerMessageView, KeyguardMessageAreaController.Factory factory, BouncerLogger bouncerLogger, BouncerMessageInteractor bouncerMessageInteractor, Continuation continuation) {
        super(3, continuation);
        this.$view = bouncerMessageView;
        this.$factory = factory;
        this.$bouncerLogger = bouncerLogger;
        this.$interactor = bouncerMessageInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BouncerMessageViewBinder$bind$1 bouncerMessageViewBinder$bind$1 = new BouncerMessageViewBinder$bind$1(this.$view, this.$factory, this.$bouncerLogger, this.$interactor, (Continuation) obj3);
        bouncerMessageViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return bouncerMessageViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            BouncerMessageView bouncerMessageView = this.$view;
            KeyguardMessageAreaController.Factory factory = this.$factory;
            BouncerKeyguardMessageArea bouncerKeyguardMessageArea = bouncerMessageView.primaryMessageView;
            KeyguardUpdateMonitor keyguardUpdateMonitor = factory.mKeyguardUpdateMonitor;
            ConfigurationController configurationController = factory.mConfigurationController;
            KeyguardMessageAreaController keyguardMessageAreaController = new KeyguardMessageAreaController(bouncerKeyguardMessageArea, keyguardUpdateMonitor, configurationController);
            bouncerMessageView.primaryMessage = keyguardMessageAreaController;
            keyguardMessageAreaController.init$9();
            KeyguardMessageAreaController keyguardMessageAreaController2 = new KeyguardMessageAreaController(bouncerMessageView.secondaryMessageView, factory.mKeyguardUpdateMonitor, configurationController);
            bouncerMessageView.secondaryMessage = keyguardMessageAreaController2;
            keyguardMessageAreaController2.init$9();
            KeyguardMessageAreaController keyguardMessageAreaController3 = this.$view.primaryMessage;
            if (keyguardMessageAreaController3 != null) {
                keyguardMessageAreaController3.setIsVisible(true);
            }
            KeyguardMessageAreaController keyguardMessageAreaController4 = this.$view.secondaryMessage;
            if (keyguardMessageAreaController4 != null) {
                keyguardMessageAreaController4.setIsVisible(true);
            }
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$interactor, this.$view, this.$bouncerLogger, null);
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
