package com.android.systemui.toast;

import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.plugins.ToastPlugin;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ToastFactory implements Dumpable {
    public ToastPlugin mPlugin;

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "ToastFactory:", "    mAttachedPlugin=");
        m.append(this.mPlugin);
        printWriter.println(m.toString());
    }
}
