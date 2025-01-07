package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DismissibilityCoordinator implements Coordinator {
    public final KeyguardStateController keyguardStateController;
    public final NotificationDismissibilityProviderImpl provider;

    public DismissibilityCoordinator(KeyguardStateController keyguardStateController, NotificationDismissibilityProviderImpl notificationDismissibilityProviderImpl) {
        this.keyguardStateController = keyguardStateController;
        this.provider = notificationDismissibilityProviderImpl;
    }

    public static boolean markNonDismissibleEntries(Set set, List list, boolean z) {
        Iterator it = list.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            ListEntry listEntry = (ListEntry) it.next();
            NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
            if (representativeEntry != null && (representativeEntry.mSbn.isNonDismissable() || (representativeEntry.mSbn.isOngoing() && z))) {
                set.add(representativeEntry.mKey);
                z2 = true;
            }
            if (listEntry instanceof GroupEntry) {
                GroupEntry groupEntry = (GroupEntry) listEntry;
                if (markNonDismissibleEntries(set, groupEntry.mUnmodifiableChildren, z)) {
                    NotificationEntry notificationEntry = groupEntry.mSummary;
                    if (notificationEntry != null) {
                        set.add(notificationEntry.mKey);
                    }
                    z2 = true;
                }
            }
        }
        return z2;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addOnBeforeRenderListListener(new OnBeforeRenderListListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.DismissibilityCoordinator$attach$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener
            public final void onBeforeRenderList$1(List list) {
                DismissibilityCoordinator dismissibilityCoordinator = DismissibilityCoordinator.this;
                boolean z = !dismissibilityCoordinator.keyguardStateController.isUnlocked();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                DismissibilityCoordinator.markNonDismissibleEntries(linkedHashSet, list, z);
                NotificationDismissibilityProviderImpl notificationDismissibilityProviderImpl = dismissibilityCoordinator.provider;
                synchronized (notificationDismissibilityProviderImpl) {
                    notificationDismissibilityProviderImpl.nonDismissableEntryKeys = CollectionsKt.toSet(linkedHashSet);
                }
            }
        });
    }
}
