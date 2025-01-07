package com.android.app.tracing;

import android.os.Trace;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FlowTracing$tracedConflatedCallbackFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function2 $block;
    final /* synthetic */ String $name;
    int I$0;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowTracing$tracedConflatedCallbackFlow$1(String str, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$name = str;
        this.$block = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlowTracing$tracedConflatedCallbackFlow$1 flowTracing$tracedConflatedCallbackFlow$1 = new FlowTracing$tracedConflatedCallbackFlow$1(this.$name, this.$block, continuation);
        flowTracing$tracedConflatedCallbackFlow$1.L$0 = obj;
        return flowTracing$tracedConflatedCallbackFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlowTracing$tracedConflatedCallbackFlow$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        String str;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            String str2 = this.$name;
            Function2 function2 = this.$block;
            if (Trace.isEnabled()) {
                String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str2, "#CallbackFlowBlock");
                int nextInt = ThreadLocalRandom.current().nextInt();
                Trace.asyncTraceForTrackBegin(4096L, "FlowTracing", m, nextInt);
                try {
                    this.L$0 = "FlowTracing";
                    this.I$0 = nextInt;
                    this.label = 1;
                    if (function2.invoke(producerScope, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                    i = nextInt;
                    str = "FlowTracing";
                    Trace.asyncTraceForTrackEnd(4096L, str, i);
                } catch (Throwable th) {
                    th = th;
                    i = nextInt;
                    str = "FlowTracing";
                    Trace.asyncTraceForTrackEnd(4096L, str, i);
                    throw th;
                }
            } else {
                this.label = 2;
                if (function2.invoke(producerScope, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else if (i2 == 1) {
            i = this.I$0;
            str = (String) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                Trace.asyncTraceForTrackEnd(4096L, str, i);
            } catch (Throwable th2) {
                th = th2;
                Trace.asyncTraceForTrackEnd(4096L, str, i);
                throw th;
            }
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
