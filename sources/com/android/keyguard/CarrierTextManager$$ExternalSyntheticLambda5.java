package com.android.keyguard;

import com.android.keyguard.CarrierTextManager;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CarrierTextManager$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ CarrierTextManager$$ExternalSyntheticLambda5(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((CarrierTextManager.CarrierTextCallback) this.f$0).updateCarrierInfo((CarrierTextManager.CarrierTextCallbackInfo) this.f$1);
                break;
            case 1:
                CarrierTextManager carrierTextManager = (CarrierTextManager) this.f$0;
                Executor executor = (Executor) this.f$1;
                boolean hasSystemFeature = carrierTextManager.mContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
                if (hasSystemFeature && carrierTextManager.mNetworkSupported.compareAndSet(false, hasSystemFeature)) {
                    carrierTextManager.handleSetListening(carrierTextManager.mCarrierTextCallback);
                    executor.execute(new CarrierTextManager$$ExternalSyntheticLambda4(0, carrierTextManager));
                    break;
                }
                break;
            default:
                ((CarrierTextManager) this.f$1).handleSetListening((CarrierTextManager.CarrierTextCallback) this.f$0);
                break;
        }
    }

    public /* synthetic */ CarrierTextManager$$ExternalSyntheticLambda5(CarrierTextManager carrierTextManager, CarrierTextManager.CarrierTextCallback carrierTextCallback) {
        this.$r8$classId = 2;
        this.f$1 = carrierTextManager;
        this.f$0 = carrierTextCallback;
    }
}
