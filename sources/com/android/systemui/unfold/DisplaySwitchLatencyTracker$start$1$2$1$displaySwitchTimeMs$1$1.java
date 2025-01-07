package com.android.systemui.unfold;

import android.os.Trace;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DisplaySwitchLatencyTracker$start$1$2$1$displaySwitchTimeMs$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $toFoldableDeviceState;
    int I$0;
    Object L$0;
    int label;
    final /* synthetic */ DisplaySwitchLatencyTracker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisplaySwitchLatencyTracker$start$1$2$1$displaySwitchTimeMs$1$1(DisplaySwitchLatencyTracker displaySwitchLatencyTracker, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = displaySwitchLatencyTracker;
        this.$toFoldableDeviceState = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DisplaySwitchLatencyTracker$start$1$2$1$displaySwitchTimeMs$1$1(this.this$0, this.$toFoldableDeviceState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DisplaySwitchLatencyTracker$start$1$2$1$displaySwitchTimeMs$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        String str;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            DisplaySwitchLatencyTracker displaySwitchLatencyTracker = this.this$0;
            int i3 = this.$toFoldableDeviceState;
            int nextInt = ThreadLocalRandom.current().nextInt();
            Trace.asyncTraceForTrackBegin(4096L, "DisplaySwitchLatency", "displaySwitch", nextInt);
            try {
                this.L$0 = "DisplaySwitchLatency";
                this.I$0 = nextInt;
                this.label = 1;
                if (DisplaySwitchLatencyTracker.access$waitForDisplaySwitch(displaySwitchLatencyTracker, i3, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                i = nextInt;
                str = "DisplaySwitchLatency";
            } catch (Throwable th) {
                th = th;
                i = nextInt;
                str = "DisplaySwitchLatency";
                Trace.asyncTraceForTrackEnd(4096L, str, i);
                throw th;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$0;
            str = (String) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                Trace.asyncTraceForTrackEnd(4096L, str, i);
                throw th;
            }
        }
        Trace.asyncTraceForTrackEnd(4096L, str, i);
        return Unit.INSTANCE;
    }
}
