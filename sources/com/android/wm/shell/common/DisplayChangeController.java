package com.android.wm.shell.common;

import android.os.RemoteException;
import android.os.Trace;
import android.util.Slog;
import android.view.IDisplayChangeWindowCallback;
import android.view.IDisplayChangeWindowController;
import android.view.IWindowManager;
import android.window.DisplayAreaInfo;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.common.DisplayChangeController;
import com.android.wm.shell.sysui.ShellInit;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayChangeController {
    public final ShellExecutor mMainExecutor;
    public final IWindowManager mWmService;
    public final CopyOnWriteArrayList mDisplayChangeListener = new CopyOnWriteArrayList();
    public final DisplayChangeWindowControllerImpl mControllerImpl = new DisplayChangeWindowControllerImpl();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DisplayChangeWindowControllerImpl extends IDisplayChangeWindowController.Stub {
        public DisplayChangeWindowControllerImpl() {
        }

        public final void onDisplayChange(final int i, final int i2, final int i3, final DisplayAreaInfo displayAreaInfo, final IDisplayChangeWindowCallback iDisplayChangeWindowCallback) {
            if (Trace.isTagEnabled(32L)) {
                Trace.beginAsyncSection("HandleRemoteDisplayChange", iDisplayChangeWindowCallback.hashCode());
            }
            ((HandlerExecutor) DisplayChangeController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.common.DisplayChangeController$DisplayChangeWindowControllerImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DisplayChangeController.DisplayChangeWindowControllerImpl displayChangeWindowControllerImpl = DisplayChangeController.DisplayChangeWindowControllerImpl.this;
                    int i4 = i;
                    int i5 = i2;
                    int i6 = i3;
                    DisplayAreaInfo displayAreaInfo2 = displayAreaInfo;
                    IDisplayChangeWindowCallback iDisplayChangeWindowCallback2 = iDisplayChangeWindowCallback;
                    DisplayChangeController displayChangeController = DisplayChangeController.this;
                    displayChangeController.getClass();
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    displayChangeController.dispatchOnDisplayChange(i4, i5, i6, displayAreaInfo2, windowContainerTransaction);
                    try {
                        try {
                            iDisplayChangeWindowCallback2.continueDisplayChange(windowContainerTransaction);
                            if (!Trace.isTagEnabled(32L)) {
                                return;
                            }
                        } catch (RemoteException e) {
                            Slog.e("DisplayChangeController", "Failed to continue handling display change", e);
                            if (!Trace.isTagEnabled(32L)) {
                                return;
                            }
                        }
                        Trace.endAsyncSection("HandleRemoteDisplayChange", iDisplayChangeWindowCallback2.hashCode());
                    } catch (Throwable th) {
                        if (Trace.isTagEnabled(32L)) {
                            Trace.endAsyncSection("HandleRemoteDisplayChange", iDisplayChangeWindowCallback2.hashCode());
                        }
                        throw th;
                    }
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnDisplayChangingListener {
        void onDisplayChange$1(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction);
    }

    public DisplayChangeController(IWindowManager iWindowManager, ShellInit shellInit, ShellExecutor shellExecutor) {
        this.mMainExecutor = shellExecutor;
        this.mWmService = iWindowManager;
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.common.DisplayChangeController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DisplayChangeController displayChangeController = DisplayChangeController.this;
                displayChangeController.getClass();
                try {
                    displayChangeController.mWmService.setDisplayChangeWindowController(displayChangeController.mControllerImpl);
                } catch (RemoteException unused) {
                    throw new RuntimeException("Unable to register rotation controller");
                }
            }
        }, this);
    }

    public final void dispatchOnDisplayChange(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
        if (Trace.isTagEnabled(32L)) {
            Trace.beginSection("dispatchOnDisplayChange");
        }
        Iterator it = this.mDisplayChangeListener.iterator();
        while (it.hasNext()) {
            ((OnDisplayChangingListener) it.next()).onDisplayChange$1(i, i2, i3, displayAreaInfo, windowContainerTransaction);
        }
        if (Trace.isTagEnabled(32L)) {
            Trace.endSection();
        }
    }
}
