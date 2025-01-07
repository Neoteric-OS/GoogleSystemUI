package com.android.systemui.mediaprojection.taskswitcher.data.repository;

import android.app.ActivityManager;
import android.app.ITaskStackListener;
import android.app.TaskStackListener;
import android.util.Log;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ActivityTaskManagerTasksRepository$foregroundTask$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ActivityTaskManagerTasksRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityTaskManagerTasksRepository$foregroundTask$1(ActivityTaskManagerTasksRepository activityTaskManagerTasksRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = activityTaskManagerTasksRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ActivityTaskManagerTasksRepository$foregroundTask$1 activityTaskManagerTasksRepository$foregroundTask$1 = new ActivityTaskManagerTasksRepository$foregroundTask$1(this.this$0, continuation);
        activityTaskManagerTasksRepository$foregroundTask$1.L$0 = obj;
        return activityTaskManagerTasksRepository$foregroundTask$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ActivityTaskManagerTasksRepository$foregroundTask$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ITaskStackListener iTaskStackListener = new TaskStackListener() { // from class: com.android.systemui.mediaprojection.taskswitcher.data.repository.ActivityTaskManagerTasksRepository$foregroundTask$1$listener$1
                public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
                    Log.d("TasksRepository", "onTaskMovedToFront: " + runningTaskInfo);
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(runningTaskInfo);
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "TasksRepository", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            this.this$0.activityTaskManager.registerTaskStackListener(iTaskStackListener);
            final ActivityTaskManagerTasksRepository activityTaskManagerTasksRepository = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.mediaprojection.taskswitcher.data.repository.ActivityTaskManagerTasksRepository$foregroundTask$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ActivityTaskManagerTasksRepository.this.activityTaskManager.unregisterTaskStackListener(iTaskStackListener);
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
