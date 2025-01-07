package com.android.systemui.dreams.conditions;

import android.app.DreamManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.shared.condition.Condition;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamCondition extends Condition {
    public final DreamManager mDreamManager;
    public final KeyguardUpdateMonitorCallback mUpdateCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.dreams.conditions.DreamCondition.1
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onDreamingStateChanged(boolean z) {
            DreamCondition.this.updateCondition(z);
        }
    };
    public final KeyguardUpdateMonitor mUpdateMonitor;

    public DreamCondition(DreamManager dreamManager, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.mDreamManager = dreamManager;
        this.mUpdateMonitor = keyguardUpdateMonitor;
    }

    @Override // com.android.systemui.shared.condition.Condition
    public final void start() {
        this.mUpdateMonitor.registerCallback(this.mUpdateCallback);
        updateCondition(this.mDreamManager.isDreaming());
    }

    @Override // com.android.systemui.shared.condition.Condition
    public final void stop() {
        this.mUpdateMonitor.removeCallback(this.mUpdateCallback);
    }
}
