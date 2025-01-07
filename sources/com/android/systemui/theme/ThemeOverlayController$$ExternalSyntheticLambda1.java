package com.android.systemui.theme;

import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.util.Log;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ThemeOverlayController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ThemeOverlayController f$0;

    public /* synthetic */ ThemeOverlayController$$ExternalSyntheticLambda1(ThemeOverlayController themeOverlayController, int i) {
        this.$r8$classId = i;
        this.f$0 = themeOverlayController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        final ThemeOverlayController themeOverlayController = this.f$0;
        switch (i) {
            case 0:
                WallpaperManager wallpaperManager = themeOverlayController.mWallpaperManager;
                int userId = ((UserTrackerImpl) themeOverlayController.mUserTracker).getUserId();
                final WallpaperColors wallpaperColors = wallpaperManager.getWallpaperColors(themeOverlayController.mWallpaperManager.getWallpaperIdForUser(2, userId) <= themeOverlayController.mWallpaperManager.getWallpaperIdForUser(1, userId) ? 1 : 2);
                Runnable runnable = new Runnable() { // from class: com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        ThemeOverlayController themeOverlayController2 = ThemeOverlayController.this;
                        WallpaperColors wallpaperColors2 = wallpaperColors;
                        themeOverlayController2.getClass();
                        Log.d("ThemeOverlayController", "Boot colors: " + wallpaperColors2);
                        themeOverlayController2.mCurrentColors.put(((UserTrackerImpl) themeOverlayController2.mUserTracker).getUserId(), wallpaperColors2);
                        themeOverlayController2.reevaluateSystemTheme(false);
                    }
                };
                if (!((DeviceProvisionedControllerImpl) themeOverlayController.mDeviceProvisionedController).isCurrentUserSetup()) {
                    runnable.run();
                    break;
                } else {
                    themeOverlayController.mMainExecutor.execute(runnable);
                    break;
                }
            default:
                int userId2 = ((UserTrackerImpl) themeOverlayController.mUserTracker).getUserId();
                WallpaperColors wallpaperColors2 = (WallpaperColors) themeOverlayController.mDeferredWallpaperColors.get(userId2);
                if (wallpaperColors2 != null) {
                    int i2 = themeOverlayController.mDeferredWallpaperColorsFlags.get(userId2);
                    themeOverlayController.mDeferredWallpaperColors.put(userId2, null);
                    themeOverlayController.mDeferredWallpaperColorsFlags.put(userId2, 0);
                    themeOverlayController.handleWallpaperColors(wallpaperColors2, i2, userId2);
                    break;
                }
                break;
        }
    }
}
