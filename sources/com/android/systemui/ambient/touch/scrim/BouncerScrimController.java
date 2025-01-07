package com.android.systemui.ambient.touch.scrim;

import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerScrimController implements ScrimController {
    public StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;

    @Override // com.android.systemui.ambient.touch.scrim.ScrimController
    public final void expand(ShadeExpansionChangeEvent shadeExpansionChangeEvent) {
        this.mStatusBarKeyguardViewManager.onPanelExpansionChanged(shadeExpansionChangeEvent);
    }

    @Override // com.android.systemui.ambient.touch.scrim.ScrimController
    public final void reset$1() {
        this.mStatusBarKeyguardViewManager.reset(false, false);
    }

    @Override // com.android.systemui.ambient.touch.scrim.ScrimController
    public final void show() {
        this.mStatusBarKeyguardViewManager.showPrimaryBouncer(false);
    }
}
