package com.android.systemui.statusbar;

import android.util.ArraySet;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.Dumpable;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator$setRemoteInputController$1;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SmartReplyController implements Dumpable {
    public final IStatusBarService mBarService;
    public RemoteInputCoordinator$setRemoteInputController$1 mCallback;
    public final NotificationClickNotifier mClickNotifier;
    public final Set mSendingKeys = new ArraySet();
    public final NotificationVisibilityProvider mVisibilityProvider;

    public SmartReplyController(DumpManager dumpManager, NotificationVisibilityProvider notificationVisibilityProvider, IStatusBarService iStatusBarService, NotificationClickNotifier notificationClickNotifier) {
        this.mBarService = iStatusBarService;
        this.mVisibilityProvider = notificationVisibilityProvider;
        this.mClickNotifier = notificationClickNotifier;
        dumpManager.registerDumpable(this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("mSendingKeys: " + ((ArraySet) this.mSendingKeys).size());
        Iterator it = ((ArraySet) this.mSendingKeys).iterator();
        while (it.hasNext()) {
            FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, " * ", (String) it.next());
        }
    }

    public final void stopSending(NotificationEntry notificationEntry) {
        if (notificationEntry != null) {
            ((ArraySet) this.mSendingKeys).remove(notificationEntry.mSbn.getKey());
        }
    }
}
