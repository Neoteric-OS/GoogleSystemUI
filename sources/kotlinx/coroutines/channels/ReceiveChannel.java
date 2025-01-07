package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.selects.SelectClause1Impl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ReceiveChannel {
    void cancel(CancellationException cancellationException);

    SelectClause1Impl getOnReceiveCatching();

    BufferedChannel.BufferedChannelIterator iterator();

    Object receive(SuspendLambda suspendLambda);

    /* renamed from: receiveCatching-JP2dKIU */
    Object mo1787receiveCatchingJP2dKIU(Continuation continuation);

    /* renamed from: tryReceive-PtdJZtk */
    Object mo1789tryReceivePtdJZtk();
}
