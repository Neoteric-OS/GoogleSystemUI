package com.android.wm.shell.draganddrop;

import android.os.RemoteException;
import android.util.Log;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.back.BackAnimationController$$ExternalSyntheticOutline0;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.draganddrop.DragAndDropController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DragAndDropController$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DragAndDropController f$0;

    public /* synthetic */ DragAndDropController$$ExternalSyntheticLambda5(DragAndDropController dragAndDropController, int i) {
        this.$r8$classId = i;
        this.f$0 = dragAndDropController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        final DragAndDropController dragAndDropController = this.f$0;
        switch (i) {
            case 0:
                ((HandlerExecutor) dragAndDropController.mMainExecutor).executeDelayed(new DragAndDropController$$ExternalSyntheticLambda5(dragAndDropController, 1), 0L);
                dragAndDropController.mShellController.addExternalInterface("extra_shell_drag_and_drop", new Supplier() { // from class: com.android.wm.shell.draganddrop.DragAndDropController$$ExternalSyntheticLambda8
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        DragAndDropController dragAndDropController2 = DragAndDropController.this;
                        dragAndDropController2.getClass();
                        DragAndDropController.IDragAndDropImpl iDragAndDropImpl = new DragAndDropController.IDragAndDropImpl();
                        iDragAndDropImpl.attachInterface(iDragAndDropImpl, "com.android.wm.shell.draganddrop.IDragAndDrop");
                        iDragAndDropImpl.mController = dragAndDropController2;
                        return iDragAndDropImpl;
                    }
                }, dragAndDropController);
                ShellTaskOrganizer shellTaskOrganizer = dragAndDropController.mShellTaskOrganizer;
                synchronized (shellTaskOrganizer.mLock) {
                    shellTaskOrganizer.mTaskVanishedListeners.add(dragAndDropController);
                }
                dragAndDropController.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.draganddrop.DragAndDropController$$ExternalSyntheticLambda9
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        DragAndDropController dragAndDropController2 = DragAndDropController.this;
                        PrintWriter printWriter = (PrintWriter) obj;
                        String str = (String) obj2;
                        dragAndDropController2.getClass();
                        String str2 = str + "  ";
                        printWriter.println(str + "DragAndDropController");
                        printWriter.println(str2 + "listeners=" + dragAndDropController2.mListeners.size());
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        sb.append("Per display:");
                        printWriter.println(sb.toString());
                        for (int i2 = 0; i2 < dragAndDropController2.mDisplayDropTargets.size(); i2++) {
                            DragAndDropController.PerDisplay perDisplay = (DragAndDropController.PerDisplay) dragAndDropController2.mDisplayDropTargets.valueAt(i2);
                            perDisplay.getClass();
                            String str3 = str2 + "  ";
                            StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str3, "displayId=");
                            m.append(perDisplay.displayId);
                            printWriter.println(m.toString());
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str3);
                            sb2.append("hasDrawn=");
                            StringBuilder m2 = BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(sb2, perDisplay.hasDrawn, printWriter, str3, "isHandlingDrag="), perDisplay.isHandlingDrag, printWriter, str3, "activeDragCount=");
                            m2.append(perDisplay.activeDragCount);
                            printWriter.println(m2.toString());
                            DragLayout dragLayout = (DragLayout) perDisplay.dragLayout;
                            dragLayout.getClass();
                            String str4 = str3 + "  ";
                            printWriter.println(str3 + "DragLayout:");
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(str4);
                            sb3.append("mIsLeftRightSplitInPortrait=");
                            StringBuilder m3 = BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(sb3, dragLayout.mAllowLeftRightSplitInPortrait, printWriter, str4, "mIsLeftRightSplit="), dragLayout.mIsLeftRightSplit, printWriter, str4, "mDisplayMargin=");
                            m3.append(dragLayout.mDisplayMargin);
                            printWriter.println(m3.toString());
                            printWriter.println(str4 + "mDividerSize=" + dragLayout.mDividerSize);
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(str4);
                            sb4.append("mIsShowing=");
                            StringBuilder m4 = BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(sb4, dragLayout.mIsShowing, printWriter, str4, "mHasDropped="), dragLayout.mHasDropped, printWriter, str4, "mCurrentTarget=");
                            m4.append(dragLayout.mCurrentTarget);
                            printWriter.println(m4.toString());
                            printWriter.println(str4 + "mInsets=" + dragLayout.mInsets);
                            printWriter.println(str4 + "mTouchableRegion=" + dragLayout.mTouchableRegion);
                        }
                    }
                }, dragAndDropController);
                GlobalDragListener globalDragListener = dragAndDropController.mGlobalDragListener;
                boolean z = globalDragListener.callback == null;
                globalDragListener.callback = dragAndDropController;
                if (z) {
                    try {
                        ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, "%s unhandled drag listener", new Object[]{"Registering"});
                        globalDragListener.wmService.setGlobalDragListener(globalDragListener.callback != null ? globalDragListener.globalDragListener : null);
                        return;
                    } catch (RemoteException unused) {
                        Log.e("GlobalDragListener", "Failed to set unhandled drag listener");
                        return;
                    }
                }
                return;
            default:
                dragAndDropController.mDisplayController.addDisplayWindowListener(dragAndDropController);
                return;
        }
    }
}
