package com.android.systemui.statusbar.disableflags;

import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda7;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisableStateTracker implements CommandQueue.Callbacks {
    public final KeyguardStatusBarViewController$$ExternalSyntheticLambda7 callback;
    public Integer displayId;
    public boolean isDisabled;

    public DisableStateTracker(KeyguardStatusBarViewController$$ExternalSyntheticLambda7 keyguardStatusBarViewController$$ExternalSyntheticLambda7) {
        this.callback = keyguardStatusBarViewController$$ExternalSyntheticLambda7;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void disable(int i, int i2, int i3, boolean z) {
        Integer num = this.displayId;
        if (num == null || i != num.intValue()) {
            return;
        }
        boolean z2 = ((1048576 & i2) == 0 && (i3 & 2) == 0) ? false : true;
        if (this.isDisabled == z2) {
            return;
        }
        this.isDisabled = z2;
        this.callback.f$0.updateViewState();
    }
}
