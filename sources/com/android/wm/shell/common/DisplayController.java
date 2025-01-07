package com.android.wm.shell.common;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Display;
import android.view.IDisplayWindowListener;
import android.view.IWindowManager;
import android.view.InsetsState;
import com.android.wm.shell.common.DisplayChangeController;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.sysui.ShellInit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayController {
    public final DisplayChangeController mChangeController;
    public final Context mContext;
    public final ShellExecutor mMainExecutor;
    public final IWindowManager mWmService;
    public final SparseArray mDisplays = new SparseArray();
    public final ArrayList mDisplayChangedListeners = new ArrayList();
    public final DisplayWindowListenerImpl mDisplayContainerListener = new DisplayWindowListenerImpl();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DisplayRecord {
        public Context mContext;
        public DisplayLayout mDisplayLayout;
        public InsetsState mInsetsState;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DisplayWindowListenerImpl extends IDisplayWindowListener.Stub {
        public DisplayWindowListenerImpl() {
        }

        public final void onDisplayAdded(int i) {
            ((HandlerExecutor) DisplayController.this.mMainExecutor).execute(new DisplayController$DisplayWindowListenerImpl$$ExternalSyntheticLambda0(this, i, 0));
        }

        public final void onDisplayConfigurationChanged(final int i, final Configuration configuration) {
            ((HandlerExecutor) DisplayController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.common.DisplayController$DisplayWindowListenerImpl$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    DisplayController.DisplayWindowListenerImpl displayWindowListenerImpl = DisplayController.DisplayWindowListenerImpl.this;
                    int i2 = i;
                    Configuration configuration2 = configuration;
                    DisplayController displayController = DisplayController.this;
                    synchronized (displayController.mDisplays) {
                        try {
                            DisplayController.DisplayRecord displayRecord = (DisplayController.DisplayRecord) displayController.mDisplays.get(i2);
                            if (displayRecord == null) {
                                Slog.w("DisplayController", "Skipping Display Configuration change on non-added display.");
                                return;
                            }
                            Display display = displayController.getDisplay(i2);
                            if (display == null) {
                                Slog.w("DisplayController", "Skipping Display Configuration change on invalid display. It may have been removed.");
                                return;
                            }
                            Context createConfigurationContext = (i2 == 0 ? displayController.mContext : displayController.mContext.createDisplayContext(display)).createConfigurationContext(configuration2);
                            DisplayLayout displayLayout = new DisplayLayout(createConfigurationContext, display);
                            displayRecord.mContext = createConfigurationContext;
                            displayRecord.mDisplayLayout = displayLayout;
                            Resources resources = createConfigurationContext.getResources();
                            displayLayout.mInsetsState = displayRecord.mInsetsState;
                            displayLayout.recalcInsets(resources);
                            for (int i3 = 0; i3 < displayController.mDisplayChangedListeners.size(); i3++) {
                                ((DisplayController.OnDisplaysChangedListener) displayController.mDisplayChangedListeners.get(i3)).onDisplayConfigurationChanged(i2, configuration2);
                            }
                        } finally {
                        }
                    }
                }
            });
        }

        public final void onDisplayRemoved(int i) {
            ((HandlerExecutor) DisplayController.this.mMainExecutor).execute(new DisplayController$DisplayWindowListenerImpl$$ExternalSyntheticLambda0(this, i, 1));
        }

        public final void onFixedRotationFinished(int i) {
            ((HandlerExecutor) DisplayController.this.mMainExecutor).execute(new DisplayController$DisplayWindowListenerImpl$$ExternalSyntheticLambda0(this, i, 2));
        }

        public final void onFixedRotationStarted(final int i, final int i2) {
            ((HandlerExecutor) DisplayController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.common.DisplayController$DisplayWindowListenerImpl$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    DisplayController.DisplayWindowListenerImpl displayWindowListenerImpl = DisplayController.DisplayWindowListenerImpl.this;
                    int i3 = i;
                    int i4 = i2;
                    DisplayController displayController = DisplayController.this;
                    synchronized (displayController.mDisplays) {
                        try {
                            if (displayController.mDisplays.get(i3) != null && displayController.getDisplay(i3) != null) {
                                for (int size = displayController.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
                                    ((DisplayController.OnDisplaysChangedListener) displayController.mDisplayChangedListeners.get(size)).onFixedRotationStarted(i4);
                                }
                                return;
                            }
                            Slog.w("DisplayController", "Skipping onFixedRotationStarted on unknown display, displayId=" + i3);
                        } finally {
                        }
                    }
                }
            });
        }

        public final void onKeepClearAreasChanged(final int i, final List list, final List list2) {
            ((HandlerExecutor) DisplayController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.common.DisplayController$DisplayWindowListenerImpl$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    DisplayController.DisplayWindowListenerImpl displayWindowListenerImpl = DisplayController.DisplayWindowListenerImpl.this;
                    int i2 = i;
                    List list3 = list;
                    List list4 = list2;
                    DisplayController displayController = DisplayController.this;
                    ArraySet arraySet = new ArraySet(list3);
                    ArraySet arraySet2 = new ArraySet(list4);
                    synchronized (displayController.mDisplays) {
                        try {
                            if (displayController.mDisplays.get(i2) != null && displayController.getDisplay(i2) != null) {
                                for (int size = displayController.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
                                    ((DisplayController.OnDisplaysChangedListener) displayController.mDisplayChangedListeners.get(size)).onKeepClearAreasChanged(i2, arraySet, arraySet2);
                                }
                                return;
                            }
                            Slog.w("DisplayController", "Skipping onKeepClearAreasChanged on unknown display, displayId=" + i2);
                        } finally {
                        }
                    }
                }
            });
        }
    }

    public DisplayController(Context context, IWindowManager iWindowManager, ShellInit shellInit, ShellExecutor shellExecutor) {
        this.mMainExecutor = shellExecutor;
        this.mContext = context;
        this.mWmService = iWindowManager;
        this.mChangeController = new DisplayChangeController(iWindowManager, shellInit, shellExecutor);
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.common.DisplayController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DisplayController displayController = DisplayController.this;
                displayController.getClass();
                try {
                    for (int i : displayController.mWmService.registerDisplayWindowListener(displayController.mDisplayContainerListener)) {
                        displayController.onDisplayAdded(i);
                    }
                } catch (RemoteException unused) {
                    throw new RuntimeException("Unable to register display controller");
                }
            }
        }, this);
    }

    public final void addDisplayChangingController(DisplayChangeController.OnDisplayChangingListener onDisplayChangingListener) {
        this.mChangeController.mDisplayChangeListener.add(onDisplayChangingListener);
    }

    public final void addDisplayWindowListener(OnDisplaysChangedListener onDisplaysChangedListener) {
        synchronized (this.mDisplays) {
            try {
                if (this.mDisplayChangedListeners.contains(onDisplaysChangedListener)) {
                    return;
                }
                this.mDisplayChangedListeners.add(onDisplaysChangedListener);
                for (int i = 0; i < this.mDisplays.size(); i++) {
                    onDisplaysChangedListener.onDisplayAdded(this.mDisplays.keyAt(i));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Display getDisplay(int i) {
        return ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).getDisplay(i);
    }

    public final Context getDisplayContext(int i) {
        DisplayRecord displayRecord = (DisplayRecord) this.mDisplays.get(i);
        if (displayRecord != null) {
            return displayRecord.mContext;
        }
        return null;
    }

    public final DisplayLayout getDisplayLayout(int i) {
        DisplayRecord displayRecord = (DisplayRecord) this.mDisplays.get(i);
        if (displayRecord != null) {
            return displayRecord.mDisplayLayout;
        }
        return null;
    }

    public final void onDisplayAdded(int i) {
        synchronized (this.mDisplays) {
            try {
                if (this.mDisplays.get(i) != null) {
                    return;
                }
                Display display = getDisplay(i);
                if (display == null) {
                    return;
                }
                Context createDisplayContext = i == 0 ? this.mContext : this.mContext.createDisplayContext(display);
                DisplayRecord displayRecord = new DisplayRecord();
                displayRecord.mInsetsState = new InsetsState();
                DisplayLayout displayLayout = new DisplayLayout(createDisplayContext, display);
                displayRecord.mContext = createDisplayContext;
                displayRecord.mDisplayLayout = displayLayout;
                Resources resources = createDisplayContext.getResources();
                displayLayout.mInsetsState = displayRecord.mInsetsState;
                displayLayout.recalcInsets(resources);
                this.mDisplays.put(i, displayRecord);
                for (int i2 = 0; i2 < this.mDisplayChangedListeners.size(); i2++) {
                    ((OnDisplaysChangedListener) this.mDisplayChangedListeners.get(i2)).onDisplayAdded(i);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void removeDisplayWindowListener(OnDisplaysChangedListener onDisplaysChangedListener) {
        synchronized (this.mDisplays) {
            this.mDisplayChangedListeners.remove(onDisplaysChangedListener);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnDisplaysChangedListener {
        default void onDisplayAdded(int i) {
        }

        default void onDisplayRemoved(int i) {
        }

        default void onFixedRotationFinished() {
        }

        default void onFixedRotationStarted(int i) {
        }

        default void onDisplayConfigurationChanged(int i, Configuration configuration) {
        }

        default void onKeepClearAreasChanged(int i, Set set, Set set2) {
        }
    }
}
