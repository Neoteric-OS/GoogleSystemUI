package com.android.systemui.animation;

import android.util.ArrayMap;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import com.android.wm.shell.shared.TransitionUtil;
import java.util.ArrayList;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RemoteAnimationTargetCompat {
    public static RemoteAnimationTarget[] wrap(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, ArrayMap arrayMap, Predicate predicate) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if (!TransitionUtil.isOrderOnly(change) && predicate.test(change)) {
                arrayList.add(TransitionUtil.newTarget(change, RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, i), false, transitionInfo, transaction, arrayMap));
            }
        }
        return (RemoteAnimationTarget[]) arrayList.toArray(new RemoteAnimationTarget[arrayList.size()]);
    }
}
