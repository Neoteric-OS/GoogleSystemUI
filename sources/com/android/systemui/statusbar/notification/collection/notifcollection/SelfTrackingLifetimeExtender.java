package com.android.systemui.statusbar.notification.collection.notifcollection;

import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda12;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SelfTrackingLifetimeExtender implements NotifLifetimeExtender, Dumpable {
    public final boolean debug;
    public NotifCollection$$ExternalSyntheticLambda12 mCallback;
    public boolean mEnding;
    public final ArrayMap mEntriesExtended = new ArrayMap();
    public final Handler mainHandler;
    public final String name;

    public SelfTrackingLifetimeExtender(Handler handler, String str, boolean z) {
        this.name = str;
        this.debug = z;
        this.mainHandler = handler;
    }

    @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
    public final void cancelLifetimeExtension$1(NotificationEntry notificationEntry) {
        String str = notificationEntry.mKey;
        if (this.debug) {
            boolean containsKey = this.mEntriesExtended.containsKey(str);
            StringBuilder sb = new StringBuilder();
            PlatformSliderColors$$ExternalSyntheticOutline0.m(sb, this.name, ".cancelLifetimeExtension(key=", str, ") isExtending=");
            CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, containsKey, "RemoteInputCoordinator");
        }
        warnIfEnding();
        this.mEntriesExtended.remove(str);
        onCanceledLifetimeExtension(notificationEntry);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0049, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0052, code lost:
    
        throw r1;
     */
    @Override // com.android.systemui.Dumpable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dump(java.io.PrintWriter r2, java.lang.String[] r3) {
        /*
            r1 = this;
            android.util.IndentingPrintWriter r2 = com.android.systemui.util.DumpUtilsKt.asIndenting(r2)
            java.lang.String r3 = "LifetimeExtender"
            java.lang.String r0 = r1.name
            com.android.systemui.util.DumpUtilsKt.println(r2, r3, r0)
            r2.increaseIndent()
            java.lang.String r3 = "mEntriesExtended"
            android.util.ArrayMap r1 = r1.mEntriesExtended     // Catch: java.lang.Throwable -> L49
            java.util.Set r1 = r1.keySet()     // Catch: java.lang.Throwable -> L49
            java.util.Collection r1 = (java.util.Collection) r1     // Catch: java.lang.Throwable -> L49
            java.io.PrintWriter r3 = r2.append(r3)     // Catch: java.lang.Throwable -> L49
            java.lang.String r0 = ": "
            java.io.PrintWriter r3 = r3.append(r0)     // Catch: java.lang.Throwable -> L49
            int r0 = r1.size()     // Catch: java.lang.Throwable -> L49
            r3.println(r0)     // Catch: java.lang.Throwable -> L49
            r2.increaseIndent()     // Catch: java.lang.Throwable -> L49
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch: java.lang.Throwable -> L40
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L40
        L32:
            boolean r3 = r1.hasNext()     // Catch: java.lang.Throwable -> L40
            if (r3 == 0) goto L42
            java.lang.Object r3 = r1.next()     // Catch: java.lang.Throwable -> L40
            r2.println(r3)     // Catch: java.lang.Throwable -> L40
            goto L32
        L40:
            r1 = move-exception
            goto L4b
        L42:
            r2.decreaseIndent()     // Catch: java.lang.Throwable -> L49
            r2.decreaseIndent()
            return
        L49:
            r1 = move-exception
            goto L4f
        L4b:
            r2.decreaseIndent()     // Catch: java.lang.Throwable -> L49
            throw r1     // Catch: java.lang.Throwable -> L49
        L4f:
            r2.decreaseIndent()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.notifcollection.SelfTrackingLifetimeExtender.dump(java.io.PrintWriter, java.lang.String[]):void");
    }

    public final void endLifetimeExtension(String str) {
        if (this.debug) {
            boolean containsKey = this.mEntriesExtended.containsKey(str);
            StringBuilder sb = new StringBuilder();
            PlatformSliderColors$$ExternalSyntheticOutline0.m(sb, this.name, ".endLifetimeExtension(key=", str, ") isExtending=");
            CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, containsKey, "RemoteInputCoordinator");
        }
        warnIfEnding();
        this.mEnding = true;
        NotificationEntry notificationEntry = (NotificationEntry) this.mEntriesExtended.remove(str);
        if (notificationEntry != null) {
            NotifCollection$$ExternalSyntheticLambda12 notifCollection$$ExternalSyntheticLambda12 = this.mCallback;
            if (notifCollection$$ExternalSyntheticLambda12 == null) {
                notifCollection$$ExternalSyntheticLambda12 = null;
            }
            notifCollection$$ExternalSyntheticLambda12.onEndLifetimeExtension(notificationEntry, this);
        }
        this.mEnding = false;
    }

    public final void endLifetimeExtensionAfterDelay(long j, final String str) {
        if (this.debug) {
            boolean containsKey = this.mEntriesExtended.containsKey(str);
            StringBuilder sb = new StringBuilder();
            PlatformSliderColors$$ExternalSyntheticOutline0.m(sb, this.name, ".endLifetimeExtensionAfterDelay(key=", str, ", delayMillis=");
            sb.append(j);
            sb.append(") isExtending=");
            sb.append(containsKey);
            Log.d("RemoteInputCoordinator", sb.toString());
        }
        if (this.mEntriesExtended.containsKey(str)) {
            this.mainHandler.postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.SelfTrackingLifetimeExtender$endLifetimeExtensionAfterDelay$1
                @Override // java.lang.Runnable
                public final void run() {
                    SelfTrackingLifetimeExtender.this.endLifetimeExtension(str);
                }
            }, j);
        }
    }

    @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
    public final String getName() {
        return this.name;
    }

    @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
    public final boolean maybeExtendLifetime(NotificationEntry notificationEntry, int i) {
        boolean queryShouldExtendLifetime = queryShouldExtendLifetime(notificationEntry);
        boolean z = this.debug;
        String str = notificationEntry.mKey;
        if (z) {
            boolean containsKey = this.mEntriesExtended.containsKey(str);
            StringBuilder sb = new StringBuilder();
            PlatformSliderColors$$ExternalSyntheticOutline0.m(sb, this.name, ".shouldExtendLifetime(key=", str, ", reason=");
            sb.append(i);
            sb.append(") isExtending=");
            sb.append(containsKey);
            sb.append(" shouldExtend=");
            CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, queryShouldExtendLifetime, "RemoteInputCoordinator");
        }
        warnIfEnding();
        if (queryShouldExtendLifetime && this.mEntriesExtended.put(str, notificationEntry) == null) {
            onStartedLifetimeExtension(notificationEntry);
        }
        return queryShouldExtendLifetime;
    }

    public abstract boolean queryShouldExtendLifetime(NotificationEntry notificationEntry);

    @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender
    public final void setCallback(NotifCollection$$ExternalSyntheticLambda12 notifCollection$$ExternalSyntheticLambda12) {
        this.mCallback = notifCollection$$ExternalSyntheticLambda12;
    }

    public final void warnIfEnding() {
        if (this.debug && this.mEnding) {
            Log.w("RemoteInputCoordinator", "reentrant code while ending a lifetime extension");
        }
    }

    public void onCanceledLifetimeExtension(NotificationEntry notificationEntry) {
    }

    public void onStartedLifetimeExtension(NotificationEntry notificationEntry) {
    }
}
