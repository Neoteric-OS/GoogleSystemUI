package com.android.systemui.statusbar.notification.collection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ListEntry {
    public final long mCreationTime;
    public final String mKey;
    public final ListAttachState mPreviousAttachState = ListAttachState.create();
    public final ListAttachState mAttachState = ListAttachState.create();

    public ListEntry(long j, String str) {
        this.mKey = str;
        this.mCreationTime = j;
    }

    public final void beginNewAttachState() {
        ListAttachState listAttachState = this.mPreviousAttachState;
        listAttachState.getClass();
        ListAttachState listAttachState2 = this.mAttachState;
        listAttachState.parent = listAttachState2.parent;
        listAttachState.section = listAttachState2.section;
        listAttachState.excludingFilter = listAttachState2.excludingFilter;
        listAttachState.promoter = listAttachState2.promoter;
        listAttachState.groupPruneReason = listAttachState2.groupPruneReason;
        SuppressedAttachState suppressedAttachState = listAttachState.suppressedChanges;
        suppressedAttachState.getClass();
        SuppressedAttachState suppressedAttachState2 = listAttachState2.suppressedChanges;
        suppressedAttachState.parent = suppressedAttachState2.parent;
        suppressedAttachState.section = suppressedAttachState2.section;
        suppressedAttachState.wasPruneSuppressed = suppressedAttachState2.wasPruneSuppressed;
        listAttachState.stableIndex = listAttachState2.stableIndex;
        listAttachState2.parent = null;
        listAttachState2.section = null;
        listAttachState2.excludingFilter = null;
        listAttachState2.promoter = null;
        listAttachState2.groupPruneReason = null;
        SuppressedAttachState suppressedAttachState3 = listAttachState2.suppressedChanges;
        suppressedAttachState3.parent = null;
        suppressedAttachState3.section = null;
        suppressedAttachState3.wasPruneSuppressed = false;
        listAttachState2.stableIndex = -1;
    }

    public String getKey() {
        return this.mKey;
    }

    public abstract NotificationEntry getRepresentativeEntry();
}
