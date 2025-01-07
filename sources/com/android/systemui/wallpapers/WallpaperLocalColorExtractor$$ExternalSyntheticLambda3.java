package com.android.systemui.wallpapers;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Trace;
import android.util.ArraySet;
import android.util.Log;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WallpaperLocalColorExtractor$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ WallpaperLocalColorExtractor f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ WallpaperLocalColorExtractor$$ExternalSyntheticLambda3(WallpaperLocalColorExtractor wallpaperLocalColorExtractor, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = wallpaperLocalColorExtractor;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                WallpaperLocalColorExtractor wallpaperLocalColorExtractor = this.f$0;
                List list = (List) this.f$1;
                synchronized (wallpaperLocalColorExtractor.mLock) {
                    try {
                        boolean isActive = wallpaperLocalColorExtractor.isActive();
                        wallpaperLocalColorExtractor.mPendingRegions.addAll(list);
                        if (!isActive && wallpaperLocalColorExtractor.isActive()) {
                            wallpaperLocalColorExtractor.mWallpaperLocalColorExtractorCallback.this$1.setOffsetNotificationsEnabled(true);
                        }
                        wallpaperLocalColorExtractor.processLocalColorsInternal();
                    } finally {
                    }
                }
                return;
            case 1:
                WallpaperLocalColorExtractor wallpaperLocalColorExtractor2 = this.f$0;
                List list2 = (List) this.f$1;
                synchronized (wallpaperLocalColorExtractor2.mLock) {
                    try {
                        boolean isActive2 = wallpaperLocalColorExtractor2.isActive();
                        wallpaperLocalColorExtractor2.mPendingRegions.removeAll(list2);
                        final Set set = wallpaperLocalColorExtractor2.mProcessedRegions;
                        Objects.requireNonNull(set);
                        list2.forEach(new Consumer() { // from class: com.android.systemui.wallpapers.WallpaperLocalColorExtractor$$ExternalSyntheticLambda6
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                set.remove((RectF) obj);
                            }
                        });
                        if (isActive2 && !wallpaperLocalColorExtractor2.isActive()) {
                            wallpaperLocalColorExtractor2.mWallpaperLocalColorExtractorCallback.this$1.setOffsetNotificationsEnabled(false);
                        }
                    } finally {
                    }
                }
                return;
            default:
                WallpaperLocalColorExtractor wallpaperLocalColorExtractor3 = this.f$0;
                Bitmap bitmap = (Bitmap) this.f$1;
                synchronized (wallpaperLocalColorExtractor3.mLock) {
                    try {
                        if (bitmap.isRecycled()) {
                            Log.i("WallpaperLocalColorExtractor", "Wallpaper has changed; deferring color extraction");
                            return;
                        }
                        if (bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
                            wallpaperLocalColorExtractor3.mBitmapWidth = bitmap.getWidth();
                            wallpaperLocalColorExtractor3.mBitmapHeight = bitmap.getHeight();
                            Trace.beginSection("WallpaperLocalColorExtractor#createMiniBitmap");
                            double min = Math.min(1.0d, Math.sqrt(12544.0d / (bitmap.getHeight() * bitmap.getWidth())));
                            Bitmap createMiniBitmap = wallpaperLocalColorExtractor3.createMiniBitmap(bitmap, Math.max(1, (int) (bitmap.getWidth() * min)), Math.max(1, (int) (min * bitmap.getHeight())));
                            Trace.endSection();
                            wallpaperLocalColorExtractor3.mMiniBitmap = createMiniBitmap;
                            wallpaperLocalColorExtractor3.mWallpaperLocalColorExtractorCallback.this$1.onMiniBitmapUpdated();
                            wallpaperLocalColorExtractor3.mPendingRegions.addAll(wallpaperLocalColorExtractor3.mProcessedRegions);
                            ((ArraySet) wallpaperLocalColorExtractor3.mProcessedRegions).clear();
                            wallpaperLocalColorExtractor3.processLocalColorsInternal();
                            return;
                        }
                        Log.e("WallpaperLocalColorExtractor", "Attempt to extract colors from an invalid bitmap");
                        return;
                    } finally {
                    }
                }
        }
    }
}
