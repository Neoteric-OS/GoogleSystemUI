package com.android.systemui.statusbar.connectivity;

import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NetworkControllerImpl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ NetworkControllerImpl$$ExternalSyntheticLambda2(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                NetworkControllerImpl networkControllerImpl = (NetworkControllerImpl) obj;
                if (networkControllerImpl.mLastServiceState == null) {
                    networkControllerImpl.mLastServiceState = networkControllerImpl.mPhone.getServiceState();
                    if (networkControllerImpl.mMobileSignalControllers.size() == 0) {
                        networkControllerImpl.recalculateEmergency();
                        break;
                    }
                }
                break;
            case 1:
                boolean z = NetworkControllerImpl.DEBUG;
                ((NetworkControllerImpl) obj).updateConnectivity();
                break;
            case 2:
                NetworkControllerImpl networkControllerImpl2 = (NetworkControllerImpl) obj;
                networkControllerImpl2.mInternetDialogManager.create(networkControllerImpl2.mAccessPoints.canConfigMobileData(), networkControllerImpl2.mAccessPoints.canConfigWifi(), null);
                break;
            case 3:
                ((NetworkControllerImpl) obj).recalculateEmergency();
                break;
            case 4:
                NetworkControllerImpl networkControllerImpl3 = (NetworkControllerImpl) obj;
                if (NetworkControllerImpl.DEBUG) {
                    networkControllerImpl3.getClass();
                    Log.d("NetworkController", ": mClearForceValidated");
                }
                networkControllerImpl3.mForceCellularValidated = false;
                networkControllerImpl3.updateConnectivity();
                break;
            case 5:
                boolean z2 = NetworkControllerImpl.DEBUG;
                ((NetworkControllerImpl) obj).registerListeners();
                break;
            case 6:
                ((NetworkControllerImpl) obj).handleConfigurationChanged();
                break;
            default:
                WifiSignalController wifiSignalController = (WifiSignalController) obj;
                wifiSignalController.getClass();
                wifiSignalController.doInBackground(new WifiSignalController$$ExternalSyntheticLambda0(wifiSignalController, 1));
                break;
        }
    }
}
