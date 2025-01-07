package com.android.wm.shell.common;

import android.util.Slog;
import android.view.SurfaceControl;
import android.window.WindowContainerTransaction;
import android.window.WindowContainerTransactionCallback;
import android.window.WindowOrganizer;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransactionPool;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SyncTransactionQueue {
    public final ShellExecutor mMainExecutor;
    public final TransactionPool mTransactionPool;
    public final ArrayList mQueue = new ArrayList();
    public SyncCallback mInFlight = null;
    public final ArrayList mRunnables = new ArrayList();
    public final SyncTransactionQueue$$ExternalSyntheticLambda0 mOnReplyTimeout = new Runnable() { // from class: com.android.wm.shell.common.SyncTransactionQueue$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            SyncTransactionQueue syncTransactionQueue = SyncTransactionQueue.this;
            synchronized (syncTransactionQueue.mQueue) {
                try {
                    SyncTransactionQueue.SyncCallback syncCallback = syncTransactionQueue.mInFlight;
                    if (syncCallback != null && syncTransactionQueue.mQueue.contains(syncCallback)) {
                        Slog.w("SyncTransactionQueue", "Sync Transaction timed-out: " + syncTransactionQueue.mInFlight.mWCT);
                        SyncTransactionQueue.SyncCallback syncCallback2 = syncTransactionQueue.mInFlight;
                        syncCallback2.onTransactionReady(syncCallback2.mId, new SurfaceControl.Transaction());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SyncCallback extends WindowContainerTransactionCallback {
        public int mId = -1;
        public final WindowContainerTransaction mWCT;

        public SyncCallback(WindowContainerTransaction windowContainerTransaction) {
            this.mWCT = windowContainerTransaction;
        }

        public final void onTransactionReady(final int i, final SurfaceControl.Transaction transaction) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL, -2814372736326485080L, 1, Long.valueOf(i));
            }
            ((HandlerExecutor) SyncTransactionQueue.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.common.SyncTransactionQueue$SyncCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SyncTransactionQueue.SyncCallback syncCallback = SyncTransactionQueue.SyncCallback.this;
                    int i2 = i;
                    SurfaceControl.Transaction transaction2 = transaction;
                    synchronized (SyncTransactionQueue.this.mQueue) {
                        try {
                            if (syncCallback.mId != i2) {
                                Slog.e("SyncTransactionQueue", "Got an unexpected onTransactionReady. Expected " + syncCallback.mId + " but got " + i2);
                                return;
                            }
                            SyncTransactionQueue syncTransactionQueue = SyncTransactionQueue.this;
                            syncTransactionQueue.mInFlight = null;
                            ((HandlerExecutor) syncTransactionQueue.mMainExecutor).removeCallbacks(syncTransactionQueue.mOnReplyTimeout);
                            SyncTransactionQueue.this.mQueue.remove(syncCallback);
                            SyncTransactionQueue syncTransactionQueue2 = SyncTransactionQueue.this;
                            int size = syncTransactionQueue2.mRunnables.size();
                            for (int i3 = 0; i3 < size; i3++) {
                                ((SyncTransactionQueue.TransactionRunnable) syncTransactionQueue2.mRunnables.get(i3)).runWithTransaction(transaction2);
                            }
                            syncTransactionQueue2.mRunnables.subList(0, size).clear();
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_enabled[1]) {
                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL, -1043029834526673881L, 1, Long.valueOf(i2));
                            }
                            transaction2.apply();
                            transaction2.close();
                            if (!SyncTransactionQueue.this.mQueue.isEmpty()) {
                                ((SyncTransactionQueue.SyncCallback) SyncTransactionQueue.this.mQueue.get(0)).send();
                            }
                        } finally {
                        }
                    }
                }
            });
        }

        public final void send() {
            SyncTransactionQueue syncTransactionQueue = SyncTransactionQueue.this;
            SyncCallback syncCallback = syncTransactionQueue.mInFlight;
            if (syncCallback == this) {
                return;
            }
            if (syncCallback == null) {
                syncTransactionQueue.mInFlight = this;
                this.mId = new WindowOrganizer().applySyncTransaction(this.mWCT, this);
                SyncTransactionQueue syncTransactionQueue2 = SyncTransactionQueue.this;
                ((HandlerExecutor) syncTransactionQueue2.mMainExecutor).executeDelayed(syncTransactionQueue2.mOnReplyTimeout, 5300L);
                return;
            }
            throw new IllegalStateException("Sync Transactions must be serialized. In Flight: " + SyncTransactionQueue.this.mInFlight.mId + " - " + SyncTransactionQueue.this.mInFlight.mWCT);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TransactionRunnable {
        void runWithTransaction(SurfaceControl.Transaction transaction);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.wm.shell.common.SyncTransactionQueue$$ExternalSyntheticLambda0] */
    public SyncTransactionQueue(TransactionPool transactionPool, ShellExecutor shellExecutor) {
        this.mTransactionPool = transactionPool;
        this.mMainExecutor = shellExecutor;
    }

    public final void queue(WindowContainerTransaction windowContainerTransaction) {
        if (windowContainerTransaction.isEmpty()) {
            return;
        }
        SyncCallback syncCallback = new SyncCallback(windowContainerTransaction);
        synchronized (this.mQueue) {
            try {
                this.mQueue.add(syncCallback);
                if (this.mQueue.size() == 1) {
                    syncCallback.send();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void runInSync(TransactionRunnable transactionRunnable) {
        synchronized (this.mQueue) {
            try {
                if (this.mInFlight != null) {
                    this.mRunnables.add(transactionRunnable);
                    return;
                }
                SurfaceControl.Transaction acquire = this.mTransactionPool.acquire();
                transactionRunnable.runWithTransaction(acquire);
                acquire.apply();
                this.mTransactionPool.release(acquire);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
