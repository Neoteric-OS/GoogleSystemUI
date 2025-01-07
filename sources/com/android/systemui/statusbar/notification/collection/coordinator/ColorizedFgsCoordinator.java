package com.android.systemui.statusbar.notification.collection.coordinator;

import android.app.Notification;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColorizedFgsCoordinator implements Coordinator {
    public final AnonymousClass1 mNotifSectioner = new AnonymousClass1("ColorizedSectioner", 3);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.coordinator.ColorizedFgsCoordinator$1, reason: invalid class name */
    public final class AnonymousClass1 extends NotifSectioner {
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final boolean isInSection(ListEntry listEntry) {
            NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
            if (representativeEntry == null) {
                return false;
            }
            Notification notification = representativeEntry.mSbn.getNotification();
            if (notification.isForegroundService() && notification.isColorized() && representativeEntry.mRanking.getImportance() > 1) {
                return true;
            }
            return representativeEntry.mRanking.getImportance() > 1 && representativeEntry.mSbn.getNotification().isStyle(Notification.CallStyle.class);
        }
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
    }
}
