package com.android.systemui.navigationbar;

import android.graphics.Rect;
import com.android.wm.shell.pip.Pip;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class TaskbarDelegate$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TaskbarDelegate f$0;

    public /* synthetic */ TaskbarDelegate$$ExternalSyntheticLambda0(TaskbarDelegate taskbarDelegate, int i) {
        this.$r8$classId = i;
        this.f$0 = taskbarDelegate;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        TaskbarDelegate taskbarDelegate = this.f$0;
        switch (i) {
            case 0:
                ((Pip) obj).removePipExclusionBoundsChangeListener(taskbarDelegate.mPipListener);
                break;
            case 1:
                taskbarDelegate.mEdgeBackGestureHandler.mPipExcludedBounds.set((Rect) obj);
                break;
            default:
                ((Pip) obj).addPipExclusionBoundsChangeListener(taskbarDelegate.mPipListener);
                break;
        }
    }
}
