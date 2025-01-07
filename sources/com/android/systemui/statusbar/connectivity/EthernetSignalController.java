package com.android.systemui.statusbar.connectivity;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EthernetSignalController extends SignalController {
    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final ConnectivityState cleanState() {
        return new ConnectivityState();
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final int getContentDescription() {
        ConnectivityState connectivityState = this.mCurrentState;
        return connectivityState.connected ? connectivityState.iconGroup.contentDesc[1] : connectivityState.iconGroup.discContentDesc;
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final void notifyListeners(SignalCallback signalCallback) {
        boolean z = this.mCurrentState.connected;
        signalCallback.setEthernetIndicators(new IconState(getCurrentIconId(), getTextIfExists(getContentDescription()).toString(), z));
    }
}
