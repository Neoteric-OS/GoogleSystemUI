package kotlinx.coroutines.channels;

import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BufferedChannelKt {
    public static final ChannelSegment NULL_SEGMENT = new ChannelSegment(-1, null, null, 0);
    public static final int SEGMENT_SIZE = SystemPropsKt.systemProp$default("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 12);
    public static final int EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS = SystemPropsKt.systemProp$default("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 12);
    public static final Symbol BUFFERED = new Symbol("BUFFERED");
    public static final Symbol IN_BUFFER = new Symbol("SHOULD_BUFFER");
    public static final Symbol RESUMING_BY_RCV = new Symbol("S_RESUMING_BY_RCV");
    public static final Symbol RESUMING_BY_EB = new Symbol("RESUMING_BY_EB");
    public static final Symbol POISONED = new Symbol("POISONED");
    public static final Symbol DONE_RCV = new Symbol("DONE_RCV");
    public static final Symbol INTERRUPTED_SEND = new Symbol("INTERRUPTED_SEND");
    public static final Symbol INTERRUPTED_RCV = new Symbol("INTERRUPTED_RCV");
    public static final Symbol CHANNEL_CLOSED = new Symbol("CHANNEL_CLOSED");
    public static final Symbol SUSPEND = new Symbol("SUSPEND");
    public static final Symbol SUSPEND_NO_WAITER = new Symbol("SUSPEND_NO_WAITER");
    public static final Symbol FAILED = new Symbol("FAILED");
    public static final Symbol NO_RECEIVE_RESULT = new Symbol("NO_RECEIVE_RESULT");
    public static final Symbol CLOSE_HANDLER_CLOSED = new Symbol("CLOSE_HANDLER_CLOSED");
    public static final Symbol CLOSE_HANDLER_INVOKED = new Symbol("CLOSE_HANDLER_INVOKED");
    public static final Symbol NO_CLOSE_CAUSE = new Symbol("NO_CLOSE_CAUSE");

    public static final boolean tryResume0(CancellableContinuation cancellableContinuation, Object obj, Function1 function1) {
        Symbol tryResume = cancellableContinuation.tryResume(obj, function1);
        if (tryResume == null) {
            return false;
        }
        cancellableContinuation.completeResume(tryResume);
        return true;
    }
}
