package com.android.systemui.statusbar.notification.collection.coordinator;

import android.util.ArrayMap;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderGroupListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener;
import com.android.systemui.statusbar.notification.collection.render.NotifViewController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GroupCountCoordinator implements Coordinator {
    public final ArrayMap untruncatedChildCounts = new ArrayMap();

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addOnBeforeFinalizeFilterListener(new OnBeforeFinalizeFilterListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GroupCountCoordinator$attach$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener
            public final void onBeforeFinalizeFilter(List list) {
                GroupCountCoordinator groupCountCoordinator = GroupCountCoordinator.this;
                groupCountCoordinator.untruncatedChildCounts.clear();
                FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(SequencesKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(list), new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GroupCountCoordinator$onBeforeFinalizeFilter$$inlined$filterIsInstance$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(obj instanceof GroupEntry);
                    }
                }));
                while (filteringSequence$iterator$1.hasNext()) {
                    GroupEntry groupEntry = (GroupEntry) filteringSequence$iterator$1.next();
                    groupCountCoordinator.untruncatedChildCounts.put(groupEntry, Integer.valueOf(groupEntry.mUnmodifiableChildren.size()));
                }
            }
        });
        notifPipeline.mRenderStageManager.onAfterRenderGroupListeners.add(new OnAfterRenderGroupListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.GroupCountCoordinator$attach$2
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderGroupListener
            public final void onAfterRenderGroup(GroupEntry groupEntry, NotifViewController notifViewController) {
                Integer num = (Integer) GroupCountCoordinator.this.untruncatedChildCounts.get(groupEntry);
                if (num == null) {
                    throw new IllegalStateException(("No untruncated child count for group: " + groupEntry.mKey).toString());
                }
                int intValue = num.intValue();
                ExpandableNotificationRow expandableNotificationRow = ((ExpandableNotificationRowController) notifViewController).mView;
                if (!expandableNotificationRow.mIsSummaryWithChildren) {
                    Log.w("NotifRowController", "Called setUntruncatedChildCount(" + intValue + ") on a leaf row");
                    return;
                }
                if (expandableNotificationRow.mChildrenContainer == null) {
                    expandableNotificationRow.mChildrenContainerStub.inflate();
                }
                NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow.mChildrenContainer;
                notificationChildrenContainer.mUntruncatedChildCount = intValue;
                notificationChildrenContainer.updateGroupOverflow();
            }
        });
    }
}
