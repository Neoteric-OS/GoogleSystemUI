package com.android.systemui.statusbar.notification.interruption;

import com.android.internal.logging.UiEventLogger;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.NotificationInterruptStateProviderImpl;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class VisualInterruptionFilter implements VisualInterruptionSuppressor {
    public final boolean isSpammy;
    public final String reason;
    public final Set types;
    public final UiEventLogger.UiEventEnum uiEventId;

    public VisualInterruptionFilter(Set set, String str, NotificationInterruptStateProviderImpl.NotificationInterruptEvent notificationInterruptEvent, int i) {
        notificationInterruptEvent = (i & 4) != 0 ? null : notificationInterruptEvent;
        boolean z = (i & 16) == 0;
        this.types = set;
        this.reason = str;
        this.uiEventId = notificationInterruptEvent;
        this.isSpammy = z;
    }

    public abstract boolean shouldSuppress(NotificationEntry notificationEntry);

    public VisualInterruptionFilter(String str, Set set) {
        this(set, str, null, 24);
    }
}
