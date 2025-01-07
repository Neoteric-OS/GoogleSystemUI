package com.google.common.collect;

import com.google.common.util.concurrent.MoreExecutors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ForwardingObject {
    public final String toString() {
        return ((MoreExecutors.ScheduledListeningDecorator.ListenableScheduledTask) this).delegate.toString();
    }
}
