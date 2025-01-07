package com.android.systemui.statusbar.notification.stack;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplaySwitchNotificationsHiderTracker$trackNotificationHideTime$2 implements FlowCollector {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DisplaySwitchNotificationsHiderTracker this$0;

    public /* synthetic */ DisplaySwitchNotificationsHiderTracker$trackNotificationHideTime$2(DisplaySwitchNotificationsHiderTracker displaySwitchNotificationsHiderTracker, int i) {
        this.$r8$classId = i;
        this.this$0 = displaySwitchNotificationsHiderTracker;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                boolean booleanValue = ((Boolean) obj).booleanValue();
                DisplaySwitchNotificationsHiderTracker displaySwitchNotificationsHiderTracker = this.this$0;
                if (booleanValue) {
                    displaySwitchNotificationsHiderTracker.latencyTracker.onActionStart(26);
                } else {
                    displaySwitchNotificationsHiderTracker.latencyTracker.onActionEnd(26);
                }
                break;
            default:
                boolean booleanValue2 = ((Boolean) obj).booleanValue();
                DisplaySwitchNotificationsHiderTracker displaySwitchNotificationsHiderTracker2 = this.this$0;
                if (booleanValue2) {
                    displaySwitchNotificationsHiderTracker2.latencyTracker.onActionStart(27);
                } else {
                    displaySwitchNotificationsHiderTracker2.latencyTracker.onActionEnd(27);
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
