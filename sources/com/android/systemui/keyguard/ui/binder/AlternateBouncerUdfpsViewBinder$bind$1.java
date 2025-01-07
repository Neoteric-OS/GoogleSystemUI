package com.android.systemui.keyguard.ui.binder;

import android.content.res.ColorStateList;
import android.widget.ImageView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerUdfpsIconViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerUdfpsIconViewModel$special$$inlined$map$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AlternateBouncerUdfpsViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ DeviceEntryIconView $view;
    final /* synthetic */ AlternateBouncerUdfpsIconViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerUdfpsViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ DeviceEntryIconView $view;
        final /* synthetic */ AlternateBouncerUdfpsIconViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerUdfpsViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C00891 extends SuspendLambda implements Function2 {
            final /* synthetic */ DeviceEntryIconView $view;
            final /* synthetic */ AlternateBouncerUdfpsIconViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00891(DeviceEntryIconView deviceEntryIconView, AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = alternateBouncerUdfpsIconViewModel;
                this.$view = deviceEntryIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00891(this.$view, this.$viewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00891) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = this.$viewModel.accessibilityDelegateHint;
                    DeviceEntryIconView deviceEntryIconView = this.$view;
                    this.label = 1;
                    deviceEntryIconView.accessibilityHintType = (DeviceEntryIconView.AccessibilityHintType) flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.$value$inlined;
                    if (unit == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerUdfpsViewBinder$bind$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ DeviceEntryIconView $view;
            final /* synthetic */ AlternateBouncerUdfpsIconViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerUdfpsViewBinder$bind$1$1$2$1, reason: invalid class name and collision with other inner class name */
            public final class C00901 implements FlowCollector {
                public final /* synthetic */ int $r8$classId = 1;
                public final /* synthetic */ Object $view;

                public C00901(ImageView imageView) {
                    this.$view = imageView;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ((DeviceEntryIconView) this.$view).setAlpha(((Number) obj).floatValue());
                            break;
                        default:
                            ((ImageView) this.$view).setImageTintList(ColorStateList.valueOf(((Number) obj).intValue()));
                            break;
                    }
                    return Unit.INSTANCE;
                }

                public C00901(DeviceEntryIconView deviceEntryIconView) {
                    this.$view = deviceEntryIconView;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(DeviceEntryIconView deviceEntryIconView, AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = alternateBouncerUdfpsIconViewModel;
                this.$view = deviceEntryIconView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$view, this.$viewModel, continuation);
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
                    AlternateBouncerUdfpsIconViewModel$special$$inlined$map$1 alternateBouncerUdfpsIconViewModel$special$$inlined$map$1 = this.$viewModel.alpha;
                    C00901 c00901 = new C00901(this.$view);
                    this.label = 1;
                    if (alternateBouncerUdfpsIconViewModel$special$$inlined$map$1.collect(c00901, this) == coroutineSingletons) {
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
        public AnonymousClass1(DeviceEntryIconView deviceEntryIconView, AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel, Continuation continuation) {
            super(2, continuation);
            this.$view = deviceEntryIconView;
            this.$viewModel = alternateBouncerUdfpsIconViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$viewModel, continuation);
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
            this.$view.setAlpha(0.0f);
            CoroutineTracingKt.launch$default(coroutineScope, null, new C00891(this.$view, this.$viewModel, null), 6);
            CoroutineTracingKt.launch$default(coroutineScope, null, new AnonymousClass2(this.$view, this.$viewModel, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlternateBouncerUdfpsViewBinder$bind$1(DeviceEntryIconView deviceEntryIconView, AlternateBouncerUdfpsIconViewModel alternateBouncerUdfpsIconViewModel, Continuation continuation) {
        super(3, continuation);
        this.$view = deviceEntryIconView;
        this.$viewModel = alternateBouncerUdfpsIconViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AlternateBouncerUdfpsViewBinder$bind$1 alternateBouncerUdfpsViewBinder$bind$1 = new AlternateBouncerUdfpsViewBinder$bind$1(this.$view, this.$viewModel, (Continuation) obj3);
        alternateBouncerUdfpsViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return alternateBouncerUdfpsViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$viewModel, null);
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
