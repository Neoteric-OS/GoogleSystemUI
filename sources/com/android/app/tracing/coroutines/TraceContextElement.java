package com.android.app.tracing.coroutines;

import android.os.Trace;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CopyableThreadContextElement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TraceContextElement extends BaseTraceElement implements CopyableThreadContextElement {
    public static final Key Key = new Key(BaseTraceElement.Key, new Function1() { // from class: com.android.app.tracing.coroutines.TraceContextElement.Key.1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            CoroutineContext.Element element = (CoroutineContext.Element) obj;
            if (element instanceof TraceContextElement) {
                return (TraceContextElement) element;
            }
            return null;
        }
    });
    public final AtomicInteger childCoroutineCount = new AtomicInteger(0);
    public final TraceData contextTraceData;
    public final String continuationTraceMessage;
    public final int coroutineDepth;
    public final int currentId;
    public final String fullCoroutineTraceName;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Key extends AbstractCoroutineContextKey {
    }

    public TraceContextElement(String str, TraceData traceData, int i, int i2) {
        this.contextTraceData = traceData;
        this.coroutineDepth = i;
        int hashCode = hashCode();
        this.currentId = hashCode;
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "");
        this.fullCoroutineTraceName = m;
        this.continuationTraceMessage = m + ";;d=" + i + ";c=" + hashCode + ";p=" + i2;
    }

    public final TraceContextElement createChildContext() {
        return new TraceContextElement(this.fullCoroutineTraceName + ":" + this.childCoroutineCount.incrementAndGet() + "^", new TraceData(), this.coroutineDepth + 1, this.currentId);
    }

    public final TraceData getContextTraceData() {
        return this.contextTraceData;
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public final void restoreThreadContext(Object obj) {
        TraceData traceData = (TraceData) obj;
        if (traceData != TraceContextElementKt.traceThreadLocal.get()) {
            TraceData traceData2 = this.contextTraceData;
            if (traceData2 != null) {
                int i = TraceDataKt.$r8$clinit;
                TraceCountThreadLocal traceCountThreadLocal = traceData2.openSliceCount;
                Integer num = (Integer) traceCountThreadLocal.get();
                int intValue = num == null ? 0 : num.intValue();
                for (int i2 = 0; i2 < intValue; i2++) {
                    TraceUtilsKt.endSlice();
                }
                traceCountThreadLocal.set(0);
            }
            TraceContextElementKt.traceThreadLocal.set(traceData);
            Trace.traceEnd(4096L);
        }
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public final Object updateThreadContext(CoroutineContext coroutineContext) {
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        TraceData traceData = (TraceData) traceDataThreadLocal.get();
        TraceData traceData2 = this.contextTraceData;
        if (traceData != traceData2) {
            Trace.traceBegin(4096L, this.continuationTraceMessage);
            traceDataThreadLocal.set(traceData2);
            if (traceData2 != null) {
                int i = TraceDataKt.$r8$clinit;
                traceData2.openSliceCount.set(0);
            }
        }
        return traceData;
    }
}
