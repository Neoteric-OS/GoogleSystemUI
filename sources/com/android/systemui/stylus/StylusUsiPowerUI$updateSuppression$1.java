package com.android.systemui.stylus;

import android.os.Build;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StylusUsiPowerUI$updateSuppression$1 implements Runnable {
    public final /* synthetic */ boolean $suppress;
    public final /* synthetic */ StylusUsiPowerUI this$0;

    public StylusUsiPowerUI$updateSuppression$1(StylusUsiPowerUI stylusUsiPowerUI, boolean z) {
        this.this$0 = stylusUsiPowerUI;
        this.$suppress = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.this$0.suppressed == this.$suppress) {
            return;
        }
        boolean z = Build.IS_DEBUGGABLE;
        Reflection.getOrCreateKotlinClass(StylusUsiPowerUI.class).getSimpleName();
        StylusUsiPowerUI stylusUsiPowerUI = this.this$0;
        stylusUsiPowerUI.suppressed = this.$suppress;
        stylusUsiPowerUI.handler.post(new StylusUsiPowerUI$refresh$1(stylusUsiPowerUI));
    }
}
