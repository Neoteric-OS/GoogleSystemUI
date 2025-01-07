package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.Handler;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.ScreenshotHelper;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TakeScreenshot extends UserAction {
    public final Handler handler;
    public final ScreenshotHelper screenshotHelper;
    public final String tag;
    public final UiEventLogger uiEventLogger;

    public TakeScreenshot(Context context, Handler handler, UiEventLogger uiEventLogger) {
        super(context, null);
        this.handler = handler;
        this.uiEventLogger = uiEventLogger;
        this.tag = "Columbus/TakeScreenshot";
        this.screenshotHelper = new ScreenshotHelper(context);
        setAvailable(true);
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.UserAction
    public final boolean availableOnLockscreen() {
        return true;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.screenshotHelper.takeScreenshot(6, this.handler, (Consumer) null);
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_SCREENSHOT);
    }
}
