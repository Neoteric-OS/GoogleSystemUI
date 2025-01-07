package com.android.systemui.statusbar.notification.interruption;

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.Collections;
import java.util.Optional;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PeekAlreadyBubbledSuppressor extends VisualInterruptionFilter {
    public final Optional bubbles;
    public final StatusBarStateController statusBarStateController;

    public PeekAlreadyBubbledSuppressor(StatusBarStateController statusBarStateController, Optional optional) {
        super("already bubbled", Collections.singleton(VisualInterruptionType.PEEK));
        this.statusBarStateController = statusBarStateController;
        this.bubbles = optional;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter
    public final boolean shouldSuppress(NotificationEntry notificationEntry) {
        if (this.statusBarStateController.getState() != 0) {
            return false;
        }
        Optional map = this.bubbles.map(PeekAlreadyBubbledSuppressor$shouldSuppress$bubblesCanShowNotification$1.INSTANCE);
        Boolean bool = (Boolean) (map.isPresent() ? map.get() : Boolean.FALSE);
        if (!notificationEntry.isBubble()) {
            return false;
        }
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue();
    }
}
