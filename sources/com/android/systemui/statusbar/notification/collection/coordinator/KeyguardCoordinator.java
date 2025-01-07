package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.provider.SectionHeaderVisibilityProvider;
import com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardCoordinator implements Coordinator {
    public final KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProvider;
    public final KeyguardCoordinator$notifFilter$1 notifFilter = new NotifFilter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinator$notifFilter$1
        {
            super("KeyguardCoordinator");
        }

        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            return KeyguardCoordinator.this.keyguardNotificationVisibilityProvider.shouldHideNotification(notificationEntry);
        }
    };
    public final SectionHeaderVisibilityProvider sectionHeaderVisibilityProvider;
    public final StatusBarStateController statusBarStateController;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinator$notifFilter$1] */
    public KeyguardCoordinator(KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProviderImpl, SectionHeaderVisibilityProvider sectionHeaderVisibilityProvider, StatusBarStateController statusBarStateController) {
        this.keyguardNotificationVisibilityProvider = keyguardNotificationVisibilityProviderImpl;
        this.sectionHeaderVisibilityProvider = sectionHeaderVisibilityProvider;
        this.statusBarStateController = statusBarStateController;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addFinalizeFilter(this.notifFilter);
        this.keyguardNotificationVisibilityProvider.onStateChangedListeners.addIfAbsent(new Consumer() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinator$attach$1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                String str = (String) obj;
                KeyguardCoordinator keyguardCoordinator = KeyguardCoordinator.this;
                boolean z = false;
                boolean z2 = keyguardCoordinator.statusBarStateController.getState() == 1;
                SectionHeaderVisibilityProvider sectionHeaderVisibilityProvider = keyguardCoordinator.sectionHeaderVisibilityProvider;
                boolean z3 = sectionHeaderVisibilityProvider.neverShowSectionHeaders;
                if (!z2 && !z3) {
                    z = true;
                }
                sectionHeaderVisibilityProvider.sectionHeadersVisible = z;
                keyguardCoordinator.notifFilter.invalidateList(str);
            }
        });
        boolean z = false;
        boolean z2 = this.statusBarStateController.getState() == 1;
        SectionHeaderVisibilityProvider sectionHeaderVisibilityProvider = this.sectionHeaderVisibilityProvider;
        boolean z3 = sectionHeaderVisibilityProvider.neverShowSectionHeaders;
        if (!z2 && !z3) {
            z = true;
        }
        sectionHeaderVisibilityProvider.sectionHeadersVisible = z;
    }
}
