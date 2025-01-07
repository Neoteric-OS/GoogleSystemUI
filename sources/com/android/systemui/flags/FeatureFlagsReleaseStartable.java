package com.android.systemui.flags;

import com.android.systemui.CoreStartable;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FeatureFlagsReleaseStartable implements CoreStartable {
    public FeatureFlagsReleaseStartable(DumpManager dumpManager, final FeatureFlags featureFlags) {
        dumpManager.registerCriticalDumpable("SysUIFlags", new Dumpable() { // from class: com.android.systemui.flags.FeatureFlagsReleaseStartable.1
            @Override // com.android.systemui.Dumpable
            public final void dump(PrintWriter printWriter, String[] strArr) {
                ((FeatureFlagsClassicRelease) FeatureFlags.this).dump(printWriter, strArr);
            }
        });
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
