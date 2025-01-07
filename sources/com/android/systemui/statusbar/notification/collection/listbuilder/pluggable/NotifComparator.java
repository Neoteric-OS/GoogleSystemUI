package com.android.systemui.statusbar.notification.collection.listbuilder.pluggable;

import com.android.systemui.statusbar.notification.collection.ListEntry;
import java.util.Comparator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotifComparator extends Pluggable implements Comparator {
    @Override // java.util.Comparator
    public abstract int compare(ListEntry listEntry, ListEntry listEntry2);
}
