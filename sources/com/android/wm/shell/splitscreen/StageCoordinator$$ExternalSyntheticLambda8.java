package com.android.wm.shell.splitscreen;

import android.app.ActivityManager;
import android.util.ArrayMap;
import com.android.wm.shell.recents.RecentTasksController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageCoordinator$$ExternalSyntheticLambda8 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ StageCoordinator$$ExternalSyntheticLambda8(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                ((RecentTasksController) obj).removeSplitPair(((ActivityManager.RunningTaskInfo) obj2).taskId);
                break;
            case 1:
                ((RecentTasksController) obj).removeSplitPair(((ActivityManager.RunningTaskInfo) obj2).taskId);
                break;
            default:
                ArrayMap arrayMap = (ArrayMap) obj2;
                RecentTasksController recentTasksController = (RecentTasksController) obj;
                for (int size = arrayMap.keySet().size() - 1; size >= 0; size--) {
                    recentTasksController.removeSplitPair(((Integer) arrayMap.keyAt(size)).intValue());
                }
                break;
        }
    }
}
