package com.android.keyguard;

import com.android.keyguard.CarrierTextManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CarrierTextManager$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ CarrierTextManager$$ExternalSyntheticLambda4(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                CarrierTextManager carrierTextManager = (CarrierTextManager) obj;
                carrierTextManager.mKeyguardUpdateMonitor.registerCallback(carrierTextManager.mCallback);
                break;
            case 1:
                CarrierTextManager carrierTextManager2 = (CarrierTextManager) obj;
                carrierTextManager2.mWakefulnessLifecycle.addObserver(carrierTextManager2.mWakefulnessObserver);
                break;
            case 2:
                CarrierTextManager carrierTextManager3 = (CarrierTextManager) obj;
                carrierTextManager3.mWakefulnessLifecycle.removeObserver(carrierTextManager3.mWakefulnessObserver);
                break;
            default:
                ((CarrierTextManager.CarrierTextCallback) obj).updateCarrierInfo(new CarrierTextManager.CarrierTextCallbackInfo("", null, false, false, null, false));
                break;
        }
    }
}
