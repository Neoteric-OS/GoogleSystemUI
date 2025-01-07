package com.android.systemui.wallpapers;

import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Trace;
import android.service.wallpaper.WallpaperService;
import android.util.ArraySet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ImageWallpaper extends WallpaperService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final DelayableExecutor mLongExecutor;
    public volatile int mPages = 1;
    public boolean mPagesComputed = false;
    public final UserTracker mUserTracker;
    public HandlerThread mWorker;

    public ImageWallpaper(DelayableExecutor delayableExecutor, UserTracker userTracker) {
        this.mLongExecutor = delayableExecutor;
        this.mUserTracker = userTracker;
    }

    @Override // android.service.wallpaper.WallpaperService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("ImageWallpaper");
        this.mWorker = handlerThread;
        handlerThread.start();
    }

    @Override // android.service.wallpaper.WallpaperService
    public final WallpaperService.Engine onCreateEngine() {
        return new CanvasEngine();
    }

    public final Looper onProvideEngineLooper() {
        HandlerThread handlerThread = this.mWorker;
        return handlerThread != null ? handlerThread.getLooper() : super.onProvideEngineLooper();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CanvasEngine extends WallpaperService.Engine implements DisplayManager.DisplayListener {
        static final int MIN_SURFACE_HEIGHT = 128;
        static final int MIN_SURFACE_WIDTH = 128;
        public Bitmap mBitmap;
        public int mBitmapUsages;
        public boolean mDrawn;
        public final Object mLock;
        public SurfaceHolder mSurfaceHolder;
        public final Object mSurfaceLock;
        public final WallpaperLocalColorExtractor mWallpaperLocalColorExtractor;
        public WallpaperManager mWallpaperManager;
        public boolean mWideColorGamut;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.wallpapers.ImageWallpaper$CanvasEngine$1, reason: invalid class name */
        public final class AnonymousClass1 {
            public AnonymousClass1() {
            }
        }

        public CanvasEngine() {
            super(ImageWallpaper.this);
            this.mDrawn = false;
            this.mWideColorGamut = false;
            this.mBitmapUsages = 0;
            Object obj = new Object();
            this.mLock = obj;
            this.mSurfaceLock = new Object();
            setFixedSizeAllowed(true);
            setShowForAllUsers(true);
            WallpaperLocalColorExtractor wallpaperLocalColorExtractor = new WallpaperLocalColorExtractor(ImageWallpaper.this.mLongExecutor, obj, new AnonymousClass1());
            this.mWallpaperLocalColorExtractor = wallpaperLocalColorExtractor;
            if (ImageWallpaper.this.mPagesComputed) {
                wallpaperLocalColorExtractor.mLongExecutor.execute(new WallpaperLocalColorExtractor$$ExternalSyntheticLambda2(wallpaperLocalColorExtractor, ImageWallpaper.this.mPages));
            }
        }

        public final void addLocalColorsAreas(List list) {
            WallpaperLocalColorExtractor wallpaperLocalColorExtractor = this.mWallpaperLocalColorExtractor;
            wallpaperLocalColorExtractor.getClass();
            if (list.size() > 0) {
                wallpaperLocalColorExtractor.mLongExecutor.execute(new WallpaperLocalColorExtractor$$ExternalSyntheticLambda3(wallpaperLocalColorExtractor, list, 0));
            } else {
                Log.w("WallpaperLocalColorExtractor", "Attempt to add colors with an empty list");
            }
        }

        public final void drawFrameInternal() {
            Bitmap bitmap;
            if (isBitmapLoaded()) {
                synchronized (this.mSurfaceLock) {
                    try {
                        if (this.mSurfaceHolder == null) {
                            int i = ImageWallpaper.$r8$clinit;
                            Log.i("ImageWallpaper", "Surface released before the image could be drawn");
                            return;
                        } else {
                            this.mBitmapUsages++;
                            drawFrameOnCanvas(this.mBitmap);
                            reportEngineShown(false);
                            unloadBitmapIfNotUsedInternal();
                            return;
                        }
                    } finally {
                    }
                }
            }
            Trace.beginSection("WPMS.ImageWallpaper.CanvasEngine#loadWallpaper");
            try {
                Trace.beginSection("WPMS.getBitmapAsUser");
                bitmap = this.mWallpaperManager.getBitmapAsUser(((UserTrackerImpl) ImageWallpaper.this.mUserTracker).getUserId(), false, getWallpaperFlags() == 2 ? 2 : 1, true);
                if (bitmap != null && bitmap.getByteCount() > RecordingCanvas.MAX_BITMAP_SIZE) {
                    throw new RuntimeException("Wallpaper is too large to draw!");
                }
            } catch (OutOfMemoryError | RuntimeException e) {
                int i2 = ImageWallpaper.$r8$clinit;
                Log.w("ImageWallpaper", "Unable to load default wallpaper!", e);
                bitmap = null;
            } catch (OutOfMemoryError | RuntimeException e2) {
                int i3 = ImageWallpaper.$r8$clinit;
                Log.w("ImageWallpaper", "Unable to load wallpaper!", e2);
                Trace.beginSection("WPMS.clearWallpaper");
                this.mWallpaperManager.clearWallpaper(getWallpaperFlags(), ((UserTrackerImpl) ImageWallpaper.this.mUserTracker).getUserId());
                Trace.beginSection("WPMS.getBitmapAsUser_defaultWallpaper");
                bitmap = this.mWallpaperManager.getBitmapAsUser(((UserTrackerImpl) ImageWallpaper.this.mUserTracker).getUserId(), false, getWallpaperFlags() == 2 ? 2 : 1, true);
                Trace.endSection();
            } catch (Throwable th) {
                throw th;
            } finally {
                Trace.endSection();
            }
            Trace.endSection();
            if (bitmap == null) {
                int i4 = ImageWallpaper.$r8$clinit;
                Log.w("ImageWallpaper", "Could not load bitmap");
            } else if (bitmap.isRecycled()) {
                int i5 = ImageWallpaper.$r8$clinit;
                Log.e("ImageWallpaper", "Attempt to load a recycled bitmap");
            } else {
                Bitmap bitmap2 = this.mBitmap;
                if (bitmap2 != bitmap) {
                    if (bitmap2 != null) {
                        Trace.beginSection("WPMS.mBitmap.recycle");
                        this.mBitmap.recycle();
                        Trace.endSection();
                    }
                    this.mBitmap = bitmap;
                    Trace.beginSection("WPMS.wallpaperSupportsWcg");
                    this.mWideColorGamut = this.mWallpaperManager.wallpaperSupportsWcg(getWallpaperFlags() == 2 ? 2 : 1);
                    Trace.endSection();
                    this.mBitmapUsages += 2;
                    Trace.beginSection("WPMS.recomputeColorExtractorMiniBitmap");
                    recomputeColorExtractorMiniBitmap();
                    Trace.endSection();
                    Trace.beginSection("WPMS.drawFrameInternal");
                    drawFrameInternal();
                    Trace.endSection();
                    ImageWallpaper.this.mLongExecutor.executeDelayed(new ImageWallpaper$CanvasEngine$$ExternalSyntheticLambda0(this, 0), 2000L);
                }
                int i6 = ImageWallpaper.$r8$clinit;
                Log.e("ImageWallpaper", "Loaded a bitmap that was already loaded");
            }
            reportEngineShown(false);
        }

        public void drawFrameOnCanvas(Bitmap bitmap) {
            Canvas canvas;
            Trace.beginSection("ImageWallpaper.CanvasEngine#drawFrame");
            Surface surface = this.mSurfaceHolder.getSurface();
            try {
                canvas = this.mWideColorGamut ? surface.lockHardwareWideColorGamutCanvas() : surface.lockHardwareCanvas();
            } catch (IllegalStateException e) {
                int i = ImageWallpaper.$r8$clinit;
                Log.w("ImageWallpaper", "Unable to lock canvas", e);
                canvas = null;
            }
            if (canvas != null) {
                try {
                    canvas.drawBitmap(bitmap, (Rect) null, this.mSurfaceHolder.getSurfaceFrame(), (Paint) null);
                    this.mDrawn = true;
                } finally {
                    surface.unlockCanvasAndPost(canvas);
                }
            }
            Trace.endSection();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            String str2;
            super.dump(str, fileDescriptor, printWriter, strArr);
            printWriter.print(str);
            printWriter.print("Engine=");
            printWriter.println(this);
            printWriter.print(str);
            printWriter.print("valid surface=");
            String str3 = "null";
            printWriter.println((getSurfaceHolder() == null || getSurfaceHolder().getSurface() == null) ? "null" : Boolean.valueOf(getSurfaceHolder().getSurface().isValid()));
            printWriter.print(str);
            printWriter.print("surface frame=");
            printWriter.println(getSurfaceHolder() != null ? getSurfaceHolder().getSurfaceFrame() : "null");
            printWriter.print(str);
            printWriter.print("bitmap=");
            Bitmap bitmap = this.mBitmap;
            if (bitmap == null) {
                str2 = "null";
            } else if (bitmap.isRecycled()) {
                str2 = "recycled";
            } else {
                str2 = this.mBitmap.getWidth() + "x" + this.mBitmap.getHeight();
            }
            printWriter.println(str2);
            WallpaperLocalColorExtractor wallpaperLocalColorExtractor = this.mWallpaperLocalColorExtractor;
            wallpaperLocalColorExtractor.getClass();
            printWriter.print(str);
            printWriter.print("display=");
            printWriter.println(wallpaperLocalColorExtractor.mDisplayWidth + "x" + wallpaperLocalColorExtractor.mDisplayHeight);
            printWriter.print(str);
            printWriter.print("mPages=");
            printWriter.println(wallpaperLocalColorExtractor.mPages);
            printWriter.print(str);
            printWriter.print("bitmap dimensions=");
            printWriter.println(wallpaperLocalColorExtractor.mBitmapWidth + "x" + wallpaperLocalColorExtractor.mBitmapHeight);
            printWriter.print(str);
            printWriter.print("bitmap=");
            Bitmap bitmap2 = wallpaperLocalColorExtractor.mMiniBitmap;
            if (bitmap2 != null) {
                if (bitmap2.isRecycled()) {
                    str3 = "recycled";
                } else {
                    str3 = wallpaperLocalColorExtractor.mMiniBitmap.getWidth() + "x" + wallpaperLocalColorExtractor.mMiniBitmap.getHeight();
                }
            }
            printWriter.println(str3);
            printWriter.print(str);
            printWriter.print("PendingRegions size=");
            printWriter.print(((ArrayList) wallpaperLocalColorExtractor.mPendingRegions).size());
            printWriter.print(str);
            printWriter.print("ProcessedRegions size=");
            printWriter.print(((ArraySet) wallpaperLocalColorExtractor.mProcessedRegions).size());
        }

        public final void getDisplaySizeAndUpdateColorExtractor() {
            Rect bounds = ((WindowManager) getDisplayContext().getSystemService(WindowManager.class)).getCurrentWindowMetrics().getBounds();
            final WallpaperLocalColorExtractor wallpaperLocalColorExtractor = this.mWallpaperLocalColorExtractor;
            final int width = bounds.width();
            final int height = bounds.height();
            wallpaperLocalColorExtractor.mLongExecutor.execute(new Runnable() { // from class: com.android.systemui.wallpapers.WallpaperLocalColorExtractor$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    WallpaperLocalColorExtractor wallpaperLocalColorExtractor2 = WallpaperLocalColorExtractor.this;
                    int i = width;
                    int i2 = height;
                    synchronized (wallpaperLocalColorExtractor2.mLock) {
                        try {
                            if (i == wallpaperLocalColorExtractor2.mDisplayWidth && i2 == wallpaperLocalColorExtractor2.mDisplayHeight) {
                                return;
                            }
                            wallpaperLocalColorExtractor2.mDisplayWidth = i;
                            wallpaperLocalColorExtractor2.mDisplayHeight = i2;
                            wallpaperLocalColorExtractor2.processLocalColorsInternal();
                        } finally {
                        }
                    }
                }
            });
        }

        public boolean isBitmapLoaded() {
            Bitmap bitmap = this.mBitmap;
            return (bitmap == null || bitmap.isRecycled()) ? false : true;
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public final WallpaperColors onComputeColors() {
            return null;
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public final void onCreate(SurfaceHolder surfaceHolder) {
            Trace.beginSection("ImageWallpaper.CanvasEngine#onCreate");
            WallpaperManager wallpaperManager = (WallpaperManager) getDisplayContext().getSystemService(WallpaperManager.class);
            this.mWallpaperManager = wallpaperManager;
            this.mSurfaceHolder = surfaceHolder;
            Rect peekBitmapDimensionsAsUser = wallpaperManager.peekBitmapDimensionsAsUser(getWallpaperFlags() != 2 ? 1 : 2, true, ((UserTrackerImpl) ImageWallpaper.this.mUserTracker).getUserId());
            this.mSurfaceHolder.setFixedSize(Math.max(128, peekBitmapDimensionsAsUser.width()), Math.max(128, peekBitmapDimensionsAsUser.height()));
            ((DisplayManager) getDisplayContext().getSystemService(DisplayManager.class)).registerDisplayListener(this, null);
            getDisplaySizeAndUpdateColorExtractor();
            Trace.endSection();
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public final void onDestroy() {
            DisplayManager displayManager;
            Context displayContext = getDisplayContext();
            if (displayContext != null && (displayManager = (DisplayManager) displayContext.getSystemService(DisplayManager.class)) != null) {
                displayManager.unregisterDisplayListener(this);
            }
            final WallpaperLocalColorExtractor wallpaperLocalColorExtractor = this.mWallpaperLocalColorExtractor;
            wallpaperLocalColorExtractor.mLongExecutor.execute(new Runnable() { // from class: com.android.systemui.wallpapers.WallpaperLocalColorExtractor$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WallpaperLocalColorExtractor wallpaperLocalColorExtractor2 = WallpaperLocalColorExtractor.this;
                    synchronized (wallpaperLocalColorExtractor2.mLock) {
                        try {
                            Bitmap bitmap = wallpaperLocalColorExtractor2.mMiniBitmap;
                            if (bitmap != null) {
                                bitmap.recycle();
                                wallpaperLocalColorExtractor2.mMiniBitmap = null;
                            }
                            ((ArraySet) wallpaperLocalColorExtractor2.mProcessedRegions).clear();
                            wallpaperLocalColorExtractor2.mPendingRegions.clear();
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            });
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayChanged(int i) {
            Trace.beginSection("ImageWallpaper.CanvasEngine#onDisplayChanged");
            try {
                if (i == getDisplayContext().getDisplayId()) {
                    getDisplaySizeAndUpdateColorExtractor();
                }
            } finally {
                Trace.endSection();
            }
        }

        public void onMiniBitmapUpdated() {
            ((ExecutorImpl) ImageWallpaper.this.mLongExecutor).execute(new ImageWallpaper$CanvasEngine$$ExternalSyntheticLambda0(this, 0));
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public final void onOffsetsChanged(float f, float f2, float f3, float f4, int i, int i2) {
            int round = (f3 <= 0.0f || f3 > 1.0f) ? 1 : Math.round(1.0f / f3) + 1;
            if (round == ImageWallpaper.this.mPages && ImageWallpaper.this.mPagesComputed) {
                return;
            }
            ImageWallpaper.this.mPages = round;
            ImageWallpaper imageWallpaper = ImageWallpaper.this;
            imageWallpaper.mPagesComputed = true;
            WallpaperLocalColorExtractor wallpaperLocalColorExtractor = this.mWallpaperLocalColorExtractor;
            wallpaperLocalColorExtractor.mLongExecutor.execute(new WallpaperLocalColorExtractor$$ExternalSyntheticLambda2(wallpaperLocalColorExtractor, imageWallpaper.mPages));
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public final void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
            synchronized (this.mSurfaceLock) {
                this.mSurfaceHolder = null;
            }
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public final void onSurfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
            ((ExecutorImpl) ImageWallpaper.this.mLongExecutor).execute(new ImageWallpaper$CanvasEngine$$ExternalSyntheticLambda0(this, 1));
        }

        public void recomputeColorExtractorMiniBitmap() {
            WallpaperLocalColorExtractor wallpaperLocalColorExtractor = this.mWallpaperLocalColorExtractor;
            wallpaperLocalColorExtractor.mLongExecutor.execute(new WallpaperLocalColorExtractor$$ExternalSyntheticLambda3(wallpaperLocalColorExtractor, this.mBitmap, 2));
        }

        public final void removeLocalColorsAreas(List list) {
            WallpaperLocalColorExtractor wallpaperLocalColorExtractor = this.mWallpaperLocalColorExtractor;
            wallpaperLocalColorExtractor.mLongExecutor.execute(new WallpaperLocalColorExtractor$$ExternalSyntheticLambda3(wallpaperLocalColorExtractor, list, 1));
        }

        public final boolean shouldWaitForEngineShown() {
            return true;
        }

        public final boolean shouldZoomOutWallpaper() {
            return true;
        }

        public final boolean supportsLocalColorExtraction() {
            return true;
        }

        public final void unloadBitmapIfNotUsedInternal() {
            int i = this.mBitmapUsages - 1;
            this.mBitmapUsages = i;
            if (i <= 0) {
                this.mBitmapUsages = 0;
                Trace.beginSection("ImageWallpaper.CanvasEngine#unloadBitmap");
                Bitmap bitmap = this.mBitmap;
                if (bitmap != null) {
                    bitmap.recycle();
                }
                this.mBitmap = null;
                synchronized (this.mSurfaceLock) {
                    try {
                        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
                        if (surfaceHolder != null) {
                            surfaceHolder.getSurface().hwuiDestroy();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                this.mWallpaperManager.forgetLoadedWallpaper();
                Trace.endSection();
            }
        }

        public final void onDimAmountChanged(float f) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayRemoved(int i) {
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public final void onSurfaceCreated(SurfaceHolder surfaceHolder) {
        }

        @Override // android.service.wallpaper.WallpaperService.Engine
        public final void onSurfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }
    }
}
