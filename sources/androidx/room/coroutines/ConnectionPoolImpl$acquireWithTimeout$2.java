package androidx.room.coroutines;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelResult;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectionPoolImpl$acquireWithTimeout$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Ref$ObjectRef $connection;
    final /* synthetic */ Pool $this_acquireWithTimeout;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectionPoolImpl$acquireWithTimeout$2(Ref$ObjectRef ref$ObjectRef, Pool pool, Continuation continuation) {
        super(2, continuation);
        this.$connection = ref$ObjectRef;
        this.$this_acquireWithTimeout = pool;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ConnectionPoolImpl$acquireWithTimeout$2(this.$connection, this.$this_acquireWithTimeout, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ConnectionPoolImpl$acquireWithTimeout$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object receive;
        Ref$ObjectRef ref$ObjectRef;
        Throwable th;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref$ObjectRef ref$ObjectRef2 = this.$connection;
            Pool pool = this.$this_acquireWithTimeout;
            this.L$0 = ref$ObjectRef2;
            this.label = 1;
            BufferedChannel bufferedChannel = pool.channel;
            Object mo1789tryReceivePtdJZtk = bufferedChannel.mo1789tryReceivePtdJZtk();
            boolean z = mo1789tryReceivePtdJZtk instanceof ChannelResult.Failed;
            if (z) {
                pool.tryOpenNewConnection();
                receive = bufferedChannel.receive(this);
            } else {
                if (z) {
                    if ((mo1789tryReceivePtdJZtk instanceof ChannelResult.Closed) && (th = ((ChannelResult.Closed) mo1789tryReceivePtdJZtk).cause) != null) {
                        throw th;
                    }
                    throw new IllegalStateException(("Trying to call 'getOrThrow' on a failed channel result: " + mo1789tryReceivePtdJZtk).toString());
                }
                receive = (ConnectionWithLock) mo1789tryReceivePtdJZtk;
            }
            if (receive == coroutineSingletons) {
                return coroutineSingletons;
            }
            obj = receive;
            ref$ObjectRef = ref$ObjectRef2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ref$ObjectRef = (Ref$ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        ref$ObjectRef.element = obj;
        return Unit.INSTANCE;
    }
}
