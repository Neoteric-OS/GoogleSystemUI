package com.android.systemui.statusbar.notification.collection.coordinator;

import android.os.Trace;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderListListener;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.collection.render.NotifStats;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.RenderNotificationListInteractor;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionControllerImpl;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StackCoordinator implements Coordinator {
    public final ActiveNotificationsInteractor activeNotificationsInteractor;
    public final GroupExpansionManagerImpl groupExpansionManagerImpl;
    public final RenderNotificationListInteractor renderListInteractor;
    public final SensitiveNotificationProtectionController sensitiveNotificationProtectionController;

    public StackCoordinator(GroupExpansionManagerImpl groupExpansionManagerImpl, RenderNotificationListInteractor renderNotificationListInteractor, ActiveNotificationsInteractor activeNotificationsInteractor, SensitiveNotificationProtectionController sensitiveNotificationProtectionController) {
        this.groupExpansionManagerImpl = groupExpansionManagerImpl;
        this.renderListInteractor = renderNotificationListInteractor;
        this.activeNotificationsInteractor = activeNotificationsInteractor;
        this.sensitiveNotificationProtectionController = sensitiveNotificationProtectionController;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.mRenderStageManager.onAfterRenderListListeners.add(new OnAfterRenderListListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.StackCoordinator$attach$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderListListener
            public final void onAfterRenderList(List list) {
                StackCoordinator stackCoordinator = StackCoordinator.this;
                stackCoordinator.getClass();
                boolean isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("StackCoordinator.onAfterRenderList");
                }
                try {
                    NotifStats calculateNotifStats = stackCoordinator.calculateNotifStats(list);
                    StateFlowImpl stateFlowImpl = stackCoordinator.activeNotificationsInteractor.repository.notifStats;
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, calculateNotifStats);
                    stackCoordinator.renderListInteractor.setRenderedList(list);
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
            }
        });
        GroupExpansionManagerImpl groupExpansionManagerImpl = this.groupExpansionManagerImpl;
        groupExpansionManagerImpl.mDumpManager.registerDumpable(groupExpansionManagerImpl);
        notifPipeline.addOnBeforeRenderListListener(groupExpansionManagerImpl.mNotifTracker);
    }

    public final NotifStats calculateNotifStats(List list) {
        boolean isSensitiveStateActive = ((SensitiveNotificationProtectionControllerImpl) this.sensitiveNotificationProtectionController).isSensitiveStateActive();
        Iterator it = list.iterator();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (it.hasNext()) {
            ListEntry listEntry = (ListEntry) it.next();
            NotifSection notifSection = listEntry.mAttachState.section;
            if (notifSection == null) {
                throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Null section for ", listEntry.getKey()).toString());
            }
            NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
            if (representativeEntry == null) {
                throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Null notif entry for ", listEntry.getKey()).toString());
            }
            boolean z5 = notifSection.bucket == 6;
            boolean z6 = (isSensitiveStateActive || !representativeEntry.isClearable() || ((Boolean) representativeEntry.mSensitive.getValue()).booleanValue()) ? false : true;
            if (z5 && z6) {
                z4 = true;
            } else if (z5 && !z6) {
                z3 = true;
            } else if (!z5 && z6) {
                z2 = true;
            } else if (!z5 && !z6) {
                z = true;
            }
        }
        return new NotifStats(list.size(), z, z2, z3, z4);
    }
}
