package com.android.systemui.statusbar.notification.collection.notifcollection;

import android.app.NotificationChannel;
import android.os.UserHandle;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChannelChangedEvent extends NotifEvent {
    public final NotificationChannel channel;
    public final int modificationType;
    public final String pkgName;
    public final UserHandle user;

    public ChannelChangedEvent(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) {
        super("onNotificationChannelModified");
        this.pkgName = str;
        this.user = userHandle;
        this.channel = notificationChannel;
        this.modificationType = i;
    }

    @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifEvent
    public final void dispatchToListener(NotifCollectionListener notifCollectionListener) {
        notifCollectionListener.onNotificationChannelModified(this.pkgName, this.user, this.channel, this.modificationType);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChannelChangedEvent)) {
            return false;
        }
        ChannelChangedEvent channelChangedEvent = (ChannelChangedEvent) obj;
        return Intrinsics.areEqual(this.pkgName, channelChangedEvent.pkgName) && Intrinsics.areEqual(this.user, channelChangedEvent.user) && Intrinsics.areEqual(this.channel, channelChangedEvent.channel) && this.modificationType == channelChangedEvent.modificationType;
    }

    public final int hashCode() {
        return Integer.hashCode(this.modificationType) + ((this.channel.hashCode() + ((this.user.hashCode() + (this.pkgName.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "ChannelChangedEvent(pkgName=" + this.pkgName + ", user=" + this.user + ", channel=" + this.channel + ", modificationType=" + this.modificationType + ")";
    }
}
