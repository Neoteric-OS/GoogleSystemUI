package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationIconContainerViewBinder$bindIsolatedIcon$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ NotificationIconContainerStatusBarViewModel $this_bindIsolatedIcon;
    final /* synthetic */ NotificationIconContainer $view;
    final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bindIsolatedIcon$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationIconContainerStatusBarViewModel $this_bindIsolatedIcon;
        final /* synthetic */ NotificationIconContainer $view;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, NotificationIconContainer notificationIconContainer, Continuation continuation) {
            super(2, continuation);
            this.$this_bindIsolatedIcon = notificationIconContainerStatusBarViewModel;
            this.$view = notificationIconContainer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$this_bindIsolatedIcon, this.$view, continuation);
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
                Flow flow = this.$this_bindIsolatedIcon.isolatedIconLocation;
                NotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1 notificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1 = new NotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1(this.$view, 1);
                this.label = 1;
                if (flow.collect(notificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1, this) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bindIsolatedIcon$2$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ NotificationIconContainerStatusBarViewModel $this_bindIsolatedIcon;
        final /* synthetic */ NotificationIconContainer $view;
        final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, NotificationIconContainer notificationIconContainer, NotificationIconContainerViewBinder.IconViewStore iconViewStore, Continuation continuation) {
            super(2, continuation);
            this.$this_bindIsolatedIcon = notificationIconContainerStatusBarViewModel;
            this.$view = notificationIconContainer;
            this.$viewStore = iconViewStore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$this_bindIsolatedIcon, this.$view, this.$viewStore, continuation);
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
                ChannelFlowTransformLatest channelFlowTransformLatest = this.$this_bindIsolatedIcon.isolatedIcon;
                NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1 notificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1 = new NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1(0, this.$view, this.$viewStore);
                this.label = 1;
                if (channelFlowTransformLatest.collect(notificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1, this) == coroutineSingletons) {
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
    public NotificationIconContainerViewBinder$bindIsolatedIcon$2(NotificationIconContainerStatusBarViewModel notificationIconContainerStatusBarViewModel, NotificationIconContainer notificationIconContainer, NotificationIconContainerViewBinder.IconViewStore iconViewStore, Continuation continuation) {
        super(2, continuation);
        this.$this_bindIsolatedIcon = notificationIconContainerStatusBarViewModel;
        this.$view = notificationIconContainer;
        this.$viewStore = iconViewStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationIconContainerViewBinder$bindIsolatedIcon$2 notificationIconContainerViewBinder$bindIsolatedIcon$2 = new NotificationIconContainerViewBinder$bindIsolatedIcon$2(this.$this_bindIsolatedIcon, this.$view, this.$viewStore, continuation);
        notificationIconContainerViewBinder$bindIsolatedIcon$2.L$0 = obj;
        return notificationIconContainerViewBinder$bindIsolatedIcon$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationIconContainerViewBinder$bindIsolatedIcon$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$this_bindIsolatedIcon, this.$view, null), 3);
        return BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$this_bindIsolatedIcon, this.$view, this.$viewStore, null), 3);
    }
}
