package com.android.systemui.user.data.repository;

import android.content.Context;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.user.data.model.SelectedUserModel;
import com.android.systemui.user.data.model.SelectionStatus;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserRepositoryImpl$selectedUser$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Ref$ObjectRef $currentSelectionStatus;
    final /* synthetic */ UserRepositoryImpl $this_run;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserRepositoryImpl$selectedUser$1$1(UserRepositoryImpl userRepositoryImpl, Ref$ObjectRef ref$ObjectRef, Continuation continuation) {
        super(2, continuation);
        this.$this_run = userRepositoryImpl;
        this.$currentSelectionStatus = ref$ObjectRef;
    }

    public static final void invokeSuspend$send(Ref$ObjectRef ref$ObjectRef, ProducerScope producerScope, UserRepositoryImpl userRepositoryImpl, SelectionStatus selectionStatus) {
        ref$ObjectRef.element = selectionStatus;
        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(new SelectedUserModel(((UserTrackerImpl) userRepositoryImpl.tracker).getUserInfo(), selectionStatus));
        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
            FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "UserRepository", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        UserRepositoryImpl$selectedUser$1$1 userRepositoryImpl$selectedUser$1$1 = new UserRepositoryImpl$selectedUser$1$1(this.$this_run, this.$currentSelectionStatus, continuation);
        userRepositoryImpl$selectedUser$1$1.L$0 = obj;
        return userRepositoryImpl$selectedUser$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UserRepositoryImpl$selectedUser$1$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.settings.UserTracker$Callback, com.android.systemui.user.data.repository.UserRepositoryImpl$selectedUser$1$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final Ref$ObjectRef ref$ObjectRef = this.$currentSelectionStatus;
            final UserRepositoryImpl userRepositoryImpl = this.$this_run;
            final ?? r1 = new UserTracker.Callback() { // from class: com.android.systemui.user.data.repository.UserRepositoryImpl$selectedUser$1$1$callback$1
                @Override // com.android.systemui.settings.UserTracker.Callback
                public final void onBeforeUserSwitching(int i2) {
                    UserRepositoryImpl$selectedUser$1$1.invokeSuspend$send(Ref$ObjectRef.this, producerScope, userRepositoryImpl, SelectionStatus.SELECTION_IN_PROGRESS);
                }

                @Override // com.android.systemui.settings.UserTracker.Callback
                public final void onProfilesChanged(List list) {
                    Ref$ObjectRef ref$ObjectRef2 = Ref$ObjectRef.this;
                    UserRepositoryImpl$selectedUser$1$1.invokeSuspend$send(ref$ObjectRef2, producerScope, userRepositoryImpl, (SelectionStatus) ref$ObjectRef2.element);
                }

                @Override // com.android.systemui.settings.UserTracker.Callback
                public final void onUserChanged(int i2, Context context) {
                    UserRepositoryImpl$selectedUser$1$1.invokeSuspend$send(Ref$ObjectRef.this, producerScope, userRepositoryImpl, SelectionStatus.SELECTION_COMPLETE);
                }
            };
            ((UserTrackerImpl) userRepositoryImpl.tracker).addCallback(r1, ExecutorsKt.asExecutor(userRepositoryImpl.mainDispatcher));
            Ref$ObjectRef ref$ObjectRef2 = this.$currentSelectionStatus;
            invokeSuspend$send(ref$ObjectRef2, producerScope, this.$this_run, (SelectionStatus) ref$ObjectRef2.element);
            final UserRepositoryImpl userRepositoryImpl2 = this.$this_run;
            Function0 function0 = new Function0() { // from class: com.android.systemui.user.data.repository.UserRepositoryImpl$selectedUser$1$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ((UserTrackerImpl) UserRepositoryImpl.this.tracker).removeCallback(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
