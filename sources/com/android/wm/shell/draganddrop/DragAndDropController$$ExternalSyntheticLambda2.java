package com.android.wm.shell.draganddrop;

import android.content.res.Configuration;
import com.android.wm.shell.common.split.SplitScreenUtils;
import com.android.wm.shell.draganddrop.DragAndDropController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DragAndDropController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DragAndDropController f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ DragAndDropController$$ExternalSyntheticLambda2(DragAndDropController dragAndDropController, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = dragAndDropController;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DragAndDropController.PerDisplay perDisplay = (DragAndDropController.PerDisplay) this.f$1;
                if (perDisplay.activeDragCount == 0) {
                    DragAndDropController.setDropTargetWindowVisibility(perDisplay, 4);
                    break;
                }
                break;
            case 1:
                DragAndDropController.PerDisplay perDisplay2 = (DragAndDropController.PerDisplay) this.f$1;
                if (perDisplay2.activeDragCount == 0) {
                    DragAndDropController.setDropTargetWindowVisibility(perDisplay2, 4);
                    break;
                }
                break;
            default:
                DragAndDropController dragAndDropController = this.f$0;
                Configuration configuration = (Configuration) this.f$1;
                for (int i = 0; i < dragAndDropController.mDisplayDropTargets.size(); i++) {
                    DragLayout dragLayout = (DragLayout) ((DragAndDropController.PerDisplay) dragAndDropController.mDisplayDropTargets.get(i)).dragLayout;
                    boolean isLeftRightSplit = SplitScreenUtils.isLeftRightSplit(dragLayout.mAllowLeftRightSplitInPortrait, configuration);
                    if (isLeftRightSplit != dragLayout.mIsLeftRightSplit) {
                        dragLayout.mIsLeftRightSplit = isLeftRightSplit;
                        dragLayout.setOrientation(!isLeftRightSplit ? 1 : 0);
                        dragLayout.updateContainerMargins(dragLayout.mIsLeftRightSplit);
                    }
                    int diff = configuration.diff(dragLayout.mLastConfiguration);
                    if ((Integer.MIN_VALUE & diff) != 0 || (diff & 512) != 0) {
                        dragLayout.mDropZoneView1.onThemeChange();
                        dragLayout.mDropZoneView2.onThemeChange();
                    }
                    dragLayout.mLastConfiguration.setTo(configuration);
                    dragLayout.requestLayout();
                }
                break;
        }
    }
}
