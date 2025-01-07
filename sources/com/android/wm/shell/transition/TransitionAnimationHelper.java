package com.android.wm.shell.transition;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.window.ScreenCapture;
import android.window.TransitionInfo;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransitionUtil;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TransitionAnimationHelper {
    public static void createExtensionSurface(SurfaceControl surfaceControl, Rect rect, Rect rect2, int i, int i2, String str, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        SurfaceControl build = new SurfaceControl.Builder().setName(str).setParent(surfaceControl).setHidden(true).setCallsite("TransitionAnimationHelper#createExtensionSurface").setOpaque(true).setBufferSize(rect2.width(), rect2.height()).build();
        ScreenCapture.ScreenshotHardwareBuffer captureLayers = ScreenCapture.captureLayers(new ScreenCapture.LayerCaptureArgs.Builder(surfaceControl).setSourceCrop(rect).setFrameScale(1.0f).setPixelFormat(1).setChildrenOnly(true).setAllowProtected(false).setCaptureSecureLayers(true).build());
        if (captureLayers == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[4]) {
                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -7898785707793238235L, 0, null);
                return;
            }
            return;
        }
        Bitmap asBitmap = captureLayers.asBitmap();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(asBitmap, tileMode, tileMode);
        Paint paint = new Paint();
        paint.setShader(bitmapShader);
        Surface surface = new Surface(build);
        Canvas lockHardwareCanvas = surface.lockHardwareCanvas();
        lockHardwareCanvas.drawRect(rect2, paint);
        surface.unlockCanvasAndPost(lockHardwareCanvas);
        surface.release();
        transaction.setLayer(build, Integer.MIN_VALUE);
        transaction.setPosition(build, i, i2);
        transaction.setVisibility(build, true);
        transaction2.remove(build);
    }

    public static void edgeExtendWindow(TransitionInfo.Change change, Animation animation, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        if ((change.getFlags() & 8) != 0) {
            return;
        }
        Transformation transformation = new Transformation();
        animation.getTransformationAt(0.0f, transformation);
        Transformation transformation2 = new Transformation();
        animation.getTransformationAt(1.0f, transformation2);
        Insets min = Insets.min(transformation.getInsets(), transformation2.getInsets());
        int max = Math.max(change.getStartAbsBounds().height(), change.getEndAbsBounds().height());
        int max2 = Math.max(change.getStartAbsBounds().width(), change.getEndAbsBounds().width());
        if (min.left < 0) {
            createExtensionSurface(change.getLeash(), new Rect(0, 0, 1, max), new Rect(0, 0, -min.left, max), min.left, 0, "Left Edge Extension", transaction, transaction2);
        }
        if (min.top < 0) {
            createExtensionSurface(change.getLeash(), new Rect(0, 0, max2, 1), new Rect(0, 0, max2, -min.top), 0, min.top, "Top Edge Extension", transaction, transaction2);
        }
        if (min.right < 0) {
            createExtensionSurface(change.getLeash(), new Rect(max2 - 1, 0, max2, max), new Rect(0, 0, -min.right, max), max2, 0, "Right Edge Extension", transaction, transaction2);
        }
        if (min.bottom < 0) {
            createExtensionSurface(change.getLeash(), new Rect(0, max - 1, max2, max), new Rect(0, 0, max2, -min.bottom), min.left, max, "Bottom Edge Extension", transaction, transaction2);
        }
    }

    public static int getTransitionTypeFromInfo(TransitionInfo transitionInfo) {
        int type = transitionInfo.getType();
        if ((type == 13 || type == 14) && !transitionInfo.getChanges().isEmpty()) {
            return TransitionUtil.isOpeningMode(((TransitionInfo.Change) transitionInfo.getChanges().get(0)).getMode()) ? 1 : 2;
        }
        if (type == 1) {
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                if ((change.getTaskInfo() != null || change.hasFlags(32)) && !TransitionUtil.isOrderOnly(change)) {
                    return type;
                }
                if (change.getTaskInfo() == null || !change.hasFlags(65826)) {
                    if (change.getMode() == 1) {
                    }
                }
            }
            return 2;
        }
        return type;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isCoveredByOpaqueFullscreenChange(android.window.TransitionInfo.Change r2, android.window.TransitionInfo r3) {
        /*
            java.util.List r3 = r3.getChanges()
            java.util.Iterator r3 = r3.iterator()
        L8:
            boolean r0 = r3.hasNext()
            r1 = 0
            if (r0 == 0) goto L31
            java.lang.Object r0 = r3.next()
            android.window.TransitionInfo$Change r0 = (android.window.TransitionInfo.Change) r0
            if (r0 != r2) goto L18
            return r1
        L18:
            int r1 = r0.getFlags()
            r1 = r1 & 4
            if (r1 != 0) goto L8
            android.app.ActivityManager$RunningTaskInfo r1 = r0.getTaskInfo()
            if (r1 == 0) goto L8
            android.app.ActivityManager$RunningTaskInfo r0 = r0.getTaskInfo()
            int r0 = r0.getWindowingMode()
            r1 = 1
            if (r0 != r1) goto L8
        L31:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.transition.TransitionAnimationHelper.isCoveredByOpaqueFullscreenChange(android.window.TransitionInfo$Change, android.window.TransitionInfo):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0158  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.view.animation.Animation loadAttributeAnimation(int r17, android.window.TransitionInfo r18, android.window.TransitionInfo.Change r19, int r20, com.android.internal.policy.TransitionAnimation r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.transition.TransitionAnimationHelper.loadAttributeAnimation(int, android.window.TransitionInfo, android.window.TransitionInfo$Change, int, com.android.internal.policy.TransitionAnimation, boolean):android.view.animation.Animation");
    }
}
