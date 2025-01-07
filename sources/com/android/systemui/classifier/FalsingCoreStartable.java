package com.android.systemui.classifier;

import com.android.systemui.CoreStartable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FalsingCoreStartable implements CoreStartable {
    public final FalsingCollector falsingCollector;

    public FalsingCoreStartable(FalsingCollector falsingCollector) {
        this.falsingCollector = falsingCollector;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.falsingCollector.init();
    }
}
