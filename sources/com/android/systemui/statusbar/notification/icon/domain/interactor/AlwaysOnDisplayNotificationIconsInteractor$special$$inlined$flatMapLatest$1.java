package com.android.systemui.statusbar.notification.icon.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AlwaysOnDisplayNotificationIconsInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ NotificationIconsInteractor $iconsInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlwaysOnDisplayNotificationIconsInteractor$special$$inlined$flatMapLatest$1(Continuation continuation, NotificationIconsInteractor notificationIconsInteractor) {
        super(3, continuation);
        this.$iconsInteractor$inlined = notificationIconsInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AlwaysOnDisplayNotificationIconsInteractor$special$$inlined$flatMapLatest$1 alwaysOnDisplayNotificationIconsInteractor$special$$inlined$flatMapLatest$1 = new AlwaysOnDisplayNotificationIconsInteractor$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$iconsInteractor$inlined);
        alwaysOnDisplayNotificationIconsInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        alwaysOnDisplayNotificationIconsInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return alwaysOnDisplayNotificationIconsInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 filteredNotifSet$default = NotificationIconsInteractor.filteredNotifSet$default(this.$iconsInteractor$inlined, false, !((Boolean) this.L$1).booleanValue(), 5);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, filteredNotifSet$default, this) == coroutineSingletons) {
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
