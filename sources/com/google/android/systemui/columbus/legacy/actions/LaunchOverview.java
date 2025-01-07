package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.recents.Recents;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LaunchOverview extends UserAction {
    public final Recents recents;
    public final String tag;
    public final UiEventLogger uiEventLogger;

    public LaunchOverview(Context context, Recents recents, UiEventLogger uiEventLogger) {
        super(context, null);
        this.recents = recents;
        this.uiEventLogger = uiEventLogger;
        this.tag = "Columbus/LaunchOverview";
        setAvailable(true);
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.recents.toggleRecentApps();
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_OVERVIEW);
    }
}
