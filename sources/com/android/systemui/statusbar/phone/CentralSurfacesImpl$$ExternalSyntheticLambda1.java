package com.android.systemui.statusbar.phone;

import android.os.RemoteException;
import com.android.internal.graphics.ColorUtils;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.statusbar.LightRevealScrim;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CentralSurfacesImpl f$0;

    public /* synthetic */ CentralSurfacesImpl$$ExternalSyntheticLambda1(CentralSurfacesImpl centralSurfacesImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = centralSurfacesImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        final CentralSurfacesImpl centralSurfacesImpl = this.f$0;
        switch (i) {
            case 0:
                centralSurfacesImpl.getClass();
                try {
                    centralSurfacesImpl.mDreamManager.awaken();
                    break;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return;
                }
            case 1:
                final float wallpaperDimAmount = (!centralSurfacesImpl.mWallpaperSupported || centralSurfacesImpl.mWallpaperManager.lockScreenWallpaperExists()) ? centralSurfacesImpl.mWallpaperManager.getWallpaperDimAmount() : 0.0f;
                ((ExecutorImpl) centralSurfacesImpl.mMainExecutor).execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda19
                    @Override // java.lang.Runnable
                    public final void run() {
                        CentralSurfacesImpl centralSurfacesImpl2 = CentralSurfacesImpl.this;
                        float f = wallpaperDimAmount;
                        ScrimController scrimController = centralSurfacesImpl2.mScrimController;
                        scrimController.getClass();
                        float compositeAlpha = ColorUtils.compositeAlpha((int) (f * 255.0f), 51) / 255.0f;
                        scrimController.mScrimBehindAlphaKeyguard = compositeAlpha;
                        for (ScrimState scrimState : ScrimState.values()) {
                            scrimState.mScrimBehindAlphaKeyguard = compositeAlpha;
                        }
                        scrimController.scheduleUpdate$1();
                    }
                });
                break;
            case 2:
                centralSurfacesImpl.onLaunchTransitionFadingEnded();
                break;
            case 3:
                LightRevealScrim lightRevealScrim = centralSurfacesImpl.mLightRevealScrim;
                boolean z = lightRevealScrim.isScrimOpaque;
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) centralSurfacesImpl.mNotificationShadeWindowController;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                if (notificationShadeWindowState.lightRevealScrimOpaque != z) {
                    notificationShadeWindowState.lightRevealScrimOpaque = z;
                    notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                }
                boolean z2 = lightRevealScrim.isScrimOpaque;
                Iterator it = centralSurfacesImpl.mScreenOffAnimationController.animations.iterator();
                while (it.hasNext()) {
                    ((ScreenOffAnimation) it.next()).onScrimOpaqueChanged(z2);
                }
                break;
            default:
                centralSurfacesImpl.checkBarModes$1();
                break;
        }
    }
}
