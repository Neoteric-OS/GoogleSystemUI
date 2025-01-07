package com.android.systemui.statusbar.pipeline.shared;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.wm.shell.R;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConnectivityConstants implements Dumpable {
    public final boolean hasDataCapabilities;
    public final boolean shouldShowActivityConfig;

    public ConnectivityConstants(Context context, DumpManager dumpManager, TelephonyManager telephonyManager) {
        dumpManager.registerNormalDumpable("ConnectivityConstants", this);
        this.hasDataCapabilities = telephonyManager.isDataCapable();
        this.shouldShowActivityConfig = context.getResources().getBoolean(R.bool.config_showActivity);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("hasDataCapabilities="), this.hasDataCapabilities, printWriter, "shouldShowActivityConfig="), this.shouldShowActivityConfig, printWriter);
    }
}
