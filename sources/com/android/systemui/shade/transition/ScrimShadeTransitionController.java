package com.android.systemui.shade.transition;

import com.android.systemui.dump.DumpManager;
import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.statusbar.phone.ScrimController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScrimShadeTransitionController {
    public Integer currentPanelState;
    public final DumpManager dumpManager;
    public ShadeExpansionChangeEvent lastExpansionEvent;
    public Float lastExpansionFraction;
    public final ScrimController scrimController;
    public final ShadeExpansionStateManager shadeExpansionStateManager;

    public ScrimShadeTransitionController(ShadeExpansionStateManager shadeExpansionStateManager, DumpManager dumpManager, ScrimController scrimController) {
        this.shadeExpansionStateManager = shadeExpansionStateManager;
        this.dumpManager = dumpManager;
        this.scrimController = scrimController;
    }

    public final void onStateChanged() {
        ShadeExpansionChangeEvent shadeExpansionChangeEvent = this.lastExpansionEvent;
        if (shadeExpansionChangeEvent == null) {
            return;
        }
        ScrimController scrimController = this.scrimController;
        scrimController.getClass();
        float f = shadeExpansionChangeEvent.fraction;
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException("rawPanelExpansionFraction should not be NaN");
        }
        scrimController.mRawPanelExpansionFraction = f;
        scrimController.calculateAndUpdatePanelExpansion();
        this.lastExpansionFraction = Float.valueOf(f);
    }
}
