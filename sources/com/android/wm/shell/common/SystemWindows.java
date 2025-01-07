package com.android.wm.shell.common;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.MergedConfiguration;
import android.util.Slog;
import android.util.SparseArray;
import android.view.DragEvent;
import android.view.IScrollCaptureResponseListener;
import android.view.IWindow;
import android.view.IWindowManager;
import android.view.IWindowSessionCallback;
import android.view.InsetsSourceControl;
import android.view.InsetsState;
import android.view.ScrollCaptureResponse;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.view.inputmethod.ImeTracker;
import android.window.ActivityWindowInfo;
import android.window.ClientWindowFrames;
import android.window.InputTransferToken;
import com.android.internal.os.IResultReceiver;
import com.android.wm.shell.common.DisplayController;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemWindows {
    public final DisplayController mDisplayController;
    public final AnonymousClass1 mDisplayListener;
    public final SparseArray mPerDisplay = new SparseArray();
    public final HashMap mViewRoots = new HashMap();
    public final IWindowManager mWmService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PerDisplay {
        public final SparseArray mWwms = new SparseArray();

        public PerDisplay() {
        }

        public final void setShellRootAccessibilityWindow(View view) {
            if (((SysUiWindowManager) this.mWwms.get(1)) == null) {
                return;
            }
            try {
                SystemWindows systemWindows = SystemWindows.this;
                systemWindows.mWmService.setShellRootAccessibilityWindow(0, 1, view != null ? ((SurfaceControlViewHost) systemWindows.mViewRoots.get(view)).getWindowToken() : null);
            } catch (RemoteException e) {
                Slog.e("SystemWindows", "Error setting accessibility window for 0:1", e);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SysUiWindowManager extends WindowlessWindowManager {
        public final HashMap mLeashForWindow;

        public SysUiWindowManager(Context context, SurfaceControl surfaceControl) {
            super(context.getResources().getConfiguration(), surfaceControl, (InputTransferToken) null);
            this.mLeashForWindow = new HashMap();
        }

        public final SurfaceControl getParentSurface(IWindow iWindow, WindowManager.LayoutParams layoutParams) {
            SurfaceControl build = new SurfaceControl.Builder().setContainerLayer().setName("SystemWindowLeash").setHidden(false).setParent(((WindowlessWindowManager) this).mRootSurface).setCallsite("SysUiWIndowManager#attachToParentSurface").build();
            synchronized (this) {
                this.mLeashForWindow.put(iWindow.asBinder(), build);
            }
            return build;
        }

        public final SurfaceControl getSurfaceControlForWindow(View view) {
            SurfaceControl surfaceControl;
            synchronized (this) {
                surfaceControl = (SurfaceControl) this.mLeashForWindow.get(getWindowBinder(view));
            }
            return surfaceControl;
        }

        public final void remove(IBinder iBinder) {
            super.remove(iBinder);
            synchronized (this) {
                new SurfaceControl.Transaction().remove((SurfaceControl) this.mLeashForWindow.get(iBinder)).apply();
                this.mLeashForWindow.remove(iBinder);
            }
        }
    }

    public SystemWindows(DisplayController displayController, IWindowManager iWindowManager) {
        DisplayController.OnDisplaysChangedListener onDisplaysChangedListener = new DisplayController.OnDisplaysChangedListener() { // from class: com.android.wm.shell.common.SystemWindows.1
            @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
            public final void onDisplayConfigurationChanged(int i, Configuration configuration) {
                PerDisplay perDisplay = (PerDisplay) SystemWindows.this.mPerDisplay.get(i);
                if (perDisplay == null) {
                    return;
                }
                for (int i2 = 0; i2 < perDisplay.mWwms.size(); i2++) {
                    ((SysUiWindowManager) perDisplay.mWwms.valueAt(i2)).setConfiguration(configuration);
                }
            }

            @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
            public final void onDisplayAdded(int i) {
            }

            @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
            public final void onDisplayRemoved(int i) {
            }
        };
        this.mWmService = iWindowManager;
        this.mDisplayController = displayController;
        displayController.addDisplayWindowListener(onDisplaysChangedListener);
        try {
            iWindowManager.openSession(new AnonymousClass2());
        } catch (RemoteException e) {
            Slog.e("SystemWindows", "Unable to create layer", e);
        }
    }

    public final void setShellRootAccessibilityWindow(View view) {
        PerDisplay perDisplay = (PerDisplay) this.mPerDisplay.get(0);
        if (perDisplay == null) {
            return;
        }
        perDisplay.setShellRootAccessibilityWindow(view);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.common.SystemWindows$2, reason: invalid class name */
    public final class AnonymousClass2 extends IWindowSessionCallback.Stub {
        public final void onAnimatorScaleChanged(float f) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ContainerWindow extends IWindow.Stub {
        public final void requestScrollCapture(IScrollCaptureResponseListener iScrollCaptureResponseListener) {
            try {
                iScrollCaptureResponseListener.onScrollCaptureResponse(new ScrollCaptureResponse.Builder().setDescription("Not Implemented").build());
            } catch (RemoteException unused) {
            }
        }

        public final void closeSystemDialogs(String str) {
        }

        public final void dispatchAppVisibility(boolean z) {
        }

        public final void dispatchDragEvent(DragEvent dragEvent) {
        }

        public final void dispatchGetNewSurface() {
        }

        public final void dispatchWindowShown() {
        }

        public final void dumpWindow(ParcelFileDescriptor parcelFileDescriptor) {
        }

        public final void insetsControlChanged(InsetsState insetsState, InsetsSourceControl.Array array) {
        }

        public final void moved(int i, int i2) {
        }

        public final void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) {
        }

        public final void executeCommand(String str, String str2, ParcelFileDescriptor parcelFileDescriptor) {
        }

        public final void hideInsets(int i, boolean z, ImeTracker.Token token) {
        }

        public final void showInsets(int i, boolean z, ImeTracker.Token token) {
        }

        public final void resized(ClientWindowFrames clientWindowFrames, boolean z, MergedConfiguration mergedConfiguration, InsetsState insetsState, boolean z2, boolean z3, int i, int i2, boolean z4, ActivityWindowInfo activityWindowInfo) {
        }

        public final void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) {
        }

        public final void dispatchWallpaperOffsets(float f, float f2, float f3, float f4, float f5, boolean z) {
        }
    }
}
