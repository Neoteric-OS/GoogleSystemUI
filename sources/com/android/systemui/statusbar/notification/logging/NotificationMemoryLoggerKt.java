package com.android.systemui.statusbar.notification.logging;

import com.android.systemui.statusbar.notification.logging.NotificationMemoryLogger;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationMemoryLoggerKt {
    public static final Map aggregateMemoryUsageData(List list) {
        Object obj;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            NotificationMemoryUsage notificationMemoryUsage = (NotificationMemoryUsage) it.next();
            Pair pair = new Pair(notificationMemoryUsage.packageName, Integer.valueOf(notificationMemoryUsage.objectUsage.style));
            Object obj2 = linkedHashMap.get(pair);
            boolean z = obj2 == null && !linkedHashMap.containsKey(pair);
            NotificationMemoryLogger.NotificationMemoryUseAtomBuilder notificationMemoryUseAtomBuilder = (NotificationMemoryLogger.NotificationMemoryUseAtomBuilder) obj2;
            if (z) {
                notificationMemoryUseAtomBuilder = new NotificationMemoryLogger.NotificationMemoryUseAtomBuilder(notificationMemoryUsage.uid, notificationMemoryUsage.objectUsage.style);
            } else {
                Intrinsics.checkNotNull(notificationMemoryUseAtomBuilder);
            }
            notificationMemoryUseAtomBuilder.count++;
            if (!notificationMemoryUsage.viewUsage.isEmpty()) {
                notificationMemoryUseAtomBuilder.countWithInflatedViews++;
            }
            int i = notificationMemoryUseAtomBuilder.smallIconObject;
            NotificationObjectUsage notificationObjectUsage = notificationMemoryUsage.objectUsage;
            int i2 = notificationObjectUsage.smallIcon;
            notificationMemoryUseAtomBuilder.smallIconObject = i + i2;
            if (i2 > 0) {
                notificationMemoryUseAtomBuilder.smallIconBitmapCount++;
            }
            int i3 = notificationMemoryUseAtomBuilder.largeIconObject;
            int i4 = notificationObjectUsage.largeIcon;
            notificationMemoryUseAtomBuilder.largeIconObject = i3 + i4;
            if (i4 > 0) {
                notificationMemoryUseAtomBuilder.largeIconBitmapCount++;
            }
            int i5 = notificationMemoryUseAtomBuilder.bigPictureObject;
            int i6 = notificationObjectUsage.bigPicture;
            notificationMemoryUseAtomBuilder.bigPictureObject = i5 + i6;
            if (i6 > 0) {
                notificationMemoryUseAtomBuilder.bigPictureBitmapCount++;
            }
            notificationMemoryUseAtomBuilder.extras += notificationObjectUsage.extras;
            notificationMemoryUseAtomBuilder.extenders += notificationObjectUsage.extender;
            Iterator it2 = notificationMemoryUsage.viewUsage.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (((NotificationViewUsage) obj).viewType == ViewType.TOTAL) {
                    break;
                }
            }
            NotificationViewUsage notificationViewUsage = (NotificationViewUsage) obj;
            if (notificationViewUsage != null) {
                notificationMemoryUseAtomBuilder.smallIconViews += notificationViewUsage.smallIcon;
                notificationMemoryUseAtomBuilder.largeIconViews += notificationViewUsage.largeIcon;
                notificationMemoryUseAtomBuilder.systemIconViews += notificationViewUsage.systemIcons;
                notificationMemoryUseAtomBuilder.styleViews += notificationViewUsage.style;
                notificationMemoryUseAtomBuilder.customViews += notificationViewUsage.customViews;
                notificationMemoryUseAtomBuilder.softwareBitmaps += notificationViewUsage.softwareBitmapsPenalty;
            }
            linkedHashMap.put(pair, notificationMemoryUseAtomBuilder);
        }
        return linkedHashMap;
    }
}
