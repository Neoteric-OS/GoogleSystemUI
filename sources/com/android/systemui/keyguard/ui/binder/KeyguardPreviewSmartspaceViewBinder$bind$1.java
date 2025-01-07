package com.android.systemui.keyguard.ui.binder;

import android.content.Context;
import android.view.View;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.shared.model.ClockSizeSetting;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewSmartspaceViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewSmartspaceViewModel$special$$inlined$map$1;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardPreviewSmartspaceViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Context $previewContext;
    final /* synthetic */ View $smartspace;
    final /* synthetic */ boolean $splitShadePreview;
    final /* synthetic */ KeyguardPreviewSmartspaceViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardPreviewSmartspaceViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Context $previewContext;
        final /* synthetic */ View $smartspace;
        final /* synthetic */ boolean $splitShadePreview;
        final /* synthetic */ KeyguardPreviewSmartspaceViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardPreviewSmartspaceViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C01221 extends SuspendLambda implements Function2 {
            final /* synthetic */ Context $previewContext;
            final /* synthetic */ View $smartspace;
            final /* synthetic */ boolean $splitShadePreview;
            final /* synthetic */ KeyguardPreviewSmartspaceViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01221(KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel, boolean z, Context context, View view, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardPreviewSmartspaceViewModel;
                this.$splitShadePreview = z;
                this.$previewContext = context;
                this.$smartspace = view;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01221(this.$viewModel, this.$splitShadePreview, this.$previewContext, this.$smartspace, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((C01221) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                final KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel = this.$viewModel;
                ReadonlyStateFlow readonlyStateFlow = keyguardPreviewSmartspaceViewModel.selectedClockSize;
                final boolean z = this.$splitShadePreview;
                final Context context = this.$previewContext;
                final View view = this.$smartspace;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardPreviewSmartspaceViewBinder.bind.1.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        int smallClockTopPadding;
                        int ordinal = ((ClockSizeSetting) obj2).ordinal();
                        boolean z2 = z;
                        KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel2 = keyguardPreviewSmartspaceViewModel;
                        if (ordinal == 0) {
                            Context context2 = context;
                            keyguardPreviewSmartspaceViewModel2.getClass();
                            smallClockTopPadding = KeyguardPreviewSmartspaceViewModel.getSmallClockTopPadding(context2, z2);
                        } else {
                            if (ordinal != 1) {
                                throw new NoWhenBranchMatchedException();
                            }
                            Context context3 = context;
                            keyguardPreviewSmartspaceViewModel2.getClass();
                            smallClockTopPadding = context3.getResources().getDimensionPixelSize(R.dimen.small_clock_height) + KeyguardPreviewSmartspaceViewModel.getSmallClockTopPadding(context3, z2);
                        }
                        View view2 = view;
                        view2.setPaddingRelative(view2.getPaddingStart(), smallClockTopPadding, view2.getPaddingEnd(), view2.getPaddingBottom());
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardPreviewSmartspaceViewBinder$bind$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $smartspace;
            final /* synthetic */ KeyguardPreviewSmartspaceViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel, View view, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = keyguardPreviewSmartspaceViewModel;
                this.$smartspace = view;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.$smartspace, continuation);
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
                    KeyguardPreviewSmartspaceViewModel$special$$inlined$map$1 keyguardPreviewSmartspaceViewModel$special$$inlined$map$1 = this.$viewModel.shouldHideSmartspace;
                    final View view = this.$smartspace;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardPreviewSmartspaceViewBinder.bind.1.1.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            view.setVisibility(((Boolean) obj2).booleanValue() ? 4 : 0);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (keyguardPreviewSmartspaceViewModel$special$$inlined$map$1.collect(flowCollector, this) == coroutineSingletons) {
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
        public AnonymousClass1(KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel, boolean z, Context context, View view, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = keyguardPreviewSmartspaceViewModel;
            this.$splitShadePreview = z;
            this.$previewContext = context;
            this.$smartspace = view;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$splitShadePreview, this.$previewContext, this.$smartspace, continuation);
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
            CoroutineTracingKt.launch$default(coroutineScope, null, new C01221(this.$viewModel, this.$splitShadePreview, this.$previewContext, this.$smartspace, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass2(this.$viewModel, this.$smartspace, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardPreviewSmartspaceViewBinder$bind$1(KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel, boolean z, Context context, View view, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = keyguardPreviewSmartspaceViewModel;
        this.$splitShadePreview = z;
        this.$previewContext = context;
        this.$smartspace = view;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardPreviewSmartspaceViewBinder$bind$1 keyguardPreviewSmartspaceViewBinder$bind$1 = new KeyguardPreviewSmartspaceViewBinder$bind$1(this.$viewModel, this.$splitShadePreview, this.$previewContext, this.$smartspace, (Continuation) obj3);
        keyguardPreviewSmartspaceViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return keyguardPreviewSmartspaceViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$splitShadePreview, this.$previewContext, this.$smartspace, null);
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
