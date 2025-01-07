package com.android.systemui.statusbar.phone;

import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.statusbar.AutoHideUiElement;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class AutoHideController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AutoHideController f$0;

    public /* synthetic */ AutoHideController$$ExternalSyntheticLambda0(AutoHideController autoHideController, int i) {
        this.$r8$classId = i;
        this.f$0 = autoHideController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        AutoHideController autoHideController = this.f$0;
        switch (i) {
            case 0:
                if (autoHideController.isAnyTransientBarShown()) {
                    try {
                        autoHideController.mWindowManagerService.hideTransientBars(autoHideController.mDisplayId);
                    } catch (RemoteException unused) {
                        Log.w("AutoHideController", "Cannot get WindowManager");
                    }
                    CentralSurfacesImpl.AnonymousClass4 anonymousClass4 = autoHideController.mStatusBar;
                    if (anonymousClass4 != null) {
                        anonymousClass4.hide();
                    }
                    AutoHideUiElement autoHideUiElement = autoHideController.mNavigationBar;
                    if (autoHideUiElement != null) {
                        autoHideUiElement.hide();
                        break;
                    }
                }
                break;
            case 1:
                autoHideController.mStatusBar.synchronizeState();
                break;
            default:
                autoHideController.mNavigationBar.synchronizeState();
                break;
        }
    }
}
