package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
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
final class NotificationIconContainerViewBinder$bindWhileAttached$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ ConfigurationState $configuration;
    final /* synthetic */ StatusBarIconViewBindingFailureTracker $failureTracker;
    final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
    final /* synthetic */ NotificationIconContainer $view;
    final /* synthetic */ NotificationIconContainerAlwaysOnDisplayViewModel $viewModel;
    final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bindWhileAttached$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ConfigurationState $configuration;
        final /* synthetic */ StatusBarIconViewBindingFailureTracker $failureTracker;
        final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
        final /* synthetic */ NotificationIconContainer $view;
        final /* synthetic */ NotificationIconContainerAlwaysOnDisplayViewModel $viewModel;
        final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ConfigurationState configurationState, NotificationIconContainerViewBinder.IconViewStore iconViewStore, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, NotificationIconContainer notificationIconContainer, SystemBarUtilsState systemBarUtilsState, Continuation continuation) {
            super(2, continuation);
            this.$view = notificationIconContainer;
            this.$viewModel = notificationIconContainerAlwaysOnDisplayViewModel;
            this.$configuration = configurationState;
            this.$systemBarUtilsState = systemBarUtilsState;
            this.$failureTracker = statusBarIconViewBindingFailureTracker;
            this.$viewStore = iconViewStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            NotificationIconContainer notificationIconContainer = this.$view;
            NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel = this.$viewModel;
            ConfigurationState configurationState = this.$configuration;
            SystemBarUtilsState systemBarUtilsState = this.$systemBarUtilsState;
            return new AnonymousClass1(configurationState, this.$viewStore, this.$failureTracker, notificationIconContainerAlwaysOnDisplayViewModel, notificationIconContainer, systemBarUtilsState, continuation);
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
                NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel = this.$viewModel;
                ConfigurationState configurationState = this.$configuration;
                SystemBarUtilsState systemBarUtilsState = this.$systemBarUtilsState;
                StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker = this.$failureTracker;
                NotificationIconContainerViewBinder.IconViewStore iconViewStore = this.$viewStore;
                this.label = 1;
                Object coroutineScope = CoroutineScopeKt.coroutineScope(this, new NotificationIconContainerViewBinder$bind$4(configurationState, iconViewStore, statusBarIconViewBindingFailureTracker, notificationIconContainerAlwaysOnDisplayViewModel, notificationIconContainer, systemBarUtilsState, null));
                if (coroutineScope != CoroutineSingletons.COROUTINE_SUSPENDED) {
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
    public NotificationIconContainerViewBinder$bindWhileAttached$1(ConfigurationState configurationState, NotificationIconContainerViewBinder.IconViewStore iconViewStore, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, NotificationIconContainer notificationIconContainer, SystemBarUtilsState systemBarUtilsState, Continuation continuation) {
        super(3, continuation);
        this.$view = notificationIconContainer;
        this.$viewModel = notificationIconContainerAlwaysOnDisplayViewModel;
        this.$configuration = configurationState;
        this.$systemBarUtilsState = systemBarUtilsState;
        this.$failureTracker = statusBarIconViewBindingFailureTracker;
        this.$viewStore = iconViewStore;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        NotificationIconContainer notificationIconContainer = this.$view;
        NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel = this.$viewModel;
        ConfigurationState configurationState = this.$configuration;
        SystemBarUtilsState systemBarUtilsState = this.$systemBarUtilsState;
        StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker = this.$failureTracker;
        NotificationIconContainerViewBinder$bindWhileAttached$1 notificationIconContainerViewBinder$bindWhileAttached$1 = new NotificationIconContainerViewBinder$bindWhileAttached$1(configurationState, this.$viewStore, statusBarIconViewBindingFailureTracker, notificationIconContainerAlwaysOnDisplayViewModel, notificationIconContainer, systemBarUtilsState, (Continuation) obj3);
        notificationIconContainerViewBinder$bindWhileAttached$1.L$0 = (LifecycleOwner) obj;
        Unit unit = Unit.INSTANCE;
        notificationIconContainerViewBinder$bindWhileAttached$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LifecycleCoroutineScopeImpl lifecycleScope = LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) this.L$0);
        NotificationIconContainer notificationIconContainer = this.$view;
        NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel = this.$viewModel;
        ConfigurationState configurationState = this.$configuration;
        SystemBarUtilsState systemBarUtilsState = this.$systemBarUtilsState;
        BuildersKt.launch$default(lifecycleScope, null, null, new AnonymousClass1(configurationState, this.$viewStore, this.$failureTracker, notificationIconContainerAlwaysOnDisplayViewModel, notificationIconContainer, systemBarUtilsState, null), 3);
        return Unit.INSTANCE;
    }
}
