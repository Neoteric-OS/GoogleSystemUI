package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OpenNotificationShade extends UserAction {
    public final Lazy notificationShadeWindowController;
    public final ShadeController shadeController;
    public final String tag;
    public final UiEventLogger uiEventLogger;

    public OpenNotificationShade(Context context, Lazy lazy, ShadeController shadeController, UiEventLogger uiEventLogger) {
        super(context, null);
        this.notificationShadeWindowController = lazy;
        this.shadeController = shadeController;
        this.uiEventLogger = uiEventLogger;
        this.tag = "Columbus/OpenNotif";
        setAvailable(true);
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        boolean z = ((NotificationShadeWindowControllerImpl) ((NotificationShadeWindowController) this.notificationShadeWindowController.get())).mCurrentState.shadeOrQsExpanded;
        ShadeController shadeController = this.shadeController;
        if (z) {
            shadeController.postAnimateCollapseShade();
            this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_NOTIFICATION_SHADE_CLOSE);
        } else {
            ((BaseShadeControllerImpl) shadeController).animateExpandShade();
            this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_NOTIFICATION_SHADE_OPEN);
        }
    }
}
