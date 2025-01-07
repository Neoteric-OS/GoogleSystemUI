package com.android.systemui.statusbar.notification.collection.coordinator;

import android.util.ArraySet;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda12;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GutsCoordinator implements Coordinator, Dumpable {
    public final GutsCoordinatorLogger logger;
    public final GutsCoordinator$mGutsListener$1 mGutsListener;
    public final GutsCoordinator$mLifetimeExtender$1 mLifetimeExtender;
    public final NotificationGutsManager notifGutsViewManager;
    public NotifCollection$$ExternalSyntheticLambda12 onEndLifetimeExtensionCallback;
    public final ArraySet notifsWithOpenGuts = new ArraySet();
    public final ArraySet notifsExtendingLifetime = new ArraySet();

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator$mLifetimeExtender$1] */
    public GutsCoordinator(NotificationGutsManager notificationGutsManager, GutsCoordinatorLogger gutsCoordinatorLogger, DumpManager dumpManager) {
        this.notifGutsViewManager = notificationGutsManager;
        this.logger = gutsCoordinatorLogger;
        dumpManager.registerDumpable(this);
        this.mLifetimeExtender = new NotifLifetimeExtender() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator$mLifetimeExtender$1
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
            public final void cancelLifetimeExtension$1(NotificationEntry notificationEntry) {
                GutsCoordinator.this.notifsExtendingLifetime.remove(notificationEntry.mKey);
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
            public final String getName() {
                return "GutsCoordinator";
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
            public final boolean maybeExtendLifetime(NotificationEntry notificationEntry, int i) {
                GutsCoordinator gutsCoordinator = GutsCoordinator.this;
                boolean contains = gutsCoordinator.notifsWithOpenGuts.contains(notificationEntry.mKey);
                if (contains) {
                    gutsCoordinator.notifsExtendingLifetime.add(notificationEntry.mKey);
                }
                return contains;
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
            public final void setCallback(NotifCollection$$ExternalSyntheticLambda12 notifCollection$$ExternalSyntheticLambda12) {
                GutsCoordinator.this.onEndLifetimeExtensionCallback = notifCollection$$ExternalSyntheticLambda12;
            }
        };
        this.mGutsListener = new GutsCoordinator$mGutsListener$1(this);
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        this.notifGutsViewManager.mGutsListener = this.mGutsListener;
        notifPipeline.addNotificationLifetimeExtender(this.mLifetimeExtender);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0031, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0077, code lost:
    
        throw r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006e, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007b, code lost:
    
        throw r3;
     */
    @Override // com.android.systemui.Dumpable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dump(java.io.PrintWriter r4, java.lang.String[] r5) {
        /*
            r3 = this;
            java.lang.String r5 = ": "
            android.util.IndentingPrintWriter r4 = com.android.systemui.util.DumpUtilsKt.asIndenting(r4)
            r4.increaseIndent()
            java.lang.String r0 = "notifsWithOpenGuts"
            android.util.ArraySet r1 = r3.notifsWithOpenGuts     // Catch: java.lang.Throwable -> L6e
            java.io.PrintWriter r0 = r4.append(r0)     // Catch: java.lang.Throwable -> L6e
            java.io.PrintWriter r0 = r0.append(r5)     // Catch: java.lang.Throwable -> L6e
            int r2 = r1.size()     // Catch: java.lang.Throwable -> L6e
            r0.println(r2)     // Catch: java.lang.Throwable -> L6e
            r4.increaseIndent()     // Catch: java.lang.Throwable -> L6e
            java.util.Iterator r0 = r1.iterator()     // Catch: java.lang.Throwable -> L31
        L23:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L31
            if (r1 == 0) goto L33
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L31
            r4.println(r1)     // Catch: java.lang.Throwable -> L31
            goto L23
        L31:
            r3 = move-exception
            goto L74
        L33:
            r4.decreaseIndent()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r0 = "notifsExtendingLifetime"
            android.util.ArraySet r1 = r3.notifsExtendingLifetime     // Catch: java.lang.Throwable -> L6e
            java.io.PrintWriter r0 = r4.append(r0)     // Catch: java.lang.Throwable -> L6e
            java.io.PrintWriter r5 = r0.append(r5)     // Catch: java.lang.Throwable -> L6e
            int r0 = r1.size()     // Catch: java.lang.Throwable -> L6e
            r5.println(r0)     // Catch: java.lang.Throwable -> L6e
            r4.increaseIndent()     // Catch: java.lang.Throwable -> L6e
            java.util.Iterator r5 = r1.iterator()     // Catch: java.lang.Throwable -> L5e
        L50:
            boolean r0 = r5.hasNext()     // Catch: java.lang.Throwable -> L5e
            if (r0 == 0) goto L60
            java.lang.Object r0 = r5.next()     // Catch: java.lang.Throwable -> L5e
            r4.println(r0)     // Catch: java.lang.Throwable -> L5e
            goto L50
        L5e:
            r3 = move-exception
            goto L70
        L60:
            r4.decreaseIndent()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r5 = "onEndLifetimeExtensionCallback"
            com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda12 r3 = r3.onEndLifetimeExtensionCallback     // Catch: java.lang.Throwable -> L6e
            com.android.systemui.util.DumpUtilsKt.println(r4, r5, r3)     // Catch: java.lang.Throwable -> L6e
            r4.decreaseIndent()
            return
        L6e:
            r3 = move-exception
            goto L78
        L70:
            r4.decreaseIndent()     // Catch: java.lang.Throwable -> L6e
            throw r3     // Catch: java.lang.Throwable -> L6e
        L74:
            r4.decreaseIndent()     // Catch: java.lang.Throwable -> L6e
            throw r3     // Catch: java.lang.Throwable -> L6e
        L78:
            r4.decreaseIndent()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator.dump(java.io.PrintWriter, java.lang.String[]):void");
    }
}
