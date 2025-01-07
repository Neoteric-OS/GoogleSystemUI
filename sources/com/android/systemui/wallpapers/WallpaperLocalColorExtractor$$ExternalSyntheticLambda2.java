package com.android.systemui.wallpapers;

import android.graphics.Bitmap;
import android.util.ArraySet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WallpaperLocalColorExtractor$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ WallpaperLocalColorExtractor f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ WallpaperLocalColorExtractor$$ExternalSyntheticLambda2(WallpaperLocalColorExtractor wallpaperLocalColorExtractor, int i) {
        this.f$0 = wallpaperLocalColorExtractor;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WallpaperLocalColorExtractor wallpaperLocalColorExtractor = this.f$0;
        int i = this.f$1;
        synchronized (wallpaperLocalColorExtractor.mLock) {
            try {
                if (wallpaperLocalColorExtractor.mPages == i) {
                    return;
                }
                wallpaperLocalColorExtractor.mPages = i;
                Bitmap bitmap = wallpaperLocalColorExtractor.mMiniBitmap;
                if (bitmap != null && !bitmap.isRecycled()) {
                    wallpaperLocalColorExtractor.mPendingRegions.addAll(wallpaperLocalColorExtractor.mProcessedRegions);
                    ((ArraySet) wallpaperLocalColorExtractor.mProcessedRegions).clear();
                    wallpaperLocalColorExtractor.processLocalColorsInternal();
                }
            } finally {
            }
        }
    }
}
