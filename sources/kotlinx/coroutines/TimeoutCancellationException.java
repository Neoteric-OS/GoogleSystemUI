package kotlinx.coroutines;

import java.util.concurrent.CancellationException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TimeoutCancellationException extends CancellationException {
    public final transient TimeoutCoroutine coroutine;

    public TimeoutCancellationException(String str, TimeoutCoroutine timeoutCoroutine) {
        super(str);
        this.coroutine = timeoutCoroutine;
    }

    public TimeoutCancellationException(String str) {
        this(str, null);
    }
}
