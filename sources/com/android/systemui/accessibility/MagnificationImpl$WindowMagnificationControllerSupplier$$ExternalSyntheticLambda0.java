package com.android.systemui.accessibility;

import android.content.Context;
import android.view.SurfaceControlViewHost;
import android.window.InputTransferToken;
import com.android.systemui.accessibility.MagnificationImpl;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DisplayIdIndexSupplier f$0;

    public /* synthetic */ MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0(DisplayIdIndexSupplier displayIdIndexSupplier, int i) {
        this.$r8$classId = i;
        this.f$0 = displayIdIndexSupplier;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        int i = this.$r8$classId;
        DisplayIdIndexSupplier displayIdIndexSupplier = this.f$0;
        switch (i) {
            case 0:
                MagnificationImpl.WindowMagnificationControllerSupplier windowMagnificationControllerSupplier = (MagnificationImpl.WindowMagnificationControllerSupplier) displayIdIndexSupplier;
                windowMagnificationControllerSupplier.getClass();
                Context context = windowMagnificationControllerSupplier.mContext;
                return new SurfaceControlViewHost(context, context.getDisplay(), new InputTransferToken(), "Magnification");
            default:
                MagnificationImpl.FullscreenMagnificationControllerSupplier fullscreenMagnificationControllerSupplier = (MagnificationImpl.FullscreenMagnificationControllerSupplier) displayIdIndexSupplier;
                fullscreenMagnificationControllerSupplier.getClass();
                Context context2 = fullscreenMagnificationControllerSupplier.mContext;
                return new SurfaceControlViewHost(context2, context2.getDisplay(), new InputTransferToken(), "Magnification");
        }
    }
}
