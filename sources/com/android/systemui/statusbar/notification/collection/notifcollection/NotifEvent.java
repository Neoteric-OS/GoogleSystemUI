package com.android.systemui.statusbar.notification.collection.notifcollection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotifEvent {
    public final String traceName;

    public NotifEvent(String str) {
        this.traceName = str;
    }

    public abstract void dispatchToListener(NotifCollectionListener notifCollectionListener);
}
