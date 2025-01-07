package com.android.systemui.navigationbar.gestural;

import android.graphics.Region;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.desktopmode.DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1;
import com.android.wm.shell.pip.Pip;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class EdgeBackGestureHandler$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ EdgeBackGestureHandler f$0;

    public /* synthetic */ EdgeBackGestureHandler$$ExternalSyntheticLambda1(EdgeBackGestureHandler edgeBackGestureHandler, int i) {
        this.$r8$classId = i;
        this.f$0 = edgeBackGestureHandler;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        EdgeBackGestureHandler edgeBackGestureHandler = this.f$0;
        switch (i) {
            case 0:
                edgeBackGestureHandler.getClass();
                edgeBackGestureHandler.mIsInPip = ((Boolean) obj).booleanValue();
                break;
            case 1:
                ((Pip) obj).setOnIsInPipStateChangedListener(edgeBackGestureHandler.mOnIsInPipStateChangedListener);
                break;
            case 2:
                EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1 = edgeBackGestureHandler.mDesktopCornersChangedListener;
                Executor executor = edgeBackGestureHandler.mUiThreadContext.executor;
                DesktopTasksController desktopTasksController = DesktopTasksController.this;
                ((HandlerExecutor) desktopTasksController.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1(desktopTasksController, edgeBackGestureHandler$$ExternalSyntheticLambda1, executor, 1));
                break;
            case 3:
                edgeBackGestureHandler.mGestureBlockingActivityRunning.set(((Boolean) obj).booleanValue());
                break;
            default:
                edgeBackGestureHandler.mDesktopModeExcludeRegion.set((Region) obj);
                break;
        }
    }
}
