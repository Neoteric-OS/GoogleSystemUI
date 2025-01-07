package com.android.wm.shell.windowdecor;

import android.view.SurfaceControl;
import android.window.WindowContainerTransaction;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DesktopModeWindowDecoration$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Supplier
    public final Object get() {
        switch (this.$r8$classId) {
            case 0:
                return new SurfaceControl.Builder();
            case 1:
                return new WindowContainerTransaction();
            default:
                return new SurfaceControl();
        }
    }
}
