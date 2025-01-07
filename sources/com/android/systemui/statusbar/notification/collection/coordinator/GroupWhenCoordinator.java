package com.android.systemui.statusbar.notification.collection.coordinator;

import android.util.ArrayMap;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderGroupListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener;
import com.android.systemui.statusbar.notification.collection.render.NotifViewController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.util.Assert;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GroupWhenCoordinator implements Coordinator {
    public ExecutorImpl.ExecutionToken cancelInvalidateListRunnable;
    public final DelayableExecutor delayableExecutor;
    public final SystemClock systemClock;
    public final GroupWhenCoordinator$invalidator$1 invalidator = new GroupWhenCoordinator$invalidator$1("GroupWhenCoordinator");
    public final ArrayMap notificationGroupTimes = new ArrayMap();
    public final GroupWhenCoordinator$invalidateListRunnable$1 invalidateListRunnable = new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator$invalidateListRunnable$1
        @Override // java.lang.Runnable
        public final void run() {
            GroupWhenCoordinator.this.invalidator.invalidateList("future notification invalidation");
        }
    };

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator$invalidateListRunnable$1] */
    public GroupWhenCoordinator(DelayableExecutor delayableExecutor, SystemClock systemClock) {
        this.delayableExecutor = delayableExecutor;
        this.systemClock = systemClock;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addOnBeforeFinalizeFilterListener(new OnBeforeFinalizeFilterListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator$attach$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener
            public final void onBeforeFinalizeFilter(List list) {
                long j;
                GroupWhenCoordinator groupWhenCoordinator = GroupWhenCoordinator.this;
                ExecutorImpl.ExecutionToken executionToken = groupWhenCoordinator.cancelInvalidateListRunnable;
                if (executionToken != null) {
                    executionToken.run();
                }
                groupWhenCoordinator.cancelInvalidateListRunnable = null;
                groupWhenCoordinator.notificationGroupTimes.clear();
                ((SystemClockImpl) groupWhenCoordinator.systemClock).getClass();
                long currentTimeMillis = System.currentTimeMillis();
                FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(SequencesKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(list), new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator$onBeforeFinalizeFilterListener$$inlined$filterIsInstance$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(obj instanceof GroupEntry);
                    }
                }));
                long j2 = Long.MAX_VALUE;
                while (filteringSequence$iterator$1.hasNext()) {
                    GroupEntry groupEntry = (GroupEntry) filteringSequence$iterator$1.next();
                    FilteringSequence$iterator$1 filteringSequence$iterator$12 = new FilteringSequence$iterator$1(SequencesKt.mapNotNull(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(groupEntry.mUnmodifiableChildren), new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator$calculateGroupNotificationTime$1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            long when = ((NotificationEntry) obj).mSbn.getNotification().getWhen();
                            Long valueOf = Long.valueOf(when);
                            if (when > 0) {
                                return valueOf;
                            }
                            return null;
                        }
                    }));
                    long j3 = Long.MAX_VALUE;
                    long j4 = Long.MIN_VALUE;
                    while (filteringSequence$iterator$12.hasNext()) {
                        long longValue = ((Number) filteringSequence$iterator$12.next()).longValue();
                        if (currentTimeMillis - longValue > 0) {
                            j4 = Math.max(j4, longValue);
                        } else {
                            j3 = Math.min(j3, longValue);
                        }
                    }
                    if (j4 == Long.MIN_VALUE && j3 == Long.MAX_VALUE) {
                        NotificationEntry notificationEntry = groupEntry.mSummary;
                        if (notificationEntry == null) {
                            throw new IllegalStateException("Required value was null.");
                        }
                        j = notificationEntry.mCreationTime;
                    } else {
                        if (j3 != Long.MAX_VALUE) {
                            j4 = j3;
                        }
                        j = j4;
                    }
                    groupWhenCoordinator.notificationGroupTimes.put(groupEntry, Long.valueOf(j));
                    if (j > currentTimeMillis) {
                        j2 = Math.min(j2, j);
                    }
                }
                if (j2 != Long.MAX_VALUE) {
                    groupWhenCoordinator.cancelInvalidateListRunnable = groupWhenCoordinator.delayableExecutor.executeDelayed(groupWhenCoordinator.invalidateListRunnable, j2 - currentTimeMillis);
                }
            }
        });
        notifPipeline.mRenderStageManager.onAfterRenderGroupListeners.add(new OnAfterRenderGroupListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator$attach$2
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderGroupListener
            public final void onAfterRenderGroup(GroupEntry groupEntry, NotifViewController notifViewController) {
                NotificationViewWrapper notificationViewWrapper;
                Long l = (Long) GroupWhenCoordinator.this.notificationGroupTimes.get(groupEntry);
                if (l != null) {
                    long longValue = l.longValue();
                    ExpandableNotificationRow expandableNotificationRow = ((ExpandableNotificationRowController) notifViewController).mView;
                    boolean z = expandableNotificationRow.mIsSummaryWithChildren;
                    if (!z) {
                        Log.w("NotifRowController", "Called setNotificationTime(" + longValue + ") on a leaf row");
                        return;
                    }
                    if (!z) {
                        Log.w("ExpandableNotifRow", "setNotificationGroupWhen( whenMillis: " + longValue + ") mIsSummaryWithChildren: false mChildrenContainer has not been inflated yet.");
                        return;
                    }
                    NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow.mChildrenContainer;
                    NotificationHeaderViewWrapper notificationHeaderViewWrapper = notificationChildrenContainer.mGroupHeaderWrapper;
                    if (notificationHeaderViewWrapper != null) {
                        notificationHeaderViewWrapper.setNotificationWhen(longValue);
                    }
                    NotificationHeaderViewWrapper notificationHeaderViewWrapper2 = notificationChildrenContainer.mMinimizedGroupHeaderWrapper;
                    if (notificationHeaderViewWrapper2 != null) {
                        notificationHeaderViewWrapper2.setNotificationWhen(longValue);
                    }
                    NotificationContentView notificationContentView = expandableNotificationRow.mPublicLayout;
                    if ((notificationContentView.mContractedChild == null || (notificationViewWrapper = notificationContentView.mContractedWrapper) == null) && ((notificationContentView.mExpandedChild == null || (notificationViewWrapper = notificationContentView.mExpandedWrapper) == null) && (notificationContentView.mHeadsUpChild == null || (notificationViewWrapper = notificationContentView.mHeadsUpWrapper) == null))) {
                        notificationViewWrapper = null;
                    }
                    if (notificationViewWrapper instanceof NotificationHeaderViewWrapper) {
                        ((NotificationHeaderViewWrapper) notificationViewWrapper).setNotificationWhen(longValue);
                    }
                }
            }
        });
        GroupWhenCoordinator$invalidator$1 groupWhenCoordinator$invalidator$1 = this.invalidator;
        ShadeListBuilder shadeListBuilder = notifPipeline.mShadeListBuilder;
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        groupWhenCoordinator$invalidator$1.mListener = new ShadeListBuilder$$ExternalSyntheticLambda0(shadeListBuilder, 4);
    }
}
