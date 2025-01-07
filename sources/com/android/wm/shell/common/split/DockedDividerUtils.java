package com.android.wm.shell.common.split;

import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DockedDividerUtils {
    public static void sanitizeStackBounds(Rect rect, boolean z) {
        if (z) {
            int i = rect.left;
            int i2 = rect.right;
            if (i >= i2) {
                rect.left = i2 - 1;
            }
            int i3 = rect.top;
            int i4 = rect.bottom;
            if (i3 >= i4) {
                rect.top = i4 - 1;
                return;
            }
            return;
        }
        int i5 = rect.right;
        int i6 = rect.left;
        if (i5 <= i6) {
            rect.right = i6 + 1;
        }
        int i7 = rect.bottom;
        int i8 = rect.top;
        if (i7 <= i8) {
            rect.bottom = i8 + 1;
        }
    }
}
