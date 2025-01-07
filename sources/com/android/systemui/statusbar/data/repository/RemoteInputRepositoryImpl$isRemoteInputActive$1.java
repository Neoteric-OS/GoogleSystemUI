package com.android.systemui.statusbar.data.repository;

import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.RemoteInputController;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class RemoteInputRepositoryImpl$isRemoteInputActive$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RemoteInputRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoteInputRepositoryImpl$isRemoteInputActive$1(RemoteInputRepositoryImpl remoteInputRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = remoteInputRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        RemoteInputRepositoryImpl$isRemoteInputActive$1 remoteInputRepositoryImpl$isRemoteInputActive$1 = new RemoteInputRepositoryImpl$isRemoteInputActive$1(this.this$0, continuation);
        remoteInputRepositoryImpl$isRemoteInputActive$1.L$0 = obj;
        return remoteInputRepositoryImpl$isRemoteInputActive$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((RemoteInputRepositoryImpl$isRemoteInputActive$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.data.repository.RemoteInputRepositoryImpl$isRemoteInputActive$1$callback$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerCoroutine producerCoroutine = (ProducerCoroutine) ((ProducerScope) this.L$0);
            producerCoroutine.mo1790trySendJP2dKIU(Boolean.FALSE);
            final ?? r1 = new RemoteInputController.Callback() { // from class: com.android.systemui.statusbar.data.repository.RemoteInputRepositoryImpl$isRemoteInputActive$1$callback$1
                @Override // com.android.systemui.statusbar.RemoteInputController.Callback
                public final void onRemoteInputActive(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Boolean.valueOf(z));
                }
            };
            NotificationRemoteInputManager notificationRemoteInputManager = this.this$0.notificationRemoteInputManager;
            RemoteInputController remoteInputController = notificationRemoteInputManager.mRemoteInputController;
            if (remoteInputController != null) {
                remoteInputController.mCallbacks.add(r1);
            } else {
                notificationRemoteInputManager.mControllerCallbacks.add(r1);
            }
            final RemoteInputRepositoryImpl remoteInputRepositoryImpl = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.data.repository.RemoteInputRepositoryImpl$isRemoteInputActive$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    NotificationRemoteInputManager notificationRemoteInputManager2 = RemoteInputRepositoryImpl.this.notificationRemoteInputManager;
                    RemoteInputRepositoryImpl$isRemoteInputActive$1$callback$1 remoteInputRepositoryImpl$isRemoteInputActive$1$callback$1 = r1;
                    RemoteInputController remoteInputController2 = notificationRemoteInputManager2.mRemoteInputController;
                    if (remoteInputController2 != null) {
                        remoteInputController2.mCallbacks.remove(remoteInputRepositoryImpl$isRemoteInputActive$1$callback$1);
                    } else {
                        notificationRemoteInputManager2.mControllerCallbacks.remove(remoteInputRepositoryImpl$isRemoteInputActive$1$callback$1);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerCoroutine, function0, this) == coroutineSingletons) {
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
