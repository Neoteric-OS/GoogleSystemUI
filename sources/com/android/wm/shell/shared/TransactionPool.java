package com.android.wm.shell.shared;

import android.util.Pools;
import android.view.SurfaceControl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TransactionPool {
    public final Pools.SynchronizedPool mTransactionPool = new Pools.SynchronizedPool(4);

    public final SurfaceControl.Transaction acquire() {
        SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) this.mTransactionPool.acquire();
        return transaction == null ? new SurfaceControl.Transaction() : transaction;
    }

    public final void release(SurfaceControl.Transaction transaction) {
        if (this.mTransactionPool.release(transaction)) {
            return;
        }
        transaction.close();
    }
}
