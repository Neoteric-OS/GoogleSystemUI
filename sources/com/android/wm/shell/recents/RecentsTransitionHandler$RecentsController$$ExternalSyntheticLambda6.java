package com.android.wm.shell.recents;

import android.app.ActivityManager;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RecentsTransitionHandler$RecentsController$$ExternalSyntheticLambda6 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = RecentsTransitionHandler.RecentsController.$r8$clinit;
        return ((ActivityManager.RunningTaskInfo) obj).getActivityType() == 2;
    }
}
