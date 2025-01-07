package com.android.systemui.statusbar.notification.collection;

import android.os.Trace;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.internal.util.Preconditions;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.statusbar.NotificationInteractionTracker;
import com.android.systemui.statusbar.notification.NotifPipelineFlags;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.collection.listbuilder.PipelineState;
import com.android.systemui.statusbar.notification.collection.listbuilder.SemiStableSort;
import com.android.systemui.statusbar.notification.collection.listbuilder.ShadeListBuilderLogger;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.DefaultNotifStabilityManager;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.Pluggable;
import com.android.systemui.statusbar.notification.collection.render.RenderStageManager$attach$1;
import com.android.systemui.util.Assert;
import com.android.systemui.util.NamedListenerSet;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeListBuilder implements Dumpable, PipelineDumpable {
    public static final AnonymousClass2 DEFAULT_SECTIONER = new AnonymousClass2("UnknownSection", 0);
    public static final int MAX_CONSECUTIVE_REENTRANT_REBUILDS = 3;
    public Collection mAllEntries;
    public final NotifPipelineChoreographerImpl mChoreographer;
    public int mConsecutiveReentrantRebuilds;
    public final DumpManager mDumpManager;
    public final ShadeListBuilder$$ExternalSyntheticLambda2 mGroupChildrenComparator;
    public final Map mGroups;
    public final NotificationInteractionTracker mInteractionTracker;
    public int mIterationCount;
    public final ShadeListBuilderLogger mLogger;
    public final List mNotifComparators;
    public final List mNotifFinalizeFilters;
    public final List mNotifPreGroupFilters;
    public final List mNotifPromoters;
    public final List mNotifSections;
    public VisualStabilityCoordinator.AnonymousClass1 mNotifStabilityManager;
    public final NamedListenerSet mOnBeforeFinalizeFilterListeners;
    public final NamedListenerSet mOnBeforeRenderListListeners;
    public final NamedListenerSet mOnBeforeSortListeners;
    public final NamedListenerSet mOnBeforeTransformGroupsListeners;
    public RenderStageManager$attach$1 mOnRenderListListener;
    public Collection mPendingEntries;
    public final PipelineState mPipelineState;
    public List mReadOnlyNewNotifList;
    public List mReadOnlyNotifList;
    public final AnonymousClass1 mReadyForBuildListener;
    public final SystemClock mSystemClock;
    public final ShadeListBuilder$$ExternalSyntheticLambda1 mTopLevelComparator;
    public final ArrayList mTempSectionMembers = new ArrayList();
    public List mNotifList = new ArrayList();
    public List mNewNotifList = new ArrayList();
    public final SemiStableSort mSemiStableSort = new SemiStableSort();
    public final ShadeListBuilder$$ExternalSyntheticLambda0 mStableOrder = new ShadeListBuilder$$ExternalSyntheticLambda0(this, 0);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.ShadeListBuilder$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.ShadeListBuilder$2, reason: invalid class name */
    public final class AnonymousClass2 extends NotifSectioner {
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
        public final boolean isInSection(ListEntry listEntry) {
            return true;
        }
    }

    /* JADX WARN: Type inference failed for: r0v23, types: [com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda1] */
    public ShadeListBuilder(DumpManager dumpManager, NotifPipelineChoreographerImpl notifPipelineChoreographerImpl, NotifPipelineFlags notifPipelineFlags, NotificationInteractionTracker notificationInteractionTracker, ShadeListBuilderLogger shadeListBuilderLogger, SystemClock systemClock) {
        PipelineState pipelineState = new PipelineState();
        pipelineState.mState = 0;
        this.mPipelineState = pipelineState;
        this.mGroups = new ArrayMap();
        this.mAllEntries = Collections.emptyList();
        this.mPendingEntries = null;
        this.mIterationCount = 0;
        this.mNotifPreGroupFilters = new ArrayList();
        this.mNotifPromoters = new ArrayList();
        this.mNotifFinalizeFilters = new ArrayList();
        this.mNotifComparators = new ArrayList();
        this.mNotifSections = new ArrayList();
        this.mOnBeforeTransformGroupsListeners = new NamedListenerSet();
        this.mOnBeforeSortListeners = new NamedListenerSet();
        this.mOnBeforeFinalizeFilterListeners = new NamedListenerSet();
        this.mOnBeforeRenderListListeners = new NamedListenerSet();
        this.mReadOnlyNotifList = Collections.unmodifiableList(this.mNotifList);
        this.mReadOnlyNewNotifList = Collections.unmodifiableList(this.mNewNotifList);
        this.mConsecutiveReentrantRebuilds = 0;
        this.mReadyForBuildListener = new AnonymousClass1();
        this.mTopLevelComparator = new Comparator() { // from class: com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                ShadeListBuilder shadeListBuilder = ShadeListBuilder.this;
                ListEntry listEntry = (ListEntry) obj;
                ListEntry listEntry2 = (ListEntry) obj2;
                shadeListBuilder.getClass();
                NotifSection notifSection = listEntry.mAttachState.section;
                int i = notifSection != null ? notifSection.index : -1;
                NotifSection notifSection2 = listEntry2.mAttachState.section;
                int compare2 = Integer.compare(i, notifSection2 != null ? notifSection2.index : -1);
                if (compare2 != 0) {
                    return compare2;
                }
                NotifSection notifSection3 = listEntry.mAttachState.section;
                if (notifSection3 != listEntry2.mAttachState.section) {
                    throw new RuntimeException("Entry ordering should only be done within sections");
                }
                NotifComparator notifComparator = notifSection3 != null ? notifSection3.comparator : null;
                if (notifComparator != null && (compare = notifComparator.compare(listEntry, listEntry2)) != 0) {
                    return compare;
                }
                for (int i2 = 0; i2 < ((ArrayList) shadeListBuilder.mNotifComparators).size(); i2++) {
                    int compare3 = ((NotifComparator) ((ArrayList) shadeListBuilder.mNotifComparators).get(i2)).compare(listEntry, listEntry2);
                    if (compare3 != 0) {
                        return compare3;
                    }
                }
                int compare4 = Integer.compare(listEntry.getRepresentativeEntry().mRanking.getRank(), listEntry2.getRepresentativeEntry().mRanking.getRank());
                return compare4 != 0 ? compare4 : Long.compare(listEntry.getRepresentativeEntry().mSbn.getNotification().getWhen(), listEntry2.getRepresentativeEntry().mSbn.getNotification().getWhen()) * (-1);
            }
        };
        this.mGroupChildrenComparator = new ShadeListBuilder$$ExternalSyntheticLambda2();
        this.mSystemClock = systemClock;
        this.mLogger = shadeListBuilderLogger;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        notifPipelineFlags.featureFlags.getClass();
        this.mInteractionTracker = notificationInteractionTracker;
        this.mChoreographer = notifPipelineChoreographerImpl;
        this.mDumpManager = dumpManager;
        setSectioners(Collections.emptyList());
    }

    public static void annulAddition(ListEntry listEntry, List list) {
        GroupEntry groupEntry = listEntry.mAttachState.parent;
        if (groupEntry == null) {
            throw new IllegalStateException("Cannot nullify addition of " + listEntry.getKey() + ": no parent.");
        }
        if (groupEntry == GroupEntry.ROOT_ENTRY && list.contains(listEntry)) {
            throw new IllegalStateException("Cannot nullify addition of " + listEntry.getKey() + ": it's still in the shade list.");
        }
        if (listEntry instanceof GroupEntry) {
            GroupEntry groupEntry2 = (GroupEntry) listEntry;
            NotificationEntry notificationEntry = groupEntry2.mSummary;
            String str = groupEntry2.mKey;
            if (notificationEntry != null) {
                throw new IllegalStateException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Cannot nullify group ", str, ": summary is not null"));
            }
            if (!groupEntry2.mUnmodifiableChildren.isEmpty()) {
                throw new IllegalStateException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Cannot nullify group ", str, ": still has children"));
            }
        } else if (listEntry instanceof NotificationEntry) {
            GroupEntry groupEntry3 = listEntry.mAttachState.parent;
            if (listEntry == groupEntry3.mSummary || groupEntry3.mUnmodifiableChildren.contains(listEntry)) {
                throw new IllegalStateException("Cannot nullify addition of child " + listEntry.getKey() + ": it's still attached to its parent.");
            }
        }
        annulAddition(listEntry);
    }

    public static boolean applyFilters(NotificationEntry notificationEntry, long j, List list) {
        NotifFilter notifFilter;
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                notifFilter = null;
                break;
            }
            notifFilter = (NotifFilter) arrayList.get(i);
            if (notifFilter.shouldFilterOut(notificationEntry, j)) {
                break;
            }
            i++;
        }
        notificationEntry.mAttachState.excludingFilter = notifFilter;
        if (notifFilter != null) {
            notificationEntry.initializationTime = -1L;
        }
        return notifFilter != null;
    }

    public static void callOnCleanup(List list) {
        for (int i = 0; i < list.size(); i++) {
            ((Pluggable) list.get(i)).onCleanup();
        }
    }

    public static boolean isSorted(List list, Comparator comparator) {
        if (list.size() <= 1) {
            return true;
        }
        Iterator it = list.iterator();
        Object next = it.next();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public final void applyNewNotifList() {
        this.mNotifList.clear();
        List list = this.mNotifList;
        this.mNotifList = this.mNewNotifList;
        this.mNewNotifList = list;
        List list2 = this.mReadOnlyNotifList;
        this.mReadOnlyNotifList = this.mReadOnlyNewNotifList;
        this.mReadOnlyNewNotifList = list2;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("\tShadeListBuilder shade notifications:");
        Assert.isMainThread();
        PipelineState pipelineState = this.mPipelineState;
        pipelineState.requireState();
        if (this.mReadOnlyNotifList.size() == 0) {
            printWriter.println("\t\t None");
        }
        Assert.isMainThread();
        pipelineState.requireState();
        List list = this.mReadOnlyNotifList;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            ListEntry listEntry = (ListEntry) list.get(i);
            String num = Integer.toString(i);
            String logKey = NotificationUtils.logKey(listEntry);
            NotificationInteractionTracker notificationInteractionTracker = this.mInteractionTracker;
            Boolean bool = (Boolean) notificationInteractionTracker.interactions.get(logKey);
            ListDumper.dumpEntry(listEntry, num, "\t\t", sb, true, bool != null ? bool.booleanValue() : false);
            if (listEntry instanceof GroupEntry) {
                GroupEntry groupEntry = (GroupEntry) listEntry;
                NotificationEntry notificationEntry = groupEntry.mSummary;
                if (notificationEntry != null) {
                    String str = i + ":*";
                    Boolean bool2 = (Boolean) notificationInteractionTracker.interactions.get(NotificationUtils.logKey(notificationEntry));
                    ListDumper.dumpEntry(notificationEntry, str, "\t\t  ", sb, true, bool2 != null ? bool2.booleanValue() : false);
                }
                List list2 = groupEntry.mUnmodifiableChildren;
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    NotificationEntry notificationEntry2 = (NotificationEntry) list2.get(i2);
                    String str2 = i + "." + i2;
                    Boolean bool3 = (Boolean) notificationInteractionTracker.interactions.get(NotificationUtils.logKey(notificationEntry2));
                    ListDumper.dumpEntry(notificationEntry2, str2, "\t\t  ", sb, true, bool3 != null ? bool3.booleanValue() : false);
                }
            }
        }
        printWriter.println(sb.toString());
    }

    @Override // com.android.systemui.statusbar.notification.collection.PipelineDumpable
    public final void dumpPipeline(PipelineDumper pipelineDumper) {
        pipelineDumper.dump(this.mChoreographer, "choreographer");
        pipelineDumper.dump(this.mNotifPreGroupFilters, "notifPreGroupFilters");
        pipelineDumper.dump(this.mOnBeforeTransformGroupsListeners, "onBeforeTransformGroupsListeners");
        pipelineDumper.dump(this.mNotifPromoters, "notifPromoters");
        pipelineDumper.dump(this.mOnBeforeSortListeners, "onBeforeSortListeners");
        pipelineDumper.dump(this.mNotifSections, "notifSections");
        pipelineDumper.dump(this.mNotifComparators, "notifComparators");
        pipelineDumper.dump(this.mOnBeforeFinalizeFilterListeners, "onBeforeFinalizeFilterListeners");
        pipelineDumper.dump(this.mNotifFinalizeFilters, "notifFinalizeFilters");
        pipelineDumper.dump(this.mOnBeforeRenderListListeners, "onBeforeRenderListListeners");
        pipelineDumper.dump(this.mOnRenderListListener, "onRenderListListener");
    }

    public final void filterNotifs(Collection collection, List list, List list2) {
        Trace.beginSection("ShadeListBuilder.filterNotifs");
        ((SystemClockImpl) this.mSystemClock).getClass();
        long uptimeMillis = android.os.SystemClock.uptimeMillis();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            ListEntry listEntry = (ListEntry) it.next();
            if (listEntry instanceof GroupEntry) {
                GroupEntry groupEntry = (GroupEntry) listEntry;
                NotificationEntry notificationEntry = groupEntry.mSummary;
                if (applyFilters(notificationEntry, uptimeMillis, list2)) {
                    groupEntry.mSummary = null;
                    annulAddition(notificationEntry);
                }
                ArrayList arrayList = (ArrayList) groupEntry.mChildren;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    NotificationEntry notificationEntry2 = (NotificationEntry) arrayList.get(size);
                    if (applyFilters(notificationEntry2, uptimeMillis, list2)) {
                        arrayList.remove(notificationEntry2);
                        annulAddition(notificationEntry2);
                    }
                }
                list.add(groupEntry);
            } else if (applyFilters((NotificationEntry) listEntry, uptimeMillis, list2)) {
                annulAddition(listEntry);
            } else {
                list.add(listEntry);
            }
        }
        Trace.endSection();
    }

    public final NotifStabilityManager getStabilityManager() {
        VisualStabilityCoordinator.AnonymousClass1 anonymousClass1 = this.mNotifStabilityManager;
        return anonymousClass1 == null ? DefaultNotifStabilityManager.INSTANCE : anonymousClass1;
    }

    public final void logAttachStateChanges(ListEntry listEntry) {
        NotifSection notifSection;
        NotifSection notifSection2;
        NotifPromoter notifPromoter;
        NotifPromoter notifPromoter2;
        ListAttachState listAttachState = listEntry.mAttachState;
        ListAttachState listAttachState2 = listEntry.mPreviousAttachState;
        if (Objects.equals(listAttachState, listAttachState2)) {
            return;
        }
        int i = this.mIterationCount;
        GroupEntry groupEntry = listAttachState2.parent;
        GroupEntry groupEntry2 = listAttachState.parent;
        ShadeListBuilderLogger shadeListBuilderLogger = this.mLogger;
        shadeListBuilderLogger.logEntryAttachStateChanged(i, listEntry, groupEntry, groupEntry2);
        GroupEntry groupEntry3 = listAttachState.parent;
        GroupEntry groupEntry4 = listAttachState2.parent;
        if (groupEntry3 != groupEntry4) {
            shadeListBuilderLogger.logParentChanged(this.mIterationCount, groupEntry4, groupEntry3);
        }
        SuppressedAttachState suppressedAttachState = listAttachState.suppressedChanges;
        GroupEntry groupEntry5 = suppressedAttachState.parent;
        GroupEntry groupEntry6 = listAttachState2.suppressedChanges.parent;
        if (groupEntry5 != null && (groupEntry6 == null || !groupEntry6.mKey.equals(groupEntry5.mKey))) {
            shadeListBuilderLogger.logParentChangeSuppressedStarted(this.mIterationCount, groupEntry5, listAttachState.parent);
        }
        if (groupEntry6 != null && groupEntry5 == null) {
            shadeListBuilderLogger.logParentChangeSuppressedStopped(this.mIterationCount, groupEntry6, listAttachState2.parent);
        }
        NotifSection notifSection3 = suppressedAttachState.section;
        if (notifSection3 != null) {
            shadeListBuilderLogger.logSectionChangeSuppressed(this.mIterationCount, notifSection3, listAttachState.section);
        }
        if (suppressedAttachState.wasPruneSuppressed) {
            shadeListBuilderLogger.logGroupPruningSuppressed(this.mIterationCount, listAttachState.parent);
        }
        if (!Objects.equals(listAttachState.groupPruneReason, listAttachState2.groupPruneReason)) {
            shadeListBuilderLogger.logPrunedReasonChanged(listAttachState2.groupPruneReason, listAttachState.groupPruneReason, this.mIterationCount);
        }
        NotifFilter notifFilter = listAttachState.excludingFilter;
        NotifFilter notifFilter2 = listAttachState2.excludingFilter;
        if (notifFilter != notifFilter2) {
            shadeListBuilderLogger.logFilterChanged(this.mIterationCount, notifFilter2, notifFilter);
        }
        boolean z = listAttachState.parent == null && listAttachState2.parent != null;
        if (!z && (notifPromoter = listAttachState.promoter) != (notifPromoter2 = listAttachState2.promoter)) {
            shadeListBuilderLogger.logPromoterChanged(this.mIterationCount, notifPromoter2, notifPromoter);
        }
        if (z || (notifSection = listAttachState.section) == (notifSection2 = listAttachState2.section)) {
            return;
        }
        shadeListBuilderLogger.logSectionChanged(this.mIterationCount, notifSection2, notifSection);
    }

    public final boolean maybeSuppressGroupChange(NotificationEntry notificationEntry, List list) {
        ListAttachState listAttachState;
        GroupEntry groupEntry;
        GroupEntry groupEntry2 = notificationEntry.mPreviousAttachState.parent;
        if (groupEntry2 == null || groupEntry2 == (groupEntry = (listAttachState = notificationEntry.mAttachState).parent)) {
            return false;
        }
        GroupEntry groupEntry3 = GroupEntry.ROOT_ENTRY;
        if ((groupEntry2 != groupEntry3 && groupEntry2.mAttachState.parent == null) || getStabilityManager().isGroupChangeAllowed(notificationEntry)) {
            return false;
        }
        listAttachState.suppressedChanges.parent = groupEntry;
        listAttachState.parent = groupEntry2;
        if (groupEntry2 == groupEntry3) {
            list.add(notificationEntry);
            return true;
        }
        groupEntry2.mChildren.add(notificationEntry);
        ArrayMap arrayMap = (ArrayMap) this.mGroups;
        String str = groupEntry2.mKey;
        if (arrayMap.containsKey(str)) {
            return true;
        }
        ((ArrayMap) this.mGroups).put(str, groupEntry2);
        return true;
    }

    public final void pruneGroupAtIndexAndPromoteAnyChildren(List list, GroupEntry groupEntry, int i) {
        String str;
        int i2 = 0;
        Preconditions.checkState(((ListEntry) list.remove(i)) == groupEntry);
        List list2 = groupEntry.mChildren;
        NotificationEntry notificationEntry = groupEntry.mSummary;
        boolean z = notificationEntry != null;
        PipelineState pipelineState = this.mPipelineState;
        if (z) {
            groupEntry.mSummary = null;
            annulAddition(notificationEntry, list);
            notificationEntry.mAttachState.groupPruneReason = "SUMMARY with too few children @ " + PipelineState.getStateName(pipelineState.mState);
        }
        if (!list2.isEmpty()) {
            if (z) {
                str = "CHILD with " + (((ArrayList) list2).size() - 1) + " siblings @ " + PipelineState.getStateName(pipelineState.mState);
            } else {
                str = "CHILD with no summary @ " + PipelineState.getStateName(pipelineState.mState);
            }
            while (true) {
                ArrayList arrayList = (ArrayList) list2;
                if (i2 >= arrayList.size()) {
                    break;
                }
                NotificationEntry notificationEntry2 = (NotificationEntry) arrayList.get(i2);
                GroupEntry groupEntry2 = GroupEntry.ROOT_ENTRY;
                ListAttachState listAttachState = notificationEntry2.mAttachState;
                listAttachState.parent = groupEntry2;
                Objects.requireNonNull(str);
                listAttachState.groupPruneReason = str;
                i2++;
            }
            list.addAll(i, list2);
            list2.clear();
        }
        annulAddition(groupEntry, list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.util.ArraySet] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.Collection, java.util.Set] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.android.systemui.statusbar.notification.collection.ShadeListBuilder] */
    public final void pruneIncompleteGroups(List list) {
        ?? arraySet;
        ArrayList arrayList;
        Trace.beginSection("ShadeListBuilder.pruneIncompleteGroups");
        if (!getStabilityManager().isEveryChangeAllowed()) {
            arraySet = new ArraySet();
            int i = 0;
            while (true) {
                ArrayList arrayList2 = (ArrayList) list;
                if (i >= arrayList2.size()) {
                    break;
                }
                GroupEntry groupEntry = ((ListEntry) arrayList2.get(i)).mAttachState.suppressedChanges.parent;
                if (groupEntry != null) {
                    arraySet.add(groupEntry.mKey);
                }
                i++;
            }
        } else {
            arraySet = Collections.emptySet();
        }
        ArraySet arraySet2 = new ArraySet((Collection) arraySet);
        for (ListEntry listEntry : this.mAllEntries) {
            StatusBarNotification statusBarNotification = listEntry.getRepresentativeEntry().mSbn;
            if (statusBarNotification.isGroup() && !statusBarNotification.getNotification().isGroupSummary() && listEntry.mAttachState.excludingFilter != null) {
                arraySet2.add(statusBarNotification.getGroupKey());
            }
        }
        int i2 = 0;
        while (true) {
            arrayList = (ArrayList) list;
            if (i2 >= arrayList.size()) {
                break;
            }
            ListEntry listEntry2 = (ListEntry) arrayList.get(i2);
            if (listEntry2.mAttachState.promoter != null) {
                arraySet2.add(listEntry2.getRepresentativeEntry().mSbn.getGroupKey());
            }
            i2++;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ListEntry listEntry3 = (ListEntry) arrayList.get(size);
            if (listEntry3 instanceof GroupEntry) {
                GroupEntry groupEntry2 = (GroupEntry) listEntry3;
                List list2 = groupEntry2.mChildren;
                boolean z = groupEntry2.mSummary != null;
                String str = groupEntry2.mKey;
                if (z && ((ArrayList) list2).size() == 0) {
                    if (arraySet2.contains(str)) {
                        pruneGroupAtIndexAndPromoteAnyChildren(arrayList, groupEntry2, size);
                    } else {
                        Preconditions.checkArgument(groupEntry2.mUnmodifiableChildren.isEmpty(), "group should have no children");
                        NotificationEntry notificationEntry = groupEntry2.mSummary;
                        notificationEntry.mAttachState.parent = GroupEntry.ROOT_ENTRY;
                        Preconditions.checkState(((ListEntry) arrayList.set(size, notificationEntry)) == groupEntry2);
                        groupEntry2.mSummary = null;
                        annulAddition(groupEntry2, arrayList);
                        notificationEntry.mAttachState.groupPruneReason = "SUMMARY with no children @ " + PipelineState.getStateName(this.mPipelineState.mState);
                    }
                } else if (z) {
                    if (((ArrayList) list2).size() < 2) {
                        Preconditions.checkState(z, "group must have summary at this point");
                        Preconditions.checkState(!r6.isEmpty(), "empty group should have been promoted");
                        boolean contains = arraySet.contains(str);
                        ListAttachState listAttachState = groupEntry2.mAttachState;
                        if (contains) {
                            listAttachState.suppressedChanges.wasPruneSuppressed = true;
                        } else if (groupEntry2.mPreviousAttachState.parent == null || getStabilityManager().isGroupPruneAllowed()) {
                            pruneGroupAtIndexAndPromoteAnyChildren(arrayList, groupEntry2, size);
                        } else {
                            Preconditions.checkState(!r6.isEmpty(), "empty group should have been pruned");
                            listAttachState.suppressedChanges.wasPruneSuppressed = true;
                        }
                    }
                } else {
                    pruneGroupAtIndexAndPromoteAnyChildren(arrayList, groupEntry2, size);
                }
            }
        }
        Trace.endSection();
    }

    public final void rebuildListIfBefore(int i) {
        int i2 = this.mPipelineState.mState;
        if (i2 == 0) {
            scheduleRebuild(i, false);
        } else {
            if (i > i2) {
                return;
            }
            scheduleRebuild(i, true);
        }
    }

    public final void scheduleRebuild(int i, boolean z) {
        NotifPipelineChoreographerImpl notifPipelineChoreographerImpl = this.mChoreographer;
        if (!z) {
            this.mConsecutiveReentrantRebuilds = 0;
            notifPipelineChoreographerImpl.schedule();
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException(MotionLayout$$ExternalSyntheticOutline0.m("Reentrant notification pipeline rebuild of state ", PipelineState.getStateName(i), " while pipeline in state ", PipelineState.getStateName(this.mPipelineState.mState), "."));
        int i2 = this.mConsecutiveReentrantRebuilds + 1;
        this.mConsecutiveReentrantRebuilds = i2;
        if (i2 > 3) {
            Log.e("ShadeListBuilder", "Crashing after more than 3 consecutive reentrant notification pipeline rebuilds.", illegalStateException);
            throw illegalStateException;
        }
        Log.wtf("ShadeListBuilder", "Allowing " + this.mConsecutiveReentrantRebuilds + " consecutive reentrant notification pipeline rebuild(s).", illegalStateException);
        notifPipelineChoreographerImpl.schedule();
    }

    public final void setSectioners(List list) {
        Assert.isMainThread();
        this.mPipelineState.requireState();
        this.mNotifSections.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            NotifSectioner notifSectioner = (NotifSectioner) it.next();
            NotifSection notifSection = new NotifSection(notifSectioner, ((ArrayList) this.mNotifSections).size());
            this.mNotifSections.add(notifSection);
            notifSectioner.mListener = new ShadeListBuilder$$ExternalSyntheticLambda0(this, 2);
            NotifComparator notifComparator = notifSection.comparator;
            if (notifComparator != null) {
                notifComparator.mListener = new ShadeListBuilder$$ExternalSyntheticLambda0(this, 3);
            }
        }
        ArrayList arrayList = (ArrayList) this.mNotifSections;
        arrayList.add(new NotifSection(DEFAULT_SECTIONER, arrayList.size()));
        ArraySet arraySet = new ArraySet();
        int i = ((ArrayList) this.mNotifSections).size() > 0 ? ((NotifSection) ((ArrayList) this.mNotifSections).get(0)).bucket : 0;
        for (NotifSection notifSection2 : this.mNotifSections) {
            int i2 = notifSection2.bucket;
            if (i != i2 && arraySet.contains(Integer.valueOf(i2))) {
                throw new IllegalStateException(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("setSectioners with non contiguous sections "), notifSection2.label, " has an already seen bucket"));
            }
            i = notifSection2.bucket;
            arraySet.add(Integer.valueOf(i));
        }
    }

    public static void annulAddition(ListEntry listEntry) {
        ListAttachState listAttachState = listEntry.mAttachState;
        listAttachState.parent = null;
        listAttachState.section = null;
        listAttachState.promoter = null;
        listAttachState.stableIndex = -1;
    }
}
