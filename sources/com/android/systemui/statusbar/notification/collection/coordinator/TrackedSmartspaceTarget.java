package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TrackedSmartspaceTarget {
    public long alertExceptionExpires;
    public ExecutorImpl.ExecutionToken cancelTimeoutRunnable;
    public final String key;
    public boolean shouldFilter;

    public TrackedSmartspaceTarget(String str) {
        this.key = str;
    }
}
