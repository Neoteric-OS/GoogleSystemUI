package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import android.view.View;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder;
import com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterViewModel;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter.AnonymousClass3;
import com.android.systemui.util.kotlin.DisposableHandleExtKt;
import com.android.systemui.util.ui.AnimatedValue;
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
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationListViewBinder$bindFooter$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ FooterView $footerView;
    final /* synthetic */ FooterViewModel $footerViewModel;
    final /* synthetic */ StateFlow $hasNonClearableSilentNotifications;
    final /* synthetic */ NotificationStackScrollLayout $parentView;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NotificationListViewBinder this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ FooterView $footerView;
        int label;
        final /* synthetic */ NotificationListViewBinder this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$2$1, reason: invalid class name */
        public final class AnonymousClass1 implements FlowCollector {
            public final /* synthetic */ FooterView $footerView;
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ AnonymousClass1(FooterView footerView, int i) {
                this.$r8$classId = i;
                this.$footerView = footerView;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                Object obj2;
                switch (this.$r8$classId) {
                    case 0:
                        AnimatedValue animatedValue = (AnimatedValue) obj;
                        boolean z = animatedValue instanceof AnimatedValue.Animating;
                        if (z) {
                            obj2 = ((AnimatedValue.Animating) animatedValue).value;
                        } else {
                            if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            obj2 = ((AnimatedValue.NotAnimating) animatedValue).value;
                        }
                        this.$footerView.setVisible(((Boolean) obj2).booleanValue(), z);
                        return Unit.INSTANCE;
                    default:
                        this.$footerView.mShouldBeHidden = ((Boolean) obj).booleanValue();
                        return Unit.INSTANCE;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(NotificationListViewBinder notificationListViewBinder, FooterView footerView, Continuation continuation) {
            super(2, continuation);
            this.this$0 = notificationListViewBinder;
            this.$footerView = footerView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.this$0, this.$footerView, continuation);
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
                Flow flow = (Flow) this.this$0.viewModel.shouldIncludeFooterView$delegate.getValue();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$footerView, 0);
                this.label = 1;
                if (flow.collect(anonymousClass1, this) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ FooterView $footerView;
        int label;
        final /* synthetic */ NotificationListViewBinder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(NotificationListViewBinder notificationListViewBinder, FooterView footerView, Continuation continuation) {
            super(2, continuation);
            this.this$0 = notificationListViewBinder;
            this.$footerView = footerView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass3(this.this$0, this.$footerView, continuation);
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
                Flow flow = (Flow) this.this$0.viewModel.shouldHideFooterView$delegate.getValue();
                AnonymousClass2.AnonymousClass1 anonymousClass1 = new AnonymousClass2.AnonymousClass1(this.$footerView, 1);
                this.label = 1;
                if (flow.collect(anonymousClass1, this) == coroutineSingletons) {
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
    public NotificationListViewBinder$bindFooter$2(FooterView footerView, FooterViewModel footerViewModel, NotificationListViewBinder notificationListViewBinder, NotificationStackScrollLayout notificationStackScrollLayout, StateFlow stateFlow, Continuation continuation) {
        super(2, continuation);
        this.$footerView = footerView;
        this.$footerViewModel = footerViewModel;
        this.this$0 = notificationListViewBinder;
        this.$parentView = notificationStackScrollLayout;
        this.$hasNonClearableSilentNotifications = stateFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationListViewBinder$bindFooter$2 notificationListViewBinder$bindFooter$2 = new NotificationListViewBinder$bindFooter$2(this.$footerView, this.$footerViewModel, this.this$0, this.$parentView, this.$hasNonClearableSilentNotifications, continuation);
        notificationListViewBinder$bindFooter$2.L$0 = obj;
        return notificationListViewBinder$bindFooter$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationListViewBinder$bindFooter$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$disposableHandle$1] */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$disposableHandle$2] */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$disposableHandle$2] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        FooterView footerView = this.$footerView;
        FooterViewModel footerViewModel = this.$footerViewModel;
        final NotificationListViewBinder notificationListViewBinder = this.this$0;
        final NotificationStackScrollLayout notificationStackScrollLayout = this.$parentView;
        final StateFlow stateFlow = this.$hasNonClearableSilentNotifications;
        ?? r4 = new View.OnClickListener() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$disposableHandle$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NotificationListViewBinder notificationListViewBinder2 = NotificationListViewBinder.this;
                NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayout;
                boolean z = !((Boolean) stateFlow.getValue()).booleanValue();
                notificationListViewBinder2.metricsLogger.action(148);
                notificationStackScrollLayout2.clearNotifications(0, true, z);
            }
        };
        final int i2 = 0;
        final int i3 = 1;
        RepeatWhenAttachedKt$repeatWhenAttached$1 bindWhileAttached = FooterViewBinder.bindWhileAttached(footerView, footerViewModel, r4, new View.OnClickListener() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$disposableHandle$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = (StatusBarNotificationActivityStarter) notificationListViewBinder.notificationActivityStarter.get();
                        ActivityStarter activityStarter = statusBarNotificationActivityStarter.mActivityStarter;
                        activityStarter.dismissKeyguardThenExecute(statusBarNotificationActivityStarter.new AnonymousClass3(false, view, activityStarter.shouldAnimateLaunch(true)), null, false);
                        break;
                    default:
                        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter2 = (StatusBarNotificationActivityStarter) notificationListViewBinder.notificationActivityStarter.get();
                        ActivityStarter activityStarter2 = statusBarNotificationActivityStarter2.mActivityStarter;
                        activityStarter2.dismissKeyguardThenExecute(statusBarNotificationActivityStarter2.new AnonymousClass3(true, view, activityStarter2.shouldAnimateLaunch(true)), null, false);
                        break;
                }
            }
        }, new View.OnClickListener() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder$bindFooter$2$disposableHandle$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i3) {
                    case 0:
                        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = (StatusBarNotificationActivityStarter) notificationListViewBinder.notificationActivityStarter.get();
                        ActivityStarter activityStarter = statusBarNotificationActivityStarter.mActivityStarter;
                        activityStarter.dismissKeyguardThenExecute(statusBarNotificationActivityStarter.new AnonymousClass3(false, view, activityStarter.shouldAnimateLaunch(true)), null, false);
                        break;
                    default:
                        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter2 = (StatusBarNotificationActivityStarter) notificationListViewBinder.notificationActivityStarter.get();
                        ActivityStarter activityStarter2 = statusBarNotificationActivityStarter2.mActivityStarter;
                        activityStarter2.dismissKeyguardThenExecute(statusBarNotificationActivityStarter2.new AnonymousClass3(true, view, activityStarter2.shouldAnimateLaunch(true)), null, false);
                        break;
                }
            }
        });
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.this$0, this.$footerView, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.this$0, this.$footerView, null), 3);
        this.label = 1;
        DisposableHandleExtKt.awaitCancellationThenDispose(bindWhileAttached, this);
        return coroutineSingletons;
    }
}
