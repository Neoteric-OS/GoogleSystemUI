package com.android.systemui.statusbar.notification.collection.coalescer;

import java.util.Comparator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class GroupCoalescer$$ExternalSyntheticLambda0 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        CoalescedEvent coalescedEvent = (CoalescedEvent) obj;
        CoalescedEvent coalescedEvent2 = (CoalescedEvent) obj2;
        int compare = Boolean.compare(coalescedEvent2.sbn.getNotification().isGroupSummary(), coalescedEvent.sbn.getNotification().isGroupSummary());
        return compare == 0 ? coalescedEvent.position - coalescedEvent2.position : compare;
    }
}
