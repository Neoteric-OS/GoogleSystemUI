package com.android.app.tracing;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Trace;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$IntRef;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlowTracing {
    public static final FlowTracing INSTANCE = null;
    public static final AtomicInteger counter = new AtomicInteger(0);

    public static FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 traceAsCounter$default(Flow flow, Function1 function1) {
        return new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(flow, new FlowTracing$traceAsCounter$2("panel_expansion", function1, null), 0);
    }

    public static FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 traceEmissionCount$default(Flow flow, String str) {
        return new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(flow, new FlowTracing$traceEmissionCount$1(new Ref$IntRef(), LazyKt__LazyJVMKt.lazy(new FlowTracing$traceEmissionCount$trackName$2(str, false)), null), 0);
    }

    public static Object tracedAwaitClose(ProducerScope producerScope, final String str, final Function0 function0, SuspendLambda suspendLambda) {
        Object awaitClose = ProduceKt.awaitClose(producerScope, new Function0() { // from class: com.android.app.tracing.FlowTracing$tracedAwaitClose$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean isEnabled;
                final String str2 = str;
                Function0 function02 = new Function0() { // from class: com.android.app.tracing.FlowTracing$tracedAwaitClose$3$traceName$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str2, "#TracedAwaitClose");
                    }
                };
                Function0 function03 = function0;
                if (Trace.isEnabled()) {
                    String str3 = (String) function02.invoke();
                    int nextInt = ThreadLocalRandom.current().nextInt();
                    Trace.asyncTraceForTrackBegin(4096L, "FlowTracing", str3, nextInt);
                    try {
                        isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice((String) function02.invoke());
                        }
                        try {
                            function03.invoke();
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        } finally {
                        }
                    } finally {
                        Trace.asyncTraceForTrackEnd(4096L, "FlowTracing", nextInt);
                    }
                } else {
                    isEnabled = Trace.isEnabled();
                    if (isEnabled) {
                        TraceUtilsKt.beginSlice((String) function02.invoke());
                    }
                    try {
                        function03.invoke();
                    } finally {
                        if (isEnabled) {
                            TraceUtilsKt.endSlice();
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        }, suspendLambda);
        return awaitClose == CoroutineSingletons.COROUTINE_SUSPENDED ? awaitClose : Unit.INSTANCE;
    }

    public static FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 tracedConflatedCallbackFlow(String str, Function2 function2) {
        return new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.buffer$default(FlowKt.callbackFlow(new FlowTracing$tracedConflatedCallbackFlow$1(str, function2, null)), -1), new FlowTracing$traceEmissionCount$1(new Ref$IntRef(), LazyKt__LazyJVMKt.lazy(new FlowTracing$traceEmissionCount$trackName$2(str, true)), null), 0);
    }

    public static FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 traceEmissionCount$default(ChannelFlowTransformLatest channelFlowTransformLatest, final Function0 function0) {
        return new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(channelFlowTransformLatest, new FlowTracing$traceEmissionCount$2(new Ref$IntRef(), LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.app.tracing.FlowTracing$traceEmissionCount$trackName$4
            final /* synthetic */ boolean $uniqueSuffix = false;

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Function0.this.invoke() + "#emissionCount" + (this.$uniqueSuffix ? AnnotationValue$1$$ExternalSyntheticOutline0.m(FlowTracing.counter.addAndGet(1), "$") : "");
            }
        }), null), 0);
    }
}
