package com.android.systemui.wallpapers;

import com.android.systemui.wallpapers.ImageWallpaper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ImageWallpaper$CanvasEngine$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ImageWallpaper.CanvasEngine f$0;

    public /* synthetic */ ImageWallpaper$CanvasEngine$$ExternalSyntheticLambda0(ImageWallpaper.CanvasEngine canvasEngine, int i) {
        this.$r8$classId = i;
        this.f$0 = canvasEngine;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ImageWallpaper.CanvasEngine canvasEngine = this.f$0;
        switch (i) {
            case 0:
                synchronized (canvasEngine.mLock) {
                    canvasEngine.unloadBitmapIfNotUsedInternal();
                }
                return;
            default:
                synchronized (canvasEngine.mLock) {
                    try {
                        if (canvasEngine.mDrawn) {
                            return;
                        }
                        canvasEngine.drawFrameInternal();
                        return;
                    } finally {
                    }
                }
        }
    }
}
