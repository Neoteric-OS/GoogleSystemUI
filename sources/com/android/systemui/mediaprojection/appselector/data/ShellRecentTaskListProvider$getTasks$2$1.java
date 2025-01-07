package com.android.systemui.mediaprojection.appselector.data;

import java.util.List;
import java.util.function.Consumer;
import kotlin.coroutines.SafeContinuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShellRecentTaskListProvider$getTasks$2$1 implements Consumer {
    public final /* synthetic */ SafeContinuation $continuation;

    public ShellRecentTaskListProvider$getTasks$2$1(SafeContinuation safeContinuation) {
        this.$continuation = safeContinuation;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.$continuation.resumeWith((List) obj);
    }
}
