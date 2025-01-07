package com.android.wm.shell.shared;

import android.graphics.Point;
import android.util.RotationUtils;
import android.view.SurfaceControl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CounterRotator {
    public SurfaceControl mSurface = null;

    public final void setup(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, int i, float f, float f2) {
        if (i == 0) {
            return;
        }
        SurfaceControl build = new SurfaceControl.Builder().setName("Transition Unrotate").setContainerLayer().setParent(surfaceControl).build();
        this.mSurface = build;
        RotationUtils.rotateSurface(transaction, build, i);
        Point point = new Point(0, 0);
        if (i % 2 != 0) {
            f2 = f;
            f = f2;
        }
        RotationUtils.rotatePoint(point, i, (int) f, (int) f2);
        transaction.setPosition(this.mSurface, point.x, point.y);
        transaction.show(this.mSurface);
    }
}
