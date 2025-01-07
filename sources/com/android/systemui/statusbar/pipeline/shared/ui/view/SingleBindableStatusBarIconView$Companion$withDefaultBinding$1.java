package com.android.systemui.statusbar.pipeline.shared.ui.view;

import android.content.res.ColorStateList;
import android.widget.ImageView;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.statusbar.StatusBarIconView;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SingleBindableStatusBarIconView$Companion$withDefaultBinding$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Function3 $block;
    final /* synthetic */ MutableStateFlow $decorTint;
    final /* synthetic */ MutableStateFlow $iconTint;
    final /* synthetic */ Ref$BooleanRef $isCollecting;
    final /* synthetic */ SingleBindableStatusBarIconView $view;
    final /* synthetic */ MutableStateFlow $visibilityState;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView$Companion$withDefaultBinding$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LifecycleOwner $$this$repeatWhenAttached;
        final /* synthetic */ MutableStateFlow $decorTint;
        final /* synthetic */ MutableStateFlow $iconTint;
        final /* synthetic */ Ref$BooleanRef $isCollecting;
        final /* synthetic */ SingleBindableStatusBarIconView $view;
        final /* synthetic */ MutableStateFlow $visibilityState;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView$Companion$withDefaultBinding$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C02391 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableStateFlow $decorTint;
            final /* synthetic */ MutableStateFlow $iconTint;
            final /* synthetic */ Ref$BooleanRef $isCollecting;
            final /* synthetic */ SingleBindableStatusBarIconView $view;
            final /* synthetic */ MutableStateFlow $visibilityState;
            private /* synthetic */ Object L$0;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView$Companion$withDefaultBinding$1$1$1$1, reason: invalid class name and collision with other inner class name */
            final class C02401 extends SuspendLambda implements Function2 {
                final /* synthetic */ SingleBindableStatusBarIconView $view;
                final /* synthetic */ MutableStateFlow $visibilityState;
                int label;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView$Companion$withDefaultBinding$1$1$1$1$1, reason: invalid class name and collision with other inner class name */
                public final class C02411 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ SingleBindableStatusBarIconView $view;

                    public /* synthetic */ C02411(SingleBindableStatusBarIconView singleBindableStatusBarIconView, int i) {
                        this.$r8$classId = i;
                        this.$view = singleBindableStatusBarIconView;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                int intValue = ((Number) obj).intValue();
                                SingleBindableStatusBarIconView singleBindableStatusBarIconView = this.$view;
                                ImageView imageView = singleBindableStatusBarIconView.iconView;
                                if (imageView == null) {
                                    imageView = null;
                                }
                                StatusBarIconView statusBarIconView = singleBindableStatusBarIconView.dotView;
                                StatusBarIconView statusBarIconView2 = statusBarIconView != null ? statusBarIconView : null;
                                if (intValue == 0) {
                                    imageView.setVisibility(0);
                                    statusBarIconView2.setVisibility(8);
                                } else if (intValue == 1) {
                                    imageView.setVisibility(4);
                                    statusBarIconView2.setVisibility(0);
                                } else if (intValue == 2) {
                                    imageView.setVisibility(4);
                                    statusBarIconView2.setVisibility(4);
                                }
                                break;
                            case 1:
                                int intValue2 = ((Number) obj).intValue();
                                ColorStateList valueOf = ColorStateList.valueOf(intValue2);
                                SingleBindableStatusBarIconView singleBindableStatusBarIconView2 = this.$view;
                                ImageView imageView2 = singleBindableStatusBarIconView2.iconView;
                                if (imageView2 == null) {
                                    imageView2 = null;
                                }
                                imageView2.setImageTintList(valueOf);
                                StatusBarIconView statusBarIconView3 = singleBindableStatusBarIconView2.dotView;
                                (statusBarIconView3 != null ? statusBarIconView3 : null).setDecorColor(intValue2);
                                break;
                            default:
                                int intValue3 = ((Number) obj).intValue();
                                StatusBarIconView statusBarIconView4 = this.$view.dotView;
                                if (statusBarIconView4 == null) {
                                    statusBarIconView4 = null;
                                }
                                statusBarIconView4.setDecorColor(intValue3);
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C02401(MutableStateFlow mutableStateFlow, SingleBindableStatusBarIconView singleBindableStatusBarIconView, Continuation continuation) {
                    super(2, continuation);
                    this.$visibilityState = mutableStateFlow;
                    this.$view = singleBindableStatusBarIconView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C02401(this.$visibilityState, this.$view, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((C02401) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    MutableStateFlow mutableStateFlow = this.$visibilityState;
                    C02411 c02411 = new C02411(this.$view, 0);
                    this.label = 1;
                    ((StateFlowImpl) mutableStateFlow).collect(c02411, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView$Companion$withDefaultBinding$1$1$1$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ MutableStateFlow $iconTint;
                final /* synthetic */ SingleBindableStatusBarIconView $view;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(MutableStateFlow mutableStateFlow, SingleBindableStatusBarIconView singleBindableStatusBarIconView, Continuation continuation) {
                    super(2, continuation);
                    this.$iconTint = mutableStateFlow;
                    this.$view = singleBindableStatusBarIconView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$iconTint, this.$view, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    MutableStateFlow mutableStateFlow = this.$iconTint;
                    C02401.C02411 c02411 = new C02401.C02411(this.$view, 1);
                    this.label = 1;
                    ((StateFlowImpl) mutableStateFlow).collect(c02411, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView$Companion$withDefaultBinding$1$1$1$3, reason: invalid class name */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ MutableStateFlow $decorTint;
                final /* synthetic */ SingleBindableStatusBarIconView $view;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(MutableStateFlow mutableStateFlow, SingleBindableStatusBarIconView singleBindableStatusBarIconView, Continuation continuation) {
                    super(2, continuation);
                    this.$decorTint = mutableStateFlow;
                    this.$view = singleBindableStatusBarIconView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$decorTint, this.$view, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    MutableStateFlow mutableStateFlow = this.$decorTint;
                    C02401.C02411 c02411 = new C02401.C02411(this.$view, 2);
                    this.label = 1;
                    ((StateFlowImpl) mutableStateFlow).collect(c02411, this);
                    return coroutineSingletons;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02391(Ref$BooleanRef ref$BooleanRef, MutableStateFlow mutableStateFlow, SingleBindableStatusBarIconView singleBindableStatusBarIconView, MutableStateFlow mutableStateFlow2, MutableStateFlow mutableStateFlow3, Continuation continuation) {
                super(2, continuation);
                this.$isCollecting = ref$BooleanRef;
                this.$visibilityState = mutableStateFlow;
                this.$view = singleBindableStatusBarIconView;
                this.$iconTint = mutableStateFlow2;
                this.$decorTint = mutableStateFlow3;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C02391 c02391 = new C02391(this.$isCollecting, this.$visibilityState, this.$view, this.$iconTint, this.$decorTint, continuation);
                c02391.L$0 = obj;
                return c02391;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((C02391) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                try {
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        throw new KotlinNothingValueException();
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    BuildersKt.launch$default(coroutineScope, null, null, new C02401(this.$visibilityState, this.$view, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$iconTint, this.$view, null), 3);
                    BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$decorTint, this.$view, null), 3);
                    this.label = 1;
                    DelayKt.awaitCancellation(this);
                    return coroutineSingletons;
                } catch (Throwable th) {
                    this.$isCollecting.element = false;
                    throw th;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LifecycleOwner lifecycleOwner, Ref$BooleanRef ref$BooleanRef, MutableStateFlow mutableStateFlow, SingleBindableStatusBarIconView singleBindableStatusBarIconView, MutableStateFlow mutableStateFlow2, MutableStateFlow mutableStateFlow3, Continuation continuation) {
            super(2, continuation);
            this.$$this$repeatWhenAttached = lifecycleOwner;
            this.$isCollecting = ref$BooleanRef;
            this.$visibilityState = mutableStateFlow;
            this.$view = singleBindableStatusBarIconView;
            this.$iconTint = mutableStateFlow2;
            this.$decorTint = mutableStateFlow3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$$this$repeatWhenAttached, this.$isCollecting, this.$visibilityState, this.$view, this.$iconTint, this.$decorTint, continuation);
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
                LifecycleOwner lifecycleOwner = this.$$this$repeatWhenAttached;
                Lifecycle.State state = Lifecycle.State.STARTED;
                C02391 c02391 = new C02391(this.$isCollecting, this.$visibilityState, this.$view, this.$iconTint, this.$decorTint, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c02391, this) == coroutineSingletons) {
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
    public SingleBindableStatusBarIconView$Companion$withDefaultBinding$1(Function3 function3, SingleBindableStatusBarIconView singleBindableStatusBarIconView, Ref$BooleanRef ref$BooleanRef, MutableStateFlow mutableStateFlow, MutableStateFlow mutableStateFlow2, MutableStateFlow mutableStateFlow3, Continuation continuation) {
        super(3, continuation);
        this.$block = function3;
        this.$view = singleBindableStatusBarIconView;
        this.$isCollecting = ref$BooleanRef;
        this.$visibilityState = mutableStateFlow;
        this.$iconTint = mutableStateFlow2;
        this.$decorTint = mutableStateFlow3;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SingleBindableStatusBarIconView$Companion$withDefaultBinding$1 singleBindableStatusBarIconView$Companion$withDefaultBinding$1 = new SingleBindableStatusBarIconView$Companion$withDefaultBinding$1(this.$block, this.$view, this.$isCollecting, this.$visibilityState, this.$iconTint, this.$decorTint, (Continuation) obj3);
        singleBindableStatusBarIconView$Companion$withDefaultBinding$1.L$0 = (LifecycleOwner) obj;
        return singleBindableStatusBarIconView$Companion$withDefaultBinding$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LifecycleOwner lifecycleOwner;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner2 = (LifecycleOwner) this.L$0;
            Function3 function3 = this.$block;
            SingleBindableStatusBarIconView singleBindableStatusBarIconView = this.$view;
            this.L$0 = lifecycleOwner2;
            this.label = 1;
            if (function3.invoke(lifecycleOwner2, singleBindableStatusBarIconView, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            lifecycleOwner = lifecycleOwner2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            LifecycleOwner lifecycleOwner3 = (LifecycleOwner) this.L$0;
            ResultKt.throwOnFailure(obj);
            lifecycleOwner = lifecycleOwner3;
        }
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), null, null, new AnonymousClass1(lifecycleOwner, this.$isCollecting, this.$visibilityState, this.$view, this.$iconTint, this.$decorTint, null), 3);
        return Unit.INSTANCE;
    }
}
