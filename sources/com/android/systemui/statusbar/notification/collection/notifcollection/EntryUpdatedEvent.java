package com.android.systemui.statusbar.notification.collection.notifcollection;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EntryUpdatedEvent extends NotifEvent {
    public final NotificationEntry entry;
    public final boolean fromSystem;

    public EntryUpdatedEvent(NotificationEntry notificationEntry, boolean z) {
        super(z ? "onEntryUpdated" : "onEntryUpdated fromSystem=true");
        this.entry = notificationEntry;
        this.fromSystem = z;
    }

    @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifEvent
    public final void dispatchToListener(NotifCollectionListener notifCollectionListener) {
        notifCollectionListener.onEntryUpdated(this.entry, this.fromSystem);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntryUpdatedEvent)) {
            return false;
        }
        EntryUpdatedEvent entryUpdatedEvent = (EntryUpdatedEvent) obj;
        return this.entry.equals(entryUpdatedEvent.entry) && this.fromSystem == entryUpdatedEvent.fromSystem;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.fromSystem) + (this.entry.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("EntryUpdatedEvent(entry=");
        sb.append(this.entry);
        sb.append(", fromSystem=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.fromSystem, ")");
    }
}
