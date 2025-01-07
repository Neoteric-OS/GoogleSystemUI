package com.android.systemui.statusbar;

import android.content.Context;
import android.util.IndentingPrintWriter;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockscreenShadeScrimTransitionController extends AbstractLockscreenShadeTransitionController {
    public float notificationsScrimDragAmount;
    public float notificationsScrimProgress;
    public int notificationsScrimTransitionDelay;
    public int notificationsScrimTransitionDistance;
    public final ScrimController scrimController;
    public float scrimProgress;
    public int scrimTransitionDistance;

    public LockscreenShadeScrimTransitionController(ScrimController scrimController, Context context, ConfigurationController configurationController, DumpManager dumpManager, SplitShadeStateControllerImpl splitShadeStateControllerImpl) {
        super(context, configurationController, dumpManager, splitShadeStateControllerImpl);
        this.scrimController = scrimController;
    }

    @Override // com.android.systemui.statusbar.AbstractLockscreenShadeTransitionController
    public final void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.println("LockscreenShadeScrimTransitionController:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("Resources:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("scrimTransitionDistance: " + this.scrimTransitionDistance);
        indentingPrintWriter.println("notificationsScrimTransitionDelay: " + this.notificationsScrimTransitionDelay);
        indentingPrintWriter.println("notificationsScrimTransitionDistance: " + this.notificationsScrimTransitionDistance);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("State");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("dragDownAmount: " + this.dragDownAmount);
        indentingPrintWriter.println("scrimProgress: " + this.scrimProgress);
        indentingPrintWriter.println("notificationsScrimProgress: " + this.notificationsScrimProgress);
        indentingPrintWriter.println("notificationsScrimDragAmount: " + this.notificationsScrimDragAmount);
    }

    @Override // com.android.systemui.statusbar.AbstractLockscreenShadeTransitionController
    public final void updateResources() {
        this.scrimTransitionDistance = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_scrim_transition_distance);
        this.notificationsScrimTransitionDelay = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_notifications_scrim_transition_delay);
        this.notificationsScrimTransitionDistance = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_notifications_scrim_transition_distance);
    }
}
