package kotlinx.coroutines.channels;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.channels.ChannelResult;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ChannelsKt {
    public static final void trySendBlocking(SendChannel sendChannel, Object obj) {
        Object mo1790trySendJP2dKIU = sendChannel.mo1790trySendJP2dKIU(obj);
        if (!(mo1790trySendJP2dKIU instanceof ChannelResult.Failed)) {
        } else {
            Object obj2 = ((ChannelResult) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new ChannelsKt__ChannelsKt$trySendBlocking$2(sendChannel, obj, null))).holder;
        }
    }
}
