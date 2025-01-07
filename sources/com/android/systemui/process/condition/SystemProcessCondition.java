package com.android.systemui.process.condition;

import com.android.systemui.process.ProcessWrapper;
import com.android.systemui.shared.condition.Condition;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SystemProcessCondition extends Condition {
    public final ProcessWrapper mProcessWrapper;

    public SystemProcessCondition(ProcessWrapper processWrapper) {
        this.mProcessWrapper = processWrapper;
    }

    @Override // com.android.systemui.shared.condition.Condition
    public final void start() {
        this.mProcessWrapper.getClass();
        updateCondition(ProcessWrapper.isSystemUser());
    }

    @Override // com.android.systemui.shared.condition.Condition
    public final void stop() {
    }
}
