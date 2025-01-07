package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.util.Log;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnpinNotifications extends Action {
    public boolean hasPinnedHeadsUp;
    public final UnpinNotifications$headsUpChangedListener$1 headsUpChangedListener;
    public final HeadsUpManager headsUpManager;
    public final SilenceAlertsDisabled silenceAlertsDisabled;
    public final String tag;

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.systemui.columbus.legacy.actions.UnpinNotifications$headsUpChangedListener$1] */
    public UnpinNotifications(Context context, SilenceAlertsDisabled silenceAlertsDisabled, Optional optional) {
        super(context, null);
        this.silenceAlertsDisabled = silenceAlertsDisabled;
        this.tag = "Columbus/UnpinNotif";
        HeadsUpManager headsUpManager = (HeadsUpManager) optional.orElse(null);
        this.headsUpManager = headsUpManager;
        this.headsUpChangedListener = new OnHeadsUpChangedListener() { // from class: com.google.android.systemui.columbus.legacy.actions.UnpinNotifications$headsUpChangedListener$1
            @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
            public final void onHeadsUpPinnedModeChanged(boolean z) {
                UnpinNotifications unpinNotifications = UnpinNotifications.this;
                unpinNotifications.hasPinnedHeadsUp = z;
                unpinNotifications.setAvailable(!unpinNotifications.silenceAlertsDisabled.isBlocking() && unpinNotifications.hasPinnedHeadsUp);
            }
        };
        Gate.Listener listener = new Gate.Listener() { // from class: com.google.android.systemui.columbus.legacy.actions.UnpinNotifications$gateListener$1
            @Override // com.google.android.systemui.columbus.legacy.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                UnpinNotifications unpinNotifications = UnpinNotifications.this;
                boolean isBlocking = unpinNotifications.silenceAlertsDisabled.isBlocking();
                UnpinNotifications$headsUpChangedListener$1 unpinNotifications$headsUpChangedListener$1 = unpinNotifications.headsUpChangedListener;
                HeadsUpManager headsUpManager2 = unpinNotifications.headsUpManager;
                if (isBlocking) {
                    if (headsUpManager2 != null) {
                        ((BaseHeadsUpManager) headsUpManager2).mListeners.remove(unpinNotifications$headsUpChangedListener$1);
                    }
                } else {
                    if (headsUpManager2 != null) {
                        ((BaseHeadsUpManager) headsUpManager2).addListener(unpinNotifications$headsUpChangedListener$1);
                    }
                    unpinNotifications.hasPinnedHeadsUp = headsUpManager2 != null ? ((BaseHeadsUpManager) headsUpManager2).mHasPinnedNotification : false;
                }
            }
        };
        if (headsUpManager == null) {
            Log.w("Columbus/UnpinNotif", "No HeadsUpManager");
        } else {
            silenceAlertsDisabled.registerListener(listener);
        }
        setAvailable(!silenceAlertsDisabled.isBlocking() && this.hasPinnedHeadsUp);
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        HeadsUpManager headsUpManager = this.headsUpManager;
        if (headsUpManager != null) {
            ((HeadsUpManagerPhone) headsUpManager).unpinAll();
        }
    }
}
