package com.android.systemui.shade.carrier;

import com.android.keyguard.CarrierTextManager;
import com.android.keyguard.CarrierTextManager$$ExternalSyntheticLambda5;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShadeCarrierGroupController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ShadeCarrierGroupController f$0;

    public /* synthetic */ ShadeCarrierGroupController$$ExternalSyntheticLambda0(ShadeCarrierGroupController shadeCarrierGroupController, int i) {
        this.$r8$classId = i;
        this.f$0 = shadeCarrierGroupController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ShadeCarrierGroupController shadeCarrierGroupController = this.f$0;
        switch (i) {
            case 0:
                boolean z = shadeCarrierGroupController.mListening;
                ShadeCarrierGroupController.AnonymousClass1 anonymousClass1 = shadeCarrierGroupController.mSignalCallback;
                CarrierTextManager carrierTextManager = shadeCarrierGroupController.mCarrierTextManager;
                NetworkController networkController = shadeCarrierGroupController.mNetworkController;
                if (!z) {
                    ((NetworkControllerImpl) networkController).removeCallback(anonymousClass1);
                    carrierTextManager.mBgExecutor.execute(new CarrierTextManager$$ExternalSyntheticLambda5(carrierTextManager, null));
                    break;
                } else {
                    NetworkControllerImpl networkControllerImpl = (NetworkControllerImpl) networkController;
                    if (networkControllerImpl.mPhone.getPhoneType() != 0) {
                        networkControllerImpl.addCallback(anonymousClass1);
                    }
                    carrierTextManager.mBgExecutor.execute(new CarrierTextManager$$ExternalSyntheticLambda5(carrierTextManager, shadeCarrierGroupController.mCallback));
                    break;
                }
            default:
                shadeCarrierGroupController.handleUpdateState();
                break;
        }
    }
}
