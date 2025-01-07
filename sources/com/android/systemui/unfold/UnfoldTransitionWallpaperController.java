package com.android.systemui.unfold;

import android.app.WallpaperInfo;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.util.WallpaperController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldTransitionWallpaperController {
    public final UnfoldTransitionProgressProvider unfoldTransitionProgressProvider;
    public final WallpaperController wallpaperController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionListener implements UnfoldTransitionProgressProvider.TransitionProgressListener {
        public TransitionListener() {
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionFinished() {
            WallpaperController wallpaperController = UnfoldTransitionWallpaperController.this.wallpaperController;
            WallpaperInfo wallpaperInfo = (WallpaperInfo) wallpaperController.wallpaperRepository.wallpaperInfo.getValue();
            if (wallpaperInfo != null ? wallpaperInfo.shouldUseDefaultUnfoldTransition() : true) {
                wallpaperController.unfoldTransitionZoomOut = 0.0f;
                wallpaperController.updateZoom();
            }
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionProgress(float f) {
            float f2 = 1 - f;
            WallpaperController wallpaperController = UnfoldTransitionWallpaperController.this.wallpaperController;
            WallpaperInfo wallpaperInfo = (WallpaperInfo) wallpaperController.wallpaperRepository.wallpaperInfo.getValue();
            if (wallpaperInfo != null ? wallpaperInfo.shouldUseDefaultUnfoldTransition() : true) {
                wallpaperController.unfoldTransitionZoomOut = f2;
                wallpaperController.updateZoom();
            }
        }
    }

    public UnfoldTransitionWallpaperController(UnfoldTransitionProgressProvider unfoldTransitionProgressProvider, WallpaperController wallpaperController) {
        this.unfoldTransitionProgressProvider = unfoldTransitionProgressProvider;
        this.wallpaperController = wallpaperController;
    }
}
