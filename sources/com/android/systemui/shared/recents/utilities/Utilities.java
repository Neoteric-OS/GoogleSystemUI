package com.android.systemui.shared.recents.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.WindowManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Utilities {
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0009, code lost:
    
        if (r3 != 3) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int calculateBackDispositionHints(int r2, int r3, boolean r4, boolean r5) {
        /*
            r0 = 2
            if (r3 == 0) goto Lf
            r1 = 1
            if (r3 == r1) goto Lf
            if (r3 == r0) goto Lf
            r1 = 3
            if (r3 == r1) goto Lc
            goto L13
        Lc:
            r2 = r2 & (-2)
            goto L13
        Lf:
            if (r4 == 0) goto Lc
            r2 = r2 | 1
        L13:
            if (r4 == 0) goto L17
            r2 = r2 | r0
            goto L19
        L17:
            r2 = r2 & (-3)
        L19:
            if (r5 == 0) goto L1e
            r2 = r2 | 4
            goto L20
        L1e:
            r2 = r2 & (-5)
        L20:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shared.recents.utilities.Utilities.calculateBackDispositionHints(int, int, boolean, boolean):int");
    }

    public static boolean isLargeScreen(Context context) {
        return isLargeScreen(context.getResources(), (WindowManager) context.getSystemService(WindowManager.class));
    }

    public static boolean isLargeScreen(Resources resources, WindowManager windowManager) {
        Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();
        return ((float) Math.min(bounds.width(), bounds.height())) / (((float) resources.getConfiguration().densityDpi) / 160.0f) >= 600.0f;
    }
}
