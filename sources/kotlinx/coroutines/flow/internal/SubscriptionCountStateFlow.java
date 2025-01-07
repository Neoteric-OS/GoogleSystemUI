package kotlinx.coroutines.flow.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SubscriptionCountStateFlow extends SharedFlowImpl implements StateFlow {
    @Override // kotlinx.coroutines.flow.StateFlow
    public final Object getValue() {
        Integer valueOf;
        synchronized (this) {
            Object[] objArr = this.buffer;
            Intrinsics.checkNotNull(objArr);
            valueOf = Integer.valueOf(((Number) objArr[((int) ((this.replayIndex + ((int) ((getHead() + this.bufferSize) - this.replayIndex))) - 1)) & (objArr.length - 1)]).intValue());
        }
        return valueOf;
    }

    public final void increment(int i) {
        synchronized (this) {
            Object[] objArr = this.buffer;
            Intrinsics.checkNotNull(objArr);
            tryEmit(Integer.valueOf(((Number) objArr[((int) ((this.replayIndex + ((int) ((getHead() + this.bufferSize) - this.replayIndex))) - 1)) & (objArr.length - 1)]).intValue() + i));
        }
    }
}
