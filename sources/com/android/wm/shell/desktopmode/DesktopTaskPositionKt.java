package com.android.wm.shell.desktopmode;

import android.graphics.Rect;
import com.android.wm.shell.desktopmode.DesktopTaskPosition;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DesktopTaskPositionKt {
    public static final DesktopTaskPosition getDesktopTaskPosition(Rect rect, Rect rect2) {
        int i = rect.top;
        int i2 = rect2.top;
        if (i == i2 && rect.left == rect2.left && rect.bottom != rect2.bottom) {
            return DesktopTaskPosition.TopLeft.INSTANCE;
        }
        if (i == i2 && rect.right == rect2.right && rect.bottom != rect2.bottom) {
            return DesktopTaskPosition.TopRight.INSTANCE;
        }
        int i3 = rect.bottom;
        int i4 = rect2.bottom;
        return (i3 == i4 && rect.left == rect2.left && i != i2) ? DesktopTaskPosition.BottomLeft.INSTANCE : (i3 == i4 && rect.right == rect2.right && i != i2) ? DesktopTaskPosition.BottomRight.INSTANCE : DesktopTaskPosition.Center.INSTANCE;
    }
}
