package com.android.systemui.communal.util;

import android.view.IWindowManager;
import android.view.WindowManagerGlobal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DensityUtils {
    public static final IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        /* renamed from: getAdjustedDp-u2uoSUM, reason: not valid java name */
        public static float m799getAdjustedDpu2uoSUM(int i) {
            return (DensityUtils.windowManagerService != null ? r0.getInitialDisplayDensity(0) / r0.getBaseDisplayDensity(0) : 1.0f) * i;
        }
    }
}
