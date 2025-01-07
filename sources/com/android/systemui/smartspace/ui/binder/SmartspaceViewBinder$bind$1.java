package com.android.systemui.smartspace.ui.binder;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.smartspace.ui.viewmodel.SmartspaceViewModel;
import com.android.systemui.smartspace.ui.viewmodel.SmartspaceViewModel$special$$inlined$filter$1;
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

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SmartspaceViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ BcSmartspaceDataPlugin.SmartspaceView $smartspaceView;
    final /* synthetic */ SmartspaceViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.smartspace.ui.binder.SmartspaceViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ BcSmartspaceDataPlugin.SmartspaceView $smartspaceView;
        final /* synthetic */ SmartspaceViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.smartspace.ui.binder.SmartspaceViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C01881 extends SuspendLambda implements Function2 {
            final /* synthetic */ BcSmartspaceDataPlugin.SmartspaceView $smartspaceView;
            final /* synthetic */ SmartspaceViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01881(SmartspaceViewModel smartspaceViewModel, BcSmartspaceDataPlugin.SmartspaceView smartspaceView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = smartspaceViewModel;
                this.$smartspaceView = smartspaceView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01881(this.$viewModel, this.$smartspaceView, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01881) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SmartspaceViewModel$special$$inlined$filter$1 smartspaceViewModel$special$$inlined$filter$1 = this.$viewModel.isAwake;
                    final BcSmartspaceDataPlugin.SmartspaceView smartspaceView = this.$smartspaceView;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.smartspace.ui.binder.SmartspaceViewBinder.bind.1.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            BcSmartspaceDataPlugin.SmartspaceView.this.setScreenOn(((Boolean) obj2).booleanValue());
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (smartspaceViewModel$special$$inlined$filter$1.collect(flowCollector, this) == coroutineSingletons) {
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
        public AnonymousClass1(SmartspaceViewModel smartspaceViewModel, BcSmartspaceDataPlugin.SmartspaceView smartspaceView, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = smartspaceViewModel;
            this.$smartspaceView = smartspaceView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$smartspaceView, continuation);
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
            BuildersKt.launch$default((CoroutineScope) this.L$0, null, null, new C01881(this.$viewModel, this.$smartspaceView, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmartspaceViewBinder$bind$1(SmartspaceViewModel smartspaceViewModel, BcSmartspaceDataPlugin.SmartspaceView smartspaceView, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = smartspaceViewModel;
        this.$smartspaceView = smartspaceView;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SmartspaceViewBinder$bind$1 smartspaceViewBinder$bind$1 = new SmartspaceViewBinder$bind$1(this.$viewModel, this.$smartspaceView, (Continuation) obj3);
        smartspaceViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return smartspaceViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$smartspaceView, null);
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
