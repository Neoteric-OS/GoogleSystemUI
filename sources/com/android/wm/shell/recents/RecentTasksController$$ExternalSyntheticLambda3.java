package com.android.wm.shell.recents;

import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.recents.RecentTasksController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RecentTasksController$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ RecentTasksController$$ExternalSyntheticLambda3(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                RecentTasksController recentTasksController = (RecentTasksController) obj2;
                recentTasksController.getClass();
                ((DesktopModeTaskRepository) obj).activeTasksListeners.add(recentTasksController);
                break;
            case 1:
                ((RecentTasksController) obj).registerRecentTasksListener(((RecentTasksController.IRecentTasksImpl) obj2).mRecentTasksListener);
                break;
            default:
                ((RecentTasksController.IRecentTasksImpl) obj2).mListener.unregister();
                break;
        }
    }
}
