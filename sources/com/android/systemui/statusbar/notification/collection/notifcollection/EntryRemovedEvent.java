package com.android.systemui.statusbar.notification.collection.notifcollection;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EntryRemovedEvent extends NotifEvent {
    public final NotificationEntry entry;
    public final int reason;

    public EntryRemovedEvent(NotificationEntry notificationEntry, int i) {
        super(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("onEntryRemoved ", NotifCollectionLoggerKt.cancellationReasonDebugString(i)));
        this.entry = notificationEntry;
        this.reason = i;
    }

    @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifEvent
    public final void dispatchToListener(NotifCollectionListener notifCollectionListener) {
        notifCollectionListener.onEntryRemoved(this.entry, this.reason);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntryRemovedEvent)) {
            return false;
        }
        EntryRemovedEvent entryRemovedEvent = (EntryRemovedEvent) obj;
        return Intrinsics.areEqual(this.entry, entryRemovedEvent.entry) && this.reason == entryRemovedEvent.reason;
    }

    public final int hashCode() {
        return Integer.hashCode(this.reason) + (this.entry.hashCode() * 31);
    }

    public final String toString() {
        return "EntryRemovedEvent(entry=" + this.entry + ", reason=" + this.reason + ")";
    }
}
