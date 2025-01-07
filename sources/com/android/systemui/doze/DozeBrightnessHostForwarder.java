package com.android.systemui.doze;

import com.android.systemui.doze.DozeMachine;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.statusbar.phone.DozeServiceHost;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeBrightnessHostForwarder extends DozeMachine.Service.Delegate {
    public final DozeServiceHost mHost;

    public DozeBrightnessHostForwarder(DozeService dozeService, DozeServiceHost dozeServiceHost) {
        super(dozeService);
        this.mHost = dozeServiceHost;
    }

    @Override // com.android.systemui.doze.DozeMachine.Service.Delegate, com.android.systemui.doze.DozeMachine.Service
    public final void setDozeScreenBrightness(int i) {
        this.mDelegate.setDozeScreenBrightness(i);
        ((NotificationShadeWindowControllerImpl) this.mHost.mNotificationShadeWindowController).mScreenBrightnessDoze = i / 255.0f;
    }

    @Override // com.android.systemui.doze.DozeMachine.Service.Delegate, com.android.systemui.doze.DozeMachine.Service
    public final void setDozeScreenBrightnessFloat(float f) {
        this.mDelegate.setDozeScreenBrightnessFloat(f);
        ((NotificationShadeWindowControllerImpl) this.mHost.mNotificationShadeWindowController).mScreenBrightnessDoze = f;
    }
}
