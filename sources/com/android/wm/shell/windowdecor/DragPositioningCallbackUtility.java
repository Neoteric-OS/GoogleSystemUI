package com.android.wm.shell.windowdecor;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.window.flags.DesktopModeFlags;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DragPositioningCallbackUtility {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface DragStartListener {
        void onDragStart(int i);
    }

    public static PointF calculateDelta(float f, float f2, PointF pointF) {
        return new PointF(f - pointF.x, f2 - pointF.y);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0133 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean changeBounds(int r9, android.graphics.Rect r10, android.graphics.Rect r11, android.graphics.Rect r12, android.graphics.PointF r13, com.android.wm.shell.common.DisplayController r14, com.android.wm.shell.windowdecor.WindowDecoration r15) {
        /*
            Method dump skipped, instructions count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.windowdecor.DragPositioningCallbackUtility.changeBounds(int, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect, android.graphics.PointF, com.android.wm.shell.common.DisplayController, com.android.wm.shell.windowdecor.WindowDecoration):boolean");
    }

    public static boolean isSizeConstraintForDesktopModeEnabled(Context context) {
        return DesktopModeStatus.canEnterDesktopMode(context) && DesktopModeFlags.ENABLE_DESKTOP_WINDOWING_SIZE_CONSTRAINTS.isTrue();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean snapTaskBoundsIfNecessary(android.graphics.Rect r5, android.graphics.Rect r6) {
        /*
            int r0 = r6.width()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            int r0 = r5.left
            int r2 = r6.left
            r3 = 1
            if (r0 >= r2) goto L15
            int r2 = r2 - r0
            r5.offset(r2, r1)
        L13:
            r0 = r3
            goto L1f
        L15:
            int r2 = r6.right
            if (r0 <= r2) goto L1e
            int r2 = r2 - r0
            r5.offset(r2, r1)
            goto L13
        L1e:
            r0 = r1
        L1f:
            int r2 = r5.top
            int r4 = r6.top
            if (r2 >= r4) goto L2a
            int r4 = r4 - r2
            r5.offset(r1, r4)
            goto L34
        L2a:
            int r6 = r6.bottom
            if (r2 <= r6) goto L33
            int r6 = r6 - r2
            r5.offset(r1, r6)
            goto L34
        L33:
            r3 = r0
        L34:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.windowdecor.DragPositioningCallbackUtility.snapTaskBoundsIfNecessary(android.graphics.Rect, android.graphics.Rect):boolean");
    }

    public static void updateTaskBounds(Rect rect, Rect rect2, PointF pointF, float f, float f2) {
        float f3 = f - pointF.x;
        float f4 = f2 - pointF.y;
        rect.set(rect2);
        rect.offset((int) f3, (int) f4);
    }
}
