package com.android.wm.shell.windowdecor;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.freeform.FreeformTaskTransitionHandler;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TaskOperations {
    public final Context mContext;
    public final SyncTransactionQueue mSyncQueue;
    public final FreeformTaskTransitionHandler mTransitionStarter;

    public TaskOperations(FreeformTaskTransitionHandler freeformTaskTransitionHandler, Context context, SyncTransactionQueue syncTransactionQueue) {
        this.mTransitionStarter = freeformTaskTransitionHandler;
        this.mContext = context;
        this.mSyncQueue = syncTransactionQueue;
    }

    public final IBinder minimizeTask(WindowContainerToken windowContainerToken, WindowContainerTransaction windowContainerTransaction) {
        windowContainerTransaction.reorder(windowContainerToken, false);
        if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
            this.mSyncQueue.queue(windowContainerTransaction);
            return null;
        }
        FreeformTaskTransitionHandler freeformTaskTransitionHandler = this.mTransitionStarter;
        IBinder startTransition = freeformTaskTransitionHandler.mTransitions.startTransition(1020, windowContainerTransaction, freeformTaskTransitionHandler);
        freeformTaskTransitionHandler.mPendingTransitionTokens.add(startTransition);
        return startTransition;
    }

    public final void sendBackEvent(int i, int i2) {
        long uptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(uptimeMillis, uptimeMillis, i, 4, 0, 0, -1, 0, 72, 257);
        keyEvent.setDisplayId(i2);
        if (((InputManager) this.mContext.getSystemService(InputManager.class)).injectInputEvent(keyEvent, 0)) {
            return;
        }
        Log.e("TaskOperations", "Inject input event fail");
    }
}
