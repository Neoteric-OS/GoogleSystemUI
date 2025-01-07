package com.android.systemui.util;

import android.app.WallpaperManager;
import android.util.Log;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WallpaperController {
    public float notificationShadeZoomOut;
    public NotificationShadeWindowView rootView;
    public float unfoldTransitionZoomOut;
    public final WallpaperManager wallpaperManager;
    public final WallpaperRepositoryImpl wallpaperRepository;

    public WallpaperController(WallpaperManager wallpaperManager, WallpaperRepositoryImpl wallpaperRepositoryImpl) {
        this.wallpaperManager = wallpaperManager;
        this.wallpaperRepository = wallpaperRepositoryImpl;
    }

    public final void updateZoom() {
        float max = Math.max(this.notificationShadeZoomOut, this.unfoldTransitionZoomOut);
        try {
            NotificationShadeWindowView notificationShadeWindowView = this.rootView;
            if (notificationShadeWindowView != null) {
                if (!notificationShadeWindowView.isAttachedToWindow() || notificationShadeWindowView.getWindowToken() == null) {
                    Log.i("WallpaperController", "Won't set zoom. Window not attached " + notificationShadeWindowView);
                } else {
                    this.wallpaperManager.setWallpaperZoomOut(notificationShadeWindowView.getWindowToken(), max);
                }
            }
        } catch (IllegalArgumentException e) {
            NotificationShadeWindowView notificationShadeWindowView2 = this.rootView;
            Log.w("WallpaperController", "Can't set zoom. Window is gone: " + (notificationShadeWindowView2 != null ? notificationShadeWindowView2.getWindowToken() : null), e);
        }
    }
}
