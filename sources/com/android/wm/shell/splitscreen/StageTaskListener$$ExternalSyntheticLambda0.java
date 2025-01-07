package com.android.wm.shell.splitscreen;

import android.app.ActivityManager;
import android.os.IBinder;
import android.window.WindowContainerToken;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageTaskListener$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ StageTaskListener$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                return ((ActivityManager.RunningTaskInfo) obj).token.asBinder() == ((IBinder) obj2);
            default:
                return ((ActivityManager.RunningTaskInfo) obj).token.equals((WindowContainerToken) obj2);
        }
    }
}
