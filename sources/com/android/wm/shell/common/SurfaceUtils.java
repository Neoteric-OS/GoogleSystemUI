package com.android.wm.shell.common;

import android.view.SurfaceControl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SurfaceUtils {
    public static SurfaceControl makeColorLayer(SurfaceControl surfaceControl, String str) {
        return new SurfaceControl.Builder().setParent(surfaceControl).setColorLayer().setName(str).setCallsite("SurfaceUtils.makeColorLayer").build();
    }
}
