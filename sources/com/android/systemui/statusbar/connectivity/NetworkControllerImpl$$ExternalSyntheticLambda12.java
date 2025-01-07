package com.android.systemui.statusbar.connectivity;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NetworkControllerImpl$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ NetworkControllerImpl f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ NetworkControllerImpl$$ExternalSyntheticLambda12(NetworkControllerImpl networkControllerImpl, boolean z) {
        this.f$0 = networkControllerImpl;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NetworkControllerImpl networkControllerImpl = this.f$0;
        networkControllerImpl.mUserSetup = this.f$1;
        for (int i = 0; i < networkControllerImpl.mMobileSignalControllers.size(); i++) {
            MobileSignalController mobileSignalController = (MobileSignalController) networkControllerImpl.mMobileSignalControllers.valueAt(i);
            ((MobileState) mobileSignalController.mCurrentState).userSetup = networkControllerImpl.mUserSetup;
            mobileSignalController.notifyListenersIfNecessary();
        }
    }
}
