package com.android.systemui.keyguard.ui.binder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$bind$1;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerUdfpsIconViewModel;
import com.android.systemui.log.LongPressHandlingViewLogger;
import dagger.Lazy;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AlternateBouncerViewBinder$optionallyAddUdfpsViews$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ LongPressHandlingViewLogger $logger;
    final /* synthetic */ Lazy $udfpsA11yOverlayViewModel;
    final /* synthetic */ AlternateBouncerUdfpsIconViewModel $udfpsIconViewModel;
    final /* synthetic */ ConstraintLayout $view;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$optionallyAddUdfpsViews$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LongPressHandlingViewLogger $logger;
        final /* synthetic */ Lazy $udfpsA11yOverlayViewModel;
        final /* synthetic */ AlternateBouncerUdfpsIconViewModel $udfpsIconViewModel;
        final /* synthetic */ ConstraintLayout $view;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$optionallyAddUdfpsViews$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C00961 extends SuspendLambda implements Function2 {
            final /* synthetic */ LongPressHandlingViewLogger $logger;
            final /* synthetic */ Lazy $udfpsA11yOverlayViewModel;
            final /* synthetic */ AlternateBouncerUdfpsIconViewModel $udfpsIconViewModel;
            final /* synthetic */ ConstraintLayout $view;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00961(AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel, ConstraintLayout constraintLayout, Lazy lazy, LongPressHandlingViewLogger longPressHandlingViewLogger, Continuation continuation) {
                super(2, continuation);
                this.$udfpsIconViewModel = alternateBouncerUdfpsIconViewModel;
                this.$view = constraintLayout;
                this.$udfpsA11yOverlayViewModel = lazy;
                this.$logger = longPressHandlingViewLogger;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00961(this.$udfpsIconViewModel, this.$view, this.$udfpsA11yOverlayViewModel, this.$logger, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00961) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel = this.$udfpsIconViewModel;
                    ChannelFlowTransformLatest channelFlowTransformLatest = alternateBouncerUdfpsIconViewModel.iconLocation;
                    AlternateBouncerViewBinder$bind$1.AnonymousClass1.C00931.C00941 c00941 = new AlternateBouncerViewBinder$bind$1.AnonymousClass1.C00931.C00941(this.$view, this.$udfpsA11yOverlayViewModel, this.$logger, alternateBouncerUdfpsIconViewModel, 1);
                    this.label = 1;
                    if (channelFlowTransformLatest.collect(c00941, this) == coroutineSingletons) {
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
        public AnonymousClass1(AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel, ConstraintLayout constraintLayout, Lazy lazy, LongPressHandlingViewLogger longPressHandlingViewLogger, Continuation continuation) {
            super(2, continuation);
            this.$udfpsIconViewModel = alternateBouncerUdfpsIconViewModel;
            this.$view = constraintLayout;
            this.$udfpsA11yOverlayViewModel = lazy;
            this.$logger = longPressHandlingViewLogger;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$udfpsIconViewModel, this.$view, this.$udfpsA11yOverlayViewModel, this.$logger, continuation);
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
            CoroutineTracingKt.launch$default((CoroutineScope) this.L$0, null, new C00961(this.$udfpsIconViewModel, this.$view, this.$udfpsA11yOverlayViewModel, this.$logger, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlternateBouncerViewBinder$optionallyAddUdfpsViews$1(AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel, ConstraintLayout constraintLayout, Lazy lazy, LongPressHandlingViewLogger longPressHandlingViewLogger, Continuation continuation) {
        super(3, continuation);
        this.$udfpsIconViewModel = alternateBouncerUdfpsIconViewModel;
        this.$view = constraintLayout;
        this.$udfpsA11yOverlayViewModel = lazy;
        this.$logger = longPressHandlingViewLogger;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AlternateBouncerViewBinder$optionallyAddUdfpsViews$1 alternateBouncerViewBinder$optionallyAddUdfpsViews$1 = new AlternateBouncerViewBinder$optionallyAddUdfpsViews$1(this.$udfpsIconViewModel, this.$view, this.$udfpsA11yOverlayViewModel, this.$logger, (Continuation) obj3);
        alternateBouncerViewBinder$optionallyAddUdfpsViews$1.L$0 = (LifecycleOwner) obj;
        return alternateBouncerViewBinder$optionallyAddUdfpsViews$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$udfpsIconViewModel, this.$view, this.$udfpsA11yOverlayViewModel, this.$logger, null);
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
