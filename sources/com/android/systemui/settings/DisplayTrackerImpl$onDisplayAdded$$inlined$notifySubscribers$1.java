package com.android.systemui.settings;

import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.DisplayTrackerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayTrackerImpl$onDisplayAdded$$inlined$notifySubscribers$1 implements Runnable {
    public final /* synthetic */ int $displayId$inlined;
    public final /* synthetic */ DisplayTrackerImpl.DisplayTrackerDataItem $it;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ DisplayTrackerImpl$onDisplayAdded$$inlined$notifySubscribers$1(DisplayTrackerImpl.DisplayTrackerDataItem displayTrackerDataItem, int i, int i2) {
        this.$r8$classId = i2;
        this.$it = displayTrackerDataItem;
        this.$displayId$inlined = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DisplayTracker.Callback callback = (DisplayTracker.Callback) this.$it.callback.get();
                if (callback != null) {
                    callback.onDisplayAdded(this.$displayId$inlined);
                    break;
                }
                break;
            default:
                DisplayTracker.Callback callback2 = (DisplayTracker.Callback) this.$it.callback.get();
                if (callback2 != null) {
                    callback2.onDisplayRemoved(this.$displayId$inlined);
                    break;
                }
                break;
        }
    }
}
