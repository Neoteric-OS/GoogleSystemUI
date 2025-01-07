package com.android.wm.shell.common;

import android.graphics.Rect;
import android.view.SurfaceControl;
import android.window.ScreenCapture;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ScreenshotUtils {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BufferConsumer implements Consumer {
        public int mLayer;
        public SurfaceControl mParentSurfaceControl;
        public SurfaceControl mScreenshot;
        public SurfaceControl.Transaction mTransaction;

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            ScreenCapture.ScreenshotHardwareBuffer screenshotHardwareBuffer = (ScreenCapture.ScreenshotHardwareBuffer) obj;
            if (screenshotHardwareBuffer == null || screenshotHardwareBuffer.getHardwareBuffer() == null) {
                return;
            }
            SurfaceControl build = new SurfaceControl.Builder().setName("ScreenshotUtils screenshot").setFormat(-3).setSecure(screenshotHardwareBuffer.containsSecureLayers()).setCallsite("ScreenshotUtils.takeScreenshot").setBLASTLayer().build();
            this.mScreenshot = build;
            this.mTransaction.setBuffer(build, screenshotHardwareBuffer.getHardwareBuffer());
            this.mTransaction.setColorSpace(this.mScreenshot, screenshotHardwareBuffer.getColorSpace());
            this.mTransaction.reparent(this.mScreenshot, this.mParentSurfaceControl);
            this.mTransaction.setLayer(this.mScreenshot, this.mLayer);
            if (screenshotHardwareBuffer.containsHdrLayers()) {
                this.mTransaction.setDimmingEnabled(this.mScreenshot, false);
            }
            this.mTransaction.show(this.mScreenshot);
            this.mTransaction.apply();
        }
    }

    public static SurfaceControl takeScreenshot(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, SurfaceControl surfaceControl2, Rect rect, int i) {
        BufferConsumer bufferConsumer = new BufferConsumer();
        bufferConsumer.mScreenshot = null;
        bufferConsumer.mTransaction = transaction;
        bufferConsumer.mParentSurfaceControl = surfaceControl2;
        bufferConsumer.mLayer = i;
        bufferConsumer.accept(ScreenCapture.captureLayers(new ScreenCapture.LayerCaptureArgs.Builder(surfaceControl).setSourceCrop(rect).setCaptureSecureLayers(true).setAllowProtected(true).setHintForSeamlessTransition(true).build()));
        return bufferConsumer.mScreenshot;
    }
}
