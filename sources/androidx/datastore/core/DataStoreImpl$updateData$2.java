package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ClosedSendChannelException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DataStoreImpl$updateData$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function2 $transform;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataStoreImpl$updateData$2(DataStoreImpl dataStoreImpl, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DataStoreImpl$updateData$2 dataStoreImpl$updateData$2 = new DataStoreImpl$updateData$2(this.this$0, this.$transform, continuation);
        dataStoreImpl$updateData$2.L$0 = obj;
        return dataStoreImpl$updateData$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DataStoreImpl$updateData$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            CompletableDeferredImpl CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default();
            Message$Update message$Update = new Message$Update(this.$transform, CompletableDeferred$default, this.this$0.inMemoryCache.getCurrentState(), coroutineScope.getCoroutineContext());
            SimpleActor simpleActor = this.this$0.writeActor;
            Object mo1790trySendJP2dKIU = simpleActor.messageQueue.mo1790trySendJP2dKIU(message$Update);
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Closed) {
                Throwable m1791exceptionOrNullimpl = ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU);
                if (m1791exceptionOrNullimpl == null) {
                    throw new ClosedSendChannelException("Channel was closed normally");
                }
                throw m1791exceptionOrNullimpl;
            }
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                throw new IllegalStateException("Check failed.");
            }
            if (simpleActor.remainingMessages.delegate.getAndIncrement() == 0) {
                BuildersKt.launch$default(simpleActor.scope, null, null, new SimpleActor$offer$2(simpleActor, null), 3);
            }
            this.label = 1;
            obj = CompletableDeferred$default.awaitInternal(this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
