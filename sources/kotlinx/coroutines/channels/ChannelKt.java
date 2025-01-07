package kotlinx.coroutines.channels;

import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.Channel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ChannelKt {
    public static BufferedChannel Channel$default(int i, BufferOverflow bufferOverflow, Function1 function1, int i2) {
        BufferedChannel conflatedBufferedChannel;
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        if (i != -2) {
            if (i == -1) {
                if (bufferOverflow == BufferOverflow.SUSPEND) {
                    return new ConflatedBufferedChannel(1, BufferOverflow.DROP_OLDEST, function1);
                }
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
            }
            if (i != 0) {
                return i != Integer.MAX_VALUE ? bufferOverflow == BufferOverflow.SUSPEND ? new BufferedChannel(i, function1) : new ConflatedBufferedChannel(i, bufferOverflow, function1) : new BufferedChannel(Integer.MAX_VALUE, function1);
            }
            conflatedBufferedChannel = bufferOverflow == BufferOverflow.SUSPEND ? new BufferedChannel(0, function1) : new ConflatedBufferedChannel(1, bufferOverflow, function1);
        } else if (bufferOverflow == BufferOverflow.SUSPEND) {
            Channel.Factory.getClass();
            conflatedBufferedChannel = new BufferedChannel(Channel.Factory.CHANNEL_DEFAULT_CAPACITY, function1);
        } else {
            conflatedBufferedChannel = new ConflatedBufferedChannel(1, bufferOverflow, function1);
        }
        return conflatedBufferedChannel;
    }
}
