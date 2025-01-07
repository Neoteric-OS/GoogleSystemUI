package com.android.systemui.statusbar.notification.footer.ui.viewbinder;

import android.view.View;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FooterViewBinder$bind$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ View.OnClickListener $clearAllNotifications;
    final /* synthetic */ FooterView $footer;
    final /* synthetic */ View.OnClickListener $launchNotificationHistory;
    final /* synthetic */ View.OnClickListener $launchNotificationSettings;
    final /* synthetic */ FooterViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bind$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ View.OnClickListener $clearAllNotifications;
        final /* synthetic */ FooterView $footer;
        final /* synthetic */ FooterViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(View.OnClickListener onClickListener, FooterView footerView, FooterViewModel footerViewModel, Continuation continuation) {
            super(2, continuation);
            this.$footer = footerView;
            this.$viewModel = footerViewModel;
            this.$clearAllNotifications = onClickListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$clearAllNotifications, this.$footer, this.$viewModel, continuation);
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
                FooterView footerView = this.$footer;
                FooterViewModel footerViewModel = this.$viewModel;
                View.OnClickListener onClickListener = this.$clearAllNotifications;
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(this, new FooterViewBinder$bindClearAllButton$2(onClickListener, footerView, footerViewModel, null)) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bind$2$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ FooterView $footer;
        final /* synthetic */ View.OnClickListener $launchNotificationHistory;
        final /* synthetic */ View.OnClickListener $launchNotificationSettings;
        final /* synthetic */ FooterViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, FooterView footerView, FooterViewModel footerViewModel, Continuation continuation) {
            super(2, continuation);
            this.$footer = footerView;
            this.$viewModel = footerViewModel;
            this.$launchNotificationSettings = onClickListener;
            this.$launchNotificationHistory = onClickListener2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$launchNotificationSettings, this.$launchNotificationHistory, this.$footer, this.$viewModel, continuation);
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
                FooterView footerView = this.$footer;
                FooterViewModel footerViewModel = this.$viewModel;
                View.OnClickListener onClickListener = this.$launchNotificationSettings;
                View.OnClickListener onClickListener2 = this.$launchNotificationHistory;
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(this, new FooterViewBinder$bindManageOrHistoryButton$2(onClickListener2, onClickListener, footerView, footerViewModel, null)) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bind$2$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ FooterView $footer;
        final /* synthetic */ FooterViewModel $viewModel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(FooterView footerView, FooterViewModel footerViewModel, Continuation continuation) {
            super(2, continuation);
            this.$footer = footerView;
            this.$viewModel = footerViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass3(this.$footer, this.$viewModel, continuation);
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
                FooterView footerView = this.$footer;
                FooterViewModel footerViewModel = this.$viewModel;
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(this, new FooterViewBinder$bindMessage$2(footerView, footerViewModel, null)) == coroutineSingletons) {
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
    public FooterViewBinder$bind$2(FooterView footerView, FooterViewModel footerViewModel, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnClickListener onClickListener3, Continuation continuation) {
        super(2, continuation);
        this.$footer = footerView;
        this.$viewModel = footerViewModel;
        this.$clearAllNotifications = onClickListener;
        this.$launchNotificationSettings = onClickListener2;
        this.$launchNotificationHistory = onClickListener3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FooterViewBinder$bind$2 footerViewBinder$bind$2 = new FooterViewBinder$bind$2(this.$footer, this.$viewModel, this.$clearAllNotifications, this.$launchNotificationSettings, this.$launchNotificationHistory, continuation);
        footerViewBinder$bind$2.L$0 = obj;
        return footerViewBinder$bind$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FooterViewBinder$bind$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$clearAllNotifications, this.$footer, this.$viewModel, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$launchNotificationSettings, this.$launchNotificationHistory, this.$footer, this.$viewModel, null), 3);
        return BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$footer, this.$viewModel, null), 3);
    }
}
