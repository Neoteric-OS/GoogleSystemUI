package com.android.systemui.temporarydisplay;

import com.android.internal.logging.InstanceId;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TemporaryViewInfo {
    public abstract String getId();

    public abstract InstanceId getInstanceId();

    public abstract ViewPriority getPriority();

    public int getTimeoutMs() {
        return 10000;
    }

    public abstract String getWakeReason();

    public abstract String getWindowTitle();
}
