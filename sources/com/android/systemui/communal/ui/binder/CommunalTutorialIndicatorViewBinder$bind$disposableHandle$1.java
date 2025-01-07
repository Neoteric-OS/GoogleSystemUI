package com.android.systemui.communal.ui.binder;

import android.widget.TextView;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.communal.ui.viewmodel.CommunalTutorialIndicatorViewModel;
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
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ boolean $isPreviewMode;
    final /* synthetic */ TextView $view;
    final /* synthetic */ CommunalTutorialIndicatorViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.communal.ui.binder.CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $isPreviewMode;
        final /* synthetic */ TextView $view;
        final /* synthetic */ CommunalTutorialIndicatorViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.communal.ui.binder.CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C00601 extends SuspendLambda implements Function2 {
            final /* synthetic */ boolean $isPreviewMode;
            final /* synthetic */ TextView $view;
            final /* synthetic */ CommunalTutorialIndicatorViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.ui.binder.CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C00611 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ TextView $view;

                public /* synthetic */ C00611(TextView textView, int i) {
                    this.$r8$classId = i;
                    this.$view = textView;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            this.$view.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            break;
                        default:
                            this.$view.setAlpha(((Number) obj).floatValue());
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00601(CommunalTutorialIndicatorViewModel communalTutorialIndicatorViewModel, boolean z, TextView textView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = communalTutorialIndicatorViewModel;
                this.$isPreviewMode = z;
                this.$view = textView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00601(this.$viewModel, this.$isPreviewMode, this.$view, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((C00601) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ReadonlyStateFlow readonlyStateFlow;
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
                }
                ResultKt.throwOnFailure(obj);
                CommunalTutorialIndicatorViewModel communalTutorialIndicatorViewModel = this.$viewModel;
                if (this.$isPreviewMode) {
                    communalTutorialIndicatorViewModel.getClass();
                    readonlyStateFlow = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(Boolean.FALSE));
                } else {
                    readonlyStateFlow = communalTutorialIndicatorViewModel.communalTutorialInteractor.isTutorialAvailable;
                }
                C00611 c00611 = new C00611(this.$view, 0);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c00611, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.communal.ui.binder.CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ TextView $view;
            final /* synthetic */ CommunalTutorialIndicatorViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(CommunalTutorialIndicatorViewModel communalTutorialIndicatorViewModel, TextView textView, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = communalTutorialIndicatorViewModel;
                this.$view = textView;
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
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = this.$viewModel.alpha;
                    C00601.C00611 c00611 = new C00601.C00611(this.$view, 1);
                    this.label = 1;
                    if (flow.collect(c00611, this) == coroutineSingletons) {
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
        public AnonymousClass1(CommunalTutorialIndicatorViewModel communalTutorialIndicatorViewModel, boolean z, TextView textView, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = communalTutorialIndicatorViewModel;
            this.$isPreviewMode = z;
            this.$view = textView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$isPreviewMode, this.$view, continuation);
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
            BuildersKt.launch$default(coroutineScope, null, null, new C00601(this.$viewModel, this.$isPreviewMode, this.$view, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$view, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1(CommunalTutorialIndicatorViewModel communalTutorialIndicatorViewModel, boolean z, TextView textView, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = communalTutorialIndicatorViewModel;
        this.$isPreviewMode = z;
        this.$view = textView;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1 communalTutorialIndicatorViewBinder$bind$disposableHandle$1 = new CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1(this.$viewModel, this.$isPreviewMode, this.$view, (Continuation) obj3);
        communalTutorialIndicatorViewBinder$bind$disposableHandle$1.L$0 = (LifecycleOwner) obj;
        return communalTutorialIndicatorViewBinder$bind$disposableHandle$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$isPreviewMode, this.$view, null);
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
