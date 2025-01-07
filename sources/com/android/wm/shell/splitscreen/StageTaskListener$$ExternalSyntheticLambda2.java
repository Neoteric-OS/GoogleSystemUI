package com.android.wm.shell.splitscreen;

import android.app.ActivityManager;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageTaskListener$$ExternalSyntheticLambda2 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) obj;
        switch (this.$r8$classId) {
            case 0:
                return runningTaskInfo.topActivityInfo != null;
            case 1:
                return runningTaskInfo.isVisible && runningTaskInfo.isVisibleRequested;
            default:
                return runningTaskInfo.isFocused;
        }
    }
}
