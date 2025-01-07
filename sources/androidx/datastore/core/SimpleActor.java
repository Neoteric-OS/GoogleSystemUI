package androidx.datastore.core;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SimpleActor {
    public final Function2 consumeMessage;
    public final BufferedChannel messageQueue = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6);
    public final AtomicInt remainingMessages = new AtomicInt();
    public final CoroutineScope scope;

    public SimpleActor(CoroutineScope coroutineScope, final Function1 function1, final Function2 function2, Function2 function22) {
        this.scope = coroutineScope;
        this.consumeMessage = function22;
        Job job = (Job) coroutineScope.getCoroutineContext().get(Job.Key.$$INSTANCE);
        if (job != null) {
            job.invokeOnCompletion(new Function1() { // from class: androidx.datastore.core.SimpleActor.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit unit;
                    Unit unit2;
                    Throwable th = (Throwable) obj;
                    Function1.this.invoke(th);
                    this.messageQueue.closeOrCancelImpl(th, false);
                    do {
                        Object mo1789tryReceivePtdJZtk = this.messageQueue.mo1789tryReceivePtdJZtk();
                        unit = null;
                        if (mo1789tryReceivePtdJZtk instanceof ChannelResult.Failed) {
                            mo1789tryReceivePtdJZtk = null;
                        }
                        unit2 = Unit.INSTANCE;
                        if (mo1789tryReceivePtdJZtk != null) {
                            function2.invoke(mo1789tryReceivePtdJZtk, th);
                            unit = unit2;
                        }
                    } while (unit != null);
                    return unit2;
                }
            });
        }
    }
}
