package com.android.wm.shell.draganddrop;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.HardwareRenderer;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Slog;
import android.util.SparseArray;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.launcher3.icons.IconProvider;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.draganddrop.DragAndDropController;
import com.android.wm.shell.draganddrop.GlobalDragListener;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DragAndDropController implements RemoteCallable, GlobalDragListener.GlobalDragListenerCallback, DisplayController.OnDisplaysChangedListener, ShellTaskOrganizer.TaskVanishedListener, View.OnDragListener, ComponentCallbacks2 {
    public final Context mContext;
    public final DisplayController mDisplayController;
    public final GlobalDragListener mGlobalDragListener;
    public final IconProvider mIconProvider;
    public final DragAndDropEventLogger mLogger;
    public final ShellExecutor mMainExecutor;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellController mShellController;
    public final ShellTaskOrganizer mShellTaskOrganizer;
    public SplitScreenController mSplitScreen;
    public final Transitions mTransitions;
    public final ArrayList mListeners = new ArrayList();
    public final SparseArray mDisplayDropTargets = new SparseArray();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PerDisplay implements HardwareRenderer.FrameDrawingCallback {
        public int activeDragCount;
        public final Context context;
        public final int displayId;
        public final DragLayoutProvider dragLayout;
        public DragSession dragSession;
        public boolean hasDrawn;
        public boolean isHandlingDrag;
        public final FrameLayout rootView;
        public final WindowManager wm;

        public PerDisplay(int i, Context context, WindowManager windowManager, FrameLayout frameLayout, DragLayoutProvider dragLayoutProvider) {
            this.displayId = i;
            this.context = context;
            this.wm = windowManager;
            this.rootView = frameLayout;
            this.dragLayout = dragLayoutProvider;
        }

        public final void onFrameDraw(long j) {
            this.hasDrawn = true;
        }
    }

    public DragAndDropController(Context context, ShellInit shellInit, ShellController shellController, ShellCommandHandler shellCommandHandler, ShellTaskOrganizer shellTaskOrganizer, DisplayController displayController, UiEventLogger uiEventLogger, IconProvider iconProvider, GlobalDragListener globalDragListener, Transitions transitions, ShellExecutor shellExecutor) {
        this.mContext = context;
        this.mShellController = shellController;
        this.mShellCommandHandler = shellCommandHandler;
        this.mShellTaskOrganizer = shellTaskOrganizer;
        this.mDisplayController = displayController;
        this.mLogger = new DragAndDropEventLogger(uiEventLogger);
        this.mIconProvider = iconProvider;
        this.mGlobalDragListener = globalDragListener;
        this.mTransitions = transitions;
        this.mMainExecutor = shellExecutor;
        shellInit.addInitCallback(new DragAndDropController$$ExternalSyntheticLambda5(this, 0), this);
    }

    public static void setDropTargetWindowVisibility(PerDisplay perDisplay, int i) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, 2483469667165484778L, 5, Long.valueOf(perDisplay.displayId), Long.valueOf(i));
        }
        perDisplay.rootView.setVisibility(i);
        if (i != 0) {
            perDisplay.hasDrawn = false;
            return;
        }
        perDisplay.rootView.requestApplyInsets();
        if (perDisplay.hasDrawn || perDisplay.rootView.getViewRootImpl() == null) {
            return;
        }
        perDisplay.rootView.getViewRootImpl().registerRtFrameCallback(perDisplay);
    }

    public void addDisplayDropTarget(int i, Context context, WindowManager windowManager, FrameLayout frameLayout, DragLayoutProvider dragLayoutProvider) {
        this.mDisplayDropTargets.put(i, new PerDisplay(i, context, windowManager, frameLayout, dragLayoutProvider));
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mMainExecutor;
    }

    public final boolean notifyListeners(Function function) {
        for (int i = 0; i < this.mListeners.size(); i++) {
            if (((Boolean) function.apply((DragAndDropListener) this.mListeners.get(i))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        ((HandlerExecutor) this.mMainExecutor).execute(new DragAndDropController$$ExternalSyntheticLambda2(this, configuration, 2));
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayAdded(int i) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, 2150044987932031977L, 1, Long.valueOf(i));
        }
        if (i != 0) {
            return;
        }
        Context createWindowContext = this.mDisplayController.getDisplayContext(i).createWindowContext(2038, null);
        WindowManager windowManager = (WindowManager) createWindowContext.getSystemService(WindowManager.class);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2038, 16777224, -3);
        layoutParams.privateFlags |= -2147483568;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.setTitle("ShellDropTarget");
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(createWindowContext).inflate(R.layout.global_drop_target, (ViewGroup) null);
        frameLayout.setOnDragListener(this);
        frameLayout.setVisibility(4);
        DragLayout dragLayout = new DragLayout(createWindowContext, this.mSplitScreen, this.mIconProvider);
        frameLayout.addView(dragLayout, new ViewGroup.LayoutParams(-1, -1));
        try {
            windowManager.addView(frameLayout, layoutParams);
            addDisplayDropTarget(i, createWindowContext, windowManager, frameLayout, dragLayout);
            createWindowContext.registerComponentCallbacks(this);
        } catch (WindowManager.InvalidDisplayException unused) {
            Slog.w("DragAndDropController", "Unable to add view for display id: " + i);
        }
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayConfigurationChanged(int i, Configuration configuration) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -1937012838822497354L, 1, Long.valueOf(i));
        }
        PerDisplay perDisplay = (PerDisplay) this.mDisplayDropTargets.get(i);
        if (perDisplay == null) {
            return;
        }
        perDisplay.rootView.requestApplyInsets();
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayRemoved(int i) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, 5861505718780965768L, 1, Long.valueOf(i));
        }
        PerDisplay perDisplay = (PerDisplay) this.mDisplayDropTargets.get(i);
        if (perDisplay == null) {
            return;
        }
        perDisplay.context.unregisterComponentCallbacks(this);
        perDisplay.wm.removeViewImmediate(perDisplay.rootView);
        this.mDisplayDropTargets.remove(i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00fd  */
    @Override // android.view.View.OnDragListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onDrag(android.view.View r18, android.view.DragEvent r19) {
        /*
            Method dump skipped, instructions count: 970
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.draganddrop.DragAndDropController.onDrag(android.view.View, android.view.DragEvent):boolean");
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskVanishedListener
    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        PerDisplay perDisplay;
        if (runningTaskInfo.baseIntent == null) {
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.mDisplayDropTargets.size()) {
                perDisplay = null;
                break;
            }
            perDisplay = (PerDisplay) this.mDisplayDropTargets.valueAt(i);
            if (perDisplay.isHandlingDrag) {
                break;
            } else {
                i++;
            }
        }
        if (perDisplay == null || perDisplay.activeDragCount <= 0 || !perDisplay.isHandlingDrag) {
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -3119176285175531349L, 1, Long.valueOf(runningTaskInfo.taskId), String.valueOf(runningTaskInfo.baseIntent.getComponent()));
        }
        perDisplay.dragSession.updateRunningTask();
        ((DragLayout) perDisplay.dragLayout).updateSession(perDisplay.dragSession);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface DragAndDropListener {
        default boolean onUnhandledDrag(PendingIntent pendingIntent, DragEvent dragEvent, GlobalDragListener$onUnhandledDrop$1 globalDragListener$onUnhandledDrop$1) {
            return false;
        }

        default void onDragStarted() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IDragAndDropImpl extends Binder implements ExternalInterfaceBinder, IInterface {
        public DragAndDropController mController;

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            this.mController = null;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.draganddrop.IDragAndDrop");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.draganddrop.IDragAndDrop");
                return true;
            }
            if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            final boolean[] zArr = new boolean[1];
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "isReadyToHandleDrag", new Consumer() { // from class: com.android.wm.shell.draganddrop.DragAndDropController$IDragAndDropImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    boolean z;
                    boolean[] zArr2 = zArr;
                    DragAndDropController dragAndDropController = (DragAndDropController) obj;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= dragAndDropController.mDisplayDropTargets.size()) {
                            z = false;
                            break;
                        } else {
                            if (((DragAndDropController.PerDisplay) dragAndDropController.mDisplayDropTargets.valueAt(i3)).hasDrawn) {
                                z = true;
                                break;
                            }
                            i3++;
                        }
                    }
                    zArr2[0] = z;
                }
            }, true);
            boolean z = zArr[0];
            parcel2.writeNoException();
            parcel2.writeBoolean(z);
            return true;
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder, android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
    }
}
