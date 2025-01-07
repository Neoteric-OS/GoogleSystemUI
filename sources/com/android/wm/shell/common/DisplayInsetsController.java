package com.android.wm.shell.common;

import android.content.ComponentName;
import android.os.RemoteException;
import android.util.Slog;
import android.util.SparseArray;
import android.view.IDisplayWindowInsetsController;
import android.view.IWindowManager;
import android.view.InsetsSourceControl;
import android.view.InsetsState;
import android.view.inputmethod.ImeTracker;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.sysui.ShellInit;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayInsetsController implements DisplayController.OnDisplaysChangedListener {
    public final DisplayController mDisplayController;
    public final ShellExecutor mMainExecutor;
    public final IWindowManager mWmService;
    public final SparseArray mInsetsPerDisplay = new SparseArray();
    public final SparseArray mListeners = new SparseArray();
    public final CopyOnWriteArrayList mGlobalListeners = new CopyOnWriteArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PerDisplay {
        public final int mDisplayId;
        public final DisplayWindowInsetsControllerImpl mInsetsControllerImpl = new DisplayWindowInsetsControllerImpl();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class DisplayWindowInsetsControllerImpl extends IDisplayWindowInsetsController.Stub {
            public DisplayWindowInsetsControllerImpl() {
            }

            public final void hideInsets(int i, boolean z, ImeTracker.Token token) {
                ((HandlerExecutor) DisplayInsetsController.this.mMainExecutor).execute(new DisplayInsetsController$PerDisplay$DisplayWindowInsetsControllerImpl$$ExternalSyntheticLambda4(this, i, z, token, 1));
            }

            public final void insetsChanged(InsetsState insetsState) {
                ((HandlerExecutor) DisplayInsetsController.this.mMainExecutor).execute(new DisplayInsetsController$PerDisplay$DisplayWindowInsetsControllerImpl$$ExternalSyntheticLambda0(this, insetsState));
            }

            public final void insetsControlChanged(final InsetsState insetsState, final InsetsSourceControl[] insetsSourceControlArr) {
                ((HandlerExecutor) DisplayInsetsController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.common.DisplayInsetsController$PerDisplay$DisplayWindowInsetsControllerImpl$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        DisplayInsetsController.PerDisplay.DisplayWindowInsetsControllerImpl displayWindowInsetsControllerImpl = DisplayInsetsController.PerDisplay.DisplayWindowInsetsControllerImpl.this;
                        InsetsState insetsState2 = insetsState;
                        InsetsSourceControl[] insetsSourceControlArr2 = insetsSourceControlArr;
                        DisplayInsetsController.PerDisplay perDisplay = DisplayInsetsController.PerDisplay.this;
                        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) DisplayInsetsController.this.mListeners.get(perDisplay.mDisplayId);
                        if (copyOnWriteArrayList == null) {
                            return;
                        }
                        Iterator it = copyOnWriteArrayList.iterator();
                        while (it.hasNext()) {
                            ((DisplayInsetsController.OnInsetsChangedListener) it.next()).insetsControlChanged(insetsState2, insetsSourceControlArr2);
                        }
                    }
                });
            }

            public final void setImeInputTargetRequestedVisibility(boolean z) {
                ((HandlerExecutor) DisplayInsetsController.this.mMainExecutor).execute(new DisplayInsetsController$$ExternalSyntheticLambda0(this, z));
            }

            public final void showInsets(int i, boolean z, ImeTracker.Token token) {
                ((HandlerExecutor) DisplayInsetsController.this.mMainExecutor).execute(new DisplayInsetsController$PerDisplay$DisplayWindowInsetsControllerImpl$$ExternalSyntheticLambda4(this, i, z, token, 0));
            }

            public final void topFocusedWindowChanged(ComponentName componentName, int i) {
                ((HandlerExecutor) DisplayInsetsController.this.mMainExecutor).execute(new DisplayInsetsController$PerDisplay$DisplayWindowInsetsControllerImpl$$ExternalSyntheticLambda0(this, componentName, i));
            }
        }

        public PerDisplay(int i) {
            this.mDisplayId = i;
        }
    }

    public DisplayInsetsController(IWindowManager iWindowManager, ShellInit shellInit, DisplayController displayController, ShellExecutor shellExecutor) {
        this.mWmService = iWindowManager;
        this.mDisplayController = displayController;
        this.mMainExecutor = shellExecutor;
        shellInit.addInitCallback(new DisplayInsetsController$$ExternalSyntheticLambda0(this), this);
    }

    public final void addInsetsChangedListener(int i, OnInsetsChangedListener onInsetsChangedListener) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.mListeners.get(i);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.mListeners.put(i, copyOnWriteArrayList);
        }
        if (copyOnWriteArrayList.contains(onInsetsChangedListener)) {
            return;
        }
        copyOnWriteArrayList.add(onInsetsChangedListener);
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayAdded(int i) {
        PerDisplay perDisplay = new PerDisplay(i);
        int i2 = perDisplay.mDisplayId;
        try {
            this.mWmService.setDisplayWindowInsetsController(i2, perDisplay.mInsetsControllerImpl);
        } catch (RemoteException unused) {
            Slog.w("DisplayInsetsController", "Unable to set insets controller on display " + i2);
        }
        this.mInsetsPerDisplay.put(i, perDisplay);
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayRemoved(int i) {
        PerDisplay perDisplay = (PerDisplay) this.mInsetsPerDisplay.get(i);
        if (perDisplay == null) {
            return;
        }
        int i2 = perDisplay.mDisplayId;
        try {
            DisplayInsetsController.this.mWmService.setDisplayWindowInsetsController(i2, (IDisplayWindowInsetsController) null);
        } catch (RemoteException unused) {
            Slog.w("DisplayInsetsController", "Unable to remove insets controller on display " + i2);
        }
        this.mInsetsPerDisplay.remove(i);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnInsetsChangedListener {
        default void insetsChanged$1(InsetsState insetsState) {
            insetsChanged(insetsState);
        }

        default void insetsChanged(InsetsState insetsState) {
        }

        default void hideInsets(int i, ImeTracker.Token token) {
        }

        default void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) {
        }

        default void showInsets(int i, ImeTracker.Token token) {
        }
    }
}
