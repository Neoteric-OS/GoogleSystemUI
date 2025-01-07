package com.android.systemui.wmshell;

import android.graphics.Color;
import com.android.systemui.statusbar.CommandQueue;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.recents.RecentTasks;
import com.android.wm.shell.recents.RecentTasksController;
import com.android.wm.shell.recents.RecentTasksController$RecentTasksImpl$$ExternalSyntheticLambda1;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WMShell$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ WMShell$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                CommandQueue commandQueue = (CommandQueue) obj2;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                synchronized (commandQueue.mLock) {
                    commandQueue.mHandler.obtainMessage(3080192, booleanValue ? 1 : 0, 0).sendToTarget();
                }
                return;
            default:
                RecentTasksController.RecentTasksImpl recentTasksImpl = (RecentTasksController.RecentTasksImpl) ((RecentTasks) obj2);
                HandlerExecutor handlerExecutor = (HandlerExecutor) RecentTasksController.this.mMainExecutor;
                handlerExecutor.execute(new RecentTasksController$RecentTasksImpl$$ExternalSyntheticLambda1(0, recentTasksImpl, (Color) obj));
                return;
        }
    }
}
