package com.android.app.tracing.coroutines.flow;

import java.lang.StackWalker;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FlowExt {
    public static final String currentFileName = ((StackWalker.StackFrame) ((Optional) StackWalker.getInstance().walk(FlowExt$currentFileName$1.INSTANCE)).get()).getFileName();
}
