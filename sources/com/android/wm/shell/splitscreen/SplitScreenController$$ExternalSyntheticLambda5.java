package com.android.wm.shell.splitscreen;

import android.window.WindowContainerToken;
import com.android.wm.shell.recents.RecentTasksController;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class SplitScreenController$$ExternalSyntheticLambda5 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ WindowContainerToken f$0;

    public /* synthetic */ SplitScreenController$$ExternalSyntheticLambda5(int i, WindowContainerToken windowContainerToken) {
        this.$r8$classId = i;
        this.f$0 = windowContainerToken;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        int i = this.$r8$classId;
        WindowContainerToken windowContainerToken = this.f$0;
        RecentTasksController recentTasksController = (RecentTasksController) obj;
        switch (i) {
        }
        return recentTasksController.getTopRunningTask(windowContainerToken);
    }
}
