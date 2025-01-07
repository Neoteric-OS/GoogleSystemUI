package androidx.room.coroutines;

import androidx.sqlite.SQLiteConnection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Pool {
    public final int capacity;
    public final BufferedChannel channel;
    public final Lambda connectionFactory;
    public final ConnectionWithLock[] connections;
    public final AtomicInt size = AtomicFU.atomic(0);

    /* JADX WARN: Multi-variable type inference failed */
    public Pool(int i, Function0 function0) {
        this.capacity = i;
        this.connectionFactory = (Lambda) function0;
        this.connections = new ConnectionWithLock[i];
        this.channel = ChannelKt.Channel$default(i, null, new Function1() { // from class: androidx.room.coroutines.Pool$channel$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Pool.this.recycle((ConnectionWithLock) obj);
                return Unit.INSTANCE;
            }
        }, 2);
    }

    public final void recycle(ConnectionWithLock connectionWithLock) {
        Object mo1790trySendJP2dKIU = this.channel.mo1790trySendJP2dKIU(connectionWithLock);
        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
            connectionWithLock.close();
            if (!(mo1790trySendJP2dKIU instanceof ChannelResult.Closed)) {
                throw new IllegalStateException("Couldn't recycle connection");
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public final void tryOpenNewConnection() {
        int i = this.size.value;
        if (i >= this.capacity) {
            return;
        }
        AtomicInt atomicInt = this.size;
        atomicInt.getClass();
        if (!AtomicInt.FU.compareAndSet(atomicInt, i, i + 1)) {
            tryOpenNewConnection();
            return;
        }
        ConnectionWithLock connectionWithLock = new ConnectionWithLock((SQLiteConnection) this.connectionFactory.invoke());
        Object mo1790trySendJP2dKIU = this.channel.mo1790trySendJP2dKIU(connectionWithLock);
        if (!(mo1790trySendJP2dKIU instanceof ChannelResult.Failed)) {
            this.connections[i] = connectionWithLock;
            return;
        }
        connectionWithLock.close();
        if (!(mo1790trySendJP2dKIU instanceof ChannelResult.Closed)) {
            throw new IllegalStateException("Couldn't send a new connection for acquisition");
        }
    }
}
