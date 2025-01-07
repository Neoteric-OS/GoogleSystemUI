package com.android.systemui.statusbar.notification.logging;

import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.dump.DumpsysTableLogger;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationMemoryDumper implements Dumpable {
    public final DumpManager dumpManager;
    public final NotifPipeline notificationPipeline;

    public NotificationMemoryDumper(DumpManager dumpManager, NotifPipeline notifPipeline) {
        this.dumpManager = dumpManager;
        this.notificationPipeline = notifPipeline;
    }

    public static String toKb(int i) {
        return i == 0 ? "--" : String.format("%.2f KB", Arrays.copyOf(new Object[]{Float.valueOf(i / 1024.0f)}, 1));
    }

    @Override // com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        Object obj;
        String str;
        List<NotificationMemoryUsage> sortedWith = CollectionsKt.sortedWith(SequencesKt.toList(new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(this.notificationPipeline.getAllNotifs()), NotificationMemoryMeter$notificationMemoryUse$1.INSTANCE)), ComparisonsKt___ComparisonsJvmKt.compareBy(new Function1() { // from class: com.android.systemui.statusbar.notification.logging.NotificationMemoryDumper$dump$memoryUse$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return ((NotificationMemoryUsage) obj2).packageName;
            }
        }, new Function1() { // from class: com.android.systemui.statusbar.notification.logging.NotificationMemoryDumper$dump$memoryUse$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return ((NotificationMemoryUsage) obj2).notificationKey;
            }
        }));
        List listOf = CollectionsKt__CollectionsKt.listOf("Package", "Small Icon", "Large Icon", "Style", "Style Icon", "Big Picture", "Extender", "Extras", "Custom View", "Key");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(sortedWith, 10));
        for (NotificationMemoryUsage notificationMemoryUsage : sortedWith) {
            String str2 = notificationMemoryUsage.packageName;
            NotificationObjectUsage notificationObjectUsage = notificationMemoryUsage.objectUsage;
            String kb = toKb(notificationObjectUsage.smallIcon);
            String kb2 = toKb(notificationObjectUsage.largeIcon);
            int i = notificationObjectUsage.style;
            if (i != -1000) {
                switch (i) {
                    case 0:
                        str = "None";
                        break;
                    case 1:
                        str = "BigPicture";
                        break;
                    case 2:
                        str = "BigText";
                        break;
                    case 3:
                        str = "Call";
                        break;
                    case 4:
                        str = "DCustomView";
                        break;
                    case 5:
                        str = "Inbox";
                        break;
                    case 6:
                        str = "Media";
                        break;
                    case 7:
                        str = "Messaging";
                        break;
                    case 8:
                        str = "RankerGroup";
                        break;
                    default:
                        str = "Unknown";
                        break;
                }
            } else {
                str = "Unspecified";
            }
            arrayList.add(CollectionsKt__CollectionsKt.listOf(str2, kb, kb2, str, toKb(notificationObjectUsage.styleIcon), toKb(notificationObjectUsage.bigPicture), toKb(notificationObjectUsage.extender), toKb(notificationObjectUsage.extras), String.valueOf(notificationObjectUsage.hasCustomView), notificationMemoryUsage.notificationKey.replace('|', (char) 9474)));
        }
        Iterator it = sortedWith.iterator();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (it.hasNext()) {
            NotificationObjectUsage notificationObjectUsage2 = ((NotificationMemoryUsage) it.next()).objectUsage;
            i3 += notificationObjectUsage2.smallIcon;
            i4 += notificationObjectUsage2.largeIcon;
            i5 += notificationObjectUsage2.styleIcon;
            i6 += notificationObjectUsage2.bigPicture;
            i7 += notificationObjectUsage2.extender;
            i8 += notificationObjectUsage2.extras;
        }
        new DumpsysTableLogger("Notification Object Usage", listOf, CollectionsKt.plus((Iterable) Collections.singletonList(CollectionsKt__CollectionsKt.listOf("TOTALS", toKb(i3), toKb(i4), "", toKb(i5), toKb(i6), toKb(i7), toKb(i8), "", "")), (Collection) arrayList)).printTableData(printWriter);
        List listOf2 = CollectionsKt__CollectionsKt.listOf("Package", "View Type", "Small Icon", "Large Icon", "Style Use", "Custom View", "Software Bitmaps", "Key");
        ArrayList<NotificationMemoryUsage> arrayList2 = new ArrayList();
        for (Object obj2 : sortedWith) {
            if (!((NotificationMemoryUsage) obj2).viewUsage.isEmpty()) {
                arrayList2.add(obj2);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (NotificationMemoryUsage notificationMemoryUsage2 : arrayList2) {
            List<NotificationViewUsage> list = notificationMemoryUsage2.viewUsage;
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (NotificationViewUsage notificationViewUsage : list) {
                arrayList4.add(CollectionsKt__CollectionsKt.listOf(notificationMemoryUsage2.packageName, notificationViewUsage.viewType.toString(), toKb(notificationViewUsage.smallIcon), toKb(notificationViewUsage.largeIcon), toKb(notificationViewUsage.style), toKb(notificationViewUsage.customViews), toKb(notificationViewUsage.softwareBitmapsPenalty), notificationMemoryUsage2.notificationKey.replace('|', (char) 9474)));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList4, arrayList3);
        }
        ArrayList arrayList5 = new ArrayList();
        for (Object obj3 : sortedWith) {
            if (!((NotificationMemoryUsage) obj3).viewUsage.isEmpty()) {
                arrayList5.add(obj3);
            }
        }
        ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList5, 10));
        Iterator it2 = arrayList5.iterator();
        while (it2.hasNext()) {
            Iterator it3 = ((NotificationMemoryUsage) it2.next()).viewUsage.iterator();
            while (true) {
                if (it3.hasNext()) {
                    obj = it3.next();
                    if (((NotificationViewUsage) obj).viewType == ViewType.TOTAL) {
                        break;
                    }
                } else {
                    obj = null;
                }
            }
            arrayList6.add((NotificationViewUsage) obj);
        }
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        for (NotificationViewUsage notificationViewUsage2 : CollectionsKt.filterNotNull(arrayList6)) {
            i2 += notificationViewUsage2.smallIcon;
            i9 += notificationViewUsage2.largeIcon;
            i10 += notificationViewUsage2.style;
            i11 += notificationViewUsage2.customViews;
            i12 += notificationViewUsage2.softwareBitmapsPenalty;
        }
        new DumpsysTableLogger("Notification View Usage", listOf2, CollectionsKt.plus((Iterable) Collections.singletonList(CollectionsKt__CollectionsKt.listOf("TOTALS", "", toKb(i2), toKb(i9), toKb(i10), toKb(i11), toKb(i12), "")), (Collection) arrayList3)).printTableData(printWriter);
    }
}
