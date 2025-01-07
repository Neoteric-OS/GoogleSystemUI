package com.android.wm.shell.compatui;

import android.util.Log;
import android.view.SurfaceControl;
import com.android.wm.shell.common.SyncTransactionQueue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CompatUIWindowManagerAbstract$$ExternalSyntheticLambda2 implements SyncTransactionQueue.TransactionRunnable {
    public final /* synthetic */ CompatUIWindowManagerAbstract f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ CompatUIWindowManagerAbstract$$ExternalSyntheticLambda2(CompatUIWindowManagerAbstract compatUIWindowManagerAbstract, int i, int i2) {
        this.f$0 = compatUIWindowManagerAbstract;
        this.f$1 = i;
        this.f$2 = i2;
    }

    @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
    public final void runWithTransaction(SurfaceControl.Transaction transaction) {
        CompatUIWindowManagerAbstract compatUIWindowManagerAbstract = this.f$0;
        SurfaceControl surfaceControl = compatUIWindowManagerAbstract.mLeash;
        if (surfaceControl == null || !surfaceControl.isValid()) {
            Log.w(compatUIWindowManagerAbstract.getClass().getSimpleName(), "The leash has been released.");
        } else {
            transaction.setPosition(compatUIWindowManagerAbstract.mLeash, this.f$1, this.f$2);
        }
    }
}
