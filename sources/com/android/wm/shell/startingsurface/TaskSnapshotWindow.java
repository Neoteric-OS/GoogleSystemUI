package com.android.wm.shell.startingsurface;

import android.app.ActivityManager;
import android.graphics.Paint;
import android.os.RemoteException;
import android.util.MergedConfiguration;
import android.view.IWindowSession;
import android.view.InsetsState;
import android.view.SurfaceControl;
import android.view.WindowManagerGlobal;
import android.window.ActivityWindowInfo;
import android.window.ClientWindowFrames;
import android.window.TaskSnapshot;
import com.android.internal.view.BaseIWindow;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TaskSnapshotWindow {
    public final Paint mBackgroundPaint;
    public final SnapshotWindowCreator$$ExternalSyntheticLambda0 mClearWindowHandler;
    public boolean mHasDrawn;
    public final boolean mHasImeSurface;
    public final int mOrientationOnCreation;
    public final IWindowSession mSession;
    public final ShellExecutor mSplashScreenExecutor;
    public final Window mWindow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Window extends BaseIWindow {
        public final WeakReference mOuter;

        public Window(TaskSnapshotWindow taskSnapshotWindow) {
            this.mOuter = new WeakReference(taskSnapshotWindow);
        }

        public final void resized(ClientWindowFrames clientWindowFrames, final boolean z, final MergedConfiguration mergedConfiguration, InsetsState insetsState, boolean z2, boolean z3, int i, int i2, boolean z4, ActivityWindowInfo activityWindowInfo) {
            final TaskSnapshotWindow taskSnapshotWindow = (TaskSnapshotWindow) this.mOuter.get();
            if (taskSnapshotWindow == null) {
                return;
            }
            ((HandlerExecutor) taskSnapshotWindow.mSplashScreenExecutor).execute(new Runnable() { // from class: com.android.wm.shell.startingsurface.TaskSnapshotWindow$Window$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MergedConfiguration mergedConfiguration2 = mergedConfiguration;
                    TaskSnapshotWindow taskSnapshotWindow2 = taskSnapshotWindow;
                    boolean z5 = z;
                    SnapshotWindowCreator$$ExternalSyntheticLambda0 snapshotWindowCreator$$ExternalSyntheticLambda0 = taskSnapshotWindow2.mClearWindowHandler;
                    ShellExecutor shellExecutor = taskSnapshotWindow2.mSplashScreenExecutor;
                    if (mergedConfiguration2 != null) {
                        if (taskSnapshotWindow2.mOrientationOnCreation != mergedConfiguration2.getMergedConfiguration().orientation) {
                            ((HandlerExecutor) shellExecutor).executeDelayed(snapshotWindowCreator$$ExternalSyntheticLambda0, 0L);
                            return;
                        }
                    }
                    if (z5 && taskSnapshotWindow2.mHasDrawn) {
                        try {
                            taskSnapshotWindow2.mSession.finishDrawing(taskSnapshotWindow2.mWindow, (SurfaceControl.Transaction) null, Integer.MAX_VALUE);
                        } catch (RemoteException unused) {
                            ((HandlerExecutor) shellExecutor).executeDelayed(snapshotWindowCreator$$ExternalSyntheticLambda0, 0L);
                        }
                    }
                }
            });
        }
    }

    public TaskSnapshotWindow(TaskSnapshot taskSnapshot, ActivityManager.TaskDescription taskDescription, int i, SnapshotWindowCreator$$ExternalSyntheticLambda0 snapshotWindowCreator$$ExternalSyntheticLambda0, ShellExecutor shellExecutor) {
        Paint paint = new Paint();
        this.mBackgroundPaint = paint;
        this.mSplashScreenExecutor = shellExecutor;
        IWindowSession windowSession = WindowManagerGlobal.getWindowSession();
        this.mSession = windowSession;
        Window window = new Window(this);
        this.mWindow = window;
        window.setSession(windowSession);
        int backgroundColor = taskDescription.getBackgroundColor();
        paint.setColor(backgroundColor == 0 ? -1 : backgroundColor);
        this.mOrientationOnCreation = i;
        this.mClearWindowHandler = snapshotWindowCreator$$ExternalSyntheticLambda0;
        this.mHasImeSurface = taskSnapshot.hasImeSurface();
    }
}
