package com.android.app.tracing.coroutines.flow;

import android.os.Trace;
import java.lang.StackWalker;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FlowExtKt {
    public static final String walkStackForClassName() {
        Trace.traceBegin(4096L, "FlowExt#walkStackForClassName");
        try {
            Optional optional = (Optional) StackWalker.getInstance().walk(FlowExtKt$walkStackForClassName$interestingFrame$1.INSTANCE);
            return optional.isPresent() ? ((StackWalker.StackFrame) optional.get()).getClassName() : "<unknown>";
        } finally {
            Trace.traceEnd(4096L);
        }
    }
}
