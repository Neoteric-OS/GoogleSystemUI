package com.android.wm.shell.transition;

import android.window.TransitionInfo;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DefaultTransitionHandler$$ExternalSyntheticLambda6 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        TransitionInfo.Change change = (TransitionInfo.Change) obj;
        return change.getTaskInfo() != null && change.getTaskInfo().getWindowingMode() == 6;
    }
}
