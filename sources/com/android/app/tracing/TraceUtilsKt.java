package com.android.app.tracing;

import android.os.Trace;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TraceUtilsKt {
    public static final void beginSlice(String str) {
        Trace.traceBegin(4096L, str);
    }

    public static final void endSlice() {
        Trace.traceEnd(4096L);
    }
}
