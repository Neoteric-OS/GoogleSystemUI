package com.android.wm.shell.windowdecor.additionalviewcontainer;

import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AdditionalViewHostViewContainer extends AdditionalViewContainer {
    public final Supplier transactionSupplier;
    public final SurfaceControl windowSurface;
    public final SurfaceControlViewHost windowViewHost;

    public AdditionalViewHostViewContainer(SurfaceControl surfaceControl, SurfaceControlViewHost surfaceControlViewHost, Supplier supplier) {
        this.windowSurface = surfaceControl;
        this.windowViewHost = surfaceControlViewHost;
        this.transactionSupplier = supplier;
    }

    public final void releaseView() {
        this.windowViewHost.release();
        SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) this.transactionSupplier.get();
        transaction.remove(this.windowSurface);
        transaction.apply();
    }
}
