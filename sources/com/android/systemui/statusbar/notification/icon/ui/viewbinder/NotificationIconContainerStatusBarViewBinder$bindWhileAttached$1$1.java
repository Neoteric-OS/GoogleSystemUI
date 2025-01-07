package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel;
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

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ NotificationIconContainer $view;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NotificationIconContainerStatusBarViewBinder this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationIconContainer $view;
        int label;
        final /* synthetic */ NotificationIconContainerStatusBarViewBinder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NotificationIconContainer notificationIconContainer, NotificationIconContainerStatusBarViewBinder notificationIconContainerStatusBarViewBinder, Continuation continuation) {
            super(2, continuation);
            this.$view = notificationIconContainer;
            this.this$0 = notificationIconContainerStatusBarViewBinder;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$view, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                NotificationIconContainer notificationIconContainer = this.$view;
                NotificationIconContainerStatusBarViewBinder notificationIconContainerStatusBarViewBinder = this.this$0;
                NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel = notificationIconContainerStatusBarViewBinder.viewModel;
                ConfigurationState configurationState = notificationIconContainerStatusBarViewBinder.configuration;
                SystemBarUtilsState systemBarUtilsState = notificationIconContainerStatusBarViewBinder.systemBarUtilsState;
                StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker = notificationIconContainerStatusBarViewBinder.failureTracker;
                StatusBarNotificationIconViewStore statusBarNotificationIconViewStore = notificationIconContainerStatusBarViewBinder.viewStore;
                this.label = 1;
                Object coroutineScope = CoroutineScopeKt.coroutineScope(this, new NotificationIconContainerViewBinder$bind$2(notificationIconContainer, notificationIconContainerStatusBarViewModel, configurationState, systemBarUtilsState, statusBarNotificationIconViewStore, statusBarIconViewBindingFailureTracker, null));
                if (coroutineScope != coroutineSingletons) {
                    coroutineScope = unit;
                }
                if (coroutineScope == coroutineSingletons) {
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
    public NotificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1(NotificationIconContainer notificationIconContainer, NotificationIconContainerStatusBarViewBinder notificationIconContainerStatusBarViewBinder, Continuation continuation) {
        super(3, continuation);
        this.$view = notificationIconContainer;
        this.this$0 = notificationIconContainerStatusBarViewBinder;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        NotificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1 notificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1 = new NotificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1(this.$view, this.this$0, (Continuation) obj3);
        notificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1.L$0 = (LifecycleOwner) obj;
        Unit unit = Unit.INSTANCE;
        notificationIconContainerStatusBarViewBinder$bindWhileAttached$1$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) this.L$0), null, null, new AnonymousClass1(this.$view, this.this$0, null), 3);
        return Unit.INSTANCE;
    }
}
