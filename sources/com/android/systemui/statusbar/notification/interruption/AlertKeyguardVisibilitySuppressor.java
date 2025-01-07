package com.android.systemui.statusbar.notification.interruption;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AlertKeyguardVisibilitySuppressor extends VisualInterruptionFilter {
    public final KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProvider;

    public AlertKeyguardVisibilitySuppressor(KeyguardNotificationVisibilityProviderImpl keyguardNotificationVisibilityProviderImpl) {
        super("hidden on keyguard", SetsKt.setOf(VisualInterruptionType.PEEK, VisualInterruptionType.PULSE, VisualInterruptionType.BUBBLE));
        this.keyguardNotificationVisibilityProvider = keyguardNotificationVisibilityProviderImpl;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter
    public final boolean shouldSuppress(NotificationEntry notificationEntry) {
        return this.keyguardNotificationVisibilityProvider.shouldHideNotification(notificationEntry);
    }
}
