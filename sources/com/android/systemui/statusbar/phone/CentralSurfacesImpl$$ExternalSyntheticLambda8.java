package com.android.systemui.statusbar.phone;

import android.util.Log;
import com.android.systemui.shade.CameraLauncher;
import com.android.systemui.statusbar.KeyboardShortcutListSearch;
import com.android.systemui.statusbar.KeyboardShortcuts;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesImpl$$ExternalSyntheticLambda8 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CentralSurfacesImpl f$0;

    public /* synthetic */ CentralSurfacesImpl$$ExternalSyntheticLambda8(CentralSurfacesImpl centralSurfacesImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = centralSurfacesImpl;
    }

    public final void onMessage() {
        switch (this.$r8$classId) {
            case 0:
                if (!this.f$0.shouldUseTabletKeyboardShortcuts()) {
                    KeyboardShortcuts.dismiss();
                    break;
                } else {
                    KeyboardShortcutListSearch.dismiss();
                    break;
                }
            default:
                CentralSurfacesImpl centralSurfacesImpl = this.f$0;
                centralSurfacesImpl.getClass();
                Log.w("CentralSurfaces", "Launch transition: Timeout!");
                ((CameraLauncher) centralSurfacesImpl.mCameraLauncherLazy.get()).mKeyguardBypassController.launchingAffordance = false;
                centralSurfacesImpl.releaseGestureWakeLock();
                centralSurfacesImpl.mShadeSurface.resetViews(false);
                break;
        }
    }
}
