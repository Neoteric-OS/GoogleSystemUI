package com.android.wm.shell.splitscreen;

import android.graphics.Rect;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.RecentTasksController;
import com.android.wm.shell.shared.split.SplitBounds;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageCoordinator$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ StageCoordinator f$0;

    public /* synthetic */ StageCoordinator$$ExternalSyntheticLambda5(StageCoordinator stageCoordinator, int i) {
        this.$r8$classId = i;
        this.f$0 = stageCoordinator;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i;
        int i2;
        int i3 = this.$r8$classId;
        StageCoordinator stageCoordinator = this.f$0;
        switch (i3) {
            case 0:
                final RecentTasksController recentTasksController = (RecentTasksController) obj;
                stageCoordinator.getClass();
                final int i4 = 0;
                stageCoordinator.mMainStage.doForAllChildTasks(new Consumer() { // from class: com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda19
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        int i5 = i4;
                        RecentTasksController recentTasksController2 = recentTasksController;
                        Integer num = (Integer) obj2;
                        switch (i5) {
                            case 0:
                                recentTasksController2.removeSplitPair(num.intValue());
                                break;
                            default:
                                recentTasksController2.removeSplitPair(num.intValue());
                                break;
                        }
                    }
                });
                final int i5 = 1;
                stageCoordinator.mSideStage.doForAllChildTasks(new Consumer() { // from class: com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda19
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        int i52 = i5;
                        RecentTasksController recentTasksController2 = recentTasksController;
                        Integer num = (Integer) obj2;
                        switch (i52) {
                            case 0:
                                recentTasksController2.removeSplitPair(num.intValue());
                                break;
                            default:
                                recentTasksController2.removeSplitPair(num.intValue());
                                break;
                        }
                    }
                });
                break;
            case 1:
                stageCoordinator.onFoldedStateChanged(((Boolean) obj).booleanValue());
                break;
            default:
                RecentTasksController recentTasksController2 = (RecentTasksController) obj;
                Rect bounds1 = stageCoordinator.mSplitLayout.getBounds1();
                Rect bounds2 = stageCoordinator.mSplitLayout.getBounds2();
                int topVisibleChildTaskId = stageCoordinator.mMainStage.getTopVisibleChildTaskId();
                int topVisibleChildTaskId2 = stageCoordinator.mSideStage.getTopVisibleChildTaskId();
                if (stageCoordinator.mSideStagePosition == 0) {
                    i2 = topVisibleChildTaskId;
                    i = topVisibleChildTaskId2;
                } else {
                    i = topVisibleChildTaskId;
                    i2 = topVisibleChildTaskId2;
                }
                SplitBounds splitBounds = new SplitBounds(i, i2, stageCoordinator.mSplitLayout.calculateCurrentSnapPosition(), bounds1, bounds2);
                if (topVisibleChildTaskId != -1 && topVisibleChildTaskId2 != -1) {
                    if (topVisibleChildTaskId != topVisibleChildTaskId2) {
                        if (recentTasksController2.mSplitTasks.get(topVisibleChildTaskId, -1) != topVisibleChildTaskId2 || !((SplitBounds) recentTasksController2.mTaskSplitBoundsMap.get(Integer.valueOf(topVisibleChildTaskId))).equals(splitBounds)) {
                            recentTasksController2.removeSplitPair(topVisibleChildTaskId);
                            recentTasksController2.removeSplitPair(topVisibleChildTaskId2);
                            recentTasksController2.mTaskSplitBoundsMap.remove(Integer.valueOf(topVisibleChildTaskId));
                            recentTasksController2.mTaskSplitBoundsMap.remove(Integer.valueOf(topVisibleChildTaskId2));
                            recentTasksController2.mSplitTasks.put(topVisibleChildTaskId, topVisibleChildTaskId2);
                            recentTasksController2.mSplitTasks.put(topVisibleChildTaskId2, topVisibleChildTaskId);
                            recentTasksController2.mTaskSplitBoundsMap.put(Integer.valueOf(topVisibleChildTaskId), splitBounds);
                            recentTasksController2.mTaskSplitBoundsMap.put(Integer.valueOf(topVisibleChildTaskId2), splitBounds);
                            recentTasksController2.notifyRecentTasksChanged();
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENT_TASKS_enabled[1]) {
                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENT_TASKS, 8682039884091831585L, 5, Long.valueOf(topVisibleChildTaskId), Long.valueOf(topVisibleChildTaskId2), String.valueOf(splitBounds));
                            }
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -5970869964711314248L, 5, Long.valueOf(i), Long.valueOf(i2));
                                break;
                            }
                        }
                    } else {
                        recentTasksController2.getClass();
                        break;
                    }
                }
                break;
        }
    }
}
