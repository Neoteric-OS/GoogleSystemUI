package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.StatusBarIconView;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationIconContainerViewBinder$bindIcons$5$2$1$2$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function3 $bindIcon;
    final /* synthetic */ StateFlow $layoutParams;
    final /* synthetic */ String $logTag;
    final /* synthetic */ String $notifKey;
    final /* synthetic */ StatusBarIconView $sbiv;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bindIcons$5$2$1$2$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ StateFlow $layoutParams;
        final /* synthetic */ String $logTag;
        final /* synthetic */ StatusBarIconView $sbiv;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(StateFlow stateFlow, String str, StatusBarIconView statusBarIconView, Continuation continuation) {
            super(2, continuation);
            this.$layoutParams = stateFlow;
            this.$logTag = str;
            this.$sbiv = statusBarIconView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$layoutParams, this.$logTag, this.$sbiv, continuation);
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
                StateFlow stateFlow = this.$layoutParams;
                final String str = this.$logTag;
                Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder.bindIcons.5.2.1.2.2.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("[", str, "] SBIV#bindLayoutParams");
                    }
                };
                NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1 notificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1 = new NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1(1, LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, function0), this.$sbiv);
                this.label = 1;
                if (stateFlow.collect(notificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1, this) == coroutineSingletons) {
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
    public NotificationIconContainerViewBinder$bindIcons$5$2$1$2$2(Function3 function3, String str, StatusBarIconView statusBarIconView, StateFlow stateFlow, String str2, Continuation continuation) {
        super(2, continuation);
        this.$bindIcon = function3;
        this.$notifKey = str;
        this.$sbiv = statusBarIconView;
        this.$layoutParams = stateFlow;
        this.$logTag = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationIconContainerViewBinder$bindIcons$5$2$1$2$2 notificationIconContainerViewBinder$bindIcons$5$2$1$2$2 = new NotificationIconContainerViewBinder$bindIcons$5$2$1$2$2(this.$bindIcon, this.$notifKey, this.$sbiv, this.$layoutParams, this.$logTag, continuation);
        notificationIconContainerViewBinder$bindIcons$5$2$1$2$2.L$0 = obj;
        return notificationIconContainerViewBinder$bindIcons$5$2$1$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationIconContainerViewBinder$bindIcons$5$2$1$2$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BuildersKt.launch$default((CoroutineScope) this.L$0, null, null, new AnonymousClass1(this.$layoutParams, this.$logTag, this.$sbiv, null), 3);
            Function3 function3 = this.$bindIcon;
            String str = this.$notifKey;
            StatusBarIconView statusBarIconView = this.$sbiv;
            this.label = 1;
            if (function3.invoke(str, statusBarIconView, this) == coroutineSingletons) {
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
