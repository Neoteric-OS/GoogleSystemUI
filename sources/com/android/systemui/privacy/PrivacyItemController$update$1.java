package com.android.systemui.privacy;

import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyItemController$update$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PrivacyItemController this$0;

    public /* synthetic */ PrivacyItemController$update$1(PrivacyItemController privacyItemController, int i) {
        this.$r8$classId = i;
        this.this$0 = privacyItemController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.updateListAndNotifyChanges.run();
                return;
            case 1:
                PrivacyItemController privacyItemController = this.this$0;
                boolean isEmpty = privacyItemController.callbacks.isEmpty();
                boolean z = !isEmpty;
                if (privacyItemController.listening == z) {
                    return;
                }
                privacyItemController.listening = z;
                if (isEmpty) {
                    Iterator it = privacyItemController.privacyItemMonitors.iterator();
                    while (it.hasNext()) {
                        AppOpsPrivacyItemMonitor appOpsPrivacyItemMonitor = (AppOpsPrivacyItemMonitor) ((PrivacyItemMonitor) it.next());
                        synchronized (appOpsPrivacyItemMonitor.lock) {
                            appOpsPrivacyItemMonitor.callback = null;
                            appOpsPrivacyItemMonitor.setListeningStateLocked();
                        }
                    }
                    ((ExecutorImpl) privacyItemController.bgExecutor).execute(new PrivacyItemController$update$1(privacyItemController, 0));
                    return;
                }
                for (PrivacyItemMonitor privacyItemMonitor : privacyItemController.privacyItemMonitors) {
                    PrivacyItemController$privacyItemMonitorCallback$1 privacyItemController$privacyItemMonitorCallback$1 = privacyItemController.privacyItemMonitorCallback;
                    AppOpsPrivacyItemMonitor appOpsPrivacyItemMonitor2 = (AppOpsPrivacyItemMonitor) privacyItemMonitor;
                    synchronized (appOpsPrivacyItemMonitor2.lock) {
                        appOpsPrivacyItemMonitor2.callback = privacyItemController$privacyItemMonitorCallback$1;
                        appOpsPrivacyItemMonitor2.setListeningStateLocked();
                    }
                }
                ((ExecutorImpl) privacyItemController.bgExecutor).execute(new PrivacyItemController$update$1(privacyItemController, 0));
                return;
            default:
                List privacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core = this.this$0.getPrivacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
                Iterator it2 = this.this$0.callbacks.iterator();
                while (it2.hasNext()) {
                    PrivacyItemController.Callback callback = (PrivacyItemController.Callback) ((WeakReference) it2.next()).get();
                    if (callback != null) {
                        callback.onPrivacyItemsChanged(privacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core);
                    }
                }
                return;
        }
    }
}
