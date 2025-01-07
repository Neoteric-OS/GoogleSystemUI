package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.shade.ShadeController;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.IColumbusServiceGestureListener$Stub$Proxy;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Collections;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SettingsAction extends ServiceAction {
    public final ShadeController shadeController;
    public final Set supportedCallerPackages;
    public final String tag;
    public final UiEventLogger uiEventLogger;

    public SettingsAction(Context context, ShadeController shadeController, UiEventLogger uiEventLogger) {
        super(context);
        this.shadeController = shadeController;
        this.uiEventLogger = uiEventLogger;
        this.tag = "Columbus/SettingsAction";
        this.supportedCallerPackages = Collections.singleton("com.android.settings");
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_ON_SETTINGS);
        this.shadeController.cancelExpansionAndCollapseShade();
        IColumbusServiceGestureListener$Stub$Proxy iColumbusServiceGestureListener$Stub$Proxy = this.columbusServiceGestureListener;
        if (iColumbusServiceGestureListener$Stub$Proxy != null) {
            try {
                iColumbusServiceGestureListener$Stub$Proxy.onTrigger();
            } catch (DeadObjectException e) {
                Log.e("Columbus/ServiceAction", "Listener crashed or closed without unregistering", e);
                this.columbusServiceGestureListener = null;
                updateAvailable$1();
            } catch (RemoteException e2) {
                Log.e("Columbus/ServiceAction", "Unable to send trigger, setting listener to null", e2);
                this.columbusServiceGestureListener = null;
                updateAvailable$1();
            }
        }
    }
}
