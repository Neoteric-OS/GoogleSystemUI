package com.android.systemui.doze;

import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.doze.DozeMachine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeFalsingManagerAdapter implements DozeMachine.Part {
    public final FalsingCollector mFalsingCollector;

    public DozeFalsingManagerAdapter(FalsingCollector falsingCollector) {
        this.mFalsingCollector = falsingCollector;
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void transitionTo(DozeMachine.State state, DozeMachine.State state2) {
        int ordinal = state2.ordinal();
        this.mFalsingCollector.setShowingAod(ordinal == 4 || ordinal == 10 || ordinal == 11);
    }
}
