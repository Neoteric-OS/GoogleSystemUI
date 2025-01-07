package com.android.wm.shell.desktopmode;

import android.R;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.TaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.SystemProperties;
import android.view.SurfaceControl;
import android.window.DisplayAreaInfo;
import android.window.RemoteTransition;
import android.window.TransitionInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import android.window.flags.DesktopModeFlags;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.MultiInstanceHelper;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.compatui.AppCompatUtils;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.desktopmode.DesktopModeVisualIndicator;
import com.android.wm.shell.desktopmode.DesktopTaskPosition;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler;
import com.android.wm.shell.draganddrop.DragAndDropController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.RecentTasksController;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import com.android.wm.shell.recents.RecentsTransitionStateListener;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.shared.desktopmode.DesktopModeTransitionSource;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.splitscreen.StageCoordinator;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.extension.TaskInfoKt;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksController implements RemoteCallable, Transitions.TransitionHandler, DragAndDropController.DragAndDropListener {
    public static final float DESKTOP_MODE_INITIAL_BOUNDS_SCALE = SystemProperties.getInt("persist.wm.debug.desktop_mode_initial_bounds_scale", 75) / 100.0f;
    public final Context context;
    public final DesktopModeDragAndDropTransitionHandler desktopModeDragAndDropTransitionHandler;
    public final Optional desktopTasksLimiter;
    public final DisplayController displayController;
    public final DragAndDropController dragAndDropController;
    public Binder dragAndDropFullscreenCookie;
    public final DragToDesktopTransitionHandler dragToDesktopTransitionHandler;
    public final EnterDesktopTaskTransitionHandler enterDesktopTaskTransitionHandler;
    public final ExitDesktopTaskTransitionHandler exitDesktopTaskTransitionHandler;
    public final Handler handler;
    public final InteractionJankMonitor interactionJankMonitor;
    public final KeyguardManager keyguardManager;
    public final ShellExecutor mainExecutor;
    public final MultiInstanceHelper multiInstanceHelper;
    public final RecentTasksController recentTasksController;
    public boolean recentsAnimationRunning;
    public final RecentsTransitionHandler recentsTransitionHandler;
    public final ReturnToDragStartAnimator returnToDragStartAnimator;
    public final RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer;
    public final ShellCommandHandler shellCommandHandler;
    public final ShellController shellController;
    public final ShellTaskOrganizer shellTaskOrganizer;
    public SplitScreenController splitScreenController;
    public final SyncTransactionQueue syncQueue;
    public final DesktopModeTaskRepository taskRepository;
    public DesktopTasksController$dragToDesktopStateListener$1 taskbarDesktopTaskListener;
    public final ToggleResizeDesktopTaskTransitionHandler toggleResizeDesktopTaskTransitionHandler;
    public final Transitions transitions;
    public DesktopModeVisualIndicator visualIndicator;
    public final DesktopModeShellCommandHandler desktopModeShellCommandHandler = new DesktopModeShellCommandHandler(this);
    public final DesktopTasksController$mOnAnimationFinishedCallback$1 mOnAnimationFinishedCallback = new DesktopTasksController$mOnAnimationFinishedCallback$1(0, this);
    public final DesktopTasksController$dragToDesktopStateListener$1 dragToDesktopStateListener = new DesktopTasksController$dragToDesktopStateListener$1(this);
    public final DesktopModeImpl desktopMode = new DesktopModeImpl();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DesktopModeImpl {
        public DesktopModeImpl() {
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SnapPosition {
        public static final /* synthetic */ SnapPosition[] $VALUES;
        public static final SnapPosition LEFT;
        public static final SnapPosition RIGHT;

        static {
            SnapPosition snapPosition = new SnapPosition("RIGHT", 0);
            RIGHT = snapPosition;
            SnapPosition snapPosition2 = new SnapPosition("LEFT", 1);
            LEFT = snapPosition2;
            SnapPosition[] snapPositionArr = {snapPosition, snapPosition2};
            $VALUES = snapPositionArr;
            EnumEntriesKt.enumEntries(snapPositionArr);
        }

        public static SnapPosition valueOf(String str) {
            return (SnapPosition) Enum.valueOf(SnapPosition.class, str);
        }

        public static SnapPosition[] values() {
            return (SnapPosition[]) $VALUES.clone();
        }
    }

    public DesktopTasksController(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, ShellController shellController, DisplayController displayController, ShellTaskOrganizer shellTaskOrganizer, SyncTransactionQueue syncTransactionQueue, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, DragAndDropController dragAndDropController, Transitions transitions, KeyguardManager keyguardManager, ReturnToDragStartAnimator returnToDragStartAnimator, EnterDesktopTaskTransitionHandler enterDesktopTaskTransitionHandler, ExitDesktopTaskTransitionHandler exitDesktopTaskTransitionHandler, DesktopModeDragAndDropTransitionHandler desktopModeDragAndDropTransitionHandler, ToggleResizeDesktopTaskTransitionHandler toggleResizeDesktopTaskTransitionHandler, DragToDesktopTransitionHandler dragToDesktopTransitionHandler, DesktopModeTaskRepository desktopModeTaskRepository, RecentsTransitionHandler recentsTransitionHandler, MultiInstanceHelper multiInstanceHelper, ShellExecutor shellExecutor, Optional optional, RecentTasksController recentTasksController, InteractionJankMonitor interactionJankMonitor, Handler handler) {
        this.context = context;
        this.shellCommandHandler = shellCommandHandler;
        this.shellController = shellController;
        this.displayController = displayController;
        this.shellTaskOrganizer = shellTaskOrganizer;
        this.syncQueue = syncTransactionQueue;
        this.rootTaskDisplayAreaOrganizer = rootTaskDisplayAreaOrganizer;
        this.dragAndDropController = dragAndDropController;
        this.transitions = transitions;
        this.keyguardManager = keyguardManager;
        this.returnToDragStartAnimator = returnToDragStartAnimator;
        this.enterDesktopTaskTransitionHandler = enterDesktopTaskTransitionHandler;
        this.exitDesktopTaskTransitionHandler = exitDesktopTaskTransitionHandler;
        this.desktopModeDragAndDropTransitionHandler = desktopModeDragAndDropTransitionHandler;
        this.toggleResizeDesktopTaskTransitionHandler = toggleResizeDesktopTaskTransitionHandler;
        this.dragToDesktopTransitionHandler = dragToDesktopTransitionHandler;
        this.taskRepository = desktopModeTaskRepository;
        this.recentsTransitionHandler = recentsTransitionHandler;
        this.multiInstanceHelper = multiInstanceHelper;
        this.mainExecutor = shellExecutor;
        this.desktopTasksLimiter = optional;
        this.recentTasksController = recentTasksController;
        this.interactionJankMonitor = interactionJankMonitor;
        this.handler = handler;
        if (DesktopModeStatus.canEnterDesktopMode(context)) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController.1
                @Override // java.lang.Runnable
                public final void run() {
                    final DesktopTasksController desktopTasksController = DesktopTasksController.this;
                    desktopTasksController.getClass();
                    DesktopTasksController.logD("onInit", new Object[0]);
                    BiConsumer biConsumer = new BiConsumer() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$onInit$1
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            String joinToString$default;
                            String joinToString$default2;
                            String joinToString$default3;
                            PrintWriter printWriter = (PrintWriter) obj;
                            String str = (String) obj2;
                            DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                            desktopTasksController2.getClass();
                            String concat = str.concat("  ");
                            printWriter.println(str.concat("DesktopTasksController"));
                            Context context2 = desktopTasksController2.context;
                            boolean z = DesktopModeStatus.IS_VEILED_RESIZE_ENABLED;
                            String str2 = concat + "  ";
                            printWriter.print(concat);
                            printWriter.println("DesktopModeStatus");
                            printWriter.print(str2);
                            printWriter.print("maxTaskLimit=");
                            printWriter.println(SystemProperties.getInt("persist.wm.debug.desktop_max_task_limit", context2.getResources().getInteger(R.integer.config_lowPowerStandbyNonInteractiveTimeout)));
                            printWriter.print(str2);
                            printWriter.print("maxTaskLimit config override=");
                            printWriter.println(context2.getResources().getInteger(R.integer.config_lowPowerStandbyNonInteractiveTimeout));
                            SystemProperties.Handle find = SystemProperties.find("persist.wm.debug.desktop_max_task_limit");
                            printWriter.print(str2);
                            printWriter.print("maxTaskLimit sysprop=");
                            printWriter.println(find == null ? "null" : Integer.valueOf(find.getInt(-1)));
                            DesktopModeTaskRepository desktopModeTaskRepository2 = desktopTasksController2.taskRepository;
                            desktopModeTaskRepository2.getClass();
                            String concat2 = concat.concat("  ");
                            printWriter.println(concat.concat("DesktopModeTaskRepository"));
                            String str3 = concat2 + "  ";
                            DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopModeTaskRepository$desktopTaskDataByDisplayId$1 = desktopModeTaskRepository2.desktopTaskDataByDisplayId;
                            int size = desktopModeTaskRepository$desktopTaskDataByDisplayId$1.size();
                            for (int i = 0; i < size; i++) {
                                int keyAt = desktopModeTaskRepository$desktopTaskDataByDisplayId$1.keyAt(i);
                                DesktopModeTaskRepository.DesktopTaskData desktopTaskData = (DesktopModeTaskRepository.DesktopTaskData) desktopModeTaskRepository$desktopTaskDataByDisplayId$1.valueAt(i);
                                printWriter.println(concat2 + "Display " + keyAt + ":");
                                joinToString$default = CollectionsKt.joinToString$default(desktopTaskData.activeTasks, ", ", "[", "]", null, 56);
                                printWriter.println(str3 + "activeTasks=" + joinToString$default);
                                joinToString$default2 = CollectionsKt.joinToString$default(desktopTaskData.visibleTasks, ", ", "[", "]", null, 56);
                                printWriter.println(str3 + "visibleTasks=" + joinToString$default2);
                                joinToString$default3 = CollectionsKt.joinToString$default(desktopTaskData.freeformTasksInZOrder, ", ", "[", "]", null, 56);
                                printWriter.println(str3 + "freeformTasksInZOrder=" + joinToString$default3);
                            }
                            printWriter.println(concat2 + "activeTasksListeners=" + desktopModeTaskRepository2.activeTasksListeners.size());
                            printWriter.println(concat2 + "visibleTasksListeners=" + desktopModeTaskRepository2.visibleTasksListeners.size());
                        }
                    };
                    ShellCommandHandler shellCommandHandler2 = desktopTasksController.shellCommandHandler;
                    shellCommandHandler2.addDumpCallback(biConsumer, desktopTasksController);
                    shellCommandHandler2.addCommandCallback("desktopmode", desktopTasksController.desktopModeShellCommandHandler, desktopTasksController);
                    desktopTasksController.shellController.addExternalInterface("extra_shell_desktop_mode", new Supplier() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$onInit$2
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                            desktopTasksController2.getClass();
                            return new DesktopTasksController.IDesktopModeImpl(desktopTasksController2);
                        }
                    }, desktopTasksController);
                    desktopTasksController.transitions.addHandler(desktopTasksController);
                    desktopTasksController.dragToDesktopTransitionHandler.dragToDesktopStateListener = desktopTasksController.dragToDesktopStateListener;
                    desktopTasksController.recentsTransitionHandler.mStateListeners.add(new RecentsTransitionStateListener() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$onInit$3
                        @Override // com.android.wm.shell.recents.RecentsTransitionStateListener
                        public final void onAnimationStateChanged(boolean z) {
                            Object[] objArr = {Boolean.valueOf(z)};
                            DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                            desktopTasksController2.getClass();
                            DesktopTasksController.logV("Recents animation state changed running=%b", objArr);
                            desktopTasksController2.recentsAnimationRunning = z;
                        }
                    });
                    desktopTasksController.dragAndDropController.mListeners.add(desktopTasksController);
                }
            }, this);
        }
    }

    public static ActivityOptions createNewWindowOptions(ActivityManager.RunningTaskInfo runningTaskInfo) {
        int i;
        if (runningTaskInfo.isFreeform()) {
            i = 5;
        } else {
            if (!TaskInfoKt.isFullscreen(runningTaskInfo) && !TaskInfoKt.isMultiWindow(runningTaskInfo)) {
                throw new IllegalStateException(("Invalid windowing mode: " + runningTaskInfo.getWindowingMode()).toString());
            }
            i = 6;
        }
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(i);
        makeBasic.setPendingIntentBackgroundActivityStartMode(3);
        return makeBasic;
    }

    public static Rect getDefaultDesktopTaskBounds(DisplayLayout displayLayout) {
        int i = displayLayout.mWidth;
        float f = DESKTOP_MODE_INITIAL_BOUNDS_SCALE;
        int i2 = (int) (i * f);
        int i3 = displayLayout.mHeight;
        int i4 = (int) (i3 * f);
        int i5 = (i3 - i4) / 2;
        int i6 = (i - i2) / 2;
        return new Rect(i6, i5, i2 + i6, i4 + i5);
    }

    public static void logD(String str, Object... objArr) {
        ShellProtoLogGroup shellProtoLogGroup = ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE;
        String concat = "%s: ".concat(str);
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add("DesktopTasksController");
        spreadBuilder.addSpread(objArr);
        ProtoLog.d(shellProtoLogGroup, concat, spreadBuilder.list.toArray(new Object[spreadBuilder.list.size()]));
    }

    public static void logV(String str, Object... objArr) {
        ShellProtoLogGroup shellProtoLogGroup = ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE;
        String concat = "%s: ".concat(str);
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add("DesktopTasksController");
        spreadBuilder.addSpread(objArr);
        ProtoLog.v(shellProtoLogGroup, concat, spreadBuilder.list.toArray(new Object[spreadBuilder.list.size()]));
    }

    public static void logW(String str, Object... objArr) {
        ShellProtoLogGroup shellProtoLogGroup = ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE;
        String concat = "%s: ".concat(str);
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add("DesktopTasksController");
        spreadBuilder.addSpread(objArr);
        ProtoLog.w(shellProtoLogGroup, concat, spreadBuilder.list.toArray(new Object[spreadBuilder.list.size()]));
    }

    public final ActivityManager.RunningTaskInfo addAndGetMinimizeChangesIfNeeded(int i, WindowContainerTransaction windowContainerTransaction, ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (!this.desktopTasksLimiter.isPresent()) {
            return null;
        }
        DesktopTasksLimiter desktopTasksLimiter = (DesktopTasksLimiter) this.desktopTasksLimiter.get();
        desktopTasksLimiter.getClass();
        ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopTasksLimiter: addMinimizeBackTaskChangesIfNeeded, newFrontTask=%d", new Object[]{Integer.valueOf(runningTaskInfo.taskId)});
        ActivityManager.RunningTaskInfo taskToMinimizeIfNeeded = desktopTasksLimiter.getTaskToMinimizeIfNeeded(DesktopTasksLimiter.createOrderedTaskListWithGivenTaskInFront(runningTaskInfo.taskId, desktopTasksLimiter.taskRepository.getActiveNonMinimizedOrderedTasks(i)));
        if (taskToMinimizeIfNeeded == null) {
            return null;
        }
        windowContainerTransaction.reorder(taskToMinimizeIfNeeded.token, false);
        return taskToMinimizeIfNeeded;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0052, code lost:
    
        if (r4 == 0) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void addMoveToDesktopChanges(android.window.WindowContainerTransaction r5, android.app.ActivityManager.RunningTaskInfo r6) {
        /*
            r4 = this;
            int r0 = r6.displayId
            com.android.wm.shell.common.DisplayController r1 = r4.displayController
            com.android.wm.shell.common.DisplayLayout r0 = r1.getDisplayLayout(r0)
            if (r0 != 0) goto Lb
            return
        Lb:
            int r1 = r6.displayId
            com.android.wm.shell.RootTaskDisplayAreaOrganizer r2 = r4.rootTaskDisplayAreaOrganizer
            android.util.SparseArray r2 = r2.mDisplayAreasInfo
            java.lang.Object r1 = r2.get(r1)
            android.window.DisplayAreaInfo r1 = (android.window.DisplayAreaInfo) r1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            android.content.res.Configuration r1 = r1.configuration
            android.app.WindowConfiguration r1 = r1.windowConfiguration
            int r1 = r1.getWindowingMode()
            r2 = 5
            if (r1 != r2) goto L26
            r2 = 0
        L26:
            android.window.flags.DesktopModeFlags r1 = android.window.flags.DesktopModeFlags.ENABLE_WINDOWING_DYNAMIC_INITIAL_BOUNDS
            boolean r1 = r1.isTrue()
            if (r1 == 0) goto L33
            android.graphics.Rect r1 = com.android.wm.shell.desktopmode.DesktopModeUtils.calculateInitialBounds$default(r0, r6)
            goto L37
        L33:
            android.graphics.Rect r1 = getDefaultDesktopTaskBounds(r0)
        L37:
            android.window.flags.DesktopModeFlags r3 = android.window.flags.DesktopModeFlags.ENABLE_CASCADING_WINDOWS
            boolean r3 = r3.isTrue()
            if (r3 == 0) goto L42
            r4.cascadeWindow(r6, r1, r0)
        L42:
            android.content.pm.ActivityInfo r4 = r6.topActivityInfo
            if (r4 == 0) goto L54
            android.content.pm.ActivityInfo$WindowLayout r4 = r4.windowLayout
            if (r4 == 0) goto L54
            int r4 = r4.gravity
            r0 = r4 & 7
            r4 = r4 & 112(0x70, float:1.57E-43)
            if (r0 != 0) goto L59
            if (r4 != 0) goto L59
        L54:
            android.window.WindowContainerToken r4 = r6.token
            r5.setBounds(r4, r1)
        L59:
            android.window.WindowContainerToken r4 = r6.token
            r5.setWindowingMode(r4, r2)
            android.window.WindowContainerToken r4 = r6.token
            r0 = 1
            r5.reorder(r4, r0)
            boolean r4 = com.android.wm.shell.shared.desktopmode.DesktopModeStatus.useDesktopOverrideDensity()
            if (r4 == 0) goto L71
            android.window.WindowContainerToken r4 = r6.token
            int r6 = com.android.wm.shell.shared.desktopmode.DesktopModeStatus.DESKTOP_DENSITY_OVERRIDE
            r5.setDensityDpi(r4, r6)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.desktopmode.DesktopTasksController.addMoveToDesktopChanges(android.window.WindowContainerTransaction, android.app.ActivityManager$RunningTaskInfo):void");
    }

    public final void addMoveToFullscreenChanges(WindowContainerTransaction windowContainerTransaction, ActivityManager.RunningTaskInfo runningTaskInfo) {
        DisplayAreaInfo displayAreaInfo = (DisplayAreaInfo) this.rootTaskDisplayAreaOrganizer.mDisplayAreasInfo.get(runningTaskInfo.displayId);
        Intrinsics.checkNotNull(displayAreaInfo);
        windowContainerTransaction.setWindowingMode(runningTaskInfo.token, displayAreaInfo.configuration.windowConfiguration.getWindowingMode() == 1 ? 0 : 1);
        windowContainerTransaction.setBounds(runningTaskInfo.token, new Rect());
        if (DesktopModeStatus.useDesktopOverrideDensity()) {
            windowContainerTransaction.setDensityDpi(runningTaskInfo.token, this.context.getResources().getDisplayMetrics().densityDpi);
        }
        if (this.taskRepository.isOnlyVisibleNonClosingTask(runningTaskInfo.taskId)) {
            removeWallpaperActivity(windowContainerTransaction);
        }
    }

    public final void addPendingMinimizeTransition(IBinder iBinder, ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (runningTaskInfo == null) {
            return;
        }
        this.desktopTasksLimiter.ifPresent(new DesktopTasksController$addPendingMinimizeTransition$1(0, iBinder, runningTaskInfo));
    }

    public final ActivityManager.RunningTaskInfo bringDesktopAppsToFront(int i, WindowContainerTransaction windowContainerTransaction, Integer num) {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        logV("bringDesktopAppsToFront, newTaskId=%d", num);
        moveHomeTask(windowContainerTransaction);
        if (i == 0 && DesktopModeFlags.ENABLE_DESKTOP_WINDOWING_WALLPAPER_ACTIVITY.isTrue()) {
            logV("addWallpaperActivity", new Object[0]);
            Intent intent = new Intent(this.context, (Class<?>) DesktopWallpaperActivity.class);
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setLaunchWindowingMode(1);
            makeBasic.setPendingIntentBackgroundActivityStartMode(3);
            windowContainerTransaction.sendPendingIntent(PendingIntent.getActivity(this.context, 0, intent, 67108864), intent, makeBasic.toBundle());
        }
        List activeNonMinimizedOrderedTasks = this.taskRepository.getActiveNonMinimizedOrderedTasks(i);
        if (num == null || !this.desktopTasksLimiter.isPresent()) {
            runningTaskInfo = null;
        } else {
            DesktopTasksLimiter desktopTasksLimiter = (DesktopTasksLimiter) this.desktopTasksLimiter.get();
            int intValue = num.intValue();
            desktopTasksLimiter.getClass();
            runningTaskInfo = desktopTasksLimiter.getTaskToMinimizeIfNeeded(DesktopTasksLimiter.createOrderedTaskListWithGivenTaskInFront(intValue, activeNonMinimizedOrderedTasks));
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : activeNonMinimizedOrderedTasks) {
            int intValue2 = ((Number) obj).intValue();
            if (runningTaskInfo == null || intValue2 != runningTaskInfo.taskId) {
                arrayList.add(obj);
            }
        }
        Iterator it = CollectionsKt.reversed(arrayList).iterator();
        while (it.hasNext()) {
            ActivityManager.RunningTaskInfo runningTaskInfo2 = this.shellTaskOrganizer.getRunningTaskInfo(((Number) it.next()).intValue());
            if (runningTaskInfo2 != null) {
                windowContainerTransaction.reorder(runningTaskInfo2.token, true);
            }
        }
        DesktopTasksController$dragToDesktopStateListener$1 desktopTasksController$dragToDesktopStateListener$1 = this.taskbarDesktopTaskListener;
        if (desktopTasksController$dragToDesktopStateListener$1 != null) {
            desktopTasksController$dragToDesktopStateListener$1.onTaskbarCornerRoundingUpdate(doesAnyTaskRequireTaskbarRounding(i, null));
        }
        return runningTaskInfo;
    }

    public final void cascadeWindow(TaskInfo taskInfo, Rect rect, DisplayLayout displayLayout) {
        Rect rect2 = new Rect();
        displayLayout.getStableBounds(rect2);
        int i = displayLayout.mNavBarFrameHeight;
        int i2 = displayLayout.mTaskbarFrameHeight;
        if (i != i2) {
            rect2.bottom = displayLayout.mHeight - i2;
        }
        Integer num = (Integer) CollectionsKt.firstOrNull(this.taskRepository.getActiveNonMinimizedOrderedTasks(taskInfo.displayId));
        if (num != null) {
            ActivityManager.RunningTaskInfo runningTaskInfo = this.shellTaskOrganizer.getRunningTaskInfo(num.intValue());
            if (runningTaskInfo != null) {
                Resources resources = this.context.getResources();
                Rect bounds = runningTaskInfo.configuration.windowConfiguration.getBounds();
                Rect rect3 = new Rect(rect);
                DesktopTaskPosition desktopTaskPosition = DesktopTaskPositionKt.getDesktopTaskPosition(rect2, bounds);
                DesktopTaskPosition.Center center = DesktopTaskPosition.Center.INSTANCE;
                Point topLeftCoordinates = center.getTopLeftCoordinates(rect2, rect3);
                rect3.offsetTo(topLeftCoordinates.x, topLeftCoordinates.y);
                int dimensionPixelSize = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.freeform_required_visible_empty_space_in_header);
                boolean z = rect3.left - bounds.left > dimensionPixelSize;
                boolean z2 = rect3.top - bounds.top > dimensionPixelSize;
                boolean z3 = bounds.right - rect3.right > dimensionPixelSize;
                boolean z4 = bounds.bottom - rect3.bottom > dimensionPixelSize;
                if ((!z && !z2 && !z3 && !z4) || !center.equals(desktopTaskPosition)) {
                    topLeftCoordinates = desktopTaskPosition.next().getTopLeftCoordinates(rect2, rect);
                }
                rect.offsetTo(topLeftCoordinates.x, topLeftCoordinates.y);
            }
        }
    }

    public final boolean doesAnyTaskRequireTaskbarRounding(int i, Integer num) {
        List activeNonMinimizedOrderedTasks = this.taskRepository.getActiveNonMinimizedOrderedTasks(i);
        ArrayList arrayList = new ArrayList();
        for (Object obj : activeNonMinimizedOrderedTasks) {
            int intValue = ((Number) obj).intValue();
            if (num == null || intValue != num.intValue()) {
                arrayList.add(obj);
            }
        }
        boolean z = false;
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ActivityManager.RunningTaskInfo runningTaskInfo = this.shellTaskOrganizer.getRunningTaskInfo(((Number) it.next()).intValue());
                if (runningTaskInfo != null) {
                    DisplayLayout displayLayout = this.displayController.getDisplayLayout(runningTaskInfo.displayId);
                    Rect rect = new Rect();
                    if (displayLayout != null) {
                        displayLayout.getStableBounds(rect);
                    }
                    logD("taskInfo = %s", runningTaskInfo);
                    Rect bounds = runningTaskInfo.configuration.windowConfiguration.getBounds();
                    SnapPosition snapPosition = SnapPosition.LEFT;
                    logD("isTaskSnappedToHalfScreen(taskInfo) = %s", Boolean.valueOf(getSnapBounds(runningTaskInfo, snapPosition).equals(bounds) || getSnapBounds(runningTaskInfo, SnapPosition.RIGHT).equals(bounds)));
                    Rect bounds2 = runningTaskInfo.configuration.windowConfiguration.getBounds();
                    float f = DesktopModeUtils.DESKTOP_MODE_INITIAL_BOUNDS_SCALE;
                    logD("isMaximizedToStableBoundsEdges(taskInfo, stableBounds) = %s", Boolean.valueOf(bounds2.width() == rect.width() && bounds2.height() == rect.height()));
                    Rect bounds3 = runningTaskInfo.configuration.windowConfiguration.getBounds();
                    if (!getSnapBounds(runningTaskInfo, snapPosition).equals(bounds3) && !getSnapBounds(runningTaskInfo, SnapPosition.RIGHT).equals(bounds3)) {
                        Rect bounds4 = runningTaskInfo.configuration.windowConfiguration.getBounds();
                        if (bounds4.width() != rect.width() || bounds4.height() != rect.height()) {
                        }
                    }
                    z = true;
                    break;
                }
                return false;
            }
        }
        logD("doesAnyTaskRequireTaskbarRounding = %s", Boolean.valueOf(z));
        return z;
    }

    public final void exitSplitIfApplicable(WindowContainerTransaction windowContainerTransaction, ActivityManager.RunningTaskInfo runningTaskInfo) {
        SplitScreenController splitScreenController = this.splitScreenController;
        if (splitScreenController == null) {
            splitScreenController = null;
        }
        if (splitScreenController.isTaskInSplitScreen(runningTaskInfo.taskId)) {
            SplitScreenController splitScreenController2 = this.splitScreenController;
            SplitScreenController splitScreenController3 = splitScreenController2 == null ? null : splitScreenController2;
            if (splitScreenController2 == null) {
                splitScreenController2 = null;
            }
            splitScreenController3.prepareExitSplitScreen(splitScreenController2.getStageOfTask(runningTaskInfo.taskId), 12, windowContainerTransaction);
            SplitScreenController splitScreenController4 = this.splitScreenController;
            StageCoordinator transitionHandler = (splitScreenController4 != null ? splitScreenController4 : null).getTransitionHandler();
            if (transitionHandler != null) {
                transitionHandler.setSplitsVisible(false);
            }
        }
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.context;
    }

    public final ActivityManager.RunningTaskInfo getFocusedFreeformTask(int i) {
        Object obj;
        Iterator it = this.shellTaskOrganizer.getRunningTasks(i).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) obj;
            if (runningTaskInfo.isFocused && runningTaskInfo.getWindowingMode() == 5) {
                break;
            }
        }
        return (ActivityManager.RunningTaskInfo) obj;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mainExecutor;
    }

    public final Rect getSnapBounds(ActivityManager.RunningTaskInfo runningTaskInfo, SnapPosition snapPosition) {
        DisplayLayout displayLayout = this.displayController.getDisplayLayout(runningTaskInfo.displayId);
        if (displayLayout == null) {
            return new Rect();
        }
        Rect rect = new Rect();
        displayLayout.getStableBounds(rect);
        int width = rect.width() / 2;
        int ordinal = snapPosition.ordinal();
        if (ordinal == 0) {
            int i = rect.right;
            return new Rect(i - width, rect.top, i, rect.bottom);
        }
        if (ordinal != 1) {
            throw new NoWhenBranchMatchedException();
        }
        int i2 = rect.left;
        return new Rect(i2, rect.top, width + i2, rect.bottom);
    }

    public final DesktopModeVisualIndicator getVisualIndicator() {
        return this.visualIndicator;
    }

    /* JADX WARN: Code restructure failed: missing block: B:91:0x024b, code lost:
    
        if (r12.isEmpty() != false) goto L129;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00dc  */
    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.window.WindowContainerTransaction handleRequest(android.os.IBinder r11, android.window.TransitionRequestInfo r12) {
        /*
            Method dump skipped, instructions count: 648
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.desktopmode.DesktopTasksController.handleRequest(android.os.IBinder, android.window.TransitionRequestInfo):android.window.WindowContainerTransaction");
    }

    public final void handleSnapResizingTask(ActivityManager.RunningTaskInfo runningTaskInfo, SnapPosition snapPosition, SurfaceControl surfaceControl, Rect rect, Rect rect2) {
        releaseVisualIndicator();
        if (runningTaskInfo.isResizeable || !DesktopModeFlags.DISABLE_NON_RESIZABLE_APP_SNAP_RESIZE.isTrue()) {
            this.interactionJankMonitor.begin(surfaceControl, this.context, this.handler, 118, "drag_resizable");
            snapToHalfScreen(runningTaskInfo, surfaceControl, rect, snapPosition);
        } else {
            this.interactionJankMonitor.begin(surfaceControl, this.context, this.handler, 118, "drag_non_resizable");
            this.returnToDragStartAnimator.start(runningTaskInfo.taskId, surfaceControl, rect, rect2, runningTaskInfo.isResizeable);
        }
    }

    public final void moveHomeTask(WindowContainerTransaction windowContainerTransaction) {
        Object obj;
        Iterator it = this.shellTaskOrganizer.getRunningTasks(this.context.getDisplayId()).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((ActivityManager.RunningTaskInfo) obj).getActivityType() == 2) {
                    break;
                }
            }
        }
        ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) obj;
        if (runningTaskInfo != null) {
            windowContainerTransaction.reorder(runningTaskInfo.getToken(), true);
        }
    }

    public final void moveRunningTaskToDesktop(ActivityManager.RunningTaskInfo runningTaskInfo, WindowContainerTransaction windowContainerTransaction, DesktopModeTransitionSource desktopModeTransitionSource) {
        if (DesktopModeFlags.ENABLE_DESKTOP_WINDOWING_MODALS_POLICY.isTrue() && AppCompatUtils.isTopActivityExemptFromDesktopWindowing(this.context, runningTaskInfo)) {
            logW("Cannot enter desktop for taskId %d, ineligible top activity found", Integer.valueOf(runningTaskInfo.taskId));
            return;
        }
        logV("moveRunningTaskToDesktop taskId=%d", Integer.valueOf(runningTaskInfo.taskId));
        exitSplitIfApplicable(windowContainerTransaction, runningTaskInfo);
        ActivityManager.RunningTaskInfo bringDesktopAppsToFront = bringDesktopAppsToFront(runningTaskInfo.displayId, windowContainerTransaction, Integer.valueOf(runningTaskInfo.taskId));
        addMoveToDesktopChanges(windowContainerTransaction, runningTaskInfo);
        if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
            this.shellTaskOrganizer.applyTransaction(windowContainerTransaction);
            return;
        }
        IBinder moveToDesktop = this.enterDesktopTaskTransitionHandler.moveToDesktop(windowContainerTransaction, desktopModeTransitionSource);
        Intrinsics.checkNotNull(moveToDesktop);
        addPendingMinimizeTransition(moveToDesktop, bringDesktopAppsToFront);
    }

    public final boolean moveTaskToDesktop(int i, WindowContainerTransaction windowContainerTransaction, DesktopModeTransitionSource desktopModeTransitionSource) {
        ActivityManager.RunningTaskInfo runningTaskInfo = this.shellTaskOrganizer.getRunningTaskInfo(i);
        if (runningTaskInfo != null) {
            moveRunningTaskToDesktop(runningTaskInfo, windowContainerTransaction, desktopModeTransitionSource);
            return true;
        }
        ActivityManager.RecentTaskInfo recentTaskInfo = null;
        RecentTasksController recentTasksController = this.recentTasksController;
        if (recentTasksController != null) {
            List recentTasks = recentTasksController.mActivityTaskManager.getRecentTasks(Integer.MAX_VALUE, 2, ActivityManager.getCurrentUser());
            int i2 = 0;
            while (true) {
                if (i2 >= recentTasks.size()) {
                    break;
                }
                ActivityManager.RecentTaskInfo recentTaskInfo2 = (ActivityManager.RecentTaskInfo) recentTasks.get(i2);
                if (!recentTaskInfo2.isVisible && i == recentTaskInfo2.taskId) {
                    recentTaskInfo = recentTaskInfo2;
                    break;
                }
                i2++;
            }
        }
        if (recentTaskInfo == null) {
            logW("moveBackgroundTaskToDesktop taskId=%d not found", Integer.valueOf(i));
            return false;
        }
        logV("moveBackgroundTaskToDesktop with taskId=%d", Integer.valueOf(i));
        ActivityManager.RunningTaskInfo bringDesktopAppsToFront = bringDesktopAppsToFront(0, windowContainerTransaction, Integer.valueOf(i));
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(5);
        windowContainerTransaction.startTask(i, makeBasic.toBundle());
        IBinder moveToDesktop = this.enterDesktopTaskTransitionHandler.moveToDesktop(windowContainerTransaction, desktopModeTransitionSource);
        Intrinsics.checkNotNull(moveToDesktop);
        addPendingMinimizeTransition(moveToDesktop, bringDesktopAppsToFront);
        return true;
    }

    public final void moveTaskToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
        logV("moveTaskToFront taskId=%s", Integer.valueOf(runningTaskInfo.taskId));
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.reorder(runningTaskInfo.token, true);
        ActivityManager.RunningTaskInfo addAndGetMinimizeChangesIfNeeded = addAndGetMinimizeChangesIfNeeded(runningTaskInfo.displayId, windowContainerTransaction, runningTaskInfo);
        if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
            this.shellTaskOrganizer.applyTransaction(windowContainerTransaction);
            return;
        }
        IBinder startTransition = this.transitions.startTransition(3, windowContainerTransaction, null);
        Intrinsics.checkNotNull(startTransition);
        addPendingMinimizeTransition(startTransition, addAndGetMinimizeChangesIfNeeded);
    }

    public final void moveToFullscreenWithAnimation(ActivityManager.RunningTaskInfo runningTaskInfo, Point point, DesktopModeTransitionSource desktopModeTransitionSource) {
        logV("moveToFullscreenWithAnimation taskId=%d", Integer.valueOf(runningTaskInfo.taskId));
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        addMoveToFullscreenChanges(windowContainerTransaction, runningTaskInfo);
        if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
            this.shellTaskOrganizer.applyTransaction(windowContainerTransaction);
            releaseVisualIndicator();
            return;
        }
        DesktopTasksController$mOnAnimationFinishedCallback$1 desktopTasksController$mOnAnimationFinishedCallback$1 = this.mOnAnimationFinishedCallback;
        ExitDesktopTaskTransitionHandler exitDesktopTaskTransitionHandler = this.exitDesktopTaskTransitionHandler;
        exitDesktopTaskTransitionHandler.mPosition = point;
        exitDesktopTaskTransitionHandler.mOnAnimationFinishedCallback = desktopTasksController$mOnAnimationFinishedCallback$1;
        int ordinal = desktopModeTransitionSource.ordinal();
        exitDesktopTaskTransitionHandler.mPendingTransitionTokens.add(exitDesktopTaskTransitionHandler.mTransitions.startTransition(ordinal != 0 ? ordinal != 2 ? ordinal != 3 ? 1108 : 1107 : 1106 : 1105, windowContainerTransaction, exitDesktopTaskTransitionHandler));
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0079 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007a  */
    @Override // com.android.wm.shell.draganddrop.DragAndDropController.DragAndDropListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onUnhandledDrag(android.app.PendingIntent r12, android.view.DragEvent r13, com.android.wm.shell.draganddrop.GlobalDragListener$onUnhandledDrop$1 r14) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.desktopmode.DesktopTasksController.onUnhandledDrag(android.app.PendingIntent, android.view.DragEvent, com.android.wm.shell.draganddrop.GlobalDragListener$onUnhandledDrop$1):boolean");
    }

    public final void releaseVisualIndicator() {
        final SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        DesktopModeVisualIndicator desktopModeVisualIndicator = this.visualIndicator;
        if (desktopModeVisualIndicator != null) {
            desktopModeVisualIndicator.releaseVisualIndicator(transaction);
        }
        this.visualIndicator = null;
        this.syncQueue.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$releaseVisualIndicator$1
            @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
            public final void runWithTransaction(SurfaceControl.Transaction transaction2) {
                transaction2.merge(transaction);
                transaction.close();
            }
        });
    }

    public final void removeWallpaperActivity(WindowContainerTransaction windowContainerTransaction) {
        WindowContainerToken windowContainerToken = this.taskRepository.wallpaperActivityToken;
        if (windowContainerToken != null) {
            logV("removeWallpaperActivity", new Object[0]);
            windowContainerTransaction.removeTask(windowContainerToken);
        }
    }

    public final void requestSplit(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z) {
        DragToDesktopTransitionHandler dragToDesktopTransitionHandler = this.dragToDesktopTransitionHandler;
        boolean z2 = dragToDesktopTransitionHandler.transitionState != null;
        if (TaskInfoKt.isFullscreen(runningTaskInfo) || runningTaskInfo.isFreeform() || z2) {
            if (z2) {
                releaseVisualIndicator();
                dragToDesktopTransitionHandler.cancelDragToDesktopTransition(z ? DragToDesktopTransitionHandler.CancelState.CANCEL_SPLIT_LEFT : DragToDesktopTransitionHandler.CancelState.CANCEL_SPLIT_RIGHT);
                return;
            }
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            windowContainerTransaction.setWindowingMode(runningTaskInfo.token, 6);
            windowContainerTransaction.setDensityDpi(runningTaskInfo.token, this.context.getResources().getDisplayMetrics().densityDpi);
            if (this.taskRepository.isOnlyVisibleNonClosingTask(runningTaskInfo.taskId)) {
                removeWallpaperActivity(windowContainerTransaction);
            }
            SplitScreenController splitScreenController = this.splitScreenController;
            if (splitScreenController == null) {
                splitScreenController = null;
            }
            splitScreenController.requestEnterSplitSelect(!z ? 1 : 0, runningTaskInfo, runningTaskInfo.configuration.windowConfiguration.getBounds(), windowContainerTransaction);
        }
    }

    public final void snapToHalfScreen(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, Rect rect, SnapPosition snapPosition) {
        Rect snapBounds = getSnapBounds(runningTaskInfo, snapPosition);
        if (snapBounds.equals(runningTaskInfo.configuration.windowConfiguration.getBounds())) {
            if (snapBounds.equals(rect)) {
                return;
            }
            this.returnToDragStartAnimator.start(runningTaskInfo.taskId, surfaceControl, rect, snapBounds, runningTaskInfo.isResizeable);
            return;
        }
        DesktopTasksController$dragToDesktopStateListener$1 desktopTasksController$dragToDesktopStateListener$1 = this.taskbarDesktopTaskListener;
        if (desktopTasksController$dragToDesktopStateListener$1 != null) {
            desktopTasksController$dragToDesktopStateListener$1.onTaskbarCornerRoundingUpdate(true);
        }
        WindowContainerTransaction bounds = new WindowContainerTransaction().setBounds(runningTaskInfo.token, snapBounds);
        if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
            this.shellTaskOrganizer.applyTransaction(bounds);
            return;
        }
        ToggleResizeDesktopTaskTransitionHandler toggleResizeDesktopTaskTransitionHandler = this.toggleResizeDesktopTaskTransitionHandler;
        toggleResizeDesktopTaskTransitionHandler.transitions.startTransition(1014, bounds, toggleResizeDesktopTaskTransitionHandler);
        toggleResizeDesktopTaskTransitionHandler.initialBounds = rect;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        return false;
    }

    public final DesktopModeVisualIndicator.IndicatorType updateVisualIndicator(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, float f, float f2, DesktopModeVisualIndicator.DragStartState dragStartState) {
        DesktopModeVisualIndicator desktopModeVisualIndicator;
        DesktopModeVisualIndicator desktopModeVisualIndicator2 = this.visualIndicator;
        if (desktopModeVisualIndicator2 == null) {
            desktopModeVisualIndicator = new DesktopModeVisualIndicator(this.syncQueue, runningTaskInfo, this.displayController, this.context, surfaceControl, this.rootTaskDisplayAreaOrganizer, dragStartState);
        } else {
            desktopModeVisualIndicator = desktopModeVisualIndicator2;
        }
        if (desktopModeVisualIndicator2 == null) {
            this.visualIndicator = desktopModeVisualIndicator;
        }
        return desktopModeVisualIndicator.updateIndicatorType(new PointF(f, f2));
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IDesktopModeImpl extends Binder implements ExternalInterfaceBinder, IInterface {
        public DesktopTasksController controller;
        public final DesktopTasksController$IDesktopModeImpl$listener$1 listener;
        public final DesktopTasksController$dragToDesktopStateListener$1 mTaskbarDesktopTaskListener;
        public final SingleInstanceRemoteListener remoteListener;

        /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.desktopmode.DesktopTasksController$IDesktopModeImpl$listener$1] */
        public IDesktopModeImpl(DesktopTasksController desktopTasksController) {
            attachInterface(this, "com.android.wm.shell.desktopmode.IDesktopMode");
            this.controller = desktopTasksController;
            this.listener = new DesktopModeTaskRepository.VisibleTasksListener() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$IDesktopModeImpl$listener$1
                @Override // com.android.wm.shell.desktopmode.DesktopModeTaskRepository.VisibleTasksListener
                public final void onTasksVisibilityChanged(final int i, final int i2) {
                    ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "IDesktopModeImpl: onVisibilityChanged display=%d visible=%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
                    SingleInstanceRemoteListener singleInstanceRemoteListener = DesktopTasksController.IDesktopModeImpl.this.remoteListener;
                    if (singleInstanceRemoteListener == null) {
                        singleInstanceRemoteListener = null;
                    }
                    singleInstanceRemoteListener.call(new SingleInstanceRemoteListener.RemoteCall() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$IDesktopModeImpl$listener$1$onTasksVisibilityChanged$1
                        @Override // com.android.wm.shell.common.SingleInstanceRemoteListener.RemoteCall
                        public final void accept(Object obj) {
                            IDesktopTaskListener$Stub$Proxy iDesktopTaskListener$Stub$Proxy = (IDesktopTaskListener$Stub$Proxy) obj;
                            int i3 = i;
                            int i4 = i2;
                            Parcel obtain = Parcel.obtain(iDesktopTaskListener$Stub$Proxy.mRemote);
                            try {
                                obtain.writeInterfaceToken("com.android.wm.shell.desktopmode.IDesktopTaskListener");
                                obtain.writeInt(i3);
                                obtain.writeInt(i4);
                                iDesktopTaskListener$Stub$Proxy.mRemote.transact(1, obtain, null, 1);
                            } finally {
                                obtain.recycle();
                            }
                        }
                    });
                }
            };
            this.mTaskbarDesktopTaskListener = new DesktopTasksController$dragToDesktopStateListener$1(this);
            final int i = 0;
            final int i2 = 1;
            this.remoteListener = new SingleInstanceRemoteListener(desktopTasksController, new Consumer(this) { // from class: com.android.wm.shell.desktopmode.DesktopTasksController.IDesktopModeImpl.1
                public final /* synthetic */ IDesktopModeImpl this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i) {
                        case 0:
                            DesktopTasksController desktopTasksController2 = (DesktopTasksController) obj;
                            IDesktopModeImpl iDesktopModeImpl = this.this$0;
                            Intrinsics.checkNotNull(desktopTasksController2);
                            desktopTasksController2.taskRepository.addVisibleTasksListener(iDesktopModeImpl.listener, desktopTasksController2.mainExecutor);
                            desktopTasksController2.taskbarDesktopTaskListener = iDesktopModeImpl.mTaskbarDesktopTaskListener;
                            break;
                        default:
                            DesktopTasksController desktopTasksController3 = (DesktopTasksController) obj;
                            IDesktopModeImpl iDesktopModeImpl2 = this.this$0;
                            Intrinsics.checkNotNull(desktopTasksController3);
                            desktopTasksController3.taskRepository.visibleTasksListeners.remove(iDesktopModeImpl2.listener);
                            desktopTasksController3.taskbarDesktopTaskListener = null;
                            break;
                    }
                }
            }, new Consumer(this) { // from class: com.android.wm.shell.desktopmode.DesktopTasksController.IDesktopModeImpl.1
                public final /* synthetic */ IDesktopModeImpl this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i2) {
                        case 0:
                            DesktopTasksController desktopTasksController2 = (DesktopTasksController) obj;
                            IDesktopModeImpl iDesktopModeImpl = this.this$0;
                            Intrinsics.checkNotNull(desktopTasksController2);
                            desktopTasksController2.taskRepository.addVisibleTasksListener(iDesktopModeImpl.listener, desktopTasksController2.mainExecutor);
                            desktopTasksController2.taskbarDesktopTaskListener = iDesktopModeImpl.mTaskbarDesktopTaskListener;
                            break;
                        default:
                            DesktopTasksController desktopTasksController3 = (DesktopTasksController) obj;
                            IDesktopModeImpl iDesktopModeImpl2 = this.this$0;
                            Intrinsics.checkNotNull(desktopTasksController3);
                            desktopTasksController3.taskRepository.visibleTasksListeners.remove(iDesktopModeImpl2.listener);
                            desktopTasksController3.taskbarDesktopTaskListener = null;
                            break;
                    }
                }
            });
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            SingleInstanceRemoteListener singleInstanceRemoteListener = this.remoteListener;
            if (singleInstanceRemoteListener == null) {
                singleInstanceRemoteListener = null;
            }
            singleInstanceRemoteListener.unregister();
            this.controller = null;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IDesktopTaskListener$Stub$Proxy iDesktopTaskListener$Stub$Proxy;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.desktopmode.IDesktopMode");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.desktopmode.IDesktopMode");
                return true;
            }
            switch (i) {
                case 1:
                    int readInt = parcel.readInt();
                    RemoteTransition remoteTransition = (RemoteTransition) parcel.readTypedObject(RemoteTransition.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.controller, "showDesktopApps", new DesktopTasksController$IDesktopModeImpl$moveToDesktop$1(readInt, remoteTransition), false);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.readInt();
                    parcel.enforceNoDataAvail();
                    ProtoLog.w(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "IDesktopModeImpl: stashDesktopApps is deprecated", new Object[0]);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.readInt();
                    parcel.enforceNoDataAvail();
                    ProtoLog.w(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "IDesktopModeImpl: hideStashedDesktopApps is deprecated", new Object[0]);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    final int readInt2 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.controller, "showDesktopApp", new Consumer() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$IDesktopModeImpl$showDesktopApp$1
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            DesktopTasksController desktopTasksController = (DesktopTasksController) obj;
                            ActivityManager.RunningTaskInfo runningTaskInfo = desktopTasksController.shellTaskOrganizer.getRunningTaskInfo(readInt2);
                            if (runningTaskInfo != null) {
                                desktopTasksController.moveTaskToFront(runningTaskInfo);
                            }
                        }
                    }, false);
                    return true;
                case 5:
                    int readInt3 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    int[] iArr = new int[1];
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.controller, "visibleTaskCount", new DesktopTasksController$IDesktopModeImpl$moveToDesktop$1(iArr, readInt3), true);
                    int i3 = iArr[0];
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 6:
                    ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) parcel.readTypedObject(ActivityManager.RunningTaskInfo.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.controller, "onDesktopSplitSelectAnimComplete", new DesktopTasksController$mOnAnimationFinishedCallback$1(1, runningTaskInfo), false);
                    return true;
                case 7:
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder == null) {
                        iDesktopTaskListener$Stub$Proxy = null;
                    } else {
                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.android.wm.shell.desktopmode.IDesktopTaskListener");
                        if (queryLocalInterface == null || !(queryLocalInterface instanceof IDesktopTaskListener$Stub$Proxy)) {
                            IDesktopTaskListener$Stub$Proxy iDesktopTaskListener$Stub$Proxy2 = new IDesktopTaskListener$Stub$Proxy();
                            iDesktopTaskListener$Stub$Proxy2.mRemote = readStrongBinder;
                            iDesktopTaskListener$Stub$Proxy = iDesktopTaskListener$Stub$Proxy2;
                        } else {
                            iDesktopTaskListener$Stub$Proxy = (IDesktopTaskListener$Stub$Proxy) queryLocalInterface;
                        }
                    }
                    parcel.enforceNoDataAvail();
                    ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "IDesktopModeImpl: set task listener=%s", new Object[]{iDesktopTaskListener$Stub$Proxy});
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.controller, "setTaskListener", new DesktopTasksController$addPendingMinimizeTransition$1(1, iDesktopTaskListener$Stub$Proxy, this), false);
                    return true;
                case 8:
                    int readInt4 = parcel.readInt();
                    DesktopModeTransitionSource desktopModeTransitionSource = (DesktopModeTransitionSource) parcel.readTypedObject(DesktopModeTransitionSource.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.controller, "moveTaskToDesktop", new DesktopTasksController$IDesktopModeImpl$moveToDesktop$1(readInt4, desktopModeTransitionSource), false);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder, android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }

    public static /* synthetic */ void getTaskbarDesktopTaskListener$annotations() {
    }
}
