package com.android.systemui.util.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PendingTasksContainer {
    public volatile AtomicReference completionCallback;
    public volatile AtomicInteger pendingTasksCount;
}
