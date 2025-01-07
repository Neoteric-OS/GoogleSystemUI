package com.android.systemui.charging;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WiredChargingRippleController$startRipple$1$onViewAttachedToWindow$1 implements Runnable {
    public final /* synthetic */ WiredChargingRippleController this$0;

    public WiredChargingRippleController$startRipple$1$onViewAttachedToWindow$1(WiredChargingRippleController wiredChargingRippleController) {
        this.this$0 = wiredChargingRippleController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WiredChargingRippleController wiredChargingRippleController = this.this$0;
        wiredChargingRippleController.viewCaptureAwareWindowManager.removeView(wiredChargingRippleController.rippleView);
    }
}
