package com.android.systemui.shared.rotation;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.util.Log;
import android.view.WindowManagerGlobal;
import com.android.systemui.shared.rotation.RotationButtonController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RotationButtonController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ RotationButtonController$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((RotationButtonController) obj).setRotateSuggestionButtonState(false, false);
                break;
            case 1:
                ((RotationButtonController) obj).mPendingRotationSuggestion = false;
                break;
            case 2:
                RotationButtonController rotationButtonController = (RotationButtonController) obj;
                rotationButtonController.getClass();
                try {
                    rotationButtonController.mContext.unregisterReceiver(rotationButtonController.mDockedReceiver);
                } catch (IllegalArgumentException e) {
                    Log.e("RotationButtonController", "Docked receiver already unregistered", e);
                }
                if (rotationButtonController.mRotationWatcherRegistered) {
                    try {
                        WindowManagerGlobal.getWindowManagerService().removeRotationWatcher(rotationButtonController.mRotationWatcher);
                        break;
                    } catch (RemoteException e2) {
                        Log.e("RotationButtonController", "UnregisterListeners caught a RemoteException", e2);
                        return;
                    }
                }
                break;
            case 3:
                final RotationButtonController rotationButtonController2 = (RotationButtonController) obj;
                rotationButtonController2.getClass();
                final Intent registerReceiver = rotationButtonController2.mContext.registerReceiver(rotationButtonController2.mDockedReceiver, new IntentFilter("android.intent.action.DOCK_EVENT"));
                rotationButtonController2.mContext.getMainExecutor().execute(new Runnable() { // from class: com.android.systemui.shared.rotation.RotationButtonController$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        RotationButtonController rotationButtonController3 = RotationButtonController.this;
                        Intent intent = registerReceiver;
                        rotationButtonController3.getClass();
                        if (intent == null) {
                            return;
                        }
                        rotationButtonController3.mDocked = intent.getIntExtra("android.intent.extra.DOCK_STATE", 0) != 0;
                    }
                });
                break;
            default:
                ((RotationButtonController.TaskStackListenerImpl) obj).this$0.setRotateSuggestionButtonState(false, false);
                break;
        }
    }
}
