package com.android.systemui.statusbar.pipeline.wifi.shared;

import android.content.Context;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.wm.shell.R;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiConstants implements Dumpable {
    public final boolean alwaysShowIconIfEnabled;

    public WifiConstants(Context context, DumpManager dumpManager) {
        dumpManager.registerNormalDumpable("WifiConstants", this);
        this.alwaysShowIconIfEnabled = context.getResources().getBoolean(R.bool.config_showWifiIndicatorWhenEnabled);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("alwaysShowIconIfEnabled="), this.alwaysShowIconIfEnabled, printWriter);
    }
}
