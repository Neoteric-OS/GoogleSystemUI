package com.android.systemui.statusbar.notification.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GroupEntry extends ListEntry {
    public static final GroupEntry ROOT_ENTRY = new GroupEntry(0, "<root>");
    public final List mChildren;
    public NotificationEntry mSummary;
    public final List mUnmodifiableChildren;

    public GroupEntry(long j, String str) {
        super(j, str);
        ArrayList arrayList = new ArrayList();
        this.mChildren = arrayList;
        this.mUnmodifiableChildren = Collections.unmodifiableList(arrayList);
    }

    @Override // com.android.systemui.statusbar.notification.collection.ListEntry
    public final NotificationEntry getRepresentativeEntry() {
        return this.mSummary;
    }
}
