package com.android.systemui.keyguard.ui.binder;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.data.repository.KeyguardClockRepositoryImpl$special$$inlined$map$2;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewClockViewModel;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.plugins.clocks.ClockEvents;
import com.android.systemui.plugins.clocks.ClockSettings;
import com.android.systemui.shared.clocks.ClockRegistry;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardPreviewClockViewBinder$bind$3 extends SuspendLambda implements Function3 {
    final /* synthetic */ ClockRegistry $clockRegistry;
    final /* synthetic */ Context $context;
    final /* synthetic */ ConstraintLayout $rootView;
    final /* synthetic */ KFunction $updateClockAppearance;
    final /* synthetic */ KeyguardPreviewClockViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardPreviewClockViewBinder$bind$3$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ClockRegistry $clockRegistry;
        final /* synthetic */ Context $context;
        final /* synthetic */ ConstraintLayout $rootView;
        final /* synthetic */ KFunction $updateClockAppearance;
        final /* synthetic */ KeyguardPreviewClockViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardPreviewClockViewBinder$bind$3$1$1, reason: invalid class name and collision with other inner class name */
        final class C01201 extends SuspendLambda implements Function2 {
            final /* synthetic */ Context $context;
            final /* synthetic */ Ref$ObjectRef $lastClock;
            final /* synthetic */ ConstraintLayout $rootView;
            final /* synthetic */ KFunction $updateClockAppearance;
            final /* synthetic */ KeyguardPreviewClockViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardPreviewClockViewBinder$bind$3$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C01211 implements FlowCollector {
                public final /* synthetic */ Context $context;
                public final /* synthetic */ Ref$ObjectRef $lastClock;
                public final /* synthetic */ ConstraintLayout $rootView;
                public final /* synthetic */ KFunction $updateClockAppearance;
                public final /* synthetic */ KeyguardPreviewClockViewModel $viewModel;

                public C01211(Ref$ObjectRef ref$ObjectRef, KFunction kFunction, KeyguardPreviewClockViewModel keyguardPreviewClockViewModel, Context context, ConstraintLayout constraintLayout) {
                    this.$lastClock = ref$ObjectRef;
                    this.$updateClockAppearance = kFunction;
                    this.$viewModel = keyguardPreviewClockViewModel;
                    this.$context = context;
                    this.$rootView = constraintLayout;
                }

                /* JADX WARN: Removed duplicated region for block: B:12:0x0098  */
                /* JADX WARN: Removed duplicated region for block: B:20:0x00e4  */
                /* JADX WARN: Removed duplicated region for block: B:28:0x00fe A[EDGE_INSN: B:28:0x00fe->B:29:0x00fe BREAK  A[LOOP:1: B:18:0x00db->B:26:0x00fa], SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:32:0x0114  */
                /* JADX WARN: Removed duplicated region for block: B:44:0x0182  */
                /* JADX WARN: Removed duplicated region for block: B:47:0x01a1  */
                /* JADX WARN: Removed duplicated region for block: B:50:0x01c6  */
                /* JADX WARN: Removed duplicated region for block: B:53:0x0266  */
                /* JADX WARN: Removed duplicated region for block: B:55:0x0272  */
                /* JADX WARN: Removed duplicated region for block: B:63:0x028e  */
                /* JADX WARN: Removed duplicated region for block: B:65:0x029a A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:72:0x0290  */
                /* JADX WARN: Removed duplicated region for block: B:74:0x0268  */
                /* JADX WARN: Removed duplicated region for block: B:75:0x01cd  */
                /* JADX WARN: Removed duplicated region for block: B:76:0x01a3  */
                /* JADX WARN: Removed duplicated region for block: B:77:0x0184  */
                /* JADX WARN: Removed duplicated region for block: B:80:0x0042  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(com.android.systemui.plugins.clocks.ClockController r19, kotlin.coroutines.Continuation r20) {
                    /*
                        Method dump skipped, instructions count: 715
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.binder.KeyguardPreviewClockViewBinder$bind$3.AnonymousClass1.C01201.C01211.emit(com.android.systemui.plugins.clocks.ClockController, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01201(KeyguardPreviewClockViewModel keyguardPreviewClockViewModel, Ref$ObjectRef ref$ObjectRef, KFunction kFunction, Context context, ConstraintLayout constraintLayout, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardPreviewClockViewModel;
                this.$lastClock = ref$ObjectRef;
                this.$updateClockAppearance = kFunction;
                this.$context = context;
                this.$rootView = constraintLayout;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01201(this.$viewModel, this.$lastClock, this.$updateClockAppearance, this.$context, this.$rootView, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01201) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    KeyguardPreviewClockViewModel keyguardPreviewClockViewModel = this.$viewModel;
                    KeyguardClockRepositoryImpl$special$$inlined$map$2 keyguardClockRepositoryImpl$special$$inlined$map$2 = keyguardPreviewClockViewModel.previewClock;
                    C01211 c01211 = new C01211(this.$lastClock, this.$updateClockAppearance, keyguardPreviewClockViewModel, this.$context, this.$rootView);
                    this.label = 1;
                    if (keyguardClockRepositoryImpl$special$$inlined$map$2.collect(c01211, this) == coroutineSingletons) {
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
        public AnonymousClass1(KeyguardPreviewClockViewModel keyguardPreviewClockViewModel, KFunction kFunction, Context context, ConstraintLayout constraintLayout, ClockRegistry clockRegistry, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = keyguardPreviewClockViewModel;
            this.$updateClockAppearance = kFunction;
            this.$context = context;
            this.$rootView = constraintLayout;
            this.$clockRegistry = clockRegistry;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$updateClockAppearance, this.$context, this.$rootView, this.$clockRegistry, continuation);
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
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            StandaloneCoroutine launch$default = CoroutineTracingKt.launch$default(coroutineScope, null, new C01201(this.$viewModel, ref$ObjectRef, this.$updateClockAppearance, this.$context, this.$rootView, null), 6);
            final ClockRegistry clockRegistry = this.$clockRegistry;
            launch$default.invokeOnCompletion(new Function1() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardPreviewClockViewBinder.bind.3.1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    ClockEvents events;
                    ClockController clockController = (ClockController) Ref$ObjectRef.this.element;
                    if (clockController != null && (events = clockController.getEvents()) != null) {
                        ClockSettings clockSettings = clockRegistry.settings;
                        events.onSeedColorChanged(clockSettings != null ? clockSettings.getSeedColor() : null);
                    }
                    return Unit.INSTANCE;
                }
            });
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardPreviewClockViewBinder$bind$3(KeyguardPreviewClockViewModel keyguardPreviewClockViewModel, KFunction kFunction, Context context, ConstraintLayout constraintLayout, ClockRegistry clockRegistry, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = keyguardPreviewClockViewModel;
        this.$updateClockAppearance = kFunction;
        this.$context = context;
        this.$rootView = constraintLayout;
        this.$clockRegistry = clockRegistry;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardPreviewClockViewBinder$bind$3 keyguardPreviewClockViewBinder$bind$3 = new KeyguardPreviewClockViewBinder$bind$3(this.$viewModel, this.$updateClockAppearance, this.$context, this.$rootView, this.$clockRegistry, (Continuation) obj3);
        keyguardPreviewClockViewBinder$bind$3.L$0 = (LifecycleOwner) obj;
        return keyguardPreviewClockViewBinder$bind$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$updateClockAppearance, this.$context, this.$rootView, this.$clockRegistry, null);
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
