package com.android.systemui.statusbar.notification.footer.ui.viewbinder;

import android.view.View;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bindMessage$2;
import com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterViewModel;
import com.android.systemui.util.ui.AnimatedValue;
import java.util.function.Consumer;
import kotlin.NoWhenBranchMatchedException;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FooterViewBinder$bindClearAllButton$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ View.OnClickListener $clearAllNotifications;
    final /* synthetic */ FooterView $footer;
    final /* synthetic */ FooterViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bindClearAllButton$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ FooterView $footer;
        final /* synthetic */ FooterViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FooterView footerView, FooterViewModel footerViewModel, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = footerViewModel;
            this.$footer = footerView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$footer, this.$viewModel, continuation);
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
                Flow flow = this.$viewModel.clearAllButton.labelId;
                FooterViewBinder$bindMessage$2.AnonymousClass1.C01961 c01961 = new FooterViewBinder$bindMessage$2.AnonymousClass1.C01961(this.$footer, 1);
                this.label = 1;
                if (flow.collect(c01961, this) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bindClearAllButton$2$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ FooterView $footer;
        final /* synthetic */ FooterViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(FooterView footerView, FooterViewModel footerViewModel, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = footerViewModel;
            this.$footer = footerView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$footer, this.$viewModel, continuation);
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
                Flow flow = this.$viewModel.clearAllButton.accessibilityDescriptionId;
                FooterViewBinder$bindMessage$2.AnonymousClass1.C01961 c01961 = new FooterViewBinder$bindMessage$2.AnonymousClass1.C01961(this.$footer, 2);
                this.label = 1;
                if (flow.collect(c01961, this) == coroutineSingletons) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bindClearAllButton$2$3, reason: invalid class name */
    public final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ View.OnClickListener $clearAllNotifications;
        final /* synthetic */ FooterView $footer;
        final /* synthetic */ FooterViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(View.OnClickListener onClickListener, FooterView footerView, FooterViewModel footerViewModel, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = footerViewModel;
            this.$footer = footerView;
            this.$clearAllNotifications = onClickListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            FooterViewModel footerViewModel = this.$viewModel;
            return new AnonymousClass3(this.$clearAllNotifications, this.$footer, footerViewModel, continuation);
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
                Flow flow = this.$viewModel.clearAllButton.isVisible;
                final FooterView footerView = this.$footer;
                final View.OnClickListener onClickListener = this.$clearAllNotifications;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder.bindClearAllButton.2.3.1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bindClearAllButton$2$3$1$1, reason: invalid class name and collision with other inner class name */
                    public final class C01941 implements Consumer {
                        public final /* synthetic */ AnimatedValue $isVisible;

                        public C01941(AnimatedValue animatedValue) {
                            this.$isVisible = animatedValue;
                        }

                        /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            AnimatedValue animatedValue = this.$isVisible;
                            if (animatedValue instanceof AnimatedValue.Animating) {
                                ((AnimatedValue.Animating) animatedValue).onStopAnimating.invoke();
                            }
                        }
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Object obj3;
                        Object obj4;
                        Object obj5;
                        AnimatedValue animatedValue = (AnimatedValue) obj2;
                        boolean z = animatedValue instanceof AnimatedValue.Animating;
                        if (z) {
                            obj3 = ((AnimatedValue.Animating) animatedValue).value;
                        } else {
                            if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            obj3 = ((AnimatedValue.NotAnimating) animatedValue).value;
                        }
                        boolean booleanValue = ((Boolean) obj3).booleanValue();
                        FooterView footerView2 = FooterView.this;
                        if (booleanValue) {
                            View.OnClickListener onClickListener2 = onClickListener;
                            if (footerView2.mClearAllButtonClickListener != onClickListener2) {
                                footerView2.mClearAllButtonClickListener = onClickListener2;
                                footerView2.mClearAllButton.setOnClickListener(onClickListener2);
                            }
                        } else if (footerView2.mClearAllButtonClickListener != null) {
                            footerView2.mClearAllButtonClickListener = null;
                            footerView2.mClearAllButton.setOnClickListener(null);
                        }
                        if (z) {
                            if (animatedValue instanceof AnimatedValue.Animating) {
                                obj5 = ((AnimatedValue.Animating) animatedValue).value;
                            } else {
                                if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                obj5 = ((AnimatedValue.NotAnimating) animatedValue).value;
                            }
                            footerView2.setSecondaryVisible(((Boolean) obj5).booleanValue(), true, new C01941(animatedValue));
                        } else {
                            if (z) {
                                obj4 = ((AnimatedValue.Animating) animatedValue).value;
                            } else {
                                if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                obj4 = ((AnimatedValue.NotAnimating) animatedValue).value;
                            }
                            footerView2.setSecondaryVisible(((Boolean) obj4).booleanValue(), false, null);
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutineSingletons) {
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
    public FooterViewBinder$bindClearAllButton$2(View.OnClickListener onClickListener, FooterView footerView, FooterViewModel footerViewModel, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = footerViewModel;
        this.$footer = footerView;
        this.$clearAllNotifications = onClickListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FooterViewModel footerViewModel = this.$viewModel;
        FooterViewBinder$bindClearAllButton$2 footerViewBinder$bindClearAllButton$2 = new FooterViewBinder$bindClearAllButton$2(this.$clearAllNotifications, this.$footer, footerViewModel, continuation);
        footerViewBinder$bindClearAllButton$2.L$0 = obj;
        return footerViewBinder$bindClearAllButton$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FooterViewBinder$bindClearAllButton$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$footer, this.$viewModel, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$footer, this.$viewModel, null), 3);
        FooterViewModel footerViewModel = this.$viewModel;
        return BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$clearAllNotifications, this.$footer, footerViewModel, null), 3);
    }
}
