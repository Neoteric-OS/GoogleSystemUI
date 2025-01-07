package com.android.systemui.statusbar.notification.collection.coalescer;

import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EventBatch {
    public ExecutorImpl.ExecutionToken mCancelShortTimeout;
    public final long mCreatedTimestamp;
    public final String mGroupKey;
    public final List mMembers = new ArrayList();

    public EventBatch(long j, String str) {
        this.mCreatedTimestamp = j;
        this.mGroupKey = str;
    }
}
