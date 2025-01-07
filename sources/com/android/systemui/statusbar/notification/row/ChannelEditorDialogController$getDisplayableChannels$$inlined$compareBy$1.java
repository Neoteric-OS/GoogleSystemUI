package com.android.systemui.statusbar.notification.row;

import android.app.NotificationChannel;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChannelEditorDialogController$getDisplayableChannels$$inlined$compareBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        String id;
        String id2;
        NotificationChannel notificationChannel = (NotificationChannel) obj;
        CharSequence name = notificationChannel.getName();
        if (name == null || (id = name.toString()) == null) {
            id = notificationChannel.getId();
        }
        NotificationChannel notificationChannel2 = (NotificationChannel) obj2;
        CharSequence name2 = notificationChannel2.getName();
        if (name2 == null || (id2 = name2.toString()) == null) {
            id2 = notificationChannel2.getId();
        }
        return ComparisonsKt___ComparisonsJvmKt.compareValues(id, id2);
    }
}
