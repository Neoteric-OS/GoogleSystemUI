package androidx.activity.compose;

import java.util.concurrent.CancellationException;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OnBackInstance {
    public final BufferedChannel channel = ChannelKt.Channel$default(-2, BufferOverflow.SUSPEND, null, 4);
    public boolean isPredictiveBack;
    public final StandaloneCoroutine job;

    public OnBackInstance(ContextScope contextScope, boolean z, Function2 function2) {
        this.isPredictiveBack = z;
        this.job = BuildersKt.launch$default(contextScope, null, null, new OnBackInstance$job$1(function2, this, null), 3);
    }

    public final void cancel() {
        this.channel.closeOrCancelImpl(new CancellationException("onBack cancelled"), true);
        this.job.cancel(null);
    }
}
