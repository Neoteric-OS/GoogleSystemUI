package com.google.android.systemui.dreamliner;

import android.content.Context;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.dock.DockManager;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.google.android.systemui.dreamliner.DockObserver;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DockObserver$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DockObserver f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ DockObserver$$ExternalSyntheticLambda1(DockObserver dockObserver, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = dockObserver;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DockObserver dockObserver = this.f$0;
                DockManager.DockEventListener dockEventListener = (DockManager.DockEventListener) this.f$1;
                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("onDockEvent mDockState = "), dockObserver.mDockState, "DLObserver");
                dockEventListener.onEvent(dockObserver.mDockState);
                break;
            case 1:
                DockObserver dockObserver2 = this.f$0;
                Context context = (Context) this.f$1;
                ((UserTrackerImpl) dockObserver2.mUserTracker).addCallback(dockObserver2.mUserChangedCallback, dockObserver2.mMainExecutor);
                if (dockObserver2.mDreamlinerGear != null) {
                    DockObserver.ProtectedBroadcastSender protectedBroadcastSender = dockObserver2.mProtectedBroadcastSender;
                    ImageView imageView = dockObserver2.mDreamlinerGear;
                    DockGestureController dockGestureController = new DockGestureController(context, protectedBroadcastSender, imageView, dockObserver2.mPhotoPreview, (View) imageView.getParent(), dockObserver2.mIndicationController, dockObserver2.mStatusBarStateController, dockObserver2.mKeyguardStateController);
                    dockObserver2.mDockGestureController = dockGestureController;
                    ((ConfigurationControllerImpl) dockObserver2.mConfigurationController).addCallback(dockGestureController);
                    break;
                } else {
                    Log.e("DLObserver", "initDockGestureController fail. dreamlinerGear is null");
                    break;
                }
            default:
                DockObserver dockObserver3 = this.f$0;
                ResultReceiver resultReceiver = (ResultReceiver) this.f$1;
                DockIndicationController dockIndicationController = dockObserver3.mIndicationController;
                dockIndicationController.mShowPromoTimes = 0;
                dockIndicationController.mShowPromo = true;
                if (!dockIndicationController.mDozing || !dockIndicationController.mDocking) {
                    resultReceiver.send(1, null);
                    break;
                } else {
                    dockIndicationController.showPromoInner();
                    resultReceiver.send(0, null);
                    break;
                }
                break;
        }
    }
}
