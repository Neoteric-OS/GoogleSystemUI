package com.android.keyguard;

import com.android.keyguard.CarrierTextManager;
import com.android.systemui.util.ViewController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CarrierTextController extends ViewController {
    public final AnonymousClass1 mCarrierTextCallback;
    public final CarrierTextManager mCarrierTextManager;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.keyguard.CarrierTextController$1] */
    public CarrierTextController(CarrierText carrierText, CarrierTextManager.Builder builder, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        super(carrierText);
        this.mCarrierTextCallback = new CarrierTextManager.CarrierTextCallback() { // from class: com.android.keyguard.CarrierTextController.1
            @Override // com.android.keyguard.CarrierTextManager.CarrierTextCallback
            public final void finishedWakingUp() {
                ((CarrierText) CarrierTextController.this.mView).setSelected(true);
            }

            @Override // com.android.keyguard.CarrierTextManager.CarrierTextCallback
            public final void startedGoingToSleep() {
                ((CarrierText) CarrierTextController.this.mView).setSelected(false);
            }

            @Override // com.android.keyguard.CarrierTextManager.CarrierTextCallback
            public final void updateCarrierInfo(CarrierTextManager.CarrierTextCallbackInfo carrierTextCallbackInfo) {
                ((CarrierText) CarrierTextController.this.mView).setText(carrierTextCallbackInfo.carrierText);
            }
        };
        builder.mShowAirplaneMode = carrierText.mShowAirplaneMode;
        builder.mShowMissingSim = carrierText.mShowMissingSim;
        builder.mDebugLocation = carrierText.mDebugLocation;
        this.mCarrierTextManager = builder.build();
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        ((CarrierText) this.mView).setSelected(this.mKeyguardUpdateMonitor.mDeviceInteractive);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        CarrierTextManager carrierTextManager = this.mCarrierTextManager;
        carrierTextManager.mBgExecutor.execute(new CarrierTextManager$$ExternalSyntheticLambda5(carrierTextManager, this.mCarrierTextCallback));
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        CarrierTextManager carrierTextManager = this.mCarrierTextManager;
        carrierTextManager.mBgExecutor.execute(new CarrierTextManager$$ExternalSyntheticLambda5(carrierTextManager, null));
    }
}
