package com.android.systemui.qs.footer.ui.compose;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsButtonViewModel;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsForegroundServicesButtonViewModel;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsSecurityButtonViewModel;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FooterActionsKt$FooterActions$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState $foregroundServices$delegate;
    final /* synthetic */ LifecycleOwner $qsVisibilityLifecycleOwner;
    final /* synthetic */ MutableState $security$delegate;
    final /* synthetic */ MutableState $userSwitcher$delegate;
    final /* synthetic */ FooterActionsViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Context $context;
        final /* synthetic */ FooterActionsViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FooterActionsViewModel footerActionsViewModel, Context context, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = footerActionsViewModel;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$viewModel, this.$context, continuation);
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
                Function2 function2 = this.$viewModel.observeDeviceMonitoringDialogRequests;
                Context context = this.$context;
                this.label = 1;
                if (function2.invoke(context, this) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$1$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ MutableState $foregroundServices$delegate;
        final /* synthetic */ MutableState $security$delegate;
        final /* synthetic */ MutableState $userSwitcher$delegate;
        final /* synthetic */ FooterActionsViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$1$2$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableState $security$delegate;
            final /* synthetic */ FooterActionsViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$1$2$1$1, reason: invalid class name and collision with other inner class name */
            public final class C01551 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ MutableState $security$delegate;

                public /* synthetic */ C01551(MutableState mutableState, int i) {
                    this.$r8$classId = i;
                    this.$security$delegate = mutableState;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            this.$security$delegate.setValue((FooterActionsSecurityButtonViewModel) obj);
                            break;
                        case 1:
                            this.$security$delegate.setValue((FooterActionsForegroundServicesButtonViewModel) obj);
                            break;
                        default:
                            this.$security$delegate.setValue((FooterActionsButtonViewModel) obj);
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(FooterActionsViewModel footerActionsViewModel, MutableState mutableState, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = footerActionsViewModel;
                this.$security$delegate = mutableState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.$viewModel, this.$security$delegate, continuation);
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
                    Flow flow = this.$viewModel.security;
                    C01551 c01551 = new C01551(this.$security$delegate, 0);
                    this.label = 1;
                    if (flow.collect(c01551, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$1$2$2, reason: invalid class name and collision with other inner class name */
        final class C01562 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableState $foregroundServices$delegate;
            final /* synthetic */ FooterActionsViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01562(FooterActionsViewModel footerActionsViewModel, MutableState mutableState, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = footerActionsViewModel;
                this.$foregroundServices$delegate = mutableState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01562(this.$viewModel, this.$foregroundServices$delegate, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01562) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = this.$viewModel.foregroundServices;
                    AnonymousClass1.C01551 c01551 = new AnonymousClass1.C01551(this.$foregroundServices$delegate, 1);
                    this.label = 1;
                    if (flow.collect(c01551, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.qs.footer.ui.compose.FooterActionsKt$FooterActions$1$2$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableState $userSwitcher$delegate;
            final /* synthetic */ FooterActionsViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(FooterActionsViewModel footerActionsViewModel, MutableState mutableState, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = footerActionsViewModel;
                this.$userSwitcher$delegate = mutableState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$viewModel, this.$userSwitcher$delegate, continuation);
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
                    Flow flow = this.$viewModel.userSwitcher;
                    AnonymousClass1.C01551 c01551 = new AnonymousClass1.C01551(this.$userSwitcher$delegate, 2);
                    this.label = 1;
                    if (flow.collect(c01551, this) == coroutineSingletons) {
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
        public AnonymousClass2(FooterActionsViewModel footerActionsViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = footerActionsViewModel;
            this.$security$delegate = mutableState;
            this.$foregroundServices$delegate = mutableState2;
            this.$userSwitcher$delegate = mutableState3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$viewModel, this.$security$delegate, this.$foregroundServices$delegate, this.$userSwitcher$delegate, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass2 anonymousClass2 = (AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass2.invokeSuspend(unit);
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
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$viewModel, this.$security$delegate, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new C01562(this.$viewModel, this.$foregroundServices$delegate, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.$userSwitcher$delegate, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FooterActionsKt$FooterActions$1(LifecycleOwner lifecycleOwner, FooterActionsViewModel footerActionsViewModel, Context context, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, Continuation continuation) {
        super(2, continuation);
        this.$qsVisibilityLifecycleOwner = lifecycleOwner;
        this.$viewModel = footerActionsViewModel;
        this.$context = context;
        this.$security$delegate = mutableState;
        this.$foregroundServices$delegate = mutableState2;
        this.$userSwitcher$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FooterActionsKt$FooterActions$1 footerActionsKt$FooterActions$1 = new FooterActionsKt$FooterActions$1(this.$qsVisibilityLifecycleOwner, this.$viewModel, this.$context, this.$security$delegate, this.$foregroundServices$delegate, this.$userSwitcher$delegate, continuation);
        footerActionsKt$FooterActions$1.L$0 = obj;
        return footerActionsKt$FooterActions$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FooterActionsKt$FooterActions$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BuildersKt.launch$default((CoroutineScope) this.L$0, null, null, new AnonymousClass1(this.$viewModel, this.$context, null), 3);
            LifecycleOwner lifecycleOwner = this.$qsVisibilityLifecycleOwner;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$viewModel, this.$security$delegate, this.$foregroundServices$delegate, this.$userSwitcher$delegate, null);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass2, this) == coroutineSingletons) {
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
