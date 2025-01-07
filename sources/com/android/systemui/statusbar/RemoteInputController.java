package com.android.systemui.statusbar;

import android.util.ArrayMap;
import android.util.Pair;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.RemoteInputControllerLogger;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.RemoteInputUriController;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RemoteInputController {
    public final NotificationStackScrollLayoutController.AnonymousClass9 mDelegate;
    public final RemoteInputControllerLogger mLogger;
    public final RemoteInputUriController mRemoteInputUriController;
    public final ArrayList mOpen = new ArrayList();
    public final ArrayMap mSpinning = new ArrayMap();
    public final ArrayList mCallbacks = new ArrayList(3);
    public Boolean mLastAppliedRemoteInputActive = null;

    public RemoteInputController(NotificationStackScrollLayoutController.AnonymousClass9 anonymousClass9, RemoteInputUriController remoteInputUriController, RemoteInputControllerLogger remoteInputControllerLogger) {
        this.mDelegate = anonymousClass9;
        this.mRemoteInputUriController = remoteInputUriController;
        this.mLogger = remoteInputControllerLogger;
    }

    public final void apply(NotificationEntry notificationEntry) {
        boolean pruneWeakThenRemoveAndContains = pruneWeakThenRemoveAndContains(notificationEntry, null, null);
        HeadsUpManagerPhone headsUpManagerPhone = (HeadsUpManagerPhone) NotificationStackScrollLayoutController.this.mHeadsUpManager;
        headsUpManagerPhone.getClass();
        HeadsUpManagerPhone.HeadsUpEntryPhone headsUpEntryPhone = (HeadsUpManagerPhone.HeadsUpEntryPhone) headsUpManagerPhone.mHeadsUpEntryMap.get(notificationEntry.mKey);
        if (headsUpEntryPhone != null && headsUpEntryPhone.mRemoteInputActive != pruneWeakThenRemoveAndContains) {
            headsUpEntryPhone.mRemoteInputActive = pruneWeakThenRemoveAndContains;
            if (pruneWeakThenRemoveAndContains) {
                headsUpEntryPhone.cancelAutoRemovalCallbacks("setRemoteInputActive(true)");
            } else {
                headsUpEntryPhone.updateEntry("setRemoteInputActive(false)", false);
            }
            headsUpManagerPhone.updateTopHeadsUpFlow();
        }
        ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.notifyHeightChanged(true);
        }
        boolean isRemoteInputActive$1 = isRemoteInputActive$1();
        int size = this.mCallbacks.size();
        for (int i = 0; i < size; i++) {
            ((Callback) this.mCallbacks.get(i)).onRemoteInputActive(isRemoteInputActive$1);
        }
        this.mLastAppliedRemoteInputActive = Boolean.valueOf(isRemoteInputActive$1);
    }

    public final boolean isRemoteInputActive$1() {
        pruneWeakThenRemoveAndContains(null, null, null);
        return !this.mOpen.isEmpty();
    }

    public final boolean pruneWeakThenRemoveAndContains(NotificationEntry notificationEntry, NotificationEntry notificationEntry2, Object obj) {
        boolean z = false;
        for (int size = this.mOpen.size() - 1; size >= 0; size--) {
            NotificationEntry notificationEntry3 = (NotificationEntry) ((WeakReference) ((Pair) this.mOpen.get(size)).first).get();
            Object obj2 = ((Pair) this.mOpen.get(size)).second;
            boolean z2 = obj == null || obj2 == obj;
            if (notificationEntry3 == null || (notificationEntry3 == notificationEntry2 && z2)) {
                this.mOpen.remove(size);
            } else if (notificationEntry3 == notificationEntry) {
                if (obj == null || obj == obj2) {
                    z = true;
                } else {
                    this.mOpen.remove(size);
                }
            }
        }
        return z;
    }

    public final void removeRemoteInput(NotificationEntry notificationEntry, Object obj, String str) {
        Objects.requireNonNull(notificationEntry);
        if (notificationEntry.mRemoteEditImeVisible && notificationEntry.mRemoteEditImeAnimatingAway) {
            this.mLogger.logRemoveRemoteInput(notificationEntry.mKey, true, true, pruneWeakThenRemoveAndContains(notificationEntry, null, null), isRemoteInputActive$1(), str, notificationEntry.getNotificationStyle());
            return;
        }
        boolean pruneWeakThenRemoveAndContains = pruneWeakThenRemoveAndContains(notificationEntry, null, null);
        boolean isRemoteInputActive$1 = isRemoteInputActive$1();
        this.mLogger.logRemoveRemoteInput(notificationEntry.mKey, notificationEntry.mRemoteEditImeVisible, notificationEntry.mRemoteEditImeAnimatingAway, pruneWeakThenRemoveAndContains, isRemoteInputActive$1, str, notificationEntry.getNotificationStyle());
        if (pruneWeakThenRemoveAndContains) {
            pruneWeakThenRemoveAndContains(null, notificationEntry, obj);
            apply(notificationEntry);
            return;
        }
        Boolean bool = this.mLastAppliedRemoteInputActive;
        if (bool == null || !bool.booleanValue() || isRemoteInputActive$1) {
            return;
        }
        this.mLogger.logRemoteInputApplySkipped(notificationEntry.mKey, str, notificationEntry.getNotificationStyle());
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        default void onRemoteInputActive(boolean z) {
        }

        default void onRemoteInputSent(NotificationEntry notificationEntry) {
        }
    }
}
