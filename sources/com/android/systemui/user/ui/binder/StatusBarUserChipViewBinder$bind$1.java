package com.android.systemui.user.ui.binder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.statusbar.phone.userswitcher.StatusBarUserSwitcherContainer;
import com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel;
import kotlin.NoWhenBranchMatchedException;
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
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class StatusBarUserChipViewBinder$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ StatusBarUserSwitcherContainer $view;
    final /* synthetic */ StatusBarUserChipViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.ui.binder.StatusBarUserChipViewBinder$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ StatusBarUserSwitcherContainer $view;
        final /* synthetic */ StatusBarUserChipViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.user.ui.binder.StatusBarUserChipViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C02521 extends SuspendLambda implements Function2 {
            final /* synthetic */ StatusBarUserSwitcherContainer $view;
            final /* synthetic */ StatusBarUserChipViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.user.ui.binder.StatusBarUserChipViewBinder$bind$1$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C02531 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ StatusBarUserSwitcherContainer $view;

                public /* synthetic */ C02531(StatusBarUserSwitcherContainer statusBarUserSwitcherContainer, int i) {
                    this.$r8$classId = i;
                    this.$view = statusBarUserSwitcherContainer;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    String str;
                    switch (this.$r8$classId) {
                        case 0:
                            this.$view.setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                            return Unit.INSTANCE;
                        case 1:
                            Text text = (Text) obj;
                            TextView textView = this.$view.text;
                            if (textView == null) {
                                textView = null;
                            }
                            if (text instanceof Text.Resource) {
                                str = textView.getContext().getString(((Text.Resource) text).res);
                            } else {
                                if (!(text instanceof Text.Loaded)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                str = ((Text.Loaded) text).text;
                            }
                            textView.setText(str);
                            return Unit.INSTANCE;
                        default:
                            Drawable drawable = (Drawable) obj;
                            ImageView imageView = this.$view.avatar;
                            if (imageView == null) {
                                imageView = null;
                            }
                            imageView.setImageDrawable(drawable);
                            return Unit.INSTANCE;
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02521(StatusBarUserSwitcherContainer statusBarUserSwitcherContainer, StatusBarUserChipViewModel statusBarUserChipViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = statusBarUserChipViewModel;
                this.$view = statusBarUserSwitcherContainer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C02521(this.$view, this.$viewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C02521) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = this.$viewModel.isChipVisible;
                    C02531 c02531 = new C02531(this.$view, 0);
                    this.label = 1;
                    if (flow.collect(c02531, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.user.ui.binder.StatusBarUserChipViewBinder$bind$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ StatusBarUserSwitcherContainer $view;
            final /* synthetic */ StatusBarUserChipViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(StatusBarUserSwitcherContainer statusBarUserSwitcherContainer, StatusBarUserChipViewModel statusBarUserChipViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = statusBarUserChipViewModel;
                this.$view = statusBarUserSwitcherContainer;
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
                    ChannelFlowTransformLatest channelFlowTransformLatest = this.$viewModel.userName;
                    C02521.C02531 c02531 = new C02521.C02531(this.$view, 1);
                    this.label = 1;
                    if (channelFlowTransformLatest.collect(c02531, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.user.ui.binder.StatusBarUserChipViewBinder$bind$1$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ StatusBarUserSwitcherContainer $view;
            final /* synthetic */ StatusBarUserChipViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(StatusBarUserSwitcherContainer statusBarUserSwitcherContainer, StatusBarUserChipViewModel statusBarUserChipViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = statusBarUserChipViewModel;
                this.$view = statusBarUserSwitcherContainer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$view, this.$viewModel, continuation);
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
                    ChannelFlowTransformLatest channelFlowTransformLatest = this.$viewModel.userAvatar;
                    C02521.C02531 c02531 = new C02521.C02531(this.$view, 2);
                    this.label = 1;
                    if (channelFlowTransformLatest.collect(c02531, this) == coroutineSingletons) {
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
        public AnonymousClass1(StatusBarUserSwitcherContainer statusBarUserSwitcherContainer, StatusBarUserChipViewModel statusBarUserChipViewModel, Continuation continuation) {
            super(2, continuation);
            this.$view = statusBarUserSwitcherContainer;
            this.$viewModel = statusBarUserChipViewModel;
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
            BuildersKt.launch$default(coroutineScope, null, null, new C02521(this.$view, this.$viewModel, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$view, this.$viewModel, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$view, this.$viewModel, null), 3);
            final StatusBarUserSwitcherContainer statusBarUserSwitcherContainer = this.$view;
            final StatusBarUserChipViewModel statusBarUserChipViewModel = this.$viewModel;
            statusBarUserSwitcherContainer.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.user.ui.binder.StatusBarUserChipViewBinder$bindButton$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    statusBarUserChipViewModel.onClick.invoke(new Expandable$Companion$fromView$1(statusBarUserSwitcherContainer));
                }
            });
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatusBarUserChipViewBinder$bind$1(StatusBarUserSwitcherContainer statusBarUserSwitcherContainer, StatusBarUserChipViewModel statusBarUserChipViewModel, Continuation continuation) {
        super(3, continuation);
        this.$view = statusBarUserSwitcherContainer;
        this.$viewModel = statusBarUserChipViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        StatusBarUserChipViewBinder$bind$1 statusBarUserChipViewBinder$bind$1 = new StatusBarUserChipViewBinder$bind$1(this.$view, this.$viewModel, (Continuation) obj3);
        statusBarUserChipViewBinder$bind$1.L$0 = (LifecycleOwner) obj;
        return statusBarUserChipViewBinder$bind$1.invokeSuspend(Unit.INSTANCE);
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
