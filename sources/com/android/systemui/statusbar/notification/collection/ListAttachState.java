package com.android.systemui.statusbar.notification.collection;

import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ListAttachState {
    public NotifFilter excludingFilter;
    public String groupPruneReason;
    public GroupEntry parent;
    public NotifPromoter promoter;
    public NotifSection section;
    public int stableIndex;
    public SuppressedAttachState suppressedChanges;

    public static final ListAttachState create() {
        SuppressedAttachState suppressedAttachState = new SuppressedAttachState();
        suppressedAttachState.section = null;
        suppressedAttachState.parent = null;
        suppressedAttachState.wasPruneSuppressed = false;
        ListAttachState listAttachState = new ListAttachState();
        listAttachState.parent = null;
        listAttachState.section = null;
        listAttachState.excludingFilter = null;
        listAttachState.promoter = null;
        listAttachState.groupPruneReason = null;
        listAttachState.suppressedChanges = suppressedAttachState;
        listAttachState.stableIndex = -1;
        return listAttachState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListAttachState)) {
            return false;
        }
        ListAttachState listAttachState = (ListAttachState) obj;
        return Intrinsics.areEqual(this.parent, listAttachState.parent) && Intrinsics.areEqual(this.section, listAttachState.section) && Intrinsics.areEqual(this.excludingFilter, listAttachState.excludingFilter) && Intrinsics.areEqual(this.promoter, listAttachState.promoter) && Intrinsics.areEqual(this.groupPruneReason, listAttachState.groupPruneReason) && Intrinsics.areEqual(this.suppressedChanges, listAttachState.suppressedChanges);
    }

    public final int hashCode() {
        GroupEntry groupEntry = this.parent;
        int hashCode = (groupEntry == null ? 0 : groupEntry.hashCode()) * 31;
        NotifSection notifSection = this.section;
        int hashCode2 = (hashCode + (notifSection == null ? 0 : notifSection.hashCode())) * 31;
        NotifFilter notifFilter = this.excludingFilter;
        int hashCode3 = (hashCode2 + (notifFilter == null ? 0 : notifFilter.hashCode())) * 31;
        NotifPromoter notifPromoter = this.promoter;
        int hashCode4 = (hashCode3 + (notifPromoter == null ? 0 : notifPromoter.hashCode())) * 31;
        String str = this.groupPruneReason;
        return this.suppressedChanges.hashCode() + ((hashCode4 + (str != null ? str.hashCode() : 0)) * 31);
    }

    public final String toString() {
        return "ListAttachState(parent=" + this.parent + ", section=" + this.section + ", excludingFilter=" + this.excludingFilter + ", promoter=" + this.promoter + ", groupPruneReason=" + this.groupPruneReason + ", suppressedChanges=" + this.suppressedChanges + ")";
    }
}
