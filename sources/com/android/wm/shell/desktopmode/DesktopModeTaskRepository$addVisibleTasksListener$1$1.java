package com.android.wm.shell.desktopmode;

import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeTaskRepository$addVisibleTasksListener$1$1 implements Runnable {
    public final /* synthetic */ int $it;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ int $visibleTaskCount;
    public final /* synthetic */ DesktopModeTaskRepository.VisibleTasksListener $visibleTasksListener;

    public /* synthetic */ DesktopModeTaskRepository$addVisibleTasksListener$1$1(DesktopModeTaskRepository.VisibleTasksListener visibleTasksListener, int i, int i2, int i3) {
        this.$r8$classId = i3;
        this.$visibleTasksListener = visibleTasksListener;
        this.$it = i;
        this.$visibleTaskCount = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.$visibleTasksListener.onTasksVisibilityChanged(this.$it, this.$visibleTaskCount);
                break;
            default:
                this.$visibleTasksListener.onTasksVisibilityChanged(this.$it, this.$visibleTaskCount);
                break;
        }
    }
}
