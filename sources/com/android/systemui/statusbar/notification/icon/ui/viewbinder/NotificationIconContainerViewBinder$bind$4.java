package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationIconContainerViewBinder$bind$4 extends SuspendLambda implements Function2 {
    final /* synthetic */ ConfigurationState $configuration;
    final /* synthetic */ StatusBarIconViewBindingFailureTracker $failureTracker;
    final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
    final /* synthetic */ NotificationIconContainer $view;
    final /* synthetic */ NotificationIconContainerAlwaysOnDisplayViewModel $viewModel;
    final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$4$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ConfigurationState $configuration;
        final /* synthetic */ StatusBarIconViewBindingFailureTracker $failureTracker;
        final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
        final /* synthetic */ NotificationIconContainer $view;
        final /* synthetic */ NotificationIconContainerAlwaysOnDisplayViewModel $viewModel;
        final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$4$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function3 {
            final /* synthetic */ StateFlow $animsEnabled;
            final /* synthetic */ StateFlow $color;
            final /* synthetic */ StateFlow $tintAlpha;
            /* synthetic */ Object L$0;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$4$1$2$1, reason: invalid class name and collision with other inner class name */
            final class C01991 extends SuspendLambda implements Function2 {
                final /* synthetic */ StateFlow $animsEnabled;
                final /* synthetic */ StateFlow $color;
                final /* synthetic */ StatusBarIconView $sbiv;
                final /* synthetic */ StateFlow $tintAlpha;
                private /* synthetic */ Object L$0;
                int label;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$4$1$2$1$1, reason: invalid class name and collision with other inner class name */
                final class C02001 extends SuspendLambda implements Function2 {
                    final /* synthetic */ StateFlow $color;
                    final /* synthetic */ StatusBarIconView $sbiv;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C02001(StatusBarIconView statusBarIconView, StateFlow stateFlow, Continuation continuation) {
                        super(2, continuation);
                        this.$sbiv = statusBarIconView;
                        this.$color = stateFlow;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new C02001(this.$sbiv, this.$color, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ((C02001) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        int i = this.label;
                        Unit unit = Unit.INSTANCE;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            StatusBarIconView statusBarIconView = this.$sbiv;
                            StateFlow stateFlow = this.$color;
                            this.label = 1;
                            Object collect = stateFlow.collect(new StatusBarIconViewBinder$bindColor$$inlined$collectTracingEach$1(statusBarIconView, 0), this);
                            if (collect != coroutineSingletons) {
                                collect = unit;
                            }
                            if (collect == coroutineSingletons) {
                                return coroutineSingletons;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return unit;
                    }
                }

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$4$1$2$1$2, reason: invalid class name and collision with other inner class name */
                final class C02012 extends SuspendLambda implements Function2 {
                    final /* synthetic */ StatusBarIconView $sbiv;
                    final /* synthetic */ StateFlow $tintAlpha;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C02012(StatusBarIconView statusBarIconView, StateFlow stateFlow, Continuation continuation) {
                        super(2, continuation);
                        this.$sbiv = statusBarIconView;
                        this.$tintAlpha = stateFlow;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new C02012(this.$sbiv, this.$tintAlpha, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ((C02012) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        int i = this.label;
                        Unit unit = Unit.INSTANCE;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            StatusBarIconView statusBarIconView = this.$sbiv;
                            StateFlow stateFlow = this.$tintAlpha;
                            this.label = 1;
                            Object collect = stateFlow.collect(new StatusBarIconViewBinder$bindColor$$inlined$collectTracingEach$1(statusBarIconView, 2), this);
                            if (collect != coroutineSingletons) {
                                collect = unit;
                            }
                            if (collect == coroutineSingletons) {
                                return coroutineSingletons;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return unit;
                    }
                }

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$4$1$2$1$3, reason: invalid class name */
                final class AnonymousClass3 extends SuspendLambda implements Function2 {
                    final /* synthetic */ StateFlow $animsEnabled;
                    final /* synthetic */ StatusBarIconView $sbiv;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass3(StatusBarIconView statusBarIconView, StateFlow stateFlow, Continuation continuation) {
                        super(2, continuation);
                        this.$sbiv = statusBarIconView;
                        this.$animsEnabled = stateFlow;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new AnonymousClass3(this.$sbiv, this.$animsEnabled, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                        int i = this.label;
                        Unit unit = Unit.INSTANCE;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            StatusBarIconView statusBarIconView = this.$sbiv;
                            StateFlow stateFlow = this.$animsEnabled;
                            this.label = 1;
                            Object collect = stateFlow.collect(new StatusBarIconViewBinder$bindColor$$inlined$collectTracingEach$1(statusBarIconView, 1), this);
                            if (collect != coroutineSingletons) {
                                collect = unit;
                            }
                            if (collect == coroutineSingletons) {
                                return coroutineSingletons;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return unit;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01991(StatusBarIconView statusBarIconView, StateFlow stateFlow, StateFlow stateFlow2, StateFlow stateFlow3, Continuation continuation) {
                    super(2, continuation);
                    this.$sbiv = statusBarIconView;
                    this.$color = stateFlow;
                    this.$tintAlpha = stateFlow2;
                    this.$animsEnabled = stateFlow3;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    C01991 c01991 = new C01991(this.$sbiv, this.$color, this.$tintAlpha, this.$animsEnabled, continuation);
                    c01991.L$0 = obj;
                    return c01991;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    C01991 c01991 = (C01991) create((CoroutineScope) obj, (Continuation) obj2);
                    Unit unit = Unit.INSTANCE;
                    c01991.invokeSuspend(unit);
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
                    BuildersKt.launch$default(coroutineScope, null, null, new C02001(this.$sbiv, this.$color, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new C02012(this.$sbiv, this.$tintAlpha, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$sbiv, this.$animsEnabled, null), 3);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(StateFlow stateFlow, StateFlow stateFlow2, StateFlow stateFlow3, Continuation continuation) {
                super(3, continuation);
                this.$color = stateFlow;
                this.$tintAlpha = stateFlow2;
                this.$animsEnabled = stateFlow3;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$color, this.$tintAlpha, this.$animsEnabled, (Continuation) obj3);
                anonymousClass2.L$0 = (StatusBarIconView) obj2;
                return anonymousClass2.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    C01991 c01991 = new C01991((StatusBarIconView) this.L$0, this.$color, this.$tintAlpha, this.$animsEnabled, null);
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(this, c01991) == coroutineSingletons) {
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
        public AnonymousClass1(ConfigurationState configurationState, NotificationIconContainerViewBinder.IconViewStore iconViewStore, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, NotificationIconContainer notificationIconContainer, SystemBarUtilsState systemBarUtilsState, Continuation continuation) {
            super(2, continuation);
            this.$configuration = configurationState;
            this.$viewModel = notificationIconContainerAlwaysOnDisplayViewModel;
            this.$view = notificationIconContainer;
            this.$systemBarUtilsState = systemBarUtilsState;
            this.$viewStore = iconViewStore;
            this.$failureTracker = statusBarIconViewBindingFailureTracker;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$configuration, this.$viewStore, this.$failureTracker, this.$viewModel, this.$view, this.$systemBarUtilsState, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x00db  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x00de A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00a1 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00a2  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x00df A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instructions count: 224
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$4.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bind$4$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationIconContainer $view;
        final /* synthetic */ NotificationIconContainerAlwaysOnDisplayViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, NotificationIconContainer notificationIconContainer, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = notificationIconContainerAlwaysOnDisplayViewModel;
            this.$view = notificationIconContainer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$viewModel, this.$view, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = this.$viewModel.areContainerChangesAnimated;
                NotificationIconContainer notificationIconContainer = this.$view;
                this.label = 1;
                Object collect = flow.collect(new NotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1(notificationIconContainer, 0), this);
                if (collect != coroutineSingletons) {
                    collect = unit;
                }
                if (collect == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return unit;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationIconContainerViewBinder$bind$4(ConfigurationState configurationState, NotificationIconContainerViewBinder.IconViewStore iconViewStore, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, NotificationIconContainer notificationIconContainer, SystemBarUtilsState systemBarUtilsState, Continuation continuation) {
        super(2, continuation);
        this.$view = notificationIconContainer;
        this.$configuration = configurationState;
        this.$viewModel = notificationIconContainerAlwaysOnDisplayViewModel;
        this.$systemBarUtilsState = systemBarUtilsState;
        this.$viewStore = iconViewStore;
        this.$failureTracker = statusBarIconViewBindingFailureTracker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationIconContainer notificationIconContainer = this.$view;
        NotificationIconContainerViewBinder$bind$4 notificationIconContainerViewBinder$bind$4 = new NotificationIconContainerViewBinder$bind$4(this.$configuration, this.$viewStore, this.$failureTracker, this.$viewModel, notificationIconContainer, this.$systemBarUtilsState, continuation);
        notificationIconContainerViewBinder$bind$4.L$0 = obj;
        return notificationIconContainerViewBinder$bind$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        NotificationIconContainerViewBinder$bind$4 notificationIconContainerViewBinder$bind$4 = (NotificationIconContainerViewBinder$bind$4) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        notificationIconContainerViewBinder$bind$4.invokeSuspend(unit);
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
        NotificationIconContainer notificationIconContainer = this.$view;
        notificationIconContainer.mUseIncreasedIconScale = true;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$configuration, this.$viewStore, this.$failureTracker, this.$viewModel, notificationIconContainer, this.$systemBarUtilsState, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$view, null), 3);
        return Unit.INSTANCE;
    }
}
