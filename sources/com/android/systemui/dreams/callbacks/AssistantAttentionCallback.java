package com.android.systemui.dreams.callbacks;

import android.util.Log;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.shared.condition.Monitor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AssistantAttentionCallback implements Monitor.Callback {
    public final DreamOverlayStateController mStateController;

    public AssistantAttentionCallback(DreamOverlayStateController dreamOverlayStateController) {
        this.mStateController = dreamOverlayStateController;
    }

    @Override // com.android.systemui.shared.condition.Monitor.Callback
    public final void onConditionsChanged(boolean z) {
        if (Log.isLoggable("AssistAttentionCallback", 3)) {
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("onConditionChanged:", "AssistAttentionCallback", z);
        }
        this.mStateController.setHasAssistantAttention(z);
    }
}
